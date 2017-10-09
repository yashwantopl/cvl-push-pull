ALTER TABLE loan_application.fs_retail_applicant_details ADD COLUMN monthly_loan_obligation DOUBLE(19,2), ADD COLUMN pat_previous_year DOUBLE(19,2), ADD COLUMN pat_current_year DOUBLE(19,2), ADD COLUMN depreciation_previous_year DOUBLE(19,2), 
ADD COLUMN depreciation_current_year DOUBLE(19,2), ADD COLUMN remuneration_previous_year DOUBLE(19,2), ADD COLUMN remuneration_current_year  DOUBLE(19,2);

ALTER TABLE loan_application.fs_retail_co_applicant_details ADD COLUMN monthly_loan_obligation DOUBLE(19,2), ADD COLUMN pat_previous_year DOUBLE(19,2), ADD COLUMN pat_current_year DOUBLE(19,2), ADD COLUMN depreciation_previous_year DOUBLE(19,2), 
ADD COLUMN depreciation_current_year DOUBLE(19,2), ADD COLUMN remuneration_previous_year DOUBLE(19,2), ADD COLUMN remuneration_current_year  DOUBLE(19,2);

ALTER TABLE loan_application.fs_retail_guarantor_details ADD COLUMN monthly_loan_obligation DOUBLE(19,2), ADD COLUMN pat_previous_year DOUBLE(19,2), ADD COLUMN pat_current_year DOUBLE(19,2), ADD COLUMN depreciation_previous_year DOUBLE(19,2), 
ADD COLUMN depreciation_current_year DOUBLE(19,2), ADD COLUMN remuneration_previous_year DOUBLE(19,2), ADD COLUMN remuneration_current_year  DOUBLE(19,2);