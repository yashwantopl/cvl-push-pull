package com.opl.mudra.api.user.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("rawtypes")
public class CommonUtils {

	public static final String USER_TYPECODE_FUNDSEEKER = "fs";
	public static final String USER_TYPECODE_FUNDPROVIDER = "fp";
	public static final String USER_TYPECODE_SERVICEPROVIDER = "sp";
	public static final String USER_TYPECODE_NETWORKPARTNER = "np";
	public static final String ONE_FORM = "oneForm";
	public static final String DMS_USER_TYPE = "user";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong..!";
	public static final String INVALID_REQUEST = "Invalid Request";
	public static final String SPLITTER = "1";
	public static final String USER_ORG_ID = "userOrgId";
	public static final String FALSE = "false";
	public static final String UTF_8 = "utf-8";
	public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String EXCEPTION = "Exception :: ";
	public static final String DATA_FOUND = "Data Found";
	public static final String NO_DATA_FOUND = "No Data Found ";
	public static final String DATA_FOUND_SUCCESSFULLY = "Data Found Successfully";
	public static final String DATA_LIST_FOUND = "Data List Found.";
	public static final String REQUESTED_DATA_CAN_NOT_BE_EMPTY = "Requested data can not be empty.";
	public static final String REQUESTED_DATA_CAN_NOT_BE_EMPTY_INVALID_REQUEST = "Requested data can not be empty.Invalid Request.";
	public static final String INVALID_DATA_OR_REQUESTED_DATA_CAN_NOT_BE_EMPTY = "Invalid data or Requested data can not be empty.";
	public static final String USERID_MUST_NOT_BE_EMPTY= "UserId must not be Empty";
	public static final String INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND = "Invalid data or Requested data not found.";
	public static final String USER_SERVICE_IS_NOT_AVAILABLE = "User service is not available";
	public static final String COULD_NOT_SAVE_OR_UPDATE_DATA = "Could not save/update data.";
	public static final String COULD_NOT_EXTRACT_EXCEL = "Couldn't extract excel";
	public static final String COULD_NOT_GENERATE_EXCEL = "Couldn't generate excel";
	public static final String COULD_NOT_LIST = "Couldn't list";
	public static final String SUCCESSFULLY_LISTED = "Successfully Listed";
	public static final String SUCCESSFULLY_LIST_DATA = "successfully list data";
	public static final String INACTIVATED_SUCCESSFULLY = "Inactivated Successfully";
	public static final String SUCCESSFULLY_COUNT= "successfully count";
	public static final String SUCCESS = "Success";
	public static final String TOKEN_NOT_FOUND_FOR_THIS_TOKEN = "Token Not Found For This Token ";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	public static final String OTP_SUCCESSFULLY_SENT = "OTP Successfully sent.";


	
	public interface UserRoles {
		public static final Long MAKER = 1l;
		public static final Long CHECKER = 2l;
		public static final Long APPROVER = 3l;
		public static final Long FP_MAKER = 8l;
		public static final Long FP_CHECKER = 9l;
		public static final Long HEAD_OFFICER = 5l;
		public static final Long BRANCH_OFFICER = 6l;
		public static final Long ADMIN_MAKER = 10l;
		public static final Long ADMIN_CHECKER = 11l;
		public static final Long SMECC = 12l;

	}
	
	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
								|| "undefined".equals(value))
						: false));
	}

	public static boolean isObjectListNull(Object... args) {
		for (Object object : args) {
			boolean flag = false;
			if (object instanceof List) {
				flag = isListNullOrEmpty((List) object);
				if (flag)
					return true;
				else
					continue;
			}
			flag = isObjectNullOrEmpty(object);
			if (flag)
				return true;
		}
		return false;
	}

	public static String getLoanCodeFromCode(String code) {
		if (isObjectNullOrEmpty(code)) {
			return code;
		}
		String[] split = code.split(SPLITTER);
		return split[split.length - 1];
	}

	public static String getCampaignCodeFromCode(String code) {
		if (isObjectNullOrEmpty(code)) {
			return code;
		}
		return code.split(SPLITTER)[0];
	}
	
	public static String decode(String encryptedString) {
		return new String(Base64.getDecoder().decode(encryptedString));
	}

	public static byte[][] GenerateKeyAndIV(int keyLength, int ivLength, int iterations, byte[] salt, byte[] password, MessageDigest md) throws DigestException {

		int digestLength = md.getDigestLength();
		int requiredLength = (keyLength + ivLength + digestLength - 1) / digestLength * digestLength;
		byte[] generatedData = new byte[requiredLength];
		int generatedLength = 0;

		md.reset();

		// Repeat process until sufficient data has been generated
		while (generatedLength < keyLength + ivLength) {

			// Digest data (last digest if available, password data, salt if available)
			if (generatedLength > 0)
				md.update(generatedData, generatedLength - digestLength, digestLength);
			md.update(password);
			if (salt != null)
				md.update(salt, 0, 8);
			md.digest(generatedData, generatedLength, digestLength);

			// additional rounds
			for (int i = 1; i < iterations; i++) {
				md.update(generatedData, generatedLength, digestLength);
				md.digest(generatedData, generatedLength, digestLength);
			}

			generatedLength += digestLength;
		}

		// Copy key and IV into separate byte arrays
		byte[][] result = new byte[2][];
		result[0] = Arrays.copyOfRange(generatedData, 0, keyLength);
		if (ivLength > 0)
			result[1] = Arrays.copyOfRange(generatedData, keyLength, keyLength + ivLength);

		return result;
	}

	public static String descryptText(String cipherText) throws DigestException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {

		String secret = "26f1ac75f77c22ebc66e2359c13ea9955ebd5e2bd7fbe50e5b3ac2977a772302";
		String decryptedText = null;
		byte[] cipherData = Base64.getDecoder().decode(cipherText);
		byte[] saltData = Arrays.copyOfRange(cipherData, 8, 16);

		MessageDigest md5 = MessageDigest.getInstance("MD5");
		final byte[][] keyAndIV = CommonUtils.GenerateKeyAndIV(32, 16, 1, saltData, secret.getBytes(StandardCharsets.UTF_8), md5);
		SecretKeySpec key = new SecretKeySpec(keyAndIV[0], "AES");
		IvParameterSpec iv = new IvParameterSpec(keyAndIV[1]);

		byte[] encrypted = Arrays.copyOfRange(cipherData, 16, cipherData.length);
		Cipher aesCBC = Cipher.getInstance("AES/CBC/PKCS5Padding");
		aesCBC.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedData = aesCBC.doFinal(encrypted);
		decryptedText = new String(decryptedData, StandardCharsets.UTF_8);
		return decryptedText;
	}
}
