package com.opl.mudra.api.user.utils;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.DigestException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opl.mudra.api.user.model.GeneralConfigData;

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
	public static final String INVALID_LIMIT_EXCEL = "INVALID LIMIT";
	public static final String GST__EXCEL = "MultipleGst.xlsx";
	public static final int EMAIL = 1;
	public static final int MOBILE = 2;
	public static final String DATA_NOT_FOUND="Data not found.";
	public static final String[] DEALEREXCEL_HEADERS = {"Sr. No.","Dealer Name*","Name of Dealer Firm/Company","Main Dealer Code*","Dealer Code/No.","Unique Dealer Mail ID*","Unique Dealer Mobile *","Select OEM*",
			"Dealer Constitution","PAN of Dealer Firm / Company","Dealer Office Address*","Dealer Description","Dealer Registration Date","Pincode*","State*","City*","Please Write Following Options only","Code of Branch"};

	private static DecimalFormat decimal = new DecimalFormat("#,##0.00");


	
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
		public static final Long RO = 13l;
		public static final Long ZO = 14l;
		public static final Long MFI_CHECKER = 18l;
		public static final Integer NBFC_FP_MAKER = 308;
		public static final Integer NBFC_FP_CHECKER = 309;
		public static final Integer NBFC_ADMIN_CHECKER = 311;
		public static final Integer NBFC_ADMIN_MAKER = 310;
		public static final Integer NBFC_HO = 305;
		public static final Integer NBFC_ASSISTED_USER = 300;


	}
	
	public static final class UserType {
		private UserType(){
			// Do nothing because of X and Y.
		}
		public static final Long FUND_PROVIDER = 2l;
		public static final Long FUND_SEEKER = 1l;
	}

	public static final class BranchType{
		private BranchType(){

		}
		public static final Integer BO = 1;
		public static final Integer RO = 2;
		public static final Integer ZO = 3;
		public static final Integer HO = 4;
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
	
	public enum BusinessType {
		
		NEW_TO_BUSINESS(2, "New to Business"),
		EXISTING_BUSINESS(1, "Existing Business"),
		RETAIL_PERSONAL_LOAN(3, "Retail Personal Loan"),
		ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS(4, "One Pager Eligibility For Existing Business"),
		RETAIL_HOME_LOAN(5, "Retail Home Loan"),
		RETAIL_AUTO_LOAN(8, "Retail Auto Loan"),
		CO_ORIGINATION(9, "Co Origination"),
		MUDRA_LOAN(10, "Mudra Loan"),
		ODOP_LOAN(22, "Odop Loan"),
		DFS_LOAN(21,"Dealer Financing Scheme");
	
		private Integer id;
		private String value;

		private BusinessType(Integer id) {
			this.id = id;
		}

		private BusinessType(Integer id, String value) {
			this.id = id;
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public Integer getId() {
			return id;
		}
		
		public static BusinessType fromValue(String v) {
			for (BusinessType c : BusinessType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public static BusinessType fromId(Integer v) {
			for (BusinessType c : BusinessType.values()) {
				if (c.id.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v.toString());

		}

	}


public enum LoanType {
	WORKING_CAPITAL(1,"Working Capital","Working Capital"),
	TERM_LOAN(2,"Term Loan","Term Loan"),
	HOME_LOAN(3,"Home Loan","Home Loan"),
	PERSONAL_LOAN(7,"Personal Loan","Personal Loan"),
	AUTO_LOAN(12,"Auto Loan","Auto Loan"),
	LOAN_AGAINST_PROPERTY(13,"Loan Against Property","Loan Against Property"),
	LOAN_AGAINST_SHARES_AND_SECUIRITIES(14,"Loan Against Shares and Secuirities","Loan Against Shares and Secuirities"),
	UNSECURED_LOAN(15,"Unsecured Loan","Unsecured Loan"),
	WCTL_LOAN(16,"Working Capital Term Loan","Working Capital Term Loan"),
	MFI_LOAN(17,"Micro Finance Institute Loan","Micro Finance Institute Loan"),
	AGRI_LOAN(18,"Agriculture Loan","Agriculture Loan");
	private final Integer id;
	private final String value;
	private final String description;
	LoanType(Integer id, String value, String description) {
	this.id = id;
	this.value = value;
	this.description = description;
}

public Integer getId() {
	return id;
}

public String getValue() {
	return value;
}

public String getDescription() {
	return description;
}
	public static LoanType getById(Integer id) {
	switch (id) {
	case 1:
		return WORKING_CAPITAL;
	case 2:
		return TERM_LOAN;
	case 3:
		return HOME_LOAN;
	case 7:
		return PERSONAL_LOAN;
	case 12:
		return AUTO_LOAN;
	case 13:
		return LOAN_AGAINST_PROPERTY;
	case 14:
		return LOAN_AGAINST_SHARES_AND_SECUIRITIES;
	case 15:
		return UNSECURED_LOAN;
	case 16:
		return WCTL_LOAN;
	case 17:
		return MFI_LOAN;
	default:
		return null;
	}
}
	public static LoanType[] getAll() {
		return LoanType.values();

	}
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
	
	/**
	 * @author nilay.darji
	 * @return Current Time Stamp
	 */
	public static Timestamp getCurrentTimeStamp(){
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * @author vijay.chauhan
	 * @return Return true if input string is numeric otherwise return false. 
	 */
	public static boolean isNumeric(String strNum) {
	    if (isObjectNullOrEmpty(strNum)) {
	        return false;
	    }
	    try {
	        Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static GeneralConfigData convertJSONToGeneralConfigDataRespo(String response) {
		 ObjectMapper mapper = new ObjectMapper();
		 try {
			 return mapper.readValue(response, GeneralConfigData.class);
		 } catch (Exception e) {
			 return null;
		 }
	}
	
	public static Object convertToDoubleForXml(Object obj, Map<String, Object> data) throws Exception {
		try {
			if (obj == null) {
				return null;
			}
			DecimalFormat decim = new DecimalFormat("0.00");
			if (obj instanceof Double) {
				obj = Double.parseDouble(decim.format(obj));
				return obj;
			} else if (obj.getClass().getName().startsWith("com.capitaworld")) {
				Field[] fields = obj.getClass().getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (data != null) {
						data.put(field.getName(), value);
					}
					if (!CommonUtils.isObjectNullOrEmpty(value) && value instanceof Double
							&& !Double.isNaN((Double) value)) {
						value = Double.parseDouble(decim.format(value));
						if (data != null) {
							value = decimal.format(value);
							data.put(field.getName(), value);
						} else {
							field.set(obj, value);
						}
					}
				}
			}
			if (data != null) {
				return data;
			}
			return obj;
		} catch (Exception e) {
			throw new Exception(e);
		}

	}

}
