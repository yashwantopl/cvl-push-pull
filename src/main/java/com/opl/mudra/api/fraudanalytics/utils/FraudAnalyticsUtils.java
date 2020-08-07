package com.opl.mudra.api.fraudanalytics.utils;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FraudAnalyticsUtils {

	public static final String SOMETHING_WENT_WRONG = "Something went wrong";
	
	public static final String SUCCESSFUL= "Successfully Called";
	public static final String INVALID_REQUEST = "Invalid Request !";
	
	public final class  Status{
		private Status(){
			// nothing.
		}
		public static final String SUCCESS_STATUS = "S";
		public static final String FAILURE_STATUS = "F";
		public static final String PENDING_STATUS = "P";
		
		// For RBI Fraud Logging
		public static final String YES_STATUS = "Yes";
		public static final String NO_STATUS = "No";
	}
	
	
	public final class  RuleType{
		private RuleType(){
			// nothing.
		}
		public static final String NATIONAL = "NATIONAL";
		public static final String LOCAL = "LOCAL";
	}
	
	public final class  RuleStatus{
		private RuleStatus(){
			//nothing
		}
		public static final String INCS = "Inconsistency";
		public static final String INC = "Inconsistency";
		public static final String NCS = "Non Clear Submission";
		public static final String NC = "Non Clear";
		public static final String VEL= "Velocity";
		public static final String PER = "Person";
		public static final String REF = "Reference";
		public static final String CL = "Clear";
	}
	
	public final class  RuleAction{
		private RuleAction(){
			// nothing.
		}
		public static final String ACCEPT = "Accept";
		public static final String REJECT = "Reject";
		public static final String INVESTIGATE = "Investigate";
	}

	public final class  ScoreInterpretation{
		private ScoreInterpretation(){
			//nothing.
		}
		public static final String RISKY = "Risky";
		public static final String LESS_RISKY = "Less Risky";
		public static final String HIGH_RISKY = "High Risky";
	}

	public final class  ScoreInterpretationSCSS{
		private ScoreInterpretationSCSS(){
			//nothing.
		}
		public static final String RISKY = "Medium Risk";
		public static final String LESS_RISKY = "Low Risk";
		public static final String HIGH_RISKY = "High Risk";
	}
	
	public final class  ProductType{
		private ProductType(){
			// nothing.
		}
		public static final String EXISTING = "Existing";
		public static final String NTB = "NTB";
	}
	
	public final class  Particulars{
		private Particulars(){
			//nothing.
		}
		public static final String FD_RBI_25_LAKHS_AND_1_CR = "Whether listed in RBI Default List (Loan Amt. between Rs. 25-100 Lakhs & Rs. 1 CR)?";
		public static final String FD_RBI_CAUTION_ADVICE = "Whether listed in RBI Caution Advice?";
		public static final String FD_RBI_DEFAULTER_LIST = "Whether listed in RBI Defaulter List?";
		public static final String FD_RBI_FRAUD = "Whether listed in RBI Fraud?";
		public static final String FD_IBA_FRAUD = "Whether listed in IBA Fraud?";
	}

	public final class  LogicCheck{
		private LogicCheck(){
			//nothing.
		}
		public static final String NAME_OF_ENTITY = "Check For Name of Entity";
		public static final String NAME_OF_PERSON= "Check For Name of Director/Promoter/Proprietor";
		public static final String PAN_OF_ENTITY_OR_PERSON = "Check for PAN of the Entity/Director/Promoter/Propieter";
		
	}

	public enum HunterScore{

		LESS_RISKY(1,950,922337),
		RISKY(2,600,950),
		HIGH_RISKY(3,0,600);

		HunterScore(Integer id, Integer lowerScore, Integer higherScore){
			this.id = id;
			this.lowerScore = lowerScore;
			this.higherScore = higherScore;
		}

		private Integer id;

		private Integer lowerScore;

		private Integer higherScore;

		public Integer getId() {
			return id;
		}

		public Integer getLowerScore() {
			return lowerScore;
		}

		public Integer getHigherScore() {
			return higherScore;
		}
	}
	
	public enum HunterScoreExisting{

		LESS_RISKY(1,998,1032),
		MEDIUM_RISKY(2,796,997),
		HIGH_RISKY(3,0,795);

		HunterScoreExisting(Integer id, Integer lowerScore, Integer higherScore){
			this.id = id;
			this.lowerScore = lowerScore;
			this.higherScore = higherScore;
		}

		private Integer id;

		private Integer lowerScore;

		private Integer higherScore;

		public Integer getId() {
			return id;
		}

		public Integer getLowerScore() {
			return lowerScore;
		}

		public Integer getHigherScore() {
			return higherScore;
		}
	}
	
	public static final Map<String, String> removableWordsInPersonName;
	public static final Map<String, String> removableWordsInEntityName;
    static {
    	Map<String, String> personRemovable = new HashMap<String, String>();
    	personRemovable.put("MR", "");
    	personRemovable.put("MR.", ""); 
    	personRemovable.put("MRS", "");
		personRemovable.put("MRS.", "");
		personRemovable.put("SMT", "");
		personRemovable.put("SMT.", "");
		personRemovable.put("SRI", "");
		personRemovable.put("SRI.", "");
		
		removableWordsInPersonName = Collections.unmodifiableMap(personRemovable);
		
		
		Map<String, String> entityRemovable = new HashMap<String, String>();
		entityRemovable.put("private", "");
		entityRemovable.put("pvt", "");
		entityRemovable.put("pvt.", ""); 
		
		entityRemovable.put("company", "");
		entityRemovable.put("co", "");
		entityRemovable.put("co.", "");
		
		entityRemovable.put("limited", "");
		entityRemovable.put("ltd", "");
		entityRemovable.put("ltd.", "");
		
		entityRemovable.put("(india)", "");
		entityRemovable.put("(p)", "");
		entityRemovable.put("-", "");
		entityRemovable.put("llp", "");
		entityRemovable.put("partnership", "");
		entityRemovable.put("pvt.ltd.", "");
		entityRemovable.put("(p)ltd", "");
		entityRemovable.put("(p)ltd.", "");
		entityRemovable.put("(p)limited", "");
		
		entityRemovable.put("soc.", "");
		
		
		
		removableWordsInEntityName = Collections.unmodifiableMap(entityRemovable);
		
    }
    
    public static Date toDate(String data) {
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
			return formatDate.parse(data);
		} catch (Exception e) {
			return null;
		}
	}
    
    public static Boolean toBoolean(String value) {
		try {
			return Boolean.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Double toDouble(String value) {
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Integer toInteger(String value) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Long toLong(String value) {
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			return null;
		}
	}
}
