DROP TABLE IF EXISTS `loan_applications`.`log_details`;
CREATE TABLE `loan_applications`.`log_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loan_application_master_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `product_mapping_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `date_type_master_id` INT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;