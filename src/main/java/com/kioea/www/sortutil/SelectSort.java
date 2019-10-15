package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:26:58
 * @version:
 */
public class SelectSort extends SortStrategy {

	// 选择排序
	public int[] selectSort(int[] array) {
		int i = 0, minValue, tmp;
		int j = i + 1;
		for (; i < array.length - 1; i++) {
			minValue = array[i];
			for (j = i + 1; j < array.length; j++) {
				if (array[j] < minValue) {
					minValue = array[j];
					tmp = array[j];
					array[j] = array[i];
					array[i] = tmp;
				}
			}
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
		return selectSort(array);
	}
}
