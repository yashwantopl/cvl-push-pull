package com.capitaworld.service.loans.repository.common.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.repository.common.CommonRepository;
import com.capitaworld.service.loans.utils.CommonUtils;

@Repository
public class CommonRepositoryImpl  implements CommonRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonRepositoryImpl.class);

	@Autowired
	private EntityManager manager;
	
	@Override
	public Object[] getUserCampainCodeByApplicationId(Long applicationId) {
		Query camp = manager.createNativeQuery("SELECT cm.code,cl.`wc_renewal_status` FROM connect.`connect_log` cl\r\n" + 
				"LEFT JOIN users.`campaign_details` cm ON cm.user_id=cl.`user_id`\r\n" + 
				"WHERE application_id =:applicationId AND cm.is_active=TRUE");
		camp.setParameter("applicationId",applicationId);
		return (Object[]) camp.getSingleResult();
	}

	@Override
	public Object[] getEmailDataByApplicationId(Long applicationId) {
		Query emailData = manager.createNativeQuery("SELECT u.email,u.`mobile`,cl.`application_id`,f.`registered_premise_number`,f.`registered_street_name`,f.`registered_land_mark`\r\n" + 
				",f.`registered_city_id`,f.`registered_state_id`,f.`registered_pincode`,f.`registered_country_id`,cl.`wc_renewal_status`,f.`organisation_name`,cl.`user_id`,pri.`purpose_of_loan_id`,cl.`business_type_id`\r\n" + 
				"FROM connect.`connect_log` cl\r\n" + 
				"LEFT JOIN users.`users` u ON u.user_id=cl.user_id\r\n" + 
				"LEFT JOIN `loan_application`.`fs_corporate_applicant_details` f ON cl.`application_id` = f.`application_id`\r\n" + 
				"LEFT JOIN `loan_application`.`fs_corporate_primary_details` pri ON pri.`application_id` = cl.`application_id`\r\n" + 
				"WHERE cl.application_id =:applicationId");
		emailData.setParameter("applicationId", applicationId);
		
		return (Object[]) emailData.getSingleResult();
	}

	@Override
	public String getCoApplicatantNameFromITR(Long coAppId) {
		Query nameData = manager.createNativeQuery("SELECT it.`name` FROM `itr_api`.`itr_home_loan_tracking` it WHERE it.co_app_id=:coAppId");
		nameData.setParameter("coAppId", coAppId);
		return (String) nameData.getSingleResult();
	}

	/* (non-Javadoc)
	 * @see com.capitaworld.service.loans.repository.common.CommonRepository#getAdminMakerDetails(java.lang.Long)
	 */
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
		return (Object) manager.createNativeQuery("SELECT wjt.updated_on FROM `workflow`.`workflow_jobs_tracker` wjt WHERE wjt.job_id = "
				+ "(SELECT wj.id FROM `workflow`.`workflow_jobs` wj WHERE wj.application_id ="+applicationId+" AND wj.is_active = TRUE) "
						+ "AND wjt.action_id=2").getSingleResult();
	}
	
	@Override
	public Integer getViewedTeaser(String emailId){
		StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("notification.isViewedTeaser");
		storedProcedureQuery.registerStoredProcedureParameter("emailId",String.class, ParameterMode.IN);
		storedProcedureQuery.setParameter("emailId",emailId);
		storedProcedureQuery.execute();
		return (Integer) storedProcedureQuery.getSingleResult();
	}
	
	@Override
	public String getNoteForHLCam(Long applicationId){
		try {
			StoredProcedureQuery storedProcedureQuery = manager.createStoredProcedureQuery("loan_application.spRetailCheckPANAlreadyExist");
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
	public Object[] getInEligibleByApplicationId(Long applicationId) {
		return (Object[]) manager.createNativeQuery("SELECT ine.user_org_id,ine.branch_id FROM `loan_application`.`ineligible_proposal_details` ine WHERE ine.application_id=:applicationId").setParameter("applicationId", applicationId).getSingleResult();
	}
	
	@Override
	public String getSidbiAmount() {
		try {
			return (String) manager.createNativeQuery("SELECT cp.value FROM payment_service.common_properties cp WHERE cp.name = 'paymentAmount' and cp.type= 'sidbiFees' and cp.is_active=true").getSingleResult();
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
	public Boolean updateRelatedPartyFilledFlagOnConnect(Long applicationId) throws Exception {
		try {
			manager.createNamedQuery("UPDATE connect.connect_log set is_related_party_filled = TRUE,modified_date=now() where application_id=:applicationId").setParameter("applicationId", true);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public Boolean getRelatedPartyFilledFlagOnConnect(Long applicationId) throws Exception {
		return Boolean.valueOf(manager.createNativeQuery("select  cl.is_related_party_filled from connect.connect_log cl where cl.application_id=:applicationId").setParameter("applicationId", applicationId).getSingleResult().toString());
	}
	
	
}
