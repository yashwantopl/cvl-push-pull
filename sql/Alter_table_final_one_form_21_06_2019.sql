ALTER TABLE loan_application.fs_emp_agriculturist_type ADD COLUMN co_app_id BIGINT;
ALTER TABLE loan_application.fs_emp_salaried_type ADD COLUMN co_app_id BIGINT;
ALTER TABLE loan_application.fs_emp_self_employed_type ADD COLUMN co_app_id BIGINT;
ALTER TABLE loan_application.`fs_retail_final_home_loan_details` ADD COLUMN job_id BIGINT;
ALTER TABLE loan_application.`fs_retail_final_home_loan_details` ADD COLUMN remarks VARCHAR(250);
ALTER TABLE loan_application.`fs_retail_final_home_loan_co_applicant_details` ADD COLUMN remarks VARCHAR(250);