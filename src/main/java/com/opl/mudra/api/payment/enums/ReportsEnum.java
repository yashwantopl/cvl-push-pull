package com.opl.mudra.api.payment.enums;

public enum ReportsEnum {

	INDUCTIVE_DOCUMENT("InductiveDocumentListForInprinciple",1),
	INDUCTIVE_DOCUMENT_SBI_SPECIFIC("InductiveDocumentListForSbiInprinciple",2),
	INPRINCIPLE_LETTER_PL("InprincipleLetterPL",3),
	INPRINCIPLE_LETTER_HL("InprincipleLetterHL",4),
	SIDBI_SPECIFIC_DOCUMENT("SidbiSpecificDocument",5),
	INDICTIVE_DOCUMENT_FOR_MSME("IndicativeDocumentListForMSME",6),
	INPRINCIPLE_LETTER_AL("InprincipleLetterAL",7),
	INPRINCIPLE_LETTER_ML("InprincipleLetterML",8),
	INDICATIVE_DOCUMENT_FOR_ML("IndicativeDocumentListForML",9),
	INPRINCIPLE_LETTER_ODOP("InprincipleLetterODOP",10),
	INDICATIVE_DOCUMENT_FOR_ODOP("IndicativeDocumentListForODOP",11);
	
	String reportTypeName;
	Integer reportTypeId;
	
	ReportsEnum(String reportTypeName,Integer reportTypeId){
		this.reportTypeId = reportTypeId;
		this.reportTypeName = reportTypeName;
	}
	 
	 public Integer getId() {
		 return reportTypeId;
	 }
	 
	 public String getName() {
		 return reportTypeName;
	 }
	 
	 public static String getJasperReportName(Integer reportTypeId){
		 for(ReportsEnum jasper :  values()) {
			 if(jasper.getId().equals(reportTypeId)){
				 return jasper.reportTypeName;
			 }
		 }
		 throw new NullPointerException("The given Report Name not found on ReportId:" + reportTypeId);
	 }
	
}
