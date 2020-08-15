package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.NbfcRatioMapping;

public interface NbfcRatioMappingRepository extends JpaRepository<NbfcRatioMapping,Long> {

    public List<NbfcRatioMapping> findByFpProductIdAndIsActive(Long fpProductId, boolean isActive);

    @Modifying
    @Query("UPDATE NbfcRatioMapping nb SET nb.isActive=FALSE WHERE nb.fpProductId=:fpProductId")
    public int inActiveByFpProductId(@Param("fpProductId")Long fpProductId);
    
    @Query("select nb.ratioId from NbfcRatioMapping nb where nb.fpProductId=:fpProductId and isActive=true")
    public List<Long> getIdsByFpProductId(@Param("fpProductId") Long fpProductId);
}
