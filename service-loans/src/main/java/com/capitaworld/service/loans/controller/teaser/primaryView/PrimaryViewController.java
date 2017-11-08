package com.capitaworld.service.loans.controller.teaser.primaryView;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.LapPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.LapPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UserTypeRequest;
import com.capitaworld.service.users.model.UsersRequest;

@RestController
@RequestMapping("/PrimaryView")
public class PrimaryViewController {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryViewController.class);
	
	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;

	@Autowired
    private PersonalLoansViewService personalLoansViewService;
	
	@Autowired
    private CarLoanPrimaryViewService carLoanPrimaryViewService;
	
	@Autowired
	private LapPrimaryViewService lapPrimaryService;
	
	@Autowired
    private WorkingCapitalPrimaryViewService workingCapitalPrimaryViewService;
	
	@Autowired
	private TermLoanPrimaryViewService termLoanPrimaryViewService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private UsersClient usersClient;
	
	@GetMapping(value = "/HomeLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewHomeLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
        	HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse = null;
			try {
				homeLoanPrimaryViewResponse = homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(homeLoanPrimaryViewResponse)){
					loansResponse.setData(homeLoanPrimaryViewResponse);
					loansResponse.setMessage("Home Loan Primary Details");
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
	
	@GetMapping(value = "/PersonalLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfPersonalLoans(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		} else {
			RetailPrimaryViewResponse personalLoansPrimaryViewResponse = null;
			try {
				personalLoansPrimaryViewResponse = personalLoansViewService.getPersonalLoansPrimaryViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(personalLoansPrimaryViewResponse)){
					loansResponse.setData(personalLoansPrimaryViewResponse);
					loansResponse.setMessage("Personal Loans Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Personal Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(personalLoansPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

    @GetMapping(value = "/CarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCarLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
        Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
			CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = null;
			try {
				carLoanPrimaryViewResponse = carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(carLoanPrimaryViewResponse)){
					loansResponse.setData(carLoanPrimaryViewResponse);
					loansResponse.setMessage("Car Loan Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Car Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(carLoanPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
        }
    }

    @GetMapping(value = "/LapLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfLap(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
        Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            LapPrimaryViewResponse lapPrimaryViewResponse= null;
			try {
				lapPrimaryViewResponse = lapPrimaryService.getLapPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(lapPrimaryViewResponse)){
					loansResponse.setData(lapPrimaryViewResponse);
					loansResponse.setMessage("LAP Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for LAP Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
	            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				loansResponse.setData(lapPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
	            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
        }
    }

	@GetMapping(value = "/WorkingCapital/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}else {
			WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = null;
			try {
				workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(toApplicationId,userType,userId);
				if(!CommonUtils.isObjectNullOrEmpty(workingCapitalPrimaryViewResponse)){
					loansResponse.setData(workingCapitalPrimaryViewResponse);
					loansResponse.setMessage("Working Capital Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Working Capital final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(workingCapitalPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/TermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfTermLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,@RequestParam(value = "clientId", required = false) Long clientId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = null;
		Integer userType = null;
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
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
				userType = CommonUtils.UserType.SERVICE_PROVIDER;
			}
			
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE)).intValue();
		}
		
		if (toApplicationId == null) {
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}else {
			TermLoanPrimaryViewResponse termLoanPrimaryViewResponse = null;
			try {
				termLoanPrimaryViewResponse = termLoanPrimaryViewService.getTermLoanPrimaryViewDetails(toApplicationId, userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(termLoanPrimaryViewResponse)) {
					loansResponse.setData(termLoanPrimaryViewResponse);
					loansResponse.setMessage("Working Capital Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Term Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(termLoanPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}
	
	
	@RequestMapping(value = "/sendPrimaryTeaserViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void primaryTeaserViewNotification(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws Exception {
		
		// request must not be null
	
			Long fromUserId = null;
			Long fromUserTypeId = null;
			Long loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());
			
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				fromUserId = clientId;
				fromUserTypeId = clientUserType;
			} else {
				fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
				fromUserTypeId = loginUserType;
			}
			Long applicationId=request.getApplicationId();
			Long fpProductId=request.getFpProductId();
			String toUserId = null;
			Long notificationId;
			
			if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
			{
				notificationId=NotificationAlias.SYS_FS_VIEW;
				Object[] o=productMasterService.getUserDetailsByPrductId(fpProductId);
				toUserId=o[0].toString();
			}
			else
			{
				Object[] o=loanApplicationService.getApplicationDetailsById(applicationId);
				toUserId=o[0].toString();
				notificationId=NotificationAlias.SYS_FP_VIEW;
			}
			
			try {
			
				notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId, applicationId, fpProductId,NotificationTemplate.PRIMARY_VIEW,loginUserType);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	
	@RequestMapping(value = "/sendFinalTeaserViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void finalTeaserViewNotification(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws Exception {
		
		// request must not be null
	
		Long fromUserId = null;
		Long fromUserTypeId = null;
		Long loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			fromUserId = clientId;
			fromUserTypeId = clientUserType;
		} else {
			fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			fromUserTypeId = loginUserType;
		}
		
			Long applicationId=request.getApplicationId();
			Long fpProductId=request.getFpProductId();
			String toUserId = null;
			Long notificationId = null;
			
			if(CommonUtils.UserType.FUND_PROVIDER == fromUserTypeId)
			{
				Object[] o=loanApplicationService.getApplicationDetailsById(applicationId);
				toUserId=o[0].toString();
				notificationId=NotificationAlias.SYS_FP_VIEWSEC;
			}
			else if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
			{
				Object[] o=loanApplicationService.getApplicationDetailsById(applicationId);
				toUserId=o[0].toString();
				notificationId=NotificationAlias.SYS_FS_VIEWSEC;
			}
			
			try {
			
				notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId, applicationId, fpProductId,NotificationTemplate.FINAL_VIEW,loginUserType);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	
	@RequestMapping(value = "/finalTeaserReqViewNotification", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void finalTeaserReqViewNotification(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) throws Exception {
		
		// request must not be null
	
		Long fromUserId = null;
		Long fromUserTypeId = null;
		Long loginUserType = Long.valueOf(httpRequest.getAttribute(CommonUtils.USER_TYPE).toString());
		
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			fromUserId = clientId;
			fromUserTypeId = clientUserType;
		} else {
			fromUserId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			fromUserTypeId = loginUserType;
		}
		
			Long applicationId=request.getApplicationId();
			Long fpProductId=request.getFpProductId();
			String toUserId = null;
			Long notificationId = null;
			
			if(CommonUtils.UserType.FUND_PROVIDER == fromUserTypeId)
			{
				Object[] o=loanApplicationService.getApplicationDetailsById(applicationId);
				toUserId=o[0].toString();
				notificationId=NotificationAlias.SYS_FP_REQ_VIEWSEC;
			}
			else if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
			{
				Object[] o=loanApplicationService.getApplicationDetailsById(applicationId);
				toUserId=o[0].toString();
				notificationId=NotificationAlias.SYS_FS_REQ_VIEWSEC;
			}
			
			try {
			
				notificationService.sendViewNotification(toUserId, fromUserId, fromUserTypeId, notificationId, applicationId, fpProductId,null,loginUserType);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
	}
	
}
