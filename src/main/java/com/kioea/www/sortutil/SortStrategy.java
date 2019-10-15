package com.kioea.www.sortutil;

/**
 * 抽象策略角色
 * 
 * @author:sekift
 * @time:2014-7-30 下午04:30:34
 * @version:
 */
abstract public class SortStrategy {

	public abstract void sort();

	public abstract int[] sort(int[] array);

}
