package net.com.gong.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import net.jlrnt.common.util.DateUtils;

/**
 * 时间比较工具类
 * 
 * @author xiabing_yi
 * 
 */
public final class TimeCompareUtil {


	/**
	 * 获取两日期之间时间差
	 * 
	 * @param begin
	 * @param end
	 * @return
	 * @author xiabing_yi 2013-9-6
	 */
	public static Integer getDayDifference(Date begin, Date end) {
		long l = end.getTime() - begin.getTime();
		return (int) (l / (24 * 60 * 60 * 1000));
	}

	/** 获取月份差 **/
	public static Integer getMonthDifference(String begin, String end) {
		String[] startTime = begin.split("-");
		String startYear = startTime[0];
		String startMonth = startTime[1];
		int sYear = Integer.parseInt(startYear);
		int sMonth = Integer.parseInt(startMonth);

		String[] endTime = end.split("-");
		String endYear = endTime[0];
		String endMonth = endTime[1];
		int eYear = Integer.parseInt(endYear);
		int eMonth = Integer.parseInt(endMonth);

		int d = (eYear - sYear) * 12 + (eMonth - sMonth);
		return d;
	}

	/***
	 * 获取某年某周开始结束时间
	 * 
	 * @param searchYear
	 * @param weekTime
	 * @return
	 * @author xiabing_yi 2013-9-13
	 */
	public static String getDayBetweenWeek(String searchYear, String weekTime) {
		Calendar currentDate = Calendar.getInstance();
		int year = Integer.parseInt(searchYear);
		int week = Integer.parseInt(weekTime) - 1;

		currentDate.set(Calendar.YEAR, year);
		currentDate.set(Calendar.MONTH, Calendar.JANUARY);
		currentDate.set(Calendar.DATE, 1);
		currentDate.add(Calendar.DATE, week * 7);
		currentDate.setFirstDayOfWeek(Calendar.MONDAY);
		currentDate.set(Calendar.HOUR_OF_DAY, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		SimpleDateFormat formatEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String beginDay = formatEnd.format(currentDate.getTime());

		currentDate.set(Calendar.HOUR_OF_DAY, 23);
		currentDate.set(Calendar.MINUTE, 59);
		currentDate.set(Calendar.SECOND, 59);
		currentDate.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		String endTime = formatEnd.format(currentDate.getTime());

		return beginDay + "&" + endTime;
	}

	/**
	 * 获取制定月起始结束日期
	 * 
	 * @param monthTime
	 * @return
	 * @author xiabing_yi 2013-9-13
	 */
	public static String getDayBetweenMonth(String monthTime) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar currentDate = Calendar.getInstance();
		Date date = new Date();
		try {
			date = format.parse(monthTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		currentDate.setTime(date);
		int i = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		String beginDate = monthTime + "-01 00:00:00";
		String endDate = monthTime + "-" + i + " 23:59:59";
		return beginDate + "&" + endDate;
	}

	/** 获取两月份之间最大天数 **/
	public static int getMaxDayDuringMonth(String startMonth, String endMonth) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar currentDate = Calendar.getInstance();
		Date date = null;
		try {
			date = format.parse(startMonth);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Integer monthDifference = getMonthDifference(startMonth, endMonth) + 1;
		if (date != null) {
			currentDate.setTime(date);
		}

		int max = 0;
		for (int i = 0; i < monthDifference; i++) {
			int tempValue = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
			if (tempValue > max) {
				max = tempValue;
			}
			currentDate.add(Calendar.MONTH, 1);
		}

		return max;
	}

	/**
	 * 比较两个日期大小
	 * 
	 * @param date1
	 * @param date2
	 * @return date1>date2: 1 | date1<date2: -1 | date1==date2: 0
	 */
	public static int compareDate(Date date1, Date date2) throws Exception {
		if (null == date1 || null == date2) {
			throw new Exception("date1 and date2 can not be null");
		}
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);
		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		if (year1 > year2) {
			return 1;
		} else if (year1 < year2) {
			return -1;
		} else {
			if (month1 > month2) {
				return 1;
			} else if (month1 < month2) {
				return -1;
			} else {
				if (day1 > day2) {
					return 1;
				} else if (day1 < day2) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	/**
	 * 获得两个日期之间的日期列表
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static List<Date> getBetweenDates(Date beginDate, Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		try {
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();

			int year1 = 0;
			int month1 = 0;
			int day1 = 0;

			c2.setTime(endDate);
			int year2 = c2.get(Calendar.YEAR);
			int month2 = c2.get(Calendar.MONTH);
			int day2 = c2.get(Calendar.DAY_OF_MONTH);

			int pointer = -1;
			if (compareDate(beginDate, endDate) <= 0) {
				do {
					pointer++;
					c1.setTime(beginDate);
					c1.add(Calendar.DAY_OF_MONTH, pointer);
					dates.add(c1.getTime());
					year1 = c1.get(Calendar.YEAR);
					month1 = c1.get(Calendar.MONTH);
					day1 = c1.get(Calendar.DAY_OF_MONTH);
				} while (year1 != year2 || month1 != month2 || day1 != day2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dates;
	}

	/**
	 * 获得两个日期之间的日期字符集合
	 * 
	 * @param beginDate
	 *            yyyy-mm
	 * @param endDate
	 *            yyyy-mm
	 * @return
	 * @author xiabing_yi 2013-9-18
	 */
	public static List<String> getBetweenDates(String beginDate, String endDate) {
		String[] time = getDayBetweenMonth(endDate).split("&");

		List<String> dates = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<Date> list = getBetweenDates(format.parse(beginDate + "-01"), format.parse(time[1]));
			for (Date date : list) {
				dates.add(format.format(date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dates;
	}

	/** 获取某周所有时间 */
	public static List<String> getAllWeek(String year, String week) {
		List<String> dates = new ArrayList<String>();
		String startAndEndData = getDayBetweenWeek(year, week);
		String[] weekTime = startAndEndData.split("&");
		Date startDate = new Date();
		Date endDate = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			startDate = format.parse(weekTime[0]);
			endDate = format.parse(weekTime[1]);

			List<Date> list = getBetweenDates(startDate, endDate);
			for (Date date : list) {
				dates.add(format.format(date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dates;
	}

	/** 获取两个时间秒数差 */
	public static long getSecondsBetweenTime(Date d1, Date d2) {
		long interval = (d2.getTime() - d1.getTime()) / 1000;
		return interval;
	}

	/** 获取当前天 */
	public static String getCurrentDay() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}
	
	/** 获取每天从零点到23点时间集合 */
	public static List<String> getAllHourOfDay(String date) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String hour = "";
			if (i < 10) {
				hour = "0" + i;
			} else {
				hour = i + "";
			}
			String time = date+" "+hour;
			list.add(time);
		}
		return list;
	}
	
	/** 获取0点到当前时刻的所有时间集合(日期到时间的小时数) 时间格式为yyyy-MM-dd HH*/
	public static List<String> getAllHourBefourCurrentTime(String date) {
		String dateValue = date.split(" ")[0];
		String hourValue = date.split(" ")[1];
		int currentHour = Integer.parseInt(hourValue);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < currentHour; i++) {
			String hour = "";
			if (i < 10) {
				hour = "0" + i;
			} else {
				hour = i + "";
			}
			String time = dateValue+" "+hour;
			list.add(time);
		}
		return list;
	}
	
	/** 获取0点到当前时刻的所有时间集合(日期到时间的小时数) 时间格式为yyyy-MM-dd HH*/
	public static List<String> getAllHourBefourCurrentTime2(String date) {
		String dateValue = date.split(" ")[0];
		String hourValue = date.split(" ")[1];
		int currentHour = Integer.parseInt(hourValue);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= currentHour; i++) {
			String hour = "";
			if (i < 10) {
				hour = "0" + i;
			} else {
				hour = i + "";
			}
			String time = dateValue+" "+hour;
			list.add(time);
		}
		return list;
	}
	
	/** 获取每天从零点到23点小时分钟集合 */
	public static List<String> getAllHourMinuteOfDay(String date) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			String hour = "";
			if (i < 10) {
				hour = "0" + i;
			} else {
				hour = i + "";
			}
			String time = hour+":00" + "<br>" + date;
			list.add(time);
		}
		return list;
	}

	/** 获取当前时间前10小时的时间 */
	public static List<String> getTenHourBeforeCurrentDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder sb = new StringBuilder();
		String current = sdf.format(date);
		String currentDate = current.substring(0, 8);
		String currentTime = current.substring(8);
		List<String> list = new ArrayList<String>();
		int hour = Integer.parseInt(currentTime);
		for(int i=0; i<10; i++){
			String value="";			
			if(hour<10){
				value = "0" + hour;		
			}else{
				value = hour + "";
			}
			String data = sb.append(currentDate).append(value).toString();
			list.add(data);
			hour = hour-1;
			sb.setLength(0);
		}	
		Collections.sort(list);
		return list;
	}
	
	/** 获取当前时间前10小时的小时以及分钟 */
	public static List<String> getHourMinutesBeforeCurrentDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		StringBuilder sb = new StringBuilder();
		String current = sdf.format(date);
		String currentDate = current.substring(0, 11);
		String currentTime = current.substring(11);
		List<String> list = new ArrayList<String>();
		int hour = Integer.parseInt(currentTime);
		for(int i=0; i<10; i++){
			String value="";			
			if(hour<10){
				value = "0" + hour;		
			}else{
				value = hour + "";
			}
			String data = sb.append(value).append(":00").append("<br>").append(currentDate).toString();
			list.add(data);
			hour = hour-1;
			sb.setLength(0);
		}	
		Collections.sort(list);
		return list;
	}
	
	/** 获取给定月份所有天集合 */
	public static List<String> getAllDayOfMonth(String month) {
		List<String> list = new ArrayList<String>();
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = format.parse(month);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
		}
		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(date);
		int maxday = currentDate.getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = 1; i <= maxday; i++) {
			String day = "";
			if (i < 10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			String pDay = month + "-" + day;
			list.add(pDay);
		}
		return list;
	}
	
