package com.capitaworld.service.loans.service.colending.impl;

import com.capitaworld.service.loans.controller.colending.RecommendDetailController;
import com.capitaworld.service.loans.domain.colending.RecommendDetail;
import com.capitaworld.service.loans.model.colending.RecommendDetailRequest;
import com.capitaworld.service.loans.repository.colending.RecommendDetailRepository;
import com.capitaworld.service.loans.service.colending.RecommendDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dhaval.panchal on 12-Sep-19.
 */
@Service
@Transactional
public class RecommendDetailServiceImpl implements RecommendDetailService {
    private static final Logger logger = LoggerFactory.getLogger(RecommendDetailController.class.getName());

    @Autowired
    private RecommendDetailRepository  repository;

    @Override
    public boolean save(RecommendDetailRequest request) {
        RecommendDetail detail = new RecommendDetail();
        try {
            BeanUtils.copyProperties(request,detail);
            repository.save(detail);
        } catch (BeansException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
