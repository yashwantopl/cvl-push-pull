package com.capitaworld.service.loans.service.teaser.primaryview.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.util.DocumentAlias;
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
        
        try {
		response.setLastAuditedAnnualReportList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_AUDITED_ANNUAL_REPORT));
        response.setSanctionLetterCopyList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_SANCTION_LETTER_COPY));
        response.setLastITReturnList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_LAST_IT_RETURN));
        response.setNetWorthStatementOfdirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_NET_WORTH_STATEMENT_OF_DIRECTORS));
        response.setProvisionalFinancialsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PROVISIONAL_FINANCIALS));
        response.setPanOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PAN_OF_DIRECTORS_CERTIFICATE_OF_INCORPORATION));
        response.setDetailedListOfShareholdersList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_DETAILED_LIST_OF_SHAREHOLDERS));
        response.setPhotoOfDirectorsList(documentManagementService.getDocumentDetails(toApplicationId, DocumentAlias.UERT_TYPE_APPLICANT, DocumentAlias.WORKING_CAPITAL_PHOTO_OF_DIRECTORS));
        } catch (DocumentException e) {
			e.printStackTrace();
		}
        
        return response;
    }
}
