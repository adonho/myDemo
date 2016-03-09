package com.demo.common.util;

import org.apache.log4j.Logger;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

/**
 * config
 */
public class Config {
	protected static final Logger LOG = Logger.getLogger(Config.class);

	public static final String KEY_ENV_FILE_PATH = "envFilePath";

	public Config() {
	}

	public static String getProperty(String key) {
		return getProperty(key, "");
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	public static String getPathProperty(String key) {
		String s = props.getProperty(key, "");
		s = s.replace('/', File.separator.charAt(0));
		return s;
	}

	public static String getContextAbsolutePath() {
		return contextAbsolutePath;
	}

	public static void setContextAbsolutePath(String path) {
		contextAbsolutePath = path;
	}

	public static String getContextPath() {
		return contextPath;
	}

	public static void setContextPath(String contextPath) {
		contextPath = contextPath;
	}

	public static void setContext(ServletContext ctx) {
		context = ctx;
	}

	public static ServletContext getContext() {
		return context;
	}

	public static InitialContext getInitialContext() {
		try {
			if (ic == null) {
				Properties p = new Properties();
				p.put("java.naming.factory.initial", getProperty("jndi.InitialContextFactory"));
				p.put("java.naming.provider.url", getProperty("jndi.ProviderURL"));
				ic = new InitialContext(p);
			}
		} catch (Exception ne) {
			log.fatal(ne);
		}
		return ic;
	}

	/**
	 * 读入config properties 文件的内容
	 */
	public static Properties loadEnvFile() {
		Properties envProperties = new Properties();
		InputStream inputStream = null;
		try {
			String strEnvFilePath = System.getenv(KEY_ENV_FILE_PATH);
			inputStream = new FileInputStream(strEnvFilePath);
			envProperties.load(inputStream);

			LOG.info("config properties 文件读入完成。");
		} catch (Exception e) {
			LOG.error(e.toString());
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

	public static void replaceAllEnvVar(Properties properties) {
		Properties envProperties = loadEnvFile();

		String strKey = null;
		String strValue = null;

		for (Enumeration e = properties.keys(); e.hasMoreElements(); ) {
			strKey = (String) e.nextElement();

			strValue = properties.getProperty(strKey);
			if (strValue.contains("${")) {
				strValue = replaceEnvVarValue(strValue, envProperties);
				//
				properties.put(strKey, strValue);
			}
		}
		// 开发本地自定义图片根目录用，现网没有这个配置
		if (envProperties.containsKey("dev.local.webapp.path")) {
			properties.put("dev.local.webapp.path", envProperties.get("dev.local.webapp.path"));
		}
	}

	public static String replaceEnvVarValue(String strValue, Properties envProperties) {
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
				LOG.warn("不能取得环境变量[" + strEnvKey + "]的值.");
				strEnvValue = "";
			}
			//  replace
			result = result.replace("${" + strEnvKey + "}", strEnvValue);
		}
		return result;
	}

	protected static final Logger log = Logger.getLogger(Config.class);

	private static Properties props;

	private static String contextAbsolutePath = "";

	private static String contextPath = "";

	private static ServletContext context;

	private static InitialContext ic;

	public static final String ROWS_PER_PAGE = "10";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";

	static {
		props = new Properties();

		InputStream in = Config.class.getResourceAsStream("/config.xml");
		if (in != null) {
			try {
				props.loadFromXML(in);

				//
				replaceAllEnvVar(props);

				in.close();
			} catch (Exception e) {
				log.fatal("error occurs during load system configuration...", e);
				throw new RuntimeException(e.getMessage());
			}
		} else {
			log.fatal("config.xml not found. Please make sure the file is in the classpath");
			throw new RuntimeException("config.xml not found. Please make sure the file is in the classpath");
		}
	}
}
