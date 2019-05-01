package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.LoanArrangementMapping;
import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoanArrangementMappingRepository  extends JpaRepository<LoanArrangementMapping, Long> {
    @Query("from LoanArrangementMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public LoanArrangementMapping getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE LoanArrangementMapping msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    @Query("select tp.loanArrangementId from LoanArrangementMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public List<Integer> getIdsByFpProductId(@Param("fpProductId") Long fpProductId);
    
    List<LoanArrangementMapping> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
}
