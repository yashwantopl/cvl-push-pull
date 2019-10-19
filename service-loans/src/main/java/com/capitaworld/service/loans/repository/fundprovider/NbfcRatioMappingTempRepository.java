package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMappingTemp;
import com.capitaworld.service.loans.domain.fundprovider.NbfcRatioMappingTemp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NbfcRatioMappingTempRepository extends JpaRepository<NbfcRatioMappingTemp,Long> {

    public List<MsmeValueMappingTemp> findByFpProductIdAndIsActive(Long fpProductId, boolean isActive);

    @Modifying
    @Query("UPDATE NbfcRatioMappingTemp nb SET nb.isActive=FALSE WHERE nb.fpProductId=:fpProductId")
    public int inActiveTempByFpProductId(@Param("fpProductId")Long fpProductId);
    
    @Modifying
    @Query("UPDATE NbfcRatioMappingTemp nb SET nb.isActive=FALSE WHERE nb.ratioId=:ratioId")
    public int inActiveTempByRatioId(@Param("ratioId")Long ratioId);
    
    @Query("select nb.ratioId from NbfcRatioMappingTemp nb where nb.fpProductId=:fpProductId and isActive=true")
    public List<Long> getTempIdsByFpProductId(@Param("fpProductId") Long fpProductId);
}
