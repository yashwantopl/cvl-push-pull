--for bodmas
ALTER TABLE `loan_application`.`fp_product_master`
	ADD COLUMN `import_from_id` BIGINT(20) NULL,
	ADD COLUMN `is_gst` BIT NULL AFTER `import_from_id`,
	ADD COLUMN `is_itr` BIT NULL AFTER `is_gst`,
	ADD COLUMN `is_bank_statement` BIT NULL AFTER `is_itr`,
	ADD COLUMN `is_mca` BIT NULL AFTER `is_bank_statement`,
	ADD COLUMN `is_bureu_personal` BIT NULL AFTER `is_mca`,
	ADD COLUMN `is_bureu_commercial` BIT NULL AFTER `is_bureu_personal`,
	ADD COLUMN `is_manual_fill` BIT NULL AFTER `is_bureu_commercial`;

ALTER TABLE `loan_application`.`fp_product_master_temp`
	ADD COLUMN `import_from_id` BIGINT(20) NULL,
	ADD COLUMN `is_gst` BIT NULL AFTER `import_from_id`,
	ADD COLUMN `is_itr` BIT NULL AFTER `is_gst`,
	ADD COLUMN `is_bank_statement` BIT NULL AFTER `is_itr`,
	ADD COLUMN `is_mca` BIT NULL AFTER `is_bank_statement`,
	ADD COLUMN `is_bureu_personal` BIT NULL AFTER `is_mca`,
	ADD COLUMN `is_bureu_commercial` BIT NULL AFTER `is_bureu_personal`,
	ADD COLUMN `is_manual_fill` BIT NULL AFTER `is_bureu_commercial`;


CREATE TABLE `loan_application`.`fp_product_conditions` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `fp_product_id` BIGINT(20),
  `condition_name` VARCHAR(500),
  `is_mandatory` BIT DEFAULT b'0',
  `is_all_mandatory` BIT DEFAULT b'0',
  `all_logical_condition` INT,
  `created_by` BIGINT(20),
  `created_date` DATETIME,
  `updated_by` BIGINT(20),
  `updated_date` DATETIME,
  `is_active` BIT DEFAULT b'1',
  PRIMARY KEY (`id`)
);

CREATE TABLE `loan_application`.`fp_product_parameters` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20),
  `condition_id` BIGINT(20),
  `bodmas_formula_id` BIGINT(20),
  `compare_value` DOUBLE,
  `min_value` DOUBLE,
  `max_value` DOUBLE,
  `logical_condition` INT,
  `parameter_operator` INT,
  `parent_id` BIGINT,
  `is_group` BIT DEFAULT b'0',
  `created_by` BIGINT(20),
  `created_date` DATETIME,
  `updated_by` BIGINT(20),
  `updated_date` DATETIME,
  `is_active` BIT DEFAULT b'1',
  PRIMARY KEY (`id`)
);