package com.kioea.www.textutil;

import java.util.ArrayList;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.kioea.www.constructeutil.MapSortUtil;
import com.kioea.www.fileutil.ReadFromFile;

/**
 * 
 * @author:sekift
 * @time:2014-8-11 下午02:43:53
 * @version:
 */
public class WordCountTest {

	String str = ReadFromFile.readFileByLines("D:/t_info.txt");

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	Map<String, Integer> mapResult = WordCount.countWordsMap(str);

	@Test
	public void testCountWordsMap() {
		System.out.println(WordCount.countWordsMap(str));

	}

	String result = WordCount.countWordsJson(str);

	@Test
	public void testCountWordsJson() {
		System.out.println(result);
	}

	@Test
	public void testGetDataMap() {
		System.out.println(WordCount.getDataMap(result));
	}

	@Test
	public void testGetCountKey() {
		System.out.println(WordCount.getCountKey(result));
	}

	@Test
	public void testGetCountValue() {
		System.out.println(WordCount.getCountValue(result));
	}

	@Test
	public void testSortByKey() {
		try {
			System.out.println(MapSortUtil.sortByKey(mapResult));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSortByValue() {
		ArrayList<Map.Entry<String, Integer>> entries;
		try {
			entries = MapSortUtil.sortByValueAsc(mapResult);
			System.out.println(entries);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
