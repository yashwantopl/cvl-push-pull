/**
 *
 */
package com.opl.mudra.api.gst.model.util;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.opl.mudra.api.gst.exception.GstException;
import com.opl.mudra.api.gst.model.Gstr4CalculationResponse;
import com.opl.mudra.api.gst.model.Gstr4PopUpResponse;
import com.opl.mudra.api.gst.model.Gstr4QoqSales;
import com.opl.mudra.api.gst.model.karza.CptySum;
import com.opl.mudra.api.gst.model.karza.CptySumGstr2a;
import com.opl.mudra.api.gst.model.karza.Details;
import com.opl.mudra.api.gst.model.karza.DetailsGstr2a;
import com.opl.mudra.api.gst.model.karza.Gstr3Details;
import com.opl.mudra.api.gst.model.karza.KarzaGstResponse;
import com.opl.mudra.api.gst.model.karza.SecSum;
import com.opl.mudra.api.gst.model.karza.SecSumGstr2a;
import com.opl.mudra.api.gst.model.karza.UinDetails;
import com.opl.mudra.api.gst.model.karza.UnregDetails;
import com.opl.mudra.api.gst.model.model.DataMapping;
import com.opl.mudra.api.gst.model.model.DataMappingProjectedSales;
import com.opl.mudra.api.gst.model.model.GstCalcMappingTable;
import com.opl.mudra.api.gst.model.model.ProjectedSalesCalcResponse;
import com.opl.mudra.api.gst.model.model.SummOfHsn;
import com.opl.mudra.api.gst.model.yuva.request.GSTR1Request;
import com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices.Cdnr;
import com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices.GSTR1CDNRInvoicesResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr1cdnurinvoices.Cdnur;
import com.opl.mudra.api.gst.model.yuva.response.gstr1cdnurinvoices.GSTR1CDNURInvoicesResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr1hsnsummary.Data;
import com.opl.mudra.api.gst.model.yuva.response.gstr1hsnsummary.Gstr1HsnSummaryResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.Gstr1SummaryResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b.B2b;
import com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b.Gstr2AB2b;
import com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b.Inv;
import com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b.Itms;
import com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Cdn;
import com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Gstr2ACdn;
import com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Nt;
import com.opl.mudra.api.gst.model.yuva.response.gstr2hsnsummary.Det;
import com.opl.mudra.api.gst.model.yuva.response.gstr2hsnsummary.Gstr2HsnSummaryResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr2summary.GSTR2SummaryResponse;
import com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.GSTR3BSummary;
import com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.PdCash;
import com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.TxPmt;
import com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.TxPy;

/**
 * @author sanket
 */
@SuppressWarnings("unchecked")
public class CommonUtils {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String USER_ORG_ID = "userOrgId";

	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "GST Service Not available, Please try again after sometime!!";
	public static final String ERROR_MESSAGE = "Something Went Wrong";
	public static final String TWELVE_MONTH_DATA_ZERO = "It seems your total sales of last 12 months is 0, request you to check sales figure entered and provide correct details as the same has impact on your eligibility calculation.";
//    public static final String SOMETHING_WENT_WRONG = "Our GST gateway is under upgradation, meanwhile get api access activated on GST website. We shall intimate (SMS/Email) you once gateway is on. This is for your better experience. Thank You!";

	public static final String COMPOSITESCHEME = "Its Composite Scheme";

	public static final String GSTR4_ZERO_RETURN_FILE_MSG = "We understand from your GST returns that the same have been filed with '0' (Zero Value). In case you have any reservations, kindly email to us at support@psbloansin59minutes.com.";

	public static final String PROJECTED_SALES_0 = "The system has not received sales values from the GST portal, please check again after some time.";

	public static final Long COMPOSITE_CODE = 907L;

	public static final Long PROJECTED_SALES_0_CODE = 906L;

	public static final String SUCCESS_MESSAGE = "Your details are successfully retrieved";

	public static final String REQUEST_NULL = "Request Should Not be Null";

	public static final String MISSING_PARAMETER = "Parameter Missing for the request :";

	public static final String UNAUTHORIZED_USER = "UNAUTHORIZED_USER";

	public static final String API_ACCESS_DENIED = "API access Denied Please Allow Access";

	public static final String RESEND_OTP = "OTP Resent Please Enter In the given Space";
	public static final String SEND_OTP = "An OTP is sent to your mobile number and email registered with GST. Please enter OTP.";

	public static final String TP_CONSTITUTION = "This Constitution ";
	public static final String TP_CONSTITUTION1 = "cannot be processed for now. Please contact System Administrator!";

	public static final String SYSTEM_FAILURE = "System Failure";

	public static final String ALREADY_USED = "GSTIN Already used";
	public static final String ALREADY_USED_MULTI_GST = "Given GST detail submitted try another GST";

	public static final String USER_NOT_VALID = "User Not Valid";

	public static final String ERROR_102 = "Invalid ID number or combination of inputs";
	public static final String ERROR_103 = "No records found for the given ID or combination of inputs";
	public static final String ERROR_104 = "Max retries exceeded";
	public static final String ERROR_105 = "Missing Consent";
	public static final String ERROR_107 = "GST Not Supported";
	public static final String ERROR_108 = "GSTR3B Data Not found.";
	public static final String INVALID_SESSION = "Invalid Session";
	public static final String INVALID_OTP = "Invalid OTP";
	public static final String INVALID_APPKEY = "Invalid Appkey";
	public static final String SESSION_EXPIRED = "Session Expired";
	public static final String USER_AUTHORIZED = "User Authorized";

	public static final String KARZA_INTERNAL_SERVER = "GST Server Down. Please try again!";
	public static final String KARZA_SOURCE_UNAVAIABLE = "GST Source Unavailable. Please try again or try Login using OTP.";

	public static final String KARZA_END_POINT = "GST Server EndPoint Request Timed Out";

	public static final String ELIGIBILITY = "Your are not eligible for the loan";

	public static final String RESEND_OTP_FOR_NEW_LOGIN = "Session Timed Out, Please ";

	public static final String DATA_EMPTY_MESSAGE = "Please login GSTN website and check whether following details are Updated or Filled  : Organsation Name, Constitution and Pan No.";
	public static final String TTL_VAL = "ttl_val";
	public static final String IAMT = "iamt";
	public static final String SAMT = "samt";
	public static final String CAMT = "camt";
	public static final String CSAMT = "csamt";
	public static final String RET_PERIOD = "ret_period";
	public static final String PD_CASH = "pd_cash";
	public static final String EXCEPTION = " :: Exception while calling :: ";

	public static final String USERID_NOT_EMPTY = "userId can not be empty ==>";
	public static final String GET_KARZA_GST_DATA_ENDED = "getKarzaGstData ended";
	public static final String GET_ALL_GST_DATA_BY_GSTIN_STARTED = "getAllGstDataByGstin started";

	public static final String ERROR_LABEL = " Error :";
	public static final String USER_ID_LABEL = " User Id";

	public static final String GST_SERVICE_ERROR = "GST Service Error";
	public static final String GST_SERVICE_IS_NOT_AVAILABLE = "GST Service is not available";
	public static final String REQ_AUTH = "req_auth";

	public static final String SOME_ERROR = "Some Error occur while saving your details! Please try to save again.";

	public static final String COMPOSITE_SCHEME_FLAG = "Its Composite Scheme";

	public static final Long IS_GROWTH_NEGATIVE = 909L;
	public static final String GROWTH_NEGATIVE_MESSAGE = "Growth is Negative";
	private static final String TXVAL = "txval";
	private static final String TAX_PAYBLE = "tax_payble";
	private static final String LATE_FEE = "late_fee";
	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	public static final Long MONTHS_MISSING_DATA_FROM_GST = 910L;
	public static final Integer DUPLICATE_GST_DETAILS = 911;
	public static final Long IS_GST_NOT_APPLICABLE = 912L;
	public static final Long FIND_BY_PAN_REGULAR_GST = 913L;
	public static final Long FIND_BY_PAN_GST_NOT_FOUND = 914L;
	public static final Long FIND_BY_PAN_COMPOSITION_GST = 915L;
	public static final String FIND_BY_PAN_REGULAR_GST_MSG = "Regular Active GSTIN found!!";
	public static final String FIND_BY_PAN_GST_NOT_FOUND_MSG = "No data found!!";
	public static final String FIND_BY_PAN_COMPOSITION_GST_MSG = "Composition GSTIN found!!";
	public static final Long GST_SPECIFIC_USER_ID = -1L;
	public static final Long GST_DETAILS_RETRIEVED_BY_PAN = 916L;
	public static final String GST_DETAILS_RETRIEVED_BY_PAN_MSG = "Your all GSTIN details are successfully retrieved";
	public static final Long GST_DETAILS_NOT_RETRIEVED_BY_PAN = 917L;
	public static final String GST_DETAILS_NOT_RETRIEVED_BY_PAN_MSG = "Your GSTIN details are not retrieved.";
	public static final Long GSTR4_SUMMARY_DATA_RETURN = 918l;
	public static final String GSTR4_DATA_CALCULATION = "Gstr4Calculation";
	public static final String GSTR4_CALL_FOR_GSTR4_POPUP_DATA = "Gstr4PopupData";

	public static final Long CLEAR = 200L;
	
	public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	public static int MONTHLIST_FOR_SALES_CUTOFF_DAYS;

	public static final class ServiceStatus {

		private ServiceStatus() {
			// Do nothing because of X and Y.
		}

		public static final String SEND_OTP = "601";
		public static final String RESEND_OTP = "602";
		public static final String AUTH_TOKEN_REFRESH = "603";
		public static final String ACCESS_DENIED = "604";
		public static final String SYSTEM_FAILURE = "605";
		public static final String ALREADY_USED = "606";

		public static final String USER_NOT_VALID = "RET11409";
		public static final String UNAUTHORIZEDUSER = "RET11402";
		public static final String INVALIDSESSION = "AUTH4033";
		public static final String INVALID_OTP = "AUTH4034";
		public static final String INVALID_APPKEY = "AUTH4036";
		public static final String API_ACCESS_DENIED = "AUTH4037";
		public static final String SESSION_EXPIRED = "AUTH4038";

		public static final String USER_AUTHORIZED = "1";

		public static final Integer KARZA_503 = 503;
		public static final Integer KARZA_504 = 504;
		public static final Integer KARZA_501 = 501;

		public static final Integer KARZA_402 = 402;
		public static final Integer KARZA_401 = 401;
		public static final Integer KARZA_400 = 400;

		public static final Integer KARZA_107 = 107;
		public static final String CONSTITUTION_MISMATCH = "607";
		public static final String DATA_EMPTY = "608";
	}

	public enum GSTDataType {
		ImCTIN, AllCTIN, PAN

	}

	public static final List<String> URLS_BRFORE_LOGIN = new ArrayList<String>(8);

	static {
		URLS_BRFORE_LOGIN.add("/gst/error".toLowerCase());
	}

