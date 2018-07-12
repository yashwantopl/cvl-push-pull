package com.capitaworld.service.loans.service.fundprovider.impl;

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
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterTempRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class WorkingCapitalParameterServiceImpl implements WorkingCapitalParameterService {
	private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalParameterServiceImpl.class);
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
	private IndustrySectorTempRepository industrySectorTempRepository;
	
	@Autowired 
	private GeographicalCountryTempRepository geographicalCountryTempRepository;
	
	@Autowired
	private GeographicalStateTempRepository geographicalStateTempRepository;
	
	@Autowired
	private GeographicalCityTempRepository geographicalCityTempRepository;
 	
	@Autowired
	private OneFormClient oneFormClient;
	
	@Autowired
	private NegativeIndustryRepository negativeIndustryRepository;
	
	@Autowired
	private WorkingCapitalParameterTempRepository workingCapitalParameterTempRepository;
	
	@Autowired
	private NegativeIndustryTempRepository negativeIndustryTempRepository;

	@Override
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveOrUpdate");
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
		workingCapitalParameter.setIsParameterFilled(true);
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
		//negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(workingCapitalParameterRequest.getId());
		saveNegativeIndustry(workingCapitalParameterRequest);
		logger.info("end saveOrUpdate");
		return true;
	}

	@Override
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id) {
		logger.info("start getWorkingCapitalParameter");
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
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				workingCapitalParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(workingCapitalParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse =oneFormClient.getSectorById(sectorList);
			workingCapitalParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(workingCapitalParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			workingCapitalParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(workingCapitalParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			workingCapitalParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(workingCapitalParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			workingCapitalParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(workingCapitalParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				workingCapitalParameterRequest.setUnInterestedIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWCParameterRequest",e);
				e.printStackTrace();
			}
		}
		
		
		logger.info("end getWorkingCapitalParameter");
		return workingCapitalParameterRequest;
	}

	private void saveIndustry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveIndustry");
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
		logger.info("end saveIndustry");
	}

	private void saveSector(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveSector");
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
		logger.info("end saveSector");
	}
	
	private void saveCountry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("save saveCountry");
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
		logger.info("end saveCountry");
	}
	
	private void saveState(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveState");
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
		logger.info("end saveState");
	}
	
	private void saveCity(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveCity");
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
		logger.info("end saveCity");
	}

	@Override
	public List<WorkingCapitalParameterRequest> getWorkingCapitalParameterListByUserId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	

	private void saveNegativeIndustry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustry");
		NegativeIndustry negativeIndustry= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getUnInterestedIndustrylist()) {
			negativeIndustry = new NegativeIndustry();
			negativeIndustry.setFpProductMasterId(workingCapitalParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustry");
		
	}
	
	public Boolean saveMasterFromTempWc(Long mappingId) throws Exception {
		try {
			WorkingCapitalParameterRequest workingCapitalParameterRequest = 	getWorkingCapitalParameterTemp(mappingId);
        return saveOrUpdate(workingCapitalParameterRequest);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public WorkingCapitalParameterRequest getWorkingCapitalParameterTemp(Long id) {
		logger.info("start getWorkingCapitalParameterTemp");
		WorkingCapitalParameterRequest workingCapitalParameterRequest = new WorkingCapitalParameterRequest();
		WorkingCapitalParameterTemp loanParameter = workingCapitalParameterTempRepository.getworkingCapitalParameterTempByFpProductId(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, workingCapitalParameterRequest);
		
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMaxTenure()))
			workingCapitalParameterRequest.setMaxTenure(workingCapitalParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMinTenure()))
			workingCapitalParameterRequest.setMinTenure(workingCapitalParameterRequest.getMinTenure() / 12);
		
		List<Long> industryList = industrySectorTempRepository
				.getIndustryByProductId(workingCapitalParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				workingCapitalParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorTempRepository
				.getSectorByProductId(workingCapitalParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse =oneFormClient.getSectorById(sectorList);
			workingCapitalParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> countryList=geographicalCountryTempRepository.getCountryByFpProductId(workingCapitalParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			workingCapitalParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateTempRepository.getStateByFpProductId(workingCapitalParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			workingCapitalParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityTempRepository.getCityByFpProductId(workingCapitalParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			workingCapitalParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e.toString());
			e.printStackTrace();
		}
		}
		
		List<Long> negativeIndustryList = negativeIndustryTempRepository
				.getIndustryByFpProductMasterId(workingCapitalParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				workingCapitalParameterRequest.setUnInterestedIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWCParameterRequest",e);
				e.printStackTrace();
			}
		}
		
		
		logger.info("end getWorkingCapitalParameterTemp");
		return workingCapitalParameterRequest;
	}


}
