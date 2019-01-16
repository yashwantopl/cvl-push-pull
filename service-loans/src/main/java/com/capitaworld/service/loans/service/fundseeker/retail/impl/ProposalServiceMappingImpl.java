
package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.exceptions.LoansException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.capitaworld.cibil.api.model.CibilRequest;
import com.capitaworld.cibil.api.model.CibilResponse;
import com.capitaworld.cibil.api.utility.CibilUtils;
import com.capitaworld.cibil.client.CIBILClient;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.CorporateProposalDetails;
import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProposalDetailsAdminRequest;
import com.capitaworld.service.loans.model.ProposalResponse;
import com.capitaworld.service.loans.model.RetailProposalDetails;
import com.capitaworld.service.loans.model.common.ProposalSearchResponse;
import com.capitaworld.service.loans.model.common.ReportRequest;
import com.capitaworld.service.loans.repository.OfflineProcessedAppRepository;
import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.sanction.LoanDisbursementRepository;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtility;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.BusinessType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ConnectionResponse;
import com.capitaworld.service.matchengine.model.DisbursementDetailsModel;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.matchengine.utils.MatchConstant.ProposalStatus;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.FundproviderType;
import com.capitaworld.service.oneform.enums.WcRenewalType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.oneform.model.SectorIndustryModel;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.BranchBasicDetailsRequest;
import com.capitaworld.service.users.model.CheckerDetailRequest;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.LocationMasterResponse;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class ProposalServiceMappingImpl implements ProposalService {

	@Autowired
	private Environment environment;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private DMSClient dmsClient;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	private CIBILClient cibilClient;
	
	@Autowired
	private UsersClient usersClient;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private LogService logService;

	@Autowired
	private CorporateDirectorIncomeService corporateDirectorIncomeService;

	@Autowired
	private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailRepository;

	@Autowired
	private LoanDisbursementRepository loanDisbursementRepository;

	@Autowired
	private ConnectClient connectClient;
	
	@Autowired
	private LoanRepository loanRepository;

	DecimalFormat df = new DecimalFormat("#");

	private static final Logger logger = LoggerFactory.getLogger(ProposalServiceMappingImpl.class.getName());

	private static final String FOUND_BRANCH_ID_MSG = "Found Branch Id --> ";
	private static final String CURRENT_USER_ID_MSG = "Current user id --> ";
	private static final String ROLE_ID_MSG = "-- Role Id -->";
	private static final String BRANCH_ID_CAN_NOT_BE_FOUND_MSG = "Branch Id Can not be found";
	private static final String THROW_EXCEPTION_WHILE_GET_BRANCH_ID_FROM_USER_ID_MSG = "Throw Exception While Get Branch Id from UserId : ";
	private static final String YOU_DO_NOT_HAVE_RIGHTS_TO_TAKE_ACTION_FOR_THIS_PROPOSAL_MSG = "You do not have rights to take action for this proposal.";
	private static final String USER_URL = "userURL";

	private String getMainDirectorName(Long appId) {
		DirectorBackgroundDetail dirBackDetails = directorBackgroundDetailsRepository
				.getMainDirectorByApplicationId(appId);
		if (!CommonUtils.isObjectNullOrEmpty(dirBackDetails)) {
			return dirBackDetails.getDirectorsName();
		}
		return "NA";

	}

	@Override
	public List fundproviderProposal(ProposalMappingRequest request) {

		List proposalDetailsList = new ArrayList();

		try {

			try {
				// set branch id to proposal request
				UsersRequest usersRequest = new UsersRequest();
				usersRequest.setId(request.getUserId());
				logger.info(CURRENT_USER_ID_MSG + request.getUserId());
				UserResponse userResponse = usersClient.getBranchDetailsBYUserId(usersRequest);
				BranchBasicDetailsRequest basicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) userResponse.getData(), BranchBasicDetailsRequest.class);
				if (!CommonUtils.isObjectNullOrEmpty(basicDetailsRequest)) {
					request.setUserRoleId(basicDetailsRequest.getRoleId());
					logger.info(FOUND_BRANCH_ID_MSG + basicDetailsRequest.getId() + ROLE_ID_MSG + basicDetailsRequest.getRoleId());
					if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.BO
							|| basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.FP_CHECKER) {
						logger.info("Current user is Branch officer or FP_CHECKER");
						request.setBranchId(basicDetailsRequest.getId());
					}
					else if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.SMECC) {
						logger.info("Current user is Branch officer or SMECC");
						request.setBranchIds(userResponse.getBranchList());
					}
				} else {
					logger.info(BRANCH_ID_CAN_NOT_BE_FOUND_MSG);
				}
			} catch (Exception e) {
				logger.error(THROW_EXCEPTION_WHILE_GET_BRANCH_ID_FROM_USER_ID_MSG,e);
			}

			// END set branch id to proposal request
			// calling MATCHENGINE for getting proposal list

			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundProvider(request);

			MatchEngineClient matchEngineClient = new MatchEngineClient(environment.getRequiredProperty("matchesURL"));

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				Long applicationId = proposalrequest.getApplicationId();
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
				Integer bId = loanApplicationMaster.getBusinessTypeId();

				// getting the value of proposal's branch address city state
				// based on proposal applicationID
				// step 1 get proposal details by applicationID and user details
				// step2 get branch details by branch id available in
				// proposalDetails
				// step3 get branch state by state id available in location
				// Master
				// step4 get branch city by city id available in location Master

				UsersRequest usersRequestData = new UsersRequest();
				usersRequestData.setId(request.getUserId());
				BranchBasicDetailsRequest basicDetailsRequest = null;

				// step 1
				logger.info("application Id:" + proposalrequest.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(proposalrequest.getApplicationId())) {
					Object[] loanDeatils = loanApplicationService
							.getApplicationDetailsById(proposalrequest.getApplicationId());
					logger.info("user id based on application Id:" + Arrays.toString(loanDeatils));
					long userId = loanDeatils[0] != null ? (long) loanDeatils[0] : 0;

					try {
						// step 2 get branch details by branch id available in
						// proposalDetails BRANCH-MASTER
						if (proposalrequest.getBranchId() != null) {
							// getlocation id available in branch then find city
							// state location name based on location id
							UserResponse userResponse = usersClient.getBranchDetailById(proposalrequest.getBranchId());
							basicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
									(LinkedHashMap<String, Object>) userResponse.getData(),
									BranchBasicDetailsRequest.class);
							logger.info(
									"--------------------------------------------------------------------------------");
							logger.info("Get BranchDetails By ID:" + userResponse.getData());
							logger.info("branch id By proposal:" + basicDetailsRequest.getBranchId());
							if (basicDetailsRequest.getLocationId() != null) {
								logger.info("location id by branchId:" + basicDetailsRequest.getLocationId());
								try {
									LocationMasterResponse locationDetails = usersClient
											.getLocationDetailByLocationId(basicDetailsRequest.getLocationId());
									logger.info("locationName:====>" + locationDetails.getLocationName());
									logger.info("cityName:====>" + locationDetails.getCity().getName());
									basicDetailsRequest.setLocationMasterResponse(locationDetails);
									logger.info("stateName:====>" + locationDetails.getState().getName());
								} catch (Exception e) {
									logger.info("Exception in getting location by id:" + e);
								}
							}
						}
					} catch (Exception e) {
						logger.info("Exception in getting value from location based on branch id:" + e);
					}
				}
				if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster)) {
					logger.info("loanApplicationMaster null ot empty !!");
					continue;
				}

				if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsActive())
						|| !loanApplicationMaster.getIsActive()) {
					logger.info("Application Id is InActive while get fundprovider proposals=====>" + applicationId);
					continue;
				}
				if (CommonUtils.UserMainType.CORPORATE == CommonUtils
						.getUserMainType(loanApplicationMaster.getProductId())) {

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);

					if (corporateApplicantDetail == null)
						continue;

					// for get address city state country
					String address = "";
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
						address += CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
								oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
						address += CommonDocumentUtils.getState(
								corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
						address += CommonDocumentUtils.getCountry(
								corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
					} else {
						address += "NA";
					}

					CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();
					corporateProposalDetails.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
					corporateProposalDetails.setAddress(address);

					// set Branch State and city and name
					try {
						if (basicDetailsRequest.getLocationId() != null) {
							corporateProposalDetails.setBranchLocationName(basicDetailsRequest.getName());
							corporateProposalDetails
									.setBranchCity(basicDetailsRequest.getLocationMasterResponse().getCity().getName());
							corporateProposalDetails.setBranchState(
									basicDetailsRequest.getLocationMasterResponse().getState().getName());
							corporateProposalDetails.setBranchLocationName(basicDetailsRequest.getName());
						}
					} catch (Exception e) {
						logger.info("Branch Id is null:");
					}
					if (CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(bId)) {

						corporateProposalDetails.setName(getMainDirectorName(applicationId));
					} else if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(bId) || bId == null) {
						if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
							corporateProposalDetails.setName("NA");
						else
							corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
					}

					corporateProposalDetails
							.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
					corporateProposalDetails.setWcRenualNew(loanApplicationMaster.getWcRenewalStatus()!= null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New");
					corporateProposalDetails.setApplicationCode(loanApplicationMaster.getApplicationCode()!= null ?  loanApplicationMaster.getApplicationCode() : "-");

					// for get industry id
					List<Long> listIndustryIds = industrySectorRepository.getIndustryByApplicationId(applicationId);
					if (listIndustryIds.size() > 0) {
						OneFormResponse formResponse = oneFormClient.getIndustryById(listIndustryIds);
						List<Map<String, Object>> loanResponseDatalist = (List<Map<String, Object>>) formResponse
								.getListData();
						String industry = "";
						if (loanResponseDatalist.size() > 0) {
							for (int k = 0; k < loanResponseDatalist.size(); k++) {
								MasterResponse masterResponse;
								masterResponse = MultipleJSONObjectHelper.getObjectFromMap(loanResponseDatalist.get(k),
										MasterResponse.class);
								industry += masterResponse.getValue() + " ,";
							}
							corporateProposalDetails.setIndustry(industry);
						} else {
							corporateProposalDetails.setIndustry("NA");
						}
					} else {
						corporateProposalDetails.setIndustry("NA");
					}

					List<Long> keyVerticalFundingId = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
						keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
					if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								corporateProposalDetails.setKeyVertical(masterResponse.getValue());
							} else {
								corporateProposalDetails.setKeyVertical("NA");
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					// key vertical sector
					List<Long> keyVerticalSectorId = new ArrayList<>();
					// getting sector id from mapping
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
						keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());
					try {
						OneFormResponse formResponse = oneFormClient
								.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
						SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper
								.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);

						// get key vertical sector value
						OneFormResponse oneFormResponse = oneFormClient
								.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							corporateProposalDetails.setSector(masterResponse.getValue());
						} else {
							corporateProposalDetails.setSector("NA");
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					// key vertical Subsector
					try {
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
							OneFormResponse oneFormResponse = oneFormClient
									.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
							corporateProposalDetails.setSubSector((String) oneFormResponse.getData());
						}
					} catch (Exception e) {
						logger.error("error while getting key vertical sub-sector : ",e);
					}

					String amount = "";
					if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
						amount += "NA";
					else
						amount += df.format(loanApplicationMaster.getAmount());

					if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
						amount += " NA";
					else
						amount += " " + Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();

					corporateProposalDetails.setAmount(amount);

					// calling DMS for getting fs corporate profile image path

					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setProductDocumentMappingId(
							CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));

					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
					String imagePath = null;
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath = null;
						}
					}
					corporateProposalDetails.setAssignDate(proposalrequest.getAssignDate());
					corporateProposalDetails.setImagePath(imagePath);
					corporateProposalDetails.setLastStatusActionDate(logService.getDateByLogType(
							proposalrequest.getApplicationId(), proposalrequest.getDateTypeMasterId()));
					corporateProposalDetails.setApplicationId(applicationId);
					corporateProposalDetails.setProposalMappingId(proposalrequest.getId());
					corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
					corporateProposalDetails.setModifiedDate(loanApplicationMaster.getModifiedDate());
					
					corporateProposalDetails.setProposalStatus(proposalrequest.getProposalStatusId());
					if(proposalrequest.getProposalStatusId() == ProposalStatus.HOLD || proposalrequest.getProposalStatusId() == ProposalStatus.DECLINE) {
						corporateProposalDetails.setLastStatusActionDate(proposalrequest.getModifiedDate());
					}
					
					if (!CommonUtils.isObjectNullOrEmpty(proposalrequest.getModifiedBy())) {
						UsersRequest usersRequest = getUserNameAndEmail(proposalrequest.getModifiedBy());
						if (!CommonUtils.isObjectNullOrEmpty(usersRequest)) {
							corporateProposalDetails.setModifiedBy(usersRequest.getName());
						}
					}

					/*
					 * if(!CommonUtils.isObjectNullOrEmpty(proposalrequest.
					 * getAssignBy())) { UsersRequest usersRequest =
					 * getUserNameAndEmail(proposalrequest.getAssignBy());
					 * if(!CommonUtils.isObjectNullOrEmpty(usersRequest)) {
					 * corporateProposalDetails.setAssignBy(usersRequest.getName
					 * ()); } }
					 */

					// city for fp
					UserResponse usrResponse = usersClient.getFPDetails(usersRequestData);
					if (!CommonUtils.isObjectNullOrEmpty(usrResponse)) {
						try {
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
									.getObjectFromMap((LinkedHashMap<String, Object>) usrResponse.getData(),
											FundProviderDetailsRequest.class);
							if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest)) {
								if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getCityId())) {
										corporateProposalDetails.setCity(CommonDocumentUtils.getCity(
												fundProviderDetailsRequest.getCityId().longValue(), oneFormClient));
								}
								if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getPincode())) {
									corporateProposalDetails.setPincode(fundProviderDetailsRequest.getPincode());
								}
							}

						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}

					}

					if (!CommonUtils.isObjectNullOrEmpty(proposalrequest.getAssignBranchTo())) {
						try {
							UserResponse userResponse = usersClient
									.getBranchNameById(proposalrequest.getAssignBranchTo());
							if (!CommonUtils.isObjectNullOrEmpty(userResponse)) {
								corporateProposalDetails.setAssignbranch((String) userResponse.getData());
							}
						} catch (Exception e) {
							logger.error("Throw Exception while get branch name by branch id--------->" + proposalrequest.getAssignBranchTo() + " :: ",e);
						}
						corporateProposalDetails.setIsAssignedToBranch(true);
					} else {
						corporateProposalDetails.setIsAssignedToBranch(false);
					}
					proposalDetailsList.add(corporateProposalDetails);
				} else {
					Long fpProductId = request.getFpProductId();

					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);

					if (retailApplicantDetail == null)
						continue;

					// for get address city state country
					String address = "";
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId())) {
						address += CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(),
								oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())) {
						address += CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(),
								oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())) {
						address += CommonDocumentUtils
								.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
					} else {
						address += "NA";
					}

					RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
					retailProposalDetails.setAddress(address);

					String name = "";

					if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
						name += "NA";
					else
						name += retailApplicantDetail.getFirstName();

					if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName()))
						name += " NA";
					else
						name += " " + retailApplicantDetail.getLastName();

					retailProposalDetails.setName(name);

					// set Branch State and city and name
					try {
						if (basicDetailsRequest.getLocationId() != null) {
							retailProposalDetails.setBranchLocationName(basicDetailsRequest.getName());
							retailProposalDetails
									.setBranchCity(basicDetailsRequest.getLocationMasterResponse().getCity().getName());
							retailProposalDetails.setBranchState(
									basicDetailsRequest.getLocationMasterResponse().getState().getName());
							retailProposalDetails.setBranchLocationName(basicDetailsRequest.getName());
						}
					} catch (Exception e) {
						logger.error("location id is null for this application:" + applicationId + "::" + e);
					}

					// calling DMS for getting fs retail profile image path

					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setProductDocumentMappingId(
							CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
					String imagePath = null;
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath = null;
						}
					}

					retailProposalDetails.setImagePath(imagePath);
					retailProposalDetails.setApplicationId(applicationId);
					retailProposalDetails.setProposalMappingId(proposalrequest.getId());
					retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
					retailProposalDetails.setBusinessTypeId(loanApplicationMaster.getBusinessTypeId());
					retailProposalDetails.setFpProductid(fpProductId);
					
					retailProposalDetails.setProposalStatus(proposalrequest.getProposalStatusId());
					if(proposalrequest.getProposalStatusId() == ProposalStatus.HOLD || proposalrequest.getProposalStatusId() == ProposalStatus.DECLINE) {
						retailProposalDetails.setLastStatusActionDate(proposalrequest.getModifiedDate());
					}
					// get retail loan amount

					String loanAmount = "";
					if (!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount())) {
						loanAmount += df.format(loanApplicationMaster.getAmount());
					} else {
						loanAmount += "NA";
					}

					if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getCurrencyId())) {
						loanAmount += " " + Currency.getById(retailApplicantDetail.getCurrencyId());
					} else {
						loanAmount += " NA";
					}

					retailProposalDetails.setAmount(loanAmount);

					try {
						MatchRequest matchRequest = new MatchRequest();
						matchRequest.setApplicationId(applicationId);
						matchRequest.setProductId(fpProductId);
						MatchDisplayResponse matchResponse = matchEngineClient.displayMatchesOfRetail(matchRequest);
						retailProposalDetails.setListMatches(matchResponse.getMatchDisplayObjectList());
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					try {
						CibilRequest cibilRequest = new CibilRequest();
						cibilRequest.setApplicationId(applicationId);
						cibilRequest.setUserId(request.getUserId());
						cibilRequest.setPan(retailApplicantDetail.getPan());
						CibilResponse cibilResponse = cibilClient.getCibilScore(cibilRequest);
						if (!CibilUtils.isObjectNullOrEmpty(cibilResponse)) {
							String response = (String) cibilResponse.getData();
							if (!CibilUtils.isObjectNullOrEmpty(response)) {
								JSONObject jsonObject = new JSONObject(response);
								JSONObject asset = jsonObject.getJSONObject("Asset");
								if (!CibilUtils.isObjectNullOrEmpty(asset)) {
									JSONObject trueLinkCreditReport = asset.getJSONObject("ns4:TrueLinkCreditReport");
									if (!CibilUtils.isObjectNullOrEmpty(trueLinkCreditReport)) {
										JSONObject creditScore = trueLinkCreditReport.getJSONObject("ns4:Borrower")
												.getJSONObject("ns4:CreditScore");
										if (!CibilUtils.isObjectNullOrEmpty(creditScore)) {
											String score = creditScore.get("riskScore").toString();
											logger.info("Pan===>" + cibilRequest.getPan() + " ==> Score===>" + score);
											retailProposalDetails.setCibilSCore(score);
										} else {
											logger.info("no data Found from key ns4:CreditScore");
										}

									} else {
										logger.info("no data Found from key ns4:TrueLinkCreditReport");
									}

								} else {
									logger.info("no data Found from key ns4:Asset");
								}
							} else {
								logger.info("Cibil Actual data Response Found NULL from Loans for PAN ==>"
										+ cibilRequest.getPan());
							}
						} else {
							logger.info("CibilResponse Found NULL from Loans for PAN ==>" + cibilRequest.getPan());
						}
					} catch (Exception e) {
						logger.error("Error while getting CIbilScore of User : ",e);
					}
					proposalDetailsList.add(retailProposalDetails);
				}

			}

		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return proposalDetailsList;
	}

	private UsersRequest getUserNameAndEmail(Long userId) {
		try {
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
						.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
				if (!CommonUtils.isObjectNullOrEmpty(request)) {
					return request;
				}
			}
		} catch (Exception e) {
			logger.error("Throw exception while get name and email by userid : ",e);
		}
		return null;
	}

	@Override
	public List<FundProviderProposalDetails> fundseekerProposal(ProposalMappingRequest request, Long userId) {

		List<FundProviderProposalDetails> proposalDetailsList = new ArrayList<FundProviderProposalDetails>();

		try {
			// calling MATCHENGINE for getting proposal list

			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundSeeker(request);

			List<Object[]> disbursmentData = loanDisbursementRepository.getDisbursmentData(request.getApplicationId());

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {
				UsersClient usersClient = new UsersClient(environment.getRequiredProperty(USER_URL));
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				ProductMaster master = productMasterRepository.findOne(proposalrequest.getFpProductId());
				if (!master.getIsActive()) {
					logger.info("Product Id is InActive while get fundSeeker proposals=====>"
							+ proposalrequest.getFpProductId());
					continue;
				}
				UsersRequest userRequest = new UsersRequest();
				userRequest.setId(master.getUserId());

				// calling USER for getting fp details
				UserResponse userResponse = usersClient.getFPDetails(userRequest);

				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
				FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();

				Long productId = proposalrequest.getFpProductId();

				fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
				fundProviderProposalDetails.setWhoAreYou(
						FundproviderType.getById(fundProviderDetailsRequest.getBusinessTypeMaster()).getValue());
				fundProviderProposalDetails.setFpType("DEBT");

				fundProviderProposalDetails
						.setFpProductName(CommonUtils.isObjectNullOrEmpty(master.getName()) ? " " : master.getName());

				// calling DMS for getting fp profile image path

				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setUserId(master.getUserId());
				documentRequest.setUserType("user");
				documentRequest.setUserDocumentMappingId(1L);

				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = "";
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse response = null;

						response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
						if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
							imagePath = response.getFilePath();
						else
							imagePath = "";
					}
				}

				fundProviderProposalDetails.setImagePath(imagePath);
				fundProviderProposalDetails.setProductId(productId);
				fundProviderProposalDetails.setProposalMappingId(proposalrequest.getId());
				fundProviderProposalDetails.setElAmount(proposalrequest.getElAmount());
				fundProviderProposalDetails.setElRoi(proposalrequest.getElRoi());
				fundProviderProposalDetails.setElTenure(proposalrequest.getElTenure());
				// add disbursed amount logic

				fundProviderProposalDetails
						.setPartiallyDisburseAmt(disbursmentData != null ? (Double) (disbursmentData.get(0)[0]) : null);
				fundProviderProposalDetails.setLastDisbursmentDate(
						disbursmentData != null ? String.valueOf(disbursmentData.get(0)[1]) : null);

				proposalDetailsList.add(fundProviderProposalDetails);
			}
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		return proposalDetailsList;
	}

	@Override
	public ProposalCountResponse fundProviderProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();

		try {
			response = proposalDetailsClient.proposalCountOfFundProvider(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public ProposalCountResponse fundSeekerProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();
		try {
			response = proposalDetailsClient.proposalCountOfFundSeeker(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public ProposalMappingResponse get(ProposalMappingRequest request) {
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.getProposal(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public ProposalMappingResponse changeStatus(ProposalMappingRequest request) {

		ProposalMappingResponse response = new ProposalMappingResponse();

		ProposalDetailsClient client = new ProposalDetailsClient(
				environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.changeStatus(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public ProposalMappingResponse listOfFundSeekerProposal(ProposalMappingRequest request) {

		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.listOfFundSeekerProposal(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public ProposalResponse getConectionList(ProposalMappingRequest proposalMappingRequest) {

		ProposalResponse proposalResponse = new ProposalResponse();

		List proposalByMatches = new ArrayList();
		List proposalWithoutMatches = new ArrayList();
		// calling MATCHENGINE for getting connection list

		try {

			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.connections(proposalMappingRequest);
			ConnectionResponse connectionResponse = (ConnectionResponse) MultipleJSONObjectHelper.getObjectFromMap(
					(Map<String, Object>) proposalDetailsResponse.getData(), ConnectionResponse.class);

			if (!(CommonUtils.UserType.FUND_SEEKER == proposalMappingRequest.getUserType())) {
				// for get suggetionListby matches (10)
				for (int i = 0; i < connectionResponse.getSuggetionByMatchesList().size(); i++) {
					try {
						BigInteger applicationId = (BigInteger) connectionResponse.getSuggetionByMatchesList().get(i);
						LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
								.findOne(applicationId.longValue());
						Integer bId = loanApplicationMaster.getBusinessTypeId();
						if (CommonUtils.UserMainType.CORPORATE == CommonUtils
								.getUserMainType(loanApplicationMaster.getProductId())) {

							CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
									.findOneByApplicationIdId(applicationId.longValue());

							if (corporateApplicantDetail == null)
								continue;

							// for get address city state country
							String address = "";
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
								address += CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
										oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
								address += CommonDocumentUtils.getState(
										corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient)
										+ ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
								address += CommonDocumentUtils.getCountry(
										corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
							} else {
								address += "NA";
							}

							CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();

							corporateProposalDetails.setAddress(address);

							if (CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(bId)) {

								corporateProposalDetails.setName(getMainDirectorName(applicationId.longValue()));

							} else if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(bId) || bId == null) {
								if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
									corporateProposalDetails.setName("NA");
								else
									corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
							}

							corporateProposalDetails.setFsMainType(
									CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
							corporateProposalDetails.setWcRenualNew(loanApplicationMaster.getWcRenewalStatus()!= null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New");
							corporateProposalDetails.setApplicationCode(loanApplicationMaster.getApplicationCode()!= null ?  loanApplicationMaster.getApplicationCode() : "-");
							
							String amount = "";
							if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
								amount += "NA";
							else
								amount += df.format(loanApplicationMaster.getAmount());

							if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
								amount += " NA";
							else
								amount += " "
										+ Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();

							corporateProposalDetails.setAmount(amount);

							// calling DMS for getting fs corporate profile
							// image
							// path

							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setProductDocumentMappingId(
									CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));

							DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath = null;
								}
							}

							corporateProposalDetails.setImagePath(imagePath);
							corporateProposalDetails.setApplicationId(applicationId.longValue());
							corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
							proposalByMatches.add(corporateProposalDetails);
						} else {
							RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
									.findOneByApplicationIdId(applicationId.longValue());

							if (retailApplicantDetail == null)
								continue;

							// for get address city state country
							String address = "";
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId())) {
								address += CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(),
										oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())) {
								address += CommonDocumentUtils.getState(
										retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())) {
								address += CommonDocumentUtils.getCountry(
										retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
							} else {
								address += "NA";
							}

							RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
							retailProposalDetails.setAddress(address);

							String name = "";

							if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
								name += "NA";
							else
								name += retailApplicantDetail.getFirstName();

							retailProposalDetails.setName(name);

							// calling DMS for getting fs retail profile image
							// path

							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setProductDocumentMappingId(
									CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
							DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath = null;
								}
							}

							retailProposalDetails.setImagePath(imagePath);
							retailProposalDetails.setApplicationId(applicationId.longValue());
							retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
							proposalByMatches.add(retailProposalDetails);
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}

				}
				// for set connection without proposal 10

				for (int i = 0; i < connectionResponse.getSuggetionList().size(); i++) {
					try {
						BigInteger applicationId = (BigInteger) connectionResponse.getSuggetionList().get(i);
						LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
								.findOne(applicationId.longValue());
						Integer bId = loanApplicationMaster.getBusinessTypeId();
						if (CommonUtils.UserMainType.CORPORATE == CommonUtils
								.getUserMainType(loanApplicationMaster.getProductId())) {

							CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
									.findOneByApplicationIdId(applicationId.longValue());

							if (corporateApplicantDetail == null)
								continue;

							// for get address city state country
							String address = "";
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
								address += CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
										oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
								address += CommonDocumentUtils.getState(
										corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient)
										+ ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
								address += CommonDocumentUtils.getCountry(
										corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
							} else {
								address += "NA";
							}

							CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();

							corporateProposalDetails.setAddress(address);

							if (CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(bId)) {

								corporateProposalDetails.setName(getMainDirectorName(applicationId.longValue()));

							} else if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(bId) || bId == null) {
								if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
									corporateProposalDetails.setName("NA");
								else
									corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
							}

							corporateProposalDetails.setFsMainType(
									CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
							corporateProposalDetails.setWcRenualNew(loanApplicationMaster.getWcRenewalStatus()!= null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New");
							corporateProposalDetails.setApplicationCode(loanApplicationMaster.getApplicationCode()!= null ?  loanApplicationMaster.getApplicationCode() : "-");
							
							String amount = "";
							if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
								amount += "NA";
							else
								amount += df.format(loanApplicationMaster.getAmount());

							if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
								amount += " NA";
							else
								amount += " "
										+ Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();

							corporateProposalDetails.setAmount(amount);

							// calling DMS for getting fs corporate profile
							// image
							// path

							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setProductDocumentMappingId(
									CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));

							DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath = null;
								}
							}

							corporateProposalDetails.setImagePath(imagePath);
							corporateProposalDetails.setApplicationId(applicationId.longValue());
							corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
							proposalWithoutMatches.add(corporateProposalDetails);
						} else {
							RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
									.findOneByApplicationIdId(applicationId.longValue());

							if (retailApplicantDetail == null)
								continue;

							// for get address city state country
							String address = "";
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId())) {
								address += CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(),
										oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId())) {
								address += CommonDocumentUtils.getState(
										retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient) + ",";
							} else {
								address += "NA ,";
							}
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId())) {
								address += CommonDocumentUtils.getCountry(
										retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
							} else {
								address += "NA";
							}

							RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
							retailProposalDetails.setAddress(address);

							String name = "";

							if (CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
								name += "NA";
							else
								name += retailApplicantDetail.getFirstName();

							retailProposalDetails.setName(name);

							// calling DMS for getting fs retail profile image
							// path

							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setProductDocumentMappingId(
									CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
							DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath = null;
								}
							}

							retailProposalDetails.setImagePath(imagePath);
							retailProposalDetails.setApplicationId(applicationId.longValue());
							retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
							proposalWithoutMatches.add(retailProposalDetails);
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}

				}

			} else {

				List<Long> userOrgSuggetionByMatchesList = new ArrayList<>();
				logger.info("Total FP found for fs connection Suggetion By Matches list ---------------------> "
						+ connectionResponse.getSuggetionByMatchesList().size());
				for (int i = 0; i < connectionResponse.getSuggetionByMatchesList().size(); i++) {
					try {
						UsersClient usersClient = new UsersClient(environment.getRequiredProperty(USER_URL));

						BigInteger fpProductId = BigInteger.class
								.cast(connectionResponse.getSuggetionByMatchesList().get(i));
						ProductMaster master = productMasterRepository.findOne(fpProductId.longValue());

						if (!CommonUtils.isObjectNullOrEmpty(master) && !CommonUtils.isObjectNullOrEmpty(master.getUserOrgId()) ) {
								if (userOrgSuggetionByMatchesList.contains(master.getUserOrgId())) {
									logger.info(
											"Found same user org id in connection suggestion by matches list ---------------"
													+ master.getId() + "--------------->" + master.getUserOrgId());
									continue;
								}
								userOrgSuggetionByMatchesList.add(master.getUserOrgId());
						}

						UsersRequest userRequest = new UsersRequest();
						userRequest.setId(master.getUserId());

						// calling USER for getting fp details
						UserResponse userResponse = usersClient.getFPDetails(userRequest);

						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(),
										FundProviderDetailsRequest.class);
						FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();

						fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
						fundProviderProposalDetails.setWhoAreYou(FundproviderType
								.getById(fundProviderDetailsRequest.getBusinessTypeMaster()).getValue());
						fundProviderProposalDetails.setFpType("DEBT");

						fundProviderProposalDetails.setFpProductName(
								CommonUtils.isObjectNullOrEmpty(master.getName()) ? " " : master.getName());

						// calling DMS for getting fp profile image path

						DocumentRequest documentRequest = new DocumentRequest();
						documentRequest.setUserId(master.getUserId());
						documentRequest.setUserType("user");
						documentRequest.setUserDocumentMappingId(1L);

						DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
						String imagePath = null;
						if (documentResponse != null && documentResponse.getStatus() == 200) {
							List<Map<String, Object>> list = documentResponse.getDataList();
							if (!CommonUtils.isListNullOrEmpty(list)) {
								StorageDetailsResponse response = null;

								response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
										StorageDetailsResponse.class);
								if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
									imagePath = response.getFilePath();
								else
									imagePath = null;
							}
						}

						fundProviderProposalDetails.setImagePath(imagePath);
						fundProviderProposalDetails.setProductId(fpProductId.longValue());
						proposalByMatches.add(fundProviderProposalDetails);
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}

				// set connection without matches
				List<Long> userOgList = new ArrayList<>();
				logger.info("Total FP found for fs connection Suggetion List ---------------------> "
						+ connectionResponse.getSuggetionList().size());
				for (int i = 0; i < connectionResponse.getSuggetionList().size(); i++) {
					try {
						UsersClient usersClient = new UsersClient(environment.getRequiredProperty(USER_URL));

						BigInteger fpProductId = BigInteger.class.cast(connectionResponse.getSuggetionList().get(i));
						ProductMaster master = productMasterRepository.findOne(fpProductId.longValue());
						if (!CommonUtils.isObjectNullOrEmpty(master) && !CommonUtils.isObjectNullOrEmpty(master.getUserOrgId()) ) {
								if (userOgList.contains(master.getUserOrgId())) {
									logger.info("Found same user org id in connection suggestion list ---------------"
											+ master.getId() + "--------------->" + master.getUserOrgId());
									continue;
								}
								userOgList.add(master.getUserOrgId());
						}
						UsersRequest userRequest = new UsersRequest();
						userRequest.setId(master.getUserId());

						// calling USER for getting fp details
						UserResponse userResponse = usersClient.getFPDetails(userRequest);

						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper
								.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(),
										FundProviderDetailsRequest.class);
						FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();

						fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
						fundProviderProposalDetails.setWhoAreYou(FundproviderType
								.getById(fundProviderDetailsRequest.getBusinessTypeMaster()).getValue());
						fundProviderProposalDetails.setFpType("DEBT");

						fundProviderProposalDetails.setFpProductName(
								CommonUtils.isObjectNullOrEmpty(master.getName()) ? " " : master.getName());

						// calling DMS for getting fp profile image path

						DocumentRequest documentRequest = new DocumentRequest();
						documentRequest.setUserId(master.getUserId());
						documentRequest.setUserType("user");
						documentRequest.setUserDocumentMappingId(1L);

						DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
						String imagePath = null;
						if (documentResponse != null && documentResponse.getStatus() == 200) {
							List<Map<String, Object>> list = documentResponse.getDataList();
							if (!CommonUtils.isListNullOrEmpty(list)) {
								StorageDetailsResponse response = null;

								response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
										StorageDetailsResponse.class);
								if (!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
									imagePath = response.getFilePath();
								else
									imagePath = null;
							}
						}

						fundProviderProposalDetails.setImagePath(imagePath);
						fundProviderProposalDetails.setProductId(fpProductId.longValue());
						proposalWithoutMatches.add(fundProviderProposalDetails);
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
				}
			}

		} catch (Exception e) {
			ProposalMappingResponse proposalMappingResponseErr = new ProposalMappingResponse(
					"Error while getting connection list", HttpStatus.INTERNAL_SERVER_ERROR.value());
			logger.error(CommonUtils.EXCEPTION,e);
			return null;
		}

		proposalResponse.setProposalByMatches(proposalByMatches);
		proposalResponse.setProposalWithoutMatches(proposalWithoutMatches);
		return proposalResponse;
	}

	@Override
	public ProposalMappingResponse sendRequest(ProposalMappingRequest request) {

		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.sendRequest(request);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return response;
	}

	@Override
	public Integer getPendingProposalCount(Long applicationId) {
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.getPendingProposalCount(applicationId);
			return (Integer) response.getData();
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}

		return null;
	}

	@Override
	public ProposalMappingResponse updateAssignDetails(ProposalMappingRequest request) throws LoansException {
		try {
			return proposalDetailsClient.updateAssignDetails(request);
		} catch (Exception e) {
			logger.error("Throw Exception while updating assign issue : ",e);
			throw new LoansException("Somethig went wrong");
		}
	}

	@Override
	public List<?> fundproviderProposalByAssignBy(ProposalMappingRequest request) {

		try {
			// set branch id to proposal request
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(request.getUserId());
			logger.info(CURRENT_USER_ID_MSG + request.getUserId());
			UserResponse userResponse = usersClient.getBranchDetailsBYUserId(usersRequest);
			BranchBasicDetailsRequest basicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), BranchBasicDetailsRequest.class);
			if (!CommonUtils.isObjectNullOrEmpty(basicDetailsRequest)) {
				logger.info(FOUND_BRANCH_ID_MSG + basicDetailsRequest.getId()
						+ ROLE_ID_MSG + basicDetailsRequest.getRoleId());
				if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.BO) {
					logger.info("Current user is Branch officer");
					request.setBranchId(basicDetailsRequest.getId());
				}
			} else {
				logger.info(BRANCH_ID_CAN_NOT_BE_FOUND_MSG);
			}
		} catch (Exception e) {
			logger.error(THROW_EXCEPTION_WHILE_GET_BRANCH_ID_FROM_USER_ID_MSG,e);
		}

		List proposalByMatches = new ArrayList();
		try {
			ProposalMappingResponse response = proposalDetailsClient.proposalListByAssignee(request);
			logger.info("Found total assigned proposal -------------------------->" + response.getDataList().size());
			// mappingRequests =response.getDataList();
			if (!CommonUtils.isListNullOrEmpty(response.getDataList())) {
				for (int i = 0; i < response.getDataList().size(); i++) {
					ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) response.getDataList().get(i),
							ProposalMappingRequest.class);

					if (CommonUtils.isObjectNullOrEmpty(proposalrequest)) {
						logger.info("proposalrequest is null or empty");
						continue;
					}

					Long applicationId = proposalrequest.getApplicationId();
					LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
					Integer bId = loanApplicationMaster.getBusinessTypeId();
					if (!loanApplicationMaster.getIsActive()) {
						logger.info(
								"Application Id is InActive while get fundprovider proposals=====>" + applicationId);
						continue;
					}

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOneByApplicationIdId(applicationId);

					if (corporateApplicantDetail == null)
						continue;

					// for get address city state country
					String address = "";
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId())) {
						address += CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(),
								oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId())) {
						address += CommonDocumentUtils.getState(
								corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient) + ",";
					} else {
						address += "NA ,";
					}
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId())) {
						address += CommonDocumentUtils.getCountry(
								corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
					} else {
						address += "NA";
					}

					CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();

					corporateProposalDetails.setAddress(address);

					if (CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(bId)) {

						corporateProposalDetails.setName(getMainDirectorName(applicationId));

					} else if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(bId) || bId == null) {
						if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
							corporateProposalDetails.setName("NA");
						else
							corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
					}

					corporateProposalDetails
							.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
					corporateProposalDetails.setWcRenualNew(loanApplicationMaster.getWcRenewalStatus()!= null ? WcRenewalType.getById(loanApplicationMaster.getWcRenewalStatus()).getValue().toString() : "New");	
					corporateProposalDetails.setApplicationCode(loanApplicationMaster.getApplicationCode()!= null ?  loanApplicationMaster.getApplicationCode() : "-");
					
					// for get industry id
					List<Long> listIndustryIds = industrySectorRepository.getIndustryByApplicationId(applicationId);
					if (listIndustryIds.size() > 0) {
						OneFormResponse formResponse = oneFormClient.getIndustryById(listIndustryIds);
						List<Map<String, Object>> loanResponseDatalist = (List<Map<String, Object>>) formResponse
								.getListData();
						String industry = "";
						if (loanResponseDatalist.size() > 0) {
							for (int k = 0; k < loanResponseDatalist.size(); k++) {
								MasterResponse masterResponse;
								masterResponse = MultipleJSONObjectHelper.getObjectFromMap(loanResponseDatalist.get(k),
										MasterResponse.class);
								industry += masterResponse.getValue() + " ,";
							}
							corporateProposalDetails.setIndustry(industry);
						} else {
							corporateProposalDetails.setIndustry("NA");
						}
					} else {
						corporateProposalDetails.setIndustry("NA");
					}

					List<Long> keyVerticalFundingId = new ArrayList<>();
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVericalFunding()))
						keyVerticalFundingId.add(corporateApplicantDetail.getKeyVericalFunding());
					if (!CommonUtils.isListNullOrEmpty(keyVerticalFundingId)) {
						try {
							OneFormResponse oneFormResponse = oneFormClient.getIndustryById(keyVerticalFundingId);
							List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
									.getListData();
							if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
								MasterResponse masterResponse = MultipleJSONObjectHelper
										.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
								corporateProposalDetails.setKeyVertical(masterResponse.getValue());
							} else {
								corporateProposalDetails.setKeyVertical("NA");
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
					}

					// key vertical sector
					List<Long> keyVerticalSectorId = new ArrayList<>();
					// getting sector id from mapping
					if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSector()))
						keyVerticalSectorId.add(corporateApplicantDetail.getKeyVerticalSector());
					try {
						OneFormResponse formResponse = oneFormClient
								.getIndustrySecByMappingId(corporateApplicantDetail.getKeyVerticalSector());
						SectorIndustryModel sectorIndustryModel = MultipleJSONObjectHelper
								.getObjectFromMap((Map) formResponse.getData(), SectorIndustryModel.class);

						// get key vertical sector value
						OneFormResponse oneFormResponse = oneFormClient
								.getSectorById(Arrays.asList(sectorIndustryModel.getSectorId()));
						List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse
								.getListData();
						if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {
							MasterResponse masterResponse = MultipleJSONObjectHelper
									.getObjectFromMap(oneResponseDataList.get(0), MasterResponse.class);
							corporateProposalDetails.setSector(masterResponse.getValue());
						} else {
							corporateProposalDetails.setSector("NA");
						}
					} catch (Exception e) {
						logger.error(CommonUtils.EXCEPTION,e);
					}
					// key vertical Subsector
					try {
						if (!CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getKeyVerticalSubsector())) {
							OneFormResponse oneFormResponse = oneFormClient
									.getSubSecNameByMappingId(corporateApplicantDetail.getKeyVerticalSubsector());
							corporateProposalDetails.setSubSector((String) oneFormResponse.getData());
						}
					} catch (Exception e) {
						logger.error("error while getting key vertical sub-sector : ",e);
					}

					String amount = "";
					if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
						amount += "NA";
					else
						amount += df.format(loanApplicationMaster.getAmount());

					if (CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
						amount += " NA";
					else
						amount += " " + Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();

					corporateProposalDetails.setAmount(amount);

					// calling DMS for getting fs corporate profile image path

					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setProductDocumentMappingId(
							CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));

					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
					String imagePath = null;
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse storageDetailsResponse = null;

							storageDetailsResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if (!CommonUtils.isObjectNullOrEmpty(storageDetailsResponse.getFilePath()))
								imagePath = storageDetailsResponse.getFilePath();
							else
								imagePath = null;
						}
					}

					corporateProposalDetails.setImagePath(imagePath);
					corporateProposalDetails.setApplicationId(applicationId);
					corporateProposalDetails.setProposalMappingId(proposalrequest.getId());
					corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
					corporateProposalDetails.setModifiedDate(loanApplicationMaster.getModifiedDate());
					corporateProposalDetails.setAssignDate(proposalrequest.getAssignDate());

					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(request.getUserId());
					UserResponse usrResponse = usersClient.getFPDetails(usersRequest);
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) usrResponse.getData(), FundProviderDetailsRequest.class);
					if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getCityId())) {
							corporateProposalDetails.setCity(CommonDocumentUtils
									.getCity(fundProviderDetailsRequest.getCityId().longValue(), oneFormClient));
					}
					if (!CommonUtils.isObjectNullOrEmpty(fundProviderDetailsRequest.getPincode())) {
						corporateProposalDetails.setPincode(fundProviderDetailsRequest.getPincode());
					}

					/*
					 * if(!CommonUtils.isObjectNullOrEmpty(proposalrequest.
					 * getAssignBy())) { UsersRequest usersRequest =
					 * getUserNameAndEmail(proposalrequest.getAssignBy());
					 * if(!CommonUtils.isObjectNullOrEmpty(usersRequest)) {
					 * corporateProposalDetails.setAssignBy(usersRequest.getName
					 * ()); } }
					 */
					if (!CommonUtils.isObjectNullOrEmpty(proposalrequest.getAssignBranchTo())) {
						try {
							UserResponse userResponse = usersClient
									.getBranchNameById(proposalrequest.getAssignBranchTo());
							if (!CommonUtils.isObjectNullOrEmpty(userResponse)) {
								corporateProposalDetails.setAssignbranch((String) userResponse.getData());
							}
						} catch (Exception e) {
							logger.error("Throw Exception while get branch name by branch id--------->" + proposalrequest.getAssignBranchTo() + " :: ",e);
						}
						corporateProposalDetails.setIsAssignedToBranch(true);
					} else {
						corporateProposalDetails.setIsAssignedToBranch(false);
					}

					proposalByMatches.add(corporateProposalDetails);
				}
			}
		} catch (Exception e) {
			logger.error("Throw Exception while Get assign by proposal : ",e);
		}
		return proposalByMatches;

	}

	@Override
	public ProposalMappingResponse saveDisbursementDetails(DisbursementDetailsModel request, Long userId) {
		try {
			// set branch id to proposal request
			logger.info("DISBURSEMENT DETAILS IS ---------------------------------------------------> " + request.toString());

			Date connectlogModifiedDate = connectClient.getInprincipleDateByAppId(request.getApplicationId());
			if (!CommonUtils.isObjectNullOrEmpty(connectlogModifiedDate) && (request.getDisbursementDate().compareTo(connectlogModifiedDate)<0 || request.getDisbursementDate().compareTo(new Date())>0)) {
				return	new ProposalMappingResponse("Please insert valid disbursement date",
							HttpStatus.INTERNAL_SERVER_ERROR.value());
			}
			ProposalMappingResponse mappingResponse = proposalDetailsClient.saveDisbursementDetails(request);
			return mappingResponse;

		} catch (Exception e) {
			logger.error("Throw Exception While saveDisbursementDetails : ",e);
			new ProposalMappingResponse("error while saving disbursement details",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return null;
	}

	@Override
	public LoansResponse checkMinMaxAmount(UsersRequest userRequest) {
		LoansResponse loansResponse = new LoansResponse();

		try {
			// "+userRequest.getApplicationId() + "userRequest.getId() :
			// "+userRequest.getId()+" getLoanAmount() :
			// "+userRequest.getLoanAmount());
			loansResponse.setFlag(true);

			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
					.findOne(userRequest.getApplicationId());

			if (loanApplicationMaster != null && userRequest != null) {
				// Check If Requested Application is assigned to Currunt Fp
				// Cheker or not
				UserResponse userResponse = null;
				userRequest.setProductIdString(CommonUtility.encode("" + loanApplicationMaster.getProductId()));
				if (loanApplicationMaster.getNpUserId() == null) {
					userResponse = usersClient.getMinMaxAmount(userRequest);
				} else if ((loanApplicationMaster.getNpUserId()).equals(userRequest.getId())) {
					userResponse = usersClient.getMinMaxAmount(userRequest);
				}

				CheckerDetailRequest checkerDetailRequest = null;
				if (userResponse != null && !CommonUtils.isObjectListNull(userResponse)
						&& !(CommonUtils.isObjectNullOrEmpty(userResponse.getData()))) {
					checkerDetailRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) userResponse.getData(), CheckerDetailRequest.class);
				}

				if (!CommonUtils.isObjectNullOrEmpty(checkerDetailRequest)) {
					// "+checkerDetailRequest.getMinAmount() + " getMaxAmount :
					// "+checkerDetailRequest.getMaxAmount());
					if (userRequest.getLoanAmount() != null && checkerDetailRequest != null && checkerDetailRequest.getMinAmount() != null
							&& checkerDetailRequest.getMaxAmount() != null
							&& !(userRequest.getLoanAmount() >= checkerDetailRequest.getMinAmount()
									&& userRequest.getLoanAmount() <= checkerDetailRequest.getMaxAmount())) {
						loansResponse.setFlag(false);
						loansResponse.setMessage(
								"You do not have rights to take action for this proposal. Kindly assign the proposal to your upper level checker.");
					}
				} else {
					// You dont have Authorised for this Action
					loansResponse.setFlag(false);
					loansResponse.setMessage(YOU_DO_NOT_HAVE_RIGHTS_TO_TAKE_ACTION_FOR_THIS_PROPOSAL_MSG);
				}

			} else {
				// You dont have Authorised for this Action
				logger.error("Not getting min max loan amount for this user");
				loansResponse.setFlag(false);
				loansResponse.setMessage(YOU_DO_NOT_HAVE_RIGHTS_TO_TAKE_ACTION_FOR_THIS_PROPOSAL_MSG);
			}
		} catch (Exception e) {

			logger.error("Error while Getting Min Max Loan Amount : ",e);
			loansResponse.setFlag(false);
			loansResponse.setMessage(YOU_DO_NOT_HAVE_RIGHTS_TO_TAKE_ACTION_FOR_THIS_PROPOSAL_MSG);
		}
		return loansResponse;
	}

	@Override
	public List<ProposalDetailsAdminRequest> getProposalsByOrgId(Long userOrgId, ProposalDetailsAdminRequest request,
			Long userId) {

		// UserResponse userData = usersClient.getUserDetailsById(userId);

		// UsersRequest data = (UsersRequest) userData.getData();

		// userData.toString());

		// Long roleId = (Long) userData.get("roleId");
		List<Object[]> result;

		// if(UsersRoles.HO.equals(roleId)) {
		// result =
		// proposalDetailRepository.getProposalDetailsByOrgId(userOrgId,
		// request.getFromDate(), request.getToDate());
		// } else if(UsersRoles.BO.equals(roleId)) {
		result = proposalDetailRepository.getProposalDetailsByOrgId(userOrgId, request.getFromDate(),
				request.getToDate());
		// }

		List<ProposalDetailsAdminRequest> responseList = new ArrayList<>(result.size());

		for (Object[] obj : result) {
			ProposalDetailsAdminRequest proposal = new ProposalDetailsAdminRequest();
			proposal.setApplicationId(CommonUtils.convertLong(obj[0]));
			proposal.setUserId(CommonUtils.convertLong(obj[1]));
			proposal.setUserName(CommonUtils.convertString(obj[2]));
			proposal.setEmail(CommonUtils.convertString(obj[3]));
			proposal.setMobile(CommonUtils.convertString(obj[4]));
			proposal.setCreatedDate(CommonUtils.convertDate(obj[5]));
			proposal.setBranchId(CommonUtils.convertLong(obj[6]));
			proposal.setLoanAmount(CommonUtils.convertString(obj[7]));
			proposal.setTenure(CommonUtils.convertString(obj[8]));
			proposal.setRate(CommonUtils.convertString(obj[9]));
			proposal.setEmi(CommonUtils.convertDouble(obj[10]));
			proposal.setProcessingFee(CommonUtils.convertDouble(obj[11]));
			proposal.setBranchName(CommonUtils.convertString(obj[12]));
			proposal.setContactPersonName(CommonUtils.convertString(obj[13]));
			proposal.setTelephoneNo(CommonUtils.convertString(obj[14]));
			proposal.setContactPersonNumber(CommonUtils.convertString(obj[15]));
			proposal.setOrganizationName(CommonUtils.convertString(obj[16]));
			proposal.setApplicationCode(CommonUtils.convertString(obj[17]));
			proposal.setCode(CommonUtils.convertString(obj[18]));
			proposal.setStreetName(CommonUtils.convertString(obj[19]));
			proposal.setState(CommonUtils.convertString(obj[20]));
			proposal.setCity(CommonUtils.convertString(obj[21]));
			proposal.setPremisesNo(CommonUtils.convertString(obj[22]));
			proposal.setFpProductId(CommonUtils.convertLong(obj[23]));
			proposal.setContactPersonEmail(CommonUtils.convertString(obj[24]));
			proposal.setIsCampaignCustomer(CommonUtils.convertLong(obj[25]) > 0);
			proposal.setGstin(CommonUtils.convertString(obj[26]));

			responseList.add(proposal);
		}

		return responseList;
	}

	@Autowired
	private OfflineProcessedAppRepository offlineProcessedAppRepository;

	@Override
	public List<Object[]> getHomeCounterDetail() {
		logger.info(
				"========== Enter in getHomeCounter()  gettting no of fp , fs and total inprinciple and inprinciple amount======== ");
		List<Object[]> object = offlineProcessedAppRepository.getHomeCounterDetail();
		logger.info("========== Exit from  getHomeCounter() ======== " + object);
		return object;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<?> basicInfoForSearch(ProposalMappingRequest request) {
        UserResponse userResponse=null;
        BranchBasicDetailsRequest basicDetailsRequest=null;
		try {
			// set branch id to proposal request
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(request.getUserId());
			logger.info(CURRENT_USER_ID_MSG + request.getUserId());
			userResponse = usersClient.getBranchDetailsBYUserId(usersRequest);
			basicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), BranchBasicDetailsRequest.class);
			if (!CommonUtils.isObjectNullOrEmpty(basicDetailsRequest)) {
				logger.info(FOUND_BRANCH_ID_MSG + basicDetailsRequest.getId()
						+ ROLE_ID_MSG + basicDetailsRequest.getRoleId());
				if (basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.BO
						|| basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.FP_CHECKER) {
					logger.info("Current user is Branch officer or FP_CHECKER");
					request.setBranchId(basicDetailsRequest.getId());
				}
			} else {
				logger.info(BRANCH_ID_CAN_NOT_BE_FOUND_MSG);
			}
		} catch (Exception e) {
			logger.error(THROW_EXCEPTION_WHILE_GET_BRANCH_ID_FROM_USER_ID_MSG,e);
		}

        List<Object[]> result = null;
		if(basicDetailsRequest.getRoleId() == CommonUtils.UsersRoles.SMECC)
        {
            result = proposalDetailRepository.getAllProposalsForSearchWithBranch(request.getFpProductId(), request.getProposalStatusId(), userResponse.getBranchList());
        }
        else
        {
            if(request.getBranchId() != null){
                result = proposalDetailRepository.getAllProposalsForSearchWithBranch(request.getFpProductId(), request.getProposalStatusId(), request.getBranchId());
            }else{
                result = proposalDetailRepository.getAllProposalsForSearch(request.getFpProductId(), request.getProposalStatusId());
            }
        }

		
		List<Map<String, Object>> finalList = new ArrayList<>(result.size());
		
		for(Object[] arr : result ){
			Map<String, Object> data = new HashMap<>();
			data.put("applicationId", arr[0]);
			data.put("organizationName", arr[1]);
			data.put("applicationCode", arr[2]);
			data.put("businessTypeId", CommonUtils.isObjectNullOrEmpty(arr[3]) ? BusinessType.EXISTING_BUSINESS.getId() : arr[3]);
			finalList.add(data);
		}
		result.clear();
		return finalList;
	}
	
	public List<ProposalSearchResponse> searchProposalByAppCode(Long loginUserId,Long loginOrgId,ReportRequest reportRequest) {
		Object[] loggedUserDetailsList = loanRepository.getRoleIdAndBranchIdByUserId(loginUserId);
		Long roleId = CommonUtils.convertLong(loggedUserDetailsList[0]);
		Long branchId = CommonUtils.convertLong(loggedUserDetailsList[1]);
		if(CommonUtils.isObjectNullOrEmpty(roleId)) {
			return Collections.emptyList();			
		}
		if(roleId == 9) {//CHECKER AND MAKER
			List<Object[]> objList = loanRepository.searchProposalForCheckerAndMaker(loginOrgId, reportRequest.getValue(), branchId,reportRequest.getNumber().longValue());
			if(objList.size() > 0) {
				return setValue(objList, false);	
			}
		} else if(roleId == 5) {//HO
			List<Object[]> objList = loanRepository.searchProposalForHO(loginOrgId, reportRequest.getValue(),reportRequest.getNumber().longValue());
			if(objList.size() > 0) {
				return setValue(objList, true);	
			}
		} else if(roleId == 12) {//SMECC
			List<Object[]> objList = loanRepository.searchProposalForSMECC(loginOrgId, reportRequest.getValue(),loginUserId,reportRequest.getNumber().longValue());
			if(objList.size() > 0) {
				return setValue(objList, true);	
			}
		}
		return Collections.emptyList();
	}
	
	private List<ProposalSearchResponse> setValue(List<Object[]> objList,boolean setBranch) {
		List<ProposalSearchResponse> responseList = new ArrayList<>();
		ProposalSearchResponse response = null;
		for(Object[] obj : objList) {
			response = new ProposalSearchResponse();
			response.setApplicationId(CommonUtils.convertLong(obj[0]));
			response.setProposalId(CommonUtils.convertLong(obj[1]));
			response.setFpProductId(CommonUtils.convertLong(obj[2]));
			response.setApplicationCode(CommonUtils.convertString(obj[3]));
			response.setOrgName(CommonUtils.convertString(obj[4]));
			response.setElAmount(CommonUtils.convertDouble(obj[5]));
			response.setProductName(CommonUtils.convertString(obj[6]));
			response.setCreatedDate(CommonUtils.convertDate(obj[7]));
			response.setBusinessTypeId(CommonUtils.convertInteger(obj[8]));
			response.setProposalStatusId(CommonUtils.convertLong(obj[9]));
			response.setProductId(CommonUtils.convertInteger(obj[10]));
			if(setBranch) {
				response.setBranchName(CommonUtils.convertString(obj[11]));
				response.setBranchCode(CommonUtils.convertString(obj[12]));
			}
			responseList.add(response);
		}
		return responseList;
	}
	
	public Map<String , Double> getFpDashBoardCount(Long loginUserId,Long loginOrgId) {
		Object[] loggedUserDetailsList = loanRepository.getRoleIdAndBranchIdByUserId(loginUserId);
		Long roleId = CommonUtils.convertLong(loggedUserDetailsList[0]);
		Long branchId = CommonUtils.convertLong(loggedUserDetailsList[1]);
		if(CommonUtils.isObjectNullOrEmpty(roleId)) {
			return null;			
		}
		Object[] count = null;
		if(roleId == 9) {//FP CHECKER
			count = loanRepository.fpDashBoardCountByOrgIdAndBranchId(loginOrgId, branchId);
		} else if(roleId == 5){//HO
			count = loanRepository.fpDashBoardCountByOrgId(loginOrgId);
		} else if(roleId == 12){//SMECC
			count = loanRepository.fpDashBoardCountByOrgIdAndUserId(loginOrgId, loginUserId);
		}
		if(count != null) {
			Map<String , Double> map = new HashMap<>();
			map.put("inPrincipleCount", CommonUtils.convertDouble(count[0]));
			map.put("holdBeforeCount", CommonUtils.convertDouble(count[1]));
			map.put("holdAfterCount", CommonUtils.convertDouble(count[2]));
			map.put("rejectBeforeCount", CommonUtils.convertDouble(count[3]));
			map.put("rejectAfterCount", CommonUtils.convertDouble(count[4]));
			map.put("sanctionedCount", CommonUtils.convertDouble(count[5]));
			map.put("disbursmentCount", CommonUtils.convertDouble(count[6]));
			return map;
		}
		return null;
	}

	@Override
	public Integer updateStatus(Long applicationId, Long fpProductId, Long status,String remarks) {
		return proposalDetailRepository.updateStatus(status, applicationId, fpProductId,remarks);
	}
}

