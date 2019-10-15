package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2014-7-30 下午04:35:18
 * @version:
 */
public class BubbleSort extends SortStrategy {

	/*
	 * 策略方法
	 */
	public void sort() {

	}

	/*
	 * 策略方法
	 */
	public int[] sort(int[] array) {
		int i = 0;
		int j, tmp;
		for (; i < array.length; i++) {
			for (j = i + 1; j < array.length; j++) {
				if (array[j] < array[i]) {
					tmp = array[j];
					array[j] = array[i];
					array[i] = tmp;
				}
			}
		}
		return array;
	}

	public double[] sort(double[] array) {
		return null;
	}
}
