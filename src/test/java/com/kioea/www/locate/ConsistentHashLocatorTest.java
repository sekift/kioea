package com.kioea.www.locate;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.kioea.www.sqlutil.locate.NodeLocator;
import com.kioea.www.sqlutil.locate.NodeLocators;
import com.kioea.www.sqlutil.locate.NodeLocators.ConsistentHashLocator;

/**
 * 
 * @author:sekift
 * @time:2015-4-21 下午05:22:22
 * @version:
 */
public class ConsistentHashLocatorTest {

	private ConsistentHashLocator<String> locator;

	@Before
	public void setUp() throws Exception {
		String mapping = "demo0,demo1,demo2,demo3";
		String[] items = mapping.trim().split(",");
		locator = new NodeLocators.ConsistentHashLocator<String>();
		Map<Long, String> nodes = new HashMap<Long, String>();
		long[] candidates = locator.nextCandidates(items.length);
		for (int i = 0; i < items.length; i++) {
			Long groupNo = Long.valueOf(candidates[i]);
			nodes.put(groupNo, items[i]);
		}
		locator.setNodes(nodes);
	}

	@Test
	public void testLocateLong() {
		String node = "";

		node = locator.locate(1L, NodeLocator.NULL_STRATEGY);
		assertEquals("demo2", node);

		node = locator.locate(1073741825L, NodeLocator.NULL_STRATEGY);
		assertEquals("demo1", node);

		node = locator.locate(2147483649L, NodeLocator.NULL_STRATEGY);
		assertEquals("demo3", node);

		node = locator.locate(3221225473L, NodeLocator.NULL_STRATEGY);
		assertEquals("demo0", node);

		node = locator.locate(3221225472L, NodeLocator.NULL_STRATEGY);
		assertEquals("demo3", node);

	}

}
