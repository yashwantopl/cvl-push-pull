package com.opl.service.loans.controller.fundseeker;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.model.InEligibleProposalDetailsRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.ProposalDetailsAdminRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingResponse;
import com.opl.mudra.api.matchengine.utils.CommonUtils;
import com.opl.service.loans.repository.common.LoanRepository;
import com.opl.service.loans.service.common.ProposalDetailsNewService;

/**
 * @author jaimin.darji
 */
@RestController
@RequestMapping("/proposalNew")
public class ProposalDetailsNewController {

    private static Logger logger = LoggerFactory.getLogger(ProposalDetailsNewController.class);
    private static final String DATA_FOUND = "data found";
    private static final String MSG_SOMETHING_WENT_WRONG = "Something went wrong";
    private static final String MSG_PARAMETER_NULL_EMPTY = "parameter is null or empty";
    private static final String MSG_APPLICATIONID_NULL_EMPTY = "applicationId is null or empty";
    private static final String MSG_INVALID_REQUEST_PARAMETERS_EMPTY = "Invalid Request.One or more parameters are empty.";
    private static final String ERROR_WHILE_CHANGE_STATUS = "Error while change status";
    private static final String ERROR_WHILE_CHANGE_PROPOSAL_STATUS = "Error while changeProposalStatus";


    @Autowired
    private ProposalDetailsNewService proposalDetailsService;

    @Autowired
    private LoanRepository loansDetailsRepository;


    @RequestMapping(value = "/saveEligibleProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalMappingResponse> saveProposalOnLoanSelection(@RequestBody ProposalMappingRequest request) {
        if (CommonUtils.isObjectNullOrEmpty(request) || CommonUtils.isObjectNullOrEmpty(request.getApplicationId())
                || (CommonUtils.isObjectNullOrEmpty(request.getFpProductId()) && CommonUtils.isObjectNullOrEmpty(request.getRatioId()))
                || CommonUtils.isObjectNullOrEmpty(request.getUserId())) {
            logger.info(MSG_INVALID_REQUEST_PARAMETERS_EMPTY);
            return new ResponseEntity<>(new ProposalMappingResponse(MSG_INVALID_REQUEST_PARAMETERS_EMPTY, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        } else {
            try {
                request = proposalDetailsService.saveProposalOnLoanSlection(request);
                logger.info("proposal added successfully {}", request);
                return new ResponseEntity<>(new ProposalMappingResponse("proposal added successfully",
                        HttpStatus.OK.value()), HttpStatus.OK);
            } catch (Exception e) {
                logger.trace(CommonUtils.EXCEPTION + e.getMessage(), e);
                logger.error(ERROR_WHILE_CHANGE_PROPOSAL_STATUS);
                return new ResponseEntity<>(new ProposalMappingResponse(MSG_SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        }
    }

    // For save In-Eligible Proposal
    @RequestMapping(value = "/saveIneligibleProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
        if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest)
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getApplicationId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserOrgId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getBranchId())) {
            logger.warn("Requested data can not be empty.Invalid Request. ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(com.opl.mudra.api.common.CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        Long isDetailsSaved = proposalDetailsService.saveOfflineProposal(inEligibleProposalDetailsRequest);
        logger.info("Into InEligible with InEligibleProposalDetailsRequest Data==>{} with isDetailsSaved==>{}", inEligibleProposalDetailsRequest.toString(), isDetailsSaved);
        if (isDetailsSaved != -1 || isDetailsSaved != 0)  {
            return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value(), isDetailsSaved), HttpStatus.OK);
        } else if (isDetailsSaved == -1) {
            return new ResponseEntity<LoansResponse>(new LoansResponse("It seems your proposal is already sanctioned by one of our bank partner. If you did not receive any communication from bank please mail your details at support@psbloansin59minutes.com", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        } else {
            return new ResponseEntity<LoansResponse>(new LoansResponse("Data not saved", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getOfflineProposalByOrgId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getOfflineProposalByOrgId(@RequestBody ProposalDetailsAdminRequest request) {

        if (CommonUtils.isObjectNullOrEmpty(request.getUserOrgId()) || CommonUtils.isObjectNullOrEmpty(request.getFromDate()) || CommonUtils.isObjectNullOrEmpty(request.getToDate()) || CommonUtils.isObjectNullOrEmpty(request.getUserId())) {
            logger.info("Bad Request !!");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Request parameter null or empty !!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        List<ProposalDetailsAdminRequest> dataList = proposalDetailsService.getOfflineProposals(request.getUserOrgId(), request);
        return new ResponseEntity<LoansResponse>(new LoansResponse("Data Found.", HttpStatus.OK.value(), dataList), HttpStatus.OK);
    }

    @PostMapping(value = "/reOpenOfflineProposalDetail", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> updateReOpenProposalDetail(@RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
        if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getReOpenReason())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserId()) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserOrgId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getIneligibleProposalId())) {
            logger.warn("Requested data can not be empty.Invalid Request. ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(com.opl.mudra.api.common.CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        Boolean isDetailsSaved = proposalDetailsService.updateReOpenProposalDetail(inEligibleProposalDetailsRequest);
        if (isDetailsSaved) {
            return new ResponseEntity<LoansResponse>(new LoansResponse("Data updated", HttpStatus.OK.value()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/checkIsExistOfflineProposalByApplicationId/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> checkIsExistOfflineProposalByApplicationId(@PathVariable(value = "applicationId") Long applicationId) {
        LoansResponse response = new LoansResponse("Success.", HttpStatus.OK.value(), proposalDetailsService.checkIsExistOfflineProposalByApplicationId(applicationId));
        return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/updateIneligibleTransferBranch", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> updateTransferBranch(@RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
        if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getBranchId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getIneligibleProposalId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserOrgId()) ||
                CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getReason())) {
            logger.warn("Requested data can not be empty.Invalid Request. ");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(com.opl.mudra.api.common.CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        Boolean isDetailsSaved = proposalDetailsService.updateTransferBranchDetail(inEligibleProposalDetailsRequest);
        if (isDetailsSaved) {
            return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value()),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
        }
    }

	@RequestMapping(value = "/updateProposalStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> updateSanctionedRejectStatus( @RequestBody InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
		if (CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserId())
                || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getUserOrgId()) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(inEligibleProposalDetailsRequest.getStatus())) {
			logger.warn("Requested data can not be empty.Invalid Request. ");
			return new ResponseEntity<LoansResponse>(new LoansResponse(com.opl.mudra.api.common.CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		Boolean isDetailsSaved = proposalDetailsService.updateStatus(inEligibleProposalDetailsRequest);
		if (isDetailsSaved) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved", HttpStatus.OK.value()), HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>( new LoansResponse("The application has encountered an error, please try again after sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
	}
}
