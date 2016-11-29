package com.hgsoft.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MobileMD5 {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * SHA1
	 * @param val
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String getMD5(String val) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(val.getBytes());
			byte[] m = md5.digest();// 加密
			return getString(m);
		}catch (NoSuchAlgorithmException e){
			return null;
		}
	}

	/**
	 * 正常MD5加密
	 * @param str
	 * @return
	 */
	public static String encodeByMD5(String str) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

	}
	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

	private static String getString(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			sb.append(b[i]);
		}
		return sb.toString();
	}

}
