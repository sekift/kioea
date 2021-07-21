package com.kioea.www.iputil;

/**
 * 
 * @author:sekift
 * @time:2014-8-13 下午04:33:07
 * @version:
 */
public class IPEntry {
	public String beginIp;
	public String endIp;
	public String country;
	public String area;

	public IPEntry() {
		beginIp = endIp = country = area = "";
	}

	@Override
    public String toString() {
		return this.area + " " + this.country + "IP范围:" + this.beginIp + "-" + this.endIp;
	}
}
