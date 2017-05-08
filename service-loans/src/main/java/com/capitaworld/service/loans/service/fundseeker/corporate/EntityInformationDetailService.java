package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author Sanket
 *
 */
public interface EntityInformationDetailService {

	public void readEntityInformationDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet managementSheet);

	public void inActiveEntityInformationDetails(Long storageDetailsId);

}
