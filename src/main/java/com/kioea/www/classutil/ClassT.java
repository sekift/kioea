package com.kioea.www.classutil;

import java.util.ArrayList;
import java.util.List;

import com.kioea.www.jsonutil.JsonUtils;

/**
 * 自做泛型
 * 
 * @author:sekift
 * @time:2015-1-26 上午11:06:44
 * @version:
 */
public class ClassT {

	public class Param {
		private String key;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		private String value;
	}

	public List<Param> getListT(String key, String value) {
		List<Param> list = new ArrayList<>();
		Param p = new Param();
		p.setKey(key);
		p.setValue(value);
		list.add(p);
		return list;
	}

	public List<Param> getListT() {
		List<Param> list = new ArrayList<>();
		Param p = null;
		for (int i = 0; i < 10; i++) {
			// 对象创建需要放在里面，才可以
			p = new Param();
			p.setKey(i + "");
			p.setValue("a" + i);
			list.add(p);
		}
		return list;
	}

	public static void main(String[] args) {
		ClassT ct = new ClassT();
		List<Param> list = ct.getListT("a", "z");
		System.out.println(JsonUtils.toJson(list));

		List<Param> list1 = ct.getListT();
		System.out.println(JsonUtils.toJson(list1));

	}

}
