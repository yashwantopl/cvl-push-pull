package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.SecurityCorporateDetail;

/**
 * @author Sanket
 *
 */
public interface SecurityCorporateDetailsRepository extends JpaRepository<SecurityCorporateDetail, Long> {

	@Query("select o from SecurityCorporateDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<SecurityCorporateDetail> listSecurityCorporateDetailFromAppId(@Param("id") Long id, @Param("userId") Long userId);

}
