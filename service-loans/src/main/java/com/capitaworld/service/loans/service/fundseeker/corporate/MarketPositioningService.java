package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface MarketPositioningService {

	public void readMarketPositioningDetails(Long applicationId, Long storageDetailsId,
			XSSFSheet marketScenerioSheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveMarketPositioningDetails(Long storageDetailsId);

}
