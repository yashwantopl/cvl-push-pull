DROP TABLE IF EXISTS loan_application.fs_emp_agriculturist_type;
create table loan_application.`fs_emp_agriculturist_type` (
	`id` bigint (20),
	`application_id` bigint (20),
	`total_land_owned_and_in_possesion` int (11),
	`presently_irrigated` int (11),
	`seasonal_irrigated` int (11),
	`rain_fed` int (11),
	`un_attended` int (11),
	`created_date` datetime ,
	`modified_date` datetime ,
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20)
);


DROP TABLE IF EXISTS loan_application.fs_emp_salaried_type;
create table loan_application.`fs_emp_salaried_type` (
	`id` bigint (20),
	`application_id` bigint (20),
	`bonus_per_annum` int (11),
	`incentive_per_annum` int (11),
	`tax_paid_last_year` int (11),
	`created_date` datetime ,
	`modified_date` datetime ,
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20)
);

DROP TABLE IF EXISTS loan_application.fs_emp_self_employed_type;
create table `fs_emp_self_employed_type` (
	`id` bigint (20),
	`application_id` bigint (20),
	`name_of_entity` varchar (1350),
	`type_of_ownership` int (11),
	`number_of_dir_partner` int (11),
	`name_of_dir_partner` varchar (1350),
	`share_hodling` int (11),
	`trade_license_no` varchar (720),
	`trade_license_exp_date` date ,
	`name_of_poa_holder` varchar (300),
	`created_date` datetime ,
	`modified_date` datetime ,
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20)
);

DROP TABLE IF EXISTS loan_application.fs_retail_final_home_loan_details;
create table loan_application.`fs_retail_final_home_loan_details` (
	`id` bigint (20),
	`application_id` bigint (20),
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20),
	`father_full_name` varchar (360),
	`mather_maiden_name` varchar (360),
	`name_of_spouse` varchar (150),
	`cast` int (11),
	`religion` int (11),
	`place_of_birth` varchar (150),
	`no_of_children` int (11),
	`permanent_premise_no` varchar (120),
	`permanent_street_name` varchar (90),
	`permanent_landmark` varchar (120),
	`permanent_pin_code` int (11),
	`permanent_city` int (11),
	`permanent_state` int (11),
	`permanent_country` int (11),
	`same_as_permanent_address` tinyint (1),
	`correspondence_premise_no` varchar (120),
	`correspondence_street_name` varchar (90),
	`correspondence_land_mark` varchar (120),
	`correspondence_pin_code` int (11),
	`correspondence_city` int (11),
	`correspondence_state` int (11),
	`correspondence_country` int (11),
	`educational_qualification` varchar (90),
	`year` int (11),
	`employeeType` int (11),
	`seller_name` varchar (150),
	`seller_address` varchar (765),
	`seller_city` int (11),
	`seller_state` int (11),
	`seller_country` int (11),
	`seller_pincode` int (11),
	`date_of_existing_loan_taken` date ,
	`original_value_of_property` int (11),
	`modified_date` date ,
	`created_date` date ,
	`created_by` bigint (20),
	`modified_by` bigint (20),
	`name` varchar (450),
	`status_id` int (11)
);

DROP TABLE IF EXISTS loan_application.fs_retail_final_home_loan_co_applicant_details;
create table loan_application.`fs_retail_final_home_loan_co_applicant_details` (
	`id` bigint (20),
	`application_id` bigint (20),
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20),
	`father_full_name` varchar (360),
	`mather_maiden_name` varchar (360),
	`name_of_spouse` varchar (150),
	`cast` int (11),
	`religion` int (11),
	`place_of_birth` varchar (150),
	`no_of_children` int (11),
	`permanent_premise_no` varchar (120),
	`permanent_street_name` varchar (90),
	`permanent_landmark` varchar (120),
	`permanent_pin_code` int (11),
	`permanent_city` int (11),
	`permanent_state` int (11),
	`permanent_country` int (11),
	`same_as_permanent_address` tinyint (1),
	`correspondence_premise_no` varchar (120),
	`correspondence_street_name` varchar (90),
	`correspondence_land_mark` varchar (120),
	`correspondence_pin_code` int (11),
	`correspondence_city` int (11),
	`correspondence_state` int (11),
	`correspondence_country` int (11),
	`educational_qualification` varchar (90),
	`year` int (11),
	`employeeType` int (11),
	`seller_name` varchar (150),
	`seller_address` varchar (765),
	`seller_city` int (11),
	`seller_state` int (11),
	`seller_country` int (11),
	`seller_pincode` int (11),
	`date_of_existing_loan_taken` date ,
	`original_value_of_property` int (11),
	`modified_date` date ,
	`created_date` date ,
	`created_by` bigint (20),
	`modified_by` bigint (20),
	`co_applicant_id` bigint (20),
	`name` varchar (450),
	`status_id` int (11)
);

DROP TABLE IF EXISTS loan_application.fs_purchase_property_details;
create table loan_application.`fs_purchase_property_details` (
	`id` bigint (20),
	`application_id` bigint (20),
	`property_name` varchar (360),
	`city` int (11),
	`state` int (11),
	`built_up_area` int (11),
	`super_built_up_area` int (11),
	`carpet_area` int (11),
	`total_price_of_property` int (11),
	`created_date` datetime ,
	`modified_date` datetime ,
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20)
);

DROP TABLE IF EXISTS loan_application.`fs_other_property_details`;
create table loan_application.`fs_other_property_details` (
	`id` bigint (20),
	`total_cost_of_land` int (11),
	`application_id` bigint (20),
	`fs_home_loan_detail_id` bigint (20),
	`total_cost_of_construction` int (11),
	`is_active` tinyint (1),
	`proposal_mapping_id` bigint (20),
	`total_cost_of_renovation` int (11),
	`type_of_repair_renovation` int (11),
	`time_for_completion_renovation` int (11),
	`time_for_completion_construction` int (11),
	`created_date` datetime ,
	`modified_date` datetime
);
