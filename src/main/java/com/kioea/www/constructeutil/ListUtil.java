package com.kioea.www.constructeutil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author:sekift
 * @time:2014-8-12 下午04:55:51
 * @version:
 */
public class ListUtil {

	/**
	 * list2set
	 * 
	 * @param list
	 * @return set
	 */
	public static Set<?> list2set(List<?> list) {
		Set<Object> set = new HashSet<Object>(); 
		for (Object li : list) { 
			set.add(li);
		}
		return set;
	}
}
