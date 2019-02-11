ALTER TABLE loan_application.fs_retail_applicant_details ADD COLUMN spouse_employment INT NULL AFTER ddo_website, ADD COLUMN no_of_dependent INT NULL AFTER spouse_employment, ADD COLUMN designation INT NULL AFTER no_of_dependent, ADD COLUMN residence_since_year INT NULL AFTER designation, ADD COLUMN residence_since_month INT NULL AFTER residence_since_year;

ALTER TABLE loan_application.fs_retail_applicant_details ADD COLUMN salary_mode INT NULL AFTER is_other_salary_bank, ADD COLUMN salary_bank_name VARCHAR(255) NULL AFTER salary_mode, ADD COLUMN salary_bank_month INT NULL AFTER salary_bank_name, ADD COLUMN salary_bank_year INT NULL AFTER salary_bank_month; 

ALTER TABLE loan_application.fs_retail_applicant_details ADD COLUMN is_other_salary_bank BIT(1) DEFAULT b'0' NULL AFTER salary_bank_year; 


ALTER TABLE loan_application.inprinciple_to_sanction ADD COLUMN sanction_count INT NULL AFTER fp_product_id, ADD COLUMN inprinciple_count INT NULL AFTER sanction_count, ADD COLUMN sanction_amount DECIMAL(20,2) NULL AFTER inprinciple_to_sanction_weight, ADD COLUMN inprinciple_amount DECIMAL(20,2) NULL AFTER sanction_amount;

ALTER TABLE loan_application.inprinciple_to_sanction ADD COLUMN inprinciple_to_sanction_rank INT NULL AFTER inprinciple_to_sanction_weight, ADD COLUMN inprinciple_to_sanction_amt_rank INT NULL AFTER inprinciple_to_sanction_amt_weight; 