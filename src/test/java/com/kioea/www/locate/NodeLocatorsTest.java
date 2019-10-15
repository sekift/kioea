package com.kioea.www.locate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.sqlutil.locate.HashAlgorithms;
import com.kioea.www.sqlutil.locate.NodeLocator;
import com.kioea.www.sqlutil.locate.NodeLocators;

/**
 * 
 * @author:sekift
 * @time:2015-4-21 上午11:09:28
 * @version:
 */
public class NodeLocatorsTest {

	private NodeLocator<String> locator;

	@Before
	public void setUp() throws Exception {
		String maps = "fw_demo_m0,fw_demo_m1,fw_demo_m2,fw_demo_m3,demo_m4";
		String[] items = maps.trim().split(",");
		locator = NodeLocators.newModLocator(HashAlgorithms.DJB_HASH, Arrays.asList(items));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetNodes() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNodes() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetHashAlgorithm() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAlgorithm() {
		fail("Not yet implemented");
	}

	@Test
	public void testLocateStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testLocateLongInt() {
		String node = "";

		node = locator.locate(0L, NodeLocator.NULL_STRATEGY);
		System.out.println(node);
		assertEquals("fw_demo_m0", node);

//		node = locator.locate(1L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//		assertEquals("fw_demo_m1", node);
//
//		node = locator.locate(2L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//		assertEquals("fw_demo_m2", node);
//
//		node = locator.locate(3L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//		assertEquals("fw_demo_m3", node);
//		
//		node = locator.locate(4L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//		
//		node = locator.locate(5L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//
//		node = locator.locate(54439635435432L, NodeLocator.NULL_STRATEGY);
//		System.out.println(node);
//		node = locator.locate(54439635435437L, 0);
//		System.out.println(node);
	}

}
