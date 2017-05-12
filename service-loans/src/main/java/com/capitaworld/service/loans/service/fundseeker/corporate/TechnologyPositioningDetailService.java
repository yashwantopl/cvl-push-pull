package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface TechnologyPositioningDetailService {

	public void readtechnologyPositioningDetail(Long applicationId, Long storageDetailsId, FileInputStream file,
			XSSFSheet technologySheet, DprUserDataDetail dprUserDataDetail);

	public void inActiveTechnologyPositioningDetails(Long storageDetailsId);

}
