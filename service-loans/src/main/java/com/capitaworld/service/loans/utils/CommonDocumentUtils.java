package com.capitaworld.service.loans.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.aspectj.weaver.tools.Trace;
import org.slf4j.Logger;
import org.springframework.core.env.Environment;

import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.EstablistmentYearClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

public class CommonDocumentUtils {

	public static Long getProductDocumentId(int productId) {
		switch (productId) {
		case 1:
			return DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE;
		case 2:
			return DocumentAlias.TERM_LOAN_PROFIEL_PICTURE;
		case 3:
			return DocumentAlias.HOME_LOAN_PROFIEL_PICTURE;
		case 12:
			return DocumentAlias.CAR_LOAN_PROFIEL_PICTURE;
		case 7:
			return DocumentAlias.PERSONAL_LOAN_PROFIEL_PICTURE;
		case 13:
			return DocumentAlias.LAP_LOAN_PROFIEL_PICTURE;
		case 14:
			return DocumentAlias.LAS_LOAN_PROFIEL_PICTURE;
		}
		return null;
	}

	public static String getCity(Long cityId, Environment environment) throws Exception {
		CityByCityListIdClient cityClient = new CityByCityListIdClient(
				environment.getRequiredProperty(CommonUtils.ONE_FORM));
		List<Long> cityIdRequest = new ArrayList<>();
		cityIdRequest.add(cityId);
		OneFormResponse response = cityClient.send(cityIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : "NA";
	}

	public static String getState(Long stateId, Environment environment) throws Exception {
		StateListByStateListIdClient stateClient = new StateListByStateListIdClient(
				environment.getRequiredProperty(CommonUtils.ONE_FORM));
		List<Long> stateIdRequest = new ArrayList<>();
		stateIdRequest.add(stateId);
		OneFormResponse response = stateClient.send(stateIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : "NA";
	}

	public static String getCountry(Long countryId, Environment environment) throws Exception {
		CountryByCountryListIdClient countryClient = new CountryByCountryListIdClient(
				environment.getRequiredProperty(CommonUtils.ONE_FORM));
		List<Long> countryIdRequest = new ArrayList<>();
		countryIdRequest.add(countryId);
		OneFormResponse response = countryClient.send(countryIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : "NA";
	}

	public static Integer getYear(Long yearId, Environment environment) throws Exception {
		EstablistmentYearClient client = new EstablistmentYearClient(
				environment.getRequiredProperty(CommonUtils.ONE_FORM));
		OneFormResponse response = client.send(yearId);
		if (!CommonUtils.isListNullOrEmpty(response.getListData())) {
			MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
			return data != null ? Integer.valueOf(data.getValue()) : 0;
		}
		return 0;
	}

	public static String getCurrency(Integer currencyId) {
		return !CommonUtils.isObjectNullOrEmpty(currencyId) ? Currency.getById(currencyId).getValue() : "NA";
	}
	
	public static String getDenomination(Integer denominationId) {
		return !CommonUtils.isObjectNullOrEmpty(denominationId) ? Denomination.getById(denominationId).getValue() :  "NA";
	}
	
	public static void startHook(Logger logger,String methodName){
		logger.info("Start " + methodName + "() Method");
	}
	
	public static void endHook(Logger logger,String methodName){
        logger.info("End " + methodName + "() Method");
	}

}
