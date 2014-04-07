package by.betfair.ui.bean;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;

import by.betfair.core.ng.entity.MarketFilter;
import by.betfair.core.ng.managers.IActionManager;
import by.betfair.core.ng.managers.NGActionManager;
import by.betfair.logic.ProbabilityCalculator;
import by.betfair.ui.enums.CountryEnum;
import by.betfair.ui.model.Competition;
import by.betfair.ui.model.Event;
import by.betfair.ui.model.Market;
import by.betfair.ui.model.Runner;
import by.betfair.util.DateUtils;

@ManagedBean(name = "getMarkets")
@SessionScoped
public class GetMarketsBean {
	private static final Logger logger = Logger.getLogger(GetMarketsBean.class);

	private IActionManager actionManager = new NGActionManager();
	private Date dateFrom;
	private Date dateTo;
	private Set<String> selectedCountries;
	private Set<String> selectedCompetitions;
	private List<Competition> competitions;
	private List<Event> events;
	private List<Market> markets;
	private Event selectedEvent;
	private Competition selectedEventCompetition;
	private Market selectedMarket;
	private Runner selectedRunner;

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public Set<String> getSelectedCountries() {
		return selectedCountries;
	}

	public void setSelectedCountries(Set<String> selectedCountries) {
		logger.debug("Count of selected counties=" + selectedCountries.size());
		this.selectedCountries = selectedCountries;
	}

	public void dateToday() {
		dateFrom = new Date();
		dateTo = DateUtils.getMidnightDate(dateFrom, 1, -1);
	}

	public void dateTodayAndTommorow() {
		dateFrom = new Date();
		dateTo = DateUtils.getMidnightDate(dateFrom, 2, -1);
	}

	public void dateTommorow() {
		Date now = new Date();
		dateFrom = DateUtils.getMidnightDate(now, 1, 0);
		dateTo = DateUtils.getMidnightDate(now, 2, -1);
	}

	public void dateThreeNextDays() {
		dateFrom = new Date();
		dateTo = DateUtils.getMidnightDate(dateFrom, 4, -1);
	}

	public void dateFiveNextDays() {
		dateFrom = new Date();
		dateTo = DateUtils.getMidnightDate(dateFrom, 6, -1);
	}

	public CountryEnum[] getCountries() {
		return CountryEnum.values();
	}

	public Set<String> getSelectedCompetitions() {
		return selectedCompetitions;
	}

	public void setSelectedCompetitions(Set<String> selectedCompetitions) {
		this.selectedCompetitions = selectedCompetitions;
	}

	public List<Competition> getCompetitions() {
		return competitions;
	}

	public void setCompetitions(List<Competition> competitions) {
		this.competitions = competitions;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Market> getMarkets() {
		return markets;
	}

	public void setMarkets(List<Market> markets) {
		this.markets = markets;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public Competition getSelectedEventCompetition() {
		return selectedEventCompetition;
	}

	public Market getSelectedMarket() {
		return selectedMarket;
	}

	public Runner getSelectedRunner() {
		return selectedRunner;
	}

	public void clear() {
		this.dateFrom = null;
		this.dateTo = null;
		this.selectedCountries.clear();
		this.competitions.clear();
		this.selectedCompetitions.clear();
		logger.debug("GetMarketBean was cleared");
	}

	/**
	 * Action listener, makes call to get list of competitions
	 * 
	 * @throws Exception
	 */
	public void loadCompetitions() {
		selectedCompetitions=null;
		MarketFilter filter = createFilter();
		try {
			competitions = actionManager.getCompetitions(filter);
			Collections.sort(competitions, Competition.marketCountComparator);
			logger.debug("List of competitions: " + competitions);
		} catch (Exception e) {
			handleException(e);
		}

	}

	public void searchEvents() throws Exception {
		MarketFilter filter = createFilter();
		events = actionManager.getEvents(filter);
	}
	
    public void onEventRowSelect(SelectEvent event) {
    	selectedEvent = (Event)event.getObject();
    	searchMarkets();
    }
    
    public void onMarketRowToggle(ToggleEvent event) {
    	selectedMarket = (Market)event.getData();
    	getMarketOdds();
    }
    
    public void onRunnerRowSelect(SelectEvent event) {
    	selectedRunner = (Runner)event.getObject();
    }
    public void searchMarkets(){
    	MarketFilter filter = new MarketFilter();;
    	filter.addEventId(selectedEvent.getId());
    	try {
			markets = actionManager.getMarkets(filter);
			setCompetitionFromMarket();
		} catch (Exception e) {
			handleException(e);
		}
    }
    
    public void getMarketOdds(){
    	try {
			Market oddsMarket = actionManager.getPricesAndTradedVolumes(selectedMarket.getMarketId());
			oddsMarket.updateCommonInfo(selectedMarket);
			selectedMarket = oddsMarket;
			ProbabilityCalculator.calculateProbability(selectedMarket);
		} catch (Exception e) {
			handleException(e);
		}
    }

	private MarketFilter createFilter() {
		MarketFilter filter = new MarketFilter(dateFrom,dateTo);// global scope??
		filter.setMarketCountries(selectedCountries);
		filter.setCompetitionIds(selectedCompetitions);
		return filter;
	}
	
	/**
	 * Initialize competition object by getting info about competition from the first market of list
	 */
	private void setCompetitionFromMarket(){
		if(markets!=null && !markets.isEmpty()){
			selectedEventCompetition = markets.get(0).getCompetition();
		}else{
			selectedEventCompetition = new Competition();
			logger.error("Can't get competition from markets");
		}
	}
	
	private void handleException(Exception e){
		logger.error(e);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("An unexpected error has appeared",e.getMessage()));
	}

}
