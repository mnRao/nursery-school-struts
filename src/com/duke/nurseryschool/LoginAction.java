package com.duke.nurseryschool;

import com.duke.nurseryschool.helper.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;

	public String authenticate() {
		if (username.equals("admin") && password.equals("admin")) {
			return Constant.RESPONSE_SUCCESS;
		}
		else {
			addActionError(getText("error.login"));
			return Constant.RESPONSE_ERROR;
		}
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