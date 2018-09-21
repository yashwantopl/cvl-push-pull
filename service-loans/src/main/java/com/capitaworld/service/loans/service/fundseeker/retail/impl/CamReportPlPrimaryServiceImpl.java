package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.Map;

import com.capitaworld.service.loans.service.fundseeker.retail.CamReportPlPdfService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CamReportPlPrimaryServiceImpl implements CamReportPlPdfService{

	@Override
	public Map<String, Object> getCamReportPlPrimaryDetails(Long applicationId, Long productId, boolean isFinalView) {
		// TODO Auto-generated method stub
		return null;
	}

}
