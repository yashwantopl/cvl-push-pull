package com.capitaworld.service.loans.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.springframework.stereotype.Repository;
import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;


@Repository
public class OfflineProcessAppRepositoryImpl implements OfflineProcessedAppRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInEligibleRecordList(Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchOfflinePendingProposal");
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
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
	
	public boolean updateSanctionedFlag(Long appId,Long orgId,Long branchId,Long userId) {
		Boolean result = false;
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spUpdateOfflineSanctionedFlag");
		storedProcedureQuery.registerStoredProcedureParameter("appId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("branchId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("userId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("result",Boolean.class, ParameterMode.OUT);
		storedProcedureQuery.setParameter("appId",appId);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("branchId",branchId);
		storedProcedureQuery.setParameter("userId",userId);
		storedProcedureQuery.execute();
		return (Boolean) storedProcedureQuery.getOutputParameterValue("result");
	}

	@Override
	public List<Object[]> getSanctionedApplicationList(Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchOfflineSanctionedProposal");
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	@Override
	public List<Object[]> getDisbursedApplicationList(Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchOfflineDisbursedProposal");
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	@Override
	public List<Object[]> getRejectProposalsList(Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchOfflineRejectProposal");
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	

	@SuppressWarnings("unchecked")
	@Override 
	public List<Object[]> getHomeCounterDetail(){
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spGetHomeCounter");
		
		return  (List<Object[]>) storedProcedureQuery.getResultList() ; 
	 }

}
