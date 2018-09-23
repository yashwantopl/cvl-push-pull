CREATE TABLE `loan_application`.`fs_retail_applicant_income_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `year` int(20) DEFAULT NULL,
  `salary_income` double DEFAULT NULL,
  `income_ratio` double DEFAULT NULL,
  `house_property` double DEFAULT NULL,
  `pgbp` double DEFAULT NULL,
  `capital_gain` double DEFAULT NULL,
  `other_source` double DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
