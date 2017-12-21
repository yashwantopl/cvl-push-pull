ALTER TABLE `loan_application`.`proposal_details` 
ADD COLUMN `el_amount` DOUBLE NULL DEFAULT 0.0 AFTER `pending_proposal_update_date`,
ADD COLUMN `el_tenure` DOUBLE NULL AFTER `el_amount`,
ADD COLUMN `el_roi` DOUBLE NULL AFTER `el_tenure`;