package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;

/**
 * @author Sanket
 *
 */
public interface ProposalService {

	public void readProposalDetails(Long applicationId, Long storageDetailsId, FileInputStream file, XSSFSheet proposalSheet,
			DprUserDataDetail dprUserDataDetail);

	public void inActiveProposalDetails(Long storageDetailsId);

}
