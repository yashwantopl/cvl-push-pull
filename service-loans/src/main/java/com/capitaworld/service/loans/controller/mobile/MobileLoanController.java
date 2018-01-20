package com.capitaworld.service.loans.controller.mobile;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MRetailCoAppGuarResponse;
import com.capitaworld.service.loans.model.mobile.MobileFPMatchesRequest;
import com.capitaworld.service.loans.model.mobile.MobileFrameRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.service.common.MobileService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.oneform.enums.ProductServicesPerse;


@RestController
@RequestMapping("/mobile")
public class MobileLoanController {

	private static final Logger logger = LoggerFactory.getLogger(MobileLoanController.class.getName());
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private ProposalService proposalService;
	
	
	@RequestMapping(value="/loanList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getLoanList(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get loan list for mobile app");
		try {
			List<MLoanDetailsResponse> response = loanApplicationService.getLoanListForMobile(mobileUserRequest.getUserId());
			logger.info("Successfully get loan lost for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(),response),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While Get Loan List For Mobile App");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplicantDetails(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get applicant details for mobile app");
		try {
			MRetailApplicantResponse response = mobileService.getApplicantDetails(mobileUserRequest);
			logger.info("Successfully get applicant details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully get data", HttpStatus.OK.value(),response),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While get applicant details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/saveApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveApplicantDetails(@RequestBody MRetailApplicantResponse mRetailApplicantResponse){
		logger.info("Enter in save applicant details for mobile app");
		try {
			Long id = mobileService.saveApplicantDetails(mRetailApplicantResponse);
			logger.info("Successfully save applicant details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully save data", HttpStatus.OK.value(),id),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While save applicant details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/lockPrimary",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lockPrimary(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in lock profile and primary details for mobile app");
		try {
			loanApplicationService.lockPrimary(mobileUserRequest.getApplicationId(),mobileUserRequest.getUserId(), true);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Your profile & primary details are locked", HttpStatus.OK.value()),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While lock profile and primary details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/getGuarantorDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getGuarantorDetails(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get Guarantor Details details for mobile app");
		try {
			if(CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getId()) || CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getUserId())
					|| CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getApplicationId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			MRetailCoAppGuarResponse coAppGuarResponse = mobileService.getGuarantorDetails(mobileUserRequest);
			logger.info("Successfullly get Guarantor details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfullly get details", HttpStatus.OK.value(),coAppGuarResponse),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While get Guarantor Details details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/saveGuarantorDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveGuarantorDetails(@RequestBody MRetailCoAppGuarResponse coAppGuarResponse){
		logger.info("Enter in save Guarantor Details details for mobile app");
		try {
			if(CommonUtils.isObjectNullOrEmpty(coAppGuarResponse.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(coAppGuarResponse.getUserId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			Long saveId = mobileService.saveGuarantorDetails(coAppGuarResponse);
			if(CommonUtils.isObjectNullOrEmpty(saveId)) {
				logger.info("Guarantor details is not saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Data not saved", HttpStatus.OK.value()),HttpStatus.OK);
			} else {
				logger.info("Successfullly Guarantor details saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfullly details saved", HttpStatus.OK.value(),saveId),HttpStatus.OK);
			}
		} catch(Exception e) {
			logger.warn("Error While save Guarantor Details details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getCoApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCoApplicantDetails(@RequestBody MobileLoanRequest mobileUserRequest){
		logger.info("Enter in get CoApplicant Details details for mobile app");
		try {
			if(CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getId()) || CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getUserId())
					|| CommonUtils.isObjectNullOrEmpty(mobileUserRequest.getApplicationId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			MRetailCoAppGuarResponse coAppGuarResponse = mobileService.getCoApplicantDetails(mobileUserRequest);
			logger.info("Successfullly get CoApplicant details for mobile app");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfullly get details", HttpStatus.OK.value(),coAppGuarResponse),HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While get CoApplicant Details details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/saveCoApplicantDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCoApplicantDetails(@RequestBody MRetailCoAppGuarResponse coAppGuarResponse){
		logger.info("Enter in save CoApplicant Details details for mobile app");
		try {
			if(CommonUtils.isObjectNullOrEmpty(coAppGuarResponse.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(coAppGuarResponse.getUserId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			Long saveId = mobileService.saveCoApplicantDetails(coAppGuarResponse);
			if(CommonUtils.isObjectNullOrEmpty(saveId)) {
				logger.info("CoApplicant details is not saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Data not saved", HttpStatus.OK.value()),HttpStatus.OK);
			} else {
				logger.info("Successfullly CoApplicant details saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfullly details saved", HttpStatus.OK.value(),saveId),HttpStatus.OK);
			}
		} catch(Exception e) {
			logger.warn("Error While save CoApplicant Details details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value="/saveLoanApplicationDetails",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveLoanApplicationDetails(@RequestBody MobileFrameRequest mobileFrameRequest){
		logger.info("Enter in save Loan Application Details details for mobile app");
		try {
			if( CommonUtils.isObjectNullOrEmpty(mobileFrameRequest.getUserId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			Long saveId = mobileService.saveLoanApplicationDetails(mobileFrameRequest);
			if(CommonUtils.isObjectNullOrEmpty(saveId)) {
				logger.info("Loan Application details is not saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Data not saved", HttpStatus.OK.value()),HttpStatus.OK);
			} else {
				logger.info("Successfullly Loan Application details saved for mobile app");
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfullly details saved", HttpStatus.OK.value(),saveId),HttpStatus.OK);
			}
		} catch(Exception e) {
			logger.warn("Error While save Loan Application Details details for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/getProductList",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProductList(@RequestBody MobileLoanRequest mobileLoanRequest){
		logger.info("Enter in get fp product list for mobile app");
		try {
			if(CommonUtils.isObjectNullOrEmpty(mobileLoanRequest.getUserId())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()),
						HttpStatus.OK);
			}
			
			List<ProductMasterRequest> list = productMasterService.getList(mobileLoanRequest.getUserId());
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(list);
			CommonDocumentUtils.endHook(logger, "getProductList for mobile");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch(Exception e) {
			logger.warn("Error While get fp product list for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/fundproviderProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> fundproviderProposal(@RequestBody MobileFPMatchesRequest request) {
		logger.info("Start get fundprovider matches list for mobile");
		try {
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			BeanUtils.copyProperties(request, proposalMappingRequest);
			List proposalDetailsList = proposalService.fundproviderProposal(proposalMappingRequest);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(proposalDetailsList);
			CommonDocumentUtils.endHook(logger, "fundproviderProposal for mobile");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch(Exception e) {
			logger.warn("Error While get fp matches list for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/fundSeekerProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> fundSeekerProposal(@RequestBody MobileFPMatchesRequest request) {
		logger.info("Start get fundseeker matches list for mobile");
		try {
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			BeanUtils.copyProperties(request, proposalMappingRequest);
			List<FundProviderProposalDetails> proposalDetailsList = proposalService.fundseekerProposal(proposalMappingRequest, request.getUserId());
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(proposalDetailsList);
			CommonDocumentUtils.endHook(logger, "fundseekerProposal matches for mobile");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch(Exception e) {
			logger.warn("Error While get FS matches list for mobile app");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> changeStatus(@RequestBody MobileFPMatchesRequest request,HttpServletRequest httpServletRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) {
		logger.info("Enter in mobile change status API");
		try {
			ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
			proposalMappingRequest.setId(request.getId());
			proposalMappingRequest.setProposalStatusId(request.getProposalStatusId());
			proposalMappingRequest.setLastActionPerformedBy(request.getUserType());
			proposalMappingRequest.setUserId(request.getUserType());
			ProposalMappingResponse changeStatus = proposalService.changeStatus(proposalMappingRequest);
			LoansResponse response = new LoansResponse(changeStatus.getMessage(), changeStatus.getStatus(), changeStatus.getData());
			logger.info("Successfully response from match-engine");
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);	
		} catch (Exception e) {
			logger.warn("Error While change mobile matches status");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
	}
	
	
	
}
