package com.kioea.www.encryptutil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author:sekift
 * @time:2015-3-24 上午11:37:14
 * @version:
 */
public class CRC32CipherTest {

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
	 * Test method for {@link com.sekift.encryptutil.CRC32Cipher#encode(byte[])}.
	 */
	@Test
	public void testEncode() {
		byte[] data={0,1,2,100};
		try {
			System.out.println(CRC32Cipher.encode(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link com.sekift.encryptutil.CRC32Cipher#crc32Hex(byte[])}.
	 */
	@Test
	public void testCrc32Hex() {
		byte[] data={0,1,2,100};
		try {
			System.out.println(CRC32Cipher.crc32Hex(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
