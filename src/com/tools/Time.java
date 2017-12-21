package com.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 用于生成时间字符串，或根据时间字符串解析返回Date对象
 * @author lubbe
 *
 */
public class Time {
/**
 * 返回当前日期和时间以"yyyy-MM-dd kk:mm:ss"格式显示的字符串
 * @return 当前日期时间字符串
 */
	public static String getDateTimeNow(){
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss") ;
	return sdf.format(time) ;	
	}
	/**
	 * 返回当前日期以"yyyy-MM-dd"格式显示的字符串
	 * @return 当前日期字符串
	 */
	public static String getDataNow(){
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd") ;
	return sdf.format(time) ;
	}
	/**
	 * 返回当前时间以"kk:mm:ss"格式显示的字符串
	 * @return 当前日时间字符串
	 */
	public static String getTimeNow(){
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss") ;
	return sdf.format(time) ;
	}
	/**
	* 给定"yyyy-MM-dd"格式的时间字符串，解析为Date类对象
	* @param date 需要解析的字符串
	 * @return 指定的Date类时间对象
	 */
	public static Date getDateByString(String date){
		Date time=new Date();
		try {
			time=new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			System.out.println("给定的字符串格式不符！");
			return time;
		
		}
		return time;
		
	}
	/**
	  * 给定"yyyy-MM-dd kk:mm:ss"格式的时间字符串，解析为Date类对象
	  * @param dateTime 需要解析的字符串
	 * @return 指定的Date类时间对象
	 */
	public static Date getDateTimeByString(String dateTime){
		Date date=new Date();
		try {
			date=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").parse(dateTime);
		} catch (ParseException e) {
			System.out.println("给定的字符串格式不符！");
			return date;
		
		}
		return date;
		
	}
}
