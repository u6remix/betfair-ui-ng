package by.betfair.ui.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *  
 * @author RemiX
 *
 */
public class Event implements Serializable,Cloneable {

	private static final long serialVersionUID = -507788946799024750L;
	private String id;
	private String name;
	private Date openDate;
	private String countryCode;
	
	public Event() {
		super();
	}
	
	public Event(String id, String name, Date openDate) {
		super();
		this.id = id;
		this.name = name;
		this.openDate = openDate;
	}
	public String getId() {
		return id;
	}
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
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
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	public Event clone() throws CloneNotSupportedException{
		return (Event)super.clone();
	}
	
	
	
}
