package com.duke.nurseryschool;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;

public class LoginAction extends CoreAction {
	private String username;
	private String password;

	public String authenticate() {
		if ("admin".equals(username) && "admin".equals(password)) {
			return SUCCESS;
		}
		else {
			addActionError(getText("error.login"));
			return ERROR;
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
		logger.info("Info Validating");

		super.validate();
	}

	// @Override
	// @SkipValidation
	// public String input() {
	// return INPUTc String input() {
	// return INPUT;
	// };
	// }

	public String dashboard() {
		return SUCCESS;
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
