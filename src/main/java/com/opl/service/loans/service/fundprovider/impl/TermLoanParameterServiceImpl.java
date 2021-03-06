package com.opl.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.DataRequest;
import com.opl.mudra.api.loans.model.corporate.TermLoanParameterRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.oneform.model.OneFormResponse;
import com.opl.mudra.api.workflow.model.WorkflowJobsTrackerRequest;
import com.opl.mudra.api.workflow.model.WorkflowResponse;
import com.opl.mudra.api.workflow.utils.WorkflowUtils;
import com.opl.mudra.client.oneform.OneFormClient;
import com.opl.mudra.client.workflow.WorkflowClient;
import com.opl.service.loans.domain.IndustrySectorDetail;
import com.opl.service.loans.domain.IndustrySectorDetailTemp;
import com.opl.service.loans.domain.fundprovider.CoLendingRatio;
import com.opl.service.loans.domain.fundprovider.ConstitutionMapping;
import com.opl.service.loans.domain.fundprovider.ConstitutionMappingTemp;
import com.opl.service.loans.domain.fundprovider.CvlVehicleMultipleOption;
import com.opl.service.loans.domain.fundprovider.CvlVehicleMultipleOptionTemp;
import com.opl.service.loans.domain.fundprovider.FpGstTypeMapping;
import com.opl.service.loans.domain.fundprovider.GeographicalCityDetail;
import com.opl.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.opl.service.loans.domain.fundprovider.GeographicalCountryDetail;
import com.opl.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.opl.service.loans.domain.fundprovider.GeographicalStateDetail;
import com.opl.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.opl.service.loans.domain.fundprovider.LoanArrangementMapping;
import com.opl.service.loans.domain.fundprovider.LoanArrangementMappingTemp;
import com.opl.service.loans.domain.fundprovider.NTBParameter;
import com.opl.service.loans.domain.fundprovider.NegativeIndustry;
import com.opl.service.loans.domain.fundprovider.NegativeIndustryTemp;
import com.opl.service.loans.domain.fundprovider.NtbTermLoanParameterTemp;
import com.opl.service.loans.domain.fundprovider.TermLoanParameter;
import com.opl.service.loans.domain.fundprovider.TermLoanParameterTemp;
import com.opl.service.loans.repository.fundprovider.CoLendingRatioRepository;
import com.opl.service.loans.repository.fundprovider.CvlVehicleMultipleOptionRepo;
import com.opl.service.loans.repository.fundprovider.CvlVehicleMultipleOptionRepoTemp;
import com.opl.service.loans.repository.fundprovider.FpConstitutionMappingRepository;
import com.opl.service.loans.repository.fundprovider.FpConstitutionMappingTempRepository;
import com.opl.service.loans.repository.fundprovider.FpGstTypeMappingRepository;
import com.opl.service.loans.repository.fundprovider.FpGstTypeMappingTempRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalCityRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalStateRepository;
import com.opl.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.opl.service.loans.repository.fundprovider.LoanArrangementMappingRepository;
import com.opl.service.loans.repository.fundprovider.LoanArrangementMappingTempRepository;
import com.opl.service.loans.repository.fundprovider.NbfcRatioMappingRepository;
import com.opl.service.loans.repository.fundprovider.NbfcRatioMappingTempRepository;
import com.opl.service.loans.repository.fundprovider.NegativeIndustryRepository;
import com.opl.service.loans.repository.fundprovider.NegativeIndustryTempRepository;
import com.opl.service.loans.repository.fundprovider.NtbParameterRepository;
import com.opl.service.loans.repository.fundprovider.NtbTermLoanParameterTempRepository;
import com.opl.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.opl.service.loans.repository.fundprovider.TermLoanParameterTempRepository;
import com.opl.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.opl.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.opl.service.loans.service.fundprovider.FPParameterMappingService;
import com.opl.service.loans.service.fundprovider.MsmeValueMappingService;
import com.opl.service.loans.service.fundprovider.TermLoanParameterService;
import com.opl.service.loans.utils.CommonDocumentUtils;

