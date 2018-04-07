
alter table loan_applications.fp_working_capital_details add column min_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_current_ratio_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_current_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_current_ratio_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_current_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_current_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_current_ratio_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_current_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_interest_coverage_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_interest_coverage_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_interest_coverage_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_interest_coverage_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_interest_coverage decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_interest_coverage_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_interest_coverage_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_tol_tnw_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_tol_tnw_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_tol_tnw_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_tol_tnw_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_tol_tnw decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_tol_tnw_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_tol_tnw_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_turnover_ratio_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_turnover_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_turnover_ratio_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_turnover_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_turnover_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_turnover_ratio_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_turnover_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_gross_cash_accurals_ratio_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_gross_cash_accurals_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_gross_cash_accurals_ratio_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_gross_cash_accurals_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_gross_cash_accurals_ratio decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_gross_cash_accurals_ratio_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_gross_cash_accurals_ratio_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column max_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_working_capital_details add column is_customer_concentration_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_customer_concentration_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column max_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_term_loan_details add column is_customer_concentration_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_customer_concentration_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_customer_concentration decimal(19,2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_customer_concentration_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_customer_concentration_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_risk_model_score int(2) default 0;
alter table loan_applications.fp_working_capital_details add column max_risk_model_score int(2) default 0;
alter table loan_applications.fp_working_capital_details add column is_risk_model_score_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_risk_model_score_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_risk_model_score int(2) default 0;
alter table loan_applications.fp_term_loan_details add column max_risk_model_score int(2) default 0;
alter table loan_applications.fp_term_loan_details add column is_risk_model_score_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_risk_model_score_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_risk_model_score int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_risk_model_score int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_risk_model_score_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_risk_model_score_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column net_worth int(2) default null;
alter table loan_applications.fp_working_capital_details add column net_worth int(2) default null;
alter table loan_applications.fp_term_loan_details add column net_worth int(2) default null;

ALTER TABLE `loan_applications`.`fp_working_capital_details`
CHANGE COLUMN `min_collateral` `min_collateral` DECIMAL(19,2) NULL DEFAULT NULL ,
CHANGE COLUMN `max_collateral` `max_collateral` DECIMAL(19,2) NULL DEFAULT NULL ;

ALTER TABLE `loan_applications`.`fp_unsecure_loan_details`
CHANGE COLUMN `min_collateral` `min_collateral` DECIMAL(19,2) NULL DEFAULT NULL ,
CHANGE COLUMN `max_collateral` `max_collateral` DECIMAL(19,2) NULL DEFAULT NULL ;

ALTER TABLE `loan_applications`.`fp_term_loan_details`
CHANGE COLUMN `min_collateral` `min_collateral` DECIMAL(19,2) NULL DEFAULT NULL ,
CHANGE COLUMN `max_collateral` `max_collateral` DECIMAL(19,2) NULL DEFAULT NULL ;

alter table loan_applications.fp_working_capital_details add column min_cheque_bounced int(2) default 0;
alter table loan_applications.fp_working_capital_details add column max_cheque_bounced int(2) default 0;
alter table loan_applications.fp_working_capital_details add column is_cheque_bounced_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_cheque_bounced_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_cheque_bounced int(2) default 0;
alter table loan_applications.fp_term_loan_details add column max_cheque_bounced int(2) default 0;
alter table loan_applications.fp_term_loan_details add column is_cheque_bounced_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_cheque_bounced_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_cheque_bounced int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_cheque_bounced int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_cheque_bounced_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_cheque_bounced_mandatory bit(1) default 0;

alter table loan_applications.fp_working_capital_details add column min_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_working_capital_details add column max_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_working_capital_details add column is_cheque_bounced_last_six_months_display bit(1) default 0;
alter table loan_applications.fp_working_capital_details add column is_cheque_bounced_last_six_months_mandatory bit(1) default 0;

alter table loan_applications.fp_term_loan_details add column min_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_term_loan_details add column max_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_term_loan_details add column is_cheque_bounced_last_six_months_display bit(1) default 0;
alter table loan_applications.fp_term_loan_details add column is_cheque_bounced_last_six_months_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column min_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column max_cheque_bounced_last_six_months int(2) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_cheque_bounced_last_six_months_display bit(1) default 0;
alter table loan_applications.fp_unsecure_loan_details add column is_cheque_bounced_last_six_months_mandatory bit(1) default 0;

alter table loan_applications.fp_unsecure_loan_details add column ddr_flow int(2) default null;
alter table loan_applications.fp_working_capital_details add column ddr_flow int(2) default null;
alter table loan_applications.fp_term_loan_details add column ddr_flow int(2) default null;

ALTER TABLE `loan_applications`.`fs_loan_application_master` ADD COLUMN `np_org_id` BIGINT(20) NULL AFTER `approved_date`;