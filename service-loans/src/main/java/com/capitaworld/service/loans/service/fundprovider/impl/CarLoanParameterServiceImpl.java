package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.CarLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.model.OneFormResponse;
@Transactional
@Service
public class CarLoanParameterServiceImpl implements CarLoanParameterService {
	@Autowired
	private CarLoanParameterRepository carLoanParameterRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
	
	@Autowired
	private Environment environment;
	
	
	@Override
	public boolean saveOrUpdate(CarLoanParameterRequest carLoanParameterRequest) {
		// TODO Auto-generated method stub
		CarLoanParameter carLoanParameter= null;

		carLoanParameter = carLoanParameterRepository.findOne(carLoanParameterRequest.getId());
		if (carLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(carLoanParameterRequest, carLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		carLoanParameter.setModifiedBy(carLoanParameterRequest.getUserId());
		carLoanParameter.setModifiedDate(new Date());
		carLoanParameterRepository.save(carLoanParameter);
		
		
		geographicalCountryRepository.inActiveMappingByFpProductId(carLoanParameterRequest.getId());
		//country data save
		saveCountry(carLoanParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(carLoanParameterRequest.getId());
		saveState(carLoanParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(carLoanParameterRequest.getId());
		saveCity(carLoanParameterRequest);
		return true;
	}

	@Override
	public CarLoanParameterRequest getCarLoanParameterRequest(Long id) {
		CarLoanParameterRequest carLoanParameterRequest= new CarLoanParameterRequest();
		CarLoanParameter carLoanParameter = carLoanParameterRepository.getByID(id);
		if(carLoanParameter==null)
			return null;
		BeanUtils.copyProperties(carLoanParameter, carLoanParameterRequest);
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(carLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		CountryByCountryListIdClient countryByCountryListIdClient=new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = countryByCountryListIdClient.send(countryList);
			carLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(carLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		StateListByStateListIdClient stateListByStateListIdClient=new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = stateListByStateListIdClient.send(stateList);
			carLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(carLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		CityByCityListIdClient cityByCityListIdClient=new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = cityByCityListIdClient.send(cityList);
			carLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return carLoanParameterRequest;
	}
	
private void saveCountry(CarLoanParameterRequest carLoanParameterRequest) {
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : carLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(carLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(carLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(carLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
	}
	
	private void saveState(CarLoanParameterRequest carLoanParameterRequest) {
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : carLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(carLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(carLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(carLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
	}
	
	private void saveCity(CarLoanParameterRequest carLoanParameterRequest) {
		
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : carLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(carLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(carLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(carLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
	}
	
	
	

}
