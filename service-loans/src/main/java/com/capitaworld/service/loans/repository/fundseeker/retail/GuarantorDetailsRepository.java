package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;

/**
 * @author Sanket
 *
 */
public interface GuarantorDetailsRepository extends JpaRepository<GuarantorDetails, Long> {

	@Query("from GuarantorDetails gua where gua.applicationId.id =:applicationId and gua.applicationId.userId =:userId and gua.isActive = true and gua.id =:id")
	public GuarantorDetails get(@Param("applicationId") Long applicationId, @Param("userId") Long userId,
			@Param("id") Long id);

	@Modifying
	@Query("update GuarantorDetails gua set gua.isActive = false,gua.modifiedDate = NOW() where gua.applicationId.id =:applicationId and gua.id =:id and gua.isActive = true")
	public int inactiveGuarantor(@Param("applicationId") Long applicationId, @Param("id") Long id);

	@Query("from GuarantorDetails gua where gua.applicationId.id =:applicationId and gua.isActive = true and gua.applicationId.userId =:userId ORDER BY gua.id")
	public List<GuarantorDetails> getList(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select count(gua.id) from GuarantorDetails gua where gua.applicationId.id =:applicationId and gua.isActive = true and gua.applicationId.userId =:userId ORDER BY gua.id")
	public Long getGuarantorCountByApplicationAndUserId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select gua.id from GuarantorDetails gua where gua.applicationId.id =:applicationId and gua.isActive = true and gua.applicationId.userId =:userId ORDER BY gua.id")
	public List<Long> getGuarantorIds(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select gua.applicationId.id from GuarantorDetails gua where gua.id =:id and gua.isActive = true")
	public Long getApplicantIdById(@Param("id") Long id);
}
