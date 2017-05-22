package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.service.fundseeker.corporate.MarketScenerioService;
import com.capitaworld.service.loans.utils.dpr.DprFifthSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class MarketScenerioServiceImpl implements MarketScenerioService {
	


	@Override
	public void readMarketScenerioDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet technologySheet, DprUserDataDetail dprUserDataDetail) {
		
		DprFifthSheetExcelReader.run(storageDetailsId, technologySheet, dprUserDataDetail);}

}
