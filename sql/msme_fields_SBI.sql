CREATE TABLE `loan_application`.`fs_corporate_director_personal_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `marital_status` BIGINT(20) NULL,
  `spouse_detail` BIGINT(20) NULL,
  `no_of_children` BIGINT(20) NULL,
  `owning_house` BIT(1) NULL,
  `assessed_for_it` BIT(1) NULL,
  `have_li_policy` BIT(1) NULL,
  `created_date` DATETIME NULL DEFAULT NULL,
  `modified_date` DATETIME NULL DEFAULT NULL,
  `created_by` BIGINT(20) NULL DEFAULT NULL,
  `modified_by` BIGINT(20) NULL DEFAULT NULL,
  `is_active` BIT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`));

ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `father_or_spouse_name` VARCHAR(45) NULL,
ADD COLUMN `educational_status` BIGINT(20) NULL,
ADD COLUMN `nationality` BIGINT(20) NULL,
ADD COLUMN `visually_impaired` BIT(1) NULL,
ADD COLUMN `resident_status` BIGINT(20) NULL,
ADD COLUMN `is_guarantor` BIT(1) NULL ,
ADD COLUMN `personal_detail_id` bigint(20) unsigned DEFAULT NULL,
ADD CONSTRAINT `fs_corporate_director_background_details_ibfk_3`
FOREIGN KEY (`personal_detail_id`)
REFERENCES `fs_corporate_director_personal_detail` (`id`);




ALTER TABLE `loan_application`.`fs_corporate_primary_details`
ADD COLUMN `com_op_date` DATETIME NULL,
ADD COLUMN `factory_premise` BIGINT(20) NULL,
ADD COLUMN `know_how` BIGINT(20) NULL,
ADD COLUMN `competition` BIGINT(20) NULL;


ALTER TABLE `loan_application`.`fs_corporate_current_financial_arrangements_details`
ADD COLUMN `lc_bg_status` BIGINT(20) NULL;
