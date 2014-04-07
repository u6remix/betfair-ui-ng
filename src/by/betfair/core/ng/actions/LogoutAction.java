package by.betfair.core.ng.actions;

import by.betfair.core.ng.util.HttpUtil;

public class LogoutAction {
	private static final String LOGOUT_URL="https://identitysso.betfair.com/api/logout";
	
	public void execute(String ssoToken) throws Exception{
		HttpUtil.sendPostRequest(LOGOUT_URL, ssoToken);
	}
}
