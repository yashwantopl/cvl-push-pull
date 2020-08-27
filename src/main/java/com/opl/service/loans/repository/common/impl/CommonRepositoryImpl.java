package com.opl.service.loans.repository.common.impl;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.service.loans.repository.common.CommonRepository;

@Repository
public class CommonRepositoryImpl  implements CommonRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonRepositoryImpl.class);

	@Autowired
	private EntityManager manager;
	
	@Override
	public Object[] getUserCampainCodeByApplicationId(Long applicationId) {
		Query camp = manager.createNativeQuery("SELECT cm.code,cl.`wc_renewal_status` FROM connect_mudra.`connect_log` cl\r\n" + 
				"LEFT JOIN users.`campaign_details` cm ON cm.user_id=cl.`user_id`\r\n" + 
				"WHERE application_id =:applicationId AND cm.is_active=TRUE");
		camp.setParameter("applicationId",applicationId);
		return (Object[]) camp.getSingleResult();
	}

	@Override
	public Object[] getEmailDataByApplicationId(Long applicationId) {
		Query emailData = manager.createNativeQuery("SELECT u.email,u.`mobile`,cl.`application_id`,f.`registered_premise_number`,f.`registered_street_name`,f.`registered_land_mark`\r\n" + 
				",f.`registered_city_id`,f.`registered_state_id`,f.`registered_pincode`,f.`registered_country_id`,cl.`wc_renewal_status`,f.`organisation_name`,cl.`user_id`,pri.`purpose_of_loan_id`,cl.`business_type_id`\r\n" + 
				"FROM connect_mudra.`connect_log` cl\r\n" + 
				"LEFT JOIN users.`users` u ON u.user_id=cl.user_id\r\n" + 
				"LEFT JOIN `fs_corporate_applicant_details` f ON cl.`application_id` = f.`application_id`\r\n" + 
				"LEFT JOIN `fs_corporate_primary_details` pri ON pri.`application_id` = cl.`application_id`\r\n" + 
				"WHERE cl.application_id =:applicationId");
		emailData.setParameter("applicationId", applicationId);
		
		return (Object[]) emailData.getSingleResult();
	}

	@Override
	public String getCoApplicatantNameFromITR(Long coAppId) {
		Query nameData = manager.createNativeQuery("SELECT it.`name` FROM `itr_api_mudra`.`itr_home_loan_tracking` it WHERE it.co_app_id=:coAppId");
		nameData.setParameter("coAppId", coAppId);
		return (String) nameData.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.repository.common.CommonRepository#getAdminMakerDetails(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getBranchUserDetailsBasedOnRoleId(Long orgId,Integer roleId) {
		return manager.createNativeQuery("SELECT  u.email,u.mobile,fp.first_name,fp.last_name,fp.`organization_name` ,u.user_id AS UserId FROM users.`users` u LEFT JOIN users.`fund_provider_details` fp ON u.user_id=fp.user_id WHERE  u.user_org_id=:orgId AND u.user_role_id =:roleId").setParameter("orgId", orgId).setParameter("roleId", roleId).getResultList();
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.repository.common.CommonRepository#getFpFullName(java.lang.Long)
	 */
	@Override
	public Object[] getFpFullName(Long userId) {
		return (Object[]) manager.createNativeQuery("SELECT fp.`first_name`,fp.`last_name` FROM users.`fund_provider_details` fp WHERE  fp.user_id=:userId").setParameter("userId", userId).getSingleResult();
	}
	
	@Override
	public Object getMakerDate(Long applicationId) {
		return (Object) manager.createNativeQuery("SELECT wjt.updated_on FROM `workflow_mudra`.`workflow_jobs_tracker` wjt WHERE wjt.job_id = "
				+ "(SELECT wj.id FROM `workflow_mudra`.`workflow_jobs` wj WHERE wj.application_id ="+applicationId+" AND wj.is_active = TRUE) "
						+ "AND wjt.action_id=2").getSingleResult();
	}
	
	@Override
	public Integer getViewedTeaser(String emailId){
		StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("notification_mudra.isViewedTeaser");
		storedProcedureQuery.registerStoredProcedureParameter("emailId",String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("emailId",emailId);
		storedProcedureQuery.execute();
		return (Integer) storedProcedureQuery.getSingleResult();
	}
	
	@Override
	public String getNoteForHLCam(Long applicationId){
		try {
			StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("spRetailCheckPANAlreadyExist");
			storedProcedureQuery.registerStoredProcedureParameter("typeId",Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("selectedLoanTypeId",Integer.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("applicationId",Long.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("panNumber",String.class, ParameterMode.IN);
			storedProcedureQuery.registerStoredProcedureParameter("loanType",Integer.class, ParameterMode.OUT);
			storedProcedureQuery.registerStoredProcedureParameter("message",String.class, ParameterMode.OUT);
			storedProcedureQuery.setParameter("typeId",5);
			storedProcedureQuery.setParameter("selectedLoanTypeId",-1);
			storedProcedureQuery.setParameter("applicationId",applicationId);
			storedProcedureQuery.setParameter("panNumber","NA");
			storedProcedureQuery.execute();
			Object result = storedProcedureQuery.getOutputParameterValue("loanType");
			if(!CommonUtils.isObjectNullOrEmpty(result)) {
				return (String) storedProcedureQuery.getOutputParameterValue("message");
			}
		} catch (Exception e) {
			logger.error("EXCEPTION spRetailCheckPANAlreadyExist while getting note for HL Cam:=- ", e);
		}
		return null;
	}

	@Override
	public String getEmailIdFromUsers(Long userId) {
		return (String) manager.createNativeQuery("SELECT u.email FROM users.users u WHERE u.user_id=:userId").setParameter("userId", userId).getSingleResult();
	}
	
	@Override
	public Object[] getEmailIdAndMobileForNBFCUser(Long userId) {
		return (Object[]) manager.createNativeQuery("SELECT fsd.email , fsd.mobile FROM users.fund_seeker_details fsd WHERE fsd.user_id=:userId").setParameter("userId", userId).getSingleResult();
	}

	@Override
	public Object[] getInEligibleByApplicationId(Long applicationId) {
		return (Object[]) manager.createNativeQuery("SELECT ine.user_org_id,ine.branch_id FROM `ineligible_proposal_details` ine WHERE ine.application_id=:applicationId").setParameter("applicationId", applicationId).getSingleResult();
	}
	
	@Override
	public String getSidbiAmount() {
		try {
			return (String) manager.createNativeQuery("SELECT cp.value FROM payment_service_mudra.common_properties cp WHERE cp.name = 'paymentAmount' and cp.type= 'sidbiFees' and cp.is_active=true").getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String getGatewayProvider() {
		try {
			return (String) manager.createNativeQuery("SELECT cp.value FROM payment_service_mudra.common_properties cp WHERE cp.name = 'gatewayProvider' and cp.type= 'Integration' and cp.is_active=true").getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Object[]> getBankDetails(Long applicationId, Long orgId){
		StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("users.getCurrentBranchByAppIdAndOrgId");
		storedProcedureQuery.registerStoredProcedureParameter("applicationId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("orgId",Long.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("applicationId" ,applicationId);
		storedProcedureQuery.setParameter("orgId" ,orgId);
		
		return (List<Object[]>) storedProcedureQuery.getResultList();
	}

	@Override
	public Object[] getUserDetailsByApplicationId(Long applicationId) throws Exception {
		return (Object[]) manager.createNativeQuery("SELECT apm.application_id,apm.user_id,pd.id,pd.fp_product_id,apm.product_id,u.email,u.mobile,pd.user_org_id\r\n" + 
				"FROM proposal_details pd  LEFT JOIN application_proposal_mapping apm  ON apm.application_id=pd.application_id\r\n" + 
				"LEFT JOIN users.users u ON u.user_id=apm.user_id WHERE apm.application_id=:applicationId LIMIT 1").setParameter("applicationId", applicationId).getSingleResult();
	}
	
	@Override
	public List<String> getUserDetailsByUserOrgIdAndUserRoleIdAndBranchId(Long orgId ,Long roleId ,Long branchId){
		return (List<String>) manager.createNativeQuery("SELECT us.email FROM users.users us WHERE us.user_org_id=:orgId AND us.user_role_id=:roleId AND us.branch_id=:branchId")
				.setParameter("orgId", orgId).setParameter("roleId", roleId).setParameter("branchId", branchId).getResultList();
	}
	
	@Override
	public Object getIsNBFCUser(Long applicationId) {
		try {
			return manager.createNativeQuery("SELECT COUNT(*) FROM connect_mudra.connect_log c WHERE c.application_id="+applicationId+" AND c.stage_id>=5 AND c.stage_id!=8 AND c.is_nbfc_user = true").getSingleResult();
		}catch (Exception e) {
			return 0;  
		}
	}
	
	@Override
	public Object[] fetchALDetailsOfManufacturerAssetsSupplier(Long manufacturerId , Long assetModelId, Integer supplierId)  {
		StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("fetch_auto_loan_details");
		storedProcedureQuery.registerStoredProcedureParameter("manufacturerId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("assetModelId",Long.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("supplierId",Integer.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("manufacturerId",manufacturerId);
		storedProcedureQuery.setParameter("assetModelId",assetModelId);
		storedProcedureQuery.setParameter("supplierId",supplierId);
		storedProcedureQuery.execute();
		return (Object[]) storedProcedureQuery.getSingleResult();
	}
	
	
	@Override
	public BigInteger checkApplicationDisbursed(String pan) {
		return (BigInteger) manager.createNativeQuery("SELECT COUNT(*) FROM `connect`.`connect_log` cn\n" +
				"INNER JOIN `proposal_details` pd ON pd.application_id=cn.application_id AND pd.is_active=TRUE AND pd.proposal_status_id IN (11,13)\n" +
				"WHERE (SUBSTR(cn.gstin,3,10) =:pan) AND\n" +
				"((cn.stage_id IN (7,9) AND cn.status=3) OR (cn.stage_id=4 AND cn.status=6))").setParameter("pan", pan).getSingleResult();
	}

	@Override
    public Object[] getLastCheckerNameByBranchId(Long branchId) throws Exception {
         return (Object[]) manager.createNativeQuery("SELECT f.first_name,f.last_name " +
                 "FROM users.users u " +
                 "LEFT JOIN users.`fund_provider_details` f ON f.user_id=u.user_id " +
                 "WHERE u.branch_id=:branchId AND u.user_role_id=9 ORDER BY u.user_id DESC Limit 1;").setParameter("branchId", branchId).getSingleResult();
    }

	/**
	 * GET STATE  NAME BY STATE CODE
	 */
	@Override
	@SuppressWarnings("unchecked")
	public String getStateByStateCode(Long id) {
		List<String> states =  manager.createNativeQuery("SELECT state_name FROM one_form_mudra.state WHERE id =:id").setParameter("id", id).getResultList();
		return 	!CommonUtils.isListNullOrEmpty(states) ? states.get(0) : "";  
	}
	
	@Override
	public Long getCountOfJobId(Long jobId , Long stepId , Long actionId) {
		try {
			Object count1 = (Object) manager.createNativeQuery("SELECT COUNT(*) FROM workflow_mudra.workflow_jobs_tracker WHERE job_id=:jobId AND step_id=:stepId AND action_id=:actionId")
					.setParameter("jobId", jobId).setParameter("stepId", stepId).setParameter("actionId", actionId).getSingleResult();
			return ((BigInteger)count1).longValue();
		}catch (Exception e) {
			logger.error("Error/Exception while getting count with jobId==>{} , stepId==>{} and actionId==>{} exception {}",jobId , stepId , actionId ,e);
		}
		return null;
	}
	
	@Override
	public Boolean checkUserForMudraLoanByUserId(Long userId) {
		try{
			Object obj = manager.createNativeQuery("SELECT * FROM users.user_role_product_mapping WHERE user_id =:userId AND business_type_id=10")
					.setParameter("userId", userId).getSingleResult();
			return obj != null ? true : false;
		}catch (NoResultException e) {
			logger.error("Error/Exception while check User For Mudra Loan for UserId==>{} .... No User Found For CoOrigination",userId);
		}catch (Exception e) {
			logger.error("Error/Exception while check User For Mudra Loan for UserId==>{} exception {}",userId ,e);
		}
		return false;
	}
	
}