@Service
@Transactional
public class TermLoanParameterServiceImpl implements TermLoanParameterService {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanParameterServiceImpl.class);

	private static final String ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG = "error while getTermLoanParameterRequest : ";
	private static final String ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG = "error while getTermLoanParameterRequestTemp : ";
	private static final String ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG = "error while getNtbTermLoanParameterRequestTemp : ";
	private static final String ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_MSG = "error while getNtbTermLoanParameterRequest : ";
	private static final String UPDATED_MSG = "updated = {}"; 
	private static final String GET_TERM_LOAN_PARAMETER_REQUEST = "getTermLoanParameterRequest";

	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private GeographicalCountryRepository geographicalCountryRepository;

	@Autowired
	private GeographicalStateRepository geographicalStateRepository;

	@Autowired
	private GeographicalCityRepository geographicalCityRepository;

	@Autowired
	private NegativeIndustryRepository negativeIndustryRepository;

	@Autowired
	private TermLoanParameterTempRepository termLoanParameterTempRepository;

	@Autowired
	private NtbTermLoanParameterTempRepository ntbTermLoanParameterTempRepository;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private IndustrySectorTempRepository industrySectorTempRepository;

	@Autowired
	private GeographicalCountryTempRepository geographicalCountryTempRepository;

	@Autowired
	private GeographicalStateTempRepository geographicalStateTempRepository;

	@Autowired
	private GeographicalCityTempRepository geographicalCityTempRepository;

	@Autowired
	private NegativeIndustryTempRepository negativeIndustryTempRepository;

	@Autowired
	private WorkflowClient workflowClient;

	@Autowired
	private MsmeValueMappingService msmeValueMappingService;

	@Autowired
	private NtbParameterRepository ntbParameterRepository;

	@Autowired
	private LoanArrangementMappingRepository loanArrangementMappingRepository;

	@Autowired
	private LoanArrangementMappingTempRepository loanArrangementMappingTempRepository;

	@Autowired
	private FpGstTypeMappingRepository fpGstTypeMappingRepository;

	@Autowired
	private FpGstTypeMappingTempRepository fpGstTypeMappingTempRepository;
	
	@Autowired
	private CoLendingRatioRepository coLendingRatioRepository;
	
	@Autowired
	private NbfcRatioMappingTempRepository nbfcRatioMappingTempRepository; 
	
	@Autowired
	private NbfcRatioMappingRepository nbfcRatioMappingRepository;
	
	@Autowired
	private FpConstitutionMappingRepository fpConstitutionMappingRepository;
	
	@Autowired
	private FpConstitutionMappingTempRepository fpConstitutionMappingTempRepository;
	
	@Autowired
	private FPParameterMappingService fPParameterMappingService;
	
	@Autowired
	private CvlVehicleMultipleOptionRepoTemp cvlVehicleMultipleOptionRepoTemp;
	
	private CvlVehicleMultipleOptionRepo cvlVehicleMultipleOptionRepo;
	
	
	

	@Override
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest, Long mappingId, Integer roleId) {
		CommonDocumentUtils.startHook(logger, CommonUtils.SAVE_OR_UPDATE);

		TermLoanParameterTemp loanParameter = termLoanParameterTempRepository
				.getTermLoanParameterTempByFpProductId(mappingId);
		TermLoanParameter termLoanParameter = null;

		if (loanParameter.getFpProductMappingId() != null) {
			termLoanParameter = termLoanParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (termLoanParameter == null) {
			termLoanParameter = new TermLoanParameter();

		}

		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		termLoanParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest
					.setMaxTenure(termLoanParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest
					.setMinTenure(termLoanParameterRequest.getMinTenure().multiply(new BigDecimal("12")));

		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, "id");
		termLoanParameter
				.setUserId(termLoanParameterRequest.getUserId() != null ? termLoanParameterRequest.getUserId() : null);
		termLoanParameter.setProductId(
				termLoanParameterRequest.getProductId() != null ? termLoanParameterRequest.getProductId() : null);
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);
		termLoanParameter.setJobId(termLoanParameterRequest.getJobId());
		TermLoanParameter termLoanParameter2 = termLoanParameterRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(termLoanParameter2.getId());
		industrySectorRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// industry data save
		saveIndustry(termLoanParameterRequest);
		// Sector data save
		saveSector(termLoanParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// country data save
		saveCountry(termLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveState(termLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveCity(termLoanParameterRequest);
		// negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(termLoanParameterRequest.getId());
		saveNegativeIndustry(termLoanParameterRequest);

		// loan arrangements
		loanArrangementMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveLoanArrangements(termLoanParameterRequest);

		// gst type
		fpGstTypeMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveLoanGstType(termLoanParameterRequest);
		
		//save constitution mapping
		fpConstitutionMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveConstitutionType(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepo.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 1l);
		saveResidenceStability(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepo.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 2l);
		saveVehicleType(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepo.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 3l);
		saveVehicleSegment(termLoanParameterRequest);

		//save nbfc ratio mapping
//		nbfcRatioMappingRepository.inActiveByFpProductId(termLoanParameterRequest.getId());
		
		fPParameterMappingService.inactiveAndSave(termLoanParameter2.getId(),CommonUtils.ParameterTypes.BUREAU_SCORE, termLoanParameterRequest.getBureauScoreIds());
		fPParameterMappingService.inactiveAndSave(termLoanParameter2.getId(),CommonUtils.ParameterTypes.BUREAU_SCORE_MAIN_DIR, termLoanParameterRequest.getMainDirBureauScoreIds());
		fPParameterMappingService.inactiveAndSave(termLoanParameter2.getId(),CommonUtils.ParameterTypes.BANK_STATEMENT_OPTIONS, termLoanParameterRequest.getBankStatementOptions());
		fPParameterMappingService.inactiveAndSaveWithObject(termLoanParameter2.getId(), CommonUtils.ParameterTypes.RISK_BASE_LOAN_AMOUNT, termLoanParameterRequest.getRiskLoanAmountList());
		//add duplicate productmaster entries based on nbfc ids
//		if(termLoanParameterRequest.getProductType()!=null && termLoanParameterRequest.getProductType()==2){
//			addduplicateEntriesForNbfc(termLoanParameterRequest,mappingId);
//		}

		boolean isUpdate = msmeValueMappingService.updateMsmeValueMapping(false, mappingId, termLoanParameter2.getId());
		logger.info(UPDATED_MSG, isUpdate);
		CommonDocumentUtils.endHook(logger, CommonUtils.SAVE_OR_UPDATE);
		return true;

	}

	private void saveConstitutionType(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveConstitutionType");
		ConstitutionMapping constitutionMapping= null;
		for (Integer dataRequest : termLoanParameterRequest.getConstitutionIds()) {
			constitutionMapping = new ConstitutionMapping();
			constitutionMapping.setFpProductId(termLoanParameterRequest.getId());
			constitutionMapping.setConstitutionId(dataRequest);
			constitutionMapping.setCreatedBy(termLoanParameterRequest.getUserId());
			constitutionMapping.setModifiedBy(termLoanParameterRequest.getUserId());
			constitutionMapping.setCreatedDate(new Date());
			constitutionMapping.setModifiedDate(new Date());
			constitutionMapping.setIsActive(true);
			// create by and update
			fpConstitutionMappingRepository.save(constitutionMapping);
		}
		CommonDocumentUtils.endHook(logger, "saveConstitutionType");
		
	}
	
	
	private void saveConstitutionTypeTemp(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveConstitutionTypeTemp");
		ConstitutionMappingTemp constitutionMapping= null;
		for (Integer dataRequest : termLoanParameterRequest.getConstitutionIds()) {
			constitutionMapping = new ConstitutionMappingTemp();
			constitutionMapping.setFpProductId(termLoanParameterRequest.getId());
			constitutionMapping.setConstitutionId(dataRequest);
			constitutionMapping.setCreatedBy(termLoanParameterRequest.getUserId());
			constitutionMapping.setModifiedBy(termLoanParameterRequest.getUserId());
			constitutionMapping.setCreatedDate(new Date());
			constitutionMapping.setModifiedDate(new Date());
			constitutionMapping.setIsActive(true);
			// create by and update
			fpConstitutionMappingTempRepository.save(constitutionMapping);
		}
		CommonDocumentUtils.endHook(logger, "saveConstitutionTypeTemp");
		
	}

	private void addduplicateEntriesForNbfc(TermLoanParameterRequest termLoanParameterRequest, Long mappingId) {/*
		// TODO Auto-generated method stub
		
		for(Long l:termLoanParameterRequest.getNbfcRatioIds())
		{
		TermLoanParameter termLoanParameter =  new TermLoanParameter();
		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, "id");
			termLoanParameter
				.setUserId(termLoanParameterRequest.getUserId() != null ? termLoanParameterRequest.getUserId() : null);
		termLoanParameter.setProductId(
				termLoanParameterRequest.getProductId() != null ? termLoanParameterRequest.getProductId() : null);
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);
		termLoanParameter.setJobId(termLoanParameterRequest.getJobId());
		
		
		

		//add scoring
		ScoringModelReqRes scoringModelReqRes;
		CoLendingRatio coLendingRatio;
		try
		{
		scoringModelReqRes=new ScoringModelReqRes();
		scoringModelReqRes.setScoringModelId(termLoanParameterRequest.getScoreModelId());
		
		//NbfcRatioMappingTemp nbfcRatioMapping = nbfcRatioMappingTempRepository.getOne(l);
		coLendingRatio = coLendingRatioRepository.getOne(l);
		scoringModelReqRes.setOrgId(coLendingRatio.getUserOrgId());
		
		ScoringModelReqRes copyScoringModel = scoringClient.copyScoringModel(scoringModelReqRes);
		termLoanParameter.setScoreModelId(copyScoringModel.getScoringModelId());
		termLoanParameter.setProductCode(
				fundProviderSequenceService.getFundProviderSequenceNumber(termLoanParameterRequest.getProductId()));
		termLoanParameter.setUserOrgId(coLendingRatio.getUserOrgId());
		}
		catch(Exception e)
		{
			logger.error("error while adding scoremodel for co-origination flow",e);
		}
		
		
		TermLoanParameter termLoanParameter2 = termLoanParameterRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(termLoanParameter2.getId());
		
		industrySectorRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		
		saveIndustry(termLoanParameterRequest);
		// Sector data save
		saveSector(termLoanParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// country data save
		saveCountry(termLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveState(termLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveCity(termLoanParameterRequest);
		// negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(termLoanParameterRequest.getId());
		saveNegativeIndustry(termLoanParameterRequest);
		
		loanArrangementMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveLoanArrangements(termLoanParameterRequest);

		// gst type
		fpGstTypeMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveLoanGstType(termLoanParameterRequest);
		
		//save constitution mapping
		fpConstitutionMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveConstitutionType(termLoanParameterRequest);
		
		//save nbfc ratio mapping
		msmeValueMappingService.updateMsmeValueMapping(false, mappingId, termLoanParameter2.getId());
		}
		
		
		
	*/}

	@SuppressWarnings("unchecked")
	@Override
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id, Long role) {
		CommonDocumentUtils.startHook(logger, GET_TERM_LOAN_PARAMETER_REQUEST);
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		TermLoanParameter loanParameter = termLoanParameterRepository.getById(id);
		
		
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(
					termLoanParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(
					termLoanParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));

		List<Long> industryList = industrySectorRepository.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> sectorList = industrySectorRepository.getSectorByProductId(termLoanParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(termLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);

				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setNegativeIndustryList(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}
		termLoanParameterRequest.setMsmeFundingIds(
				msmeValueMappingService.getDataListFromFpProductId(2, id, termLoanParameterRequest.getUserId()));
		termLoanParameterRequest.setGstType(fpGstTypeMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));

		termLoanParameterRequest.setLoanArrangementIds(
				loanArrangementMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setNbfcRatioIds(nbfcRatioMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		
		termLoanParameterRequest.setConstitutionIds(fpConstitutionMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		
		termLoanParameterRequest.setResidencyStabilityIds(cvlVehicleMultipleOptionRepo.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(), 1l));
		termLoanParameterRequest.setVehicleTypeIds(cvlVehicleMultipleOptionRepo.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(),2l));
		termLoanParameterRequest.setVehicleSegmentIds(cvlVehicleMultipleOptionRepo.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(),3l));
		
		termLoanParameterRequest.setBureauScoreIds(fPParameterMappingService.getParameters(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BUREAU_SCORE));
		termLoanParameterRequest.setMainDirBureauScoreIds(fPParameterMappingService.getParameters(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BUREAU_SCORE_MAIN_DIR));
		termLoanParameterRequest.setBankStatementOptions(fPParameterMappingService.getParameters(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BANK_STATEMENT_OPTIONS));
		termLoanParameterRequest.setRiskLoanAmountList(fPParameterMappingService.getParametersWithObject(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RISK_BASE_LOAN_AMOUNT));
		CommonDocumentUtils.endHook(logger, GET_TERM_LOAN_PARAMETER_REQUEST);
		return termLoanParameterRequest;
	}

	private void saveIndustry(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveIndustry");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveIndustry");
	}

	private void saveSector(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveSector");
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveSector");
	}

	private void saveCountry(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCountry");

		GeographicalCountryDetail geographicalCountryDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetail();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryRepository.save(geographicalCountryDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCountry");
	}

	private void saveState(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveState");
		GeographicalStateDetail geographicalStateDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetail();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateRepository.save(geographicalStateDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveState");
	}

	private void saveCity(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveCity");
		GeographicalCityDetail geographicalCityDetail = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetail();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(termLoanParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityRepository.save(geographicalCityDetail);
		}
		CommonDocumentUtils.endHook(logger, "saveCity");
	}

	private void saveNegativeIndustry(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustry");
		NegativeIndustry negativeIndustry = null;
		for (DataRequest dataRequest : termLoanParameterRequest.getNegativeIndustryList()) {
			negativeIndustry = new NegativeIndustry();
			negativeIndustry.setFpProductMasterId(termLoanParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(termLoanParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(termLoanParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustry");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.capitaworld.service.loans.service.fundprovider.
	 * TermLoanParameterService#saveMasterFromTempTl(java.lang.Long)
	 */
	@Override
	public Boolean saveMasterFromTempTl(Long mappingId, Integer roleId) throws LoansException {
		try {
			TermLoanParameterRequest temp = getTermLoanParameterRequestTemp(mappingId, null, null);
			return saveOrUpdate(temp, mappingId,roleId);

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public TermLoanParameterRequest getTermLoanParameterRequestTemp(Long id, Long role, Long userId) {
		CommonDocumentUtils.startHook(logger, GET_TERM_LOAN_PARAMETER_REQUEST);
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		TermLoanParameterTemp loanParameter = termLoanParameterTempRepository.getTermLoanParameterTempByFpProductId(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest.setMaxTenure(
					termLoanParameterRequest.getMaxTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest.setMinTenure(
					termLoanParameterRequest.getMinTenure().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));

		List<Long> industryList = industrySectorTempRepository.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> sectorList = industrySectorTempRepository.getSectorByProductId(termLoanParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);

				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(termLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);

				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateTempRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityTempRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> negativeIndustryList = negativeIndustryTempRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setNegativeIndustryList(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}
		CommonDocumentUtils.endHook(logger, "getTermLoanParameterRequestTemp");

		termLoanParameterRequest.setJobId(loanParameter.getJobId());
		List<DataRequest>  ratioMasterList;
		List<CoLendingRatio> listAllActiveByOrgkId;
		//add nbfc ratio list
		if(!CommonUtils.isObjectNullOrEmpty(role))
		{
		if(role.equals(WorkflowUtils.Role.NBFC_CHECKER) || role.equals(WorkflowUtils.Role.NBFC_MAKER))
		{
			listAllActiveByOrgkId =coLendingRatioRepository.listAllActiveProposalByOrgId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
			
		}
		else
		{
			listAllActiveByOrgkId = coLendingRatioRepository.listAllActiveByBankId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
		}
		
		for(CoLendingRatio coLendingRatio:listAllActiveByOrgkId)
		{
			DataRequest dataRequest=new DataRequest();
			dataRequest.setId(coLendingRatio.getId());
			dataRequest.setValue(coLendingRatio.getName());
			dataRequest.setTenure(coLendingRatio.getTenure());
			String label="Bank:"+coLendingRatio.getBankRatio().toString()+" Nbfc:"+coLendingRatio.getNbfcRatio()+" tenure:"+coLendingRatio.getTenure();
			dataRequest.setLabel(label);
			dataRequest.setUserOrgId(coLendingRatio.getUserOrgId());
			dataRequest.setBankId(coLendingRatio.getBankId());
			ratioMasterList.add(dataRequest);
		}
		
		termLoanParameterRequest.setNbfcRatioMasterList(ratioMasterList);
		}
		

		// set workflow buttons

		if (!CommonUtils.isObjectNullOrEmpty(loanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
			WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(loanParameter.getJobId(),
					Arrays.asList(role), userId);
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse)
					&& !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				try {
					WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) workflowResponse.getData(),
							WorkflowJobsTrackerRequest.class);
					if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils
							.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
						termLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
					} else {
						logger.info("response from workflow NULL jobId = {} and roleId = {}", loanParameter.getJobId(),
								role);
					}
				} catch (IOException e) {
					logger.error("Error While getting data from workflow {}", e);
				}
			}
		} else {
			logger.info("you set jobId or list of roleId NULL for calling workflow");
		}
		
		termLoanParameterRequest.setMsmeFundingIds(msmeValueMappingService.getDataListFromFpProductId(1, id, userId));
		
		termLoanParameterRequest.setResidencyStabilityIds(cvlVehicleMultipleOptionRepoTemp.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(),1l));
		termLoanParameterRequest.setVehicleTypeIds(cvlVehicleMultipleOptionRepoTemp.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(),2l));
		termLoanParameterRequest.setVehicleSegmentIds(cvlVehicleMultipleOptionRepoTemp.finMasterIddByFpProductIdAndIsActiveAndTypeId(termLoanParameterRequest.getId(),3l));
		termLoanParameterRequest.setLoanArrangementIds(
				loanArrangementMappingTempRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setGstType(fpGstTypeMappingTempRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
//		termLoanParameterRequest.setNbfcRatioIds(nbfcRatioMappingTempRepository.getTempIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setConstitutionIds(fpConstitutionMappingTempRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setBureauScoreIds(fPParameterMappingService.getParametersTemp(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BUREAU_SCORE));
		termLoanParameterRequest.setMainDirBureauScoreIds(fPParameterMappingService.getParametersTemp(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BUREAU_SCORE_MAIN_DIR));
		termLoanParameterRequest.setBankStatementOptions(fPParameterMappingService.getParametersTemp(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.BANK_STATEMENT_OPTIONS));
		termLoanParameterRequest.setRiskLoanAmountList(fPParameterMappingService.getParametersTempWithObject(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RISK_BASE_LOAN_AMOUNT));
		logger.info("end getTermLoanParameterRequestTemp");
		return termLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateTemp(TermLoanParameterRequest termLoanParameterRequest) {
		CommonDocumentUtils.startHook(logger, CommonUtils.SAVE_OR_UPDATE);

		TermLoanParameterTemp termLoanParameter = null;

		if (termLoanParameterRequest.getAppstage() == 1) {
			termLoanParameter = termLoanParameterTempRepository.findOne(termLoanParameterRequest.getId());
		} else {

			termLoanParameter = termLoanParameterTempRepository
					.getTermLoanParameterTempByFpProductMappingId(termLoanParameterRequest.getId());

		}

		if (termLoanParameter == null) {
			termLoanParameter = new TermLoanParameterTemp();
			termLoanParameter.setFpProductMappingId(termLoanParameterRequest.getId());
		}

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenure()))
			termLoanParameterRequest
					.setMaxTenure(termLoanParameterRequest.getMaxTenure().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenure()))
			termLoanParameterRequest
					.setMinTenure(termLoanParameterRequest.getMinTenure().multiply(new BigDecimal("12")));

		if (termLoanParameterRequest.getAppstage() != 1) {
			termLoanParameter.setFpProductMappingId(termLoanParameterRequest.getId());
		}

		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, "id");
		termLoanParameter
				.setUserId(termLoanParameterRequest.getUserId() != null ? termLoanParameterRequest.getUserId() : null);
		termLoanParameter.setProductId(
				termLoanParameterRequest.getProductId() != null ? termLoanParameterRequest.getProductId() : null);
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);

		termLoanParameter.setStatusId(CommonUtils.Status.OPEN);
		termLoanParameter.setIsApproved(false);
		termLoanParameter.setIsDeleted(false);
		termLoanParameter.setIsCopied(false);
		termLoanParameter.setApprovalDate(null);
		/*
		 * if (CommonUtils.isObjectNullOrEmpty(termLoanParameter.getJobId())) {
		 * WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
		 * WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS,
		 * WorkflowUtils.Action.SEND_FOR_APPROVAL,
		 * termLoanParameterRequest.getUserId()); Long jobId = null; if
		 * (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) { jobId =
		 * Long.valueOf(workflowResponse.getData().toString()); }
		 * 
		 * termLoanParameter.setJobId(jobId); }
		 */

		termLoanParameter = termLoanParameterTempRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(termLoanParameter.getId());
		industrySectorTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		// industry data save
		saveIndustryTemp(termLoanParameterRequest);
		// Sector data save
		saveSectorTemp(termLoanParameterRequest);
		geographicalCountryTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		// country data save
		saveCountryTemp(termLoanParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveStateTemp(termLoanParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveCityTemp(termLoanParameterRequest);
		// negative industry save
		negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(termLoanParameter.getId());
		saveNegativeIndustryTemp(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepoTemp.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 1l);
		saveResidenceStabilityTemp(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepoTemp.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 2l);
		saveVehicleTypeTemp(termLoanParameterRequest);
		
		cvlVehicleMultipleOptionRepoTemp.inActiveMasterByFpProductIdAndType(termLoanParameter.getId(), 3l);
		saveVehicleSegmentTemp(termLoanParameterRequest);

		// loan arrangements
		loanArrangementMappingTempRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveLoanArrangementsTemp(termLoanParameterRequest);
		
		//save constitution mapping
		fpConstitutionMappingTempRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveConstitutionTypeTemp(termLoanParameterRequest);
		
		//save nbfc ratio mapping
		fPParameterMappingService.inactiveAndSaveTemp(termLoanParameterRequest.getId(),CommonUtils.ParameterTypes.BUREAU_SCORE, termLoanParameterRequest.getBureauScoreIds());
		fPParameterMappingService.inactiveAndSaveTemp(termLoanParameterRequest.getId(),CommonUtils.ParameterTypes.BUREAU_SCORE_MAIN_DIR, termLoanParameterRequest.getMainDirBureauScoreIds());
		fPParameterMappingService.inactiveAndSaveTempWithObject(termLoanParameterRequest.getId(), CommonUtils.ParameterTypes.RISK_BASE_LOAN_AMOUNT, termLoanParameterRequest.getRiskLoanAmountList());

		boolean isUpdate = msmeValueMappingService.updateMsmeValueMappingTemp(
				termLoanParameterRequest.getMsmeFundingIds(), termLoanParameterRequest.getId(),
				termLoanParameterRequest.getUserId());
		logger.info(UPDATED_MSG, isUpdate);

		CommonDocumentUtils.endHook(logger, CommonUtils.SAVE_OR_UPDATE);
		return true;

	}


	private void saveResidenceStabilityTemp(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveResidenceStabilityTemp");
		CvlVehicleMultipleOptionTemp cvlVehicleMultipleOptionTemp= null;
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getResidencyStabilityIds())) {
		for (Long dataRequest : termLoanParameterRequest.getResidencyStabilityIds()) {
			cvlVehicleMultipleOptionTemp = new CvlVehicleMultipleOptionTemp();
			cvlVehicleMultipleOptionTemp.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOptionTemp.setMasterId(dataRequest);
			cvlVehicleMultipleOptionTemp.setTypeId(1l);
			cvlVehicleMultipleOptionTemp.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setCreatedDate(new Date());
			cvlVehicleMultipleOptionTemp.setModifiedDate(new Date());
			cvlVehicleMultipleOptionTemp.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepoTemp.save(cvlVehicleMultipleOptionTemp);
		}}
		logger.info("end saveResidenceStabilityTemp");
		
	}
	private void saveVehicleTypeTemp(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveVehicleTypeTemp");
		CvlVehicleMultipleOptionTemp cvlVehicleMultipleOptionTemp= null;
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getVehicleTypeIds())) {
		for (Long dataRequest : termLoanParameterRequest.getVehicleTypeIds()) {
			cvlVehicleMultipleOptionTemp = new CvlVehicleMultipleOptionTemp();
			cvlVehicleMultipleOptionTemp.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOptionTemp.setMasterId(dataRequest);
			cvlVehicleMultipleOptionTemp.setTypeId(2l);
			cvlVehicleMultipleOptionTemp.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setCreatedDate(new Date());
			cvlVehicleMultipleOptionTemp.setModifiedDate(new Date());
			cvlVehicleMultipleOptionTemp.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepoTemp.save(cvlVehicleMultipleOptionTemp);
		}}
		logger.info("end saveVehicleTypeTemp");
		
	}


	private void saveVehicleSegmentTemp(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveVehicleSegmentTemp");
		CvlVehicleMultipleOptionTemp cvlVehicleMultipleOptionTemp= null;
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getVehicleSegmentIds())) {
		for (Long dataRequest : termLoanParameterRequest.getVehicleSegmentIds()) {
			cvlVehicleMultipleOptionTemp = new CvlVehicleMultipleOptionTemp();
			cvlVehicleMultipleOptionTemp.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOptionTemp.setMasterId(dataRequest);
			cvlVehicleMultipleOptionTemp.setTypeId(3l);
			cvlVehicleMultipleOptionTemp.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOptionTemp.setCreatedDate(new Date());
			cvlVehicleMultipleOptionTemp.setModifiedDate(new Date());
			cvlVehicleMultipleOptionTemp.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepoTemp.save(cvlVehicleMultipleOptionTemp);
		}}
		logger.info("end saveVehicleSegmentTemp");
		
	}
	

	
	private void saveResidenceStability(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveResidenceStability");
		CvlVehicleMultipleOption cvlVehicleMultipleOption= null;
		
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getVehicleSegmentIds())) {
		for (Long dataRequest : termLoanParameterRequest.getResidencyStabilityIds()) {
			cvlVehicleMultipleOption = new CvlVehicleMultipleOption();
			cvlVehicleMultipleOption.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOption.setMasterId(dataRequest);
			cvlVehicleMultipleOption.setTypeId(1l);
			cvlVehicleMultipleOption.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setCreatedDate(new Date());
			cvlVehicleMultipleOption.setModifiedDate(new Date());
			cvlVehicleMultipleOption.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepo.save(cvlVehicleMultipleOption);
		}}
		logger.info("end saveResidenceStability");
		
	}
	private void saveVehicleType(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveVehicleType");
		CvlVehicleMultipleOption cvlVehicleMultipleOption= null;
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getVehicleTypeIds())) {
		for (Long dataRequest : termLoanParameterRequest.getVehicleTypeIds()) {
			cvlVehicleMultipleOption = new CvlVehicleMultipleOption();
			cvlVehicleMultipleOption.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOption.setMasterId(dataRequest);
			cvlVehicleMultipleOption.setTypeId(2l);
			cvlVehicleMultipleOption.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setCreatedDate(new Date());
			cvlVehicleMultipleOption.setModifiedDate(new Date());
			cvlVehicleMultipleOption.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepo.save(cvlVehicleMultipleOption);
		}}
		logger.info("end saveVehicleType");
		
	}


	private void saveVehicleSegment(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		logger.info("start saveVehicleSegment");
		
		CvlVehicleMultipleOption cvlVehicleMultipleOption= null;
		if(!CommonUtils.isObjectNullOrEmpty(termLoanParameterRequest.getVehicleSegmentIds())) {
		for (Long dataRequest : termLoanParameterRequest.getVehicleSegmentIds()) {
			cvlVehicleMultipleOption = new CvlVehicleMultipleOption();
			cvlVehicleMultipleOption.setFpProductId(termLoanParameterRequest.getId());
			cvlVehicleMultipleOption.setMasterId(dataRequest);
			cvlVehicleMultipleOption.setTypeId(3l);
			cvlVehicleMultipleOption.setCreatedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setModifiedBy(termLoanParameterRequest.getUserId());
			cvlVehicleMultipleOption.setCreatedDate(new Date());
			cvlVehicleMultipleOption.setModifiedDate(new Date());
			cvlVehicleMultipleOption.setIsActive(true);
			// create by and update
			cvlVehicleMultipleOptionRepo.save(cvlVehicleMultipleOption);
		}}
		logger.info("end saveVehicleSegment");
		
	}
	
	private void saveIndustryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveIndustryTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		logger.info("" + workingCapitalParameterRequest.getIndustrylist());
		for (DataRequest dataRequest : workingCapitalParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveIndustryTemp");
	}

	private void saveSectorTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveSectorTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveSectorTemp");
	}

	private void saveCountryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("save saveCountryTemp");
		GeographicalCountryDetailTemp geographicalCountryDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCountryList()) {
			geographicalCountryDetail = new GeographicalCountryDetailTemp();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCountryDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryTempRepository.save(geographicalCountryDetail);
		}
		logger.info("end saveCountryTemp");
	}

	private void saveStateTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveStateTemp");
		GeographicalStateDetailTemp geographicalStateDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getStateList()) {
			geographicalStateDetail = new GeographicalStateDetailTemp();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalStateDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateTempRepository.save(geographicalStateDetail);
		}
		logger.info("end saveStateTemp");
	}

	private void saveCityTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		logger.info("start saveCityTemp");
		List<GeographicalCityDetailTemp> list = new ArrayList<GeographicalCityDetailTemp>();
		GeographicalCityDetailTemp geographicalCityDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getCityList()) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(workingCapitalParameterRequest.getId());
			geographicalCityDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			list.add(geographicalCityDetail);

		}
		// create by and update
		geographicalCityTempRepository.save(list);
		logger.info("end saveCityTemp");
	}

	private void saveNegativeIndustryTemp(TermLoanParameterRequest workingCapitalParameterRequest) {
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustryTemp");
		NegativeIndustryTemp negativeIndustry = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getUnInterestedIndustrylist()) {
			negativeIndustry = new NegativeIndustryTemp();
			negativeIndustry.setFpProductMasterId(workingCapitalParameterRequest.getId());
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setModifiedBy(workingCapitalParameterRequest.getUserId());
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryTempRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustryTemp");

	}

	@SuppressWarnings("unchecked")
	@Override
	public TermLoanParameterRequest getNtbTermLoanParameterRequestTemp(Long id, Long role, Long userId) {
		CommonDocumentUtils.startHook(logger, "getNtbTermLoanParameterRequestTemp");
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		NtbTermLoanParameterTemp loanParameter = ntbTermLoanParameterTempRepository
				.getNtbTermLoanParameterTempByFpProductId(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenureNtb()))
			termLoanParameterRequest.setMaxTenureNtb(
					termLoanParameterRequest.getMaxTenureNtb().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenureNtb()))
			termLoanParameterRequest.setMinTenureNtb(
					termLoanParameterRequest.getMinTenureNtb().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));

		List<Long> industryList = industrySectorTempRepository.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> sectorList = industrySectorTempRepository.getSectorByProductId(termLoanParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> countryList = geographicalCountryTempRepository
				.getCountryByFpProductId(termLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateTempRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityTempRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}

		List<Long> negativeIndustryList = negativeIndustryTempRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setNegativeIndustryList(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_TEMP_MSG, e);
			}
		}
		List<DataRequest>  ratioMasterList;
		List<CoLendingRatio> listAllActiveByOrgkId;
		if(!CommonUtils.isObjectNullOrEmpty(role))
		{
		if(role.equals(WorkflowUtils.Role.NBFC_CHECKER) || role.equals(WorkflowUtils.Role.NBFC_MAKER))
		{
			listAllActiveByOrgkId =coLendingRatioRepository.listAllActiveProposalByOrgId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
			
		}
		else
		{
			listAllActiveByOrgkId = coLendingRatioRepository.listAllActiveByBankId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
		}
		
		for(CoLendingRatio coLendingRatio:listAllActiveByOrgkId)
		{
			DataRequest dataRequest=new DataRequest();
			dataRequest.setId(coLendingRatio.getId());
			dataRequest.setValue(coLendingRatio.getName());
			dataRequest.setTenure(coLendingRatio.getTenure());
			String label="Bank:"+coLendingRatio.getBankRatio().toString()+" Nbfc:"+coLendingRatio.getNbfcRatio()+" tenure:"+coLendingRatio.getTenure();
			dataRequest.setLabel(label);
			dataRequest.setUserOrgId(coLendingRatio.getUserOrgId());
			dataRequest.setBankId(coLendingRatio.getBankId());
			ratioMasterList.add(dataRequest);
		}
		
		termLoanParameterRequest.setNbfcRatioMasterList(ratioMasterList);
		CommonDocumentUtils.endHook(logger, "getNtbTermLoanParameterRequestTemp");

		termLoanParameterRequest.setJobId(loanParameter.getJobId());
		}

		// set workflow buttons

		if (!CommonUtils.isObjectNullOrEmpty(loanParameter.getJobId()) && !CommonUtils.isObjectNullOrEmpty(role)) {
			WorkflowResponse workflowResponse = workflowClient.getActiveStepForMaster(loanParameter.getJobId(),
					Arrays.asList(role), userId);
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse)
					&& !CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				try {
					WorkflowJobsTrackerRequest workflowJobsTrackerRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) workflowResponse.getData(),
							WorkflowJobsTrackerRequest.class);
					if (!CommonUtils.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep()) && !CommonUtils
							.isObjectNullOrEmpty(workflowJobsTrackerRequest.getStep().getStepActions())) {
						termLoanParameterRequest.setWorkflowData(workflowJobsTrackerRequest.getStep().getStepActions());
					} else {
						logger.info("response from workflow NULL jobId = {} and roleId = {}", loanParameter.getJobId(),
								role);
					}
				} catch (IOException e) {
					logger.error("Error While getting data from workflow {}", e);
				}
			}
		} else {
			logger.info("you set jobId or list of roleId NULL for calling workflow");
		}

		termLoanParameterRequest.setMsmeFundingIds(msmeValueMappingService.getDataListFromFpProductId(1, id, userId));
		termLoanParameterRequest.setGstType(fpGstTypeMappingTempRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setNbfcRatioIds(nbfcRatioMappingTempRepository.getTempIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setConstitutionIds(fpConstitutionMappingTempRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		logger.info("end getNtbTermLoanParameterRequestTemp");
		return termLoanParameterRequest;
	}

	@Override
	public Boolean saveOrUpdateNtbTemp(TermLoanParameterRequest termLoanParameterRequest) {
		NtbTermLoanParameterTemp termLoanParameter = null;

		if (termLoanParameterRequest.getAppstage() == 1) {
			termLoanParameter = ntbTermLoanParameterTempRepository.findOne(termLoanParameterRequest.getId());

		} else {

			termLoanParameter = ntbTermLoanParameterTempRepository
					.getNtbTermLoanParameterTempByFpProductMappingId(termLoanParameterRequest.getId());

		}

		if (termLoanParameter == null) {
			termLoanParameter = new NtbTermLoanParameterTemp();

		}

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenureNtb()))
			termLoanParameterRequest
					.setMaxTenureNtb(termLoanParameterRequest.getMaxTenureNtb().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenureNtb()))
			termLoanParameterRequest
					.setMinTenureNtb(termLoanParameterRequest.getMinTenureNtb().multiply(new BigDecimal("12")));

		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, "id");
		termLoanParameter
				.setUserId(termLoanParameterRequest.getUserId() != null ? termLoanParameterRequest.getUserId() : null);
		termLoanParameter.setProductId(
				termLoanParameterRequest.getProductId() != null ? termLoanParameterRequest.getProductId() : null);

		if (termLoanParameterRequest.getAppstage() != 1) {
			termLoanParameter.setFpProductMappingId(termLoanParameterRequest.getId());
		}
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);

		termLoanParameter.setStatusId(CommonUtils.Status.OPEN);
		termLoanParameter.setIsApproved(false);
		termLoanParameter.setIsDeleted(false);
		termLoanParameter.setIsCopied(false);
		termLoanParameter.setApprovalDate(null);
		if (CommonUtils.isObjectNullOrEmpty(termLoanParameter.getJobId())) {
			WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
					WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
					termLoanParameterRequest.getUserId());
			Long jobId = null;
			if (!CommonUtils.isObjectNullOrEmpty(workflowResponse.getData())) {
				jobId = Long.valueOf(workflowResponse.getData().toString());
			}

			termLoanParameter.setJobId(jobId);
		}

		termLoanParameter = ntbTermLoanParameterTempRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(termLoanParameter.getId());
		industrySectorTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		// industry data save
		saveIndustryTemp(termLoanParameterRequest);
		// Sector data save
		saveSectorTemp(termLoanParameterRequest);
		geographicalCountryTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		// country data save
		saveCountryTemp(termLoanParameterRequest);
		// state data save
		geographicalStateTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveStateTemp(termLoanParameterRequest);
		// city data save
		geographicalCityTempRepository.inActiveMappingByFpProductId(termLoanParameter.getId());
		saveCityTemp(termLoanParameterRequest);
		// negative industry save
		negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(termLoanParameter.getId());
		saveNegativeIndustryTemp(termLoanParameterRequest);
		
		//save nbfc ratio mapping
		nbfcRatioMappingTempRepository.inActiveTempByFpProductId(termLoanParameter.getId());
		//save constitution mapping
		fpConstitutionMappingTempRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveConstitutionTypeTemp(termLoanParameterRequest);
		
		
		boolean isUpdate = msmeValueMappingService.updateMsmeValueMappingTemp(
				termLoanParameterRequest.getMsmeFundingIds(), termLoanParameterRequest.getId(),
				termLoanParameterRequest.getUserId());
		logger.info(UPDATED_MSG, isUpdate);

		CommonDocumentUtils.endHook(logger, CommonUtils.SAVE_OR_UPDATE);
		return true;
	}
	
	@Override
	public Boolean saveMasterFromNtbTempTl(Long mappingId) throws LoansException {
		try {
			TermLoanParameterRequest temp = getNtbTermLoanParameterRequestTemp(mappingId, null, null);

			return saveOrUpdateNtb(temp, mappingId);

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION, e);
			return false;
		}
	}

	@Override
	public boolean saveOrUpdateNtb(TermLoanParameterRequest termLoanParameterRequest, Long mappingId) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdateTmp");
		NtbTermLoanParameterTemp loanParameter = ntbTermLoanParameterTempRepository
				.getNtbTermLoanParameterTempByFpProductId(mappingId);

		NTBParameter termLoanParameter = null;

		if (loanParameter.getFpProductMappingId() != null) {
			termLoanParameter = ntbParameterRepository.findOne(loanParameter.getFpProductMappingId());
		}
		if (termLoanParameter == null) {
			termLoanParameter = new NTBParameter();

		}

		loanParameter.setStatusId(CommonUtils.Status.APPROVED);
		loanParameter.setIsDeleted(false);
		loanParameter.setIsEdit(false);
		loanParameter.setIsCopied(true);
		loanParameter.setIsApproved(true);
		loanParameter.setApprovalDate(new Date());
		ntbTermLoanParameterTempRepository.save(loanParameter);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenureNtb()))
			termLoanParameterRequest
					.setMaxTenureNtb(termLoanParameterRequest.getMaxTenureNtb().multiply(new BigDecimal("12")));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenureNtb()))
			termLoanParameterRequest
					.setMinTenureNtb(termLoanParameterRequest.getMinTenureNtb().multiply(new BigDecimal("12")));

		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, "id");
		termLoanParameter
				.setUserId(termLoanParameterRequest.getUserId() != null ? termLoanParameterRequest.getUserId() : null);
		termLoanParameter.setProductId(
				termLoanParameterRequest.getProductId() != null ? termLoanParameterRequest.getProductId() : null);

		termLoanParameter.setModifiedBy(termLoanParameterRequest.getUserId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameter.setIsActive(true);
		termLoanParameter.setIsParameterFilled(true);
		termLoanParameter.setJobId(termLoanParameterRequest.getJobId());
		NTBParameter ntbParameter = ntbParameterRepository.save(termLoanParameter);
		termLoanParameterRequest.setId(ntbParameter.getId());

		industrySectorRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// industry data save
		saveIndustry(termLoanParameterRequest);
		// Sector data save
		saveSector(termLoanParameterRequest);
		geographicalCountryRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		// country data save
		saveCountry(termLoanParameterRequest);
		// state data save
		geographicalStateRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveState(termLoanParameterRequest);
		// city data save
		geographicalCityRepository.inActiveMappingByFpProductId(termLoanParameterRequest.getId());
		saveCity(termLoanParameterRequest);
		// negative industry save
		negativeIndustryRepository.inActiveMappingByFpProductMasterId(termLoanParameterRequest.getId());
		saveNegativeIndustry(termLoanParameterRequest);
		
		nbfcRatioMappingRepository.inActiveByFpProductId(termLoanParameterRequest.getId());
		
		//save constitution mapping
		fpConstitutionMappingRepository.inActiveMasterByFpProductId(termLoanParameterRequest.getId());
		saveConstitutionType(termLoanParameterRequest);

		boolean isUpdate = msmeValueMappingService.updateMsmeValueMapping(false, mappingId, ntbParameter.getId());
		logger.info(UPDATED_MSG, isUpdate);
		CommonDocumentUtils.endHook(logger, CommonUtils.SAVE_OR_UPDATE);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TermLoanParameterRequest getNtbTermLoanParameterRequest(Long id, Long role) {

		CommonDocumentUtils.startHook(logger, "getNtbTermLoanParameterRequest");

		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		NTBParameter loanParameter = ntbParameterRepository.getById(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);

		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMaxTenureNtb()))
			termLoanParameterRequest.setMaxTenureNtb(
					termLoanParameterRequest.getMaxTenureNtb().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));
		if (!CommonUtils.isObjectListNull(termLoanParameterRequest.getMinTenureNtb()))
			termLoanParameterRequest.setMinTenureNtb(
					termLoanParameterRequest.getMinTenureNtb().divide(new BigDecimal("12"), 2, RoundingMode.HALF_UP));

		List<Long> industryList = industrySectorRepository.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(industryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setIndustrylist(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> sectorList = industrySectorRepository.getSectorByProductId(termLoanParameterRequest.getId());
		if (!sectorList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getSectorById(sectorList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setSectorlist(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> countryList = geographicalCountryRepository
				.getCountryByFpProductId(termLoanParameterRequest.getId());
		if (!countryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCountryByCountryListId(countryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setCountryList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> stateList = geographicalStateRepository.getStateByFpProductId(termLoanParameterRequest.getId());
		if (!stateList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getStateByStateListId(stateList);

				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}

				termLoanParameterRequest.setStateList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_NTB_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> cityList = geographicalCityRepository.getCityByFpProductId(termLoanParameterRequest.getId());
		if (!cityList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getCityByCityListId(cityList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setCityList(dataRequests);

			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}

		List<Long> negativeIndustryList = negativeIndustryRepository
				.getIndustryByFpProductMasterId(termLoanParameterRequest.getId());
		if (!negativeIndustryList.isEmpty()) {
			try {
				OneFormResponse formResponse = oneFormClient.getIndustryById(negativeIndustryList);
				List<DataRequest> dataRequests = new ArrayList<>(formResponse.getListData().size());
				for (Object object : formResponse.getListData()) {
					DataRequest dataRequest = com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper
							.getObjectFromMap((LinkedHashMap<String, Object>) object, DataRequest.class);
					dataRequests.add(dataRequest);
				}
				termLoanParameterRequest.setNegativeIndustryList(dataRequests);
			} catch (Exception e) {
				logger.error(ERROR_WHILE_GET_TERM_LOAN_PARAMETER_REQUEST_MSG, e);
			}
		}
		List<DataRequest>  ratioMasterList;
		List<CoLendingRatio> listAllActiveByOrgkId;
		if(!CommonUtils.isObjectNullOrEmpty(role))
		{
		if(role.equals(WorkflowUtils.Role.NBFC_CHECKER) || role.equals(WorkflowUtils.Role.NBFC_MAKER))
		{
			listAllActiveByOrgkId =coLendingRatioRepository.listAllActiveProposalByOrgId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
			
		}
		else
		{
			listAllActiveByOrgkId = coLendingRatioRepository.listAllActiveByBankId(loanParameter.getUserOrgId());
			ratioMasterList=new ArrayList<>(listAllActiveByOrgkId.size());
		}
		
		for(CoLendingRatio coLendingRatio:listAllActiveByOrgkId)
		{
			DataRequest dataRequest=new DataRequest();
			dataRequest.setId(coLendingRatio.getId());
			dataRequest.setValue(coLendingRatio.getName());
			dataRequest.setTenure(coLendingRatio.getTenure());
			String label="Bank:"+coLendingRatio.getBankRatio().toString()+" NBFC:"+coLendingRatio.getNbfcRatio()+" Tenure:"+coLendingRatio.getTenure();
			dataRequest.setLabel(label);
			dataRequest.setUserOrgId(coLendingRatio.getUserOrgId());
			dataRequest.setBankId(coLendingRatio.getBankId());
			ratioMasterList.add(dataRequest);
		}
		
		termLoanParameterRequest.setNbfcRatioMasterList(ratioMasterList);
		}
		termLoanParameterRequest.setMsmeFundingIds(
				msmeValueMappingService.getDataListFromFpProductId(2, id, termLoanParameterRequest.getUserId()));
		termLoanParameterRequest.setGstType(fpGstTypeMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		termLoanParameterRequest.setNbfcRatioIds(nbfcRatioMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		
		termLoanParameterRequest.setConstitutionIds(fpConstitutionMappingRepository.getIdsByFpProductId(termLoanParameterRequest.getId()));
		CommonDocumentUtils.endHook(logger, "getNtbTermLoanParameterRequest");
		return termLoanParameterRequest;
	}

	private void saveLoanArrangements(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveLoanArrangements");
		LoanArrangementMapping loanArrangementMapping = null;
		for (Integer dataRequest : termLoanParameterRequest.getLoanArrangementIds()) {
			loanArrangementMapping = new LoanArrangementMapping();
			loanArrangementMapping.setFpProductId(termLoanParameterRequest.getId());
			loanArrangementMapping.setLoanArrangementId(dataRequest);
			loanArrangementMapping.setCreatedBy(termLoanParameterRequest.getUserId());
			loanArrangementMapping.setModifiedBy(termLoanParameterRequest.getUserId());
			loanArrangementMapping.setCreatedDate(new Date());
			loanArrangementMapping.setModifiedDate(new Date());
			loanArrangementMapping.setIsActive(true);
			// create by and update
			loanArrangementMappingRepository.save(loanArrangementMapping);
		}
		CommonDocumentUtils.endHook(logger, "saveLoanArrangements");

	}

	private void saveLoanArrangementsTemp(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub

		CommonDocumentUtils.startHook(logger, "saveLoanArrangementsTemp");
		LoanArrangementMappingTemp loanArrangementMapping = null;
		for (Integer dataRequest : termLoanParameterRequest.getLoanArrangementIds()) {
			loanArrangementMapping = new LoanArrangementMappingTemp();
			loanArrangementMapping.setFpProductId(termLoanParameterRequest.getId());
			loanArrangementMapping.setLoanArrangementId(dataRequest);
			loanArrangementMapping.setCreatedBy(termLoanParameterRequest.getUserId());
			loanArrangementMapping.setModifiedBy(termLoanParameterRequest.getUserId());
			loanArrangementMapping.setCreatedDate(new Date());
			loanArrangementMapping.setModifiedDate(new Date());
			loanArrangementMapping.setIsActive(true);
			// create by and update
			loanArrangementMappingTempRepository.save(loanArrangementMapping);
		}
		CommonDocumentUtils.endHook(logger, "saveLoanArrangementsTemp");

	}
	
	private void saveLoanGstType(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveGstTypeTemp");
		FpGstTypeMapping fpGstTypeMapping= null;
		for (Integer dataRequest : termLoanParameterRequest.getGstType()) {
			fpGstTypeMapping = new FpGstTypeMapping();
			fpGstTypeMapping.setFpProductId(termLoanParameterRequest.getId());
			fpGstTypeMapping.setGstTypeId(dataRequest);
			fpGstTypeMapping.setCreatedBy(termLoanParameterRequest.getUserId());
			fpGstTypeMapping.setModifiedBy(termLoanParameterRequest.getUserId());
			fpGstTypeMapping.setCreatedDate(new Date());
			fpGstTypeMapping.setModifiedDate(new Date());
			fpGstTypeMapping.setIsActive(true);
			// create by and update
			fpGstTypeMappingRepository.save(fpGstTypeMapping);
		}
		CommonDocumentUtils.endHook(logger, "saveGstTypeTemp");
		
	}
}
