-- Create New HomeLoan Temp Table
CREATE TABLE `fp_home_loan_details_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_home_loan_details_temp_ibfk_1_new` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- Remove Unwanted Columns for HomeLoan
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN min_yearly_income_range;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN max_yearly_income_range;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN min_asset_value;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN max_asset_value;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN min_score;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN max_score;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_yearly_income_range_display;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_yearly_income_range_mandatory;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_asset_value_display;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_asset_value_mandatory;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_score_display;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_score_mandatory;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN created_date;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN modified_date;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN modified_by;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_active;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN created_by;


-- Adding new Columns for HomeLoan
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_net_monthly_income_range DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_net_monthly_income_range DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_monthly_income_range_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_monthly_income_range_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_cibil_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_cibil_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_cibil_score_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_cibil_score_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_dpds INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_dpds_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_dpds_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN current_employment_status INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_current_employment_status_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_current_employment_status_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN loan_purpose INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_loan_purpose_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_loan_purpose_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN residential_status INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_residential_status_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_residential_status_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN borrower_type INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_borrower_type_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_borrower_type_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_bank_relationship INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_bank_relationship BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_bank_relationship_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_bank_relationship_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN salary_mode INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_salary_mode_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_salary_mode_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN borr_sal_acc INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_borr_sal_acc_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_borr_sal_acc_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_ltv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_ltv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_grss_mon_income_as_home_pay_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_grss_mon_income_as_home_pay_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_grss_mon_income_as_home_pay_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_net_income_permiss_emi_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_income_permiss_emi_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_income_permiss_emi_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_time_consi_month_grss_income_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_time_consi_month_grss_income_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_time_consi_month_grss_income_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_grss_mon_income_as_home_pay_oth_thn_sal_indi DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_grss_mon_income_as_home_pay_oth_thn_sal_indi_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_grss_mon_income_as_home_pay_oth_thn_sal_indi_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_net_income_permiss_emi_oth_thn_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_income_permiss_emi_oth_thn_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_net_income_permiss_emi_oth_thn_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_time_consi_month_grss_income_oth_thn_sal_indi DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_time_consi_month_grss_income_oth_thn_sal_indi_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_time_consi_month_grss_income_oth_thn_sal_indi_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_current_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_current_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_current_job_experience_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_current_job_experience_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_total_job_experience_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_total_job_experience_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_total_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_total_job_experience BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN max_current_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN min_current_job_experience  INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN min_current_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN max_current_job_experience  INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN max_bank_relationship INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN min_total_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details` MODIFY COLUMN max_total_job_experience INTEGER(2);

ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN min_cibil_score;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN max_cibil_score;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_cibil_score_display;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN is_cibil_mandatory;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_bureau_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_bureau_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_bureau_score_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_bureau_score_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_product_master` ADD COLUMN score_model_id_coapp_id BIGINT DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_risk_score_model_coapp_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_risk_score_model_coapp_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_risk_score_model_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_risk_score_model_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_risk_score_model DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_risk_score_model_co_app DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN no_of_co_app_or_gua INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_no_of_co_app_or_gua_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_no_of_co_app_or_gua_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_consider_income_of_co_app BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_consider_income_of_co_app_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_consider_income_of_co_app_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN ltv;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN min_ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_max_age_allowed_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_max_age_allowed_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN max_age_allowed INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN assessment_method_id INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_product_master` ADD COLUMN purpose_loan_model_id LONG DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN current_employment_status;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN residential_status;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN borrower_type;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN salary_mode;
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN borr_sal_acc;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_gross_net_income BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN mon_income_type INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN foir DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_times_multiplier_income BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN mon_income_multiplier_type INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN times_multiplier INTEGER(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_ltv BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_pur_ren_cons_exp_rep_cost BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_market_value BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN ltv_for_eligibility INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_purpose_loan_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details` ADD COLUMN is_purpose_loan_mandatory BIT(1) DEFAULT NULL;

