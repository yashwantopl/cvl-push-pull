package com.opl.service.loans.repository.fundprovider;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opl.mudra.api.loans.model.ProductDetailsForSp;
import com.opl.service.loans.domain.fundprovider.ProductMaster;
import com.opl.service.loans.domain.fundprovider.ProductMasterTemp;

@Repository
public interface ProductMasterRepository extends JpaRepository<ProductMaster, Long>{

	@Modifying
	@Query("update ProductMaster pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where pm.userId =:userId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId);
	
	@Modifying
	@Query("update ProductMaster pm set pm.name =:name,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where  pm.id=:productMappingId ")
	public int changeProductName(@Param("userId") Long userId,@Param("productMappingId") Long productMappingId,@Param("name") String name);
	
	@Modifying
	@Query("update ProductMaster pm set pm.isActive = :status,pm.modifiedDate = NOW(),pm.modifiedBy =:userId  where  pm.id=:productId")
	public int changeStatus(@Param("userId") Long userId,@Param("productId") Long productId,@Param("status") Boolean status);
	
	@Modifying
	@Query("update ProductMaster pm set pm.isActive = :status, pm.activeInactiveJobId = NULL, pm.modifiedDate = NOW(), pm.modifiedBy =:userId where pm.id=:productId")
	public int changeStatusAndActiveInactiveJobId(@Param("userId") Long userId,@Param("productId") Long productId,@Param("status") Boolean status);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.isActive = true")
	public List<ProductMaster> getUserProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId")
	public List<ProductMaster> getUserProductActiveInActiveListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userId =:userId  and productId in (1,2) order by pm.id desc")
	public List<ProductMaster> getUserCorporateProductList(@Param("userId") Long userId);
	
	@Query("from ProductMaster pm where pm.userId =:userId  and pm.productId =:productId and pm.businessTypeId =:businessTypeId")
	public List<ProductMaster> getUserCorporateProductListByBusinessTypeIdAndProductId(@Param("userId") Long userId,@Param("businessTypeId")Long businessTypeId,@Param("productId") Integer productId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId  and productId in (1,2) order by pm.id desc")
	public List<ProductMaster> getUserCorporateProductListByOrgId(@Param("userOrgId") Long userOrgId);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId  and pm.productId =:productId and pm.businessTypeId =:businessTypeId")
	public List<ProductMaster> getUserCorporateProductListByOrgIdAndBusinessTypeIdAndProductId(@Param("userOrgId") Long userOrgId,@Param("businessTypeId")Long businessTypeId,@Param("productId") Integer productId);
	
	@Query("from ProductMaster pm where pm.userId =:userId  and productId  in (:productIds)")
	public List<ProductMaster> getUserRetailProductList(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds);
	
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId  and productId  in  (:productIds)")
	public List<ProductMaster> getUserRetailProductListByOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds);
	
	@Query("from ProductMaster pm where pm.userId =:userId and productId=:productId ")
	public List<ProductMaster> getUserProductListByProduct(@Param("userId") Long userId,@Param("productId") Integer productId);
	
	@Query("from ProductMaster pm where pm.userId =:userId and pm.id=:productId ")
	public ProductMaster getUserProduct(@Param("productId") Long productId,@Param("userId") Long userId);
	
	public ProductMaster findByIdAndIsActive(Long id, Boolean isActive);
	
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

	@Query("select new com.capitaworld.service.loans.model.ProductDetailsForSp(pm.id,pm.productId,pm.name)  from ProductMaster pm where pm.userId=:userId and pm.isMatched=true")
	public List<ProductDetailsForSp> getMatchedAndActiveInActiveProduct(@Param("userId") Long userId);
	
	public Long countByUserIdAndIsMatched(Long userId, Boolean isMatched);
	
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
	
	@Query(value="SELECT IF (pm.modified_date IS NULL,pm.created_date,pm.modified_date) FROM loan_application.fp_product_master pm WHERE pm.fp_product_id =:fpProductId and pm.is_active = true",nativeQuery = true)
	public Date getUpdatedDate(@Param("fpProductId")Long fpProductId);

	@Query("from ProductMaster pm where pm.id=:id")
	public ProductMaster getById(@Param("id") Long id);

	/**
	 * @author Dhaval
	 * @param userOrgId
	 * @param businessTypeId
	 * @return
	 */
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId AND pm.businessTypeId=:businessTypeId")
	public List<ProductMaster> getUserProductActiveList(@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
	
	/**
	 * @author vijay.chauhan
	 * @param productId
	 * @param userOrgId
	 * @param businessTypeId
	 * @return
	 */
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.id=:productId and pm.businessTypeId=:businessTypeId")
	public ProductMaster getUserProductByOrgId(@Param("productId") Long productId,@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
	
	/**
	 * @author vijay.chauhan
	 * @param userOrgId
	 * @param businessTypeId
	 * @return
	 */
	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.isActive = true and pm.businessTypeId=:businessTypeId")
	public List<ProductMaster> getUserProductListByOrgId(@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
	
	/**
	 * @author vijay.chauhan
	 * @param userId
	 * @param businessTypeId
	 * @return
	 */
	@Query("from ProductMaster pm where pm.userId =:userId and pm.isActive = true and pm.businessTypeId=:businessTypeId")
	public List<ProductMaster> getUserProductList(@Param("userId") Long userId,@Param("businessTypeId") Long businessTypeId);
	
	/**
	 * @author vijay.chauhan
	 * @param productId
	 * @param userId
	 * @param businessTypeId
	 * @return
	 */
	@Query("from ProductMaster pm where pm.userId =:userId and pm.id=:productId  and pm.businessTypeId=:businessTypeId")
	public ProductMaster getUserProduct(@Param("productId") Long productId,@Param("userId") Long userId,@Param("businessTypeId") Long businessTypeId);
	
	public ProductMaster findByIdAndIsActiveAndBusinessTypeId(Long id, Boolean isActive,Long businessTypeId);

	@Query("from ProductMaster pm where pm.userId =:userOrgId  and productId in (:productIds) and isActive =:status")
	public List<ProductMaster> getProductListByUserOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds,@Param("status") boolean status);

	@Query("from ProductMaster pm where pm.userId =:userId  and productId in (:productIds) and isActive =:status")
	public List<ProductMaster> getProductListByUserId(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds,@Param("status") boolean status);

	@Query("from ProductMaster pm where pm.userId =:userOrgId  and productId in (:productIds) ")
	public List<ProductMaster> getProductListByUserOrgId(@Param("userOrgId") Long userOrgId,@Param("productIds") List<Integer> productIds);

	@Query("from ProductMaster pm where pm.userId =:userId  and productId in (:productIds)")
	public List<ProductMaster> getProductListByUserId(@Param("userId") Long userId,@Param("productIds") List<Integer> productIds);
	
	@Query(value="select bureau_version from fp_product_master pm where pm.fp_product_id=:productId", nativeQuery=true)
	public Integer findBureauVersionByFpProductId(@Param("productId") Long fpProductId);

	@Query("from ProductMaster pm where pm.userOrgId =:userOrgId and pm.businessTypeId=:businessTypeId order by pm.id desc")
	public List<ProductMaster> getUserProductListByOrgIdByBusinessTypeId(@Param("userOrgId") Long userOrgId,@Param("businessTypeId") Long businessTypeId);
	
	@Query("SELECT o.productId FROM ProductMaster o WHERE o.id=:fpProductId")
	public Integer getProductIdById(@Param("fpProductId")Long fpProductId);
	
	@Query(value = "SELECT COUNT(fp_product_id) FROM loan_application.fp_product_master WHERE is_active = TRUE AND is_parameter_filled = TRUE AND (product_id=:productId) AND business_type_id =:businessTypeId AND user_org_id =:userOrgId  AND wc_renewal_status =:wcRenewalStatus AND (campaign_type=2 OR campaign_type=3)",nativeQuery = true)
	public Long getWCRenewalProductsCount(@Param("productId")Integer productId, @Param("userOrgId") Long userOrgId, @Param("businessTypeId") Long businessTypeId, @Param("wcRenewalStatus") Integer wcRenewalStatus);
	

	@Query("from ProductMaster pm where pm.id =:fpProductId and pm.isActive = false")
	public ProductMaster checkParameterIsactive(@Param("fpProductId")Long fpProductId);

	@Query(value="select * from scoring_sidbi.scoring_model pm where pm.id=:scoreModelId and pm.is_active = false", nativeQuery=true)
	public String checkParameterInScoringIsActive(@Param("scoreModelId")Long scoreModelId);

	@Query("from ProductMasterTemp pm where pm.id =:fpProductId and pm.isActive = false")
	public ProductMasterTemp checkParameterIsactiveForPanding(@Param("fpProductId")Long fpProductId);
	

	@Query(value="select * from scoring_sidbi.scoring_model_temp pm where pm.id=:scoreModelId and pm.is_active = false", nativeQuery=true)
	public String checkcoringIsActiveForPanding(@Param("scoreModelId")Long scoreModelId);
}
