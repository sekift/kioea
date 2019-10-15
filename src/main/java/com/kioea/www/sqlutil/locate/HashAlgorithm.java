package com.kioea.www.sqlutil.locate;

public interface HashAlgorithm {

	/**
	 * 对key进行hash运行,获取hash值
	 * @param key -- 被进行hash的key
	 * @return -- hash值
	 */
	long hash(final String key);
}
