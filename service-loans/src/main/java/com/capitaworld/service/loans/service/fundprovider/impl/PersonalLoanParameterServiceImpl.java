package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.model.OneFormResponse;
@Transactional
@Service
public class  PersonalLoanParameterServiceImpl implements PersonalLoanParameterService {
	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
 	
	@Autowired
	private Environment environment;
	
	@Override
	public boolean saveOrUpdate(PersonalLoanParameterRequest personalLoanParameterRequest) {
		// TODO Auto-generated method stub
		PersonalLoanParameter personalLoanParameter= null;

		personalLoanParameter = personalLoanParameterRepository.findOne(personalLoanParameterRequest.getId());
		if (personalLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
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
		return true;
	}

	@Override
	public PersonalLoanParameterRequest getPersonalLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
		PersonalLoanParameter personalLoanParameter = personalLoanParameterRepository.getByID(id);
		if(personalLoanParameter==null)
			return null;
		BeanUtils.copyProperties(personalLoanParameter, personalLoanParameterRequest);
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(personalLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		StateListByStateListIdClient stateListByStateListIdClient=new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = stateListByStateListIdClient.send(stateList);
			personalLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(personalLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		CityByCityListIdClient cityByCityListIdClient=new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = cityByCityListIdClient.send(cityList);
			personalLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return personalLoanParameterRequest;
	}
	
	
private void saveCountry(PersonalLoanParameterRequest personalLoanParameterRequest) {
		
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
	}
	
	private void saveState(PersonalLoanParameterRequest personalLoanParameterRequest) {
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
	}
	
	private void saveCity(PersonalLoanParameterRequest personalLoanParameterRequest) {
		
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
	}

}
