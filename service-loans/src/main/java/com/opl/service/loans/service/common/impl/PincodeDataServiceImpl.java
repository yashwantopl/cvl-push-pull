package com.opl.service.loans.service.common.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.PincodeDataResponse;
import com.opl.service.loans.domain.PincodeData;
import com.opl.service.loans.repository.PincodeDataRepository;
import com.opl.service.loans.service.common.PincodeDateService;

@Service
@Transactional
public class PincodeDataServiceImpl implements PincodeDateService {

    @Autowired
    private PincodeDataRepository pincodeDataRepository;

    @Override
    public List<PincodeDataResponse> listData(String pincode) {
        List<PincodeData> pincodeDataList = pincodeDataRepository.findAllByPincode(pincode);
        List<PincodeDataResponse> dataResponseList = new ArrayList<>();
        for (PincodeData data:pincodeDataList) {
            PincodeDataResponse response = new PincodeDataResponse();
            BeanUtils.copyProperties(data,response);
            dataResponseList.add(response);
        }
        return dataResponseList;
    }

	@Override
	public PincodeDataResponse getById(Long id) {
		PincodeData findOne = pincodeDataRepository.findOne(id);
		if(findOne == null) {
			return null;
		}
		PincodeDataResponse response = new PincodeDataResponse();
        BeanUtils.copyProperties(findOne,response);
		return response;
	}
}
