package com.opl.service.loans.controller.fundseeker.corporate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.service.fundseeker.corporate.AssociatedConcernDetailService;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.utils.CommonDocumentUtils;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/associated_concern_details")
public class AssociatedConcernDetailController {

	private static final Logger logger = LoggerFactory.getLogger(AssociatedConcernDetailController.class);

	@Autowired
	private AssociatedConcernDetailService associatedConcernDetaillService;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest frameRequest, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest can not be empty ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		// application id must not be null
		if (frameRequest.getProposalMappingId() == null) {
			logger.warn("proposal id and user id must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()), HttpStatus.OK);
		}

		try {
			frameRequest.setUserId(userId);
			if(CommonDocumentUtils.isThisClientApplication(request)){
				frameRequest.setClientId(clientId);
			}

			Long finalUserId = (CommonUtils.isObjectNullOrEmpty(frameRequest.getClientId()) ? userId
					: frameRequest.getClientId());
			Boolean finalLocked = loanApplicationService.isFinalLockedByProposalId(frameRequest.getProposalMappingId(), finalUserId);
			if (!CommonUtils.isObjectNullOrEmpty(finalLocked) && finalLocked.booleanValue()) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			
			associatedConcernDetaillService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Associated Concerns Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/save_associate_concern", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveAssociateConcern(@RequestBody AssociatedConcernDetailRequest acRequest, HttpServletRequest request) {
		
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (acRequest == null) {
			logger.warn("acRequest can not be empty ==>" + acRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		try {
			acRequest.setUserId(userId);
			associatedConcernDetaillService.saveOrUpdate(acRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Associated Concerns Details==>", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "/inactive_associate_concern", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inactiveAssociateConcern(@RequestBody AssociatedConcernDetailRequest acRequest, HttpServletRequest request) {
		
		// request must not be null
		CommonDocumentUtils.startHook(logger, "save");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		
		if (CommonUtils.isObjectListNull(acRequest, acRequest.getApplicationId())) {
			logger.warn("acRequest can not be empty ==>" + acRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			acRequest.setUserId(userId);
			associatedConcernDetaillService.inactive(acRequest);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error while saving Associated Concerns Details==>", e);
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
		}
		
	}

	@RequestMapping(value = "/getListByProposalId/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByProposalId(@PathVariable Long proposalId, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		Long userId = null;
		if(CommonDocumentUtils.isThisClientApplication(request)){
			userId = clientId;
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}
		try {
			if (proposalId == null) {
				logger.warn("ID Require to get Associated Concerns Details ==>" + proposalId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<AssociatedConcernDetailRequest> response = associatedConcernDetaillService
					.getAssociatedConcernsDetailListByProposalId(proposalId,userId);
			Integer currentYear = null;
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			currentYear = calendar.get(Calendar.YEAR);

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(currentYear);
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Associated Concerns Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/getList/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id, HttpServletRequest request,@RequestParam(value = "clientId",required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		Long userId = null;
		   if(CommonDocumentUtils.isThisClientApplication(request)){
		    userId = clientId;
		   } else {
		    userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		   }
		try {
			if (id == null) {
				logger.warn("ID Require to get Associated Concerns Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<AssociatedConcernDetailRequest> response = associatedConcernDetaillService
					.getAssociatedConcernsDetailList(id,userId);
				Integer currentYear = null;
			if (!CommonUtils.isListNullOrEmpty(response)) {
				currentYear = response.get(0).getCurrentYear();
				if (currentYear == null) {
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(new Date());
					currentYear = calendar.get(Calendar.YEAR);
				}
			} else {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(new Date());
				currentYear = calendar.get(Calendar.YEAR);
			}

			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(currentYear);
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Associated Concerns Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
