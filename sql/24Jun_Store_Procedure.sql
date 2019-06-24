DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spRetailCheckPANAlreadyExist`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spRetailCheckPANAlreadyExist`(IN typeId INT,IN selectedLoanTypeId INT,IN applicationId BIGINT(20),IN panNumber VARCHAR(50),OUT loanType INTEGER,OUT message VARCHAR(250))
BEGIN
	DECLARE vb_inprinciple_date_range INT;	
	DECLARE vb_loan_type INT;
	DECLARE vb_user_email VARCHAR(100);
	DECLARE vb_connect_stage INT;
	DECLARE vb_count INT;
	
	SELECT CAST(`value` AS UNSIGNED) INTO vb_inprinciple_date_range FROM `loan_application`.`common_properties` WHERE `key` = 'CONNECT_RETAIL_NEW_APPLICATION_DATE_RANGE';
		
	IF (typeId = 1) THEN -- ITR CHECK POINT
		SELECT con.`loan_type_id`,us.`email`,con.`stage_id` INTO vb_loan_type,vb_user_email,vb_connect_stage
		FROM connect.`connect_log` con 
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` fs ON fs.`application_id` = con.`application_id` AND fs.`proposal_mapping_id` IS NULL
		LEFT JOIN `loan_application`.`fs_retail_co_applicant_details` coApp ON coApp.`application_id` = con.`application_id` AND coApp.`proposal_mapping_id` IS NULL AND coApp.`is_income_consider` = TRUE
		LEFT JOIN users.`users` us ON us.`user_id` = con.`user_id`
		WHERE (UPPER(fs.`pan`) = UPPER(panNumber) OR UPPER(coApp.`pan`) = UPPER(panNumber))
		AND ((((con.`stage_id` IN (207,509) AND con.`status` = 6) OR (con.`stage_id` IN (208,510) AND con.`status` = 3)) AND (con.`modified_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW())) 
		OR (con.`stage_id` IN (210,211,512,513) AND (con.`In_principle_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW()))) ORDER BY con.`id` DESC LIMIT 1;
		
		IF (vb_loan_type > 0) THEN
			SET loanType = vb_loan_type;
			
			IF (vb_loan_type = 7) THEN 
				SET @loanTypeName = "Personal Loan";
				SET @diffloanTypeName = "Home Loan";
			ELSE 
				SET @loanTypeName = "Home Loan";
				SET @diffloanTypeName = "Personal Loan";
			END IF;
			
			IF (vb_connect_stage = 208 OR vb_connect_stage = 510) THEN
				SET message = CONCAT("It seems that your " ,@loanTypeName," application is already on Bank Selection page with E-mail id - ",`emailEncrypt`(vb_user_email) 
					,". Request you to complete that application first to ignore duplication entries.");
			ELSE 
				SET message = CONCAT("You have taken ",@loanTypeName," on same PAN, so you are only eligible for ",@diffloanTypeName,". Do you want to apply for ",@diffloanTypeName," ?");
			END IF;
		END IF;
		
	ELSEIF (typeId = 2) THEN -- LOAN SELECTION CHECK POINT
		
		SELECT con.`loan_type_id`,us.`email`,con.`stage_id` INTO vb_loan_type,vb_user_email,vb_connect_stage FROM connect.`connect_log` con 
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` fs ON fs.`application_id` = con.`application_id` AND fs.`proposal_mapping_id` IS NULL
		LEFT JOIN `loan_application`.`fs_retail_co_applicant_details` coApp ON coApp.`application_id` = con.`application_id` AND coApp.`proposal_mapping_id` IS NULL AND coApp.`is_income_consider` = TRUE
		LEFT JOIN users.`users` us ON us.`user_id` = con.`user_id` WHERE 
		(UPPER(fs.`pan`) IN (
			SELECT `pan` FROM `loan_application`.`fs_retail_applicant_details` WHERE `application_id` = applicationId AND pan IS NOT NULL
			UNION ALL
			SELECT co.`pan` FROM `loan_application`.`fs_retail_co_applicant_details` co WHERE co.`application_id` = applicationId AND co.`is_active` = TRUE AND co.`pan` IS NOT NULL) 
		OR UPPER(coApp.`pan`) IN (
			SELECT `pan` FROM `loan_application`.`fs_retail_applicant_details` WHERE `application_id` = applicationId AND pan IS NOT NULL
			UNION ALL
			SELECT co.`pan` FROM `loan_application`.`fs_retail_co_applicant_details` co WHERE co.`application_id` = applicationId AND co.`is_active` = TRUE AND co.`pan` IS NOT NULL))
		AND ((((con.`stage_id` IN (207,509) AND con.`status` = 6) OR (con.`stage_id` IN (208,509) AND con.`status` = 3)) AND (con.`modified_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW())) 
		OR (con.`stage_id` IN (210,211,512,513) AND (con.`In_principle_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW()))) ORDER BY con.`id` DESC LIMIT 1;
	
		IF (vb_loan_type > 0 AND vb_loan_type = selectedLoanTypeId) THEN
			SET loanType = vb_loan_type;
			
			IF (vb_loan_type = 7) THEN 
				SET @loanTypeName = "Personal Loan";
				SET @diffloanTypeName = "Home Loan";
			ELSE 
				SET @loanTypeName = "Home Loan";
				SET @diffloanTypeName = "Personal Loan";
			END IF;
			
			IF (vb_connect_stage = 208 OR vb_connect_stage = 510) THEN
				IF (selectedLoanTypeId = 7) THEN 
					SET @selectedLoan = "Personal Loan";
				ELSE 
					SET @selectedLoan = "Home Loan";
				END IF;
				
				SET message = CONCAT("It seems that your " ,@selectedLoan," application is already on Bank Selection page with E-mail id - ",`emailEncrypt`(vb_user_email) 
					,". Request you to complete that application first to ignore duplication entries.");
			ELSE 
				SET message = CONCAT("You have taken ",@loanTypeName," on same PAN, so you are only eligible for ",@diffloanTypeName,". Do you want to apply for ",@diffloanTypeName," ?");
			END IF;
		END IF;
		
	ELSEIF (typeId = 3) THEN -- MATCHES PAGE CHECK POINT
		
		SELECT con.`loan_type_id`,us.`email`,con.`stage_id` INTO vb_loan_type,vb_user_email,vb_connect_stage FROM connect.`connect_log` con 
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` fs ON fs.`application_id` = con.`application_id` AND fs.`proposal_mapping_id` IS NULL
		LEFT JOIN `loan_application`.`fs_retail_co_applicant_details` coApp ON coApp.`application_id` = con.`application_id` AND coApp.`proposal_mapping_id` IS NULL  AND coApp.`is_income_consider` = TRUE
		LEFT JOIN users.`users` us ON us.`user_id` = con.`user_id` WHERE 
		(UPPER(fs.`pan`) IN (
			SELECT `pan` FROM `loan_application`.`fs_retail_applicant_details` WHERE `application_id` = applicationId AND pan IS NOT NULL
			UNION ALL
			SELECT co.`pan` FROM `loan_application`.`fs_retail_co_applicant_details` co WHERE co.`application_id` = applicationId AND co.`is_active` = TRUE AND co.`pan` IS NOT NULL) 
		OR UPPER(coApp.`pan`) IN (
			SELECT `pan` FROM `loan_application`.`fs_retail_applicant_details` WHERE `application_id` = applicationId AND pan IS NOT NULL
			UNION ALL
			SELECT co.`pan` FROM `loan_application`.`fs_retail_co_applicant_details` co WHERE co.`application_id` = applicationId AND co.`is_active` = TRUE AND co.`pan` IS NOT NULL))
		AND ((((con.`stage_id` IN (207,509) AND con.`status` = 6) OR (con.`stage_id` IN (208,509) AND con.`status` = 3)) AND (con.`modified_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW())) 
		OR (con.`stage_id` IN (210,211,512,513) AND (con.`In_principle_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW()))) 
		AND con.`application_id` != applicationId
		ORDER BY con.`id` DESC LIMIT 1;
	
		IF (vb_loan_type > 0) THEN
			SET loanType = vb_loan_type;
			
			IF (vb_loan_type = 7) THEN 
				SET @loanTypeName = "Personal Loan";
				SET @diffloanTypeName = "Home Loan";
			ELSE 
				SET @loanTypeName = "Home Loan";
				SET @diffloanTypeName = "Personal Loan";
			END IF;
			
			IF (vb_connect_stage = 208 OR vb_connect_stage = 510) THEN
				IF (selectedLoanTypeId = 7) THEN 
					SET @selectedLoan = "Personal Loan";
				ELSE 
					SET @selectedLoan = "Home Loan";
				END IF;
				
				SET message = CONCAT("It seems that your " ,@selectedLoan," application is already on Bank Selection page with E-mail id - ",`emailEncrypt`(vb_user_email) 
					,". Request you to complete that application first to ignore duplication entries.");
			ELSE 
				SET message = CONCAT("You have taken ",@loanTypeName," on same PAN, so you are only eligible for ",@diffloanTypeName,".");
			END IF;
		END IF;
		
	ELSEIF (typeId = 4) THEN -- CHECK WHEN ADD CO-APPLICANT
		SELECT con.`loan_type_id`,us.`email`,con.`stage_id` INTO vb_loan_type,vb_user_email,vb_connect_stage
		FROM connect.`connect_log` con 
		LEFT JOIN `loan_application`.`fs_retail_applicant_details` fs ON fs.`application_id` = con.`application_id` AND fs.`proposal_mapping_id` IS NULL
		LEFT JOIN `loan_application`.`fs_retail_co_applicant_details` coApp ON coApp.`application_id` = con.`application_id` AND coApp.`proposal_mapping_id` IS NULL AND coApp.`is_income_consider` = TRUE
		LEFT JOIN users.`users` us ON us.`user_id` = con.`user_id`
		WHERE (UPPER(fs.`pan`) = UPPER(panNumber) OR UPPER(coApp.`pan`) = UPPER(panNumber))
		AND ((((con.`stage_id` IN (207,509) AND con.`status` = 6) OR (con.`stage_id` IN (208,510) AND con.`status` = 3)) AND (con.`modified_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW())) 
		OR (con.`stage_id` IN (210,211,512,513) AND (con.`In_principle_date` BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW()))) ORDER BY con.`id` DESC LIMIT 1;
		
		IF (vb_loan_type > 0) THEN
			SET loanType = vb_loan_type;
			
			IF (vb_loan_type = 7) THEN 
				SET @loanTypeName = "Personal Loan";
			ELSE 
				SET @loanTypeName = "Home Loan";
			END IF;
			
			SET message = CONCAT("It seems that added co-applicant has already availed ",@loanTypeName," on PSBloans platform, hence he/she can not be added again as Co-applicant. The same person can be added as Co-applicant if his/her income is not considered for Income Eligibility.");
		ELSE
			SELECT COUNT(*) INTO vb_count FROM `loan_application`.`fs_retail_applicant_details` fs WHERE fs.`pan` = panNumber AND fs.`application_id` = applicationId AND fs.`is_active` = TRUE;
			IF (vb_count > 0) THEN
				SET loanType = 100;
				SET message = CONCAT("It seems you have added PAN are already submited in applicant details, Please enter different PAN details");
			ELSE 
				SELECT COUNT(*) INTO vb_count FROM `loan_application`.`fs_retail_co_applicant_details` co WHERE co.`application_id` = applicationId AND co.`is_active` = TRUE AND co.`pan` = panNumber;
				IF (vb_count > 0) THEN
					SET loanType = 100;
					SET message = CONCAT("It seems you have added PAN are already submited in another Co-Applicant details, Please enter different PAN details");
				END IF;
			END IF;
			
		END IF;
		
		
	END IF;
	
	
	
    END$$

