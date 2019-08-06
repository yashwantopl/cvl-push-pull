package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiExpenseExpectedIncomeDetails;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MfiExpenseExpectedIncomeDetailRepository extends JpaRepository<MfiExpenseExpectedIncomeDetails, Long> {

   @Query("from MfiExpenseExpectedIncomeDetails mfe where mfe.applicationId =:applicationId and mfe.type =:type and mfe.isActive = true ")
   public MfiExpenseExpectedIncomeDetails findByApplicationIdAndType(@Param("applicationId") Long applicationId,@Param("type") Integer type);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq(mfe.applicationId,mfe.totalMonthlyIncomeForFamily,mfe.totalExpense,mfe.netSaving,mfe.monthlyIncome,mfe.cashFlow) from MfiExpenseExpectedIncomeDetails mfe where mfe.applicationId =:applicationId and mfe.isActive = true and mfe.type =:type")
    public List<MfiLoanAssessmentDetailsReq> findCashFlowAssessment(@Param("applicationId") Long applicationId, @Param("type") Integer type);

    @Modifying
    @Query("update MfiExpenseExpectedIncomeDetails n set n.isActive = false where n.applicationId =:applicationId")
    public int inactiveExpene(@Param("applicationId") Long applicationId);
}
