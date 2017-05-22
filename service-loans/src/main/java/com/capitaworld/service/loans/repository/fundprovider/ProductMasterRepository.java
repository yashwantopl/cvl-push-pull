package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMaster;
import com.capitaworld.service.loans.model.ProductDetailsForSp;

public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>{

	@Modifying
	@Query("update ProductMaster pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where pm.userId =:userId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.id=:productId and pm.isActive = true")
	public ProductMaster getUserProduct(@Param("productId") Long productId,@Param("userId") Long userId);
	
	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMaster pm where pm.userId=:userId and pm.isActive = true")
	public List<ProductDetailsForSp> getListByUserId(@Param("userId") Long userId);
	
}
