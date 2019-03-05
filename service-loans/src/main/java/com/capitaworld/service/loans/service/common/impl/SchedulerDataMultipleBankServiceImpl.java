package com.capitaworld.service.loans.service.common.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.bouncycastle.cert.ocsp.Req;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.SchedulerDataMultipleBank;
import com.capitaworld.service.loans.model.SchedulerDataMultipleBankRequest;
import com.capitaworld.service.loans.repository.SchedulerDataMultipleBankRepository;
import com.capitaworld.service.loans.service.common.SchedulerDataMultipleBankService;

@Service
public class SchedulerDataMultipleBankServiceImpl  implements SchedulerDataMultipleBankService{

	@Autowired
	private SchedulerDataMultipleBankRepository repository;
	
	private Logger logger=org.slf4j.LoggerFactory.getLogger(SchedulerDataMultipleBankServiceImpl.class);
	
	@Transactional
	@Override
	public Boolean saveToSchedular(SchedulerDataMultipleBankRequest toBeSave) throws Exception {
		logger.info("inside start saving data in scheduler class");
		List<SchedulerDataMultipleBank> dataList = gateDataOfSchedular(toBeSave.getApplicationId());
		logger.info("data fatch from db==> "+ dataList);
		int dayDiffrence=toBeSave.getDayDiffrence();
		toBeSave.setDayDiffrence(null);
		if(!dataList.isEmpty()) {
			for (SchedulerDataMultipleBank dataToBeCampare : dataList) {
				if(dataToBeCampare != null && dataToBeCampare.getApplicationId() != null) {
					if (toBeSave.getApplicationId() == dataToBeCampare.getApplicationId()) {
						try{
							if(toBeSave.getInpricipleDate() == null) {
								toBeSave.setInpricipleDate(new Timestamp(toBeSave.getInpricipleUtilDate().getTime()));
							}
							Integer diffInDays = (int) ((toBeSave.getInpricipleDate().getTime() - dataToBeCampare.getInpricipleDate().getTime()) / (1000 * 60 * 60 * 24));
							if(diffInDays == dayDiffrence) {
								logger.info("inside day diffrence true condition ==diffInDays"+diffInDays);
								toBeSave.setDayDiffrence(dayDiffrence);
								toBeSaved(toBeSave);
							}else {
								logger.info("day difference not matched == dayDifference:"+dayDiffrence);
							}
						}catch (NullPointerException e) {
							logger.error("null pointer exception :" + e.getMessage());
						}
					}else {
						logger.info("This application is fresh data");
						toBeSaved(toBeSave);
					}
				}
			}
		}else {
			logger.info("This application is fresh data");
			toBeSaved(toBeSave);
		}
		logger.info("inside end of saving data in scheduler class");
		return true;
	}
   /**
    * 500 and 1 are default status for fields
    * */
	private void toBeSaved(SchedulerDataMultipleBankRequest toBeSave) {
		SchedulerDataMultipleBank scheduledDate=new SchedulerDataMultipleBank();
		toBeSave.setIsActive(1);
		toBeSave.setMailStatus(500);
		toBeSave.setSysNotificationStatus(500);
		toBeSave.setSmsStatus(500);
		toBeSave.setIsActive(1);
		toBeSave.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		BeanUtils.copyProperties(toBeSave, scheduledDate);
		logger.info("data-to be saved:"+scheduledDate);
		repository.save(scheduledDate);
		logger.info("application has been saved");
	}
	 
	@Override
	public List<SchedulerDataMultipleBank> gateDataOfSchedular(Long applicationId) throws Exception {
		return repository.getDataByApplicationId(applicationId);
	}

	
	
	public static void main(String[] args) throws Exception {

/*		String dateStart = "11/03/14 09:29:58";
	    String dateStop = "11/03/14 09:33:43";

	    
	    SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");

	    Date d1 = null;
	    Date d2 = null;
	    try {
	        d1 = format.parse(dateStart);
	        d2 = format.parse(dateStop);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }

	    // Get msec from each, and subtract.
	    long diff = d2.getTime() - d1.getTime();
	    long diffSeconds = diff / 1000 % 60;
	    long diffMinutes = diff / (60 * 1000) % 60;
	    long diffHours = diff / (60 * 60 * 1000);
	    System.out.println("Time in seconds: " + diffSeconds + " seconds.");
	    System.out.println("Time in minutes: " + diffMinutes + " minutes.");
	    System.out.println("Time in hours: " + diffHours + " hours.");
	    
	    
	    System.out.println("=========================>");
	    */
	    
		 Date dt1 = new Timestamp(System.currentTimeMillis()-200000000);
		 Date dt2 = new Timestamp(System.currentTimeMillis());

	        long diff = dt2.getTime() - dt1.getTime();
	        long diffSeconds = diff / 1000 % 60;
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000);
	        int diffInDays = (int) ((dt2.getTime() - dt1.getTime()) / (1000 * 60 * 60 * 24));
	        System.out.println(diffInDays);
	        if (diffInDays > 1) {
	            System.err.println("Difference in number of days (2) : " + diffInDays);
	        } else if (diffHours > 24) {

	            System.err.println(">24");
	        } else if ((diffHours == 24) && (diffMinutes >= 1)) {
	            System.err.println("minutes");
	        }
	}

	
}
