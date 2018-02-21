ALTER TABLE `loan_applications`.`fs_loan_application_master` 
ADD COLUMN `is_accept_consent` BIT(1) NULL AFTER `campaign_code`;
