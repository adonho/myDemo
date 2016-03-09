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

package com.blossom.ap.monitor.util;


import com.blossom.ap.monitor.bean.DataSourceMonitorBean;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;

/**
 * XMLUtil Util
 *
 * @author zhangshsh
 *         作成 2015/04/10
 */
public class XMLUtil {
    /**
     * LOG
     */
    private static Logger log = Logger.getLogger(XMLUtil.class);
    /**
     * ENCODING
     */
    public static final String ENCODING = "UTF-8";

    /**
     * 取得 xml Document
     *
     * @param xmlFileNam xmlFileNam
     * @return xml Document
     */
    public static Document getDocument(String xmlFileNam) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true); // never forget this!

            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(xmlFileNam);
        } catch (Exception e) {
            log.error("", e);
        }
        return doc;
    }

    /**
     * 从 xml文件中生成 初始的 PageStaticBean
     *
     * @param configFileName xml文件
     * @return 初始的 PageStaticBean
     */
    public static ArrayList<DataSourceMonitorBean> getInitedDataSourceMonitorBean(String configFileName) {
        ArrayList<DataSourceMonitorBean> resultList = new ArrayList<DataSourceMonitorBean>();
        try {
            Document doc = getDocument(configFileName);
            if(doc == null){
                log.warn(" 文件不存在或不能正确解析.fileName=" + configFileName);
                // 返回默认的
                return resultList;
            }

            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();
            String queryPath = "//dataSource";
            XPathExpression pathExpression = xpath.compile(queryPath);

            Object result = pathExpression.evaluate(doc, XPathConstants.NODESET);
            if (result == null) {
                log.warn(" 不能取得 site element.");
                // 返回默认的
                return resultList;
            }

            NodeList dataSourceNodes = (NodeList) result;
            Node dataSourceNode = null;
            DataSourceMonitorBean dataSourceMonitorBean = null;
            for (int i = 0; i < dataSourceNodes.getLength(); i++) {
                dataSourceNode = dataSourceNodes.item(i);
                if (dataSourceNode != null && dataSourceNode.getNodeType() == Node.ELEMENT_NODE) {
                    // dataSourceId
                    String strDataSourceId = dataSourceNode.getAttributes().getNamedItem("id").getNodeValue();

                    dataSourceMonitorBean = new DataSourceMonitorBean();
                    dataSourceMonitorBean.setDataSourceId(strDataSourceId);

                    NodeList itemNodes = dataSourceNode.getChildNodes();
                    Node itemNode = null;

                    String strItemName = null;

                    for (int j = 0; j < itemNodes.getLength(); j++) {

                        itemNode = itemNodes.item(j);
                        if (itemNode != null && itemNode.getNodeType() == Node.ELEMENT_NODE) {

                            // ItemName
                            strItemName = itemNode.getAttributes().getNamedItem("name").getNodeValue();
                            //
                            dataSourceMonitorBean.getServiceNameList().add(strItemName) ;
                        }
                    }
                    resultList.add(dataSourceMonitorBean);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return resultList;
    }

}
