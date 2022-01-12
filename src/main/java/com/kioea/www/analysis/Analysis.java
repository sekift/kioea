package com.kioea.www.analysis;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:sekift
 * @time:2015-10-26 上午10:34:43
 * @version: 1
 */
public class Analysis {
    static File inputFile = new File("C:\\xxx.txt");
	private static List<String> travelList = new ArrayList<>(400000);

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

	public static void main(String[] args) {
		Analysis ipa = new Analysis();
		long startTime = System.currentTimeMillis();
		ipa.travelIpA();

		//ipa.travelIpA(); //测量这个的时候记得将travelList、初始化等等的去掉
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
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

}
