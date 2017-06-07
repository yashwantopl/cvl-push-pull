package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;

/**
 * @author Sanket
 *
 */
public interface PersonalLoansViewService {
	public RetailPrimaryViewResponse getPersonalLoansPrimaryViewDetails(Long parseLong) throws Exception;
}
