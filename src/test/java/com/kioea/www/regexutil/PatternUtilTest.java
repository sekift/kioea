package com.kioea.www.regexutil;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.Constant;

/**
 * 
 * @author:sekift
 * @time:2014-8-19 上午10:24:26
 * @version:
 */
public class PatternUtilTest {

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
	 * Test method for {@link com.sekift.regexutil.PatternUtil#patternAll(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public void testPatternAll() {
		System.out.println(PatternUtil.patternAll(1223324, "^1(2+3+|3+2+)(2|3)*4$"));
		System.out.println(PatternUtil.patternAll("中文2343", Constant.regex_ID.CHINESE));
	}

	/**
	 * Test method for {@link com.sekift.regexutil.PatternUtil#patternAllByList(java.lang.Object, java.lang.String)}.
	 */
	@Test
	public void testPatternAllByList() {
		fail("Not yet implemented");
	}

}
