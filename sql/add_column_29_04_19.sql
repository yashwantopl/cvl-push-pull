ALTER TABLE loan_application.application_product_match_data ADD COLUMN minAdditionalLoanFP VARCHAR(20) AFTER investmentSizeMaxFP;
ALTER TABLE loan_application.application_product_match_data ADD COLUMN additionalLoanAmountFS VARCHAR(20) AFTER minAdditionalLoanFP;
ALTER TABLE loan_application.application_product_match_data ADD COLUMN maxAdditionalLoanFP VARCHAR(20) AFTER additionalLoanAmountFS;
ALTER TABLE loan_application.application_product_match_data ADD COLUMN minTotalLoanFP VARCHAR(20) AFTER maxAdditionalLoanFP;
ALTER TABLE loan_application.application_product_match_data ADD COLUMN existingLoanAmount VARCHAR(20) AFTER minTotalLoanFP;
ALTER TABLE loan_application.application_product_match_data ADD COLUMN maxTotalLoanFP VARCHAR(20) AFTER existingLoanAmount;
ALTER TABLE loan_application.`fs_corporate_primary_details` ADD COLUMN product_service_description VARCHAR(120) AFTER competition;