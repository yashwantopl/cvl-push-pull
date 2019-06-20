/**
 * 
 */
package com.capitaworld.service.loans.service.sidbi.impl;

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

import com.capitaworld.service.loans.domain.sidbi.PersonalCorporateGuarantee;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.sidbi.PersonalCorporateGuaranteeRequest;
import com.capitaworld.service.loans.repository.sidbi.PersonalCorporateGuaranteeRepository;
import com.capitaworld.service.loans.service.sidbi.PersonalCorporateGuaranteeService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author vijay.chauhan
 *
 */

@Service
@Transactional
public class PersonalCorporateGuaranteeServiceImpl implements PersonalCorporateGuaranteeService {

private static final Logger logger = LoggerFactory.getLogger(PrimaryCollateralSecurityServiceImpl.class.getName());
	
	@Autowired
	private PersonalCorporateGuaranteeRepository personalCorporateGuaranteeRepository;
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PersonalCorporateGuaranteeRequest personalCorporateGuaranteeRequest = MultipleJSONObjectHelper.getObjectFromMap(obj, PersonalCorporateGuaranteeRequest.class);
				PersonalCorporateGuarantee personalCorporateGuarantee = null;
				if (personalCorporateGuaranteeRequest.getId() != null) {
					personalCorporateGuarantee = personalCorporateGuaranteeRepository.findOne(personalCorporateGuaranteeRequest.getId());
				} else {
					personalCorporateGuarantee = new PersonalCorporateGuarantee();
					personalCorporateGuarantee.setCreatedBy(frameRequest.getUserId());
					personalCorporateGuarantee.setCreatedDate(new Date());
				}
				
				BeanUtils.copyProperties(personalCorporateGuaranteeRequest, personalCorporateGuarantee);				
				personalCorporateGuarantee.setApplicationId(frameRequest.getApplicationId());
				personalCorporateGuarantee.setModifiedBy(frameRequest.getUserId());
				personalCorporateGuarantee.setModifiedDate(new Date());
				personalCorporateGuaranteeRepository.save(personalCorporateGuarantee);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save personal Corporate Guarantee Request :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<PersonalCorporateGuaranteeRequest> getPersonalCorporateGuaranteeListAppId(Long applicationId)
			throws LoansException {
		List<PersonalCorporateGuaranteeRequest> personalCorporateGuaranteeRequestList = null;
		try {
			List<PersonalCorporateGuarantee> personalCorporateGuaranteeList = personalCorporateGuaranteeRepository.getPersonalCorporateGuaranteeListAppId(applicationId);
			personalCorporateGuaranteeRequestList = new ArrayList<>(personalCorporateGuaranteeList.size());

			for (PersonalCorporateGuarantee detail : personalCorporateGuaranteeList) {
				PersonalCorporateGuaranteeRequest personalCorporateGuaranteeRequest = new PersonalCorporateGuaranteeRequest();
				BeanUtils.copyProperties(detail, personalCorporateGuaranteeRequest);
				
				personalCorporateGuaranteeRequestList.add(personalCorporateGuaranteeRequest);
			}
			return personalCorporateGuaranteeRequestList;
		}

		catch (Exception e) { 
			logger.error("Exception in get personalCorporateGuaranteeRequestList :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
		
	}

}
