package by.betfair.core.ng.actions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import by.betfair.core.ng.entity.PriceData;
import by.betfair.core.ng.entity.PriceProjection;
import by.betfair.core.ng.util.ApiNgOperation;
import by.betfair.core.ng.util.HttpUtil;

/**
 * API description
 *  https://api.developer.betfair.com/services/webapps/docs/display/1smk3cen4v3lu3yomq5qye0ni/listMarketBook
 */
public class ListMarketBookAction {
	private static final String MARKET_IDS = "marketIds";
	private static final String PRICE_PROJECTION = "priceProjection";
	private static PriceProjection priceProjection = new PriceProjection();
	static{
		priceProjection.setVirtualise(true);
		Set<PriceData> priceData = new HashSet<PriceData>(Arrays.asList(
				PriceData.EX_BEST_OFFERS,
				PriceData.EX_TRADED));
		priceProjection.setPriceData(priceData);
	}
	
	public String execute(String marketId,String ssoToken)throws Exception{
		Map<String, Object> params = new HashMap<String, Object>();
		List<String> marketIds = new ArrayList<String>(1);
		marketIds.add(marketId);
		params.put(MARKET_IDS, marketIds);
		params.put(PRICE_PROJECTION,priceProjection);
		String resp = HttpUtil.sendPostRequest(ApiNgOperation.LISTMARKETBOOK.getOperationName(), params, ssoToken);
		return resp;
	}
	
}
