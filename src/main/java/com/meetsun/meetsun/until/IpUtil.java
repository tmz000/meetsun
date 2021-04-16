package com.meetsun.meetsun.until;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtil {
	
	public static String getIpAddr() {
		try {
			InetAddress ip4 = Inet4Address.getLocalHost();
			return ip4.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;	
	}
}
