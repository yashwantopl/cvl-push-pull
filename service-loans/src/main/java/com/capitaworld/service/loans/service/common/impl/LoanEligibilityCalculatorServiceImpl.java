 package com.capitaworld.service.loans.service.common.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.util.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.service.common.LoanEligibilityCalculatorService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
@Transactional
public class LoanEligibilityCalculatorServiceImpl implements LoanEligibilityCalculatorService {

	private static final Logger logger = LoggerFactory.getLogger(LoanEligibilityCalculatorServiceImpl.class);

	@Autowired
	private LoanEligibilityCriteriaRepository loanEligibilityCriteriaRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@SuppressWarnings("unchecked")
	private Map<Integer,JSONObject> calculateMinMaxForHomeLoan(HomeLoanEligibilityRequest homeLoanRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateHomeLoan");
		try {
			Integer tenure = homeLoanRequest.getTenure();
			if (CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getTenure())) {
				Integer age = CommonUtils.getAgeFromBirthDate(homeLoanRequest.getDateOfBirth());
				tenure = (60 - age > 30 ? 30 : 60 - age);
			} else {
				tenure = (tenure > 30 ? 30 : tenure);
			}
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, JSONObject> minMaxData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);
				HomeLoanEligibilityCriteria homeLoanCriteria = loanEligibilityCriteriaRepository
						.getHomeLoanBySalarySlab(homeLoanRequest.getIncome(), homeLoanRequest.getEmploymentType(),
								Integer.class.cast(bankResponse.getId()));
				if (homeLoanCriteria == null)
					continue;
				double income = (Double.valueOf((homeLoanRequest.getIncome() * homeLoanCriteria.getFoir()) / 100))
						.longValue();
				if (!CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getObligation())) {
					income = income - homeLoanRequest.getObligation();
				}
				
				//Maximum Amount Based on Salary and Max ROI
				double monthlyRate = homeLoanCriteria.getRoiLow() / 100 / 12;
				double totalPayments = tenure * 12;
				double loanAmount = 100000;
				double result = Double
						.valueOf((monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalPayments)) * loanAmount);
				double maximum = ((income / result) * loanAmount);

				//Minimum Amount Based on Salary and Min ROI
				monthlyRate = homeLoanCriteria.getRoiHigh() / 100 / 12;
				result = Double.valueOf((monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalPayments)) * loanAmount);
				double minimum = ((income / result) * loanAmount);
				JSONObject json = new JSONObject();
				json.put(CommonUtils.MAXIMUM, maximum);
				json.put(CommonUtils.MINIMUM, minimum);
				minMaxData.put(homeLoanCriteria.getBankId(), json);
			}
			CommonDocumentUtils.endHook(logger, "calculateHomeLoan");
			return minMaxData;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating HomeLoan Eligibility");
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws Exception{
		Map<Integer,JSONObject> minMaxData = calculateMinMaxForHomeLoan(homeLoanRequest);
		if(minMaxData == null){
			return null;
		}
		
		JSONObject json = new JSONObject();
		Entry<Integer, JSONObject> minFromMap = getMinFromMap(minMaxData);
		if(minFromMap != null){
			json.put(CommonUtils.MINIMUM, Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM)));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if(minFromMap != null){
			json.put(CommonUtils.MAXIMUM, Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM)));
		}
		return json;
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinSVMVForHomeLoan");
		try {
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer,Double> minData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);
				HomeLoanEligibilityCriteria homeLoanCriteria = loanEligibilityCriteriaRepository.getHomeLoanBySVMV(
						homeLoanRequest.getStampValue(), homeLoanRequest.getMarketValue(),Integer.class.cast(bankResponse.getId()));
				if (homeLoanCriteria == null)
					continue;
				double saleDeedValue = 0.0;
				double marketValue = 0.0;
				if (!CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getStampValue())) {
					saleDeedValue = homeLoanRequest.getStampValue() * homeLoanCriteria.getSaleDeedValue() / 100;
				}
				if (!CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getMarketValue())) {
					marketValue = homeLoanRequest.getMarketValue() * homeLoanCriteria.getMarketValue() / 100;
				}
				if (saleDeedValue < marketValue) {
					minData.put(homeLoanCriteria.getBankId(),saleDeedValue);
				} else {
					minData.put(homeLoanCriteria.getBankId(),marketValue);
				}
			}
			
			CommonDocumentUtils.endHook(logger, "calculateMinSVMVForHomeLoan");
			Map<Integer, JSONObject> minMaxFromSalaryAndMVSV = getMinMaxFromSalaryAndMVSV(calculateMinMaxForHomeLoan(homeLoanRequest),minData);
			Map<Double, Double> resultMap = new HashMap<>(minMaxFromSalaryAndMVSV.size());
			for (Entry<Integer, JSONObject> entry : minMaxFromSalaryAndMVSV.entrySet()) {
				JSONObject json = entry.getValue();
				Double min = (Double) json.get(CommonUtils.MINIMUM);
				Double max = (Double) json.get(CommonUtils.MAXIMUM);
				if(max.doubleValue() == min.doubleValue()){
					min = (min - (min * 10 / 100));
				}
				resultMap.put(max, min);
			}
			
			JSONObject result = new JSONObject();
			result.put(CommonUtils.MAXIMUM, Math.round(Collections.max(resultMap.keySet())));
			result.put(CommonUtils.MINIMUM, Math.round(Collections.min(resultMap.values())));
			Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForHomeLoan();
			if(!CommonUtils.isObjectNullOrEmpty(minMaxArr)){
				result.put("minRoi",minMaxArr[0]);
				result.put("maxRoi",minMaxArr[1]);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating HomeLoan Eligibility");
		}
		return null;
	}

	@Override
	public Integer calculateTenure(HomeLoanEligibilityRequest homeLoanRequest) throws Exception {
		try {
			Integer age = CommonUtils.getAgeFromBirthDate(homeLoanRequest.getDateOfBirth());
			if(age == null || age >= 60){
				return null;
			}
			return (60 - age > 30 ? 30 : 60 - age);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while calulating tenure");
			throw new ExcelException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private static Entry<Integer, JSONObject> getMinFromMap(Map<Integer, JSONObject> data) {
		Map.Entry<Integer, JSONObject> minEntry = null;
		for (Entry<Integer, JSONObject> entry : data.entrySet()) {
			JSONObject json = entry.getValue();
			Double minNew = null;
			Double min = (Double) json.get(CommonUtils.MINIMUM);
			logger.info("min==>" + min);
			if (minEntry != null) {
				minNew = (Double) minEntry.getValue().get(CommonUtils.MINIMUM);
			}
			if (minEntry == null || min < minNew) {
				minEntry = entry;
			}
		}
		return minEntry;
	}

	private static Entry<Integer, JSONObject> getMaxFromMap(Map<Integer, JSONObject> data) {
		Map.Entry<Integer, JSONObject> maxEntry = null;
		for (Entry<Integer, JSONObject> entry : data.entrySet()) {
			JSONObject json = entry.getValue();
			Double maxNew = null;
			Double max = (Double) json.get(CommonUtils.MAXIMUM);
			if (maxEntry != null) {
				maxNew = (Double) maxEntry.getValue().get(CommonUtils.MAXIMUM);
			}
			if (maxEntry == null || max > maxNew) {
				maxEntry = entry;
			}
		}
		return maxEntry;
	}
	
	@SuppressWarnings("unchecked")
	public static Map<Integer,JSONObject> getMinMaxFromSalaryAndMVSV(Map<Integer,JSONObject> salaryMap,Map<Integer,Double> mvsvMap){
		Map<Integer,JSONObject> finalMap = new HashMap<>(salaryMap.size());
		for (Entry<Integer, JSONObject> entry : salaryMap.entrySet()) {
			JSONObject finaljson = new JSONObject();
			JSONObject json = entry.getValue();
			Double mvsnMin = mvsvMap.get(entry.getKey());
			
			//Setting Minimum
			Double salMinMax = (Double) json.get(CommonUtils.MINIMUM);
			if(mvsnMin < salMinMax){
				finaljson.put(CommonUtils.MINIMUM, mvsnMin);
			}else{
				finaljson.put(CommonUtils.MINIMUM, salMinMax);
			}

			//Setting Maximum
			salMinMax = (Double) json.get(CommonUtils.MAXIMUM);
			if(mvsnMin > salMinMax){
				finaljson.put(CommonUtils.MAXIMUM, mvsnMin);
			}else{
				finaljson.put(CommonUtils.MAXIMUM, salMinMax);
			}
			finalMap.put(entry.getKey(), finaljson);
		}
		return finalMap;
	}

}
