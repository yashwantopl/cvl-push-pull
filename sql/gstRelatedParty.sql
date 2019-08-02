CREATE TABLE `loan_application`.gst_related_party(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
application_id BIGINT NOT NULL,
gst_party_name VARCHAR(500),
sales DOUBLE,
purchase DOUBLE,
invoice_value DOUBLE NULL,
pan VARCHAR(20) NULL,
gstin VARCHAR(20) NULL,
transaction_type VARCHAR(100) NULL,
per_of_invoice_value BIGINT NULL,
created_by BIGINT,
created_date DATETIME,
modified_date DATETIME,
modified_by BIGINT,
is_active BIT DEFAULT TRUE
)

ALTER TABLE `loan_application`.`gst_related_party`   
  ADD INDEX (`application_id`),
  ADD INDEX (`is_active`),
  ADD INDEX (`sales`),
  ADD INDEX (`purchase`);

 
  
  --- corporate--applicant--details
  
 ALTER TABLE `loan_application`.`fs_corporate_applicant_details`   
  ADD COLUMN `is_noneof_related_party_selected` BIT NULL AFTER `cast_category`;
 