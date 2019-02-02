ALTER TABLE loan_application.fs_corporate_applicant_details ADD COLUMN remarks VARCHAR(255) NULL AFTER temp;

ALTER TABLE loan_application.fs_corporate_primary_details ADD COLUMN enhancement_amount DECIMAL(19,2) NULL AFTER gross_sales;


--------------------- SQL ON 21-01-2019 ------------------------
ALTER TABLE loan_application.fs_corporate_applicant_details ADD COLUMN business_since_year INT NULL AFTER proposal_mapping_id, ADD COLUMN business_since_month INT NULL AFTER business_since_year; 