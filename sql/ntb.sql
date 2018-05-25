ALTER TABLE `loan_application`.`fs_corporate_director_background_details` ADD COLUMN `is_itr_completed` BIT(1) NULL AFTER `shareholding`;
ALTER TABLE `loan_application`.`fs_corporate_director_background_details` ADD COLUMN `is_cibil_completed` BIT(1) NULL AFTER `is_itr_completed`;
ALTER TABLE `loan_application`.`fs_corporate_director_background_details` ADD COLUMN `is_bank_state_completed` BIT(1) NULL AFTER `is_cibil_completed`;
ALTER TABLE `loan_application`.`fs_corporate_director_background_details` ADD COLUMN `is_one_form_completed` BIT(1) NULL AFTER `is_bank_state_completed`;

ALTER TABLE `loan_application`.`fs_corporate_current_financial_arrangements_details` ADD COLUMN `director_id` BIGINT(20) NULL AFTER `emi`;
ALTER TABLE `loan_application`.`fs_corporate_current_financial_arrangements_details` 
ADD INDEX `fk_fs_corporate_current_financial_arrangements_details_1_idx` (`director_id` ASC);
ALTER TABLE `loan_application`.`fs_corporate_current_financial_arrangements_details` 
ADD CONSTRAINT `fk_fs_corporate_current_financial_arrangements_details_1`
  FOREIGN KEY (`director_id`)
  REFERENCES `loan_application`.`fs_corporate_director_background_details` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
