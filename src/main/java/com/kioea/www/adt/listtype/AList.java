package com.kioea.www.adt.listtype;

public class AList<T> implements ListInterface<T> {
	private T[] entry;
	private int length;
	private static final int MAX_SIZE = 50;

	public AList() {
		this(MAX_SIZE);
	}

	@SuppressWarnings("unchecked")
	public AList(int maxSize) {
		length = 0;
		entry = (T[]) new Object[maxSize];
	}

	@Override
	public boolean add(T newEntry) {
		boolean result = false;
		if (!isFull()) {
			entry[length] = newEntry;
			length++;
			result = true;
		}
		return result;
	}

	@Override
	public boolean add(int newPosition, T newEntry) {
		return false;
	}

	@Override
	public T remove(int givenPosition) {
		return null;
	}

	@Override
	public void clear() {
		length = 0;
	}

	@Override
	public boolean replace(T oldEntry, T newEntry) {
		return false;
	}

	@Override
	public T getEntry(int givenPostion) {
		return null;
	}

	@Override
	public boolean contains(T anEntry) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean isFull() {
		return this.length == entry.length;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public void display() {
		for (int i = 0; i < length; i++) {
			System.out.println(entry[i]);
		}
	}

}
