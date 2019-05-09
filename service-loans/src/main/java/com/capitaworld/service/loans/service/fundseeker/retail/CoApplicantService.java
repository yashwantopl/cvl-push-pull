package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequestOld;
import com.capitaworld.service.loans.model.retail.HLOneformRequest;
import com.capitaworld.service.loans.model.retail.HLOnefromResponse;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;

import java.util.List;

public interface CoApplicantService {
	public boolean save(CoApplicantRequest applicantRequest, Long applicationId, Long userId) throws LoansException;

	public CoApplicantRequest get(Long userId, Long applicationId, Long id) throws LoansException;

	public List<CoApplicantRequest> getList(Long applicationId, Long userId) throws LoansException;

	public boolean saveFinal(FinalCommonRetailRequestOld applicantRequest, Long userId) throws LoansException;

	public FinalCommonRetailRequestOld getFinal(Long userId, Long applicationId, Long id) throws LoansException;

	public List<RetailProfileViewResponse> getCoApplicantPLResponse(Long applicantId, Long userId,int productId) throws LoansException;
	
	public List<Long> getCoAppIds(Long userId, Long applicationId) throws LoansException;
	
	public List<RetailFinalViewCommonResponse> getCoApplicantFinalResponse(Long applicantId, Long userId,int productId) throws LoansException;
	
	public Long getApplicantIdById(Long id) throws LoansException;
	
	public boolean saveITRResponse(RetailApplicantRequest applicantRequest) throws LoansException;
	
	public Boolean updateFlag(Long directorId,Integer apiId,Boolean apiFlag,Long userId);
	
	
}
