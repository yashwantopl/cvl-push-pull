/**
 * 
 */
package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ProductMasterTemp;
import com.capitaworld.service.loans.model.ProductDetailsForSp;

/**
 * @author sanket
 *
 */
public interface ProductMasterTempRepository extends JpaRepository<ProductMasterTemp, Long>{

	@Query("SELECT o FROM ProductMasterTemp o WHERE o.id=:fpProductId")
	public ProductMasterTemp getProductMasterTemp(@Param("fpProductId")Long fpProductId);
	
	@Query("SELECT o.productId FROM ProductMasterTemp o WHERE o.id=:fpProductId")
	public Integer getProductIdById(@Param("fpProductId")Long fpProductId);
	
	@Modifying
	@Query("update ProductMasterTemp pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where pm.userId =:userId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId);
	
	@Modifying
	@Query("update ProductMasterTemp pm set pm.name =:name,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where  pm.id=:productMappingId ")
	public int changeProductName(@Param("userId") Long userId,@Param("productMappingId") Long productMappingId,@Param("name") String name);
	
	@Modifying
	@Query("update ProductMasterTemp pm set pm.isActive = :status,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where  pm.id=:productId")
	public int changeStatus(@Param("userId") Long userId,@Param("productId") Long productId,@Param("status") Boolean status);
	
	@Modifying
	@Query("update ProductMasterTemp pm set pm.isActive = :status, pm.activeInactiveJobId = NULL, pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where  pm.id=:productId")
	public int changeStatusAndActiveInactiveJobId(@Param("userId") Long userId,@Param("productId") Long productId,@Param("status") Boolean status);
	
	@Query("from ProductMasterTemp pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMasterTemp> getUserProductList(@Param("userId") Long userId);
	
	@Query("from ProductMasterTemp pm where pm.userOrgId =:userOrgId and pm.isActive = true")
	public List<ProductMasterTemp> getUserProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMasterTemp pm where pm.userId =:userId and pm.isApproved!=true and isCopied=false and productId in (1,2,15,16,17) order by pm.id desc")
	public List<ProductMasterTemp> getUserCorporateProductList(@Param("userId") Long userId);
	
	@Query("from ProductMasterTemp pm where pm.userOrgId =:userOrgId  and isCopied=false and productId in (1,2,15,16,17) order by pm.id desc")
	public List<ProductMasterTemp> getUserCorporateProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMasterTemp pm where pm.userId =:userId and pm.isApproved != true and pm.productId  IN (:productIds)")
	public List<ProductMasterTemp> getUserRetailProductList(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds);
	
	@Query("select pm from ProductMasterTemp pm where pm.userOrgId =:userOrgId and pm.isCopied=false and pm.productId  IN (:productIds)")
	public List<ProductMasterTemp> getUserRetailProductListByOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds);
	
	@Query("from ProductMasterTemp pm where pm.userId =:userId and productId=:productId ")
	public List<ProductMasterTemp> getUserProductListByProduct(@Param("userId") Long userId,@Param("productId") Integer productId);
	
	@Query("from ProductMasterTemp pm where pm.userId =:userId and pm.id=:productId ")
	public ProductMasterTemp getUserProduct(@Param("productId") Long productId,@Param("userId") Long userId);
	
	public ProductMasterTemp findByIdAndIsActive(Long id, Boolean isActive);
	
	@Query("from ProductMasterTemp pm where pm.userOrgId =:userOrgId and pm.id=:productId ")
	public ProductMasterTemp getUserProductByOrgId(@Param("productId") Long productId,@Param("userOrgId") Long userOrgId);
	
	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMasterTemp pm where pm.userId=:userId and pm.isActive = true")
	public List<ProductDetailsForSp> getListByUserId(@Param("userId") Long userId);

	@Query(value="select user_id, fp_name,name from fp_product_master pm where pm.fp_product_id=:productId", nativeQuery=true)
	public List<Object[]> findById(@Param("productId") Long fpProductId);
	
	@Query("select count(id) from ProductMasterTemp pm where pm.id=:productId and pm.isParameterFilled=1 and pm.isActive = true")
	public Long checkParameterIsFilled(@Param("productId") Long productId);
	
	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMasterTemp pm where pm.userId=:userId and pm.isActive = true and pm.isMatched=true")
	public List<ProductDetailsForSp> getMatchedAndActiveProduct(@Param("userId") Long userId);

	//get userid list by productid
	@Query("select DISTINCT  userId  from ProductMasterTemp pm where pm.productId=:productId and pm.isActive = true")
	public List<Long> getUserIdListByProductId(@Param("productId") Integer productId);

	
	@Modifying
	@Query("update ProductMasterTemp pm set pm.isMatched=true,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.id =:id and pm.userId =:userId and pm.isActive = true")
	public int setIsMatchProduct(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("from ProductMasterTemp  where modifiedDate=(select max(modifiedDate) from  ProductMasterTemp pm where pm.userId =:userId and pm.isActive = true)")
	public ProductMasterTemp getLastAccessedProduct(@Param("userId") Long userId);
	
	@Query("select count(fpProductId) from ProductMasterTemp pm where pm.id=:productId and pm.isActive = true")
	public Long getActiveProductsById(@Param("productId") Long productId);
	
	@Query(value = "SELECT name FROM fp_product_master WHERE fp_product_id=:fpProductId", nativeQuery=true)
	public String getFpProductName(@Param("fpProductId")Long fpProductId);
	
	@Query("select distinct(pm.productId) from ProductMasterTemp pm where pm.isActive = true and pm.userOrgId =:orgId")
	public List<Integer> getProductsByOrgId(@Param("orgId")Long orgId);


	@Modifying
	@Query("update ProductMasterTemp pm set pm.statusId=:statusId where pm.id =:id")
	public int updateStatusToInProgress(@Param("id")Long id,@Param("statusId")Integer statusId);

	@Query("select pm from ProductMasterTemp pm where pm.userOrgId =:userOrgId and pm.isCopied=false and pm.productId  IN (:productIds) and isActive =:status")
	public List<ProductMasterTemp> getProductListByUserOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds,@Param("status") boolean status);

	@Query("select pm from ProductMasterTemp pm where pm.userOrgId =:userOrgId and pm.isCopied=false and pm.productId  IN (:productIds)")
	public List<ProductMasterTemp> getProductListByUserOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds);

