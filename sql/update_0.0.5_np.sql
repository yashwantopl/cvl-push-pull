INSERT  INTO `loan_application`.`fs_application_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) VALUES 
(1,'Open','opn',NOW(),NULL,NULL,NULL,''),
(2,'Assigned','asgn',NOW(),NULL,NULL,NULL,''),
(3,'Submitted','sbmt',NOW(),NULL,NULL,NULL,''),
(4,'Submitted to Approve','sbmta',NOW(),NULL,NULL,NULL,''),
(5,'Approved','aprv',NOW(),NULL,NULL,NULL,''),
(6,'Reverted','rvrt',NOW(),NULL,NULL,NULL,'');


ALTER TABLE `loan_application`.`fs_loan_application_master` ADD COLUMN `np_user_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_loan_application_master` ADD COLUMN `np_assignee_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_loan_application_master` ADD COLUMN `ddr_status_id` BIGINT(20) DEFAULT NULL;

ALTER TABLE `loan_application`.`application_status_audit` ADD COLUMN `np_assignee_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`application_status_audit` ADD COLUMN `ddr_status_id` BIGINT(20) DEFAULT NULL;
/*create table*/

CREATE TABLE `loan_application`.`application_status_audit` (
  `audit_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `product_id` INT(11) UNSIGNED DEFAULT NULL,
  `currency_id` INT(2) UNSIGNED DEFAULT NULL,
  `denomination_id` INT(2) UNSIGNED DEFAULT NULL,
  `name` VARCHAR(100) DEFAULT '',
  `status` INT(11) UNSIGNED DEFAULT NULL,
  `tenure` DOUBLE DEFAULT NULL,
  `amount` DECIMAL(19,2) DEFAULT NULL,
  `denomination_master_id` BIGINT(2) UNSIGNED DEFAULT NULL,
  `category_code` VARCHAR(11) DEFAULT '',
  `user_id` BIGINT(20) DEFAULT NULL,
  `is_applicant_details_filled` BIT(1) DEFAULT b'0',
  `is_applicant_primary_filled` BIT(1) DEFAULT b'0',
  `is_applicant_final_filled` BIT(1) DEFAULT b'0',
  `is_co_app1_details_filled` BIT(1) DEFAULT b'0',
  `is_co_app1_final_filled` BIT(1) DEFAULT b'0',
  `is_co_app2_details_filled` BIT(1) DEFAULT b'0',
  `is_co_app2_final_filled` BIT(1) DEFAULT b'0',
  `is_guarantor1_details_filled` BIT(1) DEFAULT b'0',
  `is_guarantor1_final_filled` BIT(1) DEFAULT b'0',
  `is_guarantor2_details_filled` BIT(1) DEFAULT b'0',
  `is_guarantor2_final_filled` BIT(1) DEFAULT b'0',
  `is_primary_upload_filled` BIT(1) DEFAULT b'0',
  `is_final_mcq_filled` BIT(1) DEFAULT b'0',
  `is_final_dpr_upload_filled` BIT(1) DEFAULT b'0',
  `is_final_upload_filled` BIT(1) DEFAULT b'0',
  `is_primary_locked` BIT(1) DEFAULT b'0',
  `is_final_locked` BIT(1) DEFAULT b'0',
  `details_filled_time` BIGINT(20) DEFAULT NULL,
  `primary_filled_time` BIGINT(20) DEFAULT NULL,
  `final_filled_time` BIGINT(20) DEFAULT NULL,
  `details_filled_count` VARCHAR(255) DEFAULT NULL,
  `primary_filled_count` VARCHAR(255) DEFAULT NULL,
  `final_filled_count` VARCHAR(255) DEFAULT NULL,
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` BIGINT(11) DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `modified_by` BIGINT(11) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  `application_code` VARCHAR(20) DEFAULT NULL,
  `campaign_code` VARCHAR(45) DEFAULT NULL,
  `mca_company_id` VARCHAR(50) DEFAULT NULL,
  `is_mca` BIT(1) DEFAULT NULL,
  `eligible_amnt` DECIMAL(19,2) DEFAULT NULL,
  `np_user_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`audit_id`),
  KEY `status` (`status`),
  KEY `denomination_master_id` (`denomination_master_id`),
  CONSTRAINT `fs_loan_application_master_ibffk_1` FOREIGN KEY (`status`) REFERENCES `fs_application_status_master` (`id`),
  CONSTRAINT `fs_loan_application_master_ibffk_2` FOREIGN KEY (`denomination_master_id`) REFERENCES `denomination_master` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=0 DEFAULT CHARSET=latin1;



/*Tigger*/
DROP TRIGGER IF EXISTS `loan_application`.`application_status_audit`;

DELIMITER $$
CREATE TRIGGER `loan_application`.application_status_audit BEFORE UPDATE
    ON `loan_application`.`fs_loan_application_master`
    FOR EACH ROW BEGIN
	IF NEW.`status` <> OLD.`status` THEN
	INSERT INTO `loan_application`.`application_status_audit` (  
	`application_id`,
	`product_id`,
	`currency_id`,
	`denomination_id`,
	`name`,
	`status`,
	`tenure`,
	`amount`,
	`denomination_master_id`,
	`category_code`,
	`user_id`,
	`is_applicant_details_filled`,
	`is_applicant_primary_filled`,
	`is_applicant_final_filled`,
	`is_co_app1_details_filled`,
	`is_co_app1_final_filled`,
	`is_co_app2_details_filled`,
	`is_co_app2_final_filled`,
	`is_guarantor1_details_filled`,
	`is_guarantor1_final_filled`,
	`is_guarantor2_details_filled`,
	`is_guarantor2_final_filled`,
	`is_primary_upload_filled`,
	`is_final_mcq_filled`,
	`is_final_dpr_upload_filled`,
	`is_final_upload_filled`,
	`is_primary_locked`,
	`is_final_locked`,
	`details_filled_time`,
	`primary_filled_time`,
	`final_filled_time`,
	`details_filled_count`,
	`primary_filled_count`,
	`final_filled_count`,
	`created_date`,
	`created_by`,
	`modified_date`,
	`modified_by`,
	`is_active`,
	`application_code`,
	`campaign_code`,
	`mca_company_id`,
	`is_mca`,
	`eligible_amnt`,
	`np_user_id`,
	np_assignee_id,
	ddr_status_id
	) 
	VALUES
	(    
	NEW.`application_id`,
	NEW.`product_id`,
	NEW.`currency_id`,
	NEW.`denomination_id`,
	NEW.`name`,
	OLD.`status`,
	NEW.`tenure`,
	NEW.`amount`,
	NEW.`denomination_master_id`,
	NEW.`category_code`,
	NEW.`user_id`,
	NEW.`is_applicant_details_filled`,
	NEW.`is_applicant_primary_filled`,
	NEW.`is_applicant_final_filled`,
	NEW.`is_co_app1_details_filled`,
	NEW.`is_co_app1_final_filled`,
	NEW.`is_co_app2_details_filled`,
	NEW.`is_co_app2_final_filled`,
	NEW.`is_guarantor1_details_filled`,
	NEW.`is_guarantor1_final_filled`,
	NEW.`is_guarantor2_details_filled`,
	NEW.`is_guarantor2_final_filled`,
	NEW.`is_primary_upload_filled`,
	NEW.`is_final_mcq_filled`,
	NEW.`is_final_dpr_upload_filled`,
	NEW.`is_final_upload_filled`,
	NEW.`is_primary_locked`,
	NEW.`is_final_locked`,
	NEW.`details_filled_time`,
	NEW.`primary_filled_time`,
	NEW.`final_filled_time`,
	NEW.`details_filled_count`,
	NEW.`primary_filled_count`,
	NEW.`final_filled_count`,
	NEW.`created_date`,
	NEW.`created_by`,
	NOW(),
	NEW.`modified_by`,
	NEW.`is_active`,
	NEW.`application_code`,
	NEW.`campaign_code`,
	NEW.`mca_company_id`,
	NEW.`is_mca`,
	NEW.`eligible_amnt`,
	NEW.`np_user_id`,
	NEW.`np_assignee_id`,
	NEW.`ddr_status_id`
         );
	END IF;	
    END$$
DELIMITER ;
