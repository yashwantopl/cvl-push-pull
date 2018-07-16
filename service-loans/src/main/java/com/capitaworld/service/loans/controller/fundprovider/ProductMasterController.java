package com.capitaworld.service.loans.controller.fundprovider;

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

import com.capitaworld.service.loans.controller.fundseeker.LoanApplicationController;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.model.retail.RetailProduct;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
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

	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> addProduct(@RequestBody AddProductRequest addProductRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		CommonDocumentUtils.startHook(logger, "addProduct");
		try {
			// request must not be null

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			addProductRequest.setUserId(userId);
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				addProductRequest.setClientId(clientId);
			}

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				CommonDocumentUtils.endHook(logger, "addProduct");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (addProductRequest == null) {
				logger.warn("addProductRequest Object can not be empty ==>" + addProductRequest);
				CommonDocumentUtils.endHook(logger, "addProduct");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Boolean response = productMasterService.saveOrUpdate(addProductRequest,userOrgId);
			if (response) {
				CommonDocumentUtils.endHook(logger, "addProduct");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "addProduct");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving addProduct Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	
	@RequestMapping(value = "/saveCorporate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCorporate(
			@RequestBody CorporateProduct corporateProduct,HttpServletRequest request) {
		CommonDocumentUtils.startHook(logger, "save");
		System.out.println("json"+corporateProduct.toString());
		try {
			if (corporateProduct == null) {
				logger.warn("corporateProduct Object can not be empty ==>",
						corporateProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (corporateProduct.getId() == null) {
				logger.warn("corporateProduct id can not be empty ==>", corporateProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			//Long userId=1755l;
			if(userId==null)
			{
				logger.warn("userId  id can not be empty ==>", userId);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			corporateProduct.setUserId(userId);
			boolean response = productMasterService.saveCorporate(corporateProduct);
			if (response) {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Error while saving corporateProduct  Parameter==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
	@RequestMapping(value = "/saveRetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveRetail(
			@RequestBody RetailProduct retailProduct,HttpServletRequest request) {
		CommonDocumentUtils.startHook(logger, "save");
		try {
			if (retailProduct == null) {
				logger.warn("retailProduct Object can not be empty ==>",
						retailProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (retailProduct.getId() == null) {
				logger.warn("retailProduct id can not be empty ==>", retailProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			//Long userId=1755l;
			if(userId==null)
			{
				logger.warn("userId  id can not be empty ==>", userId);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			retailProduct.setUserId(userId);
			boolean response = productMasterService.saveRetail(retailProduct);
			if (response) {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Error while saving retailProduct  Parameter==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getList");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get product Details ==>" + userId);
				CommonDocumentUtils.endHook(logger, "getList");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			List<ProductMasterRequest> response = productMasterService.getList(userId,userOrgId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Products Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getListByUserType/{userType}/{applicationStage}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByUserType(HttpServletRequest request,
			@PathVariable(value = "userType") String userType,@PathVariable(value = "applicationStage")String applicationStage,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getListByUserType");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}		
			//get org id
			Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			
			if (userId == null) {
				logger.warn("UserId Require to get product Details ==>" + userId);
				CommonDocumentUtils.endHook(logger, "getListByUserType");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (userType == null) {
				logger.warn("userType Require to get product Details ==>" + userId);
				CommonDocumentUtils.endHook(logger, "getListByUserType");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			//List<ProductMasterRequest> response = productMasterService.getListByUserType(userId, userType);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(productMasterService.getListByUserType(userId, Integer.parseInt(CommonUtils.decode(userType)),Integer.parseInt(CommonUtils.decode(applicationStage)),userOrgId));
			CommonDocumentUtils.endHook(logger, "getListByUserType");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Products Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getFPProduct/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFPProduct(HttpServletRequest request,
			@PathVariable(value = "id") Long id
			) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getFPProduct");
		try {
			
			
			
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(productMasterService.getProductMasterWithAllData(id));
			CommonDocumentUtils.endHook(logger, "getListByUserType");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Products Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	
	@RequestMapping(value = "/getUserNameByProductId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserNameByProductId(@RequestBody Long productId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getUserNameByProductId");
		try {

			if (productId == null) {
				logger.warn("productId ID Require to get Details ==>" + productId);
				CommonDocumentUtils.endHook(logger, "getUserNameByProductId");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Object[] response = productMasterService.getUserDetailsByPrductId(productId);
			LoansResponse loansResponse;
			if (response == null) {
				loansResponse = new LoansResponse("Data Not Found.", HttpStatus.OK.value());
			} else {
				loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				if (!CommonUtils.isObjectNullOrEmpty(response[1])) {
					loansResponse.setData(response[1]);
				} else {
					loansResponse.setData("NA");
				}
			}
			CommonDocumentUtils.endHook(logger, "getUserNameByProductId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting user name ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getUserIdByProductId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUserIdByProductId(@RequestBody Long productId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getUserIdByProductId");
		try {

			if (productId == null) {
				logger.warn("productId ID Require to get Details ==>" + productId);
				CommonDocumentUtils.endHook(logger, "getUserIdByProductId");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			Object[] response = productMasterService.getUserDetailsByPrductId(productId);
			LoansResponse loansResponse;
			if (response == null) {
				loansResponse = new LoansResponse("Data Not Found.", HttpStatus.OK.value());
			} else {
				loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				if (!CommonUtils.isObjectNullOrEmpty(response[0])) {
					loansResponse.setData(response[0]);
				}

			}
			CommonDocumentUtils.endHook(logger, "getUserIdByProductId");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting user name ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getListByUserIdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getListByUseIdList(@RequestBody Long userId, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getListByUserIdList");
		try {

			if (userId == null) {
				logger.warn("UserId Require to get Loan Applications Details ==>" + userId);
				CommonDocumentUtils.endHook(logger, "getListByUserIdList");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<ProductDetailsForSp> response = productMasterService.getProductDetailsByUserIdList(userId);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getListByUserIdList");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/productDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDetailsResponse> fpProductDetails(
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		CommonDocumentUtils.startHook(logger, "fpProductDetails");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (userId == null) {
				ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse("User id is null or empty",
						HttpStatus.BAD_REQUEST.value());
				logger.error("User id is null or empty");
				CommonDocumentUtils.endHook(logger, "fpProductDetails");
				return new ResponseEntity<ProductDetailsResponse>(productDetailsResponse, HttpStatus.OK);
			}
			Long userOrgId = null;
			if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ORG_ID))) {
				userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);	
			}

			ProductDetailsResponse productDetailsResponse = productMasterService.getProductDetailsResponse(userId,userOrgId);
			CommonDocumentUtils.endHook(logger, "fpProductDetails");
			return new ResponseEntity<ProductDetailsResponse>(productDetailsResponse, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse("Something went wrong",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error("Something went wrong");
			e.printStackTrace();
			return new ResponseEntity<ProductDetailsResponse>(productDetailsResponse, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getProductDetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFpDetails(@RequestBody Long productMappingId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getFpDetails");
		try {

			if (productMappingId == null) {
				logger.warn("productMappingId  Require to get product Details ==>" + productMappingId);
				CommonDocumentUtils.endHook(logger, "getFpDetails");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			FpProductDetails response = productMasterService.getProductDetails(productMappingId);
			LoansResponse loansResponse;
			if (response == null) {
				loansResponse = new LoansResponse("Data Not Found.", HttpStatus.OK.value());
			} else {
				loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(response);
			}
			CommonDocumentUtils.endHook(logger, "getFpDetails");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while getting fp  product details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/is_self_view/{fpMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> isSelfView(@RequestParam(value = "clientId", required = false) Long clientId,
			@PathVariable("fpMappingId") Long fpMappingId, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "isSelfView");
		try {
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (fpMappingId == null) {
				CommonDocumentUtils.endHook(logger, "isSelfView");
				logger.warn("fpMappingId  Require to get product Details ==>" + fpMappingId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(productMasterService.isSelfView(fpMappingId, userId));
			CommonDocumentUtils.endHook(logger, "isSelfView");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while checking self view ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/checkParameterIsFilled/{fpMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkParameterIsFilled(@PathVariable("fpMappingId") Long fpMappingId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "checkParameterIsFilled");
		try {

			if (fpMappingId == null) {
				CommonDocumentUtils.endHook(logger, "checkParameterIsFilled");
				logger.warn("fpMappingId  Require to check parameter filled or not==>" + fpMappingId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(productMasterService.checkParameterIsFilled(fpMappingId));
			CommonDocumentUtils.endHook(logger, "checkParameterIsFilled");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while checking fp parameter filled or not ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/changeStatus/{productMappingId}/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> changeStatus(@PathVariable("status") Boolean status,
			@PathVariable("productMappingId") Long productMappingId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "changeStatus");
		try {

			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get product Details ==>" + userId);
				CommonDocumentUtils.endHook(logger, "getList");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			if (status == null || productMappingId==null) {
				CommonDocumentUtils.endHook(logger, "changeStatus");
				logger.warn("productMappingId  and status Require to changeStatus==>" + status);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("changeStatus successfully.", HttpStatus.OK.value());
			loansResponse.setMessage(status?"activated":"inactivated");
			loansResponse.setData(productMasterService.changeStatus(productMappingId,status, userId));
			CommonDocumentUtils.endHook(logger, "changeStatus");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while changeStatus ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/lastAccessedProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> lastAccessedProduct( HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "lastAccessedProduct");
		try {

			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (userId == null) {
				logger.warn("UserId Require to get lastAccessedProduct ==>" + userId);
				CommonDocumentUtils.endHook(logger, "lastAccessedProduct");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			LoansResponse loansResponse = new LoansResponse("last access product detail", HttpStatus.OK.value());
			
			loansResponse.setData(productMasterService.lastAccessedProduct(userId));
			CommonDocumentUtils.endHook(logger, "lastAccessedProduct");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while lastAccessedProduct ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getChatListByApplicationId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getChatListByApplicationId(HttpServletRequest request,
			@RequestBody Long mappingId) {
		// request must not be null
		try {
			CommonDocumentUtils.startHook(logger, "getChatListByApplicationId");
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(productMasterService.getChatListByFpMappingId(mappingId));
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getChatListByApplicationId==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get_product/{orgId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProductsByOrg(@PathVariable("orgId") Long orgId) {
		try {
			logger.info("start getProductsByOrg()");
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setData(productMasterService.getProductByOrgId(orgId));
			logger.info("End getProductsByOrg()");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getProductsByOrg==>{}", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/saveMasterFromTemp ", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveMasterFromTemp(HttpServletRequest request,
			@RequestBody Long mappingId) {
		// request must not be null
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		
		if (userId == null) {
			logger.warn("UserId Require to saveMasterFromTemp ==>" + userId);
			CommonDocumentUtils.endHook(logger, "saveMasterFromTemp");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			CommonDocumentUtils.startHook(logger, "saveMasterFromTemp");
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			productMasterService.saveCorporateMasterFromTemp(mappingId);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saveMasterFromTemp==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/saveCorporateInTemp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCorporateInTemp(
			@RequestBody CorporateProduct corporateProduct,HttpServletRequest request) {
		CommonDocumentUtils.startHook(logger, "save");
		System.out.println("json"+corporateProduct.toString());
		try {
			if (corporateProduct == null) {
				logger.warn("corporateProduct Object can not be empty ==>",
						corporateProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (corporateProduct.getId() == null) {
				logger.warn("corporateProduct id can not be empty ==>", corporateProduct);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			//Long userId=1755l;
			if(userId==null)
			{
				logger.warn("userId  id can not be empty ==>", userId);
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			corporateProduct.setUserId(userId);
			boolean response = productMasterService.saveCorporateInTemp(corporateProduct);
			if (response) {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				CommonDocumentUtils.endHook(logger, "save");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("Error while saving corporateProduct  Parameter==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
