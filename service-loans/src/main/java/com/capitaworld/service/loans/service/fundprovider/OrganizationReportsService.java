package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.reports.OrganizationPieChartResponse;

/**
 * Created by dhaval on 02-Dec-17.
 */
public interface OrganizationReportsService {
    public String getCountOfProposalInInboxAndPrimary(Long organization_id);
    public String getCountOfAdvance(Long organization_id);
    public String getSumOfAmountProposalInInboxAndPrimary(Long organization_id);
    public String getSumOfAmountProposalInAdvance(Long organization_id);
    public String getBranchPieDetails(Long organizationId);
    public OrganizationPieChartResponse getDetails(Long organization_id);

}
