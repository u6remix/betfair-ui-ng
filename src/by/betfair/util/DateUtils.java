package by.betfair.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.log4j.Logger;

public class DateUtils {
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	private static final Logger logger = Logger.getLogger(DateUtils.class);
	public static final TimeZone systemTimezone = TimeZone.getDefault();
	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");
	
	public static java.sql.Timestamp convertToSqlTimestamp(java.util.Date date){
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		return timestamp;
	}
	
	public static final String convertToString(java.util.Date date){
		return df.format(date);
	}
	
/**
 * Clear hours,minutes... of currentDate(startDate param) and then add countAddDate and cuntAddSecond
 * @param startDate
 * @param countAddDate
 * @return for current date and countAddDate=1 countAddSec=-1 returns 23:59:59 of current day,
 * 			for countAddDate =2 and countAddSec=-1 returns 23:59:59 of tomorrow....
 */
	public static java.util.Date getMidnightDate(java.util.Date startDate, int countAddDate, int countAddSec){
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, countAddDate);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.SECOND,countAddSec);
        return cal.getTime();
	}
	
	public static java.util.Date parseToDate(String input){
		try {
			return df.parse(input);
		} catch (ParseException e) {
			logger.error("The date string "+ input+ " wasn't parsed",e);
			return null;
		}
	}
	
	/**
	 * Convert date from server time zone to UTC
	 * @param inputDate
	 * @return
	 */
	public static java.util.Date convertToUTC(java.util.Date inputDate){
        if(inputDate== null){
        	return null;
        }
		long fromTZDst = 0;  
        if(systemTimezone.inDaylightTime(inputDate))  
        {  
            fromTZDst = systemTimezone.getDSTSavings();  
        }  
  
        long fromTZOffset = systemTimezone.getRawOffset() + fromTZDst;  
  
        long toTZDst = 0;  

        if(UTC.inDaylightTime(inputDate))  
        {  
            toTZDst = UTC.getDSTSavings();  
        }  
        long toTZOffset = UTC.getRawOffset() + toTZDst;  
  
        return new java.util.Date(inputDate.getTime() + (toTZOffset - fromTZOffset));  
	}
}