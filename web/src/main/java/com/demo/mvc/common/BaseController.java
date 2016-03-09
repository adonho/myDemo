package com.demo.mvc.common;

import com.demo.common.util.MessageUtil;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by adon on 2016/1/30 0030.
 */
public abstract class BaseController extends AbstractController {

	protected Logger log = Logger.getLogger(this.getClass());

	protected ModelAndView modelAndView;

	/**
	 * for Internationalization
	 *
	 * @param request
	 * @return
	 */
	public static Locale getLocale(HttpServletRequest request) {
		Locale locale = null;
		HttpSession session = request.getSession();
		if (session != null) {
			locale = (Locale) session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
			if (locale == null) {
				String localeName = (String) session.getServletContext().getAttribute("localeName");
				if ((localeName != null) && (!"".equals(localeName.trim())) && (localeName.indexOf("_") > -1)) {
					String[] localeNames = localeName.split("_");
					locale = new Locale(localeNames[0], localeNames[1]);
				}
			}
		}
		if (locale == null) {
			locale = request.getLocale();
			if (locale == null) {
				return Locale.CHINA;
			}
		}
		return locale;
	}

	/**
	 * for Internationalization
	 *
	 * @param key
	 * @param locale
	 * @param params
	 * @return
	 */
	public String getMessage(String key, Locale locale, String... params) {
		return MessageUtil.getMessage(MessageUtil.WEB_RESOURCE_NAME,
				this.getClass().getSimpleName() + "." + key, locale, params);
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return null;
	}

}
