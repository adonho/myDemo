//*************************************************************************
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*                                                                       *
//*                COPYRIGHT (C) FINANCING CITY CORPORATION 2012          *
//*    ALL RIGHTS RESERVED BY FINANCING CITY CORPORATION. THIS PROGRAM    *
//* MUST BE USED  SOLELY FOR THE PURPOSE FOR WHICH IT WAS FURNISHED BY    *
//* FINANCING CITY CORPORATION. NO PART OF THIS PROGRAM MAY BE REPRODUCED *
//* OR DISCLOSED TO OTHERS,IN ANY FORM, WITHOUT THE PRIOR WRITTEN         *
//* PERMISSION OF FINANCING CITY CORPORATION. USE OF COPYRIGHT NOTICE     *
//* DOES NOT EVIDENCE PUBLICATION OF THE PROGRAM.                         *
//*                  FINANCING CITY CONFIDENTIAL AND PROPRIETARY          *
//*************************************************************************

package com.blossom.ap;

import com.alibaba.dubbo.config.ServiceConfig;
import com.alibaba.dubbo.container.spring.SpringContainer;
import com.blossom.ap.event.DubboInitedEvent;
import com.blossom.ap.monitor.DataSourceMonitorTask;
import com.blossom.common.ConfigManager;
import com.blossom.common.ConfigReader;
import com.blossom.msg.MessageManager;
import com.blossom.msg.MyThreadFactory;
import com.blossom.msg.TelegramWorkTask;
import com.blossom.msg.bean.TelegramInfo;
import com.blossom.msg.process.HandleManager;
import com.blossom.msg.util.TelegramInfoUtil;
import com.blossom.statusmanager.ApStatusManagerFactory;
import com.blossom.statusmanager.HouseStatusManager;
import net.rubyeye.xmemcached.MemcachedClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * DubboInit
 *
 * @author zhangshsh
 *         作成 2014/01/17
 */
public class DubboInit implements ApplicationListener, ApplicationEventPublisherAware {

    /**
     * The log4j logger
     */
    private static final Logger log = Logger.getLogger(DubboInit.class);

    private ApplicationEventPublisher applicationEventPublisher;

    /**
     * 构造器
     */
    public DubboInit() {
    }

