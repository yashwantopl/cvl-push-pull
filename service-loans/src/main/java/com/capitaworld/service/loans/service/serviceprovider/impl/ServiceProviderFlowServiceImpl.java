package com.capitaworld.service.loans.service.serviceprovider.impl;

import java.util.ArrayList;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.serviceprovider.ServiceProviderFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.SpClientResponse;
import com.capitaworld.service.users.model.UserResponse;


@Service
@Transactional
public class ServiceProviderFlowServiceImpl implements ServiceProviderFlowService {

	@Autowired
	private Environment environmment;
	
	@Autowired
	LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	private static final String USERS_BASE_URL_KEY = "userURL";
	private static final String ONEFORM_BASE_URL_KEY = "oneForm";
	@Override
	public List<SpClientListing> spClientList(Long spId, String userTypeCode) throws Exception {
		UsersClient usersClient = new UsersClient(environmment.getRequiredProperty(USERS_BASE_URL_KEY));
		try {
			UserResponse userResponse = usersClient.getSpUserIdClientMappingList(spId, userTypeCode);
			List<Map<String, Object>> spClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			List<SpClientListing> clientListings = new ArrayList<SpClientListing>();
			for (int i = 0; i < spClientResponseList.size(); i++) {
				SpClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(spClientResponseList.get(i),
						SpClientResponse.class);
				SpClientListing spClientDetail = new SpClientListing();
				spClientDetail.setClientId(clientResponse.getClientId());
				spClientDetail.setClientName(clientResponse.getClientName());
				spClientDetail.setClientEmail(clientResponse.getClientEmail());
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCity())
						&& clientResponse.getClientCity() != 0) {
					CityByCityListIdClient cityListIdClient = new CityByCityListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> cityList = new ArrayList<>();
					cityList.add((long) clientResponse.getClientCity());
					OneFormResponse cityResponse = cityListIdClient.send(cityList);
					List<Map<String, Object>> cityResponseDatalist = (List<Map<String, Object>>) cityResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(cityResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientCity(masterResponse.getValue());
					} else {
						spClientDetail.setClientCity("NA");
					}
				} else {
					spClientDetail.setClientCity("NA");
				}
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientState())
						&& clientResponse.getClientState() != 0) {
					StateListByStateListIdClient stateListIdClient = new StateListByStateListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> stateList = new ArrayList<>();
					stateList.add((long) clientResponse.getClientState());
					OneFormResponse stateResponse = stateListIdClient.send(stateList);
					List<Map<String, Object>> stateResponseDatalist = (List<Map<String, Object>>) stateResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(stateResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientState(masterResponse.getValue());
					} else {
						spClientDetail.setClientState("NA");
					}
				} else {
					spClientDetail.setClientState("NA");
				}
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCountry())
						&& clientResponse.getClientCountry() != 0) {
					CountryByCountryListIdClient countryListIdClient = new CountryByCountryListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> countryList = new ArrayList<>();
					countryList.add((long) clientResponse.getClientCountry());
					OneFormResponse countryResponse = countryListIdClient.send(countryList);
					List<Map<String, Object>> countryResponseDatalist = (List<Map<String, Object>>) countryResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(countryResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientCountry(masterResponse.getValue());
					} else {
						spClientDetail.setClientCountry("NA");
					}
				} else {
					spClientDetail.setClientCountry("NA");
				}


				if (userTypeCode.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDSEEKER)) {

					List<LoanApplicationDetailsForSp> fsClientDetails = loanApplicationService.getLoanDetailsByUserIdList(clientResponse.getClientId());
					List<LoanApplicationDetailsForSp> fsApplicationDetails = new ArrayList<LoanApplicationDetailsForSp>();
					for (LoanApplicationDetailsForSp applicationDetailsForSp : fsClientDetails) {
						applicationDetailsForSp.setProductName(LoanType.getById(applicationDetailsForSp.getProductId()).getValue());
						applicationDetailsForSp.setDenominationValue(Denomination.getById(applicationDetailsForSp.getDenominationId()).getValue());
						fsApplicationDetails.add(applicationDetailsForSp);
					}
					spClientDetail.setListData(fsApplicationDetails);
					clientListings.add(spClientDetail);

				} else if (userTypeCode.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDPROVIDER)) {

					List<ProductDetailsForSp> fpClientDetails = productMasterService.getProductDetailsByUserIdList(clientResponse.getClientId());
					List<ProductDetailsForSp> fpProductsDetails = new ArrayList<ProductDetailsForSp>();
					for(ProductDetailsForSp productDetailsForSp : fpClientDetails){
						fpProductsDetails.add(productDetailsForSp);
					}
					spClientDetail.setListData(fpProductsDetails);
					clientListings.add(spClientDetail);
				}
			}
			return clientListings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error while getting client list.");
		}

	}
	
	@Override
	public JSONObject spClientCount(Long spId) throws Exception {
		UsersClient usersClient = new UsersClient(environmment.getRequiredProperty(USERS_BASE_URL_KEY));
		try {
			UserResponse response = usersClient.getSPClientCount(spId);
			if(!CommonUtils.isObjectNullOrEmpty(response.getData())){
				return MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)response.getData(), JSONObject.class);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Error while getting SP client count.");
		}
		return null;
	}	
}
