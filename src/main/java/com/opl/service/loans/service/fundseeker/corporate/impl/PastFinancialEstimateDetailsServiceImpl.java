package com.opl.service.loans.service.fundseeker.corporate.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.opl.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.opl.service.loans.domain.fundseeker.corporate.PastFinancialEstimatesDetail;
import com.opl.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PastFinancialEstimateDetailsRepository;
import com.opl.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.opl.service.loans.utils.CommonDocumentUtils;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PastFinancialEstimateDetailsServiceImpl implements PastFinancialEstiamateDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(PastFinancialEstimateDetailsServiceImpl.class.getName());

	private static final String MONTH_03_DAY_31_FORMAT = "-03-31";

	@Autowired
	private OneFormClient oneFormClient; 
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private PastFinancialEstimateDetailsRepository pastFinancialEstimateDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PastFinancialEstimatesDetailRequest pastFinancialEstimateDetailRequest = (PastFinancialEstimatesDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, PastFinancialEstimatesDetailRequest.class);
				PastFinancialEstimatesDetail pastFinancialEstimateDetail = null;

				if (pastFinancialEstimateDetailRequest.getId() != null) {
					pastFinancialEstimateDetail = pastFinancialEstimateDetailsRepository
							.findOne(pastFinancialEstimateDetailRequest.getId());
				} else {
					pastFinancialEstimateDetail = new PastFinancialEstimatesDetail();
					pastFinancialEstimateDetail.setCreatedBy(frameRequest.getUserId());
					pastFinancialEstimateDetail.setCreatedDate(new Date());
				}
				BeanUtils.copyProperties(pastFinancialEstimateDetailRequest, pastFinancialEstimateDetail);
				pastFinancialEstimateDetail
						.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				pastFinancialEstimateDetail.setModifiedBy(frameRequest.getUserId());
				pastFinancialEstimateDetail.setModifiedDate(new Date());
				pastFinancialEstimateDetailsRepository.save(pastFinancialEstimateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save pastFinancialEstimateDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimateDetailsList(Long id) throws LoansException {
		try {
			List<PastFinancialEstimatesDetail> pastFinancialEstimateDetails = pastFinancialEstimateDetailsRepository
					.listPastFinancialEstimateDetailsFromAppId(id);
			List<PastFinancialEstimatesDetailRequest> pastFinancialEstimateDetailRequests = new ArrayList<PastFinancialEstimatesDetailRequest>();

			for (PastFinancialEstimatesDetail detail : pastFinancialEstimateDetails) {
				PastFinancialEstimatesDetailRequest pastFinancialEstimateDetailRequest = new PastFinancialEstimatesDetailRequest();
				BeanUtils.copyProperties(detail, pastFinancialEstimateDetailRequest);
				pastFinancialEstimateDetailRequests.add(pastFinancialEstimateDetailRequest);
			}
			return pastFinancialEstimateDetailRequests;
		} catch (Exception e) {
			logger.error("Exception  in getting pastFinancialEstimateDetail  :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<PastFinancialEstimatesDetailRequest> getFinancialListData(Long userId, Long applicationId)
			throws LoansException {
		List<PastFinancialEstimatesDetail> pastFinancialEstimateDetails = pastFinancialEstimateDetailsRepository
				.listPastFinancialEstimateDetailsFromAppId(applicationId);

		// calculation of year display
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// get current date time with Calendar()
		Calendar cal = Calendar.getInstance();
		int currentYear = cal.get(Calendar.YEAR);
		Date todayDate = null;
		Date establishmentDate = null;
		Date compareDate = null;
		CorporateApplicantDetail detail = corporateApplicantDetailRepository.getByApplicationAndUserId(userId, applicationId);
		if(detail == null){
			return java.util.Collections.emptyList();
		}
		
		Integer establishmentYear = null;
		Integer establishmentMonth = detail.getEstablishmentMonth();
		
		if(!CommonUtils.isObjectNullOrEmpty(detail.getEstablishmentYear())){
			establishmentYear = CommonDocumentUtils.getYear(detail.getEstablishmentYear().longValue(), oneFormClient);
		}
		
		List<PastFinancialEstimatesDetailRequest> yearList = new ArrayList<PastFinancialEstimatesDetailRequest>();
		if (pastFinancialEstimateDetails != null && pastFinancialEstimateDetails.size() <= 0) {
			try {
				todayDate = dateFormat.parse(establishmentYear + MONTH_03_DAY_31_FORMAT);
				establishmentDate = dateFormat.parse(establishmentYear + "-" + establishmentMonth + "-30");
				int differenceOfYears = currentYear - establishmentYear;
				if ((establishmentYear - currentYear) == 0) {
					if (todayDate.compareTo(establishmentDate) > 0) {
						differenceOfYears += 1;
						for (int i = differenceOfYears; i > 0; i--) {
							String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
							yearList.add(new PastFinancialEstimatesDetailRequest(year));
						}
					}
				} else if (differenceOfYears <= 3) {
					if (todayDate.compareTo(establishmentDate) > 0) {
						differenceOfYears += 1;
						for (int i = differenceOfYears; i > 0; i--) {
							String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
							yearList.add(new PastFinancialEstimatesDetailRequest(year));
						}
					} else {
						for (int i = differenceOfYears; i > 0; i--) {
							String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
							yearList.add(new PastFinancialEstimatesDetailRequest(year));
						}
					}
				} else {
					compareDate = dateFormat.parse(cal.get(Calendar.YEAR)+MONTH_03_DAY_31_FORMAT);
					if (new Date().compareTo(compareDate) > 0) {
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 4) + " - " + (currentYear - 3)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 3) + " - " + (currentYear - 2)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 2) + " - " + (currentYear - 1)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 1) + " - " + (currentYear)));
					}else{
						currentYear -= 1;
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 4) + " - " + (currentYear - 3)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 3) + " - " + (currentYear - 2)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 2) + " - " + (currentYear - 1)));
						yearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 1) + " - " + (currentYear)));
					}
				}
	
				
				return yearList;

			} catch (ParseException e) {
				logger.error("Error While getting Year List of past Financial Year : ",e);
				throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
			}
		} else {
			if(pastFinancialEstimateDetails.get(0).getApplicationId().getMcaCompanyId()==null){
			try {
				todayDate = dateFormat.parse(establishmentYear + MONTH_03_DAY_31_FORMAT);
				establishmentDate = dateFormat.parse(establishmentYear + "-" + establishmentMonth + "-30");
				int differenceOfYears = currentYear - establishmentYear;
				if ((establishmentYear - currentYear) == 0) {
					if (todayDate.compareTo(establishmentDate) > 0) {
						differenceOfYears += 1;
					}
				} else if (differenceOfYears <= 3) {
					if (todayDate.compareTo(establishmentDate) > 0) {
						differenceOfYears += 1;
					}
				} else {
					differenceOfYears = 4;
				}

				if (differenceOfYears == pastFinancialEstimateDetails.size()) {
					return getRequestFromDomain(pastFinancialEstimateDetails);
				} else if (differenceOfYears < pastFinancialEstimateDetails.size()) {
					for (int i = 0; i < (pastFinancialEstimateDetails.size()-differenceOfYears); i++) {
						pastFinancialEstimateDetailsRepository.inactiveByApplicationAndId(applicationId,pastFinancialEstimateDetails.get(i).getId());
						pastFinancialEstimateDetails.remove(i);
					}
					return getRequestFromDomain(pastFinancialEstimateDetails);
				} else {
					List<PastFinancialEstimatesDetail> financialEstimatesNewList = new ArrayList<>();
					String[] recordYearsArray = null;
					if(pastFinancialEstimateDetails.get(0)!=null && pastFinancialEstimateDetails.get(0).getFinancialYear()!=null ){
							recordYearsArray = pastFinancialEstimateDetails.get(0).getFinancialYear().toString().split("-");
					}
					int lastYear= 0;
					if(recordYearsArray !=null){
					 lastYear = Integer.parseInt(recordYearsArray[0].trim());
					}
					int yearCount = 0;
					for (int i = pastFinancialEstimateDetails.size(); i < differenceOfYears; i++) {
						PastFinancialEstimatesDetail financialEstimateObject = new PastFinancialEstimatesDetail();
						financialEstimateObject.setApplicationId(new LoanApplicationMaster(applicationId));
						financialEstimateObject.setFinancialYear((lastYear -(yearCount + 1)) + " - " + (lastYear - yearCount));
						financialEstimateObject.setIsActive(true);
						financialEstimateObject.setCreatedBy(userId);
						financialEstimateObject.setCreatedDate(new Date());
						financialEstimateObject.setModifiedBy(userId);
						financialEstimateObject.setModifiedDate(new Date());
						financialEstimatesNewList.add(financialEstimateObject);
						pastFinancialEstimateDetailsRepository.save(financialEstimateObject);
						yearCount++;
					}
					financialEstimatesNewList.addAll(pastFinancialEstimateDetails);
					return getRequestFromDomain(financialEstimatesNewList);
				}

			} catch (ParseException e) {
				logger.error(CommonUtils.EXCEPTION,e);
				throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
			}
		}
			else{
				

				try {
					List<PastFinancialEstimatesDetailRequest> mcaYearList = new ArrayList<PastFinancialEstimatesDetailRequest>();
					todayDate = dateFormat.parse(establishmentYear + MONTH_03_DAY_31_FORMAT);
					establishmentDate = dateFormat.parse(establishmentYear + "-" + establishmentMonth + "-30");
					int differenceOfYears = currentYear - establishmentYear;
					if ((establishmentYear - currentYear) == 0) {
						if (todayDate.compareTo(establishmentDate) > 0) {
							differenceOfYears += 1;
							for (int i = differenceOfYears; i > 0; i--) {
								String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
								mcaYearList.add(new PastFinancialEstimatesDetailRequest(year));
							}
						}
					} else if (differenceOfYears <= 3) {
						if (todayDate.compareTo(establishmentDate) > 0) {
							differenceOfYears += 1;
							for (int i = differenceOfYears; i > 0; i--) {
								String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
								mcaYearList.add(new PastFinancialEstimatesDetailRequest(year));
							}
						} else {
							for (int i = differenceOfYears; i > 0; i--) {
								String year = (cal.get(Calendar.YEAR) - i) + " - " + (cal.get(Calendar.YEAR) - (i - 1));
								mcaYearList.add(new PastFinancialEstimatesDetailRequest(year));
							}
						}
					} else {
						compareDate = dateFormat.parse(cal.get(Calendar.YEAR)+MONTH_03_DAY_31_FORMAT);
						if (new Date().compareTo(compareDate) > 0) {
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 4) + " - " + (currentYear - 3)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 3) + " - " + (currentYear - 2)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 2) + " - " + (currentYear - 1)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 1) + " - " + (currentYear)));
						}else{
							currentYear -= 1;
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 4) + " - " + (currentYear - 3)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 3) + " - " + (currentYear - 2)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 2) + " - " + (currentYear - 1)));
							mcaYearList.add(new PastFinancialEstimatesDetailRequest((currentYear - 1) + " - " + (currentYear)));
						}
					}
					
					List<PastFinancialEstimatesDetailRequest> finalYearList = new ArrayList<PastFinancialEstimatesDetailRequest>();
					for(int j = 0 ; j< mcaYearList.size() ; j++){
						for(PastFinancialEstimatesDetail a : pastFinancialEstimateDetails){
							if((mcaYearList.get(j).getFinancialYear()).equals(a.getFinancialYear())){
								PastFinancialEstimatesDetailRequest b = new PastFinancialEstimatesDetailRequest();
								BeanUtils.copyProperties(a, b);
								finalYearList.add(b);
								break;
							}
						}
						
					}
					
					for(int m =0; m<mcaYearList.size() ; m++){
						boolean isMatch = false;
					for(int k = m ; k < finalYearList.size() ; k++){
							
							logger.info(mcaYearList.get(m).getFinancialYear());
							logger.info(finalYearList.get(k).getFinancialYear());
							if(finalYearList.get(k).getFinancialYear().equals(mcaYearList.get(m).getFinancialYear())){
								isMatch = true;
								break;
							}else if(!finalYearList.get(k).getFinancialYear().equals(mcaYearList.get(m).getFinancialYear()) && !isMatch){
								finalYearList.add(mcaYearList.get(k));
								isMatch = true;
								break;
							}
						}
					int t = finalYearList.size();
					if(t<mcaYearList.size() && !isMatch){
						finalYearList.add(mcaYearList.get(m));
					}
					}
					logger.info(""+finalYearList);
					return finalYearList;
				} catch (ParseException e) {
					logger.error("Error While getting Year List of past Financial Year : ",e);
					throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
				}
			
			}
		}
	}

	private static List<PastFinancialEstimatesDetailRequest> getRequestFromDomain(
			List<PastFinancialEstimatesDetail> data) {
		List<PastFinancialEstimatesDetailRequest> detailRequest = new ArrayList<>(data.size());
		for (PastFinancialEstimatesDetail details : data) {
			PastFinancialEstimatesDetailRequest request = new PastFinancialEstimatesDetailRequest();
			BeanUtils.copyProperties(details, request);
			detailRequest.add(request);
		}
		return detailRequest;

	}

}
