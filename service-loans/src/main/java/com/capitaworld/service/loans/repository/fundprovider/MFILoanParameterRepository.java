package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.MFILoanParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MFILoanParameterRepository extends JpaRepository<MFILoanParameter, Long> {

    @Query("from MFILoanParameter pp where pp.fpProductId.id =:id ")
    public MFILoanParameter getByID(@Param("id") Long id);
}
