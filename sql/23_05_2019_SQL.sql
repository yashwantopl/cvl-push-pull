============================= FROM Date : 22-05-2019 ========================================

ALTER TABLE loan_application.fs_retail_applicant_details MODIFY COLUMN loan_purpose_other TEXT;

CREATE TABLE one_form.salary_mode ( id int(11) DEFAULT NULL, value varchar(250) DEFAULT NULL, is_active bit(1) DEFAULT b'1' );

INSERT INTO one_form.salary_mode (id, VALUE, is_active ) VALUES (1, 'Cash', TRUE), (2, 'Cheque', TRUE), (3, 'Direct Deposit', TRUE); 

CREATE TABLE one_form.borrower_salary_account ( id int(11) DEFAULT NULL, value varchar(250) DEFAULT NULL, is_active bit(1) DEFAULT b'1' );

INSERT INTO one_form.borrower_salary_account (id, VALUE, is_active ) VALUES (1, 'My Bank', TRUE), (2, 'Other Bank', TRUE), (3, 'Both', TRUE), (4, 'Not-Applicable', TRUE);

CREATE TABLE loan_application.application_product_match_data_pl (
id bigint(20) unsigned NOT NULL,
applicationId bigint(20) DEFAULT NULL,
fpProdId bigint(20) DEFAULT NULL,
productId bigint(20) DEFAULT NULL,
fpTableName varchar(100) DEFAULT NULL,
pan varchar(100) DEFAULT NULL,
itrYear int(11) DEFAULT NULL,
matchCalculatedDate datetime DEFAULT NULL,
matchDataStoredDate datetime DEFAULT NULL,
loanAmountFS varchar(20) DEFAULT NULL,
minLoanAmount varchar(20) DEFAULT NULL,
maxLoanAmount varchar(20) DEFAULT NULL,
isLoanAmountMandatory bit(1) DEFAULT NULL,
isLoanAmountDisplay bit(1) DEFAULT NULL,
isLoanAmountMatch bit(1) DEFAULT NULL,
netMonthlyIncomeFS varchar(20) DEFAULT NULL,
minNetMonthlyIncome varchar(20) DEFAULT NULL,
maxNetMonthlyIncome varchar(20) DEFAULT NULL,
isNetMonthlyIncomeMandatory bit(1) DEFAULT NULL,
isNetMonthlyIncomeDisplay bit(1) DEFAULT NULL,
isNetMonthlyIncomeMatch bit(1) DEFAULT NULL,
grossMonthlyIncomeFS varchar(20) DEFAULT NULL,
minGrossMonthlyIncome varchar(20) DEFAULT NULL,
maxGrossMonthlyIncome varchar(20) DEFAULT NULL,
isGrossMonthlyIncomeMandatory bit(1) DEFAULT NULL,
isGrossMonthlyIncomeDisplay bit(1) DEFAULT NULL,
isGrossMonthlyIncomeMatch bit(1) DEFAULT NULL,
ageFS varchar(10) DEFAULT NULL,
minAge varchar(10) DEFAULT NULL,
maxAge varchar(10) DEFAULT NULL,
isAgeMandatory bit(1) DEFAULT NULL,
isAgeDisplay bit(1) DEFAULT NULL,
isAgeMatch bit(1) DEFAULT NULL,
tenureFS varchar(10) DEFAULT NULL,
minTenure varchar(10) DEFAULT NULL,
maxTenure varchar(10) DEFAULT NULL,
isTenureMandatory bit(1) DEFAULT NULL,
isTenureDisplay bit(1) DEFAULT NULL,
isTenureMatch bit(1) DEFAULT NULL,
geoGraphicalFocusFS text,
geoGraphicalFocusFP text,
isGeoGraphicalFocusMandatory bit(1) DEFAULT NULL,
isGeoGraphicalFocusDisplay bit(1) DEFAULT NULL,
isGeoGraphicalFocusMatch bit(1) DEFAULT NULL,
bureauScoreFS varchar(10) DEFAULT NULL,
minBureauScoreFP varchar(10) DEFAULT NULL,
isBureauScoreMandatory bit(1) DEFAULT NULL,
isBureauScoreDisplay bit(1) DEFAULT NULL,
isBureauScoreMatch bit(1) DEFAULT NULL,
dpdFS varchar(10) DEFAULT NULL,
maxDpdFP varchar(10) DEFAULT NULL,
isDpdMandatory bit(1) DEFAULT NULL,
isDpdDisplay bit(1) DEFAULT NULL,
isDpdMatch bit(1) DEFAULT NULL,
riskModelScoreFS varchar(10) DEFAULT NULL,
minRiskModelScoreFP varchar(10) DEFAULT NULL,
isRiskModelScoreMandatory bit(1) DEFAULT NULL,
isRiskModelScoreDisaply bit(1) DEFAULT NULL,
isRiskModelScoreMatch bit(1) DEFAULT NULL,
eligibleEmployerFS text,
eligibleEmployerFP text,
isEligibleEmployerMandatory bit(1) DEFAULT NULL,
isEligibleEmployerDisplay bit(1) DEFAULT NULL,
isEligibleEmployerMatch bit(1) DEFAULT NULL,
totalJobExpFS varchar(10) DEFAULT NULL,
minTotalJobExp varchar(10) DEFAULT NULL,
maxTotalJobExp varchar(10) DEFAULT NULL,
isTotalJobExpMandatory bit(1) DEFAULT NULL,
isTotalJobDisplay bit(1) DEFAULT NULL,
isTotalJobMatch bit(1) DEFAULT NULL,
currentJobExpFS varchar(10) DEFAULT NULL,
minCurrentJobExp varchar(10) DEFAULT NULL,
maxCurrentJobExp varchar(10) DEFAULT NULL,
isCurrentJobExpMandatory bit(1) DEFAULT NULL,
isCurrentJobExpDisplay bit(1) DEFAULT NULL,
isCurrentJobExpMatch bit(1) DEFAULT NULL,
currentEmpStatusFS varchar(255) DEFAULT NULL,
currentEmpStatusFP varchar(255) DEFAULT NULL,
isCurrentEmpStatusMandatory bit(1) DEFAULT NULL,
isCurrentEmpStatusDisplay bit(1) DEFAULT NULL,
isCurrentEmpStatusMatch bit(1) DEFAULT NULL,
bankingRelationshipMonthsFS varchar(10) DEFAULT NULL,
minBankingRelationshipMonthsFP varchar(10) DEFAULT NULL,
isBankingRelationshipMonthsMandatory bit(1) DEFAULT NULL,
isBankingRelationshipMonthsDisplay bit(1) DEFAULT NULL,
isBankingRelationshipMonthsMatch bit(1) DEFAULT NULL,
modeOfSalaryFS varchar(250) DEFAULT NULL,
modeOfSalaryFP varchar(250) DEFAULT NULL,
isModeOfSalaryMandatory bit(1) DEFAULT NULL,
isModeOfSalaryDisplay bit(1) DEFAULT NULL,
isModeOfSalaryMatch bit(1) DEFAULT NULL,
salaryAccountFS varchar(250) DEFAULT NULL,
salaryAccountFP varchar(250) DEFAULT NULL,
isSalaryAccountMandatory bit(1) DEFAULT NULL,
isSalaryAccountDisplay bit(1) DEFAULT NULL,
isSalaryAccountMatch bit(1) DEFAULT NULL,
PRIMARY KEY (id),
KEY appId (applicationId),
KEY fpProdId (fpProdId),
KEY appIdfpProdId (applicationId,fpProdId)
);



