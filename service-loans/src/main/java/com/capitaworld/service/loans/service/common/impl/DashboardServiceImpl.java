package com.capitaworld.service.loans.service.common.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
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
import com.capitaworld.service.oneform.client.OneFormClient;
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
	private OneFormClient  oneFormClient;
	
	@Autowired
	private UsersClient usersClient;

	@Override
	public DashboardProfileResponse getBasicProfileInfo(Long applicationId, Long userId,boolean isSP) throws Exception {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getBasicProfileInfo");
		
		Integer productId = null;
		/*if(isSP){
			productId = loanApplicationRepository.getProductIdByApplicationIdForSP(applicationId, userId);
		}else{
			productId = loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);			
		}*/
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
		productId = loanApplicationMaster.getProductId();
		DashboardProfileResponse dashboardProfileResponse = null;
		int userMainType = CommonUtils.getUserMainType(productId);
		if (userMainType == CommonUtils.UserMainType.CORPORATE) {
			dashboardProfileResponse = new DashboardProfileResponse();
			dashboardProfileResponse.setProductId(productId);
			CorporateApplicantDetail corporateApplicantDetail = null;
			if(isSP){
				corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserIdForSP(userId, applicationId);
			}else{
				corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(userId, applicationId);	
			}
			

			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				CommonDocumentUtils.endHook(logger, "getBasicProfileInfo");
				return dashboardProfileResponse;
			}
			dashboardProfileResponse.setPan(corporateApplicantDetail.getPanNo());
			dashboardProfileResponse.setId(corporateApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(corporateApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
				dashboardProfileResponse.setCity(
						CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
				dashboardProfileResponse.setState(CommonDocumentUtils
						.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
			}

			dashboardProfileResponse
					.setName(!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName())
							? corporateApplicantDetail.getOrganisationName() : "NA");
			dashboardProfileResponse.setAbout(corporateApplicantDetail.getAboutUs());

		} else {
			dashboardProfileResponse = new DashboardProfileResponse();
			dashboardProfileResponse.setProductId(productId);
			RetailApplicantDetail retailApplicantDetail = null;
			if(isSP){
				retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserIdForSP(userId, applicationId);
			}else{
				retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(userId, applicationId);	
			}
			
			if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
				CommonDocumentUtils.endHook(logger, "getBasicProfileInfo");
				return dashboardProfileResponse;
			}
			dashboardProfileResponse.setPan(retailApplicantDetail.getPan());
			dashboardProfileResponse.setId(retailApplicantDetail.getId());
			dashboardProfileResponse.setApplicationId(retailApplicantDetail.getApplicationId().getId());
			// Setting City Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId())) {
				dashboardProfileResponse
						.setCity(CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), oneFormClient));
			}

			// Setting State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())) {
				dashboardProfileResponse.setState(CommonDocumentUtils
						.getState(retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient));
			}

			// Country State Value
			if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())) {
				dashboardProfileResponse.setCountry(CommonDocumentUtils
						.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient));
			}

			String name = !CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())
					? retailApplicantDetail.getFirstName() + " " : "NA ";
			name = name.concat(!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())
					? retailApplicantDetail.getLastName() : "NA");
			dashboardProfileResponse.setName(name);
		}
		dashboardProfileResponse.setAddress();
		CommonDocumentUtils.endHook(logger, "getBasicProfileInfo");
		return dashboardProfileResponse;
	}

	@Override
	public Integer getCount(int userType) throws Exception {
		CommonDocumentUtils.startHook(logger, "getCount");
		try {
			UserResponse response = usersClient.getActiveUserCount(userType);
			if (response != null) {
				return (Integer) response.getData();
			}
			CommonDocumentUtils.endHook(logger, "getCount");
			return 0;
		} catch (Exception e) {
			logger.error("Error while getting count for Dashbord");
			e.printStackTrace();
			throw new ExcelException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public UserResponse getFPBasicProfileInfo(Long userId) throws Exception {
		CommonDocumentUtils.startHook(logger, "getFPBasicProfileInfo");
		try {
			CommonDocumentUtils.endHook(logger, "getFPBasicProfileInfo");
			return usersClient.getFPDashboardDetails(userId);
		} catch (Exception e) {
			logger.error("Error while getting FP Details on Dashbord");
			e.printStackTrace();
			throw new ExcelException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
