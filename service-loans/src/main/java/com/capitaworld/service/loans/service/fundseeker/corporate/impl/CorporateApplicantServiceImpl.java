package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FutureFinancialEstimatesDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.common.GraphResponse;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.SubSectorListRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FutureFinancialEstimatesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SectorIndustryMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.SubSectorRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class CorporateApplicantServiceImpl implements CorporateApplicantService {
	private static final Logger logger = LoggerFactory.getLogger(CorporateApplicantService.class.getName());
	@Autowired
	private CorporateApplicantDetailRepository applicantRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private SubSectorRepository subSectorRepository;

	@Autowired
	private SectorIndustryMappingRepository sectorIndustryMappingRepository;

	@Autowired
	private SubSectorMappingRepository subSectorMappingRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private FutureFinancialEstimatesDetailsRepository futureFinancialEstimateDetailsRepository;

	@Override
	public boolean save(CorporateApplicantRequest applicantRequest, Long userId) throws Exception {
		try {
			// application id must not be null
			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(applicantRequest.getClientId()) ? userId
					: applicantRequest.getClientId());
			CorporateApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId,
					applicantRequest.getApplicationId());
			if (applicantDetail != null) {
				// throw new NullPointerException("Applicant ID does not match
				// with the database==> Applicant ID==>"
				// + applicantRequest.getApplicationId() + " and User Id==>" +
				// userId);
				applicantDetail.setModifiedBy(userId);
				applicantDetail.setModifiedDate(new Date());
				// inactive previous before adding new Data
				int updatedRecords = industrySectorRepository
						.inActiveMappingByApplicationId(applicantDetail.getApplicationId().getId());
				logger.info("updated industrySector ==>" + updatedRecords);
				// inactive previous before adding new Data
				int subSecors = subSectorRepository
						.inActiveMappingByApplicationId(applicantDetail.getApplicationId().getId());
				logger.info("updated subSector==>" + subSecors);
			} else {
				applicantDetail = new CorporateApplicantDetail();
				applicantDetail.setCreatedBy(userId);
				applicantDetail.setCreatedDate(new Date());
				applicantDetail.setIsActive(true);
				applicantDetail.setApplicationId(new LoanApplicationMaster(applicantRequest.getApplicationId()));
			}

			BeanUtils.copyProperties(applicantRequest, applicantDetail, CommonUtils.IgnorableCopy.ID);
			applicantDetail.setModifiedBy(userId);
			applicantDetail.setModifiedDate(new Date());
			copyAddressFromRequestToDomain(applicantRequest, applicantDetail);
			applicantDetail = applicantRepository.save(applicantDetail);
			// industry data save
			saveIndustry(applicantDetail.getApplicationId().getId(), applicantRequest.getIndustrylist());
			// Sector data save
			saveSector(applicantDetail.getApplicationId().getId(), applicantRequest.getSectorlist());
			// sub sector save
			saveSubSector(applicantDetail.getApplicationId().getId(), applicantRequest.getSubsectors());

			// Setting Flag to applicantDetailFilled or not
			loanApplicationRepository.setIsApplicantProfileMandatoryFilled(applicantRequest.getApplicationId(),
					finalUserId, CommonUtils.isObjectNullOrEmpty(applicantRequest.getIsApplicantDetailsFilled()) ? false
							: applicantRequest.getIsApplicantDetailsFilled());
			return true;

		} catch (Exception e) {
			logger.error("Error while Saving Corporate Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public CorporateApplicantRequest getCorporateApplicant(Long userId, Long applicationId) throws Exception {
		try {
			// TODO Auto-generated method stub
			CorporateApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
					applicationId);
			if (applicantDetail == null) {
				return null;
			}
			CorporateApplicantRequest applicantRequest = new CorporateApplicantRequest();
			BeanUtils.copyProperties(applicantDetail, applicantRequest);
			copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
			applicantRequest.setIndustrylist(industrySectorRepository.getIndustryByApplicationId(applicationId));
			applicantRequest.setSectorlist(industrySectorRepository.getSectorByApplicationId(applicationId));
			applicantRequest.setSubsectors(subSectorRepository.getSubSectorByApplicationId(applicationId));
			return applicantRequest;
		} catch (Exception e) {
			logger.error("Error while getting Corporate Profile:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	/*@Override
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag) throws Exception {
		try {
			loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicationId, userId, flag);
		} catch (Exception e) {
			logger.error("Error while updating final information flag");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}*/

	private void saveIndustry(Long applicationId, List<Long> industrylist) {
		IndustrySectorDetail industrySectorDetail = null;
		for (Long id : industrylist) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setApplicationId(applicationId);
			industrySectorDetail.setIndustryId(id);
			industrySectorDetail.setCreatedBy(applicationId);
			industrySectorDetail.setModifiedBy(applicationId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSector(Long applicationId, List<Long> sectorlist) {
		// sector data save
		for (Long id : sectorlist) {
			IndustrySectorDetail industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setApplicationId(applicationId);
			industrySectorDetail.setSectorId(id);
			industrySectorDetail.setCreatedBy(applicationId);
			industrySectorDetail.setModifiedBy(applicationId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSubSector(Long applicationId, List<Long> subSectorlist) {
		// sector data save
		for (Long id : subSectorlist) {
			SubsectorDetail subsectorDetail = new SubsectorDetail();
			subsectorDetail.setApplicationId(applicationId);
			subsectorDetail.setSectorSubsectorTransactionId(id);
			subsectorDetail.setCreatedBy(applicationId);
			subsectorDetail.setModifiedBy(applicationId);
			subsectorDetail.setCreatedDate(new Date());
			subsectorDetail.setModifiedDate(new Date());
			subsectorDetail.setIsActive(true);
			// create by and update
			subSectorRepository.save(subsectorDetail);
		}
	}

	private static void copyAddressFromRequestToDomain(CorporateApplicantRequest from, CorporateApplicantDetail to) {
		// Setting Regsiterd Address
		if (from.getFirstAddress() != null) {
			to.setRegisteredPremiseNumber(from.getFirstAddress().getPremiseNumber());
			to.setRegisteredLandMark(from.getFirstAddress().getLandMark());
			to.setRegisteredStreetName(from.getFirstAddress().getStreetName());
			to.setRegisteredPincode(from.getFirstAddress().getPincode());
			to.setRegisteredCityId(from.getFirstAddress().getCityId());
			to.setRegisteredStateId(from.getFirstAddress().getStateId());
			to.setRegisteredCountryId(from.getFirstAddress().getCountryId());
		}

		// Setting Administrative Address
		if (from.getSameAs() != null && from.getSameAs().booleanValue()) {
			if (from.getFirstAddress() != null) {
				to.setAdministrativePremiseNumber(from.getFirstAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getFirstAddress().getLandMark());
				to.setAdministrativeStreetName(from.getFirstAddress().getStreetName());
				to.setAdministrativePincode(from.getFirstAddress().getPincode());
				to.setAdministrativeCityId(from.getFirstAddress().getCityId());
				to.setAdministrativeStateId(from.getFirstAddress().getStateId());
				to.setAdministrativeCountryId(from.getFirstAddress().getCountryId());
			}
		} else {
			if (from.getSecondAddress() != null) {
				to.setAdministrativePremiseNumber(from.getSecondAddress().getPremiseNumber());
				to.setAdministrativeLandMark(from.getSecondAddress().getLandMark());
				to.setAdministrativeStreetName(from.getSecondAddress().getStreetName());
				to.setAdministrativePincode(from.getSecondAddress().getPincode());
				to.setAdministrativeCityId(from.getSecondAddress().getCityId());
				to.setAdministrativeStateId(from.getSecondAddress().getStateId());
				to.setAdministrativeCountryId(from.getSecondAddress().getCountryId());
			}
		}
	}

	private static void copyAddressFromDomainToRequest(CorporateApplicantDetail from, CorporateApplicantRequest to) {
		// Setting Regsiterd Address
		Address address = new Address();

		address.setPremiseNumber(from.getRegisteredPremiseNumber());
		address.setLandMark(from.getRegisteredLandMark());
		address.setStreetName(from.getRegisteredStreetName());
		address.setPincode(from.getRegisteredPincode());
		address.setCityId(from.getRegisteredCityId());
		address.setStateId(from.getRegisteredStateId());
		address.setCountryId(from.getRegisteredCountryId());
		to.setFirstAddress(address);
		if (from.getSameAs() != null && from.getSameAs()) {
			to.setSecondAddress(address);
		} else {
			address = new Address();
			address.setPremiseNumber(from.getAdministrativePremiseNumber());
			address.setLandMark(from.getAdministrativeLandMark());
			address.setStreetName(from.getAdministrativeStreetName());
			address.setPincode(from.getAdministrativePincode());
			address.setCityId(from.getAdministrativeCityId());
			address.setStateId(from.getAdministrativeStateId());
			address.setCountryId(from.getAdministrativeCountryId());
			to.setSecondAddress(address);

		}

		// Setting Administrative Address
	}

	@Override
	public List<Long> getSectorListByIndustryId(List<Long> industryList) throws Exception {
		// TODO Auto-generated method stub
		return sectorIndustryMappingRepository.getSectorListByIndustryList(industryList);
	}

	@Override
	public List<SubSectorListRequest> getSubSectorList(List<Long> list) {
		// TODO Auto-generated method stub
		List<SubSectorListRequest> subSectorListRequests = new ArrayList<SubSectorListRequest>(list.size());
		for (Long id : list) {
			SubSectorListRequest subSectorListRequest = new SubSectorListRequest();
			if (industrySectorRepository.findOneBySectorId(id) != null)
				subSectorListRequest.setIndustryId(industrySectorRepository.findOneBySectorId(id));

			if (sectorIndustryMappingRepository.findIndustryBySectorId(id) != null)
				subSectorListRequest.setIndustryId(sectorIndustryMappingRepository.findIndustryBySectorId(id));

			subSectorListRequest.setSectorId(id);
			subSectorListRequest.setSubSectorIdList(subSectorMappingRepository.getSectorListByIndustryList(id));
			subSectorListRequests.add(subSectorListRequest);

		}
		return subSectorListRequests;
	}

	@Override
	public GraphResponse getGraphs(Long applicationId, Long userId) {
		//For now code has been written as it was in spring old last release. will improve later once i(Akshay) understands how graph data should be.
		
		GraphResponse graphResponse = new GraphResponse();
		
		DecimalFormat decimalFormat = new DecimalFormat("#");
        DecimalFormat decimalFormat1 = new DecimalFormat("#.##");
        
		List<FutureFinancialEstimatesDetail> finEstimates = futureFinancialEstimateDetailsRepository.listFutureFinancialEstimateDetailsFromAppId(applicationId, userId);
		if(CommonUtils.isListNullOrEmpty(finEstimates)){
			graphResponse.setGraphAvailable(false);
			return graphResponse;
		}
		
		List<Double> pats = new ArrayList<>(finEstimates.size());
        List<Double> sales = new ArrayList<>(finEstimates.size());
        List<Double> ebidta = new ArrayList<>(finEstimates.size());
        List<Double> netWorth = new ArrayList<>(finEstimates.size());
        List<Double> currentAsset = new ArrayList<>(finEstimates.size());
        List<Double> currentLiabilities = new ArrayList<>(finEstimates.size());
        List<Double> fixedAsset = new ArrayList<>(finEstimates.size());
        List<Double> debt = new ArrayList<>(finEstimates.size());
        
        List<String> financialYears = new ArrayList<>(finEstimates.size());
        
		for (FutureFinancialEstimatesDetail finEst : finEstimates) {
			financialYears.add(finEst.getFinancialYear());
			
			pats.add(finEst.getPat());
			sales.add(finEst.getSales());
			ebidta.add(finEst.getEbitda());
			netWorth.add(finEst.getNetWorth());
			currentAsset.add(finEst.getCurrentAssets());
			currentLiabilities.add(finEst.getCurrentLiabilities());
			fixedAsset.add(finEst.getFixedAssets());
			debt.add(finEst.getLongTermDebt());
		}
		
		 Double val;
         //calculate pat%
		
	     List<Double> patsPercentage = new ArrayList<>(pats.size());
		 for (int i = 0; i <= pats.size() - 1; i++) {
             //System.out.println(pats.get(i)+"-"+sales.get(i));
             val = (pats.get(i) / sales.get(i));
             val = val * 100;
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             patsPercentage.add(Double.valueOf(decimalFormat.format(val)));
         }
		 
		 List<Double> salesPercentage = new ArrayList<>(sales.size());
         //calculate revenue % (Previous sales/sales)%
         for (int i = 0; i <= (sales.size() - 2); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (sales.get(i + 1) - sales.get(i));
             val = val / sales.get(i);
             val = val * 100;
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             salesPercentage.add(Double.valueOf(decimalFormat.format(val)));
         }
         //calculate Ebidta Percentage (Ebidta/sales)%
         List<Double> ebidtaPercentage = new ArrayList<>(sales.size());
         for (int i = 0; i <= (sales.size() - 1); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (ebidta.get(i) / sales.get(i));
             val = val * 100;
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             ebidtaPercentage.add(Double.valueOf(decimalFormat.format(val)));
         }
         //calculate ROE(%) (pat/netWorth)%
         List<Double> roePercentage = new ArrayList<>(pats.size());
         for (int i = 0; i <= (pats.size() - 1); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (pats.get(i) / netWorth.get(i));
             val = val * 100;
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             roePercentage.add(Double.valueOf(decimalFormat.format(val)));
         }
         //calculate ROCE(%) (EBIDTA/CurrentAssets+FixsedAssets)%
         List<Double> rocePercentage = new ArrayList<>(ebidta.size());
         for (int i = 0; i <= (ebidta.size() - 1); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (ebidta.get(i) / (currentAsset.get(i) + fixedAsset.get(i)));
             val = val * 100;
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             rocePercentage.add(Double.valueOf(decimalFormat.format(val)));
         }

         List<Double> debtEquityPercentage = new ArrayList<>(debt.size());
         for (int i = 0; i <= (debt.size() - 1); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (debt.get(i) / netWorth.get(i));
             if (Double.isNaN(val)) {
                 val = 0d;
             }
             debtEquityPercentage.add(Double.valueOf(decimalFormat1.format(val)));
         }
         
       //calculate current Ration (Current Assets/Current Liabilities)
         List<Double> currentRatio = new ArrayList<>(currentAsset.size());
         for (int i = 0; i <= (currentAsset.size() - 1); i++) {
             //System.out.println(sales.get(i+1)+"-"+sales.get(i));
             val = (currentAsset.get(i) / (currentLiabilities.get(i)));
                 /*if(Double.isNaN(val)){
                     val=0;
                 }*/
             currentRatio.add(Double.valueOf(decimalFormat1.format(val)));
         }

   graphResponse.setxAxisOfPat(financialYears);      
   graphResponse.setPats(pats);
   graphResponse.setSales(sales);
   graphResponse.setEbidta(ebidta);
   graphResponse.setNetWorth(netWorth);
   graphResponse.setCurrentAsset(currentAsset);
   graphResponse.setCurrentLiabilities(currentLiabilities);
   graphResponse.setFixedAsset(fixedAsset);
   graphResponse.setDebt(debt);
   
   graphResponse.setPatsPercentage(patsPercentage);
   graphResponse.setSalesPercentage(salesPercentage);
   graphResponse.setEbidtaPercentage(ebidtaPercentage);
   graphResponse.setRoePercentage(roePercentage);
   graphResponse.setRocePercentage(rocePercentage);
   graphResponse.setCurrentRatio(currentRatio);
   graphResponse.setDebtEquityPercentage(debtEquityPercentage);
   
   return graphResponse;
		
	}
	
}