DELIMITER ;






DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spApplicationInactive`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spApplicationInactive`(IN applicationId BIGINT(20),IN msg VARCHAR(100))
BEGIN
	IF (msg IS NULL OR msg = '') THEN
		SET msg = "Application is deleted from Ristict Loan on same Pan!!";
	END IF;
	UPDATE connect.`connect_log` SET `status` = 5,`modified_date` = NOW(),`message` = msg WHERE `application_id` = applicationId;
	UPDATE `loan_application`.`fs_loan_application_master` SET `is_active` = FALSE, `modified_date` = NOW() WHERE `application_id` = applicationId;
END$$

DELIMITER ;










DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `getSerachProposalListByRoleSP`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `getSerachProposalListByRoleSP`(IN orgId INT,IN searchString VARCHAR(100),IN userId INT,IN listLimit INT,IN busiTypeId INT,IN branchId INT)
BEGIN
	SELECT `user_role_id` INTO @userRoleId FROM `users`.`users` WHERE `user_id`=userId;
	
	IF (@userRoleId = 9) THEN -- CHECKER
	
		SELECT DISTINCT(prop.`application_id`),prop.`id`,prop.`fp_product_id`,loan.`application_code`,
		appl.`organisation_name`,prop.`el_amount`,product.`name`,prop.`created_date`,loan.`business_type_id`,
		prop.`proposal_status_id`,loan.`product_id` AS pro,
		CONCAT(retail.first_name,' ',retail.last_name) AS pl_user_name,
		branch.name AS branchName,branch.code,
		((SELECT COUNT(*) FROM loan_application.proposal_details WHERE proposal_status_id IN(5,11,13) AND user_org_id <> prop.`user_org_id` AND application_id = prop.`application_id`)) AS is_sactioned_from_other
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id` AND appl.`proposal_mapping_id` = prop.`id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = prop.`branch_id`
		LEFT JOIN fs_retail_applicant_details AS retail ON retail.application_id = loan.application_id AND retail.proposal_mapping_id = prop.id
		WHERE prop.`is_active` = TRUE 
			AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%') 
			OR retail.first_name LIKE CONCAT('%', searchString , '%') OR retail.middle_name LIKE CONCAT('%', searchString , '%') 
			OR retail.last_name LIKE CONCAT('%', searchString , '%')) AND prop.`user_org_id` = orgId
			AND prop.`branch_id` = branchId
			AND loan.`business_type_id`=busiTypeId
			ORDER BY prop.`created_date` DESC LIMIT listLimit;
		
	ELSEIF (@userRoleId = 12) THEN -- SMECC
		
		SELECT DISTINCT(prop.`application_id`),prop.`id`,prop.`fp_product_id`,loan.`application_code`,appl.`organisation_name`,prop.`el_amount`,product.`name`,prop.`created_date`,loan.`business_type_id`,prop.`proposal_status_id`,loan.`product_id` AS pro,
		CONCAT(retail.first_name,' ',retail.last_name) AS pl_user_name,
		branch.name AS branchName,branch.code,
		((SELECT COUNT(*) FROM loan_application.proposal_details WHERE proposal_status_id IN(5,11,13) AND user_org_id <> prop.`user_org_id` AND application_id = prop.`application_id`)) AS is_sactioned_from_other
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id` AND appl.`proposal_mapping_id` = prop.`id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = prop.`branch_id`
		LEFT JOIN fs_retail_applicant_details AS retail ON retail.application_id = loan.application_id AND retail.proposal_mapping_id = prop.id
		WHERE prop.`is_active` = TRUE AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%')) 
			AND prop.`user_org_id` = orgId AND prop.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = userId AND `is_active` = TRUE) 
			AND loan.`business_type_id`=busiTypeId
			ORDER BY prop.`created_date` DESC LIMIT listLimit;
		
	ELSEIF (@userRoleId = 5) THEN -- HO
	
		SELECT DISTINCT (prop.`application_id`),prop.`id`,prop.`fp_product_id`,loan.`application_code`,appl.`organisation_name`,prop.`el_amount`,product.`name`,
		prop.created_date,loan.`business_type_id`,prop.`proposal_status_id`,loan.`product_id` AS prod,
		CONCAT(retail.first_name,' ',retail.last_name) AS pl_user_name,
		branch.name AS branchName,branch.code,
		((SELECT COUNT(*) FROM loan_application.proposal_details WHERE proposal_status_id IN(5,11,13) AND user_org_id <> prop.`user_org_id` AND application_id = prop.`application_id`)) AS is_sactioned_from_other
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = prop.`branch_id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		LEFT JOIN fs_retail_applicant_details AS retail ON retail.application_id = loan.application_id AND retail.proposal_mapping_id = prop.id
		WHERE prop.`is_active` = TRUE 
			AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') 
			OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%') 
			OR retail.first_name LIKE CONCAT('%', searchString , '%') 
			OR retail.middle_name LIKE CONCAT('%', searchString , '%') 
			OR retail.last_name LIKE CONCAT('%', searchString , '%')) 
			AND prop.`user_org_id` = orgId 
			AND loan.`business_type_id`=busiTypeId
			ORDER BY prop.`created_date` DESC LIMIT listLimit;
	
	END IF;
  END$$

DELIMITER ;

















DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spFetchBankAdminEligibleProposal`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminEligibleProposal`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	-- DECLARE @vb_query VARCHAR(2000);
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	-- SET vb_role_id = (SELECT `user_role_id` FROM users.`users`  WHERE `user_id` = userId);
	-- SET vb_branch_id = (SELECT `branch_id` FROM users.`users`  WHERE `user_id` = userId);
	
	SET @vb_query = CONCAT("SELECT pd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, cl.`modified_date`, pd.branch_id, 
		pd.el_amount, pd.el_tenure, pd.el_roi, pd.emi, pd.processing_fee, branch.name AS branchname, 
		branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, 
		lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form`.`state` s 
		WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form`.`city` c WHERE c.id = branch.city_id), branch.premises_no, 
		(SELECT product_id FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = pd.fp_product_id), branch.contact_person_email, 
		(SELECT COUNT(id) FROM `users`.`campaign_details` cd WHERE cd.user_id = cl.user_id AND cd.is_active = TRUE),cl.`gstin`,
		product.`name` AS productName,product.`is_active` AS productActive,proStatus.`status` AS proposalStatus,pd.fp_product_id AS fpProductId,appcnt.`organisation_name` AS companyname,
		cl.`wc_renewal_status`,IF(cl.`wc_renewal_status` = 2,IF(prm.`enhancement_amount` IS NULL,'Same Limit','Enhancement Limit'),'') AS enhLimit,
		(SELECT COUNT(*) FROM loan_application.proposal_details WHERE proposal_status_id IN(5,11,13) AND user_org_id <> pd.`user_org_id` AND application_id = pd.`application_id`) AS is_sactioned_from_other
		FROM `loan_application`.`proposal_details` pd 
		LEFT JOIN `connect`.`connect_log` cl ON cl.application_id = pd.application_id AND cl.`proposal_id` = pd.id
		LEFT JOIN `loan_application`.`proposal_status_master` proStatus ON proStatus.`id` = pd.`proposal_status_id`  
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = pd.`fp_product_id` 
		LEFT JOIN `users`.`users` usr ON usr.user_id = cl.user_id 
		LEFT JOIN `users`.`fund_seeker_details` fs ON fs.user_id = usr.user_id 
		LEFT JOIN  `users`.`branch_master` branch ON branch.id = pd.branch_id 
		LEFT JOIN `users`.`user_organisation_master` org ON org.user_org_id = pd.user_org_id 
		LEFT JOIN loan_application.`application_proposal_mapping` lam ON lam.`application_id` = pd.`application_id` AND lam.`proposal_id` = pd.`id`
		LEFT JOIN `loan_application`.`fs_corporate_applicant_details` appcnt ON appcnt.application_id = pd.application_id AND appcnt.`proposal_mapping_id` = pd.`id`
		LEFT JOIN `loan_application`.`fs_corporate_primary_details` prm ON prm.`application_id` = pd.`application_id` 
		WHERE pd.user_org_id =", vb_org_id," AND pd.is_active = TRUE AND cl.stage_id IN (7,9) 
		AND (cl.`In_principle_date` BETWEEN '",fromDate,"' AND '",toDate,"') ");
	
	IF (vb_role_id = 8 OR vb_role_id = 9) THEN
		SET @vb_query = CONCAT(@vb_query," AND pd.branch_id = ",vb_branch_id, " ORDER BY pd.id DESC");
	ELSEIF vb_role_id = 5 THEN
		SET @vb_query = CONCAT(@vb_query," ORDER BY pd.id DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END$$

DELIMITER ;


























DELIMITER $$

USE `loan_application`$$

DROP FUNCTION IF EXISTS `emailEncrypt`$$

CREATE DEFINER=`dbsidbi`@`%` FUNCTION `emailEncrypt`(email VARCHAR(100)) RETURNS VARCHAR(100) CHARSET latin1
    DETERMINISTIC
BEGIN
	SET @length = LOCATE("@", email);
	SET @startLength = "***************************************************";
	SET @encryptEmail = CONCAT(SUBSTRING(email, (@length - (@length-1)), 1),SUBSTRING(@startLength, 1,@length-3),SUBSTRING(email, @length-1, @length));
	RETURN (@encryptEmail);
END$$

DELIMITER ;