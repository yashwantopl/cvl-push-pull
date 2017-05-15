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
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.CorporateProposalDetails;
import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.model.RetailProposalDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
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

	@Override
	public List fundproviderProposal(ProposalMappingRequest request) {
		// TODO Auto-generated method stub

		List proposalDetailsList = new ArrayList();

		try {
			ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
					environment.getRequiredProperty("matchesURL"));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundProvider(request);

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {
				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				Long applicationId = proposalrequest.getApplicationId();
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
				if (CommonUtils.UserMainType.CORPORATE == CommonUtils
						.getUserMainType(loanApplicationMaster.getProductId())) {

					CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository
							.findOne(applicationId);

					CorporateProposalDetails corporateProposalDetails = new CorporateProposalDetails();
					corporateProposalDetails.setName(corporateApplicantDetail.getOrganisationName());
					corporateProposalDetails
							.setFsMainType(CommonUtils.getCorporateLoanType(loanApplicationMaster.getProductId()));
					corporateProposalDetails.setIndustry("industry");
					corporateProposalDetails.setAmount(loanApplicationMaster.getAmount().toString() + " "
							+ loanApplicationMaster.getDenominationId().toString());

					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(
							CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = "";
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							imagePath = response.getFilePath();
						}
					}
					corporateProposalDetails.setImagePath(imagePath);
					corporateProposalDetails.setApplicationId(applicationId);
					corporateProposalDetails.setProposalMappingId(proposalrequest.getId());
					corporateProposalDetails.setFsType(CommonUtils.UserMainType.CORPORATE);
					proposalDetailsList.add(corporateProposalDetails);
				} else {
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository
							.findOne(applicationId);

					RetailProposalDetails retailProposalDetails = new RetailProposalDetails();
					retailProposalDetails.setName(retailApplicantDetail.getFatherName() + " "
							+ retailApplicantDetail.getMiddleName() + " " + retailApplicantDetail.getLastName());

					DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
					DocumentRequest documentRequest = new DocumentRequest();
					documentRequest.setApplicationId(applicationId);
					documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
					documentRequest.setUserDocumentMappingId(
							CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
					DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
					String imagePath = "";
					if (documentResponse != null && documentResponse.getStatus() == 200) {
						List<Map<String, Object>> list = documentResponse.getDataList();
						if (!CommonUtils.isListNullOrEmpty(list)) {
							StorageDetailsResponse response = null;

							response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
									StorageDetailsResponse.class);

							imagePath = response.getFilePath();
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
		}
		return proposalDetailsList;
	}

	@Override
	public List<FundProviderProposalDetails> fundseekerProposal(ProposalMappingRequest request, Long userId) {
		// TODO Auto-generated method stub
		List<FundProviderProposalDetails> proposalDetailsList = new ArrayList<FundProviderProposalDetails>();

		try {
			ProposalDetailsClient proposalDetailsClient = new ProposalDetailsClient(
					environment.getRequiredProperty("matchesURL"));
			ProposalMappingResponse proposalDetailsResponse = proposalDetailsClient.proposalListOfFundSeeker(request);

			UsersClient usersClient = new UsersClient(environment.getRequiredProperty("userURL"));

			List<ProposalMappingRequest> proposalMappingList = new ArrayList<ProposalMappingRequest>();

			for (int i = 0; i < proposalDetailsResponse.getDataList().size(); i++) {

				ProposalMappingRequest proposalrequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) proposalDetailsResponse.getDataList().get(i),
						ProposalMappingRequest.class);

				UsersRequest userRequest = new UsersRequest();
				userRequest.setId(userId);
				UserResponse userResponse = usersClient.getFPDetails(userRequest);
				FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
						(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);

				FundProviderProposalDetails fundProviderProposalDetails = new FundProviderProposalDetails();
				Long productId = proposalrequest.getFpProductId();
				fundProviderProposalDetails.setName(fundProviderDetailsRequest.getOrganizationName());
				fundProviderProposalDetails
						.setWhoAreYou(fundProviderDetailsRequest.getBusinessTypeMaster().getId().toString());
				fundProviderProposalDetails.setFpType("fp type");

				DMSClient dmsClient = new DMSClient(environment.getRequiredProperty("dmsURL"));
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setUserId(userId);
				documentRequest.setUserType("user");
				documentRequest.setUserDocumentMappingId(1L);
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = "";
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse response = null;

						response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);

						imagePath = response.getFilePath();
					}
				}

				fundProviderProposalDetails.setImagePath(imagePath);
				fundProviderProposalDetails.setProductId(productId);
				fundProviderProposalDetails.setProposalMappingId(proposalrequest.getId());
				proposalDetailsList.add(fundProviderProposalDetails);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return proposalDetailsList;
	}

}
