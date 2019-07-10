package com.capitaworld.service.loans.service.common.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.capitaworld.service.loans.exceptions.LoansException;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.itr.api.model.ITRAdditionalFieldsRequest;
import com.capitaworld.itr.api.model.ITRBasicDetailsResponse;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.dms.util.MultipleJSONObjectHelper;
import com.capitaworld.service.gst.GstCalculation;
import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.gst.client.GstClient;
import com.capitaworld.service.gst.yuva.request.GSTR1Request;
import com.capitaworld.service.loans.domain.common.HomeLoanEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.LAPEligibilityCriteria;
import com.capitaworld.service.loans.domain.common.PersonalLoanEligibilityCriteria;
import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.model.CMADetailResponse;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LoanEligibilility;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
import com.capitaworld.service.loans.repository.common.LoanEligibilityCriteriaRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
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

	private static final String ERROR_WHILE_CALCULATING_HOME_LOAN_ELIGIBILITY_MSG = "Error while Calculating HomeLoan Eligibility : ";
	private static final String MESSAGE_LITERAL = "message";
	private static final String NO_RESULT_FOUND = "No Result Found";
	private static final String MIN_ROI = "minRoi";
	private static final String MAX_ROI = "maxRoi";
	private static final String FUND_PROIVDERS = "fundProivders";
	private static final String CALCULATE_TENURE = "calculateTenure";

	@Autowired
	private LoanEligibilityCriteriaRepository loanEligibilityCriteriaRepository;

	@Autowired
	private OneFormClient oneFormClient;
	
