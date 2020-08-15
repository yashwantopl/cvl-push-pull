package com.opl.service.loans.service.fundprovider;

import com.opl.mudra.api.loans.model.common.ReportRequest;

public interface OfflineProcessedAppService {

	public String getApplicationList(ReportRequest reportRequest);

	public String getSanctionedApplicationList(ReportRequest reportRequest);

	public String getDisbursedApplicationList(ReportRequest reportRequest);

	public String getRejectProposalList(ReportRequest reportRequest);

	public String getOtherProposalList(Long userId);
	
	/**
	 * Getting Application List of Uniform Product
	 * @param userId
	 * @return
	 */
	public String getUniformApplicationList(Long userId);
	
	/**
	 * Getting Sanctioned List
	 * @param userId
	 * @return
	 */
	public String getUniformSanctionedApplicationList(Long userId);

	/**
	 * Getting Uniform Disbursed Application List
	 * @param userId
	 * @return
	 */

	public String getUniformDisbursedApplicationList(Long userId);


	/**
	 * Get Rejected Uniform Application
	 * @param userId
	 * @return
	 */
	public String getUniformRejectProposalList(Long userId);


	/**
	 * Getting Uniform Other Application List
	 * @param userId
	 * @return
	 */

	public String getUniformOtherProposalList(Long userId);
}
