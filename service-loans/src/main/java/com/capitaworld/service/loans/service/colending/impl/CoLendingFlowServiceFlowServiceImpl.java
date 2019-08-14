package com.capitaworld.service.loans.service.colending.impl;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.repository.colending.CoLendingFlowRepository;
import com.capitaworld.service.loans.service.colending.CoLendingFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.NbfcClientResponse;
import com.capitaworld.service.users.model.UserResponse;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
					if ((objects[0].equals("7") || objects[0].equals("9")) && objects[1].equals("3")){
						clientDetailCoLending.setClientStatus("In-Principle");
					}else if(objects[0].equals("4") && objects[1].equals("3")){
						clientDetailCoLending.setClientStatus("In-Eligible");
					}else{
						clientDetailCoLending.setClientStatus("In-Progress");
					}
				}else{
					clientDetailCoLending.setClientStatus("NA");
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


}
