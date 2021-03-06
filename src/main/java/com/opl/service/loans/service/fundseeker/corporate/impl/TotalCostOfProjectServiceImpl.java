package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.corporate.TotalCostOfProjectRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.oneform.enums.Denomination;
import com.opl.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.TotalCostOfProject;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.TotalCostOfProjectRepository;
import com.opl.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;

@Service
@Transactional
public class TotalCostOfProjectServiceImpl implements TotalCostOfProjectService {
	private static final Logger logger = LoggerFactory.getLogger(TotalCostOfProjectServiceImpl.class.getName());
	@Autowired
	private TotalCostOfProjectRepository totalCostOfProjectRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				TotalCostOfProjectRequest totalCostOfProjectRequest = (TotalCostOfProjectRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, TotalCostOfProjectRequest.class);
				TotalCostOfProject totalCostOfProject = null;
				if (totalCostOfProjectRequest.getId() != null) {
					totalCostOfProject = totalCostOfProjectRepository.findOne(totalCostOfProjectRequest.getId());
				} else {
					totalCostOfProject = new TotalCostOfProject();
					totalCostOfProject.setCreatedBy(frameRequest.getUserId());
					totalCostOfProject.setCreatedDate(new Date());
				}
				
				BeanUtils.copyProperties(totalCostOfProjectRequest, totalCostOfProject);
				totalCostOfProject.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				totalCostOfProject.setProposalId(new ApplicationProposalMapping(frameRequest.getProposalMappingId()));
				totalCostOfProject.setModifiedBy(frameRequest.getUserId());
				totalCostOfProject.setModifiedDate(new Date());
				totalCostOfProjectRepository.save(totalCostOfProject);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save totalCostOfProject :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailListByProposalId(Long proposalId, Long userId)
			throws Exception {
		try {
			List<TotalCostOfProject> totalCostOfProjectRequest = totalCostOfProjectRepository
					.listCostOfProjectFromProposalId(proposalId);
			List<TotalCostOfProjectRequest> totalCostOfProjectRequests = new ArrayList<TotalCostOfProjectRequest>(
					totalCostOfProjectRequest.size());

			for (TotalCostOfProject detail : totalCostOfProjectRequest) {
				TotalCostOfProjectRequest totalCostofProjectRequest = new TotalCostOfProjectRequest();
				BeanUtils.copyProperties(detail, totalCostofProjectRequest);
				totalCostOfProjectRequests.add(totalCostofProjectRequest);
			}
			return totalCostOfProjectRequests;
		} catch (Exception e) {
			logger.error("Exception getting TotalCostOfProjects  :- {}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailList(Long applicationId, Long userId)
			throws LoansException {
		try {
			List<TotalCostOfProject> totalCostOfProjectRequest = totalCostOfProjectRepository
					.listCostOfProjectFromAppId(applicationId, userId);
			List<TotalCostOfProjectRequest> totalCostOfProjectRequests = new ArrayList<TotalCostOfProjectRequest>(
					totalCostOfProjectRequest.size());

			for (TotalCostOfProject detail : totalCostOfProjectRequest) {
				TotalCostOfProjectRequest totalCostofProjectRequest = new TotalCostOfProjectRequest();
				BeanUtils.copyProperties(detail, totalCostofProjectRequest);
				totalCostOfProjectRequests.add(totalCostofProjectRequest);
			}
			return totalCostOfProjectRequests;
		} catch (Exception e) {
			logger.error("Exception getting TotalCostOfProjects  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
	
	
	@Override
	public Double getCostOfProject(Long applicationId, Long userId) {
			List<TotalCostOfProject> totalCostOfProjectRequest = totalCostOfProjectRepository
					.listCostOfProjectFromAppId(applicationId, userId);
			Double totalCost=(double) 0;
			for(TotalCostOfProject totalCostOfProject:totalCostOfProjectRequest)
			{
				totalCost+=totalCostOfProject.getTotalCost();
			}
			
			Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
			totalCost=totalCost*Denomination.getById(denominationId).getDigit();
			return totalCost;
	}

}
