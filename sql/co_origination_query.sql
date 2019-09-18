
ALTER TABLE `loan_application`.`proposal_details` ADD COLUMN `nbfc_flow` INT NULL;
ALTER TABLE `connect`.`connect_log` ADD COLUMN `is_processing` BIT NULL;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`loan_application` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `loan_application`;

/*Table structure for table `proposal_details_audit_nbfc` */

DROP TABLE IF EXISTS `proposal_details_audit_nbfc`;

CREATE TABLE `proposal_details_audit_nbfc` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `proposal_id` BIGINT(20),
  `application_id` BIGINT(20) DEFAULT NULL,
  `fp_product_id` BIGINT(20) DEFAULT NULL,
  `proposal_status_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_proposal_auto` BIT(1) DEFAULT b'0',
  `proposal_stage` INT(1) DEFAULT NULL,
  `initiated_by` INT(1) DEFAULT NULL,
  `last_action_performed_by` INT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `user_org_id` BIGINT(1) DEFAULT NULL,
  `user_score` DOUBLE DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  `pending_proposal_update_date` DATETIME DEFAULT NULL,
  `existing_loan_amount` DOUBLE DEFAULT '0',
  `additional_loan_amount` DOUBLE DEFAULT '0',
  `el_amount` DOUBLE DEFAULT '0',
  `el_tenure` DOUBLE DEFAULT NULL,
  `el_roi` DOUBLE DEFAULT NULL,
  `assign_by` BIGINT(20) DEFAULT NULL,
  `assign_branch_to` BIGINT(20) DEFAULT NULL,
  `assign_date` DATETIME DEFAULT NULL,
  `emi` DOUBLE DEFAULT NULL,
  `processing_fee` DOUBLE DEFAULT NULL,
  `branch_id` BIGINT(20) DEFAULT NULL,
  `reason` TEXT,
  `version` INT(3) DEFAULT NULL,
  `is_push_next_day` BIT(1) DEFAULT b'0',
  `is_save_phase_one` BIT(1) DEFAULT b'0',
  `is_save_phase_two` BIT(1) DEFAULT b'0',
  `sanction_disbursed_status` VARCHAR(50) DEFAULT NULL,
  `reject_hold_by` INT(11) DEFAULT NULL,
  `service_not_found` VARCHAR(255) DEFAULT NULL,
  `min_pf` DOUBLE DEFAULT NULL,
  `max_pf` DOUBLE DEFAULT NULL,
  `consession_roi` DOUBLE DEFAULT NULL,
  `concession_based_on_type` VARCHAR(200) DEFAULT NULL,
  `mclr_roi` DOUBLE DEFAULT NULL,
  `spread_roi` DOUBLE DEFAULT NULL,
  `scoring_model_based_on` INT(11) DEFAULT NULL,
  `nbfc_flow` INT(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `match_status_id` (`proposal_status_id`),
  CONSTRAINT `proposal_details_ibfk_11` FOREIGN KEY (`proposal_status_id`) REFERENCES `proposal_status_master` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


CREATE DATABASE /*!32312 IF NOT EXISTS*/`loan_application` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `loan_application`;

/*Table structure for table `nbfc_proposal_blended_rate` */

DROP TABLE IF EXISTS `nbfc_proposal_blended_rate`;

CREATE TABLE `nbfc_proposal_blended_rate` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `nbfc_org_id` bigint(20) DEFAULT NULL,
  `bank_org_id` bigint(20) DEFAULT NULL,
  `bl_exisiting_amount` double DEFAULT NULL,
  `bl_additional_amount` double DEFAULT NULL,
  `bl_amount` double DEFAULT NULL,
  `bl_tenure` double DEFAULT NULL,
  `bl_roi` double DEFAULT NULL,
  `bl_emi` double DEFAULT NULL,
  `bl_processing_fee` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;