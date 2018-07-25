package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.MsmeValueMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MsmeValueMappingRepository  extends JpaRepository<MsmeValueMapping, Long> {
    @Query("from MsmeValueMapping tp where tp.fpProductId=:fpProductId and isActive=true")
    public MsmeValueMapping getByFpProductId(@Param("fpProductId") Long fpProductId);

}
