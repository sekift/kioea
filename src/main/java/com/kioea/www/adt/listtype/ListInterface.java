package com.kioea.www.adt.listtype;

/**
 * ADT 线性表接口 位置从1开始
 * 
 * @author luyz
 *
 */
public interface ListInterface<T> {

	public boolean add(T newEntry);

	public boolean add(int newPosition, T newEntry);

	public T remove(int givenPosition);

	public void clear();

	public boolean replace(T oldEntry, T newEntry);

	public T getEntry(int givenPostion);

	public boolean contains(T anEntry);

	public boolean isEmpty();

	public boolean isFull();

	public int getLength();

	public void display();

}