	@Query("select pm from ProductMasterTemp pm where pm.userId =:userId and pm.isCopied=false and pm.productId  IN (:productIds) and isActive =:status")
	public List<ProductMasterTemp> getProductListByUserId(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds,@Param("status") boolean status);

	@Query("select pm from ProductMasterTemp pm where pm.userId =:userId and pm.isCopied=false and pm.productId  IN (:productIds)")
	public List<ProductMasterTemp> getProductListByUserId(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds);

	@Query("from ProductMasterTemp pm where pm.userOrgId =:userOrgId  and pm.isCopied=false and pm.productId in (1,2,15,16,17) and pm.businessTypeId=:businessTypeId order by pm.id desc")
	public List<ProductMasterTemp> getUserCorporateProductListByOrgIdByBusinessTypeId(@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
	
	@Query(value =  "SELECT pm.fp_product_id,pm.product_id,pm.user_id,pm.fp_name,pm.name,pm.is_parameter_filled,pm.is_active,pm.product_code,pm.user_org_id,pm.score_model_id,pm.business_type_id,pm.is_approved,pm.is_deleted,pm.is_copied,pm.is_edit,pm.status_id,pm.job_id,pm.fin_type_id,pm.campaign_type,pm.gst_type,pm.wc_renewal_status,pm.created_date FROM `loan_application`.fp_product_master_temp pm WHERE pm.user_org_id =:userOrgId AND is_copied = FALSE AND product_id IN (1,2,15,16,17) AND pm.business_type_id=:businessTypeId ORDER BY pm.fp_product_id DESC",nativeQuery = true)
	public List<Object[]> getProductListByBusinessTypeId(@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
}
