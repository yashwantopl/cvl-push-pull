ALTER TABLE `loan_application`.`fp_working_capital_details`
ADD COLUMN `assessment_method_id` INT(4) NULL ;

ALTER TABLE `loan_application`.`fp_wc_tl_details`
ADD COLUMN `assessment_method_id` INT(4) NULL DEFAULT NULL ;

ALTER TABLE `loan_application`.`fp_term_loan_details`
ADD COLUMN `assessment_method_id` INT(4) NULL DEFAULT NULL;



ALTER TABLE `loan_application`.`fs_corporate_primary_details`
ADD COLUMN `cost_of_machinery` DOUBLE NULL,
ADD COLUMN `incremental_turnover` DOUBLE NULL;