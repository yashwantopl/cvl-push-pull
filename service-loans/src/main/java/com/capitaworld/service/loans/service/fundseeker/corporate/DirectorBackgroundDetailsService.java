package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * @author Sanket
 *
 */
public interface DirectorBackgroundDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetailList(Long applicationId,Long userId) throws Exception;
	
	public List<DirectorBackgroundDetailRequest> getDirectorBasicDetailsListForNTB(Long applicationId) throws Exception;
	
	public Boolean updateFlag(Long directorId,Integer apiId,Boolean apiFlag,Long userId);
	
	public Boolean saveDirectors(Long applicationId,Long userId,Integer noOfDirector);
	
	public DirectorBackgroundDetailRequest getDirectorBackgroundDetail(Long id);

}
