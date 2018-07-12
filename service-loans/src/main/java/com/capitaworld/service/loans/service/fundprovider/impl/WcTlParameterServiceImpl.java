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
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameter;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.WcTlParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.WcTlLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WcTlParameterTempRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.capitaworld.service.loans.service.fundprovider.WcTlParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class WcTlParameterServiceImpl implements WcTlParameterService {
	private static final Logger logger = LoggerFactory.getLogger(WcTlParameterServiceImpl.class);
	@Autowired
	private WcTlLoanParameterRepository wcTlLoanParameterRepository;
	
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
	private WcTlParameterTempRepository wcTlParameterTempRepository;

	@Override
	public boolean saveOrUpdate(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		// TODO Auto-generated method stub
		
		WcTlParameter WcTlParameter = null;

		WcTlParameter = wcTlLoanParameterRepository.findOne(wcTlParameterRequest.getId());
		if (WcTlParameter == null) {
			return false;
		}
		
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMaxTenure()))
			wcTlParameterRequest.setMaxTenure(wcTlParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMinTenure()))
			wcTlParameterRequest.setMinTenure(wcTlParameterRequest.getMinTenure().multiply(new BigDecimal("12")));
		
		BeanUtils.copyProperties(wcTlParameterRequest, WcTlParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		WcTlParameter.setModifiedBy(wcTlParameterRequest.getUserId());
		WcTlParameter.setModifiedDate(new Date());
		WcTlParameter.setIsActive(true);
		WcTlParameter.setIsParameterFilled(true);
		wcTlLoanParameterRepository.save(WcTlParameter);
		
		industrySectorRepository.inActiveMappingByFpProductId(wcTlParameterRequest.getId());
		// industry data save
		saveIndustry(wcTlParameterRequest);
		// Sector data save
		saveSector(wcTlParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(wcTlParameterRequest.getId());
		//country data save
		saveCountry(wcTlParameterRequest);
		//state data save
		geographicalStateRepository.inActiveMappingByFpProductId(wcTlParameterRequest.getId());
		saveState(wcTlParameterRequest);
		//city data save
		geographicalCityRepository.inActiveMappingByFpProductId(wcTlParameterRequest.getId());
		saveCity(wcTlParameterRequest);
		//negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(wcTlParameterRequest.getId());
		saveNegativeIndustry(wcTlParameterRequest);
		
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;

	}

	

	@Override
	public WcTlParameterRequest getWcTlRequest(Long id) {
		CommonDocumentUtils.startHook(logger, "getTermLoanParameterRequest");
		// TODO Auto-generated method stub
		WcTlParameterRequest wcTlParameterRequest = new WcTlParameterRequest();
		WcTlParameter loanParameter = wcTlLoanParameterRepository.getById(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, wcTlParameterRequest);
		
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMaxTenure()))
			wcTlParameterRequest.setMaxTenure(wcTlParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMinTenure()))
			wcTlParameterRequest.setMinTenure(wcTlParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		
		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(wcTlParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				wcTlParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(wcTlParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
			wcTlParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}

		List<Long> countryList=geographicalCountryRepository.getCountryByFpProductId(wcTlParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			wcTlParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateRepository.getStateByFpProductId(wcTlParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			wcTlParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityRepository.getCityByFpProductId(wcTlParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			wcTlParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getTermLoanParameterRequest",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(wcTlParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				wcTlParameterRequest.setNegativeIndustryList((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getTermLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		CommonDocumentUtils.endHook(logger, "getTermLoanParameterRequest");
		return wcTlParameterRequest;
	}
	
	private void saveIndustry(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveIndustry");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : wcTlParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(wcTlParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(wcTlParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(wcTlParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveIndustry");
	}

	private void saveSector(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveSector");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : wcTlParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(wcTlParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(wcTlParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(wcTlParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveSector");
	}
	
	private void saveCountry(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");
		
		GeographicalCountryDetail geographicalCountryDetail= null;
		for (DataRequest dataRequest : wcTlParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(wcTlParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(wcTlParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(wcTlParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}
	
	private void saveState(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail= null;
		for (DataRequest dataRequest : wcTlParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(wcTlParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(wcTlParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(wcTlParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}
	
	private void saveCity(WcTlParameterRequest wcTlParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail= null;
		for (DataRequest dataRequest : wcTlParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(wcTlParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(wcTlParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(wcTlParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

	private void saveNegativeIndustry(WcTlParameterRequest wcTlParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustry");
		NegativeIndustry negativeIndustry= null;
		for (DataRequest dataRequest : wcTlParameterRequest.getNegativeIndustryList()) {
			negativeIndustry = new NegativeIndustry();
			negativeIndustry.setFpProductMasterId(wcTlParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(wcTlParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(wcTlParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustry");
		
	}



	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.fundprovider.WcTlParameterService#saveMasterFromTempWcTl(java.lang.Long)
	 */
	@Override
	public Boolean saveMasterFromTempWcTl(Long mappingId) throws Exception {
		try {
			WcTlParameterRequest temp =  getWcTlRequestTemp(mappingId);

		return saveOrUpdate(temp);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public WcTlParameterRequest getWcTlRequestTemp(Long id) {
		CommonDocumentUtils.startHook(logger, "getWcTlRequestTemp");
		// TODO Auto-generated method stub
		WcTlParameterRequest wcTlParameterRequest = new WcTlParameterRequest();
		WcTlParameterTemp loanParameter =  wcTlParameterTempRepository.getWcTlParameterTempByFpProductId(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, wcTlParameterRequest);
		
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMaxTenure()))
			wcTlParameterRequest.setMaxTenure(wcTlParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(wcTlParameterRequest.getMinTenure()))
			wcTlParameterRequest.setMinTenure(wcTlParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		
		List<Long> industryList = industrySectorTempRepository
				.getIndustryByProductId(wcTlParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				wcTlParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWcTlRequestTemp",e);
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorTempRepository
				.getSectorByProductId(wcTlParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
			wcTlParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getWcTlRequestTemp",e);
			e.printStackTrace();
		}
		}

		List<Long> countryList=geographicalCountryTempRepository.getCountryByFpProductId(wcTlParameterRequest.getId());
		if(!countryList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
			wcTlParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getWcTlRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> stateList=geographicalStateTempRepository.getStateByFpProductId(wcTlParameterRequest.getId());
		if(!stateList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
			wcTlParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getWcTlRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> cityList=geographicalCityTempRepository.getCityByFpProductId(wcTlParameterRequest.getId());
		if(!cityList.isEmpty())
		{
		try {
			OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
			wcTlParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("error while getWcTlRequestTemp",e);
			e.printStackTrace();
		}
		}
		
		
		List<Long> negativeIndustryList = negativeIndustryTempRepository
				.getIndustryByFpProductMasterId(wcTlParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				wcTlParameterRequest.setNegativeIndustryList((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getWcTlRequestTemp",e);
				e.printStackTrace();
			}
		}
		CommonDocumentUtils.endHook(logger, "getWcTlRequestTemp");
		return wcTlParameterRequest;
	}
	
}
