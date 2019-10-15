package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2015-4-17 上午11:25:01
 * @version:
 */
public class QuickSort extends SortStrategy {

	// 快速排序
	public int[] quickSort(int[] array) {
		quick(array, 0, array.length - 1);
		return array;
	}

	// 获取中间数
	public int getMiddle(int[] list, int low, int high) {
		int tmp = list[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && list[high] >= tmp) {
				high--;
			}
			list[low] = list[high]; // 比中轴小的记录移到低端
			while (low < high && list[low] <= tmp) {
				low++;
			}
			list[high] = list[low]; // 比中轴大的记录移到高端
		}
		list[low] = tmp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	// 快速排序辅助方法
	public void quick(int[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high); // 将list数组进行一分为二
			quick(list, low, middle - 1); // 对低字表进行递归排序
			quick(list, middle + 1, high); // 对高字表进行递归排序
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
		return quickSort(array);
	}
}
