ALTER TABLE `loan_application`.`fs_corporate_associated_concern_details` ADD COLUMN pan VARCHAR(10);
ALTER TABLE `loan_application`.`fs_corporate_associated_concern_details` ADD COLUMN financial_institution_name VARCHAR(200);
ALTER TABLE `loan_application`.`fs_corporate_associated_concern_details` ADD COLUMN limit_availed BIGINT(20);