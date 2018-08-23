ALTER TABLE `loan_application`.`sanction_detail` 
ADD COLUMN `bank_sanction_pk` BIGINT(20) NULL AFTER `processing_fee`;
ALTER TABLE `loan_application`.`disbursement_detail` 
ADD COLUMN `bank_disbursement_pk` BIGINT(20) NULL AFTER `is_active`;
