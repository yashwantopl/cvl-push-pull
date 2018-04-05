package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;

public interface LiabilitiesDetailsRepository  extends JpaRepository<LiabilitiesDetails	, Long>{
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails l set l.isActive = false where l.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update LiabilitiesDetails l set l.isActive = false where l.fsLoanApplicationMaster.id = :applicationId and l.isActive = true")
	public void inActiveAssetsDetailsByAppId(@Param("applicationId") Long applicationId);

	@Query("from LiabilitiesDetails l where l.fsLoanApplicationMaster.id = :appId and l.year = :yr and l.isActive = true")
	public LiabilitiesDetails getLiabilitiesDetails(@Param("appId") Long applicationId, @Param("yr") String year);
	
	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.isActive = true")
	public List<LiabilitiesDetails> getByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("select o from LiabilitiesDetails o where o.fsLoanApplicationMaster.id = :applicationId and o.isActive = true and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement ORDER BY o.year ASC")
	public List<LiabilitiesDetails> getLiabilitiesDetailsByApplicationId(@Param("applicationId") Long applicationId,@Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select max(a.year) , a.sundryCreditors , a.advancePaymentsFromCustomers from LiabilitiesDetails a where a.fsLoanApplicationMaster.id= :applicationId  AND a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement ")
	public List<Object[]> getCMADetail(@Param("applicationId") Long applicationId, @Param("financialYearlyStatement") String financialYearlyStatement);
}
