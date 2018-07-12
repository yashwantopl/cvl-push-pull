package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;

/**
 * @author Sanket
 *
 */
public interface FinancialArrangementDetailsRepository extends JpaRepository<FinancialArrangementsDetail, Long> {

	@Query("select o from FinancialArrangementsDetail o where o.applicationId.id =:id  and o.applicationId.userId =:userId and o.isActive = true and o.directorBackgroundDetail IS NULL")
	public List<FinancialArrangementsDetail> listSecurityCorporateDetailFromAppId(@Param("id")Long id, @Param("userId") Long userId);
	
	@Query("select o from FinancialArrangementsDetail o where o.applicationId.id =:id and o.isActive = true and o.directorBackgroundDetail IS NULL")
	public List<FinancialArrangementsDetail> listSecurityCorporateDetailFromAppId(@Param("id")Long id);
	
	@Modifying
	@Query("update FinancialArrangementsDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true and pm.directorBackgroundDetail IS NULL")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

	
	@Modifying
	@Query("update FinancialArrangementsDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true and pm.directorBackgroundDetail.id =:directorId")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId,@Param("directorId") Long directorId);
	
	@Query("select sum(o.emi) from FinancialArrangementsDetail o where o.applicationId.id =:id and o.isActive = true and o.directorBackgroundDetail IS NULL")
	public Double getTotalEmiByApplicationId(@Param("id")Long id);

	public FinancialArrangementsDetail findByIdAndIsActive(Long id,Boolean isActive);

	@Query("select o from FinancialArrangementsDetail o where o.applicationId.id =:id  and o.isActive = true and o.directorBackgroundDetail IS NULL")
	public List<FinancialArrangementsDetail> listSecurityCorporateDetailByAppId(@Param("id")Long id);

	@Query("select o from FinancialArrangementsDetail o where o.directorBackgroundDetail.id =:id and o.isActive =:isActive")
	public FinancialArrangementsDetail findByDirectorIdAndIsActive(@Param("id")Long id, @Param("isActive")Boolean isActive);

	@Query("select o from FinancialArrangementsDetail o where o.directorBackgroundDetail.id =:directorId and o.applicationId.id =:applicationId  and o.isActive =:isActive")
	public List<FinancialArrangementsDetail> listFinancialListForPartner(@Param("directorId")Long directorId, @Param("applicationId")Long applicationId, @Param("isActive")Boolean isActive);
}
