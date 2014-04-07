package by.betfair.ui.enums;

public enum MarketFilterNamesEnum {
	MARKET_NAME ("marketName"),
	TURNING_IN_PLAY ("turningInPlay");
	
	MarketFilterNamesEnum(String name) {
		this.name = name;
	}


	private String name;
	
	public String getName(){
		return this.name;
	}
}
