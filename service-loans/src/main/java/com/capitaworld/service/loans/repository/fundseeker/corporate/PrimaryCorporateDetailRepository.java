package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;

public interface PrimaryCorporateDetailRepository extends JpaRepository<PrimaryCorporateDetail, Long> {

    @Query("from PrimaryCorporateDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
    public PrimaryCorporateDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
                                                           @Param("userId") Long id);

    @Query("select pd.loanAmount from PrimaryCorporateDetail pd where pd.applicationId.id =:applicationId")
    public Double getLoanAmountByApplication(@Param("applicationId") Long applicationId);

    @Query("from PrimaryCorporateDetail pd where pd.applicationId.id =:applicationId")
    public PrimaryCorporateDetail findOneByApplicationIdId(@Param("applicationId") Long applicationId);

    @Modifying
    @Query("update PrimaryCorporateDetail pd set pd.turnOverPrevFinYear =:turnOverPrevFinYear and pd.turnOverCurrFinYearTillMonth =:turnOverCurrFinYearTillMonth and pd.profitCurrFinYear =:profitCurrFinYear  where pd.applicationId.id =:applicationId")
    public PrimaryCorporateDetail updatedFinancialFieldsForUniformProduct(@Param("applicationId") Long applicationId,@Param("turnOverPrevFinYear") Double turnOverPrevFinYear,@Param("turnOverCurrFinYearTillMonth") Double turnOverCurrFinYearTillMonth,@Param("profitCurrFinYear") Double profitCurrFinYear);
}


