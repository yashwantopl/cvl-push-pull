package com.capitaworld.service.loans.service.colending;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ClientListingCoLending;
import org.json.simple.JSONObject;

import java.util.List;

public interface CoLendingFlowService {

	public List<ClientListingCoLending> clientListCoLending(int pageIndex, int size, Long npUserId) throws LoansException;

    public JSONObject nbfcClientCount(Long nbfcUserId) throws LoansException;

    public Boolean calculateBlendedRateNbfc(Long applicationId) throws LoansException;

}
