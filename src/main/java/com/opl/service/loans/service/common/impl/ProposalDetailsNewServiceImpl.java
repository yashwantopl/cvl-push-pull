package com.opl.service.loans.service.common.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.InEligibleProposalDetailsRequest;
import com.opl.mudra.api.loans.model.ProposalDetailsAdminRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.mudra.api.matchengine.utils.CommonUtils;
import com.opl.mudra.api.matchengine.utils.MatchConstant;
import com.opl.mudra.api.user.model.UserResponse;
import com.opl.mudra.client.notification.NotificationClient;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.service.loans.domain.fundprovider.ProposalDetails;
import com.opl.service.loans.domain.fundprovider.ProposalStatusMaster;
import com.opl.service.loans.repository.common.LoanRepository;
import com.opl.service.loans.repository.fundprovider.NbfcRatioMappingRepository;
import com.opl.service.loans.repository.fundprovider.ProductMasterRepository;
import com.opl.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.opl.service.loans.service.common.IneligibleProposalDetailsService;
import com.opl.service.loans.service.common.ProposalDetailsNewService;

/**
 * @author jaimin.darji
 */
@Service
@Transactional
public class ProposalDetailsNewServiceImpl implements ProposalDetailsNewService {

    private static Logger logger = LoggerFactory.getLogger(ProposalDetailsNewServiceImpl.class);

    @Autowired
    ProposalDetailsRepository proposalDetailsRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    UsersClient usersClient;
    @Autowired
    IneligibleProposalDetailsService ineligibleProposalDetailsService;

    @Autowired
    ProductMasterRepository productDetailsRepository;

    @Autowired
    PrimaryCorporateDetailRepository primaryCorporateDetailRepository;
    @Autowired
    LoanApplicationRepository loanApplicationRepository;

    @Autowired
    NbfcRatioMappingRepository nbfcRatioMappingRepository;

    @Autowired
    private NotificationClient notificationClient;


    @Autowired
    private OneFormClient oneFormClient;

    @Override
    public ProposalDetails saveEligibleProposal(ProposalDetails proposalDetail) {
        return proposalDetailsRepository.save(proposalDetail);
    }

