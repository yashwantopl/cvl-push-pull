package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.MFILoanParameterTemp;

public interface MFILoanParameterTempRepository extends JpaRepository<MFILoanParameterTemp, Long> {

    @Query("from MFILoanParameterTemp pp where pp.fpProductId.id =:id ")
    public MFILoanParameterTemp getByID(@Param("id") Long id);

    @Query("select o from MFILoanParameterTemp o where o.fpProductMappingId =:fpProductMappingId and isCopied=false")
    public MFILoanParameterTemp getMFIParameterTempByFpProductMappingId(@Param("fpProductMappingId")Long fpProductId);
}
