package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.gateway.client.GatewayClient;
import com.capitaworld.service.gateway.model.GatewayRequest;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateCoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryUnsecuredLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.GuarantorDetails;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AdminPanelLoanDetailsResponse;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoanEligibilityRequest;
import com.capitaworld.service.loans.model.PaymentRequest;
import com.capitaworld.service.loans.model.ReportResponse;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.EkycResponse;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.loans.model.mobile.MLoanDetailsResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.repository.common.LogDetailsRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateCoApplicantRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.CoApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.GuarantorDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryHomeLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.PrimaryLapLoanDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.ApplicationSequenceService;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.service.fundprovider.OrganizationReportsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateCoApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.exception.MatchException;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.matchengine.utils.MatchConstant;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
//import com.capitaworld.service.matchengine.model.ProposalStatusList;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.CampaignCode;
import com.capitaworld.service.oneform.enums.Constitution;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Gender;
import com.capitaworld.service.oneform.enums.LogDateTypeMaster;
import com.capitaworld.service.oneform.enums.OccupationNature;
import com.capitaworld.service.oneform.model.IrrBySectorAndSubSector;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.IndustryResponse;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FpProfileBasicDetailRequest;
import com.capitaworld.service.users.model.NetworkPartnerDetailsRequest;
import com.capitaworld.service.users.model.RegisteredUserResponse;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import com.capitaworld.service.users.model.mobile.MobileUserRequest;

