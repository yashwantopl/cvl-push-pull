package com.opl.service.loans.service.common.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.opl.mudra.api.gst.model.GstCalculation;
import com.opl.mudra.api.gst.model.GstResponse;
import com.opl.mudra.api.gst.model.util.CommonUtils.GstType;
import com.opl.mudra.api.gst.model.yuva.request.GSTR1Request;
import com.opl.mudra.api.loans.model.GstRelatedPartyRequest;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.client.connect.ConnectClient;
import com.opl.mudra.client.gst.GstClient;
import com.opl.service.loans.domain.GstRelatedParty;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.repository.GstRelatedpartyRepository;
import com.opl.service.loans.repository.common.CommonRepository;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.service.common.RelatedPartyService;

/**
  *@auther : Maaz Shaikh
  */
@Service
public class RelatedPartyServiceImpl implements RelatedPartyService {

	@Autowired
	private GstRelatedpartyRepository gstRelatedPartyRepository;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateRepository;
	
	@Autowired
	private CommonRepository commonRepo;
	
	@Autowired
	private GstClient gstClient;
	
	@Autowired
	private ConnectClient connectClient;
	
	private static Logger logger = LoggerFactory.getLogger(RelatedPartyServiceImpl.class);
	
	@Modifying
	@Transactional(rollbackOn=Exception.class)
	@Override
	public Boolean saveRelatedParty(GstRelatedPartyRequest[] relativeParty)  throws Exception {
		Boolean status = false;
		if(relativeParty!=null && relativeParty.length > 0) {
			List<GstRelatedParty> existingData = gstRelatedPartyRepository.findAllByApplicationIdAndIsActiveIsTrue(relativeParty[0].getApplicationId());
			for (GstRelatedParty gstRelatedParty : existingData) {
				gstRelatedParty .setModifiedBy(gstRelatedParty.getCreatedBy());
				gstRelatedParty.setModifiedDate(new Date());
				gstRelatedParty.setIsActive(false);
				gstRelatedPartyRepository.save(gstRelatedParty);
			}
			
			for (GstRelatedPartyRequest request : relativeParty) {
				GstRelatedParty gstParty=new GstRelatedParty();
				BeanUtils.copyProperties(request, gstParty);
				gstParty.setCreatedBy(request.getCreatedBy());
				gstParty.setCreatedDate(new Date());
				gstParty.setIsActive(true);
				gstRelatedPartyRepository.save(gstParty);
				status = true;
			}
		}
		
		return status;
	}
	
	@Modifying
	@Transactional(rollbackOn=Exception.class)
	@Override
	public Boolean saveRelatedPartyFlag(GstRelatedPartyRequest relativeParty)  throws Exception {
		Boolean status = false;
		if(relativeParty!=null) {
			CorporateApplicantDetail corporate = corporateRepository.findByApplicationIdIdAndIsActive(relativeParty.getApplicationId(), true);
			if(corporate != null && corporate.getApplicationId() != null) {
				corporate.setIsNoneOfRelatedPartySelected(relativeParty.getIsNoneRelativePartyOfSelected());
				corporate.setModifiedDate(new Date());
				corporate.setModifiedBy(relativeParty.getUserId());
				if(corporateRepository.save(corporate) != null) {
					status = true;
				}
			}
	
		}
		
		return status;
	}

	@Override
	public Boolean updateRelatedPartyFilledFlageOnConnect(Long applicationId) throws Exception {
		return connectClient.saveRelatedPartyFlag(applicationId).getIsRelatedPartyFilled();
	}

	@Override
	public Boolean checkGstType(GstRelatedPartyRequest request) throws Exception {
		Boolean status = false;
		GSTR1Request gstRequest=new GSTR1Request();
		gstRequest.setApplicationId(request.getApplicationId());
		try {
			GstResponse calculations = gstClient.getCalculations(gstRequest);
			GstCalculation calc=MultipleJSONObjectHelper.getObjectFromMap((Map)calculations.getData(), GstCalculation.class);
			if(calc != null && calc.getGstType() != null) {
				 if(GstType.COMPOSITE.equals(calc.getGstType()) || GstType.GST_NOT_APPLICABLE.equals(calc.getGstType())){
					 status=updateRelatedPartyFilledFlageOnConnect(request.getApplicationId());
				 }	
			}
			return status;
		}catch (Exception e) {
			logger.error("Exception in getting gst type details for relatedparty {}",e);
			return status;
		}
	}

	

}
