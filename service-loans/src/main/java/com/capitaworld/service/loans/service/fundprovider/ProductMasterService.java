package com.capitaworld.service.loans.service.fundprovider;

import java.io.IOException;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.model.CommonResponse;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;

public interface ProductMasterService {
	public List<CommonResponse>  saveOrUpdate(MultipleFpPruductRequest productsRequest);
	
	public ProductMaster getProductMaster(Long id);
	
	public List<ProductMasterRequest> getList(Long userId);
	
	public String getUserNameByApplicationId(Long productId,Long userId);
	
	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId);

	public Object[] getUserDetailsByPrductId(Long fpMappingId);

	public ProductDetailsResponse getProductDetailsResponse(Long userId);
	
	public FpProductDetails getProductDetails(Long productMappingId) throws Exception;
	
	public boolean isSelfView(Long fpProductId,Long userId);
	
	public boolean isProductMatched(Long userId,MultipleFpPruductRequest multipleFpPruductRequest) throws IOException;
	
	public int setIsMatchProduct(Long id,  Long userId);
}
