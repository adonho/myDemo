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

import org.apache.log4j.Logger;

import java.io.File;

/**
 * DataManager
 *
 * @author zhangshsh
 *         作成 2013/06/17
 */
public class DataManager {
    /**
     * The log4j logger
     */
    private static final Logger log = Logger.getLogger(DataManager.class);
    /**
     * instance
     */
    private static DataManager instance = null;
    /**
     * root
     */
    private String root = null;
    /**
     * cfgPath
     */
    private String cfgPath = null;
    /**
     * dataPath
     */
    private String dataPath = null;

    /**
     * 取得  single instance
     *
     * @return DataManager
     */
    public synchronized static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;

    }

    /**
     * 构造器
     */
    private DataManager() {
    }

    /**
     * initialize Path
     */
    public void initializePath() {
        log.debug("begin.");

        root = System.getProperty("MyAppHome");
        if (!root.trim().endsWith(File.separator)) {
            root = root.trim() + File.separator;
        }
        cfgPath = root + "config/";
        dataPath = root + "data/";

        log.info("MyAppHome=" + root);
        log.debug("end.");
    }

    /**
     * 取得 root 目录
     *
     * @return root 目录
     */
    public String getRoot() {
        return root;
    }

    /**
     * 取得 cfgPath
     *
     * @return cfgPath
     */
    public String getCfgPath() {
        return cfgPath;
    }

    /**
     * 取得 dataPath
     *
     * @return dataPath
     */
    public String getDataPath() {
        return dataPath;
    }
}