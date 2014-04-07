package by.betfair.ui.enums;

import java.util.ArrayList;
import java.util.List;

public enum CountryEnum {
	IT("Italy","IT","ITA"),
	GB("England","GB","GBR"),
	DE("German","DE","DEU"),
	ES("Spain","ES","ESP"),
	FR("France","FR","FRA");
	
	
	private final String fullName;
	private final String code;
	private final String region;

	private CountryEnum(String fullName, String code, String region) {
		this.fullName = fullName;
		this.code = code;
		this.region = region;
	}
	public String getFullName() {
		return fullName;
	}
	public String getCode() {
		return code;
	}
	public String getRegion() {
		return region;
	}
	
	public static List<String> getListFullNames(){
		List<String> listCountries = new ArrayList<String>();
		CountryEnum[] arrCountries = CountryEnum.values();
		for(CountryEnum country: arrCountries){
			listCountries.add(country.fullName);
		}
		return listCountries;
	}
	
	
}
