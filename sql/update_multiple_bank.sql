ALTER TABLE `loan_application`.`fs_corporate_final_mcq_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_sector_subsector_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`industry_sector_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_existing_product_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_associated_concern_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_security_corporate_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_credit_rating_organization_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_finance_means_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_project_cost_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_ddr_form_details` ADD COLUMN `org_id` BIGINT NULL AFTER `customer_name`;
ALTER TABLE `workflow`.`workflow_jobs` ADD COLUMN `proposal_id` BIGINT NULL AFTER `application_id`;
ALTER TABLE `document_management`.`product_storage_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_assets_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_liabilities_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_operating_statement_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_promotor_background_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;

------------------------------------------------------------------------

USE `loan_application`;

/*Table structure for table loan_application.`application_product_audit_delete_log` */

DROP TABLE IF EXISTS loan_application.`application_product_audit_delete_log`;

CREATE TABLE loan_application.`application_product_audit_delete_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `fp_product_id` bigint(20) DEFAULT NULL,
  `fp_user_id` bigint(20) DEFAULT NULL,
  `stage_id` bigint(20) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `offered_roi` decimal(19,2) DEFAULT NULL,
  `processing_fee` decimal(19,2) DEFAULT NULL,
  `foir` decimal(19,2) DEFAULT NULL,
  `emi` double DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `type_id` bigint(20) DEFAULT NULL,
  `message` varchar(1000) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_stage_wise_mathces_1_idx` (`stage_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
------------------------------------------------------------------------

DELIMITER $$

USE loan_application$$

DROP TRIGGER /*!50032 IF EXISTS */ `application_product_audit_before_delete`$$

CREATE
    /*!50017 DEFINER = 'dbsidbi'@'%' */
    TRIGGER `application_product_audit_before_delete` AFTER DELETE ON `loan_application`.`application_product_audit`
    FOR EACH ROW BEGIN
INSERT INTO `loan_application`.`application_product_audit_delete_log` (
  `id`,
  `application_id`,
  `fp_product_id`,
  `fp_user_id`,
  `stage_id`,
  `max_loan_amount`,
  `max_tenure`,
  `offered_roi`,
  `processing_fee`,
  `foir`,
  `emi`,
  `created_by`,
  `created_date`,
  `modified_by`,
  `modified_date`,
  `is_active`,
  `type_id`,
  `message`,
  `score`,
  delete_date
)
VALUES
  (
    OLD.id,
    OLD.application_id,
    OLD.fp_product_id,
    OLD.fp_user_id,
    OLD.stage_id,
    OLD.max_loan_amount,
    OLD.max_tenure,
    OLD.offered_roi,
    OLD.processing_fee,
    OLD.foir,
    OLD.emi,
    OLD.created_by,
    OLD.created_date,
    OLD.modified_by,
    OLD.modified_date,
    OLD.is_active,
    OLD.type_id,
    OLD.message,
    OLD.score,
    NOW()
  ) ;

END;
$$

DELIMITER ;