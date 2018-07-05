package com.xuan.xutils.utils;

import com.xuan.xutils.time.TimeUtils;
import org.junit.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class TimeUtilsTest{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testYearMonthTextFormat() {
		Assert.assertEquals(TimeUtils.yearMonthTextFormat(LocalDate.of(2017, 11, 5)), "1711");
		Assert.assertEquals(TimeUtils.yearMonthTextFormat(LocalDateTime.of(2017, 11, 5, 8, 8, 8)), "1711");
		Assert.assertEquals(TimeUtils.yearMonthTextFormat(LocalDate.of(2017, 9, 5)), "1709");
		Assert.assertEquals(TimeUtils.yearMonthTextFormat(LocalDateTime.of(2017, 9, 5, 8, 8, 8)), "1709");
	}
	
	@Test
	public void testHourFormatLocalTime() {
		Assert.assertEquals(TimeUtils.hourFormat(LocalTime.of(0,0)), "00");
		Assert.assertEquals(TimeUtils.hourFormat(LocalTime.of(8,0)), "08");
		Assert.assertEquals(TimeUtils.hourFormat(LocalTime.of(12,0)), "12");
		Assert.assertEquals(TimeUtils.hourFormat(LocalTime.of(23,59)), "23");
		
		Assert.assertNotEquals(TimeUtils.hourFormat(LocalTime.of(1,0)), "00");
		Assert.assertNotEquals(TimeUtils.hourFormat(LocalTime.of(9,0)), "08");
		Assert.assertNotEquals(TimeUtils.hourFormat(LocalTime.of(13,0)), "12");
		Assert.assertNotEquals(TimeUtils.hourFormat(LocalTime.of(22,59)), "23");
	}

	@Test
	public void testHourFormatInt() {
		Assert.assertEquals(TimeUtils.hourFormat(0), "00");
		Assert.assertEquals(TimeUtils.hourFormat(8), "08");
		Assert.assertEquals(TimeUtils.hourFormat(12), "12");
		Assert.assertEquals(TimeUtils.hourFormat(23), "23");
		
		Assert.assertNotEquals(TimeUtils.hourFormat(1), "00");
		Assert.assertNotEquals(TimeUtils.hourFormat(2), "08");
		Assert.assertNotEquals(TimeUtils.hourFormat(22), "12");
		Assert.assertNotEquals(TimeUtils.hourFormat(10), "23");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHourFormatIntExceptionNegate() {
		TimeUtils.hourFormat(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testHourFormatIntException() {
		TimeUtils.hourFormat(24);
	}
	
	@Test
	public void testDateTextFormatLocalDateTime() {
		LocalDateTime localDateTime=LocalDateTime.of(2017, 01, 01, 01, 01);
		Assert.assertEquals(TimeUtils.dateTextFormat(localDateTime), "170101");
		Assert.assertNotEquals(TimeUtils.dateTextFormat(localDateTime), "171111");
		
		localDateTime=LocalDateTime.of(2017, 11, 11, 11, 11);
		Assert.assertEquals(TimeUtils.dateTextFormat(localDateTime), "171111");
		Assert.assertNotEquals(TimeUtils.dateTextFormat(localDateTime), "170101");
	}
	
	@Test
	public void testDateTextFormatLocalDate() {
		LocalDate localDateTime=LocalDate.of(2017, 01, 01);
		Assert.assertEquals(TimeUtils.dateTextFormat(localDateTime), "170101");
		Assert.assertNotEquals(TimeUtils.dateTextFormat(localDateTime), "171111");
		
		localDateTime=LocalDate.of(2017, 11, 11);
		Assert.assertEquals(TimeUtils.dateTextFormat(localDateTime), "171111");
		Assert.assertNotEquals(TimeUtils.dateTextFormat(localDateTime), "170101");
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testDateFormatDate() {
		Assert.assertEquals(TimeUtils.dateFormat(Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-19");
		
		Assert.assertEquals(TimeUtils.dateFormat(Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(Date.from(LocalDateTime.of(1999, 10, 19, 19, 19,19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-19");
		
		Assert.assertEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-19");
		
		Assert.assertEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 19, 19,19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-19");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-19");
		Assert.assertNotEquals(TimeUtils.dateFormat(Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-09");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-19");
		Assert.assertNotEquals(TimeUtils.dateFormat(Date.from(LocalDateTime.of(1999, 10, 19, 19, 19,19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-09");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-19");
		Assert.assertNotEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-09");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-19");
		Assert.assertNotEquals(TimeUtils.dateFormat(java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 19, 19,19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-09");
	}

	@Test
	public void testDateFormatLocalDate() {
		Assert.assertEquals(TimeUtils.dateFormat(LocalDate.of(1999, 9, 9)), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(LocalDate.of(1999, 10, 19)), "1999-10-19");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(LocalDate.of(1999, 9, 9)), "1999-09-19");
		Assert.assertNotEquals(TimeUtils.dateFormat(LocalDate.of(1999, 10, 19)), "1999-10-09");
	}

	@Test
	public void testDateFormatLocalDateTime() {
		Assert.assertEquals(TimeUtils.dateFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999-09-09");
		Assert.assertEquals(TimeUtils.dateFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19)), "1999-10-19");
		
		Assert.assertNotEquals(TimeUtils.dateFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999-09-09 09:09:09");
		Assert.assertNotEquals(TimeUtils.dateFormat(LocalDateTime.of(1999, 10, 19, 19, 19, 19)), "1999-10-19 19:19:19");
	}
	
	@Test
	public void testChineseDateFormatLocalDate() {
		Assert.assertEquals(TimeUtils.chineseDateFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9).toLocalDate()), "1999年09月09日");
		Assert.assertEquals(TimeUtils.chineseDateFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19).toLocalDate()), "1999年10月19日");
	}
	
	@Test
	public void testChineseDateWeekFormatLocalDate() {
		Assert.assertEquals(TimeUtils.chineseDateWeekFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9).toLocalDate()), "1999年09月09日 星期四");
		Assert.assertEquals(TimeUtils.chineseDateWeekFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19).toLocalDate()), "1999年10月19日 星期二");
	}
	
	@Test
	public void testChineseDateTimeFormatLocalDateTime() {
		Assert.assertEquals(TimeUtils.chineseDateTimeFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999年09月09日 09时09分09秒");
		Assert.assertEquals(TimeUtils.chineseDateTimeFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19)), "1999年10月19日 19时19分19秒");
	}
	
	@Test
	public void testChineseDateTimeWeekFormatLocalDateTime() {
		Assert.assertEquals(TimeUtils.chineseDateTimeWeekFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999年09月09日 09时09分09秒 星期四");
		Assert.assertEquals(TimeUtils.chineseDateTimeWeekFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19)), "1999年10月19日 19时19分19秒 星期二");
	}

	@Test
	public void testDateTimeFormatLocalDateTime() {
		Assert.assertEquals(TimeUtils.dateTimeFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999-09-09 09:09:09");
		Assert.assertEquals(TimeUtils.dateTimeFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19)), "1999-10-19 19:19:19");
		
		Assert.assertNotEquals(TimeUtils.dateTimeFormat(LocalDateTime.of(1999, 9, 9, 9, 9, 9)), "1999-09-09");
		Assert.assertNotEquals(TimeUtils.dateTimeFormat(LocalDateTime.of(1999, 10, 19, 19, 19,19)), "1999-10-19");
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testDateTimeFormatDate() {
		Assert.assertEquals(TimeUtils.dateTimeFormat(Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-09 00:00:00");
		Assert.assertEquals(TimeUtils.dateTimeFormat(Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-19 00:00:00");
		
		Assert.assertEquals(TimeUtils.dateTimeFormat(Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-09 09:09:09");
		Assert.assertEquals(TimeUtils.dateTimeFormat(Date.from(LocalDateTime.of(1999, 10, 19, 19, 19, 19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-19 19:19:19");
		
		Assert.assertEquals(TimeUtils.dateTimeFormat(java.sql.Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-09-09 00:00:00");
		Assert.assertEquals(TimeUtils.dateTimeFormat(java.sql.Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant())), "1999-10-19 00:00:00");
		
		Assert.assertEquals(TimeUtils.dateTimeFormat(java.sql.Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())), "1999-09-09 09:09:09");
		Assert.assertEquals(TimeUtils.dateTimeFormat(java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 19, 19, 19).atZone(ZoneId.systemDefault()).toInstant())), "1999-10-19 19:19:19");
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testDateToDate() {
		Assert.assertEquals(TimeUtils.dateToDate("1999-09-09"),Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateToDate("1999-10-19"),Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateToDate("1999-09-19"),Date.from(LocalDateTime.of(1999, 9, 19, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateToDate("1999-10-19"),Date.from(LocalDateTime.of(1999, 10, 19, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateToDate("1999-09-19"),java.sql.Date.from(LocalDate.of(1999, 9, 19).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateToDate("1999-10-19"),java.sql.Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateToDate("1999-09-19"),java.sql.Date.from(LocalDateTime.of(1999, 9, 19, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateToDate("1999-10-19"),java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-09-09"),Date.from(LocalDate.of(1999, 9, 10).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-10-19"),Date.from(LocalDate.of(1999, 10, 20).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-09-19"),Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-10-19"),Date.from(LocalDateTime.of(1999, 10, 19, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-09-19"),java.sql.Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-10-19"),java.sql.Date.from(LocalDate.of(1999, 10, 29).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-09-19"),java.sql.Date.from(LocalDateTime.of(1999, 9, 19, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertNotEquals(TimeUtils.dateToDate("1999-10-19"),java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testDateTimeToDate() {
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-09-09 00:00:00"),Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-10-19 00:00:00"),Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-09-09 09:09:09"),Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-10-19 19:19:19"),Date.from(LocalDateTime.of(1999, 10, 19, 19, 19, 19).atZone(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-09-09 00:00:00"),java.sql.Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-10-19 00:00:00"),java.sql.Date.from(LocalDate.of(1999, 10, 19).atStartOfDay(ZoneId.systemDefault()).toInstant()));
		
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-09-09 09:09:09"),java.sql.Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant()));
		Assert.assertEquals(TimeUtils.dateTimeToDate("1999-10-19 19:19:19"),java.sql.Date.from(LocalDateTime.of(1999, 10, 19, 19, 19, 19).atZone(ZoneId.systemDefault()).toInstant()));
	}
	
	@Test
	public void testDateToLocalDateTime() {
		Assert.assertEquals(TimeUtils.dateToLocalDateTime(Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())),LocalDateTime.of(1999, 9, 9, 0, 0, 0));
		Assert.assertEquals(TimeUtils.dateToLocalDateTime(Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())),LocalDateTime.of(1999, 9, 9, 9, 9, 9));
	}
	
	@Test
	public void testDateToLocalDate() {
		Assert.assertEquals(TimeUtils.dateToLocalDate(Date.from(LocalDate.of(1999, 9, 9).atStartOfDay(ZoneId.systemDefault()).toInstant())),LocalDate.of(1999, 9, 9));
		Assert.assertEquals(TimeUtils.dateToLocalDate(Date.from(LocalDateTime.of(1999, 9, 9, 9, 9, 9).atZone(ZoneId.systemDefault()).toInstant())),LocalDate.of(1999, 9, 9));
	}

	@Test
	public void testDateParse() {
		Assert.assertEquals(TimeUtils.dateParse("1999-09-09"), LocalDate.of(1999, 9, 9));
		Assert.assertEquals(TimeUtils.dateParse("1999-10-19"), LocalDate.of(1999, 10, 19));
		
		Assert.assertNotEquals(TimeUtils.dateParse("1999-09-19"), LocalDate.of(1999, 9, 9));
		Assert.assertNotEquals(TimeUtils.dateParse("1999-10-19"), LocalDate.of(1999, 10, 10));
	}

	@Test
	public void testDateTimeParse() {
		Assert.assertEquals(TimeUtils.dateTimeParse("1999-09-09 09:09:09"), LocalDateTime.of(1999, 9, 9, 9, 9, 9));
		Assert.assertEquals(TimeUtils.dateTimeParse("1999-10-19 19:19:19"), LocalDateTime.of(1999, 10, 19, 19, 19, 19));
	}

}
