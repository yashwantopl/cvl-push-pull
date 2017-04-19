package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;

@Service
public class AssetsDetailsServiceImpl implements AssetsDetailsService{

	@Autowired
	AssetsDetailsRepository assetsDetailsRepository; 
	
	@Override
	public void saveOrUpdate(AssetsDetails assetsDetails) {
		// TODO Auto-generated method stub
		assetsDetailsRepository.save(assetsDetails);	
	}

	@Override
	public JSONObject readAssetsDetails(FileInputStream file, XSSFSheet sheet) {
		// TODO Auto-generated method stub
		return null;
	}

}