UPDATE one_form.employement_status_pl SET id = 4 WHERE id =3;
UPDATE one_form.employement_status_pl SET id = 3 WHERE id =2;
UPDATE one_form.employement_status_pl SET id = 2 WHERE id =1;

INSERT INTO one_form.employee_with_pl (id, VALUE, is_active) VALUES 
(8, 'Bank', TRUE),
(9, 'Insurance Company', TRUE),
(10, 'Small Sector (Pvt Ltd. Companies)', TRUE),
(11, 'Small Sector (Partnership)', TRUE),
(12, 'Small Sector (Proprietorship)', TRUE),
(13, 'Unorganised Sector', TRUE);

CREATE TABLE one_form.home_loan_purpose ( id bigint(20) NOT NULL, value varchar(250) DEFAULT NULL, is_active bit(1) DEFAULT b'1', PRIMARY KEY (id) );

INSERT INTO one_form.home_loan_purpose (id, VALUE) VALUES ('1', 'Purchase'); 
INSERT INTO one_form.home_loan_purpose (id, VALUE) VALUES ('2', 'Construction/Expansion'); 
INSERT INTO one_form.home_loan_purpose (id, VALUE) VALUES ('3', 'Repairs & Renovations'); 
INSERT INTO one_form.home_loan_purpose (id, VALUE) VALUES ('4', 'Others'); 

=================================================================================================
Copy Procedure from  DB : loan_application
1) spMatchSavePl
2) spCalculateAndSaveMatches

