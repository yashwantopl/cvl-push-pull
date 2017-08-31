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
import com.capitaworld.service.loans.domain.common.LAPEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.PersonalLoanEligibilityCriteria;
import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LoanEligibilility;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.service.common.LoanEligibilityCalculatorService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
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

	// HOME LOAN STARTS
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForHomeLoan(HomeLoanEligibilityRequest homeLoanRequest)
			throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinMaxForHomeLoan");
		try {
			Integer tenure = homeLoanRequest.getTenure();
			if (CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getTenure())) {
				Integer age = CommonUtils.getAgeFromBirthDate(homeLoanRequest.getDateOfBirth());
				if (age == null || age >= 60) {
					return null;
				}
				tenure = (60 - age > 30 ? 30 : 60 - age);
			} else {
				if (tenure <= 0) {
					return null;
				}
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

				// Maximum Amount Based on Salary and Max ROI
				double monthlyRate = homeLoanCriteria.getRoiLow() / 100 / 12;
				double totalPayments = tenure * 12;
				double result = getPMTCalculation(monthlyRate, totalPayments);
				double maximum = getMinMax(income, result);

				// Minimum Amount Based on Salary and Min ROI
				monthlyRate = homeLoanCriteria.getRoiHigh() / 100 / 12;
				result = getPMTCalculation(monthlyRate, totalPayments);
				double minimum = getMinMax(income, result);
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
	public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "getMinMaxBySalarySlab");
		Map<Integer, JSONObject> minMaxData = calculateMinMaxForHomeLoan(homeLoanRequest);
		if (minMaxData == null) {
			return null;
		}

		JSONObject json = new JSONObject();
		Entry<Integer, JSONObject> minFromMap = getMinFromMap(minMaxData);
		if (minFromMap != null) {
			json.put(CommonUtils.MINIMUM, Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM)));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM, Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM)));
		}
		CommonDocumentUtils.endHook(logger, "getMinMaxBySalarySlab");
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinSVMVForHomeLoan");
		Map<Integer, JSONObject> minMaxSalary = calculateMinMaxForHomeLoan(homeLoanRequest);
		if (minMaxSalary == null) {
			return null;
		}
		try {
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, Double> minData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);
				HomeLoanEligibilityCriteria homeLoanCriteria = loanEligibilityCriteriaRepository.getHomeLoanBySVMV(
						homeLoanRequest.getStampValue(), homeLoanRequest.getMarketValue(),
						Integer.class.cast(bankResponse.getId()));
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
					minData.put(homeLoanCriteria.getBankId(), saleDeedValue);
				} else {
					minData.put(homeLoanCriteria.getBankId(), marketValue);
				}
			}

			CommonDocumentUtils.endHook(logger, "calculateMinSVMVForHomeLoan");
			Map<Integer, JSONObject> minMaxFromSalaryAndMVSV = getMinMaxFromSalaryAndMVSV(minMaxSalary, minData);
			Map<Double, Double> resultMap = new HashMap<>(minMaxFromSalaryAndMVSV.size());
			for (Entry<Integer, JSONObject> entry : minMaxFromSalaryAndMVSV.entrySet()) {
				JSONObject json = entry.getValue();
				Double min = (Double) json.get(CommonUtils.MINIMUM);
				Double max = (Double) json.get(CommonUtils.MAXIMUM);
				if (max.doubleValue() == min.doubleValue()) {
					min = (min - (min * 10 / 100));
				}
				resultMap.put(max, min);
			}

			JSONObject result = new JSONObject();
			if (resultMap.isEmpty()) {
				result.put("message", "No Result Found");
			} else {
				result.put(CommonUtils.MAXIMUM, Math.round(Collections.max(resultMap.keySet())));
				result.put(CommonUtils.MINIMUM, Math.round(Collections.min(resultMap.values())));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForHomeLoan();
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put("minRoi", minMaxArr[0]);
					result.put("maxRoi", minMaxArr[1]);
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating HomeLoan Eligibility");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static Map<Integer, JSONObject> getMinMaxFromSalaryAndMVSV(Map<Integer, JSONObject> salaryMap,
			Map<Integer, Double> mvsvMap) {
		Map<Integer, JSONObject> finalMap = new HashMap<>(salaryMap.size());
		for (Entry<Integer, JSONObject> entry : salaryMap.entrySet()) {
			JSONObject finaljson = new JSONObject();
			JSONObject json = entry.getValue();
			Double mvsnMin = mvsvMap.get(entry.getKey());

			// Setting Minimum
			Double salMinMax = (Double) json.get(CommonUtils.MINIMUM);
			if (mvsnMin < salMinMax) {
				finaljson.put(CommonUtils.MINIMUM, mvsnMin);
			} else {
				finaljson.put(CommonUtils.MINIMUM, salMinMax);
			}

			// Setting Maximum
			salMinMax = (Double) json.get(CommonUtils.MAXIMUM);
			if (mvsnMin > salMinMax) {
				finaljson.put(CommonUtils.MAXIMUM, mvsnMin);
			} else {
				finaljson.put(CommonUtils.MAXIMUM, salMinMax);
			}
			finalMap.put(entry.getKey(), finaljson);
		}
		return finalMap;
	}
	// HOME LOAN ENDS

	// PERSONAL LOAN STARTS
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForPersonalLoan(PersonalLoanEligibilityRequest eligibilityRequest)
			throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinMaxForPersonalLoan");
		try {
			Integer tenure = eligibilityRequest.getTenure();
			if (CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getTenure())) {
				Integer age = CommonUtils.getAgeFromBirthDate(eligibilityRequest.getDateOfBirth());
				if (age == null || age >= 60) {
					return null;
				}
				tenure = (60 - age > 5 ? 5 : 60 - age);
			} else {
				if (tenure <= 0) {
					return null;
				}
				tenure = (tenure > 5 ? 5 : tenure);
			}
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, JSONObject> minMaxData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);

				PersonalLoanEligibilityCriteria personalLoanCriteria = loanEligibilityCriteriaRepository
						.getPersonalLoanBySalarySlab(eligibilityRequest.getIncome(),
								eligibilityRequest.getConstitution(), Integer.class.cast(bankResponse.getId()));
				if (personalLoanCriteria == null)
					continue;
				double income = (Double
						.valueOf((eligibilityRequest.getIncome() * personalLoanCriteria.getFoir()) / 100)).longValue();
				if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getObligation())) {
					income = income - eligibilityRequest.getObligation();
				}

				// Maximum Amount Based on Salary and Max ROI
				double monthlyRate = personalLoanCriteria.getRoiLow() / 100 / 12;
				double totalPayments = tenure * 12;
				double result = getPMTCalculation(monthlyRate, totalPayments);
				double maximum = getMinMax(income, result);

				// Minimum Amount Based on Salary and Min ROI
				monthlyRate = personalLoanCriteria.getRoiHigh() / 100 / 12;
				result = getPMTCalculation(monthlyRate, totalPayments);
				double minimum = getMinMax(income, result);
				JSONObject json = new JSONObject();
				json.put(CommonUtils.MAXIMUM, maximum);
				json.put(CommonUtils.MINIMUM, minimum);
				minMaxData.put(personalLoanCriteria.getBankId(), json);
			}
			CommonDocumentUtils.endHook(logger, "calculateMinMaxForPersonalLoan");
			return minMaxData;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating Personal Loan Eligibility");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcMinMaxForPersonalLoan(PersonalLoanEligibilityRequest eligibilityRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calcMinMaxForPersonalLoan");
		Map<Integer, JSONObject> minMaxData = calculateMinMaxForPersonalLoan(eligibilityRequest);
		if (minMaxData == null) {
			return null;
		}

		JSONObject json = new JSONObject();
		Entry<Integer, JSONObject> minFromMap = getMinFromMap(minMaxData);
		if (minFromMap != null) {
			json.put(CommonUtils.MINIMUM, Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM)));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM, Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM)));
		}

		if (json.isEmpty()) {
			json.put("message", "No Result Found");
		} else {
			Object[] minMaxArr = loanEligibilityCriteriaRepository
					.getMinMaxRoiForPersonalLoan(eligibilityRequest.getConstitution());
			if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
				json.put("minRoi", minMaxArr[0]);
				json.put("maxRoi", minMaxArr[1]);
			}
		}
		CommonDocumentUtils.endHook(logger, "calcMinMaxForPersonalLoan");
		return json;
	}

	// PERSONAL LOAN ENDS

	// LAP Starts
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForLAP(LAPEligibilityRequest eligibilityRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinMaxForLAP");
		try {
			Integer tenure = eligibilityRequest.getTenure();
			if (CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getTenure())) {
				Integer age = CommonUtils.getAgeFromBirthDate(eligibilityRequest.getDateOfBirth());
				if (age == null || age >= 60) {
					return null;
				}
				tenure = (60 - age > 30 ? 30 : 60 - age);
			} else {
				if (tenure <= 0) {
					return null;
				}
				tenure = (tenure > 30 ? 30 : tenure);
			}
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, JSONObject> minMaxData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);

				LAPEligibilityCriteria lapEligibilityCriteria = loanEligibilityCriteriaRepository.getLAPBySalarySlab(
						eligibilityRequest.getIncome(), eligibilityRequest.getEmploymentType(),
						Integer.class.cast(bankResponse.getId()), eligibilityRequest.getPropertyType());
				if (lapEligibilityCriteria == null || CommonUtils.isObjectNullOrEmpty(lapEligibilityCriteria.getMin()))
					continue;
				double income = (Double
						.valueOf((eligibilityRequest.getIncome() * lapEligibilityCriteria.getFoir()) / 100))
								.longValue();
				if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getObligation())) {
					income = income - eligibilityRequest.getObligation();
				}

				// Maximum Amount Based on Salary and Max ROI
				double monthlyRate = lapEligibilityCriteria.getRoiLow() / 100 / 12;
				double totalPayments = tenure * 12;
				double result = getPMTCalculation(monthlyRate, totalPayments);
				double maximum = getMinMax(income, result);

				monthlyRate = lapEligibilityCriteria.getRoiHigh() / 100 / 12;
				// Minimum Amount Based on Salary and Min ROI
				result = getPMTCalculation(monthlyRate, totalPayments);
				double minimum = getMinMax(income, result);
				JSONObject json = new JSONObject();
				json.put(CommonUtils.MAXIMUM, maximum);
				json.put(CommonUtils.MINIMUM, minimum);
				minMaxData.put(lapEligibilityCriteria.getBankId(), json);
			}
			CommonDocumentUtils.endHook(logger, "calculateMinMaxForLAP");
			return minMaxData;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating LAP Eligibility");
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcMinMaxForLAP(LAPEligibilityRequest eligibilityRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calcMinMaxForLAP");
		Map<Integer, JSONObject> minMaxData = calculateMinMaxForLAP(eligibilityRequest);
		if (minMaxData == null) {
			return null;
		}

		JSONObject json = new JSONObject();
		Entry<Integer, JSONObject> minFromMap = getMinFromMap(minMaxData);
		if (minFromMap != null) {
			json.put(CommonUtils.MINIMUM, Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM)));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM, Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM)));
		}
		CommonDocumentUtils.endHook(logger, "calcMinMaxForLAP");
		return json;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcLAPAmount(LAPEligibilityRequest eligibilityRequest) throws Exception {
		CommonDocumentUtils.startHook(logger, "calcLAPAmount");
		Map<Integer, JSONObject> minMaxSalary = calculateMinMaxForLAP(eligibilityRequest);
		if (minMaxSalary == null) {
			return null;
		}
		try {
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, Double> minData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);
				LAPEligibilityCriteria eligibilityCriteria = loanEligibilityCriteriaRepository.getLAPBySalarySlab(
						eligibilityRequest.getIncome(), eligibilityRequest.getEmploymentType(),
						Integer.class.cast(bankResponse.getId()), eligibilityRequest.getPropertyType());
				if (eligibilityCriteria == null || CommonUtils.isObjectNullOrEmpty(eligibilityCriteria.getMin()))
					continue;

				double marketValue = eligibilityRequest.getMarketValue() * eligibilityCriteria.getMarketValue() / 100;
				minData.put(eligibilityCriteria.getBankId(), marketValue);
			}

			CommonDocumentUtils.endHook(logger, "calcLAPAmount");
			Map<Integer, JSONObject> minMaxFromSalaryAndMVSV = getMinMaxFromSalaryAndMVSV(minMaxSalary, minData);
			Map<Double, Double> resultMap = new HashMap<>(minMaxFromSalaryAndMVSV.size());
			for (Entry<Integer, JSONObject> entry : minMaxFromSalaryAndMVSV.entrySet()) {
				JSONObject json = entry.getValue();
				Double min = (Double) json.get(CommonUtils.MINIMUM);
				Double max = (Double) json.get(CommonUtils.MAXIMUM);
				if (max.doubleValue() == min.doubleValue()) {
					min = (min - (min * 10 / 100));
				}
				resultMap.put(max, min);
			}

			JSONObject result = new JSONObject();
			if (resultMap.isEmpty()) {
				result.put("message", "No Result Found");
			} else {
				result.put(CommonUtils.MAXIMUM, Math.round(Collections.max(resultMap.keySet())));
				result.put(CommonUtils.MINIMUM, Math.round(Collections.min(resultMap.values())));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForLAP();
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put("minRoi", minMaxArr[0]);
					result.put("maxRoi", minMaxArr[1]);
				}
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Calculating HomeLoan Eligibility");
		}
		return null;
	}

	// LAP Ends

	// COMMON STARTS
	@Override
	public Integer calculateTenure(LoanEligibilility eligibilility, Integer productId) throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateTenure");
		try {
			Integer age = CommonUtils.getAgeFromBirthDate(eligibilility.getDateOfBirth());
			if (age == null || age >= 60 || age == 0) {
				return null;
			}
			LoanType type = CommonUtils.LoanType.getType(productId);
			switch (type) {
			case HOME_LOAN:
			case LAP_LOAN:
				return (60 - age > 30 ? 30 : 60 - age);
			case PERSONAL_LOAN:
				return (60 - age > 5 ? 5 : 60 - age);
			default:
				CommonDocumentUtils.endHook(logger, "calculateTenure");
				return null;
			}
		} catch (Exception e) {
			CommonDocumentUtils.endHook(logger, "calculateTenure");
			e.printStackTrace();
			logger.error("Error while calulating tenure for Product ==>" + productId);
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

	private static double getPMTCalculation(double monthlyRate, double totalPayments) {
		double loanAmount = 100000;
		return (monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalPayments)) * loanAmount;
	}

	private static double getMinMax(double income, double perLakhEMI) {
		double loanAmount = 100000;
		return ((income / perLakhEMI) * loanAmount);
	}

	// COMMON ENDS

}
