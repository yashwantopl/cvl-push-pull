package com.capitaworld.service.loans.service.bodmas.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.capitaworld.bodmas.domain.BodmasReqRes;
import com.capitaworld.bodmas.domain.CalculationReqRes;
import com.capitaworld.bodmas.domain.FolderMasterRequest;
import com.capitaworld.bodmas.domain.FormulaMasterRequest;
import com.capitaworld.bodmas.exception.BodmasException;
import com.capitaworld.service.BodmasClient;
import com.capitaworld.service.loans.service.bodmas.BodmasService;

@Service
@Transactional
public class BodmasServiceImpl implements BodmasService {

	@Autowired
	BodmasClient bodmasclient ; 
	
	
	 private final Logger logger = LoggerFactory.getLogger(BodmasServiceImpl.class);
	@Override
	public String ping() {
		
		String response = "";  
		try {
			response =   bodmasclient.ping();
		} catch (BodmasException e) {
			System.out.println("Error while calling bodmas client -------");
			e.printStackTrace();
		}
		return response;
	}


	@Override
	public ResponseEntity<BodmasReqRes> listFolderMaster(FolderMasterRequest req) {
		BodmasReqRes response = null ;   
		try {
			response  = bodmasclient.listFolderMaster(req);
		} catch (Exception e) {
			logger.error("Error while fetching folder", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error while listing folder master", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<BodmasReqRes> saveOrUpdateFolderMaster(FolderMasterRequest req) {
		BodmasReqRes response = null ; 
		try {
			response = bodmasclient.saveOrUpdateFolderMaster(req);
		} catch (Exception e) {
			logger.error("Error while saving folder ", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error while saving folder master", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<BodmasReqRes> listFormulaMaster(FormulaMasterRequest req) {
		
		BodmasReqRes response = null ; 
		try {
			response = bodmasclient.listFormulaMaster(req);
		} catch (Exception e) {
			logger.error("Error while fetching formula", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error while listing formula master", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<BodmasReqRes> saveOrUpdateFormulaMaster(FormulaMasterRequest req) {
		
		BodmasReqRes response = null ; 
		try {
			response = bodmasclient.saveOrUpdateFormulaMaster(req);
		} catch (Exception e) {
			logger.error("Error while saving formula", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error while saving formula", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	
	}


	@Override
	public ResponseEntity<BodmasReqRes> calculateFormulaList(List<CalculationReqRes> calculationReqResList) {
		BodmasReqRes response = null ; 
		try {
			response = bodmasclient.calculateFormulaList(calculationReqResList);
		} catch (Exception e) {
			logger.error("Error while calculating formula listing", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error while calulating formula list ", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	}


	@Override
	public ResponseEntity<BodmasReqRes> listDropDownValueRequest(BodmasReqRes bodmasReqRes) {
		BodmasReqRes response = null ; 
		try {
			response = bodmasclient.listDropDownValueRequest(bodmasReqRes);
		} catch (Exception e) {
			logger.error("Error while list dropdown value", e);
			return new ResponseEntity<BodmasReqRes>(new BodmasReqRes("Error fetching dropdown list", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
		return new ResponseEntity<BodmasReqRes>(response,HttpStatus.OK);
	}
	

}
