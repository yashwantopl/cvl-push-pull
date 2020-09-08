ALTER TABLE `loan_application`.`fs_corporate_final_mcq_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_sector_subsector_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`industry_sector_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_existing_product_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_associated_concern_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_security_corporate_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_credit_rating_organization_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_finance_means_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_project_cost_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_ddr_form_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `document_management`.`product_storage_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_assets_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_liabilities_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_cma_operating_statement_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_promotor_background_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_current_financial_arrangements_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_director_background_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_bs_profitibility_statement_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`application_status_audit` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_ownership_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_achievement_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_guarantors_corporate_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `loan_application`.`fs_corporate_proposed_product_details` ADD COLUMN `proposal_mapping_id` BIGINT(20) DEFAULT NULL;
ALTER TABLE `connect`.`connect_log` ADD COLUMN `proposal_id` BIGINT(20) DEFAULT NULL;