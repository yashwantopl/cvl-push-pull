package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;

/**
 * @author Sanket
 *
 */
public interface CoApplicantDetailRepository extends JpaRepository<CoApplicantDetail, Long> {

	@Query("from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.applicationId.userId =:userId and cd.isActive = true and cd.id =:id")
	public CoApplicantDetail get(@Param("applicationId") Long applicationId, @Param("userId") Long userId,
			@Param("id") Long id);

	@Modifying
	//@Query("UPDATE CoApplicantDetail o set o.isActive = false where o.isActive = true and o.applicationId.id =:applicationId and o.applicationId.userId =:userId and o.id =:id")
	@Query("update CoApplicantDetail coa set coa.isActive = false,coa.modifiedDate = NOW() where coa.applicationId.id =:applicationId  and coa.id =:id and coa.isActive = true")
	public int inactiveCoApplicant(@Param("applicationId") Long applicationId,
			@Param("id") Long id);

	@Query("from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and (cd.isItrSkip = false or cd.isItrSkip = null) and cd.applicationId.userId =:userId ORDER BY cd.id")
	public List<CoApplicantDetail> getList(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select count(cd.id) from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and cd.applicationId.userId =:userId ORDER BY cd.id")
	public Long getCoAppCountByApplicationAndUserId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select count(cd.id) from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and (cd.isOneFormCompleted IS NULL or cd.isOneFormCompleted = false) and (cd.isCibilCompleted IS NULL or cd.isCibilCompleted = false)")
	public Long checkPendingCoAppOnefrom(@Param("applicationId") Long applicationId);
	
	@Query("select cd.id from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and cd.applicationId.userId =:userId ORDER BY cd.id")
	public List<Long> getCoAppIds(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select cd.applicationId.id from CoApplicantDetail cd where cd.id =:id and cd.isActive = true")
	public Long getApplicantIdById(@Param("id") Long id);
	
	public CoApplicantDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	@Query("select rt.firstName,rt.lastName,rt.isOneFormCompleted,rt.isCibilCompleted,rt.id from CoApplicantDetail rt where rt.applicationId.id =:applicationId and rt.isActive = true")
	public List<Object[]> getBasicDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Modifying
	@Query("update CoApplicantDetail pm set pm.isItrCompleted =:flag, pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateITRFlag(@Param("userId") Long userId,@Param("id") Long coAppId, @Param("flag") boolean flag);
	
	@Modifying
	@Query("update CoApplicantDetail pm set pm.isCibilCompleted =:flag, pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateCIBILFlag(@Param("userId") Long userId,@Param("id") Long coAppId, @Param("flag") boolean flag);
	
	@Modifying
	@Query("update CoApplicantDetail pm set pm.isBankStatementCompleted =:flag, pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateBankStatementFlag(@Param("userId") Long userId,@Param("id") Long coAppId, @Param("flag") boolean flag);
	
	@Modifying
	@Query("update CoApplicantDetail pm set pm.isOneFormCompleted =:flag, pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateOneFormFlag(@Param("userId") Long userId,@Param("id") Long coAppId, @Param("flag") boolean flag);


}
