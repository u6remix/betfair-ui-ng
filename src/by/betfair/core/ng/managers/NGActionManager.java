package by.betfair.core.ng.managers;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import by.betfair.core.ng.actions.ListCompetitions;
import by.betfair.core.ng.actions.ListEventsAction;
import by.betfair.core.ng.actions.ListMarketBookAction;
import by.betfair.core.ng.actions.ListMarketCatalogueAction;
import by.betfair.core.ng.actions.LoginAction;
import by.betfair.core.ng.actions.LogoutAction;
import by.betfair.core.ng.entity.MarketFilter;
import by.betfair.core.ng.util.JsonConverter;
import by.betfair.ui.model.Competition;
import by.betfair.ui.model.Event;
import by.betfair.ui.model.Market;

public class NGActionManager implements IActionManager {
	// do it like singleton ??
	
	private static String ssoToken = null;
	private LoginAction loginAction = null;
	private LogoutAction logoutAction = null;
	private ListMarketCatalogueAction listMarketAction = null;
	private ListEventsAction listEventsAction = null;
	private ListMarketBookAction listMarketBookAction = null;
	private ListCompetitions listCompetitions = null;

	public void login() throws Exception {
		loginAction = new LoginAction();
		loginAction.execute();
		ssoToken = loginAction.getResult();
	}

	public void logout() throws Exception {
		logoutAction = new LogoutAction();
		logoutAction.execute(ssoToken);
	}

	public List<Event> getEvents(MarketFilter filter) throws Exception {
		listEventsAction = new ListEventsAction();
		String resp = listEventsAction.execute(filter, ssoToken);
		return JsonConverter.convertResultFromJson(resp, new TypeToken<List<Event>>() {}.getType());
	}

	public List<Market> getMarkets(MarketFilter marketFilter) throws Exception {
		listMarketAction = new ListMarketCatalogueAction();
		String resp = listMarketAction.execute(marketFilter, ssoToken);
		return JsonConverter.convertResultFromJson(resp, new TypeToken<List<Market>>() {}.getType());
	}

	public Market getPricesAndTradedVolumes(String marketId) throws Exception {
		listMarketBookAction = new ListMarketBookAction();
		String resp = listMarketBookAction.execute(marketId, ssoToken);
		List<Market> listMarket = JsonConverter.convertResultFromJson(resp, new TypeToken<List<Market>>() {}.getType());
		return listMarket.get(0);
	}

	public List<Competition> getCompetitions(MarketFilter filter) throws Exception {
		listCompetitions = new ListCompetitions();
		String resp = listCompetitions.execute(filter, ssoToken);
		return JsonConverter.convertResultFromJson(resp, new TypeToken<List<Competition>>() {}.getType());
	}

}
