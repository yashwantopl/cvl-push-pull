package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.GstRelatedPartyRequest;

/**
  *@auther : Maaz Shaikh
  */
public interface RelatedPartyService {

	public Boolean saveRelatedParty(GstRelatedPartyRequest[] request)  throws Exception;
}
