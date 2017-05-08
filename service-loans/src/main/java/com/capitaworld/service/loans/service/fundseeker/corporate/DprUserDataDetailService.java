package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface DprUserDataDetailService {

	public void readDprUserDataDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet productsSheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveDprUserDataDetails(Long storageDetailsId);

	public void save(Long storageDetailsId,DprUserDataDetail dprUserDataDetail);

}
