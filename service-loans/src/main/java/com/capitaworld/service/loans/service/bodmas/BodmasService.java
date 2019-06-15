package com.capitaworld.service.loans.service.bodmas;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.capitaworld.bodmas.domain.BodmasReqRes;
import com.capitaworld.bodmas.domain.CalculationReqRes;
import com.capitaworld.bodmas.domain.FolderMasterRequest;
import com.capitaworld.bodmas.domain.FormulaMasterRequest;

public interface BodmasService {
	
	public String ping();

	public ResponseEntity<BodmasReqRes> listFolderMaster(FolderMasterRequest req);
	
	public ResponseEntity<BodmasReqRes> saveOrUpdateFolderMaster(FolderMasterRequest req);
	
	public ResponseEntity<BodmasReqRes> listFormulaMaster(FormulaMasterRequest req);
	
	public ResponseEntity<BodmasReqRes> saveOrUpdateFormulaMaster(FormulaMasterRequest req);
	
	public ResponseEntity<BodmasReqRes> calculateFormulaList(List<CalculationReqRes> calculationReqResList);
	
	public ResponseEntity<BodmasReqRes> listDropDownValueRequest(BodmasReqRes bodmasReqRes);
	
}
