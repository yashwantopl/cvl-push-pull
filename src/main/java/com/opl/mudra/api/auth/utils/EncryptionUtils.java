package com.opl.mudra.api.auth.utils;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Convert
public class EncryptionUtils implements AttributeConverter<String, String> {
	
	private static final String ALGORITHM = "AES";
	private static final String CHAR_ENCODING = "UTF-8";
	private static final String KEY = "C@p!ta@W0rld#AES";
	private static final String SECRET = "26f1ac75f77c22ebc66e2359c13ea9955ebd5e2bd7fbe50e5b3ac2977a772302";

	private static final Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);

	public String convertToDatabaseColumn(String plainText) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(plainText)) {
				byte[] keyBytes = Arrays.copyOf(KEY.getBytes("ASCII"), 16);

				SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, key);

				byte[] cleartext = plainText.getBytes(CHAR_ENCODING);
				byte[] ciphertextBytes = cipher.doFinal(cleartext);

				return new String(Hex.encodeHex(ciphertextBytes));
			}
		} catch (Exception e) {
			logger.error("error while encrypting data : " + plainText, e);
		}
		return null;
	}

	public String convertToEntityAttribute(String encryptedText) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(encryptedText)) {
				byte[] keyBytes = Arrays.copyOf(KEY.getBytes("ASCII"), 16);

				SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, key);

				// byte[] ciphertextBytes = cipher.doFinal(cleartext);

				return new String(cipher.doFinal(Hex.decodeHex(encryptedText.toCharArray())));
			}
		} catch (Exception e) {
			logger.error("error while decrypting data : " + encryptedText, e);
		}
		return null;
	}

	/**
	 * this method for decription with secret key
	 * 
	 * @param encryptedText
	 * @return
	 */
	public String decriptWithKey(String encryptedText) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(encryptedText)) {
				byte[] keyBytes = Arrays.copyOf(SECRET.getBytes("ASCII"), 16);

				SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.DECRYPT_MODE, key);

				// byte[] ciphertextBytes = cipher.doFinal(cleartext);

				return new String(cipher.doFinal(Hex.decodeHex(encryptedText.toCharArray())));
			}
		} catch (Exception e) {
			logger.error("error while decrypting data : " + encryptedText, e);
		}
		return null;
	}

	public String encryptionWithKey(String plainText) {
		try {
			if (!CommonUtils.isObjectNullOrEmpty(plainText)) {
				byte[] keyBytes = Arrays.copyOf(SECRET.getBytes("ASCII"), 16);

				SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
				Cipher cipher = Cipher.getInstance(ALGORITHM);
				cipher.init(Cipher.ENCRYPT_MODE, key);

				byte[] cleartext = plainText.getBytes(CHAR_ENCODING);
				byte[] ciphertextBytes = cipher.doFinal(cleartext);

				return new String(Hex.encodeHex(ciphertextBytes));
			}
		} catch (Exception e) {
			logger.error("error while encrypting data : " + plainText, e);
		}
		return null;
	}
	
}
