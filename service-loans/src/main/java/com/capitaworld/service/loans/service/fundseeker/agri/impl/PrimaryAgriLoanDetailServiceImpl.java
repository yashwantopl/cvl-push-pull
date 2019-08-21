package com.capitaworld.service.loans.service.fundseeker.agri.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.agri.PrimaryAgriLoanDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.agri.PrimaryAgriLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.agri.PrimaryAgriLoanDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.agri.PrimaryAgriLoanDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class PrimaryAgriLoanDetailServiceImpl implements PrimaryAgriLoanDetailService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PrimaryAgriLoanDetailServiceImpl.class.getName());

	private static final String PRIMARY_AGRI_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG = "PrimaryAgriLoanDetail not exist in DB with ID=>{}";

	@Autowired
	private PrimaryAgriLoanDetailRepository primaryAgriLoanDetailRepository; 

	@Override
	@Transactional
	public boolean saveOrUpdate(PrimaryAgriLoanDetailRequest agriLoanDetailRequest, Long userId) throws LoansException {
		PrimaryAgriLoanDetail primaryAgriLoanDetail = primaryAgriLoanDetailRepository.findById(agriLoanDetailRequest.getId());
		if(primaryAgriLoanDetail == null) {
			throw new LoansException(PRIMARY_AGRI_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + agriLoanDetailRequest.getId());
		}
		primaryAgriLoanDetail.setLandSize(agriLoanDetailRequest.getLandSize());
		primaryAgriLoanDetail.setIrrigratedUnirrigrated(agriLoanDetailRequest.getIrrigratedUnirrigrated());
		primaryAgriLoanDetail.setSurveyNo(agriLoanDetailRequest.getSurveyNo());
		primaryAgriLoanDetail.setSeasonId(agriLoanDetailRequest.getSeasonId());
		primaryAgriLoanDetail.setCropId(agriLoanDetailRequest.getCropId());
		primaryAgriLoanDetail.setAcres(agriLoanDetailRequest.getAcres());
		primaryAgriLoanDetail.setGuarantorName(agriLoanDetailRequest.getGuarantorName());
		primaryAgriLoanDetail.setDistLevelComAval(agriLoanDetailRequest.getDistLevelComAval());
		primaryAgriLoanDetail.setAvailabilityInputs(agriLoanDetailRequest.getAvailabilityInputs());
		primaryAgriLoanDetail.setIsScaleFinanceDltc(agriLoanDetailRequest.getIsScaleFinanceDltc());
		primaryAgriLoanDetail.setLandIrrFacility(agriLoanDetailRequest.getLandIrrFacility());
		primaryAgriLoanDetail.setAdeArrSellFarmProd(agriLoanDetailRequest.getAdeArrSellFarmProd());
		primaryAgriLoanDetail.setCropSuitSoilClimatic(agriLoanDetailRequest.getCropSuitSoilClimatic());
		primaryAgriLoanDetail.setCropUnderInsuranceScheme(agriLoanDetailRequest.getCropUnderInsuranceScheme());
		primaryAgriLoanDetail.setModifiedBy(userId);
		primaryAgriLoanDetail.setModifiedDate(new Date());
		if(primaryAgriLoanDetail.getApplicationStatusMaster() == null) {
			primaryAgriLoanDetail.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.AgriLoanStatus.PENDING.getId()));
		}
		primaryAgriLoanDetailRepository.save(primaryAgriLoanDetail);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public PrimaryAgriLoanDetailRequest get(Long applicationId, Long userId) throws LoansException  {
		PrimaryAgriLoanDetail primaryAgriLoanDetail = primaryAgriLoanDetailRepository.findByIdAndUserId(applicationId,userId);
		if(primaryAgriLoanDetail == null) {
			throw new LoansException(PRIMARY_AGRI_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + applicationId + " And UserId===>" + userId);
		}
		return prepareRequestFromDomain(primaryAgriLoanDetail);
	}

	@Override
	@Transactional(readOnly = true)
	public PrimaryAgriLoanDetailRequest get(Long applicationId) throws LoansException {
		PrimaryAgriLoanDetail primaryAgriLoanDetail = primaryAgriLoanDetailRepository.findById(applicationId);
		if(primaryAgriLoanDetail == null) {
			throw new LoansException(PRIMARY_AGRI_DETAIL_NOT_EXIST_IN_DB_WITH_ID_MSG + applicationId);
		}
		return prepareRequestFromDomain(primaryAgriLoanDetail);
	}
	
	private PrimaryAgriLoanDetailRequest prepareRequestFromDomain(PrimaryAgriLoanDetail primaryAgriLoanDetail) {
		PrimaryAgriLoanDetailRequest agriLoanDetailRequest = new PrimaryAgriLoanDetailRequest();
		BeanUtils.copyProperties(primaryAgriLoanDetail, agriLoanDetailRequest);
		return agriLoanDetailRequest;
	}
}