package com.xuan.xutils.time;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Clock;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;

/**
 * 农历日期历法.该类支持的年份是{@value #MIN_YEAR}-{@value #MAX_YEAR}
 * 由于农历历法并无演算规律,所以对于时间的支持只限于人为计算后的年份值
 * 而并不能无限的演算下去,该类保证线程安全性与不变性
 * @author kotomikot
 *
 */
public final class LunarCalendarLocalDate implements Comparable<LunarCalendarLocalDate>, Serializable{
	
	private static final long serialVersionUID = -380960968731320774L;

	/**
	 * 近150年的阴历数据
	 * 从公历1900年1月31日(农历 己亥猪年(1900)正月初一)开始
	 * 到公历2050年1月22日(农历 己巳蛇年(2049)腊月廿九)结束
	 * 
	 * 数据结构关系:共17位数据
	 * 第17位:表示当年闰月的天数(0表示29天,1表示30天)
	 * 第16-第5位:表示12个月,16=1月依次类推.天数关系(0表示29天,1表示30天)
	 * 第4位-第1位:表示闰月是哪个月,如果当年没有闰月,则为0
	 */
	static private final int[] LUNAR_INFO = { 
			0x04bd8, 0x04ae0, 0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0, 0x055d2, 0x04ae0,
			0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540, 0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 
			0x0b4b5, 0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970, 0x06566, 0x0d4a0, 0x0ea50, 
			0x06e95, 0x05ad0, 0x02b60, 0x186e3, 0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0, 
			0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0, 0x0b550, 0x15355, 0x04da0, 0x0a5d0, 
			0x14573, 0x052d0, 0x0a9a8, 0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570, 0x05260, 
			0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5, 0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 
			0x0b540, 0x0b5a0, 0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50, 0x06d40, 0x0af46,
			0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0, 0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 
			0x092e0, 0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7, 0x025d0, 0x092d0, 0x0cab5, 
			0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50, 0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954, 
			0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260, 0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 
			0x096d0, 0x04bd7, 0x04ad0, 0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0, 0x055b2, 
			0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20, 0x0ada0
	};
	
	/** 农历大月最大天数 */
	static private final int DAYS_OF_BIG_MONTH=30;
	
	/** 农历小月最大天数 */
	static private final int DAYS_OF_SMALL_MONTH=29;
	
	/** 实例支持的最早农历年,该值应当与{@link #LUNAR_INFO}中第一元素对应 */
	static private final int MIN_YEAR=1900;
	
	/** 实例支持的最晚农历年,该值应当与{@link #LUNAR_INFO}中最后元素对应 */
	static private final int MAX_YEAR=2049;
	
	/** 计算基准日,以公历相对于该基准公历日的偏移量计算农历, 低于此时间的公历日期无法计算. 该值应当与{@link #LUNAR_INFO}中第一元素对应 */
	static private final LocalDate MIN_LOCAL_DATE=LocalDate.of(1900, 1, 31);
	
	/** 最大支持公历日期,高于此时间的公历日期无法计算. 该值应当与{@link #LUNAR_INFO}中第一元素对应 */
	static private final LocalDate MAX_LOCAL_DATE=LocalDate.of(2050, 1, 22);
	
	/** 支持的最低农历日期,低于此农历日期无法计算 */
	static public final LunarCalendarLocalDate MIN= LunarCalendarLocalDate.of(MIN_LOCAL_DATE);
	
	/** 支持的最高农历日期,低于此农历日期无法计算 */
	static public final LunarCalendarLocalDate MAX= LunarCalendarLocalDate.of(MAX_LOCAL_DATE);
	
	/**
	 * 中国农历生肖表示,该枚举通常与农历年的相关操作是密不可分的
	 * @author Alan
	 *
	 */
	public static enum Zodiac {
		
		/** 子鼠 */
		MOUSE,
		
		/** 丑牛 */
		OX,
		
		/** 寅虎 */
		TIGER,
		
		/** 卯兔 */
		RABBIT,
		
		/** 辰龙 */
		DRAGON,
		
		/** 巳蛇 */
		SNAKE,
		
		/** 午马 */
		HORSE,
		
		/** 未羊 */
		SHEEP,
		
		/** 申猴 */
		MONKEY,
		
