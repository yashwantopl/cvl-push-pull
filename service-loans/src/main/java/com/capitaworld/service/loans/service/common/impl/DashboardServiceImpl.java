package com.capitaworld.service.loans.service.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

	private static final Logger logger = LoggerFactory.getLogger(DashboardServiceImpl.class);

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private Environment environment;

	@Override
	public DashboardProfileResponse getBasicProfileInfo(Long applicationId, Long userId) throws Exception {
		// TODO Auto-generated method stub
		Integer productId = loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);
		DashboardProfileResponse dashboardProfileResponse = null;
		int userMainType = CommonUtils.getUserMainType(productId);
		if (userMainType == CommonUtils.UserMainType.CORPORATE) {
			dashboardProfileResponse = new DashboardProfileResponse();
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.getByApplicationAndUserId(userId, applicationId);

			dashboardProfileResponse.setId(corporateApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(corporateApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
				dashboardProfileResponse.setCity(
						CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), environment));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
				dashboardProfileResponse.setState(CommonDocumentUtils
						.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), environment));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), environment));
			}

			dashboardProfileResponse
					.setName(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName())
							? corporateApplicantDetail.getOrganisationName() : "NA");
			dashboardProfileResponse.setAbout(corporateApplicantDetail.getAboutUs());

		} else {
			dashboardProfileResponse = new DashboardProfileResponse();
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
					.getByApplicationAndUserId(userId, applicationId);
			dashboardProfileResponse.setId(retailApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(retailApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCity())) {
				dashboardProfileResponse
						.setCity(CommonDocumentUtils.getCity(retailApplicantDetail.getAddressCity(), environment));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressState())) {
				dashboardProfileResponse.setState(
						CommonDocumentUtils.getState(retailApplicantDetail.getAddressState().longValue(), environment));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCountry())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(retailApplicantDetail.getAddressCountry().longValue(), environment));
			}

			String name = !CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())
					? retailApplicantDetail.getFirstName() + " " : "NA ";
			name = name.concat(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())
					? retailApplicantDetail.getLastName() : "NA");
			dashboardProfileResponse.setName(name);
		}
		dashboardProfileResponse.setProductId(productId);
		dashboardProfileResponse.setAddress();
		return dashboardProfileResponse;
	}

	@Override
	public Integer getCount(int userType) throws Exception {
		try {
			UsersClient client = new UsersClient(environment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
			UserResponse response = client.getActiveUserCount(CommonUtils.UserType.FUND_PROVIDER);
			if (response != null) {
				return (Integer)response.getData();
			}
			return 0;
		} catch (Exception e) {
			logger.error("Error while getting count for Dashbord");
			e.printStackTrace();
			throw new ExcelException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
