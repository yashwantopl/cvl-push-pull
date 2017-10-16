DROP TABLE IF EXISTS `loan_applications`.`fp_negative_industry`;

CREATE TABLE `loan_applications`.`fp_negative_industry` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `industry_id` bigint(20) unsigned DEFAULT NULL,
  `fp_product_master_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master_id` (`fp_product_master_id`),
  CONSTRAINT `fp_negative_industry_ibfk_1` FOREIGN KEY (`fp_product_master_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
alter table `loan_applications`.`fp_working_capital_details` ADD is_uninterested_industry_display  bit(1)  default b'0',ADD is_uninterested_industry_mandatory  bit(1)  default b'0';
alter table `loan_applications`.`fp_term_loan_details` ADD is_uninterested_industry_display  bit(1)  default b'0',ADD is_uninterested_industry_mandatory  bit(1)  default b'0';