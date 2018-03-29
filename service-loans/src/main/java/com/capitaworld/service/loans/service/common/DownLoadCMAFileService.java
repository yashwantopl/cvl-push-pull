package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.dms.model.DocumentResponse;

public interface DownLoadCMAFileService {

	public DocumentResponse cmaFileGenerator(Long applicationId,Long productDocumentMappingId) ;
	
	public DocumentResponse  coCMAFileGenerator(Long applicationId, Long productDocumentMappingId) ;
}
