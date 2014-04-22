package com.duke.nurseryschool.action.core;

import com.duke.nurseryschool.core.interceptors.UserAware;

/**
 * Base class for authentication-required actions
 */
public class AuthenticationRequiredAction extends CoreAction implements
		UserAware {
	private static final long serialVersionUID = -718474123858655169L;

	private String user;

	@Override
	public void setUser(String user) {
		this.user = user;
	}

	public String getUser(String user) {
		return this.user;
	}
}
