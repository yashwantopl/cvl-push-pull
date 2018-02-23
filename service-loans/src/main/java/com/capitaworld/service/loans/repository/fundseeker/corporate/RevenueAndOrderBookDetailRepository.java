package com.capitaworld.service.loans.repository.fundseeker.corporate;


import com.capitaworld.service.loans.domain.fundseeker.corporate.RevenueAndOrderBookDetail;
import com.capitaworld.service.loans.model.teaser.finalview.RevenueAndOrderBookResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sanket
 *
 */
public interface RevenueAndOrderBookDetailRepository extends JpaRepository<RevenueAndOrderBookDetail, Long> {

	@Modifying
	@Transactional
	@Query("update RevenueAndOrderBookDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveRevenueAndOrderBookDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update RevenueAndOrderBookDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveRevenueAndOrderBookDetailsByAppId(@Param("applicationId")Long applicationId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.RevenueAndOrderBookResponse(a.clientName,a.geography,a.ordersInHand,a.potentialOrders,a.revenues) from RevenueAndOrderBookDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<RevenueAndOrderBookResponse> listByApplicationId(@Param("applicationId") Long applicationId);
}
