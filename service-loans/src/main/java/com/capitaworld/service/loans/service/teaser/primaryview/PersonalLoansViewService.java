package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;

/**
 * @author Sanket
 *
 */
public interface PersonalLoansViewService {

	public Boolean validatePersonalLoansPrimaryViewRequest(String toApplicationId);

	public RetailPrimaryViewResponse getPersonalLoansPrimaryViewDetails(Long parseLong, Long userId) throws Exception;

}
