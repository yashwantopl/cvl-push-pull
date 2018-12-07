package com.capitaworld.service.loans.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.service.sanction.LoanSanctionService;

@Component
public class APIScheduled {
	
	private static final Logger logger = LoggerFactory.getLogger(APIScheduled.class);
	
	@Autowired
	private LoanSanctionService loanSanctionService;
	
	//@Scheduled(cron = "0 0/30 1-9 * * *")
	public void saveSanctionAndDisbursementDetailFromBank() {
		logger.info("============ start calling saveSanctionAndDisbursementDetailFromBank(){} call at scheduled time==============  ");
		try {
			loanSanctionService.saveSanctionAndDisbursementDetailsFromBank();
		} catch (Exception e) {
			
			e.printStackTrace();
		} 	
	}	
}
