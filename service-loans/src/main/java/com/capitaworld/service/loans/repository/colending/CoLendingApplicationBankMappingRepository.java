package com.capitaworld.service.loans.repository.colending;

import com.capitaworld.service.loans.domain.colending.CoLendingApplicationBankMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dhaval.panchal on 27-Aug-19.
 */
public interface CoLendingApplicationBankMappingRepository extends JpaRepository<CoLendingApplicationBankMapping,Long>{

    public List<Integer> findListByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
}
