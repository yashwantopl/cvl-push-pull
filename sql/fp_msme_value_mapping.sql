-- Table structure for table `fp_msme_value_mapping`

DROP TABLE IF EXISTS `loan_application`.`fp_msme_value_mapping`;

CREATE TABLE `loan_application`.`fp_msme_value_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fp_product_id` bigint(20) unsigned DEFAULT NULL,
  `msme_funding_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_id` (`fp_product_id`),
  CONSTRAINT `fp_msme_value_mapping_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



--parameter addition
ALTER TABLE `loan_application`.`fp_term_loan_details`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;


ALTER TABLE `loan_application`.`fp_wc_tl_details`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;



ALTER TABLE `loan_application`.`fp_working_capital_details`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;


------- In TEMP
DROP TABLE IF EXISTS `loan_application`.`fp_msme_value_mapping_temp`;

CREATE TABLE `loan_application`.`fp_msme_value_mapping_temp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fp_product_id` bigint(20) unsigned DEFAULT NULL,
  `msme_funding_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_id` (`fp_product_id`),
  CONSTRAINT `fp_msme_value_mapping_temp_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master_temp` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

------- In TEMP parameter addition
ALTER TABLE `loan_application`.`fp_term_loan_details_temp`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;

ALTER TABLE `loan_application`.`fp_wc_tl_details_temp`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;

ALTER TABLE `loan_application`.`fp_working_capital_details_temp`
ADD COLUMN `min_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `max_cgtmse_coverage` DECIMAL(19,2) NULL DEFAULT '0.00',
ADD COLUMN `is_cgtmse_coverage_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_cgtmse_coverage_mandatory` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_display` BIT(1) NULL DEFAULT FALSE,
ADD COLUMN `is_msme_funding_mandatory` BIT(1) NULL DEFAULT FALSE;

