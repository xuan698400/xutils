package com.xuan.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 处理日期时间的工具类。
 * 
 * @author xuan
 * @version $Revision: 1.0 $, $Date: 2012-11-22 上午9:46:12 $
 */
public abstract class DateUtils {

    private static final int[] DAY_OF_MONTH = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    /**
     * 取得指定天数后的时间
     * 
     * @param date
     *            基准时间
     * @param dayAmount
     *            指定天数，允许为负数
     * @return 指定天数后的时间
     */
    public static Date addDay(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定小时数后的时间
     * 
     * @param date
     *            基准时间
     * @param hourAmount
     *            指定小时数，允许为负数
     * @return 指定小时数后的时间
     */
    public static Date addHour(Date date, int hourAmount) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, hourAmount);
        return calendar.getTime();
    }

    /**
     * 取得指定分钟数后的时间
     * 
     * @param date
     *            基准时间
     * @param minuteAmount
     *            指定分钟数，允许为负数
     * @return 指定分钟数后的时间
     */
    public static Date addMinute(Date date, int minuteAmount) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minuteAmount);
        return calendar.getTime();
    }

    /**
     * 比较两日期对象中的小时和分钟部分的大小.
     * 
     * @param date
     *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @param anotherDate
     *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareHourAndMinute(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }

        if (anotherDate == null) {
            anotherDate = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hourOfDay1 = cal.get(Calendar.HOUR_OF_DAY);
        int minute1 = cal.get(Calendar.MINUTE);

        cal.setTime(anotherDate);
        int hourOfDay2 = cal.get(Calendar.HOUR_OF_DAY);
        int minute2 = cal.get(Calendar.MINUTE);

        if (hourOfDay1 > hourOfDay2) {
            return 1;
        }
        else if (hourOfDay1 == hourOfDay2) {
            // 小时相等就比较分钟
            return minute1 > minute2 ? 1 : (minute1 == minute2 ? 0 : -1);
        }
        else {
            return -1;
        }
    }

    /**
     * 比较两日期对象的大小, 忽略秒, 只精确到分钟.
     * 
     * @param date
     *            日期对象1, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @param anotherDate
     *            日期对象2, 如果为 <code>null</code> 会以当前时间的日期对象代替
     * @return 如果日期对象1大于日期对象2, 则返回大于0的数; 反之返回小于0的数; 如果两日期对象相等, 则返回0.
     */
    public static int compareIgnoreSecond(Date date, Date anotherDate) {
        if (date == null) {
            date = new Date();
        }

        if (anotherDate == null) {
            anotherDate = new Date();
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        date = cal.getTime();

        cal.setTime(anotherDate);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        anotherDate = cal.getTime();

        return date.compareTo(anotherDate);
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10 20:56:30.756
     * 
     * @return 当前时间的字符串表示
     */
    public static String currentDate2String() {
        return date2String(new Date());
    }

    /**
     * 取得当前时间的字符串表示，格式为2006-01-10
     * 
     * @return 当前时间的字符串表示
     */
    public static String currentDate2StringByDay() {
        return date2StringByDay(new Date());
    }

    /**
     * 取得今天的最后一个时刻
     * 
     * @return 今天的最后一个时刻
     */
    public static Date currentEndDate() {
        return getEndDate(new Date());
    }

    /**
     * 取得今天的第一个时刻
     * 
     * @return 今天的第一个时刻
     */
    public static Date currentStartDate() {
        return getStartDate(new Date());
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56:30.756
     * 
     * @param date
     *            时间
     * @return 时间字符串
     */
    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    /**
     * 按照指定格式把时间转换成字符串，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param date
     *            时间
     * @param pattern
     *            格式
     * @return 时间字符串
     */
    public static String date2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10
     * 
     * @param date
     *            时间
     * @return 时间字符串
     */
    public static String date2StringByDay(Date date) {
        return date2String(date, "yyyy-MM-dd");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56
     * 
     * @param date
     *            时间
     * @return 时间字符串
     */
    public static String date2StringByMinute(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm");
    }

    /**
     * 把时间转换成字符串，格式为2006-01-10 20:56:30
     * 
     * @param date
     *            时间
     * @return 时间字符串
     */
    public static String date2StringBySecond(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 根据某星期几的英文名称来获取该星期几的中文数. <br>
     * e.g. <li>monday -> 一</li> <li>sunday -> 日</li>
     * 
     * @param englishWeekName
     *            星期的英文名称
     * @return 星期的中文数
     */
    public static String getChineseWeekNumber(String englishWeekName) {
        if ("monday".equalsIgnoreCase(englishWeekName)) {
            return "一";
        }

        if ("tuesday".equalsIgnoreCase(englishWeekName)) {
            return "二";
        }

        if ("wednesday".equalsIgnoreCase(englishWeekName)) {
            return "三";
        }

        if ("thursday".equalsIgnoreCase(englishWeekName)) {
            return "四";
        }

        if ("friday".equalsIgnoreCase(englishWeekName)) {
            return "五";
        }

        if ("saturday".equalsIgnoreCase(englishWeekName)) {
            return "六";
        }

        if ("sunday".equalsIgnoreCase(englishWeekName)) {
            return "日";
        }

        return null;
    }

    /**
     * 根据指定的年, 月, 日等参数获取日期对象.
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date) {
        return getDate(year, month, date, 0, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分等参数获取日期对象.
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param hourOfDay
     *            时(24小时制)
     * @param minute
     *            分
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute) {
        return getDate(year, month, date, hourOfDay, minute, 0);
    }

    /**
     * 根据指定的年, 月, 日, 时, 分, 秒等参数获取日期对象.
     * 
     * @param year
     *            年
     * @param month
     *            月
     * @param date
     *            日
     * @param hourOfDay
     *            时(24小时制)
     * @param minute
     *            分
     * @param second
     *            秒
     * @return 对应的日期对象
     */
    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, date, hourOfDay, minute, second);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 取得某个日期是星期几，星期日是1，依此类推
     * 
     * @param date
     *            日期
     * @return 星期几
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取某天的结束时间, e.g. 2005-10-01 23:59:59.999
     * 
     * @param date
     *            日期对象
     * @return 该天的结束时间
     */
    public static Date getEndDate(Date date) {

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * 取得一个月最多的天数
     * 
     * @param year
     *            年份
     * @param month
     *            月份，0表示1月，依此类推
     * @return 最多的天数
     */
    public static int getMaxDayOfMonth(int year, int month) {
        if (month == 1 && isLeapYear(year)) {
            return 29;
        }
        return DAY_OF_MONTH[month];
    }

    /**
     * 得到指定日期的下一天
     * 
     * @param date
     *            日期对象
     * @return 同一时间的下一天的日期对象
     */
    public static Date getNextDay(Date date) {
        return addDay(date, 1);
    }

    /**
     * 获取某天的起始时间, e.g. 2005-10-01 00:00:00.000
     * 
     * @param date
     *            日期对象
     * @return 该天的起始时间
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 根据日期对象来获取日期中的时间(HH:mm:ss).
     * 
     * @param date
     *            日期对象
     * @return 时间字符串, 格式为: HH:mm:ss
     */
    public static String getTime(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(date);
    }

    /**
     * 根据日期对象来获取日期中的时间(HH:mm).
     * 
     * @param date
     *            日期对象
     * @return 时间字符串, 格式为: HH:mm
     */
    public static String getTimeIgnoreSecond(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }

    /**
     * 判断是否是闰年
     * 
     * @param year
     *            年份
     * @return 是true，否则false
     */
    public static boolean isLeapYear(int year) {
        Calendar calendar = Calendar.getInstance();
        return ((GregorianCalendar) calendar).isLeapYear(year);
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10
     * 
     * @param str
     *            字符串
     * @return 日期
     */
    public static Date string2Date(String str) {
        return string2Date(str, "yyyy-MM-dd");
    }

    /**
     * 按照指定的格式把字符串转换成时间，格式的写法类似yyyy-MM-dd HH:mm:ss.SSS
     * 
     * @param str
     *            字符串
     * @param pattern
     *            格式
     * @return 时间
     */
    public static Date string2Date(String str, String pattern) {
        if (Validators.isEmpty(str)) {
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        }
        catch (ParseException e) {
            // ignore
        }
        return date;
    }

    /**
     * 把字符串转换成日期，格式为2006-01-10 20:56:30
     * 
     * @param str
     *            字符串
     * @return 日期
     */
    public static Date string2DateTime(String str) {
        return string2Date(str, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 取得一年中的第几周。
     * 
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取上周的指定星期的日期。
     * 
     * @param dayOfWeek
     *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfPreviousWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }

        return getDateOfRange(dayOfWeek, -7);
    }

    /**
     * 获取本周的指定星期的日期。
     * 
     * @param dayOfWeek
     *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfCurrentWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }

        return getDateOfRange(dayOfWeek, 0);
    }

    /**
     * 获取下周的指定星期的日期。
     * 
     * @param dayOfWeek
     *            星期几，取值范围是 {@link Calendar#MONDAY} - {@link Calendar#SUNDAY}
     */
    public static Date getDateOfNextWeek(int dayOfWeek) {
        if (dayOfWeek > 7 || dayOfWeek < 1) {
            throw new IllegalArgumentException("参数必须是1-7之间的数字");
        }

        return getDateOfRange(dayOfWeek, 7);
    }

    private static Date getDateOfRange(int dayOfWeek, int dayOfRange) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + dayOfRange);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

}
