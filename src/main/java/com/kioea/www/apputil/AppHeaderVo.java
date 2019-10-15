package com.kioea.www.apputil;

/**
 * 
 * @Description: app header vo
 */
public class AppHeaderVo {
	
	/**   channel = bd;
	*     headCstr = 1abe7d781cf4d7e7d2d6791901479edd;
	*     mobile = "iPhone Simulator";
	*     os = iPhone;
	*     product = product1;
	*     uuid = "F667471E-89AE-496C-9F36-C85F1649CCFB";
	*     vcode = 21;
	*     vname = "2.1.0";
	*     hw = 400;
	*     ahw = 320;
	*/
	private String channel; // 安卓发布渠道、平台
	private String mobile; // 手机型号
	private String os; // 系统名称
	private String product; // 产品名称
	private String uuid; // 手机唯一码
	private String vcode; // 版本代号
	private String vname; // 版本名称
	private String headCstr; // 校验码
	private String hw; // 设备宽高，16进制后数值，使用;分隔；
	private String ahw; // 应用宽高，16进制后数值，使用;分隔；
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getVcode() {
		return vcode;
	}
	public void setVcode(String vcode) {
		this.vcode = vcode;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getHeadCstr() {
		return headCstr;
	}
	public void setHeadCstr(String headCstr) {
		this.headCstr = headCstr;
	}
	public String getHw() {
		return hw;
	}
	public void setHw(String hw) {
		this.hw = hw;
	}
	public String getAhw() {
		return ahw;
	}
	public void setAhw(String ahw) {
		this.ahw = ahw;
	}
	@Override
	public String toString() {
		return "AppheaderVo [channel=" + channel + ", headCstr=" + headCstr
				+ ", mobile=" + mobile + ", os=" + os + ", product=" + product
				+ ", uuid=" + uuid + ", vcode=" + vcode + ", vname=" + vname
				+ ", hw=" + hw + ", ahw=" + ahw
				+ "]";
	}
	
	public int vcodetoInt(){
		try {
			return Integer.parseInt(this.vcode);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/** 
	* 产品_平台
	* 
	*/
	public String toAPPClient(){
		return product +"_"+ os;
	}
	
	/** 
	 * 产品_平台_版本
	 * 
	 */
	public String toAPPVersion(){
		return toAPPClient() +"_"+ vname;
	}
	
	/** 
	* 是否产品客户端
	* 
	*/
	public boolean isQing(){
		return "product".equals(product);
	}
	/** 
	* 是否IOS
	*/
	public boolean isIPhone(){
		return "iPhone".equals(os);
	}
	/** 
	* 是否安卓
	*/
	public boolean isAndroid(){
		return "android".equals(os);
	}
	
	/** 
	* 浏览器附加信息
	* 
	*/
	public String toUserAgent(){
		return "Channel= "+ channel +";Vcode="+ vcode +";UUID="+ uuid +";Mobile="+ mobile + ";";
	}
	
}
