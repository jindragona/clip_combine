/*
 *  Midas version 1.0
 *
 *  Copyright ⓒ 2015 kt corp. All rights reserved.
 *
 *  This is a proprietary software of kt corp, and you may not use this file except in
 *  compliance with license agreement with kt corp. Any redistribution or use of this
 *  software, with or without modification shall be strictly prohibited without prior written
 *  approval of kt corp, and the copyright notice above does not evidence any actual or
 *  intended publication of such software.
 */

package com.kt.midas.adm.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	public static String getDateTimeWithS() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

		return dateFormat.format(date);
	}
	
	public static String getCurrentDate(){
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);

		return dateFormat.format(date);
	}

	/**
	 * 현재 날짜에서 파라미터로 전달한 날짜를 더한다.
	 * @param iDay 
	 * @return
	 */
	public static String getDate(int iDay) {
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		cal.add(Calendar.DAY_OF_YEAR, iDay);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
		
		return df.format(cal.getTime());
	}

	public static int getYear() {
		
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		
		int year = cal.get ( Calendar.YEAR );
		return year;
	}
	
	public static int getMonth() {
		
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		
		int month = cal.get ( Calendar.MONTH ) + 1 ;
		return month;
	}
	
	public static int getDate() {
		
		Calendar cal = new GregorianCalendar(Locale.KOREA);
		cal.setTime(new Date());
		
		int date = cal.get ( Calendar.DATE ) ;
		return date;
	}
	
	
}
