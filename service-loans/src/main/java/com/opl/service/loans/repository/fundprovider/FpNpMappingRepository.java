package com.opl.service.loans.repository.fundprovider;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundprovider.FpNpMapping;

public interface FpNpMappingRepository extends JpaRepository<FpNpMapping,Long>{

    @Query("from FpNpMapping where npUserId =:npUserId and isActive=1")
    public List<FpNpMapping> listOfNpCheckerAssignedByBo(@Param("npUserId")Long npUserId);

    @Query("from FpNpMapping where npUserId =:npUserId and isActive=1")
    public List<FpNpMapping> listOfNpCheckerAssignedByBoForPagination(Pageable pageable, @Param("npUserId")Long npUserId);

    @Query("from FpNpMapping where applicationId=:applicationId and fpProductId=:fpProductId and isActive=1")
    public FpNpMapping getNpCheckerUserIdBasedOnAppIdAndProdId(@Param("applicationId")Long applicationId,@Param("fpProductId")Long fpProductId);
}
