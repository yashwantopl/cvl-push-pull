package com.capitaworld.service.loans.repository.colending.impl;

import com.capitaworld.service.loans.repository.colending.CoLendingFlowRepository;
import com.capitaworld.service.loans.service.colending.impl.CoLendingFlowServiceFlowServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by dhaval.panchal on 14-Aug-19.
 */
@Repository
public class CoLendingFlowRepositoryImpl implements CoLendingFlowRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(CoLendingFlowRepositoryImpl.class);

    public Object[] getStageAndStatus(Long userId) {
        try {
            return  (Object[]) entityManager
                    .createNativeQuery("SELECT l.stage_id,l.status FROM connect.connect_log l WHERE l.user_id =:userId ORDER BY l.id DESC LIMIT 1")
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Error while getting Stage and Status");
        }
        return null;
    }
}
