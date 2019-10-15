package com.kioea.www.analysis;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

/**
 * 
 * @author:sekift
 * @time:2015-10-26 上午10:34:43
 * @version:
 */
public class Analysis {
	private static List<String> travelList = new ArrayList<String>(400000);
	static File inputFile = new File("C:\\xxx.txt");

	static {
		LineIterator it;
		try {
			it = FileUtils.lineIterator(inputFile, "utf-8");
			while (it.hasNext()) {
				travelList.add(it.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void travelIpA() {
		LineIterator it;
		try {
			it = FileUtils.lineIterator(inputFile, "utf-8");
			while (it.hasNext()) {
				it.nextLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Analysis ipa = new Analysis();
		long startTime = System.currentTimeMillis();
		ipa.travelIpA();

		//ipa.travelIpA(); //测量这个的时候记得将travelList、初始化等等的去掉
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}

}
