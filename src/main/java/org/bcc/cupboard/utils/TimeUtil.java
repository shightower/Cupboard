package org.bcc.cupboard.utils;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public abstract class TimeUtil {
	private static final String DATE_FORMAT = "YYYY-MM-dd hh:mm";
	
	public static Date getDateFromString(String dateStr) {
		DateTimeFormatter format = DateTimeFormat.forPattern(DATE_FORMAT);
		DateTime date = DateTime.parse(dateStr, format);
		return date.toDate();
	}
	
	public static Date getNextAvailableDate(Date lastDate, int waitPeriod) {
		//if the data param is null, it will be assumed to be today
		DateTime nextDate;
		
		if(lastDate == null) {
			lastDate = new Date();
		} 
		
		nextDate = new DateTime(lastDate.getTime());
		nextDate = nextDate.plusDays(waitPeriod);
		return nextDate.toDate();
	}
}
