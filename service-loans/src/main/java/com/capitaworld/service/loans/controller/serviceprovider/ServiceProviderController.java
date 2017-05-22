package com.capitaworld.service.loans.controller.serviceprovider;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.service.serviceprovider.ServiceProviderFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;


@RestController
@RequestMapping("/sp")
public class ServiceProviderController {

	private static final Logger logger = LoggerFactory.getLogger(ServiceProviderController.class.getName());
	
	@Autowired
	ServiceProviderFlowService serviceProviderFlowService;
	
	@RequestMapping(value = "/client/list",method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserResponse> clientList(@RequestBody UsersRequest usersRequest,HttpServletRequest request){
		if(CommonUtils.isObjectNullOrEmpty(usersRequest) || CommonUtils.isObjectNullOrEmpty(usersRequest.getUserType())){
			return new ResponseEntity<UserResponse>(
					new UserResponse("Invalid data or Requested data not found.", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		try {
			List<SpClientListing> clientList = serviceProviderFlowService.spClientList(Long.valueOf("576"), usersRequest.getUserType().getCode());
			if(clientList != null){
				logger.info("Serivce provider's client list");
				return new ResponseEntity<UserResponse>(
						new UserResponse(clientList,"Serivce provider's client list", HttpStatus.OK.value()),
						HttpStatus.OK);
			}else{
				logger.info("Something went wrong..!");
				return new ResponseEntity<UserResponse>(
						new UserResponse("Something went wrong..!-->", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Something went wrong..!");
			return new ResponseEntity<UserResponse>(
					new UserResponse("Something went wrong..!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
	}
	
}
