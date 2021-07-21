package com.kioea.www.sortutil;

/**
 * 
 * @author:sekift
 * @time:2014-7-30 下午05:07:57
 * @version:
 */
public class Test {
	public static void main(String[] args) {
		int[] array = { 1, 3, 4, 6, 7, 8, 9, 43, 4, 56, 4, 6, 7, 78, 23 };
		BubbleSort bs = new BubbleSort();// 直接创建
		for (int i : bs.sort(array)) {
			System.out.print(i + " ");
		}
		System.out.println();
		// 使用策略模式
		Sorter sorter = new Sorter();
		sorter.setSortStrategy(new BubbleSort());// 这里放置不同的策略方法
		for (int i : sorter.sort(array)) {
			System.out.print(i + " ");
		}
		System.out.println();
		// 使用策略模式
		sorter.setSortStrategy(new InsertSort());// 这里放置不同的策略方法
		for (int i : sorter.sort(array)) {
			System.out.print(i + " ");
		}
	}
}