	public static <T> T convertJSONToObject(String response, Class<T> clazz) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		try {
			return mapper.readValue(response, clazz);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return null;
		}
	}

	public static Object convertValueIndianCurrency(Object value) {
		if (value != null) {
			NumberFormat formatter = NumberFormat.getNumberInstance(new Locale("en", "IN"));
			formatter.setMinimumFractionDigits(0);
			formatter.setMaximumFractionDigits(0);
			return formatter.format(value);
		} else {
			return 0;
		}

	}

	public static String convertObjectToString(Object value) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return null;
		}
	}

	public static String getStringfromListOfObject(List<?> list) throws IOException {
		if (!isListNullOrEmpty(list)) {
			final StringWriter sw = new StringWriter();
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
			objectMapper.writeValue(sw, list);
			return sw.toString();
		} else {
			return "[]";
		}
	}

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static final class UserType {

		private UserType() {
			// Do Nothing because of X and Y.
		}

		public static final Long FUND_SEEKER = 1L;
		public static final Long FUND_PROVIDER = 2L;
		public static final Long SERVICE_PROVIDER = 3L;
		public static final Long NETWORK_PARTNER = 4L;
	}

	public static final class Service {

		private Service() {
			// Do Nothing.
		}

		public static final String OTPREQUEST = "OTPREQUEST";
		public static final String AUTHTOKEN = "AUTHTOKEN";
		public static final String RETSUM = "RETSUM";
		public static final String REFAUTHTOKEN = "REFAUTHTOKEN";
		public static final String KARZA_TRRN = "karza_trrn";
		public static final String GSTR2B2B = "B2B";
		public static final String GSTR2CDN = "CDN";
		public static final String PUBLIC_TP = "TP";
		public static final String GSTR1CDNR = "CDNR";
		public static final String GSTR1CDNUR = "CDNUR";
		public static final String PUBLIC_TP_BY_PAN = "TP_BY_PAN";
		public static final String RETSUM_GSTR4 = "RETSUM_GSTR4";
		public static final String GSTR4_CDNR = "GSTR4_CDNR";
		public static final String GSTR4_CDNUR = "GSTR4_CDNUR";
		public static final String HSNSUM = "HSNSUM";
	}

	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String getCurrentTimeStampV() {

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(date) + "+0530";
	}

	public static final class Status {

		private Status() {
			// Do Nothing.
		}

		public static final String SUCCESS_STATUS = "S";
		public static final String FAILURE_STATUS = "F";
		public static final String PENDING_STATUS = "P";
	}

	public static final class GstType {

		private GstType() {
			// Do Nothing.
		}

		public static final Integer REGULAR = 1;
		public static final Integer COMPOSITE = 2;
		public static final Integer GST_NOT_APPLICABLE = 3;
	}

	public static final class DTY {

		private DTY() {
			// Do nothing.
		}

		public static final String REGULAR = "Regular";
		public static final String COMPOSITION = "Composition";
	}

	public static final class RequestType {

		private RequestType() {
			// Do Nothing.
		}

		public static final String GSTR1_SUMMARY = "GSTR1_SUMMARY";
		public static final String GSTR2_SUMMARY = "GSTR2_SUMMARY";
		public static final String GSTR4_SUMMARY = "GSTR4_SUMMARY";
		public static final String GSTR2A_B2B = "GSTR2A_B2B";
		public static final String GSTR2A_CDN = "GSTR2A_CDN";
		public static final String GSTR3_SUMMARY = "GSTR3_SUMMARY";
		public static final String TP = "TP";
		public static final String KARZA_TRRN = "KARZA_TRRN";
		public static final String GSTR1_CDNR = "GSTR1_CDNR";
		public static final String GSTR1_CDNUR = "GSTR1_CDNUR";
		public static final String GSTR2_CDN = "GSTR2_CDN";
		public static final String GSTR2_CDNUR = "GSTR2_CDNUR";
		public static final String TP_BY_PAN = "TP_BY_PAN";
		public static final String GSTR4_CDNR = "GSTR4_CDNR";
		public static final String GSTR4_CDNUR = "GSTR4_CDNUR";
		public static final String GSTR1_HSN_SUMMARY = "GSTR1_HSN_SUMMARY";
		public static final String GSTR2_HSN_SUMMARY = "GSTR2_HSN_SUMMARY";
	}

	public static String getUniqueTransactionId() {
		Random random = new Random();
		int length = 10;
		StringBuilder captchaStringBuffer = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int captchaNumber = Math.abs(random.nextInt()) % 60;
			int charNumber = 0;
			if (captchaNumber < 26) {
				charNumber = 65 + captchaNumber;
			} else if (captchaNumber < 52) {
				charNumber = 97 + (captchaNumber - 26);
			} else {
				charNumber = 48 + (captchaNumber - 52);
			}
			captchaStringBuffer.append((char) charNumber);
		}
		return captchaStringBuffer.toString().toUpperCase();
	}

	public static List<String> returnPeriodList() {
		List<String> retPeriod = new ArrayList<String>();
		String gstDate = "01/07/2017";
		SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
		Date date = null;
		try {
			date = format.parse(gstDate);
		} catch (ParseException e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		Calendar gstCal = Calendar.getInstance();
		gstCal.add(Calendar.MONTH, -1);
		gstCal.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.MONTH);
		Boolean flag = true;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day <= CommonUtils.MONTHLIST_FOR_SALES_CUTOFF_DAYS) {
			cal.add(Calendar.MONTH, -1);
		}
		while (flag) {
			cal.add(Calendar.MONTH, -1);
			String retPer = (String.format("%02d", cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR);
			if (cal.after(gstCal)) {
				retPeriod.add(retPer);
			} else {
				flag = false;
			}
		}

		retPeriod.sort(new DateComparator2());
		return retPeriod;
	}

	public static List<String> returnPeriodListForPurchaseCalc() {
		List<String> retPeriod = new ArrayList<String>();
		String gstDate = "01/07/2017";
		SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
		Date date = null;
		try {
			date = format.parse(gstDate);
		} catch (ParseException e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		Calendar gstCal = Calendar.getInstance();
		gstCal.add(Calendar.MONTH, -1);
		gstCal.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.MONTH);
		Boolean flag = true;
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day <= CommonUtils.MONTHLIST_FOR_SALES_CUTOFF_DAYS) {
			cal.add(Calendar.MONTH, -1);
		}
		while (flag) {
			cal.add(Calendar.MONTH, -1);
			String retPer = (String.format("%02d", cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR);
			if (cal.after(gstCal)) {
				retPeriod.add(retPer);
			} else {
				flag = false;
			}
		}

		retPeriod.sort(new DateComparator2());
		Collections.reverse(retPeriod);

		return retPeriod.stream().limit(24l).collect(Collectors.toList());
	}

	public static List<String> returnPeriodListForComposition() {
		List<String> retPeriod = new ArrayList<String>();
		String gstDate = "01/07/2017";
		SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
		Date date = null;
		try {
			date = format.parse(gstDate);
		} catch (ParseException e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}
		Calendar gstCal = Calendar.getInstance();
		gstCal.add(Calendar.MONTH, -1);
		gstCal.setTime(date);
		Calendar cal = Calendar.getInstance();
		cal.get(Calendar.MONTH);
		Boolean flag = true;
//        retPeriod.add((String.format("%02d", cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR));
		while (flag) {
			cal.add(Calendar.MONTH, -1);
			if (Integer.valueOf(String.format("%02d", cal.get(Calendar.MONTH) + 1)) % 3 == 0) {
				String retPer = (String.format("%02d", cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR);
				if (cal.after(gstCal)) {
					retPeriod.add(retPer);
				} else {
					flag = false;
				}
			}
		}
		retPeriod.sort(new DateComparator2());
		return retPeriod;
	}

	private static List<String> urlsBrforeLogin = null;

	public static List<String> getUrlsBrforeLogin() {
		return urlsBrforeLogin;
	}

	static {
		urlsBrforeLogin = new ArrayList<String>(3);
		urlsBrforeLogin.add("/loans/loan_application/getUsersRegisteredLoanDetails");
		urlsBrforeLogin.add("/loans/loan_application/getLoanDetailsForAdminPanel");
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim()) || "null".equals(value)
								|| "undefined".equals(value))
						: false));
	}

	public static String firstTwo(String str) {
		return str.length() < 2 ? str : str.substring(0, 2);
	}

	public static Integer firstTwoInt(String strNumber) {
		return strNumber.length() < 2 ? Integer.valueOf(strNumber) : Integer.valueOf(strNumber.substring(0, 2));
	}

	public static final class ServiceProvider {

		private ServiceProvider() {
			// Do Nothing.
		}

		public static final Long GST = 1L;
		public static final Long KARZAGST = 2L;
		public static final Long VAYANAGST = 3L;
	}

	public static Map<String, Double> generation(Gstr1SummaryResponse[] resp) {
		Map<String, Double> map = new HashMap<String, Double>();

		for (Gstr1SummaryResponse response : resp) {
			if (response.getSecSum() != null) {
				for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : response.getSecSum()) {
					if (secSum.getCptySum() != null && "B2B".equals(secSum.getSecNm())) {
						for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.CptySum cptySum : secSum
								.getCptySum()) {

							if (map.containsKey(cptySum.getCtin())) {
								map.put(cptySum.getCtin(), map.get(cptySum.getCtin()) + cptySum.getTtlTax());

							} else {
								map.put(cptySum.getCtin(), cptySum.getTtlTax());
							}
						}
					}
				}
			}
		}

		return map;

	}

	public static Double getMax(Map<String, Double> map) {
		try {
			Map.Entry<String, Double> max = map
					// from all entries
					.entrySet()
					// stream them
					.stream()
					// max, obviously
					.max(
							// this one is cool. It generates
							// Map.Entry comparators by delegating to another
							// comparator, exists also for keys
							Map.Entry.comparingByValue(Double::compareTo))
					// Get the optional (optional because the map can be empty)
					.get();

			return max.getValue();
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static Map.Entry<String, Double> getMaxForCalc(Map<String, Double> map) {
		try {
			Map.Entry<String, Double> max = map
					// from all entries
					.entrySet()
					// stream them
					.stream()
					// max, obviously
					.max(
							// this one is cool. It generates
							// Map.Entry comparators by delegating to another
							// comparator, exists also for keys
							Map.Entry.comparingByValue(Double::compareTo))
					// Get the optional (optional because the map can be empty)
					.get();

			return max;
		} catch (Exception e) {
			return null;
		}
	}

	public static Double getTotalUniqueCustomer(Map<String, Double> map) {
		if (map != null) {
			return Double.valueOf(map.size());
		} else {
			return 0.0;
		}
	}

	public static Double getConcentration(Double maxValue, Double total) {
		if (total != 0) {
			return (maxValue / total) * 100.0;
		} else {
			return 100.00;
		}

	}

	public static Double getNoOfReturnPeriod(GSTR3BSummary[] resp) throws GstException {
		try {
			Double noOfReturnPeriod = 0.0;
			for (GSTR3BSummary response : resp) {
				if (response.getStatusCd() == null || response.getStatusCd().equals("1")) {
					noOfReturnPeriod = noOfReturnPeriod + 1.0;
				}
			}

			return noOfReturnPeriod;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static Double getNoOfReturnPeriod(Gstr1SummaryResponse[] resp) throws GstException {
		try {
			Double noOfReturnPeriod = 0.0;
			for (Gstr1SummaryResponse response : resp) {
				if (response.getStatusCd() == null || response.getStatusCd().equals("1")) {
					noOfReturnPeriod = noOfReturnPeriod + 1.0;
				}
			}

			return noOfReturnPeriod;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static Double totalSum(Map<String, Double> map) {
		try {
			return map.values().stream().mapToDouble(Number::doubleValue).sum();
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static String getPanNum(String gstin) {
		return gstin.substring(2, 12);

	}

	// karza

	public static Map<String, Double> generationFromKarzaData(KarzaGstResponse[] resp) {
		Map<String, Double> map = new HashMap<String, Double>();
		KarzaGstResponse response = resp[resp.length - 1];
		if (response.getResult() != null) {
			if (response.getResult().getCurrent().getGstr1() != null
					&& (response.getResult().getCurrent().getGstr1().getDetails() != null)
					|| (response.getResult().getCurrent().getGstr1().getDetails().length > 0)) {
				for (Details details : response.getResult().getCurrent().getGstr1().getDetails()) {
					if (details.getSecSum() != null) {
						for (SecSum secSum : details.getSecSum()) {
							if (secSum.getCptySum() != null) {
								for (CptySum cptySum : secSum.getCptySum()) {
									if (map.containsKey(cptySum.getCtin())) {
										map.put(cptySum.getCtin(), map.get(cptySum.getCtin()) + cptySum.getTtlTax());

									} else {
										map.put(cptySum.getCtin(), cptySum.getTtlTax());
									}
								}
							}
						}
					}
				}
			}
			if ((response.getResult().getPrevious().getGstr1() != null)
					&& (response.getResult().getPrevious().getGstr1().getDetails() != null)
					|| (response.getResult().getPrevious().getGstr1().getDetails().length > 0)) {
				for (Details details : response.getResult().getPrevious().getGstr1().getDetails()) {
					if (details.getSecSum() != null) {
						for (SecSum secSum : details.getSecSum()) {
							if (secSum.getCptySum() != null) {
								for (CptySum cptySum : secSum.getCptySum()) {
									if (map.containsKey(cptySum.getCtin())) {
										map.put(cptySum.getCtin(), map.get(cptySum.getCtin()) + cptySum.getTtlTax());

									} else {
										map.put(cptySum.getCtin(), cptySum.getTtlTax());
									}
								}
							}
						}
					}
				}
			}
		}

		return map;

	}

	public static Double getNoOfReturnPeriodForKarza(KarzaGstResponse[] resp) throws GstException {
		try {
			Double noOfReturnPeriod = 0.0;
			KarzaGstResponse response = resp[resp.length - 1];
			if ((response.getResult() != null) && (response.getResult().getCurrent().getGstr3b() != null)
					&& (response.getResult().getPrevious().getGstr3b() != null)
					&& (response.getResult().getCurrent().getGstr3b().getGstr3details() != null)
					&& (response.getResult().getCurrent().getGstr3b().getGstr3details().length > 0)
					&& (response.getResult().getPrevious().getGstr3b().getGstr3details() != null)
					&& (response.getResult().getPrevious().getGstr3b().getGstr3details().length > 0)) {

				noOfReturnPeriod = Double
						.valueOf(String.valueOf(response.getResult().getCurrent().getGstr3b().getGstr3details().length
								+ response.getResult().getPrevious().getGstr3b().getGstr3details().length));

			}

			return noOfReturnPeriod;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public enum Step {

		REQUEST_OTP, AUTH_TOKEN_FROM_OTP, GSTR_1_SUMMARY, KARZA_GST;
	}

	public static Double getHistoricSalesCalulationFromGSTR3B(DataMapping dataMapping) {
		try {
			Double previousYrsRemainTotal = 0.0;
			int countCY = dataMapping.getCurrentYears().size();
			Double currentYearsTotal = dataMapping.getCurrentYears().stream()
					.mapToDouble(DataMappingProjectedSales::getValues).sum();
			if (countCY < 12) {
				int remainingMnths = 12 - countCY;
				previousYrsRemainTotal = dataMapping.getPreviousYears().stream()
						.sorted(new DateComparatorDataMappDescTime()).limit(remainingMnths)
						.collect(Collectors.summingDouble(DataMappingProjectedSales::getValues));
			}
			Double totalHistoricSales = currentYearsTotal + previousYrsRemainTotal;
			return totalHistoricSales;
		} catch (Exception e) {
			return null;
		}
	}

	public static Double getTotalSalesCalulationFromGSTR3B(DataMapping dataMapping) {
		try {
			Double previousYrsRemainTotal = 0.0;
			int countCY = dataMapping.getCurrentYears().size();
			Double currentYearsTotal = dataMapping.getCurrentYears().stream()
					.mapToDouble(DataMappingProjectedSales::getValues).sum();
			if (countCY < 12) {
				int remainingMnths = 12 - countCY;
				previousYrsRemainTotal = dataMapping.getPreviousYears().stream()
						.sorted(new DateComparatorDataMappDescTime()).limit(remainingMnths)
						.collect(Collectors.summingDouble(DataMappingProjectedSales::getValues));
			}
			Double totalHistoricSales = currentYearsTotal + previousYrsRemainTotal;
			return totalHistoricSales / 12.0;
		} catch (Exception e) {
			return null;
		}
	}

	public static Double calculateGSTR3BSalesRequestedMonths(DataMapping dataMapping, GSTR1Request gstr1Request) {
		try {
			List<DataMappingProjectedSales> allYearsReponseList = new ArrayList<>();
			allYearsReponseList.addAll(dataMapping.getPreviousYears());
			allYearsReponseList.addAll(dataMapping.getCurrentYears());
			Collections.reverse(allYearsReponseList);
			Double getr3bTotalSales = allYearsReponseList.stream()
					.limit(gstr1Request.getRequestedMonths() != null && gstr1Request.getRequestedMonths() != 0
							? gstr1Request.getRequestedMonths()
							: 12l)
					.mapToDouble(DataMappingProjectedSales::getValues).sum();
			return getr3bTotalSales;
		} catch (Exception e) {
			return null;
		}
	}

	public static TreeMap<String, Object> getCalulationFromGSTR3B(GSTR3BSummary[] gstr3bSummary) {
		try {

			TreeMap<String, Double> retPeriodPrevious = new TreeMap<>(new DateComparatorDescTime());
			TreeMap<String, Double> retPeriodCurrent = new TreeMap<>(new DateComparator2());

			TreeMap<String, Object> retPeriod = new TreeMap<>();
			for (GSTR3BSummary summary : gstr3bSummary) {
				if (summary.getStatusCd() == null || summary.getStatusCd().equals("1")
						|| summary.getStatusCd().equals("")) {
					if (!afterAprilOfCurrentYear(summary.getRetPeriod())) {// previous Year

						if (summary.getSupDetails() != null) {

							retPeriodPrevious.put(summary.getRetPeriod(),
									addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
											summary.getSupDetails().getOsupDet().getTxval(),
											summary.getSupDetails().getOsupNilExmp().getTxval(),
											summary.getSupDetails().getOsupNongst().getTxval()));
						} else {
							retPeriodPrevious.put(summary.getRetPeriod(), 0.0);
						}

					}
					if (afterAprilOfCurrentYear(summary.getRetPeriod())) {// Current Year

						if (summary.getSupDetails() != null) {

							retPeriodCurrent.put(summary.getRetPeriod(),
									addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
											summary.getSupDetails().getOsupDet().getTxval(),
											summary.getSupDetails().getOsupNilExmp().getTxval(),
											summary.getSupDetails().getOsupNongst().getTxval()));
						} else {
							retPeriodCurrent.put(summary.getRetPeriod(), 0.0);
						}
					}
				}

			}
			retPeriod.putAll(retPeriodCurrent);
			retPeriod.putAll(retPeriodPrevious);

			return retPeriod;
			/*
			 * Integer allCount = retPeriodCurrent.size()+retPeriodPrevious.size();
			 * if(allCount!=0) { Integer diff = dateDifferenceCalc(); Integer remainingCount
			 * = 12 - diff; if(retPeriodPrevious.size() ==0) { Double total
			 * =totalSum(retPeriodCurrent)/retPeriodCurrent.size()*12; Double conce =
			 * Double.isFinite(total)?total : 0.0; data.put("conc", conce); return data; }
			 * else if (retPeriodCurrent.size() ==0) { Double total =
			 * totalSum(retPeriodPrevious)/retPeriodPrevious.size()*12; Double conce =
			 * Double.isFinite(total)?total : 0.0; data.put("conc", conce); return data; }
			 * else {
			 * 
			 * Double currentcalc = totalSum(retPeriodCurrent)/retPeriodCurrent.size()*diff;
			 * if(!Double.isFinite(currentcalc)) { currentcalc = 0.0; }
			 * 
			 * int i = 0; if(retPeriodPrevious.size()>remainingCount) { TreeMap<String,
			 * Double> mm = new TreeMap<>(new DateComparatorDescTime()); for(Entry<String,
			 * Double> m : retPeriodPrevious.entrySet()) { i++; if(i <= remainingCount) {
			 * mm.put(m.getKey(),m.getValue()); } else { break; }
			 * 
			 * }
			 * 
			 * Double previouscalc = totalSum(mm)/mm.size()*remainingCount;
			 * if(!Double.isFinite(previouscalc)) { previouscalc = 0.0; } Double conce =
			 * (currentcalc+previouscalc); data.put("conc", conce); return data; }
			 * 
			 * Double previouscalc =
			 * totalSum(retPeriodPrevious)/retPeriodPrevious.size()*remainingCount;
			 * if(!Double.isFinite(previouscalc)) { previouscalc = 0.0; }
			 * 
			 * Double conce = (currentcalc+previouscalc); data.put("conc", conce); return
			 * data; }
			 * 
			 * } else { data.put("conc", 0.0); return data; }
			 */

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return null;
		}
	}

	public static DataMapping getCalulationFromGSTR3BPhase2(GSTR3BSummary[] gstr3bSummary) {
		try {
			/*
			// FOR ONLY USE FOR TESTING PORPOSE - Start
			if(true) {
				TreeMap<String, Object> retPeriod = new TreeMap<>(new DateComparatorDescTime());
				List<String> remainingMonths = returnPeriodList().stream()
						.filter(x -> !(Arrays.asList(retPeriod.keySet().toArray()).stream()
								.map(object -> Objects.toString(object, null)).collect(Collectors.toList())).contains(x))
						.collect(Collectors.toList());

				DataMapping dataMapping = new DataMapping();
				retPeriod.entrySet().stream().forEach(pair -> {
					if (afterAprilOfCurrentYear(pair.getKey())) {
						dataMapping.addCurrentYears(new DataMappingProjectedSales(pair.getKey(),
								Double.valueOf(pair.getValue().toString()), false));
					} else {
						dataMapping.addPreviousYear(new DataMappingProjectedSales(pair.getKey(),
								Double.valueOf(pair.getValue().toString()), false));
					}
				});

				dataMapping.setCurrentYearTotal(dataMapping.getCurrentYears().stream().filter(o -> o.getValues()!=null).mapToDouble(o -> o.getValues()).sum());
				dataMapping.setPreviousYearTotal(dataMapping.getPreviousYears().stream().filter(o -> o.getValues()!=null).mapToDouble(o -> o.getValues()).sum());


				remainingMonths.forEach(month -> {

					if (afterAprilOfCurrentYear(month)) {
						dataMapping.addCurrentYears(new DataMappingProjectedSales(month, 0.0, true));
					} else {
						dataMapping.addPreviousYear(new DataMappingProjectedSales(month, 0.0, true));
					}

				});
				dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
				dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());

				dataMapping.setStatusCd(MONTHS_MISSING_DATA_FROM_GST);
				return dataMapping;
			}
			// FOR ONLY USE FOR TESTING PORPOSE - End
			*/
			TreeMap<String, Object> retPeriod = new TreeMap<>(new DateComparatorDescTime()); // total sales

			for (GSTR3BSummary summary : gstr3bSummary) {
				if (summary.getStatusCd() == null || summary.getStatusCd().equals("1")
						|| summary.getStatusCd().equals("")) {
					if (summary.getSupDetails() != null) {

						retPeriod.put(summary.getRetPeriod(),
								addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
										summary.getSupDetails().getOsupDet().getTxval(),
										summary.getSupDetails().getOsupNilExmp().getTxval(),
										summary.getSupDetails().getOsupNongst().getTxval()));
					} else {
						retPeriod.put(summary.getRetPeriod(), 0.0);
					}
				}
			}

			List<String> remainingMonths = returnPeriodList().stream()
					.filter(x -> !(Arrays.asList(retPeriod.keySet().toArray()).stream()
							.map(object -> Objects.toString(object, null)).collect(Collectors.toList())).contains(x))
					.collect(Collectors.toList());

			DataMapping dataMapping = new DataMapping();

			retPeriod.entrySet().stream().forEach(pair -> {
				if (afterAprilOfCurrentYear(pair.getKey())) {
					dataMapping.addCurrentYears(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				} else {
					dataMapping.addPreviousYear(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				}
			});

			dataMapping.setCurrentYearTotal(dataMapping.getCurrentYears().stream().filter(o -> o.getValues()!=null).mapToDouble(o -> o.getValues()).sum());
			dataMapping.setPreviousYearTotal(dataMapping.getPreviousYears().stream().filter(o -> o.getValues()!=null).mapToDouble(o -> o.getValues()).sum());

			if (remainingMonths.size() != 0) {


				remainingMonths.forEach(month -> {

					if (afterAprilOfCurrentYear(month)) {
						dataMapping.addCurrentYears(new DataMappingProjectedSales(month, 0.0, true));
					} else {
						dataMapping.addPreviousYear(new DataMappingProjectedSales(month, 0.0, true));
					}

				});
				dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
				dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());

				dataMapping.setStatusCd(MONTHS_MISSING_DATA_FROM_GST);
				return dataMapping;
			} else {
				ProjectedSalesCalcResponse projectedSalesCalcResponse = calculateProjectedSales(dataMapping);
				if(projectedSalesCalcResponse.getIsNegativeGrowth()) {
					dataMapping.setStatusCd(IS_GROWTH_NEGATIVE);
				}
				else {
					dataMapping.setStatusCd(CLEAR);
				}
				dataMapping.setProjectedSalesCalcResponse(projectedSalesCalcResponse);
				return dataMapping;

			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION + " getCalulationFromGSTR3BPhase2() :", e);
			return null;
		}
	}

	public static DataMapping getCalulationFromGSTR3BPhase2Specific(GSTR3BSummary[] gstr3bSummary) {
		try {

			TreeMap<String, Object> retPeriod = new TreeMap<>(new DateComparatorDescTime());

			for (GSTR3BSummary summary : gstr3bSummary) {
				if (summary.getStatusCd() == null || summary.getStatusCd().equals("1")
						|| summary.getStatusCd().equals("")) {
					if (summary.getSupDetails() != null) {

						retPeriod.put(summary.getRetPeriod(),
								addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
										summary.getSupDetails().getOsupDet().getTxval(),
										summary.getSupDetails().getOsupNilExmp().getTxval()));
					} else {
						retPeriod.put(summary.getRetPeriod(), 0.0);
					}
				}
			}

//			List<String> remainingMonths = returnPeriodList().stream()
//					.filter(x -> !(Arrays.asList(retPeriod.keySet().toArray()).stream()
//							.map(object -> Objects.toString(object, null)).collect(Collectors.toList())).contains(x))
//					.collect(Collectors.toList());

			DataMapping dataMapping = new DataMapping();

			retPeriod.entrySet().stream().forEach(pair -> {
				if (afterAprilOfCurrentYear(pair.getKey())) {
					dataMapping.addCurrentYears(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				} else {
					dataMapping.addPreviousYear(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				}
			});

			dataMapping.setCurrentYearTotal(dataMapping.getCurrentYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());
			dataMapping.setPreviousYearTotal(dataMapping.getPreviousYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());

			/*
			 * if (remainingMonths.size() != 0) {
			 * 
			 * 
			 * remainingMonths.forEach(month -> {
			 * 
			 * if (afterAprilOfCurrentYear(month)) { dataMapping.addCurrentYears(new
			 * DataMappingProjectedSales(month, 0.0, true)); } else {
			 * dataMapping.addPreviousYear(new DataMappingProjectedSales(month, 0.0, true));
			 * }
			 * 
			 * });
			 */
			/*
			 * dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
			 * dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());
			 */
			/*
			 * dataMapping.setStatusCd(MONTHS_MISSING_DATA_FROM_GST); return dataMapping; }
			 * else {
			 */
			ProjectedSalesCalcResponse projectedSalesCalcResponse = calculateProjectedSales(dataMapping);
			if (projectedSalesCalcResponse.getIsNegativeGrowth()) {
				dataMapping.setStatusCd(IS_GROWTH_NEGATIVE);
			} else {
				dataMapping.setStatusCd(CLEAR);
			}
			dataMapping.setProjectedSalesCalcResponse(projectedSalesCalcResponse);
			return dataMapping;

			/*
			 * // FOR ONLY USE FOR TESTING PORPOSE - Start remainingMonths.forEach(month ->
			 * {
			 * 
			 * if (afterAprilOfCurrentYear(month)) { dataMapping.addCurrentYears(new
			 * DataMappingProjectedSales(month, 0.0, true)); } else {
			 * dataMapping.addPreviousYear(new DataMappingProjectedSales(month, 0.0, true));
			 * }
			 * 
			 * }); dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
			 * dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());
			 * 
			 * dataMapping.setStatusCd(MONTHS_MISSING_DATA_FROM_GST); return dataMapping; //
			 * FOR ONLY USE FOR TESTING PORPOSE - End
			 */
//			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION + " getCalulationFromGSTR3BPhase2() :", e);
			return null;
		}
	}

	public static ProjectedSalesCalcResponse calculateProjectedSales(DataMapping dataMapping) {
		ProjectedSalesCalcResponse projectedSalesCalcResponse = new ProjectedSalesCalcResponse();
		try {
			List<DataMappingProjectedSales> allYrs = new ArrayList<DataMappingProjectedSales>();

			dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
			dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());
			allYrs.addAll(dataMapping.getPreviousYears());
			allYrs.addAll(dataMapping.getCurrentYears());
//			dataMapping.getPreviousYears().addAll(dataMapping.getCurrentYears());
//			DataMappingProjectedSales startMonth = (DataMappingProjectedSales) dataMapping.getPreviousYears().stream().min( new DateComparatorAscTime()::compare).get();
//			DataMappingProjectedSales endMonth = (DataMappingProjectedSales) dataMapping.getCurrentYears().stream().max( new DateComparatorAscTime()::compare).get();

			LocalDate date = LocalDate.now();
			Calendar end = Calendar.getInstance();
			end.add(Calendar.YEAR, -1);
			int year = end.get(Calendar.YEAR);
			end.set(Calendar.MONTH, Integer.valueOf(allYrs.get(allYrs.size() - 1).getReturnPeriod().substring(0, 2)));
			String endRetPer = null;
			if (date.getMonthValue() == 1) {
				endRetPer = (String.format("%02d", end.get(Calendar.MONTH) + 1)) + "" + (year - 1);
			} else {
				endRetPer = (String.format("%02d", end.get(Calendar.MONTH) + 1)) + "" + year;
			}
			DataMappingProjectedSales endMonthPrev = new DataMappingProjectedSales(endRetPer, null, false);
//
//		    Double totalPreviousYearsSales =allYrs.stream().filter(x -> compareRetrunPeriod(x, startMonth) && !compareRetrunPeriod(x, endMonthPrev)).collect(Collectors.toList())
//				.stream().mapToDouble(DataMappingProjectedSales :: getValues).sum();
//
//
//
//			Calendar start = Calendar.getInstance();
//		    start.set(Calendar.MONTH, Integer.parseInt(startMonth.getReturnPeriod().substring(0, 2))-1);
//		    start.set(Calendar.YEAR, Integer.parseInt(startMonth.getReturnPeriod().substring(2))+1);
//		    String startRetPerCurr = (String.format("%02d", start.get(Calendar.MONTH) + 1)) + "" + start.get(Calendar.YEAR);
//		    DataMappingProjectedSales startMonthCurrent = new DataMappingProjectedSales(startRetPerCurr,null,false);

//		    Calendar endCurr = Calendar.getInstance();
//		    String endRetPerCurr = (String.format("%02d", endCurr.get(Calendar.MONTH) + 1)) + "" + endCurr.get(Calendar.YEAR);
//			DataMappingProjectedSales endMonthCurr = new DataMappingProjectedSales(endRetPerCurr,null,false);

			List<DataMappingProjectedSales> m = allYrs.stream().filter(x -> !compareRetrunPeriod(x, endMonthPrev))
					.collect(Collectors.toList());
			Collections.reverse(m);
			Double totalCurrentYearsSales = allYrs.stream().filter(x -> compareRetrunPeriod(x, endMonthPrev))
					.collect(Collectors.toList()).stream().mapToDouble(DataMappingProjectedSales::getValues).sum();
			Double totalPreviousYearsSales = m.stream().limit(12L).collect(Collectors.toList()).stream()
					.mapToDouble(DataMappingProjectedSales::getValues).sum();

			Double diff = totalCurrentYearsSales - totalPreviousYearsSales;
			Double growth = (diff / totalPreviousYearsSales) * 100;
			projectedSalesCalcResponse.setGrowth(growth);

			/*
			 * List<DataMappingProjectedSales> m = allYrs.stream().filter(x ->
			 * !compareRetrunPeriod(x, startMonthCurrent) && !compareRetrunPeriod(x,
			 * endMonthCurr)).collect(Collectors.toList()); Collections.reverse(m); m =
			 * m.stream().limit(12L).collect(Collectors.toList());
			 */
			/*
			 * Double remainingPrviousTotal =allYrs.stream().filter(x ->
			 * compareRetrunPeriod(x, endMonthPrev)).collect(Collectors.toList())
			 * .stream().mapToDouble(DataMappingProjectedSales :: getValues).sum();
			 */

			if (growth < 0) {
				projectedSalesCalcResponse.setIsNegativeGrowth(true);
			} else {
				projectedSalesCalcResponse.setIsNegativeGrowth(false);
			}

//			Double sales2 = dataMapping.getCurrentYears().stream().mapToDouble(DataMappingProjectedSales::getValues).sum();
//			Double remainingPrevTotal = remainingPrviousTotal-sales2;
			Double sales0 = (totalCurrentYearsSales * (100 + growth)) / 100.00;

			projectedSalesCalcResponse.setSales1(totalCurrentYearsSales);

			projectedSalesCalcResponse.setSales2(totalPreviousYearsSales);
			Double projectedSales = sales0;
			projectedSalesCalcResponse.setProjectedSales(projectedSales);

			return projectedSalesCalcResponse;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION + " calculateProjectedSales() :", e);
			return projectedSalesCalcResponse;
		}
	}

	public static ProjectedSalesCalcResponse calculateGstr1ProjectedSales(DataMapping dataMapping) {
		ProjectedSalesCalcResponse projectedSalesCalcResponse = new ProjectedSalesCalcResponse();
		try {
//			List<DataMappingProjectedSales> allYrs = new ArrayList<DataMappingProjectedSales>();

			dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
			dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());

			Double totalCurrentYearsSales = dataMapping.getCurrentYearTotal();
			Double totalPreviousYearsSales = dataMapping.getPreviousYearTotal();

			Double diff = totalCurrentYearsSales - totalPreviousYearsSales;
			Double growth = (diff / totalPreviousYearsSales) * 100;
			projectedSalesCalcResponse.setGrowth(growth);

			if (growth < 0) {
				projectedSalesCalcResponse.setIsNegativeGrowth(true);
			} else {
				projectedSalesCalcResponse.setIsNegativeGrowth(false);
			}

			Double sales0 = (totalCurrentYearsSales * (100 + growth)) / 100.00;

			projectedSalesCalcResponse.setSales1(totalCurrentYearsSales);

			projectedSalesCalcResponse.setSales2(totalPreviousYearsSales);
			Double projectedSales = sales0;
			projectedSalesCalcResponse.setProjectedSales(projectedSales);

			return projectedSalesCalcResponse;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION + " calculateProjectedSales() :", e);
			return projectedSalesCalcResponse;
		}
	}

	public static ProjectedSalesCalcResponse calculateProjectedSalesForMUltiBank(DataMapping dataMapping) {
		ProjectedSalesCalcResponse projectedSalesCalcResponse = new ProjectedSalesCalcResponse();
		try {
			List<DataMappingProjectedSales> allYrs = new ArrayList<DataMappingProjectedSales>();

			dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
			dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());
			allYrs.addAll(dataMapping.getPreviousYears());
			allYrs.addAll(dataMapping.getCurrentYears());
//			dataMapping.getPreviousYears().addAll(dataMapping.getCurrentYears());
//			DataMappingProjectedSales startMonth = (DataMappingProjectedSales) dataMapping.getPreviousYears().stream().min( new DateComparatorAscTime()::compare).get();
//			DataMappingProjectedSales endMonth = (DataMappingProjectedSales) dataMapping.getCurrentYears().stream().max( new DateComparatorAscTime()::compare).get();

			Calendar end = Calendar.getInstance();
			end.add(Calendar.YEAR, -1);
			end.set(Calendar.MONTH, Integer.valueOf(allYrs.get(allYrs.size() - 1).getReturnPeriod().substring(0, 2)));
			String endRetPer = (String.format("%02d", end.get(Calendar.MONTH) + 1)) + "" + end.get(Calendar.YEAR);
			DataMappingProjectedSales endMonthPrev = new DataMappingProjectedSales(endRetPer, null, false);
//
//		    Double totalPreviousYearsSales =allYrs.stream().filter(x -> compareRetrunPeriod(x, startMonth) && !compareRetrunPeriod(x, endMonthPrev)).collect(Collectors.toList())
//				.stream().mapToDouble(DataMappingProjectedSales :: getValues).sum();
//
//
//
//			Calendar start = Calendar.getInstance();
//		    start.set(Calendar.MONTH, Integer.parseInt(startMonth.getReturnPeriod().substring(0, 2))-1);
//		    start.set(Calendar.YEAR, Integer.parseInt(startMonth.getReturnPeriod().substring(2))+1);
//		    String startRetPerCurr = (String.format("%02d", start.get(Calendar.MONTH) + 1)) + "" + start.get(Calendar.YEAR);
//		    DataMappingProjectedSales startMonthCurrent = new DataMappingProjectedSales(startRetPerCurr,null,false);

//		    Calendar endCurr = Calendar.getInstance();
//		    String endRetPerCurr = (String.format("%02d", endCurr.get(Calendar.MONTH) + 1)) + "" + endCurr.get(Calendar.YEAR);
//			DataMappingProjectedSales endMonthCurr = new DataMappingProjectedSales(endRetPerCurr,null,false);

			List<DataMappingProjectedSales> m = allYrs.stream().filter(x -> !compareRetrunPeriod(x, endMonthPrev))
					.collect(Collectors.toList());
			Collections.reverse(m);
			Double totalCurrentYearsSales = allYrs.stream().filter(x -> compareRetrunPeriod(x, endMonthPrev))
					.collect(Collectors.toList()).stream().mapToDouble(DataMappingProjectedSales::getValues).sum();
			Double totalPreviousYearsSales = m.stream().limit(12L).collect(Collectors.toList()).stream()
					.mapToDouble(DataMappingProjectedSales::getValues).sum();

			Double diff = totalCurrentYearsSales - totalPreviousYearsSales;
			Double growth = (diff / totalPreviousYearsSales) * 100;
			projectedSalesCalcResponse.setGrowth(growth);

			/*
			 * List<DataMappingProjectedSales> m = allYrs.stream().filter(x ->
			 * !compareRetrunPeriod(x, startMonthCurrent) && !compareRetrunPeriod(x,
			 * endMonthCurr)).collect(Collectors.toList()); Collections.reverse(m); m =
			 * m.stream().limit(12L).collect(Collectors.toList());
			 */
			/*
			 * Double remainingPrviousTotal =allYrs.stream().filter(x ->
			 * compareRetrunPeriod(x, endMonthPrev)).collect(Collectors.toList())
			 * .stream().mapToDouble(DataMappingProjectedSales :: getValues).sum();
			 */

			if (growth < 0) {
				projectedSalesCalcResponse.setIsNegativeGrowth(true);
			} else {
				projectedSalesCalcResponse.setIsNegativeGrowth(false);
			}

//			Double sales2 = dataMapping.getCurrentYears().stream().mapToDouble(DataMappingProjectedSales::getValues).sum();
//			Double remainingPrevTotal = remainingPrviousTotal-sales2;
			Double sales0 = (totalCurrentYearsSales * (100 + growth)) / 100.00;

			projectedSalesCalcResponse.setSales1(totalCurrentYearsSales);

			projectedSalesCalcResponse.setSales2(totalPreviousYearsSales);
			Double projectedSales = sales0;
			projectedSalesCalcResponse.setProjectedSales(projectedSales);

			return projectedSalesCalcResponse;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION + " calculateProjectedSales() :", e);
			return projectedSalesCalcResponse;
		}
	}

	private static Boolean compareRetrunPeriod(DataMappingProjectedSales o1, DataMappingProjectedSales o2) {
		try {
			Date d1 = convertToDate(o1.getReturnPeriod());
			Date d2 = convertToDate(o2.getReturnPeriod());
			if (d1.after(d2)) {
				return true;
			} else if (d1.before(d2)) {
				return false;
			} else if (d1.equals(d2)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return null;
		}
	}

	static Date convertToDate(Object o1) throws ParseException {
		String dateStr = "";
		String mainStr = String.valueOf(o1);
		String date = "01";
		String month = "";
		String year = "";
		if (mainStr.length() == 6) {
			month = mainStr.substring(0, 2);
			year = mainStr.substring(2, mainStr.length());
		} else {
			month = mainStr.substring(0, 1);
			year = mainStr.substring(1, mainStr.length());
		}
		dateStr = date + "/" + month + "/" + year;
		return new SimpleDateFormat(DD_MM_YYYY).parse(dateStr);
	}

	public static Integer dateDifferenceCalc() {
		Date startDate = new Date();
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(startDate);
		Calendar cal = Calendar.getInstance();
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.set(startCalendar.get(Calendar.YEAR), Calendar.APRIL, startCalendar.get(Calendar.DATE));
		Integer diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		if (Calendar.JANUARY <= (cal.get(Calendar.MONTH)) || Calendar.MARCH >= (cal.get(Calendar.MONTH))) {
			endCalendar.set(startCalendar.get(Calendar.YEAR) - 1, Calendar.APRIL, startCalendar.get(Calendar.DATE));
			diffYear = startCalendar.get(Calendar.YEAR) - endCalendar.get(Calendar.YEAR);
		}
		logger.info("Year diff : " + diffYear);
		Integer diffMonth = diffYear * 12 + startCalendar.get(Calendar.MONTH) - endCalendar.get(Calendar.MONTH);
		logger.info("Mont diff : " + diffMonth);
		return diffMonth;
	}

	public static Boolean afterAprilOfCurrentYear(String retPeriod) {
		String month = "";

		Calendar currentFinancialYr = new GregorianCalendar();
		month = String.valueOf(currentFinancialYr.get(Calendar.MONTH) + 1);
		if (month.length() == 1)
			month = "0" + month;

		if (getFinancialYear(retPeriod).equalsIgnoreCase(
				getFinancialYear(month.concat(String.valueOf(currentFinancialYr.get(Calendar.YEAR))))))
			return true;
		else
			return false;
		/*
		 * 
		 * String dateStr = ""; String date = "01"; String month = ""; String year = "";
		 * if (retPeriod.length() == 6) { month = retPeriod.substring(0, 2); year =
		 * retPeriod.substring(2, retPeriod.length()); } else { month =
		 * retPeriod.substring(0, 1); year = retPeriod.substring(1, retPeriod.length());
		 * } dateStr = date + "/" + month + "/" + year; Date retPer = null; try { retPer
		 * = new SimpleDateFormat(DD_MM_YYYY).parse(dateStr); } catch (ParseException e)
		 * { logger.error(CommonUtils.EXCEPTION ,e); } Calendar cal =
		 * Calendar.getInstance(); Calendar currentFinancialYr = new
		 * GregorianCalendar();
		 * currentFinancialYr.set(currentFinancialYr.get(Calendar.YEAR),
		 * (Calendar.APRIL)-1, currentFinancialYr.get(Calendar.DATE));
		 * 
		 * Calendar returnPeriod = new GregorianCalendar();
		 * returnPeriod.setTime(retPer);
		 * 
		 * if(Calendar.APRIL == Integer.valueOf(month)-1 &&
		 * currentFinancialYr.get(Calendar.YEAR) == Integer.valueOf(year )) { return
		 * true; } // else if (currentFinancialYr.get(Calendar.YEAR) ==
		 * Integer.valueOf(year )) else if ((Calendar.JANUARY <=
		 * (cal.get(Calendar.MONTH)) || Calendar.MARCH >= (cal.get(Calendar.MONTH))) &&
		 * currentFinancialYr.get(Calendar.YEAR) == Integer.valueOf(year )) { return
		 * returnPeriod.after(currentFinancialYr); } else if(Calendar.MAY <=
		 * (cal.get(Calendar.MONTH)) || Calendar.DECEMBER >= (cal.get(Calendar.MONTH)))
		 * { currentFinancialYr.set(Integer.valueOf(year), Integer.valueOf(month),
		 * currentFinancialYr.get(Calendar.DATE)); return
		 * returnPeriod.after(currentFinancialYr); } else { return
		 * returnPeriod.after(currentFinancialYr); }
		 */
	}

	public static String getFinancialYear(String retPeriod) {

		if (Integer.valueOf(retPeriod.substring(0, 2)) < 4) {
			return Integer.valueOf(retPeriod.substring(2)) - 1 + "-" + Integer.valueOf(retPeriod.substring(2));
		} else {
			return Integer.valueOf(retPeriod.substring(2)) + "-" + (Integer.valueOf(retPeriod.substring(2)) + 1);
		}

	}

	public static String getPreviousFinancialYear(int previousYear) {

		Calendar cal = Calendar.getInstance();
		if (previousYear < 0)
			cal.add(Calendar.YEAR, previousYear);
		int month = cal.get(Calendar.MONTH) + 1;
		int year = cal.get(Calendar.YEAR);
		if (month < 4) {
			return (year - 1) + "-" + year;
		} else {
			return year + "-" + (year + 1);
		}

	}
	/*
	 * public static void main(String[] arg) { TreeMap<String, Object> retPeriod =
	 * new TreeMap<>(new DateComparatorDescTime()); DataMapping dataMapping = new
	 * DataMapping(); List<String> remainingMonths = returnPeriodList().stream()
	 * .filter(x -> !(Arrays.asList(retPeriod.keySet().toArray()).stream()
	 * .map(object -> Objects.toString(object,
	 * null)).collect(Collectors.toList())).contains(x))
	 * .collect(Collectors.toList()); remainingMonths.forEach(month -> {
	 * 
	 * if (afterAprilOfCurrentYear(month)) { dataMapping.addCurrentYears(new
	 * DataMappingProjectedSales(month, 0.0, true)); } else {
	 * dataMapping.addPreviousYear(new DataMappingProjectedSales(month, 0.0, true));
	 * }
	 * 
	 * }); dataMapping.getCurrentYears().sort(new DateComparatorAscTime3());
	 * dataMapping.getPreviousYears().sort(new DateComparatorAscTime3());
	 * System.out.println(dataMapping); } /*public static void main(String[] args) {
	 * for(String b : CommonUtils.returnPeriodList()) { Boolean a=
	 * CommonUtils.afterAprilOfCurrentYear(b); System.out.println(b +"--> "+ a);
	 * 
	 * }
	 */
	/*
	 * Boolean a= CommonUtils.afterAprilOfCurrentYear("042018");
	 * System.out.println(a);
	 */

//	}
	public static Boolean isBeforeGstImp(String retPeriod) {

		if ("042017".equals(retPeriod)) {
			return true;
		} else if ("052017".equals(retPeriod)) {
			return true;
		} else if ("062017".equals(retPeriod)) {
			return true;
		} else {
			return false;
		}

	}

	public static Double addNumbers(Double... values) {
		Double sum = 0.0;
		for (Double a : values) {
			sum += a;
		}
		return sum;
	}

	public static Double getCalulationFromGSTR3BKarza(KarzaGstResponse[] gstr3bSummarys) {
		try {
			Map<String, Double> map = new HashMap<String, Double>();
//		for(KarzaGstResponse gstr3bSummary :gstr3bSummarys) {
			KarzaGstResponse gstr3bSummary = gstr3bSummarys[gstr3bSummarys.length - 1];
			if ((gstr3bSummary != null) && (gstr3bSummary.getResult() != null)
					&& (gstr3bSummary.getResult().getCurrent().getGstr3b() != null)
					&& (gstr3bSummary.getResult().getCurrent().getGstr3b().getGstr3details() != null)) {
				Boolean flag = true;
				if (gstr3bSummary.getResult().getPrevious() != null
						&& gstr3bSummary.getResult().getPrevious().getGstr3b() != null
						&& gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details() != null
						&& gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details().length > 0) {
					flag = false;
				}

				if (gstr3bSummary.getResult().getCurrent().getGstr3b().getGstr3details().length >= 4 && flag) {// case
					// 1

					for (Gstr3Details a : gstr3bSummary.getResult().getCurrent().getGstr3b().getGstr3details()) {
						if (a.getSupDetails() != null) {
							map.put(a.getRetPeriod(),
									addNumbers(a.getSupDetails().getOsupZero().getTxval(),
											a.getSupDetails().getOsupDet().getTxval(),
											a.getSupDetails().getOsupNilExmp().getTxval()));
						}
					}
					return totalSum(map) / gstr3bSummary.getResult().getCurrent().getGstr3b().getGstr3details().length
							* 12;
				} else {

					if ((gstr3bSummary.getResult().getPrevious().getGstr3b() != null)
							&& (gstr3bSummary.getResult().getCurrent().getGstr3b() != null)
							&& (gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details() != null)
							&& (gstr3bSummary.getResult().getCurrent().getGstr3b().getGstr3details() != null)) {
						Integer totalMonths = (gstr3bSummary.getResult().getCurrent().getGstr3b()
								.getGstr3details().length)
								+ (gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details().length);
						if (totalMonths == 12) {// case 2

							for (Gstr3Details a : gstr3bSummary.getResult().getCurrent().getGstr3b()
									.getGstr3details()) {
								if (a.getSupDetails() != null) {
									map.put(a.getRetPeriod(),
											addNumbers(a.getSupDetails().getOsupZero().getTxval(),
													a.getSupDetails().getOsupDet().getTxval(),
													a.getSupDetails().getOsupNilExmp().getTxval()));
								}
							}

							for (Gstr3Details a : gstr3bSummary.getResult().getPrevious().getGstr3b()
									.getGstr3details()) {
								if (a.getSupDetails() != null) {
									map.put(a.getRetPeriod(),
											addNumbers(a.getSupDetails().getOsupZero().getTxval(),
													a.getSupDetails().getOsupDet().getTxval(),
													a.getSupDetails().getOsupNilExmp().getTxval()));
								}
							}
							return totalSum(map);
						} else {
							// case 3

							if (gstr3bSummary.getResult().getCurrent().getGstr3b()
									.getGstr3details().length == dateDifferenceCalc()) {
								Integer returnata = 12 - dateDifferenceCalc();
								for (Gstr3Details a : gstr3bSummary.getResult().getCurrent().getGstr3b()
										.getGstr3details()) {
									if (a.getSupDetails() != null) {
										map.put(a.getRetPeriod(),
												addNumbers(a.getSupDetails().getOsupZero().getTxval(),
														a.getSupDetails().getOsupDet().getTxval(),
														a.getSupDetails().getOsupNilExmp().getTxval()));
									}
								}
								List<Gstr3Details> asd = Arrays
										.asList(gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details());

								asd.sort(new DateComparator3());
								Collections.reverse(asd);
								int i = 0;
								for (Gstr3Details b : asd) {
									if (i <= returnata && b.getSupDetails() != null) {
										map.put(b.getRetPeriod(),
												addNumbers(b.getSupDetails().getOsupZero().getTxval(),
														b.getSupDetails().getOsupDet().getTxval(),
														b.getSupDetails().getOsupNilExmp().getTxval()));
									}
									i++;
								}
								Double size = Double.valueOf(map.size());
								Double total = totalSum(map);

								return (total / size) * 12;
							} else if (gstr3bSummary.getResult().getCurrent().getGstr3b()
									.getGstr3details().length < dateDifferenceCalc()) {
								map = new HashMap<>();
								Integer returnata = 12 - dateDifferenceCalc();
								for (Gstr3Details a : gstr3bSummary.getResult().getCurrent().getGstr3b()
										.getGstr3details()) {
									if (a.getSupDetails() != null) {
										map.put(a.getRetPeriod(),
												addNumbers(a.getSupDetails().getOsupZero().getTxval(),
														a.getSupDetails().getOsupDet().getTxval(),
														a.getSupDetails().getOsupNilExmp().getTxval()));
									}
								}

								Double totalcurrent = (totalSum(map) / map.size()) * dateDifferenceCalc();
								if (totalcurrent == null || totalcurrent.isNaN() || totalcurrent.isInfinite()) {
									totalcurrent = 0.0;
								}

								List<Gstr3Details> asd = Arrays
										.asList(gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details());

								asd.sort(new DateComparator3());
								Collections.reverse(asd);
								int i = 0;
								// totalSum(map);
								map = new HashMap<>();
								for (Gstr3Details b : asd) {
									if (i < returnata && b.getSupDetails() != null) {
										map.put(b.getRetPeriod(),
												addNumbers(b.getSupDetails().getOsupZero().getTxval(),
														b.getSupDetails().getOsupDet().getTxval(),
														b.getSupDetails().getOsupNilExmp().getTxval()));
									}
									i++;
								}
								Double totalPrevious = totalSum(map);
								if (totalPrevious == null || totalPrevious.isNaN() || totalPrevious.isInfinite()) {
									totalPrevious = 0.0;
								}

								if (totalcurrent == null || totalcurrent.isNaN() || totalcurrent.isInfinite()
										|| totalcurrent == 0.0) {
									totalPrevious = totalPrevious * 12 / map.size();
								} else {
									totalPrevious = totalPrevious * returnata / map.size();
								}
								return totalcurrent + totalPrevious;
							}

						}
					}

				}
			}

//                    if (gstr3bSummary.getResult().getPrevious().getGstr3b() != null) {
//                        if (gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details() != null) {
//                            for (Gstr3Details a : gstr3bSummary.getResult().getPrevious().getGstr3b().getGstr3details()) {
//                                if (a.getSupDetails() != null) {
//                                    map.put(a.getRetPeriod(), addNumbers(a.getSupDetails().getOsupZero().getTxval(), a.getSupDetails().getOsupDet().getTxval(), a.getSupDetails().getOsupNilExmp().getTxval()));
//                                }
//                            }
//                        }
//                    }

//                }
//            }

			return 0.0;
		} catch (Exception e) {
			return 0.0;
		}
	}

	public static <T> T getObjectFromObj(Object map, Class<?> clazz) throws IOException {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return (T) mapper.convertValue(map, clazz);
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Object>>> generateDetailsCalcForB2B(Gstr2AB2b rsp,
			String retPeriod) {
		Map<String, Map<String, Map<String, Object>>> stringMapMap = new HashMap<>();
		if (!rsp.getStatusCd().equals("0")) {
			for (B2b b2b : rsp.getB2b()) {
				if (stringMapMap.containsKey(b2b.getCtin())) {
					updateValue(stringMapMap.get(b2b.getCtin()), b2b, retPeriod);
				} else {
					addValues(stringMapMap, b2b, retPeriod);
				}
			}
		}
		return stringMapMap;
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Object>>> generateDetailsCalcForCDRN(Gstr2ACdn rsp,
			String retPeriod) {
		Map<String, Map<String, Map<String, Object>>> stringMapMap = new HashMap<>();

		if (!rsp.getStatusCd().equals("0")) {
			for (Cdn cdn : rsp.getCdn()) {
				if (stringMapMap.containsKey(retPeriod)) {
					updateCDNRValue2(stringMapMap.get(retPeriod), cdn);
				} else {
					addCDNRValue2(stringMapMap, cdn, retPeriod);
				}
			}
		}

		return stringMapMap;
	}

	static void addOrUpdateValue(Map<String, Map<String, Double>> m, String ctin, String column, Double value) {

		Map<String, Double> m_ttl = new HashMap<>();
		if (m.containsKey(ctin)) {
			m_ttl = m.get(ctin);
			value += m_ttl.getOrDefault(column, 0.0);
		}
		m_ttl.put(column, value);
		m.put(ctin, m_ttl);
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static Map<String, Map<String, Double>> generateDetailsCalcForB2B(Gstr1SummaryResponse rsp) {
		Map<String, Map<String, Double>> m = new HashMap<String, Map<String, Double>>();
		if (rsp != null && rsp.getSecSum() != null) {
			for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : rsp.getSecSum()) {
				if (PropertyMapping.B2B.equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
					for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.CptySum cptySum : secSum.getCptySum()) {
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.REC, 1.0);
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.VAL, cptySum.getTtlVal());
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.TAX, cptySum.getTtlTax());
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.IGST, cptySum.getTtlIgst());
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.CGST, cptySum.getTtlCgst());
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.SGST, cptySum.getTtlSgst());
						addOrUpdateValue(m, cptySum.getCtin(), PropertyMapping.CESS, cptySum.getTtlCess());
						/*
						 * Double percOfTtlSales =0.0; if (Double.isFinite(cptySum.getTtlVal()) &&
						 * Double.isFinite(cptySum.getTtlRec())) { percOfTtlSales =
						 * ((cptySum.getTtlVal() / cptySum.getTtlRec()) * 100); }
						 * addOrUpdateValue(m,cptySum.getCtin(),PropertyMapping.PER_OF_SALES,Double.
						 * isFinite(percOfTtlSales)?percOfTtlSales:0.0);
						 */
					}
				}
			}
		}
		return m;
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForB2CLYUVA(Gstr1SummaryResponse rsp) {
		return generateDetailsCalcForYUVA2(rsp, "B2CL");
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForB2CSYUVA(Gstr1SummaryResponse rsp) {
		return generateDetailsCalcForYUVA2(rsp, "B2CS");
	}

	/**
	 * @param d
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForB2ESSSYUVA(Gstr1SummaryResponse d) {
		return generateDetailsCalcForB2ESSSYUVA(d, "EXP");
	}

	/**
	 * @param rsp
	 * @param secSumName
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForYUVA2(Gstr1SummaryResponse rsp, String secSumName) {

		List<Map<String, Double>> list = new ArrayList<>();
		if (rsp != null && rsp.getSecSum() != null) {
			for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : rsp.getSecSum()) {
				if (secSumName.equals(secSum.getSecNm())) {
					Map<String, Double> columnValueMap = new HashMap<>();
					columnValueMap.put(PropertyMapping.REC, secSum.getTtlRec() != null ? secSum.getTtlRec() : 0.0);
					columnValueMap.put(PropertyMapping.IGST, secSum.getTtlIgst() != null ? secSum.getTtlIgst() : 0.0);
					columnValueMap.put(PropertyMapping.VAL, secSum.getTtlVal() != null ? secSum.getTtlVal() : 0.0);
					columnValueMap.put(PropertyMapping.CGST, secSum.getTtlCgst() != null ? secSum.getTtlCgst() : 0.0);
					columnValueMap.put(PropertyMapping.SGST, secSum.getTtlSgst() != null ? secSum.getTtlSgst() : 0.0);
					columnValueMap.put(PropertyMapping.CESS, secSum.getTtlCess() != null ? secSum.getTtlCess() : 0.0);
					columnValueMap.put(PropertyMapping.TAX, secSum.getTtlTax() != null ? secSum.getTtlTax() : 0.0);
					/*
					 * Double percOfTtlSales =0.0; if (Double.isFinite(secSum.getTtlVal()) &&
					 * Double.isFinite(secSum.getTtlRec())) { percOfTtlSales = ((secSum.getTtlVal()
					 * / secSum.getTtlRec()) * 100); }
					 * columnValueMap.put(PropertyMapping.PER_OF_SALES,Double.isFinite(
					 * percOfTtlSales)?percOfTtlSales:0.0);
					 */
					list.add(columnValueMap);
				}
			}
		}
		return list;
	}

	private static List<Map<String, Double>> generateDetailsCalcForB2ESSSYUVA(Gstr1SummaryResponse rsp,
			String secSumName) {
		List<Map<String, Double>> list = new ArrayList<>();
		if (rsp != null && rsp.getSecSum() != null) {

			for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : rsp.getSecSum()) {
				if (secSumName.equals(secSum.getSecNm())) {
					Map<String, Double> m = new HashMap<>();
					m.put(PropertyMapping.REC, secSum.getTtlRec());
					m.put(PropertyMapping.VAL, secSum.getTtlVal());
					m.put(PropertyMapping.TAX, secSum.getTtlTax());
					m.put(PropertyMapping.IGST, secSum.getTtlIgst());
					list.add(m);
				}
			}
		}
		return list;
	}

	/**
	 * @param d
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForB2ENRYUVA(Gstr1SummaryResponse d) {
		return generateDetailsCalcForB2ENRYUVA(d, "NIL");
	}

	/**
	 * @param rsp
	 * @return
	 */
	public static List<Map<String, Double>> generateDetailsCalcForB2ENRYUVA(Gstr1SummaryResponse rsp,
			String secSumName) {
		List<Map<String, Double>> list = new ArrayList<>();
		if (rsp != null && rsp.getSecSum() != null) {

			for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : rsp.getSecSum()) {
				if (secSumName.equals(secSum.getSecNm())) {
					Map<String, Double> m = new HashMap<>();
					m.put(PropertyMapping.REC, secSum.getTtlRec());
					m.put(PropertyMapping.EXPT_AMT, secSum.getTtlExptAmt());
					m.put(PropertyMapping.NG_SUP_AMT, secSum.getTtlNgsupAmt());
					m.put(PropertyMapping.NIL_SUP_AMT, secSum.getTtlNilSupAmt());
					list.add(m);
				}
			}
		}
		return list;
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2CSSKarza(KarzaGstResponse d) {
		return generateDetailsCalcForKarza(d, "B2CS");
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2CLKarza(KarzaGstResponse d) {
		return generateDetailsCalcForKarza(d, "B2CL");
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2ESSSKarza(KarzaGstResponse d) {
		return generateDetailsCalcForB2ESSSKarza(d, "EXP");
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2ENRKarza(KarzaGstResponse d) {
		return generateDetailsCalcForB2ENRKarza(d, "NIL");
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2ENRKarza(KarzaGstResponse d,
			String secSumName) {

		Map<String, List<Map<String, Double>>> listMap = new HashMap<>();
		List<Map<String, Double>> list;
		if (isValidKarzaGstPreviousResponse(d)) {
			for (Details rsp : d.getResult().getPrevious().getGstr1().getDetails()) {
				if (rsp.getSecSum() != null) {
					list = new ArrayList<>();
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							if (listMap.containsKey(rsp.getRetPeriod())) {
								listMap.get(rsp.getRetPeriod());
							}
							addENRValues(secSum, list);
						}
					}
					listMap.put(rsp.getRetPeriod(), list);
				}
			}
		}
		if (isValidKarzaGstCurrentResponse(d)) {
			for (Details rsp : d.getResult().getCurrent().getGstr1().getDetails()) {
				if (rsp.getSecSum() != null) {
					list = new ArrayList<>();
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							if (listMap.containsKey(rsp.getRetPeriod())) {
								listMap.get(rsp.getRetPeriod());
							}
							addENRValues(secSum, list);
						}
					}
					listMap.put(rsp.getRetPeriod(), list);
				}
			}
		}
		return listMap;
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, List<Map<String, Double>>> generateDetailsCalcForB2ESSSKarza(KarzaGstResponse d,
			String secSumName) {

		Map<String, List<Map<String, Double>>> listMap = new HashMap<>();
		List<Map<String, Double>> list;
		if (isValidKarzaGstPreviousResponse(d)) {
			for (Details rsp : d.getResult().getPrevious().getGstr1().getDetails()) {
				list = new ArrayList<>();
				if (rsp.getSecSum() != null) {
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							addESSValues(secSum, list);
						}
					}
					listMap.put(rsp.getRetPeriod(), list);
				}
			}
		}
		if (isValidKarzaGstCurrentResponse(d)) {

			for (Details rsp : d.getResult().getCurrent().getGstr1().getDetails()) {
				list = new ArrayList<>();
				if (rsp.getSecSum() != null) {
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							addESSValues(secSum, list);
						}
					}
					listMap.put(rsp.getRetPeriod(), list);
				}
			}
		}
		return listMap;
	}

	private static void addENRValues(SecSum secSum, List<Map<String, Double>> list) {

		Map<String, Double> m = new HashMap<>();
		m.put(PropertyMapping.REC, secSum.getTtlRec());
		m.put(PropertyMapping.EXPT_AMT, secSum.getTtlExptAmt());
		m.put(PropertyMapping.NG_SUP_AMT, secSum.getTtlNgsupAmt());
		m.put(PropertyMapping.NIL_SUP_AMT, secSum.getTtlNilsupAmt());
		list.add(m);
	}

	private static void addESSValues(SecSum secSum, List<Map<String, Double>> list) {

		Map<String, Double> m = new HashMap<>();
		m.put(PropertyMapping.REC, secSum.getTtlRec());
		m.put(PropertyMapping.VAL, secSum.getTtlVal());
		m.put(PropertyMapping.TAX, secSum.getTtlTax());
		m.put(PropertyMapping.IGST, secSum.getTtlIgst());
		list.add(m);
	}

	private static Map<String, List<Map<String, Double>>> generateDetailsCalcForKarza(KarzaGstResponse d,
			String secSumName) {

		Map<String, List<Map<String, Double>>> listMap = new HashMap<>();
		List<Map<String, Double>> list = new ArrayList<>();
		if (isValidKarzaGstPreviousResponse(d)) {
			for (Details rsp : d.getResult().getPrevious().getGstr1().getDetails()) {
				if (rsp.getSecSum() != null) {
					list = new ArrayList<>();
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							addValues(secSum, list);
						}
					}

				}
				listMap.put(rsp.getRetPeriod(), list);
			}
		}
		if (isValidKarzaGstCurrentResponse(d)) {
			for (Details rsp : d.getResult().getCurrent().getGstr1().getDetails()) {
				list = new ArrayList<>();
				if (rsp.getSecSum() != null) {
					for (SecSum secSum : rsp.getSecSum()) {
						if (secSumName.equals(secSum.getSecNm())) {
							addValues(secSum, list);
						}
					}
				}
				listMap.put(rsp.getRetPeriod(), list);
			}
		}
		return listMap;
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Object>>> generateDetailsCalcForCDNRPurchaseKarza(
			KarzaGstResponse d) {

		Map<String, Map<String, Map<String, Object>>> m_ttl3 = new HashMap<>();
		if (isValidPurchaseKarzaGstPreviousResponse(d)) {
			for (DetailsGstr2a rsp : d.getResult().getPrevious().getGstr2a().getDetailsGstr2a()) {
				if (rsp.getSecSum() != null) {
					for (SecSumGstr2a secSum : rsp.getSecSum()) {
						if ("CDNR".equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
							for (CptySumGstr2a cptySum : secSum.getCptySum()) {
								if (m_ttl3.containsKey(rsp.getRetPeriod())) {
									updateCDNRValue2(m_ttl3.get(rsp.getRetPeriod()), cptySum);
								} else {
									addCDNRValue2(m_ttl3, cptySum, rsp.getRetPeriod());
								}
							}
						}
					}
				}
			}
		}
		if (isValidPurchaseKarzaGstCurrentResponse(d)) {
			for (DetailsGstr2a rsp : d.getResult().getCurrent().getGstr2a().getDetailsGstr2a()) {
				if (rsp.getSecSum() != null) {
					for (SecSumGstr2a secSum : rsp.getSecSum()) {
						if ("CDNR".equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
							for (CptySumGstr2a cptySum : secSum.getCptySum()) {
								if (m_ttl3.containsKey(rsp.getRetPeriod())) {
									updateCDNRValue2(m_ttl3.get(rsp.getRetPeriod()), cptySum);
								} else {
									addCDNRValue2(m_ttl3, cptySum, rsp.getRetPeriod());
								}
							}
						}
					}
				}
			}
		}
		return m_ttl3;
	}

	private static void addCDNRValue2(Map<String, Map<String, Map<String, Object>>> stringMapMap, Cdn cdn,
			String retPeriod) {

		Map<String, Map<String, Object>> stringStringMap = new HashMap<>();
		Map<String, Object> cNote = new HashMap<>();
		Map<String, Object> dNote = new HashMap<>();
		addCreditInfo(cNote, cdn);
		addDeditInfo(dNote, cdn);
		stringStringMap.put(PropertyMapping.C_NOTE, cNote);
		stringStringMap.put(PropertyMapping.D_NOTE, dNote);
		stringMapMap.put(retPeriod, stringStringMap);
	}

	private static void addCDNRValue2(Map<String, Map<String, Map<String, Object>>> stringMapMap, CptySumGstr2a cptySum,
			String retPeriod) {

		Map<String, Map<String, Object>> stringStringMap = new HashMap<>();
		Map<String, Object> cNote = new HashMap<>();
		Map<String, Object> dNote = new HashMap<>();
		addCreditInfo(cNote, cptySum);
		addDeditInfo(dNote, cptySum);
		stringStringMap.put(PropertyMapping.C_NOTE, cNote);
		stringStringMap.put(PropertyMapping.D_NOTE, dNote);
		stringMapMap.put(retPeriod, stringStringMap);
	}

	private static void updateCDNRValue2(Map<String, Map<String, Object>> stringMapMap, CptySumGstr2a cptySum) {

		if (stringMapMap.containsKey(PropertyMapping.C_NOTE)) {
			addCreditInfo(stringMapMap.get(PropertyMapping.C_NOTE), cptySum);
		}
		if (stringMapMap.containsKey(PropertyMapping.D_NOTE)) {
			addDeditInfo(stringMapMap.get(PropertyMapping.D_NOTE), cptySum);
		}
	}

	private static void updateCDNRValue2(Map<String, Map<String, Object>> stringMapMap, Cdn cdn) {

		if (stringMapMap.containsKey(PropertyMapping.C_NOTE)) {
			addCreditInfo(stringMapMap.get(PropertyMapping.C_NOTE), cdn);
		}
		if (stringMapMap.containsKey(PropertyMapping.D_NOTE)) {
			addDeditInfo(stringMapMap.get(PropertyMapping.D_NOTE), cdn);
		}
	}

	private static void addCreditInfo(Map<String, Object> countValueMap, Cdn cdn) {
		addValue(countValueMap, cdn, PropertyMapping.C_NOTE);
	}

	private static void addCreditInfo(Map<String, Object> countValueMap, CptySumGstr2a cptySum) {
		addValue(countValueMap, cptySum, PropertyMapping.C_NOTE);
	}

	private static void addValue(Map<String, Object> countValueMap, Cdn cdn, String type) {

		Double cgst = 0.0;
		Double igst = 0.0;
		Double sgst = 0.0;
		Double cess = 0.0;
		Double tax = 0.0;
		Double val = 0.0;

		Nt[] nts = cdn.getNt();
		for (Nt nt : nts) {
			if (type.equals(nt.getNtty())) {
				for (com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Itms itm : nt.getItms()) {
					igst += itm.getItm_det().getIamt();
					sgst += itm.getItm_det().getSamt();
					cgst += itm.getItm_det().getCamt();
					cess += itm.getItm_det().getCsamt();
					tax += itm.getItm_det().getTxval();
				}
				val += nt.getVal();
			}
		}
		countValueMap.put(PropertyMapping.CGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.CGST, 0.0))) + cgst);
		countValueMap.put(PropertyMapping.IGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.IGST, 0.0))) + igst);
		countValueMap.put(PropertyMapping.SGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.SGST, 0.0))) + sgst);
		countValueMap.put(PropertyMapping.CESS,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.CESS, 0.0))) + cess);
		countValueMap.put(PropertyMapping.TAX,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.TAX, 0.0))) + tax);
		countValueMap.put(PropertyMapping.VAL,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.VAL, 0.0))) + val);
	}

	private static void addValue(Map<String, Object> countValueMap, CptySumGstr2a cptySum, String type) {
		Double cgst = 0.0;
		Double igst = 0.0;
		Double sgst = 0.0;
		Double cess = 0.0;
		Double tax = 0.0;
		Double val = 0.0;

		com.opl.mudra.api.gst.model.karza.Nt[] nts = cptySum.getNt();
		for (com.opl.mudra.api.gst.model.karza.Nt nt : nts) {
			if (type.equals(nt.getNtty())) {
				cgst += nt.getCamt();
				igst += nt.getIamt();
				sgst += nt.getSamt();
				cess += nt.getCsamt();
				tax += nt.getTxval();
				val += nt.getVal();
			}
		}
		countValueMap.put(PropertyMapping.CGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.CGST, 0.0))) + cgst);
		countValueMap.put(PropertyMapping.IGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.IGST, 0.0))) + igst);
		countValueMap.put(PropertyMapping.SGST,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.SGST, 0.0))) + sgst);
		countValueMap.put(PropertyMapping.CESS,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.CESS, 0.0))) + cess);
		countValueMap.put(PropertyMapping.TAX,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.TAX, 0.0))) + tax);
		countValueMap.put(PropertyMapping.VAL,
				Double.valueOf(String.valueOf(countValueMap.getOrDefault(PropertyMapping.VAL, 0.0))) + val);
	}

	private static void addDeditInfo(Map<String, Object> countValueMap, CptySumGstr2a cptySum) {
		addValue(countValueMap, cptySum, PropertyMapping.D_NOTE);
	}

	private static void addDeditInfo(Map<String, Object> countValueMap, Cdn cdn) {
		addValue(countValueMap, cdn, PropertyMapping.D_NOTE);
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Object>>> generateDetailsCalcForB2BPurchaseKarza(
			KarzaGstResponse d) {

		Map<String, Map<String, Map<String, Object>>> m_ttl2 = new HashMap<>();
		if (isValidPurchaseKarzaGstPreviousResponse(d)) {
			for (DetailsGstr2a rsp : d.getResult().getPrevious().getGstr2a().getDetailsGstr2a()) {
				if (rsp.getSecSum() != null) {
					for (SecSumGstr2a secSum : rsp.getSecSum()) {
						if (PropertyMapping.B2B.equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
							for (CptySumGstr2a cptySum : secSum.getCptySum()) {
								if (m_ttl2.containsKey(cptySum.getCtin())) {
									updateValue(m_ttl2.get(cptySum.getCtin()), cptySum, rsp.getRetPeriod());
								} else {
									addNewValue(m_ttl2, cptySum, rsp.getRetPeriod());
								}
							}
						}
					}
				}
			}
		}

		if (isValidPurchaseKarzaGstCurrentResponse(d)) {
			for (DetailsGstr2a rsp : d.getResult().getCurrent().getGstr2a().getDetailsGstr2a()) {
				if (rsp.getSecSum() != null) {
					for (SecSumGstr2a secSum : rsp.getSecSum()) {
						if (PropertyMapping.B2B.equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
							for (CptySumGstr2a cptySum : secSum.getCptySum()) {
								if (m_ttl2.containsKey(cptySum.getCtin())) {
									updateValue(m_ttl2.get(cptySum.getCtin()), cptySum, rsp.getRetPeriod());
								} else {
									addNewValue(m_ttl2, cptySum, rsp.getRetPeriod());
								}
							}
						}
					}
				}
			}
		}
		return m_ttl2;
	}

	private static void addNewValue(Map<String, Map<String, Map<String, Object>>> stringMapMap, CptySumGstr2a cptySum,
			String retPeriod) {

		Map<String, Map<String, Object>> stringObjectMap1 = new HashMap<>();
		Map<String, Object> stringObjectMap = new HashMap<>();
		stringObjectMap.put(PropertyMapping.RET_PERIOD, retPeriod);
		stringObjectMap.put(PropertyMapping.TTL_SGST, cptySum.getTtlSgst());
		stringObjectMap.put(PropertyMapping.TTL_CGST, cptySum.getTtlCgst());
		stringObjectMap.put(PropertyMapping.TTL_IGST, cptySum.getTtlIgst());
		stringObjectMap.put(PropertyMapping.TTL_CESS, cptySum.getTtlCess());
		stringObjectMap.put(PropertyMapping.TTL_REC, cptySum.getTtlRec());
		stringObjectMap.put(PropertyMapping.TTL_TAX, cptySum.getTtlTax());
		stringObjectMap.put(PropertyMapping.TTL_VAL, cptySum.getTtlVal());
		Double percOfTtlSales = 0.0;
		if (Double.isFinite(cptySum.getTtlVal()) && Double.isFinite(cptySum.getTtlRec())) {
			if (cptySum.getTtlRec() == 0.0)
				percOfTtlSales = ((cptySum.getTtlVal() / 1.0) * 100);
			else
				percOfTtlSales = ((cptySum.getTtlVal() / cptySum.getTtlRec()) * 100);
		}
		stringObjectMap.put(PropertyMapping.PER_OF_PURCHASE, Double.isFinite(percOfTtlSales) ? percOfTtlSales : 0.0);
		stringObjectMap.put(PropertyMapping.CNAME, cptySum.getcName());
		stringObjectMap1.put(retPeriod, stringObjectMap);
		stringMapMap.put(cptySum.getCtin(), stringObjectMap1);
	}

	private static void updateValue(Map<String, Map<String, Object>> m_ttl2, CptySumGstr2a cptySum, String retPeriod) {

		Map<String, Object> m_ttl21 = new HashMap<>();
		if (m_ttl2.containsKey(retPeriod)) {
			for (Map.Entry<String, Map<String, Object>> mapEntry : m_ttl2.entrySet()) {
				Map<String, Object> map = mapEntry.getValue();
				m_ttl21.put(PropertyMapping.TTL_CGST,
						((Double) map.get(PropertyMapping.TTL_CGST)) + cptySum.getTtlCgst());
				m_ttl21.put(PropertyMapping.TTL_SGST,
						((Double) map.get(PropertyMapping.TTL_SGST)) + cptySum.getTtlSgst());
				m_ttl21.put(PropertyMapping.TTL_IGST,
						((Double) map.get(PropertyMapping.TTL_IGST)) + cptySum.getTtlIgst());
				m_ttl21.put(PropertyMapping.TTL_VAL, ((Double) map.get(PropertyMapping.TTL_VAL)) + cptySum.getTtlVal());
				m_ttl21.put(PropertyMapping.TTL_TAX, ((Double) map.get(PropertyMapping.TTL_TAX)) + cptySum.getTtlTax());
				m_ttl21.put(PropertyMapping.TTL_REC, ((Double) map.get(PropertyMapping.TTL_REC)) + cptySum.getTtlRec());
				m_ttl21.put(PropertyMapping.TTL_CESS,
						((Double) map.get(PropertyMapping.TTL_CESS)) + cptySum.getTtlCess());
				m_ttl21.put(PropertyMapping.CNAME, cptySum.getcName());
				Double ttlVal = (Double) m_ttl21.get(PropertyMapping.TTL_VAL);
				Double ttlRec = (Double) m_ttl21.get(PropertyMapping.TTL_CESS);
				Double percOfTtlSales = 0.0;
				if (Double.isFinite(ttlVal) && Double.isFinite(ttlRec)) {
					if (ttlRec == 0.0)
						ttlRec = 1.0;
					percOfTtlSales = ((ttlVal / ttlRec) * 100);
				}
				m_ttl21.put(PropertyMapping.PER_OF_PURCHASE, Double.isFinite(percOfTtlSales) ? percOfTtlSales : 0.0);
				m_ttl2.put(mapEntry.getKey(), m_ttl21);
			}
		} else {
			m_ttl21.put(PropertyMapping.TTL_CGST, cptySum.getTtlCgst());
			m_ttl21.put(PropertyMapping.TTL_SGST, cptySum.getTtlSgst());
			m_ttl21.put(PropertyMapping.TTL_IGST, cptySum.getTtlIgst());
			m_ttl21.put(PropertyMapping.TTL_VAL, cptySum.getTtlVal());
			m_ttl21.put(PropertyMapping.TTL_TAX, cptySum.getTtlTax());
			m_ttl21.put(PropertyMapping.TTL_REC, cptySum.getTtlRec());
			m_ttl21.put(PropertyMapping.TTL_CESS, cptySum.getTtlCess());
			m_ttl21.put(PropertyMapping.CNAME, cptySum.getcName());
			Double ttlVal = (Double) m_ttl21.get(PropertyMapping.TTL_VAL);
			Double ttlRec = (Double) m_ttl21.get(PropertyMapping.TTL_CESS);
			Double percOfTtlSales = 0.0;
			if (Double.isFinite(ttlVal) && Double.isFinite(ttlRec)) {
				if (ttlRec == 0.0)
					ttlRec = 1.0;
				percOfTtlSales = ((ttlVal / ttlRec) * 100);
			}
			m_ttl21.put(PropertyMapping.PER_OF_PURCHASE, Double.isFinite(percOfTtlSales) ? percOfTtlSales : 0.0);
			m_ttl2.put(retPeriod, m_ttl21);
		}
	}

	private static void updateValue(Map<String, Map<String, Object>> m_ttl2, B2b b2b, String retPeriod) {

		Map<String, Object> stringObjectMap = m_ttl2.get(retPeriod);
		Inv[] invs = b2b.getInv();
		Double val = 0.0;
		Double txtVal = 0.0;
		Double igst = 0.0;
		Double sgst = 0.0;
		Double cgst = 0.0;
		Double cess = 0.0;
		Double rec = 0.0;
		for (Inv inv : invs) {
			Itms[] items = inv.getItms();
			for (Itms itm : items) {
				igst += itm.getItm_det().getIamt();
				sgst += itm.getItm_det().getSamt();
				cgst += itm.getItm_det().getCamt();
				cess += itm.getItm_det().getCsamt();
				txtVal += itm.getItm_det().getTxval();
//                rec+=itm.getItm_det().getRt();
			}
			val += inv.getVal();
		}
		if (!isObjectNullOrEmpty(invs)) {
			rec = Double.valueOf(invs.length);
		}
		stringObjectMap.put(PropertyMapping.TTL_CESS,
				(((Double) stringObjectMap.get(PropertyMapping.TTL_CESS)) + cess));
		stringObjectMap.put(PropertyMapping.TTL_TAX,
				(((Double) stringObjectMap.get(PropertyMapping.TTL_TAX)) + txtVal));
		stringObjectMap.put(PropertyMapping.TTL_VAL, (((Double) stringObjectMap.get(PropertyMapping.TTL_VAL)) + val));
		stringObjectMap.put(PropertyMapping.TTL_CGST,
				(((Double) stringObjectMap.get(PropertyMapping.TTL_CGST)) + cgst));
		stringObjectMap.put(PropertyMapping.TTL_IGST,
				(((Double) stringObjectMap.get(PropertyMapping.TTL_IGST)) + igst));
		stringObjectMap.put(PropertyMapping.TTL_SGST,
				(((Double) stringObjectMap.get(PropertyMapping.TTL_SGST)) + sgst));
		stringObjectMap.put(PropertyMapping.TTL_REC, (((Double) stringObjectMap.get(PropertyMapping.TTL_REC)) + rec));
		m_ttl2.put(retPeriod, stringObjectMap);
	}

	/**
	 * @param d
	 * @return
	 */
	public static Map<String, Map<String, Map<String, Double>>> generateDetailsCalcForB2BKarza(KarzaGstResponse d) {

		Map<String, Map<String, Map<String, Double>>> m = new HashMap<>();
		if (isValidKarzaGstPreviousResponse(d)) {
			for (Details rsp : d.getResult().getPrevious().getGstr1().getDetails()) {
				addValues(rsp, m, rsp.getRetPeriod());
			}
		}
		if (isValidKarzaGstCurrentResponse(d)) {
			for (Details rsp : d.getResult().getCurrent().getGstr1().getDetails()) {
				addValues(rsp, m, rsp.getRetPeriod());
			}
		}
		return m;
	}

	static boolean isValidPurchaseKarzaGstCurrentResponse(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getCurrent() != null
				&& d.getResult().getCurrent().getGstr2a() != null
				&& d.getResult().getCurrent().getGstr2a().getDetailsGstr2a() != null
				&& d.getResult().getCurrent().getGstr2a().getDetailsGstr2a().length > 0;
	}

	static boolean isValidPurchaseKarzaGstPreviousResponse(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getPrevious() != null
				&& d.getResult().getPrevious().getGstr1() != null
				&& d.getResult().getPrevious().getGstr2a().getDetailsGstr2a() != null
				&& d.getResult().getPrevious().getGstr2a().getDetailsGstr2a().length > 0;
	}

	static boolean isValidKarzaGstCurrentResponse(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getCurrent() != null
				&& d.getResult().getCurrent().getGstr1() != null
				&& d.getResult().getCurrent().getGstr1().getDetails() != null
				&& d.getResult().getCurrent().getGstr1().getDetails().length > 0;
	}

	static boolean isValidKarzaGstPreviousResponse(KarzaGstResponse d) {
		return d != null && d.getResult() != null && d.getResult().getPrevious() != null
				&& d.getResult().getPrevious().getGstr1() != null
				&& d.getResult().getPrevious().getGstr1().getDetails() != null
				&& d.getResult().getPrevious().getGstr1().getDetails().length > 0;
	}

	static void addValues(Map<String, Map<String, Map<String, Map<String, Object>>>> stringObjectMap2, Cdn cdn,
			String retPeriod) {

		Map<String, Map<String, Map<String, Object>>> stringObjectMap = new HashMap<>();
		Map<String, Map<String, Object>> stringObjectMap1 = new HashMap<>();
		Nt[] nts = cdn.getNt();
		for (Nt nt : nts) {
			Nt[] invs = cdn.getNt();
			Double val = 0.0;
			Double txtVal = 0.0;
			Double igst = 0.0;
			Double sgst = 0.0;
			Double cgst = 0.0;
			Double cess = 0.0;
			for (Nt inv : invs) {
				com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Itms[] items = inv.getItms();
				for (com.opl.mudra.api.gst.model.yuva.response.gstr2acdn.Itms itm : items) {
					igst += itm.getItm_det().getIamt();
					sgst += itm.getItm_det().getSamt();
					cgst += itm.getItm_det().getCamt();
					cess += itm.getItm_det().getCsamt();
					txtVal += itm.getItm_det().getTxval();
				}
				val += inv.getVal();
			}
			if (stringObjectMap1.containsKey(nt.getNtty())) {
				Map<String, Object> countValueMap = stringObjectMap1.get(nt.getNtty());
				countValueMap.put(PropertyMapping.CGST,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.CGST))) + cgst);
				countValueMap.put(PropertyMapping.IGST,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.IGST))) + igst);
				countValueMap.put(PropertyMapping.SGST,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.SGST))) + sgst);
				countValueMap.put(PropertyMapping.CESS,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.CESS))) + cess);
				countValueMap.put(PropertyMapping.TAX,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.TAX))) + txtVal);
				countValueMap.put(PropertyMapping.VAL,
						Double.valueOf(String.valueOf(countValueMap.get(PropertyMapping.VAL))) + val);
				stringObjectMap1.put(nt.getNtty(), countValueMap);
			} else {
				Map<String, Object> countValueMap = new HashMap<>();
				countValueMap.put(PropertyMapping.CGST, cgst);
				countValueMap.put(PropertyMapping.IGST, igst);
				countValueMap.put(PropertyMapping.SGST, sgst);
				countValueMap.put(PropertyMapping.CESS, cess);
				countValueMap.put(PropertyMapping.TAX, txtVal);
				countValueMap.put(PropertyMapping.VAL, val);
				stringObjectMap1.put(nt.getNtty(), countValueMap);
			}
		}
		stringObjectMap.put(retPeriod, stringObjectMap1);
		stringObjectMap2.put(cdn.getCtin(), stringObjectMap);
	}

	static void addValues(Map<String, Map<String, Map<String, Object>>> stringMapMap, B2b b2b, String retPeriod) {
		Inv[] invs = b2b.getInv();
		Double val = 0.0;
		Double txtVal = 0.0;
		Double igst = 0.0;
		Double sgst = 0.0;
		Double cgst = 0.0;
		Double cess = 0.0;
		Double rec = 0.0;
		for (Inv inv : invs) {
			Itms[] items = inv.getItms();
			for (Itms itm : items) {
				igst += itm.getItm_det().getIamt();
				sgst += itm.getItm_det().getSamt();
				cgst += itm.getItm_det().getCamt();
				cess += itm.getItm_det().getCsamt();
				txtVal += itm.getItm_det().getTxval();
//                rec+=itm.getItm_det().getRt();

			}
			val += inv.getVal();
		}
		if (!isObjectNullOrEmpty(invs)) {
			rec = Double.valueOf(invs.length);
		}
		Map<String, Object> stringObjectMap = new HashMap<>();
		Map<String, Map<String, Object>> stringMapMap1 = new HashMap<>();
		stringObjectMap.put(PropertyMapping.TTL_CESS, cess);
		stringObjectMap.put(PropertyMapping.TTL_TAX, txtVal);
		stringObjectMap.put(PropertyMapping.TTL_VAL, val);
		stringObjectMap.put(PropertyMapping.TTL_CGST, cgst);
		stringObjectMap.put(PropertyMapping.TTL_IGST, igst);
		stringObjectMap.put(PropertyMapping.TTL_SGST, sgst);
		stringObjectMap.put(PropertyMapping.TTL_REC, rec);
		stringMapMap1.put(retPeriod, stringObjectMap);
		stringMapMap.put(b2b.getCtin(), stringMapMap1);
	}

	static void addValues(SecSum secSum, List<Map<String, Double>> list) {
		Map<String, Double> m = new HashMap<>();
		m.put(PropertyMapping.CESS, secSum.getTtlCess());
		m.put(PropertyMapping.CGST, secSum.getTtlCgst());
		m.put(PropertyMapping.IGST, secSum.getTtlIgst());
		m.put(PropertyMapping.STATE_CODE, 1.0);
		m.put(PropertyMapping.AMT_OF_ALl_INC, secSum.getTtlExptAmt());
		m.put(PropertyMapping.TAX, secSum.getTtlTax());
		m.put(PropertyMapping.REC, secSum.getTtlRec());
		m.put(PropertyMapping.VAL, secSum.getTtlVal());
		/*
		 * if(Double.isFinite(secSum.getTtlVal()) &&
		 * Double.isFinite(secSum.getTtlRec())) { Double percOfTtlSales =
		 * ((secSum.getTtlVal() / secSum.getTtlRec())*100);
		 * m.put(PropertyMapping.PER_OF_SALES,Double.isFinite(percOfTtlSales)?
		 * percOfTtlSales:0.0); } else { m.put(PropertyMapping.PER_OF_SALES,0.0); }
		 */
		list.add(m);
	}

	static void addOrUpdateValue(CptySum cptySum, Map<String, Map<String, Map<String, Double>>> m, String column,
			Double value, String retPeriod) {

		Map<String, Double> s = new HashMap<>();
		Map<String, Map<String, Double>> s1 = new HashMap<>();
		if (m.containsKey(cptySum.getCtin())) {
			if (m.get(cptySum.getCtin()).containsKey(retPeriod)) {
				s1 = m.get(cptySum.getCtin());
				s = s1.get(retPeriod);
				if (s.containsKey(column))
					value += s.get(column);
			} else {
				s1 = m.get(cptySum.getCtin());
				s1.putAll(m.get(cptySum.getCtin()));
			}
		}
		s.put(column, value);
		s1.put(retPeriod, s);
		m.put(cptySum.getCtin(), s1);
	}

	static void addValues(Details rsp, Map<String, Map<String, Map<String, Double>>> m, String retPeriod) {

		if (rsp.getSecSum() != null) {
			for (SecSum secSum : rsp.getSecSum()) {
				if (PropertyMapping.B2B.equals(secSum.getSecNm()) && secSum.getCptySum() != null) {
					for (CptySum cptySum : secSum.getCptySum()) {
						addOrUpdateValue(cptySum, m, PropertyMapping.REC, cptySum.getTtlRec(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.VAL, cptySum.getTtlVal(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.TAX, cptySum.getTtlTax(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.IGST, cptySum.getTtlIgst(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.CGST, cptySum.getTtlCgst(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.SGST, cptySum.getTtlSgst(), retPeriod);
						addOrUpdateValue(cptySum, m, PropertyMapping.CESS, cptySum.getTtlCess(), retPeriod);

						/*
						 * Double percOfTtlSales =0.0; if (Double.isFinite(cptySum.getTtlVal()) &&
						 * Double.isFinite(cptySum.getTtlRec())) { percOfTtlSales =
						 * ((cptySum.getTtlVal() / cptySum.getTtlRec()) * 100); }
						 * addOrUpdateValue(cptySum,m,PropertyMapping.PER_OF_SALES,Double.isFinite(
						 * percOfTtlSales)?percOfTtlSales:0.0,retPeriod);
						 */
					}
				}
			}
		}
	}

	public static boolean isValidPurchaseCurrentKarzaGstResponse3b(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getCurrent() != null
				&& d.getResult().getCurrent().getGstr3b() != null
				&& d.getResult().getCurrent().getGstr3b().getGstr3details() != null
				&& d.getResult().getCurrent().getGstr3b().getGstr3details().length > 0;
	}

	public static boolean isValidPurchasePreviousKarzaGstResponse3b(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getPrevious() != null
				&& d.getResult().getPrevious().getGstr3b() != null
				&& d.getResult().getPrevious().getGstr3b().getGstr3details() != null
				&& d.getResult().getPrevious().getGstr3b().getGstr3details().length > 0;
	}

	public static boolean isValidPurchaseKarzaGstResponse3b(KarzaGstResponse d) {

		return d != null && d.getResult() != null && d.getResult().getCurrent() != null
				&& d.getResult().getPrevious() != null && d.getResult().getCurrent().getGstr3b() != null
				&& d.getResult().getPrevious().getGstr3b() != null
				&& d.getResult().getCurrent().getGstr3b().getGstr3details() != null
				&& d.getResult().getCurrent().getGstr3b().getGstr3details().length > 0
				&& d.getResult().getPrevious().getGstr3b().getGstr3details() != null
				&& d.getResult().getPrevious().getGstr3b().getGstr3details().length > 0;
	}

	public static void addValuesFor3b(Gstr3Details rsp, Map<String, Object> m_ttl, String retPeriod) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupDet() != null) {
			m_ttl.put(TTL_VAL,
					rsp.getSupDetails().getOsupDet().getTxval() != null ? rsp.getSupDetails().getOsupDet().getTxval()
							: 0.0);
			m_ttl.put(IAMT,
					rsp.getSupDetails().getOsupDet().getIamt() != null ? rsp.getSupDetails().getOsupDet().getIamt()
							: 0.0);
			m_ttl.put(CAMT,
					rsp.getSupDetails().getOsupDet().getCamt() != null ? rsp.getSupDetails().getOsupDet().getCamt()
							: 0.0);
			m_ttl.put(SAMT,
					rsp.getSupDetails().getOsupDet().getSamt() != null ? rsp.getSupDetails().getOsupDet().getSamt()
							: 0.0);
			m_ttl.put(CSAMT,
					rsp.getSupDetails().getOsupDet().getCsamt() != null ? rsp.getSupDetails().getOsupDet().getCsamt()
							: 0.0);
			m_ttl.put(RET_PERIOD, retPeriod);
		}
	}

	public static void addValuesFor3b(GSTR3BSummary rsp, Map<String, Object> m_ttl, String retPeriod) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupDet() != null) {
			m_ttl.put(TTL_VAL,
					rsp.getSupDetails().getOsupDet().getTxval() != null ? rsp.getSupDetails().getOsupDet().getTxval()
							: 0.0);
			m_ttl.put(IAMT,
					rsp.getSupDetails().getOsupDet().getIamt() != null ? rsp.getSupDetails().getOsupDet().getIamt()
							: 0.0);
			m_ttl.put(CAMT,
					rsp.getSupDetails().getOsupDet().getCamt() != null ? rsp.getSupDetails().getOsupDet().getCamt()
							: 0.0);
			m_ttl.put(SAMT,
					rsp.getSupDetails().getOsupDet().getSamt() != null ? rsp.getSupDetails().getOsupDet().getSamt()
							: 0.0);
			m_ttl.put(CSAMT,
					rsp.getSupDetails().getOsupDet().getCsamt() != null ? rsp.getSupDetails().getOsupDet().getCsamt()
							: 0.0);
			m_ttl.put(RET_PERIOD, retPeriod);
		}
	}

	public static void addValuesFor3bZero(Gstr3Details rsp, Map<String, Object> m_ttl, String retPeriod) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupZero() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupZero().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getOsupZero().getIamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getOsupZero().getCsamt());
			m_ttl.put(RET_PERIOD, retPeriod);
		}
	}

	public static void addValuesFor3bZero(GSTR3BSummary rsp, Map<String, Object> m_ttl, String retPeriod) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupZero() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupZero().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getOsupZero().getIamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getOsupZero().getCsamt());
			m_ttl.put(RET_PERIOD, retPeriod);
		}
	}

	public static void addValuesFor3bExp(Gstr3Details rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupNilExmp() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNilExmp().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getOsupNilExmp().getIamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getOsupNilExmp().getCsamt());
		}
	}

	public static void addValuesFor3bExp(GSTR3BSummary rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupNilExmp() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNilExmp().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getOsupNilExmp().getIamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getOsupNilExmp().getCsamt());
		}
	}

	public static void addValuesFor3bInw(Gstr3Details rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getIsupRev() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getIsupRev().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getIsupRev().getIamt());
			m_ttl.put(CAMT, rsp.getSupDetails().getIsupRev().getCamt());
			m_ttl.put(SAMT, rsp.getSupDetails().getIsupRev().getSamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getIsupRev().getCsamt());
		}
	}

	public static void addValuesFor3bInw(GSTR3BSummary rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getIsupRev() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getIsupRev().getTxval());
			m_ttl.put(IAMT, rsp.getSupDetails().getIsupRev().getIamt());
			m_ttl.put(CAMT, rsp.getSupDetails().getIsupRev().getCamt());
			m_ttl.put(SAMT, rsp.getSupDetails().getIsupRev().getSamt());
			m_ttl.put(CSAMT, rsp.getSupDetails().getIsupRev().getCsamt());
		}
	}

	public static void addValuesFor3bOutWrdNonGST(Gstr3Details rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupNongst() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
		}
	}

	public static void addValuesFor3bOutWrdNonGST(GSTR3BSummary rsp, Map<String, Double> m_ttl) {
		if (rsp.getSupDetails() != null && rsp.getSupDetails().getOsupNongst() != null) {
			m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
		}
	}

	public static void addValuesFor3bSuppMadeUnregPer(Gstr3Details rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInterSup() != null && rsp.getInterSup().getUnregDetails() != null
				&& rsp.getInterSup().getUnregDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (UnregDetails unReg : rsp.getInterSup().getUnregDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put(IAMT, unReg.getIamt());
				m.put(TXVAL, unReg.getTxval());
				m.put("pos", unReg.getPos());
				m_ttl.add(m);
			}
		}
	}

	public static void addValuesFor3bSuppMadeUnregPer(GSTR3BSummary rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInterSup() != null && rsp.getInterSup().getUnregDetails() != null
				&& rsp.getInterSup().getUnregDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.UnregDetails unReg : rsp.getInterSup()
					.getUnregDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put(IAMT, unReg.getIamt());
				m.put(TXVAL, unReg.getTxval());
				m.put("pos", unReg.getPos());
				m_ttl.add(m);
			}
		}
	}

	/*
	 * public static void addValuesFor3bSuppMadeUnregPer(Gstr3Details rsp,
	 * List<Map<String, Object>> m_ttl,String retPeriod) { if (rsp.getInterSup() !=
	 * null && rsp.getInterSup().getCompDetails()!=null &&
	 * rsp.getInterSup().getCompDetails().length>0) { //m_ttl.put(TTL_VAL,
	 * rsp.getSupDetails().getOsupNongst().getTxval()); for(CompDetails unReg :
	 * rsp.getInterSup().getCompDetails()) { Map<String, Object> m = new
	 * HashMap<String, Object>(); m.put(IAMT, unReg.getIamt()); m.put(TXVAL,
	 * unReg.getTxval()); m.put("pos", unReg.getPos()); m_ttl.add(m); } } }
	 */
	public static void addValuesFor3bSuppMadeUinHolder(Gstr3Details rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInterSup() != null && rsp.getInterSup().getUinDetails() != null
				&& rsp.getInterSup().getUinDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (UinDetails uinDelt : rsp.getInterSup().getUinDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put(IAMT, uinDelt.getIamt());
				m.put(TXVAL, uinDelt.getTxval());
				m.put("pos", uinDelt.getPos());
				m_ttl.add(m);
			}
		}
	}

	public static void addValuesFor3bSuppMadeUinHolder(GSTR3BSummary rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInterSup() != null && rsp.getInterSup().getUinDetails() != null
				&& rsp.getInterSup().getUinDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.UinDetails uinDelt : rsp.getInterSup()
					.getUinDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put(IAMT, uinDelt.getIamt());
				m.put(TXVAL, uinDelt.getTxval());
				m.put("pos", uinDelt.getPos());
				m_ttl.add(m);
			}
		}
	}

	public static void addValuesFor3bGstExempt(Gstr3Details rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInwardSup() != null && rsp.getInwardSup().getIsupDetails() != null
				&& rsp.getInwardSup().getIsupDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (com.opl.mudra.api.gst.model.karza.IsupDetails uinDelt : rsp.getInwardSup().getIsupDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("inter", uinDelt.getInter());
				m.put("intra", uinDelt.getIntra());
				m.put("ty", uinDelt.getTy());
				m_ttl.add(m);
			}
		}
	}

	public static void addValuesFor3bGstExempt(GSTR3BSummary rsp, List<Map<String, Object>> m_ttl) {
		if (rsp.getInwardSup() != null && rsp.getInwardSup().getIsupDetails() != null
				&& rsp.getInwardSup().getIsupDetails().length > 0) {
			// m_ttl.put(TTL_VAL, rsp.getSupDetails().getOsupNongst().getTxval());
			for (com.opl.mudra.api.gst.model.yuva.response.gstr3bsummary.IsupDetails uinDelt : rsp.getInwardSup()
					.getIsupDetails()) {
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("inter", uinDelt.getInter());
				m.put("intra", uinDelt.getIntra());
				m.put("ty", uinDelt.getTy());
				m_ttl.add(m);
			}
		}
	}

	public static void addSummTaxPayrs(TxPmt rsp, Map<String, Double> m_ttl) {
		if (rsp.getTxPy() != null && rsp.getTxPy().length > 0) {
			for (TxPy txpy : rsp.getTxPy()) {
				if (m_ttl.containsKey(TAX_PAYBLE)) {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put(TAX_PAYBLE, m_ttl.get(TAX_PAYBLE) + txpy.getCgst().getTx() + txpy.getIgst().getTx()
								+ txpy.getSgst().getTx() + txpy.getCess().getTx());
					} else {
						m_ttl.put(TAX_PAYBLE, m_ttl.get(TAX_PAYBLE) + 0.0);
					}

				}

				else {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put(TAX_PAYBLE, txpy.getCgst().getTx() + txpy.getIgst().getTx() + txpy.getSgst().getTx()
								+ txpy.getCess().getTx());
					} else {
						m_ttl.put(TAX_PAYBLE, 0.0);
					}

				}
			}
		} else {
			m_ttl.put(TAX_PAYBLE, 0.0);
		}

		if (rsp.getPditc() != null) {
			m_ttl.put("paid_thrgh_itc",
					(rsp.getPditc().getiPdi() != null ? rsp.getPditc().getiPdi() : 0.0)
							+ (rsp.getPditc().getiPdc() != null ? rsp.getPditc().getiPdc() : 0.0)
							+ (rsp.getPditc().getiPds() != null ? rsp.getPditc().getiPds() : 0.0)
							+ (rsp.getPditc().getcPdi() != null ? rsp.getPditc().getcPdi() : 0.0)
							+ (rsp.getPditc().getcPdc() != null ? rsp.getPditc().getcPdc() : 0.0)
							+ (rsp.getPditc().getsPdi() != null ? rsp.getPditc().getsPdi() : 0.0)
							+ (rsp.getPditc().getsPds() != null ? rsp.getPditc().getsPds() : 0.0)
							+ (rsp.getPditc().getCsPdcs() != null ? rsp.getPditc().getCsPdcs() : 0.0));
		} else {
			m_ttl.put("paid_thrgh_itc", 0.0);
		}

		if (rsp.getPdcash() != null && rsp.getPdcash().length > 0) {
			for (PdCash pdc : rsp.getPdcash()) {
				if (m_ttl.containsKey(PD_CASH)) {
					m_ttl.put(PD_CASH, m_ttl.get(PD_CASH) + (pdc.getIpd() != null ? pdc.getIpd() : 0.0)
							+ (pdc.getCpd() != null ? pdc.getCpd() : 0.0) + (pdc.getSpd() != null ? pdc.getSpd() : 0.0)
							+ (pdc.getCspd() != null ? pdc.getCspd() : 0.0)
							+ (pdc.getiIntrpd() != null ? pdc.getiIntrpd() : 0.0)
							+ (pdc.getcIntrpd() != null ? pdc.getcIntrpd() : 0.0)
							+ (pdc.getsIntrpd() != null ? pdc.getsIntrpd() : 0.0)
							+ (pdc.getCsIntrpd() != null ? pdc.getCsIntrpd() : 0.0));
				} else {
					m_ttl.put(PD_CASH,
							(pdc.getIpd() != null ? pdc.getIpd() : 0.0) + (pdc.getCpd() != null ? pdc.getCpd() : 0.0)
									+ (pdc.getSpd() != null ? pdc.getSpd() : 0.0)
									+ (pdc.getCspd() != null ? pdc.getCspd() : 0.0)
									+ (pdc.getiIntrpd() != null ? pdc.getiIntrpd() : 0.0)
									+ (pdc.getcIntrpd() != null ? pdc.getcIntrpd() : 0.0)
									+ (pdc.getsIntrpd() != null ? pdc.getsIntrpd() : 0.0)
									+ (pdc.getCsIntrpd() != null ? pdc.getCsIntrpd() : 0.0));
				}
			}
		} else {
			m_ttl.put(PD_CASH, 0.0);
		}

		if (rsp.getTxPy() != null && rsp.getTxPy().length > 0) {
			for (TxPy txpy : rsp.getTxPy()) {
				if (m_ttl.containsKey("int")) {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put("int", m_ttl.get("int") + txpy.getCgst().getIntr() + txpy.getIgst().getIntr()
								+ txpy.getSgst().getIntr() + txpy.getCess().getIntr());
					} else {
						m_ttl.put("int", m_ttl.get("int") + 0.0);
					}

				}

				else {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put("int", txpy.getCgst().getIntr() + txpy.getIgst().getIntr() + txpy.getSgst().getIntr()
								+ txpy.getCess().getIntr());
					} else {
						m_ttl.put("int", 0.0);
					}

				}
			}
		} else {
			m_ttl.put("int", 0.0);
		}

		if (rsp.getTxPy() != null && rsp.getTxPy().length > 0) {
			for (TxPy txpy : rsp.getTxPy()) {
				if (m_ttl.containsKey(LATE_FEE)) {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put(LATE_FEE, m_ttl.get(LATE_FEE) + txpy.getCgst().getFee() + txpy.getIgst().getFee()
								+ txpy.getSgst().getFee() + txpy.getCess().getFee());
					} else {
						m_ttl.put(LATE_FEE, m_ttl.get(LATE_FEE) + 0.0);
					}

				}

				else {

					if (txpy.getCgst() != null && txpy.getIgst() != null && txpy.getSgst() != null
							&& txpy.getCess() != null) {
						m_ttl.put(LATE_FEE, txpy.getCgst().getFee() + txpy.getIgst().getFee() + txpy.getSgst().getFee()
								+ txpy.getCess().getFee());
					} else {
						m_ttl.put(LATE_FEE, 0.0);
					}

				}
			}
		} else {
			m_ttl.put(LATE_FEE, 0.0);
		}

	}

	/**
	 * @param det
	 * @param m_ttl
	 */
	public static void addSummTaxPayrs(Gstr3Details det, Map<String, Double> m_ttl) {
		if (det != null && det.getTtVal() != null) {
			m_ttl.put(TAX_PAYBLE, det.getTtVal().getTtPay());
			m_ttl.put("gst", det.getTtVal().getTtItcPd());
			m_ttl.put("cash", det.getTtVal().getTtCshPd());
		} else {
			m_ttl.put(TAX_PAYBLE, 0.0);
			m_ttl.put("gst", 0.0);
			m_ttl.put("cash", 0.0);
		}

		if (det != null && det.getIntrItfee() != null && det.getIntrItfee().getItfreeDetails() != null) {
			m_ttl.put(LATE_FEE,
					det.getIntrItfee().getItfreeDetails().getCsamt() + det.getIntrItfee().getItfreeDetails().getCamt()
							+ det.getIntrItfee().getItfreeDetails().getIamt()
							+ det.getIntrItfee().getItfreeDetails().getSamt());
		} else {
			m_ttl.put(LATE_FEE, 0.0);
		}
	}

	public static Double calc_median(List<Double> medianList) {

		Double median = 0.0;

		int a, b;

		int size = medianList.size();
		if (size != 0) {
			if (size % 2 == 0) {
				if (size == 2) {
					a = 0;
					b = a + 1;
				} else {
					a = size / 2;
					b = a + 1;
				}

				median = (medianList.get(a) + medianList.get(b)) / 2;
			} else {
				if (size == 1) {
					a = 0;
				} else {
					a = (size + 1) / 2;
				}

				median = (medianList.get(a));
			}
		}
		return median;

	}

	public static Double calcMedianGstr4(List<Double> medianList) {

		Double median = 0.0;

		int a, b;

		int size = medianList.size();
		if (size != 0) {
			if (size % 2 == 0) {
				if (size == 2) {
					a = 0;
					b = a + 1;
				} else {
					a = size / 2;
					b = a + 1;
				}

				median = (medianList.get(a - 1) + medianList.get(b - 1)) / 2;
			} else {
				if (size == 1) {
					a = 0;
				} else {
					a = (size + 1) / 2;
				}

				median = (medianList.get(a - 1));
			}
		}
		return median;

	}

	public static Object convertToDoubleForXml(Object obj, Map<String, Object> data) throws GstException {

		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(obj);
				if (data != null) {
					data.put(field.getName(), value);
				}
				if ((!CommonUtils.isObjectNullOrEmpty(value)) && (value instanceof Double)
						&& (!Double.isNaN((Double) value))) {
					DecimalFormat decim = new DecimalFormat("0.00");
					value = Double.parseDouble(decim.format(value));
					if (data != null) {
						value = decim.format(value);
						data.put(field.getName(), value);
					} else {
						field.set(obj, value);
					}
				}
			}
			if (data != null) {
				return data;
			}
			return obj;
		} catch (Exception e) {
			logger.error("Exception in convertToDoubleForXml()", e);
			throw new GstException(e);
		}
	}

	public static void generateDetailsCalcForCreDebInfoGstr1(Map<String, Object> creDebInfoGstr1,
			GSTR1CDNRInvoicesResponse rsp) {

		double cr = 0.0;
		double dr = 0.0;
		double rr = 0.0;
		double ttl = 0.0;
		if (!rsp.getStatusCd().equals("0")) {
			for (Cdnr cdnr : rsp.getCdnr()) {
				cr += addValue(cdnr, PropertyMapping.C_NOTE);
				dr += addValue(cdnr, PropertyMapping.D_NOTE);
				rr += addValue(cdnr, PropertyMapping.REFUND_VOUCHER);
			}
		}
		if (creDebInfoGstr1.containsKey(PropertyMapping.TOTAL))
			ttl = (Double) creDebInfoGstr1.get(PropertyMapping.TOTAL);

		ttl += cr + dr + rr;
		creDebInfoGstr1.put(PropertyMapping.C_REGISTERED, cr);
		creDebInfoGstr1.put(PropertyMapping.D_REGISTERED, dr);
		creDebInfoGstr1.put(PropertyMapping.R_REGISTERED, rr);
		creDebInfoGstr1.put(PropertyMapping.TOTAL, ttl);

	}

	public static void generateDetailsCalcForCreDebInfoGstr1(Map<String, Object> creDebInfoGstr1,
			GSTR1CDNURInvoicesResponse rsp) {

		double cr = 0.0;
		double dr = 0.0;
		double rr = 0.0;
		double ttl = 0.0;
		if (!rsp.getStatusCd().equals("0")) {
			for (Cdnur cdnur : rsp.getCdnur()) {
				cr += addValue(cdnur, PropertyMapping.C_NOTE);
				dr += addValue(cdnur, PropertyMapping.D_NOTE);
				rr += addValue(cdnur, PropertyMapping.REFUND_VOUCHER);
			}
		}
		if (creDebInfoGstr1.containsKey(PropertyMapping.TOTAL))
			ttl = (Double) creDebInfoGstr1.get(PropertyMapping.TOTAL);

		ttl += cr + dr + rr;
		creDebInfoGstr1.put(PropertyMapping.C_UNREGISTERED, cr);
		creDebInfoGstr1.put(PropertyMapping.D_UNREGISTERED, dr);
		creDebInfoGstr1.put(PropertyMapping.R_UNREGISTERED, rr);
		creDebInfoGstr1.put(PropertyMapping.TOTAL, ttl);

	}

	private static Double addValue(Cdnr cdnr, String type) {
		Double val = 0.0;
		com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices.Nt[] nts = cdnr.getNt();
		for (com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices.Nt nt : nts) {
			if (type.equals(nt.getNtty()) && (!CommonUtils.isObjectNullOrEmpty(nt.getVal()))
					&& (nt.getVal() instanceof Double) && (!Double.isNaN((Double) nt.getVal()))) {
				val += nt.getVal();
			}
		}
		return val;
	}

	private static Double addValue(Cdnur cdnur, String type) {
		Double val = 0.0;
		if (type.equals(cdnur.getNtty()) && (!CommonUtils.isObjectNullOrEmpty(cdnur.getVal()))
				&& (cdnur.getVal() instanceof Double) && (!Double.isNaN((Double) cdnur.getVal()))) {
			val += cdnur.getVal();
		}
		return val;
	}

	public static void calTotalCreDebInfoGstr1(GstCalcMappingTable gstMapTbl,
			Map<String, Map<String, Object>> creDebInfoGstr1) {

		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.C_REGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.C_REGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.C_UNREGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.C_UNREGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.D_REGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.D_REGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.D_UNREGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.D_UNREGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.R_REGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.R_REGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.R_UNREGISTERED,
				sumCDR(creDebInfoGstr1, PropertyMapping.R_UNREGISTERED));
		gstMapTbl.getCamgstData().getTotalCreDebInfoGstr1().put(PropertyMapping.TOTAL,
				sumCDR(creDebInfoGstr1, PropertyMapping.TOTAL));

	}

	private static double sumCDR(Map<String, Map<String, Object>> creDebInfoGstr1, String key) {
		return creDebInfoGstr1.values().stream().filter(info -> info.get(key) != null)
				.mapToDouble(v1 -> (Double) v1.get(key)).sum();
	}

	public static Map<String, Object> genCalcForMonthWisePurchaseGstr2(GSTR2SummaryResponse rsp, String retPeriod) {
		Map<String, Object> infoMap = null;

		double b2b = 0.0;
		double b2bUnrTp = 0.0;
		double impGoods = 0.0;
		double impServices = 0.0;
		double nilGoods = 0.0;
		double exeGoods = 0.0;
		double nonGst = 0.0;
		double compoundingDealer = 0.0;
		double ttl = 0.0;
		if (!rsp.getStatusCd().equals("0")) {

			infoMap = new HashMap<>();
			infoMap.put(PropertyMapping.B2B, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("B2B")).findAny().get().getTtlVal());
			infoMap.put(PropertyMapping.B2B_UNRTP, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("B2BUR")).findAny().get().getTtlVal());
			infoMap.put(PropertyMapping.IMP_GOODS, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("IMPG")).findAny().get().getTtlVal());
			infoMap.put(PropertyMapping.IMP_SERVICES, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("IMPS")).findAny().get().getTtlVal());
			infoMap.put(PropertyMapping.NIL_GOODS, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("NIL")).findAny().get().getTtlNilsply());
			infoMap.put(PropertyMapping.EXE_GOODS, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("NIL")).findAny().get().getTtlExpdsply());
			infoMap.put(PropertyMapping.NON_GST, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("NIL")).findAny().get().getTtlNgsply());
			infoMap.put(PropertyMapping.COMPOUNDING_DEALER, rsp.getSectionSummary().stream()
					.filter(o -> o.getSecNm().equalsIgnoreCase("NIL")).findAny().get().getTtlCppdr());

			ttl += b2b + b2bUnrTp + impGoods + impServices + nilGoods + nilGoods + exeGoods + nonGst
					+ compoundingDealer;

			infoMap.put(PropertyMapping.RET_PERIOD, retPeriod);
			infoMap.put(PropertyMapping.TOTAL, ttl);
		}
		return infoMap;
	}

	public static void totalMonthWisePurchGstr2(GstCalcMappingTable gstMapTbl, List<Map<String, Object>> list) {
		if (list != null && !list.isEmpty()) {
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.B2B,
					sumFromList(list, PropertyMapping.B2B));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.B2B_UNRTP,
					sumFromList(list, PropertyMapping.B2B_UNRTP));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.IMP_GOODS,
					sumFromList(list, PropertyMapping.IMP_GOODS));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.IMP_SERVICES,
					sumFromList(list, PropertyMapping.IMP_SERVICES));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.NIL_GOODS,
					sumFromList(list, PropertyMapping.NIL_GOODS));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.EXE_GOODS,
					sumFromList(list, PropertyMapping.EXE_GOODS));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.NON_GST,
					sumFromList(list, PropertyMapping.NON_GST));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.COMPOUNDING_DEALER,
					sumFromList(list, PropertyMapping.COMPOUNDING_DEALER));
			gstMapTbl.getCamgstData().getTotalMonthWisePurchGstr2().put(PropertyMapping.TOTAL,
					sumFromList(list, PropertyMapping.TOTAL));
		}
	}

	public static double sumFromList(List<Map<String, Object>> list, String key) {
		return list.stream().filter(info -> info.get(key) != null).mapToDouble(v1 -> (Double) v1.get(key)).sum();
	}

	public static Long getStateCodeFromGstin(String gstin) {
		if (!isObjectNullOrEmpty(gstin))
			return Long.parseLong(gstin.subSequence(0, 2).toString());
		else
			return null;
	}

	public static List<String> getMonthsList(String dates) throws ParseException {
		List<String> data = new ArrayList<>();
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH);
			Integer day = cal.get(Calendar.DATE);
			month = month - 2;
			cal.set(year, month, day);
			int i = 1;
			while (i <= 6) {
				month = month - 1;
				cal.set(year, month, day);
				String dateDdta = new SimpleDateFormat("MMyyyy").format(cal.getTime());
				data.add(dateDdta);
				i++;
			}
		}
		return data;
	}

	public static List<String> getMonthsListNoGst(String dates, Long reqMonths) throws ParseException {
		Integer reqMonth = (int) (reqMonths != null && reqMonths <= 12 ? reqMonths : 6);
		List<String> data = new ArrayList<>();
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Integer year = cal.get(Calendar.YEAR);
			Integer month = cal.get(Calendar.MONTH);
			Integer day = cal.get(Calendar.DATE);
			month = month - 0;
			cal.set(year, month, day);
			int i = 1;
			while (i <= reqMonth) {
				month = month - 1;
				cal.set(year, month, day);
				String dateDdta = new SimpleDateFormat("MMyyyy").format(cal.getTime());
				data.add(dateDdta);
				i++;
			}
		}
		return data;
	}

	/**
	 * Calculate the Sum from map
	 * 
	 * @author vijay.chauhan
	 * @param map
	 * @return double
	 */
	public static double sumFromMap(Map<String, Double> map) {
		return map.values().stream().mapToDouble(v -> v).sum();
	}

	/**
	 * Generating sales value from GSTR1
	 * 
	 * @author vijay.chauhan
	 * @param resp
	 * @return Map<String, Double>
	 */
	public static Map<String, Double> generationSales(Gstr1SummaryResponse[] resp) {
		Map<String, Double> map = new HashMap<>();

		for (Gstr1SummaryResponse response : resp) {
			if (!CommonUtils.isObjectNullOrEmpty(response) && response.getSecSum() != null) {
				for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : response.getSecSum()) {
					if (secSum.getCptySum() != null && "B2B".equals(secSum.getSecNm())) {
						for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.CptySum cptySum : secSum
								.getCptySum()) {

							if (map.containsKey(cptySum.getCtin())) {
								map.put(cptySum.getCtin(), map.get(cptySum.getCtin()) + cptySum.getTtlVal());

							} else {
								map.put(cptySum.getCtin(), cptySum.getTtlVal());
							}
						}
					}
				}
			}
		}

		return map;

	}

	/**
	 * Generating sales value from GSTR2-B2B
	 * 
	 * @author vijay.chauhan
	 * @param respList
	 * @return Map<String,Double>
	 */
	public static Map<String, Double> generationPurchase(Gstr2AB2b[] respList) {
		Map<String, Double> map = new HashMap<>();
		for (Gstr2AB2b response : respList) {
			if (!CommonUtils.isObjectNullOrEmpty(response) && !response.getStatusCd().equals("0")) {
				for (B2b b2b : response.getB2b()) {
					if (map.containsKey(b2b.getCtin())) {

						Inv[] invs = b2b.getInv();
						Double val = 0.0;

						for (Inv inv : invs) {
							val += inv.getVal();
						}

						map.put(b2b.getCtin(), map.get(b2b.getCtin()) + val);

					} else {
						Inv[] invs = b2b.getInv();
						Double val = 0.0;

						for (Inv inv : invs) {
							val += inv.getVal();
						}
						map.put(b2b.getCtin(), val);
					}
				}
			}
		}
		return map;

	}

	public static List<String> financialYearList() {
		List<String> retPeriod = new ArrayList<String>();
		String gstDate = "01/04/2017";
//        String curDate = "01/05/2020";
		SimpleDateFormat format = new SimpleDateFormat(DD_MM_YYYY);
		Date date = null;
		try {
			date = format.parse(gstDate);
//            curdate = format.parse(curDate);
		} catch (ParseException e) {
			logger.error(CommonUtils.EXCEPTION, e);
		}

		Calendar gstCal = Calendar.getInstance();
//        gstCal.add(Calendar.MONTH, -1);
		gstCal.setTime(date);
		Calendar cal = Calendar.getInstance();
//        cal.setTime(curdate);
		cal.add(Calendar.MONTH, -1);
		cal.get(Calendar.YEAR);
		Boolean flag = true;
		while (flag) {
			String retPer = gstCal.get(Calendar.YEAR) + "-";
			gstCal.add(Calendar.YEAR, 1);
			retPer += gstCal.get(Calendar.YEAR);
			if (cal.before(gstCal)) {
				flag = false;
			}
			retPeriod.add(retPer);
		}

		retPeriod.sort(new DateComparator2());
		return retPeriod;
	}

	public static String getMonthName(String dates) throws ParseException {
		String data = null;
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			data = new SimpleDateFormat("MMMM").format(cal.getTime());

		}
		return data;
	}

	public static String getMonthNameWithYear(String dates) throws ParseException {
		String data = null;
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			data = new SimpleDateFormat("MMMM-yyyy").format(cal.getTime());

		}
		return data;
	}

	public static String getMonthAndYearName(String dates) throws ParseException {
		String data = null;
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

			data = new SimpleDateFormat("MMMM-yyyy").format(cal.getTime());

		}
		return data;
	}

	public static String getQuarter(String dates) throws ParseException {
		String data = null;
		if (null != dates && !dates.isEmpty()) {
			Date date = new SimpleDateFormat("MMyyyy").parse(dates);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);

//			data ="Q-"+((cal.get(Calendar.MONTH)/3)+1);
			int month = cal.get(Calendar.MONTH) + 1;
			data = month <= 3 ? "4" : month <= 6 ? "1" : month <= 9 ? "2" : "3";
			/*
			 * if (month <= 3) { // data ="Q-"+ 4; data =4+""; } else if (month <= 6) { //
			 * data ="Q-"+ 1; data =1+""; } else if (month <= 9) { // data ="Q-"+ 2; data
			 * =2+""; } else { // data ="Q-"+ 3; data =3+""; }
			 */
		}
		return data;

	}

	/**
	 * @author nilay.darji
	 * @param req
	 * @return is twelve months sales is zero or not
	 */
	public static Boolean isTwelveMonthDataZero(GSTR1Request req) {
		if (req != null && req.getDataMapping() != null) {

			logger.info("Start isTwelveMonthDataZero Gstin ==>>>>>>>>>>>>>>>>{}", req.getGstin());
			logger.info("isTwelveMonthDataZero getCurrentYearTotal==>>>>>>>>>>>>>>>>{}",
					req.getDataMapping().getCurrentYearTotal());
			logger.info("isTwelveMonthDataZero getPreviousYearTotal==>>>>>>>>>>>>>>>>{}",
					req.getDataMapping().getPreviousYearTotal());
			if (req.getDataMapping().getCurrentYearTotal() != 0) {
				logger.info("End isTwelveMonthDataZero - false - Gstin ==>>>>>>>>>>>>>>>>{}", req.getGstin());
				return false;
			} else {
				List<DataMappingProjectedSales> cur = new ArrayList<>();
				cur.addAll(req.getDataMapping().getPreviousYears());
				cur.addAll(req.getDataMapping().getCurrentYears());
				cur.sort(new DateComparatorAscTime3());
				Collections.reverse(cur);
				Double tt = cur.stream().limit(12L).collect(Collectors.toList()).stream()
						.mapToDouble(DataMappingProjectedSales::getValues).sum();
				logger.info("isTwelveMonthDataZero ==>>>>>>>>>>>>>>>>{}", tt);
				if (0d == tt) {
					logger.info("End isTwelveMonthDataZero - true - Gstin ==>>>>>>>>>>>>>>>>{}", req.getGstin());
					return true;
				} else {
					logger.info("End isTwelveMonthDataZero - false - Gstin ==>>>>>>>>>>>>>>>>{}", req.getGstin());
					return false;
				}
			}
		}
		return null;
	}

	/**
	 * @author nilay.darji
	 * @param gstReqType
	 * @return gstReqType
	 */
	public static String returnGstReqType(String gstReqType) {
		switch (gstReqType) {
		case RequestType.GSTR4_SUMMARY:
			return RequestType.GSTR4_SUMMARY;
		case RequestType.GSTR4_CDNR:
			return RequestType.GSTR4_CDNR;
		case RequestType.GSTR4_CDNUR:
			return RequestType.GSTR4_CDNUR;
		default:
			return null;
		}
	}

	public static Gstr4CalculationResponse calculateProjectedSalesGstr4(Gstr4PopUpResponse gstr4PopUpResponse) {
		Gstr4CalculationResponse calculationResponse = new Gstr4CalculationResponse();

		List<Gstr4QoqSales> sales = gstr4PopUpResponse.getData();
		calculationResponse.setqOnqSales(CommonUtils.convertObjectToString(sales));

		Collections.reverse(sales);
		Double currTotal = sales.stream().limit(4l).collect(Collectors.toList()).stream()
				.mapToDouble(Gstr4QoqSales::getValue).sum();// first 12 month sales data
		Double prevTotal = sales.subList(4, 8).stream().collect(Collectors.toList()).stream()
				.mapToDouble(Gstr4QoqSales::getValue).sum();// previons 12 month sales data
		Double growth = (currTotal - prevTotal) * 100 / prevTotal;
		Double proSales = currTotal * (1 + (growth / 100));
		List<String> retperStr = new ArrayList<>();
		sales.stream().forEach(x -> {
			retperStr.add(x.getRetPer());
		});
		calculationResponse.setRetPerStr(retperStr);
		calculationResponse.setCurrTotal(currTotal);
		calculationResponse.setPrevTotal(prevTotal);
		if (Double.isFinite(growth)) { // for growth
			calculationResponse.setGrowth(growth);
		} else {
			calculationResponse.setGrowth(0.0);
			calculationResponse.setIsHistoric(Boolean.TRUE);
		}
		if (Double.isFinite(proSales)) { // for projected sales
			calculationResponse.setProSales(proSales);
		} else {
			calculationResponse.setProSales(0d);
			calculationResponse.setIsHistoric(Boolean.TRUE);
		}
		return calculationResponse;
	}

	public static Double checkLastYearSalesZeroOrNotGstr4(GSTR1Request request) {
		Gstr4PopUpResponse gstr4PopUpResponse = convertJSONToObject(request.getGstr4PopUpResponse(),
				Gstr4PopUpResponse.class);
		Collections.reverse(gstr4PopUpResponse.getData());
		Double d = gstr4PopUpResponse.getData().stream().limit(4L).collect(Collectors.toList()).stream()
				.mapToDouble(Gstr4QoqSales::getValue).sum();
		return d;
	}

	public static List<String> returnPeriodListByNoOfLastMonth(int previousNoOfMonth) {
		List<String> retPeriod = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);

		while (previousNoOfMonth > 0) {
			String retPer = (String.format("%02d", cal.get(Calendar.MONTH) + 1)) + "" + cal.get(Calendar.YEAR);
			retPeriod.add(retPer);
			cal.add(Calendar.MONTH, -1);
			previousNoOfMonth--;
		}
		retPeriod.sort(new DateComparator2());
		return retPeriod;
	}

	public static Map<String, SummOfHsn> generateDetailsCalcForHsnSales(Gstr1HsnSummaryResponse rsp) {
		Map<String, SummOfHsn> mapByHsn = new HashMap<>();
		if (rsp != null && rsp.getHsn() != null && rsp.getHsn().getData() != null
				&& !rsp.getHsn().getData().isEmpty()) {
			SummOfHsn summOfHsn = null;
			for (Data data : rsp.getHsn().getData()) {
//                	System.out.println(data.getHsnSc()+"|  Desc==>>>>>>>>>>> "+data.getDesc());
				if (mapByHsn.containsKey(data.getHsnSc())) {
					summOfHsn = mapByHsn.get(data.getHsnSc());

					if (!CommonUtils.isObjectNullOrEmpty(data.getDesc())) {
						summOfHsn.setDesc(data.getDesc().toUpperCase().trim());
						summOfHsn.addDesc(summOfHsn.getDesc());
					}
					if (!CommonUtils.isObjectNullOrEmpty(data.getUqc())) {
						summOfHsn.setUqc(data.getUqc().toUpperCase().trim());
					}

					summOfHsn.setNum((summOfHsn.getNum() == null ? 0 : summOfHsn.getNum())
							+ (data.getNum() == null ? 0 : data.getNum()));
					summOfHsn.setQty((summOfHsn.getQty() == null ? 0.0 : summOfHsn.getQty())
							+ (data.getQty() == null ? 0.0 : data.getQty()));
					summOfHsn.setVal((summOfHsn.getVal() == null ? 0.0 : summOfHsn.getVal())
							+ (data.getVal() == null ? 0.0 : data.getVal()));
					summOfHsn.setTxval((summOfHsn.getTxval() == null ? 0.0 : summOfHsn.getTxval())
							+ (data.getTxval() == null ? 0.0 : data.getTxval()));
					summOfHsn.setIamt((summOfHsn.getIamt() == null ? 0.0 : summOfHsn.getIamt())
							+ (data.getIamt() == null ? 0.0 : data.getIamt()));
					summOfHsn.setCamt((summOfHsn.getCamt() == null ? 0.0 : summOfHsn.getCamt())
							+ (data.getCamt() == null ? 0.0 : data.getCamt()));
					summOfHsn.setSamt((summOfHsn.getSamt() == null ? 0.0 : summOfHsn.getSamt())
							+ (data.getSamt() == null ? 0.0 : data.getSamt()));
					summOfHsn.setCsamt((summOfHsn.getCsamt() == null ? 0.0 : summOfHsn.getCsamt())
							+ (data.getCsamt() == null ? 0.0 : data.getCsamt()));
					mapByHsn.put(data.getHsnSc(), summOfHsn);
				} else {
					summOfHsn = new SummOfHsn();
					BeanUtils.copyProperties(data, summOfHsn);
					if (!CommonUtils.isObjectNullOrEmpty(data.getDesc())) {
						summOfHsn.setDesc(data.getDesc().toUpperCase().trim());
						summOfHsn.addDesc(summOfHsn.getDesc());
					}
					if (!CommonUtils.isObjectNullOrEmpty(data.getUqc())) {
						summOfHsn.setUqc(data.getUqc().toUpperCase().trim());
					}
					summOfHsn.setNum((data.getNum() == null ? 0 : data.getNum()));
					summOfHsn.setQty((data.getQty() == null ? 0.0 : data.getQty()));
					summOfHsn.setVal((data.getVal() == null ? 0.0 : data.getVal()));
					summOfHsn.setTxval((data.getTxval() == null ? 0.0 : data.getTxval()));
					summOfHsn.setIamt((data.getIamt() == null ? 0.0 : data.getIamt()));
					summOfHsn.setCamt((data.getCamt() == null ? 0.0 : data.getCamt()));
					summOfHsn.setSamt((data.getSamt() == null ? 0.0 : data.getSamt()));
					summOfHsn.setCsamt((data.getCsamt() == null ? 0.0 : data.getCsamt()));
					mapByHsn.put(data.getHsnSc(), summOfHsn);
				}
			}
		}
		return mapByHsn;
	}

	public static Map<String, SummOfHsn> generateDetailsCalcForHsnPurchases(Gstr2HsnSummaryResponse rsp) {
		Map<String, SummOfHsn> mapByHsn = new HashMap<>();
		if (rsp != null && rsp.getHsnsum() != null && rsp.getHsnsum().getDet() != null
				&& !rsp.getHsnsum().getDet().isEmpty()) {
			SummOfHsn summOfHsn = null;
			for (Det data : rsp.getHsnsum().getDet()) {
				if (mapByHsn.containsKey(data.getHsnSc())) {
					summOfHsn = mapByHsn.get(data.getHsnSc());

					if (!CommonUtils.isObjectNullOrEmpty(data.getDesc())) {
						summOfHsn.setDesc(data.getDesc().toUpperCase().trim());
						summOfHsn.addDesc(summOfHsn.getDesc());
					}
					if (!CommonUtils.isObjectNullOrEmpty(data.getUqc())) {
						summOfHsn.setUqc(data.getUqc().toUpperCase().trim());
					}

					summOfHsn.setNum((summOfHsn.getNum() == null ? 0 : summOfHsn.getNum())
							+ (data.getNum() == null ? 0 : data.getNum()));
					summOfHsn.setQty((summOfHsn.getQty() == null ? 0.0 : summOfHsn.getQty())
							+ (data.getQty() == null ? 0.0 : data.getQty()));
					summOfHsn.setVal((summOfHsn.getVal() == null ? 0.0 : summOfHsn.getVal())
							+ (data.getVal() == null ? 0.0 : data.getVal()));
					summOfHsn.setTxval((summOfHsn.getTxval() == null ? 0.0 : summOfHsn.getTxval())
							+ (data.getTxval() == null ? 0.0 : data.getTxval()));
					summOfHsn.setIamt((summOfHsn.getIamt() == null ? 0.0 : summOfHsn.getIamt())
							+ (data.getIamt() == null ? 0.0 : data.getIamt()));
					summOfHsn.setCamt((summOfHsn.getCamt() == null ? 0.0 : summOfHsn.getCamt())
							+ (data.getCamt() == null ? 0.0 : data.getCamt()));
					summOfHsn.setSamt((summOfHsn.getSamt() == null ? 0.0 : summOfHsn.getSamt())
							+ (data.getSamt() == null ? 0.0 : data.getSamt()));
					summOfHsn.setCsamt((summOfHsn.getCsamt() == null ? 0.0 : summOfHsn.getCsamt())
							+ (data.getCsamt() == null ? 0.0 : data.getCsamt()));
					mapByHsn.put(data.getHsnSc(), summOfHsn);
				} else {
					summOfHsn = new SummOfHsn();
					BeanUtils.copyProperties(data, summOfHsn);
					if (!CommonUtils.isObjectNullOrEmpty(data.getDesc())) {
						summOfHsn.setDesc(data.getDesc().toUpperCase().trim());
						summOfHsn.addDesc(summOfHsn.getDesc());
					}
					if (!CommonUtils.isObjectNullOrEmpty(data.getUqc())) {
						summOfHsn.setUqc(data.getUqc().toUpperCase().trim());
					}
					summOfHsn.setNum((data.getNum() == null ? 0 : data.getNum()));
					summOfHsn.setQty((data.getQty() == null ? 0.0 : data.getQty()));
					summOfHsn.setVal((data.getVal() == null ? 0.0 : data.getVal()));
					summOfHsn.setTxval((data.getTxval() == null ? 0.0 : data.getTxval()));
					summOfHsn.setIamt((data.getIamt() == null ? 0.0 : data.getIamt()));
					summOfHsn.setCamt((data.getCamt() == null ? 0.0 : data.getCamt()));
					summOfHsn.setSamt((data.getSamt() == null ? 0.0 : data.getSamt()));
					summOfHsn.setCsamt((data.getCsamt() == null ? 0.0 : data.getCsamt()));
					mapByHsn.put(data.getHsnSc(), summOfHsn);
				}
			}
		}
		return mapByHsn;
	}

	public static DataMapping getCalulationFromGSTR1(Gstr1SummaryResponse[] gstr1Summary, String imGstin) {
		try {
			DataMapping dataMapping = new DataMapping();

			TreeMap<String, Double> map = calculateGstr1ByPreviousYear(gstr1Summary);
			map.entrySet().stream().forEach(pair -> {
				dataMapping.addPreviousYearsGSTR1Sales(new DataMappingProjectedSales(pair.getKey(),
						Double.valueOf(pair.getValue().toString()), false));
			});

			dataMapping.setPreviousYearTotalGSTR1Sales(dataMapping.getPreviousYearsGSTR1Sales().stream()
					.filter(o -> o.getValues() != null).mapToDouble(o -> o.getValues()).sum());

			TreeMap<String, Double> imGstinSalesMap = generationByRetPeriod(gstr1Summary, imGstin);

			imGstinSalesMap.entrySet().stream().forEach(pair -> {
				if (getPreviousFinancialYear(0).equalsIgnoreCase(getFinancialYear(pair.getKey()))) {
					dataMapping.addCurrentYears(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				} else if (getPreviousFinancialYear(-1).equalsIgnoreCase(getFinancialYear(pair.getKey()))) {
					dataMapping.addPreviousYear(new DataMappingProjectedSales(pair.getKey(),
							Double.valueOf(pair.getValue().toString()), false));
				}
			});
			dataMapping.setImGstinPreviousYearTotal(
					dataMapping.getPreviousYears().stream().mapToDouble(DataMappingProjectedSales::getValues).sum());
			dataMapping.setCurrentYearTotal(dataMapping.getCurrentYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());
			dataMapping.setPreviousYearTotal(dataMapping.getPreviousYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());

			ProjectedSalesCalcResponse projectedSalesCalcResponse = calculateProjectedSales(dataMapping);
			if (projectedSalesCalcResponse.getIsNegativeGrowth()) {
				dataMapping.setStatusCd(IS_GROWTH_NEGATIVE);
			} else {
				dataMapping.setStatusCd(CLEAR);
			}
			dataMapping.setProjectedSalesCalcResponse(projectedSalesCalcResponse);
			return dataMapping;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return null;
		}
	}

	public static DataMapping getPurchaseCalulationFromGSTR2AB2B(
			Map<String, Map<String, Double>> gstinWiseGSTR2AB2BData) {
		try {
			DataMapping dataMapping = new DataMapping();

			Map<String, Double> temp = new HashMap<>();
			gstinWiseGSTR2AB2BData.entrySet().stream().forEach(x -> {
				x.getValue().entrySet().stream().forEach(y -> {
					if (temp.containsKey(y.getKey())) {
						temp.put(y.getKey(), temp.get(y.getKey()) + y.getValue());
					} else {
						temp.put(y.getKey(), y.getValue());
					}
				});
			});

			returnPeriodListForPurchaseCalc().stream().forEach(c -> {
				if (!temp.containsKey(c)) {
					temp.put(c, 0.0);
				}
			});

			String resps = CommonUtils.convertObjectToString(temp);
			System.out.println(resps);
			/*
			 * gstinWiseGSTR2AB2BData.entrySet().stream().forEach(y->{
			 * y.getValue().entrySet().stream().filter(c ->
			 * getPreviousFinancialYear(0).equalsIgnoreCase(getFinancialYear(c.getKey()))).
			 * forEach(m ->{ dataMapping.addCurrentYears(new
			 * DataMappingProjectedSales(m.getKey(),Double.valueOf(m.getValue().toString()),
			 * false)); }); y.getValue().entrySet().stream().filter(c ->
			 * getPreviousFinancialYear(-1).equalsIgnoreCase(getFinancialYear(c.getKey()))).
			 * forEach(m ->{ dataMapping.addPreviousYear(new
			 * DataMappingProjectedSales(m.getKey(),Double.valueOf(m.getValue().toString()),
			 * false)); }); });
			 */
			temp.entrySet().stream()
					.filter(c -> getPreviousFinancialYear(0).equalsIgnoreCase(getFinancialYear(c.getKey())))
					.forEach(m -> {
						dataMapping.addCurrentYears(new DataMappingProjectedSales(m.getKey(),
								Double.valueOf(m.getValue().toString()), false));
					});
			temp.entrySet().stream()
					.filter(c -> getPreviousFinancialYear(-1).equalsIgnoreCase(getFinancialYear(c.getKey())))
					.forEach(m -> {
						dataMapping.addPreviousYear(new DataMappingProjectedSales(m.getKey(),
								Double.valueOf(m.getValue().toString()), false));
					});
			dataMapping.setImGstinPreviousYearTotal(
					dataMapping.getPreviousYears().stream().mapToDouble(DataMappingProjectedSales::getValues).sum());
			dataMapping.setCurrentYearTotal(dataMapping.getCurrentYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());
			dataMapping.setPreviousYearTotal(dataMapping.getPreviousYears().stream().filter(o -> o.getValues() != null)
					.mapToDouble(o -> o.getValues()).sum());
			ProjectedSalesCalcResponse projectedSalesCalcResponse = null;
			dataMapping.getCurrentYears().clear();
			dataMapping.getPreviousYears().clear();

			returnPeriodListForPurchaseCalc().stream().forEach(x -> {
				if (x.substring(2, 6).equals(String.valueOf(LocalDate.now().getYear()))) {
					dataMapping.addCurrentYears(new DataMappingProjectedSales(x, temp.get(x), false));
				} else {
					dataMapping.addPreviousYear(new DataMappingProjectedSales(x, temp.get(x), false));
				}
			});

			if (dataMapping != null && !dataMapping.getCurrentYears().isEmpty()
					&& !dataMapping.getPreviousYears().isEmpty()) {
				dataMapping.setHistoricValue(getHistoricPurchase(dataMapping));
				projectedSalesCalcResponse = calculateProjectedSales(dataMapping);
			}
			if (projectedSalesCalcResponse != null && projectedSalesCalcResponse.getIsNegativeGrowth()) {
				dataMapping.setStatusCd(IS_GROWTH_NEGATIVE);
			} else {
				dataMapping.setStatusCd(CLEAR);
			}
			dataMapping.setProjectedSalesCalcResponse(projectedSalesCalcResponse);
			return dataMapping;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return null;
		}
	}

	public static Double getHistoricPurchase(DataMapping dataMapping1) {
		List<DataMappingProjectedSales> allYrs = new ArrayList<DataMappingProjectedSales>();

		dataMapping1.getCurrentYears().sort(new DateComparatorAscTime3());
		dataMapping1.getPreviousYears().sort(new DateComparatorAscTime3());
		allYrs.addAll(dataMapping1.getPreviousYears());
		allYrs.addAll(dataMapping1.getCurrentYears());

		Collections.reverse(allYrs);
		return allYrs.stream().limit(12l).mapToDouble(DataMappingProjectedSales::getValues).sum();
	}

	public static TreeMap<String, Double> calculateGstr1ByPreviousYear(Gstr1SummaryResponse[] resp) {
		TreeMap<String, Double> map = new TreeMap<>(new DateComparatorDescTime());
		Double totSales = 0.0;
		for (Gstr1SummaryResponse response : resp) {
			totSales = 0.0;
			if (!"0".equalsIgnoreCase(response.getStatusCd())
					&& !CommonUtils.isObjectNullOrEmpty(response.getSecSum())) {
				for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : response.getSecSum()) {
					if (getPreviousFinancialYear(-1).equalsIgnoreCase(getFinancialYear(response.getRatePeriod()))) {
						// B2B+B2CL+B2CS+NIL rated+Exempted+Non GST sales value
						// Mapping: "sec_nm": "B2B" plus (+) "sec_nm": "B2CL" plus (+) "sec_nm": "B2CS"
						// plus (+) "sec_nm": "EXP" plus (+) "sec_nm": "NIL"
						if ("B2B".equals(secSum.getSecNm())) {
							totSales += secSum.getTtlTax();
						}
						if ("B2CL".equals(secSum.getSecNm())) {
							totSales += secSum.getTtlTax();
						}
						if ("B2CS".equals(secSum.getSecNm())) {
							totSales += secSum.getTtlTax();
						}
						if ("EXP".equals(secSum.getSecNm())) {
							totSales += secSum.getTtlTax();
						}
						if ("NIL".equals(secSum.getSecNm())) {
							totSales += secSum.getTtlTax();
						}
						map.put(response.getRatePeriod(), totSales);
					}
				}
			}
		}
		return map;
	}

	public static TreeMap<String, Double> generationByRetPeriod(Gstr1SummaryResponse[] resp, String imGstin) {
		TreeMap<String, Double> retPeriod = new TreeMap<>(new DateComparatorDescTime());
		Double totSales = 0.0;
		if (!isObjectNullOrEmpty(imGstin)) {
			for (Gstr1SummaryResponse response : resp) {
				totSales = 0.0;
				if (!"0".equalsIgnoreCase(response.getStatusCd())
						&& !CommonUtils.isObjectNullOrEmpty(response.getSecSum())) {
					for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.SecSum secSum : response.getSecSum()) {
						if (secSum.getCptySum() != null && "B2B".equals(secSum.getSecNm())) {
							for (com.opl.mudra.api.gst.model.yuva.response.gstr1summary.CptySum cptySum : secSum
									.getCptySum()) {
								if (imGstin.equalsIgnoreCase(cptySum.getCtin())) {
									totSales += cptySum.getTtlTax();
								}
							}
						}
					}
					retPeriod.put(response.getRatePeriod(), totSales);
				}
			}
		}

		return retPeriod;

	}

	public static Double generate3bPreviousYearTotal(GSTR3BSummary[] gstr3bSummary) {
		TreeMap<String, Object> retPeriod = new TreeMap<>(new DateComparatorDescTime());

		for (GSTR3BSummary summary : gstr3bSummary) {
			if (summary.getStatusCd() == null || summary.getStatusCd().equals("1")
					|| summary.getStatusCd().equals("")) {
				if (summary.getSupDetails() != null) {

					retPeriod.put(summary.getRetPeriod(),
							addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
									summary.getSupDetails().getOsupDet().getTxval(),
									summary.getSupDetails().getOsupNilExmp().getTxval(),
									summary.getSupDetails().getOsupNongst().getTxval()));
				} else {
					retPeriod.put(summary.getRetPeriod(), 0.0);
				}
			}
		}

