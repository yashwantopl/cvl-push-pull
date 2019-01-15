ALTER TABLE loan_application.fs_corporate_applicant_details ADD COLUMN remarks VARCHAR(255) NULL AFTER temp;

ALTER TABLE loan_application.fs_corporate_primary_details ADD COLUMN enhancement_amount DECIMAL(19,2) NULL AFTER gross_sales; 