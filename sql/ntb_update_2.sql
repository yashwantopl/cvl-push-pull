/*Table structure for table `fp_ntb_tl_details` */

DROP TABLE IF EXISTS `fp_ntb_tl_details`;

CREATE TABLE `fp_ntb_tl_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(11) DEFAULT NULL,
  `denomination` int(11) DEFAULT NULL,
  `min_tenure` decimal(19,2) DEFAULT NULL,
  `max_tenure` decimal(19,2) DEFAULT NULL,
  `is_tenure_display` bit(1) DEFAULT NULL,
  `is_tenure_mandatory` bit(1) DEFAULT NULL,
  `is_geographical_display` bit(1) DEFAULT NULL,
  `is_geographical_mandatory` bit(1) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT NULL,
  `is_industry_sector_mandatory` bit(1) DEFAULT NULL,
  `min_avrg_age` decimal(19,2) DEFAULT NULL,
  `max_avrg_age` decimal(19,2) DEFAULT NULL,
  `is_avrg_age_display` bit(1) DEFAULT NULL,
  `is_avrg_age_mandatory` bit(1) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `is_investment_size_display` bit(1) DEFAULT NULL,
  `is_investment_size_mandatory` bit(1) DEFAULT NULL,
  `min_avrg_yearly_income` decimal(19,2) DEFAULT NULL,
  `max_avrg_yearly_income` decimal(19,2) DEFAULT NULL,
  `is_avrg_yearly_income_display` bit(1) DEFAULT NULL,
  `is_avrg_yearly_income_mandatory` bit(1) DEFAULT NULL,
  `min_loan_asset_value` decimal(19,2) DEFAULT NULL,
  `max_loan_asset_value` decimal(19,2) DEFAULT NULL,
  `is_loan_to_asset_val_display` bit(1) DEFAULT NULL,
  `is_loan_to_asset_val_mandatory` bit(1) DEFAULT NULL,
  `avrg_cibil_score` int(11) DEFAULT NULL,
  `is_avrg_cibil_score_display` bit(1) DEFAULT NULL,
  `is_avrg_cibil_score_mandatory` bit(1) DEFAULT NULL,
  `min_current_foir` decimal(19,2) DEFAULT NULL,
  `max_current_foir` decimal(19,2) DEFAULT NULL,
  `is_current_foir_display` bit(1) DEFAULT NULL,
  `is_current_foir_mandatory` bit(1) DEFAULT NULL,
  `min_risk_score_model` int(11) DEFAULT NULL,
  `max_risk_score_model` int(11) DEFAULT NULL,
  `is_risk_score_model_display` bit(1) DEFAULT NULL,
  `is_risk_score_model_mandatory` bit(1) DEFAULT NULL,
  `min_avrg_work_exp` decimal(19,2) DEFAULT NULL,
  `max_avrg_work_exp` decimal(19,2) DEFAULT NULL,
  `is_avrg_work_exp_display` bit(1) DEFAULT NULL,
  `is_avrg_work_exp_mandatory` bit(1) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_ntb_tl_details_fk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;