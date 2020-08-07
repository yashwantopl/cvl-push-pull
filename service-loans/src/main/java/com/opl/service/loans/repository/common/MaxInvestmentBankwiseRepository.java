package com.opl.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.common.MaxInvestmentBankwise;

public interface MaxInvestmentBankwiseRepository extends JpaRepository<MaxInvestmentBankwise,Long> {

    @Query(value="SELECT * FROM max_investment_bankwise WHERE modified_date>=CURRENT_DATE AND is_active=TRUE and code=:code",nativeQuery = true)
    public MaxInvestmentBankwise getInvestmentSizeByBankCode(@Param("code")String code);
}
