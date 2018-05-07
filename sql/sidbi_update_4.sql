ALTER TABLE `loan_applications`.`fs_corporate_promotor_background_details`
ADD COLUMN `din` DOUBLE NULL DEFAULT NULL AFTER `networth`,
ADD COLUMN `designation` VARCHAR(20) NULL DEFAULT NULL AFTER `din`,
ADD COLUMN `appointment_date` DATETIME NULL DEFAULT NULL AFTER `designation`,
ADD COLUMN `mobile` VARCHAR(20) NULL DEFAULT NULL AFTER `appointment_date`,
ADD COLUMN `dob` DATETIME NULL DEFAULT NULL AFTER `mobile`,
ADD COLUMN `gender` BIGINT(20) NULL DEFAULT NULL AFTER `dob`,
ADD COLUMN `relationship_type` BIGINT(20) NULL DEFAULT NULL AFTER `gender`;