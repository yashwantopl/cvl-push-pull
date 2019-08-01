package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiExpenseExpectedIncomeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MfiExpenseExpectedIncomeDetailRepository extends JpaRepository<MfiExpenseExpectedIncomeDetails, Long> {

    @Query("from MfiExpenseExpectedIncomeDetails mfe where mfe.applicationId =:applicationId and mfe.isActive = true ")
   public MfiExpenseExpectedIncomeDetails findByApplicationId(@Param("applicationId") Long applicationId);
}
