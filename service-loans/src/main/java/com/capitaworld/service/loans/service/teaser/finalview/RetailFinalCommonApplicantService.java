package com.capitaworld.service.loans.service.teaser.finalview;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;

public interface RetailFinalCommonApplicantService {

	public RetailFinalViewCommonResponse getApplicantCommonInfo(Long applicantId,RetailApplicantDetail applicantDetail);
}
