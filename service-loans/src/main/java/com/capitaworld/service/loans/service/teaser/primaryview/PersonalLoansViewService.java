package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.PersonalLoandsPrimaryViewResponse;

/**
 * @author Sanket
 *
 */
public interface PersonalLoansViewService {

	public Boolean validatePersonalLoansPrimaryViewRequest(String toApplicationId);

	public PersonalLoandsPrimaryViewResponse getPersonalLoansPrimaryViewDetails(Long parseLong, Long userId) throws Exception;

}
