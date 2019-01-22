/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.7.23-log
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `loan_application`.application_proposal_mapping` (
	`proposal_id` bigint (20),
	`application_id` bigint (20),
	`tenure` Decimal (11),
	`loan_amount` bigint (20),
	`product_id` int (11),
	`application_code` varchar (60),
	`org_id` bigint (20),
	`status` int (11),
	`ddr_status_id` bigint (20),
	`payment_status` varchar (150),
	`fp_maker_id` bigint (20),
	`np_assignee_id` bigint (20),
	`np_user_id` bigint (20),
	`approved_date` datetime ,
	`primary_filled_count` varchar (765),
	`is_primary_upload_filled` bit (1),
	`is_primary_locked` bit (1),
	`is_final_locked` bit (1),
	`is_final_dpr_upload_filled` bit (1),
	`is_final_upload_filled` bit (1),
	`is_final_mcq_filled` bit (1),
	`details_filled_count` varchar (765),
	`is_applicant_details_filled` bit (1),
	`is_applicant_primary_filled` bit (1),
	`is_applicant_final_filled` bit (1),
	`is_active` bit (1),
	`created_by` bigint (20),
	`created_date` datetime ,
	`modified_by` bigint (20),
	`modified_date` datetime ,
	`user_id` bigint (20),
	`business_type_id` int (11)
); 
