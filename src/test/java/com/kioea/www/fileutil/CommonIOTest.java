package com.kioea.www.fileutil;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;


/**
 * 
 * @author:Administrator
 * @time:2015-9-16 上午10:22:11
 * @version:
 */
public class CommonIOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		File file=new File("D:\\temp\\test.txt");
//		try {
//			List<String> list= FileUtils.readLines(file, "utf-8");
//			for(int i=0;i<list.size();i++){
//				System.out.println(list.get(i));
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		File file=new File("D:\\temp\\fir.txt");
		String encoding="utf-8";
		int i=0;
		try {
			LineIterator ite=FileUtils.lineIterator(file, encoding);
			while(ite.hasNext()){
				System.out.println(ite.next());
				i++;
				if(i>2){
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
