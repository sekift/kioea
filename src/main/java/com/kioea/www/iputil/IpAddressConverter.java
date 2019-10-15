package com.kioea.www.iputil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

public class IpAddressConverter {
	private static Logger logger = Logger.getLogger(IpAddressConverter.class);

	private static List<Node> ipCityList = new ArrayList<Node>(50000);
	private static List<Node> ipCountryList = new ArrayList<Node>(101000);
	private static int ipCityListSize;
	private static int ipCountryListSize;

	static {
		String line = null;
		String[] arrays = null;
		try {
			Reader r = new InputStreamReader(IpAddressConverter.class
					.getClassLoader().getResourceAsStream(
		                     "config/ip_city.txt"), "GBK");
			BufferedReader br = new BufferedReader(r);

			while ((line = br.readLine()) != null) {
				arrays = StringUtils.split(line, ";");

				Node node = new Node(Long.parseLong(arrays[0]), Long
						.parseLong(arrays[1]),arrays[2]);
				ipCityList.add(node);
			}

			logger.info("装载ip_city数据成功:" + ipCityList.size());

			br.close();
			br = null;
		} catch (Exception ex) {
			logger.error("装载ip_city数据出错：", ex);
		}

		try {
			Reader r = new InputStreamReader(IpAddressConverter.class
					.getClassLoader().getResourceAsStream(
							"config/ip_country.txt"), "GBK");
			BufferedReader br = new BufferedReader(r);

			while ((line = br.readLine()) != null) {
				arrays = StringUtils.split(line, ";");

				Node node = new Node(Long.parseLong(arrays[0]), Long
						.parseLong(arrays[1]), arrays[2]);

				ipCountryList.add(node);
			}

			logger.info("装载ip_country数据成功:" + ipCountryList.size());

			br.close();
			br = null;
		} catch (Exception ex) {
			logger.error("装载ip_country数据出错：", ex);
		}

		ipCityListSize = ipCityList.size();
		ipCountryListSize = ipCountryList.size();
	}

	public static long convertIpToInt(String ip) {
		String[] ipArray = StringUtils.split(ip, ".");
		long[] ips = new long[ipArray.length];

		long ipInt = 0;

			for (int i = 0; i < ipArray.length; i++) {
				ipArray[i] = ipArray[i].trim();

				try {
					ips[i]=Long.parseLong(ipArray[i]);
				} catch (Exception e) {
				}

				if(ips[i] < 0) {
					ips[i] = 0;
				} else if(ips[i] > 255) {
					ips[i] = 255;
				}

				ipInt += (ips[i] << (24 - i * 8));
			}

		return ipInt;
	}
	
	public static void main(String[] args) {
//		System.out.println(convertIpToInt("121.33.201.170"));
		IpAddressConverter iac=new IpAddressConverter();
		System.out.println(iac.getLocationByIp("222.39.112.15"));
		System.out.println(iac.getLocationByIp("60.21.212.15"));
		System.out.println(iac.getLocationByIp("211.158.11.8"));
		System.out.println(iac.getLocationByIp("221.14.243.186"));
		System.out.println(iac.getLocationByIp("221.7.62.98"));
		System.out.println(iac.getLocationByIp("119.39.254.67"));
		System.out.println(iac.getCountryCodeByIp("119.39.254.67"));
		System.out.println(iac.getCountryCodeByIp("119.39.254.67"));
		System.out.println(iac.getCountryCodeByIp("119.39.254.67"));
		System.out.println(iac.getCountryCodeByIp("119.39.254.67"));
	}
	
	public String getCityAddressByIp(String ip) {
		long intIP = convertIpToInt(ip);
		if (intIP == 0) {
			return "0";
		}

		int min = 0;
		int max = ipCityListSize-1;
		int mid = (min + max) / 2;

		Node node = null;
		Node resultNode = null;
		for (; min <= max; mid = (min + max) / 2) {
			node = (Node) ipCityList.get(mid);
			if (node.getFirstNode() <= intIP && intIP <= node.getSecondNode()) {
				resultNode = node;
				break;
			} else if (node.getFirstNode() <= intIP) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}

		if (resultNode == null) {
			return "0";
		} else {
			return resultNode.getValue();
		}
	}

	/**
	 * 根据IP取位置，优先取城市，取不到城市取国家代码
	 * @param ip
	 * @return
	 */
	public String getLocationByIp(String ip) {
		String result = getCityAddressByIp(ip);
		if("0".equals(result)){
			result = getCountryCodeByIp(ip);
		}
		return result;
	}

	public String getCountryCodeByIp(String ip) {
		long intIP = convertIpToInt(ip);
		if (intIP == 0) {
			return "0";
		}

		int min = 0;
		int max = ipCountryListSize-1;
		int mid = (min + max) / 2;

		Node node = null;
		Node resultNode = null;
		for (; min <= max; mid = (min + max) / 2) {
			node = (Node) ipCountryList.get(mid);
			if (node.getFirstNode() <= intIP && intIP <= node.getSecondNode()) {
				resultNode = node;
				break;
			} else if (node.getFirstNode() <= intIP) {
				min = mid + 1;
			} else {
				max = mid - 1;
			}
		}

		if (resultNode == null) {
			return "0";
		} else {
			return resultNode.getValue();
		}
	}

	static class Node {
		private long firstNum;
		private long secondNum;
		private String value;

		public Node(long firstNum, long secondNum, String value) {
			this.firstNum = firstNum;
			this.secondNum = secondNum;
			this.value = value;
		}

		public long getFirstNode() {
			return firstNum;
		}

		public long getSecondNode() {
			return secondNum;
		}

		public String getValue() {
			return value;
		}
	}
}
