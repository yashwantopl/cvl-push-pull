ALTER TABLE `loan_application`.`fp_uniform_product_details`   
  ADD COLUMN `min_cibil_score` DOUBLE UNSIGNED NULL AFTER `pf`,
  ADD COLUMN `cm_rating` INT(2) UNSIGNED NULL AFTER `min_cibil_score`,
  ADD COLUMN `currency` INT(2) UNSIGNED NULL AFTER `cm_rating`,
  ADD COLUMN `denomination` INT(2) UNSIGNED NULL AFTER `currency`,
  ADD COLUMN `assessment_method` INT(3) UNSIGNED NULL AFTER `denomination`,
  ADD COLUMN `promotor_contribution` DOUBLE UNSIGNED NULL AFTER `assessment_method`,
  ADD COLUMN `wc_requirement` DOUBLE UNSIGNED NULL AFTER `promotor_contribution`,
  ADD COLUMN `max_growth` DOUBLE UNSIGNED NULL AFTER `wc_requirement`;


CREATE TABLE `loan_application`.`fp_uniform_product_details_temp`(
 `fp_product_id` BIGINT(20) UNSIGNED NOT NULL, 
 `min_amount` DOUBLE, 
 `max_amount` DOUBLE, 
 `roi` DOUBLE, 
 `pf` DOUBLE, 
 `min_cibil_score` DOUBLE UNSIGNED NULL,
 `cm_rating` INT(2) UNSIGNED NULL,
 `currency` INT(2) UNSIGNED NULL,
 `denomination` INT(2) UNSIGNED NULL,
 `assessment_method` INT(3) UNSIGNED NULL,
 `promotor_contribution` DOUBLE UNSIGNED NULL,
 `wc_requirement` DOUBLE UNSIGNED NULL,
 `max_growth` DOUBLE UNSIGNED NULL,
 PRIMARY KEY (`fp_product_id`), FOREIGN KEY (`fp_product_id`) REFERENCES `loan_application`.`fp_product_master_temp`(`fp_product_id`) 
 );


ALTER TABLE `loan_application`.`fp_uniform_product_details`
  CHANGE COLUMN `cm_rating` `min_cmr` INT(2) UNSIGNED NULL,
  ADD COLUMN `max_cmr` INT(2) UNSIGNED NULL;
  
  ALTER TABLE `loan_application`.`fp_uniform_product_details_temp`
  CHANGE COLUMN `cm_rating` `min_cmr` INT(2) UNSIGNED NULL,
  ADD COLUMN `max_cmr` INT(2) UNSIGNED NULL;



ALTER TABLE `loan_application`.`fp_uniform_product_details`
  ADD COLUMN `version` INT(2) UNSIGNED NULL;
  
  ALTER TABLE `loan_application`.`fp_uniform_product_details_temp`
  ADD COLUMN `version` INT(2) UNSIGNED NULL;



