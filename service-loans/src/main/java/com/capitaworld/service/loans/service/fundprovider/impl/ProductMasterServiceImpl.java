package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.LapParameter;
import com.capitaworld.service.loans.domain.fundprovider.LasParameter;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.GeographicalCountryRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.service.common.FundProviderSequenceService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;

@Service
@Transactional
public class ProductMasterServiceImpl implements ProductMasterService {

	@Autowired
	private Environment environment;

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

	@Override
	public List<CommonResponse> saveOrUpdate(MultipleFpPruductRequest productMasters) {
		// TODO Auto-generated method stub
		// inactive old record before saving new record
		productMasterRepository.inActive((CommonUtils.isObjectNullOrEmpty(productMasters.getClientId())
				? productMasters.getUserId() : productMasters.getClientId()));
		WorkingCapitalParameter capitalParameter = null;
		TermLoanParameter termLoanParameter = null;
		HomeLoanParameter homeLoanParameter = null;
		CarLoanParameter carLoanParameter = null;
		PersonalLoanParameter personalLoanParameter = null;
		LasParameter lasParameter = null;
		LapParameter lapParameter = null;

		List<CommonResponse> commonResponses = new ArrayList<CommonResponse>();
		try {
			for (Map<String, Object> obj : productMasters.getDataList()) {
				ProductMasterRequest productMasterRequest = (ProductMasterRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ProductMasterRequest.class);
				ProductMaster productMaster = null;
				LoanType loanType = LoanType.getById(Integer.parseInt(productMasterRequest.getProductId().toString()));
				if (loanType == null)
					continue;
				switch (loanType) {
				case WORKING_CAPITAL:
					productMaster = new WorkingCapitalParameter();
					if (productMasterRequest.getId() != null)
						capitalParameter = workingCapitalParameterRepository.findOne(productMasterRequest.getId());
					if (capitalParameter != null)
						BeanUtils.copyProperties(capitalParameter, productMaster);
					break;
				case TERM_LOAN:
					productMaster = new TermLoanParameter();
					if (productMasterRequest.getId() != null)
						termLoanParameter = termLoanParameterRepository.findOne(productMasterRequest.getId());
					if (termLoanParameter != null)
						BeanUtils.copyProperties(termLoanParameter, productMaster);
					break;
				case HOME_LOAN:
					productMaster = new HomeLoanParameter();
					if (productMasterRequest.getId() != null)
						homeLoanParameter = homeLoanParameterRepository.findOne(productMasterRequest.getId());
					if (homeLoanParameter != null)
						BeanUtils.copyProperties(homeLoanParameter, productMaster);
					break;
				case CAR_LOAN:
					productMaster = new CarLoanParameter();
					if (productMasterRequest.getId() != null)
						carLoanParameter = carLoanParameterRepository.findOne(productMasterRequest.getId());
					if (carLoanParameter != null)
						BeanUtils.copyProperties(carLoanParameter, productMaster);
					break;
				case PERSONAL_LOAN:
					productMaster = new PersonalLoanParameter();
					if (productMasterRequest.getId() != null)
						personalLoanParameter = personalLoanParameterRepository.findOne(productMasterRequest.getId());
					if (personalLoanParameter != null)
						BeanUtils.copyProperties(personalLoanParameter, productMaster);
					break;
				case LOAN_AGAINST_PROPERTY:
					productMaster = new LapParameter();
					if (productMasterRequest.getId() != null)
						lapParameter = lapParameterRepository.findOne(productMasterRequest.getId());
					if (lapParameter != null)
						BeanUtils.copyProperties(lapParameter, productMaster);
					break;
				case LOAN_AGAINST_SHARES_AND_SECUIRITIES:
					productMaster = new LasParameter();
					if (productMasterRequest.getId() != null)
						lasParameter = lasParameterRepository.findOne(productMasterRequest.getId());
					if (lasParameter != null)
						BeanUtils.copyProperties(lasParameter, productMaster);
					break;

				default:
					break;
				}
				BeanUtils.copyProperties(productMasterRequest, productMaster);
				productMaster.setFpName(productMasters.getFpName());
				productMaster.setUserId((CommonUtils.isObjectNullOrEmpty(productMasters.getClientId())
						? productMasters.getUserId() : productMasters.getClientId()));
				productMaster.setCreatedBy(productMasters.getUserId());
				productMaster.setCreatedDate(new Date());
				productMaster.setModifiedBy(productMasters.getUserId());
				productMaster.setIsParameterFilled(true);
				productMaster.setModifiedDate(new Date());
				productMaster.setIsActive(true);
				productMaster.setProductCode(fundProviderSequenceService.getFundProviderSequenceNumber(productMasterRequest.getProductId()));
				ProductMaster master = productMasterRepository.save(productMaster);
				CommonResponse commonResponse = new CommonResponse();
				commonResponse.setId(master.getId());
				commonResponses.add(commonResponse);
			}
			return commonResponses;
		}

		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ProductMaster getProductMaster(Long id) {
		// TODO Auto-generated method stub
		return productMasterRepository.findOne(id);
	}

	@Override
	public List<ProductMasterRequest> getList(Long userId) {
		// TODO Auto-generated method stub
		List<ProductMaster> results = productMasterRepository.getUserProductList(userId);
		List<ProductMasterRequest> requests = new ArrayList<>(results.size());
		for (ProductMaster master : results) {
			ProductMasterRequest request = new ProductMasterRequest();
			BeanUtils.copyProperties(master, request);
			requests.add(request);
		}
		return requests;
	}

	@Override
	public String getUserNameByApplicationId(Long productId, Long userId) {
		// TODO Auto-generated method stub

		ProductMaster productMaster = productMasterRepository.getUserProduct(productId, userId);
		if (productMaster != null) {
			return productMaster.getFpName();
		}
		return null;
	}

	@Override
	public Object[] getUserDetailsByPrductId(Long fpMappingId) {
		// TODO Auto-generated method stub
		List<Object[]> pm = productMasterRepository.findById(fpMappingId);
		return (pm != null && !pm.isEmpty()) ? pm.get(0) : null;
	}

	@Override
	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId) {

		return productMasterRepository.getListByUserId(userId);

	}

