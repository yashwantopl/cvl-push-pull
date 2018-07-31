package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.*;

import com.capitaworld.service.loans.model.corporate.MsmeValueMappingRequest;
import com.capitaworld.service.loans.service.fundprovider.MsmeValueMappingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
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

import javax.persistence.criteria.CriteriaBuilder;

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

	@Autowired
	private WorkflowClient workflowClient;

	@Autowired
	private MsmeValueMappingService msmeValueMappingService;

	@Override
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest,Long mappingId) {
		logger.info("start saveOrUpdate");
		// TODO Auto-generated method stub
		
		WorkingCapitalParameterTemp loanParameter = workingCapitalParameterTempRepository
				.getworkingCapitalParameterTempByFpProductId(mappingId);
		
		
		WorkingCapitalParameter workingCapitalParameter = null;

		if(loanParameter.getFpProductMappingId()!=null)
		{
		workingCapitalParameter = workingCapitalParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (workingCapitalParameter == null) {
			workingCapitalParameter=new WorkingCapitalParameter();
			
		}
		
		
		loanParameter.setStatusId(CommonUtils.Status.APPROVED); 
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		loanParameter.setFpProductMappingId(workingCapitalParameter.getId());
		workingCapitalParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMaxTenure()))
			workingCapitalParameterRequest.setMaxTenure(workingCapitalParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMinTenure()))
			workingCapitalParameterRequest.setMinTenure(workingCapitalParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameter);
		
		workingCapitalParameter.setUserId(workingCapitalParameterRequest.getUserId()!=null?workingCapitalParameterRequest.getUserId():null);
		workingCapitalParameter.setProductId(workingCapitalParameterRequest.getProductId()!=null?workingCapitalParameterRequest.getProductId():null);
		
		workingCapitalParameter.setModifiedBy(workingCapitalParameterRequest.getUserId());
		workingCapitalParameter.setIsActive(true);
		workingCapitalParameter.setModifiedDate(new Date());
		workingCapitalParameter.setIsParameterFilled(true);
		workingCapitalParameter.setJobId(workingCapitalParameterRequest.getJobId());
		WorkingCapitalParameter workingCapitalParameter2=workingCapitalParameterRepository.save(workingCapitalParameter);
		workingCapitalParameterRequest.setId(workingCapitalParameter2.getId());
		industrySectorRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		// industry data save
		saveIndustry(workingCapitalParameterRequest);
		// Sector data save
		saveSector(workingCapitalParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		// country data save
		saveCountry(workingCapitalParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		saveState(workingCapitalParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(workingCapitalParameterRequest.getId());
		saveCity(workingCapitalParameterRequest);
		// negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(workingCapitalParameterRequest.getId());
		saveNegativeIndustry(workingCapitalParameterRequest);

		//Dhaval
		boolean isUpdate = msmeValueMappingService.updateMsmeValueMapping(false, workingCapitalParameterRequest.getId());
		logger.info("updated = {}",isUpdate);

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
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> sectorList = industrySectorRepository.getSectorByProductId(workingCapitalParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
				
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(workingCapitalParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> stateList = geographicalStateRepository
				.getStateByFpProductId(workingCapitalParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(workingCapitalParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setCityList(dataRequests);

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
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest
						.setUnInterestedIndustrylist(dataRequests);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWCParameterRequest", e);
				e.printStackTrace();
			}
		}
		workingCapitalParameterRequest.setMsmeFundingIds(msmeValueMappingService.getDataListFromFpProductId(2,id, workingCapitalParameterRequest.getUserId()));
		logger.info("end getWorkingCapitalParameter");
		return workingCapitalParameterRequest;
	}
	
	

	private void saveIndustry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveIndustry");
		IndustrySectorDetail industrySectorDetail = null;
		System.out.println(workingCapitalParameterRequest.getIndustrylist());
		List<DataRequest> dataRequests=workingCapitalParameterRequest.getIndustrylist();
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
		GeographicalCountryDetail geographicalCountryDetail = null;
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
		GeographicalStateDetail geographicalStateDetail = null;
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
		GeographicalCityDetail geographicalCityDetail = null;
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
		NegativeIndustry negativeIndustry = null;
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
			WorkingCapitalParameterRequest workingCapitalParameterRequest = getWorkingCapitalParameterTemp(mappingId,null,null);
			return saveOrUpdate(workingCapitalParameterRequest,mappingId);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public WorkingCapitalParameterRequest getWorkingCapitalParameterTemp(Long id,Long role,Long userId) {
		logger.info("start getWorkingCapitalParameterTemp");
		WorkingCapitalParameterRequest workingCapitalParameterRequest = new WorkingCapitalParameterRequest();
		WorkingCapitalParameterTemp loanParameter = workingCapitalParameterTempRepository
				.getworkingCapitalParameterTempByFpProductId(id);
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
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> sectorList = industrySectorTempRepository
				.getSectorByProductId(workingCapitalParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
				
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				workingCapitalParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(workingCapitalParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				workingCapitalParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> stateList = geographicalStateTempRepository
				.getStateByFpProductId(workingCapitalParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				workingCapitalParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.toString());
				e.printStackTrace();
			}
		}

		List<Long> cityList = geographicalCityTempRepository
				.getCityByFpProductId(workingCapitalParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				workingCapitalParameterRequest.setCityList(dataRequests);

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
				List<DataRequest> dataRequests=new ArrayList<>(formResponse.getListData().size());
				for(Object object:formResponse.getListData())
				{
					DataRequest dataRequest=com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				
				workingCapitalParameterRequest
						.setUnInterestedIndustrylist(dataRequests);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWCParameterRequest", e);
				e.printStackTrace();
			}
		}
		workingCapitalParameterRequest.setJobId(loanParameter.getJobId());
		
		//set workflow buttons
		
		 if (!CommonUtils.isObjectNullOrEmpty(loanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
             WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(loanParameter.getJobId(),Arrays.asList(role), userId);
             if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
                 try {
                     WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
                     if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
                    	 workingCapitalParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
                     } else {
                         logger.info("response from workflow NULL jobId = {} and roleId = {}", loanParameter.getJobId(), role);
                     }
                 } catch (IOException e) {
                     logger.error("Error While getting data from workflow {}", e);
                 }
             }
         } else {
             logger.info("you set jobId or list of roleId NULL for calling workflow");
         }

		workingCapitalParameterRequest.setMsmeFundingIds(msmeValueMappingService.getDataListFromFpProductId(1,id, userId));
		logger.info("end getWorkingCapitalParameterTemp");
		return workingCapitalParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveOrUpdateTemp");
		// TODO Auto-generated method stub
		WorkingCapitalParameterTemp workingCapitalParameter = null;

		if(workingCapitalParameterRequest.getAppstage()==1)
		{
			workingCapitalParameter = workingCapitalParameterTempRepository.findOne(workingCapitalParameterRequest.getId());
		}
		else
		{
			
			workingCapitalParameter = workingCapitalParameterTempRepository.getworkingCapitalParameterTempByFpProductMappingId(workingCapitalParameterRequest.getId());
			
		}
		
		if (workingCapitalParameter == null) {
			workingCapitalParameter=new WorkingCapitalParameterTemp();
			workingCapitalParameter.setFpProductMappingId(workingCapitalParameterRequest.getId());
		}

		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMaxTenure()))
			workingCapitalParameterRequest.setMaxTenure(workingCapitalParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(workingCapitalParameterRequest.getMinTenure()))
			workingCapitalParameterRequest.setMinTenure(workingCapitalParameterRequest.getMinTenure() * 12);

		if(workingCapitalParameterRequest.getAppstage()!=1)
		{
			workingCapitalParameter.setFpProductMappingId(workingCapitalParameterRequest.getId());
		}
		
		BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameter);
		
		workingCapitalParameter.setUserId(workingCapitalParameterRequest.getUserId()!=null?workingCapitalParameterRequest.getUserId():null);
		workingCapitalParameter.setProductId(workingCapitalParameterRequest.getProductId()!=null?workingCapitalParameterRequest.getProductId():null);
		workingCapitalParameter.setModifiedBy(workingCapitalParameterRequest.getUserId());
		workingCapitalParameter.setIsActive(true);
		workingCapitalParameter.setModifiedDate(new Date());
		workingCapitalParameter.setIsParameterFilled(true);
		workingCapitalParameter.setStatusId(CommonUtils.Status.OPEN);
		workingCapitalParameter.setIsApproved(false);
		workingCapitalParameter.setIsDeleted(false);
		workingCapitalParameter.setIsCopied(false);
		workingCapitalParameter.setApprovalDate(null);

		if (CommonUtils.isObjectNullOrEmpty(workingCapitalParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
					WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
					workingCapitalParameterRequest.getUserId());
			Long jobId = null;
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
			}

			workingCapitalParameter.setJobId(jobId);
		}
		
		workingCapitalParameterTempRepository.save(workingCapitalParameter);
		workingCapitalParameterRequest.setId(workingCapitalParameter.getId());
		industrySectorTempRepository.inActiveMappingByFpProductId(workingCapitalParameter.getId());
		// industry data save
		saveIndustryTemp(workingCapitalParameterRequest);
		// Sector data save
		saveSectorTemp(workingCapitalParameterRequest);
		geographicalCountryTempRepository.inActiveMappingByFpProductId(workingCapitalParameter.getId());
		// country data save
		saveCountryTemp(workingCapitalParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(workingCapitalParameter.getId());
		saveStateTemp(workingCapitalParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(workingCapitalParameter.getId());
		saveCityTemp(workingCapitalParameterRequest);
		// negative industry save
		negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(workingCapitalParameter.getId());
		saveNegativeIndustryTemp(workingCapitalParameterRequest);
		//Dhaval
		boolean isUpdate = msmeValueMappingService.updateMsmeValueMappingTemp(workingCapitalParameterRequest.getMsmeFundingIds(),workingCapitalParameterRequest.getId(), workingCapitalParameterRequest.getUserId());
		logger.info("updated = {}",isUpdate);
		logger.info("end saveOrUpdateTemp");
		return true;
	}

	private void saveIndustryTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
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

	private void saveSectorTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
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

	private void saveCountryTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("save saveCountryTemp");
		GeographicalCountryDetailTemp geographicalCountryDetail = null;
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

	private void saveStateTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveStateTemp");
		GeographicalStateDetailTemp geographicalStateDetail = null;
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

	private void saveCityTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveCityTemp");
		GeographicalCityDetailTemp geographicalCityDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityTempRepository.save(geographicalCityDetail);
		}
		logger.info("end saveCityTemp");
	}

	private void saveNegativeIndustryTemp(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustryTemp");
		NegativeIndustryTemp negativeIndustry = null;
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
