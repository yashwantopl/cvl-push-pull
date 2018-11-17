ALTER TABLE loan_application.fp_product_master_temp ADD COLUMN active_inactive_job_id BIGINT NULL AFTER campaign_type; 

ALTER TABLE loan_application.fp_product_master ADD COLUMN active_inactive_job_id BIGINT NULL AFTER campaign_type; 

ALTER TABLE loan_application.fp_product_master ADD COLUMN action_for VARCHAR(20) NULL AFTER active_inactive_job_id; 

ALTER TABLE loan_application.fp_product_master_temp ADD COLUMN action_for VARCHAR(20) NULL AFTER active_inactive_job_id; 
