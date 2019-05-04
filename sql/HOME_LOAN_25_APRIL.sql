
    
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN email VARCHAR(100);
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN mobile VARCHAR(25);
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_itr_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_itr_skip BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_cibil_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_bank_state_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_one_form_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN is_itr_manual BIT(1) DEFAULT 0; 
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD


ALTER TABLE `itr_api`.`itr_home_loan_tracking` ADD COLUMN is_manual_filled BIT(1) DEFAULT 0; 


ALTER TABLE `itr_api`.`itr_manual_filled_audit` ADD COLUMN co_app_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_one_form_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_cibil_completed BIT(1) DEFAULT 0; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 


ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN address_district_mapping_id BIGINT(20); 
=======
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_oneform_primary_complete BIT(1) DEFAULT 0; 


ALTER TABLE `itr_api`.`itr_home_loan_tracking` ADD COLUMN is_manual_filled BIT(1) DEFAULT 0; 

ALTER TABLE `itr_api`.`itr_manual_filled_audit` ADD COLUMN co_app_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_one_form_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_cibil_completed BIT(1) DEFAULT 0; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 






ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN address_district_mapping_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN nationality INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_type INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_year INT DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_month INT DEFAULT NULL; 
	


ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_premise_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_street_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_landmark VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_country BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_state BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_city BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_pincode BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_district_mapping_id BIGINT(20); 
>>>>>>> branch 'sidbi-development' of https://github.com/capitawrld/service-loans.git
=======
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_oneform_primary_complete BIT(1) DEFAULT 0; 


ALTER TABLE `itr_api`.`itr_home_loan_tracking` ADD COLUMN is_manual_filled BIT(1) DEFAULT 0; 

ALTER TABLE `itr_api`.`itr_manual_filled_audit` ADD COLUMN co_app_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_one_form_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_cibil_completed BIT(1) DEFAULT 0; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 






ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN address_district_mapping_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN nationality INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_type INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_year INT DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_month INT DEFAULT NULL; 
	


ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_premise_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_street_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_landmark VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_country BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_state BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_city BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_pincode BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_district_mapping_id BIGINT(20); 
>>>>>>> branch 'sidbi-development' of https://github.com/capitawrld/service-loans.git
=======
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_oneform_primary_complete BIT(1) DEFAULT 0; 


ALTER TABLE `itr_api`.`itr_home_loan_tracking` ADD COLUMN is_manual_filled BIT(1) DEFAULT 0; 

ALTER TABLE `itr_api`.`itr_manual_filled_audit` ADD COLUMN co_app_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_one_form_completed BIT(1) DEFAULT 0; 
ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN is_cibil_completed BIT(1) DEFAULT 0; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 






ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN category INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN networth DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_status_other VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN current_employment_status INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN gross_monthly_income DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN annual_income_of_spouse DOUBLE DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN address_district_mapping_id BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN nationality INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN employment_type INT DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_year INT DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details` ADD COLUMN residence_since_month INT DEFAULT NULL; 
	


ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_premise_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_street_name VARCHAR(255) DEFAULT NULL; 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_landmark VARCHAR(255) DEFAULT NULL; 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_country BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_state BIGINT(20); 
ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_city BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_pincode BIGINT(20); 

ALTER TABLE `loan_application`.`fs_retail_primary_home_loan_details` ADD COLUMN prop_district_mapping_id BIGINT(20); 
>>>>>>> branch 'sidbi-development' of https://github.com/capitawrld/service-loans.git

	
