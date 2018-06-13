package com.capitaworld.service.loans.repository.fundprovider.impl;

import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

public class ProposalDetailsRepositoryImpl implements ProposalDetailsRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BigInteger> getApplicationsBasedOnFpProductId(Long fpProductId) {

        List<BigInteger> applicationIdList = (List) entityManager
                .createNativeQuery("SELECT application_id FROM proposal_details WHERE fpProductId =:fpProductId and is_active = 1")
                .setParameter("fpProductId", fpProductId).getResultList();
        return applicationIdList;
    }
}
