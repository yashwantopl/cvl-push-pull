package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.json.simple.JSONObject;

public interface ExcelExtractionService {
	
	public Boolean readCMA(Long applicationId,Long storageDetailsId,String filePath);

}
