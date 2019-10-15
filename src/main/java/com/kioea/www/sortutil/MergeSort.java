package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:28:38
 * @version:
 */
public class MergeSort extends SortStrategy {

	// mergeSort
	public int[] mergeSort(int[] array) {
		int[] temp = new int[array.length];// 临时数组
		recMergeSort(array, temp, 0, array.length - 1);
		return temp;
	}

	// 递归分割数据到基本单位
	private void recMergeSort(int[] list, int[] temp, int low, int upper) {
		if (low == upper) {
			return;
		} else {
			int mid = (low + upper) / 2;
			recMergeSort(list, temp, low, mid);
			recMergeSort(list, temp, mid + 1, upper);
			merge(list, temp, low, mid + 1, upper);
		}
	}

	// 归并操作将基本单位归并成整个有序的数组
	private void merge(int[] list, int[] temp, int left, int right, int last) {
		int j = 0;
		int lowIndex = left;
		int mid = right - 1;
		int n = last - lowIndex + 1;
		while (left <= mid && right <= last) {
			if (list[left] < list[right]) {
				temp[j++] = list[left++];
			} else {
				temp[j++] = list[right++];
			}
		}
		while (left <= mid) {
			temp[j++] = list[left++];
		}
		while (right <= last) {
			temp[j++] = list[right++];
		}
		for (j = 0; j < n; j++) {
			list[lowIndex + j] = temp[j];
		}
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
		return mergeSort(array);
	}
}
