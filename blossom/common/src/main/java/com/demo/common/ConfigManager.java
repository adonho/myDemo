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
package com.demo.common;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * config properties 文件管理
 * 初始化时将会读入文件内容，当config properties 文件被外部修改后，ConfigManager将会自动重新读入文件。
 *
 * @author zhangshsh
 *         2013/05/07
 */
public class ConfigManager implements Runnable {
	protected static final Logger log = Logger.getLogger(ConfigManager.class);

	public static final String KEY_ENV_FILE_PATH = "envFilePath";

	/**
	 * instance
	 */
	private static ConfigManager instance = null;

	/**
	 * config properties 文件名
	 */
	private String propertyFileName = null;

	/**
	 * config properties 文件的最后被修改的时间
	 */
	private long fileLastTimeStamp = 0;

	/**
	 * properties
	 */
	private Properties properties = new Properties();

	/**
	 * 取得   ConfigManager instance
	 *
	 * @return ConfigManager instance
	 */
	public static ConfigManager getInstance() {
		if (instance == null){
			synchronized (ConfigManager.class){
				if (instance == null){
					instance = new ConfigManager();
				}
			}
		}
		return instance;
	}

	/**
	 * 构造函数
	 */
	private ConfigManager() {
	}

	/**
	 * 初始化
	 *
	 * @param fileName config properties 文件名
	 */
	public synchronized void init(String fileName) {

		if (this.propertyFileName != null) {
			log.error("config properties 文件名[" + fileName + "]");
			throw new RuntimeException("has loaded config file[" +
					this.propertyFileName + "]! repeat config file[" + fileName + "]");
		}

		log.info("config properties 文件名[" + fileName + "]");

		// config properties 文件名
		this.propertyFileName = fileName;

		// 设定 config properties 文件的最后被修改的时间
		isFileChanged();

		// 读入config properties 文件的内容
		this.loadFile();

		// 开始新Thread 监测config properties 文件的变化
		new Thread(this).start();
	}

	/**
	 * 读入config properties 文件的内容
	 */
	public void loadFile() {
		InputStream inputStream = null;
		try {
			properties.clear();

			inputStream = new FileInputStream(this.propertyFileName);
			//properties.load(inputStream);
			properties.loadFromXML(inputStream);

			//
			this.replaceAllEnvVar(properties);

			log.info("config properties 文件读入完成。");
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					//ingnore error
				}
			}
		}
	}

	/**
	 * 取得 PropertyValue
	 *
	 * @param key key
	 * @return PropertyValue
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	/**
	 * 监测config properties 文件的变化
	 */
	public void run() {
		while (true) {
			try {
				if (this.isFileChanged()) {
					log.info("config properties 文件 被修改了，将重新读入。");

					loadFile();
				}

				Thread.sleep(5000);// sleep 5 seconds
			} catch (Exception e) {
				log.error(e.toString());
			}
		}
	}

	/**
	 * 检查config properties 文件的变化
	 *
	 * @return true：变化了; false:无变化
	 */
	public boolean isFileChanged() {
		boolean result = false;
		try {
			File file = new File(propertyFileName);
			long tempTimeStamp = file.lastModified();
			if (tempTimeStamp != this.fileLastTimeStamp) {
				this.fileLastTimeStamp = tempTimeStamp;
				result = true;
			}
		} catch (Exception e) {
			log.error(e.toString());
		}
		return result;
	}

	/**
	 * 读入config properties 文件的内容
	 */
	private Properties loadEnvFile() {
		Properties envProperties = new Properties();
		InputStream inputStream = null;
		try {
			String strEnvFilePath = System.getenv(KEY_ENV_FILE_PATH);
			inputStream = new FileInputStream(strEnvFilePath);
			envProperties.load(inputStream);

			log.info("config properties 文件读入完成。");
		} catch (Exception e) {
			log.error("", e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					//ingnore error
				}
			}
		}
		return envProperties;
	}

	private void replaceAllEnvVar(Properties properties) {
		Properties envProperties = this.loadEnvFile();

		String strKey = null;
		String strValue = null;

		for (Enumeration e = properties.keys(); e.hasMoreElements(); ) {
			strKey = (String) e.nextElement();

			strValue = properties.getProperty(strKey);
			if (strValue.contains("${")) {
				strValue = this.replaceEnvVarValue(strValue, envProperties);
				//
				properties.put(strKey, strValue);
			}
		}
	}

	private String replaceEnvVarValue(String strValue, Properties envProperties) {
		String result = strValue;

		String strEnvKey = null;
		String strEnvValue = null;
		int posBegin = 0;
		int posEnd = 0;
		while (result.contains("${")) {
			posBegin = result.indexOf("${");
			posEnd = result.indexOf("}", posBegin);

			strEnvKey = result.substring(posBegin + 2, posEnd);
			// get EnvValue
			strEnvValue = envProperties.getProperty(strEnvKey);
			if (strEnvValue == null) {
				log.warn("不能取得环境变量[" + strEnvKey + "]的值.");
				strEnvValue = "";
			}
			//  replace
			result = result.replace("${" + strEnvKey + "}", strEnvValue);
		}
		return result;
	}

}
