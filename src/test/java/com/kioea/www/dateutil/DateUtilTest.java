package com.kioea.www.dateutil;

import static org.junit.Assert.fail;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-2 上午10:49:45
 * @version:
 */
public class DateUtilTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#addDate(java.lang.String, int, java.util.Date)}.
	 */
	@Test
	public void testAddDate() {
		Date date=new Date();
		System.out.println(date);
		Date date1=DateUtil.addDate("dd", 1, date);
		System.out.println(DateUtil.convertDateToStr(date1,DateUtil.DEFAULT_SHORT_DATE_FORMAT));
		date1=DateUtil.addDate("MM", 1, date);
		System.out.println(DateUtil.convertDateToStr(date1,DateUtil.DEFAULT_SHORT_DATE_FORMAT));
		date1=DateUtil.addDate("yy", 1, date);
		System.out.println(DateUtil.convertDateToStr(date1,DateUtil.DEFAULT_SHORT_DATE_FORMAT));
		
		 SimpleDateFormat df = new SimpleDateFormat(DateUtil.DEFAULT_SHORT_DATE_FORMAT);//定义格式，不显示毫秒
		 Timestamp now = new Timestamp(System.currentTimeMillis());//获取系统当前时间
		 String str = df.format(now);
		 System.out.println(str);
		 
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		Date dateA = new Date();
		try {
			dateA = ts;
			System.out.println(dateA);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#compareTime(java.lang.String)}.
	 */
	@Test
	public void testCompareTimeString() {
		System.out.println(DateUtil.compareTime("2020-05-21 23:59:59:00"));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#compareTime(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCompareTimeStringString() {
		System.out.println(DateUtil.compareTime("2015-02-28 11:12:50","2015-03-28 11:11:50"));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#compareTime(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testCompareTimeStringStringString() {
		System.out.println(DateUtil.compareTime("2015-02-28","2015-03-28",DateUtil.DEFAULT_SHORT_DATE_FORMAT));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertDateToStr(java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testConvertDateToStr() {
		Date date=new Date();
		System.out.println(DateUtil.convertDateToStr(date, DateUtil.DEFAULT_SHORT_DATE_FORMAT));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertStrToDate(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testConvertStrToDateStringString() {
		String date="2015-02-23";
		System.out.println(DateUtil.convertStrToDate(date, DateUtil.DEFAULT_SHORT_DATE_FORMAT));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertStrToDate(java.lang.String, java.lang.String, java.util.Locale)}.
	 */
	@Test
	public void testConvertStrToDateStringStringLocale() {
		String date="2015-03-23";
		System.out.println(DateUtil.convertStrToDate(date, DateUtil.DEFAULT_SHORT_DATE_FORMAT, Locale.CHINA));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertStrToTimestamp(java.lang.String)}.
	 */
	@Test
	public void testConvertStrToTimestamp() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertStrToTimestampZero(java.lang.String)}.
	 */
	@Test
	public void testConvertStrToTimestampZero() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#convertToPeriod(long)}.
	 */
	@Test
	public void testConvertToPeriod() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#dateDiff(java.lang.String, java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testDateDiff() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#getCurrDateStr()}.
	 */
	@Test
	public void testGetCurrDateStr() {
		System.out.println(DateUtil.getCurrDateStr());
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#getCurrDateStr(java.lang.String)}.
	 */
	@Test
	public void testGetCurrDateStrString() {
		System.out.println(DateUtil.getCurrDateStr("yyyy-MM-dd HH:mm:ss"));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#getCurrTimestamp()}.
	 */
	@Test
	public void testGetCurrTimestamp() {
		System.out.println(DateUtil.getCurrTimestamp());
	}
	
	@Test
	public void testStrToTimestamp() {
		System.out.println(DateUtil.convertStrToTimestamp("2020-05-21 23:59:59:00").getTime());
		Timestamp ts = new Timestamp(System.currentTimeMillis() - 3600*1000);//1554872797599l
		Date dateA = new Date();
		try {
			dateA = ts;
			System.out.println(dateA);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#toBeginDate(java.lang.String)}.
	 */
	@Test
	public void testToBeginDate() {
		System.out.println(DateUtil.toBeginDate("2015-02-02"));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#toEndDate(java.lang.String)}.
	 */
	@Test
	public void testToEndDate() {
		System.out.println(DateUtil.toEndDate("2015-02-02"));
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#getStandardDatetimeStr(java.lang.String)}.
	 */
	@Test
	public void testGetStandardDatetimeStr() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.dateutil.DateUtil#getTimeMillis(java.lang.String)}.
	 */
	@Test
	public void testGetTimeMillis() {
		Date date=new Date();
		System.out.println(date);
		date = DateUtil.addDate("dd", 7, date);
		System.out.println(date);
		String strDate=DateUtil.convertDateToStr(date,DateUtil.DEFAULT_SHORT_DATE_FORMAT);
		System.out.println(strDate);
		String strEndDate=DateUtil.toEndDate(strDate);
		System.out.println(strEndDate);
		System.out.println(DateUtil.getTimeMillis(strEndDate));
//		
//		System.out.println(DateUtil.convertStrToTimestamp("2016-05-13 23:59:59:50"));
	}

}
