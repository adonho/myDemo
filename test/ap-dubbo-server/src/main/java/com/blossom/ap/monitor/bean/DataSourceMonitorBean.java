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
package com.blossom.ap.monitor.bean;

import java.util.ArrayList;
import java.util.Date;

/**
 * DataSource监视信息
 *
 * @author zhangshsh
 *         Created 2015/04/10
 */
public class DataSourceMonitorBean implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * dataSourceId
     */
    private String dataSourceId;

    private ArrayList<String> serviceNameList = new  ArrayList<String>();

    private boolean unExported = false;

    /**
     * failure Count
     */
    private int failureCount = 0 ;

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public ArrayList<String> getServiceNameList() {
        return serviceNameList;
    }

    public void setServiceNameList(ArrayList<String> serviceNameList) {
        this.serviceNameList = serviceNameList;
    }

    public boolean isUnExported() {
        return unExported;
    }

    public void setUnExported(boolean unExported) {
        this.unExported = unExported;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }
}
