-- Create New HomeLoan Temp Table
CREATE TABLE `loan_application`.`fp_home_loan_details_temp` (
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

CREATE TABLE `loan_application`.`retail_model`
( `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(255), `business_type_id` INT(2) UNSIGNED,
 `org_id` BIGINT(20) UNSIGNED,
 `created_by` BIGINT(20) UNSIGNED,
 `created_date` DATETIME,
 `is_active` BIT(1),
 `job_id` BIGINT(20) UNSIGNED,
 `modified_by` BIGINT(20) UNSIGNED,
 `modified_date` DATETIME,
 PRIMARY KEY (`id`) 
 ); 

-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN is_approved BIT(1) DEFAULT NULL;
-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN is_deleted BIT(1) DEFAULT NULL;
-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN is_copied BIT(1) DEFAULT NULL;
-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN is_edit BIT(1) DEFAULT NULL;
-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN status_id INT(2) DEFAULT NULL;
-- ALTER TABLE `loan_application`.`retail_model` ADD COLUMN approval_date DATETIME DEFAULT NULL;

CREATE TABLE `loan_application`.`home_loan_model` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  `is_pur_ready_built_house` BIT(1) DEFAULT NULL,
  `is_pur_ready_built_independent_house` BIT(1) DEFAULT NULL,
  `is_pur_residetial_flat` BIT(1) DEFAULT NULL,
  `is_pur_residetial_flat_allotee` BIT(1) DEFAULT NULL,
  `is_pur_residetial_site` BIT(1) DEFAULT NULL,
  `is_constru_residetial_buid` BIT(1) DEFAULT NULL,
  `is_constru_expa_res_build` BIT(1) DEFAULT NULL,
  `is_constru_pur_res_site` BIT(1) DEFAULT NULL,
  `is_rep_pur_ready_built_independant` BIT(1) DEFAULT NULL,
  `is_rep_ren_imp_flat_house` BIT(1) DEFAULT NULL,
  `is_oth_ref_excess_margin_paid` BIT(1) DEFAULT NULL,
  `is_oth_loan_reimbursement_flat` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `retail_model_ibfk_1` FOREIGN KEY (`id`) REFERENCES `retail_model` (`id`)
);

ALTER TABLE `loan_application`.`retail_model` ADD COLUMN user_id BIGINT(20) DEFAULT NULL;



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
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_consider_income_of_co_app BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN no_of_co_app_or_gua INTEGER DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_no_of_co_app_or_gua_display BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_home_loan_details_temp` ADD COLUMN is_no_of_co_app_or_gua_mandatory BIT(1) DEFAULT NULL;



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


CREATE TABLE `loan_application`.`retail_model_temp` 
( `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
 `name` VARCHAR(255), `business_type_id` INT(2) UNSIGNED,
 `org_id` BIGINT(20) UNSIGNED,
 `created_by` BIGINT(20) UNSIGNED,
 `created_date` DATETIME,
 `is_active` BIT(1),
 `job_id` BIGINT(20) UNSIGNED,
 `modified_by` BIGINT(20) UNSIGNED,
 `modified_date` DATETIME,
 PRIMARY KEY (`id`) 
 );

ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN is_approved BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN is_deleted BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN is_copied BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN is_edit BIT(1) DEFAULT NULL;
ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN status_id INT(2) DEFAULT NULL;

CREATE TABLE `loan_application`.`home_loan_model_temp` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  `is_pur_ready_built_house` BIT(1) DEFAULT NULL,
  `is_pur_ready_built_independent_house` BIT(1) DEFAULT NULL,
  `is_pur_residetial_flat` BIT(1) DEFAULT NULL,
  `is_pur_residetial_flat_allotee` BIT(1) DEFAULT NULL,
  `is_pur_residetial_site` BIT(1) DEFAULT NULL,
  `is_constru_residetial_buid` BIT(1) DEFAULT NULL,
  `is_constru_expa_res_build` BIT(1) DEFAULT NULL,
  `is_constru_pur_res_site` BIT(1) DEFAULT NULL,
  `is_rep_pur_ready_built_independant` BIT(1) DEFAULT NULL,
  `is_rep_ren_imp_flat_house` BIT(1) DEFAULT NULL,
  `is_oth_ref_excess_margin_paid` BIT(1) DEFAULT NULL,
  `is_oth_loan_reimbursement_flat` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `retail_model_temp_ibfk_1` FOREIGN KEY (`id`) REFERENCES `retail_model_temp` (`id`)
);


ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN user_id BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`retail_model_temp` ADD COLUMN approval_date DATETIME DEFAULT NULL;


-- Scoring Related Changes
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(80,'AGE_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(81,'TOTAL_JOB_EXP_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(82,'CURRENT_JOB_EXP_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(83,'TOTAL_BUSI_PROFE_EXP_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(84,'RESIDENCE_TYPE_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(85,'NO_YEARS_STAY_CURR_LOC_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(86,'BUREAU_SCORE_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(87,'MARITAL_STATUS_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(88,'EMPLOYMENT_TYPE_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(89,'EMPLOYMENT_CATEG_JOB_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(90,'EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(91,'CURRENT_EMPLOYMENT_STATUS_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(92,'MIN_BANKING_RELATIONSHIP_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(93,'SPOUSE_EMPLOYEMENT_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(94,'NO_OF_DEPENDANTS_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(95,'NO_OF_APPLICANTS_HL',2,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(96,'ANNUAL_INCOME_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(97,'AVAILABLE_INCOME_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(98,'TOIR_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(99,'ADDI_INCOME_SPOUSE_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(100,'MON_INCOME_DEPENDANT_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(101,'AVG_INCREASE_INCOME_REPORT_3_YEARS_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(102,'REPAYMENT_PERIOD_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(103,'TENURE_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(104,'AGE_PROPERTY_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(105,'AVG_DEPOS_LAST_6_MONTH_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(106,'CHECQUE_BOUNSE_LAST_1_MONTH_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(107,'CHECQUE_BOUNSE_LAST_6_MONTH_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(108,'DPD_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(109,'LTV_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(110,'EMI_NMI_RATIO_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(111,'LOAN_PURPOSE_HL',3,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`) VALUES(112,'APPLICANT_NW_TO_LOAN_AMOUNT_HL',1,NOW(),TRUE);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(113,'PUR_READY_BUILT_HOUSE_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(114,'PUR_READY_BUILT_INDEPENDENT_HOUSE_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(115,'PUR_RESIDETIAL_FLAT_HL',2,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(116,'PUR_RESIDETIAL_FLAT_ALLOTEE_HL',2,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(117,'PUR_RESIDETIAL_SITE_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(118,'CONSTRU_RESIDETIAL_BUID_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(119,'CONSTRU_EXPA_RES_BUILD_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(120,'CONSTRU_PUR_RES_SITE_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(121,'REP_PUR_READY_BUILT_INDEPENDANT_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(122,'REP_REN_IMP_FLAT_HOUSE_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(123,'OTH_REF_EXCESS_MARGIN_PAID_HL',1,NOW(),TRUE,111);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`,`type`,`created_date`,`is_active`,`parent_field_id`) VALUES(124,'OTH_LOAN_REIMBURSEMENT_FLAT_HL',1,NOW(),TRUE,111);






INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','80','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','81','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','82','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','83','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','84','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','85','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','86','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','87','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','88','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','89','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','90','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','91','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','92','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','93','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','94','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','95','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','96','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','97','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','98','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','99','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','100','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','101','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','102','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','103','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','104','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','105','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','106','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','107','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','108','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','109','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','110','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','111','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','112','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','113','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','114','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','115','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','116','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','117','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','118','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','119','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','120','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','121','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','122','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','123','5','3');
INSERT INTO `scoring_sidbi`.`field_mapping` (`created_date`,`is_active`,`field_master_id`,`business_type_id`,`financial_type_id`) VALUES (NOW(),b'1','124','5','3');




ALTER TABLE `scoring_sidbi`.`scoring_model_temp` ADD COLUMN loan_purpose_model_id BIGINT(20);
ALTER TABLE `scoring_sidbi`.`scoring_model` ADD COLUMN loan_purpose_model_id BIGINT(20);
ALTER TABLE `scoring_sidbi`.`model_parameter_temp` ADD COLUMN is_consider_co_app BIT(1);
ALTER TABLE `scoring_sidbi`.`model_parameter` ADD COLUMN is_consider_co_app BIT(1);
ALTER TABLE `scoring_sidbi`.`field_master` ADD COLUMN `parent_field_id` BIGINT(20) UNSIGNED NULL AFTER `is_active`; 
ALTER TABLE `scoring_sidbi`.`field_master` CHANGE `parent_field_id` `parent_field_id` BIGINT(20) NULL, ADD CONSTRAINT `parent_field_id_fk` FOREIGN KEY (`parent_field_id`) REFERENCES `scoring_sidbi`.`field_master`(`id`); 

ALTER TABLE `scoring_sidbi`.`model_parameter_option_temp` ADD COLUMN min_sub_range DOUBLE;
ALTER TABLE `scoring_sidbi`.`model_parameter_option_temp` ADD COLUMN max_sub_range DOUBLE;
ALTER TABLE `scoring_sidbi`.`model_parameter_option` ADD COLUMN min_sub_range DOUBLE;
ALTER TABLE `scoring_sidbi`.`model_parameter_option` ADD COLUMN max_sub_range DOUBLE;

ALTER TABLE `scoring_sidbi`.`scoring_model_temp` ADD COLUMN total_co_app_score DOUBLE;
ALTER TABLE `scoring_sidbi`.`scoring_model` ADD COLUMN total_co_app_score DOUBLE;

ALTER TABLE `scoring_sidbi`.`risk_grading_temp` ADD COLUMN ltv DOUBLE;
ALTER TABLE `scoring_sidbi`.`risk_grading` ADD COLUMN ltv DOUBLE;

ALTER TABLE `scoring_sidbi`.`scoring_model_temp` ADD COLUMN ltv_scaling INT;
ALTER TABLE `scoring_sidbi`.`scoring_model` ADD COLUMN ltv_scaling INT;

ALTER TABLE `loan_application`.`application_product_audit` ADD COLUMN ltv_scaling INT(2);

ALTER TABLE `loan_eligibility`.`personal_loan_calculation` ADD COLUMN type_id INT(2);

ALTER TABLE `loan_application`.`retail_model` ADD COLUMN `retail_model_temp_ref_id` BIGINT(20) UNSIGNED NULL AFTER `user_id`, 
ADD CONSTRAINT `retail_model_temp_id_fk` FOREIGN KEY (`retail_model_temp_ref_id`) REFERENCES `loan_application`.`retail_model_temp`(`id`); 
ALTER TABLE `scoring_sidbi`.`proposal_score` ADD COLUMN ltv DOUBLE;