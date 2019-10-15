package com.kioea.www.serializableutil;

import java.io.Serializable;

public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6686356275244711111L;
	private String name;
	private int age;

	public Person() {

	}

	public Person(String str, int n) {
		name = str;
		age = n;
	}

	String getName() {
		return name;
	}

	int getAge() {
		return age;
	}

}