	@Override
	public ProductDetailsResponse getProductDetailsResponse(Long userId) {
		// TODO Auto-generated method stub

		List<ProductMaster> productMasterList = productMasterRepository.getUserProductList(userId);
		ProductMaster productMaster = null;
		ProductDetailsResponse productDetailsResponse = new ProductDetailsResponse();
		if (productMasterList.size() > 0) {
			productMaster = productMasterList.get(0);
		}
		if (productMaster != null) {
			productDetailsResponse.setProductId(productMaster.getProductId());
			productDetailsResponse.setProductMappingId(productMaster.getId());
			productDetailsResponse.setMessage("Proposal Details Sent");
			productDetailsResponse.setStatus(HttpStatus.OK.value());
		} else {
			productDetailsResponse.setMessage("Something went wrong");
			productDetailsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return productDetailsResponse;
	}

	@Override
	public FpProductDetails getProductDetails(Long productMappingId) throws Exception {
		// TODO Auto-generated method stub

		ProductMaster productMaster = productMasterRepository.findOne(productMappingId);
		LoanType loanType = LoanType.getById(productMaster.getProductId());
		FpProductDetails fpProductDetails = new FpProductDetails();
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
		CountryByCountryListIdClient byCountryListIdClient = new CountryByCountryListIdClient(
				environment.getRequiredProperty(CommonUtils.ONE_FORM));
		OneFormResponse oneFormResponse = (OneFormResponse) byCountryListIdClient.send(countryList);
		List<Map<String, Object>> oneResponseDataList = (List<Map<String, Object>>) oneFormResponse.getListData();
		if (oneResponseDataList != null && !oneResponseDataList.isEmpty()) {

			for (int i = 0; i < oneResponseDataList.size(); i++) {
				MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(oneResponseDataList.get(0),
						MasterResponse.class);
				countryname.add(masterResponse.getValue());
			}
		}

		fpProductDetails.setGeographicalFocus(countryname);
		//fp profile details
		UsersClient usersClient=new UsersClient(environment.getProperty(CommonUtils.USER_CLIENT_URL));
		fpProductDetails.setFpDashboard(usersClient.getFPDashboardDetails(productMaster.getUserId()));
				
		
	
		return fpProductDetails;
	}

	@Override
	public boolean isSelfView(Long fpProductId,Long userId){
		return productMasterRepository.getUserProduct(fpProductId, userId) != null;
	}


}