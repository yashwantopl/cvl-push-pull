package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateCoApplicantDetail;

public interface CorporateCoApplicantRepository extends JpaRepository<CorporateCoApplicantDetail, Long>{

	@Query("from CorporateCoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.applicationId.userId =:userId and cd.isActive = true and cd.id =:id")
	public CorporateCoApplicantDetail get(@Param("applicationId") Long applicationId, @Param("userId") Long userId,
			@Param("id") Long id);

	@Modifying
	//@Query("UPDATE CoApplicantDetail o set o.isActive = false where o.isActive = true and o.applicationId.id =:applicationId and o.applicationId.userId =:userId and o.id =:id")
	@Query("update CorporateCoApplicantDetail coa set coa.isActive = false,coa.modifiedDate = NOW() where coa.applicationId.id =:applicationId  and coa.id =:id and coa.isActive = true")
	public int inactiveCoApplicant(@Param("applicationId") Long applicationId,
			@Param("id") Long id);

	@Query("from CorporateCoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and cd.applicationId.userId =:userId ORDER BY cd.id")
	public List<CorporateCoApplicantDetail> getList(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select count(cd.id) from CorporateCoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and cd.applicationId.userId =:userId ORDER BY cd.id")
	public Long getCoAppCountByApplicationAndUserId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select cd.id from CorporateCoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true")
	public List<Long> getCoAppIds(@Param("applicationId") Long applicationId);
	
	@Query("select cd.applicationId.id from CorporateCoApplicantDetail cd where cd.id =:id and cd.isActive = true")
	public Long getApplicantIdById(@Param("id") Long id);
	
}
