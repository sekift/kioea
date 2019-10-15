package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:22:04
 * @version:
 */
public class ShellSort extends SortStrategy {

	// 希尔排序
	public int[] shellSort(int[] array) {
		double d1 = array.length;
		int temp = 0;
		while (true) {
			d1 = Math.ceil(d1 / 2);
			int d = (int) d1;
			for (int x = 0; x < d; x++) {
				for (int i = x + d; i < array.length; i += d) {
					int j = i - d;
					temp = array[i];
					for (; j >= 0 && temp < array[j]; j -= d) {
						array[j + d] = array[j];
					}
					array[j + d] = temp;
				}
			}
			if (d == 1)
				break;
		}
		return array;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sekift.sortutil.SortStrategy#sort()
	 */
	@Override
	public void sort() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sekift.sortutil.SortStrategy#sort(int[])
	 */
	@Override
	public int[] sort(int[] array) {
		// TODO Auto-generated method stub
		return shellSort(array);
	}

}
