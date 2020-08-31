package com.opl.mudra.api.matchengine.utils;

public class MatchConstant {
	 private MatchConstant(){
		 // Do nothing because of X and Y.
	 }
	 // start Credit Rating Organization variable
	 public static final class CreditRatingOrganization
	 {
	 	 private CreditRatingOrganization(){
			// Do nothing because of X and Y.
		 }
		 public static final Long LONG_TERM =1L;
		 public static final Long SHORT_TERM =2L;
		 public static final Long SME =3L;
		 public static final Long CORPORATE_CREDIT_RATING =4L;
	 }
	 
	 
	    // start proposal status  Variable
	    public static final class ProposalStatus {
	 		private ProposalStatus(){
				// Do nothing because of X and Y.
			}
	        public static final Long PENDING = 1L;
	        public static final Long ACCEPT = 2L;
	        public static final Long HOLD = 3L;
	        public static final Long DECLINE = 4L;
	        public static final Long APPROVED = 5L;
	        public static final Long APPROVED_BY_NBFC = -5L;
	        public static final Long PUBLISHED = 6L;
	        public static final Long CANCELED = 7L;
	        public static final Long SENDREQUEST = 8L;
	        public static final Long MATCH = 9L;
	        public static final Long SUGGESTION = 10L;
	        public static final Long TO_BE_DISBURSED = 12L;
	        public static final Long DISBURSED = 11L;
	        public static final Long DISBURSED_BY_NBFC = -11L;
	        public static final Long PARTIALLY_DISBURSED = 13L;
	        // In-Eligible statusId
			public static final Long SANCTIONED = 17L;
			public static final Long SANCTIONED_BY_OTHER_BANK = 18L;
			public static final Long SANCTIONED_BY_OTHER_BRANCH = 19L;
			public static final Long OTHER_BRANCH = 20L;
			public static final Long OTHER_BANK = 21L;
			public static final Long ALREADY_ONLINE_IN_PRINCIPLE = 22L;
			public static final Long DECLINE_AFTER_SANTIONED = 23L;
	    }
	    
	    //initiated by status
	    public static final class InitiatedBy {
	 		private InitiatedBy(){
				// Do nothing because of X and Y.
			}
	        public static final Integer FS = 1;
	        public static final Integer FP = 2;
	    }
	    
	    
	    // start application status  Variable
	    public static final class ApplicationStatus {
	 		private ApplicationStatus(){
				// Do nothing because of X and Y.
			}
	    	public static final Integer NEED_TO_APPLY = 1;
	    	public static final Integer LIVE = 2;
	        
	    }
	    
	    
	    // start user type  Variable
	    public static final class UserType {
	 		private UserType(){
				// Do nothing because of X and Y.
			}
	        public static final Long FUNDSEEKER = 1L;
	        public static final Long FUNDPROVIDER = 2L;
	    }
	    
	    // start proposal stage  Variable
	    public static final class ProposalStage {
	 		private ProposalStage(){
				// Do nothing because of X and Y.
			}
	    	public static final Long PENDING = 0L;
	        public static final Long PRIMARY = 1L;
	        public static final Long FINAL = 2L;
	    }


	public  static final class DateDifference
	    {
	    	private DateDifference()
			{
				// nothing.
			}
	    	public static final Integer CORPORATE=7;
	    	public static final Integer RETAIL=5;
	    }
}
