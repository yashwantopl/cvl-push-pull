#DB: loan_application
#--------------------------------------------------------------------

ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `exisitng_activity` `exisitng_activity` VARCHAR(255) NULL; 
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `msme_registration_date` `msme_uam_reg_date` DATETIME NULL,CHANGE `msme_registration_number` `msme_uam_reg_no` VARCHAR(255) CHARSET latin1 COLLATE latin1_swedish_ci NULL;  
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` DROP COLUMN `aadhar`; 
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN `repayment_months` INT(2) NULL AFTER `is_covered_under_cgtmse`, CHANGE `repaymemt_period` `repayment_years` INT(2) NULL;
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN `moratorium_period_months` INT(2) NULL AFTER `repayment_years`;
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN `declaration_date` DATETIME NULL AFTER `prop_fact_leased`, ADD COLUMN `declaration_place` VARCHAR(50) NULL AFTER `declaration_date`;
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN is_copy_of_existing_fact_addr BIT DEFAULT b'0' NULL, ADD COLUMN is_copy_of_registered_addr BIT DEFAULT b'0' NULL, ADD COLUMN activity_detail varchar(255) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `constitution_id` `constitution_id` INT(2) NULL;
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `msme_uam_reg_date` `msme_uam_reg_date` DATE NULL, CHANGE `declaration_date` `declaration_date` DATE NULL;
 
ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` ADD COLUMN `relationship_with_applicant` VARCHAR(2000) NULL AFTER `name_Of_owner`;
ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` CHANGE `particulars_of_charge` `particulars_of_charge` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL, 
ADD COLUMN `existing_charge_holder` VARCHAR(500) NULL AFTER `particulars_of_charge`;
ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` CHANGE `particulars_id` `particulars` VARCHAR(2000) NULL;
 
ALTER TABLE `loan_application`.`fs_past_performance_details` ADD COLUMN `contingent_liabilities` VARCHAR(50) NULL AFTER `compNetWorthNextYear`;

ALTER TABLE `loan_application`.`fs_corporate_sidbi_project_cost_details` ADD COLUMN particular_name VARCHAR(100) NULL, ADD COLUMN is_editable bit(1) DEFAULT b'0';

ALTER TABLE `loan_application`.`fs_sidbi_raw_material_details` ADD COLUMN `name_of_raw_material` VARCHAR(100) NULL AFTER `application_id`;

ALTER TABLE `loan_application`.`fs_sidbi_facility_details` CHANGE `rupee_term_loan` `rupee_term_loan` DOUBLE NULL;
ALTER TABLE `loan_application`.`fs_sidbi_facility_details` ADD COLUMN `currency_type` VARCHAR(10) NULL AFTER `foreign_currency`;
 