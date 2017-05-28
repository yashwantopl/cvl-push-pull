package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.repository.fundprovider.CarLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.HomeLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LapParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.LasParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.PersonalLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class ProductMasterServiceImpl implements ProductMasterService {

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

	@Override
	public List<CommonResponse> saveOrUpdate(MultipleFpPruductRequest productMasters) {
		// TODO Auto-generated method stub
		// inactive old record before saving new record
		productMasterRepository.inActive((CommonUtils.isObjectNullOrEmpty(productMasters.getClientId())
				? productMasters.getUserId() : productMasters.getClientId()));
		WorkingCapitalParameter capitalParameter = null;
		TermLoanParameter  termLoanParameter=null;
		HomeLoanParameter homeLoanParameter=null;
		CarLoanParameter carLoanParameter=null;
		PersonalLoanParameter personalLoanParameter=null;
		LasParameter lasParameter=null;
		LapParameter lapParameter=null;

		List<CommonResponse> commonResponses = new ArrayList<CommonResponse>();
		try {
			for (Map<String, Object> obj : productMasters.getDataList()) {
				ProductMasterRequest productMasterRequest = (ProductMasterRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ProductMasterRequest.class);
				ProductMaster productMaster = null;
				LoanType loanType = LoanType.getType(Integer.parseInt(productMasterRequest.getProductId().toString()));
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
				case LAP_LOAN:
					productMaster = new LapParameter();
					if (productMasterRequest.getId() != null)
						lapParameter = lapParameterRepository.findOne(productMasterRequest.getId());
					if (lapParameter != null)
						BeanUtils.copyProperties(lapParameter, productMaster);
					break;
				case LAS_LOAN:
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
	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId) {

		return productMasterRepository.getListByUserId(userId);

	}

	@Override
	public ProductDetailsResponse getProductDetailsResponse(Long userId) {
		// TODO Auto-generated method stub
		
		List<ProductMaster> productMasterList=productMasterRepository.getUserProductList(userId);
		ProductMaster productMaster = null;
		ProductDetailsResponse productDetailsResponse=new ProductDetailsResponse();
		if(productMasterList.size() > 0)
		{
			productMaster=productMasterList.get(0);
		}
		if(productMaster!=null)
		{
			productDetailsResponse.setProductId(productMaster.getProductId());
			productDetailsResponse.setProductMappingId(productMaster.getId());
			productDetailsResponse.setMessage("Proposal Details Sent");
			productDetailsResponse.setStatus(HttpStatus.OK.value());
		}
		else
		{
			productDetailsResponse.setMessage("Something went wrong");
			productDetailsResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		}
		return productDetailsResponse;
	}

}