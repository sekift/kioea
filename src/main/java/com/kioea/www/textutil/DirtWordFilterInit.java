package com.kioea.www.textutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import multi.patt.match.ac.AhoCorasick;
import multi.patt.match.ac.SearchResult;

/**
 * 敏感字过滤器, 使用AC多模式匹配算法. 保证敏感字库的大小对匹配算法的速度无影响
 * 
 */
public class DirtWordFilterInit {

	static {
		tree = new AhoCorasick();
		init();
	}

	private static AhoCorasick tree;

	public DirtWordFilterInit() {
		
	}

	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public static void init() {
		BufferedReader br = null;
		String keyword = null;

		try {
			br = new BufferedReader(new InputStreamReader(DirtWordFilterInit.class.getClassLoader().getResourceAsStream(
					"key1.txt"), "UTF-8"));
			while ((keyword = br.readLine()) != null) {
				if (keyword.trim().length() != 0) {
					tree.add(keyword.trim().getBytes(), keyword.trim());
				}
			}

			tree.prepare();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断该文本是否包含敏感字
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean containsDirtWord(String text) throws UnsupportedEncodingException {
		Iterator<?> it = tree.search(text.toUpperCase().getBytes("UTF-8"));

		return it.hasNext();
	}

	/**
	 * 过滤文本, 敏感字转换为***
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String doFilter(String text) throws UnsupportedEncodingException {
		String filterText = text;

		Iterator<?> it = tree.search(text.getBytes("UTF-8"));
		SearchResult result = null;
		while (it.hasNext()) {
			result = (SearchResult) it.next();
			Iterator<?> outputIt = result.getOutputs().iterator();
			while (outputIt.hasNext()) {
				String dirtWord = (String) outputIt.next();
				filterText = filterText.replaceAll(dirtWord, getMask(dirtWord.length()));
			}
		}

		return filterText;
	}

	/**
	 * 返回敏感词
	 * 
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Set<String> getDirtWordSet(String text) throws UnsupportedEncodingException {
		Set<String> set = new HashSet<String>();
		Iterator<?> it = tree.search(text.getBytes("UTF-8"));
		SearchResult result = null;
		while (it.hasNext()) {
			result = (SearchResult) it.next();
			Iterator<?> outputIt = result.getOutputs().iterator();
			while (outputIt.hasNext()) {
				String dirtWord = (String) outputIt.next();
				set.add(dirtWord);
			}
		}

		return set;
	}

	private static final String mask1 = "*";
	private static final String mask2 = "**";
	private static final String mask3 = "***";
	private static final String mask4 = "****";

	private String getMask(int length) {
		if (length < 1) {
			throw new IllegalArgumentException(String.valueOf(length));
		}

		if (length <= 4) {
			return getSmallMask(length);
		}

		int count = length / 4;
		StringBuilder maskBuilder = new StringBuilder();
		for (int i = 0; i < count; i++) {
			maskBuilder.append(mask4);
		}

		// 计算余数
		int remainder = length % 4;
		if (remainder != 0) {
			maskBuilder.append(getSmallMask(remainder));
		}

		return maskBuilder.toString();
	}

	private String getSmallMask(int length) {
		switch (length) {
		case 1:
			return mask1;
		case 2:
			return mask2;
		case 3:
			return mask3;
		case 4:
			return mask4;
		}
		return "";
	}

}
