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
public class AnalysisSeven {
    private static List<String> analysisList = new ArrayList<>(1000);
    static File inputFile = new File("F:\\7yue.txt");
    static File outputFile1 = new File("F:\\7yue-1.txt");
    static File outputFile2 = new File("F:\\7yue-2.txt");
    static File outputFile3 = new File("F:\\7yue-3.txt");
    static File outputFile4 = new File("F:\\7yue-4.txt");
    static File outputFile5 = new File("F:\\7yue-5.txt");


    static {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(inputFile, "utf-8");
            while (it.hasNext()) {
                analysisList.add(it.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analysisList1() {
        try {
            for (int i = 0; i < analysisList.size(); i++) {
                String hang = analysisList.get(i);
                if (hang.contains("上午")) {
                    String[] itemStr = hang.split("\t");
                    for (int j = 0; j < itemStr.length; j++) {
                        if (j < 2) {
                            FileUtils.writeStringToFile(outputFile1, itemStr[j] + "\t", "utf-8", true);
                        } else {
                            if (!"".equals(itemStr[j])) {
                                if (!itemStr[j].contains(":")) {
                                    FileUtils.writeStringToFile(outputFile1, "09:00", "utf-8", true);
                                    if (j < itemStr.length - 1) {
                                        FileUtils.writeStringToFile(outputFile1, "\t", "utf-8", true);
                                    } else {
                                        FileUtils.writeStringToFile(outputFile1, "\r\n", "utf-8", true);
                                    }
                                } else {
                                    FileUtils.writeStringToFile(outputFile1, itemStr[j], "utf-8", true);
                                    if (j < itemStr.length - 1) {
                                        FileUtils.writeStringToFile(outputFile1, "\t", "utf-8", true);
                                    } else {
                                        FileUtils.writeStringToFile(outputFile1, "\r\n", "utf-8", true);
                                    }
                                }
                            }
                        }
                    }
                } else if (hang.contains("下午")) {
                    String[] itemStr = hang.split("\t");
                    for (int j = 0; j < itemStr.length; j++) {
                        if (j < 2) {
                            FileUtils.writeStringToFile(outputFile1, itemStr[j] + "\t", "utf-8", true);
                        } else {
                            if (!"".equals(itemStr[j])) {
                                if (!itemStr[j].contains(":")) {
                                    FileUtils.writeStringToFile(outputFile1, "18:00", "utf-8", true);
                                    if (j < itemStr.length - 1) {
                                        FileUtils.writeStringToFile(outputFile1, "\t", "utf-8", true);
                                    } else {
                                        FileUtils.writeStringToFile(outputFile1, "\r\n", "utf-8", true);
                                    }
                                } else {
                                    FileUtils.writeStringToFile(outputFile1, itemStr[j], "utf-8", true);
                                    if (j < itemStr.length - 1) {
                                        FileUtils.writeStringToFile(outputFile1, "\t", "utf-8", true);
                                    } else {
                                        FileUtils.writeStringToFile(outputFile1, "\r\n", "utf-8", true);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analysisList2() {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(outputFile1, "utf-8");
            while (it.hasNext()) {
                String hang = it.nextLine();
                FileUtils.writeStringToFile(outputFile2, hang.replace(":", "") + "\r\n", "utf-8", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analysisList3() {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(outputFile2, "utf-8");
            while (it.hasNext()) {
                String hang = it.nextLine();
                String[] itemStr = hang.split("\t");
                String name = itemStr[0];
                int sum1 = 0, sum2 = 0;
                if (hang.contains("上午")) {
                    for (int i = 2; i < itemStr.length; i++) {
                        int item = Integer.valueOf(itemStr[i]);
                        int count1 = 0, count2 = 0;
                        if (item >= 600 && item < 700) {
                            count1 = 2 * 60 + 660 - item;
                        } else if (item >= 700 && item < 800) {
                            count1 = 60 + 760 - item;
                        } else if (item >= 800 && item < 900) {
                            count1 = 860 - item;
                        } else if (item >= 900 && item < 1000) {
                            count1 = 900 - item;
                        } else if (item >= 1000 && item < 1100) {
                            count1 = -60 + 1000 - item;
                        } else {
                            count1 = 0;
                            //System.out.println("名称= " + name + "；上午；异常：" + item);
                        }
                        sum1 += count1;
                    }
                } else if (hang.contains("下午")) {
                    for (int i = 2; i < itemStr.length; i++) {
                        int item = Integer.valueOf(itemStr[i]);
                        int count1 = 0, count2 = 0;
                        if (item >= 1200 && item < 1800) {
                            count2 = 0;
                        } else if (item >= 1800 && item < 1900) {
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
                        } else if (item >= 0 && item < 60) {
                            count2 = 6 * 60 + item;
                        } else if (item >= 100 && item < 160) {
                            count2 = 7 * 60 + item - 100;
                        } else if (item >= 200 && item < 260) {
                            count2 = 8 * 60 + item - 200;
                        } else if (item >= 300 && item < 360) {
                            count2 = 9 * 60 + item - 300;
                        } else {
                            System.out.println("名称= " + name + "；下午；异常：" + item);
                        }

                        sum2 += count2;
                    }
                }
                //System.out.println("名字\t" + name + "\t天数\t"+(itemStr.length-2)+" \t早卡\t" + sum1 + " \t晚卡\t" + sum2);
                String output = "名字\t" + name + "\t天数\t" + (itemStr.length - 2) + " \t上午\t" + sum1 + " \t下午\t" + sum2;
                FileUtils.writeStringToFile(outputFile3, output + "\r\n", "utf-8", true);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analysisList4() {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(outputFile3, "utf-8");
            int i = 0;
            while (it.hasNext()) {
                String hang = it.nextLine();
                if (i % 2 == 0) {
                    String[] itemStr = hang.split("\t");
                    for (int j = 0; j < 6; j++) {
                        FileUtils.writeStringToFile(outputFile4, itemStr[j] + "\t", "UTF-8", true);
                    }

                } else {
                    String[] itemStr = hang.split("\t");
                    for (int j = 6; j < itemStr.length; j++) {
                        if (j < itemStr.length - 1) {
                            FileUtils.writeStringToFile(outputFile4, itemStr[j] + "\t", "UTF-8", true);
                        } else {
                            FileUtils.writeStringToFile(outputFile4, itemStr[j] + "\r\n", "UTF-8", true);
                        }
                    }
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void analysisList5() {
        LineIterator it;
        try {
            it = FileUtils.lineIterator(outputFile4, "utf-8");
            while (it.hasNext()) {
                String hang = it.nextLine();
                String[] itemStr = hang.split("\t");
                // 天数
                int day = Integer.valueOf(itemStr[3].trim());
                int am = Integer.valueOf(itemStr[5].trim());
                int pm = Integer.valueOf(itemStr[7].trim());

                int total = am + pm;
                String avg = String.format("%.2f", (double) total / day / 60);

                FileUtils.writeStringToFile(outputFile5, hang + "\t总数\t" + total + "\t平均(小时)\t" + avg + "\r\n", "UTF-8", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnalysisSeven ipa = new AnalysisSeven();
        ipa.analysisList5();
    }

}
