DELIMITER $$

USE `users`$$

DROP PROCEDURE IF EXISTS `getCurrentBranchByAppIdAndOrgId`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `getCurrentBranchByAppIdAndOrgId`(IN applicationId BIGINT , IN orgId BIGINT )
BEGIN
 
    SELECT c.status INTO @status FROM connect.connect_log c WHERE c.application_id = applicationId;

-- for eligible
IF(@status != 6) THEN

SELECT bm.id , bm.code , bm.`name` , 'Current' , CASE WHEN apm.`fp_maker_id` IS NULL AND pd.`proposal_status_id`=2 THEN TRUE ELSE FALSE END AS isNotAssigned ,bm.`street_name`,(SELECT city_name FROM one_form.city WHERE id =  bm.city_id LIMIT 1) AS city,(SELECT state_name FROM one_form.state WHERE id =  bm.state_id LIMIT 1) AS state,bm.pincode ,(SELECT `organisation_name` FROM users.`user_organisation_master` WHERE user_org_id =  orgId LIMIT 1) AS bankName
	FROM loan_application.`proposal_details` pd
	LEFT JOIN users.`branch_master` bm ON pd.`branch_id`=bm.`id` AND bm.`is_active`=TRUE AND bm.`org_id`= pd.`user_org_id`
	LEFT JOIN `loan_application`.`application_proposal_mapping` apm ON apm.`application_id`=pd.`application_id` AND apm.proposal_id = pd.id AND apm.`is_active`= TRUE
WHERE pd.`is_active`=TRUE AND pd.`application_id` = applicationId AND pd.`user_org_id`=orgId	
    
UNION ALL 
SELECT bm.id , bm.code , bm.name , 'Old',NULL ,bm.street_name,(SELECT city_name FROM one_form.city WHERE id =  5 LIMIT 1) AS city,(SELECT state_name FROM one_form.state WHERE id =  bm.state_id LIMIT 1) AS state,bm.pincode,(SELECT `organisation_name` FROM users.`user_organisation_master` WHERE user_org_id =  orgId LIMIT 1) AS bankName FROM `users`.`branch_master` bm 
WHERE bm.id = ( 
	SELECT bfl.old_branch_id FROM `loan_application`.`branch_transfer_log` bfl 
	LEFT JOIN `loan_application`.`proposal_details` pd  ON 
	bfl.application_id = pd.application_id   AND bfl.fp_product_id  = pd.fp_product_id 
	AND  bfl.org_id = pd.user_org_id  AND bfl.new_branch_id = pd.branch_id  AND pd.is_active = TRUE AND bfl.is_active = TRUE 
	WHERE pd.application_id = applicationId AND pd.user_org_id = orgId ORDER BY bfl.id DESC LIMIT 1
) AND bm.is_active = TRUE; 
 
 -- for inEligible
 ELSE
 
 SELECT bm.id , bm.code , bm.`name` , 'Current',"Null", bm.`street_name`,(SELECT city_name FROM one_form.city WHERE id =  bm.city_id LIMIT 1) AS city,(SELECT state_name FROM one_form.state WHERE id =  bm.state_id LIMIT 1) AS state,bm.pincode ,(SELECT `organisation_name` FROM users.`user_organisation_master` WHERE user_org_id =  orgId LIMIT 1) AS bankName
	FROM loan_application.`ineligible_proposal_details` pd
	LEFT JOIN users.`branch_master` bm ON pd.`branch_id`=bm.`id` AND bm.`is_active`=TRUE AND bm.`org_id`= pd.`user_org_id`
WHERE pd.`is_active`=TRUE AND pd.`application_id` = applicationId AND pd.`user_org_id`=orgId	
    
UNION ALL 

SELECT bm.id , bm.code , bm.name , 'Old' ,"Null",bm.street_name,(SELECT city_name FROM one_form.city WHERE id =  bm.city_id LIMIT 1) AS city,(SELECT state_name FROM one_form.state WHERE id =  bm.state_id LIMIT 1) AS state,bm.pincode,NULL FROM `users`.`branch_master` bm 
WHERE bm.id = ( 
	SELECT bfl.old_branch_id FROM `loan_application`.`ineligible_proposal_transfer_history` bfl 
	LEFT JOIN `loan_application`.`ineligible_proposal_details` pd  ON 
	bfl.application_id = pd.application_id  WHERE pd.application_id = applicationId AND pd.user_org_id = orgId ORDER BY bfl.id DESC LIMIT 1
) AND bm.is_active = TRUE; 
 END IF;
 
 END$$

DELIMITER ;