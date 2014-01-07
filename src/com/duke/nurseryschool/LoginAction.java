package com.duke.nurseryschool;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;

public class LoginAction extends CoreAction {
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

	@Override
	public void validate() {
		if (username == null || username.trim().equals(Constant.EMPTY_STRING)) {
			addFieldError(Constant.TAG.LOGIN_USERNAME,
					getText(Constant.I18N.ERROR_LOGIN_USERNAME));
		}
		if (password == null || password.trim().equals(Constant.EMPTY_STRING)) {
			addFieldError(Constant.TAG.LOGIN_PASSWORD,
					getText(Constant.I18N.ERROR_LOGIN_PASSWORD));
		}
		super.validate();
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
