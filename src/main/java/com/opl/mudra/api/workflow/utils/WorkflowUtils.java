package com.opl.mudra.api.workflow.utils;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class WorkflowUtils {

	public static final String USER_ID = "userId";
	public static final String USER_TYPE = "userType";
	public static final String INVALID_REQUEST = "Invalid Request !";
	public static final String SOMETHING_WENT_WRONG = "Something went wrong !";
	public static final String ELIGIBILITY_SERVICE_NOT_AVAILABLE = "Workflow Service is not available !";
	public static final String ENTITY_MANAGER_REFERENCE = "workflowDataStoreEM";
	public static final String TRANSACTION_MANAGER_REFERENCE = "workflowDataStoreTM";
	public static final String DATASTORE = "workflowDataStore";
	private static final String DATE_FORMATE = "dd-MM-yyyy";
	public static final String UTF_8 = "UTF-8";

	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null
				|| (value instanceof String
						? (((String) value).isEmpty() || "".equals(((String) value).trim())
								|| "null".equalsIgnoreCase((String) value) || "undefined".equals((String) value)
								|| "{}".equals((String) value) || "[]".equals((String) value))
						: false));
	}

	public static String formateDate(Date date) {
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE);
			return dateFormat.format(date);
		} else {
			return null;
		}
	}

	public static String formateDate(Date date, String formate) {
		if (!isObjectNullOrEmpty(date)) {
			if (!isObjectNullOrEmpty(formate)) {
				SimpleDateFormat dateFormat = new SimpleDateFormat(formate);
				return dateFormat.format(date);
			}
			return formateDate(date);
		} else {
			return null;
		}
	}

	public static boolean isObjectListNull(Object... args) {
		for (Object object : args) {
			boolean flag = false;
			if (object instanceof List) {
				flag = isListNullOrEmpty((List<?>) object);
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

	public enum LoanType {
		WORKING_CAPITAL(1), TERM_LOAN(2), HOME_LOAN(3), CAR_LOAN(12), PERSONAL_LOAN(7), LAP_LOAN(13), LAS_LOAN(
				14), UNSECURED_LOAN(15);
		private int value;

		private LoanType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static LoanType getType(Integer x) {
			switch (x) {
			case 1:
				return WORKING_CAPITAL;
			case 2:
				return TERM_LOAN;
			case 3:
				return HOME_LOAN;
			case 12:
				return CAR_LOAN;
			case 7:
				return PERSONAL_LOAN;
			case 13:
				return LAP_LOAN;
			case 14:
				return LAS_LOAN;
			case 15:
				return UNSECURED_LOAN;
			default:
				return null;
			}
		}

	}

	public final class UserType {
		private UserType(){
			// Do nothing because of X and Y.
		}

		public static final int FUND_SEEKER = 1;
		public static final int FUND_PROVIDER = 2;
		public static final int SERVICE_PROVIDER = 3;
		public static final int NETWORK_PARTNER = 4;
	}

	public static final class Action {
		private Action(){
			// Do nothing because of X and Y.
		}
		public static final Long ASSIGN_TO_MAKER_ON_CREATE_PROCESS = 1l;
		public static final Long ASSIGN_TO_MAKER_ON_SAVE = 2l;
		public static final Long DDR_SUBMITTED_TO_CHECKER_ON_SUBMIT_TO_CHECKER = 3l;
		public static final Long DDR_SUBMITTED_TO_CHECKER_ON_SAVE = 4l;
		public static final Long DDR_SUBMITTED_TO_APPROVER_ON_SUBMIT_TO_APPROVE = 5l;
		public static final Long DDR_APPROVED_ON_APPROVE = 6l;
		public static final Long DDR_SEND_BACK_TO_CHECKER_ON_SEND_BACK = 7l;
		public static final Long PENDING = 16l;
		public static final Long SEND_FOR_APPROVAL = 18l;
		public static final Long APPROVED = 19l;
		public static final Long SEND_BACK = 20l;
		public static final Long CHECKER_REVERT_TO_MAKER = 17l;
		public static final Long PICKED_BY_MFI_MAKER = 24l;
		public static final Long CALCULATE_MATCHES = 25l;
		public static final Long APP_SENT_TO_SIDBI_MAKER = 26l;
		public static final Long SAVE_APP_DETAILS = 27l;
		public static final Long APP_SENT_TO_MFI_MAKER = 28l;
		public static final Long APP_SENT_TO_SIDBI_CHECKER = 29l;
		public static final Long SENT_BACK_TO_MFI_CHECKER = 30l;
		public static final Long MFI_APP_IS_APPROVED = 31l;
		public static final Long SENT_BACK_TO_SIDBI_MAKER = 32l;
		public static final Long SENT_TO_DDR_CHECKER = 39l;
		public static final Long SENT_TO_BANK_MAKER = 40l;
		public static final Long SENT_BACK_TO_NBFC_MAKER = 41l;
		public static final Long SENT_TO_BANK_CHECKER = 42l;
		public static final Long APPROVE_SANCTION = 43l;
		public static final Long SEND_TO_NBFC_CHECKER_FOR_DISBURSEMENT = 44l;
		public static final Long SEND_BANK_TO_NBFC_MAKER_DISBURSEMENT_FLOW = 45l;
		public static final Long SEND_TO_BANK_MAKER_FOR_DISBURSEMENT = 46l;
		public static final Long SEND_TO_BANK_CHECKER_FOR_DISBURSEMENT = 47l;
		public static final Long DISBURSED_BY_BANK_CHECKER = 48l;
		public static final Long SEND_BANK_TO_NBFC_MAKER_DISBURSEMENT_HANDOFF_FLOW = 49l;
		public static final Long HANDSOFF_BY_BANK_CHECKER = 50l;
		public static final Long REJECT = 52l;
		
		//Picked By MFI Maker
		//Calculate matches
		//Application sent to sidbi maker
		//Save Application details
		//Application sent back to mfi maker
		//Application is sent to sidbi checker
		//Sent back to mfi checker
		//MFI application is approved
		//Sent back to sidbi maker


	}

	
	public static final class step{
		public static final Long HANDOFF_SEND_TO_NBFC_CHECKER = 62l;
		public static final Long HANDSOFF_SEND_TO_BANK_MAKER = 63l;
		public static final Long HANDOFF_SEND_TO_BANK_CHECKER = 64l;
		public static final Long HANDOFF_REQUEST_APPROVED = 65l;
	}
	
	public static final class Workflow {
		private Workflow(){
			// Do nothing because of X and Y.
		}
		public static final Long DDR = 1l;
		public static final Long BANK_PROCESS = 2l;
		public static final Long FP_SEND_REQUEST_PROCESS = 3l;
		public static final Long MASTER_DATA_APPROVAL_PROCESS = 4l;
		public static final Long PL_PROCESS = 5l;
		public static final Long MFI_PROCESS = 6l;
		public static final Long NBFC_LOAN_PROCESS = 7l;
		public static final Long NBFC_BANK_SANCTION_PROCESS = 8l;
		public static final Long NBFC_BANK_RATIO_SETUP_PROCESS = 9l;
		public static final Long SERVICING_FEES_PROCESS = 10l;
		public static final Long ADD_IM_USER = 11l;
		public static final Long ADD_DEALER_USER = 12l;
	}

	public static final class Role {
		private Role(){
			// Do nothing because of X and Y.
		}
		public static final Long MAKER = 1l;
		public static final Long CHECKER = 2l;
		public static final Long APPROVER = 3l;
		public static final Long ADMIN_FP = 4l;
		public static final Long HEAD_OFFICER = 5l;
		public static final Long BRANCH_OFFICER = 6l;
		public static final Long DEFAULT_FS = 7l;
		public static final Long FP_MAKER = 8l;
		public static final Long FP_CHECKER = 9l;
		public static final Long ADMIN_MAKER = 10l;
		public static final Long ADMIN_CHECKER = 11l;
		public static final Long MFI_MAKER = 17l;
		public static final Long MFI_CHECKER = 18l;
		public static final Long NBFC_MAKER = 310l;
		public static final Long NBFC_CHECKER = 311l;
	}
}
