package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateDirectorIncomeDetails;

public interface CorporateDirectorIncomeDetailsRepository extends JpaRepository<CorporateDirectorIncomeDetails, Long> {


	@Query("from CorporateDirectorIncomeDetails cd where cd.applicationId.id =:applicationId and cd.directorId =:directorId and cd.isActive=true")
	public CorporateDirectorIncomeDetails getByApplicationIdAndDirectorId(@Param("applicationId") Long applicationId, @Param("directorId") Long directorId);
	
	public CorporateDirectorIncomeDetails findByApplicationIdAndDirectorIdAndYear(Long applicationId, Long directorId, String year);

	public List<CorporateDirectorIncomeDetails> findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);
	
	
	@Query("select sum(totalIncome) from CorporateDirectorIncomeDetails cd where cd.applicationId =:applicationId and cd.directorId =:directorId and cd.isActive=true")
	public Double getTotalIncomeByApplicationIdAndDirectorId(@Param("applicationId") Long applicationId, @Param("directorId") Long directorId);

	@Query("select sum(salary) from CorporateDirectorIncomeDetails cd where cd.applicationId =:applicationId and cd.directorId =:directorId and cd.isActive=true")
	public Double getTotalSalaryByApplicationIdAndDirectorId(@Param("applicationId") Long applicationId, @Param("directorId") Long directorId);

	@Query(value = "select * from fs_corporate_director_income_details cd where cd.is_active = true and cd.application_id =:applicationId and cd.year IN (select max(year) from fs_corporate_director_income_details where application_id =:applicationId GROUP BY director_id )" , nativeQuery = true)
	public List<CorporateDirectorIncomeDetails> getLatestYearDetails(@Param("applicationId")Long applicationId);
	
	
}
