package com.capitaworld.service.loans.service.fundseeker.agri.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.agri.CorpDetail;
import com.capitaworld.service.loans.model.api_model.agri.CorpDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.agri.CorpDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.agri.CorpDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class CorpDetailServiceImpl implements CorpDetailService {

	
	@Autowired
	private CorpDetailRepository corpDetailRepository; 
	
	@Override
	public Boolean save(CorpDetailRequest corpDetailRequest,Long applicationId,Long userId) {
		CorpDetail corpDetail = corpDetailRepository.findOne(corpDetailRequest.getId());
		if(corpDetail == null) {
			corpDetail = new CorpDetail();
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
	public Boolean save(List<CorpDetailRequest> corpDetailRequest,Long applicationId,Long userId) {
		for(CorpDetailRequest detailRequest :   corpDetailRequest) {
			save(detailRequest, applicationId, userId);
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CorpDetailRequest> getList(Long applicationId) {
		List<CorpDetail> dbResponse = corpDetailRepository.findByApplicationIdAndIsActive(applicationId, true);
		if(CommonUtils.isListNullOrEmpty(dbResponse)) {
			return Collections.emptyList();
		}
		List<CorpDetailRequest> corpDetailRequests = new ArrayList<>(dbResponse.size());
		CorpDetailRequest corpDetailRequest = null;
		for(CorpDetail corpDetail :  dbResponse) {
			corpDetailRequest = new CorpDetailRequest();
			BeanUtils.copyProperties(corpDetail, corpDetailRequest);
			corpDetailRequests.add(corpDetailRequest);
		}
		return corpDetailRequests;
	}

	@Override
	@Transactional
	public Boolean inActiveExistingAndsave(List<CorpDetailRequest> corpDetailRequest, Long applicationId,Long userId) {
		corpDetailRepository.inactive(applicationId);
		return save(corpDetailRequest, applicationId, userId);
	}
}
