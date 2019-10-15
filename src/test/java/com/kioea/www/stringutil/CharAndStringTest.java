package com.kioea.www.stringutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2014-7-31 上午11:35:40
 * @version:
 */
public class CharAndStringTest {
	char[] ch = { 'c', 'd', 'd', 's', 'a', '人' };
	String st = "aefwefwege中文";
	String[] str = { "a", "c", "c", "e", "b", "人" };

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
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#chararray2string(char[])}.
	 */
	@Test
	public void testChararray2string() {
		System.out.println(StringCharge.chararray2string(ch));
	}

	/**
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#string2chararray(java.lang.String)}
	 * .
	 */
	@Test
	public void testString2chararray() {
		System.out.println(StringCharge.string2chararray(st));
	}

	/**
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#stringarray2string(java.lang.String[])}
	 * .
	 */
	@Test
	public void testStringarray2string() {
		System.out.println(StringCharge.stringarray2string(str));
	}

	/**
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#string2stringarray(java.lang.String)}
	 * .
	 */
	@Test
	public void testString2stringarray() {
		String[] str = StringCharge.string2stringarray(st);
		for (String i : str) {
			System.out.print(i);
		}
		System.out.println();
	}

	/**
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#chararray2stringarray(char[])}.
	 */
	@Test
	public void testChararray2stringarray() {
		String[] str = StringCharge.chararray2stringarray(ch);
		for (String i : str) {
			System.out.print(i);
		}
		System.out.println();
	}

	/**
	 * Test method for
	 * {@link com.sekift.stringutil.StringCharge#stringarray2chararray(java.lang.String[])}
	 * .
	 */
	@Test
	public void testStringarray2chararray() {
		System.out.println(StringCharge.stringarray2chararray(str));
	}
	
	@Test
	public void string2char(){
		String str="a";
		System.out.println(StringCharge.string2char(str));
	}
	
	@Test
	public void char2string(){
		char ch='b';
		System.out.println(StringCharge.char2string(ch));
		
	}

}
