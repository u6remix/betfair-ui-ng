package by.betfair.core.ng.actions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import by.betfair.core.ng.entity.MarketFilter;
import by.betfair.core.ng.entity.MarketProjection;
import by.betfair.core.ng.util.ApiNgOperation;
import by.betfair.core.ng.util.HttpUtil;

public class ListMarketCatalogueAction {
	
	private static final String FILTER = "filter";
	private static final String MAXRESULT = "maxResults";
	private static final String MAXRESULT_VALUE = "1000";
	private static final String MARKET_PROJECTION = "marketProjection";
	private static final Set<MarketProjection> MARKET_PROJECTION_SET = new HashSet<MarketProjection>(Arrays.asList(
			MarketProjection.COMPETITION,
			MarketProjection.EVENT,
			MarketProjection.EVENT_TYPE,
			MarketProjection.MARKET_START_TIME,
			MarketProjection.RUNNER_DESCRIPTION));
	
	public String execute(MarketFilter marketFilter,String ssoToken)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		params.put(FILTER, marketFilter);
		params.put(MAXRESULT,MAXRESULT_VALUE);
		params.put(MARKET_PROJECTION, MARKET_PROJECTION_SET);
		String resp = HttpUtil.sendPostRequest(ApiNgOperation.LISTMARKETCATALOGUE.getOperationName(), params, ssoToken);
		return resp;
	}
}
