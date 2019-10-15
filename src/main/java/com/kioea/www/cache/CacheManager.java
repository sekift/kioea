package com.kioea.www.cache;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 简单实现一个缓冲功能
 * @author sekift
 *
 */
public class CacheManager {
	private static Logger logger = LoggerFactory.getLogger(CacheManager.class);
	
	private static ConcurrentHashMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();

    private volatile static CacheManager instance;

    public static CacheManager getInstance() {
        if (instance == null) {
            synchronized (CacheManager.class) {
                if (instance == null)
                    instance = new CacheManager();
            }
        }
        return instance;
    }
    
	// 得到缓存
	private Cache getCache(String key) {
		return (Cache) cacheMap.get(key);
	}

	// 判断是否存在一个缓存
	private boolean hasCache(String key) {
		return cacheMap.containsKey(key);
	}
	
	// 清除指定的缓存
	public void clearOnly(String key) {
		cacheMap.remove(key);
	}

	// 载入缓存
	public void putCache(String key, Cache obj) {
		cacheMap.put(key, obj);
	}

	// 获取缓存信息
	public Cache getCacheInfo(String key) {
		if (hasCache(key)) {
			return getCache(key);
		} else
			return null;
	}

	// 获取缓存中的大小
	public int getCacheSize() {
		return cacheMap.size();
	}

	// 获取缓存对象中的所有值名称
	public ArrayList<Cache> getCacheAllkey() {
		ArrayList<Cache> a = new ArrayList<Cache>();
		try {
			Iterator<Entry<String, Cache>> i = cacheMap.entrySet().iterator();
			while (i.hasNext()) {
				@SuppressWarnings("rawtypes")
				java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
				a.add((Cache) entry.getValue());
			}
		} catch (Exception ex) {
			logger.error("CacheManager获取所有的键值出错了。",ex);
		}
		return a;
	}

}