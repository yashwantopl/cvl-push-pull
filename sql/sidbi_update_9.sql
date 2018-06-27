ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `is_main_director` BIT(1) NULL ;




ALTER TABLE `loan_application`.`fs_corporate_applicant_details`
ADD COLUMN `msme_registration_number` VARCHAR(100) NULL;