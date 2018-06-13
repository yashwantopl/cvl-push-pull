package com.capitaworld.service.loans.controller.teaser.finalview;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.dms.util.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.CarLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.CorporateFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.LapFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.PersonalLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.TermLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.UnsecuredLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.WorkingCapitalFinalViewResponse;
import com.capitaworld.service.loans.service.teaser.finalview.CarLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.CorporateFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.LapFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.PersonalLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.TermLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.UnsecuredLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.WorkingCapitalFinalService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;

@RestController
@RequestMapping("/FinalView")
public class FinalViewController {

private static final Logger logger = LoggerFactory.getLogger(FinalViewController.class);
	
	@Autowired
	private HomeLoanFinalViewService hlFinalViewService;
	
	@Autowired
	private CarLoanFinalViewService clFinalViewService;
	
	@Autowired
	private PersonalLoanFinalViewService plFinalViewService;

	@Autowired
	private WorkingCapitalFinalService wcFinalService;

	@Autowired
	private TermLoanFinalViewService tlFinalViewService;
	
	@Autowired
	private UnsecuredLoanFinalViewService unsecuredLoanFinalViewService;

	@Autowired
	private LapFinalViewService lapFinalViewService;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private CorporateFinalViewService corporateFinalViewService;
	
	@GetMapping(value = "/HomeLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewHomeLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {
				if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	HomeLoanFinalViewResponse homeLoanFinalViewResponse = null;
			try {
				homeLoanFinalViewResponse = hlFinalViewService.getHomeLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(homeLoanFinalViewResponse)){
					loansResponse.setData(homeLoanFinalViewResponse);
		            loansResponse.setMessage("Home Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Home Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}      
        }
	}
	
	@GetMapping(value = "/CarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewCarLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {
				if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	CarLoanFinalViewResponse clFinalViewResponse = null;
			try {
				clFinalViewResponse = clFinalViewService.getCarLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(clFinalViewResponse)){
					loansResponse.setData(clFinalViewResponse);
		            loansResponse.setMessage("Car Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Car Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}      
        }
	}
	
	@GetMapping(value = "/PersonalLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewPersonalLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {						
				if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	PersonalLoanFinalViewResponse plFinalViewResponse = null;
			try {
				plFinalViewResponse = plFinalViewService.getPersonalLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(plFinalViewResponse)){
					loansResponse.setData(plFinalViewResponse);
		            loansResponse.setMessage("Personal Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Personal Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}     
        }
	}
	
	@GetMapping(value = "/LapLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewLapLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	LapFinalViewResponse lapFinalViewResponse = null;
			try {
				lapFinalViewResponse = lapFinalViewService.getLapFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(lapFinalViewResponse)){
					loansResponse.setData(lapFinalViewResponse);
		            loansResponse.setMessage("Lap Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Lap final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}     
        }
	}

	@GetMapping(value = "/WorkingCapital/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> finalViewWrokingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
		//get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}else {
			WorkingCapitalFinalViewResponse workingCapitalFinalViewResponse = null;
			try {
				workingCapitalFinalViewResponse = wcFinalService.getWorkingCapitalFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(workingCapitalFinalViewResponse)){
					loansResponse.setData(workingCapitalFinalViewResponse);
					loansResponse.setMessage("Working Capital Final Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for working capital final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setMessage("Something went wrong..!"+e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/TermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> finalViewTermLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
		//get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}else {
			TermLoanFinalViewResponse termLoanFinalViewResponse = null;
			try {
				termLoanFinalViewResponse = tlFinalViewService.getTermLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(termLoanFinalViewResponse)){
					loansResponse.setData(termLoanFinalViewResponse);
					loansResponse.setMessage("Term Loan Final Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Term Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setMessage("Something went wrong..!"+e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}
	
	@GetMapping(value = "/UnsecuredLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> finalViewUnsecuredLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
		LoansResponse loansResponse = new LoansResponse();
		//get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonDocumentUtils.isThisClientApplication(request)) {
			if(!CommonUtils.isObjectNullOrEmpty(clientId)){
				//MEANS FS, FP VIEW
				userId = clientId;
				try {
					UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
					if(response != null && response.getData() != null){
						UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
						userType = req.getId().intValue();
					} else {
						logger.warn("user_verification, Invalid Request... Client Id is not valid");
						return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid",
								HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
					}	
				} catch(Exception e) {
					logger.warn("user_verification, Invalid Request... Something went wrong");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong",
							HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
				}
			} else {if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
				}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
					userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
			}
			
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}else {
			UnsecuredLoanFinalViewResponse unsecuredLoanFinalViewResponse = null;
			try {
				unsecuredLoanFinalViewResponse = unsecuredLoanFinalViewService.getUnsecuredLoanFinalViewDetails(toApplicationId,userType,userId);
				if(!CommonUtils.isObjectNullOrEmpty(unsecuredLoanFinalViewResponse)){
					loansResponse.setData(unsecuredLoanFinalViewResponse);
					loansResponse.setMessage("Unsecured Loan Final Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Unsecured Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setMessage("Something went wrong..!"+e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}
	
		 //COMMON FINAL CORPORATE TEASER VIEW
		@GetMapping(value = "/Corporate/{toApplicationId}")
		public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCorporateCommon(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest request) {
			logger.info("IN FINAL CORPORATE TEASER VIEW======>"+toApplicationId);
			LoansResponse loansResponse = new LoansResponse();
			//GET USERID 
			Long userId = null;
			Integer userType = null;

			if (CommonDocumentUtils.isThisClientApplication(request)) {
				if(!CommonUtils.isObjectNullOrEmpty(clientId)){
					//FOR FS,FP UNDER SP OR NP
					userId = clientId;
					try {
						UserResponse response = usersClient.getUserTypeByUserId(new UsersRequest(userId));
						if(response != null && response.getData() != null){
							UserTypeRequest req = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String,Object>) response.getData(), UserTypeRequest.class);
							userType = req.getId().intValue();
						} else {
							logger.warn("user_verification, Invalid Request... Client Id is not valid");
							return new ResponseEntity<LoansResponse>(new LoansResponse("Client Id is not valid", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
						}
					} catch(Exception e) {
						logger.warn("user_verification, Invalid Request... Something went wrong");
						e.printStackTrace();
						return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
					}
				} else {
					if(CommonUtils.UserType.SERVICE_PROVIDER == userType){
						userType = CommonUtils.UserType.SERVICE_PROVIDER;
					}else if(CommonUtils.UserType.NETWORK_PARTNER == userType){
						userType = CommonUtils.UserType.NETWORK_PARTNER;
					}
				}
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
				userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE)).intValue();
			}
			if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
				logger.warn("Invalid data or Requested data not found.", toApplicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
			}else {
				CorporateFinalViewResponse corporateFinalViewResponse = null;
				try {
					logger.info("Request users details:- toApplicationId,userType,userId is" +toApplicationId +userType +userId);
					corporateFinalViewResponse = corporateFinalViewService.getCorporateFinalViewDetails(toApplicationId,userType,userId);
					if(!CommonUtils.isObjectNullOrEmpty(corporateFinalViewResponse)){
						logger.info("response is"+corporateFinalViewResponse.toString());
						loansResponse.setData(corporateFinalViewResponse);
						loansResponse.setMessage("Corporate Primary Details");
						loansResponse.setStatus(HttpStatus.OK.value());
					}else{
						loansResponse.setMessage("No data found for Corporate final view");
						loansResponse.setStatus(HttpStatus.OK.value());
					}
					return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
				}catch (Exception e){
					loansResponse.setData(corporateFinalViewResponse);
					loansResponse.setMessage("Something went wrong..!");
					loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
			}
		}
}
