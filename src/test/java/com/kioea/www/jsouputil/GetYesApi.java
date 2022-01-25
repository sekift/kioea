package com.kioea.www.jsouputil;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class GetYesApi {

    private static final String URL_DOMAIN = "http://open.yesapi.cn";

    private static String getDatabaseName(String href) {
        String str = href.replace("/tablelist/", "");
        str = str.replace(".html", "");
        String[] strs = str.split("_");
        str = strs[0] + "_" + strs[1];
        str = str.replace("okayapi", "des");
        str = str.replace("yesapi", "des");
        return str.toString();
    }

    private static void getUrl(String url, String name) {
        Document doc = JsoupUtil.getDocByConnect(url);

        Elements eles = doc.getElementsByClass("btn btn-green");
        StringBuilder rar = new StringBuilder();
        String databaseName = "";
        for (Element element : eles) {
            String href = element.attr("href");
            if (!href.startsWith("/?")) {
                databaseName = getDatabaseName(href);
                String content = getTableCreate(URL_DOMAIN + href);
                rar.append("\n").append(content);
            }
        }
        try {
            FileUtils.writeStringToFile(new File("F:/yesapi/" + databaseName + ".txt"), rar.toString(),
                    "utf-8", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 200; i++) {
            getUrl(URL_DOMAIN + "/list" + i + ".html", "list" + i);
        }

    }

    private static String getTableCreate(String url) {
        Document doc = JsoupUtil.getDocByConnect(url);
        Elements eles = doc.getElementsByClass("hljs");
        StringBuilder rar = new StringBuilder();
        for (Element element : eles) {
            String content = element.text();
            content = content.replace("okayapi", "des");
            content = content.replace("yesapi", "des");
            rar.append(content);
        }
        return rar.toString();
    }
}
