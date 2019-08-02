drop table loan_application.fs_mfi_applicant_details;
CREATE TABLE loan_application.`fs_mfi_applicant_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `proposal_mapping_id` bigint(20) DEFAULT NULL,
  `aadhar_number` varchar(50) DEFAULT NULL,
  `name_as_per_aadharCard` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender_id` int(11) DEFAULT NULL,
  `marital_status_id` int(11) DEFAULT NULL,
  `current_district` varchar(50) DEFAULT NULL,
  `aadhar_district` varchar(50) DEFAULT NULL,
  `current_house` varchar(50) DEFAULT NULL,
  `aadhar_house` varchar(50) DEFAULT NULL,
  `current_landmark` varchar(200) DEFAULT NULL,
  `aadhar_landmark` varchar(200) DEFAULT NULL,
  `current_location` varchar(50) DEFAULT NULL,
  `aadhar_location` varchar(50) DEFAULT NULL,
  `current_state` varchar(50) DEFAULT NULL,
  `aadhar_state` varchar(50) DEFAULT NULL,
  `current_street` varchar(50) DEFAULT NULL,
  `aadhar_street` varchar(50) DEFAULT NULL,
  `current_vtc` varchar(50) DEFAULT NULL,
  `aadhar_vtc` varchar(50) DEFAULT NULL,
  `aadhar_subdist` varchar(50) DEFAULT NULL,
  `current_subdist` varchar(50) DEFAULT NULL,
  `aadhar_po` varchar(50) DEFAULT NULL,
  `current_po` varchar(50) DEFAULT NULL,
  `aadhar_care_of` varchar(50) DEFAULT NULL,
  `address_pincode` varchar(50) DEFAULT NULL,
  `address_same_as_aadhar` bit(1) DEFAULT b'0',
  `aadhar_pincode` varchar(50) DEFAULT NULL,
  `address_proof_type` int(11) DEFAULT NULL,
  `father_name` varchar(50) DEFAULT NULL,
  `mother_name` varchar(50) DEFAULT NULL,
  `spouse_name` varbinary(50) DEFAULT NULL,
  `spouse_birth_date` date DEFAULT NULL,
  `spouse_mobile` varchar(50) DEFAULT NULL,
  `no_dependent` int(11) DEFAULT NULL,
  `nominee_name` varchar(50) DEFAULT NULL,
  `relation_with_nominee_id` int(11) DEFAULT NULL,
  `nominee_address` varchar(200) DEFAULT NULL,
  `nominee_pincode` varchar(50) DEFAULT NULL,
  `religion` int(11) DEFAULT NULL,
  `education_qualification` int(11) DEFAULT NULL,
  `land_holding` double(19,2) DEFAULT NULL,
  `name_of_firm` varchar(50) DEFAULT NULL,
  `business_type` int(11) DEFAULT NULL,
  `house_type` int(11) DEFAULT NULL,
  `loan_type` int(11) DEFAULT NULL,
  `loan_purpose` varchar(50) DEFAULT NULL,
  `loan_amount_required` double(19,2) DEFAULT NULL,
  `cost_of_project` double(19,2) DEFAULT NULL,
  `cost_of_equipment` double(19,2) DEFAULT NULL,
  `working_cap_of_equipment` double(19,2) DEFAULT NULL,
  `total_cost_equipment` double(19,2) DEFAULT NULL,
  `promoter_contribution` double(19,2) DEFAULT NULL,
  `loan_required_from_sidbi` double(19,2) DEFAULT NULL,
  `total_mean_finance` double(19,2) DEFAULT NULL,
  `total_cash_flow` double(19,2) DEFAULT NULL,
  `repayment_frequency` int(11) DEFAULT NULL,
  `insurence_required` bit(1) DEFAULT b'0',
  `insurence_company_name` varchar(50) DEFAULT NULL,
  `insurence_premium` double(19,2) DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` date DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_date` date DEFAULT NULL,
  `remarks` varchar(200) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `is_personal_details_filled` bit(1) DEFAULT b'0',
  `is_family_details_filled` bit(1) DEFAULT b'0',
  `is_nominee_details_filled` bit(1) DEFAULT b'0',
  `is_acadamic_details_filled` bit(1) DEFAULT b'0',
  `is_bank_details_filled` bit(1) DEFAULT b'0',
  `is_account_details_filled` bit(1) DEFAULT b'0',
  `is_existing_loan_details_filled` bit(1) DEFAULT b'0',
  `is_income_details_filled` bit(1) DEFAULT b'0',
  `is_family_income_filled` bit(1) DEFAULT b'0',
  `is_family_expense_filled` bit(1) DEFAULT b'0',
  `is_expected_income_filled` bit(1) DEFAULT b'0',
  `is_ppi_filled` bit(1) DEFAULT b'0',
  `is_project_details_filled` bit(1) DEFAULT b'0',
  `is_apply_loan_filled` bit(1) DEFAULT b'0',
  `is_cost_project_filled` bit(1) DEFAULT b'0',
  `is_mean_finance_filled` bit(1) DEFAULT b'0',
  `is_cash_flow_details_filled` bit(1) DEFAULT b'0',
  `is_assets_details_filled` bit(1) DEFAULT b'0',
  `is_current_assets_filled` bit(1) DEFAULT b'0',
  `is_fixed_assets_filled` bit(1) DEFAULT b'0',
  `is_currnt_liability_filled` bit(1) DEFAULT b'0',
  `is_repayment_details_filled` bit(1) DEFAULT b'0',
  `is_consent_form_filled` bit(1) DEFAULT b'0',
  `status` int(11) DEFAULT NULL,
  `nominee_state` varchar(50) DEFAULT NULL,
  `nominee_city` varchar(50) DEFAULT NULL,
  `nominee_district` varchar(32) DEFAULT NULL,
  `nominee_location` varchar(32) DEFAULT NULL,
  `nominee_house_no` varchar(32) DEFAULT NULL,
  `nominee_landmark` varchar(32) DEFAULT NULL,
  `academic_religion` int(11) DEFAULT NULL,
  `academic_caste` int(11) DEFAULT NULL,
  `is_academic_life_insurance` int(11) DEFAULT NULL,
  `academic_sum_insured` double DEFAULT NULL,
  `house_ownership` int(11) DEFAULT NULL,
  `area_type` int(11) DEFAULT NULL,
  `business_premises` int(50) DEFAULT NULL,
  `exp_in_same_line` int(11) DEFAULT NULL,
  `nominee_birth_date` date DEFAULT NULL,
  `address_proof_img` blob,
  `consent_form_img` blob,
  `profile_img` blob,
  `ship_shgi_installment` double DEFAULT NULL,
  `other_installment` double DEFAULT NULL,
  `loan_installment` double DEFAULT NULL,
  `education_expense` double DEFAULT NULL,
  `medical_expense` double DEFAULT NULL,
  `food_expense` double DEFAULT NULL,
  `other_expense` double DEFAULT NULL,
  `business_in_brief` int(11) DEFAULT NULL,
  `monthly_cashflow` double DEFAULT NULL,
  `monthly_expenditure` double DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `ppi_no_family_member` int(11) DEFAULT NULL,
  `ppi_acadamic_head_family` int(11) DEFAULT NULL,
  `ppi_rafrigerator_in_family` int(11) DEFAULT NULL,
  `ppi_stove_in_family` int(11) DEFAULT NULL,
  `ppi_pressure_cooker_in_family` int(11) DEFAULT NULL,
  `ppi_tv_in_family` int(11) DEFAULT NULL,
  `ppi_fan_in_family` int(11) DEFAULT NULL,
  `ppi_vehicle_in_family` int(11) DEFAULT NULL,
  `ppi_dressing_table_in_family` int(11) DEFAULT NULL,
  `ppi_other_table_in_family` int(11) DEFAULT NULL,
  `purpose_of_loan` int(11) DEFAULT NULL,
  `client_type` int(11) DEFAULT NULL,
  `is_business_premise_visited` bit(1) DEFAULT NULL,
  `repayment_track` int(11) DEFAULT NULL,
  `creadit_worthiness` int(11) DEFAULT NULL,
  `loan_liability_ratio` varchar(50) DEFAULT NULL,
  `competition` int(11) DEFAULT NULL,
  `loan_amount_recomandation` double DEFAULT NULL,
  `tenure_recomandation` int(11) DEFAULT NULL,
  `moratorium_recomandation` int(11) DEFAULT NULL,
  `interest_rate_recomandation` double DEFAULT NULL,
  `installment_recomandation` int(11) DEFAULT NULL,
  `is_loanassessment_details_filled` bit(1) DEFAULT NULL,
  `total_expense` double DEFAULT NULL,
  `total_monthly_income_for_family` double DEFAULT NULL,
  `address_proof_no` varchar(200) DEFAULT NULL,
  `house_hold_expense` double DEFAULT NULL,
  `clothes_expense` double DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE loan_application.`fs_mfi_assets_liability_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `assets_liability_type` int(11) DEFAULT NULL,
  `particulars` int(11) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `asset_owner_detail` varchar(50) DEFAULT NULL,
  `outstanding` double DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE loan_application.`fs_mfi_bank_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `bank_id` bigint(20) DEFAULT NULL,
  `branch_name` varchar(200) DEFAULT NULL,
  `account_no` varchar(200) DEFAULT NULL,
  `account_type` int(11) DEFAULT NULL,
  `ifsc_code` varchar(45) DEFAULT NULL,
  `passbook_img` longblob,
  PRIMARY KEY (`id`)
);


