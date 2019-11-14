ALTER TABLE loan_application.disbursement_detail ADD COLUMN loan_account_number BIGINT;


INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('189','NBFC Projected Financials','pdf,excel','25','2019-11-07 10:52:20','2019-11-07 10:52:22',NULL,NULL,TRUE);

INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
 VALUES('190','NBFC ID PROOF','pdf','25','2019-11-08 16:45:59','2019-11-08 16:45:59',NULL,NULL,TRUE);

INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
 VALUES('191','NBFC Age Proof','png,jpg,jpeg,pdf','25','2019-11-08 16:45:59','2019-11-08 16:45:59',NULL,NULL,TRUE);

INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('192','NBFC Address proof at disbusement level','png,jpg,jpeg,pdf','25','2019-11-08 16:45:59','2019-11-08 16:45:59',NULL,NULL,TRUE);

INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('193','NBFC application form','png,jpg,jpeg,pdf','25','2019-11-08 16:45:59','2019-11-08 16:45:59',NULL,NULL,TRUE);

INSERT INTO `document_management`.`document_master` (`id`, `name`, `type`, `size`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('194','NBFC Detail Assessment note','png,jpg,jpeg,pdf','25','2019-11-08 16:45:59','2019-11-08 16:45:59',NULL,NULL,TRUE);




INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
 VALUES('610','189',NULL,TRUE,NULL,'2019-11-07 10:52:40','2019-11-07 10:52:40',NULL,NULL,TRUE);

INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('611','191',NULL,TRUE,NULL,'2019-11-08 16:39:58','2019-11-08 16:39:58',NULL,NULL,TRUE);

INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('612','190',NULL,TRUE,NULL,'2019-11-08 16:39:58','2019-11-08 16:39:58',NULL,NULL,TRUE);

INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
 VALUES('613','192',NULL,TRUE,NULL,'2019-11-08 16:39:58','2019-11-08 16:39:58',NULL,NULL,TRUE);

INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('614','193',NULL,TRUE,NULL,'2019-11-08 16:47:08','2019-11-08 16:47:08',NULL,NULL,TRUE);

INSERT INTO `document_management`.`product_document_mapping` (`id`, `document_id`, `product_id`, `is_mandatory`, `limit`, `created_date`, `modified_date`, `created_by`, `modified_by`, `is_active`)
VALUES('615','194',NULL,TRUE,NULL,'2019-11-08 16:51:07','2019-11-08 16:51:07',NULL,NULL,TRUE);