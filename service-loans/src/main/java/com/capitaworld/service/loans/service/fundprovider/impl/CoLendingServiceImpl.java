package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.api.workflow.model.WorkflowJobsTrackerRequest;
import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.MultipleJSONObjectHelper;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.connect.api.ConnectRequest;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.domain.fundprovider.CoLendingRatio;
import com.capitaworld.service.loans.domain.fundprovider.FpCoLendingBanks;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.colending.CoLendingProposalResponse;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import com.capitaworld.service.loans.repository.colending.CoLendingFlowRepository;
import com.capitaworld.service.loans.repository.fundprovider.CoLendingRatioRepository;
import com.capitaworld.service.loans.repository.fundprovider.FpCoLendingBanksRepository;
import com.capitaworld.service.loans.repository.fundprovider.NbfcRatioMappingTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.service.fundprovider.CoLendingService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.utils.MatchConstant;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.BranchBasicDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.*;

@Service
@Transactional
public class CoLendingServiceImpl implements CoLendingService {

	
	private static final Logger logger = LoggerFactory.getLogger(CoLendingServiceImpl.class);
	private static final String ERROR_WHILE_FP_BRANCH_DETAILS = "error while FP branch details : ";

	@Autowired
	private FpCoLendingBanksRepository fpCoLendingBanksRepository;
	
	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private CoLendingRatioRepository coLendingRatioRepository;

	@Autowired
	private CoLendingFlowRepository coLendingFlowRepository;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private ApplicationProposalMappingRepository applicationProposalMappingRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
	private NbfcRatioMappingTempRepository nbfcRatioMappingTempRepository;

	DecimalFormat df = new DecimalFormat("#");

	@Override
	public List<FpCoLendingBanks> getBankList() {
		// TODO Auto-generated method stub
		return fpCoLendingBanksRepository.getAllActive();
	}
	
