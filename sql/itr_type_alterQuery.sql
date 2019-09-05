
ALTER TABLE `loan_application`.`fs_retail_applicant_income_details`   
	ADD COLUMN `form_type` VARCHAR(20) NULL;
	
	ALTER TABLE `loan_application`.`fs_retail_co_applicant_income_details`   
	ADD COLUMN `form_type` VARCHAR(20) NULL;
