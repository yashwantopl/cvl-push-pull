package com.capitaworld.service.loans.repository.common.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capitaworld.service.loans.repository.common.CommonRepository;

@Repository
public class CommonRepositoryImpl  implements CommonRepository {

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

}
