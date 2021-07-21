package com.kioea.www.classutil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


/**
 * 
 * @author:Administrator
 * @time:2015-9-25 下午03:46:18
 * @version:
 */
public class ClassScaner {
	
	public static void main(String[] args){
		try {
			System.out.println(FileUtils.readLines(new File("D:\\workspace\\Demo\\bin\\Adapter\\CloneTest.class"),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
}
