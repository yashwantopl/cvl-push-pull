ALTER TABLE statement_analyzer.prefious_report ADD COLUMN co_applicant_id BIGINT NULL;


CREATE TABLE loan_application.fs_retail_emi_nmi_mapping ( id BIGINT NOT NULL AUTO_INCREMENT, fp_product_id BIGINT,
min_income DOUBLE, max_income DOUBLE, emi_nmi FLOAT, created_date DATETIME, modified_date DATETIME, created_by BIGINT, 
modified_by BIGINT, is_active BIT, PRIMARY KEY (id) );


CREATE TABLE loan_application.fs_retail_emi_nmi_mapping_temp ( id BIGINT NOT NULL AUTO_INCREMENT, fp_product_id BIGINT,
min_income DOUBLE, max_income DOUBLE, emi_nmi FLOAT, created_date DATETIME, modified_date DATETIME, created_by BIGINT, 
modified_by BIGINT, is_active BIT, PRIMARY KEY (id) );