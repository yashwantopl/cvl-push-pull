package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opl.service.loans.domain.fundprovider.MFIMappingTemp;

@Repository
public interface MFIMappingTempRepository extends JpaRepository<MFIMappingTemp, Long> {

    @Query("select o.orgId from MFIMappingTemp o where o.fpProductId = :fpProductId and o.isActive = true")
    public List<Long> getMfiByProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("update MFIMappingTemp isd set isd.isActive = false where isd.fpProductId =:fpProductId and isd.isActive = true")
    public int inActiveMappingByFpProductId(@Param("fpProductId") Long fpProductId);
}
