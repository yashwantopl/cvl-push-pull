package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.TotalCostOfProject;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TotalCostOfProjectRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.Denomination;

@Service
@Transactional
public class TotalCostOfProjectServiceImpl implements TotalCostOfProjectService {
	private static final Logger logger = LoggerFactory.getLogger(TotalCostOfProjectServiceImpl.class.getName());
	@Autowired
	private TotalCostOfProjectRepository totalCostOfProjectRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		// TODO Auto-generated method stub
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
				totalCostOfProject.setModifiedBy(frameRequest.getUserId());
				totalCostOfProject.setModifiedDate(new Date());
				totalCostOfProjectRepository.save(totalCostOfProject);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception in save totalCostOfProject :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailList(Long applicationId, Long userId)
			throws Exception {
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
			logger.info("Exception getting TotalCostOfProjects  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
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
