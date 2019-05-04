DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spMatchSave`$$

CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spMatchSave`(IN applicationId BIGINT,IN fpProdId BIGINT,IN id BIGINT,IN matchCalculatedDate DATETIME)
BEGIN
 DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
-- ===================================== Start Get Global Variable ===================================== --
SET @applicationId := applicationId;
SET @id := id;
SET @matchCalculatedDate := matchCalculatedDate;
SET @matchDataStoredDate := NOW();
SET @itrYearQuery:= CONCAT('SELECT LEFT(IFNULL(`third_year`,IFNULL(second_year,IFNULL(first_year,NULL))),4) INTO @itrYear FROM itr_api.`itr_tracking` WHERE application_id = ' , @applicationId);
PREPARE stmt FROM @itrYearQuery;
EXECUTE stmt;
SET @fpProdId := fpProdId;
SET @productId := (SELECT (SELECT product_id FROM `loan_application`.`fp_product_master` WHERE `fp_product_id`=fpProdId));
IF @productId = 1 THEN
	SET @fpTableName := 'fp_working_capital_details';
END IF;
IF @productId = 2 THEN
	SET @fpTableName := 'fp_term_loan_details';
END IF;
IF @productId = 16 THEN
	SET @fpTableName := 'fp_wc_tl_details';
END IF;
-- ===================================== End Get Global Variable =====================================  --
-- ===================================== Start Get Product Parameter Variable ===================================== --
   SET @productParaQuery:= CONCAT('SELECT
	  is_industry_sector_display,is_industry_sector_mandatory
	 ,is_investment_size_display,is_investment_size_mandatory
	 ,is_tenure_display,is_tenure_mandatory   ,is_geographical_display,is_geographical_mandatory
	 ,is_credit_rating_display,is_credit_rating_mandatory
	 ,is_establishment_display,is_establishment_mandatory
	 ,is_profitability_history_display,is_profitability_history_mandatory
	 ,is_past_year_turnover_display,is_past_year_turnover_mandatory
	 ,is_debt_equity_display,is_debt_equity_mandatory  ,is_collateral_display,is_collateral_mandatory
	 ,is_networth_display,is_networth_mandatory  ,is_current_ratio_display,is_current_ratio_mandatory
	 ,is_interest_coverage_display,is_interest_coverage_mandatory
	 ,is_tol_tnw_display,is_tol_tnw_mandatory  ,is_turnover_ratio_display,is_turnover_ratio_mandatory
	 ,is_gross_cash_accurals_ratio_display,is_gross_cash_accurals_ratio_mandatory
	 ,is_customer_concentration_display,is_customer_concentration_mandatory
	 ,is_risk_model_score_display,is_risk_model_score_mandatory
	 ,is_cheque_bounced_display,is_cheque_bounced_mandatory
	 ,is_cheque_bounced_last_six_months_display,is_cheque_bounced_last_six_months_mandatory
	 ,is_individual_cibil_display,is_individual_cibil_mandatory,is_Commercial_cibil_display,is_Commercial_cibil_mandatory
	 ,is_cgtmse_coverage_display,is_cgtmse_coverage_mandatory
	 ,is_msme_funding_display,is_msme_funding_mandatory
	 ,is_max_drop_in_turnover_display,is_max_drop_in_turnover_mandatory
	 ,is_utilisation_percentage_display,is_utilisation_percentage_mandatory
	 ,is_credit_summation_display,is_credit_summation_mandatory
	 ,is_collateral_coverage_display,is_collateral_coverage_mandatory INTO
	  @isIndustrySectorDisplay,@isIndustrySectorMandatory
	 ,@isInvestmentSizeDisplay,@isInvestmentSizeMandatory
	 ,@isTenureDisplay,@isTenureMandatory
	 ,@isGeographicalDisplay,@isGeographicalMandatory
	 ,@isCreditratingDisplay,@isCreditratingMandatory
	 ,@isEstablishmentDisplay,@isEstablishmentMandatory
	 ,@isProfitabilityHistoryDisplay,@isProfitabilityHistoryMandatory
	 ,@isPastYearTurnoverDisplay,@isPastYearTurnoverMandatory
	 ,@isDebtEquityDisplay,@isDebtEquityMandatory
	 ,@isCollateralDisplay,@isCollateralMandatory
	 ,@isNetworthDisplay,@isNetworthMandatory
	 ,@isCurrentratioDisplay,@isCurrentratioMandatory
	 ,@isInterestCoverageDisplay,@isInterestCoverageMandatory
	 ,@isTolTnwDisplay,@isTolTnwMandatory
	 ,@isTurnOverRatioDisplay,@isTurnOverRatioMandatory
	 ,@isGrossCashAccuralsRatioDisplay,@isGrossCashAccuralsRatioMandatory
	 ,@isCustomerConcentrationDisplay,@isCustomerConcentrationMandatory
	 ,@isRiskModelScoreDisplay,@isRiskModelScoreMandatory
	 ,@isChequeBouncedDisplay,@isChequeBouncedMandatory
	 ,@isChequeBouncedLastSixMonthsDisplay,@isChequeBouncedLastSixMonthsMandatory
	 ,@isIndividualCibilDisplay,@isIndividualCibilMandatory
	 ,@isCommercialCibilDisplay,@isCommercialCibilMandatory
	 ,@isCgtmseCoverageDisplay,@isCgtmseCoverageMandatory
	 ,@isMsmeFundingDisplay,@isMsmeFundingMandatory
	 ,@isMaxDropInTurnoverDisplay,@isMaxDropInTurnoverMandatory
	 ,@isUtilisationPercentageDisplay,@isUtilisationPercentageMandatory
	 ,@isCreditSummationDisplay,@isCreditSummationMandatory
	 ,@isCollateralCoverageNewDisplay,@isCollateralCoverageNewMandatory
	  FROM ',@fpTableName , ' WHERE fp_product_id = ', @fpProdId);
	   PREPARE stmt FROM @productParaQuery ;
	   EXECUTE stmt ;
	 SET @isTolTnwMatch = NULL ,@tolTnwMatchMaxFS= NULL ,@tolTnwMatchMinFP= NULL ,@tolTnwMatchMaxFP = NULL; --  TOL TNW
	 SET @isDebtEquityMatch= NULL ,@debtEquityValFS= NULL ,@debtEquityValMinFP= NULL ,@debtEquityValMaxFP= NULL; --  Debt Equity Ratio
	 SET @isCustomerConcentrationMatch = NULL ,@customerConcentrationFS= NULL ,@minCustomerConcentration= NULL ,@maxCustomerConcentration= NULL; --  Customer Concentration
	 SET @isIndustrySectorMatch= NULL ,@industryFS= NULL ,@industryFP= NULL; --  Industry Sector
	 SET @isGeoGraphicalFocusMatch= NULL ,@geoGraphicalFocusFS= NULL ,@geoGraphicalFocusFP= NULL;--  Geo Graphical Focus
	 SET @isCurrentRatioMatch= NULL ,@currentRatioFS= NULL ,@currentRatioMinFP= NULL ,@currentRatioMaxFP= NULL; --  Current Ratio
	 SET @isInterestCoverageRatioMatch= NULL ,@interestCoverageRatioFS= NULL ,@interestCoverageRatioMinFP= NULL ,@interestCoverageRatioMaxFP= NULL; --  Interest Coverage Ratio
	 SET @isPastYearTurnOverMatch= NULL ,@pastYearTurnOverFS= NULL ,@pastYearTurnOverMinFP= NULL ,@pastYearTurnOverMaxFP= NULL; --  Past Year Turn Over
	 SET @isAgeOfEstablishmentMatch= NULL ,@ageOfEstablishmentFS= NULL ,@ageOfEstablishmentMinFP= NULL ,@ageOfEstablishmentMaxFP= NULL; --  Age of Establishment
	 SET @isProfitablityHistoryMatch= NULL ,@profitablityHistoryFS= NULL ,@profitablityHistoryFP= NULL; --  Positive Profitablity History
	 SET @isNetworthMatch= NULL ,@networthFS= NULL ,@networthFP= NULL;   --  Networth
	 SET @isChequeBounceLastOneMonthMatch= NULL ,@chequeBounceLastOneMonthFS= NULL ,@chequeBounceLastOneMonthMinFP= NULL ,@chequeBounceLastOneMonthMaxFP= NULL; --  Cheque Bounce Last One Month
	 SET @isChequeBounceLastSixMonthMatch= NULL ,@chequeBounceLastSixMonthFS= NULL ,@chequeBounceLastSixMonthMinFP= NULL ,@chequeBounceLastSixMonthMaxFP= NULL; --  Cheque Bounce Last Six Month
	 SET @isIndividualCibilMatch= NULL ,@individualCibilFS= NULL ,@individualCibilFP= NULL; --  Individual CIBIL
 	 SET @isCommercialCibilMatch= NULL ,@commercialCibilFS= NULL ,@commercialCibilFP= NULL;  --  Commercial Cibil
	 SET @isInvestmentSizeMatch= NULL ,@investmentSizeFS= NULL ,@investmentSizeMinFP= NULL ,@investmentSizeMaxFP= NULL; --  Investment Size
	 SET @minAdditionalLoanFP= NULL ,@additionalLoanAmountFS= NULL ,@maxAdditionalLoanFP= NULL ,@minTotalLoanFP= NULL,@existingLoanAmount= NULL,@maxTotalLoanFP= NULL,@wcRenewalStatus=NULL,@fpArrangmentId=NULL,@loanArrangementId=NUL; --  Investment exiting and addition loan amount changes
	 SET @isTenureMatch= NULL ,@tenureFS= NULL ,@tenureMinFP= NULL ,@tenureMaxFP= NULL; --  Tenure
	 SET @isScoreMatch= NULL ,@scoreFS= NULL ,@scoreMinFP= NULL ,@scoreMaxFP= NULL;  --  Score
	 SET @isColleteralCoverageMatch= NULL ,@colleteralCoverageFS= NULL ,@colleteralCoverageMinFP= NULL ,@colleteralCoverageMaxFP= NULL; --  Colleteral Coverage
	 SET @isCgtmseCoverageMatch= NULL ,@cgtmseCoverageFS= NULL ,@cgtmseCoverageFP= NULL; --  CGTMSE Coverage
	 SET @isMsmeFundingMatch= NULL ,@msmeFundingFS= NULL ,@msmeFundingFP= NULL; --  MSME Funding
	 SET @isTurnOverMatch= NULL ,@turnOverFS= NULL ,@turnOverMinFP= NULL ,@turnOverMaxFP= NULL; --  Turn Over
	 SET @isGrossCashAccuralMatch= NULL ,@grossCashAccuralFS= NULL ,@grossCashAccuralMinFP= NULL ,@grossCashAccuralMaxFP= NULL; --  Gross Cash Accural
	 SET @currentYearSales=NULL,@previousYearSales=NULL,@isMaxDropInTurnoverMatch= FALSE ,@dropInTurnoverFS= NULL ,@dropInTurnoverFP= NULL; --  Drop In Turn Over
	 SET @isUtilisationPercentageMatch= FALSE ,@minUtilPercntValFP= NULL ,@maxUtilPercntValFP= NULL,@utilPercntValFP=NULL,@utilPercntValFS=NULL; --  Utilization percentage
	 SET @isCreditSummationMatched= FALSE ,@minCreditSummationValFP= NULL ,@maxCreditSummationValFP= NULL,@creditSummationValFP=NULL,@creditSummationValFS=NULL; --  Credit Summation
	 SET @isCollateralCoverageNewMatched= FALSE ,@minCollateralCoverageNewValFP= NULL ,@maxCollateralCoverageNewValFP= NULL,@collateralCoverageNewValFP=NULL,@collateralCoverageNewValFS=NULL,@loanAmout=NULL; --  Collateral Coverage
-- ===================================== End Get Product Parameter Variable ===================================== --
-- ===================================== Start Get Debt Equity Ration Matches Variable  ===================================== --
SET @debtEquityValFS=(SELECT (SELECT (IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other) + IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest) + IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)
							) FROM fs_corporate_cma_liabilities_details WHERE application_id =applicationId AND is_active = 1 AND YEAR= @itrYear AND proposal_mapping_id IS NULL)
							 /(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) - IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details WHERE application_id =applicationId AND is_active = 1 AND YEAR= @itrYear AND proposal_mapping_id IS NULL));
