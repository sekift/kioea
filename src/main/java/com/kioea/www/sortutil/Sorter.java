package com.kioea.www.sortutil;

/**
 * 策略模式
 * 
 * @author:sekift
 * @time:2014-7-30 下午04:28:13
 * @version:
 */
public class Sorter {

	private SortStrategy sortStrategy;

	// 策略方法
	public void sort() {
		sortStrategy.sort();
	}

	public int[] sort(int[] array) {
		return sortStrategy.sort(array);
	}

	public void setSortStrategy(SortStrategy sort) {
		this.sortStrategy = sort;
	}

}
