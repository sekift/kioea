package com.kioea.www.textutil;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

/**
 * StringTemplate 字符串模板工具类
 */
public class StUtils {

    public static void main(String[] args) {
        ST hello = new ST("Hello, <name>!");
        hello.add("name", "World");
        String output = hello.render();
        System.out.println(output);

        STGroup g = new STGroupFile("test.stg");
        ST st = g.getInstanceOf("test");
        st.add("str1", "商品名称：爱奇艺黄金会员");
        String str = st.render();
        System.out.println(str);
    }

}
