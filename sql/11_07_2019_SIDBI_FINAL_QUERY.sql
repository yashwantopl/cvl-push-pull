
DROP TABLE IF EXISTS `loan_application`.`fs_corporate_sidbi_basic_details`;

CREATE TABLE `loan_application`.`fs_corporate_sidbi_basic_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_since` date DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL,
  `associated_group` varchar(255) DEFAULT NULL,
  `commencement_date` datetime DEFAULT NULL,
  `constitution_id` int(2) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `establishment_date` datetime DEFAULT NULL,
  `exisitng_activity` varchar(255) DEFAULT NULL,
  `industry_name` varchar(255) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `land_mark` varchar(255) DEFAULT NULL,
  `landline_no` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `msme_uam_reg_date` date DEFAULT NULL,
  `msme_uam_reg_no` varchar(255) DEFAULT NULL,
  `organisation_name` varchar(255) DEFAULT NULL,
  `pincode` bigint(20) DEFAULT NULL,
  `premise_number` varchar(255) DEFAULT NULL,
  `proposed_activity` varchar(255) DEFAULT NULL,
  `sidbi_branch` varchar(255) DEFAULT NULL,
  `street_name` varchar(255) DEFAULT NULL,
  `trial_run_end_date` datetime DEFAULT NULL,
  `commence_com_op_date` datetime DEFAULT NULL,
  `is_covered_under_cgtmse` bit(1) DEFAULT NULL,
  `repayment_months` int(2) DEFAULT NULL,
  `moratorium_period_months` int(2) DEFAULT NULL,
  `exis_fact_premise_number` varchar(255) DEFAULT NULL,
  `exis_fact_street_name` varchar(255) DEFAULT NULL,
  `exis_fact_land_mark` varchar(255) DEFAULT NULL,
  `exis_fact_pincode` bigint(20) DEFAULT NULL,
  `exis_fact_Owned` bit(1) DEFAULT NULL,
  `exis_fact_ranted` bit(1) DEFAULT NULL,
  `exis_fact_leased` bit(1) DEFAULT NULL,
  `prop_fact_premise_number` varchar(255) DEFAULT NULL,
  `prop_fact_street_name` varchar(255) DEFAULT NULL,
  `prop_fact_land_mark` varchar(255) DEFAULT NULL,
  `prop_fact_pincode` bigint(20) DEFAULT NULL,
  `prop_fact_Owned` bit(1) DEFAULT NULL,
  `prop_fact_ranted` bit(1) DEFAULT NULL,
  `prop_fact_leased` bit(1) DEFAULT NULL,
  `declaration_date` date DEFAULT NULL,
  `declaration_place` varchar(50) DEFAULT NULL,
  `is_copy_of_existing_fact_addr` bit(1) DEFAULT b'0',
  `is_copy_of_registered_addr` bit(1) DEFAULT b'0',
  `activity_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_application`.`fs_corporate_sidbi_project_cost_details`;

CREATE TABLE `loan_application`.`fs_corporate_sidbi_project_cost_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `already_incurred` double DEFAULT NULL,
  `to_be_incurred` double DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `particulars_id` int(2) DEFAULT NULL,
  `particular_name` varchar(100) DEFAULT NULL,
  `other_particular` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_editable` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `FKBCFB238FDC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_sidbi_project_cost_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_application`.`fs_corporate_sidbi_means_of_finance_details`;

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
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_application`.`fs_sidbi_facility_details`;

CREATE TABLE `loan_application`.`fs_sidbi_facility_details` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(100) DEFAULT NULL,
  `rupee_term_loan` double DEFAULT NULL,
  `foreign_currency` double DEFAULT NULL,
  `currency_type` varchar(10) DEFAULT NULL,
  `inr_currency` double DEFAULT NULL,
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


DROP TABLE IF EXISTS `loan_application`.`fs_sidbi_raw_material_details`;

CREATE TABLE `loan_application`.`fs_sidbi_raw_material_details` (
  `id` bigint(100) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(100) DEFAULT NULL,
  `name_of_raw_material` varchar(100) DEFAULT NULL,
  `quantity_required` bigint(10) DEFAULT NULL,
  `import_indigenous` varchar(100) DEFAULT NULL,
  `source_suppliers` varchar(100) DEFAULT NULL,
  `payment_terms` varchar(100) DEFAULT NULL,
  `quantity_unit_cost` bigint(10) DEFAULT NULL,
  `lead_procure_time` double DEFAULT NULL,
  `availability` tinyint(1) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created_by` bigint(100) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_by` bigint(100) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_application`.`fs_sidbi_primary_collateral_security`;

CREATE TABLE `loan_application`.`fs_sidbi_primary_collateral_security` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `particulars` varchar(2000) DEFAULT NULL,
  `other_particular` varchar(2000) DEFAULT NULL,
  `name_Of_owner` varchar(2000) DEFAULT NULL,
  `relationship_with_applicant` varchar(2000) DEFAULT NULL,
  `nature` varchar(2000) DEFAULT NULL,
  `details` varchar(2000) DEFAULT NULL,
  `market_value` double DEFAULT NULL,
  `particulars_of_charge` varchar(20) DEFAULT NULL,
  `existing_charge_holder` varchar(500) DEFAULT NULL,
  `charge_offered_to_sidbi` varchar(2000) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `loan_application`.`fs_sidbi_personal_corporate_guarantee`;

CREATE TABLE `loan_application`.`fs_sidbi_personal_corporate_guarantee` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `name_of_guarantor` varchar(2000) DEFAULT NULL,
  `pan_cin_any_no` varchar(500) DEFAULT NULL,
  `net_worth` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `loan_application`.`fs_past_performance_details`;

CREATE TABLE `loan_application`.`fs_past_performance_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `netSalesPastYear1` double DEFAULT NULL,
  `netSalesPastYear2` double DEFAULT NULL,
  `netSalesPresentYear` double DEFAULT NULL,
  `netSalesNextYear` double DEFAULT NULL,
  `netProfitPastYear1` double DEFAULT NULL,
  `netProfitPastYear2` double DEFAULT NULL,
  `netProfitPresentYear` double DEFAULT NULL,
  `netProfitNextYear` double DEFAULT NULL,
  `compNetWorthPastYear1` double DEFAULT NULL,
  `compNetWorthPastYear2` double DEFAULT NULL,
  `compNetWorthPresentYear` double DEFAULT NULL,
  `compNetWorthNextYear` double DEFAULT NULL,
  `contingent_liabilities` varchar(2000) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_application`.`fs_sidbi_corporate_governance_compliance`;

CREATE TABLE `loan_application`.`fs_sidbi_corporate_governance_compliance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) NOT NULL,
  `corporate_governance_id` int(11) NOT NULL,
  `selected_option` int(1) NOT NULL,
  `updated_value` varchar(200) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;