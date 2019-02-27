package com.capitaworld.service.loans.service.common;

import org.apache.poi.ss.usermodel.Workbook;

public interface DownLoadCMAFileService {

	public Workbook cmaFileGenerator(Long applicationId,Long productDocumentMappingId) ;

	public Workbook cmaFileGeneratorByProposalId(Long applicationId,Long proposalId,Long productDocumentMappingId) ;

	public Workbook coCMAFileGenerator(Long applicationId, Long productDocumentMappingId) ;

	public Workbook coCMAFileGeneratorByProposalId(Long applicationId, Long proposalId,Long productDocumentMappingId) ;
}
