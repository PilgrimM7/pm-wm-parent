package com.pilgrimm.core.util.wy;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

public class DateUtil {

	private static Logger logger = Logger.getLogger(DateUtil.class);

	private static Map<Integer, String> monthMap = new HashMap<Integer, String>();

	static {
		monthMap.put(1, "一月");
		monthMap.put(2, "二月");
		monthMap.put(3, "三月");
		monthMap.put(4, "四月");
		monthMap.put(5, "五月");
		monthMap.put(6, "六月");
		monthMap.put(7, "七月");
		monthMap.put(8, "八月");
		monthMap.put(9, "九月");
		monthMap.put(10, "十月");
		monthMap.put(11, "十一月");
		monthMap.put(12, "十二月");
	}

	private DateUtil() {

	}

	/**
	 * 
	 * 此方法描述的是：根据月份获取月份对应的中文大写
	 * 
	 * @Title: getMonthName
	 * @author: philwilla@sina.com
	 * @param monthValue
	 * @return
	 * @return String 返回类型
	 * @version: 2013-10-30 上午09:58:59
	 */
	public static String getMonthName(Integer monthValue) {
		if (monthMap.containsKey(monthValue)) {
			return monthMap.get(monthValue);
		}
		return "";
	}

	/**
	 * 将日期转化成字符串
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String dateToStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 
	 * 此方法描述的是：将日期 转化为DATE
	 * 
	 * @author: czl
	 * @version: 2011-1-21 上午09:45:36
	 */
	public static Date strToDate(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			logger.error(e);
			return null;
		}
	}

	/**
	 * 时间+随机数
	 * 
	 * @return
	 */
	public static String getfeedBackNo() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Random r = new Random();
		return sdf.format(date) + r.nextInt(9999);
	}
}
