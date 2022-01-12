package com.kioea.www.analysis;

import com.kioea.www.jsouputil.JsoupUtil;

import java.util.List;

/**
 * @author:sekift
 * @time:2015-10-26 上午10:34:43
 * @version: 1
 */
public class AnalysisTest {

    public static void main(String[] args) {
        String tmpDir = System.getProperty("java.io.tmpdir");
        System.out.println(tmpDir);
        /// open:file
        System.out.println(JsoupUtil.getDocByConnect("https://www.google.com/"));
    }

    private void test1(List<String> list, String str) {
        ///add


    }
}
