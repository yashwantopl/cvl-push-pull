package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.MsmeValueMapping;

public interface MsmeValueMappingRepository  extends JpaRepository<MsmeValueMapping, Long> {
    @Query("from MsmeValueMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public MsmeValueMapping getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE MsmeValueMapping msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    List<MsmeValueMapping> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
}
