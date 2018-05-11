ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `first_name` VARCHAR(50) NULL DEFAULT NULL AFTER `relationship_type`,
ADD COLUMN `middle_name` VARCHAR(50) NULL AFTER `first_name`,
ADD COLUMN `last_name` VARCHAR(50) NULL AFTER `middle_name`,
ADD COLUMN `title` VARCHAR(45) NULL AFTER `last_name`;
