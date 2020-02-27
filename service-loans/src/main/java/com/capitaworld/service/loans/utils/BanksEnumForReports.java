package com.capitaworld.service.loans.utils;



public enum BanksEnumForReports {
			
	UNION_BANK_OF_INDIA(1L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/ubi-logo.png","UBI", "Union Bank of India"),
	SARASWAT_BANK(2L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/Saraswatbank.png","SRB","Saraswat Bank"),
	AXIS_BANK(3L ,null,"AXB","AXIS BANK"),
	ICICI_BANK(4L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/icici.png","ICI","ICICI BANK"),
	IDBI_BANK(5L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/IDBI.jpg","IDB", "IDBI BANK"),
	RBL_BANK(6L ,null,"RBL","RBL BANK"),
	TATA_CAPITAL(7L ,null,"TTC","TATA CAPITAL"),
	IDFC_BANK(8L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/idfc-first-bank.png","IDFC","IDFC BANK"),
	
	DENA_BANK(9L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/Dena+bank.png", "DB","DENA BANK"),
	SIDBI_BANK(10L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Sidbi.jpg", "SIDBI","SIDBI BANK"),
	NHBS_BANK(11L ,null, "NHBS","NHBS BANK"),
	CANARA_BANK(12L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Canara-Bank.jpg", "CANARA","CANARA BANK"),
	INDIAN_BANK(13L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Indian-Bank.jpg", "IB","INDIAN BANK"),
	BOI_BANK(14L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/BOI.jpg", "BOI","Bank of India"),
	VIJAYA_BANK(15L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/vijya.png", "VB","VIJAYA BANK"),
	SBI_BANK(16L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/sbi.png", "SBI","State Bank Of India"),
	BOB_BANK(17L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/BOB.png", "BOB","Bank of Baroda"),
	PNB_BANK(18L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/PNB.jpg", "PNB","Punjab National Bank"),
	UCO_BANK(19L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/UCObank.png", "UCO","UCO BANK"),
	PSB_BANK(20L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/punjabandsindbank.png", "PSB","PSB BANK"),
	ORIENTAL_BANK_OF_COMMERCE(21L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/obc+bank.png", "ORIENTAL","Oriental Bank Of Commerce"),
	SYNDICATE_BANK(22L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/syndicate+bank.png", "Syndicate","Syndicate Bank"),
	ALLAHABAD_BANK(23L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/allahabad+bank.png", "ALLAHABAD","Allahabad Bank"),
	CORPORATION_BANK(24L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/Corporationbank.png", "CORPORATION","Corporation Bank"),
	CENTRAL_BANK(25L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/central-bank-of-india-logo.png", "CENTRAL","Central Bank"),
	ANDHRA_BANK(26L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/andhra+bank.png", "ANDHRA","Andhra Bank"),
	BANK_OF_MAHARASHTRA(27L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/BOM.png", "MAHARASHTRA","Bank Of Maharashtra"),
	INDIAN_OVERSEAS_BANK(28L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/indianoverseasbank.png", "IOB","Indian Overseas Bank"),
	UNITED_BANK_OF_INDIA(29L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/united+bank+of+india.png", "UNITED","United Bank Of India"),
	KOTAK_BANK(30L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/Kotek.png", "KOTAK","KOTAK BANK"),
	ONLINE_PSB_LOANS_LIMITED(31L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/CW-log.png", "CW","Online PSB Loans Limited"),
	HDFC_BANK(32L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/HDFC.png", "HDFC","HDFC Bank"),
	ALRAEDAH_FINANCE(33L ,null, "ALRAEDAH FINANCE","ALRAEDAH FINANCE"),
	INDUSIND_BANK(34L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/email-content/Indusindlogo.jpg", "INDUSIND BANK","IndusInd Bank"),
	YES_BANK(35L ,null, "YES","YES Bank"),
	ELECTRONICA_FINANCE(36L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/efl.jpg", "ELECTRONICA FINANCE","Electronica Finance"),
	KC_BANK(37L ,null, "KC BANK","KC Bank"),
	INCRED_FINANCE(38L ,null, "INCRED FINANCE","INCRED Finance"),
	J_AND_K_BANK(39L ,null, "J AND K BANK", "J & K Bank");
	
	
	Long orgId;
	private final String bankUrl;
	private final String bankName;
        private final String bankFullName;
	BanksEnumForReports(Long orgId, String bankUrl, String bankName, String bankFullName) {
		this.orgId = orgId;
		this.bankUrl = bankUrl;
		this.bankName = bankName;
        this.bankFullName = bankFullName;
	}

                public Long getOrgId() {
                    return orgId;
                }

//                public void setOrgId(Long orgId) {
//                    this.orgId = orgId;
//                }

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
				 str = new String[3];
				 str[0] = banksEnum.bankName != null ? banksEnum.bankName : null;
				 str[1] = banksEnum.bankUrl != null ? banksEnum.bankUrl : null;
                 str[2] = banksEnum.bankFullName != null ? banksEnum.bankFullName : null;
				 return str;
			 }
		 }
		 return null;
	 }
	
}
