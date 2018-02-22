package com.capitaworld.service.loans.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;

import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.oneform.client.OneFormClient;
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
		case 15:// UNSECURED_LOAN_PROFIEL_PICTURE
			return DocumentAlias.UNSECURED_LOAN_PROFIEL_PICTURE;
		}
		return null;
	}

	public static String getCity(Long cityId, OneFormClient oneFormClient) throws Exception {
		if (CommonUtils.isObjectNullOrEmpty(cityId))
			return null;
		List<Long> cityIdRequest = new ArrayList<>(1);
		cityIdRequest.add(cityId);
		OneFormResponse response = oneFormClient.getCityByCityListId(cityIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : null;
	}

	public static String getState(Long stateId, OneFormClient oneFormClient) throws Exception {
		if (CommonUtils.isObjectNullOrEmpty(stateId))
			return null;
		List<Long> stateIdRequest = new ArrayList<>(1);
		stateIdRequest.add(stateId);
		OneFormResponse response = oneFormClient.getStateByStateListId(stateIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : null;
	}

	public static String getStateCode(Long stateId, OneFormClient oneFormClient) throws Exception {
		if (CommonUtils.isObjectNullOrEmpty(stateId))
			return null;
		List<Long> stateIdRequest = new ArrayList<>(1);
		stateIdRequest.add(stateId);
		OneFormResponse response = oneFormClient.getStateCodeByStateListId(stateIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : null;
	}

	public static String getCountry(Long countryId, OneFormClient oneFormClient) throws Exception {
		if (CommonUtils.isObjectNullOrEmpty(countryId))
			return null;
		List<Long> countryIdRequest = new ArrayList<>(1);
		countryIdRequest.add(countryId);
		OneFormResponse response = oneFormClient.getCountryByCountryListId(countryIdRequest);
		MasterResponse data = MultipleJSONObjectHelper
				.getObjectFromMap((LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
		return data != null ? data.getValue() : null;
	}

	public static Integer getYear(Long yearId, OneFormClient oneFormClient) throws Exception {
		if (CommonUtils.isObjectNullOrEmpty(yearId))
			return null;
		OneFormResponse response = oneFormClient.getYearByYearId(yearId);
		if (!CommonUtils.isListNullOrEmpty(response.getListData())) {
			MasterResponse data = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) response.getListData().get(0), MasterResponse.class);
			return data != null ? Integer.valueOf(data.getValue()) : 0;
		}
		return 0;
	}

	public static String getCurrency(Integer currencyId) {
		if (!CommonUtils.isObjectNullOrEmpty(currencyId)) {
			if (!CommonUtils.isObjectNullOrEmpty(Currency.getById(currencyId))) {
				return Currency.getById(currencyId).getValue();
			}
		}
		return "NA";
	}

	public static String getDenomination(Integer denominationId) {
		if (!CommonUtils.isObjectNullOrEmpty(denominationId)) {
			if (!CommonUtils.isObjectNullOrEmpty(Denomination.getById(denominationId))) {
				return Denomination.getById(denominationId).getValue();
			}
		}
		return "NA";
	}

	public static Double convertAmountInAbsolute(Integer denominationId, Double amount) {
		if (!CommonUtils.isObjectNullOrEmpty(denominationId) && !CommonUtils.isObjectNullOrEmpty(amount)) {
			switch (denominationId) {
			case 1:
				amount = amount * 100000;// Lakhs
				break;
			case 2:
				amount = amount * 1000000;// Millions
				break;
			case 3:
				amount = amount * 10000000;// Crores
				break;
			case 4:
				amount = amount * 1000000000;// Billions
				break;
			default:
				break;// Absolute
			}
		}
		return amount;
	}

	public static void startHook(Logger logger, String methodName) {
		logger.info("Start " + methodName + "() Method");
	}

	public static void endHook(Logger logger, String methodName) {
		logger.info("End " + methodName + "() Method");
	}

	public static String getAdministrativeOfficeAddress(CorporateApplicantDetail applicantDetail,
			OneFormClient oneFormClient) throws Exception {
		String admntOfficeAdd = "";
		admntOfficeAdd = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativePremiseNumber())
				? applicantDetail.getAdministrativePremiseNumber() + ", "
				: "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativeStreetName())
				? applicantDetail.getAdministrativeStreetName() + ", "
				: "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getAdministrativeLandMark())
				? applicantDetail.getAdministrativeLandMark() + ", "
				: "";

		String country = CommonDocumentUtils.getCountry(applicantDetail.getAdministrativeCountryId().longValue(),
				oneFormClient);
		String state = CommonDocumentUtils.getState(applicantDetail.getAdministrativeStateId().longValue(),
				oneFormClient);
		String city = CommonDocumentUtils.getCity(applicantDetail.getAdministrativeCityId(), oneFormClient);
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(country) ? country + ", " : "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(state) ? state + ", " : "";
		admntOfficeAdd += !CommonUtils.isObjectNullOrEmpty(city) ? city : "";
		return admntOfficeAdd;
	}
	
	
	public static String getPermenantAddress(RetailApplicantDetail applicantDetail,
			OneFormClient oneFormClient) throws Exception {
		String permAdd = "";
		permAdd = !CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentPremiseNumberName())
				? applicantDetail.getPermanentPremiseNumberName() + ", "
				: "";
		permAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentStreetName())
				? applicantDetail.getPermanentStreetName() + ", "
				: "";
		permAdd += !CommonUtils.isObjectNullOrEmpty(applicantDetail.getPermanentLandMark())
				? applicantDetail.getPermanentLandMark() + ", "
				: "";

		String country = CommonDocumentUtils.getCountry(applicantDetail.getPermanentCountryId().longValue(),
				oneFormClient);
		String state = CommonDocumentUtils.getState(applicantDetail.getPermanentStateId().longValue(),
				oneFormClient);
		String city = CommonDocumentUtils.getCity(applicantDetail.getPermanentCityId(), oneFormClient);
		permAdd += !CommonUtils.isObjectNullOrEmpty(country) ? country + ", " : "";
		permAdd += !CommonUtils.isObjectNullOrEmpty(state) ? state + ", " : "";
		permAdd += !CommonUtils.isObjectNullOrEmpty(city) ? city : "";
		return permAdd;
	}

}
