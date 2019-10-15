package com.kioea.www.photoutil;

import java.io.File;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @author:Administrator
 * @time:2015-6-23 下午04:27:42
 * @version:
 */
public class QREncoder {

	public static void main(String args[]) {
		String contents = "http://bbs.bubbt.com";
		Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
		hints.put(EncodeHintType.CHARACTER_SET, "GBK");
		BitMatrix matrix = null;
		try {
			// MultiFormatWriter
			matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, 300, 300, hints);
		} catch (Exception e) {
			e.printStackTrace();
		}
		File file = new File("D://qrcode.png");
		try {
			MatrixToImageWriter.writeToFile(matrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
