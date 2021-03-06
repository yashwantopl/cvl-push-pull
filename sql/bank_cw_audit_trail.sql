create TABLE `loan_application`.`bank_cw_audit_trail` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `org_id` bigint(11) unsigned DEFAULT NULL,
  `application_id` bigint(11) unsigned DEFAULT NULL,
  `bank_request` blob,
  `cw_response` blob,
  `status` varchar(100) DEFAULT NULL,
  `msg` varchar(100) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

ALTER TABLE `loan_application`.`bank_cw_audit_trail` 
CHANGE COLUMN `msg` `msg` VARCHAR(200) NULL DEFAULT NULL ;

ALTER TABLE `loan_application`.`bank_cw_audit_trail` 
CHANGE COLUMN `status` `status` VARCHAR(200) NULL DEFAULT NULL ;

ALTER TABLE `loan_application`.`bank_cw_audit_trail` 
ADD COLUMN `statement_type` BIGINT(10) NULL AFTER `msg`;

ALTER TABLE `loan_application`.`bank_cw_audit_trail` 
CHANGE COLUMN `statement_type` `api_type` BIGINT(10) NULL DEFAULT NULL AFTER `status`,
CHANGE COLUMN `msg` `failure_reason` VARCHAR(200) NULL DEFAULT NULL ;


ALTER TABLE `loan_application`.`bank_cw_audit_trail` 
CHANGE COLUMN `failure_reason` `failure_reason` LONGTEXT NULL DEFAULT NULL ;
