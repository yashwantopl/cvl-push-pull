package com.capitaworld.service.loans.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.repository.common.LoanRepository;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Object[] getRoleIdAndBranchIdByUserId(Long userId) {
		try {
			Object[] value = (Object[]) entityManager
					.createNativeQuery("SELECT user_role_id,branch_id FROM users.`users` WHERE user_id =:userId")
					.setParameter("userId", userId)
					.getSingleResult();
			return value;	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Object[]> searchProposalByOrgNameAndAppCode(Long orgId,String searchString,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("searchString",String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("listLimit",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("searchString",searchString);
		storedProcedureQuery.setParameter("listLimit",listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public List<Object[]> searchProposalByOrgNameAndAppCode(Long orgId,String searchString,Long branchId,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndBranchAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("searchString",String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("branchId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("listLimit",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("searchString",searchString);
		storedProcedureQuery.setParameter("branchId",branchId);
		storedProcedureQuery.setParameter("listLimit",listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public Object[] fpDashBoardCountByOrgId(Long orgId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgId");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
	public Object[] fpDashBoardCountByOrgIdAndBranchId(Long orgId,Long branchId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndBranchId");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("branchId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("branchId",branchId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
}
