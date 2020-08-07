package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.ConstitutionMapping;

public interface FpConstitutionMappingRepository  extends JpaRepository<ConstitutionMapping, Long> {
    @Query("from ConstitutionMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public ConstitutionMapping getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE ConstitutionMapping msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    List<ConstitutionMapping> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
    
    
    @Query("select tp.constitutionId from ConstitutionMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public List<Integer> getIdsByFpProductId(@Param("fpProductId") Long fpProductId);
}
