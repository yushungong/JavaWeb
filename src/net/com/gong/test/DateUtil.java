package net.com.gong.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期共通类
 * 
 * @author xiang_wang
 */
public class DateUtil {

	/**
	 * 字符串类型日期格式转换。
	 */
	public static String formatDate(String inputDate, String inputFormat, String outputFormat) {
		Date outputDate = null;
		SimpleDateFormat dfInput = new SimpleDateFormat(inputFormat);
		SimpleDateFormat dfOutput = new SimpleDateFormat(outputFormat);
		try {
			outputDate = dfInput.parse(inputDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}

		return dfOutput.format(outputDate);
	}

	/**
	 * 雷达监测时间区间大于某时间段时，默认修改为该时间区间。<br/>
	 * 
	 * <p>
	 * 例如，选择开始时间为2016-12-10 10:00，结束时间为2016-12-14 10:00，但限制最大时间为48h，
	 * 则返回结束时间为2016-12-12 10:00。
	 * </p>
	 * 
	 * @return
	 */
	public static String getEndTime(String startTime, String endTime, int maxLimit, String formatStr) {

		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date startTimeDate = null;
		Date endTimeDate = null;
		String result = null;
		try {
			startTimeDate = sdf.parse(startTime);
			endTimeDate = sdf.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 判断时间差是否超过限制时间
		if (endTimeDate.getTime() - startTimeDate.getTime() > maxLimit * 60 * 60 * 1000) {
			result = sdf.format(new Date(startTimeDate.getTime() + maxLimit * 60 * 60 * 1000));
		} else {
			result = endTime;
		}

		return result;
	}
}
