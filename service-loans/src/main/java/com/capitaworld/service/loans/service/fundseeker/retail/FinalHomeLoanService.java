package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.FinalHomeLoanDetailRequest;

public interface FinalHomeLoanService {

	public boolean saveOrUpdate(FinalHomeLoanDetailRequest finalHomeLoanDetailRequest,Long userId) throws Exception;

	public FinalHomeLoanDetailRequest get(Long id,Long userId) throws Exception;
}
