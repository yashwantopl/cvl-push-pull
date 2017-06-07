package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OwnershipDetail;

/**
 * @author Sanket
 *
 */
public interface OwnershipDetailsRepository extends JpaRepository<OwnershipDetail, Long> {

	@Query("from OwnershipDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<OwnershipDetail> listOwnershipFromAppId(@Param("id") Long id, @Param("userId") Long userId);

}
