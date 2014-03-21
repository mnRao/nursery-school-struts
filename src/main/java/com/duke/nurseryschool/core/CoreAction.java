package com.duke.nurseryschool.core;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CoreAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = -1857836323217571L;

	protected final Logger logger = Logger.getLogger(this.getClass());
	protected HttpServletRequest request = (HttpServletRequest) ActionContext
			.getContext().get(ServletActionContext.HTTP_REQUEST);

	protected SessionMap<String, Object> sessionAttributes = null;

	@Override
	public void setSession(Map<String, Object> sessionAttributes) {
		this.sessionAttributes = (SessionMap<String, Object>) sessionAttributes;
	}
}
