 ALTER TABLE `loan_application`.`fs_corporate_cma_operating_statement_details` ADD COLUMN `financial_yearly_statement` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL;
 
ALTER TABLE `loan_application`.`fs_corporate_cma_liabilities_details` ADD COLUMN `financial_yearly_statement` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL;  
  
ALTER TABLE `loan_application`.`fs_corporate_cma_assets_details` ADD COLUMN  `financial_yearly_statement` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL;
  
ALTER TABLE `loan_application`.`fs_corporate_bs_balance_sheet_details` ADD COLUMN `financial_yearly_statement` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL;

ALTER TABLE `loan_application`.`fs_corporate_bs_profitibility_statement_details` ADD COLUMN `financial_yearly_statement` VARCHAR(30) CHARSET utf8 COLLATE utf8_general_ci NULL;

  