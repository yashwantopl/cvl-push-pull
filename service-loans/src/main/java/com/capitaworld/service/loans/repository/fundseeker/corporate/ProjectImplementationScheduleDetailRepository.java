package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.model.teaser.finalview.ProjectImplementationScheduleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProjectImplementationScheduleDetail;

import java.util.List;

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
	
	@Modifying
	@Transactional
	@Query("update ProjectImplementationScheduleDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveProjectImplementationScheduleDetailsByAppId(@Param("applicationId")Long applicationId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.ProjectImplementationScheduleResponse(a.activities,a.commencementDate,a.completionDate,a.timelineTotal) from ProjectImplementationScheduleDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<ProjectImplementationScheduleResponse> listByApplicationId(@Param("applicationId") Long applicationId);
}
