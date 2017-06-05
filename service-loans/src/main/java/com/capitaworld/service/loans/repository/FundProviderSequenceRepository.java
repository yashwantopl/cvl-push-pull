package com.capitaworld.service.loans.repository;

import com.capitaworld.service.loans.domain.FundProviderSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by dhaval on 02-Jun-17.
 */
public interface FundProviderSequenceRepository extends JpaRepository<FundProviderSequence,Long>{

    @Query("select sequenceNumber from FundProviderSequence where productId=:productId")
    public Long getFundProviderSequenceNumber(@Param("productId")Long productId);

    @Modifying(clearAutomatically = true)
    @Query("update FundProviderSequence set sequenceNumber=:sequenceNumber where productId=:productId")
    public int updateSequenceNumber(@Param("sequenceNumber")Long sequenceNumber,@Param("productId")Long productId);
}