		/** 酉鸡 */
		ROOSTER,
		
		/** 戌狗 */
		DOG,
		
		/** 亥猪 */
		PIG;
		
		static private final Zodiac[] ENUMS=Zodiac.values();
		
		/**
		 * 获取某农历年相对应的生肖
		 * @param year 农历年
		 * @return 生肖枚举
		 */
		public static Zodiac of(int year){
			int zodiacIndex=(year-MIN_YEAR)%ENUMS.length;
			return ENUMS[zodiacIndex>=0?zodiacIndex:ENUMS.length+zodiacIndex];
		}
		
		/**
		 * 获取公历日期的生肖
		 * @param localDate 公历日期实体
		 * @return 生肖枚举
		 */
		public static Zodiac of(LocalDate localDate){
			return of(LunarCalendarLocalDate.of(localDate));
		}
		
		/**
		 * 获取农历日期的生肖
		 * @param lunarCalendarLocalDate 农历日期实体
		 * @return 生肖枚举
		 */
		public static Zodiac of(LunarCalendarLocalDate lunarCalendarLocalDate){
			return of(lunarCalendarLocalDate.year);
		}
		
		/**
		 * 获取当前生肖年之后N年的生肖实例
		 * @param yearNum 相对当前生肖年的后偏移年数
		 * @return 生肖枚举
		 */
		public Zodiac plus(long yearNum){
			 return ENUMS[(ordinal() + (((int)yearNum%ENUMS.length)+ ENUMS.length)) % ENUMS.length];
		}
		
		/**
		 * 获取当前生肖年之前N年的生肖实例
		 * @param yearNum 相对当前生肖年的前偏移年数
		 * @return 生肖枚举
		 */
		public Zodiac minus(long yearNum){
			return plus(-yearNum);
		}
	}
	
	/** 实例的农历年份 */
	private final int year;
	
	/** 实例的农历月份 */
	private final short month;
	
	/** 实例的农历天数 */
	private final short day;
	
	/** 实例表示的当前月份是否为闰月 */
	private final boolean isLeapMonth;
	
	/** 实例对应的公历 */
	private final transient LocalDate gregorianCalendar;
	
	/**
	 * 以系统默认时区及时钟构造一个实例
	 * @return 基于系统默认时区和时钟的农历实例
	 */
	public static LunarCalendarLocalDate now(){
		return now(Clock.systemDefaultZone());
	}
	
	/**
	 * 以指定的时区与当前系统的时钟偏移构造一个实例
	 * @param zoneId 时区信息实例
	 * @return 指定的时区与当前系统的时钟偏移构造的农历实例
	 * @throws NullPointerException 若时区参数为{@code null}
	 */
	public static LunarCalendarLocalDate now(ZoneId zoneId){
		return now(Clock.system(zoneId));
	}
	
	/**
	 * 以指定的时钟构造一个实例
	 * @param clock 拥有时区及时间信息的时钟实例
	 * @return 指定的时钟构造一个实例的农历实例
	 * @throws IllegalArgumentException 如果参数为{@code null}
	 */
	public static LunarCalendarLocalDate now(Clock clock){
		if(clock==null){
			throw  new NullPointerException("Clock can not be null.");
		}
		return LunarCalendarLocalDate.of(LocalDate.now(clock));
	}
	
	/**
     * 以指定的农历年、月、日构造一个农历实例
     * 该方法从不匹配闰月{@link #ofLeapMonthFirst(int, int, int)}
     * @param year 农历年
     * @param month 月份
     * @param dayOfMonth 当月天数
     * @return 农历实例
     * @throws DateTimeException 若日期超出了当年当月的最大日期或农历年份无法支持
     */
	public static LunarCalendarLocalDate of(int year, int month, int dayOfMonth){
		return create(year, month, dayOfMonth);
	}
	
	/**
     * 以指定的农历年、月、日构造一个农历实例
     * 该方法总会优先匹配闰月,若当年的闰月与参数月相同,则构造的实例表示的是闰月.
     * {@link #of(int, int, int)}
     * @param year 农历年
     * @param month 月份
     * @param dayOfMonth 当月天数
     * @return 农历实例
     * @throws DateTimeException 若日期超出了当年当月的最大日期或农历年份无法支持
     */
	public static LunarCalendarLocalDate ofLeapMonthFirst(int year, int month, int dayOfMonth){
		return createFirstLeapMonth(year, month, dayOfMonth);
	}
	
