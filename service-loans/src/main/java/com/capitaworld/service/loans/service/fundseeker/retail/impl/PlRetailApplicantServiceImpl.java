package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.Address;
import com.capitaworld.service.loans.model.retail.PLRetailApplicant;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class PlRetailApplicantServiceImpl implements PlRetailApplicantService {
    private static final Logger logger = LoggerFactory.getLogger(PlRetailApplicantServiceImpl.class.getName());

    @Autowired
    private RetailApplicantDetailRepository applicantRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    @Override
    public boolean save(PLRetailApplicant plRetailApplicant, Long userId) throws Exception {
        try {
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(plRetailApplicant.getClientId()) ? userId : plRetailApplicant.getClientId());
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(finalUserId, plRetailApplicant.getApplicationId());
            if (applicantDetail != null) {
                applicantDetail.setModifiedBy(userId);
                applicantDetail.setModifiedDate(new Date());
            } else {
                applicantDetail = new RetailApplicantDetail();
                applicantDetail.setCreatedBy(userId);
                applicantDetail.setCreatedDate(new Date());
                applicantDetail.setIsActive(true);
                applicantDetail.setApplicationId(new LoanApplicationMaster(plRetailApplicant.getApplicationId()));
            }

            BeanUtils.copyProperties(plRetailApplicant, applicantDetail, CommonUtils.IgnorableCopy.RETAIL_FINAL);
            copyAddressFromRequestToDomain(plRetailApplicant, applicantDetail);
            if (plRetailApplicant.getDate() != null && plRetailApplicant.getMonth() != null
                    && plRetailApplicant.getYear() != null) {
                Date birthDate = CommonUtils.getDateByDateMonthYear(plRetailApplicant.getDate(), plRetailApplicant.getMonth(), plRetailApplicant.getYear());
                applicantDetail.setBirthDate(birthDate);
            }
            if (plRetailApplicant.getQualifyingMonth() != null && plRetailApplicant.getQualifyingYear() != null) {
                Date qualifyingYear = CommonUtils.getDateByDateMonthYear(1, plRetailApplicant.getQualifyingMonth(), plRetailApplicant.getQualifyingYear());
                applicantDetail.setQualifyingYear(qualifyingYear);
            }
            if (plRetailApplicant.getBusinessStartMonth() != null && plRetailApplicant.getBusinessStartYear() != null) {
                Date businessStartDate = CommonUtils.getDateByDateMonthYear(1, plRetailApplicant.getBusinessStartMonth(), plRetailApplicant.getBusinessStartYear());
                applicantDetail.setBusinessStartDate(businessStartDate);
            }
            applicantDetail = applicantRepository.save(applicantDetail);
           /* for (CoApplicantRequest request : applicantRequest.getCoApplicants()) {
                coApplicantService.save(request, applicantRequest.getApplicationId(), finalUserId);
            }
            for (GuarantorRequest request : applicantRequest.getGuarantors()) {
                guarantorService.save(request, applicantRequest.getApplicationId(), finalUserId);
            }
*/
            // Updating Flag
            loanApplicationRepository.setIsApplicantProfileMandatoryFilled(plRetailApplicant.getApplicationId(),
                    finalUserId, plRetailApplicant.getIsApplicantDetailsFilled());

            /*// Updating Bowl Count
            loanApplicationRepository.setProfileFilledCount(applicantRequest.getApplicationId(), finalUserId,
                    applicantRequest.getDetailsFilledCount());*/

            return true;

        } catch (Exception e) {
            logger.error("Error while Saving Retail Profile:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public PLRetailApplicant get(Long userId, Long applicationId) throws Exception {
        try {
            RetailApplicantDetail applicantDetail = applicantRepository.getByApplicationAndUserId(userId,
                    applicationId);
            if (applicantDetail == null) {
                PLRetailApplicant request = new PLRetailApplicant();
                LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
                        userId);
                request.setDetailsFilledCount(applicationMaster.getDetailsFilledCount());
                return request;
            }
            PLRetailApplicant applicantRequest = new PLRetailApplicant();
            BeanUtils.copyProperties(applicantDetail, applicantRequest);
            copyAddressFromDomainToRequest(applicantDetail, applicantRequest);
            Integer[] saperatedTime = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getBirthDate());
            applicantRequest.setDate(saperatedTime[0]);
            applicantRequest.setMonth(saperatedTime[1]);
            applicantRequest.setYear(saperatedTime[2]);
            if (applicantDetail.getQualifyingYear() != null) {
                Integer[] saperatedQualifyingYear = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getQualifyingYear());
                applicantRequest.setQualifyingMonth(saperatedQualifyingYear[1]);
                applicantRequest.setQualifyingYear(saperatedQualifyingYear[2]);
            }
            if (applicantDetail.getBusinessStartDate() != null) {
                Integer[] saperatedBusinessStartDate = CommonUtils.saperateDayMonthYearFromDate(applicantDetail.getBusinessStartDate());
                applicantRequest.setBusinessStartMonth(saperatedBusinessStartDate[1]);
                applicantRequest.setBusinessStartYear(saperatedBusinessStartDate[2]);
            }
            applicantRequest.setDetailsFilledCount(applicantDetail.getApplicationId().getDetailsFilledCount());
            return applicantRequest;
        } catch (Exception e) {
            logger.error("Error while Saving Retail Profile:-");
            e.printStackTrace();
            throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
        }
    }


    public static void copyAddressFromRequestToDomain(PLRetailApplicant from, RetailApplicantDetail to) {
        if (from.getFirstAddress() != null) {
            to.setPermanentPremiseNumberName(from.getFirstAddress().getPremiseNumber());
            to.setPermanentStreetName(from.getFirstAddress().getStreetName());
            to.setPermanentLandMark(from.getFirstAddress().getLandMark());
            to.setPermanentCityId(from.getFirstAddress().getCityId());
            to.setPermanentStateId(from.getFirstAddress().getStateId());
            to.setPermanentCountryId(from.getFirstAddress().getCountryId());
            to.setPermanentPincode(from.getFirstAddress().getPincode());
        }

        /*if (from.getAddressSameAs()) {
            if (from.getFirstAddress() != null) {
                to.setOfficePremiseNumberName(from.getFirstAddress().getPremiseNumber());
                to.setOfficeStreetName(from.getFirstAddress().getStreetName());
                to.setOfficeLandMark(from.getFirstAddress().getLandMark());
                to.setOfficeCityId(from.getFirstAddress().getCityId());
                to.setOfficeStateId(from.getFirstAddress().getStateId());
                to.setOfficeCountryId(from.getFirstAddress().getCountryId());
                to.setOfficePincode(from.getFirstAddress().getPincode());
            }
        } else {
            if (from.getSecondAddress() != null) {
                to.setOfficePremiseNumberName(from.getSecondAddress().getPremiseNumber());
                to.setOfficeStreetName(from.getSecondAddress().getStreetName());
                to.setOfficeLandMark(from.getSecondAddress().getLandMark());
                to.setOfficeCityId(from.getSecondAddress().getCityId());
                to.setOfficeStateId(from.getSecondAddress().getStateId());
                to.setOfficeCountryId(from.getSecondAddress().getCountryId());
                to.setOfficePincode(from.getSecondAddress().getPincode());
            }
        }*/
    }

    public static void copyAddressFromDomainToRequest(RetailApplicantDetail from, PLRetailApplicant to) {
        Address address = new Address();
        address.setPremiseNumber(from.getPermanentPremiseNumberName());
        address.setLandMark(from.getPermanentLandMark());
        address.setStreetName(from.getPermanentStreetName());
        address.setCityId(from.getPermanentCityId());
        address.setStateId(from.getPermanentStateId());
        address.setCountryId(from.getPermanentCountryId());
        address.setPincode(from.getPermanentPincode());
        to.setFirstAddress(address);
        if (!CommonUtils.isObjectNullOrEmpty(from.getAddressSameAs())) {
            if (from.getAddressSameAs()) {
                to.setSecondAddress(address);
            } else {
                address = new Address();
                address.setPremiseNumber(from.getOfficePremiseNumberName());
                address.setLandMark(from.getOfficeLandMark());
                address.setStreetName(from.getOfficeStreetName());
                address.setCityId(from.getOfficeCityId());
                address.setStateId(from.getOfficeStateId());
                address.setCountryId(from.getOfficeCountryId());
                address.setPincode(from.getOfficePincode());
                to.setSecondAddress(address);
            }
        }
    }
}
