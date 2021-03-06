package com.opl.service.loans.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.service.fundseeker.corporate.ProposalService;

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
		  logger.error(CommonUtils.EXCEPTION,e);
	  }
	 }
	
/*	@Autowired
	private FPAsyncComponent fp;

	@Scheduled(initialDelay = 10,fixedDelay=100000)
	 public void runF(){
	  logger.info("Entry ScheduledTasks proposal");
	  try {
		  logger.info("Proposasl Schedule Call................. ");


			Map<String, Object> parameters = new HashMap<>();
			CorporateApplicantRequest applicantRequest = new CorporateApplicantRequest();
			applicantRequest.setEmail("maaz.shaikh@capitaworld.com");
			applicantRequest.setUserId(3304L);
			parameters.put("application_id", 4041); // app -4041  userId - 3304
			parameters.put("product_type", "NTB");
			try {
				fp.sendMailToFsWhenMakerAcceptPorposal(parameters, applicantRequest, "Maaz Shaikh");
			} catch (Exception e) {
				logger.error("Exception inside main class:",e);
			}


	   logger.info("Exit ScheduledTasks proposal");
	  } catch (Exception e) {
		  logger.error(CommonUtils.EXCEPTION,e);
	  }
 	 } */
}
