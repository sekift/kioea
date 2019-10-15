package com.kioea.www.cache;

public class Cache {
	private String key;// 缓存ID, userId
	private Object value;// 缓存数据, port
	private long createTime;// 更新时间, 注册时间

	public Cache() {
		super();
	}

	public Cache(String key, Object value, long createTime) {
		this.key = key;
		this.value = value;
		this.createTime = createTime;
	}

	public String getKey() {
		return key;
	}

	public long getCreateTime() {
		return createTime;
	}

	public Object getValue() {
		return value;
	}

	public void setKey(String string) {
		key = string;
	}

	public void setCreateTime(long l) {
		createTime = l;
	}

	public void setValue(Object object) {
		value = object;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Cache [");
		sb.append("key=").append(key)
		  .append(",value=").append(value)
		  .append(",createTime=").append(createTime)
		  .append("]");
		return sb.toString();
	}
}