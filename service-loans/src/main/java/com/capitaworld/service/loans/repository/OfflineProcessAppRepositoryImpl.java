package com.capitaworld.service.loans.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;


@Repository
public class OfflineProcessAppRepositoryImpl implements OfflineProcessedAppRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInEligibleRecordList(Long orgId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchIneligibleRecordsForOffline");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	@Override
	public IneligibleProposalDetails findByAppliationId(Long applicationId) {
		List<IneligibleProposalDetails> data = entityManager.createQuery("SELECT ipd FROM IneligibleProposalDetails ipd where ipd.applicationId =:applicationId",IneligibleProposalDetails.class).setParameter("applicationId", applicationId).getResultList();
		if(data != null && !data.isEmpty()) {
			return data.get(0);
		}
		return null;
	}

	@Override
	public List<Object[]> getSanctionedApplicationList(Long orgId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchSanctionsApplicationList");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	@Override
	public List<Object[]> getDisbursedApplicationList(Long orgId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchDisbursedApplicationList");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	


}