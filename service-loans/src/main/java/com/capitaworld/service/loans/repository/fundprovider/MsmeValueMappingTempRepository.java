package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMappingTemp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsmeValueMappingTempRepository extends JpaRepository<MsmeValueMappingTemp,Long> {

    public List<MsmeValueMappingTemp> findByFpProductIdAndIsActive(Long fpProductId, boolean isActive);

    @Modifying
    @Query("UPDATE MsmeValueMappingTemp msme SET msme.isActive=FALSE WHERE msme.fpProductId=:fpProductId")
    public int inActiveTempByFpProductId(@Param("fpProductId")Long fpProductId);
}
