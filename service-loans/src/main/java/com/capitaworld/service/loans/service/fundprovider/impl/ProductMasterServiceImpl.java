package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.CarLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.HomeLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.LapParameter;
import com.capitaworld.service.loans.domain.fundprovider.LasParameter;
import com.capitaworld.service.loans.domain.fundprovider.PersonalLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {
	
	@Autowired
	private ProductMasterRepository productMasterRepository;

	@Override
	public boolean saveOrUpdate(MultipleFpPruductRequest productMasters) {
		// TODO Auto-generated method stub

		try {
			for (Map<String, Object> obj : productMasters.getDataList()) {
				ProductMasterRequest productMasterRequest = (ProductMasterRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, ProductMasterRequest.class);
				ProductMaster productMaster = null;
					LoanType loanType=LoanType.getType(Integer.parseInt(productMasterRequest.getProductId().toString()));
					if(loanType==null)
						continue;
					switch (loanType) {
					case WORKING_CAPITAL:
						productMaster = new WorkingCapitalParameter();
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
				productMaster.setUserId(productMasters.getUserId());
				productMaster.setCreatedBy(productMasters.getUserId());
				productMaster.setCreatedDate(new Date());
				productMaster.setModifiedBy(productMasters.getUserId());
				productMaster.setModifiedDate(new Date());
				productMaster.setIsActive(true);
				productMasterRepository.save(productMaster);
			}
			return true;
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ProductMaster getProductMaster(Long id) {
		// TODO Auto-generated method stub
		return productMasterRepository.findOne(id);
	}

}
