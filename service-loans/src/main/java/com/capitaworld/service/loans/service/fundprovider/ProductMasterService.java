package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;

public interface ProductMasterService {
	public boolean saveOrUpdate(MultipleFpPruductRequest productsRequest);
	
	public ProductMaster getProductMaster(Long id);
}
