package com.capitaworld.service.loans.service.sanctionimpl;

import java.util.Date;

import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;
import com.capitaworld.service.loans.model.LoanSanctionRequest;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.sanction.LoanSanctionService;
import com.capitaworld.service.loans.utils.CommonUtils;
/**
 * @author Ankit
 *
 */

@Service
@Transactional
public class LoanSanctionServiceImpl implements LoanSanctionService {
	private static final Logger logger = LoggerFactory.getLogger(LoanSanctionServiceImpl.class);

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Override
	public Boolean saveLoanSanctionDetail(LoanSanctionRequest loanSanctionRequest) {

		logger.info("Enter in saveLoanSanctionDetail() ----------------------->  LoanSanctionRequest "+ loanSanctionRequest);
		LoanSanctionDomain loanSanctionDomain =loanSanctionRepository.findByAppliationId(loanSanctionRequest.getApplicationId());
		if(CommonUtils.isObjectNullOrEmpty(loanSanctionDomain) ) {
			loanSanctionDomain = new LoanSanctionDomain();
		}
		BeanUtils.copyProperties(loanSanctionRequest, loanSanctionDomain);
		loanSanctionDomain.setIsActive(true);
		loanSanctionDomain.setCreatedDate(new Date());
		loanSanctionDomain.setModifiedDate(new Date());
		logger.info("Exit saveLoanSanctionDetail() -----------------------> LoanSanctionDomain "+ loanSanctionDomain);
		return loanSanctionRepository.save(loanSanctionDomain) != null;

	}

}
