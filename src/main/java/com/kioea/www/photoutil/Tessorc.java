package com.kioea.www.photoutil;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

/**
 * 
 * @author:sekift
 * @time:2015-3-20 下午04:26:17
 * @version:
 */
public class Tessorc {
	
	private static void loadDLL(String libFullName) {
        try {
            String nativeTempDir = System.getProperty("java.io.tmpdir");
            InputStream in = null;
            FileOutputStream writer = null;
            BufferedInputStream reader = null;
            File extractedLibFile = new File(nativeTempDir + File.separator + libFullName);
            System.out.println(extractedLibFile.getAbsolutePath());
            
            if (!extractedLibFile.exists()) {
                try {
                    in = Tesseract.class.getResourceAsStream("/" + libFullName);
                    Tesseract.class.getResource(libFullName);
                    reader = new BufferedInputStream(in);
                    writer = new FileOutputStream(extractedLibFile);
                    byte[] buffer = new byte[1024];
                    while (reader.read(buffer) > 0) {
                        writer.write(buffer);
                        buffer = new byte[1024];
                    }
                    in.close();
                    writer.close();
                    System.load(extractedLibFile.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (writer != null) {
                        writer.close();
                    }
                }
            } else {
                System.load(extractedLibFile.toString());
            }
        } catch (IOException e) {
            System.out.println("初始化 " + libFullName + " DLL错误" + e);
        }
 }
	
	public static void main(String[] args){
//		File imageFile = new File("D:\\IDE\\eclipse-helios\\eclipse\\workspace\\sekiftutil-1.1.4\\bin\\eurotext.tif");  
		File imageFile = new File("D:\\orctest.jpg");  
		loadDLL("liblept168.dll");//注意加载先后顺序
		loadDLL("libtesseract302.dll");//注意加载先后顺序
		Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping  
		try {
			
			String result = instance.doOCR(imageFile);
			System.out.println(result);
		} catch (TesseractException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}

}
