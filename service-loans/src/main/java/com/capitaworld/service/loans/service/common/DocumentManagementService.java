package com.capitaworld.service.loans.service.common;

import java.util.List;

import com.capitaworld.service.dms.exception.DocumentException;

/**
 * @author Sanket
 *
 */
public interface DocumentManagementService {
	
	public List<Object> getDocumentDetails(Long toApplicationId,String userType,Long documentMappingId) throws DocumentException;

}
