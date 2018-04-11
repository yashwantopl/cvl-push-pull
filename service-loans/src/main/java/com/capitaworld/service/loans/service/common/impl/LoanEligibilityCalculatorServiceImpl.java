package com.capitaworld.service.loans.service.common.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
import com.capitaworld.service.loans.model.CMADetailResponse;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LoanEligibilility;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
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

	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	// HOME LOAN STARTS
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForHomeLoan(HomeLoanEligibilityRequest homeLoanRequest)
			throws Exception {
		CommonDocumentUtils.startHook(logger, "calculateMinMaxForHomeLoan");
		try {
			Integer tenure = getUpdatedTenure(homeLoanRequest, CommonUtils.LoanType.HOME_LOAN.getValue());
			if (tenure == null) {
				return null;
			}
			OneFormResponse bankByStatus = oneFormClient.getBankByStatus(true);
			Map<Integer, JSONObject> minMaxData = new HashMap<>(bankByStatus.getListData().size());
			for (Object data : bankByStatus.getListData()) {
				MasterResponse bankResponse = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) data, MasterResponse.class);

				logger.info("bankResponse.getId()==>" + bankResponse.getId());
				HomeLoanEligibilityCriteria homeLoanCriteria = loanEligibilityCriteriaRepository
						.getHomeLoanBySalarySlab(homeLoanRequest.getIncome(), homeLoanRequest.getEmploymentType(),
								Integer.class.cast(bankResponse.getId()));
				if (homeLoanCriteria == null)
					continue;

				logger.info("homeLoanCriteria==>" + homeLoanCriteria.toString());
				logger.info("homeLoanCriteria.getFoir()==>" + homeLoanCriteria.getFoir());
				logger.info("Before Income==>" + homeLoanRequest.getIncome());
				double income = homeLoanRequest.getIncome() * homeLoanCriteria.getFoir() / 100;
				logger.info("homeLoanRequest.getObligation()==>" + homeLoanRequest.getObligation());
				if (!CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getObligation())) {
					income = income - homeLoanRequest.getObligation();
				}
				logger.info("Before After==>" + income);

				if (income <= 0.0) {
					continue;
				}

				// Maximum Amount Based on Salary and Max ROI
				double monthlyRate = homeLoanCriteria.getRoiLow() / 100 / 12;
				logger.info("monthlyRate first==>" + monthlyRate);
				double totalPayments = tenure * 12;
				double result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result first==>" + result);
				double maximum = getMinMax(income, result);
				logger.info("maximum first==>" + maximum);

				// Minimum Amount Based on Salary and Min ROI
				monthlyRate = homeLoanCriteria.getRoiHigh() / 100 / 12;
				logger.info("monthlyRate second==>" + monthlyRate);
				result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result second==>" + result);
				double minimum = getMinMax(income, result);
				logger.info("minimum second==>" + minimum);
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
			json.put(CommonUtils.MINIMUM,
					Math.abs(Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM))));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM,
					Math.abs(Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM))));
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
				Float marketValue = loanEligibilityCriteriaRepository.getHomeLoanByMV(homeLoanRequest.getMarketValue(),
						Integer.class.cast(bankResponse.getId()));
				Double mv = null;
				Double sv = null;
				logger.info("Request Market Value==>" + homeLoanRequest.getMarketValue());
				logger.info("Request Stamp Value==>" + homeLoanRequest.getStampValue());
				if (marketValue != null && !CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getMarketValue())) {
					logger.info("db Market Value==>" + marketValue);
					mv = (double) (homeLoanRequest.getMarketValue() * marketValue / 100);
				}

				Float stampValue = loanEligibilityCriteriaRepository.getHomeLoanBySV(homeLoanRequest.getStampValue(),
						Integer.class.cast(bankResponse.getId()));
				if (stampValue != null && !CommonUtils.isObjectNullOrEmpty(homeLoanRequest.getStampValue())) {
					logger.info("db Stamp Value==>" + stampValue);
					sv = (double) homeLoanRequest.getStampValue() * stampValue / 100;
				}
				if (mv == null && sv == null) {
					continue;
				}
				logger.info("Result MV==>" + mv);
				logger.info("Result SV==>" + sv);

				logger.info("saleDeedValue==> " + stampValue);
				if (sv == null && mv != null) {
					minData.put(Integer.class.cast(bankResponse.getId()), mv);
				} else if (mv == null && sv != null) {
					minData.put(Integer.class.cast(bankResponse.getId()), sv);
				} else {
					if (sv < mv) {
						minData.put(Integer.class.cast(bankResponse.getId()), sv);
					} else {
						minData.put(Integer.class.cast(bankResponse.getId()), mv);
					}
				}

			}

			CommonDocumentUtils.endHook(logger, "calculateMinSVMVForHomeLoan");
			Map<Integer, JSONObject> minMaxFromSalaryAndMVSV = getMinMaxFromSalaryAndMVSV(minMaxSalary, minData);
			logger.info("minMaxFromSalaryAndMVSV==> " + minMaxFromSalaryAndMVSV.toString());
			List<Double> finalMinList = new ArrayList<>(minMaxFromSalaryAndMVSV.size());
			List<Double> finalMaxList = new ArrayList<>(minMaxFromSalaryAndMVSV.size());
			List<Integer> bankIds = new ArrayList<Integer>(minMaxFromSalaryAndMVSV.size());

			for (Entry<Integer, JSONObject> entry : minMaxFromSalaryAndMVSV.entrySet()) {
				JSONObject json = entry.getValue();
				logger.info("json==>" + json.toJSONString());
				Double min = (Double) json.get(CommonUtils.MINIMUM);
				Double max = (Double) json.get(CommonUtils.MAXIMUM);
				if (max.doubleValue() == min.doubleValue()) {
					min = (min - (min * 10 / 100));
				}
				finalMinList.add(min);
				finalMaxList.add(max);
				bankIds.add(entry.getKey());
			}

			logger.info("finalMaxList==> " + finalMaxList.toString());
			logger.info("finalMinList==> " + finalMinList.toString());

			JSONObject result = new JSONObject();
			if (finalMaxList.isEmpty() && finalMinList.isEmpty()) {
				result.put("message", "No Result Found");
			} else {
				result.put(CommonUtils.MAXIMUM, Math.abs(Math.round(Collections.max(finalMaxList))));
				result.put(CommonUtils.MINIMUM, Math.abs(Math.round(Collections.min(finalMinList))));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForHomeLoan(bankIds);
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put("minRoi", minMaxArr[0]);
					result.put("maxRoi", minMaxArr[1]);
				}
				result.put("fundProivders", bankIds.size());
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

		logger.info("Before Remove minData ==> " + mvsvMap.toString());
		logger.info("Before Remove minMaxSalary ==> " + salaryMap.toString());

		salaryMap.entrySet().removeIf(e -> !mvsvMap.containsKey(e.getKey()));
		mvsvMap.entrySet().removeIf(e -> !salaryMap.containsKey(e.getKey()));

		logger.info("After Remove minData ==> " + mvsvMap.toString());
		logger.info("After Remove minMaxSalary ==> " + salaryMap.toString());

		Map<Integer, JSONObject> finalMap = new HashMap<>(salaryMap.size());
		for (Entry<Integer, JSONObject> entry : salaryMap.entrySet()) {
			JSONObject finaljson = new JSONObject();
			JSONObject json = entry.getValue();
			Double mvsnMin = mvsvMap.get(entry.getKey());
			Double salMinMax = (Double) json.get(CommonUtils.MINIMUM);
			// Setting Minimum
			if (mvsnMin < salMinMax) {
				finaljson.put(CommonUtils.MINIMUM, mvsnMin);
			} else {
				finaljson.put(CommonUtils.MINIMUM, salMinMax);
			}

			// Setting Maximum
			salMinMax = (Double) json.get(CommonUtils.MAXIMUM);
			if (mvsnMin < salMinMax) {
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
			Integer tenure = getUpdatedTenure(eligibilityRequest, CommonUtils.LoanType.PERSONAL_LOAN.getValue());
			if (tenure == null) {
				return null;
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
				logger.info("PersonalLoanEligibilityCriteria==>" + personalLoanCriteria.toString());
				logger.info("personalLoanCriteria.getFoir()==>" + personalLoanCriteria.getFoir());
				logger.info("eligibilityRequest.getObligation()==>" + eligibilityRequest.getObligation());
				logger.info("Before income==>" + eligibilityRequest.getIncome());
				double income = eligibilityRequest.getIncome() * personalLoanCriteria.getFoir() / 100;
				if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getObligation())) {
					income = income - eligibilityRequest.getObligation();
				}
				logger.info("After income==>" + income);
				if (income <= 0.0) {
					continue;
				}
				// Maximum Amount Based on Salary and Max ROI
				logger.info("=========>Bank Name=====>" + bankResponse.getValue());
				double monthlyRate = personalLoanCriteria.getRoiLow() / 100 / 12;
				logger.info("monthlyRate==>" + monthlyRate);
				double totalPayments = tenure * 12;
				logger.info("totalPayments==>" + totalPayments);
				double result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result==>" + result);

				double maximum = getMinMax(income, result);
				logger.info("maximum==>" + maximum);

				// Minimum Amount Based on Salary and Min ROI
				monthlyRate = personalLoanCriteria.getRoiHigh() / 100 / 12;
				logger.info("monthlyRate Sec==>" + monthlyRate);
				result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result Sec==>" + result);
				logger.info("incomeincome==>" + income);
				double minimum = getMinMax(income, result);
				logger.info("minimum ==>" + minimum);
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
			json.put(CommonUtils.MINIMUM,
					Math.abs(Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM))));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM,
					Math.abs(Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM))));
		}

		if (json.isEmpty()) {
			json.put("message", "No Result Found");
		} else {

			// Getting Loan Providers
			List<Integer> bankIds = new ArrayList<>(minMaxData.size());
			for (Entry<Integer, JSONObject> entry : minMaxData.entrySet()) {
				bankIds.add(entry.getKey());
			}
			json.put("fundProivders", bankIds.size());
			Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForPersonalLoan(bankIds,
					eligibilityRequest.getConstitution());
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
			Integer tenure = getUpdatedTenure(eligibilityRequest, CommonUtils.LoanType.LAP_LOAN.getValue());
			if (tenure == null) {
				return null;
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

				logger.info("lapEligibilityCriteria==>" + lapEligibilityCriteria.toString());
				logger.info("Before Income==>" + eligibilityRequest.getIncome());
				logger.info("lapEligibilityCriteria.getFoir()==>" + lapEligibilityCriteria.getFoir());
				logger.info("eligibilityRequest.getObligation()==>" + eligibilityRequest.getObligation());

				double income = eligibilityRequest.getIncome() * lapEligibilityCriteria.getFoir() / 100;
				if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest.getObligation())) {
					income = income - eligibilityRequest.getObligation();
				}
				logger.info("After Income==>" + income);

				if (income <= 0.0) {
					continue;
				}

				// Maximum Amount Based on Salary and Max ROI
				logger.info("Bank Name()==>" + bankResponse.getValue());
				double monthlyRate = lapEligibilityCriteria.getRoiLow() / 100 / 12;
				logger.info("monthlyRate First==>" + monthlyRate);
				double totalPayments = tenure * 12;
				double result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result First==>" + result);
				double maximum = getMinMax(income, result);
				logger.info("maximum==>" + maximum);

				monthlyRate = lapEligibilityCriteria.getRoiHigh() / 100 / 12;
				logger.info("monthlyRate Sec==>" + monthlyRate);
				// Minimum Amount Based on Salary and Min ROI
				result = getPMTCalculation(monthlyRate, totalPayments);
				logger.info("result Sec==>" + result);
				double minimum = getMinMax(income, result);
				logger.info("minimum==>" + minimum);

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
			json.put(CommonUtils.MINIMUM,
					Math.abs(Math.round((Double) minFromMap.getValue().get(CommonUtils.MINIMUM))));
		}
		Entry<Integer, JSONObject> maxFromMap = getMaxFromMap(minMaxData);
		if (maxFromMap != null) {
			json.put(CommonUtils.MAXIMUM,
					Math.abs(Math.round((Double) maxFromMap.getValue().get(CommonUtils.MAXIMUM))));
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
			List<Double> finalMinList = new ArrayList<>(minMaxFromSalaryAndMVSV.size());
			List<Double> finalMaxList = new ArrayList<>(minMaxFromSalaryAndMVSV.size());
			List<Integer> bankIds = new ArrayList<Integer>(minMaxFromSalaryAndMVSV.size());
			for (Entry<Integer, JSONObject> entry : minMaxFromSalaryAndMVSV.entrySet()) {
				JSONObject json = entry.getValue();
				Double min = (Double) json.get(CommonUtils.MINIMUM);
				Double max = (Double) json.get(CommonUtils.MAXIMUM);
				if (max.doubleValue() == min.doubleValue()) {
					min = (min - (min * 10 / 100));
				}
				finalMinList.add(min);
				finalMaxList.add(max);
				bankIds.add(entry.getKey());
			}

			JSONObject result = new JSONObject();
			if (finalMinList.isEmpty() && finalMaxList.isEmpty()) {
				result.put("message", "No Result Found");
			} else {
				result.put(CommonUtils.MAXIMUM, Math.abs(Math.round(Collections.max(finalMaxList))));
				result.put(CommonUtils.MINIMUM, Math.abs(Math.round(Collections.min(finalMinList))));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForLAP(bankIds,
						eligibilityRequest.getEmploymentType(), eligibilityRequest.getPropertyType());
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put("minRoi", minMaxArr[0]);
					result.put("maxRoi", minMaxArr[1]);
				}
				result.put("fundProivders", bankIds.size());
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
				CommonDocumentUtils.endHook(logger, "calculateTenure");
				return (60 - age > 30 ? 30 : 60 - age);
			case LAP_LOAN:
				CommonDocumentUtils.endHook(logger, "calculateTenure");
				return (60 - age > 15 ? 15 : 60 - age);
			case PERSONAL_LOAN:
				CommonDocumentUtils.endHook(logger, "calculateTenure");
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
		logger.info("Min From MAP==>" + data.toString());
		for (Entry<Integer, JSONObject> entry : data.entrySet()) {
			JSONObject json = entry.getValue();
			Double minNew = null;
			Double min = (Double) json.get(CommonUtils.MINIMUM);
			logger.info("min==>" + min);
			if (minEntry != null) {
				minNew = (Double) minEntry.getValue().get(CommonUtils.MINIMUM);
				logger.info("minNew==>" + minNew);
			}
			if (minEntry == null || min < minNew) {
				minEntry = entry;
			}
		}
		return minEntry;
	}

	private static Entry<Integer, JSONObject> getMaxFromMap(Map<Integer, JSONObject> data) {
		Map.Entry<Integer, JSONObject> maxEntry = null;
		logger.info("Max From MAP==>" + data.toString());
		for (Entry<Integer, JSONObject> entry : data.entrySet()) {
			JSONObject json = entry.getValue();
			Double maxNew = null;
			Double max = (Double) json.get(CommonUtils.MAXIMUM);
			logger.info("MAx==>" + max);
			if (maxEntry != null) {
				maxNew = (Double) maxEntry.getValue().get(CommonUtils.MAXIMUM);
				logger.info("maxNew==>" + maxNew);
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
		return (income / perLakhEMI * loanAmount);
	}
	public static Integer getUpdatedTenure(LoanEligibilility request, Integer productId) {
		Integer age = null;
		Integer tenure = request.getTenure();
		if (CommonUtils.isObjectNullOrEmpty(tenure)) {
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			if (age == null || age >= 60) {
				return null;
			}
		} else {
			if (tenure <= 0) {
				return null;
			}
		}
		LoanType type = CommonUtils.LoanType.getType(productId);
		Integer actualTenure = null;
		switch (type) {
		case HOME_LOAN:
			CommonDocumentUtils.endHook(logger, "calculateTenure");
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 30 ? 30 : 60 - age);
			break;
		case LAP_LOAN:
			CommonDocumentUtils.endHook(logger, "calculateTenure");
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 15 ? 15 : 60 - age);
			break;
		case PERSONAL_LOAN:
			CommonDocumentUtils.endHook(logger, "calculateTenure");
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 5 ? 5 : 60 - age);
			break;
		default:
			CommonDocumentUtils.endHook(logger, "calculateTenure");
			break;
		}
		
		if(tenure == null || tenure > actualTenure) {
			tenure = actualTenure;
		}
		return tenure;
	}
	@Override
	public CMADetailResponse getCMADetail(Long applicationId) {
		logger.info("=============Enter in the getCMADetail() =======>" + applicationId);
		CMADetailResponse  cmaDetailResponse=new  CMADetailResponse();
		try {
		logger.info("==================================>1");
			
		List<Object[]> operating=operatingStatementDetailsRepository.getCMADetail(applicationId,"Audited");
		logger.info("==================================>2");
			if(operating!=null) {
				logger.info("==================================>3");
				cmaDetailResponse.setDomesticSales((Double)operating.get(0)[1]);
				logger.info("==================================>4");
				cmaDetailResponse.setExportSales((Double) operating.get(0)[2]);
				logger.info("==================================>5");
				cmaDetailResponse.setNetProfitOrLoss((Double) operating.get(0)[3]);
				logger.info("==================================>6");
				cmaDetailResponse.setDepreciation((Double) operating.get(0)[4]);
				logger.info("==================================>7");
				cmaDetailResponse.setProvisionForDeferredTax((Double)operating.get(0)[5]);
				logger.info("==================================>8");
				logger.info("Successfully get from operating ");
			}
			List<Object[]> liabilitie =liabilitiesDetailsRepository.getCMADetail(applicationId,"Audited");
			logger.info("==================================>9");
			if(liabilitie!=null) {
				logger.info("==================================>10");
				cmaDetailResponse.setSundryCreditors((Double)liabilitie.get(0)[1]);
				logger.info("==================================>11");
				cmaDetailResponse.setAdvancePaymentsFromCustomers((Double)liabilitie.get(0)[2]);
				logger.info("==================================>12");
				logger.info("Successfully get from liabilitie ");
			}
			List<Object[]> asset =assetsDetailsRepository.getCMADetail(applicationId,"Audited");
			logger.info("==================================>13");
			if(asset!=null) {
				logger.info("==================================>14");
				cmaDetailResponse.setReceivableOtherThanDefferred((Double)asset.get(0)[1]);
				logger.info("==================================>15");
				cmaDetailResponse.setExportReceivables((Double)asset.get(0)[2]);
				logger.info("==================================>16");
				cmaDetailResponse.setInventory((Double)asset.get(0)[3]);
				logger.info("==================================>17");
				cmaDetailResponse.setAdvanceToSupplierRawMaterials((Double)asset.get(0)[4]);
				logger.info("==================================>18");
				logger.info("Successfully get from asset ");
			}
			
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.info("-----------------Exception in getCMADetail() -------------------");
		}
		logger.info("-----------------Exit from getCMADetail() -------------------");
		return cmaDetailResponse ;
	}

	// COMMON ENDS

}
