CREATE TABLE `loan_application`.`token_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `is_expired` bit(1) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `token` varchar(255) NOT NULL,
  `is_active` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
