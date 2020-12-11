package com.opl.service.loans.repository;

import com.opl.service.loans.domain.ProposedVehicleIncomeExpenseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public interface ProposedVehicleIncomeExpenseDetailRepository extends JpaRepository<ProposedVehicleIncomeExpenseDetail,Long> {

    public ProposedVehicleIncomeExpenseDetail findByApplicationIdAndIsActiveIsTrue(Long applicationId);

}
