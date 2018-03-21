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
	
	@Modifying
	@Query("update ProductMaster pm set pm.name =:name,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where pm.userId =:userId and pm.id=:productMappingId ")
	public int changeProductName(@Param("userId") Long userId,@Param("productMappingId") Long productMappingId,@Param("name") String name);
	
	@Modifying
	@Query("update ProductMaster pm set pm.isActive = :status,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where pm.userId =:userId  and pm.id=:productId")
	public int changeStatus(@Param("userId") Long userId,@Param("productId") Long productId,@Param("status") Boolean status);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.isActive = true")
	public List<ProductMaster> getUserProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userId =:userId  and productId in (1,2,15)")
	public List<ProductMaster> getUserCorporateProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId  and productId in (1,2,15)")
	public List<ProductMaster> getUserCorporateProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userId =:userId  and productId not in (1,2)")
	public List<ProductMaster> getUserRetailProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId  and productId not in (1,2)")
	public List<ProductMaster> getUserRetailProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and productId=:productId ")
	public List<ProductMaster> getUserProductListByProduct(@Param("userId") Long userId,@Param("productId") Integer productId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.id=:productId ")
	public ProductMaster getUserProduct(@Param("productId") Long productId,@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.id=:productId ")
	public ProductMaster getUserProductByOrgId(@Param("productId") Long productId,@Param("userOrgId") Long userOrgId);
	
	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMaster pm where pm.userId=:userId and pm.isActive = true")
	public List<ProductDetailsForSp> getListByUserId(@Param("userId") Long userId);

	@Query(value="select user_id, fp_name,name from fp_product_master pm where pm.fp_product_id=:productId", nativeQuery=true)
	public List<Object[]> findById(@Param("productId") Long fpProductId);
	
	@Query("select count(id) from ProductMaster pm where pm.id=:productId and pm.isParameterFilled=1 and pm.isActive = true")
	public Long checkParameterIsFilled(@Param("productId") Long productId);
	
	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMaster pm where pm.userId=:userId and pm.isActive = true and pm.isMatched=true")
	public List<ProductDetailsForSp> getMatchedAndActiveProduct(@Param("userId") Long userId);

	//get userid list by productid
	@Query("select DISTINCT  userId  from ProductMaster pm where pm.productId=:productId and pm.isActive = true")
	public List<Long> getUserIdListByProductId(@Param("productId") Integer productId);

	
	@Modifying
	@Query("update ProductMaster pm set pm.isMatched=true,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.userId =:userId and pm.isActive = true")
	public int setIsMatchProduct(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("from ProductMaster  where modifiedDate=(select max(modifiedDate) from  ProductMaster pm where pm.userId =:userId and pm.isActive = true)")
	public ProductMaster getLastAccessedProduct(@Param("userId") Long userId);
	
	@Query("select count(fpProductId) from ProductMaster pm where pm.id=:productId and pm.isActive = true")
	public Long getActiveProductsById(@Param("productId") Long productId);
	
	@Query(value = "SELECT name FROM fp_product_master WHERE fp_product_id=:fpProductId", nativeQuery=true)
	public String getFpProductName(@Param("fpProductId")Long fpProductId);
	
	@Query("select distinct(pm.productId) from ProductMaster pm where pm.isActive = true and pm.userOrgId =:orgId")
	public List<Integer> getProductsByOrgId(@Param("orgId")Long orgId);
	
}
