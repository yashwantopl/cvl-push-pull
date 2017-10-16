package com.capitaworld.service.loans.service.fundprovider;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.model.FpProductDetails;
import com.capitaworld.service.loans.model.MultipleFpPruductRequest;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.common.ChatDetails;
import com.capitaworld.service.loans.model.common.ProposalList;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.model.retail.RetailProduct;
public interface ProductMasterService {
	public Boolean saveOrUpdate(AddProductRequest addProductRequest);

	public ProductMaster getProductMaster(Long id);

	public List<ProductMasterRequest> getList(Long userId);
	
	public List<Object> getListByUserType(Long userId,Integer userType);

	public String getUserNameByApplicationId(Long productId, Long userId);

	public List<ProductDetailsForSp> getProductDetailsByUserIdList(Long userId);

	public Object[] getUserDetailsByPrductId(Long fpMappingId);

	public ProductDetailsResponse getProductDetailsResponse(Long userId);

	public FpProductDetails getProductDetails(Long productMappingId) throws Exception;

	public boolean isSelfView(Long fpProductId, Long userId);
	
	public Boolean changeStatus(Long fpProductId,Boolean status, Long userId);

	public boolean isProductMatched(Long userId, MultipleFpPruductRequest multipleFpPruductRequest) throws IOException;

	public int setIsMatchProduct(Long id, Long userId);

	public JSONObject checkParameterIsFilled(Long productId);
	
	public Boolean saveCorporate(CorporateProduct corporateProduct);
	
	public Boolean saveRetail(RetailProduct retailProduct);
	
	public ProductMasterRequest lastAccessedProduct(Long userId);
	
	public List<ChatDetails> getChatListByFpMappingId(Long applicationId);
	
	public boolean isProductActive(Long productId);

	
}
