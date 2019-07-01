#DB: loan_application
#--------------------------------------------------------------------

ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `exisitng_activity` `exisitng_activity` VARCHAR(255) NULL; 

ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` ADD COLUMN `relationship_with_applicant` VARCHAR(2000) NULL AFTER `name_Of_owner`;

ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` CHANGE `particulars_of_charge` `particulars_of_charge` VARCHAR(20) CHARSET utf8 COLLATE utf8_general_ci NULL, 
ADD COLUMN `existing_charge_holder` VARCHAR(500) NULL AFTER `particulars_of_charge`;

ALTER TABLE `loan_application`.`fs_sidbi_primary_collateral_security` CHANGE `particulars_id` `particulars` VARCHAR(2000) NULL; 

ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` CHANGE `msme_registration_date` `msme_uam_reg_date` DATETIME NULL, 
CHANGE `msme_registration_number` `msme_uam_reg_no` VARCHAR(255) CHARSET latin1 COLLATE latin1_swedish_ci NULL;  
