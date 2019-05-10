CREATE TABLE `loan_application`.`payment_gateway_audit_master`( `id` BIGINT(20), `application_id` BIGINT(20), `user_id` BIGINT(20), `name` VARCHAR(100), `email` VARCHAR(100), `mobile` VARCHAR(20), `txn_id` VARCHAR(100), `response_param` TEXT, `txn_type` VARCHAR(100), `billdesk_number` VARCHAR(100), `status` VARCHAR(100) ) ENGINE=INNODB CHARSET=latin1 COLLATE=latin1_swedish_ci; 

ALTER TABLE `loan_application`.`payment_gateway_audit_master` ADD COLUMN `created_date` TIMESTAMP NULL AFTER `mobile`, ADD COLUMN `modified_date` TIMESTAMP NULL AFTER `created_date`; 


ALTER TABLE `loan_application`.`payment_gateway_audit_master` CHANGE `id` `id` BIGINT(20) NOT NULL, ADD PRIMARY KEY (`id`); 

ALTER TABLE `loan_application`.`payment_gateway_audit_master` CHANGE `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT; 