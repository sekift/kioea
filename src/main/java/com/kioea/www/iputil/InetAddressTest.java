package com.kioea.www.iputil;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 
 * @author:sekift
 * @time:2017-2-20 上午11:13:11
 * @version:
 */
public class InetAddressTest {
	public static void main(String args[]) {
		// get the network interfaces and associated address for this host
		try {
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			if (interfaceList == null) {
				System.out.println("interfaces is null");
			} else {
				while (interfaceList.hasMoreElements()) {
					NetworkInterface iface = interfaceList.nextElement();
					System.out.println("interface " + iface.getName() + ":");
					Enumeration<InetAddress> addrList = iface.getInetAddresses();
					if (!addrList.hasMoreElements()) {
						System.out.println("No address");
					}
					while (addrList.hasMoreElements()) {
						InetAddress address = addrList.nextElement();
						System.out.println("v4");
						System.out.println(address.getHostAddress());
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