	/** 获取0点到当前时刻的所有时间集合(精确到分钟) 时间格式为yyyy-MM-dd HH:mm*/
	public static List<String> getAllHourAndMinuteBefourCurrentTime(String date){
		List<String> minuteDateList = new ArrayList<String>();
		String hourDate = date.substring(0,13);
		String minute = date.substring(14,16);
		List<String> hourDateList = getAllHourBefourCurrentTime(hourDate);
		for(int j=0;j<hourDateList.size();j++){
			for(int i=0;i<60;i++){
				String minutes = "";
				if (i < 10) {
					minutes = "0" + i;
				} else {
					minutes = i + "";
				}
				String minuteDate = hourDateList.get(j)+":"+minutes;
				minuteDateList.add(minuteDate);
			}
		}
		for(int i=0;i<=Integer.valueOf(minute);i++){
			String minutes = "";
			if (i < 10) {
				minutes = "0" + i;
			} else {
				minutes = i + "";
			}
			String minuteDate = hourDate+":"+minutes;
			minuteDateList.add(minuteDate);
		}
		return minuteDateList;
	}
	
	/** 获取2个时间段内的所有时间数(精确到分钟) 时间格式为yyyy-MM-dd HH*/
	public static List<String> getAllHourAndMinuteBetweenDates(String beginTime,String endTime){
		List<String> resultList = new ArrayList<String>();
		List<String> hourList = new ArrayList<String>();
		String beginRealHour = beginTime.substring(11, 13);
		String endRealHour = endTime.substring(11, 13);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTimeValue = null;
		Date endTimeValue = null;
		try {
			beginTimeValue = sdf.parse(beginTime.substring(0,10));
			endTimeValue = sdf.parse(endTime.substring(0,10));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Date> dateList= DateUtils.getBetweenDates(beginTimeValue, endTimeValue);
		List<String> dateStringList = new ArrayList<String>();
		for(int i=0;i<dateList.size();i++){
			String stringDates = sdf.format(dateList.get(i));
			dateStringList.add(stringDates);
		}
		
		for(int i=0;i<dateStringList.size();i++){
			String hourValue = null;
			if(dateStringList.size()==1){
				for(int j=Integer.parseInt(beginRealHour); j<=Integer.parseInt(endRealHour); j++){
					String hour = null;
					if(j<10){
						hour = "0" + j;
					}else{
						hour = j + "";
					}
					hourValue = dateStringList.get(i)+" "+hour;
					hourList.add(hourValue);
				}
			}else{
				if(i==0){
					for(int j=Integer.parseInt(beginRealHour); j<24; j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}
				else if(i==dateStringList.size()-1){
					for(int j=0; j<=Integer.parseInt(endRealHour); j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}else{
					for(int j=0; j<24; j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}
			}
		}
		
		for(int i=0;i<hourList.size();i++){
			String result = null;
			for(int j=0; j<60; j++){
				String minute = null;
				if(j<10){
					minute = "0" + j;
				}else{
					minute = j + "";
				}
				result = hourList.get(i)+":"+minute;
				resultList.add(result);
			}
		}
		return resultList;
	}
	
	/** 获取2个时间段内的所有时间数(精确到小时) 时间格式为yyyy-MM-dd*/
	public static List<String> getAllHourBetweenDates(String beginTime,String endTime){
		List<String> hourList = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTimeValue = null;
		Date endTimeValue = null;
		try {
			beginTimeValue = sdf.parse(beginTime.substring(0,10));
			endTimeValue = sdf.parse(endTime.substring(0,10));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Date> dateList= DateUtils.getBetweenDates(beginTimeValue, endTimeValue);
		List<String> dateStringList = new ArrayList<String>();
		for(int i=0;i<dateList.size();i++){
			String stringDates = sdf.format(dateList.get(i));
			dateStringList.add(stringDates);
		}
		
		for(int i=0;i<dateStringList.size();i++){
			String hourValue = null;
			for(int j=0; j<24; j++){
				String hour = null;
				if(j<10){
					hour = "0" + j;
				}else{
					hour = j + "";
				}
				hourValue = dateStringList.get(i)+" "+hour;
				hourList.add(hourValue);
			}
		}
		
		return hourList;
	}
	
	/** 获取2个时间段内的所有时间数(精确到天) 时间格式为yyyy-MM-dd*/
	public static List<String> getDateBetweenDates(String beginTime,String endTime){
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTimeValue = null;
		Date endTimeValue = null;
		try {
			beginTimeValue = sdf.parse(beginTime.substring(0,10));
			endTimeValue = sdf.parse(endTime.substring(0,10));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Date> dateList= DateUtils.getBetweenDates(beginTimeValue, endTimeValue);
		List<String> dateStringList = new ArrayList<String>();
		for(int i=0;i<dateList.size();i++){
			String stringDates = sdf.format(dateList.get(i));
			dateStringList.add(stringDates);
		}
		return dateStringList;
	}
	
	/** 获取该月份的所有日期，精确到天*/
	public static List<String> getAllDateOfMonth(String time){
		String year = time.substring(0,4);
		String month = time.substring(5,7);
		
		Calendar a = Calendar.getInstance();  
        a.set(Calendar.YEAR, Integer.parseInt(year));  
        a.set(Calendar.MONTH, Integer.parseInt(month) - 1);  
        a.set(Calendar.DATE, 1);  
        a.roll(Calendar.DATE, -1);  
        int maxDate = a.get(Calendar.DATE);  
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= maxDate; i++) {
			String day = "";
			if (i < 10) {
				day = "0" + i;
			} else {
				day = "" + i;
			}
			String pDay = time + "-" + day;
			list.add(pDay);
		}
		return list;
	}
	
	/** 通过年和季度获取月份*/
	public static List<String> getMonthOfYearInSeason(String year,String num){
		List<String> list = new ArrayList<String>();
		if(num.equals("1")){
			for (int i = 1; i <= 3; i++) {
				String time = "";
				if (i < 10) {
					time = "0" + i;
				} else {
					time = "" + i;
				}
				String pDay = year + "-" + time;
				list.add(pDay);
			}
		}else if(num.equals("2")){
			for (int i = 4; i <= 6; i++) {
				String time = "";
				if (i < 10) {
					time = "0" + i;
				} else {
					time = "" + i;
				}
				String pDay = year + "-" + time;
				list.add(pDay);
			}
		}else if(num.equals("3")){
			for (int i = 7; i <= 9; i++) {
				String time = "";
				if (i < 10) {
					time = "0" + i;
				} else {
					time = "" + i;
				}
				String pDay = year + "-" + time;
				list.add(pDay);
			}
		}else{
			for (int i = 10; i <= 12; i++) {
				String time = "";
				if (i < 10) {
					time = "0" + i;
				} else {
					time = "" + i;
				}
				String pDay = year + "-" + time;
				list.add(pDay);
			}
		}
		return list;
	}
	
	/** 通过年获取所有月份*/
	public static List<String> getAllMonthOfYear(String year){
		List<String> list = new ArrayList<String>();
		for (int i = 1; i <= 12; i++) {
			String time = "";
			if (i < 10) {
				time = "0" + i;
			} else {
				time = "" + i;
			}
			String pDay = year + "-" + time;
			list.add(pDay);
		}
		return list;
	}
	
	/** 获取2个时间段内的所有时间数(精确到小时) 时间格式为yyyy-MM-dd HH*/
	public static List<String> getAllHourBetweenTwoDates(String beginTime,String endTime){
		List<String> hourList = new ArrayList<String>();
		String beginRealHour = beginTime.substring(11, 13);
		String endRealHour = endTime.substring(11, 13);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTimeValue = null;
		Date endTimeValue = null;
		try {
			beginTimeValue = sdf.parse(beginTime.substring(0,10));
			endTimeValue = sdf.parse(endTime.substring(0,10));
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Date> dateList= DateUtils.getBetweenDates(beginTimeValue, endTimeValue);
		List<String> dateStringList = new ArrayList<String>();
		for(int i=0;i<dateList.size();i++){
			String stringDates = sdf.format(dateList.get(i));
			dateStringList.add(stringDates);
		}
		
		for(int i=0;i<dateStringList.size();i++){
			String hourValue = null;
			if(dateStringList.size()==1){
				for(int j=Integer.parseInt(beginRealHour); j<=Integer.parseInt(endRealHour); j++){
					String hour = null;
					if(j<10){
						hour = "0" + j;
					}else{
						hour = j + "";
					}
					hourValue = dateStringList.get(i)+" "+hour;
					hourList.add(hourValue);
				}
			}else{
				if(i==0){
					for(int j=Integer.parseInt(beginRealHour); j<24; j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}
				else if(i==dateStringList.size()-1){
					for(int j=0; j<=Integer.parseInt(endRealHour); j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}else{
					for(int j=0; j<24; j++){
						String hour = null;
						if(j<10){
							hour = "0" + j;
						}else{
							hour = j + "";
						}
						hourValue = dateStringList.get(i)+" "+hour;
						hourList.add(hourValue);
					}
				}
			}
		}
		return hourList;
	}
	
}