//		List<String> remainingMonths = returnPeriodList().stream()
//				.filter(x -> !(Arrays.asList(retPeriod.keySet().toArray()).stream()
//						.map(object -> Objects.toString(object, null)).collect(Collectors.toList())).contains(x))
//				.collect(Collectors.toList());

		DataMapping dataMapping = new DataMapping();

		retPeriod.entrySet().stream().forEach(pair -> {
			if (getPreviousFinancialYear(0).equalsIgnoreCase(getFinancialYear(pair.getKey()))) {
				dataMapping.addCurrentYears(new DataMappingProjectedSales(pair.getKey(),
						Double.valueOf(pair.getValue().toString()), false));
			} else if (getPreviousFinancialYear(-1).equalsIgnoreCase(getFinancialYear(pair.getKey()))) {
				dataMapping.addPreviousYear(new DataMappingProjectedSales(pair.getKey(),
						Double.valueOf(pair.getValue().toString()), false));
			}
		});

		return dataMapping.getPreviousYears().stream().mapToDouble(DataMappingProjectedSales::getValues).sum();

	}

	public static TreeMap<String, Object> basicDetailsData(GSTR3BSummary[] gstr3bSummary, DataMapping dataMapping) {
		TreeMap<String, Object> retPeriod = new TreeMap<>(new DateComparatorDescTime()); // total sales
		List<DataMappingProjectedSales> list = new ArrayList<DataMappingProjectedSales>();
		list.addAll(dataMapping.getPreviousYears());
		list.addAll(dataMapping.getCurrentYears());
		Collections.reverse(list);

		for (GSTR3BSummary summary : gstr3bSummary) {
			if (summary.getStatusCd() == null || summary.getStatusCd().equals("1")
					|| summary.getStatusCd().equals("")) {
				if (summary.getSupDetails() != null) {

					retPeriod.put(summary.getRetPeriod(),
							addNumbers(summary.getSupDetails().getOsupZero().getTxval(),
									summary.getSupDetails().getOsupDet().getTxval(),
									summary.getSupDetails().getOsupNilExmp().getTxval(),
									summary.getSupDetails().getOsupNongst().getTxval()));
				} else {
					retPeriod.put(summary.getRetPeriod(), 0.0);
				}
			}
		}

		list.forEach(data -> {
			if (data.getReturnPeriod() != null) {
				retPeriod.entrySet().stream().forEach(map -> {
					if (map.getKey() != null && data.getReturnPeriod().equals(map.getKey())) {
						map.setValue(data.getValues());
					}
				});
			}
		});
		return retPeriod;
	}

	public static Double basicDetailsDataPurchase(Map<String, Gstr2AB2b> gstr2AB2bdata) {
		Map<String, Map<String, Double>> gstinWiseGSTR2AB2BData = new HashMap<>();
		gstr2AB2bdata.entrySet().stream().forEach(x -> {
			Arrays.asList(x.getValue().getB2b()).stream().forEach(y -> {
				Map<String, Double> data = new HashMap<>();
				if (gstinWiseGSTR2AB2BData.containsKey(y.getCtin())) {

					if (gstinWiseGSTR2AB2BData.get(y.getCtin()).containsKey(x.getKey())) {
						gstinWiseGSTR2AB2BData.get(y.getCtin()).put(x.getKey(),
								gstinWiseGSTR2AB2BData.get(y.getCtin()).get(x.getKey())
										+ Arrays.asList(y.getInv()).stream().mapToDouble(Inv::getVal).sum());
					} else {
						data.put(x.getKey(), Arrays.asList(y.getInv()).stream().mapToDouble(Inv::getVal).sum());
						gstinWiseGSTR2AB2BData.get(y.getCtin()).putAll(data);
					}

				} else {

					data.put(x.getKey(), Arrays.asList(y.getInv()).stream().mapToDouble(Inv::getVal).sum());
					gstinWiseGSTR2AB2BData.put(y.getCtin(), data);
				}
			});
		});

		Double purSum = 0d;
		for (Map.Entry<String, Map<String, Double>> m : gstinWiseGSTR2AB2BData.entrySet()) {
			for (Map.Entry<String, Double> inval : m.getValue().entrySet()) {
				purSum = purSum + (Double) inval.getValue();
			}
		}
		return purSum;
	}
	
	public static int dayDiffrence(Date modifedDate, Date todayDate){
        return (int)( (todayDate.getTime() - modifedDate.getTime()) / (1000 * 60 * 60 * 24));
	}
	
