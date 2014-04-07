package by.betfair.ui.model;

import java.util.ArrayList;
import java.util.List;

public class ExchangePrices {

	private List<PriceSize> availableToBack;
	private List<PriceSize> availableToLay;
	private List<PriceSize> tradedVolume;
	
	public List<PriceSize> getAvailableToBack() {
		return availableToBack;
	}

	public void setAvailableToBack(List<PriceSize> availableToBack) {
		this.availableToBack = availableToBack;
	}

	public List<PriceSize> getAvailableToLay() {
		return availableToLay;
	}

	public void setAvailableToLay(List<PriceSize> availableToLay) {
		this.availableToLay = availableToLay;
	}

	public List<PriceSize> getTradedVolume() {
		return tradedVolume;
	}

	public void setTradedVolume(List<PriceSize> tradedVolume) {
		this.tradedVolume = tradedVolume;
	}

	/**
	 * Must be used ONLY to a display back prices in the reverse ordering on the page
	 */
	@Deprecated
	public List<PriceSize> getAvailableToBackReorder() {
		if(availableToBack!=null && availableToBack.size()>1){
			List<PriceSize> reorderedPrices = new ArrayList<PriceSize>(availableToBack.size());
			for(int i=availableToBack.size()-1;i>-1;i--){
				reorderedPrices.add(availableToBack.get(i));
			}
			return reorderedPrices;
		}else{
			return availableToBack;
		}
	}

	public String toString() {
		return "{" + "" + "availableToBack=" + getAvailableToBack() + ","
				+ "availableToLay=" + getAvailableToLay() + ","
				+ "tradedVolume=" + getTradedVolume() + "," + "}";
	}

}
