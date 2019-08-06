/**
 * 
 */
package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiAssetsLiabilityDetails;
import com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq;

/**
 * @author harsukh.ghumaliya
 *
 */
public interface MfiAssetsDetailsRepository extends JpaRepository<MfiAssetsLiabilityDetails, Long> {

	@Query("select new com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq(fn.applicationId,fn.assetsLiabilityType,fn.assetOwnerDetail,fn.amount,fn.particulars,fn.type) from MfiAssetsLiabilityDetails fn where fn.applicationId = :appId and fn.type=1")
	public List<MfiAssetsDetailsReq> findAssetsDetailsByAppId(@Param("appId") Long appId);
	
	
	@Query("select new com.capitaworld.service.loans.model.micro_finance.MfiAssetsDetailsReq(fn.applicationId,fn.assetsLiabilityType,fn.amount,fn.outstanding,fn.type,fn.particulars) from MfiAssetsLiabilityDetails fn where fn.applicationId = :appId and fn.type=2")
	public List<MfiAssetsDetailsReq> findLiabilityDetailsByAppId(@Param("appId") Long appId);
}
