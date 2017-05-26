package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.model.teaser.finalview.AvailabilityProposedPlantDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.BoardOfDirectorsResponse;
import com.capitaworld.service.loans.model.teaser.finalview.CapacityDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.DprUserDataDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.EmployeesCategoryBreaksResponse;
import com.capitaworld.service.loans.model.teaser.finalview.KeyManagementResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RequirementsAndAvailabilityRawMaterialsDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.RevenueAndOrderBookResponse;
import com.capitaworld.service.loans.model.teaser.finalview.ScotAnalysisDetailResponse;
import com.capitaworld.service.loans.model.teaser.finalview.StrategicAlliancesResponse;
import com.capitaworld.service.loans.model.teaser.finalview.TechnologyPositioningResponse;
import com.capitaworld.service.loans.model.teaser.finalview.WorkingCapitalFinalViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AvailabilityProposedPlantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.BoardOfDirectorsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CapacityDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DprUserDataDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.EmployeesCategoryBreaksDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.KeyManagementDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.RevenueAndOrderBookDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ScotAnalysisDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.StrategicAlliancesDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.TechnologyPositioningDetailRepository;
import com.capitaworld.service.loans.service.common.DocumentManagementService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalFinalService;

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
    
    @Autowired
    private CapacityDetailRepository capacityDetailRepository;
    
    @Autowired
    private AvailabilityProposedPlantDetailRepository availabilityProposedPlantDetailRepository;
    
    @Autowired
    private RequirementsAndAvailabilityRawMaterialsDetailRepository requirementsAndAvailabilityRawMaterialsDetailRepository;
    
    @Autowired
    private ScotAnalysisDetailRepository scotAnalysisDetailRepository;
    
    @Autowired
    private DprUserDataDetailRepository dprUserDataDetailRepository;
    
    @Autowired
    private DocumentManagementService documentManagementService;

    @Override
    public WorkingCapitalFinalViewResponse getWorkingCapitalFinalViewDetails(Long toApplicationId, Long userId) {
        List<BoardOfDirectorsResponse> boardOfDirectorsResponseList  = boardOfDirectorsDetailRepository.listByApplicationId(toApplicationId);
        List<StrategicAlliancesResponse> strategicAlliancesResponseList = strategicAlliancesDetailRepository.listByApplicationId(toApplicationId);
        List<KeyManagementResponse> keyManagementResponseList = keyManagementDetailRepository.listByApplicationId(toApplicationId);
        List<EmployeesCategoryBreaksResponse> employeesCategoryBreaksResponseList = employeesCategoryBreaksDetailRepository.listByApplicationId(toApplicationId);
        List<TechnologyPositioningResponse> technologyPositioningResponseList = technologyPositioningDetailRepository.listByApplicationId(toApplicationId);
        List<RevenueAndOrderBookResponse> revenueAndOrderBookResponseList = revenueAndOrderBookDetailRepository.listByApplicationId(toApplicationId);
        
        List<CapacityDetailResponse> capacityDetailResponses = capacityDetailRepository.listByApplicationId(toApplicationId);
        List<AvailabilityProposedPlantDetailResponse> availabilityProposedPlantDetailResponses = availabilityProposedPlantDetailRepository.listByApplicationId(toApplicationId);
        List<RequirementsAndAvailabilityRawMaterialsDetailResponse> requirementsAndAvailabilityRawMaterialsDetailResponses = requirementsAndAvailabilityRawMaterialsDetailRepository.listByApplicationId(toApplicationId);
        List<ScotAnalysisDetailResponse> scotAnalysisDetailResponses = scotAnalysisDetailRepository.listByApplicationId(toApplicationId);
        List<DprUserDataDetailResponse> dprUserDataDetailResponses = dprUserDataDetailRepository.listByApplicationId(toApplicationId);
        
        WorkingCapitalFinalViewResponse response = new WorkingCapitalFinalViewResponse();
        
        response.setAvailabilityProposedPlantDetailResponse(availabilityProposedPlantDetailResponses);
        response.setBoardOfDirectorsResponseList(boardOfDirectorsResponseList);
        response.setCapacityDetailResponses(capacityDetailResponses);
        response.setDprUserDataDetailResponses(dprUserDataDetailResponses);
        response.setEmployeesCategoryBreaksResponseList(employeesCategoryBreaksResponseList);
        response.setKeyManagementResponseList(keyManagementResponseList);
        response.setRequirementsAndAvailabilityRawMaterialsDetailResponse(requirementsAndAvailabilityRawMaterialsDetailResponses);
        response.setRevenueAndOrderBookResponseList(revenueAndOrderBookResponseList);
        response.setScotAnalysisDetailResponses(scotAnalysisDetailResponses);
        response.setStrategicAlliancesResponseList(strategicAlliancesResponseList);
        response.setTechnologyPositioningResponseList(technologyPositioningResponseList);
        
        
        
        
        
        return response;
    }
}
