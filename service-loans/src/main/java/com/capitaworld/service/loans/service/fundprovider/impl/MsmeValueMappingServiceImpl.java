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

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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
                        BeanUtils.copyProperties(temp, master, "id","modified_date","created_date");
                        master.setCreatedDate(new Date());
                        master.setModifiedDate(new Date());
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
    public boolean updateMsmeValueMappingTemp(List<Integer> requestList, Long fpProductId, Long userId) {
        logger.info("entry in updateMsmeValueMappingTemp()");
        try {
            tempRepository.inActiveTempByFpProductId(fpProductId);
            if (!CommonUtils.isObjectNullOrEmpty(requestList)) {
                for (Integer request : requestList) {
                    MsmeValueMappingTemp temp = new MsmeValueMappingTemp();
                    temp.setMsmeFundingId(Long.valueOf(request));
                    temp.setFpProductId(fpProductId);
                    temp.setCreatedBy(userId);
                    temp.setCreatedDate(new Date());
                    temp.setModifiedBy(userId);
                    temp.setModifiedDate(new Date());
                    temp.setActive(true);
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
    public List<Integer> getDataListFromFpProductId(int dataRequestType, Long fpProductId, Long userId) {
        List<MsmeValueMappingRequest> requestList = new ArrayList<>();
        List<Integer> msmeFundingIds = new ArrayList<Integer>();
        if (dataRequestType == 1) {//pending
            List<MsmeValueMappingTemp> tempList = tempRepository.findByFpProductIdAndIsActive(fpProductId, true);
            for (MsmeValueMappingTemp temp : tempList) {
                msmeFundingIds.add(Integer.valueOf(temp.getMsmeFundingId().toString()));
            }
        } else if (dataRequestType == 2) {//master
            List<MsmeValueMapping> masterList = masterRepository.findByFpProductIdAndIsActive(fpProductId, true);
            for (MsmeValueMapping master: masterList) {
                msmeFundingIds.add(Integer.valueOf(master.getMsmeFundingId().toString()));
            }
        }
        return msmeFundingIds;
    }
}

