package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.EmpAgriculturistTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSalariedTypeRequest;
import com.capitaworld.service.loans.model.retail.EmpSelfEmployedTypeRequest;
import com.capitaworld.service.loans.model.retail.ReferenceRetailDetailsRequest;

import java.util.List;

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
