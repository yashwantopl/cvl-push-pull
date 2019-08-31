
CREATE TABLE `loan_application`.`fp_auto_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,    
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,  
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,      
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',  
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',    
  `min_net_monthly_income_range` DOUBLE DEFAULT NULL,
  `max_net_monthly_income_range` DOUBLE DEFAULT NULL,
  `is_net_monthly_income_range_display` BIT(1) DEFAULT NULL,
  `is_net_monthly_income_range_mandatory` BIT(1) DEFAULT NULL,  
  `max_dpds` INT(11) DEFAULT NULL,
  `is_dpds_display` BIT(1) DEFAULT NULL,
  `is_dpds_mandatory` BIT(1) DEFAULT NULL,
  `is_current_employment_status_display` BIT(1) DEFAULT NULL,
  `is_current_employment_status_mandatory` BIT(1) DEFAULT NULL,
  `is_loan_purpose_display` BIT(1) DEFAULT NULL,
  `is_loan_purpose_mandatory` BIT(1) DEFAULT NULL,
  `is_residential_status_display` BIT(1) DEFAULT NULL,
  `is_residential_status_mandatory` BIT(1) DEFAULT NULL,
  `is_borrower_type_display` BIT(1) DEFAULT NULL,
  `is_borrower_type_mandatory` BIT(1) DEFAULT NULL,
  `min_bank_relationship` INT(11) DEFAULT NULL,
  `max_bank_relationship` INT(2) DEFAULT NULL,
  `is_bank_relationship_display` BIT(1) DEFAULT NULL,
  `is_bank_relationship_mandatory` BIT(1) DEFAULT NULL,
  `is_salary_mode_display` BIT(1) DEFAULT NULL,
  `is_salary_mode_mandatory` BIT(1) DEFAULT NULL,
  `is_borr_sal_acc_display` BIT(1) DEFAULT NULL,
  `is_borr_sal_acc_mandatory` BIT(1) DEFAULT NULL,    
  `is_current_job_experience_display` BIT(1) DEFAULT NULL,
  `is_current_job_experience_mandatory` BIT(1) DEFAULT NULL,
  `is_total_job_experience_display` BIT(1) DEFAULT NULL,
  `is_total_job_experience_mandatory` BIT(1) DEFAULT NULL,
  `min_total_job_experience` INT(2) DEFAULT NULL,
  `max_total_job_experience` INT(2) DEFAULT NULL,
  `max_current_job_experience` INT(2) DEFAULT NULL,
  `min_current_job_experience` INT(2) DEFAULT NULL,
  `min_bureau_score` INT(11) DEFAULT NULL,
  `max_bureau_score` INT(11) DEFAULT NULL,
  `is_bureau_score_display` BIT(1) DEFAULT NULL,
  `is_bureau_score_mandatory` BIT(1) DEFAULT NULL,
  `is_risk_score_model_coapp_display` BIT(1) DEFAULT NULL,
  `is_risk_score_model_coapp_mandatory` BIT(1) DEFAULT NULL,
  `is_risk_score_model_display` BIT(1) DEFAULT NULL,
  `is_risk_score_model_mandatory` BIT(1) DEFAULT NULL,
  `min_risk_score_model_co_app` DOUBLE DEFAULT NULL,
  `min_risk_score_model` DOUBLE DEFAULT NULL,
  `no_of_co_app_or_gua` INT(11) DEFAULT NULL,
  `is_consider_income_of_co_app` BIT(1) DEFAULT NULL,  
  `max_age_allowed` INT(11) DEFAULT NULL,
  `assessment_method_id` INT(11) DEFAULT NULL,
  `is_gross_net_income` BIT(1) DEFAULT NULL,
  `mon_income_type` INT(2) DEFAULT NULL,
  `foir` DOUBLE DEFAULT NULL,
  `is_times_multiplier_income` BIT(1) DEFAULT NULL,
  `mon_income_multiplier_type` INT(2) DEFAULT NULL,
  `times_multiplier` INT(1) DEFAULT NULL,
  `is_ltv` BIT(1) DEFAULT NULL,  
  `ltv_for_eligibility` INT(2) DEFAULT NULL,
  `is_purpose_loan_display` BIT(1) DEFAULT NULL,
  `is_purpose_loan_mandatory` BIT(1) DEFAULT NULL,
  `is_employment_with_display` BIT(1) DEFAULT NULL,
  `is_employment_with_mandatory` BIT(1) DEFAULT NULL,
  `is_self_employed_with_display` BIT(1) DEFAULT NULL,
  `is_self_employed_with_mandatory` BIT(1) DEFAULT NULL,
  `salary_ac_type` INT(1) DEFAULT NULL,
  `min_gross_monthly_income_range` DOUBLE DEFAULT NULL,
  `max_gross_monthly_income_range` DOUBLE DEFAULT NULL,
  `is_gross_monthly_income_range_display` BIT(1) DEFAULT NULL,
  `is_gross_monthly_income_range_mandatory` BIT(1) DEFAULT NULL,
  `min_net_take_home_salary` DOUBLE DEFAULT NULL,
  `is_min_net_take_home_salary_display` BIT(1) DEFAULT b'0',
  `is_min_net_take_home_salary_mandatory` BIT(1) DEFAULT b'0',
  `min_bureau_score_less_than6_month` DOUBLE DEFAULT NULL,
  `noBureauCreditHistory` BIT(1) DEFAULT b'1',
  `no_bureau_credit_history` BIT(1) DEFAULT b'1',
  `min_risk_score_model_oth_thn_sal` DOUBLE DEFAULT NULL,
  `min_risk_score_model_co_app_oth_thn_sal` DOUBLE DEFAULT NULL,
  `is_risk_score_model_oth_thn_sal_display` BIT(1) DEFAULT b'0',
  `is_risk_score_model_oth_thn_sal_mandatory` BIT(1) DEFAULT b'0',
  `is_risk_score_model_coapp_oth_thn_sal_display` BIT(1) DEFAULT b'0',
  `is_risk_score_model_coapp_oth_thn_sal_mandatory` BIT(1) DEFAULT b'0',
  `min_nmtlr` DOUBLE DEFAULT NULL,
  `max_nmtlr` DOUBLE DEFAULT NULL,      
  `is_nmtlr_display` BIT(1) DEFAULT b'0',
  `is_nmtlr_mandatory` BIT(1) DEFAULT b'0',
  `min_emi_mi` DOUBLE DEFAULT NULL,
  `max_emi_mi` DOUBLE DEFAULT NULL,      
  `is_emi_mi_display` BIT(1) DEFAULT b'0',
  `is_emi_mi_mandatory` BIT(1) DEFAULT b'0',
  `min_collateral_security` DOUBLE DEFAULT NULL,
  `max_collateral_security` DOUBLE DEFAULT NULL,      
  `is_collateral_security_display` BIT(1) DEFAULT b'0',
  `is_collateral_security_mandatory` BIT(1) DEFAULT b'0',
  `is_repayment_mode_display` BIT(1) DEFAULT b'0',
  `is_repayment_mode_mandatory` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_auto_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;





CREATE TABLE `loan_application`.`fp_auto_loan_details_temp` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,    
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,  
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,      
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',  
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',    
  `min_net_monthly_income_range` DOUBLE DEFAULT NULL,
  `max_net_monthly_income_range` DOUBLE DEFAULT NULL,
  `is_net_monthly_income_range_display` BIT(1) DEFAULT NULL,
  `is_net_monthly_income_range_mandatory` BIT(1) DEFAULT NULL,  
  `max_dpds` INT(11) DEFAULT NULL,
  `is_dpds_display` BIT(1) DEFAULT NULL,
  `is_dpds_mandatory` BIT(1) DEFAULT NULL,
  `is_current_employment_status_display` BIT(1) DEFAULT NULL,
  `is_current_employment_status_mandatory` BIT(1) DEFAULT NULL,
  `is_loan_purpose_display` BIT(1) DEFAULT NULL,
  `is_loan_purpose_mandatory` BIT(1) DEFAULT NULL,
  `is_residential_status_display` BIT(1) DEFAULT NULL,
  `is_residential_status_mandatory` BIT(1) DEFAULT NULL,
  `is_borrower_type_display` BIT(1) DEFAULT NULL,
  `is_borrower_type_mandatory` BIT(1) DEFAULT NULL,
  `min_bank_relationship` INT(11) DEFAULT NULL,
  `max_bank_relationship` INT(2) DEFAULT NULL,
  `is_bank_relationship_display` BIT(1) DEFAULT NULL,
  `is_bank_relationship_mandatory` BIT(1) DEFAULT NULL,
  `is_salary_mode_display` BIT(1) DEFAULT NULL,
  `is_salary_mode_mandatory` BIT(1) DEFAULT NULL,
  `is_borr_sal_acc_display` BIT(1) DEFAULT NULL,
  `is_borr_sal_acc_mandatory` BIT(1) DEFAULT NULL,    
  `is_current_job_experience_display` BIT(1) DEFAULT NULL,
  `is_current_job_experience_mandatory` BIT(1) DEFAULT NULL,
  `is_total_job_experience_display` BIT(1) DEFAULT NULL,
  `is_total_job_experience_mandatory` BIT(1) DEFAULT NULL,
  `min_total_job_experience` INT(2) DEFAULT NULL,
  `max_total_job_experience` INT(2) DEFAULT NULL,
  `max_current_job_experience` INT(2) DEFAULT NULL,
  `min_current_job_experience` INT(2) DEFAULT NULL,
  `min_bureau_score` INT(11) DEFAULT NULL,
  `max_bureau_score` INT(11) DEFAULT NULL,
  `is_bureau_score_display` BIT(1) DEFAULT NULL,
  `is_bureau_score_mandatory` BIT(1) DEFAULT NULL,
  `is_risk_score_model_coapp_display` BIT(1) DEFAULT NULL,
  `is_risk_score_model_coapp_mandatory` BIT(1) DEFAULT NULL,
  `is_risk_score_model_display` BIT(1) DEFAULT NULL,
  `is_risk_score_model_mandatory` BIT(1) DEFAULT NULL,
  `min_risk_score_model_co_app` DOUBLE DEFAULT NULL,
  `min_risk_score_model` DOUBLE DEFAULT NULL,
  `no_of_co_app_or_gua` INT(11) DEFAULT NULL,
  `is_consider_income_of_co_app` BIT(1) DEFAULT NULL,  
  `max_age_allowed` INT(11) DEFAULT NULL,
  `assessment_method_id` INT(11) DEFAULT NULL,
  `is_gross_net_income` BIT(1) DEFAULT NULL,
  `mon_income_type` INT(2) DEFAULT NULL,
  `foir` DOUBLE DEFAULT NULL,
  `is_times_multiplier_income` BIT(1) DEFAULT NULL,
  `mon_income_multiplier_type` INT(2) DEFAULT NULL,
  `times_multiplier` INT(1) DEFAULT NULL,
  `is_ltv` BIT(1) DEFAULT NULL,  
  `ltv_for_eligibility` INT(2) DEFAULT NULL,
  `is_purpose_loan_display` BIT(1) DEFAULT NULL,
  `is_purpose_loan_mandatory` BIT(1) DEFAULT NULL,
  `is_employment_with_display` BIT(1) DEFAULT NULL,
  `is_employment_with_mandatory` BIT(1) DEFAULT NULL,
  `is_self_employed_with_display` BIT(1) DEFAULT NULL,
  `is_self_employed_with_mandatory` BIT(1) DEFAULT NULL,
  `salary_ac_type` INT(1) DEFAULT NULL,
  `min_gross_monthly_income_range` DOUBLE DEFAULT NULL,
  `max_gross_monthly_income_range` DOUBLE DEFAULT NULL,
  `is_gross_monthly_income_range_display` BIT(1) DEFAULT NULL,
  `is_gross_monthly_income_range_mandatory` BIT(1) DEFAULT NULL,
  `min_net_take_home_salary` DOUBLE DEFAULT NULL,
  `is_min_net_take_home_salary_display` BIT(1) DEFAULT b'0',
  `is_min_net_take_home_salary_mandatory` BIT(1) DEFAULT b'0',
  `min_bureau_score_less_than6_month` DOUBLE DEFAULT NULL,
  `noBureauCreditHistory` BIT(1) DEFAULT b'1',
  `no_bureau_credit_history` BIT(1) DEFAULT b'1',
  `min_risk_score_model_oth_thn_sal` DOUBLE DEFAULT NULL,
  `min_risk_score_model_co_app_oth_thn_sal` DOUBLE DEFAULT NULL,
  `is_risk_score_model_oth_thn_sal_display` BIT(1) DEFAULT b'0',
  `is_risk_score_model_oth_thn_sal_mandatory` BIT(1) DEFAULT b'0',
  `is_risk_score_model_coapp_oth_thn_sal_display` BIT(1) DEFAULT b'0',
  `is_risk_score_model_coapp_oth_thn_sal_mandatory` BIT(1) DEFAULT b'0',
  `min_nmtlr` DOUBLE DEFAULT NULL,
  `max_nmtlr` DOUBLE DEFAULT NULL,      
  `is_nmtlr_display` BIT(1) DEFAULT b'0',
  `is_nmtlr_mandatory` BIT(1) DEFAULT b'0',
  `min_emi_mi` DOUBLE DEFAULT NULL,
  `max_emi_mi` DOUBLE DEFAULT NULL,      
  `is_emi_mi_display` BIT(1) DEFAULT b'0',
  `is_emi_mi_mandatory` BIT(1) DEFAULT b'0',
  `min_collateral_security` DOUBLE DEFAULT NULL,
  `max_collateral_security` DOUBLE DEFAULT NULL,      
  `is_collateral_security_display` BIT(1) DEFAULT b'0',
  `is_collateral_security_mandatory` BIT(1) DEFAULT b'0',
  `is_repayment_mode_display` BIT(1) DEFAULT b'0',
  `is_repayment_mode_mandatory` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_auto_loan_details_temp_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN  is_on_road_price BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN  is_on_road_price BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN  is_ex_showroom_price BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN  is_ex_showroom_price BIT(1) DEFAULT FALSE;
	

	
	

ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN min_vehicle_age DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN max_vehicle_age DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN is_vehicle_age_display BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN is_vehicle_age_mandatory BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN min_vehicle_age DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN max_vehicle_age DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN is_vehicle_age_display BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN is_vehicle_age_mandatory BIT(1) DEFAULT FALSE;


ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN min_dscr DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN max_dscr DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN is_dscr_display BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN is_dscr_mandatory BIT(1) DEFAULT FALSE;

ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN min_dscr DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN max_dscr DOUBLE DEFAULT NULL;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN is_dscr_display BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN is_dscr_mandatory BIT(1) DEFAULT FALSE;

ALTER TABLE `loan_application`.`fp_auto_loan_details` DROP COLUMN min_collateral_security;
ALTER TABLE `loan_application`.`fp_auto_loan_details` DROP COLUMN max_collateral_security;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` DROP COLUMN min_collateral_security;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` DROP COLUMN max_collateral_security;
ALTER TABLE `loan_application`.`fp_auto_loan_details` ADD COLUMN is_collateral_security BIT(1) DEFAULT FALSE;
ALTER TABLE `loan_application`.`fp_auto_loan_details_temp` ADD COLUMN is_collateral_security BIT(1) DEFAULT FALSE;



INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(165,'AGE_AL','1','2019-04-20 14:20:56',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(166,'TOTAL_WORK_EXP_AL','1','2019-04-20 14:20:56',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(167,'CURRENT_JOB_EXP_AL','1','2019-04-20 14:20:57',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(168,'RESIDENCE_TYPE_AL','2','2019-04-20 14:20:58',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(169,'NO_YEARS_STAY_CURR_LOC_AL','1','2019-04-20 14:21:00',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(170,'BUREAU_SCORE_AL','1','2019-04-20 14:21:01',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(171,'MARITAL_STATUS_AL','2','2019-04-20 14:21:01',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(172,'EMPLOYMENT_TYPE_AL','2','2019-04-20 14:21:01',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(173,'EMPLOYMENT_CATEG_JOB_AL','2','2019-04-20 14:21:02',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(174,'EMPLOYMENT_CATEG_PROF_SELF_EMPLOYED_AL','2','2019-04-20 14:21:02',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(175,'CURRENT_EMPLOYMENT_STATUS_AL','2','2019-04-20 14:21:02',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(176,'MIN_BANKING_RELATIONSHIP_AL','1','2019-04-20 14:21:03',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(177,'SPOUSE_EMPLOYEMENT_AL','2','2019-04-20 14:21:04',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(178,'NO_OF_DEPENDANTS_AL','1','2019-04-20 14:21:05',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(179,'NO_OF_APPLICANTS_AL','2','2019-04-20 14:21:05',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(180,'ANNUAL_INCOME_AL','1','2019-04-20 14:21:05',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(181,'AVAILABLE_INCOME_AL','1','2019-04-20 14:21:05',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(182,'ADDI_INCOME_SPOUSE_AL','1','2019-04-20 14:21:06',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(183,'MON_INCOME_DEPENDANT_AL','1','2019-04-20 14:21:08',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(184,'AVG_INCREASE_INCOME_REPORT_3_YEARS_AL','1','2019-04-20 14:21:08',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(185,'TENURE_AL','1','2019-04-20 14:21:09',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(186,'AVG_DEPOS_LAST_6_MONTH_AL','1','2019-04-20 14:21:09',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(187,'CHECQUE_BOUNSE_LAST_1_MONTH_AL','1','2019-04-20 14:21:10',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(188,'CHECQUE_BOUNSE_LAST_6_MONTH_AL','1','2019-04-20 14:21:11',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(189,'DPD_AL','1','2019-04-20 14:21:11',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(190,'LTV_AL','1','2019-04-20 14:21:12',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(191,'EMI_NMI_RATIO_AL','1','2019-04-20 14:21:14',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(192,'LOAN_PURPOSE_AL','2','2019-04-20 14:21:15',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(193,'APPLICANT_NW_TO_LOAN_AMOUNT_AL','1','2019-04-20 15:15:35',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(194,'DESIGNATION_AL','2','2019-05-25 15:55:43',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(195,'EDUCATION_QUALIFICATION_AL','2','2019-05-25 16:24:26',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(196,'INCOME_PROOF_AL','2','2019-05-25 16:29:16',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(197,'EMI_NMI_AL','1','2019-05-25 17:22:37',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(198,'LOAN_TO_INCOME_RATIO_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(199,'INCOME_TO_INSTALLMENT_RATIO_AL','1','2019-05-25 18:16:56',NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(200,'AVG_EOD_BALANCE_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(201,'CAR_SEGMENT_AL','2',NOW(),NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(202,',SECURITY_COVERAGE_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(203,'TAKE_HOME_PAY_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);	
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(204,'BORROWER_MARGIN_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);	
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(205,'AGE_OF_VEHICLE_AL','1',NOW(),NULL,NULL,NULL,TRUE,NULL);
INSERT INTO `scoring_sidbi`.`field_master` (`id`,`name`, `type`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`, `parent_field_id`) VALUES(206,'REPAYMENT_PERIOD_AL','2',NOW(),NULL,NULL,NULL,TRUE,NULL);


insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:39',TRUE,NULL,NULL,NULL,'165','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:39',TRUE,NULL,NULL,NULL,'166','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:39',TRUE,NULL,NULL,NULL,'167','8','3',TRUE,'2');
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:40',TRUE,NULL,NULL,NULL,'168','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:40',TRUE,NULL,NULL,NULL,'169','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:40',TRUE,NULL,NULL,NULL,'170','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:41',TRUE,NULL,NULL,NULL,'171','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:41',TRUE,NULL,NULL,NULL,'172','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:41',TRUE,NULL,NULL,NULL,'173','8','3',TRUE,'2');
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:41',TRUE,NULL,NULL,NULL,'174','8','3',TRUE,'3');
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:41',TRUE,NULL,NULL,NULL,'175','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'176','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'177','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'178','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'179','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'180','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:42',TRUE,NULL,NULL,NULL,'181','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:43',TRUE,NULL,NULL,NULL,'182','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:43',TRUE,NULL,NULL,NULL,'183','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:43',TRUE,NULL,NULL,NULL,'184','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:44',TRUE,NULL,NULL,NULL,'185','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:44',TRUE,NULL,NULL,NULL,'186','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:45',TRUE,NULL,NULL,NULL,'187','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:45',TRUE,NULL,NULL,NULL,'188','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:45',TRUE,NULL,NULL,NULL,'189','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:45',TRUE,NULL,NULL,NULL,'190','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:45',TRUE,NULL,NULL,NULL,'191','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:46',TRUE,NULL,NULL,NULL,'192','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-04-22 11:38:46',TRUE,NULL,NULL,NULL,'193','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 15:55:43',TRUE,NULL,NULL,NULL,'194','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 16:24:26',TRUE,NULL,NULL,NULL,'195','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 16:29:16',TRUE,NULL,NULL,NULL,'196','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 17:22:39',TRUE,NULL,NULL,NULL,'197','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 18:09:06',TRUE,NULL,NULL,NULL,'198','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,'2019-05-25 18:16:57',TRUE,NULL,NULL,NULL,'199','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'200','8','3',TRUE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'201','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'202','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'203','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'204','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'205','8','3',FALSE,NULL);
insert into `scoring_sidbi`.`field_mapping` (`created_by`, `created_date`, `is_active`, `loan_type_id`, `modified_by`, `modified_date`, `field_master_id`, `business_type_id`, `financial_type_id`, `is_consider_co_app`, `employment_type_id`) values(NULL,NOW(),TRUE,NULL,NULL,NULL,'206','8','3',FALSE,NULL);




ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN `borrower_contribution` BIGINT(20) NULL;