package by.betfair.ui.model;

import java.util.Comparator;

public class Competition implements Cloneable {

	private String id;
	private String name;
	private int marketCount;
	private String competitionRegion;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarketCount() {
		return marketCount;
	}

	public void setMarketCount(int marketCount) {
		this.marketCount = marketCount;
	}

	public String getCompetitionRegion() {
		return competitionRegion;
	}

	public void setCompetitionRegion(String competitionRegion) {
		this.competitionRegion = competitionRegion;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", marketCount=" + marketCount + ", competitionRegion="
				+ competitionRegion + "]";
	}
	
	public Competition clone() throws CloneNotSupportedException{
		return (Competition)super.clone();
	}

	
	public static Comparator<Competition> marketCountComparator = new Comparator<Competition>() {
		public int compare(Competition c1, Competition c2) {
			return new Integer(c2.getMarketCount()).compareTo(new Integer(c1.getMarketCount()));
		}

	};

}
