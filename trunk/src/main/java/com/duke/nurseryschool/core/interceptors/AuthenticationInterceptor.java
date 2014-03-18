package com.duke.nurseryschool.core.interceptors;

import java.util.Map;

import com.duke.nurseryschool.helper.Constant;
import com.duke.nurseryschool.helper.CookieManager;
import com.duke.nurseryschool.helper.StringUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class AuthenticationInterceptor implements Interceptor {

	private static final long	serialVersionUID	= -7055331347332322371L;

	@Override
	public void destroy() {
		System.out.println("End...");
	}

	@Override
	public void init() {
		System.out.println("Init...");
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("Inside Auth Interceptor ... "
				+ actionInvocation.getAction().toString());
		// Check user in session
		Map<String, Object> sessionAttributes = actionInvocation
				.getInvocationContext().getSession();
		String userInSession = (String) sessionAttributes
				.get(Constant.SESSION_USER);

		// Get user in cookie (if exists)
		String userInCookie = CookieManager.getInstance().getUserInCookies();

		boolean cookieIsExpired = this.checkIfUserIsExpired(userInCookie);
		boolean sessionIsExpired = this.checkIfUserIsExpired(userInSession);
		if (cookieIsExpired && sessionIsExpired) {
			return Action.LOGIN;
		}
		else {
			// Choose username from b/w session and cookie
			String userGlobal = cookieIsExpired ? userInSession : userInCookie;
			// If session is not used then cookie is being used
			if (sessionIsExpired) {
				// Set new cookie
				CookieManager.getInstance().setLoginCookie(userGlobal);
			}
			// Invoke action
			Action action = (Action) actionInvocation.getAction();
			if (action instanceof UserAware) {
				((UserAware) action).setUser(userGlobal);
			}
			return actionInvocation.invoke();
		}
	}

	private boolean checkIfUserIsExpired(String user) {
		return StringUtil.isEmpty(user);
	}

}
