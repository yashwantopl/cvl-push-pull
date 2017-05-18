package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface MarketPositioningService {

	public void readMarketPositioningDetails(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet marketScenerioSheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveMarketPositioningDetails(Long storageDetailsId);

}
