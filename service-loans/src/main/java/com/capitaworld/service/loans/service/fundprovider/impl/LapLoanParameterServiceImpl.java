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
import com.capitaworld.service.loans.domain.fundprovider.LapParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.LapParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.LapLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Transactional
@Service
public class LapLoanParameterServiceImpl implements LapLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(LapLoanParameterServiceImpl.class);
	@Autowired
	private LapParameterRepository lapParameterRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
 	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Override
	public boolean saveOrUpdate(LapParameterRequest lapParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		LapParameter lapParameter= null;

		lapParameter = lapParameterRepository.findOne(lapParameterRequest.getId());
		if (lapParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(lapParameterRequest.getMaxTenure()))
			lapParameterRequest.setMaxTenure(lapParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(lapParameterRequest.getMinTenure()))
			lapParameterRequest.setMinTenure(lapParameterRequest.getMinTenure() * 12);
		
		BeanUtils.copyProperties(lapParameterRequest, lapParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		lapParameter.setModifiedBy(lapParameterRequest.getUserId());
		lapParameter.setModifiedDate(new Date());
		lapParameter.setIsParameterFilled(true);
		lapParameterRepository.save(lapParameter);
		
		geographicalCountryRepository.inActiveMappingByFpProductId(lapParameterRequest.getId());
		//country data save
		saveCountry(lapParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(lapParameterRequest.getId());
		saveState(lapParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(lapParameterRequest.getId());
		saveCity(lapParameterRequest);
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;
	}

	@Override
	public LapParameterRequest getLapParameterRequest(Long id) {
		CommonDocumentUtils.startHook(logger, "getLapParameterRequest");
		// TODO Auto-generated method stub
		LapParameterRequest lapParameterRequest= new LapParameterRequest();
		LapParameter lapParameter = lapParameterRepository.getByID(id);
		if(lapParameter==null)
			return null;
		BeanUtils.copyProperties(lapParameter, lapParameterRequest);
		
		if (!CommonUtils.isObjectListNull(lapParameterRequest.getMaxTenure()))
			lapParameterRequest.setMaxTenure(lapParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(lapParameterRequest.getMinTenure()))
			lapParameterRequest.setMinTenure(lapParameterRequest.getMinTenure() / 12);
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(lapParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			lapParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getLapParameterRequest",e);
			e.printStackTrace();
		}
		}
		
	
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(lapParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			lapParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getLapParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(lapParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			lapParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getLapParameterRequest",e);
			e.printStackTrace();
		}
		}
		CommonDocumentUtils.endHook(logger, "getLapParameterRequest");
		return lapParameterRequest;
	}
	
private void saveCountry(LapParameterRequest lapParameterRequest) {
	CommonDocumentUtils.startHook(logger, "saveCountry");
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : lapParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(lapParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(lapParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(lapParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(LapParameterRequest lapParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : lapParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(lapParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(lapParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(lapParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(LapParameterRequest lapParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : lapParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(lapParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(lapParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(lapParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}
	

}
