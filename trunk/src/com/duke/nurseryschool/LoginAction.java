package com.duke.nurseryschool;

import com.duke.nurseryschool.helper.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;

	public String authenticate() {
		if ("admin".equals(username) && "admin".equals(password)) {
			return Constant.RESPONSE_SUCCESS;
		}
		else {
			addActionError(getText("error.login"));
			return Constant.RESPONSE_ERROR;
		}
	}

	public String dashboard() {
		return Constant.RESPONSE_SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
