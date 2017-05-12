package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProjectImplementationScheduleDetail;

/**
 * @author Sanket
 *
 */
public interface ProjectImplementationScheduleDetailRepository
		extends JpaRepository<ProjectImplementationScheduleDetail, Long> {

	@Modifying
	@Transactional
	@Query("update ProjectImplementationScheduleDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveProjectImplementationScheduleDetails(@Param("sId")Long storageDetailsId);

}
