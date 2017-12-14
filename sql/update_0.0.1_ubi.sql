CREATE TABLE `loan_applications`.org_branch_audit (
	id BIGINT (20) NOT NULL AUTO_INCREMENT,
	application_id BIGINT (20) DEFAULT NULL,
	branch_id BIGINT (20) DEFAULT NULL,
	branch_name VARCHAR (150) DEFAULT NULL,
	org_id BIGINT(20) DEFAULT NULL,
	created_date DATETIME DEFAULT NULL,
	modified_date DATETIME DEFAULT NULL,
  	created_by BIGINT(20) DEFAULT NULL,
  	modified_by BIGINT(20) DEFAULT NULL,
  	is_active BIT(1) DEFAULT NULL,
 	PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


ALTER TABLE loan_application.`org_branch_audit` ADD COLUMN  user_org_id BIGINT(20) DEFAULT NULL;

ALTER TABLE `loan_applications`.`fp_home_loan_details` 
ADD COLUMN `min_score` DOUBLE NULL AFTER `max_tenure`,
ADD COLUMN `max_score` DOUBLE NULL AFTER `min_score`;

ALTER TABLE `loan_applications`.`fp_home_loan_details` 
ADD COLUMN `is_score_display` BIT(1) NULL DEFAULT b'1' AFTER `is_geographical_mandatory`,
ADD COLUMN `is_score_mandatory` BIT(1) NULL DEFAULT b'1' AFTER `is_score_display`;

ALTER TABLE `loan_applications`.`proposal_details` 
ADD COLUMN `user_score` DOUBLE NULL AFTER `created_date`,
ADD COLUMN `user_org_id` BIGINT(20) NULL AFTER `user_score`;

ALTER TABLE `loan_applications`.`fp_product_master` 
ADD COLUMN `user_org_id` BIGINT(1) NULL AFTER `is_matched`;

ALTER TABLE `loan_applications`.`fp_product_master` 
ADD COLUMN `score_model_id` BIGINT(1) NULL AFTER `user_org_id`;

ALTER TABLE `loan_applications`.`proposal_details` 
ADD COLUMN `user_org_id` BIGINT(1) NULL AFTER `created_date`;

ALTER TABLE `loan_application`.`proposal_details` 
ADD COLUMN `user_score` double NULL AFTER `user_org_id`;

ALTER TABLE `loan_applications`.`org_branch_audit` 
ADD COLUMN  `user_org_id` BIGINT(20) DEFAULT NULL;

ALTER TABLE `loan_applications`.`fs_loan_application_master` 
ADD COLUMN `campaign_code`  VARCHAR(45) NULL DEFAULT NULL;

ALTER TABLE `fs_loan_application_master` ADD COLUMN eligible_amnt DECIMAL(19,2);