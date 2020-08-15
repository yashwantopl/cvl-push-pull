package com.opl.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.opl.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface FeasibilityService {

	public void readFeasibilityDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet feasibilitySheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveFeasibilityDetails(Long storageDetailsId);

}
