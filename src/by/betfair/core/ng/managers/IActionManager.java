package by.betfair.core.ng.managers;

import java.util.List;

import by.betfair.core.ng.entity.MarketFilter;
import by.betfair.ui.model.Competition;
import by.betfair.ui.model.Event;
import by.betfair.ui.model.Market;

public interface IActionManager {
	
	public static final String APPLICATION_KEY = "JtyTHsR8rsY5qt77";
    public static final String USERNAME = "";
    public static final String PASSWORD = "";
    
    public void login() throws Exception;
    public void logout() throws Exception;
    public List<Competition> getCompetitions(MarketFilter filter) throws Exception;
    public List<Event> getEvents(MarketFilter filter) throws Exception;
    public List<Market> getMarkets(MarketFilter marketFilter) throws Exception;
    public Market getPricesAndTradedVolumes(String marketId) throws Exception;
    
}
