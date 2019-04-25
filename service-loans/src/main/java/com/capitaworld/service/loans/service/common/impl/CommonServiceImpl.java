package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.service.common.CommonService;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class CommonServiceImpl implements CommonService{
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	private OneFormClient oneFormClient;
	
	public Map<String , Object> getCityStateCountryNameFromOneForm(Long cityId , Integer stateId ,Integer countryId) {
		try {
			OneFormResponse oneFormResponse= oneFormClient.getCityStateCountryById(cityId, stateId, countryId);
			if(oneFormResponse != null && oneFormResponse.getListData() != null && !oneFormResponse.getListData().isEmpty() &&  oneFormResponse.getListData().get(0) != null ) {	
				@SuppressWarnings("unchecked")
				List<String> b = (List<String>) oneFormResponse.getListData().get(0);
				Map<String ,Object> map= new HashMap<>();
				map.put("cityName" , b.get(0));
				map.put("stateName" , b.get(1));
				map.put("countryName" , b.get(2));
				logger.info("Data of city state and country {} " , oneFormResponse);
				return map;
			}
		} catch (Exception e) {
			logger.error("Error/Exception while getting city state and country name by calling oneform client",e);
		}
		return null;
	}
}
