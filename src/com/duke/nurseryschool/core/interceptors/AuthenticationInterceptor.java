package com.duke.nurseryschool.core.interceptors;

import java.util.Map;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("End...");
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		System.out.println("Init...");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("Inside Auth Interceptor ... "
				+ actionInvocation.getAction().toString());
		Map<String, Object> sessionAttributes = actionInvocation
				.getInvocationContext().getSession();
		String user = (String) sessionAttributes.get(Constant.SESSION_USER);

		if (StringUtil.isEmpty(user)) {
			return Action.LOGIN;
		}
		else {
			Action action = (Action) actionInvocation.getAction();
			if (action instanceof UserAware) {
				((UserAware) action).setUser(user);
			}
			return actionInvocation.invoke();
		}
	}

}
