package com.opl.service.loans.repository.fundseeker;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundseeker.ApplicationCheckerAudit;

public interface ApplicationCheckerAuditRepository extends JpaRepository<ApplicationCheckerAudit,Long>{
}
