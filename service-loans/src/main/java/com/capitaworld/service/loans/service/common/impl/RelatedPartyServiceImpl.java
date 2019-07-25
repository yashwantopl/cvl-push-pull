package com.capitaworld.service.loans.service.common.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.GstRelatedParty;
import com.capitaworld.service.loans.model.GstRelatedPartyRequest;
import com.capitaworld.service.loans.repository.GstRelatedpartyRepository;
import com.capitaworld.service.loans.service.common.RelatedPartyService;

/**
  *@auther : Maaz Shaikh
  */
@Service
public class RelatedPartyServiceImpl implements RelatedPartyService {

	@Autowired
	private GstRelatedpartyRepository gstRelatedPartyRepository;
	
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

}
