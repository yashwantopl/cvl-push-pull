DB: loan_application
--------------------------------------------------------------------

CREATE TABLE `loan_application`.`fs_corporate_sidbi_basic_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `aadhar` VARCHAR(255) DEFAULT NULL,
  `activity_since` VARCHAR(255) DEFAULT NULL,
  `application_id` BIGINT(20) DEFAULT NULL,
  `associated_group` VARCHAR(255) DEFAULT NULL,
  `commencement_date` DATETIME DEFAULT NULL,
  `constitution_id` BIGINT(20) DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `email` VARCHAR(255) DEFAULT NULL,
  `establishment_date` DATETIME DEFAULT NULL,
  `exisitng_activity` INT(11) DEFAULT NULL,
  `existing` VARCHAR(255) DEFAULT NULL,
  `factory_address` VARCHAR(255) DEFAULT NULL,
  `industry_id` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `land_mark` VARCHAR(255) DEFAULT NULL,
  `landline_no` VARCHAR(255) DEFAULT NULL,
  `mobile` VARCHAR(255) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `msme_registration_date` DATETIME DEFAULT NULL,
  `msme_registration_number` VARCHAR(255) DEFAULT NULL,
  `organisation_name` VARCHAR(255) DEFAULT NULL,
  `pincode` BIGINT(20) DEFAULT NULL,
  `premise_number` VARCHAR(255) DEFAULT NULL,
  `proposed` VARCHAR(255) DEFAULT NULL,
  `proposed_activity` VARCHAR(255) DEFAULT NULL,
  `sidbi_branch` VARCHAR(255) DEFAULT NULL,
  `street_name` VARCHAR(255) DEFAULT NULL,
  `trial_run_end_date` DATETIME DEFAULT NULL,
  `is_covered_under_cgtmse` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `loan_application`.`fs_corporate_sidbi_means_of_finance_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `already_incurred` double DEFAULT NULL,
  `to_be_incurred` double DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `particulars_id` int(2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


CREATE TABLE `loan_application`.`fs_corporate_sidbi_project_cost_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `already_incurred` double DEFAULT NULL,
  `to_be_incurred` double DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `particulars_id` int(2) DEFAULT NULL,
  `other_particular` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBCFB238FDC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_sidbi_project_cost_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
);


CREATE TABLE `loan_application`.`fs_sidbi_primary_collateral_security` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,  
  `particulars_id` INT(2) DEFAULT NULL,
  `other_particular` VARCHAR(2000) DEFAULT NULL,
  `name_Of_owner` VARCHAR(2000) DEFAULT NULL,
  `nature` VARCHAR(2000) DEFAULT NULL,
  `details` VARCHAR(2000) DEFAULT NULL,
  `market_value` DOUBLE DEFAULT NULL,
  `particulars_of_charge` VARCHAR(2000) DEFAULT NULL,
  `charge_offered_to_sidbi` VARCHAR(2000) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,  
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `loan_application`.`fs_sidbi_personal_corporate_guarantee` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,    
  `name_of_guarantor` VARCHAR(2000) DEFAULT NULL,
  `pan_cin_any_no` VARCHAR(500) DEFAULT NULL,
  `net_worth` DOUBLE DEFAULT NULL,  
  `is_active` BIT(1) DEFAULT NULL,  
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `loan_application`.`fs_sidbi_raw_material_details` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(100) DEFAULT NULL,
  `quantity_required` bigint(10) DEFAULT NULL,
  `import_indigenous` varchar(100) DEFAULT NULL,
  `source_suppliers` varchar(100) DEFAULT NULL,
  `payment_terms` varchar(100) DEFAULT NULL,
  `quantity_unit_cost` bigint(10) DEFAULT NULL,
  `lead_procure_time` int(2) DEFAULT NULL,
  `availability` tinyint(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created_by` bigint(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_by` bigint(100) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `loan_application`.`fs_sidbi_facility_details` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(100) DEFAULT NULL,
  `rupee_term_loan` bigint(10) DEFAULT NULL,
  `foreign_currency` double DEFAULT NULL,
  `working_capital_fund` double DEFAULT NULL,
  `working_capital_non_fund` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created_by` bigint(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_by` bigint(100) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `loan_application`.`fs_past_performance_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `netSalesPastYear1` bigint(20) DEFAULT NULL,
  `netSalesPastYear2` bigint(20) DEFAULT NULL,
  `netSalesPresentYear` bigint(20) DEFAULT NULL,
  `netSalesNextYear` bigint(20) DEFAULT NULL,
  `netProfitPastYear1` bigint(20) DEFAULT NULL,
  `netProfitPastYear2` bigint(20) DEFAULT NULL,
  `netProfitPresentYear` bigint(20) DEFAULT NULL,
  `netProfitNextYear` bigint(20) DEFAULT NULL,
  `compNetWorthPastYear1` bigint(20) DEFAULT NULL,
  `compNetWorthPastYear2` bigint(20) DEFAULT NULL,
  `compNetWorthPresentYear` bigint(20) DEFAULT NULL,
  `compNetWorthNextYear` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `fs_sidbi_corporate_governance_compliance` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) NOT NULL,
  `corporate_governance_id` INT(11) NOT NULL,
  `selected_option` INT(1) NOT NULL,
  `updated_value` VARCHAR(200) DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
);


ALTER TABLE loan_application.fs_corporate_sidbi_basic_details ADD COLUMN repaymemt_period INT NULL AFTER is_covered_under_cgtmse; 

ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` DROP COLUMN `existing`, DROP COLUMN `factory_address`, DROP COLUMN `proposed`; 

ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` 
ADD COLUMN `exis_fact_premise_number` VARCHAR(255) NULL AFTER `repaymemt_period`, ADD COLUMN `exis_fact_street_name` VARCHAR(255) NULL AFTER `exis_fact_premise_number`, 
ADD COLUMN `exis_fact_land_mark` VARCHAR(255) NULL AFTER `exis_fact_street_name`, ADD COLUMN `exis_fact_pincode` BIGINT(20) NULL AFTER `exis_fact_land_mark`, 
ADD COLUMN `exis_fact_Owned` BIT(1) NULL AFTER `exis_fact_pincode`, ADD COLUMN `exis_fact_ranted` BIT(1) NULL AFTER `exis_fact_Owned`, ADD COLUMN `exis_fact_leased` BIT(1) NULL AFTER `exis_fact_ranted`, ADD COLUMN `prop_fact_premise_number` VARCHAR(255) NULL AFTER `exis_fact_leased`, ADD COLUMN `prop_fact_street_name` VARCHAR(255) NULL AFTER `prop_fact_premise_number`, 
ADD COLUMN `prop_fact_land_mark` VARCHAR(255) NULL AFTER `prop_fact_street_name`, ADD COLUMN `prop_fact_pincode` BIGINT(20) NULL AFTER `prop_fact_land_mark`, 
ADD COLUMN `prop_fact_Owned` BIT(1) NULL AFTER `prop_fact_pincode`, ADD COLUMN `prop_fact_ranted` BIT(1) NULL AFTER `prop_fact_Owned`, 
ADD COLUMN `prop_fact_leased` BIT(1) NULL AFTER `prop_fact_ranted`;

ALTER TABLE `loan_application`.`fs_sidbi_raw_material_details` ADD COLUMN `name_of_raw_material` VARCHAR(100) NULL AFTER `application_id`;