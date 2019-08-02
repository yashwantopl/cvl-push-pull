package com.capitaworld.service.loans.repository.fundseeker.agri;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.agri.CorpDetail;

/**
 * @author Akshay
 *
 */
public interface CorpDetailRepository extends JpaRepository<CorpDetail, Long> {

	public List<CorpDetail> findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	
	@Modifying
	@Query("update CorpDetail crp set crp.isActive = false where crp.isActive = true and crp.applicationId =:applicationId")
	public int inactive(@Param("applicationId") Long applicationId);
	
}
