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
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
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

	@Override
	public List<CommonResponse> saveOrUpdate(MultipleFpPruductRequest productMasters) {
		// TODO Auto-generated method stub
		// inactive old record before saving new record
		productMasterRepository.inActive((CommonUtils.isObjectNullOrEmpty(productMasters.getClientId())
				? productMasters.getUserId() : productMasters.getClientId()));
		WorkingCapitalParameter capitalParameter = null;

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
					break;
				case HOME_LOAN:
					productMaster = new HomeLoanParameter();
					break;
				case CAR_LOAN:
					productMaster = new CarLoanParameter();
					break;
				case PERSONAL_LOAN:
					productMaster = new PersonalLoanParameter();
					break;
				case LAP_LOAN:
					productMaster = new LapParameter();
					break;
				case LAS_LOAN:
					productMaster = new LasParameter();
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
	public Object[] getUserDetailsByPrductId(Long fpMappingId) {
		// TODO Auto-generated method stub
		Object[] values = productMasterRepository.getUserDetailsByMappingId(fpMappingId);
		System.out.println(values);
		return values;
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