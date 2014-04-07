package by.betfair.ui.model;

import java.util.Date;

public class Runner {
	// properties from json response
	private Long selectionId;
	private double handicap;
	private String runnerName;
	private String status;
	private double adjustmentFactor;
	private double lastPriceTraded;
	private double totalMatched;
	private Date removalDate;
	private ExchangePrices ex;
	
	// calculated properties
	private double probabilityByOdds;
	private double fairProbabilityByOdds;// without margin of bookmaker
	private double probabilityByAmounts;

	public Long getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(Long selectionId) {
		this.selectionId = selectionId;
	}

	public double getHandicap() {
		return handicap;
	}

	public void setHandicap(double handicap) {
		this.handicap = handicap;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAdjustmentFactor() {
		return adjustmentFactor;
	}

	public void setAdjustmentFactor(double adjustmentFactor) {
		this.adjustmentFactor = adjustmentFactor;
	}

	public double getLastPriceTraded() {
		return lastPriceTraded;
	}

	public void setLastPriceTraded(double lastPriceTraded) {
		this.lastPriceTraded = lastPriceTraded;
	}

	public double getTotalMatched() {
		return totalMatched;
	}

	public void setTotalMatched(double totalMatched) {
		this.totalMatched = totalMatched;
	}

	public Date getRemovalDate() {
		return removalDate;
	}

	public void setRemovalDate(Date removalDate) {
		this.removalDate = removalDate;
	}

	public ExchangePrices getEx() {
		return ex;
	}

	public void setEx(ExchangePrices ex) {
		this.ex = ex;
	}

	
	public String getRunnerName() {
		return runnerName;
	}

	public void setRunnerName(String runnerName) {
		this.runnerName = runnerName;
	}

	public double getProbabilityByOdds() {
		if(probabilityByOdds==0 && ex.getAvailableToBack()!=null && !ex.getAvailableToBack().isEmpty()){
			this.probabilityByOdds = 100/ex.getAvailableToBack().get(0).getPrice();
		}
		return probabilityByOdds;
	}

	public void setProbabilityByOdds(double probabilityByOdds) {
		this.probabilityByOdds = probabilityByOdds;
	}

	public double getFairProbabilityByOdds() {
		return fairProbabilityByOdds;
	}

	public void setFairProbabilityByOdds(double pureProbabilityByOdds) {
		this.fairProbabilityByOdds = pureProbabilityByOdds;
	}

	public double getProbabilityByAmounts() {
		return probabilityByAmounts;
	}

	public void setProbabilityByAmounts(double probabilityByAmounts) {
		this.probabilityByAmounts = probabilityByAmounts;
	}
	
	


}