	/**
	 * 根据农历年份以及当年的天数构造一个实例
	 * @param year 年份
	 * @param dayOfYears 当年的相对天数
	 * @return 农历实例
	 * @throws DateTimeException 若参数中的天数超出当年最大天数或年份不被支持
	 */
	public static LunarCalendarLocalDate of(int year, int dayOfYears){
		checkYear(year);
		int maxDayOfYears=getYearDays(year);
		if(dayOfYears<1||dayOfYears>maxDayOfYears){
			throw new DateTimeException("Lunar Calendar max day of years:"+year+" is 1-"+maxDayOfYears+". but was:"+dayOfYears);
		}
		int dayOfMonth=dayOfYears, leapMonth=getLeapMonth(year), month;
		boolean initLeapMonth=leapMonth!=0, isLeapMonth=false;
		for(month=1; month<=12; month++){
			int maxDayOfMonth;
			if(initLeapMonth&&leapMonth==month-1){
				maxDayOfMonth=getLeapMonthDays(year);
				if(dayOfMonth-maxDayOfMonth<1){
					isLeapMonth=true;
				}
				month--;
			}else{
				maxDayOfMonth=getMonthDays(year, month);
			}
			if((dayOfMonth-=maxDayOfMonth)<1){
				dayOfMonth+=maxDayOfMonth;
				break;
			}
		}
		return create(year, month, dayOfMonth, isLeapMonth);
	}
	
	/**
	 * 以世纪天数构造一个实例,世纪天数标识是从公历1970-01-01开始
	 * @param epochDay 从公历1970-01-01偏移的天数,负数则为在此日期之前
	 * @return 指定的时钟构造一个实例的农历实例
	 */
	public static LunarCalendarLocalDate ofEpochDay(long epochDay){
    	return of(LocalDate.ofEpochDay(epochDay));
    }
    
	/**
	 * 以公历日期实例构造一个对应的农历日期实例
	 * @param localDate 公历日期实例
	 * @return 对应公历日期的农历日期实例
	 */
    public static LunarCalendarLocalDate of(LocalDate localDate){
    	if(localDate.isBefore(MIN_LOCAL_DATE)||localDate.isAfter(MAX_LOCAL_DATE)){
    		throw new DateTimeException("only supported date range between:"+MIN_LOCAL_DATE+" and "+MAX_LOCAL_DATE+".but was:"+localDate);
    	}
    	int offsetDays=(int)MIN_LOCAL_DATE.until(localDate, ChronoUnit.DAYS)+1;
    	int lunarYear, lunarMonth;
    	boolean isLeapMonth=false;
    	for(lunarYear=MIN_YEAR; lunarYear<=MAX_YEAR; lunarYear++){
    		int yearDays=getYearDays(lunarYear);
    		if((offsetDays-=yearDays)<1){
    			offsetDays+=yearDays;
    			break;
    		}
    	}
    	int leapMonth=getLeapMonth(lunarYear);
    	boolean initLeapMonth=leapMonth!=0;
    	for(lunarMonth=1; lunarMonth<=12; lunarMonth++){
    		int daysOfMonth;
    		if(initLeapMonth&&leapMonth==lunarMonth-1){
    			daysOfMonth=getLeapMonthDays(lunarYear);
    			initLeapMonth=false;
    			isLeapMonth=offsetDays-daysOfMonth<1;
    			--lunarMonth;
    		}else{
    			daysOfMonth=getMonthDays(lunarYear, lunarMonth);
    		}
    		if((offsetDays-=daysOfMonth)<1){
    			offsetDays+=daysOfMonth;
    			break;
    		}
    	}
    	return create(lunarYear, lunarMonth, offsetDays, isLeapMonth);
    }
    
