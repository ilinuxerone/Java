package dateUtil;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import org.apache.commons.lang.StringUtils;  
import org.apache.log4j.Logger;

public class DateUtil {
	 private static Logger logger = Logger.getLogger(DateUtil.class);
	    private static String defaultDatePattern = null;  
	    private static String timePattern = "HH:mm";  
	    private static Calendar cale = Calendar.getInstance();  
	    public static final String TS_FORMAT = DateUtil.getDatePattern() + " HH:mm:ss.S";  
	    /** 日期格式yyyy-MM字符串常量 */  
	    private static final String MONTH_FORMAT = "yyyy-MM";  
	    /** 日期格式yyyy-MM-dd字符串常量 */  
	    private static final String DATE_FORMAT = "yyyy-MM-dd";  
	    /** 日期格式HH:mm:ss字符串常量 */  
	    private static final String HOUR_FORMAT = "HH:mm:ss";  
	    /** 日期格式yyyy-MM-dd HH:mm:ss字符串常量 */  
	    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";  
	    /** 某天开始时分秒字符串常量  00:00:00 */  
	    private static final String DAY_BEGIN_STRING_HHMMSS = " 00:00:00";  
	    /**  某天结束时分秒字符串常量  23:59:59  */  
	    public static final String DAY_END_STRING_HHMMSS = " 23:59:59";  
	    private static SimpleDateFormat sdf_date_format = new SimpleDateFormat(DATE_FORMAT);  
	    private static SimpleDateFormat sdf_hour_format = new SimpleDateFormat(HOUR_FORMAT);  
	    private static SimpleDateFormat sdf_datetime_format = new SimpleDateFormat(DATETIME_FORMAT);  
	      
	    
	
	/**
     * 把日期字符串格式化成日期类型
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convert2Date(String dateStr, String format) {  
        SimpleDateFormat simple = new SimpleDateFormat(format); 
        try {  
            simple.setLenient(false);  
            return simple.parse(dateStr);  
        } catch (Exception e) {  
            return  null;  
        }  
     }  
    
    
    /**
     * 把日期类型格式化成字符串
     * @param date
     * @param format
     * @return
     */
    public static String convert2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * 转sql的time格式
     * @param date
     * @return
     */
    public static java.sql.Timestamp convertSqlTime(Date date){
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        return timestamp;
    }
    
    /**
     * 转sql的日期格式
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDate(Date date){
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }
    
    
    /**
     * 获取当前日期
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
    
    /**
     * 获取时间戳
     * @return
     */
    public static long getTimestamp()
    {
        return System.currentTimeMillis();
    }
    
    /**
     * 获取月份的天数
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    /**
     * 获取日期的年
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    
    /**
     * 获取日期的月
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    /**
     * 获取日期的日
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }
    
    /**
     * 获取日期的时
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }
    
    /**
     * 获取日期的分种
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    
    /**
     * 获取日期的秒
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }
    
    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek-1;
    }
    
    /**
     * 获取哪一年共有多少周
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }
    
    /**
     * 取得某天是一年中的多少周
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }
    
    /**
     * 取得某天所在周的第一天
     * @param year
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        return c.getTime();
    }
    
    /**
     * 取得某天所在周的最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return c.getTime();
    }
    
    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }
    
    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }
    
    
    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }
    
    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     * @param date
     * @param amount
     * @return
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    /**
     * 增加秒
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }
    
    
    
   /**
    * time差
    * @param before
    * @param after
    * @return
    */
   public static long diffTimes(Date before, Date after){
       return after.getTime() - before.getTime();
   }
   
   /**
    * 秒差
    * @param before
    * @param after
    * @return
    */
   public static long diffSecond(Date before, Date after){
       return (after.getTime() - before.getTime())/1000;
   }
   
   /**
    * 分种差
    * @param before
    * @param after
    * @return
    */
   public static int diffMinute(Date before, Date after){
       return (int)(after.getTime() - before.getTime())/1000/60;
   }
   
   /**
    * 时差
    * @param before
    * @param after
    * @return
    */
   public static int diffHour(Date before, Date after){
       return (int)(after.getTime() - before.getTime())/1000/60/60;
   }
    
