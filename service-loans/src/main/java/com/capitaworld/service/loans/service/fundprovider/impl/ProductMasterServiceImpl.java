package com.capitaworld.service.loans.service.fundprovider.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.WcTlParameterTemp;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameterTemp;
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
import com.capitaworld.service.loans.model.retail.CarLoanParameterRequest;
import com.capitaworld.service.loans.model.retail.HomeLoanParameterRequest;
import com.capitaworld.service.loans.model.retail.LapParameterRequest;
import com.capitaworld.service.loans.model.retail.PersonalLoanParameterRequest;
import com.capitaworld.service.loans.model.retail.RetailProduct;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterTempRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
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
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
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
					productMaster = new TermLoanParameterTemp();
					break;
				case WCTL_LOAN:
					productMaster = new WcTlParameterTemp();
					break;

				default:
					break;
				}

				// productMaster.setJobId(null);
				jobId = Long.valueOf(workflowResponse.getData().toString());
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
				productMaster.setIsActive(true);
				productMaster.setUserOrgId(userOrgId);
				productMaster.setProductCode(
						fundProviderSequenceService.getFundProviderSequenceNumber(addProductRequest.getProductId()));
				productMasterTempRepository.save(productMaster);
				return true;
			}

		}

		catch (Exception e) {
			e.printStackTrace();
			logger.error("error while saveOrUpdate", e);
			return false;
		}
	}

	@Override
	public ProductMaster getProductMaster(Long id) {
		// TODO Auto-generated method stub
		return productMasterRepository.findOne(id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject checkParameterIsFilled(Long productId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
	public String getUserNameByApplicationId(Long productId, Long userId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		CommonDocumentUtils.endHook(logger, "setIsMatchProduct");
		return productMasterRepository.setIsMatchProduct(id, userId);

	}

	@Override
	public List<ProductMasterRequest> getListByUserType(Long userId, Integer userType, Integer stage, Long userOrgId) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "changeStatus");
		try {
			if (stage == 2) {
				productMasterRepository.changeStatus(userId, fpProductId, status);
			} else {
				productMasterTempRepository.changeStatus(userId, fpProductId, status);
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			logger.error("error while changeStatus", e);
		}
		CommonDocumentUtils.endHook(logger, "changeStatus");
		return null;
	}

	@Override
	public Boolean saveCorporate(CorporateProduct corporateProduct) {
		// TODO Auto-generated method stub
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
					return termLoanParameterService.saveOrUpdate(loanParameterRequest,null);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.UNSECURED_LOAN.getValue()) {
					UnsecuredLoanParameterRequest unsecuredLoanParameterRequest = new UnsecuredLoanParameterRequest();
					BeanUtils.copyProperties(corporateProduct, unsecuredLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return unsecuredLoanParameterService.saveOrUpdate(unsecuredLoanParameterRequest);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.WCTL_LOAN.getValue()) {
					WcTlParameterRequest wcTlParameterRequest = new WcTlParameterRequest();
					BeanUtils.copyProperties(corporateProduct, wcTlParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return wcTlParameterService.saveOrUpdate(wcTlParameterRequest,null);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveCorporate");
		return false;
	}

	@Override
	public Boolean saveRetail(RetailProduct retailProduct) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveRetail");
		if (!CommonUtils.isObjectNullOrEmpty(retailProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(retailProduct.getProductId())) {
				if (retailProduct.getProductId() == CommonUtils.LoanType.CAR_LOAN.getValue()) {
					CarLoanParameterRequest carLoanParameterRequest = new CarLoanParameterRequest();
					BeanUtils.copyProperties(retailProduct, carLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetail");
					return carLoanParameterService.saveOrUpdate(carLoanParameterRequest);
				} else if (retailProduct.getProductId() == CommonUtils.LoanType.HOME_LOAN.getValue()) {
					HomeLoanParameterRequest homeLoanParameterRequest = new HomeLoanParameterRequest();
					BeanUtils.copyProperties(retailProduct, homeLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetail");
					return homeLoanParameterService.saveOrUpdate(homeLoanParameterRequest);
				} else if (retailProduct.getProductId() == CommonUtils.LoanType.PERSONAL_LOAN.getValue()) {
					PersonalLoanParameterRequest personalLoanParameterRequest = new PersonalLoanParameterRequest();
					BeanUtils.copyProperties(retailProduct, personalLoanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetail");
					return personalLoanParameterService.saveOrUpdate(personalLoanParameterRequest);
				} else if (retailProduct.getProductId() == CommonUtils.LoanType.LAP_LOAN.getValue()) {
					LapParameterRequest lapParameterRequest = new LapParameterRequest();
					BeanUtils.copyProperties(retailProduct, lapParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveRetail");
					return lapLoanParameterService.saveOrUpdate(lapParameterRequest);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveRetail");
		return false;
	}

	@Override
	public ProductMasterRequest lastAccessedProduct(Long userId) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "lastAccessedProduct");
		ProductMasterRequest productMasterRequest = new ProductMasterRequest();
		BeanUtils.copyProperties(productMasterRepository.getLastAccessedProduct(userId), productMasterRequest);
		CommonDocumentUtils.endHook(logger, "lastAccessedProduct");
		return productMasterRequest;
	}

	@Override
	public List<ChatDetails> getChatListByFpMappingId(Long mappingId) {
		// TODO Auto-generated method stub
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
							e.printStackTrace();
							throw new DocumentException(e.getMessage());
						}

						chatDetailList.add(chatDetails);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				return chatDetailList;
			}
		} catch (MatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		// TODO Auto-generated method stub

		if (!CommonUtils.isObjectNullOrEmpty(stage) && stage == 1) {
			ProductMasterTemp master = productMasterTempRepository.findOne(id);

			if (master.getProductId() == 1) {
				return workingCapitalParameterService.getWorkingCapitalParameterTemp(master.getId(), role, userId);
			} else if (master.getProductId() == 2) {
				return termLoanParameterService.getTermLoanParameterRequestTemp(master.getId(),role,userId);
			} /*
				 * else if (master.getProductId() == 15) { return
				 * unsecuredLoanParameterService.
				 * getUnsecuredLoanParameterRequest(master.getId()); }
				 */ else if (master.getProductId() == 16) {
				return wcTlParameterService.getWcTlRequestTemp(master.getId(),role,userId);
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
				return termLoanParameterService.getTermLoanParameterRequest(master.getId());
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
		// TODO Auto-generated method stub

		ProductMasterTemp corporateProduct = productMasterTempRepository.getProductMasterTemp(mappingId);
		CommonDocumentUtils.startHook(logger, "saveCorporate");
		if (!CommonUtils.isObjectNullOrEmpty(corporateProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateProduct.getProductId())) {
				if (corporateProduct.getProductId() == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return workingCapitalParameterService.saveMasterFromTempWc(mappingId);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.TERM_LOAN.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return termLoanParameterService.saveMasterFromTempTl(mappingId);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.WCTL_LOAN.getValue()) {
					CommonDocumentUtils.endHook(logger, "saveCorporate");
					return wcTlParameterService.saveMasterFromTempWcTl(mappingId);
				}
			}
		}
		CommonDocumentUtils.endHook(logger, "saveCorporate");
		return false;
	}

	@Override
	public Boolean saveCorporateInTemp(CorporateProduct corporateProduct) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveCorporateInTemp");
		if (!CommonUtils.isObjectNullOrEmpty(corporateProduct)) {
			if (!CommonUtils.isObjectNullOrEmpty(corporateProduct.getProductId())) {
				if (corporateProduct.getProductId() == CommonUtils.LoanType.WORKING_CAPITAL.getValue()) {
					WorkingCapitalParameterRequest capitalParameterRequest = new WorkingCapitalParameterRequest();
					BeanUtils.copyProperties(corporateProduct, capitalParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
					return workingCapitalParameterService.saveOrUpdateTemp(capitalParameterRequest);
				} else if (corporateProduct.getProductId() == CommonUtils.LoanType.TERM_LOAN.getValue()) {
					TermLoanParameterRequest loanParameterRequest = new TermLoanParameterRequest();
					BeanUtils.copyProperties(corporateProduct, loanParameterRequest);
					CommonDocumentUtils.endHook(logger, "saveCorporateInTemp");
					return termLoanParameterService.saveOrUpdateTemp(loanParameterRequest);
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
		// TODO Auto-generated method stub
		try {

			WorkflowRequest request = new WorkflowRequest();
			request.setActionId(workflowData.getActionId());
			request.setCurrentStep(workflowData.getWorkflowStep());
			request.setToStep(workflowData.getNextworkflowStep());
			request.setJobId(workflowData.getJobId());
			request.setUserId(workflowData.getUserId());

			WorkflowResponse workflowResponse = workflowClient.updateJob(request);
			if (workflowResponse.getStatus() == 200) {

				if (workflowData.getActionId() == WorkflowUtils.Action.SEND_FOR_APPROVAL) {
					int rowUpdated = productMasterTempRepository.updateStatusToInProgress(workflowData.getFpProductId(),
							2);
					if (rowUpdated > 0) {
						return true;
					} else {
						logger.info("could not updated in productMaster temp", workflowData.getJobId());
						return false;

					}
				} else if (workflowData.getActionId() == WorkflowUtils.Action.APPROVED) {
					return saveCorporateMasterFromTemp(workflowData.getFpProductId());
				} else if (workflowData.getActionId() == WorkflowUtils.Action.SEND_BACK) {
					int rowUpdated = productMasterTempRepository.updateStatusToInProgress(workflowData.getFpProductId(),
							3);
					if (rowUpdated > 0) {
						return true;
					} else {
						logger.info("could not updated in productMaster temp", workflowData.getJobId());
						return false;

					}

				}
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}