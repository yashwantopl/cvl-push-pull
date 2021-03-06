package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.EmployeesCategoryBreaksResponse;
import com.opl.service.loans.domain.fundseeker.corporate.EmployeesCategoryBreaksDetail;

/**
 * @author Sanket
 *
 */
public interface EmployeesCategoryBreaksDetailRepository extends JpaRepository<EmployeesCategoryBreaksDetail, Long> {

	@Modifying
	@Transactional
	@Query("update EmployeesCategoryBreaksDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveemployeesCategoryBreaksDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update EmployeesCategoryBreaksDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveemployeesCategoryBreaksDetailsByAppId(@Param("applicationId") Long applicationId);

	@Query("select new com.opl.mudra.api.loans.model.teaser.finalview.EmployeesCategoryBreaksResponse(a.employment,a.employmentStatusFuture,a.employmentStatusPresent) from EmployeesCategoryBreaksDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<EmployeesCategoryBreaksResponse> listByApplicationId(@Param("applicationId")Long applicationId);
	
	public List<EmployeesCategoryBreaksDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
}
