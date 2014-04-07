package by.betfair.ui.model;

import java.util.Date;
import java.util.List;

public class Market {

	private String marketId;
	private String marketName;
	private boolean isMarketDataDelayed;
	private String status;
	private int betDelay;
	private boolean bspReconciled;
	private boolean complete;
	private boolean inplay;
	private int numberOfWinners;
	private int numberOfRunners;
	private int numberOfActiveRunners;
	private Date marketStartTime;
	private Date lastMatchTime;
	private double totalMatched;
	private double totalAvailable;
	private boolean crossMatching;
	private boolean runnersVoidable;
	private long version;
	private List<Runner> runners = null;
	private Competition competition;
	private Event event;

	//calculating properties
	// sum of all runner's probabilities by odds, the value displayes bookmaker's margin and  should be >100%
	private double totalProbabilityByOdds;
	 
	
	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Date getMarketStartTime() {
		return marketStartTime;
	}

	public void setMarketStartTime(Date marketStartTime) {
		this.marketStartTime = marketStartTime;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public List<Runner> getRunners() {
		return runners;
	}

	public void setRunners(List<Runner> runners) {
		this.runners = runners;
	}


	public Competition getCompetition() {
		return competition;
	}

	public void setCompetition(Competition competition) {
		this.competition = competition;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString() {
		return getMarketName();
	}

	public boolean getIsMarketDataDelayed() {
		return isMarketDataDelayed;
	}

	public void setIsMarketDataDelayed(boolean isMarketDataDelayed) {
		this.isMarketDataDelayed = isMarketDataDelayed;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getBetDelay() {
		return betDelay;
	}

	public void setBetDelay(int betDelay) {
		this.betDelay = betDelay;
	}

	public boolean getBspReconciled() {
		return bspReconciled;
	}

	public void setBspReconciled(boolean bspReconciled) {
		this.bspReconciled = bspReconciled;
	}

	public boolean getComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}

	public boolean getInplay() {
		return inplay;
	}

	public void setInplay(boolean inplay) {
		this.inplay = inplay;
	}

	public int getNumberOfWinners() {
		return numberOfWinners;
	}

	public void setNumberOfWinners(int numberOfWinners) {
		this.numberOfWinners = numberOfWinners;
	}

	public int getNumberOfRunners() {
		return numberOfRunners;
	}

	public void setNumberOfRunners(int numberOfRunners) {
		this.numberOfRunners = numberOfRunners;
	}

	public int getNumberOfActiveRunners() {
		return numberOfActiveRunners;
	}

	public void setNumberOfActiveRunners(int numberOfActiveRunners) {
		this.numberOfActiveRunners = numberOfActiveRunners;
	}

	public Date getLastMatchTime() {
		return lastMatchTime;
	}

	public void setLastMatchTime(Date lastMatchTime) {
		this.lastMatchTime = lastMatchTime;
	}

	public double getTotalMatched() {
		return totalMatched;
	}

	public void setTotalMatched(double totalMatched) {
		this.totalMatched = totalMatched;
	}

	public double getTotalAvailable() {
		return totalAvailable;
	}

	public void setTotalAvailable(double totalAvailable) {
		this.totalAvailable = totalAvailable;
	}

	public boolean getCrossMatching() {
		return crossMatching;
	}

	public void setCrossMatching(boolean crossMatching) {
		this.crossMatching = crossMatching;
	}

	public boolean getRunnersVoidable() {
		return runnersVoidable;
	}

	public void setRunnersVoidable(boolean runnersVoidable) {
		this.runnersVoidable = runnersVoidable;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	public double getTotalProbabilityByOdds() {
		if(totalProbabilityByOdds==0 && runners!=null && !runners.isEmpty()){
			for(Runner runner:runners){
				totalProbabilityByOdds += runner.getProbabilityByOdds();
			}
		}
		return totalProbabilityByOdds;
	}

	public void setTotalProbabilityByOdds(double totalProbabilityByOdds) {
		this.totalProbabilityByOdds = totalProbabilityByOdds;
	}

	/**
	 * Add competition, event and other fields
	 * @param commonMarket
	 * @throws CloneNotSupportedException 
	 */
	public void updateCommonInfo(Market commonMarket) throws CloneNotSupportedException{
		this.setMarketName(commonMarket.getMarketName());
		this.setMarketStartTime(commonMarket.getMarketStartTime());
		this.setCompetition(commonMarket.getCompetition().clone());
		this.setEvent(commonMarket.getEvent().clone());
		if(this.getRunners()!=null && commonMarket.getRunners()!=null && 
				commonMarket.getRunners().size() == this.getRunners().size()){
			for(int i=0;i<this.getRunners().size();i++){
				this.getRunners().get(i).setRunnerName(commonMarket.getRunners().get(i).getRunnerName());
			}
		}else{
			// probably need to throw exception
		}
	}

}
