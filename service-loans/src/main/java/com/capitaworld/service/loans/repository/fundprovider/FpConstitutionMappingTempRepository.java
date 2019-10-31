package com.capitaworld.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundprovider.ConstitutionMapping;
import com.capitaworld.service.loans.domain.fundprovider.ConstitutionMappingTemp;
import com.capitaworld.service.loans.domain.fundprovider.FpGstTypeMapping;

public interface FpConstitutionMappingTempRepository  extends JpaRepository<ConstitutionMappingTemp, Long> {
    @Query("from ConstitutionMappingTemp tp where tp.fpProductId=:fpProductId and isActive=true")
    public ConstitutionMappingTemp getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE ConstitutionMappingTemp msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    List<ConstitutionMappingTemp> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
    
    
    @Query("select tp.constitutionId from ConstitutionMappingTemp tp where tp.fpProductId=:fpProductId and isActive=true")
    public List<Integer> getIdsByFpProductId(@Param("fpProductId") Long fpProductId);
}
