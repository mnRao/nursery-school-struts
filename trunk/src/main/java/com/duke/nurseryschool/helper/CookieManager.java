package com.duke.nurseryschool.helper;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class CookieManager {
	private static CookieManager instance = null;

	public static CookieManager getInstance() {
		if (instance == null) {
			instance = new CookieManager();
		}

		return instance;
	}

	public void setLoginCookie(String username) {
		Logger.getLogger(this.getClass()).info("Registering new cookie ...");
		Cookie cookie = new Cookie(Constant.TAG.COOKIE_USERNAME, username);
		cookie.setMaxAge(Constant.COOKIE_TIME_OUT);
		ServletActionContext.getResponse().addCookie(cookie);
	}

	public String getUserInCookies() {
		String user = Constant.EMPTY_STRING;
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constant.TAG.COOKIE_USERNAME)) {
					user = cookie.getValue();
					break;
				}
			}
		}
		return user;
	}

	public void deleteLoginCookie() {
		Cookie[] cookies = ServletActionContext.getRequest().getCookies();
		if (cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constant.TAG.COOKIE_USERNAME)) {
					cookie.setMaxAge(0);
					cookie.setValue(Constant.EMPTY_STRING);
					ServletActionContext.getResponse().addCookie(cookie);
					break;
				}
			}
		}
	}
}
