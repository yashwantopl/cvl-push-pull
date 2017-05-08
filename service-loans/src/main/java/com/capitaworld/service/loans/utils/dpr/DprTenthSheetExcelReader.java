package com.capitaworld.service.loans.utils.dpr;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.ScotAnalysisDetail;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ScotAnalysisDetailRepository;

/**
 * Created by Dhaval on 25-Jan-16.
 */
public class DprTenthSheetExcelReader
{
    public static void run(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster profile,ScotAnalysisDetailRepository scotAnalysisService) {

        saveScotAnalysisService(storageDetailsId,sheet,profile,scotAnalysisService);
    }

    public static void saveScotAnalysisService(Long storageDetailsId,XSSFSheet sheet,LoanApplicationMaster profile,ScotAnalysisDetailRepository scotAnalysisService)
    {
        int nullCounter=0;
        String strengthDetails           = getDataFromCell(sheet,"C5");
        String concernsDetails           = getDataFromCell(sheet,"C6");
        String concernsMeasure           = getDataFromCell(sheet,"D6");
        String opportunitiesDetails      = getDataFromCell(sheet,"C7");
        String weaknessDetails           = getDataFromCell(sheet,"C8");
        String weaknessMeasure           = getDataFromCell(sheet,"D8");


        if (strengthDetails.isEmpty())
        {
            ++nullCounter;
        }
        if (concernsDetails.isEmpty())
        {
            ++nullCounter;
        }
        if (concernsMeasure.isEmpty())
        {
            ++nullCounter;
        }
        if (opportunitiesDetails.isEmpty())
        {
            ++nullCounter;
        }
        if (weaknessDetails.isEmpty())
        {
            ++nullCounter;
        }
        if (weaknessMeasure.isEmpty())
        {
            ++nullCounter;
        }
        if (!(nullCounter==6))
        {
            try {
                ScotAnalysisDetail scotAnalysis = new ScotAnalysisDetail();
                scotAnalysis.setApplicationId(profile);
                scotAnalysis.setStrengthDetails(strengthDetails);
                scotAnalysis.setConcernsDetails(concernsDetails);
                scotAnalysis.setConcernsMeasure(concernsMeasure);
                scotAnalysis.setOpportunitiesDetials(opportunitiesDetails);
                scotAnalysis.setWeaknessDetials(weaknessDetails);
                scotAnalysis.setWeaknessMeasure(weaknessMeasure);
                scotAnalysis.setStorageDetailsId(storageDetailsId);
                scotAnalysis.setIsActive(true);
                scotAnalysis.setCreatedDate(new Date());
                scotAnalysis.setModifiedDate(new Date());
                scotAnalysisService.save(scotAnalysis);
            } catch (Exception e) {
                e.printStackTrace();
            }
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