CREATE TABLE `loan_application`.`fp_uniform_product_details_audit`(
 `id` BIGINT(20) UNSIGNED NOT NULL, 
 `min_amount` DOUBLE, 
 `min_amount_audit` VARCHAR(200),
 `max_amount` DOUBLE, 
 `max_amount_audit` VARCHAR(200),
 `roi` DOUBLE, 
 `roi_audit` VARCHAR(200),
 `pf` DOUBLE, 
 `pf_audit` VARCHAR(200),
 `min_cibil_score` DOUBLE UNSIGNED NULL,
 `min_cibil_score_audit` VARCHAR(200),
 `cm_rating` INT(2) UNSIGNED NULL,
 `cm_rating_audit` VARCHAR(200),
 `currency` INT(2) UNSIGNED NULL,
 `currency_audit` VARCHAR(200),
 `denomination` INT(2) UNSIGNED NULL,
 `denomination_audit` VARCHAR(200),
 `assessment_method` INT(3) UNSIGNED NULL,
 `assessment_method_audit` VARCHAR(200),
 `promotor_contribution` DOUBLE UNSIGNED NULL,
 `promotor_contribution_audit` VARCHAR(200),
 `wc_requirement` DOUBLE UNSIGNED NULL,
 `wc_requirement_audit` VARCHAR(200),
 `max_growth` DOUBLE UNSIGNED NULL ,
 `max_growth_audit` VARCHAR(200),
 `created_date` DATETIME,
 PRIMARY KEY (`id`)
 );

ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` CHANGE `id` `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT; 

ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` 
ADD COLUMN fp_product_id BIGINT(20),
ADD COLUMN org_id BIGINT(20);



ALTER TABLE `loan_application`.`fp_uniform_product_details_audit`
CHANGE COLUMN `cm_rating` `min_cmr` INT(2) UNSIGNED NULL,
CHANGE COLUMN `cm_rating_audit` `min_cmr_audit` VARCHAR(200),
ADD COLUMN `max_cmr` INT(2) UNSIGNED NULL,
ADD COLUMN `max_cmr_audit` VARCHAR(200);




DELIMITER $$

USE `loan_application`$$

DROP TRIGGER /*!50032 IF EXISTS */ `uniform_product_update_audit`$$

CREATE
    /*!50017 DEFINER = 'dbsidbi'@'%' */
    TRIGGER `uniform_product_update_audit` AFTER UPDATE ON `fp_uniform_product_details` 
    FOR EACH ROW BEGIN 
    IF (NEW.version IS NOT NULL AND NEW.version != OLD.version) THEN
    INSERT INTO `loan_application`.`fp_uniform_product_details_audit` 
    (
    fp_product_id,
    min_amount,
    min_amount_audit,
    max_amount,
    max_amount_audit,
    roi,
    roi_audit,
    pf,
    pf_audit,
    min_cibil_score,
    min_cibil_score_audit,
    min_cmr,
    min_cmr_audit,
    max_cmr,
    max_cmr_audit,
    currency,
    currency_audit,
    denomination,
    denomination_audit,
    assessment_method,
    assessment_method_audit,
    promotor_contribution,
    promotor_contribution_audit,
    wc_requirement,
    wc_requirement_audit,
    max_growth,
    max_growth_audit,
    created_date,
    org_id,
    `version`,
    version_audit,
    dpd,
    dpd_audit,
    is_active,
    is_active_audit,
    from_date) 
    VALUES (
    OLD.fp_product_id,
    OLD.min_amount, 
    CONCAT('{"oldValue":',OLD.min_amount , ',"newValue":',NEW.min_amount, ',"isChanged":', (OLD.min_amount <> NEW.min_amount), ' } ' ) ,
    OLD.max_amount,
    CONCAT('{"oldValue":',OLD.max_amount , ',"newValue":',NEW.max_amount, ',"isChanged":', (OLD.max_amount <> NEW.max_amount), ' } ' ),
    OLD.roi,
    CONCAT('{"oldValue":',OLD.roi , ',"newValue":',NEW.roi, ',"isChanged":', (OLD.roi <> NEW.roi), ' } ' ),
    OLD.pf,
    CONCAT('{"oldValue":',OLD.pf , ',"newValue":',NEW.pf, ',"isChanged":', (OLD.pf <> NEW.pf), ' } ' ),
    OLD.min_cibil_score,
    CONCAT('{"oldValue":',OLD.min_cibil_score , ',"newValue":',NEW.min_cibil_score, ',"isChanged":', (OLD.min_cibil_score <> NEW.min_cibil_score), ' } ' ),
    OLD.min_cmr,
    CONCAT('{"oldValue":',OLD.min_cmr , ',"newValue":',NEW.min_cmr, ',"isChanged":', (OLD.min_cmr <> NEW.min_cmr), ' } ' ),
    OLD.max_cmr,
    CONCAT('{"oldValue":',OLD.max_cmr , ',"newValue":',NEW.max_cmr, ',"isChanged":', (OLD.max_cmr <> NEW.max_cmr), ' } ' ),
    OLD.currency,
    CONCAT('{"oldValue":',OLD.currency , ',"newValue":',NEW.currency, ',"isChanged":', (OLD.currency <> NEW.currency), ' } ' ),
    OLD.denomination,
    CONCAT('{"oldValue":',OLD.denomination , ',"newValue":',NEW.denomination, ',"isChanged":', (OLD.denomination <> NEW.denomination), ' } ' ),
    OLD.assessment_method,
    CONCAT('{"oldValue":',OLD.assessment_method , ',"newValue":',NEW.assessment_method, ',"isChanged":', (OLD.assessment_method <> NEW.assessment_method), ' } ' ),
    OLD.promotor_contribution,
    CONCAT('{"oldValue":',OLD.promotor_contribution , ',"newValue":',NEW.promotor_contribution, ',"isChanged":', (OLD.promotor_contribution <> NEW.promotor_contribution), ' } ' ),
    OLD.wc_requirement,
    CONCAT('{"oldValue":',OLD.wc_requirement , ',"newValue":',NEW.wc_requirement, ',"isChanged":', (OLD.wc_requirement <> NEW.wc_requirement), ' } ' ),
    OLD.max_growth,
    CONCAT('{"oldValue":',OLD.max_growth , ',"newValue":',NEW.max_growth, ',"isChanged":', (OLD.max_growth <> NEW.max_growth), ' } ' ),
    NOW(),
    (SELECT pm.user_org_id FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id),
    OLD.version,
    CONCAT('{"oldValue":',OLD.version , ',"newValue":',NEW.version, ',"isChanged":', (OLD.version <> NEW.version), ' } '),
    OLD.dpd,
    CONCAT('{"oldValue":',OLD.dpd , ',"newValue":',NEW.dpd, ',"isChanged":', (OLD.dpd <> NEW.dpd), ' } '),
    (SELECT pm.is_active FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id),
    CONCAT('{"oldValue":',(SELECT pm.is_active FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id) , ',"newValue":',(SELECT pm.is_active FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id), ',"isChanged":', ((SELECT pm.is_active FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id) <> (SELECT pm.is_active FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id)),' } '),
    (SELECT IF (pm.modified_date IS NULL,pm.created_date,pm.modified_date) FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = OLD.fp_product_id));
    END IF;
    END;
$$
DELIMITER ;

ALTER TABLE `loan_application`.`fp_uniform_product_details_audit`
ADD COLUMN `version` INT(3);
ALTER TABLE `loan_application`.`fp_uniform_product_details_audit`
ADD COLUMN `version_audit` VARCHAR(200);



ALTER TABLE `loan_application`.`proposal_details` ADD COLUMN `version` INT(3) NULL AFTER `reason`; 
ALTER TABLE `loan_application`.`application_product_audit` ADD COLUMN `version` INT(3) NULL AFTER `score`; 


ALTER TABLE `loan_application`.`fp_uniform_product_details` ADD COLUMN `dpd` INT(3);
ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` ADD COLUMN `dpd` INT(3);
ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` ADD COLUMN `dpd_audit` VARCHAR(1000);
ALTER TABLE `loan_application`.`fp_uniform_product_details_temp` ADD COLUMN `dpd` INT(3);

ALTER TABLE `loan_application`.`fp_uniform_product_details_temp` ADD COLUMN `is_edited` BIT(1);

ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` 
ADD COLUMN is_active BIT(1),
ADD COLUMN is_active_audit BIT(1);


ALTER TABLE `loan_application`.`fp_uniform_product_details_audit` 
MODIFY COLUMN is_active_audit VARCHAR(200);