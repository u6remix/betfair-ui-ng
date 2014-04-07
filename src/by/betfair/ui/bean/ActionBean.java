package by.betfair.ui.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import by.betfair.core.ng.managers.IActionManager;
import by.betfair.core.ng.managers.NGActionManager;
import by.betfair.ui.model.NamePageNavigator;
import by.betfair.util.DateUtils;

@ManagedBean(name = "action")
@SessionScoped
/**
 * Uses on the welcome page, contains references to another beans and names of displayed pages into content area.
 * @author RemiX
 *
 */
public class ActionBean {

	private static final Logger logger = Logger.getLogger(ActionBean.class);
	private boolean isLogged = false;
	private int selectedIndex = -1;// for top menu
	private String page;// Name of page, the main navigation function!
	private IActionManager actionManager = new NGActionManager();

	public boolean getIsLogged() {
		return isLogged;
	}

	public void setIsLogged(boolean isLogged) {
		this.isLogged = isLogged;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void login(ActionEvent e) {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			actionManager.login();
			logger.debug("Login operation was executed");
			context.addMessage(null, new FacesMessage("Login operation was executed"));
			selectedIndex = 0;
			isLogged = true;
			// this.page = NamePageNavigator.WELCOME;
		} catch (Exception exc) {
			logger.error(exc);
			context.addMessage(null, new FacesMessage("Unexpected error"));
		}
	}

	public void logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			actionManager.logout();
			context.addMessage(null, new FacesMessage("Logout operation was executed"));
			selectedIndex = 3;
			isLogged = false;
		} catch (Exception e) {
			logger.error(e);
			context.addMessage(null, new FacesMessage("Unexpected error"));
		}
	}

	public void toGetMarkets() {
			FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage("Preparing of request for getting markets"));
		selectedIndex=1;
		this.page = NamePageNavigator.GET_MARKETS;
	}

	public void toStatistics() {
		// statisticsBean.init();
		// selectedIndex=2;
		// this.page = NamePageNavigator.STATISTICS;
	}

	public String getDisplayedTimeZone() {
		//logger.debug(DateUtils.systemTimezone.getDisplayName());
		return DateUtils.systemTimezone.getID();
	}
}
