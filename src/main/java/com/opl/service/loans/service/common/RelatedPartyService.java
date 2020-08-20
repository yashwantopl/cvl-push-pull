package com.opl.service.loans.service.common;

import com.opl.mudra.api.loans.model.GstRelatedPartyRequest;

/**
  *@auther : Maaz Shaikh
  */
public interface RelatedPartyService {

	public Boolean saveRelatedParty(GstRelatedPartyRequest[] request)  throws Exception;
	
	public Boolean saveRelatedPartyFlag(GstRelatedPartyRequest request) throws Exception;
	
	public Boolean updateRelatedPartyFilledFlageOnConnect(Long applicationId)throws Exception;
	
	public Boolean checkGstType(GstRelatedPartyRequest request)throws Exception;
}
