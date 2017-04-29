package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.repository.fundprovider.ProductMasterRepository;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
public class ProductMasterServiceImpl implements ProductMasterService {
	private static final Logger logger = LoggerFactory.getLogger(ProductMasterServiceImpl.class.getName());

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
					//int type = CommonUtils.LoanType.valueOf(productMasterRequest.getProductId().toString()).ordinal();
					LoanType loanType=LoanType.getType(Integer.parseInt(productMasterRequest.getProductId().toString()));
					if(loanType==null)
						continue;
					switch (loanType) {
					case TERM_LOAN:
						productMaster = new TermLoanParameter();
						break;

					default:
						break;
				}/* catch (IllegalArgumentException ex) {
					logger.error("not having  this product "+productMasterRequest.getProductId().toString());
					return false;
				}*/

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
