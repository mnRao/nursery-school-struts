package com.duke.nurseryschool;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.validation.SkipValidation;

import com.duke.nurseryschool.core.CoreAction;
import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.CookieManager;

public class AuthenticationAction extends CoreAction {
	private String username;
	private String password;
	private boolean rememberMe;

	public String login() {
		if ("admin".equals(this.username) && "admin".equals(this.password)) {

			// If REMEMBER_ME is chosen => store cookies
			if (this.isRememberMe()) {
				CookieManager.getInstance().setLoginCookie(this.username);
			}
			else {
				// Set user in session
				this.sessionAttributes
						.put(Constant.SESSION_USER, this.username);
			}

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

		// Clear session
		this.sessionAttributes.clear();
		// Clear cookie
		CookieManager.getInstance().deleteLoginCookie();

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

	@SkipValidation
	public String form() {
		return INPUT;
	}

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

	public boolean isRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
