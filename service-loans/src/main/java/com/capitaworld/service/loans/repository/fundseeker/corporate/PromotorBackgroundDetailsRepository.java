package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PromotorBackgroundDetail;

/**
 * @author Sanket
 *
 */
public interface PromotorBackgroundDetailsRepository extends JpaRepository<PromotorBackgroundDetail, Long> {

	@Query("from PromotorBackgroundDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and isActive = true")
	public List<PromotorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id,@Param("userId")Long userId);

}
