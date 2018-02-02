
CREATE TABLE `loan_applications`.`fs_ddr_form_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`application_id`  bigint(20) NOT NULL,
`user_id` bigint(20) NOT NULL,
`godown_stockyard`  varchar(255) DEFAULT NULL,
`company_success_plan`  Text DEFAULT NULL,
`details_of_banking_arrangement` Text DEFAULT NULL,
`sales_of_automobile_division`  double DEFAULT NULL,
`others_sales`  double DEFAULT NULL,
`total_consolidated_sales`  double DEFAULT NULL,
`corporate_sale`  double DEFAULT NULL,
`corporate_sale_to_total_sale`  double DEFAULT NULL,
`corporate_sale_to_automobile_div_sale` double DEFAULT NULL,
`outside_loans` double DEFAULT NULL,
`loans_from_family_members_relative`  double DEFAULT NULL,
`fall_in_sales` Text DEFAULT NULL,
`reason_for_sales_decline`  Text DEFAULT NULL,
`negative_profit` Text DEFAULT NULL,
`fall_in_profit`  Text DEFAULT NULL,
`reason_for_profit_decline`  Text DEFAULT NULL,
`provisional_sales_figure`  Text DEFAULT NULL,
`sales_breakup_by_product`  Text DEFAULT NULL,
`is_any_seasonal_pattern_in_sales`  Text DEFAULT NULL,
`outstanding_dues_amount` varchar(255) DEFAULT NULL,
`outstanding_dues_age`  varchar(255) DEFAULT NULL,
`outstanding_dues_comment`  Text DEFAULT NULL,
`summary_of_debtors_ageing_0_30_days` double DEFAULT NULL,
`summary_of_debtors_ageing_31_60_days`  double DEFAULT NULL,
`summary_of_debtors_ageing_61_90_days`  double DEFAULT NULL,
`summary_of_debtors_ageing_91_180_days` double DEFAULT NULL,
`summary_of_debtors_ageing_greater_180_days`  double DEFAULT NULL,
`summary_of_debtors_ageing_total` double DEFAULT NULL,
`avg_debtor_turnover_period`  varchar(255) DEFAULT NULL,
`cheque_bounces_during_last_6months`  Text DEFAULT NULL,
`summary_of_debtors_ageing_comment` Text DEFAULT NULL,
`avg_turnover_period` varchar(255) DEFAULT NULL,
`creditors_comment` Text DEFAULT NULL,
`business_whether_ssi_or_not` int(20) DEFAULT NULL,
`investment_in_plant_and_machinery` double DEFAULT NULL,
`major_clients` varchar(255) DEFAULT NULL,
`credit_period_enjoyed_from_suppliers`  varchar(255) DEFAULT NULL,
`credit_period_extended_to_buyers`  varchar(255) DEFAULT NULL,
`other_source_of_income`  varchar(255) DEFAULT NULL,
`other_business_in_family_name` varchar(255) DEFAULT NULL,
`operating_add_comment` Text DEFAULT NULL,
`others_details_comment`  Text DEFAULT NULL,
`creadit_card_held_by_cust_comment` Text DEFAULT NULL,
`field_audit_report`  int(20) DEFAULT NULL,
`audited_financials_for_last_3years`  int(20) DEFAULT NULL,
`provisional_financials_for_current_year` int(20) DEFAULT NULL,
`itr_for_last_3years` int(20) DEFAULT NULL,
`sanction_letter` int(20) DEFAULT NULL,
`bank_statement_of_last_12months` int(20) DEFAULT NULL,
`debtors_list`  int(20) DEFAULT NULL,
`financial_figures` int(20) DEFAULT NULL, 
`moa_of_the_company`  int(20) DEFAULT NULL,
`pan_card_of_the_company` int(20) DEFAULT NULL,
`resolution_and_form_32for_addition_of_director`  int(20) DEFAULT NULL,
`central_sales_tax_registration_of_company` int(20) DEFAULT NULL,
`central_excise_registration_of_company` int(20) DEFAULT NULL,
`vat_registration_of_company` int(20) DEFAULT NULL,
`letter_of_Intent_from_fund_providers`  int(20) DEFAULT NULL,
`pan_card_and_residence_add_proof_of_directors` int(20) DEFAULT NULL,
`ca_certified_networth_Statement` int(20) DEFAULT NULL,
`irr_of_all_directors_for_last_2years`  int(20) DEFAULT NULL,
`list_of_directors` int(20) DEFAULT NULL,
`list_of_shareholders_and_share_holding_patter` int(20) DEFAULT NULL,
`summary_of_bservations`  Text DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY  (`id`),
UNIQUE KEY `application_id` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `loan_applications`.`fs_ddr_creditors_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id`  bigint(20) NOT NULL,
`name`  varchar(255) DEFAULT NULL,
`amount`  double DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `loan_applications`.`fs_ddr_office_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`area_in_sqft` varchar(255) DEFAULT NULL,
`no_employee` int(20) DEFAULT NULL,
`any_other_showroom` varchar(255) DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
`office_type` int(20) DEFAULT NULL,
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `loan_applications`.`fs_ddr_authorized_sign_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`name` varchar(255) DEFAULT NULL,
`designation` varchar(255) DEFAULT NULL,
`document_obtained` varchar(255) DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `loan_applications`.`fs_ddr_vehicles_owned_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`bank_name` varchar(255) DEFAULT NULL,
`vehicles_owned` double DEFAULT NULL,
`reference_no` varchar(255) DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `loan_applications`.`fs_ddr_rel_with_dbs_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`type_of_rel` varchar(255) DEFAULT NULL,
`reference_no` double DEFAULT NULL,
`comment` TEXT DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `loan_applications`.`fs_ddr_other_bank_loan_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`type_of_rel` varchar(255) DEFAULT NULL,
`reference_no` double DEFAULT NULL,
`comment` TEXT DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `loan_applications`.`fs_ddr_credit_card_details` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`fs_ddr_form_id` bigint(20) NOT NULL,
`bank_name` varchar(255) DEFAULT NULL,
`credit_card` double DEFAULT NULL,
`reference_no` varchar(255) DEFAULT NULL,
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modify_by` bigint(20) DEFAULT NULL,
`modify_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `loan_applications`.`fs_ddr_financial_summary` (
  `id` BIGINT(20) unsigned NOT NULL auto_increment,
  `fs_ddr_form_id` BIGINT(20) NULL,
  `perc_of_sales_of_anchor_pro` VARCHAR(45) NULL,
  `sales_of_anchor_pro` VARCHAR(45) NULL,
  `financial_year` VARCHAR(45) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` datetime NULL,
  `modify_by` BIGINT(20) NULL,
  `modify_date` datetime NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `loan_applications`.`fs_ddr_family_directors_details` (
  `id` BIGINT(20) unsigned NOT NULL auto_increment,
  `fs_ddr_form_id` BIGINT(20) NULL,
  `background_id`BIGINT(20) NULL,	
  `name` VARCHAR(45) NULL,
  `marital_status` INT NULL,
  `is_house_owned` VARCHAR(45) NULL,
  `address_of_other_property` VARCHAR(45) NULL,
  `name_occupation_of_spouse` VARCHAR(45) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` DATETIME NULL,
  `modify_by` BIGINT(20) NULL,
  `modify_date` DATETIME NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;









