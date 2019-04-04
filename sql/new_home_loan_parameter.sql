-- Create New HomeLoan Temp Table
CREATE TABLE `fp_home_loan_details_temp` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_asset_value` decimal(19,2) DEFAULT NULL,
  `max_asset_value` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `min_score` double DEFAULT NULL,
  `max_score` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_score_display` bit(1) DEFAULT b'1',
  `is_score_mandatory` bit(1) DEFAULT b'1',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `borr_sal_acc` int(2) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_home_loan_details_temp_ibfk_1_new` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1

-- Remove Unwanted Columns for HomeLoan
ALTER TABLE `loan_application`.`fp_home_loan_details` DROP COLUMN currency;
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




