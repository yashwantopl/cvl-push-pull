package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;
@Transactional
@Service
public class  PersonalLoanParameterServiceImpl implements PersonalLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(PersonalLoanParameterServiceImpl.class);
	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
 	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Override
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		PersonalLoanParameter personalLoanParameter= null;

		personalLoanParameter = personalLoanParameterRepository.findOne(personalLoanParameterRequest.getId());
		if (personalLoanParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameter.setMinTenure(personalLoanParameterRequest.getMinTenure() * 12);
		
		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		personalLoanParameter.setIsParameterFilled(true);
		personalLoanParameter.setModifiedBy(personalLoanParameterRequest.getUserId());
		personalLoanParameter.setModifiedDate(new Date());
		personalLoanParameterRepository.save(personalLoanParameter);
		
		
		
		geographicalCountryRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		//country data save
		saveCountry(personalLoanParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		saveState(personalLoanParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(personalLoanParameterRequest.getId());
		saveCity(personalLoanParameterRequest);
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getPersonalLoanParameterRequest");
		PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
		PersonalLoanParameter personalLoanParameter = personalLoanParameterRepository.getByID(id);
		if(personalLoanParameter==null)
			return null;
		BeanUtils.copyProperties(personalLoanParameter, personalLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMaxTenure()))
			personalLoanParameterRequest.setMaxTenure(personalLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(personalLoanParameterRequest.getMinTenure()))
			personalLoanParameterRequest.setMinTenure(personalLoanParameterRequest.getMinTenure() / 12);
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(personalLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse =oneFormClient.getCountryByCountryListId(countryList);
			personalLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getPersonalLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(personalLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			personalLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getPersonalLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(personalLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			personalLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getPersonalLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		CommonDocumentUtils.endHook(logger, "getPersonalLoanParameterRequest");
		return personalLoanParameterRequest;
	}
	
	
private void saveCountry(PersonalLoanParameterRequest personalLoanParameterRequest) {
	CommonDocumentUtils.startHook(logger, "saveCountry");
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(PersonalLoanParameterRequest personalLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(PersonalLoanParameterRequest personalLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : personalLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(personalLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(personalLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

}