    /**
     * 天数差
     * @param date1
     * @param date2
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }
    
    /**
     * 月差
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after){
        int monthAll=0;
        int yearsX = diffYear(before,after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll=yearsX*12+monthsX;
        int daysX =c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if(daysX>0){
            monthAll=monthAll+1;
        }
        return monthAll;
    }
    
    /**
     * 年差
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }
    
    /**
     * 设置23:59:59
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.HOUR_OF_DAY, 23);  
        calendar.set(Calendar.MINUTE, 59);  
        calendar.set(Calendar.SECOND, 59);  
        return calendar.getTime();  
    } 
    
    /**
     * 设置00:00:00
     * @param date
     * @return
     */
    public static Date setStartDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.set(Calendar.HOUR_OF_DAY, 00);  
        calendar.set(Calendar.MINUTE, 00);  
        calendar.set(Calendar.SECOND, 00);  
        return calendar.getTime();  
    } 
    
    /** 
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDateTime() {  
        try {  
            return sdf_datetime_format.format(cale.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDateTime():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDate() {  
        try {  
            return sdf_date_format.format(cale.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDate():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getTime() {  
        String temp = " ";  
        try {  
            temp += sdf_hour_format.format(cale.getTime());  
            return temp;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getTime():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 统计时开始日期的默认值 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getStartDate() {  
        try {  
            return getYear() + "-01-01";  
        } catch (Exception e) {  
            logger.debug("DateUtil.getStartDate():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 统计时结束日期的默认值 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getEndDate() {  
        try {  
            return getDate();  
        } catch (Exception e) {  
            logger.debug("DateUtil.getEndDate():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期的年份 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getYear() {  
        try {  
            return String.valueOf(cale.get(Calendar.YEAR));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getYear():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器当前日期的月份 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getMonth() {  
        try {  
            java.text.DecimalFormat df = new java.text.DecimalFormat();  
            df.applyPattern("00;00");  
            return df.format((cale.get(Calendar.MONTH) + 1));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMonth():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 获得服务器在当前月中天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getDay() {  
        try {  
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));  
        } catch (Exception e) {  
            logger.debug("DateUtil.getDay():" + e.getMessage());  
            return "";  
        }  
    }  
  
    /** 
     * 比较两个日期相差的天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMargin(String date1, String date2) {  
        int margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_date_format.parse(date1, pos);  
            Date dt2 = sdf_date_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (int) (l / (24 * 60 * 60 * 1000));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 比较两个日期相差的天数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static double getDoubleMargin(String date1, String date2) {  
        double margin;  
        try {  
            ParsePosition pos = new ParsePosition(0);  
            ParsePosition pos1 = new ParsePosition(0);  
            Date dt1 = sdf_datetime_format.parse(date1, pos);  
            Date dt2 = sdf_datetime_format.parse(date2, pos1);  
            long l = dt1.getTime() - dt2.getTime();  
            margin = (l / (24 * 60 * 60 * 1000.00));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 比较两个日期相差的月数 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date1 
     * @param date2 
     * @return 
     */  
    public static int getMonthMargin(String date1, String date2) {  
        int margin;  
        try {  
            margin = (Integer.parseInt(date2.substring(0, 4)) - Integer.parseInt(date1.substring(0, 4))) * 12;  
            margin += (Integer.parseInt(date2.substring(4, 7).replaceAll("-0",  
                    "-")) - Integer.parseInt(date1.substring(4, 7).replaceAll("-0", "-")));  
            return margin;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMargin():" + e.toString());  
            return 0;  
        }  
    }  
  
    /** 
     * 返回日期加X天后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addDay(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.DATE, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addDay():" + e.toString());  
            return getDate();  
        }  
    }  
  
    /** 
     * 返回日期加X月后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addMonth(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.MONTH, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addMonth():" + e.toString());  
            return getDate();  
        }  
    }  
  
    /** 
     * 返回日期加X年后的日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param date 
     * @param i 
     * @return 
     */  
    public static String addYear(String date, int i) {  
        try {  
            GregorianCalendar gCal = new GregorianCalendar(  
                    Integer.parseInt(date.substring(0, 4)),   
                    Integer.parseInt(date.substring(5, 7)) - 1,   
                    Integer.parseInt(date.substring(8, 10)));  
            gCal.add(GregorianCalendar.YEAR, i);  
            return sdf_date_format.format(gCal.getTime());  
        } catch (Exception e) {  
            logger.debug("DateUtil.addYear():" + e.toString());  
            return "";  
        }  
    }  
  
    /** 
     * 返回某年某月中的最大天 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param year 
     * @param month 
     * @return 
     */  
    public static int getMaxDay(int iyear, int imonth) {  
        int day = 0;  
        try {  
            if (imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7  
                    || imonth == 8 || imonth == 10 || imonth == 12) {  
                day = 31;  
            } else if (imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11) {  
                day = 30;  
            } else if ((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))) {  
                day = 29;  
            } else {  
                day = 28;  
            }  
            return day;  
        } catch (Exception e) {  
            logger.debug("DateUtil.getMonthDay():" + e.toString());  
            return 1;  
        }  
    }  
  
    /** 
     * 格式化日期 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param orgDate 
     * @param Type 
     * @param Span 
     * @return 
     */  
    @SuppressWarnings("static-access")  
    public String rollDate(String orgDate, int Type, int Span) {  
        try {  
            String temp = "";  
            int iyear, imonth, iday;  
            int iPos = 0;  
            char seperater = '-';  
            if (orgDate == null || orgDate.length() < 6) {  
                return "";  
            }  
  
            iPos = orgDate.indexOf(seperater);  
            if (iPos > 0) {  
                iyear = Integer.parseInt(orgDate.substring(0, iPos));  
                temp = orgDate.substring(iPos + 1);  
            } else {  
                iyear = Integer.parseInt(orgDate.substring(0, 4));  
                temp = orgDate.substring(4);  
            }  
  
            iPos = temp.indexOf(seperater);  
            if (iPos > 0) {  
                imonth = Integer.parseInt(temp.substring(0, iPos));  
                temp = temp.substring(iPos + 1);  
            } else {  
                imonth = Integer.parseInt(temp.substring(0, 2));  
                temp = temp.substring(2);  
            }  
  
            imonth--;  
            if (imonth < 0 || imonth > 11) {  
                imonth = 0;  
            }  
  
            iday = Integer.parseInt(temp);  
            if (iday < 1 || iday > 31)  
                iday = 1;  
  
            Calendar orgcale = Calendar.getInstance();  
            orgcale.set(iyear, imonth, iday);  
            temp = this.rollDate(orgcale, Type, Span);  
            return temp;  
        } catch (Exception e) {  
            return "";  
        }  
    }  
  
    public static String rollDate(Calendar cal, int Type, int Span) {  
        try {  
            String temp = "";  
            Calendar rolcale;  
            rolcale = cal;  
            rolcale.add(Type, Span);  
            temp = sdf_date_format.format(rolcale.getTime());  
            return temp;  
        } catch (Exception e) {  
            return "";  
        }  
    }  
  
    /** 
     * 返回默认的日期格式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static synchronized String getDatePattern() {  
        defaultDatePattern = "yyyy-MM-dd";  
        return defaultDatePattern;  
    }  
  
    /** 
     * 将指定日期按默认格式进行格式代化成字符串后输出如：yyyy-MM-dd 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aDate 
     * @return 
     */  
    public static final String getDate(Date aDate) {  
        SimpleDateFormat df = null;  
        String returnValue = "";  
        if (aDate != null) {  
            df = new SimpleDateFormat(getDatePattern());  
            returnValue = df.format(aDate);  
        }  
        return (returnValue);  
    }  
  
    /** 
     * 取得给定日期的时间字符串，格式为当前默认时间格式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param theTime 
     * @return 
     */  
    public static String getTimeNow(Date theTime) {  
        return getDateTime(timePattern, theTime);  
    }  
  
    /** 
     * 取得当前时间的Calendar日历对象 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     * @throws ParseException 
     */  
    public Calendar getToday() throws ParseException {  
        Date today = new Date();  
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());  
        String todayAsString = df.format(today);  
        Calendar cal = new GregorianCalendar();  
        cal.setTime(convertStringToDate(todayAsString));  
        return cal;  
    }  
  
    /** 
     * 将日期类转换成指定格式的字符串形式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aMask 
     * @param aDate 
     * @return 
     */  
    public static final String getDateTime(String aMask, Date aDate) {  
        SimpleDateFormat df = null;  
        String returnValue = "";  
  
        if (aDate == null) {  
            logger.error("aDate is null!");  
        } else {  
            df = new SimpleDateFormat(aMask);  
            returnValue = df.format(aDate);  
        }  
        return (returnValue);  
    }  
  
    /** 
     * 将指定的日期转换成默认格式的字符串形式 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aDate 
     * @return 
     */  
    public static final String convertDateToString(Date aDate) {  
        return getDateTime(getDatePattern(), aDate);  
    }  
  
    /** 
     * 将日期字符串按指定格式转换成日期类型 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param aMask 指定的日期格式，如:yyyy-MM-dd 
     * @param strDate 待转换的日期字符串 
     * @return 
     * @throws ParseException 
     */  
    public static final Date convertStringToDate(String aMask, String strDate)  
            throws ParseException {  
        SimpleDateFormat df = null;  
        Date date = null;  
        df = new SimpleDateFormat(aMask);  
  
        if (logger.isDebugEnabled()) {  
            logger.debug("converting '" + strDate + "' to date with mask '" + aMask + "'");  
        }  
        try {  
            date = df.parse(strDate);  
        } catch (ParseException pe) {  
            logger.error("ParseException: " + pe);  
            throw pe;  
        }  
        return (date);  
    }  
  
    /** 
     * 将日期字符串按默认格式转换成日期类型 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param strDate 
     * @return 
     * @throws ParseException 
     */  
    public static Date convertStringToDate(String strDate)  
            throws ParseException {  
        Date aDate = null;  
  
        try {  
            if (logger.isDebugEnabled()) {  
                logger.debug("converting date with pattern: " + getDatePattern());  
            }  
            aDate = convertStringToDate(getDatePattern(), strDate);  
        } catch (ParseException pe) {  
            logger.error("Could not convert '" + strDate + "' to a date, throwing exception");  
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());  
        }  
        return aDate;  
    }  
  
    /** 
     * 返回一个JAVA简单类型的日期字符串 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @return 
     */  
    public static String getSimpleDateFormat() {  
        SimpleDateFormat formatter = new SimpleDateFormat();  
        String NDateTime = formatter.format(new Date());  
        return NDateTime;  
    }  
      
    /** 
     * 将指定字符串格式的日期与当前时间比较 
     * @author DYLAN 
     * @date Feb 17, 2012 
     * @param strDate 需要比较时间 
     * @return  
     *      <p> 
     *      int code 
     *      <ul> 
     *      <li>-1 当前时间 < 比较时间 </li> 
     *      <li> 0 当前时间 = 比较时间 </li> 
     *      <li>>=1当前时间 > 比较时间 </li> 
     *      </ul> 
     *      </p> 
     */  
    public static int compareToCurTime (String strDate) {  
/*        if (StringUtils.isBlank(strDate)) {  
            return -1;  
        }  */
        Date curTime = cale.getTime();  
        String strCurTime = null;  
        try {  
            strCurTime = sdf_datetime_format.format(curTime);  
        } catch (Exception e) {  
            if (logger.isDebugEnabled()) {  
                logger.debug("[Could not format '" + strDate + "' to a date, throwing exception:" + e.getLocalizedMessage() + "]");  
            }  
        }  
/*        if (StringUtils.isNotBlank(strCurTime)) {  
            return strCurTime.compareTo(strDate);  
        }  */
        return -1;  
    }  
      
    /** 
     * 为查询日期添加最小时间 
     *  
     * @param 目标类型Date 
     * @param 转换参数Date 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static Date addStartTime(Date param) {  
        Date date = param;  
        try {  
            date.setHours(0);  
            date.setMinutes(0);  
            date.setSeconds(0);  
            return date;  
        } catch (Exception ex) {  
            return date;  
        }  
    }  
  
    /** 
     * 为查询日期添加最大时间 
     *  
     * @param 目标类型Date 
     * @param 转换参数Date 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static Date addEndTime(Date param) {  
        Date date = param;  
        try {  
            date.setHours(23);  
            date.setMinutes(59);  
            date.setSeconds(0);  
            return date;  
        } catch (Exception ex) {  
            return date;  
        }  
    }  
  
    /** 
     * 返回系统现在年份中指定月份的天数 
     *  
     * @param 月份month 
     * @return 指定月的总天数 
     */  
    @SuppressWarnings("deprecation")  
    public static String getMonthLastDay(int month) {  
        Date date = new Date();  
        int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },  
                { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };  
        int year = date.getYear() + 1900;  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {  
            return day[1][month] + "";  
        } else {  
            return day[0][month] + "";  
        }  
    }  
  
    /** 
     * 返回指定年份中指定月份的天数 
     *  
     * @param 年份year 
     * @param 月份month 
     * @return 指定月的总天数 
     */  
    public static String getMonthLastDay(int year, int month) {  
        int[][] day = { { 0, 30, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 },  
                { 0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 } };  
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {  
            return day[1][month] + "";  
        } else {  
            return day[0][month] + "";  
        }  
    }  
  
    /** 
     * 判断是平年还是闰年 
     * @author dylan_xu 
     * @date Mar 11, 2012 
     * @param year 
     * @return 
     */   
    public static boolean isLeapyear(int year) {  
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400) == 0) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
   
  
    /** 
     * 取得指定时间的日戳 
     *  
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public static String getTimestamp(Date date) {  
        String timestamp = "" + (date.getYear() + 1900) + date.getMonth()  
                + date.getDate() + date.getMinutes() + date.getSeconds()  
                + date.getTime();  
        return timestamp;  
    }  
  
    public static void main(String[] args) {  
        System.out.println(getYear() + "|" + getMonth() + "|" + getDate());  
    }  
}
