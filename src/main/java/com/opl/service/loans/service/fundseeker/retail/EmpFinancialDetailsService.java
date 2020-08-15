package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.EmpAgriculturistTypeRequest;
import com.opl.mudra.api.loans.model.retail.EmpSalariedTypeRequest;
import com.opl.mudra.api.loans.model.retail.EmpSelfEmployedTypeRequest;

/**
 * @author Sanket
 *
 */
public interface EmpFinancialDetailsService {

	public Boolean saveOrUpdateSalariedEmpDetails(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateAgriculturistEmpDetails(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateSelfEmpDetails(FrameRequest frameRequest) throws LoansException;

	public List<EmpSalariedTypeRequest> getSalariedEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<EmpSalariedTypeRequest> getSalariedEmpFinDetailListByProposalIdCoAppId(Long proposalId, int applicationType,Long coAppId) throws LoansException;

	public List<EmpAgriculturistTypeRequest> getAgriculturistEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<EmpAgriculturistTypeRequest> getAgriculturistEmpFinDetailListByProposalIdAndCoAppId(Long proposalId, int applicationType,Long coAppId) throws LoansException;

	public List<EmpSelfEmployedTypeRequest> getSelfEmpFinDetailListByProposalId(Long proposalId, int applicationType) throws LoansException;

	public List<EmpSelfEmployedTypeRequest> getSelfEmpFinDetailListByProposalIdAndCoAppId(Long proposalId, int applicationType,Long coAppId) throws LoansException;
}
