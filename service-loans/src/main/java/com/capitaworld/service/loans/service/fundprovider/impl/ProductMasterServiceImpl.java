
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.IndustrySectorDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCityDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalCountryDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.GeographicalStateDetailTemp;
import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMapping;
import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMappingTemp;
import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustryTemp;
import com.capitaworld.service.loans.domain.fundprovider.NtbTermLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameterTemp;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.WorkflowData;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.model.corporate.UnsecuredLoanParameterRequest;
import com.capitaworld.service.loans.model.corporate.WcTlParameterRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.model.retail.RetailProduct;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCityTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalStateTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.MsmeValueMappingRepository;
import com.capitaworld.service.loans.repository.fundprovider.MsmeValueMappingTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.NegativeIndustryTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorTempRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.LapLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.PersonalLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.UnsecuredLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.WcTlParameterService;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.UserMainType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.BranchBasicDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class ProductMasterServiceImpl implements ProductMasterService {
	private static final Logger logger = LoggerFactory.getLogger(ProductMasterServiceImpl.class);
	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private WorkingCapitalParameterRepository workingCapitalParameterRepository;

	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;

	@Autowired
	private HomeLoanParameterRepository homeLoanParameterRepository;

	@Autowired
	private CarLoanParameterRepository carLoanParameterRepository;

	@Autowired
	private PersonalLoanParameterRepository personalLoanParameterRepository;

	@Autowired
	private LasParameterRepository lasParameterRepository;

	@Autowired
	private LapParameterRepository lapParameterRepository;

	@Autowired
	private FundProviderSequenceService fundProviderSequenceService;

	@Autowired
	private GeographicalCountryRepository geoCountry;

	@Autowired
	private WorkingCapitalParameterService workingCapitalParameterService;

	@Autowired
	private TermLoanParameterService termLoanParameterService;

	@Autowired
	private UnsecuredLoanParameterService unsecuredLoanParameterService;

	@Autowired
	private HomeLoanParameterService homeLoanParameterService;

	@Autowired
	private CarLoanParameterService carLoanParameterService;

	@Autowired
	private PersonalLoanParameterService personalLoanParameterService;

	@Autowired
	private LapLoanParameterService lapLoanParameterService;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private WcTlParameterService wcTlParameterService;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private ProductMasterTempRepository productMasterTempRepository;

	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private FPAsyncComponent fpAsyncComponent;
	
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
    private MsmeValueMappingRepository masterRepository;

    @Autowired
    private MsmeValueMappingTempRepository tempRepository;

    @Autowired
    private ProposalDetailsRepository proposalDetailsRepository;
    
    
    @Autowired
    private EntityManager entityManager; 
   
	
	@Override
	public Boolean saveOrUpdate(AddProductRequest addProductRequest, Long userOrgId) {
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");

		try {

			if (!CommonUtils.isObjectNullOrEmpty(addProductRequest.getProductMappingId())) {
				if (addProductRequest.getStage() == 2) {

					productMasterRepository.changeProductName(
							(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
									? addProductRequest.getUserId() : addProductRequest.getClientId()),
							addProductRequest.getProductMappingId(), addProductRequest.getName());
				} else {
					productMasterTempRepository.changeProductName(
							(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
									? addProductRequest.getUserId() : addProductRequest.getClientId()),
							addProductRequest.getProductMappingId(), addProductRequest.getName());
				}
				CommonDocumentUtils.endHook(logger, "saveOrUpdate");
				return true;
			} else {
				ProductMasterTemp productMaster = null;
				LoanType loanType = LoanType.getById(Integer.parseInt(addProductRequest.getProductId().toString()));
				WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
						WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL,
						(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
								? addProductRequest.getUserId() : addProductRequest.getClientId()));
				Long jobId = null;

				switch (loanType) {
				case WORKING_CAPITAL:
					productMaster = new WorkingCapitalParameterTemp();
					break;
				case TERM_LOAN:
					if (addProductRequest.getBusinessTypeId() == 2) {
						productMaster = new NtbTermLoanParameterTemp();
					} else {
						productMaster = new TermLoanParameterTemp();
					}
					break;
				case WCTL_LOAN:
					productMaster = new WcTlParameterTemp();
					break;
				case PERSONAL_LOAN:
					productMaster = new PersonalLoanParameterTemp();
					break;

				default:
					break;
				}
				List<DataRequest> industrySecIdList = null,secIdList=null,geogaphicallyCountry=null,geogaphicallyState=null,geogaphicallyCity=null,negativeIndList=null;
				if(!CommonUtils.isObjectNullOrEmpty(addProductRequest.getLoanId()))
				{
					switch (loanType) {
					case WORKING_CAPITAL:
						WorkingCapitalParameterTemp workingCapitalParameterTemp = new WorkingCapitalParameterTemp();
						WorkingCapitalParameterRequest workingCapitalParameterRequest=workingCapitalParameterService.getWorkingCapitalParameter(addProductRequest.getLoanId());
						industrySecIdList=workingCapitalParameterRequest.getIndustrylist();
						//set multiple value in temp
						industrySecIdList=workingCapitalParameterRequest.getIndustrylist();
						secIdList=workingCapitalParameterRequest.getSectorlist();
						geogaphicallyCountry=workingCapitalParameterRequest.getCountryList();
						geogaphicallyState=workingCapitalParameterRequest.getStateList();
						geogaphicallyCity=workingCapitalParameterRequest.getCityList();
						negativeIndList=workingCapitalParameterRequest.getUnInterestedIndustrylist();
						//END set multiple value in temp
						BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameterTemp,"id");
						productMaster = workingCapitalParameterTemp;
						productMaster.setIsParameterFilled(true);
						break;
					case TERM_LOAN:
						if (addProductRequest.getBusinessTypeId()==2) {
							//productMaster = new NtbTermLoanParameterTemp();
							
							NtbTermLoanParameterTemp ntbTermLoanParameterTemp = new NtbTermLoanParameterTemp();
							TermLoanParameterRequest termLoanParameterRequest=termLoanParameterService.getNtbTermLoanParameterRequest(addProductRequest.getLoanId());
							industrySecIdList=termLoanParameterRequest.getIndustrylist();
							//set multiple value in temp
							industrySecIdList=termLoanParameterRequest.getIndustrylist();
							secIdList=termLoanParameterRequest.getSectorlist();
							geogaphicallyCountry=termLoanParameterRequest.getCountryList();
							geogaphicallyState=termLoanParameterRequest.getStateList();
							geogaphicallyCity=termLoanParameterRequest.getCityList();
							negativeIndList=termLoanParameterRequest.getUnInterestedIndustrylist();
							//END set multiple value in temp
							BeanUtils.copyProperties(termLoanParameterRequest, ntbTermLoanParameterTemp,"id");
							productMaster = ntbTermLoanParameterTemp;
							productMaster.setIsParameterFilled(true);
						} else {
							//productMaster = new TermLoanParameterTemp();
							TermLoanParameterTemp termLoanParameterTemp = new TermLoanParameterTemp();
							TermLoanParameterRequest termLoanParameterRequest=termLoanParameterService.getTermLoanParameterRequest(addProductRequest.getLoanId());
							industrySecIdList=termLoanParameterRequest.getIndustrylist();
							//set multiple value in temp
							industrySecIdList=termLoanParameterRequest.getIndustrylist();
							secIdList=termLoanParameterRequest.getSectorlist();
							geogaphicallyCountry=termLoanParameterRequest.getCountryList();
							geogaphicallyState=termLoanParameterRequest.getStateList();
							geogaphicallyCity=termLoanParameterRequest.getCityList();
							negativeIndList=termLoanParameterRequest.getUnInterestedIndustrylist();
							//END set multiple value in temp
							BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameterTemp,"id");
							productMaster = termLoanParameterTemp;
							productMaster.setIsParameterFilled(true);
						}
						break;
					case WCTL_LOAN:
						WcTlParameterTemp wcTlParameterTemp= new WcTlParameterTemp();
						WcTlParameterRequest wcTlParameterRequest=wcTlParameterService.getWcTlRequest(addProductRequest.getLoanId());
						industrySecIdList=wcTlParameterRequest.getIndustrylist();
						//set multiple value in temp
						industrySecIdList=wcTlParameterRequest.getIndustrylist();
						secIdList=wcTlParameterRequest.getSectorlist();
						geogaphicallyCountry=wcTlParameterRequest.getCountryList();
						geogaphicallyState=wcTlParameterRequest.getStateList();
						geogaphicallyCity=wcTlParameterRequest.getCityList();
						negativeIndList=wcTlParameterRequest.getUnInterestedIndustrylist();
						//END set multiple value in temp
						BeanUtils.copyProperties(wcTlParameterRequest, wcTlParameterTemp,"id");
						productMaster = wcTlParameterTemp;
						productMaster.setIsParameterFilled(true);
						break;
					case PERSONAL_LOAN:
						productMaster = new PersonalLoanParameterTemp();
						PersonalLoanParameterTemp personalLoanParameterTemp = new PersonalLoanParameterTemp();
						PersonalLoanParameterRequest personalLoanParameterRequest=personalLoanParameterService.getPersonalLoanParameterRequest(addProductRequest.getLoanId());
					
						geogaphicallyCountry=personalLoanParameterRequest.getCountryList();
						geogaphicallyState=personalLoanParameterRequest.getStateList();
						geogaphicallyCity=personalLoanParameterRequest.getCityList();
						//negativeIndList=personalLoanParameterRequest.getUnInterestedIndustrylist();
						//END set multiple value in temp
						BeanUtils.copyProperties(personalLoanParameterRequest, personalLoanParameterTemp,"id");
						productMaster = personalLoanParameterTemp;
						productMaster.setIsParameterFilled(true);
						break;

					default:
						break;
					}
					
					
				}
				// productMaster.setJobId(null);
				if(workflowResponse != null){
					jobId = workflowResponse.getData() != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
				}
				
				productMaster.setJobId(jobId);

				productMaster.setProductId(addProductRequest.getProductId());
				productMaster.setIsMatched(false);
				productMaster.setName(addProductRequest.getName());
				productMaster.setFpName(addProductRequest.getFpName());
				productMaster.setUserId((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				productMaster.setCreatedBy((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				productMaster.setCreatedDate(new Date());
				productMaster.setModifiedBy((CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				productMaster.setIsParameterFilled(false);
				productMaster.setModifiedDate(new Date());
				// set business type id
				productMaster.setBusinessTypeId(addProductRequest.getBusinessTypeId());
				productMaster.setWcRenewalStatus(addProductRequest.getWcRenewalStatus());
				productMaster.setFinId(addProductRequest.getFinId());
				productMaster.setIsCopied(false);
				productMaster.setIsActive(true);
				productMaster.setUserOrgId(userOrgId);
				productMaster.setStatusId(1);
				productMaster.setProductCode(
						fundProviderSequenceService.getFundProviderSequenceNumber(addProductRequest.getProductId()));
				ProductMasterTemp productMaster2=productMasterTempRepository.save(productMaster);
				industrySectorTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
				// industry data save
				if(!CommonUtils.isListNullOrEmpty(industrySecIdList))
				saveIndustryTemp(productMaster2.getId(),industrySecIdList,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				// Sector data save
				if(!CommonUtils.isListNullOrEmpty(secIdList))
				saveSectorTemp(productMaster2.getId(),secIdList,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				geographicalCountryTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
				// country data save
				if(!CommonUtils.isListNullOrEmpty(geogaphicallyCountry))
				saveCountryTemp(productMaster2.getId(),geogaphicallyCountry,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				// state data save
				geographicalStateTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
				if(!CommonUtils.isListNullOrEmpty(geogaphicallyState))
				saveStateTemp(productMaster2.getId(),geogaphicallyState,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				// city data save
				geographicalCityTempRepository.inActiveMappingByFpProductId(productMaster2.getId());
				if(!CommonUtils.isListNullOrEmpty(geogaphicallyCity))
				saveCityTemp(productMaster2.getId(),geogaphicallyCity,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				// negative industry save
				negativeIndustryTempRepository.inActiveMappingByFpProductMasterId(productMaster2.getId());
				if(!CommonUtils.isListNullOrEmpty(negativeIndList))
				saveNegativeIndustryTemp(productMaster2.getId(),negativeIndList,(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
						? addProductRequest.getUserId() : addProductRequest.getClientId()));
				
				
				//msme value
				tempRepository.inActiveTempByFpProductId(productMaster2.getId());
				List<MsmeValueMapping> masterList = masterRepository.findByFpProductIdAndIsActive(addProductRequest.getLoanId(), true);
				
				if (!CommonUtils.isListNullOrEmpty(masterList)) {
                    for (MsmeValueMapping master : masterList) {
                    	MsmeValueMappingTemp temp= new MsmeValueMappingTemp();
                        BeanUtils.copyProperties(master,temp, "id");
                        temp.setActive(true);
                        temp.setFpProductId(productMaster2.getId());
                        temp.setCreatedBy(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
        						? addProductRequest.getUserId() : addProductRequest.getClientId());
                        temp.setCreatedDate(new Date());
                        temp.setModifiedBy(CommonUtils.isObjectNullOrEmpty(addProductRequest.getClientId())
        						? addProductRequest.getUserId() : addProductRequest.getClientId());
                        temp.setModifiedDate(new Date());
                        tempRepository.save(temp);
                    }
                }
				
				return true;
			}

		}

		catch (Exception e) {
			logger.error("error while saveOrUpdate : ", e);
			return false;
		}
	}

	private void saveNegativeIndustryTemp(Long id, List<DataRequest> negativeIndList,Long userId) {
		CommonDocumentUtils.startHook(logger, "saveNegativeIndustryTemp");
		NegativeIndustryTemp negativeIndustry = null;
		for (DataRequest dataRequest : negativeIndList) {
			negativeIndustry = new NegativeIndustryTemp();
			negativeIndustry.setFpProductMasterId(id);
			negativeIndustry.setIndustryId(dataRequest.getId());
			negativeIndustry.setCreatedBy(userId);
			negativeIndustry.setModifiedBy(userId);
			negativeIndustry.setCreatedDate(new Date());
			negativeIndustry.setModifiedDate(new Date());
			negativeIndustry.setIsActive(true);
			// create by and update
			negativeIndustryTempRepository.save(negativeIndustry);
		}
		CommonDocumentUtils.endHook(logger, "saveNegativeIndustryTemp");
	}

	private void saveCityTemp(Long id, List<DataRequest> geogaphicallyCity,Long userId) {

		logger.info("start saveCity");
		GeographicalCityDetailTemp geographicalCityDetail = null;
		//List<GeographicalCityDetailTemp> geographicalCityDetailTemps=new ArrayList<>(geogaphicallyCity.size()); 
		for (DataRequest dataRequest : geogaphicallyCity) {
			geographicalCityDetail = new GeographicalCityDetailTemp();
			geographicalCityDetail.setCityId(dataRequest.getId());
			geographicalCityDetail.setFpProductMaster(id);
			geographicalCityDetail.setCreatedBy(userId);
			geographicalCityDetail.setModifiedBy(userId);
			geographicalCityDetail.setCreatedDate(new Date());
			geographicalCityDetail.setModifiedDate(new Date());
			geographicalCityDetail.setIsActive(true);
			// create by and update
			geographicalCityTempRepository.save(geographicalCityDetail);
		}
		
		 
		
		     
		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("TDEMSPU");
        entityManager = emf.createEntityManager();


        entityManager.getTransaction().begin(); 

      //  List<Enquiry> tempEnqList = tempEnqList();
        for (Iterator<GeographicalCityDetailTemp> it = geographicalCityDetailTemps.iterator(); it.hasNext();) {
        	GeographicalCityDetailTemp geographicalCityDetailTemp = it.next();

        	entityManager.persist(geographicalCityDetailTemp);
        	entityManager.flush();
        	entityManager.clear();
        }

        entityManager.getTransaction().commit();*/
		logger.info("end saveCity");
		
	}

	private void saveStateTemp(Long id, List<DataRequest> geogaphicallyState,Long userId) {
		 logger.info("start saveStateTemp");
		GeographicalStateDetailTemp geographicalStateDetail = null;
		for (DataRequest dataRequest : geogaphicallyState) {
			geographicalStateDetail = new GeographicalStateDetailTemp();
			geographicalStateDetail.setStateId(dataRequest.getId());
			geographicalStateDetail.setFpProductMaster(id);
			geographicalStateDetail.setCreatedBy(userId);
			geographicalStateDetail.setModifiedBy(userId);
			geographicalStateDetail.setCreatedDate(new Date());
			geographicalStateDetail.setModifiedDate(new Date());
			geographicalStateDetail.setIsActive(true);
			// create by and update
			geographicalStateTempRepository.save(geographicalStateDetail);
		}
		logger.info("end saveStateTemp");
		
	}

	private void saveCountryTemp(Long id, List<DataRequest> geogaphicallyCountry,Long userId) {

		logger.info("save saveCountryTemp");
		GeographicalCountryDetailTemp geographicalCountryDetail = null;
		for (DataRequest dataRequest : geogaphicallyCountry) {
			geographicalCountryDetail = new GeographicalCountryDetailTemp();
			geographicalCountryDetail.setCountryId(dataRequest.getId());
			geographicalCountryDetail.setFpProductMaster(id);
			geographicalCountryDetail.setCreatedBy(userId);
			geographicalCountryDetail.setModifiedBy(userId);
			geographicalCountryDetail.setCreatedDate(new Date());
			geographicalCountryDetail.setModifiedDate(new Date());
			geographicalCountryDetail.setIsActive(true);
			// create by and update
			geographicalCountryTempRepository.save(geographicalCountryDetail);
		}
		logger.info("end saveCountryTemp");
		
	}

	private void saveSectorTemp(Long id, List<DataRequest> secIdList,Long userId) {

		logger.info("start saveSectorTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		for (DataRequest dataRequest : secIdList) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(id);
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(userId);
			industrySectorDetail.setModifiedBy(userId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveSectorTemp");
		
	}

	private void saveIndustryTemp(Long id, List<DataRequest> industrySecIdList,Long userId) {

		logger.info("start saveIndustryTemp");
		IndustrySectorDetailTemp industrySectorDetail = null;
		for (DataRequest dataRequest : industrySecIdList) {
			industrySectorDetail = new IndustrySectorDetailTemp();
			industrySectorDetail.setFpProductId(id);
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(userId);
			industrySectorDetail.setModifiedBy(userId);
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorTempRepository.save(industrySectorDetail);
		}
		logger.info("end saveIndustryTemp");
		
	}

	private void saveSectorTemp(List<DataRequest> secIdList,Long userId) {
		// Do nothing because of X and Y.
		
	}

	private void saveIndustryTemp(List<DataRequest> industrySecIdList,Long userId) {
		// Do nothing because of X and Y.
		
	}

	@Override
	public ProductMaster getProductMaster(Long id) {
		return productMasterRepository.findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject checkParameterIsFilled(Long productId) {
		CommonDocumentUtils.startHook(logger, "checkParameterIsFilled");
		ProductMaster productMaster = productMasterRepository.findOne(productId);
		JSONObject obj = new JSONObject();
		if (CommonUtils.isObjectNullOrEmpty(productMaster)) {
			obj.put("status", false);
			obj.put("message", "Product id is not valid");
			return obj;
		}
		if (!productMaster.getIsActive()) {
			obj.put("status", false);
			obj.put("message", "Requested User is In Active");
			return obj;
		}
		if (!productMaster.getIsParameterFilled()) {
			obj.put("status", false);
			obj.put("message", "Requested user has not filled parameter yet");
			return obj;
		}
		obj.put("status", true);
		obj.put("message", "Show teaser view");
		CommonDocumentUtils.endHook(logger, "checkParameterIsFilled");
		return obj;
	}

	@Override
	public List<ProductMasterRequest> getList(Long userId, Long userOrgId) {
		CommonDocumentUtils.startHook(logger, "getList");
		List<ProductMaster> results;
		if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
			results = productMasterRepository.getUserProductListByOrgId(userOrgId);
		} else {
			results = productMasterRepository.getUserProductList(userId);
		}
		List<ProductMasterRequest> requests = new ArrayList<>(results.size());
		for (ProductMaster master : results) {
			ProductMasterRequest request = new ProductMasterRequest();
			BeanUtils.copyProperties(master, request);
			request.setIsMatched(productMasterRepository.getMatchedAndActiveProduct(userId).size() > 0 ? true : false);
			requests.add(request);
		}
		CommonDocumentUtils.endHook(logger, "getList");
		return requests;
	}
	
	@Override
	public List<ProductMasterRequest> getActiveInActiveList(Long userId, Long userOrgId) {
		CommonDocumentUtils.startHook(logger, "getActiveInActiveList");
		UserResponse userResponse=null;
		BranchBasicDetailsRequest basicDetailsRequest = null;
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(userId);
			logger.info("Current user id ---------------------------------------------------> " + userId);
			userResponse = usersClient.getBranchDetailsBYUserId(usersRequest);
			basicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), BranchBasicDetailsRequest.class);
		} catch (Exception e) {
			logger.error("Throw Exception While Get Branch Id from UserId : ",e);
		}
		List<ProductMaster> results;
		if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
			results = productMasterRepository.getUserProductActiveInActiveListByOrgId(userOrgId);
		} else {
			results = productMasterRepository.getUserProductList(userId);
		}
		List<ProductMasterRequest> requests = new ArrayList<>(results.size());
		
		Long matchCount = productMasterRepository.countByUserIdAndIsMatched(userId, true);
		
		for (ProductMaster master : results) {
			ProductMasterRequest request = new ProductMasterRequest();
			BeanUtils.copyProperties(master, request);
//			request.setIsMatched(productMasterRepository.getMatchedAndActiveInActiveProduct(userId).size() > 0 ? true : false);
			request.setIsMatched(matchCount > 0 ? true : false);
			Long count = null;
			if(basicDetailsRequest != null && basicDetailsRequest.getId() != null){
				if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.BO || basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.FP_CHECKER) {
					count = proposalDetailsRepository.getProposalCountByFpProductIdAndBranchId(master.getId(), basicDetailsRequest.getId());
				}else if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.HO) {
					count = proposalDetailsRepository.getProposalCountByFpProductId(master.getId());
				}
				else if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.SMECC) // Hiren
				{
					count = proposalDetailsRepository.getProposalCountByFpProductIdAndBranchId(master.getId(),userResponse.getBranchList());
				}
				/*else {
					logger.info("Branch Id Can't found,set by assignee");
					count = proposalDetailsRepository.countProposalListOfFundProviderByAssignId(master.getId(), userId);
				}*/
			}else{
				count = proposalDetailsRepository.getProposalCountByFpProductId(master.getId());
			}
			request.setProposalCount(count);
			requests.add(request);
		}
		CommonDocumentUtils.endHook(logger, "getActiveInActiveList");
		return requests;
	}

	@Override
	public String getUserNameByApplicationId(Long productId, Long userId) {
		CommonDocumentUtils.startHook(logger, "getUserNameByApplicationId");
		ProductMaster productMaster = productMasterRepository.getUserProduct(productId, userId);
		if (productMaster != null) {
			CommonDocumentUtils.endHook(logger, "getUserNameByApplicationId");
			return productMaster.getFpName();
		}
		CommonDocumentUtils.endHook(logger, "getUserNameByApplicationId");
		return null;
	}

	@Override
	public Object[] getUserDetailsByPrductId(Long fpMappingId) {
		CommonDocumentUtils.startHook(logger, "getUserDetailsByPrductId");
		List<Object[]> pm = productMasterRepository.findById(fpMappingId);
		CommonDocumentUtils.endHook(logger, "getUserDetailsByPrductId");
		return (pm != null && !pm.isEmpty()) ? pm.get(0) : null;
	}

	@Override
	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId) {

		return productMasterRepository.getListByUserId(userId);

	}

	@Override
	public ProductDetailsResponse getProductDetailsResponse(Long userId, Long userOrgId) {
		CommonDocumentUtils.startHook(logger, "getProductDetailsResponse");
		UserResponse usrResponse = usersClient.getLastAccessApplicant(new UsersRequest(userId));
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
		if (usrResponse != null && usrResponse.getStatus() == 200) {
			Long fpMappingId = usrResponse.getId();
			if (fpMappingId != null) {
				ProductMaster userProduct = null;
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					userProduct = productMasterRepository.getUserProductByOrgId(fpMappingId, userOrgId);
				} else if (!CommonUtils.isObjectNullOrEmpty(userId)) {
					userProduct = productMasterRepository.getUserProduct(fpMappingId, userId);
				} else {
					userProduct = productMasterRepository.findByIdAndIsActive(fpMappingId, true);
				}
				productDetailsResponse.setProductId(userProduct.getProductId());
				productDetailsResponse.setProductMappingId(fpMappingId);
				productDetailsResponse.setMessage("Proposal Details Sent");
				productDetailsResponse.setStatus(HttpStatus.OK.value());
			} else {
				List<ProductMaster> userProductList = null;
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					userProductList = productMasterRepository.getUserProductListByOrgId(userOrgId);
				} else {
					userProductList = productMasterRepository.getUserProductList(userId);
				}
				if (!CommonUtils.isListNullOrEmpty(userProductList)) {
					ProductMaster productMaster = userProductList.get(0);
					productDetailsResponse.setProductId(productMaster.getProductId());
					productDetailsResponse.setProductMappingId(productMaster.getId());
					productDetailsResponse.setMessage("Proposal Details Sent");
					productDetailsResponse.setStatus(HttpStatus.OK.value());
					UsersRequest req = new UsersRequest();
					req.setId(productMaster.getId());
					usersClient.setLastAccessApplicant(req);
				} else {
					UsersRequest req = new UsersRequest();
					req.setId(null);
					usersClient.setLastAccessApplicant(req);
					productDetailsResponse.setMessage("Something went wrong");
					productDetailsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
				}
			}
		} else {
			productDetailsResponse.setMessage("Something went wrong");
			productDetailsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		}

		return productDetailsResponse;
	}

	@Override
	public FpProductDetails getProductDetails(Long productMappingId) throws Exception {
		CommonDocumentUtils.startHook(logger, "getProductDetails");
		ProductMaster productMaster = productMasterRepository.findOne(productMappingId);
		LoanType loanType = LoanType.getById(productMaster.getProductId());
		FpProductDetails fpProductDetails = new FpProductDetails();
		if (!CommonUtils.isObjectNullOrEmpty(productMaster.getName())) {
			fpProductDetails.setName(productMaster.getName());
		}
		switch (loanType) {
		case WORKING_CAPITAL:
			productMaster = workingCapitalParameterRepository.findOne(productMappingId);
			break;
		case TERM_LOAN:
			productMaster = termLoanParameterRepository.findOne(productMappingId);
			break;
		case HOME_LOAN:
			productMaster = homeLoanParameterRepository.findOne(productMappingId);
			break;
		case CAR_LOAN:
			productMaster = carLoanParameterRepository.findOne(productMappingId);
			break;
		case PERSONAL_LOAN:
			productMaster = personalLoanParameterRepository.findOne(productMappingId);
			break;
		case LOAN_AGAINST_PROPERTY:
			productMaster = lapParameterRepository.findOne(productMappingId);
			break;
		case LOAN_AGAINST_SHARES_AND_SECUIRITIES:
			productMaster = lasParameterRepository.findOne(productMappingId);
			break;

		default:
			break;
		}
		fpProductDetails.setTypeOfInvestment(LoanType.getById(productMaster.getProductId()).getValue());
		List<String> countryname = new ArrayList<String>();
		List<Long> countryList = geoCountry.getCountryByFpProductId(productMappingId);
		if (!CommonUtils.isListNullOrEmpty(countryList)) {
			OneFormResponse oneFormResponse = oneFormClient.getCountryByCountryListId(countryList);
			List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
			if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {

				for (int i = 0; i < oneResponseDataList.size(); i++) {
					MasterResponse masterResponse = MultipleJSONObjectHelper
							.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
					countryname.add(masterResponse.getValue());
				}
			}
			fpProductDetails.setGeographicalFocus(countryname);
		}

		// fp profile details
		fpProductDetails.setFpDashboard(usersClient.getFPDashboardDetails(productMaster.getUserId()));

		CommonDocumentUtils.endHook(logger, "getProductDetails");
		return fpProductDetails;
	}

	@Override
	public boolean isSelfView(Long fpProductId, Long userId) {
		return productMasterRepository.getUserProduct(fpProductId, userId) != null;
	}

	@Override
	public boolean isProductMatched(Long userId, MultipleFpPruductRequest multipleFpPruductRequest) throws IOException {
		CommonDocumentUtils.startHook(logger, "isProductMatched");
		List<ProductDetailsForSp> productDetailsForSps = productMasterRepository.getMatchedAndActiveProduct(userId);
		if (CommonUtils.isListNullOrEmpty(productDetailsForSps)) {
			return false;
		}
		if (!CommonUtils.isObjectNullOrEmpty(multipleFpPruductRequest)) {
			for (Map<String, Object> obj : multipleFpPruductRequest.getDataList()) {
				ProductMasterRequest productMasterRequest = (ProductMasterRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ProductMasterRequest.class);

				ProductMaster master = productMasterRepository.findOne(productMasterRequest.getId());
				if (!CommonUtils.isObjectNullOrEmpty(master)) {
					// if(master.getId())
					if (!productMasterRequest.getProductId().toString()
							.equals(productDetailsForSps.get(0).getProductId().toString())) {
						CommonDocumentUtils.endHook(logger, "isProductMatched");
						return true;
					}
				}
			}

		}
		CommonDocumentUtils.endHook(logger, "isProductMatched");
		return false;
	}

	@Override
	public int setIsMatchProduct(Long id, Long userId) {
		CommonDocumentUtils.startHook(logger, "setIsMatchProduct");
		CommonDocumentUtils.endHook(logger, "setIsMatchProduct");
		return productMasterRepository.setIsMatchProduct(id, userId);

	}

	@Override
	public List<ProductMasterRequest> getListByUserType(Long userId, Integer userType, Integer stage, Long userOrgId) {
		CommonDocumentUtils.startHook(logger, "getListByUserType");
		List<ProductMasterRequest> productMasterRequests = new ArrayList<>();

		if (!CommonUtils.isObjectNullOrEmpty(stage) && stage == 1) {
			List<ProductMasterTemp> results = null;
			if (userType == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					results = productMasterTempRepository.getUserRetailProductListByOrgId(userOrgId);
				} else {
					results = productMasterTempRepository.getUserRetailProductList(userId);
				}
				/*
				 * if (!CommonUtils.isListNullOrEmpty(results)) { for
				 * (ProductMaster master : results) { if (master.getProductId()
				 * == 3) { requests.add(homeLoanParameterService.
				 * getHomeLoanParameterRequest(master. getId())); } else if
				 * (master.getProductId() == 7) {
				 * requests.add(personalLoanParameterService.
				 * getPersonalLoanParameterRequest( master.getId())); } else if
				 * (master.getProductId() == 12) {
				 * requests.add(carLoanParameterService.
				 * getCarLoanParameterRequest(master.getId( ))); } else if
				 * (master.getProductId() == 13) {
				 * requests.add(lapLoanParameterService.getLapParameterRequest(
				 * master.getId())); } } }
				 */
				for (ProductMasterTemp productMaster : results) {
					ProductMasterRequest productMasterRequest = new ProductMasterRequest();
					BeanUtils.copyProperties(productMaster, productMasterRequest);
					productMasterRequests.add(productMasterRequest);
				}
			} else {

				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					results = productMasterTempRepository.getUserCorporateProductListByOrgId(userOrgId);
				} else {
					results = productMasterTempRepository.getUserCorporateProductList(userId);
				}
				/*
				 * if (!CommonUtils.isListNullOrEmpty(results)) { for
				 * (ProductMaster master : results) { if (master.getProductId()
				 * == 1) {
				 * requests.add(productMasterRepository.getOne()(master.getId())
				 * ); } else if (master.getProductId() == 2) {
				 * requests.add(termLoanParameterService.
				 * getTermLoanParameterRequest(master. getId())); } else if
				 * (master.getProductId() == 15) {
				 * requests.add(unsecuredLoanParameterService.
				 * getUnsecuredLoanParameterRequest( master.getId())); } else if
				 * (master.getProductId() == 16) {
				 * requests.add(wcTlParameterService.getWcTlRequest(master.getId
				 * ())); } } }
				 */
				for (ProductMasterTemp productMaster : results) {
					//System.out.println("ProductMasterTemp id: "+productMaster.getId()+" jobid : " + productMaster.getActiveInactiveJobId());
					ProductMasterRequest productMasterRequest = new ProductMasterRequest();
					BeanUtils.copyProperties(productMaster, productMasterRequest);
					productMasterRequests.add(productMasterRequest);
				}
			}
		} else {
			List<ProductMaster> results = null;
			if (userType == 1) {
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					results = productMasterRepository.getUserRetailProductListByOrgId(userOrgId);
				} else {
					results = productMasterRepository.getUserRetailProductList(userId);
				}
				/*
				 * if (!CommonUtils.isListNullOrEmpty(results)) { for
				 * (ProductMaster master : results) { if (master.getProductId()
				 * == 3) { requests.add(homeLoanParameterService.
				 * getHomeLoanParameterRequest(master. getId())); } else if
				 * (master.getProductId() == 7) {
				 * requests.add(personalLoanParameterService.
				 * getPersonalLoanParameterRequest( master.getId())); } else if
				 * (master.getProductId() == 12) {
				 * requests.add(carLoanParameterService.
				 * getCarLoanParameterRequest(master.getId( ))); } else if
				 * (master.getProductId() == 13) {
				 * requests.add(lapLoanParameterService.getLapParameterRequest(
				 * master.getId())); } } }
				 */
			} else {
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					results = productMasterRepository.getUserCorporateProductListByOrgId(userOrgId);
				} else {
					results = productMasterRepository.getUserCorporateProductList(userId);
				}
				/*
				 * if (!CommonUtils.isListNullOrEmpty(results)) { for
				 * (ProductMaster master : results) { if (master.getProductId()
				 * == 1) {
				 * requests.add(productMasterRepository.getOne()(master.getId())
				 * ); } else if (master.getProductId() == 2) {
				 * requests.add(termLoanParameterService.
				 * getTermLoanParameterRequest(master. getId())); } else if
				 * (master.getProductId() == 15) {
				 * requests.add(unsecuredLoanParameterService.
				 * getUnsecuredLoanParameterRequest( master.getId())); } else if
				 * (master.getProductId() == 16) {
				 * requests.add(wcTlParameterService.getWcTlRequest(master.getId
				 * ())); } } }
				 */
			}
			for (ProductMaster productMaster : results) {
				//System.out.println("ProductMaster is copied is" + productMaster.getActiveInactiveJobId());
				ProductMasterRequest productMasterRequest = new ProductMasterRequest();
				BeanUtils.copyProperties(productMaster, productMasterRequest);
				productMasterRequests.add(productMasterRequest);
			}
		}
		/*
		 * if (CommonUtils.isListNullOrEmpty(results)) return null; for
		 * (ProductMaster master : results) { ProductMasterRequest request = new
		 * ProductMasterRequest(); BeanUtils.copyProperties(master, request);
		 * request.setIsMatched(productMasterRepository.
		 * getMatchedAndActiveProduct(userId).size() > 0 ? true : false);
		 * requests.add(request); }
		 */
		CommonDocumentUtils.endHook(logger, "getListByUserType");

		return productMasterRequests;
	}

	@Override
	public Boolean changeStatus(Long fpProductId, Boolean status, Long userId, Integer stage) {
		CommonDocumentUtils.startHook(logger, "changeStatus");
		try {
			if (stage == 2) {
				productMasterRepository.changeStatus(userId, fpProductId, status);
			} else {
				productMasterTempRepository.changeStatus(userId, fpProductId, status);
			}
			return true;
		} catch (Exception e) {
			logger.error("error while changeStatus : ", e);
		}
		CommonDocumentUtils.endHook(logger, "changeStatus");
		return null;
	}

	@Override
	public Boolean saveCorporate(CorporateProduct corporateProduct) {
		CommonDocumentUtils.startHook(logger, "saveCorporate");
		if (!CommonUtils.isObjectNullOrEmpty(corporateProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateProduct.getProductId())) {
				if (corporateProduct.getProductId() == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {
					WorkingCapitalParameterRequest capitalParameterRequest = new WorkingCapitalParameterRequest();
					BeanUtils.copyProperties(corporateProduct, capitalParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return workingCapitalParameterService.saveOrUpdate(capitalParameterRequest, null);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.TERM_LOAN.getValue()) {
					TermLoanParameterRequest loanParameterRequest = new TermLoanParameterRequest();
					BeanUtils.copyProperties(corporateProduct, loanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return termLoanParameterService.saveOrUpdate(loanParameterRequest, null);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.UNSECURED_LOAN.getValue()) {
					UnsecuredLoanParameterRequest unsecuredLoanParameterRequest = new UnsecuredLoanParameterRequest();
					BeanUtils.copyProperties(corporateProduct, unsecuredLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return unsecuredLoanParameterService.saveOrUpdate(unsecuredLoanParameterRequest);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.WCTL_LOAN.getValue()) {
					WcTlParameterRequest wcTlParameterRequest = new WcTlParameterRequest();
					BeanUtils.copyProperties(corporateProduct, wcTlParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return wcTlParameterService.saveOrUpdate(wcTlParameterRequest, null);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveCorporate");
		return false;
	}

	@Override
	public Boolean saveRetail(RetailProduct retailProduct) {
		CommonDocumentUtils.startHook(logger, "saveRetail");
		if (!CommonUtils.isObjectNullOrEmpty(retailProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(retailProduct.getProductId())) {
				 if (retailProduct.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue()) {
					PersonalLoanParameterRequest personalLoanParameterRequest = new PersonalLoanParameterRequest();
					BeanUtils.copyProperties(retailProduct, personalLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetail");
					return personalLoanParameterService.saveOrUpdate(personalLoanParameterRequest,null);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveRetail");
		return false;
	}

	@Override
	public ProductMasterRequest lastAccessedProduct(Long userId) {
		CommonDocumentUtils.startHook(logger, "lastAccessedProduct");
		ProductMasterRequest productMasterRequest = new ProductMasterRequest();
		BeanUtils.copyProperties(productMasterRepository.getLastAccessedProduct(userId), productMasterRequest);
		CommonDocumentUtils.endHook(logger, "lastAccessedProduct");
		return productMasterRequest;
	}

	@Override
	public List<ChatDetails> getChatListByFpMappingId(Long mappingId) {
		ProposalMappingRequest mappingRequest = new ProposalMappingRequest();
		mappingRequest.setApplicationId(mappingId);
		try {
			List<LinkedHashMap<String, Object>> mappingRequestList = (List<LinkedHashMap<String, Object>>) proposalDetailsClient
					.getFundSeekerChatList(mappingRequest).getDataList();
			if (!CommonUtils.isListNullOrEmpty(mappingRequestList)) {
				List<ChatDetails> chatDetailList = new ArrayList<ChatDetails>(mappingRequestList.size());
				for (LinkedHashMap<String, Object> linkedHashMap : mappingRequestList) {
					try {
						ChatDetails chatDetails = new ChatDetails();
						ProposalMappingRequest proposalMappingRequest = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) linkedHashMap, ProposalMappingRequest.class);

						ProductMaster productMaster = productMasterRepository
								.findOne(proposalMappingRequest.getFpProductId());
						chatDetails.setProposalId(proposalMappingRequest.getId());
						chatDetails.setAppAndFpMappingId(proposalMappingRequest.getFpProductId());
						chatDetails.setIsAppFpProdActive(isProductActive(proposalMappingRequest.getFpProductId()));
						chatDetails.setName(productMaster.getFpName());

						// set profile pic
						DocumentRequest documentRequest = new DocumentRequest();
						documentRequest.setUserType(DocumentAlias.UERT_TYPE_USER);
						documentRequest.setUserId(productMaster.getUserId());
						documentRequest.setUserDocumentMappingId(DocumentAlias.FUND_PROVIDER_PROFIEL_PICTURE);
						try {
							DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
							if (!CommonUtils.isObjectNullOrEmpty(documentResponse)) {
								if (!CommonUtils.isListNullOrEmpty(documentResponse.getDataList())) {
									StorageDetailsResponse storageDetailsResponse = MultipleJSONObjectHelper
											.getObjectFromMap((LinkedHashMap<String, Object>) documentResponse
													.getDataList().get(0), StorageDetailsResponse.class);
									if (!CommonUtils.isObjectNullOrEmpty(storageDetailsResponse)) {
										chatDetails.setProfile(storageDetailsResponse.getFilePath());
									}
								}
							}
						} catch (DocumentException e) {
							logger.error(CommonUtils.EXCEPTION,e);
							throw new DocumentException(e.getMessage());
						}

						chatDetailList.add(chatDetails);
					} catch (IOException e) {
						logger.error(CommonUtils.EXCEPTION,e);
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
				return chatDetailList;
			}
		} catch (MatchException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}

	@Override
	public boolean isProductActive(Long productId) {
		Long count = productMasterRepository.getActiveProductsById(productId);
		if (!CommonUtils.isObjectNullOrEmpty(count) && (count > 0)) {
			return true;
		}
		return false;
	}

	@Override
	public List<ProductMasterRequest> getProductByOrgId(Long orgId) {
		logger.info("Start getProductByOrgId()");
		List<Integer> productIds = productMasterRepository.getProductsByOrgId(orgId);
		logger.info("Product Ids =={}======>Provided By====>{}", productIds, orgId);
		List<ProductMasterRequest> response = new ArrayList<>(productIds.size());
		for (Integer productId : productIds) {
			com.capitaworld.service.loans.utils.CommonUtils.LoanType type = CommonUtils.LoanType
					.getType(productId.intValue());
			if (CommonUtils.isObjectNullOrEmpty(type)) {
				continue;
			}
			ProductMasterRequest request = new ProductMasterRequest();
			request.setProductCode(type.getCode(false));
			request.setProductId(productId);
			request.setName(type.getName());
			response.add(request);
		}
		logger.info("End getProductByOrgId()");
		return response;
	}

	@Override
	public Object getProductMasterWithAllData(Long id, Integer stage, Long role, Long userId) {

		if (!CommonUtils.isObjectNullOrEmpty(stage) && stage == 1) {
			ProductMasterTemp master = productMasterTempRepository.findOne(id);

			if (master.getProductId() == 1) {
				return workingCapitalParameterService.getWorkingCapitalParameterTemp(master.getId(), role, userId);
			} else if (master.getProductId() == 2) {
				if (master.getBusinessTypeId() != null && master.getBusinessTypeId() == 2) {
					return termLoanParameterService.getNtbTermLoanParameterRequestTemp(id, role, userId);
				} else {
					return termLoanParameterService.getTermLoanParameterRequestTemp(master.getId(), role, userId);
				}
			} 
			else if (master.getProductId() == 7) {
				return personalLoanParameterService.getPersonalLoanParameterRequestTemp(master.getId(), role, userId);
			}
			/*
				 * else if (master.getProductId() == 15) { return
				 * unsecuredLoanParameterService.
				 * getUnsecuredLoanParameterRequest(master.getId()); }
				 */ else if (master.getProductId() == 16) {
				return wcTlParameterService.getWcTlRequestTemp(master.getId(), role, userId);
			}
		} else {
			ProductMaster master = productMasterRepository.findOne(id);

			if (master.getProductId() == 3) {
				return homeLoanParameterService.getHomeLoanParameterRequest(master.getId());
			} else if (master.getProductId() == 7) {
				return personalLoanParameterService.getPersonalLoanParameterRequest(master.getId());
			} else if (master.getProductId() == 12) {
				return carLoanParameterService.getCarLoanParameterRequest(master.getId());
			} else if (master.getProductId() == 13) {
				return lapLoanParameterService.getLapParameterRequest(master.getId());
			}

			else if (master.getProductId() == 1) {
				return workingCapitalParameterService.getWorkingCapitalParameter(master.getId());
			} else if (master.getProductId() == 2) {

				if (master.getBusinessTypeId() != null && master.getBusinessTypeId() == 2) {
					return termLoanParameterService.getNtbTermLoanParameterRequest(master.getId());
				} else {
					return termLoanParameterService.getTermLoanParameterRequest(master.getId());
				}
			} else if (master.getProductId() == 15) {
				return unsecuredLoanParameterService.getUnsecuredLoanParameterRequest(master.getId());
			} else if (master.getProductId() == 16) {
				return wcTlParameterService.getWcTlRequest(master.getId());
			}
		}
		return null;
	}

	@Override
	public Boolean saveCorporateMasterFromTemp(Long mappingId) throws Exception {

		ProductMasterTemp corporateProduct = productMasterTempRepository.getProductMasterTemp(mappingId);
		CommonDocumentUtils.startHook(logger, "saveCorporate");
		if (!CommonUtils.isObjectNullOrEmpty(corporateProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateProduct.getProductId())) {
				if (corporateProduct.getProductId() == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return workingCapitalParameterService.saveMasterFromTempWc(mappingId);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.TERM_LOAN.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					if (corporateProduct.getBusinessTypeId() != null && corporateProduct.getBusinessTypeId() == 2) {
						return termLoanParameterService.saveMasterFromNtbTempTl(mappingId);
					} else {
						return termLoanParameterService.saveMasterFromTempTl(mappingId);
					}

				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.WCTL_LOAN.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return wcTlParameterService.saveMasterFromTempWcTl(mappingId);
				}
				 else if (corporateProduct.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue()) {
						CommonDocumentUtils.endHook(logger, "saveCorporate");
						return personalLoanParameterService.saveMasterFromTempPl(mappingId);
					}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveCorporate");
		return false;
	}

	@Override
	public Boolean saveCorporateInTemp(CorporateProduct corporateProduct) {

		CommonDocumentUtils.startHook(logger, "saveCorporateInTemp");
		if (!CommonUtils.isObjectNullOrEmpty(corporateProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateProduct.getProductId())) {
				if (corporateProduct.getProductId() == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {
					WorkingCapitalParameterRequest capitalParameterRequest = new WorkingCapitalParameterRequest();
					BeanUtils.copyProperties(corporateProduct, capitalParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
					return workingCapitalParameterService.saveOrUpdateTemp(capitalParameterRequest);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.TERM_LOAN.getValue()) {
					if (corporateProduct.getBusinessTypeId() != null && corporateProduct.getBusinessTypeId() == 2) {
						TermLoanParameterRequest loanParameterRequest = new TermLoanParameterRequest();
						BeanUtils.copyProperties(corporateProduct, loanParameterRequest);
						CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
						return termLoanParameterService.saveOrUpdateNtbTemp(loanParameterRequest);
					} else {
						TermLoanParameterRequest loanParameterRequest = new TermLoanParameterRequest();
						BeanUtils.copyProperties(corporateProduct, loanParameterRequest);
						CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
						return termLoanParameterService.saveOrUpdateTemp(loanParameterRequest);
					}

				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.WCTL_LOAN.getValue()) {
					WcTlParameterRequest wcTlParameterRequest = new WcTlParameterRequest();
					BeanUtils.copyProperties(corporateProduct, wcTlParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
					return wcTlParameterService.saveOrUpdateTemp(wcTlParameterRequest);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveCorporate");
		return false;
	}

	@Override
	public Boolean clickOnWorkFlowButton(WorkflowData workflowData) {

		try {

			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(workflowData.getJobId());
			request.setUserId(workflowData.getUserId());
			
			ProductMasterTemp productMasterTemp = productMasterTempRepository.findOne(workflowData.getFpProductId());
			Integer productStatus = null;
			String productType = null;
			if(!CommonUtils.isObjectNullOrEmpty(productMasterTemp) && 
			   !CommonUtils.isObjectNullOrEmpty(productMasterTemp.getStatusId())) {
				productStatus = productMasterTemp.getStatusId();
			}
			
			if(productMasterTemp.getProductId() !=null) {
			  productType = CommonUtils.LoanType.getType(productMasterTemp.getProductId()).getName();
			}

			if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL) {
				int rowUpdated = productMasterTempRepository.updateStatusToInProgress(workflowData.getFpProductId(), 2);
				WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				if (rowUpdated > 0 && workflowResponse.getStatus() == 200) {
					if(!CommonUtils.isObjectNullOrEmpty(productMasterTemp)) {
						
						if(productStatus == CommonUtils.Status.REVERTED) {
							try {
								logger.info("Inside sending mail to Checker when Admin Maker resend product for Approval");
								fpAsyncComponent.sendEmailToCheckerWhenAdminMakerResendProductForApproval(productMasterTemp,workflowData.getUserId(),productType);	
							}
							catch(Exception e) {
								logger.error("Exception occured while sending mail to Checker when Admin Maker resend product for Approval : ",e);
							}
						}
						else if(productStatus == CommonUtils.Status.OPEN){
							try {
								logger.info("Inside sending mail to Checker when Admin Maker send product for Approval");
								fpAsyncComponent.sendEmailToCheckerWhenAdminMakerSendProductForApproval(productMasterTemp,workflowData.getUserId(),productType);	
							}
							catch(Exception e) {
								logger.error("Exception occured while sending mail to Checker when Admin Maker send product for Approval : ",e);
							}
						}
						
					}
					return true;
				} else {
					logger.info("could not updated in productMaster temp", workflowData.getJobId());
					return false;

				}
			} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED) {
				Boolean result = saveCorporateMasterFromTemp(workflowData.getFpProductId());
				if (result) {
					WorkflowResponse workflowResponse = workflowClient.updateJob(request);
					if (workflowResponse.getStatus() == 200) {
						if(!CommonUtils.isObjectNullOrEmpty(productMasterTemp)) {
							try {
								logger.info("Inside sending mail to Maker when Admin Checker Approved Product");
								fpAsyncComponent.sendEmailToMakerWhenAdminCheckerApprovedProduct(productMasterTemp,workflowData.getUserId(),productType);	
							}
							catch(Exception e) {
								logger.error("Exception occured while sending mail to Maker when Admin Checker Approved Product : ",e);
							}
						}
						return true;
					}
				}
			} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK) {
				int rowUpdated = productMasterTempRepository.updateStatusToInProgress(workflowData.getFpProductId(), 3);
				WorkflowResponse workflowResponse = workflowClient.updateJob(request);
				if (rowUpdated > 0 && workflowResponse.getStatus() == 200) {
					if(!CommonUtils.isObjectNullOrEmpty(productMasterTemp)) {
						try {
							logger.info("Inside sending mail to Maker when Admin Checker reverted Product");
							fpAsyncComponent.sendEmailToMakerWhenAdminCheckerRevertedProduct(productMasterTemp,workflowData.getUserId(),productType);	
						}
						catch(Exception e) {
							logger.error("Exception occured while sending mail to Maker when Admin Checker reverted Product : ",e);
						}
					}
					return true;
				} else {
					logger.info("could not updated in productMaster temp", workflowData.getJobId());
					return false;

				}

			}
			return false;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

	@Override
	public Boolean saveRetailInTemp(RetailProduct retailProduct) {

		CommonDocumentUtils.startHook(logger, "saveRetailInTemp");
		if (!CommonUtils.isObjectNullOrEmpty(retailProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(retailProduct.getProductId())) {
				if (retailProduct.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue()) {
					PersonalLoanParameterRequest personalLoanParameterRequest= new PersonalLoanParameterRequest();
					BeanUtils.copyProperties(retailProduct, personalLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetailInTemp");
					return personalLoanParameterService.saveOrUpdateTemp(personalLoanParameterRequest);
				} 
			}
		}
		CommonDocumentUtils.endHook(logger, "saveRetailInTemp");
		return false;
	}

	@Override
	public List<ProductMasterRequest> getApprovedListByProductType(Long userId, Integer productId, Integer businessId,Long userOrgId) {

		List<ProductMaster> results = null;
		List<ProductMasterRequest> productMasterRequests = new ArrayList<>();
		
			if(CommonUtils.getUserMainType(productId)==UserMainType.RETAIL)
			{
				if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
					results = productMasterRepository.getUserRetailProductListByOrgId(userOrgId);
				} else {
					results = productMasterRepository.getUserRetailProductList(userId);
				}
			}
		
			else
			{


		if(CommonUtils.getUserMainType(productId)== CommonUtils.UserMainType.RETAIL)
		{
			if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
				results = productMasterRepository.getUserRetailProductListByOrgId(userOrgId);
			} else {
				results = productMasterRepository.getUserRetailProductList(userId);
			}
		}

		else
		{

			if (!CommonUtils.isObjectNullOrEmpty(userOrgId)) {
				results = productMasterRepository.getUserCorporateProductListByOrgId(userOrgId);
			} else {
				results = productMasterRepository.getUserCorporateProductList(userId);
			}

			}

		}

		
		
		for (ProductMaster productMaster : results) {
			if(productMaster.getProductId()!=productId)
				continue;
				
			
			if (!CommonUtils.isObjectNullOrEmpty(businessId) && productMaster.getProductId()==2 && !CommonUtils.isObjectNullOrEmpty(productMaster.getBusinessTypeId())) {
				if(!businessId.toString().equals(productMaster.getBusinessTypeId().toString()))
						{
							continue;
						}
			}
			ProductMasterRequest productMasterRequest = new ProductMasterRequest();
			BeanUtils.copyProperties(productMaster, productMasterRequest);
			productMasterRequests.add(productMasterRequest);
		}
		
		return productMasterRequests;
	}

	@Override
	public Long createJobId(Long userId) {
		
		WorkflowResponse workflowResponse = workflowClient.createJobForMasters(
				WorkflowUtils.Workflow.MASTER_DATA_APPROVAL_PROCESS, WorkflowUtils.Action.SEND_FOR_APPROVAL, userId);
		Long jobId = workflowResponse != null ? Long.valueOf(workflowResponse.getData().toString()) : null;
		
		return jobId;
	}

	@Override
	public Boolean changeStatusWithWorkFlow(WorkflowData workflowData) {
		try {

			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(workflowData.getJobId());
			request.setUserId(workflowData.getUserId());
			
			ProductMasterTemp productMasterTemp = null;
			ProductMaster productMaster = null;
			Boolean status = null;
			
			if(workflowData.getStage() == 2) {
				productMaster = productMasterRepository.findOne(workflowData.getFpProductId());
			}else {
				productMasterTemp = productMasterTempRepository.findOne(workflowData.getFpProductId());
			}
			
			WorkflowResponse workflowResponse = workflowClient.updateJob(request);
			
			if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL && workflowResponse != null) {
//				System.out.println("fp_product_id : "+workflowData.getFpProductId()+" stage :"+workflowData.getStage()+" send for approval :" + workflowData.getJobId());
				
				if(workflowData.getActionFor() == null) {
					return false;
				}
				
				if (workflowData.getStage() == 2) {
					productMaster.setActiveInactiveJobId(workflowData.getJobId());
					productMaster.setActionFor(workflowData.getActionFor());
					productMasterRepository.save(productMaster);
				} else {
					productMasterTemp.setActiveInactiveJobId(workflowData.getJobId());
					productMasterTemp.setActionFor(workflowData.getActionFor());
					productMasterTempRepository.save(productMasterTemp);
				}
				
				return true;
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED  && workflowResponse != null) {
								
				if (workflowData.getStage() == 2) {
					if(productMaster.getActionFor() == null) {
						return false;
					}
					status = productMaster.getActionFor().equals("Active") ? true : false;
					productMasterRepository.changeStatusAndActiveInactiveJobId(workflowData.getUserId(), workflowData.getFpProductId(), status);
				} else {
					if(productMasterTemp.getActionFor() == null) {
						return false;
					}
					status = productMasterTemp.getActionFor().equals("Active") ? true : false;
					productMasterTempRepository.changeStatusAndActiveInactiveJobId(workflowData.getUserId(), workflowData.getFpProductId(), status);
				}
				return true;
				
			} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK  && workflowResponse != null) {
				if (workflowData.getStage() == 2) {
					productMaster.setActiveInactiveJobId(null);
					productMasterRepository.save(productMaster);
				} else {
					productMasterTemp.setActiveInactiveJobId(null);
					productMasterTempRepository.save(productMasterTemp);
				}
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
			return false;
		}
	}

}

