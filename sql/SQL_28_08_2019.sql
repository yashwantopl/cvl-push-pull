Database : document_management

INSERT INTO document_management.document_master (id, NAME, TYPE, size, created_date, is_active) VALUES
(176, 'MFI Demand promissory note', 'pdf', 20, NOW(), TRUE ), 
(177, 'MFI Letter of Intent', 'pdf', 20, NOW(), TRUE ),
(178, 'MFI Loan Agreement', 'pdf', 20, NOW(), TRUE ),
(179, 'MFI Application Form', 'pdf', 20, NOW(), TRUE );


INSERT INTO document_management.product_document_mapping (id, document_id, is_mandatory, created_date, is_active) VALUES
(597, 176, TRUE, NOW(), TRUE),
(598, 177, TRUE, NOW(), TRUE),
(599, 178, TRUE, NOW(), TRUE),
(600, 179, TRUE, NOW(), TRUE);


Database : loan_application

ALTER TABLE loan_application.fs_mfi_applicant_details ADD COLUMN dpn_doc VARCHAR(20) NULL AFTER total_emi, ADD COLUMN loi_doc VARCHAR(20) NULL AFTER dpn_doc, ADD COLUMN loh_doc VARCHAR(20) NULL AFTER loi_doc, ADD COLUMN agreement_doc VARCHAR(20) NULL AFTER loh_doc; 

ALTER TABLE `loan_application`.`fs_mfi_bank_details` CHANGE `passbook_img` `passbook_img` LONGTEXT NULL
ALTER TABLE `loan_application`.`fs_mfi_applicant_details`  ADD COLUMN `total_emi` DOUBLE NULL;
