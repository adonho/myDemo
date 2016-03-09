package com.demo.common.exception;

import java.util.Locale;

/**
 * Created by adon on 2016/3/4 0004.
 */
public class UncheckedException extends IllegalArgumentException {

	public UncheckedException() {
	}

	public UncheckedException(String s) {
		super(s);
	}

	public UncheckedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UncheckedException(Throwable cause) {
		super(cause);
	}

	public String getLocaleMessage(String prop, String prefixKey, Locale locale) {

		return getMessage();
	}

	public String getLocaleMessage(String prop, String prefixKey) {

		return getLocaleMessage(prop, prefixKey, Locale.CHINA);
	}

}