/*	@Autowired 
	private LoansClient loansClient;*/
	
	@Autowired 
	private  GstClient  gstClient;
	
	@Autowired
	private ITRClient itrClient;
	
	@Autowired
	private LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private AssetsDetailsRepository assetsDetailsRepository;

	@Autowired
	private OperatingStatementDetailsRepository operatingStatementDetailsRepository;
	
	@Autowired
    private CorporateApplicantDetailRepository applicantRepository;
	
	// HOME LOAN STARTS
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForHomeLoan(HomeLoanEligibilityRequest homeLoanRequest)
			throws LoansException {
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
				double totalPayments = (tenure * 12);
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
			logger.error(ERROR_WHILE_CALCULATING_HOME_LOAN_ELIGIBILITY_MSG,e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getMinMaxBySalarySlab(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException {
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
	public JSONObject calcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException {
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
				result.put(MESSAGE_LITERAL, NO_RESULT_FOUND);
			} else {
				result.put(CommonUtils.MAXIMUM, Math.abs(Math.round(Collections.max(finalMaxList))));
				result.put(CommonUtils.MINIMUM, Math.abs(Math.round(Collections.min(finalMinList))));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForHomeLoan(bankIds);
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put(MIN_ROI, minMaxArr[0]);
					result.put(MAX_ROI, minMaxArr[1]);
				}
				result.put(FUND_PROIVDERS, bankIds.size());
			}
			return result;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_CALCULATING_HOME_LOAN_ELIGIBILITY_MSG,e);
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
			throws LoansException {
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
				double totalPayments = (tenure * 12);
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
			logger.error("Error while Calculating Personal Loan Eligibility : ",e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcMinMaxForPersonalLoan(PersonalLoanEligibilityRequest eligibilityRequest) throws LoansException {
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
			json.put(MESSAGE_LITERAL, NO_RESULT_FOUND);
		} else {

			// Getting Loan Providers
			List<Integer> bankIds = new ArrayList<>(minMaxData.size());
			for (Entry<Integer, JSONObject> entry : minMaxData.entrySet()) {
				bankIds.add(entry.getKey());
			}
			json.put(FUND_PROIVDERS, bankIds.size());
			Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForPersonalLoan(bankIds,
					eligibilityRequest.getConstitution());
			if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
				json.put(MIN_ROI, minMaxArr[0]);
				json.put(MAX_ROI, minMaxArr[1]);
			}

		}
		CommonDocumentUtils.endHook(logger, "calcMinMaxForPersonalLoan");
		return json;
	}

	// PERSONAL LOAN ENDS

	// LAP Starts
	@SuppressWarnings("unchecked")
	private Map<Integer, JSONObject> calculateMinMaxForLAP(LAPEligibilityRequest eligibilityRequest) throws LoansException {
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
				double totalPayments = (tenure * 12);
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
			logger.error("Error while Calculating LAP Eligibility : ",e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject calcMinMaxForLAP(LAPEligibilityRequest eligibilityRequest) throws LoansException {
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
	public JSONObject calcLAPAmount(LAPEligibilityRequest eligibilityRequest) throws LoansException {
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
				result.put(MESSAGE_LITERAL, NO_RESULT_FOUND);
			} else {
				result.put(CommonUtils.MAXIMUM, Math.abs(Math.round(Collections.max(finalMaxList))));
				result.put(CommonUtils.MINIMUM, Math.abs(Math.round(Collections.min(finalMinList))));
				Object[] minMaxArr = loanEligibilityCriteriaRepository.getMinMaxRoiForLAP(bankIds,
						eligibilityRequest.getEmploymentType(), eligibilityRequest.getPropertyType());
				if (!CommonUtils.isObjectNullOrEmpty(minMaxArr)) {
					result.put(MIN_ROI, minMaxArr[0]);
					result.put(MAX_ROI, minMaxArr[1]);
				}
				result.put(FUND_PROIVDERS, bankIds.size());
			}

			return result;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_CALCULATING_HOME_LOAN_ELIGIBILITY_MSG,e);
		}
		return null;
	}

	// LAP Ends

	// COMMON STARTS
	@Override
	public Integer calculateTenure(LoanEligibilility eligibilility, Integer productId) throws LoansException {
		CommonDocumentUtils.startHook(logger, CALCULATE_TENURE);
		try {
			Integer age = CommonUtils.getAgeFromBirthDate(eligibilility.getDateOfBirth());
			if (age == null || age >= 60 || age == 0) {
				return null;
			}
			LoanType type = CommonUtils.LoanType.getType(productId);
			switch (type) {
			case HOME_LOAN:
				CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
				return (60 - age > 30 ? 30 : 60 - age);
			case LAP_LOAN:
				CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
				return (60 - age > 15 ? 15 : 60 - age);
			case PERSONAL_LOAN:
				CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
				return (60 - age > 5 ? 5 : 60 - age);
			default:
				CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
				return null;
			}
		} catch (Exception e) {
			CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
			logger.error("Error while calulating tenure for Product ==>" + productId + CommonUtils.EXCEPTION + e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
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
			CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 30 ? 30 : 60 - age);
			break;
		case LAP_LOAN:
			CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 15 ? 15 : 60 - age);
			break;
		case PERSONAL_LOAN:
			CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
			age = CommonUtils.getAgeFromBirthDate(request.getDateOfBirth());
			actualTenure = (60 - age > 5 ? 5 : 60 - age);
			break;
		default:
			CommonDocumentUtils.endHook(logger, CALCULATE_TENURE);
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
			
		List<Object[]> operating=operatingStatementDetailsRepository.getCMADetail(applicationId,CommonUtils.AUDITED);
		logger.info("==================================>2");
			if(!CommonUtils.isObjectListNull(operating)) {
				logger.info("==================================>3");
				cmaDetailResponse.setDomesticSales((Double)operating.get(0)[0]);
				logger.info("new added ==================================>19");
				cmaDetailResponse.setInterest((Double)operating.get(0)[1]);
				logger.info("==================================>4");
				cmaDetailResponse.setExportSales((Double) operating.get(0)[2]);
				logger.info("==================================>5");
				cmaDetailResponse.setNetProfitOrLoss((Double) operating.get(0)[3]);
				logger.info("==================================>6");
				cmaDetailResponse.setDepreciation((Double) operating.get(0)[4]);
				logger.info("==================================>7");
				cmaDetailResponse.setProvisionForDeferredTax((Double)operating.get(0)[5]);
				logger.info("==================================>8");
				cmaDetailResponse.setOpProfitBeforeIntrest((Double)operating.get(0)[6]);
				logger.info("==================================>9");
				logger.info("Successfully get from operating ");
			}
			List<Object[]> liabilitie =liabilitiesDetailsRepository.getCMADetail(applicationId,CommonUtils.AUDITED);
			logger.info("==================================>9");
			if(!CommonUtils.isObjectListNull(liabilitie)) {
				logger.info("==================================>10");
				cmaDetailResponse.setSundryCreditors((Double)liabilitie.get(0)[0]);
				logger.info("==================================>11");
				cmaDetailResponse.setAdvancePaymentsFromCustomers((Double)liabilitie.get(0)[1]);
				logger.info("==================================>12");
				cmaDetailResponse.setSubTotalA((Double)liabilitie.get(0)[2]);
				logger.info("==================================>13");
				cmaDetailResponse.setTotalCurrentLiabilities((Double)liabilitie.get(0)[3]);
				logger.info("==================================>14");
				cmaDetailResponse.setTotalOutsideLiabilities((Double)liabilitie.get(0)[4]);
				logger.info("==================================>15");
				logger.info("Successfully get from liabilitie ");
			}
			List<Object[]> asset =assetsDetailsRepository.getCMADetail(applicationId,CommonUtils.AUDITED);
			logger.info("==================================>15");
			if(!CommonUtils.isObjectListNull(asset)) {
				logger.info("==================================>16");
				cmaDetailResponse.setReceivableOtherThanDefferred((Double)asset.get(0)[0]);
				logger.info("==================================>17");
				cmaDetailResponse.setExportReceivables((Double)asset.get(0)[1]);
				logger.info("==================================>18");
				cmaDetailResponse.setInventory((Double)asset.get(0)[2]);
				logger.info("==================================>19");
				cmaDetailResponse.setAdvanceToSupplierRawMaterials((Double)asset.get(0)[3]);
				logger.info("==================================>20");
				cmaDetailResponse.setGrossBlock((Double)asset.get(0)[4]);
				logger.info("==================================>21");
				cmaDetailResponse.setTotalCurrentAssets((Double)asset.get(0)[5]);
				logger.info("==================================>22");
				cmaDetailResponse.setTangibleNetWorth((Double)asset.get(0)[6]);
				logger.info("==================================>23");
				logger.info("Successfully get from asset ");
			}
			
		} catch (NullPointerException e) {
			logger.error("-----------------Exception in getCMADetail() -------------------",e);
		}
		logger.info("-----------------Exit from getCMADetail() -------------------");
		return cmaDetailResponse ;
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.service.common.LoanEligibilityCalculatorService#getCMADetailApi(java.lang.Long)
	 */
	@Override
	public CMADetailResponse getCMADetailApi(Long applicationId) {
		
		// TODO Auto-generated method stub
		
		logger.info("ENTER IN GET CMA DETAILS FOR SBI RELATED API");
		CMADetailResponse  cmaDetailResponse=new  CMADetailResponse();
		
		try {
		List<OperatingStatementDetails> operating=operatingStatementDetailsRepository.getCMADetailForAPI(applicationId,CommonUtils.AUDITED);  // FOR OPERATING STMT
	//	List<OperatingStatementDetails> operatingMinYear = operatingStatementDetailsRepository.getCMADetailForAPIMaxAndMinYear(applicationId,CommonUtils.AUDITED);  // MIN AND MAX YEAR
		
		List<LiabilitiesDetails> liabilitie =liabilitiesDetailsRepository.getCMADetailForAPI(applicationId,CommonUtils.AUDITED);              // FOR LIBIBILITIES
		List<LiabilitiesDetails> liabilitieMinYear =liabilitiesDetailsRepository.getCMADetailForAPIMinAndMaxYear(applicationId,CommonUtils.AUDITED); // MIN AND MAX YEAR
		
		List<AssetsDetails> asset =assetsDetailsRepository.getCMADetailAPI(applicationId,CommonUtils.AUDITED);     // FOR ASSESTS
		List<AssetsDetails> assetMinYear =assetsDetailsRepository.getCMADetailAPIMinAndMaxYear(applicationId,CommonUtils.AUDITED);    // MIN AND MAX YEAR		
		
			if(!CommonUtils.isObjectListNull(operating)) {
				cmaDetailResponse.setSgAndACosts(operating.get(0).getSellingAndDistributionExpenses()+operating.get(0).getOtherMfgExpenses());  // D54+D56
				cmaDetailResponse.setOpmAndOPNS((CommonUtils.divideNumbers(operating.get(0).getOpProfitAfterInterest() ,operating.get(0).getNetSales()))*100);             //(D64+D15)*100
				cmaDetailResponse.setPbtNetSales((CommonUtils.divideNumbers(operating.get(0).getOpProfitBeforeIntrest() , operating.get(0).getNetSales()))*100);          //(D60/D15)*100
				cmaDetailResponse.setCashAccurals(operating.get(0).getNetProfitOrLoss() + operating.get(0).getDepreciation());                // netProfit loss + Depriciation
				cmaDetailResponse.setPbDIT(operating.get(0).getOpProfitBeforeIntrest() + operating.get(0).getDepreciation());
				Double result  = operating.get(0).getOpProfitBeforeIntrest() + operating.get(0).getDepreciation();
				cmaDetailResponse.setInterestCovrageRatio(CommonUtils.divideNumbers(result,operating.get(0).getInterest())); // (D60+D34)/D62
				Double outStandingAmount = 0.0d; 
				Double result1  = operating.get(0).getOpProfitBeforeIntrest() + operating.get(0).getDepreciation();
				cmaDetailResponse.setDscr((CommonUtils.divideNumbers(result1,outStandingAmount)));                         // CHANGES FOR OUTSTANDING AMOUNT IS REMAINING
				cmaDetailResponse.setRoce((operating.get(0).getOpProfitBeforeIntrest() + operating.get(0).getDepreciation() * 2) / liabilitie.get(0).getOrdinarySharesCapital()+ liabilitie.get(0).getTotalOutsideLiabilities());
				// Interim Financials HOLD
				// Efficiency Ratios HOLD
				cmaDetailResponse.setNetSalesTotalTangible(CommonUtils.divideNumbers(operating.get(0).getNetSales(), asset.get(0).getTotalAssets()) -asset.get(0).getIntangibleAssets());  // remaining for chages
				cmaDetailResponse.setPbtToTotalTangilbeAssets(CommonUtils.divideNumbers(operating.get(0).getOpProfitBeforeIntrest() , asset.get(0).getTotalAssets()) - asset.get(0).getIntangibleAssets());
				cmaDetailResponse.setOperatingCostToSales(operating.get(0).getRawMaterials() + operating.get(0).getPowerAndFuel()+ operating.get(0).getDirectLabour() + operating.get(0).getOtherMfgExpenses()/operating.get(0).getDomesticSales() + operating.get(0).getExportSales());  // (D20+D28+D30+D32)/(D8+D9)
				cmaDetailResponse.setBankFinanceToCurrentAssests((liabilitie.get(0).getShortTermBorrowingFromOthers() + liabilitie.get(0).getShortTermBorrowingFromOthers()+liabilitie.get(0).getTermLoans() / asset.get(0).getTotalCurrentAssets()));
				cmaDetailResponse.setInventoryAndNetSales((CommonUtils.divideNumbers(asset.get(0).getInventory() , operating.get(0).getNetSales())) + asset.get(0).getReceivableOtherThanDefferred() +
						(CommonUtils.divideNumbers(asset.get(0).getExportReceivables() , operating.get(0).getDomesticSales())));  // Done Changes After Development 
				cmaDetailResponse.setInterestCostsOfSales(CommonUtils.divideNumbers(operating.get(0).getInterest() ,operating.get(0).getTotalCostSales()));
				//  BF/Gross sales HOLD
				//Movement of TNW
				cmaDetailResponse.setIncreaseInEquality(liabilitie.get(0).getOrdinarySharesCapital() - liabilitieMinYear.get(0).getOrdinarySharesCapital());
				cmaDetailResponse.setAddSubstractChange(asset.get(0).getIntangibleAssets()- assetMinYear.get(0).getIntangibleAssets());
				//Synopsis of Balance Sheet: Hold 
				cmaDetailResponse.setTlInsRepayable(liabilitie.get(0).getDepositsOrInstalmentsOfTermLoans() + liabilitie.get(0).getShortTermBorrowingFromOthers()); //D29+D17
				cmaDetailResponse.setProvisionOtherCl(liabilitie.get(0).getOtherCurrentLiability()+ liabilitie.get(0).getProvisionalForTaxation() + liabilitie.get(0).getOtherStatutoryLiability() + liabilitie.get(0).getDividendPayable()); //D32+D23+D27+D25
				cmaDetailResponse.setTermLoanOthers(liabilitie.get(0).getTermDeposits() + liabilitie.get(0).getTermLoans()); // D51+D46
				cmaDetailResponse.setUnsecuredLoans(liabilitie.get(0).getTermLoans()+ liabilitie.get(0).getOtherCurrentLiability() + liabilitie.get(0).getOtherCurrentLiability()); // D47+D58+D59   
				cmaDetailResponse.setOtherTermLibilities(liabilitie.get(0).getOtherTermLiabilies()+liabilitie.get(0).getDeferredPaymentsCredits());  // D53+D49
				cmaDetailResponse.setReservesSurplus(liabilitie.get(0).getGeneralReserve()+ liabilitie.get(0).getOtherReservse()+ liabilitie.get(0).getOthers()); // D73+D77+D83 Done
				cmaDetailResponse.setReceivables(asset.get(0).getReceivableOtherThanDefferred() + asset.get(0).getExportReceivables()+ asset.get(0).getInstalmentsDeferred()); // D16+D18+D20
				cmaDetailResponse.setOtherCurrentAssests(asset.get(0).getAdvanceToSupplierRawMaterials() + asset.get(0).getOtherCurrentAssets());  // D37+D39
				cmaDetailResponse.setNetBlock(asset.get(0).getNetBlock()+ asset.get(0).getOtherNcaOtherCapitalWorkInprogress()); // D56+D60
				cmaDetailResponse.setReceivables(asset.get(0).getInvestmentsOrBookDebts());  // D69 Done 
				cmaDetailResponse.setOthers(asset.get(0).getInvestmentsOrBookDebts()+ asset.get(0).getOthers()+ asset.get(0).getOtherNonCurrentAssets()); // D71+D76+D78
				cmaDetailResponse.setcLoans(liabilitie.get(0).getTotalOutsideLiabilities() - liabilitie.get(0).getTotalCurrentLiabilities()); // D63-D37
				cmaDetailResponse.setLongTermUses(asset.get(0).getGrossBlock() - assetMinYear.get(0).getGrossBlock()); // D45-C47
				cmaDetailResponse.setDecreaseInTermLibilities(liabilitie.get(0).getTotalTermLiabilities() - liabilitieMinYear.get(0).getTotalTermLiabilities());// D55-C55
				//Fund Flow Cash_Flow	
				cmaDetailResponse.setIncreaseAndDecreaseInSundry(asset.get(0).getReceivableOtherThanDefferred()+ asset.get(0).getExportReceivables()- assetMinYear.get(0).getReceivableOtherThanDefferred()+ assetMinYear.get(0).getExportReceivables()); // D16+D18-C16+C18
				cmaDetailResponse.setIncreaseAndDecreaseInInventories(asset.get(0).getInventory()- assetMinYear.get(0).getInventory()); // D22-C22
				cmaDetailResponse.setIncreaseAndDecreaseOtherCurrentAssessts(asset.get(0).getInvestments()+asset.get(0).getInstalmentsDeferred()+asset.get(0).getOtherCurrentAssets()- asset.get(0).getInvestments()+ asset.get(0).getInvestments());  // D11+D69-C11+C69+C41
				cmaDetailResponse.setIncreaseAndDecreaseLoansAndAdvan(asset.get(0).getAdvanceToSupplierRawMaterials() + asset.get(0).getAdvancePaymentTaxes()  - assetMinYear.get(0).getAdvanceToSupplierRawMaterials() + assetMinYear.get(0).getAdvancePaymentTaxes() );  //D37+D39-C37+C37  Done
				cmaDetailResponse.setIncreaseAndDecreaseTradeCreaditors(liabilitie.get(0).getSundryCreditors() - liabilitieMinYear.get(0).getSundryCreditors()); //  D19-C19  
				cmaDetailResponse.setPurchaseOfFixedAssests(asset.get(0).getGrossBlock() - assetMinYear.get(0).getGrossBlock()); //   Purchase Of Fixed Assets Remaining for Changes.
				cmaDetailResponse.setSalesOfFixedAssests(asset.get(0).getGrossBlock() - assetMinYear.get(0).getGrossBlock());  //  CHANGES IS REMAINING  (If the figure comes positive above 0 then value should be displayed Or else 0 should be displayed)
				cmaDetailResponse.setIncreaseAndDecreaseInCapital(asset.get(0).getOtherNcaOtherCapitalWorkInprogress()+ assetMinYear.get(0).getOtherNcaOtherCapitalWorkInprogress());   // D60+C60   
				// cmaDetailResponse.setdev    Dividend From Investments-others  CHANGES FOR REMAININIG
				cmaDetailResponse.setIncreaseAndDecreaseInSecuredLoans(liabilitie.get(0).getTermLoans() - liabilitieMinYear.get(0).getTermLoans());  // D46-C46  
				cmaDetailResponse.setIncreaseAndDecreaseInUnsecuredLoans(liabilitie.get(0).getTermLoans() + liabilitie.get(0).getOtherNcl()+ liabilitie.get(0).getOtherNcl() - liabilitieMinYear.get(0).getTermLoans()+ liabilitieMinYear.get(0).getOtherNcl()+liabilitieMinYear.get(0).getOtherNcl());  //D47+D58+D59-C47+C58+C59
				
				cmaDetailResponse.setIncreaseAndDecreaseSecuredWorking(liabilitie.get(0).getShortTermBorrowingFromOthers()- liabilitieMinYear.get(0).getShortTermBorrowingFromOthers());
				cmaDetailResponse.setIncreaseInShareCapital(liabilitie.get(0).getOrdinarySharesCapital()- liabilitieMinYear.get(0).getOrdinarySharesCapital());  // D67-C67 Done 
				cmaDetailResponse.setNetIncreaseInCash(asset.get(0).getCashAndBankBalance()- assetMinYear.get(0).getCashAndBankBalance()); //  D9-C9  Done 
/*				Double projectedSales = 0.0d;
				cmaDetailResponse.setEstimatedSalesCurrentYear(projectedSales);  // REMAINING FOR CHANGES  GST PROJECTED SALES*/
				cmaDetailResponse.setEstimatedAverageReceivable(asset.get(0).getReceivableOtherThanDefferred()+ asset.get(0).getExportReceivables()+asset.get(0).getInstalmentsDeferred());  // D16+D18+D20
				cmaDetailResponse.setLessOtherSourcesLike(liabilitie.get(0).getTermLoans()+ liabilitie.get(0).getOtherNcl()+ liabilitie.get(0).getOtherNcl());   // D47+D58+D59  remaining for Changes
				cmaDetailResponse.setInventoryAndNetSales((CommonUtils.divideNumbers(asset.get(0).getInventory(), operating.get(0).getNetSales())) + asset.get(0).getReceivableOtherThanDefferred() +
						(CommonUtils.divideNumbers(asset.get(0).getExportReceivables(),operating.get(0).getDomesticSales())));  // D22/D15+D16+D18/D8
				cmaDetailResponse.setNetSalesToTotalTangibleAssessts(CommonUtils.divideNumbers((operating.get(0).getNetSales()) ,asset.get(0).getTotalOtherNonCurrentAssets()));  //D15-/D80
				cmaDetailResponse.setWcGap(asset.get(0).getTotalCurrentAssets()- liabilitie.get(0).getOtherCurrentLiability());  // D43-D32
				cmaDetailResponse.setNWC(asset.get(0).getTotalCurrentAssets() - liabilitie.get(0).getTotalCurrentLiabilities()); // D43-D37
				cmaDetailResponse.setNwcAndTCA(asset.get(0).getTotalCurrentAssets() - liabilitie.get(0).getTotalCurrentLiabilities() / asset.get(0).getTotalCurrentAssets());  // D43-D37/D43
				cmaDetailResponse.setCrAndTCA(liabilitie.get(0).getSundryCreditors()/ asset.get(0).getTotalCurrentAssets());  // D19/D43
				cmaDetailResponse.setBfTCA(CommonUtils.divideNumbers(liabilitie.get(0).getShortTermBorrowingFromOthers(),asset.get(0).getTotalCurrentAssets())); // D15/D43
				cmaDetailResponse.setOclTCA(CommonUtils.divideNumbers(liabilitie.get(0).getOtherCurrentLiability(),asset.get(0).getTotalCurrentAssets()));  // D32/D43
				cmaDetailResponse.setOcaAndTCA(asset.get(0).getAdvanceToSupplierRawMaterials()+ asset.get(0).getAdvancePaymentTaxes() + (CommonUtils.divideNumbers(asset.get(0).getOtherCurrentAssets(),asset.get(0).getTotalCurrentAssets()))); // D37+D39+D41/D43

				//ENDS HERE CALCULATION FOR CMA DATA --->
				CorporateApplicantDetail applicantRequest =null;
				applicantRequest = applicantRepository.findByApplicationIdIdAndIsActive(applicationId,true);
				// GETTING DETAILS FOR PROJECTED SALES
				GstResponse calculations =null;
				GSTR1Request gstr1Request = new GSTR1Request();
				if(applicantRequest.getGstIn()!=null){
					gstr1Request.setGstin(applicantRequest.getGstIn());
				}
 				try{
 					calculations = gstClient.getCalculations(gstr1Request);
 				}catch (Exception e) {
 					e.getMessage();
				}
 				
 			// getting gstr3B projected Sales
 				Double projectedSales =0.0d; 
 				if(calculations!=null && calculations.getData()!=null){
 					try {
						@SuppressWarnings("unchecked")
						GstCalculation calculation = (GstCalculation) MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) calculations.getData(), GstCalculation.class);
							if(calculation.getProjectedSales()!=null){
								projectedSales = calculation.getProjectedSales();
									cmaDetailResponse.setEstimatedSalesCurrentYear(projectedSales);
						}
					} catch (IOException e) {
						logger.error("EXCEPTION IS GETTING ------project sales ",e);
					}
 				}
				// ENDS HERE PROJECTED SALES CALCULATION
 				
 
 				List<ITRAdditionalFieldsRequest>  itrResponse = null;
 			
 				ITRAdditionalFieldsRequest itrRequest = new ITRAdditionalFieldsRequest();
 				itrRequest.setApplicationId(applicationId);
 				itrResponse = itrClient.getAdditionalDataFields(itrRequest);
 						
 				if(!CommonUtils.isObjectNullOrEmpty(itrResponse)){
 				for(ITRAdditionalFieldsRequest response  : itrResponse){
 					Integer currentFinyear  = getFinYear(applicationId);
 					logger.info("------------------->"+currentFinyear);
 					if(response.getYear().equals(currentFinyear)){
 		 				cmaDetailResponse.setDividendsFromInvestments(response.getDividendsFromInvestments());
 		 				cmaDetailResponse.setProfitLossOnSaleOfFixedAssets(response.getProfitLossOnSaleOfFixedAssets());
 		 				cmaDetailResponse.setProfitLossOnForexFluctuations(response.getProfitLossOnForexFluctuations());
 		 				cmaDetailResponse.setBadDebtsWrittenOff(response.getBadDebtsWrittenOff());
 						}
 					}
}
			}	
			
			
			
		} catch (NullPointerException e) {
			logger.error("EXCEPTION IS GETTING FOR CMA DATA API======{}===={} ",e);
		}
		logger.info("EXIST FROM GET CMA API DATA ======{}===={}");
		return cmaDetailResponse ;
	}

	private Integer getFinYear(Long applicationId){
			Integer year = 0;
			ITRConnectionResponse itrConnectionResponse = null;
			try {
				itrConnectionResponse = itrClient.getIsUploadAndYearDetails(applicationId);
			}catch (Exception e){
				logger.error("error while calling itr client for getIsUploadAndYearDetails()",e);
			}
			try {
				if(!CommonUtils.isObjectNullOrEmpty(itrConnectionResponse) && !CommonUtils.isObjectNullOrEmpty(itrConnectionResponse == null ? null : itrConnectionResponse.getData())){
					@SuppressWarnings("unchecked")
					Map<String,Object> map = (Map<String,Object>)itrConnectionResponse.getData();
					ITRBasicDetailsResponse res = MultipleJSONObjectHelper.getObjectFromMap(map, ITRBasicDetailsResponse.class);
					if(!CommonUtils.isObjectNullOrEmpty(res) &&!CommonUtils.isObjectNullOrEmpty(res.getYear())){
						year = Integer.parseInt(res.getYear());
					}
				}
			} catch (IOException e) {
				logger.error("error while getting year from itr response {}",e);
			}
			return year;
		}
	
	
	
	
}