@Service
@Transactional
public class LoanApplicationServiceImpl implements LoanApplicationService {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class.getName());

	@Autowired
	private Environment environment;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private CorporateCoApplicantService corporateCoApplicantService;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private CoApplicantDetailRepository coApplicantDetailRepository;

	@Autowired
	private CorporateCoApplicantRepository corporateCoApplicantRepository;

	@Autowired
	private GuarantorDetailsRepository guarantorDetailsRepository;

	@Autowired
	private ApplicationSequenceService applicationSequenceService;

	@Autowired
	private UsersClient userClient;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private CorporateUploadService corporateUploadService;

	@Autowired
	private LogService logService;

	@Autowired
	private GatewayClient gatewayClient;

	@Autowired
	private PrimaryLapLoanDetailRepository primaryLapLoanDetailRepository;

	@Autowired
	private PrimaryHomeLoanDetailRepository primaryHomeLoanDetailRepository;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private OrganizationReportsService organizationReportsService;

	@Autowired
	private LogDetailsRepository logDetailsRepository;

	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private RatingClient ratingClient;

	@Value("${capitaworld.service.gateway.product}")
	private String product;

	@Value("${capitaworld.service.gateway.amount}")
	private String amount;

	@Autowired
	private NetworkPartnerService networkPartnerService;
	/*@Autowired
	private AsyncComponent asyncComponent;
	*/
	@Override
	public boolean saveOrUpdate(FrameRequest commonRequest, Long userId) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = null;
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanApplicationRequest loanApplicationRequest = (LoanApplicationRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanApplicationRequest.class);
				LoanType type = CommonUtils.LoanType.getType(loanApplicationRequest.getProductId());
				if (type == null) {
					continue;
				}
				applicationMaster = getLoanByType(type);
				logger.info("userId==>" + (CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId()) ? userId
						: commonRequest.getClientId()));
				BeanUtils.copyProperties(loanApplicationRequest, applicationMaster, "name");
				applicationMaster.setUserId((CommonUtils.isObjectNullOrEmpty(commonRequest.getClientId()) ? userId
						: commonRequest.getClientId()));
				applicationMaster.setCreatedBy(userId);
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				if (CommonUtils.UserMainType.CORPORATE == CommonUtils
						.getUserMainType(loanApplicationRequest.getProductId())) {
					ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
					applicationStatusMaster.setId(CommonUtils.ApplicationStatus.OPEN);
					applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
					applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.OPEN);
				}
				applicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
				applicationMaster = loanApplicationRepository.save(applicationMaster);
			}
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest saveFromCampaign(Long userId, Long clientId, String campaignCode) throws Exception {
		try {
			String loanCode = com.capitaworld.service.users.utils.CommonUtils.getLoanCodeFromCode(campaignCode);
			Integer productId = CommonUtils.getProductIdByLoanCode(loanCode);
			LoanApplicationMaster applicationMaster = null;
			LoanType type = CommonUtils.LoanType.getType(productId);
			if (type == null) {
				logger.warn("Loan Type is NULL while Creating new Loan From Campaign================>");
				return null;
			}
			LoanApplicationRequest request = new LoanApplicationRequest();
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			applicationMaster = getLoanByType(type);
			applicationMaster.setUserId(finalUserId);
			applicationMaster.setProductId(productId);
			applicationMaster.setCreatedBy(userId);
			applicationMaster.setCreatedDate(new Date());
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
				ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
				applicationStatusMaster.setId(CommonUtils.ApplicationStatus.OPEN);
				applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			}
			applicationMaster.setCategoryCode(loanCode.toLowerCase());
			applicationMaster.setCampaignCode(campaignCode);
			applicationMaster
					.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
			applicationMaster = loanApplicationRepository.save(applicationMaster);
			BeanUtils.copyProperties(applicationMaster, request);
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationMaster.getId());
			usersRequest.setId(finalUserId);
			userClient.setLastAccessApplicant(usersRequest);
			return request;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details From Campaign:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public boolean saveOrUpdateFromLoanEligibilty(FrameRequest commonRequest, Long userId) throws Exception {
		logger.info("Entry in saveOrUpdateFromLoanEligibilty");
		try {
			LoanApplicationMaster applicationMaster = null;
			for (Map<String, Object> obj : commonRequest.getDataList()) {
				LoanEligibilityRequest loanEligibilityRequest = (LoanEligibilityRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, LoanEligibilityRequest.class);
				LoanType type = CommonUtils.LoanType.getType(loanEligibilityRequest.getProductId());
				if (type == null) {
					continue;
				}

				switch (type) {
				case WORKING_CAPITAL:
					applicationMaster = new PrimaryWorkingCapitalLoanDetail();
					break;
				case TERM_LOAN:
					applicationMaster = new PrimaryTermLoanDetail();
					break;
				/*
				 * case LAS_LOAN: applicationMaster = new PrimaryLasLoanDetail(); break;
				 */
				case LAP_LOAN:
					applicationMaster = new PrimaryLapLoanDetail();
					break;
				case PERSONAL_LOAN:
					applicationMaster = new PrimaryPersonalLoanDetail();
					break;
				case HOME_LOAN:
					applicationMaster = new PrimaryHomeLoanDetail();
					break;
				case CAR_LOAN:
					applicationMaster = new PrimaryCarLoanDetail();
					break;

				default:
					continue;
				}

				logger.info("userId==>" + userId);
				// BeanUtils.copyProperties(loanEligibilityRequest,
				// applicationMaster, "name");
				if (!CommonUtils.isObjectNullOrEmpty(loanEligibilityRequest.getTenure())) {
					applicationMaster.setTenure(loanEligibilityRequest.getTenure() * 12);
				}
				applicationMaster.setCategoryCode(loanEligibilityRequest.getCategoryCode()); // categaoryCode set
				applicationMaster.setProductId(loanEligibilityRequest.getProductId());
				applicationMaster.setUserId(userId);
				applicationMaster.setCreatedBy(userId);
				applicationMaster.setCreatedDate(new Date());
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				applicationMaster
						.setApplicationCode(applicationSequenceService.getApplicationSequenceNumber(type.getValue()));
				applicationMaster = loanApplicationRepository.save(applicationMaster);

				// for save primary details

				switch (type) {
				case WORKING_CAPITAL:

					break;
				case TERM_LOAN:

					break;
				/*
				 * case LAS_LOAN: applicationMaster = new PrimaryLasLoanDetail(); break;
				 */
				case LAP_LOAN:
					PrimaryLapLoanDetail lapLoanDetail = primaryLapLoanDetailRepository
							.findOne(applicationMaster.getId());
					lapLoanDetail.setPropertyValue(loanEligibilityRequest.getMarketValue());
					lapLoanDetail.setPropertyType(loanEligibilityRequest.getPropertyType());
					primaryLapLoanDetailRepository.save(lapLoanDetail);

					// create record in fs retail applicant
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case PERSONAL_LOAN:
					// create record in fs retail applicant
					RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
					retailApplicantDetail.setEmployedWithId(loanEligibilityRequest.getEmploymentType());
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case HOME_LOAN:
					PrimaryHomeLoanDetail primaryHomeLoanDetail = primaryHomeLoanDetailRepository
							.findOne(applicationMaster.getId());
					if (primaryHomeLoanDetail.getPropertyUsedType() == 3) {
						primaryHomeLoanDetail.setPropertyPrice(loanEligibilityRequest.getMarketValue());
					}
					if (primaryHomeLoanDetail.getPropertyType() == 6) {
						primaryHomeLoanDetail.setLandPlotCost(loanEligibilityRequest.getMarketValue());
					}
					primaryHomeLoanDetailRepository.save(primaryHomeLoanDetail);
					// create record in fs retail applicant
					saveRetailApplicantDetailFromLoanEligibility(applicationMaster, loanEligibilityRequest);
					break;
				case CAR_LOAN:
					break;

				default:
					continue;
				}
			}
			logger.info("Exit from saveOrUpdateFromLoanEligibilty");
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	public Boolean saveRetailApplicantDetailFromLoanEligibility(LoanApplicationMaster applicationMaster,
			LoanEligibilityRequest loanEligibilityRequest) {
		try {
			RetailApplicantDetail retailApplicantDetail = new RetailApplicantDetail();
			retailApplicantDetail.setApplicationId(applicationMaster);
			retailApplicantDetail.setOccupationId(loanEligibilityRequest.getEmploymentType());
			retailApplicantDetail.setBirthDate(loanEligibilityRequest.getDateOfBirth());
			retailApplicantDetail.setMonthlyIncome(loanEligibilityRequest.getIncome());
			retailApplicantDetail.setMonthlyLoanObligation(loanEligibilityRequest.getObligation());
			retailApplicantDetail.setIsActive(true);
			retailApplicantDetail.setCreatedBy(applicationMaster.getUserId());
			retailApplicantDetail.setModifiedBy(applicationMaster.getUserId());
			retailApplicantDetail.setCreatedDate(new Date());
			retailApplicantDetail.setModifiedDate(new Date());
			retailApplicantDetailRepository.save(retailApplicantDetail);
			return true;
		} catch (Exception e) {
			logger.error("Error while Saving RetailApplicantDetailFromLoanEligibility:-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public LoanApplicationRequest getLoanBasicDetails(Long id, Long userId) {
		LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
		LoanApplicationMaster applicationMaster = loanApplicationRepository.getById(id);
		if (applicationMaster == null) {
			return null;
		}
		applicationRequest.setApplicationCode(applicationMaster.getApplicationCode());
		applicationRequest.setProductId(applicationMaster.getProductId());
		applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));
		applicationRequest.setAmount(applicationMaster.getAmount());
		applicationRequest.setDenominationId(applicationMaster.getDenominationId());
		return applicationRequest;
	}

	@Override
	public LoanApplicationRequest get(Long id, Long userId) throws Exception {
		try {
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(id, userId);
			if (applicationMaster == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id + " of User ID==>" + userId);
			}
			BeanUtils.copyProperties(applicationMaster, applicationRequest, "name");
			applicationRequest.setHasAlreadyApplied(
					hasAlreadyApplied(userId, applicationMaster.getId(), applicationMaster.getProductId()));
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				applicationRequest.setLoanTypeMain(CommonUtils.CORPORATE);
				String currencyAndDenomination = "NA";
				if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getDenominationId())) {
					try {
						currencyAndDenomination = CommonDocumentUtils.getCurrency(applicationMaster.getCurrencyId());
						currencyAndDenomination = currencyAndDenomination.concat(
								" in " + CommonDocumentUtils.getDenomination(applicationMaster.getDenominationId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				applicationRequest.setCurrencyValue(currencyAndDenomination);
				applicationRequest.setLoanTypeSub(CommonUtils.getCorporateLoanType(applicationMaster.getProductId()));
				
				if(!CommonUtils.isObjectNullOrEmpty(applicationRequest.getTypeOfPayment()) && applicationRequest.getTypeOfPayment().equals(CommonUtils.PaymentMode.ONLINE)){
					GatewayRequest gatewayRequest = networkPartnerService.getPaymentStatuOfApplication(applicationRequest.getId());
					if(!CommonUtils.isObjectNullOrEmpty(gatewayRequest)){
						if(gatewayRequest.getStatus().equals(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS)){
							applicationRequest.setPaymentStatus(gatewayRequest.getStatus());
						}else{
							applicationRequest.setPaymentStatus(gatewayRequest.getStatus());
						}	
					}
				}
			} else {
				applicationRequest.setLoanTypeMain(CommonUtils.RETAIL);
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, applicationMaster.getId());
				applicationRequest.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
				applicationRequest.setLoanTypeSub("DEBT");
			}
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			try {
				ProposalMappingResponse response = proposalDetailsClient
						.getFundSeekerApplicationStatus(applicationMaster.getId());
				applicationRequest.setStatus(
						CommonUtils.isObjectNullOrEmpty(response.getData()) ? null : (Integer) response.getData());
				com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
						.getById(applicationMaster.getProductId());
				applicationRequest.setName(loanType.getValue());
				return applicationRequest;
			} catch (Exception e) {
				logger.error("Error while getting Status From Proposal Client");
				e.printStackTrace();
				return applicationRequest;
			}
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest inActive(Long id, Long userId) throws Exception {
		loanApplicationRepository.inActive(id, userId);
		List<LoanApplicationMaster> userLoans = loanApplicationRepository.getUserLoans(userId);
		UsersRequest usersRequest = new UsersRequest();
		if (!CommonUtils.isListNullOrEmpty(userLoans)) {
			LoanApplicationMaster loan = userLoans.get(0);
			usersRequest.setLastAccessApplicantId(loan.getId());
			usersRequest.setId(userId);
			userClient.setLastAccessApplicant(usersRequest);
			return new LoanApplicationRequest(loan.getId(), loan.getProductId());
		} else {
			usersRequest.setId(userId);
			usersRequest.setLastAccessApplicantId(null);
			userClient.setLastAccessApplicant(usersRequest);
		}
		return null;
	}

	@Override
	public List<LoanApplicationRequest> getList(Long userId) throws Exception {
		try {
			List<LoanApplicationMaster> results = loanApplicationRepository.getUserLoans(userId);
			List<LoanApplicationRequest> requests = new ArrayList<>(results.size());
			for (LoanApplicationMaster master : results) {
				LoanApplicationRequest request = new LoanApplicationRequest();
				BeanUtils.copyProperties(master, request, "name");
				request.setHasAlreadyApplied(hasAlreadyApplied(userId, master.getId(), master.getProductId()));
				int userMainType = CommonUtils.getUserMainType(master.getProductId());
				if (userMainType == CommonUtils.UserMainType.CORPORATE) {
					request.setLoanTypeMain(CommonUtils.CORPORATE);
					String currencyAndDenomination = "NA";
					if (!CommonUtils.isObjectNullOrEmpty(master.getCurrencyId())
							&& !CommonUtils.isObjectNullOrEmpty(master.getDenominationId())) {
						try {
							currencyAndDenomination = CommonDocumentUtils.getCurrency(master.getCurrencyId());
							currencyAndDenomination = currencyAndDenomination
									.concat(" in " + CommonDocumentUtils.getDenomination(master.getDenominationId()));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					request.setCurrencyValue(currencyAndDenomination);
					request.setLoanTypeSub(CommonUtils.getCorporateLoanType(master.getProductId()));
				} else {
					request.setLoanTypeMain(CommonUtils.RETAIL);
					Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, master.getId());
					request.setCurrencyValue(CommonDocumentUtils.getCurrency(currencyId));
					request.setLoanTypeSub("DEBT");
				}
				request.setProfilePrimaryLocked(master.getIsPrimaryLocked());
				request.setFinalLocked(master.getIsFinalLocked());
				try {
					if(!CommonUtils.isObjectNullOrEmpty(master.getApplicationStatusMaster())){
						request.setStatus(Integer.valueOf(master.getApplicationStatusMaster().getId().toString()));
						request.setIsNhbsApplication(true);
						request.setDdrStatusId(CommonUtils.isObjectListNull(master.getDdrStatusId()) ? null : Integer.valueOf(master.getDdrStatusId().toString()));
					}else{
						ProposalMappingResponse response = proposalDetailsClient
								.getFundSeekerApplicationStatus(master.getId());
						request.setStatus(
								CommonUtils.isObjectNullOrEmpty(response.getData()) ? null : (Integer) response.getData());
						request.setIsNhbsApplication(false);
					}					
					com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
							.getById(master.getProductId());
					request.setName(loanType.getValue());
					requests.add(request);
				} catch (Exception e) {
					logger.error(
							"Error while Getting Loan Status from Proposal Client or Proposal Service is not available:-");
					e.printStackTrace();
					// throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
			return requests;
		} catch (Exception e) {
			logger.error("Error while Getting Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<MLoanDetailsResponse> getLoanListForMobile(Long userId) {
		List<LoanApplicationMaster> loanApplicationMasterList = loanApplicationRepository.getUserLoans(userId);
		List<MLoanDetailsResponse> responseList = new ArrayList<>(loanApplicationMasterList.size());
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationMasterList) {
			MLoanDetailsResponse response = new MLoanDetailsResponse();
			response.setId(loanApplicationMaster.getId());
			response.setApplicationCode(loanApplicationMaster.getApplicationCode());
			response.setLoan(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));
			response.setAmount(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount())
					? loanApplicationMaster.getAmount()
					: 0.0);
			response.setCreatedDate(loanApplicationMaster.getCreatedDate());
			response.setProductId(loanApplicationMaster.getProductId());
			int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				response.setLoanType(CommonUtils.CORPORATE);
				String currencyAndDenomination = "NA";
				if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId())) {
					try {
						currencyAndDenomination = CommonDocumentUtils
								.getCurrency(loanApplicationMaster.getCurrencyId());
						currencyAndDenomination = currencyAndDenomination.concat(" in "
								+ CommonDocumentUtils.getDenomination(loanApplicationMaster.getDenominationId()));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				response.setCurrency(currencyAndDenomination);
			} else {
				response.setLoanType(CommonUtils.RETAIL);
				Integer currencyId = retailApplicantDetailRepository.getCurrency(userId, loanApplicationMaster.getId());
				response.setCurrency(CommonDocumentUtils.getCurrency(currencyId));
			}
			responseList.add(response);
		}
		return responseList;
	}

	@Override
	public List<LoanApplicationDetailsForSp> getLoanDetailsByUserIdList(Long userId) {
		return loanApplicationRepository.getListByUserId(userId);
	}

	@Override
	public boolean lockPrimary(Long applicationId, Long userId, boolean flag) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}

			applicationMaster.setIsPrimaryLocked(flag);
			loanApplicationRepository.save(applicationMaster);
			// create log when teaser submit
			logService.saveFsLog(applicationId, LogDateTypeMaster.TEASER_SUBMIT.getId());

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Profile and Primary Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public LoanApplicationRequest lockFinal(Long applicationId, Long userId, boolean flag) throws Exception {
		try {
			
			LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
			loanApplicationRequest.setIsMailSent(false);
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception(
						"LoanapplicationMaster object Must not be null while locking the Profile And Primary Details==>"
								+ applicationMaster);
			}
			applicationMaster.setIsFinalLocked(flag);
			applicationMaster
					.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.SUBMITTED));
			loanApplicationRepository.save(applicationMaster);

			// send FP notification
			ProposalMappingRequest request = new ProposalMappingRequest();
			request.setApplicationId(applicationId);
			request.setProposalStatusId(MatchConstant.ProposalStatus.ACCEPT);
			ProposalMappingResponse response = proposalDetailsClient.proposalListOfFundSeeker(request);
			NotificationRequest notificationRequest = new NotificationRequest();
			notificationRequest.setClientRefId(userId.toString());
			String fsName = getApplicantName(applicationId);
			for (int i = 0; i < response.getDataList().size(); i++) {
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) response.getDataList().get(i), ProposalMappingRequest.class);

				ProductMaster master = productMasterRepository.findOne(proposalrequest.getFpProductId());
				if (!master.getIsActive()) {
					logger.info("Product Id is InActive while get fundSeeker proposals=====>"
							+ proposalrequest.getFpProductId());
					continue;
				}
				UsersRequest userRequest = new UsersRequest();
				userRequest.setId(master.getUserId());
				Map<String, Object> parameters = new HashMap<String, Object>();
				// calling USER for getting fp details
				UserResponse userResponse = userClient.getFPDetails(userRequest);

				// FundProviderDetailsRequest fundProviderDetailsRequest =
				// MultipleJSONObjectHelper.getObjectFromMap(
				// (LinkedHashMap<String, Object>) userResponse.getData(),
				// FundProviderDetailsRequest.class);
				// fundProviderDetailsRequest.get
				try {
					if (CommonUtils.isObjectNullOrEmpty(fsName)) {
						parameters.put("fs_name", "NA");
					} else {
						parameters.put("fs_name", fsName);
					}

				} catch (Exception e) {
					// TODO: handle exception
					parameters.put("fs_name", "NA");
				}
				// String fpName = "";
				// try {
				// Object[] name = getFPName(proposalrequest.getFpProductId());
				// if (!CommonUtils.isObjectNullOrEmpty(name[1])) {
				// fpName = name[1].toString();
				// } else {
				// fpName = "NA";
				// }
				//
				// parameters.put("fp_name", CommonUtils.isObjectNullOrEmpty(fpName) ? "NA" :
				// fpName);
				// } catch (Exception e) {
				// // TODO: handle exception
				// e.printStackTrace();
				// parameters.put("fp_name", "NA");
				// }
				// try {
				// String fpPName =
				// productMasterRepository.getFpProductName(proposalrequest.getFpProductId());
				// if(CommonUtils.isObjectNullOrEmpty(fpPName))
				// {
				// parameters.put("fp_pname", "NA");
				// }
				// else
				// {
				// parameters.put("fp_pname", fpPName);
				// }
				//
				// } catch (Exception e) {
				// // TODO: handle exception
				// e.printStackTrace();
				// parameters.put("fp_pname", "NA");
				// }
				//
				String[] a = { master.getUserId().toString() };
				notificationRequest
						.addNotification(createNotification(a, userId, 0L, NotificationAlias.SYS_FS_FINAL_LOCK,
								parameters, applicationId, proposalrequest.getFpProductId()));
			}
			logger.info("Before send mail-------------------------------------->");
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				logger.info("Current loan is corporate-------------------------------------->");
				if(!CommonUtils.isObjectNullOrEmpty(applicationMaster.getNpAssigneeId()) 
						&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getNpUserId())) {
					logger.info("Start sending mail when maker has lock primary details");
					
					loanApplicationRequest.setId(applicationMaster.getId());
					loanApplicationRequest.setNpAssigneeId(applicationMaster.getNpAssigneeId());
					loanApplicationRequest.setNpUserId(applicationMaster.getNpUserId());
					loanApplicationRequest.setApplicationCode(applicationMaster.getApplicationCode());
					loanApplicationRequest.setProductId(applicationMaster.getProductId());
					loanApplicationRequest.setName(fsName);
					loanApplicationRequest.setIsMailSent(true);
					/*asyncComponent.sendEmailWhenMakerLockFinalDetails(applicationMaster.getNpAssigneeId(),applicationMaster.getNpUserId(),
							applicationMaster.getApplicationCode(),applicationMaster.getProductId(),fsName,applicationMaster.getId());*/				
				} else {
					logger.info("NP userId or assign id null or empty-------------------------------------->");
				}
				
			}
			notificationClient.send(notificationRequest);
			// send FP notification end

			// create log when teaser submit
			logService.saveFsLog(applicationId, LogDateTypeMaster.FINAL_SUBMIT.getId());
			return loanApplicationRequest;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Locking Final Information");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}
	}

	private Object[] getFPName(Long fpMappingId) {
		List<Object[]> pm = productMasterRepository.findById(fpMappingId);
		CommonDocumentUtils.endHook(logger, "getUserDetailsByPrductId");
		return (pm != null && !pm.isEmpty()) ? pm.get(0) : null;
	}

	private String getApplicantName(long applicationId) throws Exception {
		// TODO Auto-generated method stub
		try {
			String applicantName = getFsApplicantName(applicationId);
			return applicantName;
		} catch (LoansException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "NA";
		}
	}

	private static Notification createNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {

		Notification notification = new Notification();

		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);

		return notification;

	}

	@Override
	public UserResponse setLastAccessApplication(Long applicationId, Long userId) throws Exception {
		try {
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setLastAccessApplicantId(applicationId);
			usersRequest.setId(userId);
			UsersClient client = new UsersClient(environment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
			return client.setLastAccessApplicant(usersRequest);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);

		}

	}

	@Override
	public boolean hasAlreadyApplied(Long userId, Long applicationId, Integer productId) {
		if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(productId)) {
			return (corporateApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		} else {
			return (retailApplicantDetailRepository.hasAlreadyApplied(userId, applicationId) > 0 ? true : false);
		}
	}

	@Override
	public Integer getProductIdByApplicationId(Long applicationId, Long userId) throws Exception {
		try {
			return loanApplicationRepository.getProductIdByApplicationId(applicationId, userId);
		} catch (Exception e) {
			logger.error("Error while getting Product Id by Application Id");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Object[] getApplicationDetailsById(Long applicationId) {
		List<Object[]> data = loanApplicationRepository.getUserDetailsByApplicationId(applicationId);
		return (!CommonUtils.isListNullOrEmpty(data)) ? data.get(0) : null;
	}

	@Override
	public void updateFinalCommonInformation(Long applicationId, Long userId, Boolean flag, String finalFilledCount)
			throws Exception {
		try {
			loanApplicationRepository.setIsApplicantFinalMandatoryFilled(applicationId, userId, flag);
			loanApplicationRepository.setFinalFilledCount(applicationId, userId, finalFilledCount);
		} catch (Exception e) {
			logger.error("Error while updating final information flag");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isProfileAndPrimaryDetailFilled(Long applicationId, Long userId) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				boolean isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsApplicantDetailsFilled(),
						applicationMaster.getIsApplicantPrimaryFilled());
				if (isAnythingIsNull)
					return false;

				return (applicationMaster.getIsApplicantDetailsFilled()
						&& applicationMaster.getIsApplicantPrimaryFilled());
			} else {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
						|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue())
					return false;

				Long coApps = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationId, userId);

				if (coApps == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue())
						return false;
				} else if (coApps == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue())
						return false;
				}

				Long guarantors = guarantorDetailsRepository.getGuarantorCountByApplicationAndUserId(applicationId,
						userId);

				if (guarantors == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue())
						return false;
				} else if (guarantors == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue())
						return false;
				}

				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
						|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue())
					return false;

				/*
				 * if
				 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
				 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) return
				 * false;
				 */

				return true;
			}
		} catch (Exception e) {
			logger.error("Error while getting isProfileAndPrimaryDetailFilled ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isPrimaryLocked(Long applicationId, Long userId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkPrimaryDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isPrimaryLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isApplicationIdActive(Long applicationId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkApplicationIdActive(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isApplicationIdActive ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalDetailFilled(Long applicationId, Long userId) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				return false;
			}

			int userMainType = CommonUtils.getUserMainType(applicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				boolean isAnythingIsNull = false;
				if (applicationMaster.getProductId() == LoanType.UNSECURED_LOAN.getValue()) {
					isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsFinalMcqFilled(),
							applicationMaster.getIsApplicantFinalFilled(), applicationMaster.getIsFinalUploadFilled());
				} else {
					isAnythingIsNull = CommonUtils.isObjectListNull(applicationMaster.getIsFinalMcqFilled(),
							applicationMaster.getIsApplicantFinalFilled(),
							applicationMaster.getIsFinalDprUploadFilled(), applicationMaster.getIsFinalUploadFilled());
				}
				if (isAnythingIsNull)
					return false;

				if (applicationMaster.getProductId() == LoanType.UNSECURED_LOAN.getValue()) {
					return (applicationMaster.getIsFinalMcqFilled() && applicationMaster.getIsApplicantFinalFilled()
							&& applicationMaster.getIsFinalUploadFilled());
				} else {
					return (applicationMaster.getIsFinalMcqFilled() && applicationMaster.getIsApplicantFinalFilled()
							&& applicationMaster.getIsFinalDprUploadFilled()
							&& applicationMaster.getIsFinalUploadFilled());
				}

			} else {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
						|| !applicationMaster.getIsApplicantFinalFilled().booleanValue())
					return false;

				Long coApps = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationId, userId);
				/*
				 * if (CommonUtils.isObjectNullOrEmpty(coApps) && coApps == 0) return false;
				 */

				if (coApps == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue())
						return false;
				} else if (coApps == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue())
						return false;
				}

				Long guarantors = guarantorDetailsRepository.getGuarantorCountByApplicationAndUserId(applicationId,
						userId);
				/*
				 * if (CommonUtils.isObjectNullOrEmpty(guarantors) && guarantors == 0) return
				 * false;
				 */

				if (guarantors == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())
						return false;
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue())
						return false;
				} else if (guarantors == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue())
						return false;
				}

				// Here we are using MCQ column for Final Home loan and Final
				// Car Loan

				com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
						.getById(applicationMaster.getProductId());
				if (CommonUtils.isObjectNullOrEmpty(loanType)) {
					logger.warn("Invalid Product Id==>" + applicationMaster.getProductId());
					return false;
				}

				if ((loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
						|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue())) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
							|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
						return false;
					}
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("Error while getting isFinalDetailFilled ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isFinalLocked(Long applicationId, Long userId) throws Exception {
		try {
			Long count = loanApplicationRepository.checkFinalDetailIsLocked(applicationId);
			return (count != null ? count > 0 : false);
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getSelfViewAndPrimaryLocked(Long applicationId, Long userId) throws Exception {
		try {
			JSONObject json = new JSONObject();
			Long selfViewCount = loanApplicationRepository.isSelfApplicantView(applicationId, userId);
			json.put("isSelfView", (!CommonUtils.isObjectNullOrEmpty(selfViewCount) && selfViewCount > 0));
			json.put("isPrimaryLocked", isPrimaryLocked(applicationId, userId));
			return json;
		} catch (Exception e) {
			logger.error("Error while getting isFinalLocked ?");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Integer getCurrencyId(Long applicationId, Long userId) throws Exception {
		return loanApplicationRepository.getCurrencyId(applicationId, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getCurrencyAndDenomination(Long applicationId, Long userId) throws Exception {
		try {
			Integer currencyId = loanApplicationRepository.getCurrencyId(applicationId, userId);
			Integer denominationId = loanApplicationRepository.getDenominationId(applicationId, userId);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("currency", CommonDocumentUtils.getCurrency(currencyId));
			jsonObject.put("denomination", CommonDocumentUtils.getDenomination(denominationId));
			return jsonObject;
		} catch (Exception e) {
			logger.error("Error while getting Currency and Denomination Value");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public JSONObject isAllowToMoveAhead(Long applicationId, Long userId, Integer nextTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
		if (CommonUtils.UserMainType.CORPORATE == userMainType) {
			return corporateValidating(loanApplicationMaster, nextTabType, coAppllicantOrGuarantorId);
		} else {
			return retailValidating(loanApplicationMaster, nextTabType, coAppllicantOrGuarantorId);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject getBowlCount(Long applicationId, Long userId) {
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
		JSONObject response = new JSONObject();
		if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
			response.put("primaryFilledCount", loanApplicationMaster.getPrimaryFilledCount());
			response.put("profileFilledCount", loanApplicationMaster.getDetailsFilledCount());
			response.put("finalFilledCount", loanApplicationMaster.getFinalFilledCount());
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject corporateValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
		List<Long> coAppIds = null;

		Long coAppCount;

		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";
		JSONObject response = new JSONObject();
		response.put("message", "NA");
		response.put("result", true);

		switch (toTabType) {

		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			coAppIds = corporateCoApplicantService.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
					response.put("message", "Please CO-APPLICANT-1 details to Move Next !");
					response.put("result", false);
					return response;
				}
			}
			break;

		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put("result", false);
				return response;
			}
			break;

		case CommonUtils.TabType.CONNECTIONS:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Co-Applicant Profile Checking

			coAppCount = null;

			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_MCQ:

			// Co-Applicant Profile Checking
			coAppCount = null;
			coAppCount = corporateCoApplicantRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message",
			 * "Please Fill PRIMARY INFORMATION details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled())
			// || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			// response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put("result", false);
			// return response;
			// }
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_DPR_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}
			// if
			// (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
			// || !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
			// response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next
			// !");
			// response.put("result", false);
			// return response;
			// }
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message",
			 * "Please Fill PRIMARY INFORMATION details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message",
			 * "Please Fill PRIMARY INFORMATION details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
					|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL MCQ details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			if (applicationMaster.getProductId() != LoanType.UNSECURED_LOAN.getValue()) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalDprUploadFilled())
						|| !applicationMaster.getIsFinalDprUploadFilled().booleanValue()) {
					response.put("message", "Please Fill FINAL DPR details to Move Next !");
					response.put("result", false);
					return response;
				}
			}
			break;
		default:
			break;
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	private JSONObject retailValidating(LoanApplicationMaster applicationMaster, Integer toTabType,
			Long coAppllicantOrGuarantorId) throws Exception {
		List<Long> coAppIds = null;
		List<Long> guaIds = null;
		Long coAppCount = null;
		Long guarantorCount = null;
		int index = 0;
		final String INVALID_MSG = "Requested data is Invalid.";

		JSONObject response = new JSONObject();
		response.put("message", "NA");
		response.put("result", true);
		switch (toTabType) {
		case CommonUtils.TabType.MATCHES:
			boolean isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to See the matches !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.CONNECTIONS:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to See the connections !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.PROFILE_CO_APPLICANT:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
						|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
					response.put("message", "Please CO-APPLICANT-1 details to Move Next !");
					response.put("result", false);
					return response;
				}
			}
			break;
		case CommonUtils.TabType.PROFILE_GUARANTOR:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}

			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
						|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
					response.put("message", "Please GUARANTOR-1 details to Move Next !");
					response.put("result", false);
					return response;
				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_INFORMATION:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			break;
		case CommonUtils.TabType.PRIMARY_UPLOAD:
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			break;
		case CommonUtils.TabType.FINAL_INFORMATION:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppCount = coApplicantDetailRepository.getCoAppCountByApplicationAndUserId(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(coAppCount) || coAppCount > 0) {
				if (coAppCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (coAppCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}

				}
			}
			// Guarantor Profile Checking
			guarantorCount = guarantorDetailsRepository
					.getGuarantorCountByApplicationAndUserId(applicationMaster.getId(), applicationMaster.getUserId());
			if (!CommonUtils.isObjectNullOrEmpty(guarantorCount) || guarantorCount > 0) {
				if (guarantorCount == 1) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}

				if (guarantorCount == 2) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Primary Information Tab Validating
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Primary Upload Tab Validating
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			break;
		case CommonUtils.TabType.FINAL_CO_APPLICANT:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// Co-Applicant Profile Checking
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(coAppIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = coAppIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Profile Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Guarnator Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Final Check
			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
						|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
					response.put("message", "Please Fill CO-APPLICANT-1 Final Details to Move Next !");
					response.put("result", false);
					return response;
				}
			}

			break;
		case CommonUtils.TabType.FINAL_GUARANTOR:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			if (CommonUtils.isListNullOrEmpty(guaIds)) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			index = guaIds.indexOf(coAppllicantOrGuarantorId);
			if (index == -1) {
				response.put("message", INVALID_MSG);
				response.put("result", false);
				return response;
			}

			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			if (index == 1) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
						|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
					response.put("message", "Please Fill GUARANTOR-1 Final Details to Move Next !");
					response.put("result", false);
					return response;
				}
			}

			break;
		// for Final HomeLoan and CarLoan
		case CommonUtils.TabType.FINAL_MCQ:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			break;

		case CommonUtils.TabType.FINAL_UPLOAD:
			isPrimaryLocked = isPrimaryLocked(applicationMaster.getId(), applicationMaster.getUserId());
			if (!isPrimaryLocked) {
				response.put("message", "Please LOCK PRIMARY DETAILS to Move next !");
				response.put("result", false);
				return response;
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantDetailsFilled())
					|| !applicationMaster.getIsApplicantDetailsFilled().booleanValue()) {
				response.put("message", "Please Fill PROFILE details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT Profile Check
			coAppIds = coApplicantDetailRepository.getCoAppIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1DetailsFilled())
							|| !applicationMaster.getIsCoApp1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2DetailsFilled())
							|| !applicationMaster.getIsCoApp2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// Guarantor Profile Check
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1DetailsFilled())
							|| !applicationMaster.getIsGuarantor1DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2DetailsFilled())
							|| !applicationMaster.getIsGuarantor2DetailsFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantPrimaryFilled())
					|| !applicationMaster.getIsApplicantPrimaryFilled().booleanValue()) {
				response.put("message", "Please Fill PRIMARY INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}
			/*
			 * if
			 * (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryUploadFilled()
			 * ) || !applicationMaster.getIsPrimaryUploadFilled().booleanValue()) {
			 * response.put("message", "Please Fill PRIMARY UPLOAD details to Move Next !");
			 * response.put("result", false); return response; }
			 */
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsApplicantFinalFilled())
					|| !applicationMaster.getIsApplicantFinalFilled().booleanValue()) {
				response.put("message", "Please Fill FINAL INFORMATION details to Move Next !");
				response.put("result", false);
				return response;
			}

			// CO-APPLICANT FINAL Check
			for (int i = 0; i < coAppIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp1FinalFilled())
							|| !applicationMaster.getIsCoApp1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsCoApp2FinalFilled())
							|| !applicationMaster.getIsCoApp2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill CO-APPLICANT-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			// FOR FINAL GUARANTOR
			guaIds = guarantorDetailsRepository.getGuarantorIds(applicationMaster.getId(),
					applicationMaster.getUserId());
			for (int i = 0; i < guaIds.size(); i++) {
				if (i == 0) {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor1FinalFilled())
							|| !applicationMaster.getIsGuarantor1FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-1 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				} else {
					if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsGuarantor2FinalFilled())
							|| !applicationMaster.getIsGuarantor2FinalFilled().booleanValue()) {
						response.put("message", "Please Fill GUARANTOR-2 FINAL details to Move Next !");
						response.put("result", false);
						return response;
					}
				}
			}

			com.capitaworld.service.oneform.enums.LoanType loanType = com.capitaworld.service.oneform.enums.LoanType
					.getById(applicationMaster.getProductId());
			if (!CommonUtils.isObjectNullOrEmpty(loanType)
					&& (loanType.getId() == CommonUtils.LoanType.HOME_LOAN.getValue()
							|| loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue())) {
				if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsFinalMcqFilled())
						|| !applicationMaster.getIsFinalMcqFilled().booleanValue()) {
					if (loanType.getId() == CommonUtils.LoanType.CAR_LOAN.getValue()) {
						response.put("message", "Please Fill CAR-LOAN FINAL details to Move Next !");
					} else {
						response.put("message", "Please Fill HOME-LOAN FINAL details to Move Next !");
					}
					response.put("result", false);
					return response;
				}
			}
			break;
		default:
			break;
		}
		return response;
	}

	@Override
	public String getFsApplicantName(Long applicationId) throws Exception {
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster))
			return null;

		if (CommonUtils.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.RETAIL) {
			RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
					.findOneByApplicationIdId(applicationId);
			return retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName();
		} else if (CommonUtils
				.getUserMainType(applicationMaster.getProductId()) == CommonUtils.UserMainType.CORPORATE) {
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(applicationId);
			return corporateApplicantDetail.getOrganisationName();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RegisteredUserResponse> getUsersRegisteredLoanDetails(MobileLoanRequest loanRequest) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetUsersRegisteredLoanDetails, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());
		UserResponse userResponse = userClient.getRegisterdUserList(
				new MobileUserRequest(loanRequest.getUserType(), loanRequest.getFromDate(), loanRequest.getToDate()));

		List userList = (List) userResponse.getData();
		List<RegisteredUserResponse> response = new ArrayList<>();
		for (Object user : userList) {
			RegisteredUserResponse users = null;
			try {
				users = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>) user,
						RegisteredUserResponse.class);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (CommonUtils.isObjectNullOrEmpty(users)) {
				continue;
			}
			if (CommonUtils.CW_SP_USER_ID.equals(users.getUserId())) {
				continue;
			}
			if (!users.getIsOtpVerified() || !users.getIsEmailVerified()) {
				response.add(users);
				continue;
			}
			if (loanRequest.getUserType().intValue() == CommonUtils.UserType.FUND_SEEKER) {
				List<JSONObject> jsonList = new ArrayList<>();
				List<LoanApplicationMaster> userLoans = loanApplicationRepository.getUserLoans(users.getUserId());
				for (LoanApplicationMaster loanMstr : userLoans) {
					JSONObject obj = new JSONObject();
					obj.put("name", CommonUtils.LoanType.getType(loanMstr.getProductId()));

					String currency = "";
					int userMainType = CommonUtils.getUserMainType(loanMstr.getProductId());
					if (userMainType == CommonUtils.UserMainType.CORPORATE) {
						if (!CommonUtils.isObjectNullOrEmpty(loanMstr.getCurrencyId())
								&& !CommonUtils.isObjectNullOrEmpty(loanMstr.getDenominationId())) {
							currency = CommonDocumentUtils.getCurrency(loanMstr.getCurrencyId());
							currency = currency
									.concat(" in " + CommonDocumentUtils.getDenomination(loanMstr.getDenominationId()));
						}
					} else {
						Integer currencyId = retailApplicantDetailRepository.getCurrency(users.getUserId(),
								loanMstr.getId());
						currency = CommonDocumentUtils.getCurrency(currencyId);
					}
					obj.put("product", CommonUtils.getUserMainTypeName(loanMstr.getProductId()));
					obj.put("profileFilled", CommonUtils.getTotalBowlCount(loanMstr.getDetailsFilledCount(),
							loanMstr.getPrimaryFilledCount(), loanMstr.getFinalFilledCount()) / 3);
					obj.put("loanCode", loanMstr.getApplicationCode());
					DecimalFormat decimalFormat = new DecimalFormat("#.##");
					obj.put("amount",
							!CommonUtils.isObjectListNull(loanMstr.getAmount())
									? decimalFormat.format(loanMstr.getAmount())
									: 0);
					obj.put("currency", currency);
					obj.put("tenure", loanMstr.getTenure() != null ? String.valueOf(loanMstr.getTenure() / 12) : null);
					ProposalMappingRequest proposalMappingRequest = new ProposalMappingRequest();
					proposalMappingRequest.setApplicationId(loanMstr.getId());
					ProposalCountResponse proposalCountResponse = null;
					try {
						proposalCountResponse = proposalDetailsClient.proposalCountOfFundSeeker(proposalMappingRequest);
					} catch (Exception e) {
						e.printStackTrace();
						logger.warn(
								"Throw Exception while get matches count for registration user details------------->"
										+ loanMstr.getId());
					}
					if (!CommonUtils.isObjectNullOrEmpty(proposalCountResponse)) {
						obj.put("totalMatches", proposalCountResponse.getTotal());
						obj.put("matches", proposalCountResponse.getMatches());
						obj.put("directSent", proposalCountResponse.getSent());
						obj.put("directRecieved", proposalCountResponse.getReceived());
						obj.put("hold", proposalCountResponse.getHold());
						obj.put("reject", proposalCountResponse.getRejected());
						obj.put("approved", proposalCountResponse.getAdvanced());
						obj.put("accept", proposalCountResponse.getPrimary());

					}

					if (!CommonUtils.isObjectNullOrEmpty(loanMstr.getProductId())) {
						int productId = CommonUtils.getUserMainType(loanMstr.getProductId());
						if (productId == CommonUtils.UserMainType.CORPORATE) {
							List<Object[]> corporateDataList = corporateApplicantDetailRepository
									.getByNameAndLastUpdateDate(loanMstr.getUserId(), loanMstr.getId());
							if (!CommonUtils.isListNullOrEmpty(corporateDataList)) {
								Object[] corporateData = corporateDataList.get(0);
								obj.put("oneFormName",
										!CommonUtils.isObjectNullOrEmpty(corporateData[0]) ? corporateData[0].toString()
												: null);
							}
						} else {
							List<Object[]> retailDataList = retailApplicantDetailRepository
									.getNameAndLastUpdatedDate(loanMstr.getUserId(), loanMstr.getId());
							if (!CommonUtils.isListNullOrEmpty(retailDataList)) {
								Object[] retailData = retailDataList.get(0);
								obj.put("oneFormName",
										(!CommonUtils.isObjectNullOrEmpty(retailData[0]) ? retailData[0].toString()
												: null)
												+ " "
												+ (!CommonUtils.isObjectNullOrEmpty(retailData[1])
														? retailData[1].toString()
														: null));
							}
						}
					}

					jsonList.add(obj);
				}
				users.setLoanList(jsonList);
			}
			response.add(users);
		}
		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AdminPanelLoanDetailsResponse> getLoanDetailsForAdminPanel(Integer type, MobileLoanRequest loanRequest)
			throws Exception {

		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveUserId();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());

		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();

			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(!CommonUtils.isObjectNullOrEmpty(usersRequest) ? usersRequest.getEmail() : null);
			response.setApplicationId(loanApplicationMaster.getApplicationCode());
			response.setCreateDate(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCreatedDate())
					? loanApplicationMaster.getCreatedDate()
					: null);
			response.setProductName(CommonUtils.getUserMainTypeName(loanApplicationMaster.getProductId()));
			response.setSubProduct(CommonUtils.LoanType.getType(loanApplicationMaster.getProductId()).name());
			response.setAbsoluteAmount(loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(loanApplicationMaster.getAmount());
			response.setAmounInRuppes(false);
			int userMainType = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
			if (userMainType == CommonUtils.UserMainType.CORPORATE) {
				if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getCurrencyId())
						&& !CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId())) {
					response.setCurrency(CommonDocumentUtils.getCurrency(loanApplicationMaster.getCurrencyId()));
					if (loanApplicationMaster.getCurrencyId().equals(Currency.RUPEES.getId())) {
						response.setAmounInRuppes(true);
						Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
								loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
						response.setAbsoluteAmount(absoluteAmount);
						response.setAbsoluteDisplayAmount(absoluteAmount);
					}
				}
			} else {
				Integer currencyId = retailApplicantDetailRepository.getCurrency(loanApplicationMaster.getUserId(),
						loanApplicationMaster.getId());
				response.setCurrency(CommonDocumentUtils.getCurrency(currencyId));
				if (!CommonUtils.isObjectNullOrEmpty(currencyId)) {
					if (currencyId.equals(Currency.RUPEES.getId())) {
						response.setAmounInRuppes(true);
					}
				}
			}

			if (type == 1) {
				response.setTenure(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getTenure())
						? Double.valueOf((loanApplicationMaster.getTenure() / 12))
						: null);
			} else {
				response.setProfileAndPrimaryLocked(CommonUtils.getYesNo(loanApplicationMaster.getIsPrimaryLocked()));
				response.setFinalLocked(CommonUtils.getYesNo(loanApplicationMaster.getIsFinalLocked()));
				response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
				response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
				response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
				response.setTotalCount(CommonUtils.getTotalBowlCount(loanApplicationMaster.getDetailsFilledCount(),
						loanApplicationMaster.getPrimaryFilledCount(), loanApplicationMaster.getFinalFilledCount())
						/ 3);
			}

			if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getProductId())) {
				int productId = CommonUtils.getUserMainType(loanApplicationMaster.getProductId());
				if (productId == CommonUtils.UserMainType.CORPORATE) {
					if (type == 1) {
						CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
							response.setName(corporateApplicantDetail.getOrganisationName());
							response.setCity(CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
									oneFormClient));
							response.setState(CommonDocumentUtils.getState(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())
											? corporateApplicantDetail.getRegisteredStateId().longValue()
											: null,
									oneFormClient));
							response.setCountry(CommonDocumentUtils.getCountry(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())
											? corporateApplicantDetail.getRegisteredCountryId().longValue()
											: null,
									oneFormClient));
							response.setConstitution(
									!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getConstitutionId())
											? Constitution.getById(corporateApplicantDetail.getConstitutionId())
													.getValue()
											: null);
						}
					} else {
						List<Object[]> corporateDataList = corporateApplicantDetailRepository
								.getByNameAndLastUpdateDate(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isListNullOrEmpty(corporateDataList)) {
							Object[] corporateData = corporateDataList.get(0);
							response.setName(
									!CommonUtils.isObjectNullOrEmpty(corporateData[0]) ? corporateData[0].toString()
											: null);
							if (!CommonUtils.isObjectNullOrEmpty(corporateData[1])) {
								response.setLastUpdatedDate(corporateData[1].toString());
							}
						}
					}

				} else {
					if (type == 1) {
						RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail)) {
							response.setName(
									retailApplicantDetail.getFirstName() + " " + retailApplicantDetail.getLastName());
							response.setCity(CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(),
									oneFormClient));
							response.setState(CommonDocumentUtils.getState(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())
											? retailApplicantDetail.getPermanentStateId().longValue()
											: null,
									oneFormClient));
							response.setCountry(CommonDocumentUtils.getCountry(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())
											? retailApplicantDetail.getPermanentCountryId().longValue()
											: null,
									oneFormClient));
							response.setConstitution(
									!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getGenderId())
											? Gender.getById(retailApplicantDetail.getGenderId()).getValue()
											: null);

							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
								response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
							}

						}
					} else {
						List<Object[]> retailDataList = retailApplicantDetailRepository.getNameAndLastUpdatedDate(
								loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
						if (!CommonUtils.isListNullOrEmpty(retailDataList)) {
							Object[] retailData = retailDataList.get(0);
							response.setName(
									(!CommonUtils.isObjectNullOrEmpty(retailData[0]) ? retailData[0].toString() : null)
											+ " "
											+ (!CommonUtils.isObjectNullOrEmpty(retailData[1])
													? retailData[1].toString()
													: null));
							if (!CommonUtils.isObjectNullOrEmpty(retailData[2])) {
								response.setLastUpdatedDate(retailData[2].toString());
							}
						}
					}

				}
			}
			responseList.add(response);
		}
		return responseList;
	}

	// report1
	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanel(MobileLoanRequest loanRequest)
			throws IOException, Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: "Direct");
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));

			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}

			}
			responseList.add(response);
		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfNotEligibility(MobileLoanRequest loanRequest)
			throws Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			if (loanApplicationMaster.getEligibleAmnt() == null) {
				AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
				UsersRequest usersRequest = listOfObjects.stream()
						.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
				response.setEmail(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
				response.setMobile(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
				response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
						? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
						: "Direct");
				response.setLastLoginDate(
						!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
								: null);
				response.setProductName(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));

				response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
				response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
				response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
				Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
						loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
				response.setAbsoluteDisplayAmount(absoluteAmount);
				response.setCreateDate(loanApplicationMaster.getCreatedDate());
				// pincode
				if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
						|| loanApplicationMaster.getProductId() == 15) {//
					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
									loanApplicationMaster.getId());
					if (corporateApplicantDetail != null) {
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
							response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
						}
					}
				} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
						|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
						|| loanApplicationMaster.getProductId() == 14) {
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
							.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
									loanApplicationMaster.getId());
					if (retailApplicantDetail != null) {
						String applicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
							applicantName += retailApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
							applicantName += " " + retailApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
							applicantName += " " + retailApplicantDetail.getLastName();
						}
						response.setName(applicantName);
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
							response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
							response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
							response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
						}
						if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
							response.setIncomeType(
									OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
						}
					}
					List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
							.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
					if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
						String coApplicantName = "";
						if (coApplicantDetails.size() > 0) {
							CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
								coApplicantName += coApplicantDetail.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
								coApplicantName += " " + coApplicantDetail.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
								coApplicantName += " " + coApplicantDetail.getLastName();
							}
							response.setCoApplicant1Name(coApplicantName);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
								response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
								response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
							}
						}
						if (coApplicantDetails.size() > 2) {
							CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
							coApplicantName = "";
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
								coApplicantName += coApplicantDetail1.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
								coApplicantName += " " + coApplicantDetail1.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
								coApplicantName += " " + coApplicantDetail1.getLastName();
							}
							response.setCoApplicant2Name(coApplicantName);
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
								response.setCoApplicant2Age(
										CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
								response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
							}
						}
					}
				}
				responseList.add(response);
			}
		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfEligibility(MobileLoanRequest loanRequest)
			throws Exception {
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getFsIsSelfActiveForAdminPanel();
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		List<Long> userIds = new ArrayList<>();
		for (UsersRequest obj : listOfObjects) {
			userIds.add(obj.getId());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanel(userIds,
				loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			// code for got eligibility
			if (loanApplicationMaster.getEligibleAmnt() != null) {
				if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())) {
					AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
					UsersRequest usersRequest = listOfObjects.stream()
							.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
					response.setEmail(
							!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
					response.setMobile(
							!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile()
									: null);
					response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
							? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
							: "Direct");
					response.setLastLoginDate(!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate())
							? usersRequest.getSignUpDate()
							: null);
					response.setProductName(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));

					response.setProfileCount(
							CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
					response.setPrimaryCount(
							CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
					response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
					Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
							loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
					response.setAbsoluteDisplayAmount(absoluteAmount);
					response.setCreateDate(loanApplicationMaster.getCreatedDate());
					// pincode
					if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
							|| loanApplicationMaster.getProductId() == 15) {//
						CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (corporateApplicantDetail != null) {
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
								response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
							}
						}
					} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
							|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
							|| loanApplicationMaster.getProductId() == 14) {
						RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
								.getByApplicationAndUserId(loanApplicationMaster.getUserId(),
										loanApplicationMaster.getId());
						if (retailApplicantDetail != null) {
							String applicantName = "";
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
								applicantName += retailApplicantDetail.getFirstName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
								applicantName += " " + retailApplicantDetail.getMiddleName();
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
								applicantName += " " + retailApplicantDetail.getLastName();
							}
							response.setName(applicantName);
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
								response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
								response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
								response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
								response.setIncomeType(
										OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
							}
						}
						responseList.add(response);
					}
				}

			}
		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfFinalLockedRejectedByUbi(
			MobileLoanRequest loanRequest) throws IOException {

		List<List<Long>> master = organizationReportsService.getApplicationIdAndUserId();
		List<Long> userId = null;
		List<Long> applicationId = null;
		if (master != null) {
			applicationId = master.get(0);
			userId = master.get(1);
		}
		UsersRequest uRequest = new UsersRequest();
		uRequest.setIds(userId);
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getDetailsOfUsersForAdminPanel(uRequest);
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: "Direct");
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));

			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}
				List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
						.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
					String coApplicantName = "";
					if (coApplicantDetails.size() > 0) {
						CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
							coApplicantName += coApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
							coApplicantName += " " + coApplicantDetail.getLastName();
						}
						response.setCoApplicant1Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
							response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
							response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
						}
					}
					if (coApplicantDetails.size() > 2) {
						CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
						coApplicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
							coApplicantName += coApplicantDetail1.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail1.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
							coApplicantName += " " + coApplicantDetail1.getLastName();
						}
						response.setCoApplicant2Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
							response.setCoApplicant2Age(CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
							response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
						}
					}
				}
			}
			responseList.add(response);

		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<AdminPanelLoanDetailsResponse> getPostLoginForAdminPanelOfApprovedByUbi(MobileLoanRequest loanRequest)
			throws IOException, Exception {
		List<ReportResponse> master = organizationReportsService.getFpProductMappingId();
		List<Long> userId = new ArrayList<>();
		List<Long> applicationId = new ArrayList<>();
		for (ReportResponse reportResponse : master) {
			applicationId.add(reportResponse.getApplicationId());
			userId.add(reportResponse.getUserId());
		}
		UsersRequest uRequest = new UsersRequest();
		uRequest.setIds(userId);
		List<AdminPanelLoanDetailsResponse> responseList = new ArrayList<>();
		UserResponse userResponse = userClient.getDetailsOfUsersForAdminPanel(uRequest);
		if (userResponse.getStatus() != HttpStatus.OK.value()) {
			return null;
		}
		List<LinkedHashMap<String, Object>> dataList = (List<LinkedHashMap<String, Object>>) userResponse.getData();
		List<UsersRequest> listOfObjects = new ArrayList<>(dataList.size());
		for (LinkedHashMap<String, Object> data : dataList) {
			UsersRequest userRequest = MultipleJSONObjectHelper.getObjectFromMap(data, UsersRequest.class);
			if (CommonUtils.CW_SP_USER_ID.equals(userRequest.getId())) {
				continue;
			}
			listOfObjects.add(userRequest);
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(loanRequest.getToDate());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		logger.info("GetLoanDetailsForAdminPanel, from and todate for admin panel --------> " + cal.getTime());
		loanRequest.setToDate(cal.getTime());

		List<LoanApplicationMaster> loanApplicationList = loanApplicationRepository.getLoanDetailsForAdminPanelUbi(
				userId, applicationId, loanRequest.getFromDate(), loanRequest.getToDate());
		SimpleDateFormat dt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		for (LoanApplicationMaster loanApplicationMaster : loanApplicationList) {
			AdminPanelLoanDetailsResponse response = new AdminPanelLoanDetailsResponse();
			UsersRequest usersRequest = listOfObjects.stream()
					.filter(x -> x.getId().equals(loanApplicationMaster.getUserId())).findFirst().orElse(null);
			response.setEmail(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getEmail()) ? usersRequest.getEmail() : null);
			response.setMobile(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getMobile()) ? usersRequest.getMobile() : null);
			response.setCampaignCode(!CommonUtils.isObjectNullOrEmpty(usersRequest.getCampaignCode())
					? CampaignCode.getById(Integer.valueOf(usersRequest.getCampaignCode())).toString()
					: "Direct");
			response.setLastLoginDate(
					!CommonUtils.isObjectNullOrEmpty(usersRequest.getSignUpDate()) ? usersRequest.getSignUpDate()
							: null);
			response.setProductName(CommonUtils.getLoanName(loanApplicationMaster.getProductId()));
			ReportResponse reportResponse = master.stream()
					.filter(x -> x.getApplicationId().equals(loanApplicationMaster.getId())).findFirst().orElse(null);
			response.setLastApprovedDate(logDetailsRepository.getDateByADFForAdminPanel(loanApplicationMaster.getId(),
					reportResponse.getFpProductId()));
			response.setProfileCount(CommonUtils.getBowlCount(loanApplicationMaster.getDetailsFilledCount(), null));
			response.setPrimaryCount(CommonUtils.getBowlCount(loanApplicationMaster.getPrimaryFilledCount(), null));
			response.setFinalCount(CommonUtils.getBowlCount(loanApplicationMaster.getFinalFilledCount(), null));
			Double absoluteAmount = CommonDocumentUtils.convertAmountInAbsolute(
					loanApplicationMaster.getDenominationId(), loanApplicationMaster.getAmount());
			response.setAbsoluteDisplayAmount(absoluteAmount);
			response.setCreateDate(loanApplicationMaster.getCreatedDate());
			// pincode
			if (loanApplicationMaster.getProductId() == 1 || loanApplicationMaster.getProductId() == 2
					|| loanApplicationMaster.getProductId() == 15) {//
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (corporateApplicantDetail != null) {
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredPincode())) {
						response.setPincode(corporateApplicantDetail.getRegisteredPincode().toString());
					}
				}
			} else if (loanApplicationMaster.getProductId() == 3 || loanApplicationMaster.getProductId() == 12
					|| loanApplicationMaster.getProductId() == 7 || loanApplicationMaster.getProductId() == 13
					|| loanApplicationMaster.getProductId() == 14) {
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if (retailApplicantDetail != null) {
					String applicantName = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName())) {
						applicantName += retailApplicantDetail.getFirstName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMiddleName())) {
						applicantName += " " + retailApplicantDetail.getMiddleName();
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName())) {
						applicantName += " " + retailApplicantDetail.getLastName();
					}
					response.setName(applicantName);
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentPincode())) {
						response.setPincode(retailApplicantDetail.getPermanentPincode().toString());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getBirthDate())) {
						response.setAge(CommonUtils.calculateAge(retailApplicantDetail.getBirthDate()));
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getMonthlyIncome())) {
						response.setApplicantMonthlyIncome(retailApplicantDetail.getMonthlyIncome());
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOccupationId())) {
						response.setIncomeType(
								OccupationNature.getById(retailApplicantDetail.getOccupationId()).toString());
					}
				}
				List<CoApplicantDetail> coApplicantDetails = coApplicantDetailRepository
						.getList(loanApplicationMaster.getId(), loanApplicationMaster.getUserId());
				if (coApplicantDetails != null && !coApplicantDetails.isEmpty()) {
					String coApplicantName = "";
					if (coApplicantDetails.size() > 0) {
						CoApplicantDetail coApplicantDetail = coApplicantDetails.get(0);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getFirstName())) {
							coApplicantName += coApplicantDetail.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getLastName())) {
							coApplicantName += " " + coApplicantDetail.getLastName();
						}
						response.setCoApplicant1Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getBirthDate())) {
							response.setCoApplicant1Age(CommonUtils.calculateAge(coApplicantDetail.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail.getMonthlyIncome())) {
							response.setCoApplicant1MonthlyIncome(coApplicantDetail.getMonthlyIncome());
						}
					}
					if (coApplicantDetails.size() > 2) {
						CoApplicantDetail coApplicantDetail1 = coApplicantDetails.get(1);
						coApplicantName = "";
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getFirstName())) {
							coApplicantName += coApplicantDetail1.getFirstName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMiddleName())) {
							coApplicantName += " " + coApplicantDetail1.getMiddleName();
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getLastName())) {
							coApplicantName += " " + coApplicantDetail1.getLastName();
						}
						response.setCoApplicant2Name(coApplicantName);
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getBirthDate())) {
							response.setCoApplicant2Age(CommonUtils.calculateAge(coApplicantDetail1.getBirthDate()));
						}
						if (!CommonUtils.isObjectNullOrEmpty(coApplicantDetail1.getMonthlyIncome())) {
							response.setCoApplicant2MonthlyIncome(coApplicantDetail1.getMonthlyIncome());
						}
					}
				}
			}
			responseList.add(response);

		}
		System.out.println(responseList);
		return responseList;
	}

	@Override
	public List<ChatDetails> getChatListByApplicationId(Long applicationId) {
		// TODO Auto-generated method stub
		ProposalMappingRequest mappingRequest = new ProposalMappingRequest();
		mappingRequest.setFpProductId(applicationId);
		try {
			List<LinkedHashMap<String, Object>> mappingRequestList = (List<LinkedHashMap<String, Object>>) proposalDetailsClient
					.getFundProviderChatList(mappingRequest).getDataList();
			if (!CommonUtils.isListNullOrEmpty(mappingRequestList)) {
				List<ChatDetails> chatDetailList = new ArrayList<ChatDetails>(mappingRequestList.size());
				for (LinkedHashMap<String, Object> linkedHashMap : mappingRequestList) {
					try {
						ChatDetails chatDetails = new ChatDetails();
						ProposalMappingRequest proposalMappingRequest = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) linkedHashMap, ProposalMappingRequest.class);
						Object[] object = getApplicationDetailsById(proposalMappingRequest.getApplicationId());
						DashboardProfileResponse dashboardProfileResponse = dashboardService.getBasicProfileInfo(
								proposalMappingRequest.getApplicationId(), (Long) object[0], false);
						chatDetails.setProposalId(proposalMappingRequest.getId());
						chatDetails.setAppAndFpMappingId(proposalMappingRequest.getApplicationId());
						chatDetails
								.setIsAppFpProdActive(isApplicationIdActive(proposalMappingRequest.getApplicationId()));
						chatDetails.setName(dashboardProfileResponse.getName());
						List<LinkedHashMap<String, Object>> detailsResponseList = (List<LinkedHashMap<String, Object>>) corporateUploadService
								.getProfilePic(proposalMappingRequest.getApplicationId(),
										getProfilePicKeyByProductId(dashboardProfileResponse.getProductId()),
										DocumentAlias.UERT_TYPE_APPLICANT)
								.getDataList();
						if (!CommonUtils.isListNullOrEmpty(detailsResponseList)) {
							StorageDetailsResponse storageDetailsResponse = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) detailsResponseList.get(0),
									StorageDetailsResponse.class);
							chatDetails.setProfile(storageDetailsResponse.getFilePath());
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

	public Long getProfilePicKeyByProductId(Integer id) {
		switch (id) {
		case 1:// WORKING CAPITAL
			return DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE;
		case 2:// Term CAPITAL
			return DocumentAlias.TERM_LOAN_PROFIEL_PICTURE;
		case 3:// HOME LOAN
			return DocumentAlias.HOME_LOAN_PROFIEL_PICTURE;
		case 7:// PERSONAL LOAN
			return DocumentAlias.PERSONAL_LOAN_PROFIEL_PICTURE;
		case 12:// CAR_LOAN
			return DocumentAlias.CAR_LOAN_PROFIEL_PICTURE;
		case 13:// LOAN_AGAINST_PROPERTY
			return DocumentAlias.LAP_LOAN_PROFIEL_PICTURE;
		case 14:// LAS_LOAN_PROFIEL_PICTURE
			return DocumentAlias.LAS_LOAN_PROFIEL_PICTURE;
		case 15:// UNSECURED_LOAN_PROFIEL_PICTURE
			return DocumentAlias.UNSECURED_LOAN_PROFIEL_PICTURE;

		default:
			return null;
		}
	}

	@Override
	public List<FpProfileBasicDetailRequest> getFpNegativeList(Long applicationId) {
		// TODO Auto-generated method stub
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				List<Long> fpUserIdList = productMasterRepository
						.getUserIdListByProductId(applicationMaster.getProductId());
				if (!CommonUtils.isListNullOrEmpty(fpUserIdList)) {
					CommonResponse response = new CommonResponse();
					// get fp name from user client

					UserResponse userResponse = userClient.getFPNameListByUserId(fpUserIdList);
					if (userResponse != null && userResponse.getData() != null) {
						List<FpProfileBasicDetailRequest> basicDetailRequests = (List<FpProfileBasicDetailRequest>) userResponse
								.getData();
						return basicDetailRequests;
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveSuggestionList(ProposalList proposalList) {
		// TODO Auto-generated method stub
		try {

			// change proposal status
			if (!CommonUtils.isListNullOrEmpty(proposalList.getSuggetionIds())) {
				proposalDetailsClient.saveSuggestionList(proposalList.getSuggetionIds());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public EkycResponse getDetailsForEkycAuthentication(EkycRequest ekycRequest) {
		EkycResponse ekycResponse = new EkycResponse();
		LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(ekycRequest.getApplicationId());

		if (CommonUtils.getUserMainType(loanApplicationMaster.getProductId()) == CommonUtils.UserMainType.CORPORATE) {
			if (ekycRequest.getApplicantType() == CommonUtils.CORPORATE_USER) {
				CorporateApplicantDetail corp = corporateApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), ekycRequest.getApplicationId());
				ekycResponse.setOrganizationName(corp.getOrganisationName());
				ekycResponse.setPanNo(corp.getPanNo());
				return ekycResponse;
			}
			if (ekycRequest.getApplicantType() == CommonUtils.CORPORATE_COAPPLICANT) {
				CorporateCoApplicantDetail corpCoapp = corporateCoApplicantRepository.get(
						ekycRequest.getApplicationId(), loanApplicationMaster.getUserId(),
						ekycRequest.getApplicantsId());
				ekycResponse.setOrganizationName(corpCoapp.getOrganisationName());
				ekycResponse.setPanNo(corpCoapp.getPanNo());
				return ekycResponse;
			}

		} else {
			if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_APPLICANT) {
				RetailApplicantDetail retail = retailApplicantDetailRepository
						.getByApplicationAndUserId(loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = retail.getFirstName() + " " + retail.getLastName();
				Date date = retail.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(retail.getPan());
				ekycResponse.setAadharNo(retail.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(retail.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;
			} else if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_COAPPLICANT) {
				CoApplicantDetail coApp = coApplicantDetailRepository.get(ekycRequest.getApplicationId(),
						loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = coApp.getFirstName() + " " + coApp.getLastName();
				Date date = coApp.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(coApp.getPan());
				ekycResponse.setAadharNo(coApp.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(coApp.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;

			} else if (ekycRequest.getApplicantType() == CommonUtils.RETAIL_GUARANTOR) {
				GuarantorDetails gua = guarantorDetailsRepository.get(ekycRequest.getApplicationId(),
						loanApplicationMaster.getUserId(), ekycRequest.getApplicantsId());
				String fullName = gua.getFirstName() + " " + gua.getLastName();
				Date date = gua.getBirthDate();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = sdf.format(date);
				ekycResponse.setFullName(fullName);
				ekycResponse.setPanNo(gua.getPan());
				ekycResponse.setAadharNo(gua.getAadharNumber());
				ekycResponse.setNameAsPerAadhar(gua.getNameAsPerAadharCard());
				ekycResponse.setDob(strDate);
				return ekycResponse;
			}
		}
		return ekycResponse;

	}

	public String getMcaCompanyId(Long applicationId, Long userId) {
		try {
			return loanApplicationRepository.getMCACompanyIdByIdAndUserId(applicationId, userId).getMcaCompanyId();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updateLoanApplication(LoanApplicationRequest loanRequest) {

		LoanApplicationMaster master = loanApplicationRepository.getByIdAndUserId(loanRequest.getId(),
				loanRequest.getUserId());
		if (!CommonUtils.isObjectNullOrEmpty(master)) {
			logger.info("In LOANAPPLICATIONMASTER");
			master.setMcaCompanyId(loanRequest.getMcaCompanyId());
			master.setIsMca(loanRequest.getIsMca());
			loanApplicationRepository.save(master);
		} else {
			logger.error("NUll LOANAPPLICATIONMASTER");
		}
	}

	@Override
	public Boolean isMca(Long applicationId, Long userId) {
		try {
			return loanApplicationRepository.getMCACompanyIdByIdAndUserId(applicationId, userId).getIsMca();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Long getTotalUserApplication(Long userId) {
		logger.info("Enter in get Total User Application");
		Long totalApp = loanApplicationRepository.getTotalUserApplication(userId);
		logger.info("Exit in get Total User Application --->" + totalApp);
		return totalApp;
	}

	@Override
	public Long getUserIdByApplicationId(Long applicationId) {
		return loanApplicationRepository.getUserIdByApplicationId(applicationId);
	}

	@Override
	public boolean isCampaignCodeExist(Long userId, Long clientId, String code) throws Exception {
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			Long long1 = loanApplicationRepository.getApplicantCountByCode(finalUserId, code);
			return long1 > 0;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Checking Code is Exists or not");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public String getCampaignCodeByApplicationId(Long applicationId) throws Exception {
		try {
			return loanApplicationRepository.getCampaignCodeByApplicationId(applicationId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while getting Code by Application Id");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	private LoanApplicationMaster getLoanByType(LoanType type) {
		LoanApplicationMaster applicationMaster = null;
		switch (type) {
		case WORKING_CAPITAL:
			applicationMaster = new PrimaryWorkingCapitalLoanDetail();
			break;
		case TERM_LOAN:
			applicationMaster = new PrimaryTermLoanDetail();
			break;
		case LAS_LOAN:
			applicationMaster = new PrimaryLasLoanDetail();
			break;
		case LAP_LOAN:
			applicationMaster = new PrimaryLapLoanDetail();
			break;
		case PERSONAL_LOAN:
			applicationMaster = new PrimaryPersonalLoanDetail();
			break;
		case HOME_LOAN:
			applicationMaster = new PrimaryHomeLoanDetail();
			break;
		case CAR_LOAN:
			applicationMaster = new PrimaryCarLoanDetail();
			break;
		case UNSECURED_LOAN:
			applicationMaster = new PrimaryUnsecuredLoanDetail();
			break;

		}
		return applicationMaster;
	}

	@Override
	public Boolean isTermLoanLessThanLimit(Long applicationId) {
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return null;
		} else {
			return CommonUtils.isTermLoanLessThanLimit(applicationMaster.getDenominationId(),
					applicationMaster.getAmount());
		}

	}

	@Override
	public Integer setEligibleLoanAmount(LoanApplicationRequest applicationRequest) throws Exception {
		logger.info("Entry in setEligibleLoanAmount()");
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(applicationRequest.getClientId())
					? applicationRequest.getUserId()
					: applicationRequest.getClientId();
			int i = loanApplicationRepository.setEligibleAmount(applicationRequest.getId(), finalUserId,
					applicationRequest.getAmount());
			logger.info("No Of updated row in Eligible Amount===>" + i);
			logger.info("Exit from setEligibleLoanAmount()");
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while updating Eligibility Amount");
			logger.info("Exit from setEligibleLoanAmount()");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public void updateFlow(Long applicationId, Long clientId, Long userId) throws Exception {
		logger.info("Entry in updateFlow()");
		try {
			Long finalUserId = CommonUtils.isObjectNullOrEmpty(clientId) ? userId : clientId;
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId,
					finalUserId);
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
				logger.info("applicationMaster found null in updateFlow");
				logger.info("Exit from updateFlow()");
				return;
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationMaster.getCampaignCode())) {
				logger.info("Campaign Code is already null so no need to re update the row.");
			} else {
				applicationMaster.setCampaignCode(null);
				applicationMaster.setModifiedBy(userId);
				applicationMaster.setModifiedDate(new Date());
				loanApplicationRepository.save(applicationMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Coverting UBI flow to Normal");
			logger.info("Exit from updateFlow()");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Long getIrrByApplicationId(Long id) throws Exception {
		// TODO Auto-generated method stub
		try {

			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(id);
			if (corporateApplicantDetail == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id);
			}

			try {
				if (corporateApplicantDetail.getKeyVerticalSector() != null
						&& corporateApplicantDetail.getKeyVerticalSubsector() != null) {
					IrrBySectorAndSubSector irr = new IrrBySectorAndSubSector();
					irr.setSectorId(corporateApplicantDetail.getKeyVerticalSector());
					irr.setSubSectorId(corporateApplicantDetail.getKeyVerticalSubsector());

					// IrrBySectorAndSubSector res
					// =(IrrBySectorAndSubSector)oneFormClient.getIrrBySectorAndSubSector(irr).getData();
					IrrBySectorAndSubSector res = (IrrBySectorAndSubSector) MultipleJSONObjectHelper.getObjectFromMap(
							(Map<String, Object>) oneFormClient.getIrrBySectorAndSubSector(irr).getData(),
							IrrBySectorAndSubSector.class);
					return res.getIrr();
				}
			} catch (Exception e) {
				logger.error("Error while getting Status From Proposal Client,getKeyVerticalSector can not be null");
				e.printStackTrace();
				return null;
			}
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return null;
	}

	@Override
	public Object updateLoanApplicationMaster(PaymentRequest paymentRequest, Long userId, Long clientId)
			throws Exception {
		logger.info("Start updateLoanApplicationMaster()");
		try {
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
					.findOne(paymentRequest.getApplicationId());
			loanApplicationMaster.setTypeOfPayment(paymentRequest.getTypeOfPayment());
			loanApplicationMaster.setPaymentAmount(paymentRequest.getPaymentAmount());
			loanApplicationMaster.setAppointmentDate(paymentRequest.getAppointmentDate());
			loanApplicationMaster.setAppointmentTime(paymentRequest.getAppointmentTime());
			loanApplicationMaster.setIsAcceptConsent(paymentRequest.getIsAcceptConsent());
			loanApplicationRepository.save(loanApplicationMaster);
			CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
					.findOneByApplicationIdId(paymentRequest.getApplicationId());

			if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail)) {
				corporateApplicantDetail = new CorporateApplicantDetail();
				corporateApplicantDetail.setApplicationId(loanApplicationMaster);
				corporateApplicantDetail.setCreatedBy(userId);
				corporateApplicantDetail.setCreatedDate(new Date());
				corporateApplicantDetail.setIsActive(true);
			}
			corporateApplicantDetail.setOrganisationName(paymentRequest.getNameOfEntity());
			corporateApplicantDetail.setAdministrativePremiseNumber(paymentRequest.getAddress().getPremiseNumber());
			corporateApplicantDetail.setAdministrativeStreetName(paymentRequest.getAddress().getStreetName());
			corporateApplicantDetail.setAdministrativeLandMark(paymentRequest.getAddress().getLandMark());
			corporateApplicantDetail.setAdministrativeCountryId(paymentRequest.getAddress().getCountryId());
			corporateApplicantDetail.setAdministrativeStateId(paymentRequest.getAddress().getStateId());
			corporateApplicantDetail.setAdministrativeCityId(paymentRequest.getAddress().getCityId());
			corporateApplicantDetail.setAdministrativePincode(paymentRequest.getAddress().getPincode());
			corporateApplicantDetail.setModifiedBy(userId);
			corporateApplicantDetail.setModifiedDate(new Date());
			corporateApplicantDetailRepository.save(corporateApplicantDetail);
			if (CommonUtils.PaymentMode.ONLINE.equalsIgnoreCase(paymentRequest.getTypeOfPayment())) {
				logger.info("Start updateLoanApplicationMaster when Payment Mode in ONLINE()");
				GatewayRequest gatewayRequest = new GatewayRequest();
				try {
					gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
					gatewayRequest.setEmail(paymentRequest.getEmailAddress());
					gatewayRequest.setPhone(paymentRequest.getMobileNumber());
					gatewayRequest.setAmount(Double.valueOf(amount));
					gatewayRequest.setFirstName(paymentRequest.getNameOfEntity());
					gatewayRequest.setUserId(userId);
					gatewayRequest.setProductInfo(product);
					Object values = gatewayClient.payout(gatewayRequest);
					System.out.println("Response for gateway is:- " + values);
					logger.info("End updateLoanApplicationMaster when Payment Mode in ONLINE()");
					return values;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("Error while Saving Payment History to Patyment Module when Payment Mode is ONLINE");
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving payment information in Loan");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		return paymentRequest.getTypeOfPayment();
	}

	@Override
	public LoanApplicationRequest updateLoanApplicationMasterPaymentStatus(PaymentRequest paymentRequest, Long userId,
			Long ClientId) throws Exception {
		logger.info("start updateLoanApplicationMasterPaymentStatus()");
		try {
			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(ClientId);
			gatewayRequest.setStatus(paymentRequest.getStatus());
			gatewayRequest.setTxnId(paymentRequest.getTrxnId());
			LoanApplicationRequest loanRequest = getFromClient(paymentRequest.getApplicationId());
			Boolean updatePayment = gatewayClient.updatePayment(gatewayRequest);
			logger.info("Status===>{}", updatePayment);
			if (!CommonUtils.isObjectNullOrEmpty(updatePayment)) {
				loanRequest.setPaymentStatus(updatePayment.toString());
			}
			if (CommonUtils.isObjectNullOrEmpty(loanRequest)) {
				logger.warn("Invalid Application Id in Updating Payment Status====>{}",
						paymentRequest.getApplicationId());
				return null;
			}

			try {
				if (CommonUtils.isObjectNullOrEmpty(loanRequest.getNpUserId())) {
					return loanRequest;
				}

				UsersRequest usersRequest = new UsersRequest();
				usersRequest.setId(loanRequest.getNpUserId());
				UserResponse userResponse = userClient.getNPDetails(usersRequest);
				if (CommonUtils.isObjectListNull(userResponse, userResponse.getData())) {
					logger.warn("User Response or Data in UserResponse must not be null===>{}", userResponse);
				} else {
					NetworkPartnerDetailsRequest npRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) userResponse.getData(), NetworkPartnerDetailsRequest.class);
					loanRequest.setProviderName(npRequest.getFirstName() + " " + npRequest.getLastName());
				}

				UserResponse emailMobile = userClient.getEmailMobile(loanRequest.getNpUserId());
				if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
					logger.warn("emailMobile or Data in emailMobile must not be null===>{}", emailMobile);
					return loanRequest;
				} else {
					UsersRequest userEmailMobile = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
					loanRequest.setEmail(userEmailMobile.getEmail());
					loanRequest.setMobile(userEmailMobile.getMobile());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while Getting Client Details from Users");
			}
			logger.info("End updateLoanApplicationMasterPaymentStatus() with success");
			return loanRequest;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public GatewayRequest getPaymentStatus(PaymentRequest paymentRequest, Long userId, Long ClientId) throws Exception {
		logger.info("start getPaymentStatus()");
		try {
			GatewayRequest gatewayRequest = new GatewayRequest();
			gatewayRequest.setApplicationId(paymentRequest.getApplicationId());
			gatewayRequest.setStatus(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
			gatewayRequest.setUserId(userId);
			gatewayRequest.setClientId(ClientId);
			logger.info("End getPaymentStatus() with success");
			GatewayRequest paymentStatus = gatewayClient.getPaymentStatus(gatewayRequest);
			return paymentStatus;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("End updateLoanApplicationMasterPaymentStatus() with Exception");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Integer getIndustryIrrByApplication(Long applicationId) {
		// TODO Auto-generated method stub
		IrrRequest irrIndustryRequest = new IrrRequest();

		Long irrId = null;
		try {
			irrId = getIrrByApplicationId(applicationId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		irrIndustryRequest.setIrrIndustryId(irrId);
		try {
			irrIndustryRequest = ratingClient.getIrrIndustry(irrIndustryRequest);
		} catch (RatingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndustryResponse industryResponse = irrIndustryRequest.getIndustryResponse();

		return industryResponse.getBusinessTypeId();

	}

	@Override
	public Boolean updateDDRStatus(Long applicationId, Long userId, Long clientId, Long statusId) throws Exception {
		logger.info("start getPaymentStatus()");
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.getByIdAndUserId(applicationId, userId);
			if (applicationMaster == null) {
				throw new Exception("LoanapplicationMaster object Must not be null while Updating DDR Status==>"
						+ applicationMaster);
			}

			applicationMaster.setDdrStatusId(statusId);
			applicationMaster.setModifiedBy(userId);
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Updating DDR Status");
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public LoanApplicationRequest getFromClient(Long id) throws Exception {
		try {
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(id);
			if (applicationMaster == null) {
				throw new NullPointerException("Invalid Loan Application ID==>" + id);
			}
			LoanApplicationRequest applicationRequest = new LoanApplicationRequest();
			BeanUtils.copyProperties(applicationMaster, applicationRequest);
			applicationRequest.setProfilePrimaryLocked(applicationMaster.getIsPrimaryLocked());
			applicationRequest.setFinalLocked(applicationMaster.getIsFinalLocked());
			applicationRequest.setUserName(getFsApplicantName(id));

			UserResponse emailMobile = userClient.getEmailMobile(applicationRequest.getUserId());
			if (CommonUtils.isObjectListNull(emailMobile, emailMobile.getData())) {
				logger.warn("emailMobile or Data in emailMobile must not be null===>{}", emailMobile);
				return applicationRequest;
			} else {
				UsersRequest userEmailMobile = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) emailMobile.getData(), UsersRequest.class);
				applicationRequest.setEmail(userEmailMobile.getEmail());
				applicationRequest.setMobile(userEmailMobile.getMobile());
			}
			// SETTING ADDRESS
			String address = null;
			int mainType = CommonUtils.getUserMainType(applicationMaster.getProductId().intValue());
			if (CommonUtils.UserMainType.CORPORATE == mainType) {
				CorporateApplicantDetail applicantDetail = corporateApplicantDetailRepository
						.findOneByApplicationIdId(id);
				if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
					address = CommonDocumentUtils.getAdministrativeOfficeAddress(applicantDetail, oneFormClient);
				}
			} else {
				RetailApplicantDetail applicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(id);
				if (!CommonUtils.isObjectNullOrEmpty(applicantDetail)) {
					address = CommonDocumentUtils.getPermenantAddress(applicantDetail, oneFormClient);
				}
			}
			applicationRequest.setAddress(!CommonUtils.isObjectNullOrEmpty(address) ? address : "NA");
			return applicationRequest;
		} catch (Exception e) {
			logger.error("Error while getting Individual Loan Details For Client:-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public Boolean isApplicationEligibleForIrr(Long applicationId) throws Exception {
		// TODO Auto-generated method stub
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
		if (CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			return false;
		} else {
			if (!CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryLocked())
					&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getIsPrimaryLocked())) {
				try {

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);
					if (corporateApplicantDetail == null) {
						throw new NullPointerException("Invalid Loan Application ID==>" + applicationId);
					}

					try {
						if (corporateApplicantDetail.getKeyVerticalSector() != null
								&& corporateApplicantDetail.getKeyVerticalSubsector() != null) {
							return true;
						} else {
							return false;
						}
					} catch (Exception e) {
						logger.error("Error while getting Status From isApplicationEligibleForIrr");
						e.printStackTrace();
						return null;
					}
				} catch (Exception e) {
					logger.error("Error while getting Individual Loan Details:-");
					e.printStackTrace();
					throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
				}
			}
		}
		return false;
	}

}
