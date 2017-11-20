package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.ConnectionReleaseMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.CorporateProposalDetails;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.model.MonthlyTurnoverDetailRequest;
import com.capitaworld.service.loans.model.ProposalResponse;
import com.capitaworld.service.loans.model.RetailProposalDetails;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ConnectionResponse;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Currency;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.FundproviderType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
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

	DecimalFormat df = new DecimalFormat("#");
	
	private static final Logger logger = LoggerFactory.getLogger(ProposalServiceMappingImpl.class.getName());

	@Override
	public List fundproviderProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub

		List proposalDetailsList = new ArrayList();

		try {

			// calling MATCHENGINE for getting proposal list

			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundProvider(request);

			MatchEngineClient matchEngineClient = new MatchEngineClient(environment.getRequiredProperty("matchesURL"));

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				Long applicationId = proposalrequest.getApplicationId();
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);

				if(!loanApplicationMaster.getIsActive()) {
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

					corporateProposalDetails.setAddress(address);

					if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
						corporateProposalDetails.setName("NA");
					else
						corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());

					corporateProposalDetails
							.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));

					// for get industry id
					List<Long> listIndustryIds = industrySectorRepository.getIndustryByApplicationId(applicationId);
					if (listIndustryIds.size() > 0) {
						OneFormResponse formResponse = oneFormClient.getIndustryById(listIndustryIds);
						List<Map<String, Object>> loanResponseDatalist = (List<Map<String, Object>>) formResponse
								.getListData();
						String industry = "";
						if (loanResponseDatalist.size() > 0) {
							for (int k = 0; k < loanResponseDatalist.size(); k++) {
								MasterResponse masterResponse = new MasterResponse();
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

					corporateProposalDetails.setImagePath(imagePath);
					corporateProposalDetails.setApplicationId(applicationId);
					corporateProposalDetails.setProposalMappingId(proposalrequest.getId());
					corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
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
						// TODO: handle exception
						e.printStackTrace();
					}
					proposalDetailsList.add(retailProposalDetails);
				}

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return proposalDetailsList;
	}

	@Override
	public List<FundProviderProposalDetails> fundseekerProposal(ProposalMappingRequest request, Long userId) {

		// TODO Auto-generated method stub
		List<FundProviderProposalDetails> proposalDetailsList = new ArrayList<FundProviderProposalDetails>();

		try {
			// calling MATCHENGINE for getting proposal list

			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundSeeker(request);

			List<ProposalMappingRequest> proposalMappingList = new ArrayList<ProposalMappingRequest>();

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {
				UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				ProductMaster master = productMasterRepository.findOne(proposalrequest.getFpProductId());
				if(!master.getIsActive()) {
					logger.info("Product Id is InActive while get fundSeeker proposals=====>" + proposalrequest.getFpProductId());
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
				
				fundProviderProposalDetails.setFpProductName(CommonUtils.isObjectNullOrEmpty(master.getName())?" ":master.getName());

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
				proposalDetailsList.add(fundProviderProposalDetails);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return proposalDetailsList;
	}

	@Override
	public ProposalCountResponse fundProviderProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();

		try {
			response = proposalDetailsClient.proposalCountOfFundProvider(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalCountResponse fundSeekerProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();
		try {
			response = proposalDetailsClient.proposalCountOfFundSeeker(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse get(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.getProposal(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse changeStatus(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();

		ProposalDetailsClient client = new ProposalDetailsClient(
				environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.changeStatus(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse listOfFundSeekerProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.listOfFundSeekerProposal(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalResponse getConectionList(ProposalMappingRequest proposalMappingRequest) {
		// TODO Auto-generated method stub

		ProposalResponse proposalResponse = new ProposalResponse();

		List proposalByMatches = new ArrayList();
		List proposalWithoutMatches = new ArrayList();
		// calling MATCHENGINE for getting connection list

		try {
			
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.connections(proposalMappingRequest);
			ConnectionResponse connectionResponse=	(ConnectionResponse) MultipleJSONObjectHelper
			.getObjectFromMap((Map<String, Object>)proposalDetailsResponse.getData(),ConnectionResponse.class);
			

			if (!(CommonUtils.UserType.FUND_SEEKER == proposalMappingRequest.getUserType())) {	
				//for get suggetionListby matches (10)
				for (int i = 0; i < connectionResponse.getSuggetionByMatchesList().size(); i++) {
					try {
						BigInteger applicationId = (BigInteger) connectionResponse.getSuggetionByMatchesList().get(i);
						LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
								.findOne(applicationId.longValue());
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

							if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
								corporateProposalDetails.setName("NA");
							else
								corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());

							corporateProposalDetails.setFsMainType(
									CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));

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
						e.printStackTrace();
					}

				}
				//for set connection without proposal 10
				
				for (int i = 0; i < connectionResponse.getSuggetionList().size(); i++) {
					try {
						BigInteger applicationId = (BigInteger) connectionResponse.getSuggetionList().get(i);
						LoanApplicationMaster loanApplicationMaster = loanApplicationRepository
								.findOne(applicationId.longValue());
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

							if (CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
								corporateProposalDetails.setName("NA");
							else
								corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());

							corporateProposalDetails.setFsMainType(
									CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));

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
						e.printStackTrace();
					}

				}
				
			} else {

				for (int i = 0; i < connectionResponse.getSuggetionByMatchesList().size(); i++) {
					try {
						UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));

						BigInteger fpProductId = BigInteger.class.cast(connectionResponse.getSuggetionByMatchesList().get(i));
						ProductMaster master = productMasterRepository.findOne(fpProductId.longValue());
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
						
						fundProviderProposalDetails.setFpProductName(CommonUtils.isObjectNullOrEmpty(master.getName())?" ":master.getName());

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
						e.printStackTrace();
					}
				}
				
				//set connection without matches
				
				for (int i = 0; i < connectionResponse.getSuggetionList().size(); i++) {
					try {
						UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));

						BigInteger fpProductId = BigInteger.class.cast(connectionResponse.getSuggetionList().get(i));
						ProductMaster master = productMasterRepository.findOne(fpProductId.longValue());
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
						
						fundProviderProposalDetails.setFpProductName(CommonUtils.isObjectNullOrEmpty(master.getName())?" ":master.getName());

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
						e.printStackTrace();
					}
				}
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			ProposalMappingResponse proposalMappingResponseErr = new ProposalMappingResponse(
					"Error while getting connection list", HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			return null;
		}

		proposalResponse.setProposalByMatches(proposalByMatches);
		proposalResponse.setProposalWithoutMatches(proposalWithoutMatches);
		return proposalResponse;
	}

	@Override
	public ProposalMappingResponse sendRequest(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.sendRequest(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public Integer getPendingProposalCount(Long applicationId) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		try {
			response = proposalDetailsClient.getPendingProposalCount(applicationId);
			return (Integer)response.getData();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
