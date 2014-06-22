package org.jboss.optaplanner.controller.login;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class hash password
 * @author martin
 *
 */
public class ShaEncoder {

	public static String hash(String string) {
		try {
			MessageDigest dig = MessageDigest.getInstance("SHA-256");
			byte[] Hash = dig.digest(string.getBytes("UTF-8"));

			StringBuffer sbr = new StringBuffer();
			for (int i = 0; i < Hash.length; i++) {
				sbr.append(Integer.toString((Hash[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sbr.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}
}
