ALTER TABLE loan_applications.`fs_loan_application_master` ADD COLUMN mca_company_id VARCHAR(50) DEFAULT NULL;
ALTER TABLE loan_applications.`fs_loan_application_master` ADD COLUMN is_mca BIT(1);