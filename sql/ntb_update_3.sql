ALTER TABLE `loan_application`.`fp_product_master`
ADD COLUMN `business_type_id` BIGINT(20) NULL ;

ALTER TABLE `loan_application`.`fp_product_master_temp`
ADD COLUMN `business_type_id` BIGINT(20) NULL ;
