package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.service.fundseeker.corporate.ResourcesService;
import com.capitaworld.service.loans.utils.dpr.DprSeventhSheetExcelReader;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class ResourcesServiceImpl implements ResourcesService {

	@Override
	public void readResourcesDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet resourcesSheet, DprUserDataDetail dprUserDataDetail) {
		DprSeventhSheetExcelReader.run(applicationId, storageDetailsId, file, resourcesSheet, dprUserDataDetail);
	}

}
