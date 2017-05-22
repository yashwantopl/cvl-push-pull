package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProposedProductDetail;

/**
 * @author Sanket
 *
 */
public interface ProposedProductDetailsRepository extends JpaRepository<ProposedProductDetail, Long> {
	
	@Query("from ProposedProductDetail  a where a.applicationId.id=:id AND  a.applicationId.userId =:userId and a.isActive=true")
	public List<ProposedProductDetail> listProposedProductFromAppId(@Param("id") Long id, @Param("userId") Long userId);

}
