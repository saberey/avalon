package com.avalon.ms.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

	 private static String lastDay = null;
	 private static final String TODAY="TODAY";
	
	  public static String dateFormat(String format, Date date) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
	        return dateFormat.format(date);
	    }
	
	/** 
	 * 
	 * @param date
	 * @return   
	 * @return boolean   
	 * @throws 
	 */
	public static boolean isWorkDay(Date date){
		boolean result = false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		dayOfWeek = dayOfWeek==0 ? 7 : dayOfWeek;
		
		String dateStr = dateFormat("yyyy-M-d", date);
		synchronized(DateUtil.class){
			if(!dateStr.equals(lastDay)){
				String url = "https://sp0.baidu.com/8aQDcjqpAAV3otqbppnN2DJv/api.php";
				Map<String,String> map = new HashMap<String, String>();
				map.put("query", dateStr);
				map.put("resource_id", "6018");
				map.put("format", "json");
				Object[] objs = RestRquestUtil.getPostData(url,map,"utf-8");
				String[] status = StringUtils.regex(dateStr+"\",\\s*\"status\":\"(\\d*)\"", objs[1].toString());
				if (status.length > 0) {
					if (status[0].equals("1"))
						result = false;
					else if (status[0].equals("2"))
						result = true;
				} else if (dayOfWeek < 6) {
					result = true;
				}
				lastDay = dateStr;
			}else{
				//result = CacheManager.getSimpleFlag(TODAY);
			}
		}
		return result;
	}
	
	public static void isWorkTime(Date date){
		//LocalTime localTime = LocalTime.now();
		LocalTime startTime = LocalTime.of(23, 30);
		LocalTime testTime = LocalTime.of(00, 31);
		LocalTime endTime = LocalTime.of(11, 31);
		LocalTime now = LocalTime.now();
		if(testTime.isAfter(startTime) || testTime.isBefore(endTime)){
			System.out.println("work time,now is "+testTime);
		}else{
			System.out.println("not work time ");
		}
	}
	
	
	public static int daysBetween(Date date1,Date date2){
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);
		long time1 = cal1.getTimeInMillis();
		cal1.setTime(date2);
		long time2 = cal1.getTimeInMillis();
		long betweenDays = (time2-time1)/(1000*3600*24);
		return Integer.parseInt(String.valueOf(betweenDays));
	}
	
	public static void main(String[] args) throws ParseException {
		/*Date date1 = new SimpleDateFormat("yyyy-mm-dd").parse("2014-09-11"); 
		Date date2 = new SimpleDateFormat("yyyy-mm-dd").parse("2017-07-26");
		int count = 1043614;
		int days = daysBetween(date1, date2);
		int avg = count/days;
		System.out.println("avg : "+avg);*/
		//isWorkTime(null);
		System.out.println(isWorkDay(new Date()));
	}
}
