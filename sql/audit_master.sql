ALTER TABLE `loan_application`.`audit_master` 
ADD COLUMN `failure_reason` VARCHAR(230) NULL AFTER `user_id`;
