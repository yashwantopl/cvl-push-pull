package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FutureFinancialEstimatesDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.FutureFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FutureFinancialEstimatesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FutureFinancialEstimatesDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FutureFinancialEstimatesDetailsServiceImpl implements FutureFinancialEstimatesDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(FutureFinancialEstimatesDetailsServiceImpl.class);

	@Autowired
	private FutureFinancialEstimatesDetailsRepository futureFinancialEstimateDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FutureFinancialEstimatesDetailRequest futureFinancialEstimateDetailRequest = (FutureFinancialEstimatesDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FutureFinancialEstimatesDetailRequest.class);
				FutureFinancialEstimatesDetail futureFinancialEstimateDetail = new FutureFinancialEstimatesDetail();
				BeanUtils.copyProperties(futureFinancialEstimateDetailRequest, futureFinancialEstimateDetail);
				if (futureFinancialEstimateDetailRequest.getId() == null) {
					futureFinancialEstimateDetail.setCreatedBy(frameRequest.getUserId());
					futureFinancialEstimateDetail.setCreatedDate(new Date());
				}
				futureFinancialEstimateDetail
						.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				futureFinancialEstimateDetail.setModifiedBy(frameRequest.getUserId());
				futureFinancialEstimateDetail.setModifiedDate(new Date());
				futureFinancialEstimateDetailsRepository.save(futureFinancialEstimateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save futureFinancialEstimateDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimateDetailsList(Long id,Long userId) throws Exception {
		try {
			List<FutureFinancialEstimatesDetail> futureFinancialEstimateDetails = futureFinancialEstimateDetailsRepository
					.listFutureFinancialEstimateDetailsFromAppId(id,userId);
			List<FutureFinancialEstimatesDetailRequest> futureFinancialEstimateDetailRequests = new ArrayList<FutureFinancialEstimatesDetailRequest>();

			for (FutureFinancialEstimatesDetail detail : futureFinancialEstimateDetails) {
				FutureFinancialEstimatesDetailRequest futureFinancialEstimateDetailRequest = new FutureFinancialEstimatesDetailRequest();
				BeanUtils.copyProperties(detail, futureFinancialEstimateDetailRequest);
				futureFinancialEstimateDetailRequests.add(futureFinancialEstimateDetailRequest);
			}
			return futureFinancialEstimateDetailRequests;
		}

		catch (Exception e) {
			logger.info("Exception  in save futureFinancialEstimateDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<FutureFinancialEstimatesDetailRequest> getFutureEstimateDetailsList(Long userId, Long applicationId)
			throws Exception {
		List<FutureFinancialEstimatesDetail> futureFinancialEstimateDetails = futureFinancialEstimateDetailsRepository
				.listFutureFinancialEstimateDetailsFromAppId(applicationId,userId);
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		LoanType loanType = CommonUtils.LoanType.getType(applicationMaster.getProductId());
		if (loanType == null) {
			Collections.emptyList();
		}
		switch (loanType) {
		case WORKING_CAPITAL:
			return calucateWCFinancialYear(futureFinancialEstimateDetails);
		case TERM_LOAN:
			return calucateTLFinancialYear(futureFinancialEstimateDetails, applicationMaster, applicationId, userId);
		default:
			break;
		}
		return Collections.emptyList();
	}

	private List<FutureFinancialEstimatesDetailRequest> calucateWCFinancialYear(
			List<FutureFinancialEstimatesDetail> futureFinancialYears) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		Date comparisonDate = null;
		Date todayDate = null;
		

		if (futureFinancialYears != null && futureFinancialYears.size() <= 0) {
			List<FutureFinancialEstimatesDetailRequest> yearList = new ArrayList<FutureFinancialEstimatesDetailRequest>();
			try {
				comparisonDate = dateFormat.parse(currentYear + "-04-30");
				todayDate = dateFormat.parse(dateFormat.format(cal.getTime()));
				if (todayDate.compareTo(comparisonDate) > 0) {
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							cal.get(Calendar.YEAR) + " - " + (cal.get(Calendar.YEAR) + 1)));
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							(cal.get(Calendar.YEAR) + 1) + " - " + (cal.get(Calendar.YEAR) + 2)));
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							(cal.get(Calendar.YEAR) + 2) + " - " + (cal.get(Calendar.YEAR) + 3)));
				} else {
					int currentYearForBeforeDate = cal.get(Calendar.YEAR) - 1;
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							(currentYearForBeforeDate) + " - " + (currentYearForBeforeDate + 1)));
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							(currentYearForBeforeDate + 1) + " - " + (currentYearForBeforeDate + 2)));
					yearList.add(new FutureFinancialEstimatesDetailRequest(
							(currentYearForBeforeDate + 2) + " - " + (currentYearForBeforeDate + 3)));
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return yearList;
		} else {
			return getRequestFromDomain(futureFinancialYears);
		}

	}

	private List<FutureFinancialEstimatesDetailRequest> calucateTLFinancialYear(
			List<FutureFinancialEstimatesDetail> futureFinancialYears, LoanApplicationMaster applicationMaster,
			Long applicationId, Long userId) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		Date comparisonDate = null;
		Date todayDate = null;
		int tenure = 0;
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getTenure())) {
			tenure = 0;
		} else {
			tenure = (int) Math.ceil(applicationMaster.getTenure() / 12);
		}
		if (futureFinancialYears.size() <= 0) {
			List<FutureFinancialEstimatesDetailRequest> yearList = new ArrayList<FutureFinancialEstimatesDetailRequest>();
			try {
				comparisonDate = dateFormat.parse(currentYear + "-04-30");
				todayDate = dateFormat.parse(dateFormat.format(cal.getTime()));
				if (todayDate.compareTo(comparisonDate) > 0) {
					int currentYearForAfterDate = cal.get(Calendar.YEAR);
					for (int i = 0; i < tenure; i++) {
						System.out.println(Calendar.YEAR);
						yearList.add(new FutureFinancialEstimatesDetailRequest(
								currentYearForAfterDate + (i) + " - " + (currentYearForAfterDate + (i + 1))));
					}
					return yearList;
				} else {
					int currentYearForBeforeDate = cal.get(Calendar.YEAR) - 1;
					for (int i = 0; i < tenure; i++) {
						yearList.add(new FutureFinancialEstimatesDetailRequest(
								(currentYearForBeforeDate + i) + " - " + (currentYearForBeforeDate + (i + 1))));
					}
					return yearList;
				}

			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			try {
				int recordSize = futureFinancialYears.size();
				if (recordSize == tenure) {
					return getRequestFromDomain(futureFinancialYears);
				} else if (recordSize > tenure) {
					// delete records from db
					for (int i = tenure; i < recordSize; i++) {
						futureFinancialEstimateDetailsRepository.inactiveByApplicationAndId(applicationId,
								futureFinancialYears.get(i).getId());
						futureFinancialYears.remove(i);
						recordSize = futureFinancialYears.size(); 
					}
					return getRequestFromDomain(futureFinancialYears);
				} else {
					// add dynamic records to db
					String recordYearsArray[] = futureFinancialYears.get((recordSize - 1)).getFinancialYear().split("-");
					int lastYear = Integer.parseInt(recordYearsArray[1].trim());
					int count = 0;
					for (int i = recordSize; i < tenure; i++) {
						FutureFinancialEstimatesDetail financialEstimatesFuture = new FutureFinancialEstimatesDetail();
						financialEstimatesFuture.setFinancialYear((lastYear + count) + " - " + (lastYear + count + 1));
						financialEstimatesFuture.setApplicationId(new LoanApplicationMaster(applicationId));
						financialEstimatesFuture.setCreatedBy(userId);
						financialEstimatesFuture.setCreatedDate(new Date());
						financialEstimatesFuture.setModifiedBy(userId);
						financialEstimatesFuture.setModifiedDate(new Date());
						financialEstimatesFuture.setIsActive(true);
						financialEstimatesFuture = futureFinancialEstimateDetailsRepository
								.save(financialEstimatesFuture);
						futureFinancialYears.add(financialEstimatesFuture);
						count++;
					}
					return getRequestFromDomain(futureFinancialYears);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Collections.emptyList();

	}

	private List<FutureFinancialEstimatesDetailRequest> getRequestFromDomain(
			List<FutureFinancialEstimatesDetail> details) {
		List<FutureFinancialEstimatesDetailRequest> response = new ArrayList<FutureFinancialEstimatesDetailRequest>(
				details.size());
		for (FutureFinancialEstimatesDetail detail : details) {
			FutureFinancialEstimatesDetailRequest request = new FutureFinancialEstimatesDetailRequest();
			BeanUtils.copyProperties(detail, request);
			response.add(request);
		}
		return response;
	}

}
