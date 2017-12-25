package com.capitaworld.service.loans.service.common.impl;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.service.common.ArrayOfBytesToFileService;
import com.capitaworld.service.loans.service.common.FsDetailsForPdfService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class ArrayOfBytesToFileServiceImpl implements ArrayOfBytesToFileService {

	@Autowired
	private FsDetailsForPdfService fsDetailsForPdfService;

	@Override
	public byte[] createByteFileFromMap(Long applicantId) {
		try {
			Map<String, Object> map = fsDetailsForPdfService.getSortedMapForUbi(applicantId);
			Iterator it = map.entrySet().iterator();
			String mapValues = "";
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                if(!CommonUtils.isObjectNullOrEmpty(pair.getValue()) && (pair.getValue() instanceof java.util.HashMap)){
                	Map<String, Object> innerMap =  (Map<String, Object>) pair.getValue();
                	Iterator innerIt = innerMap.entrySet().iterator();
                	while(innerIt.hasNext()){
                		Map.Entry pair1 = (Map.Entry)innerIt.next();
                		if(!CommonUtils.isObjectNullOrEmptyOrDash(pair1.getValue())){
                			mapValues+=pair1.getValue()+"|";	
                		}else{
                			mapValues+="|";	
                		}
                	}
                }else{
                	if(!CommonUtils.isObjectNullOrEmptyOrDash(pair.getValue())){
                		mapValues+=pair.getValue()+"|";
                    }else{
                    	mapValues+="|";
                    }	
                }   
            }
            return mapValues.getBytes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
