package com.kioea.www.constructeutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

/**
 * 对Map<String,Integer>的排序操作
 * 
 * @author:sekift
 * @time:2014-8-11 下午04:31:21
 * @version:
 */
public class MapSortUtil {

	/**
	 * 对map的key进行排序
	 * 
	 * @param map
	 * @return map
	 * @throws Exception
	 */
	public static Map<String, Integer> sortByKey(Map<String, Integer> map) throws Exception {
		Map<String, Integer> treeMap = new TreeMap<String, Integer>(map);
		return treeMap;
	}

	/**
	 * 对map的value降序排列
	 * 
	 * @param map
	 * @return arraylist
	 * @throws Exception
	 */
	public static ArrayList<Map.Entry<String, Integer>> sortByValueDesc(Map<String, Integer> map) throws Exception {
		return sortByValue(map, true);
	}

	/**
	 * 对map的value升序排列
	 * 
	 * @param map
	 * @return arraylist
	 * @throws Exception
	 */
	public static ArrayList<Map.Entry<String, Integer>> sortByValueAsc(Map<String, Integer> map) throws Exception {
		return sortByValue(map, false);
	}

	/**
	 * 对map的value进行排序
	 * 
	 * @param map
	 * @return arraylist
	 * @throws Exception
	 */
	private static ArrayList<Map.Entry<String, Integer>> sortByValue(Map<String, Integer> map, final boolean flag)
			throws Exception {
		List<Map.Entry<String, Integer>> infoIds = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
		Collections.sort(infoIds, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				if (flag) {
					return (o2.getValue() - o1.getValue());
				}
				return (o1.getValue() - o2.getValue());
			}
		});
		return (ArrayList<Entry<String, Integer>>) infoIds;
	}

}
