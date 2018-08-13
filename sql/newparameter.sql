ALTER TABLE `loan_application`.`fp_term_loan_details`  
ADD COLUMN `cgtmse_coverage` INT(2) ;

ALTER TABLE `loan_application`.`fp_term_loan_details_temp`  
ADD COLUMN `cgtmse_coverage` INT(2) ;


ALTER TABLE `loan_application`.`fp_wc_tl_details`  
ADD COLUMN `cgtmse_coverage` INT(2) ;


ALTER TABLE `loan_application`.`fp_wc_tl_details_temp`  
ADD COLUMN `cgtmse_coverage` INT(2) ;


ALTER TABLE `loan_application`.`fp_working_capital_details`  
ADD COLUMN `cgtmse_coverage` INT(2) ;


ALTER TABLE `loan_application`.`fp_working_capital_details_temp`  
ADD COLUMN `cgtmse_coverage` INT(2) ;