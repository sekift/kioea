package com.kioea.www.countrys;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.kioea.www.CloseUtil;
import com.kioea.www.configutil.XmlUtil;

/**
 * 根据国家啥的获取城市
 * 
 * @author:sekift
 * @time:2014-9-29 下午03:09:40
 * @version:
 */
public class GetCountrys {

	/**
	 * 配置
	 */
	private static Map<?, ?> config = null;

	private static final String C_FILE = GetCountrys.class.getResource("/Countrys.xml").toString().substring(5);

	/**
	 * 初始化操作
	 */
	static {
		// 加载配置数据
		FileInputStream in = null;
		try {
			in = new FileInputStream(new File(C_FILE));
			config = XmlUtil.toMap(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			CloseUtil.closeSilently(in);
		}
	}

	/**
	 * 
	 */
	public static String getCountryName(String name) {
		try {
			List<Map<String, String>> list = (List<Map<String, String>>) config.get("Country");
			for (Map<String, String> m : list) {
				String code = null;
				if (null != m.get("Code")) {
					code = m.get("Code");
				} else {
					code = "Code";
				}

				String nameCN = null;
				if (null != m.get("Country_name_CN")) {
					nameCN = m.get("Country_name_CN");
				} else {
					nameCN = "nameCN";
				}

				String numberCode = null;
				if (null != m.get("NumberCode")) {
					numberCode = m.get("NumberCode");
				} else {
					numberCode = "numberCode";
				}

				String aliasTW = null;
				if (null != m.get("Alias_TW")) {
					aliasTW = m.get("Alias_TW");
				} else {
					aliasTW = "aliasTW";
				}

				String aliasHK = null;
				if (null != m.get("Alias_HK")) {
					aliasHK = m.get("Alias_HK");
				} else {
					aliasHK = "Alias_HK";
				}

				String dialingCode = null;
				if (null != m.get("DialingCode")) {
					dialingCode = m.get("DialingCode");
				} else {
					dialingCode = "dialingCode";
				}

				if (nameCN.equals(name)) {
					return m.get("Country_name_CN") + "," + m.get("Country_name") + "," + m.get("Code") + ","
							+ m.get("NumberCode") + "," + m.get("Alias_TW") + "," + m.get("Alias_HK") + ","
							+ m.get("DialingCode");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		System.out.println(getCountryName("中国"));
	}
}
