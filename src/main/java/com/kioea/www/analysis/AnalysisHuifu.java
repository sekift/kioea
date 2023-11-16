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
public class AnalysisHuifu {
    private static File inputFile1 = new File("F:\\he1.txt");
    private static File inputFile2 = new File("F:\\he2.txt");
    private static File outputFile = new File("F:\\he-out.txt");
    private static List<String> travelList1 = new ArrayList<>(2000);
    private static List<String> travelList2 = new ArrayList<>(20000);


    static {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(inputFile1, "utf-8");
            while (it.hasNext()) {
                travelList1.add(it.nextLine());
            }

            it = FileUtils.lineIterator(inputFile2, "utf-8");
            while (it.hasNext()) {
                travelList2.add(it.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnalysisHuifu ipa = new AnalysisHuifu();
        long startTime = System.currentTimeMillis();
        ipa.travelIpA();

        //ipa.travelIpA(); //测量这个的时候记得将travelList、初始化等等的去掉
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }

    private void travelIpA() {
        try {
            for (int i = 0; i < travelList1.size(); i++) {
                String str1 = travelList1.get(i);
                for (int j = 0; j < travelList2.size(); j++) {
                    String[] str2 = travelList2.get(j).split("\\t");
                    if (str1.equals(str2[0])) {
                        FileUtils.writeStringToFile(outputFile, travelList2.get(j) + "\r\n", "UTF-8", true);
                        System.out.println("str=" + travelList2.get(j));
                        break;
                    } else {
                        if (j == travelList2.size() - 1) {
                            FileUtils.writeStringToFile(outputFile, str1 + "\t" + 0 + "\r\n", "UTF-8", true);
                            System.out.println("str=" + str1 + "\t" + 0);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