    public ProposalMappingRequest saveProposalOnLoanSlection(ProposalMappingRequest proposalMappingRequest) {
        //proposalDetailsRepository.inActiveByApplicationId(proposalMappingRequest.getApplicationId(), MatchConstant.ProposalStatus.ACCEPT);
        proposalDetailsRepository.deleteByApplicationId(proposalMappingRequest.getApplicationId(), proposalMappingRequest.getFpProductId());
        //proposalDetailsRepository.deleteByApplicationId(proposalMappingRequest.getApplicationId());

        ProposalDetails proposalDetail = new ProposalDetails();
        proposalDetail.setApplicationId(proposalMappingRequest.getApplicationId());
        proposalDetail.setFpProductId(proposalMappingRequest.getFpProductId());
        proposalDetail.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.ACCEPT));
        proposalDetail.setIsProposalAuto(true);
        proposalDetail.setProposalStageId(MatchConstant.ProposalStage.PRIMARY);
        proposalDetail.setInitiatedBy(MatchConstant.UserType.FUNDSEEKER);
        proposalDetail.setLastActionPerformedBy(MatchConstant.UserType.FUNDPROVIDER);
        if (!CommonUtils.isObjectNullOrEmpty(proposalMappingRequest.getExistingLoanAmount()))
            proposalDetail.setExistingLoanAmount(proposalMappingRequest.getExistingLoanAmount());
        if (!CommonUtils.isObjectNullOrEmpty(proposalMappingRequest.getAdditionalLoanAmount()))
            proposalDetail.setAdditionalLoanAmount(proposalMappingRequest.getAdditionalLoanAmount());
        proposalDetail.setElAmount(proposalMappingRequest.getElAmount());
        proposalDetail.setElTenure(proposalMappingRequest.getElTenure());
        proposalDetail.setElRoi(proposalMappingRequest.getElRoi());
        proposalDetail.setEmi(proposalMappingRequest.getEmi());
        proposalDetail.setBranchId(proposalMappingRequest.getBranchId());
        proposalDetail.setProcessingFee(proposalMappingRequest.getProcessingFee());
        proposalDetail.setBranchId(proposalMappingRequest.getBranchId());
        proposalDetail.setMinPf(proposalMappingRequest.getMinPf());
        proposalDetail.setMaxPf(proposalMappingRequest.getMaxPf());
        proposalDetail.setNbfcFlow(proposalMappingRequest.getNbfcFlow());
        if (!CommonUtils.isObjectNullOrEmpty(proposalMappingRequest.getConcessionBasedOnType())) {
            proposalDetail.setConcessionBasedOnType(proposalMappingRequest.getConcessionBasedOnType());
        }
        proposalDetail.setConsessionRoi(proposalMappingRequest.getConsessionRoi());
        proposalDetail.setPfConcession(proposalMappingRequest.getPfConcession());
        proposalDetail.setPfConcessionText(proposalMappingRequest.getPfConcessionText());
        proposalDetail.setSpreadRoi(proposalMappingRequest.getSpreadRoi());
        proposalDetail.setMclrRoi(proposalMappingRequest.getMclrRoi());
        proposalDetail.setScoringModelBasedOn(proposalMappingRequest.getScoringModelBasedOn());
        if (proposalMappingRequest.getFpProductId() == null && proposalMappingRequest.getRatioId() != null) {
            UserResponse userResponse = usersClient.getNBFCOrgIdFromFSUserId(proposalMappingRequest.getUserId());
            Long orgId = (Long) userResponse.getId();
            List<Integer> productIds = nbfcRatioMappingRepository.getIdsByratioId(proposalMappingRequest.getRatioId());
            BigInteger fpProductId = productDetailsRepository.getProductIds(orgId, productIds);
            proposalMappingRequest.setFpProductId(fpProductId.longValue());
            proposalDetail.setFpProductId(fpProductId.longValue());
        }
        Long userOrgId = productDetailsRepository.getOrgIdFromFpMappingId(proposalMappingRequest.getFpProductId());
        if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
            proposalDetail.setUserOrgId(userOrgId.longValue());
            try {
                proposalDetail.setVersion(loanRepository.getVersionFromOrgId(userOrgId.longValue()));
            } catch (Exception e) {
                logger.error("Exception in Getting Version For ORGId=====>{}=====>{}", userOrgId, e);
            }
        }
        proposalDetail.setIsActive(false);
        proposalDetail.setCreatedDate(new Date());
        proposalDetail.setModifiedDate(new Date());
        proposalDetail.setCreatedBy(proposalMappingRequest.getUserId());
        proposalDetail.setModifiedBy(proposalMappingRequest.getUserId());
        proposalDetail.setConnectFlowTypeId(proposalMappingRequest.getConnectFlowTypeId());
        proposalDetail.setIsOffline(false);
        BeanUtils.copyProperties(proposalDetailsRepository.save(proposalDetail), proposalMappingRequest);
        return proposalMappingRequest;
    }

    @Override
    public ProposalMappingRequest getActiveProposalByApplicationId(Long applicationId) {

        logger.info("Inside getActiveProposalByApplicationId");
        List<ProposalDetails> proposalDetails = proposalDetailsRepository.findByApplicationId(applicationId);
        if (!CommonUtils.isListNullOrEmpty(proposalDetails)) {
            ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
            ProposalDetails proposalDetail = proposalDetails.get(proposalDetails.size() - 1);
            BeanUtils.copyProperties(proposalDetail, proposalMappingRequest);
            proposalMappingRequest.setProposalStatusId(proposalDetail.getProposalStatusId().getId());
            return proposalMappingRequest;
        }
        logger.info("Outside getActiveProposalByApplicationId");
        return null;
    }

    /**
     * For InEligible Proposal
     *
     * @param inlPropReq
     * @return
     */
    public Long saveOfflineProposal(InEligibleProposalDetailsRequest inlPropReq) {
        try {
            String gstin = loanRepository.getGSTINByAppId(inlPropReq.getApplicationId());

            ProposalDetails inlProposalDetails = proposalDetailsRepository.findFirstByApplicationIdAndIsActiveOrderByIdDesc(inlPropReq.getApplicationId(), true);
            boolean isCreateNew = false;
            if (!CommonUtils.isObjectNullOrEmpty(inlProposalDetails)) {
                if (!CommonUtils.isObjectNullOrEmpty(inlProposalDetails.getIsSanctioned()) && inlProposalDetails.getIsSanctioned()) {//HANDLE MESSAGE
                    // THIS APPLCATION IS ALREADY SANCTIONED
                    return -1l;
                }
                //IF ALREADY FOUND DATA WITH THIS APPLICATION ID THEN NEED TO COMPARE BANK ID WITH ALREADY EXISTS DATA
                if (inlProposalDetails.getUserOrgId() != inlPropReq.getUserOrgId()) {
                    //IF NOT MATCHED WIH EXSTING BANK DATA THEN CURRENT OBJECT IS INACTIVE AND UPDATE STATUS
                    inlProposalDetails.setIsActive(false);
                    inlProposalDetails.setModifiedBy(inlPropReq.getUserId());
                    inlProposalDetails.setModifiedDate(new Date());
                    inlProposalDetails.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.OTHER_BANK));
                    proposalDetailsRepository.save(inlProposalDetails);
                    isCreateNew = true;
                } else if (inlProposalDetails.getBranchId() != inlPropReq.getBranchId()) {
                    //IF NOT MATCHED WIH EXSTING BRANCH DATA THEN CURRENT OBJECT IS INACTIVE
                    inlProposalDetails.setIsActive(false);
                    inlProposalDetails.setModifiedBy(inlPropReq.getUserId());
                    inlProposalDetails.setModifiedDate(new Date());
                    inlProposalDetails.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.OTHER_BRANCH));
                    proposalDetailsRepository.save(inlProposalDetails);
                    isCreateNew = true;
                }
            } else {
                isCreateNew = true;
            }

            try {
                Integer currentAppLoanTypeId = primaryCorporateDetailRepository.getPurposeLoanId(inlPropReq.getApplicationId());
                if (!CommonUtils.isObjectNullOrEmpty(gstin)) {
                    //UPDARE STATUS FOR SAME GSTIN OLD APPLICATIONS
                    List<ProposalDetails> inlProposalList = proposalDetailsRepository.findByGstinPan(gstin.substring(2, 12));
                    for (ProposalDetails inlProposal : inlProposalList) {
                        Integer loanTypeId = primaryCorporateDetailRepository.getPurposeLoanId(inlProposal.getApplicationId());
                        if (loanTypeId == currentAppLoanTypeId) {//CHECK IF SAME LOAN ID THEN WE NEED TO CHECK BELOW CONDITION
                            //CHECK IF SAME BANK PROPOSAL AVAILABLE FOR THIS GSTIN primaryCorporateDetailRepository
                            if (inlProposal.getUserOrgId() == inlPropReq.getUserOrgId()) {
                                // NEED TO CHECK IF ALREADY SANCTIONED OR NOT
                                if (CommonUtils.isObjectNullOrEmpty(inlProposal.getIsSanctioned()) || !inlProposal.getIsSanctioned()) {
                                    // CHECK 60 DAY IN-PRINCIPLE VALIDITY
                                    long dateDiff = daysBetween(new Date(), inlProposal.getCreatedDate());

                                    String value = loanRepository.getCommonPropertiesValue(com.opl.commons.lib.common.CommonUtils.COMMON_PROPERTIES.CONNECT_MSME_INPRINCIPLE_DATE_RANGE);
                                    Integer DAY_DIFFERENCE_FOR_INPRINCIPLE = 0;
                                    if (com.opl.mudra.api.common.CommonUtils.isObjectNullOrEmpty(value)) {//IF NULL IN COMMON PROPERTIES THEN DEFAULT VALUE IS 60 DAYS
                                        DAY_DIFFERENCE_FOR_INPRINCIPLE = 60;
                                    } else {
                                        DAY_DIFFERENCE_FOR_INPRINCIPLE = Integer.valueOf(value);
                                    }
                                    if (dateDiff < DAY_DIFFERENCE_FOR_INPRINCIPLE) {
                                        inlProposal.setIsActive(false);
                                        inlProposal.setModifiedBy(inlPropReq.getUserId());
                                        inlProposal.setModifiedDate(new Date());
                                        inlProposal.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.OTHER_BANK));
                                        proposalDetailsRepository.save(inlProposal);
                                        //isCreateNew = true;
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("Exception while check GSTIN in save ineligible proposal details", e);
            }


            if (isCreateNew) {
                inlProposalDetails = new ProposalDetails();
                inlProposalDetails.setUserOrgId(inlPropReq.getUserOrgId());
                inlProposalDetails.setBranchId(inlPropReq.getBranchId());
                inlProposalDetails.setApplicationId(inlPropReq.getApplicationId());
                inlProposalDetails.setCreatedDate(new Date());
                inlProposalDetails.setCreatedBy(inlPropReq.getUserId());
                inlProposalDetails.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.PENDING));
                inlProposalDetails.setBusinessTypeId(inlPropReq.getBusinessTypeId());
                inlProposalDetails.setIsOffline(true);
                try {
                    //SET GSTIN
                    inlProposalDetails.setGstin(gstin);
                } catch (Exception e) {
                    logger.error(com.opl.mudra.api.common.CommonUtils.EXCEPTION, e);
                }
                inlProposalDetails.setIsActive(true);
            } else {
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getUserOrgId())) {
                    inlProposalDetails.setUserOrgId(inlPropReq.getUserOrgId());
                }
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getBranchId())) {
                    inlProposalDetails.setBranchId(inlPropReq.getBranchId());
                }
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getStatus())) {
                    inlProposalDetails.setProposalStatusId(new ProposalStatusMaster(inlPropReq.getStatus().longValue()));
                }
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getIsDisbursed())) {
                    inlProposalDetails.setIsDisbursed(inlPropReq.getIsDisbursed());
                }
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getIsSanctioned())) {
                    inlProposalDetails.setIsSanctioned(inlPropReq.getIsSanctioned());
                }
                if (!CommonUtils.isObjectNullOrEmpty(inlPropReq.getBusinessTypeId())) {
                    inlProposalDetails.setBusinessTypeId(inlPropReq.getBusinessTypeId());
                }

                try {
                    //SET GSTIN
                    if (CommonUtils.isObjectNullOrEmpty(inlProposalDetails.getGstin())) {
                        inlProposalDetails.setGstin(gstin);
                    }
                } catch (Exception e) {
                    logger.error(CommonUtils.EXCEPTION, e);
                }
                inlProposalDetails.setModifiedDate(new Date());
                inlProposalDetails.setModifiedBy(inlPropReq.getUserId());
            }
            // Set Created Date.

            inlProposalDetails = proposalDetailsRepository.save(inlProposalDetails);
            // Send mail
            Boolean isSendMail = true;
            ProposalMappingRequest proposalDetailsRequest = getActiveProposalByApplicationId(inlPropReq.getApplicationId());
            if (proposalDetailsRequest != null) {
                Boolean isCampaignUser = loanRepository.isCampaignUser(inlPropReq.getApplicationId());
                if (isCampaignUser) {
                    if (com.opl.mudra.api.common.CommonUtils.OfflineApplicationConfig.BankSpecific.ON.equalsIgnoreCase(proposalDetailsRequest.getAddiFields())) {
                        isSendMail = true;
                    } else {
                        isSendMail = false;
                    }
                } else {
                    if (com.opl.mudra.api.common.CommonUtils.OfflineApplicationConfig.MarketPlace.ON.equalsIgnoreCase(proposalDetailsRequest.getAddiFields())) {
                        isSendMail = true;
                    } else {
                        isSendMail = false;
                    }
                }
            }
            Integer fsBusinessType = loanApplicationRepository.findOneBusinessTypeIdByIdAndIsActive(inlPropReq.getApplicationId());
            Boolean isEligible = false;
            if (!CommonUtils.isObjectNullOrEmpty(fsBusinessType) && fsBusinessType == com.opl.mudra.api.common.CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
                /** Trigger mail  to fs and bank branch
                 This email check if the selected bank is (sbi and wc_renewal) or sidbi specific then this email shoot*/
                isEligible = ineligibleProposalDetailsService.sendMailToFsAndBankBranchForSbiBankSpecific(inlPropReq.getApplicationId(),
                        inlPropReq.getBranchId(),inlPropReq.getUserOrgId(), fsBusinessType,false);
            }
            if (!isEligible && (isSendMail != null && isSendMail)) {
                //If users is not from sbi and sidbi specific then this email shoot
                Boolean isSent = ineligibleProposalDetailsService.sendMailToFsAndBankBranch(inlPropReq.getApplicationId(),inlPropReq.getBranchId(),inlPropReq.getUserOrgId());
                if (isSent) {
                    logger.info("Email sent to fs and branch");
                } else {
                    logger.info("Error in sending email to fs and branch");
                }
            }
            // update proposal id in connect
            proposalDetailsRepository.updateProposalId(inlPropReq.getApplicationId(), inlProposalDetails.getId());
            proposalDetailsRepository.updateProposalIdInProfile(inlPropReq.getApplicationId(), inlProposalDetails.getId());
            return inlProposalDetails.getId();
        } catch (Exception e) {
            logger.error("error while saving in eligible proposal : ", e);
        }
        return 0l;
    }

    private static long daysBetween(Date one, Date two) {
        long difference = (one.getTime() - two.getTime()) / 86400000;
        return Math.abs(difference);
    }

    public List<ProposalDetailsAdminRequest> getOfflineProposals(Long userOrgId, ProposalDetailsAdminRequest request) {

        List<Object[]> result = proposalDetailsRepository.getOfflineProposalDetailsByOrgId(userOrgId, request.getFromDate(),request.getToDate());

        List<ProposalDetailsAdminRequest> responseList = new ArrayList<>(result.size());

        for (Object[] obj : result) {
            ProposalDetailsAdminRequest proposal = new ProposalDetailsAdminRequest();
            proposal.setApplicationId(com.opl.mudra.api.common.CommonUtils.convertLong(obj[0]));
            proposal.setUserId(com.opl.mudra.api.common.CommonUtils.convertLong(obj[1]));
            proposal.setUserName(com.opl.mudra.api.common.CommonUtils.convertString(obj[2]));
            proposal.setEmail(com.opl.mudra.api.common.CommonUtils.convertString(obj[3]));
            proposal.setMobile(com.opl.mudra.api.common.CommonUtils.convertString(obj[4]));
            proposal.setCreatedDate(com.opl.mudra.api.common.CommonUtils.convertDate(obj[5]));
            proposal.setBranchId(com.opl.mudra.api.common.CommonUtils.convertLong(obj[6]));
            proposal.setBranchName(com.opl.mudra.api.common.CommonUtils.convertString(obj[7]));
            proposal.setContactPersonName(com.opl.mudra.api.common.CommonUtils.convertString(obj[8]));
            proposal.setTelephoneNo(com.opl.mudra.api.common.CommonUtils.convertString(obj[9]));
            proposal.setContactPersonNumber(com.opl.mudra.api.common.CommonUtils.convertString(obj[10]));
            proposal.setOrganizationName(com.opl.mudra.api.common.CommonUtils.convertString(obj[11]));
            proposal.setApplicationCode(com.opl.mudra.api.common.CommonUtils.convertString(obj[12]));
            proposal.setCode(com.opl.mudra.api.common.CommonUtils.convertString(obj[13]));
            proposal.setStreetName(com.opl.mudra.api.common.CommonUtils.convertString(obj[14]));
            proposal.setState(com.opl.mudra.api.common.CommonUtils.convertString(obj[15]));
            proposal.setCity(com.opl.mudra.api.common.CommonUtils.convertString(obj[16]));
            proposal.setPremisesNo(com.opl.mudra.api.common.CommonUtils.convertString(obj[17]));
            proposal.setContactPersonEmail(com.opl.mudra.api.common.CommonUtils.convertString(obj[18]));

            responseList.add(proposal);
        }

        return responseList;
    }

    public boolean updateTransferBranchDetail(InEligibleProposalDetailsRequest inEliProReq) {
        try {
            //find entity by Id and update branch transfer details
            ProposalDetails proposalDetails = null;
            try {
                proposalDetails = proposalDetailsRepository.findOne(inEliProReq.getIneligibleProposalId());
            } catch (Exception e) {
                logger.error(CommonUtils.EXCEPTION, e);
                return false;
            }
            if (CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
                return false;
            }
            Long branchId = proposalDetails.getBranchId();
            proposalDetails.setBranchId(inEliProReq.getBranchId());
            proposalDetails.setModifiedBy(inEliProReq.getUserId());
            proposalDetails.setModifiedDate(new Date());
            proposalDetailsRepository.save(proposalDetails);
            // Note : save updated branch history in Transfer history table Please do need ful
//			IneligibleProposalTransferHistory proposalTransferHistory = new IneligibleProposalTransferHistory();
//			proposalTransferHistory.setIneligibleProposalid(proposalDetails.getId());
//			proposalTransferHistory.setNewBranchId(inEliProReq.getBranchId());
//			proposalTransferHistory.setOldBranchId(branchId);
//			proposalTransferHistory.setReason(inEliProReq.getReason());
//			proposalTransferHistory.setCreatedBy(inEliProReq.getUserId());
//			proposalTransferHistory.setCreatedDate(new Date());
//			proposalTransferHistory.setApplicationId(proposalDetails.getApplicationId());
//			historyRepository.save(proposalTransferHistory);
            return true;
        } catch (Exception e) {
            logger.error("error while update ineligible proposal : ", e);
        }
        return false;
    }

    public boolean updateReOpenProposalDetail(InEligibleProposalDetailsRequest inEliProReq) {
        //find entity by Id for update details of reopen status
        ProposalDetails proposalDetails = null;
        try {
            proposalDetails = proposalDetailsRepository.findOne(inEliProReq.getIneligibleProposalId());
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION, e);
            return false;
        }
        if (CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
            return false;
        }
        //reopen should be changed as pending status
        proposalDetails.setProposalStatusId(new ProposalStatusMaster(MatchConstant.ProposalStatus.PENDING));
        proposalDetails.setReopenReason(inEliProReq.getReOpenReason());
        proposalDetails.setModifiedBy(inEliProReq.getUserId());
        proposalDetails.setModifiedDate(new Date());
        proposalDetailsRepository.save(proposalDetails);
        return true;
    }

    public boolean updateStatus(InEligibleProposalDetailsRequest inEliProReq) {
        ProposalDetails ineligibleProposalDetails = null;
        try {
            ineligibleProposalDetails = proposalDetailsRepository.findByApplicationIdAndUserOrgIdAndIsActive(inEliProReq.getApplicationId(), inEliProReq.getUserOrgId(), true);
        } catch (Exception e) {
            logger.error(CommonUtils.EXCEPTION, e);
            return false;
        }
        if (CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails)) {
            return false;
        }
        // IF ALREADY DISBURED OR REJECTED THEN RETURN
        if (ineligibleProposalDetails.getProposalStatusId().getId().equals((MatchConstant.ProposalStatus.DECLINE)) ||
                ineligibleProposalDetails.getProposalStatusId().getId().equals((MatchConstant.ProposalStatus.DISBURSED))) {
            return false;
        }
        ineligibleProposalDetails.setProposalStatusId(new ProposalStatusMaster(inEliProReq.getStatus().longValue()));
        ineligibleProposalDetails.setReason(inEliProReq.getReason());
        ineligibleProposalDetails.setModifiedBy(inEliProReq.getUserId());
        ineligibleProposalDetails.setModifiedDate(new Date());
        proposalDetailsRepository.save(ineligibleProposalDetails);


        return true;
    }

    public Boolean checkIsExistOfflineProposalByApplicationId(Long applicationId) {
        return !CommonUtils.isObjectNullOrEmpty(proposalDetailsRepository.getOfflineProposalByApplicationId(applicationId));
    }

}
