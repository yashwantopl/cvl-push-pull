package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.RevenueAndOrderBookDetail;

public interface ProfitibilityStatementDetailRepository extends JpaRepository<ProfitibilityStatementDetail, Long>{

	@Modifying
	@Transactional
	@Query("update ProfitibilityStatementDetail p set p.isActive = false where p.storageDetailsId= :sId")
	public void inActiveProfitibilityStatementDetail(@Param("sId") Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update ProfitibilityStatementDetail p set p.isActive = false where p.applicationId.id = :applicationId and p.isActive = true")
	public void inActiveProfitibilityStatementDetailByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from ProfitibilityStatementDetail p where p.applicationId.id = :appId and p.year = :yr and p.isActive = true")
	public ProfitibilityStatementDetail getProfitibilityStatementDetail(@Param("appId") Long applicationId, @Param("yr") String year);
	
	@Query("select o from ProfitibilityStatementDetail o where o.applicationId.id = :applicationId and o.isActive = true")
	public List<ProfitibilityStatementDetail> getByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("select o from ProfitibilityStatementDetail o where o.applicationId.id = :applicationId and o.isActive = true and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement  ORDER BY o.year ASC")
	public List<ProfitibilityStatementDetail> getProfitibilityStatementDetailByApplicationId(@Param("applicationId") Long applicationId,@Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	public List<ProfitibilityStatementDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
	
	
}
