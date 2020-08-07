package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface DirectorBackgroundDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetailList(Long applicationId,Long userId) throws LoansException;
	
	public List<DirectorBackgroundDetailRequest> getDirectorBasicDetailsListForNTB(Long applicationId) throws LoansException;
	
	public Boolean updateFlag(Long directorId,Integer apiId,Boolean apiFlag,Long userId);
	
	public Boolean saveDirectors(Long applicationId,Long userId,Integer noOfDirector);
	
	public DirectorBackgroundDetailRequest getDirectorBackgroundDetail(Long id);
	
	public boolean saveDirectorInfo(DirectorBackgroundDetailRequest backgroundDetailRequest,Long applicationId,Long userId);
	
	public boolean inactive(Long applicationId,Long userId);

}
