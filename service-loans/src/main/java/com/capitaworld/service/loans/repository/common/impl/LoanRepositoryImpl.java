package com.capitaworld.service.loans.repository.common.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.repository.common.LoanRepository;
import com.capitaworld.service.loans.utils.CommonUtils;

@Repository
public class LoanRepositoryImpl implements LoanRepository {

	private static final Logger logger = LoggerFactory.getLogger(LoanRepositoryImpl.class);

	private static final String ORG_ID = "orgId";
	private static final String BRANCH_ID = "branchId";
	private static final String LIST_LIMIT = "listLimit";
	private static final String SEARCH_STRING = "searchString";
	private static final String BUSI_TYPE_ID = "busiTypeId";
	
	@PersistenceContext
	private EntityManager entityManager;

	public Object[] getRoleIdAndBranchIdByUserId(Long userId) {
		try {
			return  (Object[]) entityManager
					.createNativeQuery("SELECT user_role_id,branch_id FROM users.`users` WHERE user_id =:userId")
					.setParameter(CommonUtils.USER_ID, userId)
					.getSingleResult();
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}
	
	public String getMobileNumberByUserId(Long userId) {
		try {
			return  (String) entityManager
					.createNativeQuery("SELECT mobile FROM users.`users` WHERE user_id =:userId")
					.setParameter(CommonUtils.USER_ID, userId)
					.getSingleResult();
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		return null;
	}

	public List<Object[]> searchProposalForHO(Long orgId,String searchString,Long listLimit,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	public List<Object[]> searchProposalForCheckerAndMaker(Long orgId,String searchString,Long branchId,Long listLimit,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndBranchAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	public List<Object[]> searchProposalForSMECC(Long orgId,String searchString,Long userId,Long listLimit,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchProposalsByOrgAndUserIdAndSearchString");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	@Override
	public List<Object[]> getSerachProposalListByRoleSP(Long orgId, String searchString, Long userId, Long listLimit, Long businessTypeId, Long branchId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("getSerachProposalListByRoleSP");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(SEARCH_STRING,String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(LIST_LIMIT,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(SEARCH_STRING,searchString);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.setParameter(LIST_LIMIT,listLimit);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	public Object[] fpDashBoardCountByOrgId(Long orgId,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}

	public Object[] fpDashBoardCountByOrgIdAndBranchId(Long orgId,Long branchId,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndBranchId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}

	public Object[] fpDashBoardCountByOrgIdAndUserId(Long orgId,Long userId,Long businessTypeId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("spFetchFpDashbordCountByOrgIdAndUserId");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}

	public Object[] fetchFpDashbordCountByRoleSP(Long orgId,Long userId,Long businessTypeId,Long branchId) {
		StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("fetchFpDashbordCountByRoleSP");
		storedProcedureQuery.registerStoredProcedureParameter(ORG_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(CommonUtils.USER_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BUSI_TYPE_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter(BRANCH_ID,Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter(ORG_ID,orgId);
		storedProcedureQuery.setParameter(CommonUtils.USER_ID,userId);
		storedProcedureQuery.setParameter(BUSI_TYPE_ID,businessTypeId);
		storedProcedureQuery.setParameter(BRANCH_ID,branchId);
		return (Object[]) storedProcedureQuery.getSingleResult();
	}

	public String getGSTINByAppId(Long applicationId) {
		return  (String) entityManager
				.createNativeQuery("SELECT gstin FROM connect.`connect_log` WHERE application_id =:applicationId order by id desc limit 1")
						.setParameter("applicationId", applicationId).getSingleResult();
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

	@Override
	public Long getOfflineCountByAppId(Long applicationId) {
		BigInteger count =  (BigInteger) entityManager
				.createNativeQuery("SELECT COUNT(*) FROM `loan_application`.`ineligible_proposal_details` inl WHERE inl.`application_id` =:applicationId AND inl.`is_active` = TRUE")
						.setParameter("applicationId", applicationId).getSingleResult();
		return count != null ? count.longValue() : 0l;
	}
	
	@Override
	public String getOfflineDetailsByAppId(Long applicationId) {
		try {
			return  (String) entityManager
					.createNativeQuery("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',inl.`application_id`,\r\n" + 
							"'status',sts.`display_name`,'bankName',org.`organisation_name`,'branchName',brn.`name`,'branchCode', brn.`code`)) AS CHAR) AS JSON \r\n" + 
							"FROM `loan_application`.`ineligible_proposal_details` inl \r\n" + 
							"LEFT JOIN `loan_application`.`ineligible_proposal_status` sts ON sts.`id` = inl.`status` \r\n" + 
							"LEFT JOIN users.`user_organisation_master` org ON org.`user_org_id` = inl.`user_org_id` \r\n" + 
							"LEFT JOIN users.`branch_master` brn ON brn.`id` = inl.`branch_id` \r\n" + 
							"WHERE inl.`application_id` =:applicationId AND inl.`is_active` = TRUE;")
							.setParameter("applicationId", applicationId).getSingleResult();	
		} catch (Exception e) {
			logger.error("Exception while get offline details by application id ----->" ,e);
		}
		return null;
	}
	
	@Override
	public String getOfflineStatusByAppId(Long applicationId) {
		try {
			return  (String) entityManager
					.createNativeQuery("SELECT CAST(JSON_OBJECT('applicationId',inl.`application_id`,'status',sts.`display_name`,'bankName',org.`organisation_name`,'modifiedDate',inl.`modified_date`) AS CHAR ) \r\n" + 
							"FROM `loan_application`.`ineligible_proposal_details` inl \r\n" + 
							"LEFT JOIN `loan_application`.`ineligible_proposal_status` sts ON sts.`id` = inl.`status`\r\n" + 
							"LEFT JOIN users.`user_organisation_master` org ON org.`user_org_id` = inl.`user_org_id`\r\n" + 
							"WHERE inl.`application_id` =:applicationId AND inl.`is_active` = TRUE")
							.setParameter("applicationId", applicationId).getSingleResult();	
		} catch (Exception e) {
			logger.error("Exception while get offline status  ----->" ,e);
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getIncomeOfItrOf3Years(Long applicationId) {

		return (List<Double>)entityManager.createNativeQuery("SELECT appInc.`salary_income` FROM `loan_application`.`fs_retail_applicant_income_details` appInc WHERE appInc.`application_id` =:applicationId AND appInc.`proposal_mapping_id` IS NULL AND `appInc`.`salary_income` IS NOT NULL ORDER BY appInc.`year` DESC ").setParameter("applicationId", applicationId) .getResultList();
//		if(!CommonUtils.isListNullOrEmpty(resultList)) {
//			List<Double> finalList = new ArrayList<>(resultList.size());
//			for(Object o : resultList) {
//				finalList.add(Double.valueOf(o.toString()));
//			}
//			return finalList;
//		}else {
//			return Collections.emptyList();			
//		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Double> getIncomeOfItrOf3YearsOfCoApplicant(Long coAppId) {

		return (List<Double>)entityManager.createNativeQuery("SELECT appInc.`salary_income` FROM `loan_application`.`fs_retail_co_applicant_income_details` appInc WHERE appInc.`id` =:id AND appInc.`proposal_mapping_id` IS NULL AND `appInc`.`salary_income` IS NOT NULL ORDER BY appInc.`year` DESC ")
				.setParameter("id", coAppId)
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isITRUploaded(Long applicationId) {
		List<Boolean> result = (List<Boolean>)entityManager.createNativeQuery("SELECT itr.`is_manual_filled` FROM `itr_api`.`itr_tracking` itr WHERE itr.`application_id` =:applicationId AND itr.`is_active` = TRUE")
				.setParameter("applicationId", applicationId)
				.getResultList();
		if(!CommonUtils.isListNullOrEmpty(result)) {
			return result.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean isITRUploadedForCoApp(Long applicationId, Long coAppId) {
		List<BigInteger> result = (List<BigInteger>)entityManager.createNativeQuery("SELECT COUNT(app.`id`) FROM `loan_application`.`fs_retail_co_applicant_details` app WHERE app.`is_itr_completed` = TRUE AND app.`id` =:id AND app.`application_id` =:applicationId AND app.`is_active` = TRUE")
				.setParameter("applicationId", applicationId)
				.setParameter("id", coAppId)
				.getResultList();
		if(!CommonUtils.isListNullOrEmpty(result)) {
			return result.get(0) != null ? result.get(0).longValue() > 0 : false;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isITRSkippedForCoApp(Long applicationId, Long coAppId) {
		List<BigInteger> result = (List<BigInteger>)entityManager.createNativeQuery("SELECT COUNT(app.`id`) FROM `loan_application`.`fs_retail_co_applicant_details` app WHERE app.`is_itr_skip` = TRUE AND app.`id` =:id AND app.`application_id` =:applicationId AND app.`is_active` = TRUE")
				.setParameter("applicationId", applicationId)
				.setParameter("id", coAppId)
				.getResultList();
		if(!CommonUtils.isListNullOrEmpty(result)) {
			return result.get(0) != null ? result.get(0).longValue() > 0 : false;
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Boolean isITRMannualForCoApp(Long applicationId, Long coAppId) {
		List<BigInteger> result = (List<BigInteger>)entityManager.createNativeQuery("SELECT COUNT(app.`id`) FROM `loan_application`.`fs_retail_co_applicant_details` app WHERE app.`is_itr_manual` = TRUE AND app.`id` =:id AND app.`application_id` =:applicationId AND app.`is_active` = TRUE")
				.setParameter("applicationId", applicationId)
				.setParameter("id", coAppId)
				.getResultList();
		if(!CommonUtils.isListNullOrEmpty(result)) {
			return result.get(0) != null ? result.get(0).longValue() > 0 : false;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double getRetailLoanAmountByApplicationId(Long applicationId) {
		List<Double> result = (List<Double>)entityManager.createNativeQuery("SELECT app.`loan_amount_required` FROM `loan_application`.`fs_retail_applicant_details` app WHERE app.`application_id` =:applicationId AND app.`is_active` = TRUE")
				.setParameter("applicationId", applicationId)
				.getResultList();
		if(!CommonUtils.isListNullOrEmpty(result)) {
			return result.get(0);
		}
		return null;
	}

	//1/6/2019...................
	@Override
	public List<Object[]> getTypeSelectionData() {
		return (List<Object[]>) entityManager.createNativeQuery("SELECT `type`,`description`,`business_type_id`,`img_path` FROM `loan_application`.`fs_loan_type_selection` WHERE `is_active` = TRUE")
				.getResultList();
	}
}