CREATE TABLE `loan_application`.`tutorial_view_audit` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20),
  `role_id` BIGINT(20),
  `role_id` INT(11),
  `tutorial_id` BIGINT(20),
  `view_date` DATE,
  PRIMARY KEY (`id`)
);

INSERT INTO `document_management`.`document_master` (`id`,`name`,`type`,`size`,`created_date`,`is_active`) VALUES (172,'MFI','jpeg,jpg,png',10,NOW(),TRUE);
INSERT INTO `document_management`.`product_document_mapping` (`id`,`document_id`,`is_mandatory`,`created_date`,`is_active`) VALUES (593,172,TRUE,NOW(),TRUE);

ALTER TABLE `loan_application`.`fs_mfi_applicant_details`
	CHANGE `address_proof_img` `address_proof_img` VARCHAR(200) NULL,
	CHANGE `consent_form_img` `consent_form_img` VARCHAR(200) NULL,
	CHANGE `profile_img` `profile_img` VARCHAR(200) NULL;
ALTER TABLE `loan_application`.`fs_mfi_bank_details`
	CHANGE `passbook_img` `passbook_img` VARCHAR(200) NULL;

