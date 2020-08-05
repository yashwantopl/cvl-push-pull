/**
 * 
 */
package com.opl.mudra.api.reports.utils;

/**
 * @author mohammad.maaz
 * 
 * This enum is used for managing file name of jasper reports
 */
public enum JasperReportEnum {
	
	INDUCTIVE_DOCUMENT("InductiveDocumentListForInprinciple",1),
	INDUCTIVE_DOCUMENT_SBI_SPECIFIC("InductiveDocumentListForSbiInprinciple",2),
	INPRINCIPLE_LETTER_PL("InprincipleLetterPL",3),
	INPRINCIPLE_LETTER_HL("InprincipleLetterHL",4),
	SIDBI_SPECIFIC_DOCUMENT("SidbiSpecificDocument",5),
	INDICTIVE_DOCUMENT_FOR_MSME("IndicativeDocumentListForMSME",6),
	INPRINCIPLE_LETTER_AL("InprincipleLetterAL",7),
	INPRINCIPLE_LETTER_ML("InprincipleLetterML",8),
	INDICATIVE_DOCUMENT_FOR_ML("IndicativeDocumentListForML",9);
	
	String reportTypeName;
	Integer reportTypeId;
	
	 JasperReportEnum(String reportTypeName,Integer reportTypeId){
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
		 for(JasperReportEnum jasper :  values()) {
			 if(jasper.getId().equals(reportTypeId)){
				 return jasper.reportTypeName;
			 }
		 }
		 throw new NullPointerException("The given Report Name not found on ReportId:" + reportTypeId);
	 }
	
}
