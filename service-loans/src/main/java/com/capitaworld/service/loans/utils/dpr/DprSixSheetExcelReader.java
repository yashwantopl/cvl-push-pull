package com.capitaworld.service.loans.utils.dpr;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DriverForFutureGrowthDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.RevenueAndOrderBookDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DriverForFutureGrowthDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.RevenueAndOrderBookDetailRepository;


/**
 * @author Sanket
 *
 */
public class DprSixSheetExcelReader
{
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,RevenueAndOrderBookDetailRepository revenueAndOrderBookDetailRepository,DriverForFutureGrowthDetailRepository driverForFutureGrowthDetailRepository,DprUserDataDetail dprUserDataDetail) {

        saveRevenuesAndOrderBook(storageDetailsId,sheet,"11",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"12",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"13",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"14",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"15",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"16",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"17",loanApplicationMaster,revenueAndOrderBookDetailRepository);
        saveRevenuesAndOrderBook(storageDetailsId,sheet,"18",loanApplicationMaster,revenueAndOrderBookDetailRepository);

        saveDriversForFutureGrowth(storageDetailsId,sheet,loanApplicationMaster,driverForFutureGrowthDetailRepository);


        //save question 790
        try {
            String question790Answer = getDataFromCell(sheet, "C4");
            if (!(question790Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question790Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setMarketsCurrentlyServed(question790Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        //save question 791
        try {
            String question791Answer = getDataFromCell(sheet, "C6");
            if (!(question791Answer.isEmpty())) {//if textbox is empty not insert record
                if (!(question791Answer.equals("Insert Text Here"))) {
                	dprUserDataDetail.setTargetMarketStrategy(question791Answer);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveDriversForFutureGrowth(Long storageDetailsId, XSSFSheet sheet,LoanApplicationMaster loanApplicationMaster,DriverForFutureGrowthDetailRepository driverForFutureGrowthDetailRepository)
    {
        int nullCounter=0;
        String firstString     = getDataFromCell(sheet,"B23");
        String secondString    = getDataFromCell(sheet,"B24");
        String thirdString     = getDataFromCell(sheet,"B25");
        String forthString     = getDataFromCell(sheet,"B26");

        if (firstString.isEmpty())
        {
            ++nullCounter;
        }
        if (secondString.isEmpty())
        {
            ++nullCounter;
        }
        if (thirdString.isEmpty())
        {
            ++nullCounter;
        }
        if (forthString.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==4)) {
            try {
            	DriverForFutureGrowthDetail driverForFutureGrowth = new DriverForFutureGrowthDetail();
                driverForFutureGrowth.setApplicationId(loanApplicationMaster);
                driverForFutureGrowth.setFirstString(firstString);
                driverForFutureGrowth.setSecondString(secondString);
                driverForFutureGrowth.setThirdString(thirdString);
                driverForFutureGrowth.setForthString(forthString);
                driverForFutureGrowth.setIsActive(true);
                driverForFutureGrowth.setModifiedDate(new Date());
                driverForFutureGrowth.setCreatedDate(new Date());
                driverForFutureGrowth.setStorageDetailsId(storageDetailsId);
                driverForFutureGrowthDetailRepository.save(driverForFutureGrowth);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void saveRevenuesAndOrderBook(Long storageDetailsId,XSSFSheet sheet, String rowNumber,LoanApplicationMaster loanApplicationMaster,RevenueAndOrderBookDetailRepository revenueAndOrderBookDetailRepository)
    {
        int nullCounter=0;
        String clientName       = getDataFromCell(sheet,"B"+rowNumber);
        String revenues         = getDataFromCell(sheet,"C"+rowNumber);
        String orderInHands     = getDataFromCell(sheet,"D"+rowNumber);
        String potentialOrders  = getDataFromCell(sheet,"E"+rowNumber);
        String geography        = getDataFromCell(sheet,"F"+rowNumber);



        if (clientName.isEmpty())
        {
            ++nullCounter;
        }
        if (revenues.isEmpty())
        {
            ++nullCounter;
        }
        if (orderInHands.isEmpty())
        {
            ++nullCounter;
        }
        if (potentialOrders.isEmpty())
        {
            ++nullCounter;
        }
        if (geography.isEmpty())
        {
            ++nullCounter;
        }

        if (!(nullCounter==5))
        {
            try {
            	RevenueAndOrderBookDetail revenueAndOrderBook = new RevenueAndOrderBookDetail();
                revenueAndOrderBook.setApplicationId(loanApplicationMaster);
                revenueAndOrderBook.setClientName(clientName);
                revenueAndOrderBook.setOrdersInHand(orderInHands);
                revenueAndOrderBook.setPotentialOrders(potentialOrders);
                revenueAndOrderBook.setGeography(geography);
                revenueAndOrderBook.setRevenues(Double.parseDouble(revenues));
                revenueAndOrderBook.setIsActive(true);
                revenueAndOrderBook.setCreatedDate(new Date());
                revenueAndOrderBook.setModifiedDate(new Date());
                revenueAndOrderBook.setStorageDetailsId(storageDetailsId);
                revenueAndOrderBookDetailRepository.save(revenueAndOrderBook);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // System.out.println(boardOfDirectorName + designation + qualification + experience + achievements + functionalDuties);
        }
    }


    public static String getDataFromCell(XSSFSheet sheet,String cellNumber)
    {
        CellReference cellReference = new CellReference(cellNumber);
        Row row = sheet.getRow(cellReference.getRow());
        Cell cell = row.getCell(cellReference.getCol());
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}
