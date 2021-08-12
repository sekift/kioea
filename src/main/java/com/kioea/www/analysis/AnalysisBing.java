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
 * @version:
 */
public class AnalysisBing {
    private static List<String> travelList = new ArrayList<>(300);
    private static File inputFile = new File("F:\\6yueshuju.txt");
    private static File outputFile = new File("F:\\6yueshuju-out.txt");

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
        try {
            for (int i = 0; i < travelList.size(); i++) {
                if(i%2==0) {
                    FileUtils.writeStringToFile(outputFile, travelList.get(i), "UTF-8", true);
                }else{
                    FileUtils.writeStringToFile(outputFile, travelList.get(i)+"\r\n", "UTF-8", true);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnalysisBing ipa = new AnalysisBing();
        long startTime = System.currentTimeMillis();
        ipa.travelIpA();

        //ipa.travelIpA(); //测量这个的时候记得将travelList、初始化等等的去掉
        long endTime = System.currentTimeMillis();
        System.out.println("处理时间= " + (endTime - startTime));
    }

}
