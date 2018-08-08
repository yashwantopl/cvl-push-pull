ALTER TABLE `loan_application`.`fs_corporate_primary_details` ADD COLUMN state_id BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_primary_details` ADD COLUMN city_id BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_primary_details` ADD COLUMN prop_cost DOUBLE DEFAULT NULL;