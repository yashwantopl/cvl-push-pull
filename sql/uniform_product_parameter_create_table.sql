CREATE TABLE `loan_application`.`fp_uniform_product_details`(
 `fp_product_id` BIGINT(20) UNSIGNED NOT NULL, 
 `min_amount` DOUBLE, 
 `max_amount` DOUBLE, 
 `roi` DOUBLE, 
 `pf` DOUBLE, 
 PRIMARY KEY (`fp_product_id`), FOREIGN KEY (`fp_product_id`) REFERENCES `loan_application`.`fp_product_master`(`fp_product_id`) 
 );