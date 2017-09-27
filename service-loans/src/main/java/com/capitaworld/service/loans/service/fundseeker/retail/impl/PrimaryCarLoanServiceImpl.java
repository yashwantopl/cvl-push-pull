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
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.FsNegativeFpListRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryCarLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PrimaryCarLoanService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class PrimaryCarLoanServiceImpl implements PrimaryCarLoanService {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryCarLoanServiceImpl.class.getName());
	
	@Autowired
	private PrimaryCarLoanDetailRepository primaryCarLoanDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private FsNegativeFpListRepository fsNegativeFpListRepository;
	
	@Override
	public boolean saveOrUpdate(PrimaryCarLoanDetailRequest carLoanDetailRequest, Long userId) throws Exception {
		// ID must not be null
		try {
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(carLoanDetailRequest.getClientId()) ? userId : carLoanDetailRequest.getClientId());
			PrimaryCarLoanDetail primaryCarLoanDetail = primaryCarLoanDetailRepository
					.getByApplicationAndUserId(carLoanDetailRequest.getId(),finalUserId);
			if (primaryCarLoanDetail == null) {
				throw new NullPointerException("PrimaryCarLoanDetail not exist in DB with ID=>"
						+ carLoanDetailRequest.getId() + " and User Id ==>" + userId);
			}
			BeanUtils.copyProperties(carLoanDetailRequest, primaryCarLoanDetail, CommonUtils.IgnorableCopy.CORPORATE);
			primaryCarLoanDetail.setTenure(CommonUtils.isObjectNullOrEmpty(carLoanDetailRequest.getTenure()) ? null : (carLoanDetailRequest.getTenure() * 12));
			primaryCarLoanDetail.setModifiedBy(userId);
			primaryCarLoanDetail.setModifiedDate(new Date());
			primaryCarLoanDetailRepository.save(primaryCarLoanDetail);
			
			//Updating Primary Flag
			loanApplicationRepository.setPrimaryFilledCount(primaryCarLoanDetail.getId(), finalUserId, carLoanDetailRequest.getPrimaryFilledCount());
			//save negative list
			fsNegativeFpListRepository.inActiveMappingByApplicationId(primaryCarLoanDetail.getApplicationId().getId());
			saveNegativeList(primaryCarLoanDetail.getApplicationId().getId(), carLoanDetailRequest.getNegativeList());
			return true;
		} catch (Exception e) {
			logger.error("Error while saving PrimaryCarLoan Details");
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
	public PrimaryCarLoanDetailRequest get(Long id, Long userId) throws Exception {
		try {
			PrimaryCarLoanDetail loanDetail = primaryCarLoanDetailRepository.getByApplicationAndUserId(id, userId);
			if (loanDetail == null) {
				throw new NullPointerException(
						"PrimaryCarLoanDetail not exist in DB with ID=>" + id + " and User Id ==>" + userId);
			}
			PrimaryCarLoanDetailRequest carLoanDetailRequest = new PrimaryCarLoanDetailRequest();
			//get fp negative list
			carLoanDetailRequest.setNegativeList(fsNegativeFpListRepository.getListByApplicationId(id));
			BeanUtils.copyProperties(loanDetail, carLoanDetailRequest);
			carLoanDetailRequest.setTenure(CommonUtils.isObjectNullOrEmpty(loanDetail.getTenure()) ? null : (loanDetail.getTenure() / 12));
			Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, id);
			carLoanDetailRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
			return carLoanDetailRequest;
		} catch (Exception e) {
			logger.error("Error while getting Primary CarLoan Details");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}
}
