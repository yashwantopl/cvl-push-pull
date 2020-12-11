package com.opl.service.loans.service.vehicledetails.impl;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.loans.model.ProposedVehicleIncomeExpenseRequest;
import com.opl.service.loans.domain.ProposedVehicleIncomeExpenseDetail;
import com.opl.service.loans.repository.ProposedVehicleIncomeExpenseDetailRepository;
import com.opl.service.loans.service.vehicledetails.IncomeExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pooja.patel on 02-12-2020.
 */
@Service
@Transactional
public class IncomeExpenseServiceImpl implements IncomeExpenseService {

    private static final Logger logger = LoggerFactory.getLogger(IncomeExpenseService.class);


    @Autowired
    ProposedVehicleIncomeExpenseDetailRepository incomeExpenseDetailRepository;

    @Override
    public ProposedVehicleIncomeExpenseRequest getIncomeExpenseDetailsByApplicationId(Long applicationId){
        try{
            ProposedVehicleIncomeExpenseRequest incomeExpenseRequest = new ProposedVehicleIncomeExpenseRequest();
            ProposedVehicleIncomeExpenseDetail incomeExpenseDetail = incomeExpenseDetailRepository.findByApplicationIdAndIsActiveIsTrue(applicationId);
            if (!CommonUtils.isObjectNullOrEmpty(incomeExpenseDetail)){
                BeanUtils.copyProperties(incomeExpenseDetail,incomeExpenseRequest);
            }
            return incomeExpenseRequest;
        }catch (Exception e){
            logger.error("Error while fetching Income Expense Details",e);
        }
        return null;
    }

    @Override
    public Boolean saveIncomeExpenseDetails(ProposedVehicleIncomeExpenseRequest incomeExpenseRequest,Long userId){
        try{
            ProposedVehicleIncomeExpenseDetail incomeExpenseDetail = null;
            if (!CommonUtils.isObjectNullOrEmpty(incomeExpenseRequest)) {
                incomeExpenseDetail = incomeExpenseRequest.getId() != null ? incomeExpenseDetailRepository.findOne(incomeExpenseRequest.getId()) : null;
                if (!CommonUtils.isObjectNullOrEmpty(incomeExpenseDetail)){
                    BeanUtils.copyProperties(incomeExpenseRequest, incomeExpenseDetail,"id");
                    incomeExpenseDetail.setModifiedBy(userId);
                    incomeExpenseDetail.setModifiedDate(new Date());
                }else {
                    incomeExpenseDetail = new ProposedVehicleIncomeExpenseDetail();
                    BeanUtils.copyProperties(incomeExpenseRequest, incomeExpenseDetail, "id");
                    incomeExpenseDetail.setCreatedBy(userId);
                    incomeExpenseDetail.setCreatedDate(new Date());
                    incomeExpenseDetail.setIsActive(true);
                }
                incomeExpenseDetailRepository.save(incomeExpenseDetail);
            } else {
                return false;
            }
            return true;
        }catch (Exception e){
            logger.error("Error while fetching Income Expense Details",e);
        }
        return false;
    }

}