    /**
     * 以指定的时间表示器构造一个农历实例
     * @param temporal 时间表示器
     * @return 对应的农历实例
     * @throws IllegalArgumentException 若参数无法被解析
     */
    public static LunarCalendarLocalDate from(TemporalAccessor temporal){
    	if(temporal==null){
    		throw new NullPointerException("temporal can not be null.");
		}
    	LocalDate localDate=temporal.query(TemporalQueries.localDate());
    	if (localDate == null) {
            throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " +
                    temporal + " of type " + temporal.getClass().getName());
        }
    	return of(localDate);
    }
    
    /**
     * 以指定的农历年、月、日构造一个农历实例
     * 与{@link #create(int, int, int, boolean)}不同的是,该方法从不匹配闰月
     * @param year 农历年
     * @param month 月份
     * @param dayOfMonth 当月天数
     * @return 农历实例
     * @throws DateTimeException 若日期超出了当年当月的最大日期或农历年份无法支持
     */
    private static LunarCalendarLocalDate create(int year, int month, int dayOfMonth){
    	checkYear(year);
    	ChronoField.MONTH_OF_YEAR.checkValidValue(month);
    	return create(year, month, dayOfMonth, false);
    }
    
    /**
     * 以指定的农历年、月、日构造一个农历实例,与{@link #create(int, int, int, boolean)}不同的是
     * 该方法总会优先匹配闰月,若当年的闰月与参数月相同,则构造的实例表示的是闰月.
     * @param year 农历年
     * @param month 月份
     * @param dayOfMonth 当月天数
     * @return 农历实例
     * @throws DateTimeException 若日期超出了当年当月的最大日期或农历年份无法支持
     */
    private static LunarCalendarLocalDate createFirstLeapMonth(int year, int month, int dayOfMonth){
    	checkYear(year);
    	ChronoField.MONTH_OF_YEAR.checkValidValue(month);
    	return create(year, month, dayOfMonth, getLeapMonth(year)==month);
    }
    
    /**
     * 以指定的农历年、月、日、是否为闰月构造一个农历实例
     * @param year 农历年
     * @param month 月份
     * @param dayOfMonth 当月天数
     * @param isLeapMonth 当月是否为闰月
     * @return 农历实例
     * @throws DateTimeException 若参数为闰月,而当年的闰月并不与参数月匹配.若日期超出了当年当月的最大日期或农历年份无法支持
     */
    private static LunarCalendarLocalDate create(int year, int month, int dayOfMonth, boolean isLeapMonth){
    	checkYear(year);
		ChronoField.MONTH_OF_YEAR.checkValidValue(month);
		if(isLeapMonth){
			checkDayOfLeapMonth(dayOfMonth, month, year);
		}else {
			checkDayOfMonth(dayOfMonth, month, year);
		}
		return new LunarCalendarLocalDate(year, month, dayOfMonth, isLeapMonth);
    }
    
	/**
	 * 获取某农历年某月份的最大天数
	 * 该操作无法获取闰月的天数,应当使用{@link #getLeapMonthDays(int)}
	 * @param lunarYeay 农历年份
	 * @param month 农历月份
	 * @return 该年该月的最大天数
	 * @throws DateTimeException 若月份为非法月份
	 */
    private static int getMonthDays(int lunarYeay, int month){
    	ChronoField.MONTH_OF_YEAR.checkValidValue(month);
        return ((LUNAR_INFO[lunarYeay - MIN_YEAR] & 0x0FFFF)&(1 << (16-month)))==0?DAYS_OF_SMALL_MONTH:DAYS_OF_BIG_MONTH;
    }
    
    /**
     * 获取某农历年闰月的最大天数
     * 该操作只取闰月的天数,自然月当使用{@link #getMonthDays(int, int)}
     * @param year 农历年份
     * @return 闰月的最大天数,不存在闰月则为0
     */
    private static int getLeapMonthDays(int year) {
    	return getLeapMonth(year)==0?0:(LUNAR_INFO[year - MIN_YEAR] & 0xf0000)==0?DAYS_OF_SMALL_MONTH:DAYS_OF_BIG_MONTH;
    }
    
    /**
	 * 获取某农历年的闰月
	 * @param year 农历年份
	 * @return 闰月(1-12),不存在闰月则为0
	 */
    private static int getLeapMonth(int year) {
        return (int) (LUNAR_INFO[year - MIN_YEAR] & 0xf);
    }
    
    /**
     * 获取某农历年的总天数
     * @param year 农历年份
     * @return 该年的总天数
     */
    private static int getYearDays(int year) {
        int sum = 29*12;
        for(int i=0x8000;i>=0x8;i>>=1){
            if((LUNAR_INFO[year-MIN_YEAR]&0xfff0&i)!=0){
                sum++;
            }
        }
        return sum+getLeapMonthDays(year);
    }
    
    /**
	 * 获取指定的农历年月日表示的天数相对于当年,表示法是基于农历的
	 * @return 年月日表示的当年的天数
	 */
	private static int getDayOfYear(int year, int month, int day, boolean isLeapMonth){
		int offsetDays=0;
		for(int i=1; i<month; i++){
			offsetDays+=getMonthDays(year, i);
		}
		if(isLeapMonth){
			offsetDays+=getMonthDays(year, month);
		}else {
			//获取实例农历年的闰月
			int leapMonth=getLeapMonth(year);
			if(month>leapMonth){
				offsetDays+=getLeapMonthDays(year);
			}
		}
		return offsetDays+day;
	}
	
	/**
	 * 获取指定的农历年月日相对于基准最小年的偏移天数
	 * @param year 农历年份
	 * @param month 月份
	 * @param day 天数
	 * @param isLeapMonth 是否为闰月
	 * @return 相对于起始年度的偏移天数
	 */
	private static int getOffSetDayOfMinLocalDate(int year, int month, int day, boolean isLeapMonth){
		int offsetDays=0;
		for(int i=MIN_YEAR; i<year; i++){
			offsetDays+=getYearDays(i);
		}
		return offsetDays+getDayOfYear(year, month, day, isLeapMonth);
	
	}
    
    /**
     * 检查农历年份是否被支持
     * @param year 农历年
     * @throws DateTimeException 农历年不被支持
     */
    private static void checkYear(int year){
    	if(year<MIN_YEAR||year>MAX_YEAR){
    		throw new DateTimeException("Only supports lunar years between "+MIN_YEAR+" - "+MAX_YEAR+". but was:"+year);
    	}
    }
    
    /**
     * 检查指定的天数是否超出农历法该年该月的最大天数
     * 该方法不会对闰月进行检查{@link #checkDayOfLeapMonth(int, int, int)}
     * @param dayOfMonth 天数
     * @param month 月份
     * @param year 农历年
     * @throws DateTimeException 若天数不合法
     */
    private static void checkDayOfMonth(int dayOfMonth, int month, int year){
    	int maxDays=getMonthDays(year, month);
    	if(dayOfMonth<1||dayOfMonth>maxDays){
    		throw new DateTimeException("Lunar Calendar of year:"+year+",month:"+month+",days:1-"+maxDays+". but was:"+dayOfMonth);
    	}
    }
    
    /**
     * 检查指定的天数是否超出农历法该年该月的最大天数
     * 该方法只会检查闰月,检查自然月请使用{@link #checkDayOfMonth(int, int, int)}
     * @param dayOfLeapMonth 天数
     * @param month 月份
     * @param year 农历年
     * @throws DateTimeException 若天数不合法
     */
    private static void checkDayOfLeapMonth(int dayOfLeapMonth, int month, int year){
    	int leapMonth=getLeapMonth(year);
    	if(leapMonth==0){
    		throw new DateTimeException("the lunar canendar is no leap month of year:"+year);
    	}
    	if(leapMonth!=month){
    		throw new DateTimeException("the lunar canendar of year:"+year+"'s leap month is:"+leapMonth+". but was:"+month);
    	}
    	int maxDayOfLeapMonth=getLeapMonthDays(year);
    	if(dayOfLeapMonth<1||dayOfLeapMonth>maxDayOfLeapMonth){
    		throw new DateTimeException("Lunar Calendar of year:"+year+",month:"+month+",days:1-"+maxDayOfLeapMonth+". but was:"+dayOfLeapMonth);
    	}
    }
    
    /**
     * 构造一个农历的实例,按指定的年、月、日基于农历
     * @param year 农历年份
     * @param month 月份
     * @param day 天数
     * @param isLeapMonth 月份是否是闰月
     */
	private LunarCalendarLocalDate(int year, int month, int day, boolean isLeapMonth) {
		super();
		this.year = year;
		this.month = (short) month;
		this.day = (short) day;
		this.isLeapMonth=isLeapMonth;
		this.gregorianCalendar=MIN_LOCAL_DATE.plusDays(getOffSetDayOfMinLocalDate(this.year, this.month, this.day, this.isLeapMonth)-1);
	}
	
	/**
	 * 将实例转换为公历日期
	 * @return 实例对应的公历日期
	 */
	public LocalDate toLocalDate(){
		return this.gregorianCalendar;
	}
	
	public int getYear(){
		return this.year;
	}
	
	public int getMonthValue(){
		return this.month;
	}
	
	public int getDayOfMonth(){
		return this.day;
	}
	
	public boolean isLeapMonth(){
		return this.isLeapMonth;
	}
	
	/**
	 * 获取此实例年的生肖
	 * @return 生肖实例
	 */
	public Zodiac getZodiac(){
		return Zodiac.of(this.year);
	}
	
	/**
	 * 获取此实例年的实例天在此实例年内的第多少天,表示法是基于农历的
	 * @return 该实例表示的当年的天数
	 */
	public int getDayOfYear(){
		return getDayOfYear(this.year, this.month, this.day, this.isLeapMonth);
	}
	
	@Override
	public int compareTo(LunarCalendarLocalDate other) {
		return compareTo0(other);
	}

	int compareTo0(LunarCalendarLocalDate otherDate) {
        int cmp = (this.year - otherDate.year);
        if (cmp == 0) {
            cmp = (this.month - otherDate.month);
            if (cmp == 0) {
            	cmp=(this.isLeapMonth?1:0)-(otherDate.isLeapMonth?1:0);
            	if(cmp==0){
            		cmp = (this.day - otherDate.day);
            	}
            }
        }
        return cmp;
    }
	
	@Override
	public int hashCode() {
		int yearValue=this.year;
		int monthValue=this.month;
		int dayValue=this.day;
		return (yearValue&0xFFFFF800)^((yearValue<<11)+(monthValue<<6)+(dayValue)+(this.isLeapMonth?1:0));
	}

	@Override
	public boolean equals(Object obj) {
		if(this==obj){
			return true;
		}
		if(obj instanceof LunarCalendarLocalDate){
			return compareTo0((LunarCalendarLocalDate)obj)==0;
		}
		return false;
	}

	@Override
	public String toString() {
		int yearValue = this.year;
		int monthValue = this.month;
		int dayValue = this.day;
		int absYear = Math.abs(yearValue);
		StringBuilder buf = new StringBuilder(10);
		if (absYear < 1000) {
			if (yearValue < 0) {
				buf.append(yearValue - 10000).deleteCharAt(1);
			} else {
				buf.append(yearValue + 10000).deleteCharAt(0);
			}
		} else {
			if (yearValue > 9999) {
				buf.append('+');
			}
			buf.append(yearValue);
		}
		return buf.append(monthValue < 10 ? "-0" : "-")
				.append(monthValue)
				.append(dayValue < 10 ? "-0" : "-")
				.append(dayValue)
				.toString();
	}
	
	/**获取此实例年的最大天数*/
	public int getMaxDaysOfYear(){
		return getYearDays(this.year);
	}
	
	/** 写出实例以包装类代理的方式,用于序列化时被jvm调用 */
	private Object writeReplace(){
		return new Ser(Ser.LUNAR_CALENDAR_LOCAL_DATE, this);
	}
	
	/** 对非法序列化攻击提供防御 */
	private void readObject(ObjectInputStream s) throws InvalidObjectException{
		throw new InvalidObjectException("Deserialization via serialization delegate");
	}
	
	void writeExternal(DataOutput out) throws IOException{
		out.writeInt(this.year);
		out.writeByte(this.month);
		out.writeByte(this.day);
		out.writeBoolean(this.isLeapMonth);
	}
	
	static LunarCalendarLocalDate readExternal(DataInput in) throws IOException {
		int year=in.readInt();
		int month=in.readByte();
		int day=in.readByte();
		boolean isLeapMonth=in.readBoolean();
		return isLeapMonth?LunarCalendarLocalDate.ofLeapMonthFirst(year, month, day):LunarCalendarLocalDate.of(year, month, day);
	}
}