CREATE TABLE `loan_application`.`fp_parameter_mapping`
( `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, 
  `fp_product_mapping_id` BIGINT(20) UNSIGNED NOT NULL,
  `parameter_id` BIGINT(20) UNSIGNED NOT NULL,
  `type` INT(3) UNSIGNED NOT NULL,
  `is_active` BIT(1) NOT NULL, PRIMARY KEY (`id`),
  CONSTRAINT `fp_product_mapping_id_master` FOREIGN KEY (`fp_product_mapping_id`) 
  REFERENCES `loan_application`.`fp_product_master`(`fp_product_id`)
  );
ALTER TABLE `loan_application`.`fp_parameter_mapping` ADD INDEX `map_id_type_active` (`fp_product_mapping_id`, `type`, `is_active`); 



-- Temp Table Queries --------------------------------


ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_net_monthly_income_range DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_net_monthly_income_range DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_monthly_income_range_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_monthly_income_range_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_cibil_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_cibil_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_cibil_score_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_cibil_score_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_dpds INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_dpds_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_dpds_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN current_employment_status INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_current_employment_status_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_current_employment_status_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN loan_purpose INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_loan_purpose_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_loan_purpose_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN residential_status INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_residential_status_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_residential_status_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN borrower_type INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_borrower_type_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_borrower_type_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_bank_relationship INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_bank_relationship BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_bank_relationship_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_bank_relationship_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN salary_mode INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_salary_mode_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_salary_mode_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN borr_sal_acc INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_borr_sal_acc_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_borr_sal_acc_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_ltv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_ltv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_grss_mon_income_as_home_pay_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_grss_mon_income_as_home_pay_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_grss_mon_income_as_home_pay_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_net_income_permiss_emi_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_income_permiss_emi_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_income_permiss_emi_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_time_consi_month_grss_income_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_time_consi_month_grss_income_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_time_consi_month_grss_income_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_grss_mon_income_as_home_pay_oth_thn_sal_indi DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_grss_mon_income_as_home_pay_oth_thn_sal_indi_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_grss_mon_income_as_home_pay_oth_thn_sal_indi_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_net_income_permiss_emi_oth_thn_sal_indiv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_income_permiss_emi_oth_thn_sal_indiv_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_net_income_permiss_emi_oth_thn_sal_indiv_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_time_consi_month_grss_income_oth_thn_sal_indi DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_time_consi_month_grss_income_oth_thn_sal_indi_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_time_consi_month_grss_income_oth_thn_sal_indi_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_current_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_current_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_current_job_experience_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_current_job_experience_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_total_job_experience_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_total_job_experience_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_total_job_experience BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_total_job_experience BIT(1) DEFAULT NULL;


ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN max_current_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN min_current_job_experience  INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN min_current_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN max_current_job_experience  INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN max_bank_relationship INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN min_total_job_experience INTEGER(2);
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` MODIFY COLUMN max_total_job_experience INTEGER(2);


ALTER TABLE `loan_application`.`fp_home_loan_details_temp` DROP COLUMN min_cibil_score;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` DROP COLUMN max_cibil_score;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` DROP COLUMN is_cibil_score_display;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` DROP COLUMN is_cibil_mandatory;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_bureau_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_bureau_score INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_bureau_score_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_bureau_score_mandatory BIT(1) DEFAULT NULL;


ALTER TABLE `loan_application`.`fp_product_master_temp` ADD COLUMN score_model_id_coapp_id BIGINT DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_risk_score_model_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_risk_score_model_mandatory BIT(1) DEFAULT NULL;

ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_risk_score_model_coapp_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_risk_score_model_coapp_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE  `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_risk_score_model_co_app DOUBLE DEFAULT NULL;
ALTER TABLE  `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_risk_score_model DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN min_ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_ltv DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` DROP COLUMN ltv;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_max_age_allowed_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_max_age_allowed_mandatory BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN max_age_allowed INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN assessment_method_id INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_product_master_temp` ADD COLUMN purpose_loan_model_id LONG DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_gross_net_income BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN mon_income_type INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN foir DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_times_multiplier_income BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN mon_income_multiplier_type INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN times_multiplier INTEGER(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_ltv BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_pur_ren_cons_exp_rep_cost BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_market_value BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN ltv_for_eligibility INTEGER(2) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_purpose_loan_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_purpose_loan_mandatory BIT(1) DEFAULT NULL;



CREATE TABLE `loan_application`.`fp_parameter_mapping_temp`
( `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT, 
  `fp_product_mapping_id` BIGINT(20) UNSIGNED NOT NULL,
  `parameter_id` BIGINT(20) UNSIGNED NOT NULL,
  `type` INT(3) UNSIGNED NOT NULL,
  `is_active` BIT(1) NOT NULL, PRIMARY KEY (`id`),
  CONSTRAINT `fp_product_mapping_id` FOREIGN KEY (`fp_product_mapping_id`) 
  REFERENCES `loan_application`.`fp_product_master_temp`(`fp_product_id`)
  ); 

ALTER TABLE `loan_application`.`fp_parameter_mapping_temp` ADD INDEX `map_id_type_active` (`fp_product_mapping_id`, `type`, `is_active`); 