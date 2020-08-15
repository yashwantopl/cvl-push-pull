package com.opl.service.loans.service.common;


import java.io.IOException;

import com.opl.mudra.api.dms.exception.DocumentException;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.common.RecentProfileViewDetailResponse;

/**
 * @author Sanket
 *
 */
public interface RecentViewService {

	RecentProfileViewDetailResponse getRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException;

	RecentProfileViewDetailResponse getRecentViewDetailListByProdId(Long productId, Long userId)throws DocumentException, IOException, LoansException;

	RecentProfileViewDetailResponse getLatestRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException;

	RecentProfileViewDetailResponse getLatestRecentViewDetailListByProdId(Long applicationId, Long userId) throws LoansException;

}
