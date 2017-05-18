package com.capitaworld.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class ProposalServiceMappingImpl implements ProposalService {

	@Autowired
	Environment environment;

	@Autowired
	LoanApplicationRepository loanApplicationRepository;

	@Autowired
	CorporateApplicantDetailRepository corporateApplicantDetailRepository;

	@Autowired
	RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	ProductMasterRepository productMasterRepository;
	
	@Autowired
	IndustrySectorRepository industrySectorRepository;

	@Override
	public List fundproviderProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub

		List proposalDetailsList = new ArrayList();

		try {
			
			// calling MATCHENGINE for getting proposal list
			
			ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
					environment.getRequiredProperty("matchesURL"));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundProvider(request);

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
					
					CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();
					
					if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail) ||CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
						corporateProposalDetails.setName("NA");
					else
						corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
					
					corporateProposalDetails.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
					corporateProposalDetails.setIndustry("industry");
					String amount="";
					if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getAmount()))
						amount += "NA";
					else
						amount +=loanApplicationMaster.getAmount().toString();
					
					if(CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDenominationId()))
						amount += " NA";
					else
						amount += " " + loanApplicationMaster.getDenominationId().toString();
							
					corporateProposalDetails.setAmount(amount);

					// calling DMS for getting fp profile image path
					
					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = "";
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath="";
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
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

					RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
					
					String name="";
					
					if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
						name+="NA";
					else name+=retailApplicantDetail.getFirstName();
					
					retailProposalDetails.setName(name);
					
					//retailProposalDetails.setName(retailApplicantDetail.getFirstName() + " "+ retailApplicantDetail.getMiddleName() + " " + retailApplicantDetail.getLastName());

					// calling DMS for getting fp profile image path
					
					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = "";
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							if(!CommonUtils.isObjectNullOrEmpty(response.getFilePath()))
								imagePath = response.getFilePath();
							else
								imagePath="";
						}
					}

					retailProposalDetails.setImagePath(imagePath);
					retailProposalDetails.setApplicationId(applicationId);
					retailProposalDetails.setProposalMappingId(proposalrequest.getId());
					retailProposalDetails.setFsType(CommonUtils.UserMainType.RETAIL);
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
					fundProviderProposalDetails.setWhoAreYou(fundProviderDetailsRequest.getBusinessTypeMaster().getId().toString());
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

}
