ALTER TABLE `loan_application`.`fs_ddr_existing_banker_details` 
ADD COLUMN `financial_arrangement_id` BIGINT(20) NULL AFTER `is_active`;

ALTER TABLE `loan_application`.`fs_ddr_authorized_sign_details` 
ADD COLUMN `occupation` VARCHAR(200) NULL AFTER `is_active`;
