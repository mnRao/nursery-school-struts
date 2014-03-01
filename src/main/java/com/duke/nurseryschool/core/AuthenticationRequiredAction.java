package com.duke.nurseryschool.core;

import com.duke.nurseryschool.core.interceptors.UserAware;

/**
 * Base class for authentication-required actions
 */
public class AuthenticationRequiredAction extends CoreAction implements
		UserAware {
	private String	user;

	@Override
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser(String user) {
		return this.user;
	}
}
