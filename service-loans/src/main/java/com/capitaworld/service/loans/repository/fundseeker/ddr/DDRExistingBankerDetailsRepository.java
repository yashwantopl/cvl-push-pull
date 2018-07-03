package com.capitaworld.service.loans.repository.fundseeker.ddr;


import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRExistingBankerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DDRExistingBankerDetailsRepository extends JpaRepository<DDRExistingBankerDetails, Long>{

    @Query("select dd from DDRExistingBankerDetails dd where dd.id =:id and dd.isActive = true")
    public DDRExistingBankerDetails getByIdAndIsActive(@Param("id") Long id);

    @Query("select dd from DDRExistingBankerDetails dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
    public List<DDRExistingBankerDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
    
    public DDRExistingBankerDetails findByFinancialArrangementIdAndIsActive(Long financialArrangementId, Boolean isActive);
}
