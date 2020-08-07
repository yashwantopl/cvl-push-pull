package com.opl.mudra.api.payment.enums;

public enum BanksEnum {
	UNION_BANK_OF_INDIA(1L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/ubi-logo.png"),
	SARASWAT_BANK(2L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/Saraswatbank.png"),
	AXIS_BANK(3L ,null),
	ICICI_BANK(4L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/iciciWithoutRoundBorder.png"), //https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/icici.png
	IDBI_BANK(5L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/IDBI.jpg"),
	RBL_BANK(6L ,null),
	TATA_CAPITAL(7L ,null),
	IDFC_BANK(8L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/idfc-first-bank.png"),
	DENA_BANK(9L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/Dena+bank.png"),
	SIDBI_BANK(10L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Sidbi.jpg"),
	NHBS_BANK(11L ,null),
//	CANARA_BANK(12L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Canara-Bank.jpg"),
	CANARA_BANK(12L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/canara_syndicate.png"),
//	INDIAN_BANK(13L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/Indian-Bank.jpg"),
	INDIAN_BANK(13L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/indian_allahabad.png"),
	BOI_BANK(14L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/BOI.jpg"),
	VIJAYA_BANK(15L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/vijya.png"),
	SBI_BANK(16L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/sbi.png"),
	BOB_BANK(17L ,"https://s3.ap-south-1.amazonaws.com/uat-sidbi-data/images/BOB.png"),
//	PNB_BANK(18L ,"https://s3.ap-south-1.amazonaws.com/qa-sidbi-data/images/PNB.jpg"),
	PNB_BANK(18L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/punjab_obc_united.png"),
	UCO_BANK(19L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/UCObank.png"),
	PSB_BANK(20L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/punjabandsindbank.png"),
//	ORIENTAL_BANK_OF_COMMERCE(21L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/obc+bank.png"),
	ORIENTAL_BANK_OF_COMMERCE(21L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/punjab_obc_united.png"),
//	SYNDICATE_BANK(22L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/syndicate+bank.png"),
	SYNDICATE_BANK(22L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/canara_syndicate.png"),
//	ALLAHABAD_BANK(23L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/allahabad+bank.png"),
	ALLAHABAD_BANK(23L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/indian_allahabad.png"),
	CORPORATION_BANK(24L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/Corporationbank.png"),
	CENTRAL_BANK(25L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/central-bank-of-india-logo.png"),
	ANDHRA_BANK(26L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/andhra+bank.png"),
	BANK_OF_MAHARASHTRA(27L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/BOM.png"),
	INDIAN_OVERSEAS_BANK(28L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/indianoverseasbank.png"),
//	UNITED_BANK_OF_INDIA(29L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/united+bank+of+india.png"),
	UNITED_BANK_OF_INDIA(29L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/signup_inpriciple/punjab_obc_united.png"),
	KOTAK_BANK(30L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/Kotek.png"),
	ONLINE_PSB_LOANS_LIMITED(31L ,"https://s3.ap-south-1.amazonaws.com/capitaworld-content-mumbai/email-images/CW-log.png"),
	HDFC_BANK(32L ,"https://s3.ap-south-1.amazonaws.com/prod-sidbi-public-image/images/HDFC.png"),
	ALRAEDAH_FINANCE(33L ,null),
	INDUSIND_BANK(34L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/email-content/Indusindlogo.jpg"),
	YES_BANK(35L ,null),
	ELECTRONICA_FINANCE(36L ,"https://prod-sidbi-public-image.s3.ap-south-1.amazonaws.com/images/efl.jpg"),
	KC_BANK(37L ,null),
	INCRED_FINANCE(38L ,null),
	J_AND_K_BANK(39L ,null);
	
	Long id;
	String bankUrl;
	
	private BanksEnum(Long orgId , String url) {
		 this.id=orgId;
		 this.bankUrl=url;
	}
	
	public Long getOrgId()
	{
		return this.id;
	}
	
	public String getBankUrl()
	{
		return this.bankUrl;
	}
	
	public static String getBankUrl(Long orgId){
		 for(BanksEnum banksEnum :  values()) {
			 if(banksEnum.getOrgId().equals(orgId)){
				 return banksEnum.bankUrl;
			 }
		 }
		 return null;
	 }
	
	
}
