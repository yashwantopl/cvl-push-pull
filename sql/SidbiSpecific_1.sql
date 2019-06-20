CREATE TABLE `loan_application`.`fs_sidbi_primary_collateral_security` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,  
  `particulars_id` INT(2) DEFAULT NULL,
  `other_particular` VARCHAR(2000) DEFAULT NULL,
  `name_Of_owner` VARCHAR(2000) DEFAULT NULL,
  `nature` VARCHAR(2000) DEFAULT NULL,
  `details` VARCHAR(2000) DEFAULT NULL,
  `market_value` DOUBLE DEFAULT NULL,
  `particulars_of_charge` VARCHAR(2000) DEFAULT NULL,
  `charge_offered_to_sidbi` VARCHAR(2000) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,  
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `loan_application`.`fs_sidbi_personal_corporate_guarantee` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,    
  `name_of_guarantor` VARCHAR(2000) DEFAULT NULL,
  `pan_cin_any_no` VARCHAR(500) DEFAULT NULL,
  `net_worth` DOUBLE DEFAULT NULL,  
  `is_active` BIT(1) DEFAULT NULL,  
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;