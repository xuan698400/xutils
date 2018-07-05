package com.xuan.xutils.time;

import com.xuan.xutils.time.LunarCalendarLocalDate.Zodiac;
import org.junit.*;

import java.time.LocalDate;

public class LunarCalendarLocalDateTest {

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
	public void testHashCode() {
	}

	@Test
	public void testNow() {
	}

	@Test
	public void testNowZoneId() {
	}

	@Test
	public void testNowClock() {
	}

	@Test
	public void testOfIntIntInt() {
		Assert.assertEquals(LunarCalendarLocalDate.of(2016, 1, 1).toLocalDate(), LocalDate.of(2016, 2, 8));
		Assert.assertEquals(LunarCalendarLocalDate.of(2017, 1, 1).toLocalDate(), LocalDate.of(2017, 1, 28));
		Assert.assertEquals(LunarCalendarLocalDate.of(2018, 1, 1).toLocalDate(), LocalDate.of(2018, 2, 16));
		Assert.assertEquals(LunarCalendarLocalDate.of(2019, 1, 1).toLocalDate(), LocalDate.of(2019, 2, 5));
		Assert.assertEquals(LunarCalendarLocalDate.of(2020, 1, 1).toLocalDate(), LocalDate.of(2020, 1, 25));
		Assert.assertEquals(LunarCalendarLocalDate.of(2021, 1, 1).toLocalDate(), LocalDate.of(2021, 2, 12));
		Assert.assertEquals(LunarCalendarLocalDate.of(2022, 1, 1).toLocalDate(), LocalDate.of(2022, 2, 1));
	}

	@Test
	public void testOfLeapMonthFirst() {
		
	}

	@Test
	public void testOfIntInt() {
		Assert.assertEquals(LunarCalendarLocalDate.of(2016, 1).toLocalDate(), LocalDate.of(2016, 2, 8));
		Assert.assertEquals(LunarCalendarLocalDate.of(2017, 1).toLocalDate(), LocalDate.of(2017, 1, 28));
		Assert.assertEquals(LunarCalendarLocalDate.of(2018, 1).toLocalDate(), LocalDate.of(2018, 2, 16));
		Assert.assertEquals(LunarCalendarLocalDate.of(2019, 1).toLocalDate(), LocalDate.of(2019, 2, 5));
		Assert.assertEquals(LunarCalendarLocalDate.of(2020, 1).toLocalDate(), LocalDate.of(2020, 1, 25));
		Assert.assertEquals(LunarCalendarLocalDate.of(2021, 1).toLocalDate(), LocalDate.of(2021, 2, 12));
		Assert.assertEquals(LunarCalendarLocalDate.of(2022, 1).toLocalDate(), LocalDate.of(2022, 2, 1));
	}

	@Test
	public void testOfEpochDay() {
		long epochDay=LocalDate.now().toEpochDay();
		Assert.assertEquals(LunarCalendarLocalDate.ofEpochDay(epochDay).toLocalDate(), LocalDate.now());
	}

	@Test
	public void testOfLocalDate() {
		Assert.assertEquals(LunarCalendarLocalDate.of(LocalDate.now()).toLocalDate(), LocalDate.now());
	}

	@Test
	public void testFrom() {
	}

	@Test
	public void testToLocalDate() {
		Assert.assertEquals(LunarCalendarLocalDate.now().toLocalDate(), LocalDate.now());
	}

	@Test
	public void testGetYear() {
	}

	@Test
	public void testGetMonthValue() {
	}

	@Test
	public void testGetDayOfMonth() {
	}

	@Test
	public void testGetZodiac() {
		Assert.assertSame(LunarCalendarLocalDate.of(2018, 1, 1).getZodiac(), Zodiac.DOG);
		Assert.assertSame(LunarCalendarLocalDate.of(1900, 1, 1).getZodiac(), Zodiac.MOUSE);
	}
	
	@Test
	public void testGetDayOfYear() {
	}

	@Test
	public void testCompareTo() {
	}

	@Test
	public void testCompareTo0() {
	}

	@Test
	public void testEqualsObject() {
		
	}

	@Test
	public void testToString() {
		
	}

	@Test
	public void testWriteExternal() {
	}

	@Test
	public void testReadExternal() {
	}

}
