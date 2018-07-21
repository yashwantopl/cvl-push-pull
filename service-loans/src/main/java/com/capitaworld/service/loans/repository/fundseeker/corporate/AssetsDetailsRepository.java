package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsRepository extends JpaRepository<AssetsDetails, Long> {
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.loanApplicationMaster.id = :applicationId and a.isActive = true")
	public void inActiveAssetsDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from AssetsDetails a where a.loanApplicationMaster.id = :appId and a.year = :yr and a.isActive = true")
	public AssetsDetails getAssetsDetails(@Param("appId") Long applicationId, @Param("yr") String year);
	
	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true")
	public List<AssetsDetails> getByApplicationId(@Param("applicationId") Long applicationId);
	
	public AssetsDetails findByIdAndIsActive(Long id, Boolean isActive);
	
	@Query("select o from AssetsDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement ORDER By o.year ASC ")
	public List<AssetsDetails> getAssetsDetailsByApplicationId(@Param("applicationId") Long applicationId, @Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select a.receivableOtherThanDefferred, a.exportReceivables, a.inventory ,a.advanceToSupplierRawMaterials , a.grossBlock from AssetsDetails a where a.loanApplicationMaster.id =:applicationId  AND year = (SELECT  max(a.year) FROM AssetsDetails a WHERE a.loanApplicationMaster.id =:applicationId AND a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement)")
	public List<Object[]> getCMADetail(@Param("applicationId") Long applicationId,@Param("financialYearlyStatement") String financialYearlyStatement);
}
