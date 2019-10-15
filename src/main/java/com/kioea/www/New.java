package com.kioea.www;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 方便创建集合泛型
 * @author:sekift
 * @time:2015-4-14 下午03:37:13
 * @version:
 */
public class New {

	public static <K,V> Map<K,V> map(){
		return new HashMap<K,V>();
	}
	
	public static <T> List<T> list(){
		return new LinkedList<T>();
	}
	
	public static <T> Set<T> set(){
		return new HashSet<T>();
	}
	
	public static <T> Queue<T> queue(){
		return new LinkedList<T>();
	}
}
