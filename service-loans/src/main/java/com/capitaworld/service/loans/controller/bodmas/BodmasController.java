package com.capitaworld.service.loans.controller.bodmas;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.bodmas.domain.BodmasReqRes;
import com.capitaworld.bodmas.domain.CalculationReqRes;
import com.capitaworld.bodmas.domain.FolderMasterRequest;
import com.capitaworld.bodmas.domain.FormulaMasterRequest;
import com.capitaworld.bodmas.model.BodmasResponse;
import com.capitaworld.itr.api.utils.CommonUtils;
import com.capitaworld.service.loans.service.bodmas.BodmasService;

@RestController
@RequestMapping("/bodmas")
public class BodmasController {
	
    private static final Logger logger = LoggerFactory.getLogger(BodmasController.class);

    @Autowired
    private BodmasService bodmasService;
    
    
    @GetMapping(value = "/ping", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasResponse> ping() {
   	    return new ResponseEntity<BodmasResponse>(new BodmasResponse("Response", HttpStatus.OK.value(), bodmasService.ping()), HttpStatus.OK) ;
    }
    
    @PostMapping(value="/list_folder_master", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> listFolderMaster(@RequestBody FolderMasterRequest folderMasterRequest, HttpServletRequest request) {
    	return bodmasService.listFolderMaster(folderMasterRequest);
    }
    
    @PostMapping(value="/save_or_update_folder_master", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> saveOrUpdateFolderMaster(@RequestBody FolderMasterRequest folderMasterRequest) {
    	return bodmasService.saveOrUpdateFolderMaster(folderMasterRequest);
    }
    
    @PostMapping(value="/list_formula_master", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> listFormulaMaster(@RequestBody FormulaMasterRequest folderMasterRequest) {
    	return bodmasService.listFormulaMaster(folderMasterRequest);
    }
    
    @PostMapping(value="/save_or_update_formula_master", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> saveOrUpdateFormulaMaster(@RequestBody FormulaMasterRequest folderMasterRequest) {
    	return bodmasService.saveOrUpdateFormulaMaster(folderMasterRequest);
    }
    
    @PostMapping(value="/calculate_formula_list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> calculateFormulaList(@RequestBody List<CalculationReqRes> calculationReqResList) {
    	return bodmasService.calculateFormulaList(calculationReqResList);
    }
    
    @PostMapping(value="/listDropDownValueRequest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BodmasReqRes> listDropDownValueRequest(@RequestBody BodmasReqRes bodmasReqRes) {
    	return bodmasService.listDropDownValueRequest(bodmasReqRes);
    }

}
