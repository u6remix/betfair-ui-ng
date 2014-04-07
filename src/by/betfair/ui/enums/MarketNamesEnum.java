package by.betfair.ui.enums;

public enum MarketNamesEnum {
	MATCH_ODDS("Match Odds"),
	TOTAL_2and5("Over/Under 2.5 Goals"),
	DOUBLE_CHANCE("Double Chance"),
	
	OVER("Over"),
	UNDER("Under"),
	HOME_OR_DRAW("1X"),
	DRAW_OR_AWAY("X2"),
	HOME_OR_AWAY("12");
	
	private final String name;
	MarketNamesEnum(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	
	
}
