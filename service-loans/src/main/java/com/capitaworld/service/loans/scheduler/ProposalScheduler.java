package com.capitaworld.service.loans.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.service.fundseeker.corporate.ProposalService;

@Component
public class ProposalScheduler {
	private static final Logger logger = LoggerFactory.getLogger(ProposalScheduler.class.getName());

	@Autowired
	private ProposalService proposalService; 
	
	@Scheduled(fixedDelayString = "${cw.proposal.scheduler.timeout}")
	 public void run(){
	  logger.info("Entry ScheduledTasks proposal");
	  try {
	  //proposalDetailsClient.checkPendingProposal();
		  logger.info("Proposasl Schedule Call................. ");
		  proposalService.checkPendingProposal();
	   logger.info("Exit ScheduledTasks proposal");
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	 }
}
