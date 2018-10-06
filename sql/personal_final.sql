ALTER TABLE `loan_application`.`fs_retail_applicant_details`
ADD COLUMN `nationality` VARCHAR(50) NULL,
ADD COLUMN `residential_status` INT(2) NULL,
ADD COLUMN `disability_type` INT(2) NULL,
ADD COLUMN `passport` VARCHAR(50) NULL,
ADD COLUMN `passport_validity` DATETIME NULL,
ADD COLUMN `voter_id` VARCHAR(50) NULL,
ADD COLUMN `residential_proof_no` VARCHAR(50) NULL,
ADD COLUMN `office_name_of_org` VARCHAR(50) NULL,
ADD COLUMN `office_email` VARCHAR(50) NULL,
ADD COLUMN `previous_employers_contact` VARCHAR(20) NULL,

ADD COLUMN `ddo_organization_type`  INT(2) UNSIGNED NULL,
ADD COLUMN `ddo_department` VARCHAR(50) NULL,
ADD COLUMN `ddo_designation` VARCHAR(50)  NULL,
ADD COLUMN `ddo_employee_no`  VARCHAR(30) NULL,
ADD COLUMN `ddo_remaining_ser_months` INT(10) UNSIGNED NULL,
ADD COLUMN `ddo_remaining_ser_yrs`  INT(10) UNSIGNED NULL,
ADD COLUMN `ddo_website`  VARCHAR(50) NULL;


ALTER TABLE `loan_application`.`fs_retail_bank_account_held_details`
CHANGE `bank_name_and_branch` `bank_name` VARCHAR(100) NULL ,
CHANGE COLUMN `account_type` `account_type` INT(2) NULL,
ADD COLUMN `branch_name` VARCHAR(50) NULL;

ALTER TABLE `loan_application`.`fs_retail_other_current_asset_details`
ADD COLUMN `asset_number` VARCHAR(50) NULL,
ADD COLUMN `remark` VARCHAR(255) NULL;

ALTER TABLE `loan_application`.`fs_retail_references_retail_details`
ADD COLUMN `pincode` BIGINT(12) UNSIGNED NULL;





CREATE TABLE `loan_application`.`fs_retail_obligation_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `obligation_head` varchar(100) DEFAULT NULL,
  `gross_amount` double DEFAULT NULL,
  `net_amount` double DEFAULT NULL,
  `periodicity` double DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_obligation_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1




ALTER TABLE `loan_application`.`fs_retail_applicant_income_details`
ADD COLUMN `salary_income_gross` DOUBLE NULL,
ADD COLUMN `house_property_gross` DOUBLE NULL,
ADD COLUMN `pgbp_gross` DOUBLE NULL,
ADD COLUMN `capital_gain_gross` DOUBLE NULL,
ADD COLUMN `other_source_gross` DOUBLE NULL,
ADD COLUMN `salary_mode` VARCHAR(50) NULL,
ADD COLUMN `house_property_mode` VARCHAR(50) NULL,
ADD COLUMN `pgbp_mode` VARCHAR(50) NULL,
ADD COLUMN `capital_gain_mode` VARCHAR(50) NULL,
ADD COLUMN `other_source_mode` VARCHAR(50) NULL,;
