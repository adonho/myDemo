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
package com.blossom.ap.monitor;

import com.blossom.ap.DubboInit;
import com.blossom.ap.monitor.bean.DataSourceMonitorBean;
import com.blossom.ap.monitor.util.XMLUtil;
import com.blossom.common.ConfigReader;
import com.mobile.framework.util.ServiceLocator;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * DataSource监视
 *
 * @author zhangshsh
 *         Created 2015/04/10
 */
public class DataSourceMonitorTask implements Runnable {
    /**
     * The log4j logger
     */
    private static final Logger log = Logger.getLogger(DataSourceMonitorTask.class);

    private ArrayList<DataSourceMonitorBean> dataSourceMonitorList = new  ArrayList<DataSourceMonitorBean>();

    /**
     * 从库dataSource监视间隔（毫秒）
     */
    private int interval = 10 *1000;

    /**
     * 从库dataSource监视最大故障次数
     */
    private int maxFailureCount  = 5;

    public DataSourceMonitorTask(){
    }

    public void init(String configFileName){
        this.dataSourceMonitorList = XMLUtil.getInitedDataSourceMonitorBean(configFileName);
        // 取得 从库dataSource监视间隔（秒）, 转为毫秒
        this.interval = ConfigReader.getSlaveDataSourceMonitorInterval() * 1000;
        // 取得 从库dataSource监视最大故障次数
        this.maxFailureCount = ConfigReader.getSlaveDataSourceMonitorMaxFailureCount();
    }

    @Override
    public void run() {
         while (true){
             //
             this.process();

             try{
                Thread.sleep(this.interval);
             }catch (Exception e){
               //
             }
         }
    }

    private void process(){
        int len = this.dataSourceMonitorList.size();
        DataSourceMonitorBean bean = null;
        String dataSourceId = null;
        for(int i = 0; i < len; i++){
            bean =  this.dataSourceMonitorList.get(i);

            if(bean.isUnExported()){
                continue;
            }

            dataSourceId = bean.getDataSourceId();
            int failureCount =  bean.getFailureCount();
            //  checkDataSource
            boolean isOK = checkDataSource(dataSourceId);
            if(isOK){
                // reset
                bean.setFailureCount(0);
            }else{
                failureCount++;
                bean.setFailureCount(failureCount);
            }

            if(failureCount >= this.maxFailureCount){
                //
                ArrayList<String> serviceNameList = bean.getServiceNameList();
                String[] serviceNameArray  = new String[serviceNameList.size()];
                bean.getServiceNameList().toArray(serviceNameArray );
                // unExportSpecialService
                DubboInit.unExportSpecialService(serviceNameArray);
                //
                bean.setUnExported(true);

                log.warn("dataSource[" + dataSourceId +"]故障次数大于监视最大故障次数["
                        +  this.maxFailureCount + "],注销了本机的相关service.");
            }
        }
    }

    private  boolean checkDataSource(String dataSourceId){
        boolean result = false;
        Connection dbCn = null;
        try{
            BasicDataSource dataSource = (BasicDataSource)ServiceLocator.getBean(dataSourceId);
            //dbCn = dataSource.getConnection();

            String strUrl = dataSource.getUrl();
            // set connectTimeout and socketTimeout (单位为毫秒)
            strUrl = strUrl + "&connectTimeout=5000&socketTimeout=1000";

            String strUserName = dataSource.getUsername();
            String strPassword = dataSource.getPassword();
            // 使用 JDBC来取得 DB连接
            dbCn = DriverManager.getConnection(strUrl, strUserName, strPassword);

            Statement stmt = dbCn.createStatement();
            String strSql = "select monitor_value from tb_system_monitor where monitor_key='DB_READ'";
            ResultSet rs = stmt.executeQuery(strSql);
            if(rs.next()){
               rs.getInt("monitor_value");
            }
            result = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }finally {
            if(dbCn != null){
                try{
                    dbCn.close();
                } catch (Exception e) {
                    // ignore error
                }
            }
        }
        return result;
    }
}
