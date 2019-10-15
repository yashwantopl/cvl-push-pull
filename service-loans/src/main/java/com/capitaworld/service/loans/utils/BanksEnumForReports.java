package com.capitaworld.service.loans.utils;



public enum BanksEnumForReports {
			
	UNION_BANK_OF_INDIA(1L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/ubi-logo.png","UBI"),
	SARASWAT_BANK(2L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/Saraswatbank.png","SRB"),
	AXIS_BANK(3L ,null,"AXB"),
	ICICI_BANK(4L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/icici.png","ICI"),
	IDBI_BANK(5L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/IDBI.jpg","IDB"),
	RBL_BANK(6L ,null,"RBL"),
	TATA_CAPITAL(7L ,null,"TTC"),
	IDFC_BANK(8L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/idfc-first-bank.png","IDFC"),
	
	DENA_BANK(9L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/Dena+bank.png", "DB"),
	SIDBI_BANK(10L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Sidbi.jpg", "SIDBI"),
	NHBS_BANK(11L ,null, "NHBS"),
	CANARA_BANK(12L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Canara-Bank.jpg", "CANARA"),
	INDIAN_BANK(13L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Indian-Bank.jpg", "IB"),
	BOI_BANK(14L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/BOI.jpg", "BOI"),
	VIJAYA_BANK(15L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/vijya.png", "VB"),
	SBI_BANK(16L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/sbi.png", "SBI"),
	BOB_BANK(17L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/BOB.png", "BOB"),
	PNB_BANK(18L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/PNB.jpg", "PNB"),
	UCO_BANK(19L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/UCObank.png", "UCO"),
	PSB_BANK(20L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/punjabandsindbank.png", "PSB"),
	ORIENTAL_BANK_OF_COMMERCE(21L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/obc+bank.png", "ORIENTAL"),
	SYNDICATE_BANK(22L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/syndicate+bank.png", "Syndicate"),
	ALLAHABAD_BANK(23L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/allahabad+bank.png", "ALLAHABAD"),
	CORPORATION_BANK(24L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/Corporationbank.png", "CORPORATION"),
	CENTRAL_BANK(25L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/central-bank-of-india-logo.png", "CENTRAL"),
	ANDHRA_BANK(26L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/andhra+bank.png", "ANDHRA"),
	BANK_OF_MAHARASHTRA(27L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/BOM.png", "MAHARASHTRA"),
	INDIAN_OVERSEAS_BANK(28L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/indianoverseasbank.png", "IOB"),
	UNITED_BANK_OF_INDIA(29L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/united+bank+of+india.png", "UNITED"),
	KOTAK_BANK(30L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/Kotek.png", "KOTAK"),
	ONLINE_PSB_LOANS_LIMITED(31L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/CW-log.png", "CW"),
	HDFC_BANK(32L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-data/images/HDFC.png", "HDFC"),
	ALRAEDAH_FINANCE(33L ,null, "ALRAEDAH FINANCE"),
	INDUSIND_BANK(34L ,null, "INDUSIND BANK"),
	YES_BANK(35L ,null, "YES"),
	ELECTRONICA_FINANCE(36L ,"https://prod-sidbi-data.s3.ap-south-1.amazonaws.com/images/efl.jpg", "ELECTRONICA FINANCE"),
	KC_BANK(37L ,null, "KC BANK"),
	INCRED_FINANCE(38L ,null, "INCRED FINANCE"),
	J_AND_K_BANK(39L ,null, "J AND K BANK");
	
	
	Long orgId;
	private final String bankUrl;
	private final String bankName;
	BanksEnumForReports(Long orgId, String bankUrl, String bankName) {
		this.orgId = orgId;
		this.bankUrl = bankUrl;
		this.bankName = bankName;
	}
	
	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getBankUrl() {
		return bankUrl;
	}

	public String getBankName() {
		return bankName;
	}

	public static String getBankUrl(Long orgId){
		 for(BanksEnumForReports banksEnum :  values()) {
			 if(banksEnum.getOrgId().equals(orgId)){
				 return banksEnum.bankUrl;
			 }
		 }
		 return null;
	 }
	
	public static String getBankName(Long orgId){
		 for(BanksEnumForReports banksEnum :  values()) {
			 if(banksEnum.getOrgId().equals(orgId)){
				 return banksEnum.bankName;
			 }
		 }
		 return null;
	 }
	
	public static String[] getBankNameAndUrl(Long orgId){
		String[] str = null;
		 for(BanksEnumForReports banksEnum :  values()) {
			 if(banksEnum.getOrgId().equals(orgId)){
				 str = new String[2];
				 str[0] = banksEnum.bankName != null ? banksEnum.bankName : null;
				 str[1] = banksEnum.bankUrl != null ? banksEnum.bankUrl : null;
				 return str;
			 }
		 }
		 return null;
	 }
	
}
