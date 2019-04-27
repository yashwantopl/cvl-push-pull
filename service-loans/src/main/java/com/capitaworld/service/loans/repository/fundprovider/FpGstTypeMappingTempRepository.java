package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.FpGstTypeMappingTemp;

public interface FpGstTypeMappingTempRepository  extends JpaRepository<FpGstTypeMappingTemp, Long> {
    @Query("from FpGstTypeMappingTemp tp where tp.fpProductId=:fpProductId and isActive=true")
    public FpGstTypeMappingTemp getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE FpGstTypeMappingTemp msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    List<FpGstTypeMappingTemp> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
    
    
    @Query("select tp.gstTypeId from FpGstTypeMappingTemp tp where tp.fpProductId=:fpProductId and isActive=true")
    public List<Integer> getIdsByFpProductId(@Param("fpProductId") Long fpProductId);
}
