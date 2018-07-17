package com.capitaworld.service.loans.service.fundprovider.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.IndustrySectorDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;
import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustryTemp;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterTempRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
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
	private NegativeIndustryRepository negativeIndustryRepository;
	
	@Autowired
	private TermLoanParameterTempRepository termLoanParameterTempRepository;  
 	
	@Autowired
	private OneFormClient oneFormClient; 
	
	@Autowired	
	private IndustrySectorTempRepository industrySectorTempRepository;
	
	@Autowired 
	private GeographicalCountryTempRepository geographicalCountryTempRepository;
	
	@Autowired
	private GeographicalStateTempRepository geographicalStateTempRepository;
	
	@Autowired
	private GeographicalCityTempRepository geographicalCityTempRepository;
	
	@Autowired
	private NegativeIndustryTempRepository negativeIndustryTempRepository;
	
    @Autowired
    private WorkflowClient workflowClient;

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
		termLoanParameter.setIsParameterFilled(true);
		termLoanParameter.setJobId(termLoanParameterRequest.getJobId());
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
		//negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(termLoanParameterRequest.getId());
		saveNegativeIndustry(termLoanParameterRequest);
		
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
		
		
		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				termLoanParameterRequest.setNegativeIndustryList((List<DataRequest>)formResponse.getListData());
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

	private void saveNegativeIndustry(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustry");
		NegativeIndustry negativeIndustry= null;
		for (DataRequest dataRequest : termLoanParameterRequest.getNegativeIndustryList()) {
			negativeIndustry = new NegativeIndustry();
			negativeIndustry.setFpProductMasterId(termLoanParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(termLoanParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(termLoanParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustry");
		
	}



	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService#saveMasterFromTempTl(java.lang.Long)
	 */
	@Override
	public Boolean saveMasterFromTempTl(Long mappingId) throws Exception {
		try {
			TermLoanParameterRequest  temp =  getTermLoanParameterRequestTemp(mappingId);
        return saveOrUpdate(temp);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public TermLoanParameterRequest getTermLoanParameterRequestTemp(Long id) {
		CommonDocumentUtils.startHook(logger, "getTermLoanParameterRequest");
		// TODO Auto-generated method stub
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		TermLoanParameterTemp loanParameter =  termLoanParameterTempRepository.getTermLoanParameterTempByFpProductId(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(termLoanParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(termLoanParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		
		List<Long> industryList = industrySectorTempRepository
				.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				termLoanParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequestTemp",e);
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorTempRepository
				.getSectorByProductId(termLoanParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
			termLoanParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequestTemp",e);
			e.printStackTrace();
		}
		}

		List<Long> countryList=geographicalCountryTempRepository.getCountryByFpProductId(termLoanParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			termLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateTempRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			termLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityTempRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			termLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> negativeIndustryList = negativeIndustryTempRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				termLoanParameterRequest.setNegativeIndustryList((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequestTemp",e);
				e.printStackTrace();
			}
		}
		CommonDocumentUtils.endHook(logger, "getTermLoanParameterRequestTemp");
		
		termLoanParameterRequest.setJobId(loanParameter.getJobId());
		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
        loanParameter.setIsDeleted(false);
        loanParameter.setIsEdit(false);
        loanParameter.setIsCopied(true);
        loanParameter.setIsApproved(true);
        loanParameter.setApprovalDate(new Date());
        termLoanParameterTempRepository.save(loanParameter);
		return termLoanParameterRequest;
	}
	
	@Override
	public Boolean saveOrUpdateTemp(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		
		TermLoanParameterTemp termLoanParameter = null;

		termLoanParameter = termLoanParameterTempRepository.findOne(termLoanParameterRequest.getId());
		if (termLoanParameter == null) {
			termLoanParameter = new TermLoanParameterTemp();
		}
		
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(termLoanParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(termLoanParameterRequest.getMinTenure().multiply(new BigDecimal("12")));
		
		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT_TEMP);
		termLoanParameter.setFpProductMappingId(termLoanParameterRequest.getId());
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);
		
		termLoanParameter.setStatusId(CommonUtils.Status.OPEN);
        termLoanParameter.setIsApproved(false);
        termLoanParameter.setIsDeleted(false);
        termLoanParameter.setIsCopied(false);
        termLoanParameter.setApprovalDate(null);
		if (CommonUtils.isObjectNullOrEmpty(termLoanParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
					WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
					termLoanParameterRequest.getUserId());
			Long jobId = null;
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
			}

			termLoanParameter.setJobId(jobId);
		}
		
		termLoanParameterTempRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(termLoanParameter.getId());
		industrySectorTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		// industry data save
		saveIndustryTemp(termLoanParameterRequest);
		// Sector data save
		saveSectorTemp(termLoanParameterRequest);
		geographicalCountryTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		//country data save
		saveCountryTemp(termLoanParameterRequest);
		//state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveStateTemp(termLoanParameterRequest);
		//city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveCityTemp(termLoanParameterRequest);
		//negative industry save
		negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(termLoanParameter.getId());
		saveNegativeIndustryTemp(termLoanParameterRequest);
		
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;

	}
	
	
	
	private void saveIndustryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveIndustryTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		System.out.println(workingCapitalParameterRequest.getIndustrylist());
		for (DataRequest dataRequest : workingCapitalParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveIndustryTemp");
	}

	private void saveSectorTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveSectorTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveSectorTemp");
	}
	
	private void saveCountryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("save saveCountryTemp");
		GeographicalCountryDetailTemp geographicalCountryDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetailTemp();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryTempRepository.save(geographicalCountryDetail);
		}
		logger.info("end saveCountryTemp");
	}
	
	private void saveStateTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveStateTemp");
		GeographicalStateDetailTemp geographicalStateDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetailTemp();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateTempRepository.save(geographicalStateDetail);
		}
		logger.info("end saveStateTemp");
	}
	
	private void saveCityTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveCityTemp");
		List<GeographicalCityDetailTemp> list = new ArrayList<GeographicalCityDetailTemp>();
		GeographicalCityDetailTemp geographicalCityDetail= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			list.add(geographicalCityDetail);
			
		}
		// create by and update
					geographicalCityTempRepository.save(list);
		logger.info("end saveCityTemp");
	}

	private void saveNegativeIndustryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustryTemp");
		NegativeIndustryTemp negativeIndustry= null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getUnInterestedIndustrylist()) {
			negativeIndustry = new NegativeIndustryTemp();
			negativeIndustry.setFpProductMasterId(workingCapitalParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryTempRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustryTemp");
		
	}

	
}
