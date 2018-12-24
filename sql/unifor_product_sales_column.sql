ALTER TABLE `loan_application`.`fs_corporate_primary_details` CHANGE `actual_sales_finYear_current` `turn_over_prev_fin_year` DOUBLE NULL, CHANGE `estimated_sales_fin_year_next` `turn_over_curr_fin_year_till_month` DOUBLE NULL, ADD COLUMN `projected_turn_over_curr_fin_year` DOUBLE NULL AFTER `turn_over_curr_fin_year_till_month`, ADD COLUMN `profit_curr_fin_year` DOUBLE NULL AFTER `projected_turn_over_curr_fin_year`, ADD COLUMN `projected_profit_curr_fin_year` DOUBLE NULL AFTER `profit_curr_fin_year`; 
ALTER TABLE `loan_application`.`fs_corporate_applicant_details` ADD COLUMN `is_gst_completed` BIT(1) DEFAULT b'0' NULL AFTER `environmental_impact_id`, ADD COLUMN `is_itr_completed` BIT(1) DEFAULT b'0' NULL AFTER `is_gst_completed`; 