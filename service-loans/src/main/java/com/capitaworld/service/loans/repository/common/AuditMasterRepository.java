package com.capitaworld.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capitaworld.service.loans.domain.common.AuditMaster;

public interface AuditMasterRepository  extends JpaRepository<AuditMaster, Long> {

}
