package com.capitaworld.service.loans.service.fundseeker.corporate;

import org.springframework.web.multipart.MultipartFile;

public interface ExcelExtractionService {
	
	public Boolean readCMA(Long applicationId,Long storageDetailsId,MultipartFile multipartFile);

	public Boolean readDPR(Long applicationId, Long storageDetailsId, MultipartFile multipartFile);
	
	public Boolean readBS(Long applicationId,Long storageDetailsId,MultipartFile multipartFile);
	
	public Boolean inActiveCMA(Long storageDetailsId);
	
	public Boolean inActiveDPR(Long storageDetailsId);
	
	public Boolean inActiveBS(Long storageDetailsId);

}
