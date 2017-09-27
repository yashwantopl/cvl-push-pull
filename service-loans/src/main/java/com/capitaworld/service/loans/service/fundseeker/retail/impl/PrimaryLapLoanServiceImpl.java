package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.FsNegativeFpList;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryLapLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryLapLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryLapLoanServiceImpl implements PrimaryLapLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryLapLoanServiceImpl.class.getName());

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;
	
	@Override
	public boolean saveOrUpdate(PrimaryLapLoanDetailRequest lapLoanDetailRequest, Long userId) throws Exception {
		// ID must not be null
		try {
			PrimaryLapLoanDetail primaryLapLoanDetail = primaryLapLoanDetailRepository.getByApplicationAndUserId(
					lapLoanDetailRequest.getId(), (CommonUtils.isObjectNullOrEmpty(lapLoanDetailRequest.getClientId())
							? userId : lapLoanDetailRequest.getClientId()));
			if (primaryLapLoanDetail == null) {
				throw new NullPointerException("PrimaryLapLoanDetail not exist in DB with Application Id=>"
						+ lapLoanDetailRequest.getId() + " and user Id ==>" + userId);
			}
			BeanUtils.copyProperties(lapLoanDetailRequest, primaryLapLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			primaryLapLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(lapLoanDetailRequest.getTenure()) ? null
					: (lapLoanDetailRequest.getTenure() * 12));
			primaryLapLoanDetail.setIsActive(true);
			primaryLapLoanDetail.setModifiedBy(userId);
			primaryLapLoanDetail.setModifiedDate(new Date());
			primaryLapLoanDetailRepository.save(primaryLapLoanDetail);
			//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryLapLoanDetail.getApplicationId().getId());
			saveNegativeList(primaryLapLoanDetail.getApplicationId().getId(), lapLoanDetailRequest.getNegativeList());
			return true;
		} catch (Exception e) {
			logger.error("Error while saving Primary Lap laon Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private void saveNegativeList(Long id, List<Long> negativeList) {
		// TODO Auto-generated method stub
		FsNegativeFpList fsNegativeFpList= null;
		for (Long fpId : negativeList) {
			fsNegativeFpList = new FsNegativeFpList();
			fsNegativeFpList.setApplicationId(id);
			fsNegativeFpList.setFpId(fpId);
			fsNegativeFpList.setCreatedBy(id);
			fsNegativeFpList.setModifiedBy(id);
			fsNegativeFpList.setCreatedDate(new Date());
			fsNegativeFpList.setModifiedDate(new Date());
			fsNegativeFpList.setIsActive(true);
			// create by and update
			fsNegativeFpListRepository.save(fsNegativeFpList);
		}
		
	}
	
	@Override
	public PrimaryLapLoanDetailRequest get(Long applicationId, Long userId) throws Exception {
		try {
			PrimaryLapLoanDetail loanDetail = primaryLapLoanDetailRepository.getByApplicationAndUserId(applicationId,
					userId);
			if (loanDetail == null) {
				throw new NullPointerException("PrimaryLapLoanDetail not exist in DB with applicationId=>"
						+ applicationId + " and UserId==>" + userId);
			}
			PrimaryLapLoanDetailRequest lapLoanDetailRequest = new PrimaryLapLoanDetailRequest();
			//get fp negative list
			lapLoanDetailRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(applicationId));
			BeanUtils.copyProperties(loanDetail, lapLoanDetailRequest);
			lapLoanDetailRequest.setTenure(
					CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationId);
			lapLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return lapLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary Lap laon Details Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
