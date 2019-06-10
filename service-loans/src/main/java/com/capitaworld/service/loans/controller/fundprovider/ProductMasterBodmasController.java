package com.capitaworld.service.loans.controller.fundprovider;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProductConditionResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.ProductParameterRequest;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterBodmasService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/product_master_bodmas", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductMasterBodmasController {

    private static final Logger logger = LoggerFactory.getLogger(ProductMasterBodmasController.class);

    private static final String USER_ID_CAN_NOT_BE_EMPTY_MSG = "userId can not be empty ==>{}";
    private static final String ADD_PRODUCT = "addProduct";
    private static final String ADD_PRODUCT_PARAMETER = "addProductParameter";
    private static final String USER_ID_REQUIRE_TO_GET_PRODUCT_DETAILS_MSG = "UserId Require to get product Details ==>{}";
    private static final String ERROR_WHILE_GETTING_PRODUCTS_DETAILS_MSG = "Error while getting Products Details ==> {}";
    private static final String GET_LIST_BY_USER_TYPE = "getListByUserType";

    @Autowired
    private ProductMasterBodmasService productMasterBodmasService;


    @PostMapping(value = "/addProduct")
    public ResponseEntity<LoansResponse> addProduct(@RequestBody AddProductRequest addProductRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        CommonDocumentUtils.startHook(logger, ADD_PRODUCT);
        try {
            // request must not be null

            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);

            if (userId == null) {
                logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if (addProductRequest == null) {
                logger.warn("addProductRequest Object can not be empty ==>" + addProductRequest);
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            addProductRequest.setUserId(userId);
            if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
                addProductRequest.setClientId(clientId);
            }

            Long response = productMasterBodmasService.saveOrUpdate(addProductRequest, userOrgId);
            if (response != null) {
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value(), response), HttpStatus.OK);
            } else {
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error while saving addProduct Details==>", e);
            return new ResponseEntity<>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @PostMapping(value = "/addProductParameter")
    public ResponseEntity<LoansResponse> addProductParameter(@RequestBody ProductParameterRequest productParameterRequest,
                                                             HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        CommonDocumentUtils.startHook(logger, ADD_PRODUCT_PARAMETER);
        try {
            // request must not be null

            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);

            if (userId == null) {
                logger.warn(USER_ID_CAN_NOT_BE_EMPTY_MSG + userId);
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if (productParameterRequest == null) {
                logger.warn("addProductParameter Object can not be empty ==>" + productParameterRequest);
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            productParameterRequest.setUserId(userId);


            Boolean response = productMasterBodmasService.saveCondition(productParameterRequest);
            if (response) {
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT_PARAMETER);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.SUCCESSFULLY_SAVED, HttpStatus.OK.value()), HttpStatus.OK);
            } else {
                CommonDocumentUtils.endHook(logger, ADD_PRODUCT_PARAMETER);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error while saving addProduct Details==>", e);
            return new ResponseEntity<>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getList")
    public ResponseEntity<LoansResponse> getList(HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        CommonDocumentUtils.startHook(logger, CommonUtils.GET_LIST);
        try {
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (userId == null) {
                logger.warn(USER_ID_REQUIRE_TO_GET_PRODUCT_DETAILS_MSG + userId);
                CommonDocumentUtils.endHook(logger, CommonUtils.GET_LIST);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
            List<ProductMasterRequest> response = productMasterBodmasService.getList(userId, userOrgId);
            LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
            loansResponse.setListData(response);
            CommonDocumentUtils.endHook(logger, CommonUtils.GET_LIST);
            return new ResponseEntity<>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_GETTING_PRODUCTS_DETAILS_MSG, e);
            return new ResponseEntity<>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getListByUserType/{userType}/{applicationStage}/{status}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getListByUserType(HttpServletRequest request,
                                                           @PathVariable(value = "userType") String userType, @PathVariable(value = "applicationStage") String applicationStage,
                                                           @PathVariable(value = "status") String status,@RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        CommonDocumentUtils.startHook(logger, GET_LIST_BY_USER_TYPE);
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
                logger.warn(USER_ID_REQUIRE_TO_GET_PRODUCT_DETAILS_MSG, userId);
                CommonDocumentUtils.endHook(logger, GET_LIST_BY_USER_TYPE);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if (userType == null) {
                logger.warn("userType Require to get product Details ==>{}", userId);
                CommonDocumentUtils.endHook(logger, GET_LIST_BY_USER_TYPE);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            //List<ProductMasterRequest> response = productMasterService.getListByUserType(userId, userType);
            LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
            loansResponse.setListData(productMasterBodmasService.getListByUserType(userId, Integer.parseInt(CommonUtils.decode(userType)), Integer.parseInt(CommonUtils.decode(applicationStage)),Integer.parseInt(CommonUtils.decode(status)), userOrgId));
            CommonDocumentUtils.endHook(logger, GET_LIST_BY_USER_TYPE);
            return new ResponseEntity<>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_GETTING_PRODUCTS_DETAILS_MSG, e);
            return new ResponseEntity<>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getConditionsList/{productId}")
    public ResponseEntity<LoansResponse> getList(@PathVariable(value = "productId") Long productId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        CommonDocumentUtils.startHook(logger, CommonUtils.GET_LIST);
        try {
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (userId == null) {
                logger.warn(USER_ID_REQUIRE_TO_GET_PRODUCT_DETAILS_MSG + userId);
                CommonDocumentUtils.endHook(logger, CommonUtils.GET_LIST);
                return new ResponseEntity<>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            Long userOrgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
            List<ProductConditionResponse> response = productMasterBodmasService.getConditionsByProductId(productId);
            LoansResponse loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
            loansResponse.setListData(response);
            CommonDocumentUtils.endHook(logger, CommonUtils.GET_LIST);
            return new ResponseEntity<>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error(ERROR_WHILE_GETTING_PRODUCTS_DETAILS_MSG, e);
            return new ResponseEntity<>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
