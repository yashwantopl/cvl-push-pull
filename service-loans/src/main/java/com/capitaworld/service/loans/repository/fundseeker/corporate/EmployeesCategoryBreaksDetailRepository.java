package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.EmployeesCategoryBreaksDetail;

/**
 * @author Sanket
 *
 */
public interface EmployeesCategoryBreaksDetailRepository extends JpaRepository<EmployeesCategoryBreaksDetail, Long> {

	@Modifying
	@Transactional
	@Query("update EmployeesCategoryBreaksDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveemployeesCategoryBreaksDetails(@Param("sId")Long storageDetailsId);

}
