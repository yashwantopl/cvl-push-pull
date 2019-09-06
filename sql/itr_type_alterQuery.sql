
ALTER TABLE `loan_application`.`fs_retail_applicant_income_details`   
	ADD COLUMN `form_type` VARCHAR(20) NULL;
	
	ALTER TABLE `loan_application`.`fs_retail_co_applicant_income_details`   
	ADD COLUMN `form_type` VARCHAR(20) NULL;

	
	
	
UPDATE loan_application.`fs_retail_applicant_income_details` fs
LEFT JOIN itr_api.`itr_tracking` tk ON tk.application_id = fs.application_id
SET fs.form_type = SUBSTRING_INDEX(SUBSTRING_INDEX(tk.first_year,':',2),":",-1)
WHERE fs.is_active = TRUE;

 

UPDATE loan_application.`fs_retail_co_applicant_income_details` fs
LEFT JOIN itr_api.`itr_tracking` tk ON tk.application_id = fs.application_id AND tk.`co_app_id` = fs.co_app_id
SET fs.form_type = SUBSTRING_INDEX(SUBSTRING_INDEX(tk.first_year,':',2),":",-1)
WHERE fs.is_active = TRUE;