//	public static Map<String, Object> gstDataCheckForUpdate(String gstin , Timestamp date, String commonDate) throws ParseException{
//		Map<String, Object> map = new HashMap<>();
//		map.put("gstin", gstin);
//		if(date != null) {
//			String[] split = date.toString().split(" ");
//			
//			Date convertedCurrentDate = sdf.parse(split[0]);
//			Calendar modifiedDate = Calendar.getInstance();
//			modifiedDate.setTime(convertedCurrentDate);
//			logger.info("modifiedDate Date :: " + modifiedDate.get(Calendar.DATE));
//			logger.info("modifiedDate month :: " + modifiedDate.get(Calendar.MONTH));
//			logger.info("modifiedDate year :: " + modifiedDate.get(Calendar.YEAR));
//			int modifiedMonth = modifiedDate.get(Calendar.MONTH) + 1;
//
////			Calendar todayDate = Calendar.getInstance();
////			int currentMonth = todayDate.get(Calendar.MONTH) + 1;
////			int lastDay = todayDate.getActualMaximum(Calendar.DAY_OF_MONTH);
////			int currentDay = todayDate.get(Calendar.DAY_OF_MONTH);
//			
//			Calendar todayDate = Calendar.getInstance();
//			todayDate.set(todayDate.get(Calendar.YEAR), todayDate.get(Calendar.MONTH), Integer.valueOf(commonDate));
//			int currentMonth = todayDate.get(Calendar.MONTH);
//			int currentYear = todayDate.get(Calendar.YEAR);
//			logger.info("today Date :: " + todayDate.get(Calendar.DATE));
//			logger.info("today month :: " + todayDate.get(Calendar.MONTH));
//			logger.info("today year :: " + todayDate.get(Calendar.YEAR));
//			
//			Calendar currDate = Calendar.getInstance();
//			
//			int lastDay = todayDate.getActualMaximum(Calendar.DAY_OF_MONTH);
//			int currentDay = todayDate.get(Calendar.DAY_OF_MONTH);
//
//			if(modifiedDate.before(todayDate)) {
//				if((modifiedDate.get(Calendar.MONTH) == todayDate.get(Calendar.MONTH)) || modifiedDate.get(Calendar.DATE) >= todayDate.get(Calendar.DATE)) {
//					map.put("isDataUpdated", true);
//					map.put("message", "Data valid upto " + (lastDay - currentDay) + " days.");
//					map.put("days", dayDiffrence(todayDate.getTime(),currDate.getInstance().getTime()));
//				}else {
//					map.put("isDataUpdated", false);
//					map.put("message", "Data is expired.");
//					map.put("days", CommonUtils.dayDiffrence(modifiedDate.getTime(),currDate.getInstance().getTime()));
//				}
//			}
//			
////			if (modifiedMonth == currentMonth) {
////				//isDataUpdated = true;
////				map.put("isDataUpdated", true);
////				map.put("message", "Data valid upto " + (lastDay - currentDay) + " days.");
////				map.put("days", (lastDay - currentDay));
////				//minDays.add((lastDay - currentDay));
////			} else {
////				map.put("isDataUpdated", false);
////				map.put("message", "Data is expired.");
////				map.put("days", CommonUtils.dayDiffrence(modifiedDate.getTime(),todayDate.getTime()));
////				// logger.info("gst data expired :: "+ CommonUtils.dayDiffrence(modifiedDate.getTime(), todayDate.getTime()));
////			}
//		}else {
//			map.put("message", "modified date is empty.");
//		}
//		return map;
//	}
	
