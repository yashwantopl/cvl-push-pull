package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.ProductConditionResponse;
import com.capitaworld.service.loans.model.ProductMasterRequest;
import com.capitaworld.service.loans.model.ProductParameterRequest;
import com.capitaworld.service.loans.model.corporate.AddProductRequest;

import java.util.List;

public interface ProductMasterBodmasService {

    public Long saveOrUpdate(AddProductRequest addProductRequest, Long userOrgId);

    public boolean saveCondition(ProductParameterRequest productParameterRequest);

    public List<ProductMasterRequest> getList(Long userId, Long userOrgId);

    public List<ProductMasterRequest> getListByUserType(Long userId, Integer userType, Integer stage, Integer status, Long userOrgId);

    public List<ProductConditionResponse> getConditionsByProductId(Long productId);
}
