package com.pilgrimm.core.util;

import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

public class MD5Util {

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };

	/**
	 * @param pwd
	 *            需要加密值
	 * @param salt
	 *            盐值大于0的偶数
	 * @param i
	 *            迭代次数
	 */
	public static String toMd5(String pwd, String salt, int i) {
		Md5Hash toMd5 = new Md5Hash(pwd, salt, i);
		return toMd5.toString();
	}

	/**
	 * @param b
	 */
	private static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; ++i) {
			sb.append(HEX_DIGITS[((b[i] & 0xF0) >>> 4)]);
			sb.append(HEX_DIGITS[(b[i] & 0xF)]);
		}
		return sb.toString();
	}

	/**
	 * @param sourceString
	 */
	public static String bit32(String sourceString) throws Exception {
		return bit32(sourceString, "");
	}

	/**
	 * @param sourceString
	 * @param charsetName
	 */
	public static String bit32(String sourceString, String charsetName) throws Exception {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		if (StringUtils.isBlank(charsetName)) {
			digest.update(sourceString.getBytes());
		} else {
			digest.update(sourceString.getBytes(charsetName));
		}
		byte[] messageDigest = digest.digest();
		return toHexString(messageDigest);
	}

	/**
	 * @param sourceString
	 */
	public static String Bit16(String sourceString) throws Exception {
		return bit32(sourceString).substring(8, 24);
	}
}
