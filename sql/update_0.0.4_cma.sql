ALTER TABLE loan_applications.fs_corporate_cma_operating_statement_details ADD COLUMN selling_distribution_exp DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_cma_operating_statement_details ADD COLUMN general_admin_exp DOUBLE DEFAULT NULL;

	
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN term_liabilities_secured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN term_liabilities_unsecured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN share_warrents_outstanding DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN minority_interest DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN other_ncl_unsecured_loans_from_other DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN other_ncl_long_term_provisions DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN other_ncl_others DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN other_ncl DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_liabilities_details ADD COLUMN other_ncl_unsecured_loans_from_promoters DOUBLE DEFAULT NULL;
	
	

	
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN land_building DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN plant_machines DOUBLE DEFAULT NULL;
	
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN impairment_asset DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN others_pre_operative_expenses_pending DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN others_assets_in_transit DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN others_other DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN total_other_nca_patent DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN total_other_nca_goodwill DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN total_other_nca_prelimnary_expenses DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN total_other_nca_bad_expenses DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN total_other_nca_other DOUBLE DEFAULT NULL;



	
	ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN general_admin_expenses DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN selling_distribution_expenses DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN expenses_capitalised DOUBLE DEFAULT NULL;

	
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN money_received_against_share_warrants DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN minority_interest DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN term_loans_secured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN term_loans_unsecured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN unsecured_loans_from_others DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN current_liabilities_secured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN current_liabilities_unsecured DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN impairments_of_assests DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN pre_operative_expenses_pending DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN assets_in_transit DOUBLE DEFAULT NULL;
	ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN cash_and_cash_equivalents DOUBLE DEFAULT NULL;



ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN others_totals DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN others_assets_transit DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN other_investments DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN other_details DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN intangible_assets_6 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_balance_sheet_details ADD COLUMN other_term_liability DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN expense_capitalized_1 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN expense_capitalized_2 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN expense_capitalized_3 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN expense_capitalized_4 DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN extraordinary_items_1 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN extraordinary_items_2 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN extraordinary_items_3 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN extraordinary_items_4 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN extraordinary_items_5 DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_cma_operating_statement_details ADD COLUMN selling_distribution_exp DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_cma_operating_statement_details ADD COLUMN general_admin_exp DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN less_item_1 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN less_item_2 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN less_item_3 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN less_item_4 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN less_item_5 DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN other_operating_revenue_1 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN other_operating_revenue_2 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN other_operating_revenue_3 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN other_operating_revenue_4 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_bs_profitibility_statement_details ADD COLUMN other_operating_revenue_5 DOUBLE DEFAULT NULL;

ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN gross_block_1 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN gross_block_2 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN gross_block_3 DOUBLE DEFAULT NULL;
ALTER TABLE loan_applications.fs_corporate_cma_assets_details ADD COLUMN gross_block_4 DOUBLE DEFAULT NULL;
