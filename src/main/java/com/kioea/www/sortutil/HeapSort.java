package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:27:38
 * @version:
 */
public class HeapSort extends SortStrategy {

	// 堆栈排序
	public int[] heapSort(int[] array) {
		int n = array.length;
		int temp = 0;
		for (int i = n / 2; i > 0; i--)
			Adjust(array, i - 1, n);
		for (int i = n - 2; i >= 0; i--) {
			temp = array[i + 1];
			array[i + 1] = array[0];
			array[0] = temp;
			Adjust(array, 0, i + 1);
		}
		return array;
	}

	//
	public static void Adjust(int[] a, int i, int n) {
		int j = 0;
		int temp = 0;
		temp = a[i];
		j = 2 * i + 1;
		while (j <= n - 1) {
			if (j < n - 1 && a[j] < a[j + 1])
				j++;
			if (temp >= a[j])
				break;
			a[(j - 1) / 2] = a[j];
			j = 2 * j + 1;
		}
		a[(j - 1) / 2] = temp;
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
		return heapSort(array);
	}
}