	@Override
	public Boolean 	saveOrUpdate(CoLendingRequest coLendingRequest, Long userOrgId) {
		// TODO Auto-generated method stub

		
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		try {
			
			
			CoLendingRatio coLendingRatio;
			if(CommonUtils.isObjectNullOrEmpty(coLendingRequest.getId()))
			{
				coLendingRatio=new CoLendingRatio();
				BeanUtils.copyProperties(coLendingRequest, coLendingRatio,"createdDate");
				coLendingRatio.setCreatedDate(new Date());
				coLendingRatio.setActive(true);
				coLendingRatio.setUserOrgId(userOrgId);
				
				//set workflow status
				WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
						WorkflowUtils.Workflow.NBFC_LOAN_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
						(CommonUtils.isObjectNullOrEmpty(coLendingRequest.getClientId())
								? coLendingRequest.getUserId() : coLendingRequest.getClientId()));
				
				Long jobId = null;
				if(workflowResponse != null){
					jobId = workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
					coLendingRatio.setJobId(jobId);
				}
				
			}
			else
			{
				coLendingRatio=coLendingRatioRepository.findOne(coLendingRequest.getId());
				BeanUtils.copyProperties(coLendingRequest, coLendingRatio,"createdDate");
			}
			
			coLendingRatioRepository.save(coLendingRatio);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while saving ratio");
			return false;
		}
	}
	
	@Override
	public List<CoLendingRequest> 	listAll(Long userId, Long userOrgId,Long role) {
		// TODO Auto-generated method stub

		
		CommonDocumentUtils.startHook(logger, "listAll");
		try {
			
			List<CoLendingRatio> coLendingRatiosList=new ArrayList<>();
			if(role==WorkflowUtils.Role.ADMIN_CHECKER)
			{
				coLendingRatiosList=coLendingRatioRepository.listAllActiveByBankId(userOrgId);
			}
			else
			{
				coLendingRatiosList=coLendingRatioRepository.listAllActiveByUserId(userOrgId);	
			}
			
			
			List<CoLendingRequest> coLendingRequestList=new ArrayList<>(coLendingRatiosList.size());
			
			for(CoLendingRatio coLendingRatio:coLendingRatiosList)
			{
				CoLendingRequest coLendingRequest2=new CoLendingRequest();
				BeanUtils.copyProperties(coLendingRatio, coLendingRequest2);
				
				//set workflow buttons
				
				 if (!CommonUtils.isObjectNullOrEmpty(coLendingRequest2.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
		             WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(coLendingRequest2.getJobId(),Arrays.asList(role), userId);
		             if (!CommonUtils.isObjectNullOrEmpty(workflowResponse) && !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
		                 try {
		                     WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) workflowResponse.getData(), WorkflowJobsTrackerRequest.class);
		                     if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
		                    	 coLendingRequest2.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
		                     } else {
		                         logger.info("response from workflow NULL jobId = {} and roleId = {}", coLendingRequest2.getJobId(), role);
		                     }
		                 } catch (IOException e) {
		                     logger.error("Error While getting data from workflow {}", e);
		                 }
		             }
		         } else {
		             logger.info("you set jobId or list of roleId NULL for calling workflow");
		         }
				
				coLendingRequestList.add(coLendingRequest2);
			}
			
			
			
			
			
			return coLendingRequestList;
		}

		catch(Exception e)
		{
			logger.error("Error while listing ratio",e);
			return null;
		}
	}

	@Override
	public Boolean clickOnWorkFlowButton(WorkflowData workflowData) {
		// TODO Auto-generated method stub
		try {

			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(workflowData.getJobId());
			request.setUserId(workflowData.getUserId());
			
			
			
		
			if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL) {
				
				//WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED) {
				//change status for activated
				coLendingRatioRepository.updateActivatedRatio(workflowData.getJobId(), true);
				
				
				
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK) {
				
				

			}
			WorkflowResponse workflowResponse = workflowClient.updateJob(request);
			return true;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	@Override
	public Boolean removeCoLendingProposal(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "removeCoLendingProposal");
		try {
			nbfcRatioMappingTempRepository.inActiveTempByRatioId(id);
			coLendingRatioRepository.inActiveRatio(id);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while removeCoLendingProposal",e);
			return false;
		}
	}

	@Override
	public Boolean addReasonByJobId(DataRequest dataRequest) {
		// TODO Auto-generated method stub
		
		CommonDocumentUtils.startHook(logger, "addReasonByJobId");
		try {
			
			coLendingRatioRepository.addReasonByJobId(dataRequest.getValue(),dataRequest.getId());
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while addReasonByJobId",e);
			return false;
		}
		
	}
	
	
	

	@Override
	public List<CoLendingRequest> listByOrgId(Long userOrgId) {
		List<CoLendingRequest> responseList = new ArrayList<>();
		List<BigInteger> bigIntegerList = coLendingFlowRepository.getBankList(userOrgId);
		for (BigInteger bigInteger:bigIntegerList) {
			CoLendingRequest request = new CoLendingRequest();
			request.setBankId(Long.parseLong(bigInteger.toString()));
			responseList.add(request);
		}
		return responseList;
	}

	@Override
	public Boolean inactiveCoLendingProposal(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "inactiveCoLendingProposal");
		try {
			
			coLendingRatioRepository.inActiveRatioAndProposal(id);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while removeCoLendingProposal",e);
			return false;
		}
	}
	
	@Override
	public Boolean activeCoLendingProposal(Long id) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "activeCoLendingProposal");
		try {
			
			coLendingRatioRepository.activeRatioAndProposal(id);
			return true;
		}

		catch(Exception e)
		{
			logger.error("Error while activeCoLendingProposal",e);
			return false;
		}
	}

	@Override
	public JSONObject getFPProposalCount(NhbsApplicationRequest nhbsApplicationRequest, Long npOrgId) {

		Long branchId = null;
		UsersRequest usersRequestForBranch = new UsersRequest();
		usersRequestForBranch.setId(nhbsApplicationRequest.getUserId());
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequestForBranch);
			BranchBasicDetailsRequest branchBasicDetailsRequest = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error(ERROR_WHILE_FP_BRANCH_DETAILS,e);
		}

		JSONObject countObj = new JSONObject();
		List<Long> proposalStatusId = new ArrayList<>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_MAKER == nhbsApplicationRequest.getUserRoleId().intValue()
				|| com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_CHECKER == nhbsApplicationRequest.getUserRoleId().intValue()
				|| com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_ASSISTED_USER == nhbsApplicationRequest.getUserRoleId().intValue()){

			List<BigInteger> newApplicationIdList = null;

			proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
			List<BigInteger> inprincipleProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgId(proposalStatusId,npOrgId,1,branchId);
			proposalStatusId.clear();
			countObj.put("inprincipleProposalCount", inprincipleProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgId(proposalStatusId,npOrgId,1,2,branchId);
			proposalStatusId.clear();
			countObj.put("sanctionProposalCount", sanctionProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdNBFC(proposalStatusId,npOrgId,1,2,branchId);
			proposalStatusId.clear();
			countObj.put("sanctionByNBFCProposalCount", sanctionByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdNBFC(proposalStatusId,npOrgId,1,2,branchId);
			proposalStatusId.clear();
			countObj.put("disbursedByNBFCProposalCount", disbursedByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgId(proposalStatusId,npOrgId,1,2,branchId);
			proposalStatusId.clear();
			countObj.put("disbursedProposalCount", disbursedProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
			List<BigInteger> holdProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,npOrgId,branchId,1,2);
			proposalStatusId.clear();
			countObj.put("holdProposalCount", holdProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
			List<BigInteger> rejectedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,npOrgId,branchId,1,2);
			proposalStatusId.clear();
			countObj.put("rejectedProposalCount", rejectedProposalCountList.size());

		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == nhbsApplicationRequest.getUserRoleId()
				|| com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER == nhbsApplicationRequest.getUserRoleId()){

			proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
			List<BigInteger> inprincipleProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForBank(proposalStatusId,npOrgId,2,1,branchId);
			proposalStatusId.clear();
			countObj.put("inprincipleProposalCount", inprincipleProposalCountList.size());

			//nbfc branchId and nbfc org id required
			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdFNBFCorBank(proposalStatusId.get(0),npOrgId,2,1,branchId);
			proposalStatusId.clear();
			countObj.put("sanctionByNBFCProposalCount", sanctionByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForBank(proposalStatusId,npOrgId,2,1,branchId);
			proposalStatusId.clear();
			countObj.put("sanctionProposalCount", sanctionProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedByNBFCProposalCountList = new ArrayList<>();
			disbursedByNBFCProposalCountList.addAll(proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdFNBFCorBank(proposalStatusId.get(0),npOrgId,2,1,branchId));
			disbursedByNBFCProposalCountList.addAll(proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdFNBFCorBank(proposalStatusId.get(1),npOrgId,2,1,branchId));
			proposalStatusId.clear();
			countObj.put("disbursedByNBFCProposalCount", disbursedByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForBank(proposalStatusId,npOrgId,2,1,branchId);
			proposalStatusId.clear();
			countObj.put("disbursedProposalCount", disbursedProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
			List<BigInteger> holdProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,npOrgId,branchId,2,1);
			proposalStatusId.clear();
			countObj.put("holdProposalCount", holdProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
			List<BigInteger> rejectedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,npOrgId,branchId,2,1);
			proposalStatusId.clear();
			countObj.put("rejectedProposalCount", rejectedProposalCountList.size());

		} else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER == nhbsApplicationRequest.getUserRoleId()){

			proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
			List<BigInteger> inprincipleProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,2);
			proposalStatusId.clear();
			countObj.put("inprincipleProposalCount", inprincipleProposalCountList.size());

			//nbfc branchId and nbfc org id required
			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,2);
			proposalStatusId.clear();
			countObj.put("sanctionByNBFCProposalCount", sanctionByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,2);
			proposalStatusId.clear();
			countObj.put("sanctionProposalCount", sanctionProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,2);
			proposalStatusId.clear();
			countObj.put("disbursedByNBFCProposalCount", disbursedByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,2);
			proposalStatusId.clear();
			countObj.put("disbursedProposalCount", disbursedProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
			List<BigInteger> holdProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,nhbsApplicationRequest.getUserOrgId(),2,1);
			proposalStatusId.clear();
			countObj.put("holdProposalCount", holdProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
			List<BigInteger> rejectedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,nhbsApplicationRequest.getUserOrgId(),2,1);
			proposalStatusId.clear();
			countObj.put("rejectedProposalCount", rejectedProposalCountList.size());

		} else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_HO == nhbsApplicationRequest.getUserRoleId().intValue()){

			proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
			List<BigInteger> inprincipleProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHO(proposalStatusId,npOrgId,1);
			proposalStatusId.clear();
			countObj.put("inprincipleProposalCount", inprincipleProposalCountList.size());

			//nbfc branchId and nbfc org id required
			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHONBFC(proposalStatusId,npOrgId,1,2);
			proposalStatusId.clear();
			countObj.put("sanctionByNBFCProposalCount", sanctionByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
			List<BigInteger> sanctionProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdFoHO(proposalStatusId,npOrgId,1,2);
			proposalStatusId.clear();
			countObj.put("sanctionProposalCount", sanctionProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedByNBFCProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHONBFC(proposalStatusId,npOrgId,1,2);
			proposalStatusId.clear();
			countObj.put("disbursedByNBFCProposalCount", disbursedByNBFCProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
			proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
			List<BigInteger> disbursedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdFoHO(proposalStatusId,npOrgId,1,2);
			proposalStatusId.clear();
			countObj.put("disbursedProposalCount", disbursedProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
			List<BigInteger> holdProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,nhbsApplicationRequest.getUserOrgId(),1,2);
			proposalStatusId.clear();
			countObj.put("holdProposalCount", holdProposalCountList.size());

			proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
			List<BigInteger> rejectedProposalCountList = proposalDetailsRepository.getFPProposalCountByStatusIdAndNBFCFlowType(proposalStatusId,nhbsApplicationRequest.getUserOrgId(),1,2);
			proposalStatusId.clear();
			countObj.put("rejectedProposalCount", rejectedProposalCountList.size());
		}
		logger.info("exit from getFPProposalCount()");
		logger.info(countObj.toJSONString());
		return countObj;
	}

	@Override
	public List<CoLendingProposalResponse> getListOfCheckerProposalsFP(NhbsApplicationRequest request) {
		logger.info("entry in getListOfCheckerProposalsFP()");
		long branchId = 0;
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setId(request.getUserId());
		usersRequest.setUserOrgId(request.getUserOrgId());
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequest);
			BranchBasicDetailsRequest branchBasicDetailsRequest = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error(ERROR_WHILE_FP_BRANCH_DETAILS,e);
		}

		List<BigInteger> proposalIdList = new ArrayList<>();
		List<Long> proposalStatusId = new ArrayList<>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_MAKER == request.getUserRoleId().intValue() ||
				com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_CHECKER == request.getUserRoleId().intValue() ||
				com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_ASSISTED_USER == request.getUserRoleId().intValue()) {

			if (request.getDdrStatusId() == MatchConstant.ProposalStatus.ACCEPT) {
				proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
				proposalIdList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdPageable(proposalStatusId, request.getUserOrgId(), 1, branchId,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdPageable(proposalStatusId,request.getUserOrgId(),1, branchId,2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdPageable(proposalStatusId,request.getUserOrgId(),1, branchId,2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdPageable(proposalStatusId,request.getUserOrgId(),1, branchId,2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdPageable(proposalStatusId,request.getUserOrgId(),1, branchId,2,(request.getPageIndex()*10), request.getSize());

			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.HOLD){
				proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),branchId,1,2,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DECLINE){
				proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),branchId,1,2,(request.getPageIndex()*10), request.getSize());
			} else {
				proposalIdList = null;
			}

		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId().intValue() ||
				com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER== request.getUserRoleId().intValue()) {

			if (request.getDdrStatusId() == MatchConstant.ProposalStatus.ACCEPT) {
				proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
				proposalIdList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdPageable(proposalStatusId, request.getUserOrgId(), 2, 1,branchId,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForBankPageable(proposalStatusId,request.getUserOrgId(),2, branchId,1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdNBFCForBankPageable(proposalStatusId,request.getUserOrgId(),2, branchId,1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForBankPageable(proposalStatusId,request.getUserOrgId(),2, branchId,1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdNBFCForBankPageable(proposalStatusId,request.getUserOrgId(),2, branchId,1,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.HOLD){
				proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(), branchId,2,1,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DECLINE){
				proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(), branchId,2,1,(request.getPageIndex()*10), request.getSize());
			} else {
				proposalIdList = null;
			}

		} else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_HO == request.getUserRoleId().intValue()){
			if (request.getDdrStatusId() == MatchConstant.ProposalStatus.ACCEPT) {
				proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
				proposalIdList = proposalDetailsRepository.getFPProposalCountByStatusIdAndUserOrgIdForHOPageable(proposalStatusId, request.getUserOrgId(), 1, (request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForHOPageable(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdForHOPageable(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForHOPageable(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdForHOPageable(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.HOLD){
				proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DECLINE){
				proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),1, 2,(request.getPageIndex()*10), request.getSize());
			} else {
				proposalIdList = null;
			}
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER == request.getUserRoleId().intValue()){

			if (request.getDdrStatusId() == MatchConstant.ProposalStatus.ACCEPT) {
				proposalStatusId.add(MatchConstant.ProposalStatus.ACCEPT);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForBankHOPageable(proposalStatusId, request.getUserOrgId(), 1,2, (request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForBankHOPageable(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.APPROVED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.APPROVED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdNBFCForBankHOPageable(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalsByStatusIdAndUserOrgIdForBankHOPageable(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			} else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DISBURSED_BY_NBFC){
				proposalStatusId.add(MatchConstant.ProposalStatus.DISBURSED);
				proposalStatusId.add(MatchConstant.ProposalStatus.PARTIALLY_DISBURSED);
				proposalIdList = proposalDetailsRepository.getFPProposalListByStatusIdAndUserOrgIdNBFCForBankHOPageable(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.HOLD){
				proposalStatusId.add(MatchConstant.ProposalStatus.HOLD);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			}else if(request.getDdrStatusId()== MatchConstant.ProposalStatus.DECLINE){
				proposalStatusId.add(MatchConstant.ProposalStatus.DECLINE);
				proposalIdList = proposalDetailsRepository.getFPProposalPageableByStatusIdAndNBFCFlowType(proposalStatusId,request.getUserOrgId(),2, 1,(request.getPageIndex()*10), request.getSize());
			} else {
				proposalIdList = null;
			}

		}
		List<CoLendingProposalResponse> responseList = null;
			if(!CommonUtils.isListNullOrEmpty(proposalIdList)) {
				responseList = new ArrayList<CoLendingProposalResponse>();
				for (BigInteger proposalId : proposalIdList) {
					CoLendingProposalResponse response = new CoLendingProposalResponse();
					ApplicationProposalMapping proposalMapping = applicationProposalMappingRepository.findOne(proposalId.longValue());
					if (!CommonUtils.isObjectNullOrEmpty(proposalMapping)) {
						CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.getByProposalId(proposalMapping.getProposalId());
						ProposalDetails proposalDetail = proposalDetailsRepository.getBranchId(proposalMapping.getApplicationId(), proposalMapping.getProposalId());

						if (!CommonUtils.isObjectNullOrEmpty(proposalMapping)) {


							if(request.getDdrStatusId() == MatchConstant.ProposalStatus.HOLD){
								if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_MAKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_CHECKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_ASSISTED_USER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_HO == request.getUserRoleId().intValue()){
									// NBFC USER
									if(proposalDetail.getProposalStatusId().getId() != MatchConstant.ProposalStatus.HOLD){
										response.setMsg("Hold by bank");
									}else{
										response.setMsg("Hold by yours");
									}
								}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER== request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER== request.getUserRoleId().intValue()){
									// BANK USER
									if(proposalDetail.getProposalStatusId().getId() != MatchConstant.ProposalStatus.HOLD){
										response.setMsg("Hold by NBFC");
									}else{
										response.setMsg("Hold by yours");
									}
								}
							}else if(request.getDdrStatusId() == MatchConstant.ProposalStatus.DECLINE){
								if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_MAKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_FP_CHECKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_ASSISTED_USER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.NBFC_HO == request.getUserRoleId().intValue()){
									// NBFC USER
									if(proposalDetail.getProposalStatusId().getId() != MatchConstant.ProposalStatus.DECLINE){
										response.setMsg("Rejected by bank");
									}else{
										response.setMsg("Rejected by yours");
									}
								}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER== request.getUserRoleId().intValue() ||
										com.capitaworld.service.users.utils.CommonUtils.UserRoles.HEAD_OFFICER== request.getUserRoleId().intValue()){
									// BANK USER
									if(proposalDetail.getProposalStatusId().getId() != MatchConstant.ProposalStatus.DECLINE){
										response.setMsg("Hold by NBFC");
									}else{
										response.setMsg("Hold by yours");
									}
								}
							}

							// set application code into response
							response.setApplicationCode(proposalMapping.getApplicationCode());
							// set application Id into response
							response.setApplicationId(proposalMapping.getApplicationId());
							// set proposal Id into response
							response.setProposalId(proposalMapping.getProposalId());
							// set organization name in response
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
								response.setOrganizationName(corporateApplicantDetail.getOrganisationName());
							}
							// set business type id in response
							response.setBusinessTypeId(proposalMapping.getBusinessTypeId());
							// set product id in response
							response.setProductId(proposalMapping.getProductId());
							// set product id in response
							response.setProductId(proposalMapping.getProductId());
						}
						if (!CommonUtils.isObjectNullOrEmpty(proposalDetail)) {
							// set product id in response
							response.setFpProductId(proposalDetail.getFpProductId());
						}

						//set amount
						String amount = "";
						if (CommonUtils.isObjectNullOrEmpty(proposalMapping.getLoanAmount())) {
							amount += "NA";
						} else {
							amount += df.format(proposalMapping.getLoanAmount());
						}
						response.setAmount(amount);

						//get branch details
						if (!CommonUtils.isObjectNullOrEmpty(proposalDetail) && !CommonUtils.isObjectNullOrEmpty(proposalDetail.getBranchId())) {
							UserResponse userResponse = usersClient.getBranchDetailById(proposalDetail.getBranchId());
							BranchBasicDetailsRequest basicDetailsRequest = null;
							try {
								basicDetailsRequest = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), BranchBasicDetailsRequest.class);
							} catch (IOException e) {
								e.printStackTrace();
							}
							if (basicDetailsRequest != null) {
								// set branch name in response
								response.setBranchName(basicDetailsRequest.getName());
								// set branch code in response
								response.setBranchCode(basicDetailsRequest.getCode());

								// set city state in response
								if (!CommonUtils.isObjectNullOrEmpty(basicDetailsRequest.getCityId())) {
									try {
										response.setBranchCity(CommonDocumentUtils.getCity(Long.valueOf(basicDetailsRequest.getCityId()), oneFormClient));
									} catch (LoansException e) {
										response.setBranchCity("NA");
									}
								} else {
									response.setBranchCity("NA");
								}
								if (!CommonUtils.isObjectNullOrEmpty(basicDetailsRequest.getStateId())) {
									try {
										response.setBranchState(CommonDocumentUtils.getState(Long.valueOf(basicDetailsRequest.getStateId()), oneFormClient));
									} catch (LoansException e) {
										response.setBranchState("NA");
									}
								} else {
									response.setBranchState("NA");
								}
							}
						}
						// set in principle date in response
						try {
							ConnectRequest connectRequest = null;
							ConnectResponse connectResponse = connectClient.getApplicationList(proposalMapping.getApplicationId());
							if (!CommonUtils.isObjectNullOrEmpty(connectResponse) && !CommonUtils.isListNullOrEmpty(connectResponse.getDataList())) {
								List<LinkedHashMap<String, Object>> list = (List<LinkedHashMap<String, Object>>) connectResponse.getDataList();
								for (LinkedHashMap<String, Object> mp : list) {
									connectRequest = com.capitaworld.service.loans.utils.MultipleJSONObjectHelper.getObjectFromMap(mp, ConnectRequest.class);
									if (response.getProposalId().equals(proposalId)) {
										Date inPrincipleDate = connectRequest.getModifiedDate();
										response.setInPrincipleDate(!CommonUtils.isObjectNullOrEmpty(inPrincipleDate) ? CommonUtils.DATE_FORMAT.format(inPrincipleDate) : "-");
									}
								}
							}
						} catch (Exception e2) {
							logger.error(CommonUtils.EXCEPTION, e2);
						}

						//add response to list
						responseList.add(response);
					}
				}
			}

		logger.info("exit from getListOfCheckerProposalsFP()");
		return responseList;
	}

}
