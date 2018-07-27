package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMapping;
import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMappingTemp;
import com.capitaworld.service.loans.model.corporate.MsmeValueMappingRequest;
import com.capitaworld.service.loans.repository.fundprovider.MsmeValueMappingRepository;
import com.capitaworld.service.loans.repository.fundprovider.MsmeValueMappingTempRepository;
import com.capitaworld.service.loans.service.fundprovider.MsmeValueMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class MsmeValueMappingServiceImpl implements MsmeValueMappingService {

    private static final Logger logger = LoggerFactory.getLogger(MsmeValueMappingServiceImpl.class.getName());

    @Autowired
    private MsmeValueMappingRepository masterRepository;

    @Autowired
    private MsmeValueMappingTempRepository tempRepository;

    @Override
    public boolean updateMsmeValueMapping(Boolean isNew, Long fpProductId) {
        logger.info("entry in updateMsmeValueMapping()");
        try {
            if (isNew) {
                List<MsmeValueMappingTemp> tempList = tempRepository.findByFpProductIdAndIsActive(fpProductId, true);
                if (!CommonUtils.isListNullOrEmpty(tempList)) {
                    for (MsmeValueMappingTemp temp : tempList) {
                        MsmeValueMapping master = new MsmeValueMapping();
                        BeanUtils.copyProperties(temp, master, "id");
                        master.setActive(true);
                        masterRepository.save(master);
                    }
                }
            } else {
                masterRepository.inActiveMasterByFpProductId(fpProductId);
                List<MsmeValueMappingTemp> tempList = tempRepository.findByFpProductIdAndIsActive(fpProductId,true);
                if (!CommonUtils.isListNullOrEmpty(tempList)) {
                    for (MsmeValueMappingTemp temp : tempList) {
                        MsmeValueMapping master = new MsmeValueMapping();
                        BeanUtils.copyProperties(temp, master, "id");
                        master.setActive(true);
                        masterRepository.save(master);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("Error While updating msme value mapping!");
            e.printStackTrace();
            return false;
        }
        logger.info("exit from updateMsmeValueMapping()");
        return true;
    }

    @Override
    public boolean updateMsmeValueMappingTemp(List<MsmeValueMappingRequest> requestList, Long fpProductId) {
        logger.info("entry in updateMsmeValueMappingTemp()");
        try {
            tempRepository.inActiveTempByFpProductId(fpProductId);
            if (!CommonUtils.isObjectNullOrEmpty(requestList)) {
                for (MsmeValueMappingRequest request : requestList) {
                    MsmeValueMappingTemp temp = new MsmeValueMappingTemp();
                    BeanUtils.copyProperties(request, temp);
                    tempRepository.save(temp);
                }
                logger.info("exit form updateMsmeValueMappingTemp() with TRUE");
                return true;
            } else {
                logger.info("exit form updateMsmeValueMappingTemp() with FALSE");
                return false;
            }
        } catch (Exception e) {
            logger.info("exit form updateMsmeValueMappingTemp() with FALSE");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<MsmeValueMappingRequest> getDataListFromFpProductId(int dataRequestType, Long fpProductId) {
        List<MsmeValueMappingRequest> requestList = new ArrayList<>();
        if (dataRequestType == 1) {//pending
            List<MsmeValueMappingTemp> tempList = tempRepository.findByFpProductIdAndIsActive(fpProductId, true);
            for (MsmeValueMappingTemp temp : tempList) {
                MsmeValueMappingRequest request = new MsmeValueMappingRequest();
                request.setMsmeFundingId(temp.getMsmeFundingId());
                requestList.add(request);
            }
        } else if (dataRequestType == 2) {//master
            List<MsmeValueMapping> masterList = masterRepository.findByFpProductIdAndIsActive(fpProductId, true);
            for (MsmeValueMapping master: masterList) {
                MsmeValueMappingRequest request = new MsmeValueMappingRequest();
                request.setMsmeFundingId(master.getMsmeFundingId());
                requestList.add(request);
            }
        }
        return requestList;
    }
}

