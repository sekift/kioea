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
public class AnalysisTran {
	private static File inputFile = new File("F:\\tran.txt");
	private static File outputFile = new File("F:\\tran-out1.txt");
	private static List<String> travelList = new ArrayList<>(200);



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
        AnalysisTran ipa = new AnalysisTran();
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
                String str = it.nextLine();

				FileUtils.writeStringToFile(outputFile, str+"\t"+1+"\r\n", "UTF-8", true);
				FileUtils.writeStringToFile(outputFile, str+"\t"+2+"\r\n", "UTF-8", true);
				FileUtils.writeStringToFile(outputFile, str+"\t"+3+"\r\n", "UTF-8", true);
				System.out.println("str=" + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
