package by.betfair.core.ng.actions;

import java.util.HashMap;
import java.util.Map;

import by.betfair.core.ng.entity.MarketFilter;
import by.betfair.core.ng.util.ApiNgOperation;
import by.betfair.core.ng.util.HttpUtil;

public class ListCompetitions {
	private static final String FILTER = "filter";
	
	public String execute(MarketFilter filter,String ssoToken) throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, filter);
		String resp = HttpUtil.sendPostRequest(ApiNgOperation.LISTCOMPETITIONS.getOperationName(), params, ssoToken);
		return resp;
	}
}
