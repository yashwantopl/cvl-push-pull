ALTER TABLE `loan_application`.`sanction_detail` 
ADD COLUMN `bank_sanction_pk` BIGINT(20) NULL AFTER `processing_fee`;
ALTER TABLE `loan_application`.`disbursement_detail` 
ADD COLUMN `bank_disbursement_pk` BIGINT(20) NULL AFTER `is_active`;


ALTER TABLE `loan_application`.`disbursement_detail` 
ADD COLUMN `account_no` VARCHAR(100) NULL AFTER `bank_disbursement_pk`;

ALTER TABLE `loan_application`.`disbursement_detail` 
ADD COLUMN `disbursement_authority` VARCHAR(100) NULL AFTER `account_no`;

ALTER TABLE `loan_application`.`disbursement_detail` 
ADD COLUMN `transaction_no` VARCHAR(45) NULL AFTER `disbursement_authority`;

