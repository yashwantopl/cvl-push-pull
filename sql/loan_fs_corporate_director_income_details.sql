CREATE TABLE `loan_application`.`fs_corporate_director_income_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `director_id` bigint(20) DEFAULT NULL,
  `year` varchar(20) DEFAULT NULL,
  `salary` double DEFAULT '0',
  `total_income` double DEFAULT NULL,
  `house_property` varchar(45) DEFAULT NULL,
  `PGBP` varchar(45) DEFAULT NULL,
  `capital_gain` varchar(45) DEFAULT NULL,
  `other_source` varchar(45) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
