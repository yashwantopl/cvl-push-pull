package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.springframework.http.ResponseEntity;

import com.capitaworld.service.gst.GstResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;

public interface FundSeekerInputRequestService {

    public boolean saveOrUpdate(FundSeekerInputRequestResponse fundSeekerInputRequest) throws Exception;

    public ResponseEntity<LoansResponse> saveOrUpdateDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest);

    public ResponseEntity<LoansResponse> get(FundSeekerInputRequestResponse fundSeekerInputRequest);

    public ResponseEntity<LoansResponse> getDirectorDetail(FundSeekerInputRequestResponse fundSeekerInputRequest);
    
    public LoansResponse callMatchEngineClient(Long applicationId,Long userId,Integer businessTypeId);
    
    
    /**
     * Use to call after Director Details saved.
     * @param ntbRequest
     * @return
     */
    public LoansResponse postDirectorBackground(NTBRequest ntbRequest);

	/**
	 * @param fundSeekerInputRequestResponse
	 * @throws Exception 
	 */
	public LoansResponse invokeFraudAnalytics(FundSeekerInputRequestResponse fundSeekerInputRequestResponse) throws Exception;
	
	/**
	 * Verify GST whether the GSTIN is Registered or Not.
	 * @param gstin
	 * @param applicationId
	 * @return
	 */
	public GstResponse verifyGST(String gstin,Long applicationId);

	
	/**
	 * Getting Data for Uniform Product OneForm
	 * @param applicationId
	 * @return
	 */
	public ResponseEntity<LoansResponse> getDataForOnePagerOneForm(Long applicationId);
	
	
	/**
	 * Update ITR Flag whether its is completed or not
	 * @param applicationId
	 * @param flag
	 * @return
	 */
	public boolean updateITRFlag(Long applicationId,Boolean flag);
}
