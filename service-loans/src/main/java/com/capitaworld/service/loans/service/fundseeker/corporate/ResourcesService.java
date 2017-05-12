package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface ResourcesService {

	public void readResourcesDetails(Long applicationId, Long storageDetailsId, FileInputStream file, XSSFSheet resourcesSheet,
			DprUserDataDetail dprUserDataDetail);

}
