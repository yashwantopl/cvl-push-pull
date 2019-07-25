package com.capitaworld.service.loans.service.teaser.primaryview;

import com.capitaworld.service.loans.model.teaser.primaryview.MFITeaserViewResponse;

/**
 * @author rahul.meena
 *
 */
public interface MFITeaserViewService {
	
	public MFITeaserViewResponse getPrimaryMFiDetails(Long applicationId,Long productMappingId,Integer mfiFpType);

}
