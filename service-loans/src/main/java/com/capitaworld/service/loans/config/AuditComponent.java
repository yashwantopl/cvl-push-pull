package com.capitaworld.service.loans.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.domain.common.AuditMaster;
import com.capitaworld.service.loans.repository.common.AuditMasterRepository;

@Component
public class AuditComponent {
	
	
	public static final int PRELIM_INFO = 1;
	public static final int DETAILED_INFO = 2;
	public static final int SCORING_DETAILS = 3;
	public static final int IRR_DETAILS = 4;
	public static final int DDR_DETAILS = 5;
	
	@Autowired
	private AuditMasterRepository auditMasterRepository;
	
	
	private static final Logger logger = LoggerFactory.getLogger(AuditComponent.class);
	
	@Async
	public void updateAudit(Integer type, Long applicationId, Long userId, boolean isSuccess) {
		AuditMaster auditMaster = new AuditMaster();
		auditMaster.setApplicationId(applicationId);
		auditMaster.setUserId(userId);
		auditMaster.setType(type);
		auditMaster.setCreatedBy(userId);
		auditMaster.setCreatedDate(new Date());
		auditMaster.setIsActive(true);
		auditMaster.setIsSuccess(isSuccess);
		auditMasterRepository.save(auditMaster);
		logger.info("SUCCESSFULLY UPADATE AUDIT DATE-----------------TYPE-->" + type + "------Application-----> "+ applicationId);
	}
	
	
}
