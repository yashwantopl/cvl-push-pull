package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMapping;
import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMappingTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsmeValueMappingRepository  extends JpaRepository<MsmeValueMapping, Long> {
    @Query("from MsmeValueMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public MsmeValueMapping getByFpProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("UPDATE MsmeValueMapping msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveMasterByFpProductId(@Param("fpProductId")Long fpProductId);

    List<MsmeValueMapping> findByFpProductIdAndIsActive(Long fpProductId, boolean b);
}
