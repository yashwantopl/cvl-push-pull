package com.capitaworld.service.loans.controller.fundprovider;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundprovider.OrganizationReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dhaval on 02-Dec-17.
 */
@RestController
public class OrganizationReportController {

    @Autowired
    private OrganizationReportsService organizationReportsService;

    private static final Logger logger = LoggerFactory.getLogger(OrganizationReportController.class);

    @GetMapping(value = "/getOrganizationPieValue/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@PathVariable("id") Long id) {
        logger.info("Enter in /getOrganizationPieValue/",id);
        LoansResponse loansResponse = new LoansResponse();
        loansResponse.setData(organizationReportsService.getDetails(id));
        logger.info("response=>",loansResponse);
        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
    }

}
