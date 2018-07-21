CREATE TABLE `loan_application` .`sanction_detail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `account_no` varchar(45) DEFAULT NULL,
  `sanction_amount` double DEFAULT NULL,
  `branch` bigint(11) DEFAULT NULL,
  `transaction_no` varchar(45) DEFAULT NULL,
  `tenure` double DEFAULT NULL,
  `roi` double DEFAULT NULL,
  `sanction_authority` varchar(45) DEFAULT NULL,
  `sanction_date` datetime DEFAULT NULL,
  `application_id` bigint(11) DEFAULT NULL,
  `reference_no` varchar(45) DEFAULT NULL,
  `created_by` bigint(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `remark` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

ALTER TABLE `loan_application`.`sanction_detail` 
CHANGE COLUMN `created_by` `created_by` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `modified_by` `modified_by` VARCHAR(45) NULL DEFAULT NULL ;

// latest new
ALTER TABLE `loan_application`.`sanction_detail` 
CHANGE COLUMN `application_id` `application_id` BIGINT(11) UNSIGNED NULL DEFAULT NULL ;



CREATE TABLE `loan_application`.`disbursement_detail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(11) DEFAULT NULL,
  `disbursed_amount` double DEFAULT NULL,
  `reference_no` varchar(45) DEFAULT NULL,
  `mode` bigint(11) DEFAULT NULL,
  `disbursement_date` datetime DEFAULT NULL,
  `remark` varchar(2100) DEFAULT NULL,
  `created_by` bigint(11) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

// latest new

ALTER TABLE `loan_application`.`disbursement_detail` 
CHANGE COLUMN `created_by` `created_by` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `modified_by` `modified_by` VARCHAR(45) NULL DEFAULT NULL ,
ADD COLUMN `disbursement_authority` VARCHAR(45) NULL AFTER `is_active`,
ADD COLUMN `account_no` VARCHAR(45) NULL AFTER `disbursement_authority`,
ADD COLUMN `transaction_no` VARCHAR(45) NULL AFTER `account_no`;

ALTER TABLE `loan_application`.`disbursement_detail` 
CHANGE COLUMN `application_id` `application_id` BIGINT(11) UNSIGNED NULL DEFAULT NULL ;
