package com.capitaworld.service.loans.service.common;

import java.io.IOException;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.loans.model.common.RecentProfileViewDetailResponse;

/**
 * @author Sanket
 *
 */
public interface RecentViewService {

	RecentProfileViewDetailResponse getRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException;

	RecentProfileViewDetailResponse getRecentViewDetailListByProdId(Long productId, Long userId)throws DocumentException, IOException, Exception;

	RecentProfileViewDetailResponse getLatestRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException;

	RecentProfileViewDetailResponse getLatestRecentViewDetailListByProdId(Long applicationId, Long userId) throws Exception;

}
