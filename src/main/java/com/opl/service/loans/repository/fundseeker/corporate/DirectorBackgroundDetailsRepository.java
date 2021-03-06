package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;

public interface DirectorBackgroundDetailsRepository extends JpaRepository<DirectorBackgroundDetail, Long> {

	@Query("from DirectorBackgroundDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and isActive = true")
	public List<DirectorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id,@Param("userId")Long userId);

	@Modifying
	@Query("update DirectorBackgroundDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

	@Query("select sum(o.networth) from DirectorBackgroundDetail o where o.applicationId.id = :applicationId and isActive = true")
	public Double getSumOfDirectorsNetworth(@Param("applicationId") Long applicationId);

	@Query("select max(o.totalExperience) from DirectorBackgroundDetail o where o.applicationId.id = :applicationId and isActive = true")
	public Double getMaxOfDirectorsExperience(@Param("applicationId") Long applicationId);
	
	@Query("from DirectorBackgroundDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<DirectorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id);

	@Query("from DirectorBackgroundDetail o where o.applicationId.id = :id and o.applicationProposalMapping.proposalId =:proposalId and o.isActive = true")
	public List<DirectorBackgroundDetail> listPromotorBackgroundFromAppIdAndProposalId(@Param("id") Long id,@Param("proposalId") Long proposalId);

	public DirectorBackgroundDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	public DirectorBackgroundDetail findByApplicationIdIdAndIsActiveIsTrueAndPanNo(Long applicationId,String pan);
	
	@Query("from DirectorBackgroundDetail o where o.applicationId.id = :appId and o.isMainDirector = true and isActive = true")
	public DirectorBackgroundDetail getByAppIdAndIsMainDirector(@Param("appId") Long appId);
	

	@Modifying
	@Query("update DirectorBackgroundDetail pm set pm.isItrCompleted =:flag,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateITRFlag(@Param("userId") Long userId,@Param("id") Long directorId,@Param("flag") boolean flag);
	
	@Modifying
	@Query("update DirectorBackgroundDetail pm set pm.isCibilCompleted =:flag,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateCIBILFlag(@Param("userId") Long userId,@Param("id") Long directorId,@Param("flag") boolean flag);
	
	@Modifying
	@Query("update DirectorBackgroundDetail pm set pm.isBankStatementCompleted =:flag,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateBankStatementFlag(@Param("userId") Long userId,@Param("id") Long directorId,@Param("flag") boolean flag);
	
	@Modifying
	@Query("update DirectorBackgroundDetail pm set pm.isOneFormCompleted =:flag,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.isActive = true")
	public int updateOneFormFlag(@Param("userId") Long userId,@Param("id") Long directorId,@Param("flag") boolean flag);

	@Query("select count(o) from DirectorBackgroundDetail o where o.applicationId.id = :applicationId and isActive = true")
	public Integer getTotalNoOfDirector(@Param("applicationId") Long applicationId);

	@Query("from DirectorBackgroundDetail o where o.applicationId.id = :applicationId and o.isMainDirector = true and isActive = true")
	public DirectorBackgroundDetail getMainDirectorByApplicationId(@Param("applicationId") Long applicationId);
	
	public DirectorBackgroundDetail findFirstByApplicationIdIdAndIsMainDirectorIsTrueAndIsActiveIsTrueOrderByIdDesc(Long applicationId);
	
	@Query("select o.directorsName from DirectorBackgroundDetail o where o.id =:directorId and o.isActive = true")
	public String getDirectorNamefromDirectorId(@Param("directorId") Long directorId);
	
	@Query("select o.id from DirectorBackgroundDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<Long> getDirectorIdFromApplicationId(@Param("id") Long id);
}
