package com.capitaworld.service.loans.repository.colending.impl;

import com.capitaworld.service.loans.repository.colending.CoLendingFlowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;

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

    public Object[] getRatioNbfcBankProduct(Long applicationId){
        try {
            return  (Object[]) entityManager
                    .createNativeQuery("SELECT ratio_id,tenure,nbfc_ratio,bank_ratio FROM nbfc_ratio_mapping n "
                                + "INNER JOIN fp_co_lending_ratio ra ON ra.id=n.ratio_id AND ra.is_active=TRUE AND ra.is_proposal_active=TRUE "
                                + "WHERE n.fp_product_id IN (SELECT fp_product_id FROM proposal_details WHERE application_id=:applicationId) "
                                + "AND n.is_active=TRUE GROUP BY n.ratio_id HAVING COUNT(n.ratio_id)>1 ORDER BY n.id ASC LIMIT 1")
                    .setParameter("applicationId", applicationId)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in getRatioNbfcBankProduct()");
        }
        return null;
    }

    public Integer saveBlendedValues(Long applicationId,Long nbfcOrgId,Long bankOrgId,Double blRoi,Double blEmi,Double blProcessingPf){
        try {
          Integer val = entityManager
                    .createNativeQuery("INSERT INTO nbfc_proposal_blended_rate (application_id,nbfc_org_id,bank_org_id,bl_exisiting_amount,bl_additional_amount,bl_amount, "
                                        + "bl_tenure,bl_roi,bl_emi,bl_processing_fee,created_date,modified_date) "
                                        + "SELECT application_id,:nbfcOrgId,:bankOrgId,SUM(existing_loan_amount),SUM(additional_loan_amount), "
                                        + "SUM(el_amount),el_tenure,:blRoi,:blEmi,:blProcessingPf,NOW(),NOW()  FROM proposal_details WHERE application_id=:applicationId")
                    .setParameter("applicationId", applicationId)
                    .setParameter("nbfcOrgId", nbfcOrgId)
                    .setParameter("bankOrgId", bankOrgId)
                    .setParameter("blRoi", blRoi)
                    .setParameter("blEmi", blEmi)
                    .setParameter("blProcessingPf",blProcessingPf)
                    .executeUpdate();
          return val;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in saveBlendedValues()");
        }
        return null;
    }

    public List<BigInteger> getBankList(Long nbfcOrgId){
        try {
            List<BigInteger> val = (List<BigInteger>) entityManager
                    .createNativeQuery("SELECT DISTINCT fp.user_org_id " +
                            "FROM `loan_application`.`fp_co_lending_ratio` clr " +
                            "INNER JOIN `loan_application`.`nbfc_ratio_mapping` rm " +
                            "ON clr.id = rm.ratio_id AND rm.is_active = TRUE " +
                            "INNER JOIN `loan_application`.`fp_product_master` fp " +
                            "ON rm.fp_product_id = fp.fp_product_id AND fp.is_active = TRUE AND fp.user_org_id !=:nbfcOrgId " +
                            "WHERE clr.user_org_id =:nbfcOrgId AND clr.is_active = TRUE AND clr.is_proposal_active = TRUE")
                    .setParameter("nbfcOrgId", nbfcOrgId)
                    .getResultList();
          return val;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error while fetching bank list");
        }
        return null;
    }
}
