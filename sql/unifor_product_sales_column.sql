ALTER TABLE `loan_application`.`fs_corporate_primary_details` CHANGE `actual_sales_finYear_current` `turn_over_prev_fin_year` DOUBLE NULL, CHANGE `estimated_sales_fin_year_next` `turn_over_curr_fin_year_till_month` DOUBLE NULL, ADD COLUMN `projected_turn_over_curr_fin_year` DOUBLE NULL AFTER `turn_over_curr_fin_year_till_month`, ADD COLUMN `profit_curr_fin_year` DOUBLE NULL AFTER `projected_turn_over_curr_fin_year`, ADD COLUMN `projected_profit_curr_fin_year` DOUBLE NULL AFTER `profit_curr_fin_year`; 