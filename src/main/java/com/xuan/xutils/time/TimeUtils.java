package com.xuan.xutils.time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

/**
 * 对时间提供解析、转换和快捷方式的工具类
 * @author kotomi
 * 
 * @see LocalDate
 * @see LocalDateTime
 * @see LocalTime
 */
public final class TimeUtils {
	
	/**
	 * 强制单例
	 */
	private static boolean isSingle=true;
	
	/**
	 * 唯一实例
	 */
	private final static TimeUtils INSTANCE=new TimeUtils();
	
	/**
	 * 对小时进行24小时转换的格式化处理器
	 */
	private final DateTimeFormatter hourFormatter=DateTimeFormatter.ofPattern("HH");
	
	/**
	 * 对时分秒进行24小时转换的格式化处理器
	 */
	private final DateTimeFormatter timeFormatter=DateTimeFormatter.ofPattern("HH:mm:ss");
	
	/**
	 * 对日期进行年月级无分隔符形式的格式化处理器
	 */
	private final DateTimeFormatter monthTextFormatter=DateTimeFormatter.ofPattern("yyMM");
	
	/**
	 * 对日期进行年月日无分隔符形式的格式化处理器
	 */
	private final DateTimeFormatter dateTextFormatter=DateTimeFormatter.ofPattern("yyMMdd");
	
	/**
	 * 将时间转换为年月日格式的处理器
	 */
	private final DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	/**
	 * 将时间转换为年月日时分秒的处理器,基于24小时制
	 */
	private final DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 将时间转换为具有时区的年月日时分秒的处理器,基于24小时制
	 */
	private final DateTimeFormatter dateTimeZoneFormatter=DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	/**
	 * 将时间转换为中文格式的处理器,仅包含日期
	 */
	private final DateTimeFormatter chineseDateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日",Locale.CHINA);
	
	/**
	 * 将时间转换为中文格式的处理器,仅包含日期与周信息
	 */
	private final DateTimeFormatter chineseWeekDateFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 EEE",Locale.CHINA);
	
	/**
	 * 将时间转换为中文格式的处理器,基于24小时制,包含时分秒信息
	 */
	private final DateTimeFormatter chineseDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒",Locale.CHINA);
	
	/**
	 * 将时间转换为中文格式的处理器,基于24小时制,包含时分秒以及周的信息
	 */
	private final DateTimeFormatter chineseWeekDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒 EEE",Locale.CHINA);
	
	/**
	 * 内部调用构造器,反射型的实例化会被拒绝
	 */
	private TimeUtils(){
		if(isSingle){
			isSingle=!isSingle;
		}else{
			throw new IllegalStateException("["+TimeUtils.class.getName()+"] instance must only once.");
		}
	}
	
	/**
	 * 接收一个时间实例,将年月日信息转换为无分隔符模式
	 * 如果希望转换包括(年月信息,而不包含日级),则应该使用{@link #yearMonthTextFormat(LocalDateTime)}
	 * @param localDateTime 时间实例
	 * @return 无分隔符模式的年月日信息,年份仅使用后两位.如{@code 171103}
	 */
	public static String dateTextFormat(final LocalDateTime localDateTime){
		return localDateTime.format(INSTANCE.dateTextFormatter);
	}
	
	/**
	 * 接收一个时间实例,将年月日信息转换为无分隔符模式
	 * 如果希望转换包括(年月信息,而不包含日级),则应该使用{@link #yearMonthTextFormat(LocalDate)}
	 * @param localDate 时间实例
	 * @return 无分隔符模式的年月日信息,年份仅使用后两位.如{@code 171103}
	 */
	public static String dateTextFormat(final LocalDate localDate){
		return localDate.format(INSTANCE.dateTextFormatter);
	}
	
	/**
	 * 接收一个时间实例,将年月信息转换为无分隔符模式
	 * 如果希望转换包括(年月日信息),则应该使用{@link #dateTextFormat(LocalDateTime)}
	 * @param localDateTime 时间实例
	 * @return 无分隔符模式的年月日信息,年份仅使用后两位.如{@code 1711}
	 */
	public static String yearMonthTextFormat(final LocalDateTime localDateTime){
		return localDateTime.format(INSTANCE.monthTextFormatter);
	}
	
