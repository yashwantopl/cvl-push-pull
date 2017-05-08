package com.capitaworld.service.loans.service.fundseeker.corporate;

public interface ExcelExtractionService {
	
	public Boolean readCMA(Long applicationId,Long storageDetailsId,String filePath);

	public Boolean readDPR(Long applicationId, Long storageDetailsId, String filePath);

}
