package com.capitaworld.service.loans.utils.scoreexcel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
				evaluator = wb.getCreationHelper().createFormulaEvaluator();
				XSSFFont  font = null;
				//Set font into style
			    XSSFCellStyle style = null;
			    style = wb.createCellStyle();
			    font = wb.createFont();
			    font.setBold(true);
			    style.setFont(font);
				System.out.println(sheet.getSheetName());
				List<Map<String, Object>> listData=null;
				int i=1,j=0;
				ProposalScoreDetailResponse proposalScoreDetailResponse =null;
				ProposalScoreResponse proposalScoreResponse=null;
				logger.info("------------------------------ write score list in excel-------------------------- list size ------"+loansResponseList.size());
			    for (LoansResponse loansResponse: loansResponseList) {
			    	j=0;
			    	sheet.createRow(--i).createCell(0).setCellStyle(style);
			    	sheet.getRow(i).getCell(0).setCellValue("TEST ID");
			    	
			    	sheet.getRow(i).createCell(1).setCellStyle(style);;
			    	sheet.getRow(i).getCell(1).setCellValue("PARAMETER NAME");
			    	
			    	sheet.getRow(i).createCell(2).setCellStyle(style);;
			    	sheet.getRow(i).getCell(2).setCellValue("PARAMETER OPTION");
			    	
			    	sheet.getRow(i).createCell(3).setCellStyle(style);;
			    	sheet.getRow(i).getCell(3).setCellValue("OBTAINED SCORE");
			    	
			    	sheet.getRow(i).createCell(4).setCellStyle(style);
			    	sheet.getRow(i).getCell(4).setCellValue("MAX SCORE");
			    	
			    	++i;
			    	listData = (List<Map<String, Object>>)loansResponse.getListData();
			    	Map<String,Object> map=  (Map<String,Object>) loansResponse.getData();
			    	proposalScoreResponse=MultipleJSONObjectHelper.getObjectFromMap(map,ProposalScoreResponse.class);
			    	while(j<listData.size()) {
			    		
			    		 proposalScoreDetailResponse = MultipleJSONObjectHelper.getObjectFromMap(listData.get(j),ProposalScoreDetailResponse.class);
				    	
 			    		 sheet.createRow(i).createCell(0).setCellValue(proposalScoreResponse.getApplicationId());
			    		 sheet.getRow(i).createCell(1).setCellValue(proposalScoreDetailResponse.getParameterName());
			    		 sheet.getRow(i).createCell(2).setCellValue(proposalScoreDetailResponse.getParameterOption());
			    		 sheet.getRow(i).createCell(3).setCellValue(proposalScoreDetailResponse.getObtainedScore());
			    		 sheet.getRow(i).createCell(4).setCellValue(proposalScoreDetailResponse.getMaxScore());
			    		
				         i++;j++;
			    	}
			        
			         sheet.createRow(i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("TOTAL_SCORE");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getTotalScore());

			    	 sheet.createRow(++i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("SCALE");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getScale());
			    	 
			    	 sheet.createRow(++i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("INTERPRETATION");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getInterpretation());
				    
			    	 sheet.createRow(++i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("FINANCIAL RISK SCORE");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getFinancialRiskScore());
			    	 sheet.getRow(i).createCell(2).setCellStyle(style);
			    	 sheet.getRow(i).getCell(2).setCellValue("FINANCIAL RISK WEIGHT");
			    	 sheet.getRow(i).createCell(3).setCellValue(proposalScoreResponse.getFinancialRiskWeight());
				    
			    	 sheet.createRow(++i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("BUSINESS RISK SCORE");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getBusinessRiskScore());
			    	 sheet.getRow(i).createCell(2).setCellStyle(style);
			    	 sheet.getRow(i).getCell(2).setCellValue("BUSINESS RISK WEIGHT");
			    	 sheet.getRow(i).createCell(3).setCellValue(proposalScoreResponse.getBusinessRiskWeight());
				    
			    	 sheet.createRow(++i).createCell(0).setCellStyle(style);
			    	 sheet.getRow(i).getCell(0).setCellValue("MANAGEMENT RISK SCORE");
			    	 sheet.getRow(i).createCell(1).setCellValue(proposalScoreResponse.getManagementRiskScore());
			    	 sheet.getRow(i).createCell(2).setCellStyle(style);
			    	 sheet.getRow(i).getCell(2).setCellValue("MANAGEMENT RISK WEIGHT");
			    	 sheet.getRow(i).createCell(3).setCellValue(proposalScoreResponse.getManagementRiskWeight());
		    	 
			        i+=5;
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
