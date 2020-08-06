package com.opl.mudra.api.cibil_integration.utils;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import java.util.Arrays;

@Convert
public class EncryptionUtils  implements AttributeConverter<String, String>{
	private static final String ALGORITHM = "AES";
	private static final String CHAR_ENCODING = "UTF-8";
	private static final String KEY = "C@p!ta@W0rld#AES";
	
	private static final Logger logger = LoggerFactory.getLogger(EncryptionUtils.class);

	
	
	public String convertToDatabaseColumn(String plainText) {
		// do some encryption
		try {
			if (!CibilUtils.isObjectNullOrEmpty(plainText)) {
				byte[] keyBytes = Arrays.copyOf(KEY.getBytes("ASCII"), 16);

		        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
		        Cipher cipher = Cipher.getInstance(ALGORITHM);
		        cipher.init(Cipher.ENCRYPT_MODE, key);

		        byte[] cleartext = plainText.getBytes(CHAR_ENCODING);
		        byte[] ciphertextBytes = cipher.doFinal(cleartext);

		        return new String(Hex.encodeHex(ciphertextBytes));
			}
		} catch (Exception e) {
			logger.error("error while encrypting data " ,e);
			logger.error("error while encrypting data " + plainText);
		}
		return null;
	}

	public String convertToEntityAttribute(String encryptedText) {
		// do some decryption
		try {
			if (!CibilUtils.isObjectNullOrEmpty(encryptedText)) {
				byte[] keyBytes = Arrays.copyOf(KEY.getBytes("ASCII"), 16);

		        SecretKey key = new SecretKeySpec(keyBytes, ALGORITHM);
		        Cipher cipher = Cipher.getInstance(ALGORITHM);
		        cipher.init(Cipher.DECRYPT_MODE, key);

		        byte[] cleartext = encryptedText.getBytes(CHAR_ENCODING);
		        //byte[] ciphertextBytes = cipher.doFinal(cleartext);

		        return new String(cipher.doFinal(Hex.decodeHex(encryptedText.toCharArray())));
			}
		} catch (Exception e) {
			logger.error("error while decrypting data " ,e);
			logger.error("error while decrypting data " + encryptedText);
		}
		return null;
	}
}
