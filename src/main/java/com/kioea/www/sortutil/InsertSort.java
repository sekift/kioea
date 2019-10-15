package com.kioea.www.sortutil;

/**
 * 插入排序
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:16:13
 * @version:
 */
public class InsertSort extends SortStrategy {

	@Override
	public void sort() {

	}

	@Override
	public int[] sort(int[] array) {
		return insertSort(array);
	}

	public static int[] insertSort(int[] array) {
		int j, tmp;
		for (int i = 1; i < array.length; i++) {
			tmp = array[i];
			j = i;
			while (j > 0 && array[j - 1] > tmp) {
				array[j] = array[j - 1];
				j--;
			}
			array[j] = tmp;
		}
		return array;
	}

}
