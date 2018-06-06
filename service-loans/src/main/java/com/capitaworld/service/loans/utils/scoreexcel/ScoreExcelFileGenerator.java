package com.capitaworld.service.loans.utils.scoreexcel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.scoring.model.ProposalScoreDetailResponse;
import com.capitaworld.service.scoring.model.ProposalScoreResponse;
import com.capitaworld.service.scoring.utils.ScoreParameter;

/**
 * @author Ankit
 *
 */
public class ScoreExcelFileGenerator {

	 private String SCORE_CALCULATION_RESULT = "cw.loan.score.result.location";

	private FormulaEvaluator evaluator;

	private final Logger logger = LoggerFactory.getLogger(ScoreExcelFileGenerator.class);

	@SuppressWarnings("unchecked")
	public Workbook scoreResultExcel(List<LoansResponse> loansResponseList, Environment environment) throws LoansException  {
		logger.info("------------------------------In scoreResultExcel() --------------------------");
		XSSFWorkbook  wb = null;
			try {
				wb = new XSSFWorkbook(OPCPackage.open(environment.getRequiredProperty(SCORE_CALCULATION_RESULT)));
				XSSFSheet  sheet = wb.getSheetAt(0);
				/*evaluator = wb.getCreationHelper().createFormulaEvaluator();*/
				XSSFFont  font1 = null;
				font1 = wb.createFont();
			    font1.setBold(true);
				//Set font into style
				XSSFCellStyle style0 =  wb.createCellStyle();
			    style0.setAlignment(HorizontalAlignment.CENTER);
			    style0.setVerticalAlignment(VerticalAlignment.CENTER);
			    style0.setFont(font1);
			    style0.setWrapText(true);
			    style0.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style0.setBorderTop(HSSFCellStyle.BORDER_THIN );
			    style0.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style0.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    
			    XSSFCellStyle style1 =  wb.createCellStyle();
			    style1.setWrapText(true);
			    style1.setVerticalAlignment(VerticalAlignment.CENTER);
			    style1.setFont(font1);
			    style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderTop(HSSFCellStyle.BORDER_THIN );
			    style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
                
			    XSSFCellStyle style2 =  wb.createCellStyle();
			    style2.setWrapText(true);
			    style2.setVerticalAlignment(VerticalAlignment.CENTER);
			    style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderTop(HSSFCellStyle.BORDER_THIN );
			    style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    
			    XSSFCellStyle style3 =  wb.createCellStyle();
			    style3.setWrapText(true);
			    style3.setVerticalAlignment(VerticalAlignment.CENTER);
			    style3.setAlignment(HorizontalAlignment.CENTER_SELECTION);
			    style3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			    style3.setBorderTop(HSSFCellStyle.BORDER_THIN );
			    style3.setBorderRight(HSSFCellStyle.BORDER_THIN);
			    style3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			    
			    XSSFCellStyle style4 =  wb.createCellStyle();
			    style4.setBorderTop(HSSFCellStyle.BORDER_THICK);
				System.out.println(sheet.getSheetName());
				List<Map<String, Object>> listData=null;
				int i=1,j=0;
				ProposalScoreDetailResponse proposalScoreDetailResponse =null;
				ProposalScoreResponse proposalScoreResponse=null;
				logger.info("------------------------------ write score list in excel-------------------------- list size ------"+loansResponseList.size());
			    for (LoansResponse loansResponse: loansResponseList) {
			    	j=0;
			    	
			    	sheet.createRow(--i).createCell(0).setCellStyle(style0);
			    	sheet.getRow(i).getCell(0).setCellValue("TEST ID");
			    	
			    	sheet.getRow(i).createCell(1).setCellStyle(style0);;
			    	sheet.getRow(i).getCell(1).setCellValue("PARAMETER NAME");
			    	
			    	sheet.getRow(i).createCell(2).setCellStyle(style0);
			    	sheet.getRow(i).getCell(2).setCellValue("PARAMETER OPTION");
			    	
			    	sheet.getRow(i).createCell(3).setCellStyle(style0);
			    	sheet.getRow(i).getCell(3).setCellValue("OBTAINED SCORE");
			    	
			    	sheet.getRow(i).createCell(4).setCellStyle(style0);
			    	sheet.getRow(i).getCell(4).setCellValue("MAX SCORE");
			    	
			    	++i;
			    	listData = (List<Map<String, Object>>)loansResponse.getListData();
			    	Map<String,Object> map=  (Map<String,Object>) loansResponse.getData();
			    	proposalScoreResponse=MultipleJSONObjectHelper.getObjectFromMap(map,ProposalScoreResponse.class);
			    	while(j<listData.size()) {
			    		
			    		 proposalScoreDetailResponse = MultipleJSONObjectHelper.getObjectFromMap(listData.get(j),ProposalScoreDetailResponse.class);
			    		 sheet.createRow(i).createCell(0).setCellStyle(style0);
			    		 if(j==0) {
			    			 CellRangeAddress  cellRangeAddress = new CellRangeAddress(i,i+15, 0, 0);
			    			 sheet.addMergedRegion(cellRangeAddress);
			    			 //sheet.createRow(i).createCell(0).setCellStyle(style1);
			    			 sheet.getRow(i).getCell(0).setCellValue(proposalScoreResponse.getApplicationId());
			    		 }
 			    		//sheet.getRow(i).createCell(1).setCellStyle(style1);
 			    		
 			    		switch (proposalScoreDetailResponse.getParameterName()) {

 			    		case ScoreParameter.COMBINED_NETWORTH:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    			 	sheet.getRow(i).getCell(1).setCellValue("Combined Networth of all Directors");	 
		    			 	break;
		    			 
 			    		case ScoreParameter.CUSTOMER_ASSOCIATE_CONCERN:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    			 	sheet.getRow(i).getCell(1).setCellValue("Customer/ Associate Concern availing financial assistance");	 
		    			 	break;
		    			 
 			    		case ScoreParameter.CIBIL_TRANSUNION_SCORE:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    			 	sheet.getRow(i).getCell(1).setCellValue("Average CIBIL score of all directors");
		    			 	
		    			 	sheet.getRow(i).createCell(6).setCellStyle(style1);
		    			 	sheet.getRow(i).getCell(6).setCellValue("SCALE");
		    			 	sheet.getRow(i).createCell(7).setCellStyle(style3);
		    			 	sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getScale());
		    			 	
		    			 	break;
 			    		case ScoreParameter.DEBT_EQUITY_RATIO:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    		 		sheet.getRow(i).getCell(1).setCellValue("Debt Equity Ratio");
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);
		    				sheet.getRow(i).getCell(6).setCellValue("INTERPRETATION");
		    				sheet.getRow(i).createCell(7).setCellStyle(style0);
		    				sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getInterpretation());
		    				break;
		    			
 			    		case ScoreParameter.TOL_TNW:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("TOL/ TNW");
		    				break;
		    				
 			    		case ScoreParameter.AVERAGE_CURRENT_RATIO:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    		 		sheet.getRow(i).getCell(1).setCellValue("Average Current Ratio for last 2 years");
		    		 		break;

 			    		case ScoreParameter.WORKING_CAPITAL_CYCLE:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
 			    			sheet.getRow(i).getCell(1).setCellValue("Working Capital Cycle Length");
		    		 	
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);
		    				sheet.getRow(i).getCell(6).setCellValue("Scoring Parameter");
		    				
		    				sheet.getRow(i).createCell(7).setCellStyle(style0);
		    				sheet.getRow(i).getCell(7).setCellValue("Actual Score");
		    				
		    				sheet.getRow(i).createCell(8).setCellStyle(style1);
		    				sheet.getRow(i).getCell(8).setCellValue("Risk Weight");
		    				
		    				sheet.getRow(i).createCell(9).setCellStyle(style3);
		    				sheet.getRow(i).getCell(9).setCellValue("Risk Weight Score");
		    				break;

 			    		case ScoreParameter.AVERAGE_ANNUAL_GROWTH_GROSS_CASH:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    		 		sheet.getRow(i).getCell(1).setCellValue("Average annual growth in Gross Cash Accrurals in last 2 yrs");
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);	
		    				sheet.getRow(i).getCell(6).setCellValue("FINANCIAL RISK SCORE");
		    				sheet.getRow(i).createCell(7).setCellStyle(style3);
		    				sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getFinancialRiskScore());
		    				
		    				sheet.getRow(i).createCell(8).setCellStyle(style1);
		    				sheet.getRow(i).getCell(8).setCellValue("FINANCIAL RISK WEIGHT");
		    				sheet.getRow(i).createCell(9).setCellStyle(style2);
		    				sheet.getRow(i).getCell(9).setCellValue(proposalScoreResponse.getFinancialRiskWeight());
		    				break;
		    				
 			    		case ScoreParameter.AVERAGE_ANNUAL_GROWTH_NET_SALE:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    		 		sheet.getRow(i).getCell(1).setCellValue("Average annual growth in Net Sales in last 2 yrs");
		    		 		
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);
		    				sheet.getRow(i).getCell(6).setCellValue("BUSINESS RISK SCORE");
		    				sheet.getRow(i).createCell(7).setCellStyle(style3);
		    				sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getBusinessRiskScore());
		    				
		    				sheet.getRow(i).createCell(8).setCellStyle(style1);
		    				sheet.getRow(i).getCell(8).setCellValue("BUSINESS RISK WEIGHT");
		    				sheet.getRow(i).createCell(9).setCellStyle(style2);
		    				sheet.getRow(i).getCell(9).setCellValue(proposalScoreResponse.getBusinessRiskWeight());
		    				break;
		    			
 			    		case ScoreParameter.AVERAGE_EBIDTA:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    		 		sheet.getRow(i).getCell(1).setCellValue("Average EBIDTA for last 2 years/ Term Loans");
		    				
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);
		    				sheet.getRow(i).getCell(6).setCellValue("MANAGEMENT RISK SCORE");
		    				sheet.getRow(i).createCell(7).setCellStyle(style3);
		    				sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getManagementRiskScore());
		    				
		    				sheet.getRow(i).createCell(8).setCellStyle(style1);
		    				sheet.getRow(i).getCell(8).setCellValue("MANAGEMENT RISK WEIGHT");
		    				sheet.getRow(i).createCell(9).setCellStyle(style2);
		    				sheet.getRow(i).getCell(9).setCellValue(proposalScoreResponse.getManagementRiskWeight());
		    				break;
		    			
 			    		case ScoreParameter.AVERAGE_ANNUAL_GROSS_CASH_ACCRUALS:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("Average annual growth in Gross Cash Accrurals in last 2 yrs");
		    			
		    				sheet.getRow(i).createCell(6).setCellStyle(style1);
		    				sheet.getRow(i).getCell(6).setCellValue("TOTAL SCORE");
		    				sheet.getRow(i).createCell(7).setCellStyle(style3);
		    				sheet.getRow(i).getCell(7).setCellValue(proposalScoreResponse.getTotalScore());
		    				break;
		    				
 			    		case ScoreParameter.AVERAGE_INTEREST_COV_RATIO:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("Average Interest Coverage Ratio for last 2 yrs");
		    				break;
 			    		case ScoreParameter.NO_OF_CUSTOMER:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("No. of customers based on GSTIN data");
		    				break;
 			    		case ScoreParameter.CONCENTRATION_CUSTOMER:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("Concentration of Customers");
		    				break;
 			    		case ScoreParameter.EXPERIENCE_IN_THE_BUSINESS:
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("Total Experience in Business");
		    				break;
 			    		case ScoreParameter.CREDIT_SUMMATION :
 			    			sheet.getRow(i).createCell(1).setCellStyle(style1);
		    				sheet.getRow(i).getCell(1).setCellValue("Credit Summation");
		    				break;
		    		}
 			    		 sheet.getRow(i).createCell(2).setCellStyle(style2);
			    		 sheet.getRow(i).getCell(2).setCellValue(proposalScoreDetailResponse.getParameterOption());
			    		 sheet.getRow(i).createCell(3).setCellStyle(style3);
			    		 sheet.getRow(i).getCell(3).setCellValue(proposalScoreDetailResponse.getObtainedScore());
			    		 sheet.getRow(i).createCell(4).setCellStyle(style3);
			    		 sheet.getRow(i).getCell(4).setCellValue(proposalScoreDetailResponse.getMaxScore());
				         i++;j++;
			    	}
			        i+=2;
			        sheet.createRow(i).setRowStyle(style4);
			        i+=3;
			    }
			    logger.info("--------------------- writing scoring list in excel complete -------------");
				
			} catch (NullPointerException | IllegalStateException | IOException | InvalidFormatException e) {
				
				e.printStackTrace();
				logger.error("-----------------Exception while write data in excel ------------- message" + e.getMessage() );
				throw new LoansException("Exception when write data in Excel File");
			}
		return wb;
	}

	/*private Double convertValueToDoubleFromString(String value) throws ParseException {
		try {
			if (value != null && value.trim().length() > 0) {
				return NumberFormat.getNumberInstance(java.util.Locale.US).parse(value).doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception e) {
			return 0.0;
		}
	}

	private Date convertStringTODate(String value) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		SimpleDateFormat output = new SimpleDateFormat("dd-MMM-yy");
		Date date = sdf.parse(value);
		String formattedTime = output.format(date);
		return output.parse(formattedTime);

	}

	private Double addStringNumbers(String... a) throws ParseException {
		Double sum = 0.0;
		for (String b : a) {
			sum = sum + convertValueToDoubleFromString(b);
		}
		return sum;
	}

	private Double evaluateCellValue(Cell cell) {
		CellValue cellValue = evaluator.evaluate(cell);
		return cellValue.getNumberValue();
	}*/

}