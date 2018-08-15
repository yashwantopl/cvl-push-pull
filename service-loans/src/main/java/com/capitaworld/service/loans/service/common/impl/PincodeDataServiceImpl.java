package com.capitaworld.service.loans.service.common.impl;

import com.capitaworld.service.loans.domain.PincodeData;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import com.capitaworld.service.loans.repository.PincodeDataRepository;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
}
