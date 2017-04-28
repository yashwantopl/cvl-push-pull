package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface CreditRatingOrganizationDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<CreditRatingOrganizationDetailRequest> getcreditRatingOrganizationDetailsList(Long id);

}
