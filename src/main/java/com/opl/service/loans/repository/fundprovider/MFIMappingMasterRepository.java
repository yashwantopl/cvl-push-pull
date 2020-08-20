package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.opl.service.loans.domain.fundprovider.MFIMappingMaster;

@Repository
public interface MFIMappingMasterRepository extends JpaRepository<MFIMappingMaster, Long> {

    @Query("select o.orgId from MFIMappingMaster o where o.fpProductId = :fpProductId and o.isActive = true")
    public List<Long> getMfiByProductId(@Param("fpProductId") Long fpProductId);

    @Modifying
    @Query("update MFIMappingMaster isd set isd.isActive = false where isd.fpProductId =:fpProductId and isd.isActive = true")
    public int inActiveMappingByFpProductId(@Param("fpProductId") Long fpProductId);
}
