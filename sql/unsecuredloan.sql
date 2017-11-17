DROP TABLE IF EXISTS `loan_applications`.`fs_corporate_primary_unsecured_loan_details`;

CREATE TABLE `loan_applications`.`fs_corporate_primary_unsecured_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `purpose_of_loan` VARCHAR(100) DEFAULT '',  
  `credit_rating_id` INT(2) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `corp_usl_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `loan_applications`.`fs_corporate_final_unsecured_loan_details`;

CREATE TABLE `loan_applications`.`fs_corporate_final_unsecured_loan_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `technology_type_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_technology_tied` BIT(1) DEFAULT b'0',
  `technology_patented_id` INT(2) UNSIGNED DEFAULT NULL,
  `technology_requires_upgradation_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_position_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_positioning_top_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_share_turnover_id` INT(2) UNSIGNED DEFAULT NULL,
  `india_distribution_network_id` INT(2) UNSIGNED DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` INT(2) UNSIGNED DEFAULT NULL,
  `brand_ambassador_id` INT(2) UNSIGNED DEFAULT NULL,
  `marketing_positioning_id` INT(2) UNSIGNED DEFAULT NULL,
  `product_services_perse_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_depends_majorly_on_government` BIT(1) DEFAULT b'0',
  `environment_certification_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_iso_certified` BIT(1) DEFAULT b'0',
  `accounting_systems_id` INT(2) UNSIGNED DEFAULT NULL,
  `internal_audit_id` INT(2) UNSIGNED DEFAULT NULL,
  `competence_id` INT(2) UNSIGNED DEFAULT NULL,
  `existing_share_holders_id` INT(2) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `modified_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `final_term_application_id_fk1` (`application_id`),
  CONSTRAINT `final_unsecure_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `loan_applications`.`fs_corporate_co_applicant_details`;

CREATE TABLE `loan_applications`.`fs_corporate_co_applicant_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `organisation_name` varchar(100) DEFAULT '',
  `pan` varchar(10) DEFAULT NULL,
  `landline_no` varchar(20) DEFAULT NULL,
  `registered_premise_number` varchar(200) DEFAULT NULL,
  `registered_street_name` varchar(200) DEFAULT NULL,
  `registered_land_mark` varchar(200) DEFAULT NULL,
  `registered_city_id` bigint(20) unsigned DEFAULT NULL,
  `registered_state_id` bigint(20) unsigned DEFAULT NULL,
  `registered_country_id` int(20) unsigned DEFAULT NULL,
  `registered_pincode` int(10) DEFAULT NULL,
  `same_as` bit(1) DEFAULT b'0',
  `administrative_premise_number` varchar(100) DEFAULT NULL,
  `administrative_street_name` varchar(100) DEFAULT NULL,
  `administrative_land_mark` varchar(100) DEFAULT NULL,
  `administrative_city_id` bigint(20) unsigned DEFAULT NULL,
  `administrative_state_id` int(20) unsigned DEFAULT NULL,
  `administrative_country_id` int(20) unsigned DEFAULT NULL,
  `administrative_pincode` int(10) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  `relationship_with_applicant` int(2) unsigned DEFAULT NULL,
  `aadhar_number` varchar(20) DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `corp_app_application_id_fk1` (`application_id`),
  CONSTRAINT `corp_co_app_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=260 DEFAULT CHARSET=latin1;



DROP TABLE IF EXISTS `loan_applications`.`fp_unsecure_loan_details`;

CREATE TABLE `loan_applications`.`fp_unsecure_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `denomination` INT(2) DEFAULT NULL,
  `min_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `max_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `long_term_credit_rating` INT(5) DEFAULT NULL,
  `short_term_credit_rating` INT(5) DEFAULT NULL,
  `min_age_establishment` INT(5) DEFAULT NULL,
  `max_age_establishment` INT(5) DEFAULT NULL,
  `profitability_history` INT(2) DEFAULT NULL,
  `min_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `max_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `min_debt_equity` DECIMAL(19,2) DEFAULT NULL,
  `max_debt_equity` DECIMAL(19,2) DEFAULT NULL,
  `min_collateral` INT(3) DEFAULT NULL,
  `max_collateral` INT(3) DEFAULT NULL,
  `min_networth` DECIMAL(19,2) DEFAULT NULL,
  `max_networth` DECIMAL(19,2) DEFAULT NULL,
  `uninterested_industry` BIGINT(20) DEFAULT NULL,
  `is_industry_sector_display` BIT(1) DEFAULT b'0',
  `is_industry_sector_mandatory` BIT(1) DEFAULT b'0',
  `is_investment_size_display` BIT(1) DEFAULT b'0',
  `is_investment_size_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `is_credit_rating_display` BIT(1) DEFAULT b'0',
  `is_credit_rating_mandatory` BIT(1) DEFAULT b'0',
  `is_establishment_display` BIT(1) DEFAULT b'0',
  `is_establishment_mandatory` BIT(1) DEFAULT b'0',
  `is_profitability_history_display` BIT(1) DEFAULT b'0',
  `is_profitability_history_mandatory` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_display` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` BIT(1) DEFAULT b'0',
  `is_debt_equity_display` BIT(1) DEFAULT b'0',
  `is_debt_equity_mandatory` BIT(1) DEFAULT b'0',
  `is_collateral_display` BIT(1) DEFAULT b'0',
  `is_collateral_mandatory` BIT(1) DEFAULT b'0',
  `is_networth_display` BIT(1) DEFAULT b'0',
  `is_networth_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `is_uninterested_industry_display` BIT(1) DEFAULT b'0',
  `is_uninterested_industry_mandatory` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_unsecure_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



insert into `loan_applications`.`application_sequence` (`id`, `product_id`, `sequence_number`) values('8','15','10000');

insert into `loan_applications`.`fund_provider_sequence` (`id`, `product_id`, `sequence_number`) values('8','15','10000');




CREATE TABLE `loan_applications`.`fs_corporate_unsecured_guarantor_detail` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL, `name` VARCHAR(100) DEFAULT '',
  `constitution_id` INT(10) UNSIGNED DEFAULT NULL, `pan` VARCHAR(10) DEFAULT NULL, 
  `industry` VARCHAR(100) DEFAULT '', `sector` VARCHAR(100) DEFAULT '', `profit_after_tax` VARCHAR(100) DEFAULT '',
  `address` VARCHAR(100) DEFAULT '', `contact_number` VARCHAR(20) DEFAULT '', `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,  `created_date` DATETIME DEFAULT NULL, `modified_date` DATETIME DEFAULT NULL,
  `is_active` BIT(1) , 
  PRIMARY KEY (`id`),
  CONSTRAINT `corp_unsecured_guarantor_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`));
  
  
  
  







ALTER TABLE `loan_applications`.`fs_retail_credit_cards_details` ADD COLUMN issuing_bank VARCHAR(200), ADD COLUMN year_of_issue Integer(20), ADD COLUMN year_of_expiry Integer(20), ADD COLUMN card_limit BIGINT(20);

ALTER TABLE `loan_applications`.`fs_retail_references_retail_details` ADD COLUMN relationship_with_applicant VARCHAR(200);

ALTER TABLE `loan_applications`.`fs_corporate_guarantors_corporate_details` ADD COLUMN industry_list BIGINT(20), ADD COLUMN sector_list BIGINT(20), ADD COLUMN constitution_id BIGINT(20),
ADD COLUMN pan  VARCHAR(200), ADD COLUMN profit_after_tax VARCHAR(200), ADD COLUMN address VARCHAR(200), ADD COLUMN contact_number VARCHAR(200);


