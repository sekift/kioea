package com.kioea.www.randomutil;

/**
 * 
 * @author:luyz
 * @time:2016-9-28 下午03:58:22
 * @version:
 */
public class RandomForRange {

	public static void rangeForRank(int total) {
		double[] list = {0.6, 0.32, 0.07, 0.01};//只需调整/增加这个list即可
		double[] doubleList = new double[list.length + 1];
		doubleList[0] = 0.0;
		for (int i = 0; i < list.length; i++) {
			if (i < 1) {
				doubleList[i + 1] = total * list[i];
			} else {
				doubleList[i + 1] = doubleList[i] + total * list[i];
			}
		}

		for (int i = 1; i <= total; i++) {
			for (int j = 1; j <= doubleList.length; j++) {
				if (i > doubleList[j - 1] && i <= doubleList[j]) {
					System.out.println("i=" + i + ":j=" + j);
				}
			}
		}
	}

	public static void main(String args[]) {
		int total = 100;
		rangeForRank(total);
	}

}
