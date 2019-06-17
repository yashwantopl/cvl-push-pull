DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spFetchOfflineDisbursedProposal`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflineDisbursedProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	DECLARE vb_business_type_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id`,last_access_business_type_id INTO vb_role_id,vb_branch_id,vb_org_id,vb_business_type_id FROM users.`users`  WHERE `user_id` = userId;
	
	IF (vb_business_type_id = 1) THEN -- MSME
		SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON) AS CHAR) FROM (SELECT JSON_OBJECT( 
		'applicationId',coapp.`application_id`,
		'organisationName',coapp.`organisation_name`,
		'branchName',branch.`name`,
		'branchCode',branch.`code`,
		'branchId',branch.`id`,
		'mobile',u.mobile,
		'email',u.email) AS JSON
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN `loan_application`.`disbursement_detail` dis ON dis.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id` and coapp.`proposal_mapping_id` is NULL
		LEFT JOIN users.`branch_master` branch ON branch.id = ine.`branch_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id` and con.`proposal_id` is NULL
		LEFT JOIN users.`users` u ON u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`is_disbursed` = TRUE 
		AND dis.`is_active` = TRUE 
		AND ine.business_type_id = 1
		AND dis.`is_disbursed_from` = 2 ");
		IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
			SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " GROUP BY ine.`application_id` ) AS main");
		ELSEIF (vb_role_id = 12) THEN -- SMECC
			SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) GROUP BY ine.`application_id` ) AS main");
		ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
			SET @vb_query = CONCAT(@vb_query," GROUP BY ine.`application_id` ) AS main");
		ELSE 
			SET @vb_query = "select '' from dual";
		END IF; 
		-- select @vb_query from dual;
		PREPARE stmt1 FROM @vb_query;
		EXECUTE stmt1;	
		
	ELSEIF(vb_business_type_id= 3) THEN -- PL	
	        SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON) AS CHAR) FROM (SELECT JSON_OBJECT( 
		'applicationId',reapp.`application_id`,
		'name',CONCAT(reapp.first_name,' ',reapp.middle_name,' ',reapp.last_name),
		'branchName',branch.`name`,
		'branchCode',branch.`code`,
		'branchId',branch.`id`,
		'mobile',u.mobile,
		'email',u.email) AS JSON
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN `loan_application`.`disbursement_detail` dis ON dis.`application_id` = ine.`application_id`
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` reapp ON reapp.application_id = ine.application_id 
		LEFT JOIN users.`branch_master` branch ON branch.id = ine.`branch_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id` and con.`proposal_id` is NULL
		LEFT JOIN users.`users` u ON u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`is_disbursed` = TRUE 
		AND dis.`is_active` = TRUE 
		AND ine.business_type_id = 3
		AND dis.`is_disbursed_from` = 2 ");
		IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)			
			SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " GROUP BY ine.`application_id` ) AS main");
		-- ELSEIF (vb_role_id = 12) THEN -- SMECC
		--	SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) GROUP BY ine.`application_id` ) AS main");
		ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
			SET @vb_query = CONCAT(@vb_query," GROUP BY ine.`application_id` ) AS main");
		ELSE 
			SET @vb_query = "select '' from dual";
		END IF; 
		-- select @vb_query from dual;
		PREPARE stmt1 FROM @vb_query;
		EXECUTE stmt1;	
		
	ELSEIF(vb_business_type_id= 5) THEN -- HL		        
	
	        SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON) AS CHAR) FROM (SELECT JSON_OBJECT( 
		'applicationId',reapp.`application_id`,
		'name',CONCAT(reapp.first_name,' ',reapp.middle_name,' ',reapp.last_name),
		'branchName',branch.`name`,
		'branchCode',branch.`code`,
		'branchId',branch.`id`,
		'mobile',u.mobile,
		'loanTypeId',con.loan_type_id,
		'businessType',con.business_type_id,
		'coAppIds',(SELECT JSON_ARRAYAGG(id) FROM `loan_application`.`fs_retail_co_applicant_details` WHERE `application_id` = reapp.`application_id`),
		'email',u.email) AS JSON
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN `loan_application`.`disbursement_detail` dis ON dis.`application_id` = ine.`application_id`
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` reapp ON reapp.application_id = ine.application_id 
		LEFT JOIN users.`branch_master` branch ON branch.id = ine.`branch_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id` and con.`proposal_id` is NULL
		LEFT JOIN users.`users` u ON u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`is_disbursed` = TRUE 
		AND dis.`is_active` = TRUE 
		AND ine.business_type_id = 5
		AND dis.`is_disbursed_from` = 2");
		IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
			SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " GROUP BY ine.`application_id` ) AS main");
		-- ELSEIF (vb_role_id = 12) THEN -- SMECC
		--	SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) GROUP BY ine.`application_id` ) AS main");
		ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
			SET @vb_query = CONCAT(@vb_query," GROUP BY ine.`application_id` ) AS main");
		ELSE 
			SET @vb_query = "select '' from dual";
		END IF; 
		-- select @vb_query from dual;
		PREPARE stmt1 FROM @vb_query;
		EXECUTE stmt1;	
	ELSE
		SET @vb_query = "select '' from dual";
	END IF;        	
	
    END$$

DELIMITER ;