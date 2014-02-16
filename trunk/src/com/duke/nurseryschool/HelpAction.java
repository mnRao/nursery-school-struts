package com.duke.nurseryschool;

import com.duke.nurseryschool.core.AuthenticationRequiredAction;

public class HelpAction extends AuthenticationRequiredAction {

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
