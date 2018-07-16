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

	public List<CorporateDirectorIncomeDetails> findByApplicationIdAndDirectorIdAndIsActive(Long applicationId, Long directorId, Boolean isActive);
	
	@Query("select sum(salary) from CorporateDirectorIncomeDetails cd where cd.applicationId.id =:applicationId and cd.directorId =:directorId and cd.isActive=true")
	public Double getTotalSalaryByApplicationIdAndDirectorId(@Param("applicationId") Long applicationId, @Param("directorId") Long directorId);

}
