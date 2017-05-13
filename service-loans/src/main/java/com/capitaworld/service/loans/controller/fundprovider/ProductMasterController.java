package com.capitaworld.service.loans.controller.fundprovider;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.capitaworld.service.loans.controller.fundseeker.LoanApplicationController;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/product_master")
public class ProductMasterController {
	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody MultipleFpPruductRequest multipleFpPruductRequest, HttpServletRequest request) {
		try {
			// request must not be null
			
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			multipleFpPruductRequest.setUserId(userId);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (multipleFpPruductRequest == null) {
				logger.warn("multipleFpPruductRequest Object can not be empty ==>" + multipleFpPruductRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			if(multipleFpPruductRequest.getDataList()==null)
			{
				logger.warn("data list can not be empty ==>" + multipleFpPruductRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			if(multipleFpPruductRequest.getFpName()==null || multipleFpPruductRequest.getFpName().length()==0)
			{
				logger.warn("fund provider name  can not be empty ==>" + multipleFpPruductRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			List<CommonResponse> response = productMasterService.saveOrUpdate(multipleFpPruductRequest);
			if (response==null||!response.isEmpty()) {
				LoansResponse loansResponse = new LoansResponse("Data saved.", HttpStatus.OK.value());
				loansResponse.setListData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving multipleFpPruductRequest Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(HttpServletRequest request) {
		// request must not be null
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("UserId Require to get product Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<ProductMasterRequest> response = productMasterService.getList(userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getUserNameByProductId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserNameByProductId(@RequestBody Long productId,
			HttpServletRequest request) {
		// request must not be null
		try {

			if (productId == null) {
				logger.warn("productId ID Require to get Details ==>" + productId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("UserId Require to get user name ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			String response = productMasterService.getUserNameByApplicationId(productId, userId);
			LoansResponse loansResponse;
			if(response==null)
			{
				 loansResponse = new LoansResponse("Data Not Found.", HttpStatus.OK.value());
			}
			else
			{
			 loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			}
			loansResponse.setData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting user name ==>", e);	
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