    /**
     * 初始化
     */
    public void init() {
        log.info("begin.");

        DataManager dataManager = DataManager.getInstance();
        dataManager.initializePath();

        String configDir = dataManager.getCfgPath();

        ConfigManager configManager = ConfigManager.getInstance();
        // (1) init server-config.xml
        configManager.init(configDir + "server-config.xml");

        // 取得  SpringContainer的 applicationContext
        ClassPathXmlApplicationContext applicationContext = SpringContainer.getContext();
        // 取得  memcachedClient
        MemcachedClient memcachedClient = (MemcachedClient) applicationContext.getBean("memCachedClientApStatus");
        // set connectTimeout as 30s, default is 60 seconds
        memcachedClient.setConnectTimeout(30000L);
        // set OpTimeout as 5s, default is 1 seconds
        memcachedClient.setOpTimeout(5000L);
        // set EnableHeartBeat as true,  default is true.
        memcachedClient.setEnableHeartBeat(true);

        // (2) HouseStatusManager init
        HouseStatusManager houseStatusManager = HouseStatusManager.getInstance();
        houseStatusManager.setMemcachedClient(memcachedClient);

        // (3) ApStatusManagerFactory init
        ApStatusManagerFactory factory = ApStatusManagerFactory.getInstance();
        factory.setMemcachedClient(memcachedClient);
        // init ap-status-config.xml
        factory.init(configDir + "ap-status-config.xml");

        // msgService
        int msgServiceEnable = ConfigReader.getMsgServiceEnable();
        if( msgServiceEnable == 1){
            //启用 Msg Service
           this.enableMsgService();
            log.warn("启用了 Msg Service。");
        }

        boolean dubboMasterFlag = ConfigReader.getDubboMasterFlag();
        if(dubboMasterFlag == false){
            // 从库需要导出的dubbo service名列表
            String[] interfaceNameList = this.getSlaveExportedServiceNameList();
            // 导出dubbo service
            this.exportSpecialService(interfaceNameList);

            //  DataSource Monitor Task
            DataSourceMonitorTask dataSourceMonitorTask = new DataSourceMonitorTask();
            dataSourceMonitorTask.init(configDir + "dataSource-monitor.xml");

            Thread thread = new Thread(dataSourceMonitorTask);
            thread.start();
        }

        log.info("end.");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStartedEvent) {
            log.warn("Spring容器启动完成，将进行初始化。");

            try {
                //
                this.init();
                log.warn("ap-dubbo-server 启动完成。");

                DubboInitedEvent dubboInitedEvent = new DubboInitedEvent("dubboInitedEvent");
                //
                this.applicationEventPublisher.publishEvent(dubboInitedEvent);

            } catch (Exception e) {
                log.error("", e);
            }

        }
    }

    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 取得 从库需要导出的dubbo service名列表
     * @return
     */
    public String[] getSlaveExportedServiceNameList(){
        // get serviceNameList
        String serviceNameList = ConfigReader.getSlaveExportedServiceNameList();

        serviceNameList = serviceNameList.replaceAll ("\r","");
        serviceNameList = serviceNameList.replaceAll ("\n","");
        serviceNameList = serviceNameList.replaceAll (" ","");

        String[] serviceNames = serviceNameList.split(",");
        return  serviceNames;
    }

    /**
     *导出特定的dubbo service
     * @param interfaceNameList interfaceName List
     */
    public void exportSpecialService(String[] interfaceNameList) {
        ClassPathXmlApplicationContext applicationContext = SpringContainer.getContext();

        int len = interfaceNameList.length;
        String interfaceName = null;
        Object object = null;
        ServiceConfig serviceConfig = null;
        for (int i = 0; i < len; i++) {
            interfaceName = interfaceNameList[i];
            if(interfaceName == null || interfaceName.trim().length() == 0){
                  continue;
            }
            try {
                object = applicationContext.getBean(interfaceName);
                if (object instanceof ServiceConfig) {
                    serviceConfig = (ServiceConfig) object;

                    serviceConfig.setExport(true);
                    //
                    serviceConfig.export();
                }
            } catch (NoSuchBeanDefinitionException e) {
                // warning
                log.warn(e.getMessage());
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    /**
     * 注销特定的dubbo service
     * @param interfaceNameList interfaceName List
     */
    public static void unExportSpecialService(String[] interfaceNameList) {
        ClassPathXmlApplicationContext applicationContext = SpringContainer.getContext();

        int len = interfaceNameList.length;
        String interfaceName = null;
        Object object = null;
        ServiceConfig serviceConfig = null;
        for (int i = 0; i < len; i++) {
            interfaceName = interfaceNameList[i];
            if(interfaceName == null || interfaceName.trim().length() == 0){
                continue;
            }
            try {
                object = applicationContext.getBean(interfaceName);
                if (object instanceof ServiceConfig) {
                    serviceConfig = (ServiceConfig) object;
                    // 注销
                    serviceConfig.unexport();
                }
            } catch (NoSuchBeanDefinitionException e) {
                // warning
                log.warn(e.getMessage());
            } catch (Exception e) {
                log.error("", e);
            }
        }
    }

    /**
     *   启用 Msg Service
      */
    private void enableMsgService(){
        DataManager dataManager = DataManager.getInstance();
        String configDir = dataManager.getCfgPath();

        String strMsgServiceUrl = ConfigReader.getMsgServiceUrl();
        String msgServiceUser =  ConfigReader.getMsgServiceUser();
        String msgServicePassword =  ConfigReader.getMsgServicePassword();
        String receiveDestinationQueues =  ConfigReader.getMsgServiceReceiveDestinationQueues();
        String receiveDestinationTopics = ConfigReader.getMsgServiceReceiveDestinationTopics();

        // messageManager
        MessageManager messageManager = MessageManager.getInstance();
        // init
        messageManager.init(strMsgServiceUrl,msgServiceUser,msgServicePassword,receiveDestinationQueues,receiveDestinationTopics);

        // handleManager
        HandleManager handleManager = HandleManager.getInstance();
        handleManager.init(configDir + "handle-list.xml");

        // telegramWorkTask
        TelegramWorkTask telegramWorkTask = TelegramWorkTask.getInstance();
        // workerThreadCount
        int workerThreadInitCount = 5;
        int workerThreadMaxCount = 100;

        // create TelegramWorker ThreadPool
        MyThreadFactory myThreadFactory = new MyThreadFactory("TelegramWorker");
        ThreadPoolExecutor telegramWorkerThreadPool = (ThreadPoolExecutor) Executors.newCachedThreadPool(myThreadFactory);
        telegramWorkerThreadPool.setCorePoolSize(workerThreadInitCount);
        telegramWorkerThreadPool.setMaximumPoolSize(workerThreadMaxCount);
        //
        for (int i = 0; i < workerThreadInitCount; i++) {
            //
            telegramWorkerThreadPool.execute(telegramWorkTask);
        }
    }

    private void msgServiceTest(){
        TelegramInfo telegramInfo = null;
        //
        telegramInfo = TelegramInfoUtil.createTelegramInfo("queue_dubbo", TelegramInfoUtil.TELEGRAMINFO_TYPE_REQUEST,
                "zssTestMsg_REQUEST_A");
        telegramInfo.getHeader().setHandleId("A001");
        //
        MessageManager messageManager = MessageManager.getInstance();
        TelegramInfo resTele = messageManager.postSyncTelegramInfo(telegramInfo,30);
        if (resTele != null) {
            String content = resTele.getBody().getContent().toString();
            log.error(content);
        }
    }
}
