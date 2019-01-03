package com.capitaworld.service.loans.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.repository.common.LoanRepository;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

	private static final Logger logger = LoggerFactory.getLogger(LoanRepositoryImpl.class);

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
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}
	
	public List<Object[]> searchProposalForHO(Long orgId,String searchString,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("searchString",String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("listLimit",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("searchString",searchString);
		storedProcedureQuery.setParameter("listLimit",listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public List<Object[]> searchProposalForCheckerAndMaker(Long orgId,String searchString,Long branchId,Long listLimit) {
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
	
	public List<Object[]> searchProposalForSMECC(Long orgId,String searchString,Long userId,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndUserIdAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("searchString",String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("userId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("listLimit",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("searchString",searchString);
		storedProcedureQuery.setParameter("userId",userId);
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
	
	public Object[] fpDashBoardCountByOrgIdAndUserId(Long orgId,Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndUserId");
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("userId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("orgId",orgId);
		storedProcedureQuery.setParameter("userId",userId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
}
