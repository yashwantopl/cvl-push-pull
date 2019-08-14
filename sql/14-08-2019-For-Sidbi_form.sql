#DB: loan_application
#--------------------------------------------------------------------
#14-08-2019-For-Sidbi_form.sql

SELECT * FROM `loan_application`.`fs_sidbi_corporate_governance_compliance` WHERE selected_option=1;
UPDATE `loan_application`.`fs_sidbi_corporate_governance_compliance` SET selected_option=0 WHERE selected_option=1;

SELECT * FROM `loan_application`.`fs_sidbi_corporate_governance_compliance` WHERE selected_option=2;
UPDATE `loan_application`.`fs_sidbi_corporate_governance_compliance` SET selected_option=1 WHERE selected_option=2;