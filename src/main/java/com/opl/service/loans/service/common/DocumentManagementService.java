package com.opl.service.loans.service.common;

import java.util.List;

import com.opl.mudra.api.dms.exception.DocumentException;

/**
 * @author Sanket
 *
 */
public interface DocumentManagementService {
	
	public List<Object> getDocumentDetails(Long toApplicationId,String userType,Long documentMappingId) throws DocumentException;

}
