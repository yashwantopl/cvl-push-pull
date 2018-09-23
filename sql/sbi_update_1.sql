ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
CHANGE COLUMN `father_or_spouse_name` `father_name` VARCHAR(45) NULL DEFAULT NULL ,
CHANGE COLUMN `visually_impaired` `visually_impaired` BIGINT(20) NULL DEFAULT NULL ,
CHANGE COLUMN `is_guarantor` `is_guarantor` BIT(1) NULL DEFAULT TRUE ;


ALTER TABLE `loan_application`.`fs_corporate_director_personal_detail`
CHANGE COLUMN `owning_house` `owning_house` BIGINT(20) NULL DEFAULT NULL ,
CHANGE COLUMN `assessed_for_it` `assessed_for_it` BIGINT(20) NULL DEFAULT NULL ,
CHANGE COLUMN `have_li_policy` `have_li_policy` BIGINT(20) NULL DEFAULT NULL ,
ADD COLUMN `spouse_name` VARCHAR(50) NULL AFTER `marital_status`;
