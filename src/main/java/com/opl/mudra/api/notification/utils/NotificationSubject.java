package com.opl.mudra.api.notification.utils;
/**	
 * 	
 * Notification Subject : Points <p>	
 * 1.if you want dynamic subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = true".</p> 	
 * 2.if you  subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = false".</p>	
 * 3.if you not want to pass the common string or its a variable subject then not need to add in this class also not need to pass isDynamic parameter.Simply pass email subject as early doing it.</p>   	
 * 	
 * @author mohammad.maaz</p>	
 * 	
 * method : {@link #getSubject()}	
 * */
public enum NotificationSubject {
	
	EMAIL_FS_PRODUCT_SELECTED_EXISTING(330L, "Existing Business - PSBLOANSIN59MINUTES"),
	EMAIL_FS_SIGNUP_COMPLETE(35L,"Registration Confirmation - Welcome to PSBLOANSIN59MINUTES "),// FS-MAIL NO. 3
	EMAIL_FS_LEAVES_AFTER_OTP(154L,"Sign Up Pending - PSBLOANSIN59MINUTES "),
	EMAIL_FS_LEAVES_BASIC_DETAILS_PAGE(337L,"Loan Application Pending - For Quick Approval "),
	/*EMAIL_FS_LEAVES_GST(136L,"GST Details Pending "),
	EMAIL_FS_LEAVES_ITR(135L,"ITR Details Pending "),
	EMAIL_FS_LEAVES_BANK_STATEMENT_PAGE(137L,"Two Step Away from Quick Business Loan "),
	EMAIL_FS_LEAVES_ONE_FORM(138L,"Submit your company/ firm/ proprietor details"),
	EMAIL_FS_LEAVES_DIRECTOR_DETAILS_PAGE(326L,"One Step Away from Quick Business Loan"),*/
	EMAIL_FS_DID_NOT_PROCEED_TO_PAY(142L,"Payment Pending - Convenience Fee "),
	EMAIL_FS_PAYS_CANCEL_PAYUMONEY(143L,"Payment Failure - For Quick Business Loan Approval "),
	EMAIL_FS_CHECKER_HOLDS_PROPOSAL(314L,"Loan Application - Hold"),
	EMAIL_FS_CHECKER_REJECTS_PROPOSAL(315L,"Loan Application - Rejected "),
	EMAIL_FS_CHECKER_SANCTIONED(316,"Congratulations - Your Loan Has Been Sanctioned!!! "), 
	EMAIL_FS_CHECKER_DISBURSED_PROPOSAL(317L,"Congratulations - Your Loan Has Been Disbursed!!! "),
	EMAIL_FS_WHEN_IN_ELIGIBLE(332,"MSME Offline Application"),
	
	/*Email Verification : OTP -  Only this email not sending from here*/
	
//	All fp dynamic subject
	/* changes made for multiple bank */
	/*EMAIL_ALL_FP_AFTER_INPRINCIPLE_FS(377L," Intimation : New In-Principle Approved Proposal "),
	EMAIL_ALL_MAKERS_AFTER_INPRINCIPLE_TO_FS(185L,"Intimation : New In-Principle Approved Proposal"),
	EMAIL_ALL_CHECKERS_AFTER_INPRINCIPLE_TO_FS(196L," Intimation : New In-Principle Approved Proposal "),
	EMAIL_ALL_BO_INPRINCIPLE_TO_FS(218L," Intimation : New In-Principle Approved Proposal "),
	EMAIL_HO_INPRINCIPLE_TO_FS(208L," Intimation : New In-Principle Approved Proposal "),
	
	EMAIL_OF_THANKYOU_BANKSPECIFIC_FP(405L," Intimation : New In-Principle Approved Proposal "),*/
	
