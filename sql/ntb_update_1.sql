DROP TABLE IF EXISTS `fs_corporate_employment_details`;
CREATE TABLE `fs_corporate_employment_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_of_employment` BIGINT(20) DEFAULT NULL,
  `employment_with` BIGINT(20) DEFAULT NULL,
  `employment_status` BIGINT(20) DEFAULT NULL,
  `total_experience` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `aadhar` VARCHAR(20) NULL DEFAULT NULL AFTER `is_one_form_completed`,
ADD COLUMN `marital_status` BIGINT(20) NULL AFTER `aadhar`,
ADD COLUMN `no_of_dependent` BIGINT(20) NULL AFTER `marital_status`,
ADD COLUMN `residence_type` BIGINT(20) NULL AFTER `no_of_dependent`,
ADD COLUMN `residence_since` BIGINT(20) NULL AFTER `residence_type`,
ADD COLUMN `is_family_member_in_business` BIT(1) NULL AFTER `residence_since`,
ADD COLUMN `emp_detail_id` BIGINT(20) UNSIGNED NULL,
ADD COLUMN `premise_number` VARCHAR(200) NULL AFTER `emp_detail_id`,
ADD COLUMN `street_name` VARCHAR(200) NULL AFTER `premise_number`,
ADD COLUMN `landmark` VARCHAR(200) NULL AFTER `street_name`,
ADD COLUMN `country_id` INT(20) UNSIGNED NULL AFTER `landmark`,
ADD INDEX `fk_fs_corporate_director_background_details_1_idx` (`emp_detail_id` ASC);
ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD CONSTRAINT `fs_corporate_director_background_details_ibfk_2`
  FOREIGN KEY (`emp_detail_id`)
  REFERENCES `loan_application`.`fs_corporate_employment_details` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;



ALTER TABLE `loan_application`.`fs_corporate_primary_details`
ADD COLUMN `tenure_required` DOUBLE NULL AFTER `purpose_of_loan_id`,
ADD COLUMN `proposed_details_of_unit` BIGINT(20) NULL AFTER `tenure_required`,
ADD COLUMN `cost_of_project` DECIMAL(19,2) NULL AFTER `proposed_details_of_unit`,
ADD COLUMN `created_date` DATETIME NULL AFTER `cost_of_project`,
ADD COLUMN `modified_date` DATETIME NULL DEFAULT NULL AFTER `created_date`,
ADD COLUMN `created_by` BIGINT(20) NULL DEFAULT NULL AFTER `modified_date`,
ADD COLUMN `modified_by` BIGINT(20) NULL DEFAULT NULL AFTER `created_by`,
ADD COLUMN `is_active` BIT(1) NULL DEFAULT NULL AFTER `modified_by`;




ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
DROP COLUMN `residence_since`;


ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `qualification_id` BIGINT(20) NULL ,
ADD COLUMN `residence_since_month` BIGINT(20) NULL ,
ADD COLUMN `residence_since_year` BIGINT(20) NULL ,
ADD COLUMN `state_id` BIGINT(20) NULL ,
ADD COLUMN `city_id` BIGINT(20) NULL ;






ALTER TABLE `loan_application`.`fs_corporate_primary_details`
ADD COLUMN `proposed_operation_date` DATETIME NULL,
ADD COLUMN `proposed_const_of_unit` INT(2) NULL,
ADD COLUMN `promoter_contribution` DOUBLE NULL ,
ADD COLUMN `assessment_id` INT(4) NULL ;
