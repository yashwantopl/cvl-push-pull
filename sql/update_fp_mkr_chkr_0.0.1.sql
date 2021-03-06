CREATE TABLE `loan_application`.`fp_product_master_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `fp_name` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `is_parameter_filled` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `product_code` varchar(20) DEFAULT NULL,
  `is_matched` bit(1) DEFAULT NULL,
  `user_org_id` bigint(1) DEFAULT NULL,
  `score_model_id` bigint(1) DEFAULT NULL,
  `org_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=440 DEFAULT CHARSET=latin1;



CREATE TABLE `fp_wc_tl_details_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `denomination` int(2) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `long_term_credit_rating` int(5) DEFAULT NULL,
  `short_term_credit_rating` int(5) DEFAULT NULL,
  `min_age_establishment` int(5) DEFAULT NULL,
  `max_age_establishment` int(5) DEFAULT NULL,
  `profitability_history` int(2) DEFAULT NULL,
  `min_past_turnover` decimal(19,2) DEFAULT NULL,
  `max_past_turnover` decimal(19,2) DEFAULT NULL,
  `min_debt_equity` decimal(19,2) DEFAULT NULL,
  `max_debt_equity` decimal(19,2) DEFAULT NULL,
  `min_collateral` decimal(19,2) DEFAULT NULL,
  `max_collateral` decimal(19,2) DEFAULT NULL,
  `min_networth` decimal(19,2) DEFAULT NULL,
  `max_networth` decimal(19,2) DEFAULT NULL,
  `uninterested_industry` bigint(20) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT b'0',
  `is_industry_sector_mandatory` bit(1) DEFAULT b'0',
  `is_investment_size_display` bit(1) DEFAULT b'0',
  `is_investment_size_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_credit_rating_display` bit(1) DEFAULT b'0',
  `is_credit_rating_mandatory` bit(1) DEFAULT b'0',
  `is_establishment_display` bit(1) DEFAULT b'0',
  `is_establishment_mandatory` bit(1) DEFAULT b'0',
  `is_profitability_history_display` bit(1) DEFAULT b'0',
  `is_profitability_history_mandatory` bit(1) DEFAULT b'0',
  `is_past_year_turnover_display` bit(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` bit(1) DEFAULT b'0',
  `is_debt_equity_display` bit(1) DEFAULT b'0',
  `is_debt_equity_mandatory` bit(1) DEFAULT b'0',
  `is_collateral_display` bit(1) DEFAULT b'0',
  `is_collateral_mandatory` bit(1) DEFAULT b'0',
  `is_networth_display` bit(1) DEFAULT b'0',
  `is_networth_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_uninterested_industry_display` bit(1) DEFAULT b'0',
  `is_uninterested_industry_mandatory` bit(1) DEFAULT b'0',
  `min_current_ratio` decimal(19,2) DEFAULT '0.00',
  `max_current_ratio` decimal(19,2) DEFAULT '0.00',
  `is_current_ratio_display` bit(1) DEFAULT b'0',
  `is_current_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `max_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `is_interest_coverage_display` bit(1) DEFAULT b'0',
  `is_interest_coverage_mandatory` bit(1) DEFAULT b'0',
  `min_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `max_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `is_tol_tnw_display` bit(1) DEFAULT b'0',
  `is_tol_tnw_mandatory` bit(1) DEFAULT b'0',
  `min_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `max_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `is_turnover_ratio_display` bit(1) DEFAULT b'0',
  `is_turnover_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `max_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `is_gross_cash_accurals_ratio_display` bit(1) DEFAULT b'0',
  `is_gross_cash_accurals_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `max_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `is_customer_concentration_display` bit(1) DEFAULT b'0',
  `is_customer_concentration_mandatory` bit(1) DEFAULT b'0',
  `min_risk_model_score` int(2) DEFAULT '0',
  `max_risk_model_score` int(2) DEFAULT '0',
  `is_risk_model_score_display` bit(1) DEFAULT b'0',
  `is_risk_model_score_mandatory` bit(1) DEFAULT b'0',
  `net_worth` int(2) DEFAULT NULL,
  `min_cheque_bounced` int(2) DEFAULT '0',
  `max_cheque_bounced` int(2) DEFAULT '0',
  `is_cheque_bounced_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_mandatory` bit(1) DEFAULT b'0',
  `min_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `max_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `is_cheque_bounced_last_six_months_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_last_six_months_mandatory` bit(1) DEFAULT b'0',
  `ddr_flow` int(2) DEFAULT NULL,
  `individual_cibil` int(2) DEFAULT '0',
  `is_individual_cibil_display` bit(1) DEFAULT b'0',
  `is_individual_cibil_mandatory` bit(1) DEFAULT b'0',
  `commercial_cibil` int(2) DEFAULT '0',
  `is_Commercial_cibil_display` bit(1) DEFAULT b'0',
  `is_Commercial_cibil_mandatory` bit(1) DEFAULT b'0',
  `assessment_method_id` int(4) DEFAULT NULL,
  `is_approved`  bit(1) DEFAULT b'0',
  `is_deleted`  bit(1) DEFAULT b'0',
  `is_copied`  bit(1) DEFAULT b'0',
  `is_edit`  bit(1) DEFAULT b'0',
  `status_id`  bigint(20) DEFAULT NULL,
  `job_id`   bigint(20) DEFAULT NULL,
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_wc_tl_details_temp_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



