package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author Sanket
 *
 */
public interface ManagementDetailService {

	public void readManagementDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet keyManagementSheet);

	public void inActiveManagementDetails(Long storageDetailsId);

}
