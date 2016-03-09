package com.dome.ap.monitor;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Created by adon on 2016/3/5 0005.
 */
public class DataManager {

	private static final Logger log = Logger.getLogger(DataManager.class);

	/**
	 * cfgPath
	 */
	private String cfgPath = null;

	/**
	 * root
	 */
	private String root = null;

	private static DataManager instance = null;

	private DataManager(){}

	public static DataManager getInstance(){
		if (instance == null){
			synchronized (DataManager.class){
				if (instance == null){
					instance = new DataManager();
				}
			}
		}
		return instance;
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
		cfgPath = root + "config" + File.separator;

		log.info("MyAppHome=" + root);
		log.debug("end.");
	}

	public String getCfgPath() {
		return cfgPath;
	}

	public void setCfgPath(String cfgPath) {
		this.cfgPath = cfgPath;
	}

	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}
}
