ALTER TABLE `loan_application`.`fp_product_master`
ADD COLUMN `wc_renewal_status` INT(2) NULL;

ALTER TABLE `loan_application`.`fp_product_master_temp`
ADD COLUMN `wc_renewal_status` INT(2) NULL;