alter table `loan_applications`.`fs_corporate_applicant_details` add
(gstin varchar(25), email varchar(255), aadhar varchar(20), credit_rating_id int(2) unsigned,
cont_liability_fy_amt decimal(19,2) default '0.00',
cont_liability_sy_amt decimal(19,2) default '0.00',
cont_liability_ty_amt decimal(19,2) default '0.00',
cont_liability_year int(4) unsigned,
not_applicable bit(1) default null);


CREATE TABLE `loan_applications`.`fs_corporate_primary_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `loan_amount` decimal(19,2) DEFAULT NULL,
  `business_asset_amt` decimal(19,2) DEFAULT NULL,
  `wc_amt` decimal(19,2) DEFAULT NULL,
  `other_amt` decimal(19,2) DEFAULT NULL,
  `have_collateral_security` bigint(10) DEFAULT NULL,
  `collateral_security_amt` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `loan_applications`.`fs_corporate_final_mcq_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `technology_type_id` int(2) unsigned DEFAULT NULL,
  `is_technology_tied` bit(1) DEFAULT b'0',
  `technology_patented_id` int(2) unsigned DEFAULT NULL,
  `technology_requires_upgradation_id` int(2) unsigned DEFAULT NULL,
  `market_position_id` int(2) unsigned DEFAULT NULL,
  `market_positioning_top_id` int(2) unsigned DEFAULT NULL,
  `market_share_turnover_id` int(2) unsigned DEFAULT NULL,
  `india_distribution_network_id` int(2) unsigned DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` int(2) unsigned DEFAULT NULL,
  `brand_ambassador_id` int(2) unsigned DEFAULT NULL,
  `marketing_positioning_id` int(2) unsigned DEFAULT NULL,
  `product_services_perse_id` int(2) unsigned DEFAULT NULL,
  `is_depends_majorly_on_government` bit(1) DEFAULT b'0',
  `environment_certification_id` int(2) unsigned DEFAULT NULL,
  `is_iso_certified` bit(1) DEFAULT b'0',
  `accounting_systems_id` int(2) unsigned DEFAULT NULL,
  `internal_audit_id` int(2) unsigned DEFAULT NULL,
  `competence_id` int(2) unsigned DEFAULT NULL,
  `existing_share_holders_id` int(2) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  `technology_risk_id` int(2) unsigned DEFAULT NULL,
  `customer_quality` int(2) unsigned DEFAULT NULL,
  `supplier_quality` int(2) unsigned DEFAULT NULL,
  `sustainability_product` int(2) unsigned DEFAULT NULL,
  `order_book_position` int(2) unsigned DEFAULT NULL,
  `employee_relations` int(2) unsigned DEFAULT NULL,
  `product_seasonality` int(2) unsigned DEFAULT NULL,
  `impact_on_operating_margins` int(2) unsigned DEFAULT NULL,
  `environmental_impact` int(2) unsigned DEFAULT NULL,
  `accounting_quality` int(2) unsigned DEFAULT NULL,
  `financial_restructuring_history` int(2) unsigned DEFAULT NULL,
  `integrity` int(2) unsigned DEFAULT NULL,
  `business_commitment` int(2) unsigned DEFAULT NULL,
  `management_competence` int(2) unsigned DEFAULT NULL,
  `business_experience` int(2) unsigned DEFAULT NULL,
  `succession_planning` int(2) unsigned DEFAULT NULL,
  `financial_strength` int(2) unsigned DEFAULT NULL,
  `financial_support` int(2) unsigned DEFAULT NULL,
  `ability_to_raise_funds` int(2) unsigned DEFAULT NULL,
  `intra_company` int(2) unsigned DEFAULT NULL,
  `internal_control` int(2) unsigned DEFAULT NULL,
  `credittrack_record` int(2) unsigned DEFAULT NULL,
  `status_of_project_clearances` int(2) unsigned DEFAULT NULL,
  `status_of_financial_closure` int(2) unsigned DEFAULT NULL,
  `infrastructure_availability` int(2) unsigned DEFAULT NULL,
  `construction_contract` int(2) unsigned DEFAULT NULL,
  `number_of_cheques` int(2) unsigned DEFAULT NULL,
  `number_of_times_dp` int(2) unsigned DEFAULT NULL,
  `cumulative_no_of_days_dp` int(2) unsigned DEFAULT NULL,
  `compliance_with_sanctioned` int(2) unsigned DEFAULT NULL,
  `delay_in_receipt` int(2) unsigned DEFAULT NULL,
  `delay_in_submission` int(2) unsigned DEFAULT NULL,
  `number_of_lc` int(2) unsigned DEFAULT NULL,
  `unhedged_foreign_currency` int(2) unsigned DEFAULT NULL,
  `projected_debt_service` int(2) unsigned DEFAULT NULL,
  `internal_rate_return` int(2) unsigned DEFAULT NULL,
  `sensititivity_analysis` int(2) unsigned DEFAULT NULL,
  `variance_in_projected_sales` int(2) unsigned DEFAULT NULL,
  `progress_reports` int(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `final_application_id_fk1` (`application_id`),
  CONSTRAINT `final_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



alter table `loan_applications`.`fs_corporate_director_background_details` add (mobile varchar(20), dob datetime);
ALTER TABLE `loan_application`.`fs_corporate_director_background_details` 
ADD COLUMN `pincode` VARCHAR(45) NULL AFTER `appointment_date`,
ADD COLUMN `state_code` VARCHAR(45) NULL AFTER `pincode`,
ADD COLUMN `city` VARCHAR(45) NULL AFTER `state_code`;



CREATE TABLE `loan_applications`.`fs_ddr_existing_banker_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fs_ddr_form_id` bigint(20) DEFAULT NULL,
  `financial_institution_name` varchar(100) DEFAULT NULL,
  `relationship_since` int(4) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modify_by` bigint(20) DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


