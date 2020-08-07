package com.opl.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opl.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface ResourcesService {

	public void readResourcesDetails(Long applicationId, Long storageDetailsId, XSSFSheet resourcesSheet,
			DprUserDataDetail dprUserDataDetail);

}
