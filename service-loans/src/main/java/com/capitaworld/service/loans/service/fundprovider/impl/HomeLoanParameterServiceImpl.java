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
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Transactional
@Service
public class HomeLoanParameterServiceImpl implements HomeLoanParameterService {
	private static final Logger logger = LoggerFactory.getLogger(HomeLoanParameterServiceImpl.class);
	@Autowired
	private HomeLoanParameterRepository homeLoanParameterRepository;

	@Autowired
	private GeographicalCountryRepository geographicalCountryRepository;

	@Autowired
	private GeographicalStateRepository geographicalStateRepository;

	@Autowired
	private GeographicalCityRepository geographicalCityRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@Override
	public boolean saveOrUpdate(HomeLoanParameterRequest homeLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		HomeLoanParameter homeLoanParameter = null;

		homeLoanParameter = homeLoanParameterRepository.findOne(homeLoanParameterRequest.getId());
		if (homeLoanParameter == null) {
			return false;
		}

		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() * 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() * 12);

		BeanUtils.copyProperties(homeLoanParameterRequest, homeLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		homeLoanParameter.setModifiedBy(homeLoanParameterRequest.getUserId());
		homeLoanParameter.setModifiedDate(new Date());
		homeLoanParameterRepository.save(homeLoanParameter);

		geographicalCountryRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		// country data save
		saveCountry(homeLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveState(homeLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(homeLoanParameterRequest.getId());
		saveCity(homeLoanParameterRequest);
		CommonDocumentUtils.endHook(logger, "saveOrUpdate");
		return true;
	}

	@Override
	public HomeLoanParameterRequest getHomeLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "getHomeLoanParameterRequest");
		HomeLoanParameterRequest homeLoanParameterRequest = new HomeLoanParameterRequest();
		HomeLoanParameter homeLoanParameter = homeLoanParameterRepository.getByID(id);
		if (homeLoanParameter == null)
			return null;
		BeanUtils.copyProperties(homeLoanParameter, homeLoanParameterRequest);
		
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMaxTenure()))
			homeLoanParameterRequest.setMaxTenure(homeLoanParameterRequest.getMaxTenure() / 12);
		if (!CommonUtils.isObjectListNull(homeLoanParameterRequest.getMinTenure()))
			homeLoanParameterRequest.setMinTenure(homeLoanParameterRequest.getMinTenure() / 12);

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(homeLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				homeLoanParameterRequest.setCountryList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getHomeLoanParameterRequest",e);
				e.printStackTrace();
			}
		}

		List<Long> stateList = geographicalStateRepository.getStateByFpProductId(homeLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				homeLoanParameterRequest.setStateList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getHomeLoanParameterRequest",e);
				e.printStackTrace();
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(homeLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				homeLoanParameterRequest.setCityList((List<DataRequest>) formResponse.getListData());

			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("error while getHomeLoanParameterRequest",e);
				e.printStackTrace();
			}
		}
		CommonDocumentUtils.endHook(logger, "getHomeLoanParameterRequest");
		return homeLoanParameterRequest;
	}

	private void saveCountry(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");

		GeographicalCountryDetail geographicalCountryDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
			CommonDocumentUtils.endHook(logger, "saveCountry");
		}
	}

	private void saveState(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}

	private void saveCity(HomeLoanParameterRequest homeLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail = null;
		for (DataRequest dataRequest : homeLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(homeLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(homeLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

}
