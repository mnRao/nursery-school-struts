package com.duke.nurseryschool.core;

import com.duke.nurseryschool.helper.Constant;
import com.opensymphony.xwork2.ActionSupport;

public class LocaleAction extends ActionSupport {

	@Override
	public String execute() {
		return Constant.RESPONSE_SUCCESS;
	}
}
