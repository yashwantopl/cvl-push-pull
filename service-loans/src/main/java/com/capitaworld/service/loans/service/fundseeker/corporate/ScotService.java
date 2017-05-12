package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * @author Sanket
 *
 */
public interface ScotService {

	public void readScotDetails(Long applicationId, Long storageDetailsId, FileInputStream file, XSSFSheet scotSheet);

	public void inActiveScotDetails(Long storageDetailsId);

}
