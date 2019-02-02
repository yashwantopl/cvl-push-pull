package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.exceptions.LoansException;

import java.util.Map;

public interface FsDetailsForPdfService {
	public Map getHomeLoanDetails(Long applicantId) throws LoansException;
	public Map getSortedMapForUbi(Long applicantId) throws LoansException;
}
