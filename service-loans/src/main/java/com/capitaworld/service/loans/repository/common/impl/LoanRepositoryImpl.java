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

	private static final String ORG_ID = "orgId";
	private static final String BRANCH_ID = "branchId";
	private static final String LIST_LIMIT = "listLimit";
	private static final String SEARCH_STRING = "searchString";

	@PersistenceContext
	private EntityManager entityManager;
	
	public Object[] getRoleIdAndBranchIdByUserId(Long userId) {
		try {
			Object[] value = (Object[]) entityManager
					.createNativeQuery("SELECT user_role_id,branch_id FROM users.`users` WHERE user_id =:userId")
					.setParameter(CommonUtils.USER_ID, userId)
					.getSingleResult();
			return value;	
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}
	
	public List<Object[]> searchProposalForHO(Long orgId,String searchString,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public List<Object[]> searchProposalForCheckerAndMaker(Long orgId,String searchString,Long branchId,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndBranchAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public List<Object[]> searchProposalForSMECC(Long orgId,String searchString,Long userId,Long listLimit) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndUserIdAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}
	
	public Object[] fpDashBoardCountByOrgId(Long orgId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
	public Object[] fpDashBoardCountByOrgIdAndBranchId(Long orgId,Long branchId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndBranchId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
	public Object[] fpDashBoardCountByOrgIdAndUserId(Long orgId,Long userId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndUserId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
	public String getGSTINByAppId(Long applicationId) {
		String fpProductName =(String) entityManager
				.createNativeQuery("SELECT gstin FROM connect.`connect_log` WHERE application_id =:applicationId")
						.setParameter("applicationId", applicationId).getSingleResult();
		return fpProductName;
	}
	
	public String getCommonPropertiesValue(String key) {
		try {
			return (String) entityManager
					.createNativeQuery("SELECT `value` FROM `loan_application`.`common_properties` WHERE `key` =:key")
							.setParameter("key", key).getSingleResult();
		} catch (Exception e) {
			logger.error("Exception while get common properties value ----->" ,e);
		}
		return null;
	}
	
}
