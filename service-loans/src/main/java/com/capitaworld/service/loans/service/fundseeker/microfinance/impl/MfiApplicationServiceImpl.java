package com.capitaworld.service.loans.service.fundseeker.microfinance.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiBankDetails;
import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.*;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiApplicationDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiBankDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.repository.fundseeker.Mfi.MfiIncomeDetailsRepository;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class MfiApplicationServiceImpl implements MfiApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(MfiApplicationServiceImpl.class.getName());

	@Autowired
	private MfiApplicationDetailsRepository detailsRepository;

	@Autowired
	private LoanApplicationService applicationService;

	@Autowired
	private MfiBankDetailsRepository bankDetailsRepository;
	
	@Autowired
	private MfiIncomeDetailsRepository MfiIncomeDetailsRepository;

	@Override
	public AadharDetailsReq saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq) {
		MFIApplicantDetail mfiApplicationDetail;
		if (aadharDetailsReq.getId() == null) {
			Long applicationId = applicationService.createMfiLoan(aadharDetailsReq.getUserId(), true,
					aadharDetailsReq.getBusinessTypeId(),aadharDetailsReq.getOrgId());
			if (applicationId != null) {
				mfiApplicationDetail = new MFIApplicantDetail();
				BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
				mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(applicationId));
				mfiApplicationDetail.setStatus(CommonUtils.PENDING);
				mfiApplicationDetail.setIsActive(true);
				mfiApplicationDetail.setCreatedBy(aadharDetailsReq.getUserId());
				mfiApplicationDetail.setCreatedDate(new Date());
				detailsRepository.save(mfiApplicationDetail);
                aadharDetailsReq.setId(mfiApplicationDetail.getId());
                aadharDetailsReq.setApplicationId(mfiApplicationDetail.getApplicationId().getId());
			}
		} else {
			mfiApplicationDetail = detailsRepository.findOne(aadharDetailsReq.getId());
			BeanUtils.copyProperties(aadharDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setApplicationId(new LoanApplicationMaster(aadharDetailsReq.getApplicationId()));
			mfiApplicationDetail.setStatus(CommonUtils.PENDING);
			mfiApplicationDetail.setModifiedBy(aadharDetailsReq.getUserId());
			mfiApplicationDetail.setModifiedDate(new Date());
			detailsRepository.save(mfiApplicationDetail);
		}
		AadharDetailsReq detailsReq = new AadharDetailsReq();
		detailsReq.setId(aadharDetailsReq.getId());
		detailsReq.setApplicationId(aadharDetailsReq.getApplicationId());
		return detailsReq;
	}

	@Override
	public AadharDetailsReq getAadharDetailsByAppId(Long applicationId) {
		List<AadharDetailsReq> detailsReq = detailsRepository.findAadharDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public boolean saveOrUpdatePersonalDetails(PersonalDetailsReq personalDetailsReq) {
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
		MFIApplicantDetail mfiApplicationDetail;
		if (null != projectDetailsReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(projectDetailsReq.getId());
			BeanUtils.copyProperties(projectDetailsReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsProjectDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
		}
		return true;
	}

	@Override
	public List<MfiReqResponse> getMfiApplicantDetails(Long applicationId) {
		logger.info("ENTER HERE MFI DETAILS===== BY APPLICATION ==={}====={}===>"+applicationId);
		List<MFIApplicantDetail> appDetailList = detailsRepository.findByApplicationIdAndIsActive(applicationId);
		List<MfiReqResponse> appResponseList = new ArrayList<>(appDetailList.size());
		MfiReqResponse appIncomeReq = null;
		
		for(MFIApplicantDetail appIncomeDetail : appDetailList) {
				appIncomeReq = new MfiReqResponse();
				BeanUtils.copyProperties(appIncomeDetail, appIncomeReq);
				appResponseList.add(appIncomeReq);
		}
		return appResponseList;
		
/*		logger.info("ENTER HERE MFI DETAILS===== BY APPLICATION ==={}====={}===>"+applicationId);
		MFIApplicantDetail detailsResp = detailsRepository.findByApplicationIdAndIsActive(applicationId,true);
		logger.info("ENTER HERE MFI DETAILS===== BY APPLICATION ==={}====={}===>"+detailsResp);
		MfiReqResponse mfiResp = new MfiReqResponse();
		BeanUtils.copyProperties(detailsResp, mfiResp);*/
	//	return  null;
	}


	@Override
	public boolean saveOrUpdateBankDetails(MfiBankDetailsReq bankDetailsReq) {
		MfiBankDetails mfiBankDetails = new MfiBankDetails();
		BeanUtils.copyProperties(bankDetailsReq, mfiBankDetails);
		bankDetailsRepository.save(mfiBankDetails);
		detailsRepository.updateBankFilledFlag(bankDetailsReq.getApplicationId());
		return true;
	}

	@Override
	public MfiBankDetailsReq fetchBankDetail(Long applicationId) {
		MfiBankDetailsReq mfiBankDetailsReq = new MfiBankDetailsReq();
		MfiBankDetails byApplicationId = bankDetailsRepository.findByApplicationId(applicationId);
		BeanUtils.copyProperties(byApplicationId,mfiBankDetailsReq);
		return mfiBankDetailsReq;
	}

	@Override
	public List<MfiApplicantDetailsReq> getAllApplicantDetails(Long applicationId){
		List<MfiApplicantDetailsReq> mfiApplicantDetailsReqs = new ArrayList<>();
		List<MFIApplicantDetail> all = detailsRepository.findByApplicationIdAndIsActive(applicationId);
		for (MFIApplicantDetail applicantDetail:all) {
			MfiApplicantDetailsReq detailsReq = new MfiApplicantDetailsReq();
			BeanUtils.copyProperties(applicantDetail,detailsReq);
			mfiApplicantDetailsReqs.add(detailsReq);
		}
		return mfiApplicantDetailsReqs;

	}
	
	@Override
	public ProjectDetailsReq getProjectDetailsAppId(Long applicationId) {
		List<ProjectDetailsReq> detailsReq = detailsRepository.findProjectDetailsByAppId(applicationId);
		return !CommonUtils.isListNullOrEmpty(detailsReq) ? detailsReq.get(0) : null;
	}

	@Override
	public boolean saveOrUpdateIncomeExpenditureDetails(MfiIncomeAndExpenditureReq mfiIncomeAndExpenditureReq) {
		MFIApplicantDetail mfiApplicationDetail = null;
		MfiIncomeDetails mfiIncomeDetails = null;
		if (null != mfiIncomeAndExpenditureReq.getId()) {
			mfiApplicationDetail = detailsRepository.findOne(mfiIncomeAndExpenditureReq.getId());
			BeanUtils.copyProperties(mfiIncomeAndExpenditureReq, mfiApplicationDetail);
			mfiApplicationDetail.setIsIncomeDetailsFilled(true);
			detailsRepository.save(mfiApplicationDetail);
		}
		List<MfiIncomeDetailsReq> mfiIncomeDetailsReqs = mfiIncomeAndExpenditureReq.getIncomeDetailsReqList();
		if (!CommonUtils.isListNullOrEmpty(mfiIncomeDetailsReqs)) {
			for (MfiIncomeDetailsReq mfiIncomeDetailsReq : mfiIncomeDetailsReqs) {
				mfiIncomeDetails = new MfiIncomeDetails();
				BeanUtils.copyProperties(mfiIncomeDetailsReq, mfiIncomeDetails);
				MfiIncomeDetailsRepository.save(mfiIncomeDetails);
			}
		}

		
		return true;
	}

	@Override
	public MfiIncomeAndExpenditureReq getIncomeExpenditureDetailsAppId(Long applicationId) {
		List<MfiIncomeAndExpenditureReq> detailsReq = detailsRepository.findIncomeAndExpenditureDetailsByAppId(applicationId);
		if(!CommonUtils.isListNullOrEmpty(detailsReq)) {
			MfiIncomeAndExpenditureReq expenditureReq = detailsReq.get(0);
			List<MfiIncomeDetailsReq> incomeDetails = MfiIncomeDetailsRepository.findIncomeDetailsByAppId(applicationId);
			expenditureReq.setIncomeDetailsReqList(!CommonUtils.isListNullOrEmpty(incomeDetails) ? incomeDetails : Collections.emptyList());
			return expenditureReq;
		}		
		return null;
	}
}
