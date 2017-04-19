package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;

@Service
public class LiabilitiesDetailsServiceImpl implements LiabilitiesDetailsService {

	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;
	
	@Override
	public void saveOrUpdate(LiabilitiesDetails liabilitiesDetails) {
		// TODO Auto-generated method stub
		liabilitiesDetailsRepository.save(liabilitiesDetails);
	}

	@Override
	public JSONObject readLiabilitiesDetails(FileInputStream file, XSSFSheet sheet) {
		// TODO Auto-generated method stub
		return null;
	}

}
