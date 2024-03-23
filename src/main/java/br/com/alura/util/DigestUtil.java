package br.com.alura.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class DigestUtil {

	public static String convertPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] passwordBytes = messageDigest.digest(password.getBytes());
		StringBuilder hexString = new StringBuilder();
		for (byte passwordByte : passwordBytes) {
			String hex = Integer.toHexString(0xff & passwordByte);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
