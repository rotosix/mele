package com.mele.utils;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.http.ParseException;

public class DateUtil {
	private static int offSet=Calendar.getInstance().getTimeZone().getRawOffset()/1000;
	/**
	 * 获取当前时间的秒数
	 * 
	 * @return
	 */
	public static int getTime() {
		return (int) (System.currentTimeMillis() / 1000l);
	}

	public static int getGMT() {
		return getTime()-offSet ;
	}

	public static int getSecondsPerDay() {
	    return (24*60*60);
	}

	public static String getStrDate(Integer intDate){
		
		String date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(intDate != null){
			try {
				date = sdf.format(new Date((long)intDate * 1000));
			} catch (Exception e) {
				e.printStackTrace();
				date = intDate.toString();
			}
		}
		
		return date;
	}
	 public static long timeop(String t, String pattern)
	 {
	  // 传入的参数要与yyyyMMddHH的格式相同 "yyyyMMddHH"
	  SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern, Locale.SIMPLIFIED_CHINESE);
	  Date date2 = null;
	  try
	  {
	   try {
		date2 = simpledateformat.parse(t);
	} catch (java.text.ParseException e) {
		
		e.printStackTrace();
	}// 将参数按照给定的格式解析参数
	  } catch (ParseException e)
	  {
	   e.printStackTrace();
	  }
	  System.out.println(date2.getTime());
	  return date2.getTime();
	 } 
	  public static  void main(String[] args){
		 long t= DateUtil.timeop("2010010100", "yyyyMMddHH");
		  System.out.println(	t);
		  System.out.println(	System.currentTimeMillis());
	  }
}
