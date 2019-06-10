CREATE TABLE `fs_loan_type_accessible_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `loan_type_selection_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `fk_loan_typesel` (`loan_type_selection_id`),
  CONSTRAINT `fk_loan_typesel` FOREIGN KEY (`loan_type_selection_id`) REFERENCES `fs_loan_type_selection` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;