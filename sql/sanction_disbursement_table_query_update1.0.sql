// add new column in bank_cw_audit_trail -- bank_primary_key 
ALTER TABLE `loan_application`.`bank_cw_audit_trail` ADD COLUMN `bank_primary_key` BIGINT(19) NULL AFTER `is_active`; 


//  change mode to payment_mode
ALTER TABLE `loan_application`.`disbursement_detail` CHANGE `mode` `payment_mode` BIGINT(11) NULL; 
