package com.capitaworld.service.loans.service.colending.impl;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetailsAuditNbfc;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.repository.colending.CoLendingFlowRepository;
import com.capitaworld.service.loans.repository.fundprovider.NbfcProposalBlendedRateRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsAuditNbfcRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.service.colending.CoLendingFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.NbfcClientResponse;
import com.capitaworld.service.users.model.UserResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

import static com.capitaworld.service.loans.utils.CommonUtils.NBFC_BANK_FLOW;
import static com.capitaworld.service.loans.utils.CommonUtils.NBFC_FLOW;


@Service
@Transactional
public class CoLendingFlowServiceFlowServiceImpl implements CoLendingFlowService {

	private static final Logger logger = LoggerFactory.getLogger(CoLendingFlowServiceFlowServiceImpl.class);

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private CoLendingFlowRepository coLendingFlowRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private ProposalDetailsAuditNbfcRepository proposalDetailsAudiNbfcRepository;

	@Autowired
	private NbfcProposalBlendedRateRepository nbfcProposalBlendedRateRepository;

	private static final String ERROR_WHILE_GETTING_CLIENT_LIST = "Error while getting client list.";
	private static final String ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT = "Error while getting NBFC client count.";

	@Override
	public List<ClientListingCoLending> clientListCoLending(int pageIndex,int size,Long npUserId) throws LoansException {
		try {
			UserResponse userResponse = usersClient.getNbfcUserIdClientMappingList(pageIndex,size,npUserId);
			List<Map<String, Object>> nbfcClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			List<ClientListingCoLending> clientListings = new ArrayList<ClientListingCoLending>();
			for (int i = 0; i < nbfcClientResponseList.size(); i++) {
				NbfcClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(nbfcClientResponseList.get(i), NbfcClientResponse.class);
				ClientListingCoLending clientDetailCoLending = new ClientListingCoLending();
				clientDetailCoLending.setClientId(clientResponse.getClientId());
				clientDetailCoLending.setClientName(clientResponse.getClientName());
				clientDetailCoLending.setClientEmail(clientResponse.getClientEmail());
				clientDetailCoLending.setClientMobile(clientResponse.getClientMobile());
				clientDetailCoLending.setLastAccessId(clientResponse.getLastAccessId());
				//get city name
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCity()) && clientResponse.getClientCity() != 0) {
					List<Long> cityList = new ArrayList<>();
					cityList.add((long) clientResponse.getClientCity());
					OneFormResponse cityResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> cityResponseDatalist = (List<Map<String, Object>>) cityResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(cityResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						clientDetailCoLending.setClientCity(masterResponse.getValue());
					} else {
						clientDetailCoLending.setClientCity("NA");
					}
				} else {
					clientDetailCoLending.setClientCity("NA");
				}
				//get state name
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientState())
						&& clientResponse.getClientState() != 0) {
					List<Long> stateList = new ArrayList<>();
					stateList.add((long) clientResponse.getClientState());
					OneFormResponse stateResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> stateResponseDatalist = (List<Map<String, Object>>) stateResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(stateResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						clientDetailCoLending.setClientState(masterResponse.getValue());
					} else {
						clientDetailCoLending.setClientState("NA");
					}
				} else {
					clientDetailCoLending.setClientState("NA");
				}
				Object[] objects = coLendingFlowRepository.getStageAndStatus(clientResponse.getClientId());
				if (objects!=null){
					Integer stage = (Integer) objects[0];
					Integer status = (Integer) objects[1];
					if ((stage == 7 || stage == 9) && status == 3) {
						clientDetailCoLending.setClientStatus("Completed");
					} else if (stage == 4 && status == 3) {
						clientDetailCoLending.setClientStatus("In-Eligible");
					} else {
						clientDetailCoLending.setClientStatus("In-Progress");
					}
				}else{
					clientDetailCoLending.setClientStatus("Journey Pending");
				}

				clientListings.add(clientDetailCoLending);

			}
			return clientListings;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_CLIENT_LIST,e);
			throw new LoansException(ERROR_WHILE_GETTING_CLIENT_LIST);
		}

	}
	
	@Override
	public JSONObject nbfcClientCount(Long nbfcUserId) throws LoansException {
		try {
			UserResponse response = usersClient.getNbfcClientCount(nbfcUserId);
			if(!CommonUtils.isObjectNullOrEmpty(response.getData())){
				return MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)response.getData(), JSONObject.class);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT,e);
			throw new LoansException(ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT);
		}
		return null;
	}

	public Boolean calculateBlendedRateNbfc(Long applicationId) throws LoansException{
		try {
			List<ProposalDetails> proposalDetailsList = proposalDetailsRepository.findByApplicationId(applicationId);
			if(!CommonUtils.isListNullOrEmpty(proposalDetailsList)){
				ProposalDetails proposalDetailsOne = proposalDetailsList.get(0);
				ProposalDetails proposalDetailsTwo = proposalDetailsList.get(1);
				ProposalDetails minLoanAmtProposalObj = new ProposalDetails();
				if(proposalDetailsTwo.getElAmount() == proposalDetailsOne.getElAmount()){
					if(proposalDetailsTwo.getElTenure() == proposalDetailsOne.getElTenure()){
						if(proposalDetailsTwo.getNbfcFlow() == NBFC_BANK_FLOW){
							minLoanAmtProposalObj = proposalDetailsTwo;
						}else {
							minLoanAmtProposalObj = proposalDetailsOne;
						}
					}else if(proposalDetailsTwo.getElTenure() < proposalDetailsOne.getElTenure()){
						minLoanAmtProposalObj = proposalDetailsTwo;
					}else {
						minLoanAmtProposalObj = proposalDetailsOne;
					}
				}else if(proposalDetailsTwo.getElAmount() < proposalDetailsOne.getElAmount()){
					minLoanAmtProposalObj = proposalDetailsTwo;
				}else {
					minLoanAmtProposalObj = proposalDetailsOne;
				}
				/*ProposalDetails minLoanAmtProposalObj = proposalDetailsList
						.stream()
						.min(Comparator.comparing(ProposalDetails::getElAmount))
						.orElseThrow(NoSuchElementException::new);*/
				ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
				BeanUtils.copyProperties(minLoanAmtProposalObj,proposalMappingRequest);
				Object[] ratioValues = coLendingFlowRepository.getRatioNbfcBankProduct(applicationId);
				Double tenure = 0d,nbfcRatio = 0d,bankRatio = 0d;
				if(!CommonUtils.isObjectNullOrEmpty(ratioValues)){
					if(!CommonUtils.isObjectNullOrEmpty(ratioValues[1])){
						tenure = Double.valueOf(ratioValues[1].toString());
					}
					if(!CommonUtils.isObjectNullOrEmpty(ratioValues[2])){
						nbfcRatio = Double.valueOf(ratioValues[2].toString());
					}
					if(!CommonUtils.isObjectNullOrEmpty(ratioValues[3])){
						bankRatio = Double.valueOf(ratioValues[3].toString());
					}
				}
				Long nbfcOrgId = null,bankOrgId = null;
				for (ProposalDetails proposalDetails : proposalDetailsList) {
					if(!CommonUtils.isObjectNullOrEmpty(proposalDetails.getNbfcFlow())){
						Double ratioVal = 0d;
						if(proposalDetails.getNbfcFlow() == NBFC_FLOW){
							ratioVal = nbfcRatio;
							nbfcOrgId = proposalDetails.getUserOrgId();
						}else if(proposalDetails.getNbfcFlow() == NBFC_BANK_FLOW){
							ratioVal = bankRatio;
							bankOrgId = proposalDetails.getUserOrgId();
						}
						calcAndSaveBlednedRate(proposalDetails,tenure,ratioVal,proposalMappingRequest);
					}
				}
				Integer isDataSaved = coLendingFlowRepository.saveBlendedValues(applicationId,nbfcOrgId,bankOrgId);
				if(isDataSaved == 0){
					logger.error("Blended rate Data not saved");
					throw new LoansException("Blended rate Data not saved for co-origination flow");
				}
				return true;
			}
		}catch (Exception e){
			logger.error("Error in calculateBlendedRateNbfc()",e);
			throw new LoansException("Error while calculating blended rate for co-origination flow");
		}
		return false;
	}

	private void calcAndSaveBlednedRate(ProposalDetails proposalDetails,Double tenure,Double ratioVal,ProposalMappingRequest minLoanAmtProposalObj){
		try {
			Double calcTenure = 0d,calcRoi = 0d,calcProcessingFee = 0d,monthlyRate = 0d,calcEmi = 0d;
			Double loanAmount = minLoanAmtProposalObj.getElAmount(),existingAmt = minLoanAmtProposalObj.getExistingLoanAmount(),additionalAmt = minLoanAmtProposalObj.getAdditionalLoanAmount();

			calcTenure = minLoanAmtProposalObj.getElTenure();
			calcRoi = (ratioVal * proposalDetails.getElRoi()) / 100;
			calcProcessingFee = (ratioVal * proposalDetails.getProcessingFee()) / 100;

			if(!CommonUtils.isObjectNullOrEmpty(additionalAmt) && additionalAmt!=0){
				additionalAmt = (ratioVal * additionalAmt) / 100;
			}
			if(!CommonUtils.isObjectNullOrEmpty(existingAmt) && existingAmt!=0){
				existingAmt = (ratioVal * existingAmt) / 100;
			}
			if(!CommonUtils.isObjectNullOrEmpty(loanAmount) && loanAmount!=0){
				loanAmount = (ratioVal * loanAmount) / 100;
			}

			monthlyRate = (calcRoi/100) / 12;
			Double mTenure = calcTenure*12;
			calcEmi= getPMTCalculationByLoanAmt(monthlyRate,mTenure,loanAmount);

			DecimalFormat df = new DecimalFormat("#.##");
			calcRoi = Double.valueOf(df.format(calcRoi));
			calcProcessingFee = Double.valueOf(df.format(calcProcessingFee));

			DecimalFormat df1 = new DecimalFormat("#");
			calcEmi = Double.valueOf(df1.format(calcEmi));

			ProposalDetailsAuditNbfc proposalDetailsAuditNbfc = new ProposalDetailsAuditNbfc();
			BeanUtils.copyProperties(proposalDetails,proposalDetailsAuditNbfc);
			proposalDetailsAuditNbfc.setProposalId(proposalDetails.getId());
			proposalDetailsAuditNbfc.setModifiedDate(new Date());
			proposalDetailsAudiNbfcRepository.save(proposalDetailsAuditNbfc);

			proposalDetails.setElAmount(loanAmount);
			proposalDetails.setAdditionalLoanAmount(additionalAmt);
			proposalDetails.setExistingLoanAmount(existingAmt);
			proposalDetails.setEmi(calcEmi);
			proposalDetails.setProcessingFee(calcProcessingFee);
			proposalDetails.setElRoi(calcRoi);
			proposalDetails.setElTenure(calcTenure);
			proposalDetails.setModifiedDate(new Date());

		}catch (Exception e){
			logger.error("Error in calcAndSaveBlednedRate()",e);
		}
	}

	public double getPMTCalculationByLoanAmt(double monthlyRate, double totalTenure, double loanAmount) {
		return (monthlyRate) / (1 - Math.pow(1 + monthlyRate, -totalTenure)) * loanAmount;
	}
}
