package com.kioea.www.serializableutil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.kioea.www.CloseUtil;

public class SerilaizeObjectToFile {

	/**
	 *
	 * @param object
	 * @param fileName
	 * @return
	 */
	public static String saveObject(Object object, String fileName) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(object);
			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();
			return "序列化出错";
		} finally {
			CloseUtil.closeSilently(oos);
			CloseUtil.closeSilently(fos);
		}
		return "序列化成功";
	}

	public static Object restoreObject(String fileName) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(fileName);
			ois = new ObjectInputStream(fis);

			return ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return "序列化读取出错";
		} finally {
			CloseUtil.closeSilently(ois);
			CloseUtil.closeSilently(fis);
		}
	}

	public static void main(String[] args) {
		// ser.savePerson();
		// ser.restorePerson();
//		Person person = new Person("John", 25);
//		SerilaizeObjectToFile.saveObject(person, "D:/ask/serializable.txt");
		Person myPerson=(Person)SerilaizeObjectToFile.restoreObject("D:/ask/serializable.txt");
		System.out.println("Name is: " + myPerson.getName());
		System.out.println("Age is: " + myPerson.getAge());
	}
}
