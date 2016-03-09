package com.demo.common.util;

import org.apache.log4j.Logger;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by adon on 2016/3/4 0004.
 * <p>Internationalization.</p>
 */
public class MessageUtil {

	private static Logger logger = Logger.getLogger(MessageUtil.class);

	public static final String SERVICE_RESOURCE_NAME = "com.demo.service.Message";

	public static final String WEB_RESOURCE_NAME = "com.demo.mvc.Message";

	public static String getMessage(String resourceName, String key, Locale locale, String... params) {
		String msg = null;
		String k = resourceName + "_" + locale.toString();
		try {
			// 加载国际化资源
			ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceName, locale);

			if (resourceBundle != null) {
				msg = resourceBundle.getString(key);
				if ((msg != null) && (params != null)) {
					msg = MessageFormat.format(msg, params);
				}
			} else {
				throw new NoSuchFieldException("Cannot load file:"+k+".properties");
			}
		} catch (Exception ex) {
			logger.error(ex);
		}
		return msg;
	}

	public static String getMessage(String resourceBaseName, String prefix, String key, Locale locale, String... params) {
		return getMessage(resourceBaseName, prefix + "." + key, locale, params);
	}

	public static String getServiceMessage(Class clz, String key, Locale locale, String... params) {
		return getMessage(SERVICE_RESOURCE_NAME, clz.getSimpleName() + "." + key, locale, params);
	}

}

