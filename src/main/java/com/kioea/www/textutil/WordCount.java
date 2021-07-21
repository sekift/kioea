package com.kioea.www.textutil;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.kioea.www.jsonutil.JsonResult;
import com.kioea.www.jsonutil.JsonUtils;

/**
 * 统计单词的程序
 * --不单单统计单词，还能返回键个数、值个数，能按键或值排序，能以map、json形式返回。
 * --排序在MapUtil类
 *
 * @author 作者:sekift
 * @author E-mail:sekiftlyz@gmail.com
 */
public class WordCount {
    /**
     * 对String进行单词统计,只返回map
     *
     * @param sentence
     * @return map
     */
    public static Map<String, Integer> countWordsMap(String sentence) {
        Map<String, Integer> map = new HashMap<>(8);
        // 分词后的split
        StringTokenizer token = new StringTokenizer(sentence);
        // 循环取词
        while (token.hasMoreTokens()) {
            String word = token.nextToken(" ");
            if (map.containsKey(word)) {
                int count = map.get(word);
                map.put(word, count + 1);
            } else {
                map.put(word, 1);
            }
        }
        return map;
    }

    /**
     * 对String进行单词统计，返回map以及统计数
     *
     * @param sentence
     * @return String
     */
    public static String countWordsJson(String sentence) {
        Map<String, Integer> map = countWordsMap(sentence);
        int countKey = map.keySet().size();
        int countValue = 0;
        for (String key : map.keySet()) {
            countValue += map.get(key);
        }
        return getResult(map, countKey, countValue);
    }

    static JsonResult jr = null;

    /**
     * 返回map
     *
     * @param result
     * @return
     * @throws Exception
     */
    public static Map<Object, Object> getDataMap(String result) {
        jr = JsonResult.newInstance(result);
        return jr.getDataMap();
    }

    /**
     * 返回countKey
     *
     * @param result
     * @return
     * @throws Exception
     */
    public static int getCountKey(String result) {
        jr = JsonResult.newInstance(result);
        return jr.getCountKey();
    }

    /**
     * 返回countValue
     *
     * @param result
     * @return
     * @throws Exception
     */
    public static int getCountValue(String result) {
        jr = JsonResult.newInstance(result);
        return jr.getCountValue();
    }

    /**
     * 构造统计后的数据
     *
     * @param countKey
     * @param countValue
     * @param map
     * @return
     */
    public static String getResult(Map<String, Integer> map, int countKey, int countValue) {
        String result = null;
        try {
            Map<Object, Object> resultMap = new HashMap<>(8);
            resultMap.put("data", map);
            resultMap.put("countKey", countKey);
            resultMap.put("countValue", countValue);
            result = JsonUtils.toJson(resultMap);
        } catch (Exception ignored) {
        }
        return result;
    }

}
