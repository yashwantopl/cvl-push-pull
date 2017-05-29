package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface DprUserDataDetailService {

	public void readDprUserDataDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet productsSheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveDprUserDataDetails(Long storageDetailsId);

	public void save(Long storageDetailsId,DprUserDataDetail dprUserDataDetail, Long applicationId);

}
