ALTER TABLE loan_application.fs_corporate_applicant_details ADD COLUMN cast_category VARCHAR(50) NULL; 

CREATE TABLE `fs_corporate_collateral_security_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `collateral_type` varchar(50) DEFAULT NULL,
  `other_collateral` varchar(50) DEFAULT NULL,
  `collateral_amount` double DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--- Update Trigger on Connect DB ---
connect_after_update

