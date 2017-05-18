package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface FeasibilityService {

	public void readFeasibilityDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet feasibilitySheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveFeasibilityDetails(Long storageDetailsId);

}
