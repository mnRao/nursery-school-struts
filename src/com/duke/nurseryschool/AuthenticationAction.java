package com.duke.nurseryschool;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;

public class AuthenticationAction extends CoreAction {
	private String	username;
	private String	password;

	public String login() {
		if ("admin".equals(this.username) && "admin".equals(this.password)) {
			this.sessionAttributes.put(Constant.SESSION_USER, this.username);
			return SUCCESS;
		}
		else {
			this.addActionError(this.getText("error.login"));
			return ERROR;
		}
	}

	@SkipValidation
	public String logout() {
		System.out.println("Logging out ...");

		this.sessionAttributes.clear();

		return "LogoutSuccess";
	}

	@Override
	public void validate() {
		if (this.username == null
				|| this.username.trim().equals(Constant.EMPTY_STRING)) {
			this.addFieldError(Constant.TAG.LOGIN_USERNAME,
					this.getText(Constant.I18N.ERROR_LOGIN_USERNAME));
		}
		if (this.password == null
				|| this.password.trim().equals(Constant.EMPTY_STRING)) {
			this.addFieldError(Constant.TAG.LOGIN_PASSWORD,
					this.getText(Constant.I18N.ERROR_LOGIN_PASSWORD));
		}
		this.logger.info("Info Validating");

		super.validate();
	}

	// @Override
	// @SkipValidation
	// public String input() {
	// return INPUTc String input() {
	// return INPUT;
	// };
	// }

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
