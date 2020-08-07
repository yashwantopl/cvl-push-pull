package com.opl.mudra.api.mca;

import java.text.DecimalFormat;
import java.text.ParseException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Sanket
 *
 */
public class McaCommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(McaCommonUtils.class);
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";
	
	public static final String REQUEST_NULL = "Request cannot be null";
	
	
	public static final String NOT_C_E = "Application Is not Type C or E";
	
	public static final String SUCCESS_STATUS = "S";
	public static final String FAILURE_STATUS = "F";
	public static final String PENDING_STATUS = "P";
	
	public static final String COMPANY_HISTORY = "getCompanyHistory";
	public static final String SEARCH_COMPANY = "searchCompanies";
	public static final String CHECK_FINANCIAL_FILE = "checkFinancialFile";
	
	public static final String VERIFY_DIN_PAN = "verifyDinPan";
	public static final String LOGIN = "login";
	public static final String DIRECTORS_HISTORY = "getDirectorsDetails";
	public static final String SEARCH_DIRECTOR = "searchDirectors";
	
	public static final String PLACE_ORDER_COMPANY_HISTORY = "getCompanyHistory: Placed Order";
	
	public static final String PLACE_ORDER_NOTIFICATION= "placeOrder";
	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String EXCEPTION ="Exception : ";
	
	public static final String FINANCIAL_FILE_READY ="20";
	public static final String FINANCIAL_FILE_NOT_READY ="10";
	

	public static final class UserType
	{
		private UserType ()
		{
			// nothing to do.
		}
		public static final Long FUND_SEEKER = 1L;
		public static final Long FUND_PROVIDER = 2L;
		public static final Long SERVICE_PROVIDER = 3L;
		public static final Long NETWORK_PARTNER = 4L;
	}
	
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String DATA_NOT_EXIST_IN_DB = "Data Not Exist !";
	public static final String INVALID_FILE_NAME = "Invalid File Name !";
	
	public enum LoanType {
		WC(1,"wc"), TL(2,"tl"), USL(3,"usl");
		private int value;
		private String type;

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		private LoanType(int value, String type) {
			this.value = value;
			this.type = type;
		}

		public int getValue() {
			return value;
		}

		public static LoanType getType(Integer x) {
			switch (x) {
			case 1:
				return WC;
			case 2:
				return TL;
			case 3:
				return USL;

				default:
					break;
			}
			return null;
		}

	}
	static DecimalFormat decimal = new DecimalFormat("#,##0.00");
	public static Number convertStringToLongValue(String value) {
		
		Number a;
		try {
			a = decimal.parse(value);
			return a;
		} catch (ParseException e) {
			logger.error(EXCEPTION, e);
		}
		return 0;
	}
	
	public static Number convertStringToDouble(String value) {
		Number a;
		try {
			a = decimal.parse(value);
			return a;
		} catch (ParseException e) {
			logger.error(EXCEPTION, e);
		}
		return 0.0;
	}
	
	public static String convertDecim(Double value) {
		
		return decimal.format(value);
	}
	
	
	 public static String convertObjectToString(Object value) {
	        ObjectMapper mapper = new ObjectMapper();
	        try {
	            return mapper.writeValueAsString(value);
	        } catch (Exception e) {
				logger.error(EXCEPTION, e);
	            return null;
	        }
	    }
	 
	  public static <T> T convertJSONToObject(String response, Class<T> clazz) {
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	        try {
	            return (T) mapper.readValue(response, clazz);
	        } catch (Exception e) {
	            
	            return null;
	        }
	    }
	  
	//return type of verify api
	public static final class getVerifyApiTypes {
		private getVerifyApiTypes() {
			// Do nothing because of X and Y.
		}

		public static final String VERIFY_WEB_HOOK_API = "VERIFY_WEBHOOK";
	}
	public static final class getVerifyApiStatus {
		private getVerifyApiStatus() {
			// Do nothing because of X and Y.
		}

		public static final String VERIFY = "Verified";
		public static final String UNVERIFY = "Unverified";
	}
	
	public static enum VerifyApiResponseStatus{
		
		FAILED(0,"failur","ERROR_MESSAGE : General Error / Exception"),
		SUCCESS(1,"Success","success"),
		INVALID_REQUEST(4,"Success","Invalid request data");
		
		private Integer statusId;
		private String statusName;
		private String message;

		VerifyApiResponseStatus(Integer statusId, String statusName,String message) {
			this.statusId = statusId;
			this.statusName = statusName;
			this.message=message;
		}
		public Integer getStatusId() {
			return this.statusId;
		}
		public String getStatusName() {
			return this.statusName;
		}
		
		public static VerifyApiResponseStatus getStatusById(Integer id) {
			 for (VerifyApiResponseStatus status : VerifyApiResponseStatus.values()) {
				if(status.statusId==id) {
					return status;
				}
			}
			  throw new IllegalArgumentException("The given Status not found statusId:"+id);
		}
	}
}
