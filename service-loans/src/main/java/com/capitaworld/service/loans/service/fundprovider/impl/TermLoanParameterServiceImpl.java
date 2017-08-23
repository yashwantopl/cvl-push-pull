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
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class TermLoanParameterServiceImpl implements TermLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(TermLoanParameterServiceImpl.class);
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	
	@Autowired
	private IndustrySectorRepository industrySectorRepository;
	
	@Autowired 
	private GeographicalCountryRepository geographicalCountryRepository;
	
	@Autowired
	private GeographicalStateRepository geographicalStateRepository;
	
	@Autowired
	private GeographicalCityRepository geographicalCityRepository;
 	
	@Autowired
	private OneFormClient oneFormClient; 

	@Override
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		
		TermLoanParameter termLoanParameter = null;

		termLoanParameter = termLoanParameterRepository.findOne(termLoanParameterRequest.getId());
		if (termLoanParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(termLoanParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(termLoanParameterRequest.getMinTenure().multiply(new BigDecimal("12")));
		
		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameterRepository.save(termLoanParameter);
		
		industrySectorRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// industry data save
		saveIndustry(termLoanParameterRequest);
		// Sector data save
		saveSector(termLoanParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		//country data save
		saveCountry(termLoanParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveState(termLoanParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveCity(termLoanParameterRequest);
		
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;

	}

	@Override
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id) {
		CommonDocumentUtils.startHook(logger, "getTermLoanParameterRequest");
		// TODO Auto-generated method stub
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		TermLoanParameter loanParameter = termLoanParameterRepository.getById(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(termLoanParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(termLoanParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		
		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				termLoanParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(termLoanParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
			termLoanParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}

		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(termLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			termLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			termLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			termLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		CommonDocumentUtils.endHook(logger, "getTermLoanParameterRequest");
		return termLoanParameterRequest;
	}
	
	private void saveIndustry(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveIndustry");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveIndustry");
	}

	private void saveSector(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveSector");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveSector");
	}
	
	private void saveCountry(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : termLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : termLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : termLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}
		
	
}
