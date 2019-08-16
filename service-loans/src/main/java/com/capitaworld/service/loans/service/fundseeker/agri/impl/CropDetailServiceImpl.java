package com.capitaworld.service.loans.service.fundseeker.agri.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.agri.CropDetail;
import com.capitaworld.service.loans.model.agri.CropDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.agri.CropDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.agri.CropDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class CropDetailServiceImpl implements CropDetailService {

	
	@Autowired
	private CropDetailRepository corpDetailRepository; 
	
	@Override
	public Boolean save(CropDetailRequest corpDetailRequest,Long applicationId,Long userId) {
		CropDetail corpDetail = corpDetailRepository.findByIdAndIsActive(corpDetailRequest.getId(),true);
		if(corpDetail == null) {
			corpDetail = new CropDetail();
			BeanUtils.copyProperties(corpDetailRequest, corpDetail,CommonUtils.IgnorableCopy.getAuditFields());
			corpDetail.setCreatedBy(userId);
			corpDetail.setCreatedDate(new Date());
			corpDetail.setIsActive(true);
		}else {
			BeanUtils.copyProperties(corpDetailRequest, corpDetail,CommonUtils.IgnorableCopy.getAuditFieldsWithId());
			corpDetail.setModifiedBy(userId);
			corpDetail.setModifiedDate(new Date());
		}
		corpDetailRepository.save(corpDetail);
		return true;
	}

	@Override	
	@Transactional
	public Boolean save(List<CropDetailRequest> corpDetailRequest,Long applicationId,Long userId) {
		for(CropDetailRequest detailRequest :   corpDetailRequest) {
			save(detailRequest, applicationId, userId);
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CropDetailRequest> getList(Long applicationId) {
		List<CropDetail> dbResponse = corpDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		if(CommonUtils.isListNullOrEmpty(dbResponse)) {
			return Collections.emptyList();
		}
		List<CropDetailRequest> corpDetailRequests = new ArrayList<>(dbResponse.size());
		CropDetailRequest corpDetailRequest = null;
		for(CropDetail corpDetail :  dbResponse) {
			corpDetailRequest = new CropDetailRequest();
			BeanUtils.copyProperties(corpDetail, corpDetailRequest);
			corpDetailRequests.add(corpDetailRequest);
		}
		return corpDetailRequests;
	}

	@Override
	@Transactional
	public Boolean inActiveExistingAndsave(List<CropDetailRequest> corpDetailRequest, Long applicationId,Long userId) {
		corpDetailRepository.inactive(applicationId);
		return save(corpDetailRequest, applicationId, userId);
	}
}
