
ALTER TABLE `loan_application`.`fs_retail_co_applicant_details`
ADD COLUMN `email` VARCHAR(100) NULL;

ALTER TABLE `loan_application`.`fs_retail_co_applicant_details`
ADD COLUMN `mobile` VARCHAR(100) NULL;


CREATE TABLE loan_application.`fs_retail_co_applicant_income_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) DEFAULT NULL,
  `year` INT(20) DEFAULT NULL,
  `salary_income` DOUBLE DEFAULT NULL,
  `income_ratio` DOUBLE DEFAULT NULL,
  `house_property` DOUBLE DEFAULT NULL,
  `pgbp` DOUBLE DEFAULT NULL,
  `capital_gain` DOUBLE DEFAULT NULL,
  `other_source` DOUBLE DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  `salary_income_gross` DOUBLE DEFAULT NULL,
  `house_property_gross` DOUBLE DEFAULT NULL,
  `pgbp_gross` DOUBLE DEFAULT NULL,
  `capital_gain_gross` DOUBLE DEFAULT NULL,
  `other_source_gross` DOUBLE DEFAULT NULL,
  `salary_mode` VARCHAR(50) DEFAULT NULL,
  `house_property_mode` VARCHAR(50) DEFAULT NULL,
  `pgbp_mode` VARCHAR(50) DEFAULT NULL,
  `capital_gain_mode` VARCHAR(50) DEFAULT NULL,
  `other_source_mode` VARCHAR(50) DEFAULT NULL,
  `proposal_mapping_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=839 DEFAULT CHARSET=latin1;