package com.capitaworld.service.loans.repository.fundprovider;

import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.List;

public interface ProposalDetailsRepository {

    public List<BigInteger> getApplicationsBasedOnFpProductId(Long fpProductId);

}