	EMAIL_MAKER_ACCEPT_PROPOSAL_OF_FS(186L,"Intimation: Proposal"),
	EMAIL_MAKER_AFTER_CHECKER_REVERTS_MAKER(188L,"Intimation: Reverted - Application ID "),
	EMAIL_FS_ACCEPTED_BY_MAKER(331L,"Maker Assigned - For Quick Business Loan Approval"),
	EMAIL_MAKER_AFTER_CHECKER_APPROVED_DDR(189L,"DDR Approved - Application ID  "),
	EMAIL_HO_CHECKER_APPROVED_DDR(213L,"Intimation DDR Approved - Application ID "),
	EMAIL_ALL_BO_CHECKER_APPROVED_DDR(223L,"Intimation DDR Approved - Application ID "),
	EMAIL_MAKER_AFTER_CHECKER_HOLDS_PROPOSAL(191L,"Intimation: Hold - Application ID "),
	EMAIL_MAKER_AFTER_CHECKER_REJECTS_PROPOSAL(192L,"Intimation: Reject - Application ID  "),
	EMAIL_HO_CHECKER_REJECTS_PROPOSAL(126,"Intimation: Reject - Application ID  "),
	EMAIL_ALL_BO_CHECKER_REJECTS_PROPOSAL(226L,"Intimation: Reject - Application ID "),
	MAIL_APPROVER_CKR_SEND_DDR_TO_APPROVER(80L,"has send Due Diligence Report for Application Code "),
	MAIL_APPROVER_CKR_SEND_DDR_TO_APPROVER_AFTR_REVIEW(81L," has resend Due Diligence Report for Application Code  "),
	EMAIL_HO_MAKER_ASSIGN_APPLICATION_TO_CHECKER(210L,"Intimation: Assigned DDR- Application ID "),
	EMAIL_BRANCH_FS_WHEN_IN_ELIGIBLE(333,"MSME Offline Application"),
	EMAIL_CHECKER_MAKER_ASSIGN_APPLICATION_TO_CHECKER(194L,"Intimation: Assigned - Application ID "),
	EMAIL_CHECKER_MAKER_REASSIGN_TO_CHECKER(195L,"Intimation : Sent Back - Application ID"),
	EMAIL_HO_MAKER_REASSIGN_TO_CHECKER(212L,"Intimation : Sent Back - Application ID"),
	EMAIL_MAKER_AFTER_CHECKER_SUBMIT_SANCTION_POPUP(190,"Intimation : Sent Back - Application ID "),
	EMAIL_HO_CHECKER_SANCTIONED(214L,"Intimation : Sent Back - Application ID  "),
	EMAIL_ALL_BO_CHECKER_SANCTIONED(224L,"Intimation : Sent Back - Application ID  "),
	EMAIL_ALL_BO_CHECKER_HOLDS_PROPOSAL(225L,"Intimation: Hold - Application ID"),
	EMAIL_MAKER_AFTER_CHECKER_DISBURSED_PROPOSAL(193L,"Intimation : Congratulations your application Disbursed -"),
	EMAIL_ALL_BO_MAKER_REASSIGN_TO_CHECKER(222L,"Intimation: DDR Sent Back - Application ID"),
	EMAIL_DDR_APPROVED(149L,"Congratulations!! Due Diligence Report is approved For Application"),
//	EMAIL_MULTIPLEBANK_SCHEDULER_FS(389L,"No response from Matched Fund Provider"),
	
	/*EMAIL_BRANCH_TRANSFER_HO_DDRCHECKER_DDRMAKER_BO_FS(378L,"Congratulations!! Due Diligence Report is approved For Application"),*/
//	mailParameters.put("isDynamic", true);
	
	/*NTB emails*/
	EMAIL_FS_LEAVES_BASIC_DETAILS_PAGE_NTB(338L,"Fill Up Basic Detail Pending - For Loan Application"),
	EMAIL_FS_LEAVES_ITR_NTB(169L,"ITR Details Pending"),
	EMAIL_FS_LEAVES_BANK_STATEMENT_PAGE_NTB(170L,"Two Step Away from Quick Business Loan "),
	EMAIL_FS_LEAVES_DIRECTOR_DETAILS_PAGE_NTB(327,"Two Step Away from - Quick Business Loan"),
	EMAIL_FS_LEAVES_ONE_FORM_OTHER_DETAILS_NTB(184L,"Step Away From Bank Offer"),
	EMAIL_FS_DID_NOT_PROCEED_TO_PAY_NTB(175L,"Payment Pending - Convenience Fee"),
	/*Pl emails*/
	PL_EMAIL_FS_LEAVES_LOAN_DETAIL_PAGE(376L,"Step Away From Bank Offer!!"),
	PL_EMAIL_FS_LEAVES_BASIC_DETAILS_PAGE(348L,"Fill Up Basic Detail Pending - For Loan Application "),
	PL_EMAIL_FS_LEAVES_PROFILE_DETAIL_PAGE(373L,"One Step Away from - Quick Personal Loan "),
	PL_EMAIL_FS_LEAVES_ITR_PAGE(349L,"ITR Details Pending"),
	PL_EMAIL_FS_LEAVES_BANK_STATEMENT_PAGE(350,"Two Step Away from - Quick Personal Loan"),
	
	BANK_SPECIFIC_GST_LINK(419,"Link verification"),

	
	/*Bulk Email*/
	BULK_EMAIL_TO_FS_FOR_GST(3791L,"Your Profile may have matched with Banks")
	; 
	
	
	
	
	
	
	
	
	private long tId;
	String subject;
	Boolean active;

	/**	
	 * 	
	 * Notification Subject : Points <p>	
	 * 1.if you want dynamic subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = true".</p> 	
	 * 2.if you  subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = false".</p>	
	 * 3.if you not want to pass the common string or its a variable subject then not need to add in this class also not need to pass isDynamic parameter.Simply pass email subject as early doing it.</p>   	
	 * 	
	 * @author mohammad.maaz</p>	
	 * 	
	 * method : {@link #getSubject()}	
	 * */
	NotificationSubject(long tid, String subj) {
		this.tId = tid;
		this.subject = subj;
	}

	private String getSubject() {
		return this.subject;
	}
	
	/**	
	 * 	
	 * Notification Subject : Points <p>	
	 * 1.if you want dynamic subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = true".</p> 	
	 * 2.if you  subject with common string, Then enter entry in this class and pass map parameter value " isDynamic = false".</p>	
	 * 3.if you not want to pass the common string or its a variable subject then not need to add in this class also not need to pass isDynamic parameter.Simply pass email subject as early doing it.</p>   	
	 * 	
	 * @author mohammad.maaz</p>	
	 * 	
	 * method : {@link #getSubject()}	
	 * */
	public static String getSubjectByNotificationId(long tId) {
		 for (NotificationSubject nSubject : NotificationSubject.values()) {
			if(nSubject.tId==tId) {
				return nSubject.getSubject();
			}
		}
		  throw new IllegalArgumentException("The given template not found TemplateId:"+tId);
	}

}

