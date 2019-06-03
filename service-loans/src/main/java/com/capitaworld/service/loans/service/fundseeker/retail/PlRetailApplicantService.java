package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.retail.BankRelationshipRequest;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
import com.capitaworld.service.loans.model.retail.RetailOnformBasicInfoReq;
import com.capitaworld.service.loans.model.retail.RetailOnformContactInfoReq;
import com.capitaworld.service.loans.model.retail.RetailOnformEmploymentInfoReq;

public interface PlRetailApplicantService {
    public boolean saveProfile(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException;

    public PLRetailApplicantRequest getProfile(Long userId, Long applicationId) throws LoansException;
    
    public PLRetailApplicantRequest getProfileByProposalId(Long userId, Long applicationId, Long proposalId) throws LoansException;

    public PLRetailApplicantRequest getCoAppProfile(Long coAppId);

    public boolean savePrimary(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException;

    public PLRetailApplicantRequest getPrimary(Long userId, Long applicationId) throws LoansException;
    
    public PLRetailApplicantRequest getPrimaryByProposalId(Long userId, Long applicationId, Long proposalId) throws LoansException;

    public boolean saveFinal(RetailFinalInfoRequest applicantRequest, Long userId) throws LoansException;

    public RetailFinalInfoRequest getFinal(Long userId, Long applicationId) throws LoansException;

    public RetailFinalInfoRequest getFinalByProposalId(Long userId, Long applicationId, Long proposalId) throws LoansException;
    
    public Boolean saveBankRelation(Long userId, Long applicationId, BankRelationshipRequest request);
    
    public PLRetailApplicantRequest getRetailBasicDetail(Long userId, Long applicationId) throws LoansException;

    public List<BankRelationshipRequest> getBankRelations(Long applicationId, Long coApplicantId);
    
    public Boolean inactivateBankRelation(Long id, Long userId);
    
    public boolean checkCoAppProfileBeforeSelectHL(Long applicationId);
    
    public RetailOnformBasicInfoReq getOneformBasicInfo(Long applicationId, Long coAppId);
    
    public RetailOnformEmploymentInfoReq getOneformEmploymentInfo(Long applicationId, Long coAppId);
    
    public RetailOnformContactInfoReq getOneformContactInfo(Long applicationId, Long coAppId);
    
    public List<FinancialArrangementsDetailRequest> getOneformCreditInfo(Long applicationId, Long coAppId);
    
    public JSONObject getApplicantAndCoAppOneFormInfo(Long applicationId, Long coAppId);

}
