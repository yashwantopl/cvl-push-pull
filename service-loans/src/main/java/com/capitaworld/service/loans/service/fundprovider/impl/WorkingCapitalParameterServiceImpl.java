package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.IndustryClient;
import com.capitaworld.service.oneform.client.SectorClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class WorkingCapitalParameterServiceImpl implements WorkingCapitalParameterService {
	@Autowired
	private WorkingCapitalParameterRepository workingCapitalParameterRepository;

	@Autowired	
	private IndustrySectorRepository industrySectorRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
 	
	@Autowired
	private Environment environment;

	@Override
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		WorkingCapitalParameter workingCapitalParameter = null;

		workingCapitalParameter = workingCapitalParameterRepository.findOne(workingCapitalParameterRequest.getId());
		if (workingCapitalParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMaxTenure()))
			workingCapitalParameterRequest.setMaxTenure(workingCapitalParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMinTenure()))
			workingCapitalParameterRequest.setMinTenure(workingCapitalParameterRequest.getMinTenure() * 12);
		
		BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameter,
				CommonUtils.IgnorableCopy.FP_PRODUCT);
		workingCapitalParameter.setModifiedBy(workingCapitalParameterRequest.getUserId());
		workingCapitalParameter.setIsActive(true);
		workingCapitalParameter.setModifiedDate(new Date());
		workingCapitalParameterRepository.save(workingCapitalParameter);
		industrySectorRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		// industry data save
		saveIndustry(workingCapitalParameterRequest);
		// Sector data save
		saveSector(workingCapitalParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		//country data save
		saveCountry(workingCapitalParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		saveState(workingCapitalParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		saveCity(workingCapitalParameterRequest);
		return true;
	}

	@Override
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id) {
		WorkingCapitalParameterRequest workingCapitalParameterRequest = new WorkingCapitalParameterRequest();
		WorkingCapitalParameter loanParameter = workingCapitalParameterRepository.getByID(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, workingCapitalParameterRequest);
		
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMaxTenure()))
			workingCapitalParameterRequest.setMaxTenure(workingCapitalParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMinTenure()))
			workingCapitalParameterRequest.setMinTenure(workingCapitalParameterRequest.getMinTenure() / 12);
		
		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(workingCapitalParameterRequest.getId());
		if (!industryList.isEmpty()) {
			IndustryClient client = new IndustryClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
			try {
				OneFormResponse formResponse = client.send(industryList);
				workingCapitalParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(workingCapitalParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		SectorClient sectorClient = new SectorClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = sectorClient.send(sectorList);
			workingCapitalParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(workingCapitalParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		CountryByCountryListIdClient countryByCountryListIdClient=new CountryByCountryListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = countryByCountryListIdClient.send(countryList);
			workingCapitalParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(workingCapitalParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		StateListByStateListIdClient stateListByStateListIdClient=new StateListByStateListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = stateListByStateListIdClient.send(stateList);
			workingCapitalParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(workingCapitalParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		CityByCityListIdClient cityByCityListIdClient=new CityByCityListIdClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = cityByCityListIdClient.send(cityList);
			workingCapitalParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("data is"+workingCapitalParameterRequest);
		return workingCapitalParameterRequest;
	}

	private void saveIndustry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		System.out.println(workingCapitalParameterRequest.getIndustrylist());
		for (DataRequest dataRequest : workingCapitalParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSector(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}
	
	private void saveCountry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
	}
	
	private void saveState(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
	}
	
	private void saveCity(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
	}
	

}
