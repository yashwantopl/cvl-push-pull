package com.opl.service.loans.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.CommonAuditTable;

public interface CommonAuditTableRepository extends JpaRepository<CommonAuditTable, Long> {

}
