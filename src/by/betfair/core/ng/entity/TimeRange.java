package by.betfair.core.ng.entity;

import java.util.Date;

import by.betfair.util.DateUtils;

public class TimeRange {

	private Date from;

	public final Date getFrom() {
		return from;
	}

	public final void setFrom(Date from) {
		this.from = DateUtils.convertToUTC(from);
	}

	private Date to;

	public final Date getTo() {
		return to;
	}

	public final void setTo(Date to) {
		this.to = DateUtils.convertToUTC(to);
	}
	
	public TimeRange(Date from,Date to){
		setFrom(from);
		setTo(to);
	}

	public TimeRange() {
		super();
	}
	

}
