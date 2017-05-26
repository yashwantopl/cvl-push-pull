package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.*;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalFinalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by dhaval on 25-May-17.
 */
@Service
@Transactional
public class WorkingCapitalFinalServiceImpl implements WorkingCapitalFinalService {

    @Autowired
    private BoardOfDirectorsDetailRepository boardOfDirectorsDetailRepository;

    @Autowired
    private StrategicAlliancesDetailRepository strategicAlliancesDetailRepository;

    @Autowired
    private KeyManagementDetailRepository keyManagementDetailRepository;

    @Autowired
    private EmployeesCategoryBreaksDetailRepository employeesCategoryBreaksDetailRepository;

    @Autowired
    private TechnologyPositioningDetailRepository technologyPositioningDetailRepository;

    @Autowired
    private RevenueAndOrderBookDetailRepository revenueAndOrderBookDetailRepository;

    @Override
    public WorkingCapitalPrimaryViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId, Long userId) {
        List<BoardOfDirectorsResponse> boardOfDirectorsResponseList  = boardOfDirectorsDetailRepository.listByApplicationId(toApplicationId);
        List<StrategicAlliancesResponse> strategicAlliancesResponseList = strategicAlliancesDetailRepository.listByApplicationId(toApplicationId);
        List<KeyManagementResponse> keyManagementResponseList = keyManagementDetailRepository.listByApplicationId(toApplicationId);
        List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList = employeesCategoryBreaksDetailRepository.listByApplicationId(toApplicationId);
        List<TechnologyPositioningResponse> technologyPositioningResponseList = technologyPositioningDetailRepository.listByApplicationId(toApplicationId);
        List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList = revenueAndOrderBookDetailRepository.listByApplicationId(toApplicationId);
        return null;
    }
}
