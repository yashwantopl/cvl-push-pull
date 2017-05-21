package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductMasterRequest;

public interface ProductMasterService {
	public List<CommonResponse>  saveOrUpdate(MultipleFpPruductRequest productsRequest);
	
	public ProductMaster getProductMaster(Long id);
	
	public List<ProductMasterRequest> getList(Long userId);
	
	public String getUserNameByApplicationId(Long productId,Long userId);
	
	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId);
	
}