CREATE TABLE loan_application.`fs_mfi_expense_expected_income_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `ship_shgi_installment` double DEFAULT NULL,
  `other_installment` double DEFAULT NULL,
  `loan_installment` double DEFAULT NULL,
  `education_expense` double DEFAULT NULL,
  `medical_expense` double DEFAULT NULL,
  `food_expense` double DEFAULT NULL,
  `other_expense` double DEFAULT NULL,
  `house_hold_expense` double DEFAULT NULL,
  `clothes_expense` double DEFAULT NULL,
  `business_in_brief` int(11) DEFAULT NULL,
  `monthly_cashflow` double DEFAULT NULL,
  `monthly_expenditure` double DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `total_expense` double DEFAULT NULL,
  `cash_flow` double DEFAULT NULL,
  `net_saving` double DEFAULT NULL,
  `total_monthly_income_for_family` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE loan_application.`fs_mfi_income_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `occupation` int(11) DEFAULT NULL,
  `net_income` double DEFAULT NULL,
  `frequency_income` int(11) DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `yearly_income` double DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE loan_application.`fs_mfi_ppi_mstr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ppi_que_id` int(11) DEFAULT NULL,
  `ans_mstr_id` int(11) DEFAULT NULL,
  `score_value` double DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','1','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','2','4',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','3','7',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','4','11',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','5','19',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','6','26',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','7','34',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('1','8','41',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('2','1','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('2','2','3',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('2','3','5',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('3','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('3','1','11',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('4','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('4','1','2',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('5','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('5','1','4',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('6','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('6','1','5',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('7','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('7','1','3',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('8','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('8','1','4',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('9','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('9','1','6',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('10','0','0',true);
insert into loan_application.`fs_mfi_ppi_mstr` (`ppi_que_id`, `ans_mstr_id`, `score_value`, `is_active`) values('10','1','19',true);

