package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ProposalDetailsRepository extends JpaRepository<ProposalDetails,Long>{

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and fpProductId=:fpProductId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchIdAndFpProductId(@Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query("SELECT pd.applicationId FROM ProposalDetails pd WHERE branchId =:branchId and isActive = 1")
    public List<Long> getApplicationsBasedOnBranchId(@Param("branchId") Long branchId);

}
