package com.capitaworld.service.loans.utils.scoreexcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capitaworld.service.loans.model.score.ScoreParameterRequestLoans;

public class ScoreExcelReader {
	private static final Logger logger = LoggerFactory.getLogger(ScoreExcelReader.class);
	
	public static List<ScoreParameterRequestLoans> extractCellFromSheet(Sheet sheet) throws NullPointerException {
		logger.info("------------------------------Enter in extractCellFromSheet()-----------------------------------> ");
           List<ScoreParameterRequestLoans> scoreParameterRequestLoansList =new ArrayList<ScoreParameterRequestLoans>();
           ScoreParameterRequestLoans scoreParameterRequestLoans=null;
           logger.info("Extract excel sheet name ----------> "+ sheet.getSheetName());
           try {
		for (int i=1; ( sheet.getRow(i)!=null && sheet.getRow(i).getCell(0)!=null );i++) {
			scoreParameterRequestLoans=new ScoreParameterRequestLoans();
		
            System.out.println(sheet.getRow(i).getCell(0).getNumericCellValue());
			scoreParameterRequestLoans.setTestId((long)sheet.getRow(i).getCell(0).getNumericCellValue());
			scoreParameterRequestLoans.setNetworthSum(sheet.getRow(i).getCell(1).getNumericCellValue());
			scoreParameterRequestLoans.setTermLoanTy(sheet.getRow(i).getCell(2).getNumericCellValue());
			scoreParameterRequestLoans.setLoanAmount(sheet.getRow(i).getCell(3).getNumericCellValue());
			scoreParameterRequestLoans.setCustomerAssociateConcern(sheet.getRow(i).getCell(4).getNumericCellValue());
			scoreParameterRequestLoans.setCibilTransuniunScore(sheet.getRow(i).getCell(5).getNumericCellValue());
			scoreParameterRequestLoans.setDebt(sheet.getRow(i).getCell(6).getNumericCellValue());
			scoreParameterRequestLoans.setEquity(sheet.getRow(i).getCell(7).getNumericCellValue());
			scoreParameterRequestLoans.setTol(sheet.getRow(i).getCell(8).getNumericCellValue());
			scoreParameterRequestLoans.setTnw(sheet.getRow(i).getCell(9).getNumericCellValue());
			scoreParameterRequestLoans.setAvgCurrentRatio(sheet.getRow(i).getCell(10).getNumericCellValue());
			scoreParameterRequestLoans.setDebtorsDays(sheet.getRow(i).getCell(11).getNumericCellValue());
			scoreParameterRequestLoans.setAvgInventory(sheet.getRow(i).getCell(12).getNumericCellValue());
			scoreParameterRequestLoans.setCogs(sheet.getRow(i).getCell(13).getNumericCellValue());
			scoreParameterRequestLoans.setCreditorsDays(sheet.getRow(i).getCell(14).getNumericCellValue());
			scoreParameterRequestLoans.setNetProfitOrLossFY(sheet.getRow(i).getCell(15).getNumericCellValue());
			scoreParameterRequestLoans.setNetProfitOrLossSY(sheet.getRow(i).getCell(16).getNumericCellValue());
			scoreParameterRequestLoans.setNetProfitOrLossTY(sheet.getRow(i).getCell(17).getNumericCellValue());
			scoreParameterRequestLoans.setDepriciationFy(sheet.getRow(i).getCell(18).getNumericCellValue());
			scoreParameterRequestLoans.setDepriciationSy(sheet.getRow(i).getCell(19).getNumericCellValue());
			scoreParameterRequestLoans.setDepriciationTy(sheet.getRow(i).getCell(20).getNumericCellValue());
			scoreParameterRequestLoans.setInterestFy(sheet.getRow(i).getCell(21).getNumericCellValue());
			scoreParameterRequestLoans.setInterestSy(sheet.getRow(i).getCell(22).getNumericCellValue());
			scoreParameterRequestLoans.setInterestTy(sheet.getRow(i).getCell(23).getNumericCellValue());
			scoreParameterRequestLoans.setTotalSaleFy(sheet.getRow(i).getCell(24).getNumericCellValue());
			scoreParameterRequestLoans.setTotalSaleSy(sheet.getRow(i).getCell(25).getNumericCellValue());
			scoreParameterRequestLoans.setTotalSaleTy(sheet.getRow(i).getCell(26).getNumericCellValue());
			scoreParameterRequestLoans.setProfitBeforeTaxOrLossSy(sheet.getRow(i).getCell(27).getNumericCellValue());
			scoreParameterRequestLoans.setProfitBeforeTaxOrLossTy(sheet.getRow(i).getCell(28).getNumericCellValue());
		    scoreParameterRequestLoans.setTotalAsset(sheet.getRow(i).getCell(29).getNumericCellValue());
		    scoreParameterRequestLoans.setOpProfitBeforeInterestSy(sheet.getRow(i).getCell(30).getNumericCellValue());
		    scoreParameterRequestLoans.setOpProfitBeforeInterestTy(sheet.getRow(i).getCell(31).getNumericCellValue());
		    scoreParameterRequestLoans.setNoOfCustomenr(sheet.getRow(i).getCell(32).getNumericCellValue());
		    scoreParameterRequestLoans.setConcentrationCustomer(sheet.getRow(i).getCell(33).getNumericCellValue());
		    scoreParameterRequestLoans.setExperienceInTheBusiness(sheet.getRow(i).getCell(34).getNumericCellValue());
		    scoreParameterRequestLoans.setTotalCredit(sheet.getRow(i).getCell(35).getNumericCellValue());
		    scoreParameterRequestLoans.setProjectedSale(sheet.getRow(i).getCell(36).getNumericCellValue());
		    
		    scoreParameterRequestLoansList.add(scoreParameterRequestLoans);
		}
           }catch (NullPointerException  e ) {
			  e.printStackTrace();
			  logger.error("Exception in extractCellFromSheet() --------------------   Error message'----------> "+ e.getMessage());
			  throw new NullPointerException("Your file formate is not valid  while calculationg scoring.");
		}
		logger.info("Exist from extractCellFromSheet() --------------------Complete excel reading and put in list  'ScoreParameterRequestLoansList'----------> " + scoreParameterRequestLoansList.size());
         return scoreParameterRequestLoansList;
	}

	/*public static double getNumericDataFromCell(XSSFSheet sheet, int cellNumber) {
		log.info("getNumericDataFromCell:" + cellNumber);
		Row row = sheet.getRow(cellNumber);
		Cell cell = row.getCell(cellNumber);
		cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		return Double.parseDouble(decimalFormat.format(cell.getNumericCellValue()));

	}

	public static String getDataFromCell(XSSFSheet sheet, String cellNumber) {
		CellReference cellReference = new CellReference(cellNumber);
		Row row = sheet.getRow(cellReference.getRow());
		Cell cell = row.getCell(cellReference.getCol());
		cell.setCellType(Cell.CELL_TYPE_STRING);
		return cell.getStringCellValue();

	}*/

}