IF (@isDebtEquityDisplay = TRUE) THEN
	SET @debtEquityQuery:= CONCAT('SELECT CASE WHEN max_debt_equity=5
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_liabilities_details
					WHERE application_id=',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1 AND
					-- //(SELECT (SELECT (IF(ISNULL(sub_total_a),0,sub_total_a)  IF(ISNULL(short_term_borrowing_from_others),0,short_term_borrowing_from_others)
					(SELECT (SELECT ( IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares)
					 +IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other)  +
					IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest)  +
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability))
					FROM fs_corporate_cma_liabilities_details WHERE application_id=',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear,' AND proposal_mapping_id IS NULL ORDER BY id DESC LIMIT 1)
					/
					(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) -
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details
					WHERE application_id=',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					>= (SELECT min_debt_equity FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId,'))
					ELSE
					(SELECT COUNT(application_id) FROM fs_corporate_cma_liabilities_details
					WHERE application_id=',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1 AND
					-- //(SELECT (SELECT (IF(ISNULL(sub_total_a),0,sub_total_a)  IF(ISNULL(short_term_borrowing_from_others),0,short_term_borrowing_from_others)
					(SELECT (SELECT ( IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares)
					 +IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other)  +
					IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest)  +
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability))
					FROM fs_corporate_cma_liabilities_details WHERE application_id=',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1)
					/
					(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) -
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details
					WHERE application_id=',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					BETWEEN (SELECT min_debt_equity FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId,') AND
					(SELECT max_debt_equity FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId,'))
					END
					INTO @debtEquityMatch FROM ' , @fpTableName ,' WHERE fp_product_id = ',@fpProdId,'');
					PREPARE stmt FROM @debtEquityQuery ;
					EXECUTE stmt ;
	IF @debtEquityMatch > 0 THEN
		SET @isDebtEquityMatch = TRUE;
	ELSE
		SET @isDebtEquityMatch = FALSE;
	END IF;
	SET @debtEquityFPMinMaxQuery:= CONCAT('SELECT f.min_debt_equity,f.max_debt_equity INTO @debtEquityValMinFP,@debtEquityValMaxFP FROM ',@fpTableName,' f WHERE f.fp_product_id=',@fpProdId);
	PREPARE stmt FROM @debtEquityFPMinMaxQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Get Debt Equity Ration Matches Variable  =====================================  --
-- =====================================Start Get Customer Concentration Matches Variable =====================================  --
SET @customerConcentrationQuery:= CONCAT('SELECT `customer_concentration` INTO @customerConcentrationFS FROM `gst`.`gst_mapping_table` WHERE gstin IN (SELECT `gstin` FROM `loan_application`.`fs_corporate_applicant_details` WHERE proposal_mapping_id IS NULL AND `application_id` = ',@applicationId,' )  ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @customerConcentrationQuery ;
	EXECUTE stmt ;
IF (@isCustomerConcentrationDisplay = TRUE) THEN
	SET @customerConcentrationParameterQuery:= CONCAT('SELECT min_customer_concentration,max_customer_concentration INTO @minCustomerConcentration,@maxCustomerConcentration FROM ' , @fpTableName ,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @customerConcentrationParameterQuery ;
	EXECUTE stmt ;
	IF @customerConcentrationFS >= @minCustomerConcentration AND @customerConcentrationFS <= @maxCustomerConcentration THEN
		SET @isCustomerConcentrationMatch =TRUE;
	ELSE
		SET @isCustomerConcentrationMatch =FALSE;
	END IF;
END IF;
-- ===================================== End Get Customer Concentration Matches Variable =====================================  --
-- ===================================== Start Get Industry Sector Matches Variable =====================================  --
SET @industryFSQuery:= CONCAT('SELECT `industry_name` INTO @industryFS FROM `one_form`.`industry` WHERE id IN (SELECT key_verical_funding FROM `loan_application`.fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId,')');
	PREPARE stmt FROM @industryFSQuery ;
	EXECUTE stmt ;
IF (@isIndustrySectorDisplay = TRUE) THEN
	SET @industrySectorQuery:= CONCAT('SELECT COUNT(application_id) INTO @industrySectorMatch FROM fs_corporate_applicant_details
							WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId,'  AND key_verical_funding
							IN(SELECT industry_id FROM industry_sector_details
							WHERE fp_product_id = ',@fpProdId,' AND is_active=1)');
	PREPARE stmt FROM @industrySectorQuery ;
	EXECUTE stmt ;
	IF @industrySectorMatch > 0 THEN
		SET @isIndustrySectorMatch = TRUE;
	ELSE
		SET @isIndustrySectorMatch = FALSE;
	END IF;
	SET @industryFPQuery:= CONCAT('SELECT GROUP_CONCAT(`industry_name`) INTO @industryFP FROM `one_form`.`industry` WHERE id IN (SELECT industry_id FROM `loan_application`.industry_sector_details WHERE fp_product_id = ',@fpProdId,' AND is_active=1 AND industry_id IS NOT NULL)');
	PREPARE stmt FROM @industryFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Get Industry Sector Matches Variable =====================================  --
-- ===================================== Start Geo Graphical Focus Matches Variable =====================================  --
SET @geoGraphicalFocusFSQuery:= CONCAT('SELECT GROUP_CONCAT(`city_name`) INTO @geoGraphicalFocusFS FROM `one_form`.`city` WHERE id IN (SELECT registered_city_id FROM loan_application.fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId,')');
	PREPARE stmt FROM @geoGraphicalFocusFSQuery ;
	EXECUTE stmt ;
IF (@isGeographicalDisplay = TRUE) THEN
	SET @geoGraphicalFocusQuery:= CONCAT('SELECT COUNT(application_id) INTO @isGeoGraphicalFocusMatch FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId,'
							AND registered_city_id IN(SELECT city_id FROM fp_geographical_city_details
							WHERE fp_product_master_id = ',@fpProdId,' AND is_active = 1 )');
	PREPARE stmt FROM @geoGraphicalFocusQuery ;
	EXECUTE stmt ;
	SET @geoGraphicalFocusFPQuery:= CONCAT('SELECT GROUP_CONCAT(`city_name`) INTO @geoGraphicalFocusFP FROM `one_form`.`city` WHERE id IN (SELECT city_id FROM `loan_application`.fp_geographical_city_details
							WHERE fp_product_master_id = ',@fpProdId,' AND is_active = 1)');
	PREPARE stmt FROM @geoGraphicalFocusFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Geo Graphical Focus Matches Variable =====================================  --
-- ===================================== Start Current Ratio Focus Matches Variable =====================================  --
SET @currentRatioFSQuery:= CONCAT('SELECT (IF(ISNULL(current_ratio),0,current_ratio)) INTO @currentRatioFS FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
						AND YEAR= ',@itrYear,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @currentRatioFSQuery ;
	EXECUTE stmt ;
IF (@isCurrentratioDisplay = TRUE) THEN
	SET @currentRatioMatchQuery:= CONCAT('SELECT CASE WHEN max_current_ratio=5
						THEN
						(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND YEAR=',@itrYear,' AND proposal_mapping_id IS NULL AND is_active = 1
						AND ((SELECT (IF(ISNULL(current_ratio),0,current_ratio)) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
						AND YEAR= ',@itrYear,' ORDER BY id DESC LIMIT 1)) >= (SELECT min_current_ratio FROM
						',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))
						ELSE
						(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND proposal_mapping_id IS NULL AND is_active = 1
						AND ((SELECT (IF(ISNULL(current_ratio),0,current_ratio)) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
						AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1)) BETWEEN (SELECT min_current_ratio FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId,') AND (SELECT max_current_ratio FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))
						END
						INTO @isCurrentRatioMatch FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @currentRatioMatchQuery ;
	EXECUTE stmt ;
	SET @currentRatioMinFPQuery:= CONCAT('SELECT min_current_ratio INTO @currentRatioMinFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'');
	PREPARE stmt FROM @currentRatioMinFPQuery ;
	EXECUTE stmt ;
	SET @currentRatioMaxFPQuery:= CONCAT('SELECT max_current_ratio INTO @currentRatioMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'');
	PREPARE stmt FROM @currentRatioMaxFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Current Ratio Focus Matches Variable =====================================  --
-- ===================================== Start Interest Coverage Ratio Matches Variable =====================================  --
SET @interestCoverageRatioFSQuery:= CONCAT('SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) INTO @interestCoverageRatioFS FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',@itrYear);
PREPARE stmt FROM @interestCoverageRatioFSQuery ;
EXECUTE stmt ;
IF (@isInterestCoverageDisplay = TRUE) THEN
	SET @ebitValueQuery:= CONCAT('SELECT IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) INTO @ebitValue FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR = ',@itrYear);
	PREPARE stmt FROM @ebitValueQuery ;
	EXECUTE stmt ;
	SET @interestValueQuery:= CONCAT('SELECT IF(ISNULL(interest),0,interest) INTO @interestValue FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR = ',@itrYear);
	PREPARE stmt FROM @interestValueQuery ;
	EXECUTE stmt ;
	IF (@ebitValue < 0 AND @interestValue < 0) THEN
		SET @isInterestCoverageRatioMatch = FALSE;
	ELSEIF (@ebitValue < 0 AND @interestValue = 0) THEN
		SET @isInterestCoverageRatioMatch = FALSE;
	ELSEIF @ebitValue = 0 THEN
		SET @isInterestCoverageRatioMatch = FALSE;
	ELSEIF @ebitValue = 0 AND @interestValue = 0 THEN
		SET @isInterestCoverageRatioMatch = FALSE;
	ELSEIF @ebitValue > 0 AND @interestValue = 0 THEN
		SET @isInterestCoverageRatioMatch = TRUE;
	ELSE
		SET @interestCoverageRatioMatchQuery:= CONCAT('SELECT CASE WHEN min_interest_coverage=0 && max_interest_coverage=10
							THEN
							1
							WHEN min_interest_coverage=0
							THEN
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
							AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) <= (SELECT max_interest_coverage FROM ',@fpTableName,'
							WHERE fp_product_id =',@fpProdId,'))
							WHEN max_interest_coverage=10
							THEN
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
							AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) >= (SELECT min_interest_coverage FROM ',@fpTableName,'
							WHERE fp_product_id =',@fpProdId,'))
							ELSE
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
							AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) BETWEEN (SELECT min_interest_coverage FROM ',@fpTableName,'
							WHERE fp_product_id =',@fpProdId,') AND (SELECT max_interest_coverage FROM ',@fpTableName,'
							WHERE fp_product_id =',@fpProdId,'))
							END
							INTO @isInterestCoverageRatioMatch FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
		PREPARE stmt FROM @interestCoverageRatioMatchQuery ;
		EXECUTE stmt ;
	END IF;
	SET @interestCoverageRatioMinFPQuery:= CONCAT('SELECT min_interest_coverage INTO @interestCoverageRatioMinFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @interestCoverageRatioMinFPQuery ;
	EXECUTE stmt ;
	SET @interestCoverageRatioMaxFPQuery:= CONCAT('SELECT max_interest_coverage INTO @interestCoverageRatioMaxFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @interestCoverageRatioMaxFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Interest Coverage Ratio Matches Variable =====================================  --
-- ===================================== Start TOL TNW Matches Variable =====================================  --
SET @tolTnwMatchMaxFSQuery:= CONCAT('SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) INTO @tolTnwMatchMaxFS FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR= ',@itrYear);
	PREPARE stmt FROM @tolTnwMatchMaxFSQuery ;
	EXECUTE stmt ;
IF (@isTolTnwDisplay = TRUE) THEN
	SET @tolTnwMatchQuery:= CONCAT('SELECT CASE WHEN max_tol_tnw=10
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR= ',@itrYear,' AND is_active = 1
					AND (SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) FROM fs_corporate_cma_assets_details
					WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
					AND YEAR=',@itrYear,') >= (SELECT min_tol_tnw FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))
					ELSE
					(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
					AND (SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) FROM
					fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
					AND YEAR= ',@itrYear,') BETWEEN (SELECT min_tol_tnw FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')
					AND (SELECT max_tol_tnw FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					END
					INTO @isTolTnwMatch FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
	PREPARE stmt FROM @tolTnwMatchQuery ;
	EXECUTE stmt ;
	SET @tolTnwMatchMinFPQuery:= CONCAT('SELECT min_tol_tnw INTO @tolTnwMatchMinFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @tolTnwMatchMinFPQuery ;
	EXECUTE stmt ;
	SET @tolTnwMatchMaxFPQuery:= CONCAT('SELECT max_tol_tnw INTO @tolTnwMatchMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @tolTnwMatchMaxFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End TOL TNW Matches Variable =====================================  --
-- ===================================== Start Past Year Turn Over Matches Variable =====================================  --
SET @pastYearTurnOverFSQuery:= CONCAT('SELECT (IF(IsNull(domestic_sales),0,domestic_sales)) + (IF(IsNull(export_sales),0,export_sales)) INTO @pastYearTurnOverFS FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND year=',@itrYear);
	PREPARE stmt FROM @pastYearTurnOverFSQuery ;
	EXECUTE stmt ;
IF (@isPastYearTurnoverDisplay = TRUE) THEN
	SET @pastYearTurnOverQuery:= CONCAT('SELECT CASE WHEN min_past_turnover=0 && max_past_turnover=1000000000
					THEN
					1
					WHEN min_past_turnover=0
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
					AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					<= (SELECT max_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					WHEN max_past_turnover=1000000000
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
					AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					>= (SELECT min_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					ELSE
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1
					AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					BETWEEN (SELECT min_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')
					AND (SELECT max_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					END
					INTO @isPastYearTurnOverMatch FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
	PREPARE stmt FROM @pastYearTurnOverQuery ;
	EXECUTE stmt ;
	SET @pastYearTurnOverMinFPQuery:= CONCAT('SELECT min_past_turnover INTO @pastYearTurnOverMinFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @pastYearTurnOverMinFPQuery ;
	EXECUTE stmt ;
	SET @pastYearTurnOverMaxFPQuery:= CONCAT('SELECT max_past_turnover INTO @pastYearTurnOverMaxFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @pastYearTurnOverMaxFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Past Year Turn Over Matches Variable =====================================  --
-- ===================================== Start Age of Establishment Matched Matches Variable =====================================  --
SET @businessYearFSQuery:= CONCAT('SELECT business_since_year INTO @businessYear FROM fs_corporate_applicant_details WHERE  proposal_mapping_id IS NULL AND application_id = ',@applicationId);
	PREPARE stmt FROM @businessYearFSQuery ;
	EXECUTE stmt ;
IF (@businessYear IS NULL) THEN
	SET @establishmentYearFSQuery:= CONCAT('SELECT establishment_year INTO @estYear FROM fs_corporate_applicant_details WHERE  proposal_mapping_id IS NULL AND application_id = ',@applicationId);
		PREPARE stmt FROM @establishmentYearFSQuery ;
		EXECUTE stmt ;
	SET @establishmentMonthFSQuery:= CONCAT('SELECT establishment_month INTO @estMonth FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId);
		PREPARE stmt FROM @establishmentMonthFSQuery ;
		EXECUTE stmt ;
	SET @ageOfEstablishmentFSQuery:= CONCAT('SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW()) INTO @ageOfEstablishmentFS');
		PREPARE stmt FROM @ageOfEstablishmentFSQuery ;
		EXECUTE stmt ;
ELSE
	SET @businessMonthFSQuery:= CONCAT('SELECT business_since_month INTO @businessMonth FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId);
		PREPARE stmt FROM @businessMonthFSQuery ;
		EXECUTE stmt ;
	IF (@businessMonth > 6) THEN
		SET @ageOfEstablishmentFS = @businessYear + 1;
	ELSE
		SET @ageOfEstablishmentFS = @businessYear;
	END IF;
END IF;
IF (@isEstablishmentDisplay = TRUE) THEN
	IF (@businessYear IS NULL) THEN
		SET @ageOfEstablishmentQuery:= CONCAT('SELECT CASE WHEN ((SELECT max_age_establishment FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId,') >= 30 )
						THEN (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id = ',@applicationId,'
						AND (SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW())) >=(SELECT min_age_establishment FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId,')) ELSE (SELECT COUNT(application_id) FROM fs_corporate_applicant_details
						WHERE proposal_mapping_id IS NULL AND application_id =',@applicationId,' AND (SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW())) BETWEEN (SELECT min_age_establishment FROM ',@fpTableName,'
						WHERE fp_product_id =',@fpProdId,')  AND (SELECT max_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) END INTO @isAgeOfEstablishmentMatch');
	ELSE
		SET @ageOfEstablishmentQuery:= CONCAT('SELECT CASE WHEN ((SELECT max_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =', @fpProdId,') >= 30 )',
						' THEN (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id =',@applicationId,' AND ',@ageOfEstablishmentFS,' >= ',
						' (SELECT min_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))',
						' ELSE (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE proposal_mapping_id IS NULL AND application_id =',@applicationId,' AND ', @ageOfEstablishmentFS,
						' BETWEEN (SELECT min_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =', @fpProdId,') AND ',
						' (SELECT max_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =', @fpProdId,') ) END INTO @isAgeOfEstablishmentMatch');
	END IF;
	PREPARE stmt FROM @ageOfEstablishmentQuery ;
	EXECUTE stmt ;
	SET @ageOfEstablishmentMinFPQuery:= CONCAT('SELECT min_age_establishment INTO @ageOfEstablishmentMinFP FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @ageOfEstablishmentMinFPQuery ;
	EXECUTE stmt ;
	SET @ageOfEstablishmentMaxFPQuery:= CONCAT('SELECT max_age_establishment INTO @ageOfEstablishmentMaxFP FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @ageOfEstablishmentMaxFPQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Age of Establishment Matched Matches Variable =====================================  --
-- ===================================== Start Positive Profitablity History Matches Variable =====================================  --
SET @profitablityHistoryFSQuery:= CONCAT('SELECT COUNT(net_profit_or_loss) INTO @profitablityHistoryFS FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= ',@itrYear,' AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryFSQuery ;
		EXECUTE stmt ;
IF (@isProfitabilityHistoryDisplay = TRUE) THEN
	SET @profitablityHistoryFPQuery:= CONCAT('SELECT profitability_history INTO @profitablityHistoryFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @profitablityHistoryFPQuery;
	EXECUTE stmt ;
	IF (@profitablityHistoryFP = 1) THEN
		SET @isProfitablityHistoryMatch = TRUE;
	ELSEIF (@profitablityHistoryFP = 2) THEN
		SET @profitablityHistoryOneYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= (',@itrYear,'-2) AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryOneYearQuery ;
		EXECUTE stmt ;
	ELSEIF (@profitablityHistoryFP = 3) THEN
		SET @profitablityHistoryTwoYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= (',@itrYear,'-1) AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryTwoYearQuery ;
		EXECUTE stmt ;
	ELSEIF (@profitablityHistoryFP = 4) THEN
		SET @profitablityHistoryThreeYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= ',@itrYear,' AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryThreeYearQuery ;
		EXECUTE stmt ;
	END IF;
END IF;
-- ===================================== End Positive Profitablity History Matches Variable =====================================  --
-- ===================================== Start Networth Matches Variable =====================================  --
SET @networthFSQuery:= CONCAT('SELECT COUNT(tangible_net_worth) INTO @networthFS FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= ',@itrYear,' AND tangible_net_worth >0 ORDER BY YEAR');
	PREPARE stmt FROM @networthFSQuery ;
	EXECUTE stmt ;
IF (@isNetworthDisplay = TRUE) THEN
	SET @networthFPQuery:= CONCAT('SELECT net_worth INTO @networthFP FROM ',@fpTableName,' WHERE fp_product_id =',fpProdId);
		PREPARE stmt FROM @networthFPQuery ;
		EXECUTE stmt ;
	IF (@networthFP = 1) THEN
		SET @isNetworthMatch = TRUE;
	ELSEIF (@networthFP = 2) THEN
		SET @isNetworthOneYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= (',@itrYear,'-2) AND tangible_net_worth >0 ORDER BY YEAR');
		PREPARE stmt FROM @isNetworthOneYearMatchQuery ;
		EXECUTE stmt ;
	ELSEIF (@networthFP = 3) THEN
		SET @isNetworthTwoYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= (',@itrYear,'-1) AND tangible_net_worth >0 ORDER BY YEAR');
		PREPARE stmt FROM @isNetworthTwoYearMatchQuery ;
		EXECUTE stmt ;
	ELSEIF (@networthFP = 4) THEN
		SET @isNetworthThreeYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND `year` <= ',@itrYear,' AND tangible_net_worth >0 ORDER BY YEAR');
		PREPARE stmt FROM @isNetworthThreeYearMatchQuery ;
		EXECUTE stmt ;
	END IF;
END IF;
-- ===================================== End Networth Matches Variable =====================================  --
-- ===================================== Start Cheque Bounce Last One Month Matches Variable =====================================  --
SET @chequeBounceLastOneMonthFSQuery:= CONCAT('SELECT last_one_month INTO @chequeBounceLastOneMonthFS FROM  `statement_analyzer`.`matches_data` WHERE application_id =',@applicationId);
	PREPARE stmt FROM @chequeBounceLastOneMonthFSQuery ;
	EXECUTE stmt ;
IF (@isChequeBouncedDisplay = TRUE) THEN
	SET @chequeBounceLastOneMonthFPQuery:= CONCAT('SELECT min_cheque_bounced,max_cheque_bounced INTO @chequeBounceLastOneMonthMinFP,@chequeBounceLastOneMonthMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @chequeBounceLastOneMonthFPQuery ;
	EXECUTE stmt ;
	IF (@chequeBounceLastOneMonthFS IS NOT NULL) THEN
		SET @isChequeBounceLastOneMonthMatchQuery:= CONCAT('SELECT IF((',@chequeBounceLastOneMonthFS,' >= ',@chequeBounceLastOneMonthMinFP,' AND (',@chequeBounceLastOneMonthMaxFP,'= 30 || ',@chequeBounceLastOneMonthFS,' <= ',@chequeBounceLastOneMonthMaxFP,' )), TRUE,FALSE) INTO @isChequeBounceLastOneMonthMatch ');
		PREPARE stmt FROM @isChequeBounceLastOneMonthMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isChequeBounceLastOneMonthMatch=FALSE;
	END IF;
END IF;
-- ===================================== End Cheque Bounce Last One Month Matches Variable =====================================  --
-- ===================================== Start Cheque Bounce Last Six Month Matches Variable =====================================  --
SET @chequeBounceLastSixMonthFSQuery:= CONCAT('SELECT last_six_month INTO @chequeBounceLastSixMonthFS FROM  `statement_analyzer`.`matches_data` WHERE application_id =',@applicationId);
	PREPARE stmt FROM @chequeBounceLastSixMonthFSQuery ;
	EXECUTE stmt ;
IF (@isChequeBouncedLastSixMonthsDisplay = TRUE) THEN
	SET @chequeBounceLastSixMonthFPQuery:= CONCAT('SELECT min_cheque_bounced_last_six_months,max_cheque_bounced_last_six_months INTO @chequeBounceLastSixMonthMinFP,@chequeBounceLastSixMonthMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @chequeBounceLastSixMonthFPQuery ;
	EXECUTE stmt ;
	SET @isChequeBounceLastSixMonthMatchQuery:= CONCAT('SELECT IF((',@chequeBounceLastSixMonthFS,' >= ',@chequeBounceLastSixMonthMinFP,' AND (',@chequeBounceLastSixMonthMaxFP,'= 200 || ',@chequeBounceLastSixMonthFS,' <= ',@chequeBounceLastSixMonthMaxFP,' )), TRUE,FALSE) INTO @isChequeBounceLastSixMonthMatch ');
	PREPARE stmt FROM @isChequeBounceLastSixMonthMatchQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Cheque Bounce Last Six Month Matches Variable =====================================  --
-- ===================================== Start Individual Cibil Matches Variable =====================================  --
SET @individualCibilFSMatchQuery:= CONCAT('SELECT ROUND(MIN(score)) INTO @individualCibilFS FROM `cibil`.`cibil_score_log_details` WHERE cibil_id IN (SELECT cibil_id FROM `cibil`.`cibil_mstr` WHERE application_id = ',@applicationId,')');
	PREPARE stmt FROM @individualCibilFSMatchQuery ;
	EXECUTE stmt ;
IF (@isIndividualCibilDisplay = TRUE) THEN
	SET @isIndividualCibilMatchQuery:= CONCAT('SELECT IF ((SELECT COUNT(score) FROM `cibil`.`cibil_score_log_details` WHERE cibil_id IN (SELECT cibil_id FROM `cibil`.`cibil_mstr` WHERE application_id=',@applicationId,')
							AND score > (SELECT individual_cibil FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))>0,TRUE,FALSE) INTO @isIndividualCibilMatch');
	PREPARE stmt FROM @isIndividualCibilMatchQuery ;
	EXECUTE stmt ;
	SET @individualCibilFPMatchQuery:= CONCAT('SELECT individual_cibil INTO @individualCibilFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @individualCibilFPMatchQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Individual Cibil Matches Variable =====================================  --
-- ===================================== Start Commercial Cibil Matches Variable =====================================  --
SET @dpdQuery:= CONCAT('SELECT UNCOMPRESS(lg.response) INTO @dpdArray FROM cibil.`cibil_audit_log_details` lg,`cibil`.`cibil_mstr` ms
							WHERE lg.cibil_id = ms.cibil_id AND lg.request_type = "MSME_COMPANY_DPD" AND ms.application_id = ',@applicationId,' order by lg.cibil_id desc limit 1');
	PREPARE stmt FROM @dpdQuery ;
	EXECUTE stmt ;
	SET @value:= REPLACE(REPLACE(@dpdArray, '[', ''), ']', '');
	DROP TEMPORARY TABLE IF EXISTS `dpd_temp_tbl`;
	CREATE TEMPORARY TABLE  dpd_temp_tbl (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `dpd` VARCHAR(100));
	IF (LOCATE(',', @value) = 0) THEN
		 SET @valueOne = SUBSTRING(@value, LOCATE(']',@value) + 1);
		 IF (@valueOne IS NOT NULL AND @valueOne != '' ) THEN
			INSERT INTO dpd_temp_tbl(dpd) VALUES(@valueOne);
		 END IF;
	END IF;
	WHILE (LOCATE(',', @value) > 0) DO
	      SET @V_DESIGNATION = SUBSTRING(@value,1, LOCATE(',',@value)-1);
	      SET @value = SUBSTRING(@value, LOCATE(',',@value) + 1);
	      INSERT INTO dpd_temp_tbl(dpd) VALUES(@V_DESIGNATION);
	END WHILE;
	SET @commercialCibilFSQuery:= CONCAT('SELECT MAX(dpd) FROM dpd_temp_tbl INTO @commercialCibilFS ','');
	PREPARE stmt FROM @commercialCibilFSQuery ;
	EXECUTE stmt ;
IF (@isCommercialCibilDisplay = TRUE) THEN
	SET @commercialCibilFPQuery:= CONCAT('SELECT commercial_cibil INTO @commercialCibilFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @commercialCibilFPQuery ;
	EXECUTE stmt ;
	SET @isCommercialCibilMatchQuery:= CONCAT('SELECT IF(COUNT(dpd)=0,TRUE,FALSE) INTO @isCommercialCibilMatch FROM dpd_temp_tbl WHERE dpd >= ' ,@commercialCibilFP );
	PREPARE stmt FROM @isCommercialCibilMatchQuery ;
	EXECUTE stmt ;
END IF;
-- ===================================== End Commercial Cibil Matches Variable =====================================  --
-- ------------------------------------------------------------ Start Recalculation ------------------------------------------------------------
-- ===================================== Start Investment Size Matches Variable =====================================  --

SET @wcRenewalStatusQuery:=CONCAT('SELECT wc_renewal_status INTO @wcRenewalStatus FROM fs_loan_application_master WHERE application_id =',@applicationId);
PREPARE stmt FROM @wcRenewalStatusQuery;
EXECUTE stmt;

IF (@wcRenewalStatus = 2) THEN

	IF (@isInvestmentSizeDisplay = TRUE) THEN

		SET @investmentSizeFSQuery:= CONCAT('SELECT ((SELECT max_loan_amount FROM loan_application.application_product_audit WHERE application_id = ',@applicationId,' AND fp_product_id = ',@fpProdId,' AND is_active = 1) *
								(SELECT d_value FROM denomination_master WHERE id = (SELECT denomination_id FROM fs_loan_application_master WHERE application_id = ',@applicationId,' ))) INTO @investmentSizeFS ');
		PREPARE stmt FROM @investmentSizeFSQuery ;
		EXECUTE stmt ;

		SET @investmentSizeMinFPQuery:= CONCAT('SELECT (SELECT min_invest_size FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,') * (SELECT d_value FROM denomination_master WHERE id =(SELECT denomination FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,')) INTO @investmentSizeMinFP');
		PREPARE stmt FROM @investmentSizeMinFPQuery ;
		EXECUTE stmt ;

		SET @investmentSizeMaxFPQuery:= CONCAT('SELECT (SELECT max_invest_size FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,') * (SELECT d_value FROM denomination_master WHERE id =(SELECT denomination FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,')) INTO @investmentSizeMaxFP');
		PREPARE stmt FROM @investmentSizeMaxFPQuery ;
		EXECUTE stmt ;

		SET @isInvestmentSizeMatchQuery:= CONCAT('SELECT IF(COUNT(application_id)>0,TRUE,FALSE) FROM fs_loan_application_master WHERE application_id =',@applicationId,'
			AND (SELECT ((SELECT max_loan_amount FROM application_product_audit WHERE application_id =',@applicationId,' AND fp_product_id=',@fpProdId,') *
			(SELECT d_value FROM denomination_master WHERE id = (SELECT denomination_id FROM fs_loan_application_master WHERE application_id =',@applicationId,'))))
			BETWEEN(SELECT ((SELECT min_invest_size FROM ',@fpTableName, '
			WHERE fp_product_id =',@fpProdId,') *
			(SELECT d_value FROM denomination_master WHERE id =(SELECT denomination FROM ',@fpTableName,'
			WHERE fp_product_id =',@fpProdId,')))) AND (SELECT ((SELECT max_invest_size FROM ',@fpTableName,'
			WHERE fp_product_id =',@fpProdId, ') *
			(SELECT d_value FROM denomination_master WHERE id =(SELECT denomination FROM ',@fpTableName,'
			WHERE fp_product_id =',@fpProdId, ')))) INTO @isInvestmentSizeMatch');
		PREPARE stmt FROM @isInvestmentSizeMatchQuery ;
		EXECUTE stmt ;
	END IF;
ELSE
	SET @fpArrangmentValuesQuery := CONCAT('select fp.id,fp.loan_arrangement_id INTO @fpArrangmentId,@loanArrangementId from fp_loan_arrangement_value_mapping fp where fp.fp_product_id=',@fpProdId,' AND fp.is_active=true');
	PREPARE stmt FROM @fpArrangmentValuesQuery ;
	EXECUTE stmt ;
	IF (@loanArrangementId = 3) THEN
		SET @minTotalLoanAmountQuery := CONCAT('select min_total_loan INTO @minTotalLoanFP from ', @fpTableName ,' where fp_product_id=',@fpProdId);
		PREPARE stmt FROM @minTotalLoanAmountQuery ;
		EXECUTE stmt ;

		SET @existingLoanAmountQuery := CONCAT('select IF(ISNULL(existing_loan_amount),0,existing_loan_amount) INTO @existingLoanAmount from application_product_audit where application_id=',@applicationId,' and fp_product_id=',@fpProdId);
		PREPARE stmt FROM @existingLoanAmountQuery ;
		EXECUTE stmt ;

		SET @maxTotalLoanQuery := CONCAT('select max_total_loan INTO @maxTotalLoanFP FROM ', @fpTableName ,' where fp_product_id=',@fpProdId);
		PREPARE stmt FROM @maxTotalLoanQuery ;
		EXECUTE stmt ;

		IF ((@minTotalLoanFP <= @existingLoanAmount) AND (@existingLoanAmount <= @maxTotalLoanFP)) THEN

			SET @minAdditionalLoanAmountQuery := CONCAT('select min_additional_loan INTO @minAdditionalLoanFP from ',@fpTableName,' where fp_product_id=',@fpProdId);
			PREPARE stmt FROM @minAdditionalLoanAmountQuery;
			EXECUTE stmt ;

			SET @additionalLoanAmountQuery := CONCAT('select IF(ISNULL(additional_loan_amount),0,additional_loan_amount) INTO @additionalLoanAmountFS from application_product_audit where application_id=',@applicationId,' and fp_product_id=',@fpProdId);
			PREPARE stmt FROM @additionalLoanAmountQuery;
			EXECUTE stmt;

			SET @maxAdditionalLoanAmountQuery := CONCAT('select max_additional_loan INTO @maxAdditionalLoanFP from ',@fpTableName,' where fp_product_id=',@fpProdId);
			PREPARE stmt FROM @maxAdditionalLoanAmountQuery;
			EXECUTE stmt;

			IF ((@minAdditionalLoanFP <= @additionalLoanAmountFS) AND (@additionalLoanAmountFS <= @maxAdditionalLoanFP)) THEN
				SET @isInvestmentSizeMatch = TRUE;
			END IF;
		END IF;
	ELSEIF(@loanArrangementId = 1) THEN

		SET @minTotalLoanAmountQuery := CONCAT('select min_total_loan INTO @minTotalLoanFP from ', @fpTableName ,' where fp_product_id=',@fpProdId);
		PREPARE stmt FROM @minTotalLoanAmountQuery ;
		EXECUTE stmt ;

		SET @existingLoanAmountQuery := CONCAT('select IF(ISNULL(existing_loan_amount),0,existing_loan_amount) INTO @existingLoanAmount from application_product_audit where application_id=',@applicationId,' and fp_product_id=',@fpProdId);
		PREPARE stmt FROM @existingLoanAmountQuery ;
		EXECUTE stmt ;

		SET @maxTotalLoanQuery := CONCAT('select max_total_loan INTO @maxTotalLoanFP FROM ', @fpTableName ,' where fp_product_id=',@fpProdId);
		PREPARE stmt FROM @maxTotalLoanQuery ;
		EXECUTE stmt ;

		IF ((@minTotalLoanFP <= @existingLoanAmount) AND (@existingLoanAmount <= @maxTotalLoanFP)) THEN
			SET @isInvestmentSizeMatch = TRUE;
		END IF;
	ELSEIF(@loanArrangementId = 2) THEN
		SET @minAdditionalLoanAmountQuery := CONCAT('select min_additional_loan INTO @minAdditionalLoanFP from ',@fpTableName,' where fp_product_id=',@fpProdId);
			PREPARE stmt FROM @minAdditionalLoanAmountQuery;
			EXECUTE stmt ;

			SET @additionalLoanAmountQuery := CONCAT('select IF(ISNULL(additional_loan_amount),0,additional_loan_amount) INTO @additionalLoanAmountFS from application_product_audit where application_id=',@applicationId,' and fp_product_id=',@fpProdId);
			PREPARE stmt FROM @additionalLoanAmountQuery;
			EXECUTE stmt;

			SET @maxAdditionalLoanAmountQuery := CONCAT('select max_additional_loan INTO @maxAdditionalLoanFP from ',@fpTableName,' where fp_product_id=',@fpProdId);
			PREPARE stmt FROM @maxAdditionalLoanAmountQuery;
			EXECUTE stmt;

			IF ((@minAdditionalLoanFP <= @additionalLoanAmountFS) AND (@additionalLoanAmountFS <= @maxAdditionalLoanFP)) THEN
				SET @isInvestmentSizeMatch = TRUE;
			END IF;
	END IF;
END IF;
-- ===================================== End Investment Size Matches Variable ===================================== --
-- ===================================== Start Tenure Matches Variable ===================================== --
SET @tenureFSQuery:= CONCAT('SELECT ((SELECT max_tenure FROM application_product_audit WHERE application_id = ',@applicationId,' AND fp_product_id= ',@fpProdId,')*12) INTO @tenureFS ');
	PREPARE stmt FROM @tenureFSQuery ;
	EXECUTE stmt ;
IF (@isTenureDisplay = TRUE) THEN
	SET @tenureFPQuery:= CONCAT('SELECT min_tenure,max_tenure INTO @tenureMinFP,@tenureMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @tenureFPQuery ;
	EXECUTE stmt ;
	IF (@tenureFS IS NOT NULL) THEN
		SET @isTenureMatchQuery:= CONCAT('SELECT IF(',@tenureFS,' >= ',@tenureMinFP,'  AND ',@tenureFS,' <= ',@tenureMaxFP,',TRUE,FALSE) INTO @isTenureMatch');
		PREPARE stmt FROM @isTenureMatchQuery ;
		EXECUTE stmt ;
		SET @tenureFS = @tenureFS /12;
	ELSE
		SET @isTenureMatch=FALSE;
	END IF;
	SET @tenureMinFP = @tenureMinFP/12;
        SET @tenureMaxFP = @tenureMaxFP/12;
END IF;
-- ===================================== End Tenure Matches Variable =====================================  --
-- ===================================== Start Score Matches Variable =====================================  --
SET @scoreFS = 0;
	SET @scoreFSQuery:= CONCAT('SELECT `is_proportionate_score_consider`,`proportionate_score_fs`
				 ,`is_weight_consider`,`management_risk_weight`,`financial_risk_weight`,`business_risk_reight`
				 ,`total_score`
				 INTO @isProportionateScoreConsider,@proportionateScoreFS
				 ,@isWeightConsider,@managementRiskWeight,@financialRiskWeight,@businessRiskWeight
				 ,@totalScore
				 FROM `scoring_sidbi`.`proposal_score` WHERE application_id = ',@applicationId,' AND `fp_product_id`= ',@fpProdId,' AND is_active=TRUE');
	PREPARE stmt FROM @scoreFSQuery ;
	EXECUTE stmt ;
	IF(@isProportionateScoreConsider = TRUE) THEN
		SET @scoreFS := @proportionateScoreFS;
	ELSEIF(@isWeightConsider = TRUE) THEN
		IF(@managementRiskWeight IS NOT NULL) THEN
			SET @scoreFS := @scoreFS + @managementRiskWeight;
		END IF;
		IF(@financialRiskWeight IS NOT NULL) THEN
			SET @scoreFS := @scoreFS + @financialRiskWeight;
		END IF;
		IF(@businessRiskWeight IS NOT NULL) THEN
			SET @scoreFS := @scoreFS + @businessRiskWeight;
		END IF;
	ELSE
		IF(@totalScore IS NOT NULL) THEN
			SET @scoreFS := @totalScore;
		END IF;
	END IF;
IF (@isRiskModelScoreDisplay = TRUE) THEN
	SET @scoreFPQuery:= CONCAT('SELECT min_risk_model_score,max_risk_model_score INTO @scoreMinFP,@scoreMaxFP FROM ',@fpTableName,'  WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @scoreFPQuery ;
	EXECUTE stmt ;
	IF (@scoreFS IS NOT NULL) THEN
		SET @isScoreMatchQuery:= CONCAT('SELECT IF(',@scoreFS,' >= ',@scoreMinFP,',TRUE,FALSE) INTO @isScoreMatch');
		PREPARE stmt FROM @isScoreMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isScoreMatch=FALSE;
	END IF;
END IF;
-- ===================================== End Score Matches Variable =====================================  --
-- ===================================== Start Collateral Coverage Variable =====================================  --
SET @colleteralCoverageQuery:= CONCAT('SELECT `colleteral_coverage` INTO @colleteralCoverageFS FROM `third_party`.`cgtmse_mapping` WHERE `application_id`= ',@applicationId,' AND `product_id`= ',@fpProdId,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @colleteralCoverageQuery ;
	EXECUTE stmt ;
IF (@isCollateralDisplay = TRUE) THEN
	SET @colleteralCoverageFPQuery:= CONCAT('SELECT min_collateral,max_collateral INTO @colleteralCoverageMinFP,@colleteralCoverageMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @colleteralCoverageFPQuery ;
	EXECUTE stmt ;
	IF (@colleteralCoverageFS IS NOT NULL)THEN
		SET @isColleteralCoverageMatchQuery:= CONCAT('SELECT IF((',@colleteralCoverageFS,' >= ',@colleteralCoverageMinFP,' AND (',@colleteralCoverageMaxFP,'= 400 || ',@colleteralCoverageFS,' <= ',@colleteralCoverageMaxFP,' )), TRUE,FALSE) INTO @isColleteralCoverageMatch ');
		PREPARE stmt FROM @isColleteralCoverageMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isColleteralCoverageMatch = FALSE;
	END IF;
END IF;
-- ===================================== End Collateral Coverage Variable =====================================  --
-- ===================================== Start CGTMSE Coverage Variable =====================================  --
IF (@isCgtmseCoverageDisplay = TRUE) THEN
	SET @cgtmseCoverageStatusFSQuery:= CONCAT('SELECT `status` INTO @cgtmseCoverageStatusFS FROM `third_party`.`cgtmse_mapping` WHERE `application_id`= ',@applicationId,' AND `product_id`= ',@fpProdId,' ORDER BY id DESC LIMIT 1');
		PREPARE stmt FROM @cgtmseCoverageStatusFSQuery ;
		EXECUTE stmt ;
	IF(@cgtmseCoverageStatusFS IS NULL OR @cgtmseCoverageStatusFS = 0 ) THEN
		SET @cgtmseCoverageStatusFS = FALSE;
	END IF;
	SET @cgtmseCoverageFPQuery:= CONCAT('SELECT  cgtmse_coverage INTO @cgtmseCoverageFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @cgtmseCoverageFPQuery;
	EXECUTE stmt ;
	IF(@cgtmseCoverageFP = 3) THEN
		SET @isCgtmseCoverageMatch =TRUE;
	ELSEIF (@cgtmseCoverageFP = 1 AND @cgtmseCoverageStatusFS = TRUE) THEN
		SET @isCgtmseCoverageMatch =TRUE;
	ELSEIF (@cgtmseCoverageFP = 2 AND @cgtmseCoverageStatusFS =FALSE ) THEN
		SET @isCgtmseCoverageMatch =TRUE;
	ELSE
		SET @isCgtmseCoverageMatch =FALSE;
	END IF;
	IF(@cgtmseCoverageFP = 3) THEN
		IF (@cgtmseCoverageStatusFS =TRUE) THEN
			SET @cgtmseCoverageFS = "Existing CGTMSE Coverage";
		ELSE
			SET @cgtmseCoverageFS = "New to CGTMSE Coverage";
		END IF;
	ELSEIF(@cgtmseCoverageFP = 2) THEN
		SET @cgtmseCoverageFS = "New to CGTMSE Coverage";
	ELSEIF(@cgtmseCoverageFP = 1) THEN
		SET @cgtmseCoverageFS = "Existing CGTMSE Coverage";
	END IF;
END IF;
-- ===================================== End CGTMSE Coverage Variable =====================================  --
-- ===================================== Start MSME Funding Variable =====================================  --
IF (@isMsmeFundingDisplay = TRUE) THEN
	DROP TEMPORARY TABLE IF EXISTS `msme_type_temp_tbl`;
	CREATE TEMPORARY TABLE  msme_type_temp_tbl (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `value` VARCHAR(100));
	INSERT INTO msme_type_temp_tbl VALUES(1,'Micro');
	INSERT INTO msme_type_temp_tbl VALUES(2,'Small');
	INSERT INTO msme_type_temp_tbl VALUES(3,'Medium');
	SET @msmeFundingFSQuery:= CONCAT('SELECT `status_of_borrower` INTO @msmeFundingFS FROM `third_party`.`cgtmse_mapping` WHERE `application_id`= ',@applicationId,' AND `product_id`= ',@fpProdId,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @msmeFundingFSQuery ;
	EXECUTE stmt ;
	IF(@msmeFundingFS = 'Micro') THEN
		SET @msmeFundingFSId = 1;
	ELSEIF(@msmeFundingFS = 'Small') THEN
		SET @msmeFundingFSId = 2;
	ELSEIF(@msmeFundingFS = 'Medium') THEN
		SET @msmeFundingFSId = 3;
	END IF;
	SET @msmeFundingFPQuery:= CONCAT('SELECT  GROUP_CONCAT(msme.value) INTO @msmeFundingFP FROM `loan_application`.fp_msme_value_mapping  AS fp_msme LEFT JOIN msme_type_temp_tbl AS msme ON msme.id=fp_msme.msme_funding_id WHERE fp_msme.fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @msmeFundingFPQuery ;
	EXECUTE stmt ;
	IF(@msmeFundingFSId IS NOT NULL) THEN
		SET @isMsmeFundingMatchQuery:= CONCAT('SELECT IF (',@msmeFundingFSId,' IN (SELECT  msme_funding_id FROM `loan_application`.fp_msme_value_mapping WHERE fp_product_id = ',@fpProdId,'),TRUE,FALSE) INTO @isMsmeFundingMatch ');
		PREPARE stmt FROM @isMsmeFundingMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isMsmeFundingMatch=FALSE;
	END IF;
END IF;
-- ===================================== End MSME Funding Variable =====================================  --
-- ===================================== Start Turn Over Ratio Variable =====================================  --
SET @maxLoanAmountFSQuery:= CONCAT('SELECT max_loan_amount INTO @maxLoanAmountFS FROM application_product_audit WHERE application_id = ',@applicationId,' AND fp_product_id = ',@fpProdId,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @maxLoanAmountFSQuery ;
	EXECUTE stmt ;
SET @netSaleFSQuery:= CONCAT('SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales)) INTO @netSaleFS FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR = ',@itrYear);
	PREPARE stmt FROM @netSaleFSQuery ;
	EXECUTE stmt ;
SET @turnOverFS=0;
IF(@maxLoanAmountFS IS NOT NULL) THEN
	SET @turnOverFS = (@netSaleFS / @maxLoanAmountFS);
END IF;
IF (@isTurnOverRatioDisplay = TRUE) THEN
	SET @turnOverFPQuery:= CONCAT('SELECT min_turnover_ratio,max_turnover_ratio INTO @turnOverMinFP,@turnOverMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @turnOverFPQuery ;
	EXECUTE stmt ;
	IF (@turnOverFS IS NOT NULL) THEN
		SET @isTurnOverMatchQuery:= CONCAT('SELECT IF((',@turnOverFS,' >= ',@turnOverMinFP,' AND (',@turnOverMaxFP,'= 50 || ',@turnOverFS,' <= ',@turnOverMaxFP,' )), TRUE,FALSE) INTO @isTurnOverMatch ');
		PREPARE stmt FROM @isTurnOverMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isTurnOverMatch=FALSE;
	END IF;
END IF;
-- ===================================== End Turn Over Ratio Variable =====================================  --
-- ===================================== Start Gross Cash Accural Variable =====================================  --
SET @grossCashAccuralQuery:= CONCAT('SELECT (IF(IsNull(net_profit_or_loss),0,net_profit_or_loss)) + (IF(IsNull(depreciation),0,depreciation)) + (IF(IsNull(interest),0,interest)) INTO @grossCashAccural FROM fs_corporate_cma_operating_statement_details
							WHERE application_id = ',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND year=',@itrYear);
	PREPARE stmt FROM @grossCashAccuralQuery ;
	EXECUTE stmt ;
SET @maxLoanAmountFSQuery:= CONCAT('SELECT max_loan_amount INTO @maxLoanAmountFS FROM application_product_audit WHERE application_id = ',@applicationId,' AND fp_product_id = ',@fpProdId,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @maxLoanAmountFSQuery ;
	EXECUTE stmt ;
SET @grossCashAccuralFS=0;
IF(@maxLoanAmountFS IS NOT NULL) THEN
	SET @grossCashAccuralFS = (@grossCashAccural / @maxLoanAmountFS);
IF (@isGrossCashAccuralsRatioDisplay = TRUE) THEN
	SET @grossCashAccuralFPQuery:= CONCAT('SELECT min_gross_cash_accurals_ratio,max_gross_cash_accurals_ratio INTO @grossCashAccuralMinFP,@grossCashAccuralMaxFP FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId);
	PREPARE stmt FROM @grossCashAccuralFPQuery ;
	EXECUTE stmt ;
	END IF;
	IF(@grossCashAccural != 0) THEN
		SET @isGrossCashAccuralrMatchQuery:= CONCAT('SELECT IF((',@grossCashAccuralFS,' >= ',@grossCashAccuralMinFP,' AND (',@grossCashAccuralMaxFP,'= 100 || ',@grossCashAccuralFS,' <= ',@grossCashAccuralMaxFP,' )), TRUE,FALSE) INTO @isGrossCashAccuralMatch ');
		PREPARE stmt FROM @isGrossCashAccuralrMatchQuery ;
		EXECUTE stmt ;
	ELSE
		SET @isGrossCashAccuralMatch =FALSE;
	END IF;
END IF;
-- ===================================== End Gross Cash Accural Variable =====================================  --
-- ===================================== Start Max Drop in turn over Variable =====================================  --
IF (@isMaxDropInTurnoverDisplay = TRUE) THEN
	SET @currentYearSalesForMaxDropInTurnoverQuery:= CONCAT('SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)-IF(ISNULL(export_sales),0,export_sales)) INTO @currentYearSales FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',@itrYear);
	PREPARE stmt FROM @currentYearSalesForMaxDropInTurnoverQuery;
	EXECUTE stmt ;
	SET @previousYearSalesForMaxDropInTurnoverQuery:= CONCAT('SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)-IF(ISNULL(export_sales),0,export_sales)) INTO @previousYearSales FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId, ' AND proposal_mapping_id IS NULL AND is_active = 1 AND YEAR=',(@itrYear-1));
	PREPARE stmt FROM @previousYearSalesForMaxDropInTurnoverQuery;
	EXECUTE stmt ;
	IF (@currentYearSales <=0) THEN
		SET @dropInTurnoverFS='Current year sale not found';
	ELSEIF (@previousYearSales <=0) THEN
		SET @dropInTurnoverFS='Previous year sale not found';
	ELSEIF ((@previousYearSales-@currentYearSales) <=0) THEN
		SET @dropInTurnoverFS='Increase in Current Year turnover compares to Previous Year turnover';
	ELSE
		SET @dropInTurnoverFS = ((@previousYearSales-@currentYearSales)/@previousYearSales) * 100;
		SET @dropInTurnoverFS  = ROUND(@dropInTurnoverFS,0);
	END IF;
	SET @fpDropInTurnoverQuery:= CONCAT('SELECT max_drop_in_turnover INTO @dropInTurnoverFP FROM ',@fpTableName,' where fp_product_id =',@fpProdId);
	PREPARE stmt FROM @fpDropInTurnoverQuery;
	EXECUTE stmt ;
	SET @isMaxDropInTurnoverMatchQuery:= CONCAT('SELECT CASE WHEN max_drop_in_turnover=100 THEN
								1 ELSE (',@dropInTurnoverFS,' <= (SELECT max_drop_in_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
								END INTO @isMaxDropInTurnoverMatch
								FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
	PREPARE stmt FROM @isMaxDropInTurnoverMatchQuery;
	EXECUTE stmt ;
	IF (@dropInTurnoverFP = NULL) THEN
		SET @dropInTurnoverFP = '-';
	ELSE
		SET @dropInTurnoverFP = ROUND(@dropInTurnoverFP,0);
	END IF;
	SET @dropInTurnoverFS = CONCAT(@dropInTurnoverFS,'%');
	SET @dropInTurnoverFP = CONCAT(@dropInTurnoverFP,'%');
ELSE
	SET @isMaxDropInTurnoverMatch = FALSE;
	SET @dropInTurnoverFP='-';
	SET @isMaxDropInTurnoverDisplay=FALSE;
	SET @isMaxDropInTurnoverMandatory=FALSE;
END IF;
-- ===================================== End Max Drop in turn over Variable =====================================  --
-- ===================================== Utilization percentage Variable =====================================  --
IF (@isUtilisationPercentageDisplay = TRUE) THEN
	SET @avrgBalanceFromBankQuery:=CONCAT('SELECT avg_balance INTO @balanceList FROM statement_analyzer.matches_data WHERE application_id=',@applicationId);
	PREPARE stmt FROM @avrgBalanceFromBankQuery;
	EXECUTE stmt;
	DROP TEMPORARY TABLE IF EXISTS `loan_application`.`avg_bal_temp_tbl`;
	CREATE TEMPORARY TABLE  `loan_application`.`avg_bal_temp_tbl` (id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY, `balance` VARCHAR(100));
	SET @minUtilPercntValFP= '0';
	SET @balanceList :=CONCAT('[',@balanceList,']');
	SET @value:= REPLACE(REPLACE(@balanceList, '[', ''), ']', '');
	IF (LOCATE(',', @value) = 0) THEN
		 SET @valueOne = SUBSTRING(@value, LOCATE(']',@value) + 1);
		IF (@valueOne IS NOT NULL AND @valueOne != '' ) THEN
		 	INSERT INTO avg_bal_temp_tbl(balance) VALUES(@valueOne);
		END IF;
	END IF;
	WHILE (LOCATE(',', @value) > 0) DO
	      SET @balance = SUBSTRING(@value,1, LOCATE(',',@value)-1);
	      SET @value = SUBSTRING(@value, LOCATE(',',@value) + 1);
	      INSERT INTO avg_bal_temp_tbl(balance) VALUES(@balance);
	END WHILE;
	SET @avgBalanceQuery := CONCAT('SELECT avg(balance) INTO @avrgBalanceFromBank FROM loan_application.avg_bal_temp_tbl');
	PREPARE stmt FROM @avgBalanceQuery;
	EXECUTE stmt;
	SET @noOfMonthQuery := CONCAT('SELECT count(balance) INTO @noOfMonths FROM loan_application.avg_bal_temp_tbl');
	PREPARE stmt FROM @noOfMonthQuery;
	EXECUTE stmt;
	SET @existingLimitsQuery:=CONCAT('select sum(o.amount) INTO @existingLimits from fs_corporate_current_financial_arrangements_details o where o.application_id =',@applicationId,' and o.is_active = true and o.director_id IS NULL and LOWER(o.loan_type) IN (\'Cash credit\',\'Overdraft\') and o.outstanding_amount IS NOT NULL and o.outstanding_amount > 0');
	PREPARE stmt FROM @existingLimitsQuery;
	EXECUTE stmt;
	IF (@avrgBalanceFromBank IS NULL OR @avrgBalanceFromBank =0) THEN
		SET @utilPercntValFS = 'Credit summation from bank account not found';
		SET @isUtilisationPercentageMatch=FALSE;
	ELSEIF (@existingLimits IS NULL OR @existingLimits=0 OR @avrgBalanceFromBank < 0) THEN
		SET @utilPercntValFS='No limit account found from Commercial Cibil';
		SET @isUtilisationPercentageMatch = TRUE;
	ELSE
		SET @utilPercntValFS = (((@avrgBalanceFromBank/@noOfMonths)/@existingLimits) * 100);
		SET @isUtilisationPercentageMatchQuery:=CONCAT('SELECT CASE WHEN min_utilisation_percentage=0 && max_utilisation_percentage=100 THEN 1 WHEN min_utilisation_percentage=0 THEN (',@utilPercntValFS,' <= (SELECT max_utilisation_percentage FROM ',@fpTableName ,' WHERE fp_product_id =',@fpProdId,')) WHEN max_utilisation_percentage=100 THEN (',@utilPercntValFS,' >= (SELECT min_utilisation_percentage FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) ELSE (',@utilPercntValFS,' BETWEEN (SELECT min_utilisation_percentage FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,') AND (SELECT max_utilisation_percentage FROM ', @fpTableName,' WHERE fp_product_id =',@fpProdId,')) END INTO @isUtilisationPercentageMatch FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
		PREPARE stmt FROM @isUtilisationPercentageMatchQuery;
		EXECUTE stmt;
		SET @utilPercntValFS = CONCAT(@utilPercntValFS,'%');
	END IF;
	SET @fpMinUtilPercntValQuery:=CONCAT('SELECT min_utilisation_percentage INTO @minUtilPercntValFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @fpMinUtilPercntValQuery;
	EXECUTE stmt;
	SET @fpMaxUtilPercntValQuery:=CONCAT('SELECT max_utilisation_percentage INTO @maxUtilPercntValFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @fpMaxUtilPercntValQuery;
	EXECUTE stmt;
	IF (@minUtilPercntValFP IS NULL) THEN
		SET @minUtilPercntValFP = '-';
	ELSE
		SET @minUtilPercntValFP = ROUND(@minUtilPercntValFP,0);
	END IF;
	IF (@maxUtilPercntValFP IS NULL) THEN
		SET @maxUtilPercntValFP = '-';
	ELSE
		SET @maxUtilPercntValFP = ROUND(@maxUtilPercntValFP,0);
	END IF;
	SET @utilPercntValFP=CONCAT(@minUtilPercntValFP,'% ',@maxUtilPercntValFP);
ELSE
	SET @isUtilisationPercentageMatch=FALSE;
	SET @utilPercntValFP='-';
	SET @isUtilisationPercentageDisplay=FALSE;
	SET @isUtilisationPercentageMandatory=FALSE;
END IF;
-- ===================================== END Utilization percentage Variable ====================================  --
-- ===================================== Credit Summery Variable =====================================  --
IF (@isCreditSummationDisplay = TRUE) THEN
	SET @GSTINQuery:=CONCAT('SELECT gstin INTO @gstin FROM connect.connect_log WHERE application_id=',@applicationId ,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @GSTINQuery;
	EXECUTE stmt;
	SET @totalCreditQuery:=CONCAT('SELECT total_credit INTO @bsTotalCredit FROM statement_analyzer.matches_data WHERE application_id=',@applicationId);
	PREPARE stmt FROM @totalCreditQuery;
	EXECUTE stmt;
	SET @bsNoOfMonthsVal = @noOfMonths;
	IF (@gstin IS NOT NULL) THEN
		SET @gstin = CONCAT('\'',@gstin,'\'');
		SET @projectedSalesQuery:=CONCAT('SELECT historical_sales INTO @hitSales FROM gst.gst_mapping_table WHERE gstin =',@gstin,' ORDER BY id DESC LIMIT 1');
		PREPARE stmt FROM @projectedSalesQuery;
		EXECUTE stmt;
		IF (@hitSales IS NULL) THEN
		 	SET @projectedSalesQuery:=CONCAT('SELECT projected_sales AS projectedSales FROM gst.gst_mapping_table WHERE gstin =',@gstin,' ORDER BY id DESC LIMIT 1');
		 	PREPARE stmt FROM @projectedSalesQuery;
		 	EXECUTE stmt;
		 	SET @hitSales =@projectedSales;
		END IF;
	END IF;
	IF (@bsTotalCredit IS NULL OR @bsTotalCredit =0 OR @bsNoOfMonthsVal IS NULL OR @bsNoOfMonthsVal =0) THEN
		SET @creditSummationValFS='Credit summation from bank account not found';
		SET @isCreditSummationMatched=FALSE;
	ELSEIF (@hitSales IS NULL OR @hitSales = 0)THEN
		SET @creditSummationValFS='GST details not fetched';
		SET @isCreditSummationMatched=FALSE;
	ELSEIF ((@hitSales/12) = 0 OR (@bsTotalCredit/@bsNoOfMonthsVal=0))THEN
		SET @creditSummationValFS='GST details not fetched';
		SET @isCreditSummationMatched=FALSE;
	ELSE
		SET @calcBsCreditVal =@bsTotalCredit/@bsNoOfMonthsVal;
		SET @calcProjectedSalesVal=@hitSales/12;
		SET @creditSummationValFS = (@calcBsCreditVal/@calcProjectedSalesVal) * 100;
		SET @creditSummationValFS = ROUND(@creditSummationValFS,2);
		SET @isCreditSummationMatchedQuery:= CONCAT('SELECT CASE WHEN min_credit_summation=0 && max_credit_summation=100 THEN 1 WHEN min_credit_summation=0 THEN (',@creditSummationValFS,' <= (SELECT max_credit_summation FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) WHEN max_credit_summation=100 THEN (',@creditSummationValFS,' >= (SELECT min_credit_summation FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) ELSE (',@creditSummationValFS,' BETWEEN (SELECT min_credit_summation FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,') AND (SELECT max_credit_summation FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) END INTO @isCreditSummationMatched FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
		PREPARE stmt FROM @isCreditSummationMatchedQuery;
		EXECUTE stmt;
		SET @creditSummationValFS = CONCAT(@creditSummationValFS,'%');
	END IF;
	SET @minMaxCreditSummationQuery:=CONCAT('SELECT min_credit_summation,max_credit_summation INTO @minCreditSummationValFP,@maxCreditSummationValFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @minMaxCreditSummationQuery;
	EXECUTE stmt;
	IF (@minCreditSummationValFP IS NULL) THEN
		SET @minCreditSummationValFP = '-';
	ELSE
		SET @minCreditSummationValFP = ROUND(@minCreditSummationValFP,0);
	END IF;
	IF (@maxCreditSummationValFP IS NULL) THEN
		SET @maxCreditSummationValFP = '-';
	ELSE
		SET @maxCreditSummationValFP = ROUND(@maxCreditSummationValFP,0);
	END IF;
	SET @creditSummationValFP = CONCAT(@minCreditSummationValFP,'% ',@maxCreditSummationValFP,'%');
ELSE
	SET @isCreditSummationDisplay=FALSE;
	SET @isCreditSummationMandatory=FALSE;
	SET @isCreditSummationMatched=FALSE;
	SET @creditSummationValFP='-';
END IF;
-- ===================================== END Credit Summery Variable ====================================  --
-- ===================================== Start Collateral Coverage Variable =====================================  --
IF (@isCollateralCoverageNewDisplay = TRUE) THEN
	SET @collateralCoverageNewQuery:=CONCAT('SELECT have_collateral_security,collateral_security_amt INTO @haveCollateralSecurity,@collateralAmt from fs_corporate_primary_details WHERE application_id =',@applicationId);
	PREPARE stmt FROM @collateralCoverageNewQuery;
	EXECUTE stmt;
	IF (@haveCollateralSecurity IS NOT NULL AND @collateralAmt IS NOT NULL AND @haveCollateralSecurity=TRUE) THEN
		SET @loanAmtQuery:=CONCAT('SELECT max_loan_amount INTO @loanAmout FROM application_product_audit WHERE application_id =',@applicationId,' AND fp_product_id =',@fpProdId);
		PREPARE stmt FROM @loanAmtQuery;
		EXECUTE stmt;
		-- SELECT @collateralAmt,@loanAmout;
		SET @collateralCoverageNewValFS= ((@collateralAmt / @loanAmout)*100);
		SET @collateralCoverageNewValFS = ROUND(@collateralCoverageNewValFS,2);
		SET @isCollateralCoverageMatchedQuery:=CONCAT('SELECT CASE WHEN max_collateral_coverage=100 THEN (',@collateralCoverageNewValFS,' >= (SELECT min_collateral_coverage FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) ELSE (',@collateralCoverageNewValFS,' BETWEEN (SELECT min_collateral_coverage FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,') AND (SELECT max_collateral_coverage FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) END INTO @isCollateralCoverageNewMatched FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId);
		PREPARE stmt FROM @isCollateralCoverageMatchedQuery;
		EXECUTE stmt;
		SET @minMaxCollateralCoverageQuery:=CONCAT('SELECT min_collateral_coverage,max_collateral_coverage INTO @minCollateralCoverageNewValFP,@maxCollateralCoverageNewValFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
		PREPARE stmt FROM @minMaxCollateralCoverageQuery;
		EXECUTE stmt;
		IF (@minCollateralCoverageNewValFP IS NULL) THEN
			SET @minCollateralCoverageNewValFP='-';
		ELSE
			SET @minCollateralCoverageNewValFP=ROUND(@minCollateralCoverageNewValFP,0);
		END IF;
		IF (@maxCollateralCoverageNewValFP IS NULL) THEN
			SET @maxCollateralCoverageNewValFP='-';
		ELSE
			SET @maxCollateralCoverageNewValFP=ROUND(@maxCollateralCoverageNewValFP,0);
		END IF;
		SET @collateralCoverageNewValFS=CONCAT(@collateralCoverageNewValFS,'%');
		SET @collateralCoverageNewValFP=CONCAT(@minCollateralCoverageNewValFP,'% ',@maxCollateralCoverageNewValFP,'%');
	ELSE
		SET @isCollateralCoverageNewMatched = FALSE;
	END IF;
ELSE
	SET @isCollateralCoverageNewDisplay=FALSE;
	SET @isCollateralCoverageNewMandatory=FALSE;
	SET @isCollateralCoverageNewMatched=FALSE;
	SET @collateralCoverageNewValFP='-';
END IF;
-- ===================================== END Collateral Coverage Variable ====================================  --
-- ------------------------------------------------------------ End Recalculation ------------------------------------------------------------
-- ===================================== Start Display Values Of Variable =====================================  --
INSERT INTO `application_product_match_data`
SELECT
	  @id
	 -- ,@gstin,@bsNoOfMonthsVal,@bsTotalCredit,@calcBsCreditVal,@hitSales,@calcProjectedSalesVal,@calcBsCreditVal,@calcProjectedSalesVal,@creditSummationValFS
	 ,@applicationId,@fpProdId,@loanArrangementId
	 ,@minTotalLoanAmountQuery,@existingLoanAmountQuery,@maxTotalLoanQuery,@minAdditionalLoanAmountQuery,@additionalLoanAmountQuery,@maxAdditionlLoanAmountQuery
	 ,@productId,@fpTableName,@itrYear
 	 ,@isTolTnwMatch,@tolTnwMatchMaxFS,@tolTnwMatchMinFP,@tolTnwMatchMaxFP -- TOL TNW
 	 ,@isDebtEquityMatch,@debtEquityValFS,@debtEquityValMinFP,@debtEquityValMaxFP -- Debt Equity Ratio
 	 ,@isCustomerConcentrationMatch ,@customerConcentrationFS,@minCustomerConcentration,@maxCustomerConcentration-- Customer Concentration
 	 ,@isIndustrySectorMatch,@industryFS,@industryFP -- Industry Sector
 	 ,@isGeoGraphicalFocusMatch,@geoGraphicalFocusFS,@geoGraphicalFocusFP-- Geo Graphical Focus
  	 ,@isCurrentRatioMatch,@currentRatioFS,@currentRatioMinFP,@currentRatioMaxFP -- Current Ratio
  	 ,@isInterestCoverageRatioMatch,@interestCoverageRatioFS,@interestCoverageRatioMinFP,@interestCoverageRatioMaxFP -- Interest Coverage Ratio
  	 ,@isPastYearTurnOverMatch,@pastYearTurnOverFS,@pastYearTurnOverMinFP,@pastYearTurnOverMaxFP -- Past Year Turn Over
  	 ,@isAgeOfEstablishmentMatch,@ageOfEstablishmentFS,@ageOfEstablishmentMinFP,@ageOfEstablishmentMaxFP -- Age of Establishment
  	 ,@isProfitablityHistoryMatch,@profitablityHistoryFS,@profitablityHistoryFP -- Positive Profitablity History
  	 ,@isNetworthMatch,@networthFS,@networthFP   -- Networth
  	 ,@isChequeBounceLastOneMonthMatch,@chequeBounceLastOneMonthFS,@chequeBounceLastOneMonthMinFP,@chequeBounceLastOneMonthMaxFP -- Cheque Bounce Last One Month
  	 ,@isChequeBounceLastSixMonthMatch,@chequeBounceLastSixMonthFS,@chequeBounceLastSixMonthMinFP,@chequeBounceLastSixMonthMaxFP -- Cheque Bounce Last Six Month
  	 ,@isIndividualCibilMatch,@individualCibilFS,@individualCibilFP -- Individual CIBIL
   	 ,@isCommercialCibilMatch,@commercialCibilFS,@commercialCibilFP  -- Commercial Cibil
	 ,@isInvestmentSizeMatch,@investmentSizeFS,@investmentSizeMinFP,@investmentSizeMaxFP -- Investment Size
	 ,@minAdditionalLoanFP,@additionalLoanAmountFS,@maxAdditionalLoanFP -- Investment exiting and additional loan amt
	 ,@minTotalLoanFP,@existingLoanAmount,@maxTotalLoanFP -- Investment exiting and additional loan amt
	 ,@isTenureMatch,@tenureFS,@tenureMinFP,@tenureMaxFP -- Tenure
	 ,@isScoreMatch,@scoreFS,@scoreMinFP,@scoreMaxFP  -- Score
	 ,@isColleteralCoverageMatch,@colleteralCoverageFS,@colleteralCoverageMinFP,@colleteralCoverageMaxFP -- Colleteral Coverage
	 ,@isCgtmseCoverageMatch,@cgtmseCoverageFS,@cgtmseCoverageFP -- CGTMSE Coverage
	 ,@isMsmeFundingMatch,@msmeFundingFS,@msmeFundingFP -- MSME Funding
	 ,@isTurnOverMatch,@turnOverFS,@turnOverMinFP,@turnOverMaxFP -- Turn Over
	 ,@isGrossCashAccuralMatch,@grossCashAccuralFS,@grossCashAccuralMinFP,@grossCashAccuralMaxFP -- Gross Cash Accural
	 ,@isMaxDropInTurnoverMatch,@dropInTurnoverFS,@dropInTurnoverFP -- drop tun over
	 ,@isUtilisationPercentageMatch,@utilPercntValFS,@minUtilPercntValFP,@maxUtilPercntValFP,@utilPercntValFP -- Utilization Percentage
	 ,@isCreditSummationMatched,@creditSummationValFS,@minCreditSummationValFP,@maxCreditSummationValFP,@creditSummationValFP -- Credit Summation
	 ,@isCollateralCoverageNewMatched,@collateralCoverageNewValFS,@minCollateralCoverageNewValFP,@maxCollateralCoverageNewValFP,@collateralCoverageNewValFP -- Collateral Coverage
	 ,@isIndustrySectorDisplay,@isIndustrySectorMandatory
	 ,@ismentSizeDisplay,@isInvestmentSizeMandatory
	 ,@isTenureDisplay,@isTenureMandatory
	 ,@isGeographicalDisplay,@isGeographicalMandatory
	 ,@isCreditratingDisplay,@isCreditratingMandatory
	 ,@isEstablishmentDisplay,@isEstablishmentMandatory
	 ,@isProfitabilityHistoryDisplay,@isProfitabilityHistoryMandatory
	 ,@isPastYearTurnoverDisplay,@isPastYearTurnoverMandatory
	 ,@isDebtEquityDisplay,@isDebtEquityMandatory
	 ,@isCollateralDisplay,@isCollateralMandatory
	 ,@isNetworthDisplay,@isNetworthMandatory
	 ,@isCurrentratioDisplay,@isCurrentratioMandatory
	 ,@isInterestCoverageDisplay,@isInterestCoverageMandatory
	 ,@isTolTnwDisplay,@isTolTnwMandatory
	 ,@isTurnOverRatioDisplay,@isTurnOverRatioMandatory
	 ,@isGrossCashAccuralsRatioDisplay,@isGrossCashAccuralsRatioMandatory
	 ,@isCustomerConcentrationDisplay,@isCustomerConcentrationMandatory
	 ,@isRiskModelScoreDisplay,@isRiskModelScoreMandatory
	 ,@isChequeBouncedDisplay,@isChequeBouncedMandatory
	 ,@isChequeBouncedLastSixMonthsDisplay,@isChequeBouncedLastSixMonthsMandatory
	 ,@isIndividualCibilDisplay,@isIndividualCibilMandatory
	 ,@isCommercialCibilDisplay,@isCommercialCibilMandatory
	 ,@isCgtmseCoverageDisplay,@isCgtmseCoverageMandatory
	 ,@isMsmeFundingDisplay,@isMsmeFundingMandatory
	 ,@isMaxDropInTurnoverDisplay,@isMaxDropInTurnoverMandatory
	 ,@isUtilisationPercentageDisplay,@isUtilisationPercentageMandatory
	 ,@isCreditSummationDisplay,@isCreditSummationMandatory
	 ,@isCollateralCoverageNewDisplay,@isCollateralCoverageNewMandatory
	 ,@matchCalculatedDate,@matchDataStoredDate
	FROM DUAL;
-- ===================================== End Display Values Of Variable =====================================  --
END$$

DELIMITER ;