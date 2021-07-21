package com.kioea.www.configutil;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * xml配置属性类
 * 
 * @author 
 * @date 
 */
@SuppressWarnings({ "unchecked"})
public class XmlProperties extends ReloadableProperties implements Config {

	private URL srcUrl = null;

	public void setSourceURL(URL url) {
		this.srcUrl = url;
	}

	@Override
	protected URL getSourceURL() {
		return srcUrl;
	}

	@Override
	protected Map reload(InputStream in, Map tarStorage) {

		try {
			Map rtv = XmlUtil.toMap(in);
			return rtv;
		} catch (Exception ex) {
			return tarStorage;
		}
	}

	@Override
	public <T> T getItem(String name) {
		return (T) props.get(name);
	}

	@Override
	public <T> T getItem(String name, T defaultValue) {

		T rtv = (T) props.get(name);
		return (null != rtv) ? rtv : defaultValue;
	}

}