//	public static Map<String, Object> gstDataCheckForUpdate(String gstin , Timestamp date, String commonDate) throws ParseException{
//		Map<String, Object> map = new HashMap<>();
//		map.put("gstin", gstin);
//		if(date != null) {
//			// gstLastModified Date
//			String[] split = date.toString().split(" ");
//			Date convertedCurrentDate = sdf.parse(split[0]);
//			Calendar modifiedDate = Calendar.getInstance();
//			modifiedDate.setTime(convertedCurrentDate);
//			logger.info("modifiedDate Date :: " + modifiedDate.get(Calendar.DATE));
//			logger.info("modifiedDate month :: " + modifiedDate.get(Calendar.MONTH));
//			logger.info("modifiedDate year :: " + modifiedDate.get(Calendar.YEAR));
//			
//			// dbGstDate
//			Calendar dbGstDate = Calendar.getInstance();
//			dbGstDate.set(dbGstDate.get(Calendar.YEAR), dbGstDate.get(Calendar.MONTH), Integer.valueOf(commonDate));
//			logger.info("today Date :: " + dbGstDate.get(Calendar.DATE));
//			logger.info("today month :: " + dbGstDate.get(Calendar.MONTH));
//			logger.info("today year :: " + dbGstDate.get(Calendar.YEAR));
//			
//			// currentDate
////			Date currDate = Calendar.getInstance().getTime();
//			Calendar currDate = Calendar.getInstance();
////			currDate.set(2020, 7, 23);
//			currDate.getTime();
//			
//			logger.info("curr Date :: " + currDate.get(Calendar.DATE));
//			logger.info("curr month :: " + currDate.get(Calendar.MONTH));
//			logger.info("curr year :: " + currDate.get(Calendar.YEAR));
//			
//			if(modifiedDate.before(dbGstDate)) {
//				if(modifiedDate.get(Calendar.YEAR) == dbGstDate.get(Calendar.YEAR)) {
//					if((modifiedDate.get(Calendar.DATE) >= dbGstDate.get(Calendar.DATE)) && (modifiedDate.get(Calendar.MONTH) == (dbGstDate.get(Calendar.MONTH) - 1)) || (modifiedDate.get(Calendar.DATE) <= dbGstDate.get(Calendar.DATE)) && (modifiedDate.get(Calendar.MONTH) == (dbGstDate.get(Calendar.MONTH)))) {
//						map.put("isDataUpdated", true);
//						map.put("message", "Data valid upto " + dayDiffrence(currDate.getTime(),dbGstDate.getTime()) + " days.");
//						map.put("days", dayDiffrence(currDate.getTime(),dbGstDate.getTime()));
//					}
//					else {
//						map.put("isDataUpdated", false);
//						map.put("message", "Data is expired.");
//						map.put("days", dayDiffrence(modifiedDate.getTime(),currDate.getTime()));
//					}
//				}else {
//					map.put("isDataUpdated", false);
//					map.put("message", "Data is expired.");
//					map.put("days", dayDiffrence(modifiedDate.getTime(),currDate.getTime()));
//				}
////				if(modifiedDate.get(Calendar.DATE) > dbGstDate.get(Calendar.DATE) && modifiedDate.get(Calendar.YEAR) == dbGstDate.get(Calendar.YEAR)) {
////					map.put("isDataUpdated", true);
////					map.put("message", "Data valid upto " + dayDiffrence(currDate.getTime(),dbGstDate.getTime()) + " days.");
////					map.put("days", dayDiffrence(currDate.getTime(),dbGstDate.getTime()));
////				}else {
////					map.put("isDataUpdated", false);
////					map.put("message", "Data is expired.");
////					map.put("days", dayDiffrence(modifiedDate.getTime(),currDate.getTime()));
////				}
//			}else {
//				// for after date
//				dbGstDate.set(dbGstDate.get(Calendar.YEAR), dbGstDate.get(Calendar.MONTH) + 1 , Integer.valueOf(commonDate));
//				map.put("isDataUpdated", true);
//				map.put("message", "Data valid upto " + dayDiffrence(currDate.getTime(),dbGstDate.getTime()) + " days.");
//				map.put("days", dayDiffrence(currDate.getTime(),dbGstDate.getTime()));
//			}
//			
////			if (modifiedMonth == currentMonth) {
////				//isDataUpdated = true;
////				map.put("isDataUpdated", true);
////				map.put("message", "Data valid upto " + (lastDay - currentDay) + " days.");
////				map.put("days", (lastDay - currentDay));
////				//minDays.add((lastDay - currentDay));
////			} else {
////				map.put("isDataUpdated", false);
////				map.put("message", "Data is expired.");
////				map.put("days", CommonUtils.dayDiffrence(modifiedDate.getTime(),todayDate.getTime()));
////				// logger.info("gst data expired :: "+ CommonUtils.dayDiffrence(modifiedDate.getTime(), todayDate.getTime()));
////			}
//		}else {
//			map.put("message", "modified date is empty.");
//		}
//		return map;
//	}
	
	public static Map<String, Object> gstDataCheckForUpdate(String gstin , Timestamp date, String commonDate) throws ParseException{
		Map<String, Object> map = new HashMap<>();
		map.put("gstin", gstin);
		if(date != null) {
			// gstLastModified Date
			String[] split = date.toString().split(" ");
			Date convertedCurrentDate = sdf.parse(split[0]);
			Calendar modifiedDate = Calendar.getInstance();
			modifiedDate.setTime(convertedCurrentDate);
			logger.info("modifiedDate Date :: " + modifiedDate.get(Calendar.DATE));
			logger.info("modifiedDate month :: " + modifiedDate.get(Calendar.MONTH));
			logger.info("modifiedDate year :: " + modifiedDate.get(Calendar.YEAR));
			
			// dbGstDate
			Calendar dbGstDate = Calendar.getInstance();
			dbGstDate.set(dbGstDate.get(Calendar.YEAR), dbGstDate.get(Calendar.MONTH), Integer.valueOf(commonDate));
			logger.info("today Date :: " + dbGstDate.get(Calendar.DATE));
			logger.info("today month :: " + dbGstDate.get(Calendar.MONTH));
			logger.info("today year :: " + dbGstDate.get(Calendar.YEAR));
			
			// currentDate
			Calendar currDate = Calendar.getInstance();
			//currDate.set(2020, 7, 23);
			currDate.getTime();
			
			logger.info("curr Date :: " + currDate.get(Calendar.DATE));
			logger.info("curr month :: " + currDate.get(Calendar.MONTH));
			logger.info("curr year :: " + currDate.get(Calendar.YEAR));
			
			if(modifiedDate.before(dbGstDate)) {
				if(modifiedDate.get(Calendar.YEAR) == dbGstDate.get(Calendar.YEAR)) {
					if((modifiedDate.get(Calendar.MONTH) >= dbGstDate.get(Calendar.MONTH))) {
						map.put("isDataUpdated", true);
						dbGstDate.set(dbGstDate.get(Calendar.YEAR), dbGstDate.get(Calendar.MONTH) + 1 , Integer.valueOf(commonDate));
						map.put("message", "Data valid upto " + dayDiffrence(currDate.getTime(),dbGstDate.getTime()) + " days.");
						map.put("days", dayDiffrence(currDate.getTime(),dbGstDate.getTime()));
					}
					else {
						map.put("isDataUpdated", false);
						map.put("message", "Data is expired.");
						map.put("days", dayDiffrence(modifiedDate.getTime(),currDate.getTime()));
					}
				}else {
					map.put("isDataUpdated", false);
					map.put("message", "Data is expired.");
					map.put("days", dayDiffrence(modifiedDate.getTime(),currDate.getTime()));
				}
			}else {
				// for after date
				dbGstDate.set(dbGstDate.get(Calendar.YEAR), dbGstDate.get(Calendar.MONTH) + 1 , Integer.valueOf(commonDate));
				map.put("isDataUpdated", true);
				map.put("message", "Data valid upto " + dayDiffrence(currDate.getTime(),dbGstDate.getTime()) + " days.");
				map.put("days", dayDiffrence(currDate.getTime(),dbGstDate.getTime()));
			}
		}else {
			map.put("message", "modified date is empty.");
		}
		return map;
	}
	
	public static String calculateYearAndMonth(Date modifiedDate,Date nowDate) {
		Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(modifiedDate);
        c2.setTime(new Date());
        int yearDiff = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        int monthDiff = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        //int dayDiff = c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
        return "Gst Since " + yearDiff + " Year , " + monthDiff + " Months";
	}
	
	
}
