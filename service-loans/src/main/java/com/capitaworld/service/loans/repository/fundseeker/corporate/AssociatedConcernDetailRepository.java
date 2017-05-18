package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssociatedConcernDetail;

/**
 * @author Sanket
 *
 */
public interface AssociatedConcernDetailRepository extends JpaRepository<AssociatedConcernDetail, Long> {

	@Query("select o from AssociatedConcernDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<AssociatedConcernDetail> listAssociatedConcernFromAppId(@Param("id")Long id);

}
