package com.capitaworld.service.loans.service.fundseeker.agri;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.agri.PrimaryAgriLoanDetailRequest;

public interface PrimaryAgriLoanDetailService {

    public boolean saveOrUpdate(PrimaryAgriLoanDetailRequest  agriLoanDetailRequest , Long userId) throws LoansException;

    public PrimaryAgriLoanDetailRequest get(Long applicationId, Long userId) throws LoansException ;

    /**
     * Getting PrimaryAgriLoanDetailRequest by Application Id
     * @param applicationId
     * @return
     */
    public PrimaryAgriLoanDetailRequest get(Long applicationId) throws LoansException;

}
