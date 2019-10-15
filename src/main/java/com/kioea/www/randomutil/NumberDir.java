package com.kioea.www.randomutil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author:luyz
 * @time:2017-12-20 下午03:23:09
 * @version:
 */
public class NumberDir {

	public static final String a = "download.fir.im/";
	public static int[] in = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static void main(String[] args) {
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in.length; j++) {
				for (int k = 0; k < in.length; k++) {
					for (int l = 0; l < in.length; l++) {
						try {
							FileUtils.writeStringToFile(new File("D:/number.txt"), a + in[i] + in[j] + in[k] + in[l]+"\r\n",
									Charset.forName("utf-8"), true);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

	}
}
