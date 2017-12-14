package com.capitaworld.service.loans.service.fundprovider.impl;

import com.capitaworld.service.loans.model.reports.OrganizationPieChartResponse;
import com.capitaworld.service.loans.service.fundprovider.OrganizationReportsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

/**
 * Created by dhaval on 02-Dec-17.
 */
@Service
@Transactional
public class OrganizationReportsServiceImpl implements OrganizationReportsService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String getCountOfProposalInInboxAndPrimary(Long organization_id) {
        String count = null;
        try {
            count = entityManager
                    .createNativeQuery("SELECT COUNT(*) FROM proposal_details WHERE proposal_details.fp_product_id IN " +
                            "(SELECT fp_product_master.fp_product_id FROM fp_product_master WHERE fp_product_master.user_org_id=:organization_id AND fp_product_master.is_active = TRUE) " +
                            "AND proposal_details.is_active = TRUE AND proposal_details.proposal_status_id IN (1,2); ")
                    .setParameter("organization_id", organization_id).getSingleResult().toString();
        } catch (Exception e) {
            count = "0";
        }
        return count;
    }

    @Override
    public String getCountOfAdvance(Long organization_id) {
        String count = null;
        try {
            count = entityManager
                    .createNativeQuery("SELECT COUNT(*) FROM proposal_details WHERE proposal_details.fp_product_id IN " +
                            "(SELECT fp_product_master.fp_product_id FROM fp_product_master WHERE fp_product_master.user_org_id=:organization_id AND fp_product_master.is_active = TRUE) " +
                            "AND proposal_details.is_active = TRUE AND proposal_details.proposal_status_id=5; ")
                    .setParameter("organization_id", organization_id).getSingleResult().toString();
        } catch (Exception e) {
            count = "0";
        }
        return count;
    }

    @Override
    public String getSumOfAmountProposalInInboxAndPrimary(Long organization_id) {
        String count = null;
        try {
            count = entityManager
                    .createNativeQuery("SELECT SUM(CASE fs_loan_application_master.denomination_id " +
                            "WHEN 1 THEN (fs_loan_application_master.amount*100000)" +
                            "WHEN 2 THEN (fs_loan_application_master.amount*1000000)" +
                            "WHEN 3 THEN (fs_loan_application_master.amount*10000000)" +
                            "WHEN 4 THEN (fs_loan_application_master.amount*100000000)" +
                            "WHEN 5 THEN (fs_loan_application_master.amount*1)" +
                            "ELSE fs_loan_application_master.amount END) AS sum_amount " +
                            "FROM fs_loan_application_master " +
                            "WHERE fs_loan_application_master.application_id IN " +
                            "(SELECT proposal_details.application_id FROM proposal_details WHERE proposal_details.fp_product_id " +
                            "IN (SELECT fp_product_master.fp_product_id FROM fp_product_master WHERE fp_product_master.user_org_id=:organization_id AND fp_product_master.is_active = TRUE) " +
                            "AND proposal_details.is_active = TRUE AND proposal_details.proposal_status_id IN (1,2));")
                    .setParameter("organization_id", organization_id).getSingleResult().toString();
        } catch (Exception e) {
            count = "0";
        }
        return count;
    }

    @Override
    public String getSumOfAmountProposalInAdvance(Long organization_id) {
        String count = null;
        try {
            count = entityManager
                    .createNativeQuery("SELECT SUM(CASE fs_loan_application_master.denomination_id " +
                            "WHEN 1 THEN (fs_loan_application_master.amount*100000)" +
                            "WHEN 2 THEN (fs_loan_application_master.amount*1000000)" +
                            "WHEN 3 THEN (fs_loan_application_master.amount*10000000)" +
                            "WHEN 4 THEN (fs_loan_application_master.amount*100000000)" +
                            "WHEN 5 THEN (fs_loan_application_master.amount*1)" +
                            "ELSE fs_loan_application_master.amount END) AS sum_amount " +
                            "FROM fs_loan_application_master " +
                            "WHERE fs_loan_application_master.application_id IN " +
                            "(SELECT proposal_details.application_id FROM proposal_details WHERE proposal_details.fp_product_id " +
                            "IN (SELECT fp_product_master.fp_product_id FROM fp_product_master WHERE fp_product_master.user_org_id=:organization_id AND fp_product_master.is_active = TRUE) " +
                            "AND proposal_details.is_active = TRUE AND proposal_details.proposal_status_id = 5);")
                    .setParameter("organization_id", organization_id).getSingleResult().toString();
        } catch (Exception e) {
            count = "0";
        }
        return count;
    }

    @Override
    public String getBranchPieDetails(Long organizationId) {
        String count = "[";
        List<Objects[]> objectsList = null;
        try {
            objectsList = entityManager
                    .createNativeQuery("SELECT branch_name,COUNT(application_id) FROM org_branch_audit WHERE user_org_id=:organization_id AND is_active = TRUE GROUP BY branch_id;")
                    .setParameter("organization_id", organizationId).getResultList();
            for (Object[] a : objectsList) {
                count +="{\"name\":\"" + a[0] + "\",\"y\":\"" + a[1]+"\"},";
            }
        } catch (Exception e) {
            count = "null";
        }
        count = count.substring(0,(count.length()-1));
        count+="]";
        return count;
    }

    @Override
    public OrganizationPieChartResponse getDetails(Long organization_id) {
        OrganizationPieChartResponse organizationPieChartResponse = new OrganizationPieChartResponse();
        organizationPieChartResponse.setApprovedAmount(getSumOfAmountProposalInAdvance(organization_id));
        organizationPieChartResponse.setApprovedFiles(getCountOfAdvance(organization_id));
        organizationPieChartResponse.setUnderProcessFiles(getCountOfProposalInInboxAndPrimary(organization_id));
        organizationPieChartResponse.setUnderProcessAmount(getSumOfAmountProposalInInboxAndPrimary(organization_id));
        organizationPieChartResponse.setBranchPieResponce(getBranchPieDetails(organization_id));
        return organizationPieChartResponse;
    }
}