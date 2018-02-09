package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FutureFinancialEstimatesDetail;

/**
 * @author Sanket
 *
 */
public interface FutureFinancialEstimatesDetailsRepository extends JpaRepository<FutureFinancialEstimatesDetail, Long> {

	@Query("select o from FutureFinancialEstimatesDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<FutureFinancialEstimatesDetail> listFutureFinancialEstimateDetailsFromAppId(@Param("id") Long id, @Param("userId") Long userId);

	@Modifying
	@Query("update FutureFinancialEstimatesDetail o set o.isActive = false where o.applicationId.id = :applicationId and o.isActive = true and o.id =:id")
	public int inactiveByApplicationAndId(@Param("applicationId") Long applicationId, @Param("id") Long id);
	
	@Modifying
	@Query("update FutureFinancialEstimatesDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);

	
	//Queries For Graph 
	
	/*//Financial Years
	@Query("select o.financialYear from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
	public List<String> getFinacialYears(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	//Pats
	@Query("select o.pat from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
	public List<Double> getPats(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	//Sales
    @Query("select o.sales from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
	public List<Double> getSales(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
    
    //ebidta
    @Query("select o.ebitda from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getEbidta(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

    //netWorth
    @Query("select o.netWorth from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getNetWorth(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

    //currentAsset
    @Query("select o.currentAssets from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getCurrentAsset(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

    //currentLiabilities
    @Query("select o.currentLiabilities from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getCurrentLiabilities(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

    //fixedAssets
    @Query("select o.fixedAssets from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getFixedAsset(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

    //longTermDebt
    @Query("select o.longTermDebt from FutureFinancialEstimatesDetail o where o.applicationId.id = :applicationId and o.applicationId.userId =:userId and o.isActive = true")
    public List<Double> getLongTermDebt(@Param("applicationId") Long applicationId, @Param("userId") Long userId);*/

}