	/**
	 * 接收一个时间实例,将年月信息转换为无分隔符模式
	 * 如果希望转换包括(年月日信息),则应该使用{@link #dateTextFormat(LocalDate)}
	 * @param localDate 时间实例
	 * @return 无分隔符模式的年月日信息,年份仅使用后两位.如{@code 1711}
	 */
	public static String yearMonthTextFormat(final LocalDate localDate){
		return localDate.format(INSTANCE.monthTextFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其中的小时部分转换为24小时制的字符串
	 * @param localTime 表示时间的实例
	 * @return 以24小时制输出参数的转换字符.如{0=00,8=08,12=12}
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String hourFormat(final LocalTime localTime){
		return localTime.format(INSTANCE.hourFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其转换为24小时制的字符串
	 * @param localTime 表示时间的实例
	 * @return 以24小时制输出参数的转换字符
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String timeFormat(final LocalTime localTime){
		return localTime.format(INSTANCE.timeFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其中的时间部份转换为24小时制的字符串
	 * @param localDateTime 表示时间的实例
	 * @return 以24小时制输出参数的转换字符
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String timeFormat(final LocalDateTime localDateTime){
		return localDateTime.format(INSTANCE.timeFormatter);
	}
	
	/**
	 * 接收一个代表小时的数字,将其转换为24小时制的字符串
	 * @param hour 代表小时的数字
	 * @return 以24小时制输出参数的转换字符.如{0=00,8=08,12=12}
	 * @throws IllegalArgumentException 超出0-23的范围的非法小时数
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String hourFormat(int hour){
		if(hour<0||hour>23){
			throw new IllegalArgumentException("Hour of Day must in range of 0-23.but was:["+hour+"]");
		}
		return hourFormat(LocalTime.of(hour, 0));
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd}转换为字符串
	 * @param date 表示时间的实例
	 * @return 以{@code yyyy-MM-dd}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 * @deprecated 鉴于1.7及以前版本时间API的缺陷，请使用1.8的时间API</br>
	 * @see #dateFormat(LocalDate)
	 */
	public static String dateFormat(final Date date){
		if(date instanceof java.sql.Date){
			return dateFormat(((java.sql.Date)date).toLocalDate());
		}
		return dateFormat(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd}转换为字符串
	 * @param date 表示时间的实例
	 * @return 以{@code yyyy-MM-dd}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String dateFormat(final LocalDate date) {
		return date.format(INSTANCE.dateFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd}转换为字符串
	 * @param dateTime 表示时间的实例
	 * @return 以{@code yyyy-MM-dd}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String dateFormat(final LocalDateTime dateTime) {
		return dateFormat(dateTime.toLocalDate());
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy年MM月dd日}转换为字符串
	 * @param date 表示时间的实例
	 * @return 以{@code yyyy年MM月dd日}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String chineseDateFormat(final LocalDate date){
		return date.format(INSTANCE.chineseDateFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy年MM月dd日 EEE}转换为字符串
	 * @param date 表示时间的实例
	 * @return 以{@code yyyy年MM月dd日 EEE}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String chineseDateWeekFormat(final LocalDate date){
		return date.format(INSTANCE.chineseWeekDateFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy年MM月dd日 hh时mm分ss秒}转换为字符串
	 * @param dateTime 表示时间的实例
	 * @return 以{@code yyyy年MM月dd日 hh时mm分ss秒}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String chineseDateTimeFormat(final LocalDateTime dateTime){
		return dateTime.format(INSTANCE.chineseDateTimeFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy年MM月dd日 hh时mm分ss秒 EEE}转换为字符串
	 * @param dateTime 表示时间的实例
	 * @return 以{@code yyyy年MM月dd日 hh时mm分ss秒 EEE}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String chineseDateTimeWeekFormat(final LocalDateTime dateTime){
		return dateTime.format(INSTANCE.chineseWeekDateTimeFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd HH:mm:ss}转换为字符串
	 * @param dateTime 表示时间的实例
	 * @return 以{@code yyyy-MM-dd HH:mm:ss}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String dateTimeFormat(final LocalDateTime dateTime){
		return dateTime.format(INSTANCE.dateTimeFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd'T'HH:mm:ss}转换为字符串
	 * @param dateTime 表示时间的实例
	 * @return 以{@code yyyy-MM-dd'T'HH:mm:ss}格式转换后的字符串
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 */
	public static String dateTimeZoneFormat(final LocalDateTime dateTime){
		return dateTime.format(INSTANCE.dateTimeZoneFormatter);
	}
	
	/**
	 * 接收一个时间实例,将其按{@code yyyy-MM-dd HH:mm:ss}转换为字符串
	 * @param date 表示时间的实例
	 * @return 以{@code yyyy-MM-dd HH:mm:ss}格式转换后的字符串
	 * @deprecated 鉴于1.7及以前版本时间API的缺陷，请使用1.8的时间API</br>
	 * @throws NullPointerException 如果参数为空
	 * @throws DateTimeException 如果参数不合法
	 * @see #dateFormat(LocalDateTime)
	 */
	public static String dateTimeFormat(final Date date){
		return dateTimeFormat(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()));
	}
	
	/**
	 * 将指定的字符串转换为时间实例
	 * @param time 表示时间的字符串{@code yyyy-MM-dd}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException {@code yyyy-MM-dd HH:mm:ss}无法转换
	 * @deprecated
	 * @see #dateTimeToDate(String)
	 */
	public static Date dateToDate(final String time){
		return Date.from(dateParse(time).atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * 将指定的字符串转换为时间实例
	 * @param time 表示时间的字符串{@code yyyy-MM-dd HH:mm:ss}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException {@code yyyy-MM-dd}无法转换
	 * @deprecated
	 * @see #dateToDate(String)
	 */
	public static Date dateTimeToDate(final String time){
		return Date.from(dateTimeParse(time).atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * 指给定的jsr310新时间API转换为1.7的时间实体,保留时分秒信息
	 * @param dateTime 时间实体
	 * @return 与其对应的时间实体
	 * @deprecated 仅为对旧代码提供兼容,程序应当使用jsr310的API
	 * @see #localDateToDate(LocalDate)
	 */
	public static Date localDateTimeToDate(LocalDateTime dateTime){
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * 指给定的jsr310新时间API转换为1.7的时间实体,不保留时分秒信息
	 * @param date 时间实体
	 * @return 与其对应的时间实体
	 * @deprecated 仅为对旧代码提供兼容,程序应当使用jsr310的API
	 */
	public static Date localDateToDate(LocalDate date){
		return Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * 将{@code date}对象转换为jsr310的新时间API,包含时分秒信息
	 * @param date 日期对象
	 * @return jsr310的新时间表示实体
	 */
	public static LocalDateTime dateToLocalDateTime(Date date){
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}
	
	/**
	 * 将{@code date}对象转换为jsr310的新时间API,只包含年月日信息
	 * @param date 日期对象
	 * @return jsr310的新时间表示实体
	 */
	public static LocalDate dateToLocalDate(Date date){
		return dateToLocalDateTime(date).toLocalDate();
	}
	
	/**
	 * 将指定的字符串转换为日期
	 * @param time 表示时间的字符串{@code yyyy-MM-dd}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException {@code yyyy-MM-dd HH:mm:ss}无法转换
	 */
	public static LocalDate dateParse(final String time) {
		return LocalDate.parse(time, INSTANCE.dateFormatter);
	}
	
	/**
	 * 将指定的字符串转换为日期
	 * @param time 表示时间的字符串{@code yyMMdd}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException 如果格式不支持
	 */
	public static LocalDate dateTextParse(final String time) {
		return LocalDate.parse(time, INSTANCE.dateTextFormatter);
	}
	
	/**
	 * 将指定的字符串转换为时分秒形式的时间实体
	 * @param time 表示时间的字符串{@code HH:mm:ss}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException {@code yyyy-MM-dd HH:mm:ss}与{@code yyyy-MM-dd}无法转换
	 */
	public static LocalTime timeParse(final String time) {
		return LocalTime.parse(time, INSTANCE.timeFormatter);
	}
	
	/**
	 * 将指定的字符串转换为日期+时间的对象
	 * @param time 表示时间的字符串{@code yyyy-MM-dd HH:mm:ss}格式
	 * @return 时间实例
	 * @throws NullPointerException 如果参数是{@code null}
	 * @throws DateTimeParseException {@code yyyy-MM-dd}无法转换
	 */
	public static LocalDateTime dateTimeParse(final String time) {
		return LocalDateTime.parse(time,INSTANCE.dateTimeFormatter);
	}
	
	/**
	 * 应对破坏单例的攻击,虽然类本身没有实现序列化接口
	 * @return 丢弃新的实例,强制使用唯一实例
	 */
	private Object readResolve(){
		return INSTANCE;
	}
}
