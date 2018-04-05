package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public interface OperatingStatementDetailsRepository  extends JpaRepository<OperatingStatementDetails, Long>{
	
	@Modifying
	@Transactional
	@Query("update OperatingStatementDetails o set o.isActive = false where o.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);

	@Modifying
	@Transactional
	@Query("update OperatingStatementDetails o set o.isActive = false where o.loanApplicationMaster.id = :applicationId and o.isActive = true")
	public void inActiveAssetsDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from OperatingStatementDetails o where o.loanApplicationMaster.id = :appId and o.year = :yr and o.isActive = true")
	public OperatingStatementDetails getOperatingStatementDetails(@Param("appId") Long applicationId, @Param("yr") String year);
	
	@Query("select o from OperatingStatementDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true")
	public List<OperatingStatementDetails> getByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("select o from OperatingStatementDetails o where o.loanApplicationMaster.id = :applicationId and o.isActive = true  and o.year IN :yearList and o.financialYearlyStatement =:financialYearlyStatement ORDER BY o.year ASC ")
	public List<OperatingStatementDetails> getOperatingStatementDetailsByApplicationId(@Param("applicationId") Long applicationId, @Param("yearList") List<String> yearList, @Param("financialYearlyStatement") String financialYearlyStatement);
	
	@Query("select max(a.year) , a.domesticSales , a.exportSales ,a.netProfitOrLoss, a.depreciation , a.provisionForDeferredTax from OperatingStatementDetails a where a.loanApplicationMaster.id= :applicationId and a.isActive=true AND a.financialYearlyStatement =:financialYearlyStatement ")
	public List<Object[]> getCMADetail(@Param("applicationId") Long applicationId, @Param("financialYearlyStatement") String financialYearlyStatement );
}
