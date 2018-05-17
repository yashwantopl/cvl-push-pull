CREATE TABLE `loan_application`.`fp_np_mapping` (
  `id` BIGINT(20) NOT NULL,
  `fp_product_id` BIGINT(20) NULL,
  `np_user_id` BIGINT(20) NULL,
  `application_id` BIGINT(20) NULL,
  `created_by` BIGINT(20) NULL,
  `created_date` DATETIME NULL,
  `modified_by` BIGINT(20) NULL,
  `modified_date` DATETIME NULL,
  `is_active` BIT(1) NULL,
  PRIMARY KEY (`id`));