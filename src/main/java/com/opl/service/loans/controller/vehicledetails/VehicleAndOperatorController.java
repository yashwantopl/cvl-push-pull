package com.opl.service.loans.controller.vehicledetails;

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
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.VehicleOperatorRequest;
import com.opl.service.loans.service.vehicledetails.VehicleOperatorService;

@RestController
@RequestMapping("/vehicle_operator")
public class VehicleAndOperatorController {
	
	private static final Logger logger = LoggerFactory.getLogger(VehicleAndOperatorController.class);
	
	@Autowired
    VehicleOperatorService vehicleOperatorService;

    @RequestMapping(value = "/getDetail/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getDetail(@PathVariable Long applicationId, HttpServletRequest httpRequest) {
        try {
        	logger.info("start /getDetail/"+applicationId);
            VehicleOperatorRequest request = vehicleOperatorService.getByApplicationId(applicationId);
            if (!CommonUtils.isObjectNullOrEmpty(request)) {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data fetched Successfully!!", HttpStatus.OK.value(), request), HttpStatus.OK);
            } else {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data Not Found!!", HttpStatus.NOT_FOUND.value()), HttpStatus.OK);
            }
        }catch (Exception e){
        	logger.error(CommonUtils.EXCEPTION,e);
        }
        return new ResponseEntity<LoansResponse>(new LoansResponse("Error while fetching Vehicle & Operator Details!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/saveDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveDetail(@RequestBody VehicleOperatorRequest request, HttpServletRequest httpRequest) {
        try {
        	logger.info("start /saveDetail");
            Long userId =  (Long) httpRequest.getAttribute(CommonUtils.USER_ID);

            Boolean saveOrUpdate = vehicleOperatorService.saveDetail(request,userId);
            if (saveOrUpdate) {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved Successfully!!", HttpStatus.OK.value()), HttpStatus.OK);
            } else {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Something Went Wrong!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        }catch (Exception e){
        	logger.error(CommonUtils.EXCEPTION,e);
        }
        return new ResponseEntity<LoansResponse>(new LoansResponse("Error while saving Vehicle & Operator Details!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
