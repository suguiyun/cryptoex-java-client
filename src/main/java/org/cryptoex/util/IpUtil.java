package org.cryptoex.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * IpUtil to get IP address.
 * 
 * @author cryptoex
 */
public class IpUtil {

	static final Logger log = LoggerFactory.getLogger(IpUtil.class);

	static final String IP = getIpAddress();

	/**
	 * Get localhost's IP address as string.
	 * 
	 * @return IP address like "10.0.1.127"
	 */
	public static String getIpAddress() {
		String ip = _getIpAddress();
		return ip;
	}

	static String _getIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.warn("Get IP address by InetAddress.getLocalHost() failed: {}", e.getMessage());
		}
		try {
			Enumeration<NetworkInterface> iterInterface = NetworkInterface.getNetworkInterfaces();
			while (iterInterface.hasMoreElements()) {
				NetworkInterface network = iterInterface.nextElement();
				if (network.isLoopback() || !network.isUp()) {
					continue;
				}
				Enumeration<InetAddress> itAddr = network.getInetAddresses();
				while (itAddr.hasMoreElements()) {
					return itAddr.nextElement().getHostAddress();
				}
			}
		} catch (Exception e) {
			log.warn("Get IP address by NetworkInterface.getNetworkInterfaces() failed: {}", e.getMessage());
		}
		throw new RuntimeException("Failed to get IP address.");
	}

	/**
	 * Get localhost's IP address as int array.
	 * 
	 * @return IP address like int[] {10, 0, 1, 127}.
	 */
	public static int[] getIpAddressAsArray() {
		return ipAddrToIntArray(getIpAddress());
	}

	/**
	 * Convert IP address to int array.
	 * 
	 * @param ipAddr
	 *            String IP address.
	 * @return int[] array.
	 */
	public static int[] ipAddrToIntArray(String ipAddr) {
		String[] arr = ipAddr.split("\\.");
		if (arr.length != 4) {
			throw new IllegalArgumentException("Bad IP address: " + ipAddr);
		}
		int[] results = new int[4];
		for (int i = 0; i < 4; i++) {
			int n = Integer.parseInt(arr[i]);
			if (n < 0 || n > 255) {
				throw new IllegalArgumentException("Bad IP address: " + ipAddr);
			}
			results[i] = n;
		}
		return results;
	}

	/**
	 * Convert IP address to int value.
	 * 
	 * @param ipAddr
	 *            String like "10.0.1.127".
	 * @return Int value like 0x0a00017f.
	 */
	public static int ipAddrToInt(String ipAddr) {
		int[] results = ipAddrToIntArray(ipAddr);
		return (results[0] << 24) | (results[1] << 16) | (results[2] << 8) | results[3];
	}

	/**
	 * Convert IP address from int to String.
	 * 
	 * @param ip
	 *            int value like 0x0a00017f
	 * @return String like "10.0.1.127".
	 */
	public static String intAddrToString(int ip) {
		return ((ip & 0xff000000) >>> 24) + "." + ((ip & 0x00ff0000) >>> 16) + "." + ((ip & 0xff00) >>> 8) + "."
				+ (ip & 0xff);
	}

	public static String getIPRestriction(int netAddress, int netmask) {
		if (netAddress == 0) {
			return "*";
		}
		if (netmask == 0xffffffff) {
			return IpUtil.intAddrToString(netAddress);
		}
		int mask = netmask;
		int n = 0;
		while (mask != 0) {
			n++;
			mask = mask << 1;
		}
		return IpUtil.intAddrToString(netAddress) + "/" + n;
	}

	/**
	 * Parse ip restriction and return net-address and net-mask.
	 * 
	 * @param ipRestriction
	 * @return a 2-int array represent net-address and net-mask.
	 */
	public static int[] parseIpRestriction(String ipRestriction) {
		if (ipRestriction != null && !ipRestriction.isEmpty() && !ipRestriction.equals("*")) {
			int pos = ipRestriction.indexOf('/');
			if (pos == -1) {
				// only ip address:
				return new int[] { ipAddrToInt(ipRestriction), 0xffffffff };
			}
			// ip address and netmask:
			int n = Integer.parseInt(ipRestriction.substring(pos + 1));
			if (n < 8 || n >= 32) {
				throw new IllegalArgumentException("Invalid subnet range: " + n);
			}
			int netmask = 0b10000000_00000000_00000000_00000000 >> (n - 1);
			int netAddress = netmask & ipAddrToInt(ipRestriction.substring(0, pos));
			return new int[] { netAddress, netmask };
		}
		// no restriction:
		return new int[] { 0, 0 };
	}
}
