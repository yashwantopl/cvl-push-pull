package com.capitaworld.service.loans.service.fundprovider.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;
import com.capitaworld.service.loans.domain.fundprovider.UnsecureLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.UnsecureLoanParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.UnsecuredLoanParameterRequest;
import com.capitaworld.service.loans.model.corporate.UnsecuredLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.UnsecuredLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.UnsecuredLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class UnsecuredLoanParameterServiceImpl implements UnsecuredLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(UnsecuredLoanParameterServiceImpl.class);
	@Autowired
	private UnsecuredLoanParameterRepository unsecuredLoanParameterRepository;
	
	@Autowired
	private IndustrySectorRepository industrySectorRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
	
	@Autowired
	private NegativeIndustryRepository negativeIndustryRepository;
 	
	@Autowired
	private OneFormClient oneFormClient; 

	@Override
	public boolean saveOrUpdate(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		
		UnsecureLoanParameter unsecureLoanParameter= null;

		unsecureLoanParameter = unsecuredLoanParameterRepository.findOne(unsecuredLoanParameterRequest.getId());
		if (
				unsecureLoanParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(unsecuredLoanParameterRequest.getMaxTenure()))
			unsecuredLoanParameterRequest.setMaxTenure(unsecuredLoanParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(unsecuredLoanParameterRequest.getMinTenure()))
			unsecuredLoanParameterRequest.setMinTenure(unsecuredLoanParameterRequest.getMinTenure().multiply(new BigDecimal("12")));
		
		BeanUtils.copyProperties(unsecuredLoanParameterRequest, unsecureLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		unsecureLoanParameter.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
		unsecureLoanParameter.setModifiedDate(new Date());
		unsecureLoanParameter.setIsActive(true);
		unsecureLoanParameter.setIsParameterFilled(true);
		unsecuredLoanParameterRepository.save(unsecureLoanParameter);
		
		industrySectorRepository.inActiveMappingByFpProductId(unsecuredLoanParameterRequest.getId());
		// industry data save
		saveIndustry(unsecuredLoanParameterRequest);
		// Sector data save
		saveSector(unsecuredLoanParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(unsecuredLoanParameterRequest.getId());
		//country data save
		saveCountry(unsecuredLoanParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(unsecuredLoanParameterRequest.getId());
		saveState(unsecuredLoanParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(unsecuredLoanParameterRequest.getId());
		saveCity(unsecuredLoanParameterRequest);
		//negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(unsecuredLoanParameterRequest.getId());
		saveNegativeIndustry(unsecuredLoanParameterRequest);
		
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;

	}

	

	@Override
	public UnsecuredLoanParameterRequest getUnsecuredLoanParameterRequest(Long id) {
		CommonDocumentUtils.startHook(logger, "getTermLoanParameterRequest");
		// TODO Auto-generated method stub
		UnsecuredLoanParameterRequest unsecuredLoanParameterRequest = new UnsecuredLoanParameterRequest();
		UnsecureLoanParameter loanParameter = unsecuredLoanParameterRepository.getById(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, unsecuredLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(unsecuredLoanParameterRequest.getMaxTenure()))
			unsecuredLoanParameterRequest.setMaxTenure(unsecuredLoanParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(unsecuredLoanParameterRequest.getMinTenure()))
			unsecuredLoanParameterRequest.setMinTenure(unsecuredLoanParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		
		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(unsecuredLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				unsecuredLoanParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(unsecuredLoanParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
			unsecuredLoanParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}

		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(unsecuredLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			unsecuredLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(unsecuredLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			unsecuredLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(unsecuredLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			unsecuredLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(unsecuredLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				unsecuredLoanParameterRequest.setNegativeIndustryList((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		CommonDocumentUtils.endHook(logger, "getTermLoanParameterRequest");
		return unsecuredLoanParameterRequest;
	}
	
	private void saveIndustry(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveIndustry");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(unsecuredLoanParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveIndustry");
	}

	private void saveSector(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveSector");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(unsecuredLoanParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveSector");
	}
	
	private void saveCountry(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(unsecuredLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(unsecuredLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(unsecuredLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

	private void saveNegativeIndustry(UnsecuredLoanParameterRequest unsecuredLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustry");
		NegativeIndustry negativeIndustry= null;
		for (DataRequest dataRequest : unsecuredLoanParameterRequest.getNegativeIndustryList()) {
			negativeIndustry = new NegativeIndustry();
			negativeIndustry.setFpProductMasterId(unsecuredLoanParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(unsecuredLoanParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(unsecuredLoanParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustry");
		
	}
	
}
