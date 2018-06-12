package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
import com.capitaworld.service.loans.model.LoanDisbursementRequest;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.service.sanction.LoanDisbursementService;
/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanDisbursementServiceImpl implements LoanDisbursementService{
		
	private static final Logger logger = LoggerFactory.getLogger(LoanDisbursementServiceImpl.class);
	
	@Autowired 
	private LoanDisbursementRepository loanDisbursementRepository;
	@Override
	public Boolean saveLoanDisbursementDetail(LoanDisbursementRequest loanDisbursementRequest) {
		logger.info("Enter in saveLoanDisbursementDetail() ----------------------->  LoanDisbursementRequest "+ loanDisbursementRequest );
		LoanDisbursementDomain loanDisbursementDomain =new LoanDisbursementDomain();
		BeanUtils.copyProperties(loanDisbursementRequest, loanDisbursementDomain);
		loanDisbursementDomain.setIsActive(true);
		loanDisbursementDomain.setCreatedDate(new Date());
		loanDisbursementDomain.setModifiedDate(new Date());
		logger.info("Exit saveLoanDisbursementDetail() -----------------------> ");
		return loanDisbursementRepository.save(loanDisbursementDomain)!=null;
	}

}
