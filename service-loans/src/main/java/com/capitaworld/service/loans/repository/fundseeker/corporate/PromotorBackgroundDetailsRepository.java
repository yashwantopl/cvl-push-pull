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

	@Query("select o from PromotorBackgroundDetail o where o.applicationId.id = :id and isActive = true")
	public List<PromotorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id);

}
