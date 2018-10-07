ALTER TABLE `loan_application`.`fs_loan_application_master` 
ADD COLUMN `wc_renewal_status` BIT(1) NULL AFTER `proposal_mapping_id`;

