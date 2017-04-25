package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;

public interface ProductMasterService {
	public boolean saveOrUpdate(ProductMaster productMaster);
	
	public ProductMaster getProductMaster(Long id);
}
