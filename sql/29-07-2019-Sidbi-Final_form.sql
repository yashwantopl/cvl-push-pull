ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN `all_amt_values_in` INT(2) NULL AFTER `prop_factory_premise`; 
ALTER TABLE `loan_application`.`fs_corporate_sidbi_basic_details` ADD COLUMN `is_locked_values_in` BIT(1) NULL AFTER `all_amt_values_in`; 

UPDATE `loan_application`.`fs_corporate_sidbi_basic_details` SET `all_amt_values_in`=3 , `is_locked_values_in`=TRUE;