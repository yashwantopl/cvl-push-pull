CREATE TABLE `fs_mfi_financial_arrangement_history_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `financial_arrangement_id` bigint(20) DEFAULT NULL,
  `outstanding_amount` double DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FINANCIAL_ARRANGEMENT_ID` (`financial_arrangement_id`),
  CONSTRAINT `FK_FINANCIAL_ARRANGEMENT_ID` FOREIGN KEY (`financial_arrangement_id`) REFERENCES `fs_mfi_current_financial_arrangements_details` (`id`)
);
