package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import com.capitaworld.service.loans.model.FundProviderProposalDetails;
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
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.MatchResponse;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.oneform.client.IndustryClient;
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
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private ProductMasterRepository productMasterRepository;
	
	@Autowired
	private IndustrySectorRepository industrySectorRepository;

	@Override
	public List fundproviderProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub

		List proposalDetailsList = new ArrayList();

		try {
			
			// calling MATCHENGINE for getting proposal list
			
			ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
					environment.getRequiredProperty("matchesURL"));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundProvider(request);
			
			MatchEngineClient matchEngineClient = new MatchEngineClient(
					environment.getRequiredProperty("matchesURL"));
			
			

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++)
			{
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				Long applicationId = proposalrequest.getApplicationId();
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
				if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
				{
					
					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
					
					if(corporateApplicantDetail == null)
						continue;
					
					// for get address city state country
					String address="";
					if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
					{
						address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), environment);
					}
					else
					{
						address+="NA ,";
					}
					if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
					{
						address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), environment);
					}
					else
					{
						address+="NA ,";
					}
					if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
					{
						address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), environment);
					}
					else
					{
						address+="NA";
					}
					
					CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();
					
					corporateProposalDetails.setAddress(address);
					
					if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
						corporateProposalDetails.setName("NA");
					else
						corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
					
					corporateProposalDetails.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
					
					//for get industry id
					List<Long> listIndustryIds=industrySectorRepository.getIndustryByApplicationId(applicationId);
					if(listIndustryIds.size()>0)
					{
						IndustryClient industryClient=new  IndustryClient(environment.getProperty(CommonUtils.ONE_FORM));
						OneFormResponse formResponse=industryClient.send(listIndustryIds);
						
						List<Map<String, Object>> loanResponseDatalist = (List<Map<String, Object>>) formResponse.getListData();
						String industry = "";
						if(loanResponseDatalist.size()>0)
						{
							for(int k=0;k<loanResponseDatalist.size();k++)
							{
								MasterResponse masterResponse=new MasterResponse();
								masterResponse= MultipleJSONObjectHelper.getObjectFromMap(loanResponseDatalist.get(k),
								         MasterResponse.class);
								industry += masterResponse.getValue() + " ,";
							}
							corporateProposalDetails.setIndustry(industry);
						}
						else
						{
							corporateProposalDetails.setIndustry("NA");
						}
					}
					else
					{
						corporateProposalDetails.setIndustry("NA");
					}
					
					String amount="";
					if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
						amount += "NA";
					else
						amount +=loanApplicationMaster.getAmount().toString();
					
					if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
						amount += " NA";
					else
						amount += " " + Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();
							
					corporateProposalDetails.setAmount(amount);

					// calling DMS for getting fp profile image path
					
					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = null;
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath=null;
						}
					}
					
					corporateProposalDetails.setImagePath(imagePath);
					corporateProposalDetails.setApplicationId(applicationId);
					corporateProposalDetails.setProposalMappingId(proposalrequest.getId());
					corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
					proposalDetailsList.add(corporateProposalDetails);
				}
				else
				{
					Long fpProductId=request.getFpProductId();
					
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

					if(retailApplicantDetail == null)
						continue;
					
					// for get address city state country
					String address="";
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
					{
						address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), environment);
					}
					else
					{
						address+="NA ,";
					}
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
					{
						address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), environment);
					}
					else
					{
						address+="NA ,";
					}
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
					{
						address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), environment);
					}
					else
					{
						address+="NA";
					}
					
					RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
					retailProposalDetails.setAddress(address);
					
					String name="";
					
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
						name+="NA";
					else name+=retailApplicantDetail.getFirstName();
					
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getLastName()))
						name+=" NA";
					else name+=" "+retailApplicantDetail.getLastName();
					
					retailProposalDetails.setName(name);

					// calling DMS for getting fp profile image path
					
					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = null;
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath=null;
						}
					}

					retailProposalDetails.setImagePath(imagePath);
					retailProposalDetails.setApplicationId(applicationId);
					retailProposalDetails.setProposalMappingId(proposalrequest.getId());
					retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
					
					try {
						MatchRequest matchRequest=new MatchRequest();
						matchRequest.setApplicationId(applicationId);
						matchRequest.setProductId(fpProductId);
						MatchDisplayResponse matchResponse =  matchEngineClient.displayMatchesOfRetail(matchRequest);
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
		
		try
		{
			// calling MATCHENGINE for getting proposal list
			
			ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
						environment.getRequiredProperty("matchesURL"));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundSeeker(request);
			
			List<ProposalMappingRequest> proposalMappingList = new ArrayList<ProposalMappingRequest>();

				for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++)
				{
					UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));
					ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
							ProposalMappingRequest.class);

					ProductMaster master=productMasterRepository.findOne(proposalrequest.getFpProductId());
					UsersRequest userRequest = new UsersRequest();
					userRequest.setId(master.getUserId());
					
					// calling USER for getting fp details
					UserResponse userResponse = usersClient.getFPDetails(userRequest);
					
					FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
							(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
					FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();
					
					Long productId = proposalrequest.getFpProductId();
					
					fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
					fundProviderProposalDetails.setWhoAreYou(FundproviderType.getById(fundProviderDetailsRequest.getBusinessTypeMaster()).getValue());
					fundProviderProposalDetails.setFpType("DEBT");

					// calling DMS for getting fp profile image path
					
					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					
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
							if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
									imagePath = response.getFilePath();
							else
									imagePath="";
						}
					}

					fundProviderProposalDetails.setImagePath(imagePath);
					fundProviderProposalDetails.setProductId(productId);
					fundProviderProposalDetails.setProposalMappingId(proposalrequest.getId());
					proposalDetailsList.add(fundProviderProposalDetails);
				}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return proposalDetailsList;
	}

	@Override
	public ProposalCountResponse fundProviderProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();
		
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.proposalCountOfFundProvider(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalCountResponse fundSeekerProposalCount(ProposalMappingRequest request) {
		ProposalCountResponse response = new ProposalCountResponse();
		
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.proposalCountOfFundSeeker(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse get(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.getProposal(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse changeStatus(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.changeStatus(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse listOfFundSeekerProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
		ProposalMappingResponse response = new ProposalMappingResponse();
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.listOfFundSeekerProposal(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public ProposalMappingResponse getConectionList(ProposalMappingRequest proposalMappingRequest) {
		// TODO Auto-generated method stub
		
		ProposalMappingResponse proposalMappingResponse=new ProposalMappingResponse();
		
		// calling MATCHENGINE for getting connection list
		
		try {
				List proposalDetailsList = new ArrayList();
			
				ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
					environment.getRequiredProperty("matchesURL"));
				ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.connections(proposalMappingRequest);
			
				if(!(CommonUtils.UserType.FUND_SEEKER == proposalMappingRequest.getUserType()))
				{
					for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++)
					{
						Integer applicationId = (Integer)proposalDetailsResponse.getDataList().get(i);
						LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId.longValue());
						if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
						{
							
							CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId.longValue());
							
							if(corporateApplicantDetail == null)
								continue;
							
							// for get address city state country
							String address="";
							if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
							{
								address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), environment);
							}
							else
							{
								address+="NA ,";
							}
							if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
							{
								address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), environment);
							}
							else
							{
								address+="NA ,";
							}
							if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
							{
								address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), environment);
							}
							else
							{
								address+="NA";
							}
							
							CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();
							
							corporateProposalDetails.setAddress(address);
							
							if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
								corporateProposalDetails.setName("NA");
							else
								corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
							
							corporateProposalDetails.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
							
							
							String amount="";
							if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
								amount += "NA";
							else
								amount +=loanApplicationMaster.getAmount().toString();
							
							if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
								amount += " NA";
							else
								amount += " " + Denomination.getById(loanApplicationMaster.getDenominationId()).getValue();
									
							corporateProposalDetails.setAmount(amount);

							// calling DMS for getting fp profile image path
							
							DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
							
							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
							
							DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath=null;
								}
							}
							
							corporateProposalDetails.setImagePath(imagePath);
							corporateProposalDetails.setApplicationId(applicationId.longValue());
							corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
							proposalDetailsList.add(corporateProposalDetails);
						}
						else
						{
							RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId.longValue());

							if(retailApplicantDetail == null)
								continue;
							
							// for get address city state country
							String address="";
							if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
							{
								address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), environment);
							}
							else
							{
								address+="NA ,";
							}
							if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
							{
								address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), environment);
							}
							else
							{
								address+="NA ,";
							}
							if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
							{
								address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), environment);
							}
							else
							{
								address+="NA";
							}
							
							RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
							retailProposalDetails.setAddress(address);
							
							String name="";
							
							if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
								name+="NA";
							else name+=retailApplicantDetail.getFirstName();
							
							retailProposalDetails.setName(name);

							// calling DMS for getting fp profile image path
							
							DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
							DocumentRequest documentRequest = new DocumentRequest();
							documentRequest.setApplicationId(applicationId.longValue());
							documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
							documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
							DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
							String imagePath = null;
							if (documentResponse != null && documentResponse.getStatus() == 200) {
								List<Map<String, Object>> list = documentResponse.getDataList();
								if (!CommonUtils.isListNullOrEmpty(list)) {
									StorageDetailsResponse response = null;

									response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
											StorageDetailsResponse.class);

									if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
									else
										imagePath=null;
								}
							}

							retailProposalDetails.setImagePath(imagePath);
							retailProposalDetails.setApplicationId(applicationId.longValue());
							retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
							proposalDetailsList.add(retailProposalDetails);
						}

					}
				}
				else
				{
				
					for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++)
					{
						UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));
					
						Integer fpProductId=Integer.class.cast(proposalDetailsResponse.getDataList().get(i));
						ProductMaster master=productMasterRepository.findOne(fpProductId.longValue());
						UsersRequest userRequest = new UsersRequest();
						userRequest.setId(master.getUserId());
						
						// calling USER for getting fp details
						UserResponse userResponse = usersClient.getFPDetails(userRequest);
						
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
								(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
						FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();
					
						
						fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
						fundProviderProposalDetails.setWhoAreYou(FundproviderType.getById(fundProviderDetailsRequest.getBusinessTypeMaster()).getValue());
						fundProviderProposalDetails.setFpType("DEBT");

						// calling DMS for getting fp profile image path
						
						DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
						
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

								response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
								if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
										imagePath = response.getFilePath();
								else
										imagePath=null;
							}
						}

						fundProviderProposalDetails.setImagePath(imagePath);
						fundProviderProposalDetails.setProductId(fpProductId.longValue());
						proposalDetailsList.add(fundProviderProposalDetails);
					}
				}
				proposalMappingResponse.setDataList(proposalDetailsList);
				proposalMappingResponse.setMessage("connection list sent");
				proposalMappingResponse.setStatus(HttpStatus.OK.value());
				
		} catch (Exception e) {
			// TODO: handle exception
			ProposalMappingResponse proposalMappingResponseErr=new ProposalMappingResponse("Error while getting connection list",HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
			return proposalMappingResponseErr;
		}
		
		return proposalMappingResponse;
	}

	@Override
	public ProposalMappingResponse sendRequest(ProposalMappingRequest request) {
		// TODO Auto-generated method stub
	ProposalMappingResponse response = new ProposalMappingResponse();
		
		ProposalDetailsClient client = new ProposalDetailsClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
		try {
			response = client.sendRequest(request);	
		} catch(Exception e){
			e.printStackTrace();
		}
		return response;
	}

	
}
