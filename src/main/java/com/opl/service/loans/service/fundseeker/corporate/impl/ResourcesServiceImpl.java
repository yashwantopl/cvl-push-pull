package com.opl.service.loans.service.fundseeker.corporate.impl;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.opl.service.loans.service.fundseeker.corporate.ResourcesService;
import com.opl.service.loans.utils.dpr.DprSeventhSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

	@Override
	public void readResourcesDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet resourcesSheet, DprUserDataDetail dprUserDataDetail) {
		DprSeventhSheetExcelReader.run(applicationId, storageDetailsId, resourcesSheet, dprUserDataDetail);
	}

}
