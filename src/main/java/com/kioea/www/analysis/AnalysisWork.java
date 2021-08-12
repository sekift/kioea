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
public class AnalysisWork {
    private static List<String> travelList = new ArrayList<>(300);
    private static File inputFile = new File("F:\\6yueshuju-out.txt");
    private static File outputFile = new File("F:\\6yueshuju-out1.txt");

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
        for (String str : travelList) {
            String[] itemStr = str.split("	");
            String name = itemStr[0];
            int sum1 = 0, sum2 = 0;
            for (int i = 1; i < itemStr.length; i++) {
                int item = Integer.valueOf(itemStr[i]);
                int count1 = 0, count2 = 0;
                if (item <= 1200) {
                    // 早卡
                    if (item >=600 && item < 700) {
                        count1 = 2 * 60 + 660 - item;
                    } else if (item >= 700 && item < 800) {
                        count1 = 60 + 760 - item;
                    } else if (item >= 800 && item < 900) {
                        count1 = 860 - item;
                    } else if (item >= 900 && item < 1000) {
                        count1 = 900 - item;
                    }else{
                        System.out.println("名称= " + name + "异常："+item);
                    }
                    sum1 += count1;
                } else {
                    // 晚卡
                    if (item >= 1800 && item < 1900) {
                        count2 = item - 1800;
                    } else if (item >= 1900 && item < 2000) {
                        count2 = 60 + item - 1900;
                    } else if (item >= 2000 && item < 2100) {
                        count2 = 2 * 60 + item - 2000;
                    } else if (item >= 2100 && item < 2200) {
                        count2 = 3 * 60 + item - 2100;
                    } else if (item >= 2200 && item < 2300) {
                        count2 = 4 * 60 + item - 2200;
                    } else if (item >= 2300 && item < 2400) {
                        count2 = 5 * 60 + item - 2300;
                    } else if (item >= 2400 && item < 2500) {
                        count2 = 6 * 60 + item - 2400;
                    }else if (item >= 2500 && item < 2600) {
                        count2 = 7 * 60 + item - 2500;
                    }else if (item >= 2600 && item < 2700) {
                        count2 = 8 * 60 + item - 2600;
                    }else{
                        System.out.println("名称= " + name + "异常："+item);
                    }
                    sum2 += count2;
                }
            }
            System.out.println("名称\t" + name + " \t早卡\t" + sum1 + " \t晚卡\t" + sum2+ "\t合计\t" + (sum1+sum2));
        }
    }

    public static void main(String[] args) {
        AnalysisWork ipa = new AnalysisWork();
        long startTime = System.currentTimeMillis();
        ipa.travelIpA();

        //ipa.travelIpA(); //测量这个的时候记得将travelList、初始化等等的去掉
        long endTime = System.currentTimeMillis();
        System.out.println("处理时间= " + (endTime - startTime));
    }

}
