package com.capitaworld.service.loans.controller.admin;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.admin.ProposalDetailsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProposalDetailController {

    @Autowired
    private ProposalDetailsAdminService proposalDetailsAdminService;

    @PostMapping(value = "/admin/getProposal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> proposalDetail() {
        LoansResponse loansResponse = new LoansResponse();
        loansResponse.setMessage("Successfully listed");
        loansResponse.setStatus(HttpStatus.OK.value());
        loansResponse.setListData(proposalDetailsAdminService.listProposal());
        return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
    }
}
