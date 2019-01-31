ALTER TABLE loan_application.fp_working_capital_details_temp ADD COLUMN is_new_dscr_check BIT(1) DEFAULT b'0' NULL AFTER kotak_sub_parameter, ADD COLUMN new_dscr_check DECIMAL(10,2) DEFAULT 0 NULL AFTER is_new_dscr_check;


ALTER TABLE loan_application.fp_working_capital_details ADD COLUMN is_new_dscr_check BIT(1) DEFAULT b'0' NULL AFTER kotak_sub_parameter, ADD COLUMN new_dscr_check DECIMAL(10,2) DEFAULT 0.00 NULL AFTER is_new_dscr_check; 