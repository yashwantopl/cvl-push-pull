ALTER TABLE `loan_application`.`fs_corporate_applicant_details`
ADD COLUMN `total_cost_of_estimate` DOUBLE(19,2) NULL DEFAULT NULL,
ADD COLUMN `total_means_of_finance` DOUBLE(19,2) NULL DEFAULT NULL,
ADD COLUMN `collateral_security_amt_total` DOUBLE(19,2) NULL DEFAULT '0.00',
ADD COLUMN `share_price_face_value` DOUBLE(25,2) NULL DEFAULT NULL,
ADD COLUMN `share_price_market_value` DOUBLE(25,2) NULL DEFAULT NULL;