package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MfiApplicationServiceImpl implements MfiApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(MfiApplicationServiceImpl.class.getName());

	@Autowired
	private MfiApplicationDetailsRepository detailsRepository;

	@Autowired
	private LoanApplicationService applicationService;

	@Override
	public boolean saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
		if (aadharDetailsReq.getId() == null) {
			Long applicationId = applicationService.createMsmeLoan(aadharDetailsReq.getUserId(), true,
					aadharDetailsReq.getBusinessTypeId());
			if (applicationId != null) {
				mfiApplicationDetail = new MFIApplicantDetail();
				BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
				mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));
				mfiApplicationDetail.setStatus(CommonUtils.PENDING);
				mfiApplicationDetail.setIsActive(true);
				detailsRepository.save(mfiApplicationDetail);
			}
		} else {
			mfiApplicationDetail = detailsRepository.findOne(aadharDetailsReq.getId());
			BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(aadharDetailsReq.getApplicationId()));
			mfiApplicationDetail.setStatus(CommonUtils.PENDING);
			detailsRepository.save(mfiApplicationDetail);
		}
		return true;
	}

	@Override
	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId) {
		List<AadharDetailsReq> detailsReq = detailsRepository.findAadharDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public boolean saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq) {
		// TODO Auto-generated method stub
		System.out.println("personalDetailsReq.getId()=======>" + personalDetailsReq.getId());
		MFIApplicantDetail mfiApplicationDetail;
		if (null != personalDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(personalDetailsReq.getId());
			BeanUtils.copyProperties(personalDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsPersonalDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
		}
		return true;
	}

	@Override
	public PersonalDetailsReq getPersonalDetailsAppId(Long applicationId) {
		List<PersonalDetailsReq> detailsReq = detailsRepository.findPersonalDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public boolean saveOrUpdateProjectDetails(ProjectDetailsReq projectDetailsReq) {
		// TODO Auto-generated method stub
		MFIApplicantDetail mfiApplicationDetail;
		if (null != projectDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(projectDetailsReq.getId());
			BeanUtils.copyProperties(projectDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsProjectDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
		}
		return true;
	}
}