CREATE TABLE `fp_working_capital_details_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `denomination` int(2) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `long_term_credit_rating` int(5) DEFAULT NULL,
  `short_term_credit_rating` int(5) DEFAULT NULL,
  `min_age_establishment` int(5) DEFAULT NULL,
  `max_age_establishment` int(5) DEFAULT NULL,
  `profitability_history` int(2) DEFAULT NULL,
  `min_past_turnover` decimal(19,2) DEFAULT NULL,
  `max_past_turnover` decimal(19,2) DEFAULT NULL,
  `min_debt_equity` decimal(19,2) DEFAULT NULL,
  `max_debt_equity` decimal(19,2) DEFAULT NULL,
  `min_collateral` decimal(19,2) DEFAULT NULL,
  `max_collateral` decimal(19,2) DEFAULT NULL,
  `min_networth` decimal(19,2) DEFAULT NULL,
  `max_networth` decimal(19,2) DEFAULT NULL,
  `uninterested_industry` bigint(20) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT b'0',
  `is_industry_sector_mandatory` bit(1) DEFAULT b'0',
  `is_investment_size_display` bit(1) DEFAULT b'0',
  `is_investment_size_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_credit_rating_display` bit(1) DEFAULT b'0',
  `is_credit_rating_mandatory` bit(1) DEFAULT b'0',
  `is_establishment_display` bit(1) DEFAULT b'0',
  `is_establishment_mandatory` bit(1) DEFAULT b'0',
  `is_profitability_history_display` bit(1) DEFAULT b'0',
  `is_profitability_history_mandatory` bit(1) DEFAULT b'0',
  `is_past_year_turnover_display` bit(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` bit(1) DEFAULT b'0',
  `is_debt_equity_display` bit(1) DEFAULT b'0',
  `is_debt_equity_mandatory` bit(1) DEFAULT b'0',
  `is_collateral_display` bit(1) DEFAULT b'0',
  `is_collateral_mandatory` bit(1) DEFAULT b'0',
  `is_networth_display` bit(1) DEFAULT b'0',
  `is_networth_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_uninterested_industry_display` bit(1) DEFAULT b'0',
  `is_uninterested_industry_mandatory` bit(1) DEFAULT b'0',
  `min_current_ratio` decimal(19,2) DEFAULT '0.00',
  `max_current_ratio` decimal(19,2) DEFAULT '0.00',
  `is_current_ratio_display` bit(1) DEFAULT b'0',
  `is_current_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `max_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `is_interest_coverage_display` bit(1) DEFAULT b'0',
  `is_interest_coverage_mandatory` bit(1) DEFAULT b'0',
  `min_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `max_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `is_tol_tnw_display` bit(1) DEFAULT b'0',
  `is_tol_tnw_mandatory` bit(1) DEFAULT b'0',
  `min_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `max_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `is_turnover_ratio_display` bit(1) DEFAULT b'0',
  `is_turnover_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `max_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `is_gross_cash_accurals_ratio_display` bit(1) DEFAULT b'0',
  `is_gross_cash_accurals_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `max_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `is_customer_concentration_display` bit(1) DEFAULT b'0',
  `is_customer_concentration_mandatory` bit(1) DEFAULT b'0',
  `min_risk_model_score` int(2) DEFAULT '0',
  `max_risk_model_score` int(2) DEFAULT '0',
  `is_risk_model_score_display` bit(1) DEFAULT b'0',
  `is_risk_model_score_mandatory` bit(1) DEFAULT b'0',
  `net_worth` int(2) DEFAULT NULL,
  `min_cheque_bounced` int(2) DEFAULT '0',
  `max_cheque_bounced` int(2) DEFAULT '0',
  `is_cheque_bounced_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_mandatory` bit(1) DEFAULT b'0',
  `min_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `max_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `is_cheque_bounced_last_six_months_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_last_six_months_mandatory` bit(1) DEFAULT b'0',
  `ddr_flow` int(2) DEFAULT NULL,
  `individual_cibil` int(2) DEFAULT '0',
  `is_individual_cibil_display` bit(1) DEFAULT b'0',
  `is_individual_cibil_mandatory` bit(1) DEFAULT b'0',
  `commercial_cibil` int(2) DEFAULT '0',
  `is_Commercial_cibil_display` bit(1) DEFAULT b'0',
  `is_Commercial_cibil_mandatory` bit(1) DEFAULT b'0',
  `assessment_method_id` int(4) DEFAULT NULL,
  `is_approved`  bit(1) DEFAULT b'0',
  `is_deleted`  bit(1) DEFAULT b'0',
  `is_copied`  bit(1) DEFAULT b'0',
  `is_edit`  bit(1) DEFAULT b'0',
  `status_id`  bigint(20) DEFAULT NULL,
  `job_id`   bigint(20) DEFAULT NULL,
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_working_capital_details_temp_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `fp_term_loan_details_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `denomination` int(2) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `long_term_credit_rating` int(5) DEFAULT NULL,
  `short_term_credit_rating` int(5) DEFAULT NULL,
  `min_age_establishment` int(5) DEFAULT NULL,
  `max_age_establishment` int(5) DEFAULT NULL,
  `profitability_history` int(2) DEFAULT NULL,
  `min_past_turnover` decimal(19,2) DEFAULT NULL,
  `max_past_turnover` decimal(19,2) DEFAULT NULL,
  `min_debt_equity` decimal(19,2) DEFAULT NULL,
  `max_debt_equity` decimal(19,2) DEFAULT NULL,
  `min_collateral` decimal(19,2) DEFAULT NULL,
  `max_collateral` decimal(19,2) DEFAULT NULL,
  `min_networth` decimal(19,2) DEFAULT NULL,
  `max_networth` decimal(19,2) DEFAULT NULL,
  `uninterested_industry` bigint(20) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT b'0',
  `is_industry_sector_mandatory` bit(1) DEFAULT b'0',
  `is_investment_size_display` bit(1) DEFAULT b'0',
  `is_investment_size_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_credit_rating_display` bit(1) DEFAULT b'0',
  `is_credit_rating_mandatory` bit(1) DEFAULT b'0',
  `is_establishment_display` bit(1) DEFAULT b'0',
  `is_establishment_mandatory` bit(1) DEFAULT b'0',
  `is_profitability_history_display` bit(1) DEFAULT b'0',
  `is_profitability_history_mandatory` bit(1) DEFAULT b'0',
  `is_past_year_turnover_display` bit(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` bit(1) DEFAULT b'0',
  `is_debt_equity_display` bit(1) DEFAULT b'0',
  `is_debt_equity_mandatory` bit(1) DEFAULT b'0',
  `is_collateral_display` bit(1) DEFAULT b'0',
  `is_collateral_mandatory` bit(1) DEFAULT b'0',
  `is_networth_display` bit(1) DEFAULT b'0',
  `is_networth_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `is_uninterested_industry_display` bit(1) DEFAULT b'0',
  `is_uninterested_industry_mandatory` bit(1) DEFAULT b'0',
  `min_current_ratio` decimal(19,2) DEFAULT '0.00',
  `max_current_ratio` decimal(19,2) DEFAULT '0.00',
  `is_current_ratio_display` bit(1) DEFAULT b'0',
  `is_current_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `max_interest_coverage` decimal(19,2) DEFAULT '0.00',
  `is_interest_coverage_display` bit(1) DEFAULT b'0',
  `is_interest_coverage_mandatory` bit(1) DEFAULT b'0',
  `min_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `max_tol_tnw` decimal(19,2) DEFAULT '0.00',
  `is_tol_tnw_display` bit(1) DEFAULT b'0',
  `is_tol_tnw_mandatory` bit(1) DEFAULT b'0',
  `min_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `max_turnover_ratio` decimal(19,2) DEFAULT '0.00',
  `is_turnover_ratio_display` bit(1) DEFAULT b'0',
  `is_turnover_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `max_gross_cash_accurals_ratio` decimal(19,2) DEFAULT '0.00',
  `is_gross_cash_accurals_ratio_display` bit(1) DEFAULT b'0',
  `is_gross_cash_accurals_ratio_mandatory` bit(1) DEFAULT b'0',
  `min_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `max_customer_concentration` decimal(19,2) DEFAULT '0.00',
  `is_customer_concentration_display` bit(1) DEFAULT b'0',
  `is_customer_concentration_mandatory` bit(1) DEFAULT b'0',
  `min_risk_model_score` int(2) DEFAULT '0',
  `max_risk_model_score` int(2) DEFAULT '0',
  `is_risk_model_score_display` bit(1) DEFAULT b'0',
  `is_risk_model_score_mandatory` bit(1) DEFAULT b'0',
  `net_worth` int(2) DEFAULT NULL,
  `min_cheque_bounced` int(2) DEFAULT '0',
  `max_cheque_bounced` int(2) DEFAULT '0',
  `is_cheque_bounced_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_mandatory` bit(1) DEFAULT b'0',
  `min_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `max_cheque_bounced_last_six_months` int(2) DEFAULT '0',
  `is_cheque_bounced_last_six_months_display` bit(1) DEFAULT b'0',
  `is_cheque_bounced_last_six_months_mandatory` bit(1) DEFAULT b'0',
  `ddr_flow` int(2) DEFAULT NULL,
  `individual_cibil` int(2) DEFAULT '0',
  `is_individual_cibil_display` bit(1) DEFAULT b'0',
  `is_individual_cibil_mandatory` bit(1) DEFAULT b'0',
  `commercial_cibil` int(2) DEFAULT '0',
  `is_Commercial_cibil_display` bit(1) DEFAULT b'0',
  `is_Commercial_cibil_mandatory` bit(1) DEFAULT b'0',
  `assessment_method_id` int(4) DEFAULT NULL,
   `is_approved`  bit(1) DEFAULT b'0',
  `is_deleted`  bit(1) DEFAULT b'0',
  `is_copied`  bit(1) DEFAULT b'0',
  `is_edit`  bit(1) DEFAULT b'0',
  `status_id`  bigint(20) DEFAULT NULL,
  `job_id`   bigint(20) DEFAULT NULL,
  `approval_date` datetime DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_term_loan_details_temp_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



  ALTER TABLE loan_application.fp_term_loan_details
  ADD job_id bigint(20) DEFAULT NULL;
  
  
  ALTER TABLE loan_application.fp_wc_tl_details
  ADD job_id bigint(20) DEFAULT NULL;
  
  
  ALTER TABLE loan_application.fp_working_capital_details
  ADD job_id bigint(20) DEFAULT NULL;
  
  

CREATE TABLE `loan_application`.`industry_sector_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `industry_id` bigint(20) DEFAULT NULL,
  `sector_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `fp_product_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `fp_product_id` (`fp_product_id`),
  CONSTRAINT `industry_sector_details_temp_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `industry_sector_details_temp_ibfk_2` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41791 DEFAULT CHARSET=latin1;

CREATE TABLE `loan_application`.`fp_geographical_state_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) DEFAULT NULL,
  `fp_product_master` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_state_details_temp_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5952 DEFAULT CHARSET=latin1;


CREATE TABLE `loan_application`.`fp_geographical_country_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_id` bigint(10) DEFAULT NULL,
  `fp_product_master` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_country_details_temp_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1402 DEFAULT CHARSET=latin1;


CREATE TABLE `loan_application`.`fp_geographical_city_details_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_id` bigint(20) DEFAULT NULL,
  `fp_product_master_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master_id`),
  CONSTRAINT `fp_geographical_city_details_temp_ibfk_1` FOREIGN KEY (`fp_product_master_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=402452 DEFAULT CHARSET=latin1;

CREATE TABLE `loan_application`.`fp_negative_industry_temp` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `industry_id` bigint(20) unsigned DEFAULT NULL,
  `fp_product_master_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master_id` (`fp_product_master_id`),
  CONSTRAINT `fp_negative_industry_temp_ibfk_1` FOREIGN KEY (`fp_product_master_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=latin1;


  