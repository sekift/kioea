package com.kioea.www.encryptutil;

import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-10 下午05:25:19
 * @version:
 */
public class SeqUUIDUtilTest {

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
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID(long)}.
	 */
	@Test
	public void testToSequenceUUIDLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID(long, long)}.
	 */
	@Test
	public void testToSequenceUUIDLongLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID(long, long, long)}.
	 */
	@Test
	public void testToSequenceUUIDLongLongLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID(char, long)}.
	 */
	@Test
	public void testToSequenceUUIDCharLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID(char, long, long, long)}.
	 */
	@Test
	public void testToSequenceUUIDCharLongLongLong() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#toSequenceUUID()}.
	 */
	@Test
	public void testToSequenceUUID() {
		System.out.println(SeqUUIDUtil.toSequenceUUID());
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#extractTimestamp(java.lang.String)}.
	 */
	@Test
	public void testExtractTimestamp() {
		String str=SeqUUIDUtil.toSequenceUUID();
		long lon=SeqUUIDUtil.extractTimestamp(str);
		System.out.println(lon);
		System.out.println(new Date(lon));
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#extractMostSignificantBits(java.lang.String)}.
	 */
	@Test
	public void testExtractMostSignificantBits() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.SeqUUIDUtil#extractLeastSignificantBits(java.lang.String)}.
	 */
	@Test
	public void testExtractLeastSignificantBits() {
		fail("Not yet implemented");
	}

}
