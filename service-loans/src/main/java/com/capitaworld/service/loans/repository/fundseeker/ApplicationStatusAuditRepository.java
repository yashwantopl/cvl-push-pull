package com.capitaworld.service.loans.repository.fundseeker;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusAudit;


public interface ApplicationStatusAuditRepository extends JpaRepository<ApplicationStatusAudit, Long> {

	@Query("select lm from ApplicationStatusAudit lm where lm.applicationId=:appId and lm.applicationStatusMaster.id =:id and lm.npAssigneeId=:assigneeId and  lm.isActive = true ")
	public List<ApplicationStatusAudit> getApplicationByAssigneeIdBasedOnStatus(@Param("appId")Long applicationId,@Param("id") Long applicationStatusId,@Param("assigneeId") Long assigneeId);
}
