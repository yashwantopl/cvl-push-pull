/*
SQLyog Ultimate v11.5 (64 bit)
MySQL - 5.7.23-log : Database - loan_application
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`loan_application` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `loan_application`;

/* Procedure structure for procedure `checkValidBranchCodeFromReverse` */

/*!50003 DROP PROCEDURE IF EXISTS  `checkValidBranchCodeFromReverse` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `checkValidBranchCodeFromReverse`(IN applicationId BIGINT  , IN orgId BIGINT , IN branchCode VARCHAR(100),  OUT result VARCHAR(1000) )
BEGIN
	DECLARE inprincipleDate DATETIME ;
	DECLARE proposalBranchId BIGINT ;
	DECLARE masterBranchId BIGINT ;
	SET result = 'null' ;
	SET proposalBranchId = ( SELECT pd1.branch_id FROM `loan_application`.`proposal_details` pd1 
			WHERE pd1.application_id = applicationId AND pd1.user_org_id = orgId AND pd1.is_active = TRUE );
	SET masterBranchId = ( SELECT bm.id FROM `users`.`branch_master` bm 
			WHERE bm.code =  branchCode AND bm.org_id = orgId AND bm.is_active = TRUE );					
	IF (proposalBranchId IS NULL  ) THEN 
		SET result = 'Invalid application Id' ;	
	END IF;					
	IF  (proposalBranchId = masterBranchId )THEN
		SET result = 'Success';		
	ELSE		
		SET result = 'Invalid branch Code';		
	END IF;			
			
    END */$$
DELIMITER ;

/* Procedure structure for procedure `checkValidBranchCodeFromReverseApi` */

/*!50003 DROP PROCEDURE IF EXISTS  `checkValidBranchCodeFromReverseApi` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `checkValidBranchCodeFromReverseApi`(IN applicationId BIGINT  , IN orgId BIGINT , IN branchCode VARCHAR(100),  OUT result VARCHAR(1000) )
BEGIN
	DECLARE inprincipleDate DATETIME ;
	DECLARE proposalBranchId BIGINT ;
	DECLARE masterBranchId BIGINT ;
	SET result = 'null' ;
	SET proposalBranchId = ( SELECT pd1.branch_id FROM `loan_application`.`proposal_details` pd1 
			WHERE pd1.application_id = applicationId AND pd1.user_org_id = orgId AND pd1.is_active = TRUE );
	SET masterBranchId = ( SELECT bm.id FROM `users`.`branch_master` bm 
			WHERE bm.code =  branchCode AND bm.org_id = orgId AND bm.is_active = TRUE );					
	IF (proposalBranchId IS NULL  ) THEN 
		SET result = 'Invalid application Id' ;	
	END IF;					
	IF  (proposalBranchId = masterBranchId )THEN
		SET result = 'Success';		
	ELSE		
		SET result = 'Invalid branch Code';		
	END IF;			
			
    END */$$
DELIMITER ;

/* Procedure structure for procedure `confirmBranchIdByBranchCode` */

/*!50003 DROP PROCEDURE IF EXISTS  `confirmBranchIdByBranchCode` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `confirmBranchIdByBranchCode`(in applicationId BIGINT  , in orgId BIGINT , in branchCode VARCHAR(100), in sanctionDate DATETIME  , OUT result varchar(1000) )
BEGIN
	declare inprincipleDate datetime ;
	DECLARE proposalBranchId bigint ;
	DECLARE masterBranchId BIGINT ;
	SET result = 'null' ;
	set inprincipleDate = DATE((SELECT con.In_principle_date FROM connect.connect_log con 
	LEFT JOIN `loan_application`.`proposal_details` pd ON pd.`application_id` = con.`application_id` AND pd.id = con.proposal_id AND pd.`is_active` = TRUE 	
	WHERE con.stage_id IN (7, 9  )  AND con.application_id = applicationId  and pd.user_org_id = orgId and con.is_active = TRUE 
	GROUP BY pd.`user_org_id` ORDER BY con.id  DESC ));  	
	SET proposalBranchId = ( SELECT pd1.branch_id FROM `loan_application`.`proposal_details` pd1 
			WHERE pd1.application_id = applicationId AND pd1.user_org_id = orgId AND pd1.is_active = TRUE );
	IF (proposalBranchId is NULL  ) THEN 
		SET result = 'Invalid application Id' ;	
	elseif (( inprincipleDate <= sanctionDate ) and ( sanctionDate <= now()) ) then
		
		set masterBranchId = ( select bm.id from `users`.`branch_master` bm 
		where bm.code =  branchCode and bm.org_id = orgId and bm.is_active = true );
				
		if (proposalBranchId = masterBranchId ) then
			SET result = 'Success';		
		ELSE		
			SET result = 'Invalid branch Code';		
		End if;			
	else
		SET result = 'Invalid Date !!! Please enter valid date of sanction date is always greater then inprinciple date and less then pull date or equal to till date';		
	END IF;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `GenerateRegReport` */

/*!50003 DROP PROCEDURE IF EXISTS  `GenerateRegReport` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `GenerateRegReport`()
BEGIN
SELECT * FROM `loan_application`.`proposal_details` WHERE `is_active` =TRUE;
SELECT COUNT(ap.message) AS counter,ap.message FROM `loan_application`.`application_product_audit` ap WHERE ap.application_id IN
(SELECT DISTINCT(app.application_id) FROM `loan_application`.`application_product_audit` app,`connect`.`connect_log` o 
WHERE app.application_id = o.application_id AND o.status = 6 AND app.fp_product_id IN
 (SELECT fp.`fp_product_id` FROM `loan_application`.`fp_product_master` fp WHERE fp.`user_org_id` = 17))
AND ap.message IS NOT NULL AND ap.message != 'MANUALY ADDED' GROUP BY ap.message ORDER BY counter DESC;
SELECT DISTINCT(pr.`application_id`) FROM `loan_application`.`ineligible_proposal_details` pr WHERE pr.`user_org_id` = 19;
SELECT COUNT(*) AS counter,pr.branch_id,(SELECT bm.name FROM `users`.`branch_master` bm WHERE bm.id = pr.branch_id) AS branchName 
FROM `loan_application`.`ineligible_proposal_details` pr WHERE pr.`user_org_id` = 17 GROUP BY pr.`branch_id` ORDER BY counter DESC;
  
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spAdminPanelChartCount` */

/*!50003 DROP PROCEDURE IF EXISTS  `spAdminPanelChartCount` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spAdminPanelChartCount`(IN fromDate DATETIME,IN toDate DATETIME,IN businessTypeId INT,OUT result VARCHAR(2000))
BEGIN
	--  ---------------------- VARIABLE DECLARATION -----------------------------------------------
	DECLARE vb_pending_mcq INTEGER; -- (1)
	DECLARE vb_pending_gst INTEGER; -- (2)
	DECLARE vb_pending_itr INTEGER; -- (3)
	DECLARE vb_pending_bs INTEGER; -- (4)
	DECLARE vb_pending_director INTEGER; -- (5)
	DECLARE vb_pending_oneform INTEGER; -- (6)
	DECLARE vb_total_in_eligible INTEGER; -- (7)
	DECLARE vb_pending_matches INTEGER; -- (8)
	DECLARE vb_pending_payment INTEGER; -- (9)
	DECLARE vb_total_in_principle INTEGER; -- (10)
	
	DECLARE vb_cleared_gst INTEGER; -- (11)
	DECLARE vb_cleared_itr INTEGER; -- (12)
	DECLARE vb_cleared_bs INTEGER; -- (13)
	DECLARE vb_cleared_director INTEGER; -- (14)
	DECLARE vb_cleared_oneform INTEGER; -- (15)
	DECLARE vb_cleared_matches INTEGER; -- (16)
	DECLARE vb_cleared_payment INTEGER; -- (17)
	
	DECLARE vb_bk_sp_new_in_principle INTEGER; -- (18)
	DECLARE vb_bk_sp_renewal_in_principle INTEGER; -- (19)
	DECLARE vb_mk_pl_in_principle INTEGER; -- (20)
	DECLARE vb_bk_sp_new_in_eligible INTEGER; -- (21)
	DECLARE vb_bk_sp_renewal_in_eligible INTEGER; -- (22)
	DECLARE vb_mk_pl_in_eligible INTEGER; -- (23)
	
	--   ---------------------- START QUERY -----------------------------------------------
	
	SELECT 
	SUM((CASE WHEN (con.`stage_id` = 8) THEN 1 ELSE 0 END)), -- PENDING MCQ (1)
	SUM((CASE WHEN (con.`stage_id` = 0) THEN 1 ELSE 0 END)), -- PENDING GST (2)
	SUM((CASE WHEN (con.`stage_id` = 1) THEN 1 ELSE 0 END)), -- PENDING ITR (3)
	SUM((CASE WHEN (con.`stage_id` = 2) THEN 1 ELSE 0 END)), -- PENDING BS (4)
	SUM((CASE WHEN (con.`stage_id` = 3) THEN 1 ELSE 0 END)), -- PENDING DIRECTOR (5)
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 3) THEN 1 ELSE 0 END)), -- PENDING ONEFORM (6)
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6) THEN 1 ELSE 0 END)), -- NOT ELIGBLE (7)
	SUM((CASE WHEN (con.`stage_id` = 5) THEN 1 ELSE 0 END)), -- PENDING MATCHES (8)
	SUM((CASE WHEN (con.`stage_id` = 6) THEN 1 ELSE 0 END)), -- PENDING PAYMENT (9)
	SUM((CASE WHEN (con.`stage_id` IN (7,9)) THEN 1 ELSE 0 END)), -- IN PRINCIPLE (10)
	SUM((CASE WHEN ((con.`stage_id` > 0) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED GST  (11)
	SUM((CASE WHEN ((con.`stage_id` > 1) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED ITR (12)
	SUM((CASE WHEN ((con.`stage_id` > 2) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED BANK STAMENT (13)
	SUM((CASE WHEN ((con.`stage_id` > 3) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED DIRECTOR (14)
	SUM((CASE WHEN ((con.`stage_id` > 4) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED ONEFROM (15)
	-- SUM((CASE WHEN (((con.`stage_id` > 4) AND (con.`stage_id` <> 8)) OR ((con.`stage_id` = 4) AND (con.`status` = 6))) THEN 1 ELSE 0 END)), -- CLEARED ONEFORM (15)
	SUM((CASE WHEN ((con.`stage_id` > 5) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED MATCHES (16)
	SUM((CASE WHEN ((con.`stage_id` > 6) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)) -- CLEARED PAYMENT (17)  
	INTO vb_pending_mcq,vb_pending_gst,vb_pending_itr,vb_pending_bs,vb_pending_director,
	vb_pending_oneform,vb_total_in_eligible,vb_pending_matches,vb_pending_payment,vb_total_in_principle,vb_cleared_gst,vb_cleared_itr,vb_cleared_bs,vb_cleared_director,vb_cleared_oneform,
	vb_cleared_matches,vb_cleared_payment
	FROM `connect`.`connect_log` con WHERE con.`modified_date` BETWEEN fromDate AND toDate AND con.`business_type_id` = businessTypeId;
	
	SELECT 
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND (con.`wc_renewal_status` IS NULL OR con.`wc_renewal_status` = 1) AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific NEW In-Principle
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND con.`wc_renewal_status` = 2 AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific RENEWAL In-Principle
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND camp.id IS NULL) THEN 1 ELSE 0 END)), -- Market Place In-Principle
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND (con.`wc_renewal_status` IS NULL OR con.`wc_renewal_status` = 1) AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific NEW In-Eligible
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND con.`wc_renewal_status` = 2 AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)),-- Bank Specific RENEWAL In-Eligible
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND camp.id IS NULL) THEN 1 ELSE 0 END)) INTO -- Bank Specific RENEWAL In-Eligible
	vb_bk_sp_new_in_principle,vb_bk_sp_renewal_in_principle,vb_mk_pl_in_principle,vb_bk_sp_new_in_eligible,vb_bk_sp_renewal_in_eligible,vb_mk_pl_in_eligible -- Morket Place In-Eligible
	FROM connect.`connect_log` con 
	LEFT JOIN `users`.`campaign_details` camp
	ON con.user_id = camp.user_id AND camp.is_active = TRUE 
	where con.`modified_date` BETWEEN fromDate AND toDate AND con.`business_type_id` = businessTypeId;
	
	-- ---------------------- START CREATE JSON OBJECT -----------------------------------------------
	
	SET result = JSON_OBJECT('mcqPending',vb_pending_mcq,'gstPending',vb_pending_gst,'itrPending',vb_pending_itr,'bankStatementPending',vb_pending_bs,'directorPending',vb_pending_director
		,'oneformPending',vb_pending_oneform,'matchesPending',vb_pending_matches,'paymentPending',vb_pending_payment,'totalInprinciple',vb_total_in_principle,
		'totalNotEligble',vb_total_in_eligible,'gstCleared',vb_cleared_gst,'itrCleared',vb_cleared_itr,'bankStatementCleared',vb_cleared_bs,'directorCleared',vb_cleared_director
		,'oneformCleared',vb_cleared_oneform,'matchesCleared',vb_cleared_matches,'paymentCleared',vb_cleared_payment,
		'inpricipleNewBankSpecific',vb_bk_sp_new_in_principle,'inpricipleRenewal',vb_bk_sp_renewal_in_principle,'inpricipleNewMarket',vb_mk_pl_in_principle,
		'ineligibleNewBankSpecific',vb_bk_sp_new_in_eligible,'ineligibleRenewal',vb_bk_sp_renewal_in_eligible,'ineligibleNewMarketPlace',vb_mk_pl_in_eligible);	
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spAdminPanelCount` */

/*!50003 DROP PROCEDURE IF EXISTS  `spAdminPanelCount` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spAdminPanelCount`(IN fromDate DATETIME,IN toDate DATETIME,IN businessTypeId INT,OUT result VARCHAR(2000))
BEGIN
	--  ---------------------- VARIABLE DECLARATION -----------------------------------------------
	DECLARE vb_pending_mcq INTEGER; -- (1)
	DECLARE vb_pending_gst INTEGER; -- (2)
	DECLARE vb_pending_itr INTEGER; -- (3)
	DECLARE vb_pending_bs INTEGER; -- (4)
	DECLARE vb_pending_director INTEGER; -- (5)
	DECLARE vb_pending_oneform INTEGER; -- (6)
	DECLARE vb_total_in_eligible INTEGER; -- (7)
	DECLARE vb_pending_matches INTEGER; -- (8)
	DECLARE vb_pending_payment INTEGER; -- (9)
	DECLARE vb_total_in_principle INTEGER; -- (10)
	
	DECLARE vb_cleared_gst INTEGER; -- (11)
	DECLARE vb_cleared_itr INTEGER; -- (12)
	DECLARE vb_cleared_bs INTEGER; -- (13)
	DECLARE vb_cleared_director INTEGER; -- (14)
	DECLARE vb_cleared_oneform INTEGER; -- (15)
	DECLARE vb_cleared_matches INTEGER; -- (16)
	DECLARE vb_cleared_payment INTEGER; -- (17)
	
	DECLARE vb_bk_sp_new_in_principle INTEGER; -- (18)
	DECLARE vb_bk_sp_renewal_in_principle INTEGER; -- (19)
	DECLARE vb_mk_pl_in_principle INTEGER; -- (20)
	DECLARE vb_bk_sp_new_in_eligible INTEGER; -- (21)
	DECLARE vb_bk_sp_renewal_in_eligible INTEGER; -- (22)
	DECLARE vb_mk_pl_in_eligible INTEGER; -- (23)
	
	--   ---------------------- START QUERY -----------------------------------------------
	
	SELECT 
	SUM((CASE WHEN (con.`stage_id` = 8) THEN 1 ELSE 0 END)), -- PENDING MCQ (1)
	SUM((CASE WHEN (con.`stage_id` = 0) THEN 1 ELSE 0 END)), -- PENDING GST (2)
	SUM((CASE WHEN (con.`stage_id` = 1) THEN 1 ELSE 0 END)), -- PENDING ITR (3)
	SUM((CASE WHEN (con.`stage_id` = 2) THEN 1 ELSE 0 END)), -- PENDING BS (4)
	SUM((CASE WHEN (con.`stage_id` = 3) THEN 1 ELSE 0 END)), -- PENDING DIRECTOR (5)
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 3) THEN 1 ELSE 0 END)), -- PENDING ONEFORM (6)
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6) THEN 1 ELSE 0 END)), -- NOT ELIGBLE (7)
	SUM((CASE WHEN (con.`stage_id` = 5) THEN 1 ELSE 0 END)), -- PENDING MATCHES (8)
	SUM((CASE WHEN (con.`stage_id` = 6) THEN 1 ELSE 0 END)), -- PENDING PAYMENT (9)
	SUM((CASE WHEN (con.`stage_id` IN (7,9)) THEN 1 ELSE 0 END)), -- IN PRINCIPLE (10)
	SUM((CASE WHEN ((con.`stage_id` > 0) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED GST  (11)
	SUM((CASE WHEN ((con.`stage_id` > 1) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED ITR (12)
	SUM((CASE WHEN ((con.`stage_id` > 2) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED BANK STAMENT (13)
	SUM((CASE WHEN ((con.`stage_id` > 3) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED DIRECTOR (14)
	SUM((CASE WHEN ((con.`stage_id` > 4) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED ONEFROM (15)
	-- SUM((CASE WHEN (((con.`stage_id` > 4) AND (con.`stage_id` <> 8)) OR ((con.`stage_id` = 4) AND (con.`status` = 6))) THEN 1 ELSE 0 END)), -- CLEARED ONEFORM (15)
	SUM((CASE WHEN ((con.`stage_id` > 5) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)), -- CLEARED MATCHES (16)
	SUM((CASE WHEN ((con.`stage_id` > 6) AND (con.`stage_id` <> 8)) THEN 1 ELSE 0 END)) -- CLEARED PAYMENT (17)  
	INTO vb_pending_mcq,vb_pending_gst,vb_pending_itr,vb_pending_bs,vb_pending_director,
	vb_pending_oneform,vb_total_in_eligible,vb_pending_matches,vb_pending_payment,vb_total_in_principle,vb_cleared_gst,vb_cleared_itr,vb_cleared_bs,vb_cleared_director,vb_cleared_oneform,
	vb_cleared_matches,vb_cleared_payment
	FROM `connect`.`connect_log` con WHERE con.`modified_date` BETWEEN fromDate AND toDate AND con.`business_type_id` = businessTypeId;
	
	SELECT 
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND (con.`wc_renewal_status` IS NULL OR con.`wc_renewal_status` = 1) AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific NEW In-Principle
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND con.`wc_renewal_status` = 2 AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific RENEWAL In-Principle
	SUM((CASE WHEN (con.`stage_id` IN (7,9) AND camp.id IS NULL) THEN 1 ELSE 0 END)), -- Market Place In-Principle
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND (con.`wc_renewal_status` IS NULL OR con.`wc_renewal_status` = 1) AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)), -- Bank Specific NEW In-Eligible
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND con.`wc_renewal_status` = 2 AND camp.id IS NOT NULL) THEN 1 ELSE 0 END)),-- Bank Specific RENEWAL In-Eligible
	SUM((CASE WHEN (con.`stage_id` = 4 AND con.`status` = 6 AND camp.id IS NULL) THEN 1 ELSE 0 END)) INTO -- Bank Specific RENEWAL In-Eligible
	vb_bk_sp_new_in_principle,vb_bk_sp_renewal_in_principle,vb_mk_pl_in_principle,vb_bk_sp_new_in_eligible,vb_bk_sp_renewal_in_eligible,vb_mk_pl_in_eligible -- Morket Place In-Eligible
	FROM connect.`connect_log` con 
	LEFT JOIN `users`.`campaign_details` camp
	ON con.user_id = camp.user_id AND camp.is_active = TRUE 
	WHERE con.`modified_date` BETWEEN fromDate AND toDate AND con.`business_type_id` = businessTypeId;
	
	-- ---------------------- START CREATE JSON OBJECT -----------------------------------------------
	
	SET result = JSON_OBJECT('mcq',vb_pending_mcq,'gst',vb_pending_gst,'itr',vb_pending_itr,'bankStatement',vb_pending_bs,'director',vb_pending_director
		,'oneform',vb_pending_oneform,'matches',vb_pending_matches,'payment',vb_pending_payment,'totalInprinciple',vb_total_in_principle,
		'totalNoteligble',vb_total_in_eligible,'gstCleared',vb_cleared_gst,'itrCleared',vb_cleared_itr,'bankStatementCleared',vb_cleared_bs,'directorCleared',vb_cleared_director
		,'oneformCleared',vb_cleared_oneform,'matchesCleared',vb_cleared_matches,'paymentCleared',vb_cleared_payment,
		'getInpricipleNewBankSpecific',vb_bk_sp_new_in_principle,'getInpricipleRenewal',vb_bk_sp_renewal_in_principle,'getInpricipleNewMarket',vb_mk_pl_in_principle,
		'ineligibleNewBankSpecific',vb_bk_sp_new_in_eligible,'ineligibleRenewal',vb_bk_sp_renewal_in_eligible,'ineligibleNewMarketPlace',vb_mk_pl_in_eligible);	
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spCheckBeforeOfflineSanctioned` */

/*!50003 DROP PROCEDURE IF EXISTS  `spCheckBeforeOfflineSanctioned` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spCheckBeforeOfflineSanctioned`(IN appId INT,OUT result INTEGER)
BEGIN
	DECLARE vb_total_in_principle_count INTEGER;
	DECLARE vb_gstin_pan VARCHAR(50);
	DECLARE vb_sanctioned_count INTEGER;
	DECLARE vb_inprinciple_date_range INT;
	SET result = 0;
	
	SELECT SUBSTR(`gstin`,3,10) into vb_gstin_pan FROM connect.`connect_log` WHERE application_id = appId;
	SELECT CAST(`value` AS UNSIGNED) INTO vb_inprinciple_date_range FROM `loan_application`.`common_properties` WHERE `key` = 'CONNECT_MSME_INPRINCIPLE_DATE_RANGE';
	
	IF (vb_gstin_pan IS NOT NULL) THEN
		SELECT COUNT(*) INTO vb_total_in_principle_count FROM connect.`connect_log` WHERE SUBSTR(`gstin`,3,10) = vb_gstin_pan AND stage_id IN (7,9) AND created_date BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW();
		IF vb_total_in_principle_count > 0  THEN
			SET result = 1; -- ALL READY IN-PRICIPLE RECIEVED
		ELSE
			SELECT COUNT(*) INTO vb_sanctioned_count FROM `loan_application`.`ineligible_proposal_details` WHERE `is_sanctioned` = TRUE AND SUBSTR(`gstin`,3,10) = vb_gstin_pan AND is_active = TRUE AND created_date BETWEEN DATE_SUB(NOW(), INTERVAL vb_inprinciple_date_range DAY) AND NOW();
			IF vb_sanctioned_count > 0  THEN
				SET result = 2; -- ALL READY SANCTIONED
			ELSE
				SET result = 4; -- MOVE TO SANCTIONED STAGE
			END IF;
		END IF;
	else
		SET result = 0; -- NOT VALID APPLCIATION ID
	END IF;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spCheckGSTINStatus` */

/*!50003 DROP PROCEDURE IF EXISTS  `spCheckGSTINStatus` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spCheckGSTINStatus`(IN gstinNo VARCHAR(20))
BEGIN
	SELECT 
	CASE 
	WHEN a.stage_id IN(7,9) THEN 'IN PRINCIPLE' 
	WHEN (a.stage_id = 4 AND a.status = 6)  THEN 'NOT ELIGIBLE' 
	WHEN (a.stage_id IN(0,1,2,3,5,8))  THEN 'IN PROCESS' 
	WHEN (a.stage_id = 4 AND a.status = 3) THEN 'IN PROCESS' 
	ELSE 'NOT FOUND' 
	END AS result 
	FROM `connect`.`connect_log` a 
	WHERE a.gstin = gstinNo ORDER BY id DESC LIMIT 1;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchAdminEligibleAndNotEligibleList` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchAdminEligibleAndNotEligibleList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchAdminEligibleAndNotEligibleList`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
    
	DECLARE vb_org_id INT;
	
	SELECT 
	CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',con.`application_id`,'email',us.`email`,'mobile',us.`mobile`,'orgName',corpApp.`organisation_name`, 
	'stage',IF(con.`stage_id` = 4,'In Eligible','In Principle'), 
	'wcRenewalStatus',IF(con.wc_renewal_status = 2,'Renewal','New'),'connectDate',con.`modified_date`, 
	'loanAmount',corpPrim.`loan_amount`,
	'loanType',IF(corpPrim.`purpose_of_loan_id` = 1,'Term Loan','Working Capital'),'loanTypeId',corpPrim.`purpose_of_loan_id`, 'fpProductId',proposal.`fp_product_id`,'stageId',con.`stage_id`,'proposalStatus',promst.`display_name`,
	'isFromCamp',IF(camp.`id` IS NULL,'Market Place','Bank Specific'),'campCode',camp.`code`,'applicationCode',mast.`application_code`,
	'businesstype',IF(con.`business_type_id` = 1,'Existing Business',IF(con.`business_type_id` = 4,'Uniform',IF(con.`business_type_id` = 3,'Personal Loan','New to Business'))),
	'bankName',org.`organisation_name`,'campaignBank',orgCamp.`organisation_name`)) AS CHAR) AS JSON
	FROM connect.`connect_log` con  
	LEFT JOIN loan_application.`proposal_details` proposal ON proposal.`application_id` = con.`application_id` AND proposal.`is_active` = TRUE AND con.`proposal_id` = proposal.`id`  
	LEFT JOIN users.`users` us ON us.`user_id` = con.`user_id`  
	LEFT JOIN loan_application.`fs_corporate_applicant_details` corpApp ON corpApp.`application_id` = con.`application_id` AND corpApp.`proposal_mapping_id` IS NULL
	LEFT JOIN loan_application.`application_proposal_mapping` mast ON mast.`application_id` = con.`application_id` AND mast.`proposal_id` = proposal.`id`
	-- LEFT JOIN loan_application.`fs_loan_application_master` mast ON mast.`application_id` = con.`application_id` 
	LEFT JOIN loan_application.`fs_corporate_primary_details` corpPrim ON corpPrim.`application_id` = con.`application_id` 
	LEFT JOIN users.`user_organisation_master` org ON org.`user_org_id` = proposal.`user_org_id`
	LEFT JOIN loan_application.`proposal_status_master` promst ON promst.`id` = proposal.`proposal_status_id`
	LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
	LEFT JOIN users.`user_organisation_master` orgCamp ON orgCamp.`organisation_code` = camp.`code`
	WHERE (con.`stage_id` IN (7,9) OR (con.`stage_id` = 4 AND con.`status` = 6))  
	AND con.`modified_date` BETWEEN fromDate AND toDate;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminEligibleProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminEligibleProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminEligibleProposal`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	-- DECLARE @vb_query VARCHAR(2000);
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	-- SET vb_role_id = (SELECT `user_role_id` FROM users.`users`  WHERE `user_id` = userId);
	-- SET vb_branch_id = (SELECT `branch_id` FROM users.`users`  WHERE `user_id` = userId);
	
	SET @vb_query = CONCAT("SELECT pd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, cl.`modified_date`, pd.branch_id, 
		pd.el_amount, pd.el_tenure, pd.el_roi, pd.emi, pd.processing_fee, branch.name AS branchname, 
		branch.contact_person_name, branch.telephone_no, branch.contact_person_number, org.organisation_name, 
		lam.application_code, branch.code, branch.street_name, (SELECT state_name FROM `one_form`.`state` s 
		WHERE s.id = branch.state_id), (SELECT city_name FROM `one_form`.`city` c WHERE c.id = branch.city_id), branch.premises_no, 
		(SELECT product_id FROM `loan_application`.`fp_product_master` pm WHERE pm.fp_product_id = pd.fp_product_id), branch.contact_person_email, 
		(SELECT COUNT(id) FROM `users`.`campaign_details` cd WHERE cd.user_id = cl.user_id AND cd.is_active = TRUE),cl.`gstin`,
		product.`name` AS productName,product.`is_active` AS productActive,proStatus.`status` AS proposalStatus,pd.fp_product_id AS fpProductId,appcnt.`organisation_name` AS companyname,
		cl.`wc_renewal_status`
		FROM `loan_application`.`proposal_details` pd 
		LEFT JOIN `connect`.`connect_log` cl ON cl.application_id = pd.application_id AND cl.`proposal_id` = pd.id
		LEFT JOIN `loan_application`.`proposal_status_master` proStatus ON proStatus.`id` = pd.`proposal_status_id`  
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = pd.`fp_product_id` 
		LEFT JOIN `users`.`users` usr ON usr.user_id = cl.user_id 
		LEFT JOIN `users`.`fund_seeker_details` fs ON fs.user_id = usr.user_id 
		LEFT JOIN  `users`.`branch_master` branch ON branch.id = pd.branch_id 
		LEFT JOIN `users`.`user_organisation_master` org ON org.user_org_id = pd.user_org_id 
		LEFT JOIN loan_application.`application_proposal_mapping` lam ON lam.`application_id` = pd.`application_id` AND lam.`proposal_id` = pd.`id`
		LEFT JOIN `loan_application`.`fs_corporate_applicant_details` appcnt ON appcnt.application_id = pd.application_id AND appcnt.`proposal_mapping_id` = pd.`id`
		WHERE pd.user_org_id =", vb_org_id," AND pd.is_active = TRUE AND cl.stage_id IN (7,9) 
		AND (cl.`In_principle_date` BETWEEN '",fromDate,"' AND '",toDate,"') ");
	
	IF (vb_role_id = 8 OR vb_role_id = 9) THEN
		SET @vb_query = CONCAT(@vb_query," AND pd.branch_id = ",vb_branch_id, " ORDER BY pd.id DESC");
	ELSEIF vb_role_id = 5 THEN
		SET @vb_query = CONCAT(@vb_query," ORDER BY pd.id DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminHoldAndReject` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminHoldAndReject` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminHoldAndReject`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT,IN pageNumber INTEGER,IN totalCount INTEGER,IN branchId INT,IN isFromReport BOOLEAN,IN proposalType INTEGER)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_report_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',s.`application_id`, 
		'email',u.`email`,'mobile', u.`mobile`,'applicationCode', `loan`.`application_code`,'gstin', l.`gstin`,'organizationName', o.`organisation_name`, 
		'branchName',br.`name`,'branchCode', br.`code`,'branchAddress',br.`street_name`,'branchState', state.`state_name`, 
		'branchCity',city.`city_name`,'contactPersonName', br.`contact_person_name`,'contactPersonEmail', br.`contact_person_email`,'contactPersonNumber', br.`contact_person_number`, 
		'typeOfLoan',co.`purpose_of_loan_id`,'name', fs.`NAME`, 
		'isFromCampaign',(IF(ISNULL(cm.`code`),'false','true')), 'campaignCode',cm.`code`,'inPrincipleDate',l.`In_principle_date`,'loanApplicationDate',loan.`created_date`,
		'elAmount',s.`el_amount`,'roi',s.`el_roi`,'tenure',s.`el_tenure`,'processingFees',s.`processing_fee`,'proposalModifiedDate',s.`modified_date`,
		'remark',s.`reason`,'companyName',cpApp.`organisation_name`)) AS CHAR) AS JSON   
		FROM `loan_application`.`proposal_details` s
		LEFT JOIN `connect`.`connect_log` l ON l.`application_id` = s.`application_id` 
		LEFT JOIN loan_application.`fs_corporate_applicant_details` cpApp ON cpApp.application_id = s.application_id AND cpApp.`proposal_mapping_id` = s.`id`
		LEFT JOIN users.`users` u ON l.`user_id` = u.`user_id`  
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = s.`application_id` AND loan.`proposal_id` = s.`id`
		LEFT JOIN users.`user_organisation_master` o ON s.`user_org_id` = o.`user_org_id`  
		LEFT JOIN users.`branch_master` br ON br.`id` = s.`branch_id` 
		LEFT JOIN one_form.`state` state ON state.id = br.`state_id`
		LEFT JOIN `one_form`.`city` city ON city.`id` = br.`city_id`
		LEFT JOIN `loan_application`.`fs_corporate_primary_details` co ON co.`application_id` = s.`application_id`
		LEFT JOIN users.`fund_seeker_details` fs ON fs.`user_id` = l.`user_id` 
		LEFT JOIN users.`campaign_details` cm ON `cm`.`user_id` = u.`user_id` AND cm.`is_active` = TRUE 
		WHERE s.id IN ( ");
		
	SET @vb_sub_report_query = CONCAT("SELECT sll.`id` FROM `loan_application`.`proposal_details` sll WHERE sll.`is_active` = TRUE 
		AND sll.`modified_date`BETWEEN '",fromDate,"' AND '",toDate,"' AND sll.user_org_id = ",vb_org_id," AND sll.`proposal_status_id` =",proposalType);
		
	IF ((branchId IS NOT NULL) AND (branchId > 0)) THEN
		SET @vb_sub_report_query = CONCAT(@vb_sub_report_query," AND sll.`branch_id` = ",branchId);
	END IF; 
		
		
	IF (isFromReport = TRUE) THEN
		SET @vb_sub_report_query = CONCAT(@vb_sub_report_query," ORDER BY sll.`modified_date` DESC ");	
	ELSE 
		SET @vb_sub_report_query = CONCAT("SELECT * FROM ( ",@vb_sub_report_query," ORDER BY sll.`modified_date` DESC LIMIT ", pageNumber, ",", totalCount,") tmp");
	END IF; 
	SET @vb_report_query = CONCAT(@vb_report_query,@vb_sub_report_query,")");
	
	PREPARE stmt1 FROM @vb_report_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminHoldAndRejectCount` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminHoldAndRejectCount` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminHoldAndRejectCount`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT,IN branchId INT, IN proposalType INTEGER)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_report_query = CONCAT("SELECT COUNT(*) FROM `loan_application`.`proposal_details` s 
		WHERE s.`is_active` = TRUE  AND s.user_org_id =" , vb_org_id , "  
		AND s.`modified_date` BETWEEN '",fromDate,"' AND '",toDate,"'  
		AND s.`proposal_status_id` = ",proposalType);
		
	IF ((branchId IS NOT NULL) AND (branchId > 0)) THEN
		SET @vb_report_query = CONCAT(@vb_report_query," AND s.`branch_id` = ",branchId);
	END IF; 
		
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_report_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminNotEligibleBankSpecificProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminNotEligibleBankSpecificProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminNotEligibleBankSpecificProposal`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT)
BEGIN
    
	DECLARE vb_org_id INT;
	SELECT `user_org_id` INTO vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	SELECT con.`application_id`,con.`gstin`,us.`email`,us.`mobile`,fs.`NAME`,IF(con.`wc_renewal_status` = 2,'Renewal','New') AS `status`,corp.`organisation_name`,con.`modified_date`
	FROM connect.`connect_log` con
	LEFT JOIN users.`users` us 
	ON us.`user_id` = con.`user_id` 
	LEFT JOIN users.`fund_seeker_details` fs
	ON fs.`user_id` = con.`user_id` 
	LEFT JOIN `loan_application`.`fs_corporate_applicant_details` corp
	ON corp.`application_id` = con.`application_id`
	LEFT JOIN users.`campaign_details` camp 
	ON camp.`user_id` = con.`user_id`
	LEFT JOIN users.`user_organisation_master` org 
	ON org.`organisation_code` = camp.`code`
	WHERE con.`stage_id` = 4 AND con.`status` = 6
	AND camp.`is_active` = TRUE AND con.`modified_date` BETWEEN fromDate AND toDate
	AND org.`user_org_id` = vb_org_id;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminNotEligibleProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminNotEligibleProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminNotEligibleProposal`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT)
BEGIN
    
	DECLARE vb_org_id INT;
	SELECT `user_org_id` INTO vb_org_id FROM users.`users`  WHERE `user_id` = userId;
    
	SELECT con.user_id,con.application_id,us.sign_up_date,cpPri.loan_amount,camp.code,usr.user_org_id, 
	(IF(ISNULL(usr.user_org_id),0,1)) AS isFromCampaign,fsDetails.NAME,us.email,us.mobile, 
	(IF(ISNULL(inlg.user_org_id),0,1)) AS SelectManualBrach,con.modified_date,cpApp.`organisation_name`
	FROM connect.connect_log con 
	LEFT JOIN users.users us ON con.user_id = us.user_id  
	LEFT JOIN loan_application.fs_corporate_primary_details cpPri ON cpPri.application_id = con.application_id 
	LEFT JOIN loan_application.`fs_corporate_applicant_details` cpApp ON cpApp.application_id = con.application_id 
	LEFT JOIN users.campaign_details camp ON camp.user_id = con.user_id AND camp.`is_active` = TRUE 
	LEFT JOIN users.user_organisation_master usr ON usr.organisation_code=camp.code AND usr.user_org_id = vb_org_id
	LEFT JOIN users.fund_seeker_details fsDetails ON fsDetails.user_id = con.user_id  
	LEFT JOIN loan_application.ineligible_proposal_details inlg ON inlg.application_id = con.application_id 
	WHERE con.modified_date BETWEEN fromDate AND toDate AND con.business_type_id = 1 
	AND con.stage_id= 4 AND con.status= 6 GROUP BY con.application_id;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminOfflineProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminOfflineProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminOfflineProposal`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT ipd.application_id, cl.user_id, fs.name, usr.email, usr.mobile, ipd.created_date, ipd.branch_id, branch.name AS branchname, branch.contact_person_name, 
		branch.telephone_no, branch.contact_person_number, org.organisation_name, lam.application_code, branch.code, branch.street_name, 
		stat.`state_name`, cit.`city_name`, 
		branch.premises_no, branch.contact_person_email,coApp.organisation_name AS companyName,IF(cl.`wc_renewal_status` = 2,'Renewal','New') AS rewType,
		IF(camp.`id` IS NULL,'Market Place','Bank Specific') AS source
		FROM `loan_application`.`ineligible_proposal_details` ipd 
		LEFT JOIN `loan_application`.`fs_corporate_applicant_details` coApp ON coApp.application_id = ipd.application_id  
		LEFT JOIN `connect`.`connect_log` cl ON cl.application_id = ipd.application_id 
		LEFT JOIN `users`.`users` usr ON usr.user_id = cl.user_id
		LEFT JOIN `users`.`fund_seeker_details` fs ON fs.user_id = usr.user_id
		LEFT JOIN `users`.`branch_master` branch ON branch.id = ipd.branch_id
		LEFT JOIN `users`.`user_organisation_master` org ON org.user_org_id = ipd.user_org_id
		LEFT JOIN `loan_application`.`fs_loan_application_master` lam ON lam.application_id = ipd.application_id
		LEFT JOIN `one_form`.`state` stat ON stat.id = branch.`state_id`
		LEFT JOIN `one_form`.`city` cit ON cit.id = branch.`city_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = cl.`user_id` AND camp.`is_active` = TRUE
		WHERE ipd.is_active = TRUE and ipd.user_org_id =", vb_org_id," AND (ipd.created_date BETWEEN '",fromDate,"' AND '",toDate,"') ");
	
	IF (vb_role_id = 8 OR vb_role_id = 9) THEN
		SET @vb_query = CONCAT(@vb_query," AND ipd.branch_id = ",vb_branch_id, " GROUP BY ipd.application_id ORDER BY ipd.id DESC");
	ELSEIF vb_role_id = 5 THEN
		SET @vb_query = CONCAT(@vb_query," GROUP BY ipd.application_id ORDER BY ipd.id DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminSanctionedReport` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminSanctionedReport` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminSanctionedReport`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT,IN pageNumber INTEGER,IN totalCount INTEGER,IN branchId INT, IN isSanctionedFrom INTEGER,IN isFromReport BOOLEAN)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_report_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',s.`application_id`,'sanctionedAmount',s.`sanction_amount`,'sanctionedDate', s.`sanction_date`, 
		'tenure',s.`tenure`,'roi', s.`roi`,'processingFees', s.`processing_fee`,'remark', s.`remark`,'email', u.`email`,
		'mobile',u.`mobile`,'applicationCode', `loan`.`application_code`,'gstin', l.`gstin`,'orgId', s.org_id,'branchName', br.`name`, 
		'branchCode',br.`code`,'branchAddress', br.`street_name`,
		'branchState',state.`state_name`,'branchCity', city.`city_name`,'contactPersonName', br.`contact_person_name`,
		'contactPersonEmail',br.`contact_person_email`,'contactPersonNumber', br.`contact_person_number`,'typeOfLoan', co.`purpose_of_loan_id`, 
		'name',fs.`NAME`, 'isFromCampaign',(IF(ISNULL(cm.`code`),'false','true')), 
		'campaignCode',cm.`code`,'inPrincipleDate', l.`In_principle_date`,'loanApplicationDate',loan.`created_date`,
		'companyName',cpApp.`organisation_name`,
		'isSanctionedFrom',IF(s.`is_sanctioned_from` = 1,'Online',IF(s.`is_sanctioned_from` = 2,'Offline',IF(s.`is_sanctioned_from` = 3,'Reverse API','NA'))),
		'wcRenewalStatus', IF(l.`wc_renewal_status` = 2,'Renewal','New'))) AS CHAR) AS JSON     
		FROM `loan_application`.`sanction_detail` s 
		LEFT JOIN `connect`.`connect_log` l ON l.`application_id` = s.`application_id` 
		LEFT JOIN `loan_application`.`proposal_details` proposal ON proposal.`application_id` = s.`application_id` AND proposal.`user_org_id` = s.`org_id` 
		LEFT JOIN loan_application.`fs_corporate_applicant_details` cpApp ON cpApp.application_id = s.`application_id` AND cpApp.`proposal_mapping_id` = proposal.`id`
		LEFT JOIN users.`users` u ON l.`user_id` = u.`user_id` 
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = proposal.`application_id` AND loan.`proposal_id` = proposal.`id`
		LEFT JOIN users.`branch_master` br ON br.`id` = s.`branch` 
		LEFT JOIN one_form.`state` state ON state.id = br.`state_id` 
		LEFT JOIN `one_form`.`city` city ON city.`id` = br.`city_id` 
		LEFT JOIN `loan_application`.`fs_corporate_primary_details` co ON co.`application_id` = s.`application_id` 
		LEFT JOIN users.`fund_seeker_details` fs ON fs.`user_id` = l.`user_id`  
		LEFT JOIN users.`campaign_details` cm ON `cm`.`user_id` = u.`user_id` AND cm.`is_active` = TRUE  
		WHERE (proposal.id IS NULL OR ((proposal.`proposal_status_id` IS NULL OR proposal.`proposal_status_id` NOT IN (3,4)) AND proposal.`is_active` = TRUE)) AND s.id IN ( ");
	
	SET @vb_sub_report_query = CONCAT("SELECT sll.`id` FROM `loan_application`.`sanction_detail` sll WHERE sll.`is_active` = TRUE 
		AND sll.`created_date`BETWEEN '",fromDate,"' AND '",toDate,"' AND sll.org_id = ",vb_org_id);
		
	IF ((branchId IS NOT NULL) AND (branchId > 0)) THEN
		SET @vb_sub_report_query = CONCAT(@vb_sub_report_query," AND sll.`branch` = ",branchId);
	END IF; 
		
	IF ((isSanctionedFrom IS NOT NULL) AND (isSanctionedFrom > 0)) THEN
		SET @vb_sub_report_query = CONCAT(@vb_sub_report_query," AND sll.`is_sanctioned_from` = ",isSanctionedFrom);
	END IF; 
	
		
	IF (isFromReport = TRUE) THEN
		SET @vb_sub_report_query = CONCAT(@vb_sub_report_query,"  ORDER BY sll.`sanction_date` DESC ");	
	ELSE 
		SET @vb_sub_report_query = CONCAT("SELECT * FROM ( ",@vb_sub_report_query," ORDER BY sll.`sanction_date` DESC LIMIT ", pageNumber, ",", totalCount,") tmp");
	END IF; 
	
	SET @vb_report_query = CONCAT(@vb_report_query,@vb_sub_report_query,")");
	
	PREPARE stmt1 FROM @vb_report_query;
	EXECUTE stmt1;
	-- select @vb_report_query;
	-- select @vb_query from dual;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminSanctionedReportCount` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminSanctionedReportCount` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminSanctionedReportCount`(IN fromDate DATETIME,IN toDate DATETIME,IN userId INT,IN branchId INT, IN isSanctionedFrom INTEGER)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_report_query = CONCAT("SELECT COUNT(*) FROM `loan_application`.`sanction_detail` s  
		LEFT JOIN loan_application.`proposal_details` proposal ON proposal.`application_id` = s.`application_id` AND proposal.`user_org_id` = s.`org_id` 
		WHERE s.`is_active` = TRUE AND s.`created_date` BETWEEN '",fromDate,"' AND '",toDate,"' 
		AND (proposal.id IS NULL OR (proposal.`proposal_status_id` NOT IN (3,4) AND proposal.`is_active` = TRUE))
		AND s.org_id = ",vb_org_id);
		
	IF ((branchId IS NOT NULL) AND (branchId > 0)) THEN
		SET @vb_report_query = CONCAT(@vb_report_query," AND s.`branch` = ",branchId);
	END IF; 
		
	IF ((isSanctionedFrom IS NOT NULL) AND (isSanctionedFrom > 0)) THEN
		SET @vb_report_query = CONCAT(@vb_report_query," AND s.`is_sanctioned_from` = ",isSanctionedFrom);
	END IF; 
		
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_report_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBankAdminUserList` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBankAdminUserList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBankAdminUserList`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT us.`user_id`,us.`email`,us.`mobile`,us.`sign_up_date`,us.`is_active`,bh.`name`,bh.`code`,ct.`city_name`,st.`state_name`,role.`display_name`
		FROM users.`users` us 
		LEFT JOIN users.`user_role_master` role ON role.`role_id` = us.user_role_id
		LEFT JOIN users.`branch_master` bh ON bh.`id` = us.`branch_id`
		LEFT JOIN one_form.`city` ct ON ct.`id` = bh.`city_id`
		LEFT JOIN one_form.`state` st ON st.`id` = bh.`state_id`
		WHERE us.`user_org_id` = ", vb_org_id," AND us.`user_role_id` IN (5,8,9,12,13,14) and us.`user_role_id` NOT IN (10,11) AND us.`user_type_id` = 2");
	
	IF (vb_role_id = 5 OR vb_role_id = 10 OR vb_role_id = 11) THEN
		SET @vb_query = CONCAT(@vb_query," ORDER BY us.user_id DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableBANKSTATEMENTCallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableBANKSTATEMENTCallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableBANKSTATEMENTCallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(mlg.id IS NOT NULL,'Bank Statement',''),
'provider',IF(mlg.id IS NOT NULL,'PERFIOS',''),
'orgName',app.`organisation_name`,
'applicationId',mlg.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',app.`gstin`,
'requestType',IF(mlg.id IS NOT NULL,mlg.`api_type`,''),
'status',IF((mlg.status != '200') AND (mlg.status IS NOT NULL),'FAILED', IF(mlg.status IS NOT NULL,'SUCCESS', '')),
'failureReason',mlg.`message`,
'createdDate',mlg.`created_date`,
'modifiedDate',mlg.`modified_date`,
'dates',JSON_OBJECT('StartTime',NULL,'ReceiveTime',NULL,'Created_date',mlg.created_date,'Modified_Date',mlg.modified_date)
)
) AS CHAR) AS JSON
FROM `statement_analyzer`.`prefious_log_history` mlg
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON mlg.`application_id` = app.`application_id`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON mlg.`application_id` = lm.`application_id`
WHERE (mlg.`created_date` BETWEEN fromDate AND toDate) AND UNCOMPRESS(mlg.request) LIKE '%status=COMPLETED%';
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableCGTMSECallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableCGTMSECallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableCGTMSECallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(mlg.id IS NOT NULL,'CGTMSE',''),
'provider',IF(mlg.id IS NOT NULL,'SIDBI',''),
'orgName',app.`organisation_name`,
'applicationId',mlg.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',app.`gstin`,
'requestType',IF(mlg.id IS NOT NULL,'CGTMSE',''),
'status',
IF((mlg.STATUS != 'S') AND (mlg.STATUS IS NOT NULL),'FAILED', IF(mlg.STATUS IS NOT NULL,'SUCCESS', '')),
'failureReason',
(CASE 
	WHEN mlg.STATUS = 'F2' THEN 'CONNECTION ISSUE'
	WHEN mlg.STATUS = 'F' THEN 'Third Party Service Error'
	ELSE ''
END)
,
'createdDate',mlg.`created_date`,
'modifiedDate',mlg.`modified_date`,
'dates',JSON_OBJECT('StartTime',NULL,'ReceiveTime',NULL,'Created_date',mlg.created_date,'Modified_Date',mlg.modified_date)
)
) AS CHAR) AS JSON
FROM third_party.`cgtmse_logging` mlg
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON mlg.`application_id` = app.`application_id`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON mlg.`application_id` = lm.`application_id`
WHERE (mlg.`created_date` BETWEEN fromDate AND toDate) AND mlg.STATUS = 'S';
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableCIBILCallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableCIBILCallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableCIBILCallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(lg.`cibil_id` IS NOT NULL,'CIBIL',''),
'provider',IF((lg.provider IS NULL or lg.provider = 1),'TransUnion','Experian'),
'orgName',app.`organisation_name`,
'applicationId',lg.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',app.`gstin`,
'requestType',clg.`request_type`,
'status',clg.`status`,
'failureReason',clg.`failure_reason`,
'apiOrg',clg.`org_id`,
'bankName',org.`display_org_name`,
'createdDate',lg.`created_date`,
'modifiedDate',lg.`updated_date`,
'dates',JSON_OBJECT('StartTime',NULL,'ReceiveTime',NULL,'Created_date',lg.created_date,'Modified_Date',lg.updated_date)
)
) AS CHAR) AS JSON
FROM `cibil`.`cibil_audit_log_details` clg
INNER JOIN  `cibil`.`cibil_mstr` lg ON clg.`cibil_id` = lg.`cibil_id` AND clg.status = 'SUCCESS' AND clg.request_type IN ('MSME_COMPANY','MSME_INDIVIDUAL','MSME_INDIVIDUAL_RETRIVE_DOCUMENT_ID','MSME_INDIVIDUAL_DOWNLOAD_DOCUMENT',
'MSME_ASSOCIATION_OF_PERSONS','MSME_FIRM','MSME_GOVERMENT','MSME_PUBLIC_LIMITED','MSME_TRUST','MSME_ASSOCIATION_OF_PERSONS','MSME_PROPRIETORSHIP','MSME_HINDU_UNDIVIDED_FAMILY','MSME_NOT_CLASSIFIED','MSME_HUF')
and lg.is_active = true
LEFT JOIN `connect`.`connect_log` ccl ON lg.application_id = ccl.application_id
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON lg.`application_id` = app.`application_id`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON lg.`application_id` = lm.`application_id`
LEFT JOIN `users`.`user_organisation_master` org ON org.`user_org_id` = clg.`org_id`
WHERE (lg.`created_date` BETWEEN fromDate AND toDate);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableGSTCallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableGSTCallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableGSTCallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(gstr.`gst_service_provider_id` IS NOT NULL,'GST',''),
'provider',CASE gstr.`gst_service_provider_id` WHEN 3 THEN "GST-Vayana" WHEN 1 THEN 'Cygnet' ELSE "" END,
'orgName',app.`organisation_name`,
'applicationId',lm.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',gstr.`gstin`,
'requestType',gstr.`request_type`,
'status',IF((gstr.response_code != '200') AND (gstr.response_code IS NOT NULL),'FAILED', IF(gstr.response_code IS NOT NULL,'SUCCESS', '')),
'failureReason',gstr.response,
'createdDate',gstr.`created_date`,
'dates',JSON_OBJECT('StartTime',NULL,'ReceiveTime',NULL,'Created_date',gstr.created_date,'Modified_Date',NULL)
)
) AS CHAR) AS JSON
FROM `gst`.`gst_data_call_record` gstr
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON gstr.`gstin` = app.`gstin`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON app.`application_id` = lm.`application_id`
WHERE (gstr.`created_date` BETWEEN fromDate AND toDate);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableHUNTERCallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableHUNTERCallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableHUNTERCallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(mlg.id IS NOT NULL,'Hunter',''),
'provider',IF(mlg.id IS NOT NULL,'Experian Hunter',''),
'orgName',app.`organisation_name`,
'applicationId',mlg.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',app.`gstin`,
'requestType',mlg.`product_type`,
'status',IF((mlg.STATUS != 'S') AND (mlg.STATUS IS NOT NULL),'FAILED', IF(mlg.STATUS IS NOT NULL,'SUCCESS', '')),
'failureReason',mlg.message,
'createdDate',mlg.`created_date`,
'modifiedDate',mlg.`modified_date`,
'dates',JSON_OBJECT('StartTime',NULL,'ReceiveTime',NULL,'Created_date',mlg.created_date,'Modified_Date',mlg.modified_date)
)
) AS CHAR) AS JSON
FROM hunter.`hunter_logging` mlg
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON mlg.`application_id` = app.`application_id`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON mlg.`application_id` = lm.`application_id`
WHERE (mlg.`created_date` BETWEEN fromDate AND toDate) AND mlg.STATUS = 'S';
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchBillableMCACallsApplications` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchBillableMCACallsApplications` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchBillableMCACallsApplications`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
SELECT 
CAST(
JSON_ARRAYAGG(
JSON_OBJECT(
-- 'module',IF(mlg.id IS NOT NULL,'MCA',''),
'provider',IF(mlg.id IS NOT NULL,'Corpository',''),
'orgName',app.`organisation_name`,
'applicationId',mlg.`application_id`,
'applicationCode',lm.`application_code`,
'gstin',app.`gstin`,
'requestType',mlg.service_type,
'status',IF((mlg.STATUS != 'S') AND (mlg.STATUS IS NOT NULL),'FAILED', IF(mlg.STATUS IS NOT NULL,'SUCCESS', '')),
'failureReason',mlg.message,
'createdDate',mlg.`created_date`,
'modifiedDate',mlg.`modified_date`,
'dates',JSON_OBJECT('StartTime',mlg.start_time,'ReceiveTime',mlg.receive_time,'Created_date',mlg.created_date,'Modified_Date',mlg.modified_date)
)
) AS CHAR) AS JSON
FROM `mca`.`mca_log_history` mlg
LEFT JOIN `loan_application`.`fs_corporate_applicant_details` app ON mlg.`application_id` = app.`application_id`
LEFT JOIN `loan_application`.`fs_loan_application_master` lm ON mlg.`application_id` = lm.`application_id`
WHERE (mlg.`created_date` BETWEEN fromDate AND toDate) AND mlg.STATUS = 'S' and mlg.service_type != 'login';
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchDisbursedApplicationList` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchDisbursedApplicationList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchDisbursedApplicationList`(IN orgId INT)
BEGIN
SELECT dis.disbursed_amount, dis.disbursement_date, dis.remark, corp.organisation_name ,dis.application_id, lam.user_id ,
(SELECT branch FROM `loan_application`.`sanction_detail` AS san WHERE san.application_id = dis.application_id)
AS branch_id
FROM `loan_application`.`fs_corporate_applicant_details` AS corp
INNER JOIN `loan_application`.`disbursement_detail` AS dis ON 
corp.application_id = dis.application_id 
INNER JOIN `loan_application`.`fs_loan_application_master` AS lam ON
corp.application_id = lam.application_id
AND dis.is_disbursed_from = 2 WHERE dis.org_id = orgId GROUP BY dis.application_id;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchFpDashbordCountByOrgId` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchFpDashbordCountByOrgId` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchFpDashbordCountByOrgId`(IN orgId INT)
BEGIN
	SELECT 
SUM((CASE WHEN (pro.`proposal_status_id` = 2) THEN 1 ELSE 0 END)) AS `InPrinciple`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Hold_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Hold_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Reject_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Reject_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 5) THEN 1 ELSE 0 END)) AS `Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 11 || pro.`proposal_status_id` = 13) THEN 1 ELSE 0 END)) AS `Disbursment`
FROM `loan_application`.`proposal_details` pro WHERE pro.`user_org_id` = orgId AND pro.`is_active` = TRUE;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchFpDashbordCountByOrgIdAndBranchId` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchFpDashbordCountByOrgIdAndBranchId` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchFpDashbordCountByOrgIdAndBranchId`(IN orgId INT,IN branchId INT)
BEGIN
	SELECT 
SUM((CASE WHEN (pro.`proposal_status_id` = 2) THEN 1 ELSE 0 END)) AS `InPrinciple`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Hold_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Hold_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Reject_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Reject_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 5) THEN 1 ELSE 0 END)) AS `Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 11 || pro.`proposal_status_id` = 13) THEN 1 ELSE 0 END)) AS `Disbursment`
FROM `loan_application`.`proposal_details` pro WHERE pro.`user_org_id` = orgId AND pro.`is_active` = TRUE AND pro.`branch_id` = branchId;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchFpDashbordCountByOrgIdAndUserId` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchFpDashbordCountByOrgIdAndUserId` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchFpDashbordCountByOrgIdAndUserId`(IN orgId INT,IN userId INT)
BEGIN
	SELECT 
SUM((CASE WHEN (pro.`proposal_status_id` = 2) THEN 1 ELSE 0 END)) AS `InPrinciple`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Hold_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 3 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Hold_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 1) THEN 1 ELSE 0 END)) AS `Reject_Before_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 4 AND pro.`proposal_stage` = 2) THEN 1 ELSE 0 END)) AS `Reject_After_Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 5) THEN 1 ELSE 0 END)) AS `Sanctioned`,
SUM((CASE WHEN (pro.`proposal_status_id` = 11 || pro.`proposal_status_id` = 13) THEN 1 ELSE 0 END)) AS `Disbursment`
FROM `loan_application`.`proposal_details` pro WHERE pro.`user_org_id` = orgId AND pro.`is_active` = TRUE 
AND pro.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = userId AND `is_active` = TRUE);
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchIneligibleRecordsForOffline` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchIneligibleRecordsForOffline` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchIneligibleRecordsForOffline`(IN orgId INT)
BEGIN
	SELECT  ineligible.application_id,con.user_id,ms.amount,
	IFNULL((SELECT cam.code  FROM `users`.`campaign_details` cam WHERE cam.user_id = con.user_id AND cam.is_active=TRUE ORDER BY id DESC LIMIT 1),"") AS cmCode,
	br.name,IFNULL(app.organisation_name,''),ineligible.is_sanctioned,
	ineligible.is_disbursed,ineligible.branch_id,
	IF(app.pan IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(app.pan),'C@p!ta@W0rld#AES')) AS CHAR(100)))) AS pan,
	app.gstin,IFNULL(br.code,''),IFNULL(br.street_name,'')
FROM `loan_application`.`ineligible_proposal_details` ineligible
INNER JOIN `connect`.`connect_log` con
ON con.application_id  = ineligible.application_id
INNER JOIN `loan_application`.`fs_loan_application_master` ms
ON ineligible.application_id = ms.application_id
INNER JOIN `users`.`branch_master` br
ON ineligible.branch_id = br.id
INNER JOIN `loan_application`.`fs_corporate_applicant_details` app
ON ineligible.application_id = app.application_id
WHERE ineligible.user_org_id = orgId AND (ineligible.`status` IS NULL OR  ineligible.`status` = 1);
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchItrPdfScrap` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchItrPdfScrap` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchItrPdfScrap`(IN fromDate DATETIME,IN toDate DATETIME)
BEGIN
	SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',i.`application_id`,'directorId',i.`director_id`,'fileName',i.`file_name`,'scrapResponse',i.`scrape_response`,
	'message',i.`message`,'createdDate',i.`created_date`)) AS CHAR) AS result 
	FROM `itr_api`.`itr_pdf_scrap_audit` i 
	WHERE i.created_date BETWEEN fromDate AND toDate ORDER BY id DESC;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchOfflineDisbursedProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchOfflineDisbursedProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflineDisbursedProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT( 'applicationId',coapp.`application_id`,'organisationName',coapp.`organisation_name`,'branchName',branch.`name`,'branchCode',branch.`code`,'branchId',branch.`id`,'mobile',u.mobile,
		'email',u.email)) AS CHAR) AS result
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN `loan_application`.`disbursement_detail` dis ON dis.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = ine.`branch_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`	
		LEFT JOIN users.`users` u ON u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`is_disbursed` = TRUE AND dis.`is_active` = TRUE 
		AND dis.`is_disbursed_from` = 2 ");
	
	IF (vb_role_id = 6 or vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 12) THEN -- SMECC
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 or vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchOfflineOtherProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchOfflineOtherProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflineOtherProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT  CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',ine.`application_id`,'organisationName',coapp.`organisation_name`,
		'pan',IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))),
		'gstin',con.`gstin`,'userId',con.`user_id`,'loanAmount',coprim.`loan_amount`,'branchName',branch.`name`,
		'branchCode',branch.`code`,'branchAddress',branch.`street_name`,'reason',ine.`reason`,'modifiedDate',DATE_FORMAT(ine.`modified_date`, '%d/%m/%Y %H:%i:%S'),
		'campaignCode',camp.`code`,'isCampaignUser',IF(camp.id IS NULL,'Market Place','Bank Specific'),'branchId',branch.`id`,
		'mobile',u.`mobile`,'email',u.`email`)) AS CHAR)AS result
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id," AND ine.`status` IN (5,6,7,8,9) ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 12) THEN -- SMECC
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchOfflinePendingProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchOfflinePendingProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflinePendingProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',ine.`application_id`,'organisationName',coapp.`organisation_name`
		 ,'pan',IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))),
		'gstin',con.`gstin`,'userId',con.`user_id`,'loanAmount',coprim.`loan_amount`,'branchName',branch.`name`,'branchCode',branch.`code`,'branchAddress',branch.`street_name`,
		 'campaignCode',camp.`code`,'isCampaignUser',IF(camp.id IS NULL,'Market Place','Bank SPECIFIC'),'branchId',ine.`branch_id`,
		 'mobile',u.mobile,'email',u.email,'userOrgId',ine.user_org_id,'proposalId',ine.id)) AS CHAR) AS result
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`is_disbursed` IS NOT TRUE AND ine.`is_sanctioned` IS NOT TRUE 
		AND ine.`status` = 1 ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 12) THEN -- SMECC
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchOfflineRejectProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchOfflineRejectProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflineRejectProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',ine.`application_id`,'organisationName',coapp.`organisation_name`,
		'pan',IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))),
		'gstin',con.`gstin`,'userId',con.`user_id`,'loanAmount',coprim.`loan_amount`,'branchName',branch.`name`,
		'branchCode',branch.`code`,'branchAddress',branch.`street_name`,'reason',ine.`reason`,'modifiedDate',DATE_FORMAT(ine.`modified_date`, '%d/%m/%Y %H:%i:%S'),
		'campaignCode',camp.`code`,'isCampaignUser',IF(camp.id IS NULL,'Market Place','Bank SPECIFIC'),'branchId',branch.`id`,
		'mobile',u.`mobile`,'email',u.`email`,'proposalId',ine.id)) AS CHAR) AS JSON
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.is_active = true and ine.`user_org_id` =", vb_org_id," AND ine.`status` = 4 ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 12) THEN -- SMECC
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchOfflineSanctionedProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchOfflineSanctionedProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchOfflineSanctionedProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT CAST(JSON_ARRAYAGG(JSON_OBJECT('applicationId',coapp.`application_id`,'organisation_name',coapp.`organisation_name`,'sanctionDate',DATE_FORMAT(sanc.`sanction_date`, '%d/%m/%Y %H:%i:%S'),'sanctionedAmount',sanc.`sanction_amount`,'tenure',sanc.`tenure`,'roi',sanc.`roi`,'processingFee',sanc.`processing_fee`,'remark',sanc.`remark`,
		'branchName',branch.`name`,'branchCode',branch.`code`,'branchId',branch.`id`,'mobile',u.mobile,'email',u.email)) AS CHAR) AS JSON
		FROM loan_application.`ineligible_proposal_details` ine 
		LEFT JOIN `loan_application`.`sanction_detail` sanc ON sanc.`application_id` = ine.`application_id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = sanc.`branch`
		LEFT JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id," AND ine.`is_sanctioned` = TRUE AND sanc.`is_active` = TRUE 
		AND ine.is_active = true and ine.`is_disbursed` IS NOT TRUE AND sanc.is_sanctioned_from = 2 ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND sanc.`branch` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 12) THEN -- SMECC
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = " , userId , " AND `is_active` = TRUE) ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchProposalsByOrgAndBranchAndSearchString` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchProposalsByOrgAndBranchAndSearchString` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchProposalsByOrgAndBranchAndSearchString`(IN orgId INT,IN searchString VARCHAR(100),IN branchId INT,IN listLimit INT)
BEGIN
	SELECT prop.`application_id`,prop.`id`,prop.`fp_product_id`,loan.`application_code`,
		appl.`organisation_name`,prop.`el_amount`,product.`name`,prop.`created_date`,loan.`business_type_id`,
		prop.`proposal_status_id`,loan.`product_id` AS pro,
		CONCAT(retail.first_name,' ',retail.middle_name,' ',retail.last_name) AS pl_user_name
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id` AND appl.`proposal_mapping_id` = prop.`id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		LEFT JOIN fs_retail_applicant_details AS retail ON retail.application_id = loan.application_id
		WHERE prop.`is_active` = TRUE 
		AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%') 
		OR retail.first_name LIKE CONCAT('%', searchString , '%') OR retail.middle_name LIKE CONCAT('%', searchString , '%') 
		OR retail.last_name LIKE CONCAT('%', searchString , '%')) AND prop.`user_org_id` = orgId
		AND prop.`branch_id` = branchId ORDER BY prop.`created_date` DESC LIMIT listLimit;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchProposalsByOrgAndSearchString` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchProposalsByOrgAndSearchString` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchProposalsByOrgAndSearchString`(IN orgId INT,IN searchString VARCHAR(100),IN listLimit INT)
BEGIN
	SELECT prop.`application_id`,prop.`id`,prop.`fp_product_id`,loan.`application_code`,appl.`organisation_name`,prop.`el_amount`,product.`name`,prop.created_date,
	loan.`business_type_id`,prop.`proposal_status_id`,loan.`product_id` AS prod,branch.name AS branchName,branch.code
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id` AND appl.`proposal_mapping_id` = prop.`id`
		LEFT JOIN users.`branch_master` branch ON branch.id = prop.`branch_id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		WHERE prop.`is_active` = TRUE AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') 
		OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%')) 
		AND prop.`user_org_id` = orgId ORDER BY prop.`created_date` DESC LIMIT listLimit;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchProposalsByOrgAndUserIdAndSearchString` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchProposalsByOrgAndUserIdAndSearchString` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchProposalsByOrgAndUserIdAndSearchString`(IN orgId INT,IN searchString VARCHAR(100),IN userId INT,IN listLimit INT)
BEGIN
	SELECT prop.`application_id`,prop.`id`,prop.`fp_product_id`,loan.`application_code`,appl.`organisation_name`,prop.`el_amount`,product.`name`,prop.`created_date`,loan.`business_type_id`,prop.`proposal_status_id`,loan.`product_id` AS pro,branch.name AS branchName,branch.code
		FROM loan_application.`proposal_details` prop
		-- LEFT JOIN loan_application.`fs_loan_application_master` loan ON loan.`application_id` = prop.`application_id`
		LEFT JOIN loan_application.`application_proposal_mapping` loan ON loan.`application_id` = prop.`application_id` AND loan.`proposal_id` = prop.`id`
		LEFT JOIN loan_application.`fs_corporate_applicant_details` appl ON appl.`application_id` = prop.`application_id` AND appl.`proposal_mapping_id` = prop.`id`
		LEFT JOIN `loan_application`.`fp_product_master` product ON product.`fp_product_id` = prop.`fp_product_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = prop.`branch_id`
		WHERE prop.`is_active` = TRUE AND (loan.`application_code` LIKE CONCAT('%', searchString , '%') OR  appl.`organisation_name` LIKE CONCAT('%', searchString , '%')) AND prop.`user_org_id` = orgId
		AND prop.`branch_id` IN (SELECT `branch_master_id` FROM users.`user_branch_mapping` WHERE `user_id` = userId AND `is_active` = TRUE) ORDER BY prop.`created_date` DESC LIMIT listLimit;
  END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchRejectProposalsForOffline` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchRejectProposalsForOffline` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchRejectProposalsForOffline`(IN orgId INT)
BEGIN
	SELECT  ineligible.application_id,con.user_id,ms.amount,IFNULL((SELECT cam.code  FROM `users`.`campaign_details` cam WHERE cam.user_id = con.user_id AND cam.is_active=TRUE ORDER BY id DESC LIMIT 1),"") AS cmCode,br.name,IFNULL(app.organisation_name,''),ineligible.is_sanctioned,ineligible.is_disbursed,ineligible.branch_id,app.pan,app.gstin,IFNULL(br.code,''),IFNULL(br.street_name,''),ineligible.`reason`,ineligible.`modified_date`
FROM `loan_application`.`ineligible_proposal_details` ineligible
INNER JOIN `connect`.`connect_log` con
ON con.application_id  = ineligible.application_id
INNER JOIN `loan_application`.`fs_loan_application_master` ms
ON ineligible.application_id = ms.application_id
INNER JOIN `users`.`branch_master` br
ON ineligible.branch_id = br.id
INNER JOIN `loan_application`.`fs_corporate_applicant_details` app
ON ineligible.application_id = app.application_id
WHERE ineligible.user_org_id = orgId AND ineligible.`status` = 4;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchSanctionsApplicationList` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchSanctionsApplicationList` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchSanctionsApplicationList`(IN orgId INT)
BEGIN
	SELECT lsd.sanction_amount, lsd.sanction_date, lsd.remark, lsd.roi, lsd.tenure, lsd.processing_fee, lsd.application_id, lsd.is_partially_disbursed_offline, corp.organisation_name,lsd.branch  
FROM `loan_application`.`fs_corporate_applicant_details` AS corp
INNER JOIN `loan_application`.`sanction_detail` AS lsd ON 
corp.application_id = lsd.application_id AND lsd.is_sanctioned_from = 2 WHERE lsd.org_id = orgId;
	END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchUniformDisbursedProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchUniformDisbursedProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchUniformDisbursedProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT coapp.`application_id`,coapp.`organisation_name`,branch.`name` AS branchName,branch.`code` AS branchCode,branch.`id`,
		ine.el_roi,ine.processing_fee,ine.fp_product_id,dis.disbursed_amount,dis.transaction_no,dis.account_no,dis.disbursement_date,dis.payment_mode,ine.id as pKeyId,u.mobile,u.email
		FROM loan_application.`proposal_details` ine 
		INNER JOIN `loan_application`.`disbursement_detail` dis ON dis.`application_id` = ine.`application_id`
		INNER JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		INNER JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id," AND dis.`is_active` = TRUE 
		AND dis.`is_disbursed_from` = 1 and (con.stage_id IN (305,306) AND con.`status` != 6)");	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchUniformOtherProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchUniformOtherProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchUniformOtherProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT ine.`application_id`,coapp.`organisation_name`,IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))) AS pan
		,con.`gstin`,con.`user_id`,coprim.`loan_amount`,branch.`name` AS branchName,branch.`code` AS branchCode,
		branch.`street_name`,ine.`modified_date`,camp.`code`,IF(camp.id IS NULL,0,1) AS isFromCampaign,branch.`id` AS branchId,ine.`proposal_status_id`,
		ine.el_roi,ine.processing_fee,ine.fp_product_id,u.mobile,u.email
		FROM loan_application.`proposal_details` ine 
		INNER JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		INNER JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		INNER JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id," AND ine.`proposal_status_id` IN (5,6) and (con.stage_id IN (305,306) AND con.`status` != 6) ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchUniformPendingProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchUniformPendingProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchUniformPendingProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT ine.`application_id`,coapp.`organisation_name`,
		IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))) AS pan,
		con.`gstin`,con.`user_id`,ine.`el_amount`,branch.`name` AS branchName,branch.`code` AS branchCode,branch.`street_name`,
		camp.`code`,IF(camp.id IS NULL,0,1) AS isFromCampaign,ine.`branch_id`,ine.el_roi,ine.processing_fee,ine.fp_product_id,u.mobile,u.email
		FROM loan_application.`proposal_details` ine 
		INNER JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		INNER JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		INNER JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id," AND 
		(SELECT COUNT(det.`id`) FROM `loan_application`.`sanction_detail` det WHERE det.`application_id` = ine.`application_id` and det.`is_active` = true) <= 0
		AND (SELECT COUNT(det.`id`) FROM `loan_application`.`disbursement_detail` det WHERE det.`application_id` = ine.`application_id` and det.`is_active` = true) <= 0
		AND (con.stage_id IN (305,306) AND con.`status` != 6) AND ine.proposal_status_id = 2 ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchUniformRejectProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchUniformRejectProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchUniformRejectProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT ine.`application_id`,coapp.`organisation_name`,
		IF(coapp.`pan` IS NULL,NULL,(CAST((AES_DECRYPT(UNHEX(coapp.`pan`),'C@p!ta@W0rld#AES')) AS CHAR(100)))) AS pan,
		con.`gstin`,con.`user_id`,
		ine.`el_amount`,branch.`name` AS branchName,branch.`code` AS branchCode,
		branch.`street_name`,ine.`reason`,ine.`modified_date`,camp.`code`,IF(camp.id IS NULL,0,1) AS isFromCampaign,branch.`id`,ine.el_roi,ine.processing_fee
		,ine.fp_product_id,u.mobile,u.email
		FROM loan_application.`proposal_details` ine 
		INNER JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		INNER JOIN loan_application.`fs_corporate_primary_details` coprim ON coprim.`application_id` = ine.`application_id`
		INNER JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`campaign_details` camp ON camp.`user_id` = con.`user_id` AND camp.`is_active` = TRUE
		LEFT JOIN users.`branch_master` branch ON branch.`id` = ine.`branch_id`
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` = ", vb_org_id," AND ine.`proposal_status_id` = 4 and (con.stage_id IN (305,306) AND con.`status` != 6) ");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND ine.`branch_id` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spFetchUniformSanctionedProposal` */

/*!50003 DROP PROCEDURE IF EXISTS  `spFetchUniformSanctionedProposal` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spFetchUniformSanctionedProposal`(IN userId INT)
BEGIN
    
	DECLARE vb_role_id INT;
	DECLARE vb_branch_id INT;
	DECLARE vb_org_id INT;
	SELECT `user_role_id`,`branch_id`,`user_org_id` INTO vb_role_id,vb_branch_id,vb_org_id FROM users.`users`  WHERE `user_id` = userId;
	
	
	SET @vb_query = CONCAT("SELECT coapp.`application_id`,coapp.`organisation_name`,sanc.`sanction_date`,sanc.`sanction_amount`,sanc.`tenure`,sanc.`roi`,sanc.`processing_fee`,sanc.`remark`,
		branch.`name` AS branchName,branch.`code` AS branchCode,branch.`id`,ine.fp_product_id,ine.id as pKeyId,u.mobile,u.email
		FROM loan_application.`proposal_details` ine 		
		INNER JOIN loan_application.`fs_corporate_applicant_details` coapp ON coapp.`application_id` = ine.`application_id`
		INNER JOIN `loan_application`.`sanction_detail` sanc ON sanc.`application_id` = ine.`application_id`
		INNER JOIN connect.`connect_log` con ON con.`application_id` = ine.`application_id`
		LEFT JOIN users.`branch_master` branch ON branch.id = sanc.`branch`		
		LEFT JOIN users.`users` u ON  u.`user_id` = con.`user_id`
		WHERE ine.`user_org_id` =", vb_org_id,"
		AND sanc.`is_active` = TRUE 
		AND sanc.is_sanctioned_from = 1 and (SELECT COUNT(det.`application_id`) FROM `loan_application`.`disbursement_detail` det WHERE det.application_id = ine.`application_id`) <= 0  and (con.stage_id IN (305,306) AND con.`status` != 6)");
	
	IF (vb_role_id = 6 OR vb_role_id=9) THEN -- BO (GET BASED ON BRANCH LEVEL)
		SET @vb_query = CONCAT(@vb_query," AND sanc.`branch` = ",vb_branch_id, " ORDER BY ine.`id` DESC");
	ELSEIF (vb_role_id = 11 OR vb_role_id = 5) THEN -- AdminChecker and HO (GET BASED ON BANK LEVEL)
		SET @vb_query = CONCAT(@vb_query," ORDER BY ine.`id` DESC");
	ELSE 
		SET @vb_query = "select '' from dual";
	END IF; 
	-- select @vb_query from dual;
	PREPARE stmt1 FROM @vb_query;
	EXECUTE stmt1;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spGetHomeCounter` */

/*!50003 DROP PROCEDURE IF EXISTS  `spGetHomeCounter` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spGetHomeCounter`()
BEGIN
SELECT COUNT(*) AS TotalData,'Total No.of FP' AS NAME  FROM `users`.`users` us WHERE us.`is_active` = TRUE AND us.`user_type_id` = 2
UNION ALL
SELECT SUM(total) AS TotalData, 'Total No.of FS' AS NAME FROM(
SELECT COUNT(*) total FROM `users`.`users` us WHERE us.`is_active` = TRUE AND us.`user_type_id` = 1 -- AND us.`created_date` > '2018-09-25'
-- UNION ALL
-- SELECT COUNT(*) total FROM `users`.`bank_user_dump` bud WHERE bud.is_active = TRUE -- AND bud.`created_date` > '2018-09-25'
) AS totalTotalCount
UNION ALL
SELECT SUM(total) AS TotalData, 'TotalInPrinciple' AS NAME FROM(
SELECT COUNT(*) total FROM connect.`connect_log` us WHERE us.stage_id IN (7,9) -- AND us.modified_Date > '2018-09-25'
-- UNION ALL
-- SELECT COUNT(*) total FROM `users`.`bank_user_dump` bud WHERE bud.is_active = TRUE -- AND `created_date` > '2018-09-25'
) AS totalInPrinciple
UNION ALL
SELECT SUM(total) AS TotalData, 'TotalInPrinciple Amt' AS NAME FROM(
SELECT SUM(el_amount) total FROM `loan_application`.`proposal_details` WHERE `is_active` = TRUE  -- AND `created_date` > '2018-09-25'
UNION ALL
SELECT SUM(bud.`sanctioned_amount`) total  FROM `users`.`bank_user_dump` bud WHERE bud.is_active = TRUE  -- AND bud.`created_date` > '2018-09-25'
) AS totalAccount;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spInprincipleToSanction` */

/*!50003 DROP PROCEDURE IF EXISTS  `spInprincipleToSanction` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spInprincipleToSanction`()
BEGIN
	-- for update old record of day
	UPDATE `loan_application`.`inprinciple_to_sanction` SET is_active=FALSE WHERE `modified_date`>= CURDATE();
	
	-- org calc start
	-- for insert organisation ratio
	SELECT COUNT(user_org_id) INTO @countV1 FROM users.`user_organisation_master`;
	SET @i = 0;
	
	SET @itsRatio = 0;
	SET @sanctionCount=0;
	SET @inprincipleCount=0;
	
	WHILE(@i<@countV1)DO
		SET @getuserOrgIdQuery:= CONCAT('SELECT user_org_id INTO @uId FROM users.`user_organisation_master` LIMIT ',@i,',1');
		PREPARE stmt FROM @getuserOrgIdQuery ;
		EXECUTE stmt ;
		
		SET @getSanctionCount:= CONCAT('SELECT COUNT(user_org_id) INTO @sanctionCount FROM `loan_application`.`proposal_details` WHERE is_active=TRUE AND `proposal_status_id` IN(5,11,13) AND user_org_id=', @uId);
		PREPARE stmt FROM @getSanctionCount;
		EXECUTE stmt;
		
		SET @getInprincipleCount:= CONCAT('SELECT IF(COUNT(user_org_id)=0,1,COUNT(user_org_id)) INTO @inprincipleCount FROM `loan_application`.`proposal_details` WHERE is_active=TRUE AND user_org_id=', @uId);
		PREPARE stmt FROM @getInprincipleCount;
		EXECUTE stmt;
		
		SET @itsRatio = @sanctionCount / @inprincipleCount;
			     
		INSERT INTO `loan_application`.`inprinciple_to_sanction` ( `org_id`,  `inprinciple_to_sanction_ratio`, sanction_count, inprinciple_count,`created_date`,modified_date) 
		VALUES  ( @uId,    @itsRatio, @sanctionCount, @inprincipleCount, NOW(), NOW() ) ;
		
		SET @i = @i + 1;
		SET @sanctionCount=0;
		SET @inprincipleCount=0;
	END WHILE;	
		
	SELECT COUNT(org_id) INTO @sancData FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NULL AND is_active=TRUE AND `modified_date`>= CURDATE();
	SET @k = 0;
	SET @itsAmtRatio = 0;
	SET @sanAmt = 0;
	SET @ipAmt = 0;
	
	WHILE(@k<@sancData)DO
		SET @getData:= CONCAT('SELECT id,org_id into @id,@orgId FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY inprinciple_to_sanction_ratio DESC,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		SET @inPrincipleAmt:= CONCAT('SELECT SUM(el_amount) into @ipAmt FROM loan_application.proposal_details WHERE user_org_id =',@orgId,' AND is_active=TRUE GROUP BY user_org_id');
		PREPARE stmt FROM @inPrincipleAmt ;
		EXECUTE stmt;
		
		SET @sanctionAmt:= CONCAT('SELECT SUM(sanction_amount) into @sanAmt FROM loan_application.sanction_detail WHERE org_id =',@orgId,' AND is_active=TRUE AND is_sanctioned_from = 1 GROUP BY org_id');
		PREPARE stmt FROM @sanctionAmt ;
		EXECUTE stmt;
		
		SET @itsAmtRatio = (@sanAmt/@ipAmt);
		IF (@itsAmtRatio > 1) THEN 
		  SET @itsAmtRatio = 1;
		ELSEIF (@itsAmtRatio IS NULL) THEN
		  SET @itsAmtRatio = 0;
		END IF;
		
		UPDATE `loan_application`.`inprinciple_to_sanction` SET inprinciple_to_sanction_amt_ratio=@itsAmtRatio, inprinciple_to_sanction_weight=0.35*(@k + 1), inprinciple_to_sanction_rank=(@k + 1), sanction_amount=@sanAmt, inprinciple_amount=@ipAmt, modified_date=NOW() WHERE id=@id;
		
		SET @k = @k + 1;
		SET @sanAmt = 0;
		SET @ipAmt = 0;
	END WHILE;
	
	-- for set inprinciple_to_sanction_amt_weight & total_weight
	SET @k = 0;
	SET @itsAmtWeight = 0;
	WHILE(@k<@sancData)DO
		SET @getData:= CONCAT('SELECT id,org_id, inprinciple_to_sanction_amt_ratio, inprinciple_to_sanction_weight into @id,@orgId,@itsAmtRatio,@itsWeight FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY inprinciple_to_sanction_amt_ratio DESC,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		SET @itsAmtWeight = 0.65*(@k+1);
		UPDATE `loan_application`.`inprinciple_to_sanction` SET inprinciple_to_sanction_amt_weight= @itsAmtWeight, inprinciple_to_sanction_amt_rank=(@k+1), total_weight=(@itsAmtWeight + @itsWeight),modified_date=NOW()  WHERE id=@id;
		
		SET @k = @k + 1;
	END WHILE;
	
	-- for set rank
	SET @k = 0;
	WHILE(@k<@sancData)DO
		SET @getData:= CONCAT('SELECT id into @id FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY total_weight,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		UPDATE `loan_application`.`inprinciple_to_sanction` SET rank=@k+1, modified_date=NOW()  WHERE id=@id;
		
		SET @k = @k + 1;
	END WHILE;
	-- org calc ends
	
	-- product calc starts
	-- for insert product ratio
	SELECT COUNT(`fp_product_id`) INTO @countFpId FROM loan_application.`fp_product_master`;
	SET @j = 0;
	
	SET @itsRatio = 0;
	SET @sanctionCount=0;
	SET @inprincipleCount=0;
	
	WHILE(@j<@countFpId)DO
		SET @getFpIdQuery:= CONCAT('SELECT fp_product_id,user_org_id INTO @fpProdId,@fpOrgId FROM loan_application.`fp_product_master` LIMIT ',@j,',1');
		PREPARE stmt FROM @getFpIdQuery ;
		EXECUTE stmt ;
		
		SET @getSanctionCount:= CONCAT('SELECT COUNT(fp_product_id) INTO @sanctionCount FROM `loan_application`.`proposal_details` WHERE is_active=TRUE AND `proposal_status_id` IN(5,11,13) AND fp_product_id=', @fpProdId);
		PREPARE stmt FROM @getSanctionCount;
		EXECUTE stmt;
		
		SET @getInprincipleCount:= CONCAT('SELECT IF(COUNT(fp_product_id)=0,1,COUNT(fp_product_id)) INTO @inprincipleCount FROM `loan_application`.`proposal_details` WHERE is_active=TRUE AND fp_product_id=', @fpProdId);
		PREPARE stmt FROM @getInprincipleCount;
		EXECUTE stmt;
		
		SET @itsRatio = @sanctionCount / @inprincipleCount;
	     
		INSERT INTO `loan_application`.`inprinciple_to_sanction` ( `org_id`, `fp_product_id` ,`inprinciple_to_sanction_ratio`, sanction_count,  inprinciple_count, `created_date`,modified_date) 
		VALUES  ( @fpOrgId, @fpProdId , @itsRatio, @sanctionCount , @inprincipleCount, NOW(),NOW() ) ;
		
		SET @j = @j + 1;
		SET @sanctionCount=0;
		SET @inprincipleCount=0;
	END WHILE;
	
	SELECT COUNT(fp_product_id) INTO @productData FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NOT NULL AND is_active=TRUE AND `modified_date`>= CURDATE();
	SET @k = 0;
	SET @itsAmtRatio = 0;
	SET @ipAmt=0;
	SET @sanAmt=0;
	WHILE(@k<@productData)DO
		SET @getData:= CONCAT('SELECT id,fp_product_id into @id,@fpProductId FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NOT NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY inprinciple_to_sanction_ratio DESC,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		SET @inPrincipleAmt:= CONCAT('SELECT SUM(el_amount) into @ipAmt FROM loan_application.proposal_details WHERE fp_product_id =',@fpProductId,' AND is_active=TRUE GROUP BY fp_product_id');
		PREPARE stmt FROM @inPrincipleAmt ;
		EXECUTE stmt;
		
		SET @sanctionAmt:= CONCAT('SELECT SUM(sanction_amount) into @sanAmt FROM loan_application.proposal_details AS pd JOIN sanction_detail AS sd ON sd.application_id = pd.application_id WHERE fp_product_id =',@fpProductId,' AND sd.is_Active = TRUE AND is_sanctioned_from = 1 GROUP BY fp_product_id');
		PREPARE stmt FROM @sanctionAmt ;
		EXECUTE stmt;
		
		SET @itsAmtRatio = COALESCE(@sanAmt / NULLIF(@ipAmt,0), 0);
		IF (@itsAmtRatio > 1) THEN 
		  SET @itsAmtRatio = 1;
		ELSEIF (@itsAmtRatio IS NULL) THEN
		  SET @itsAmtRatio = 0;
		END IF;
		
		UPDATE `loan_application`.`inprinciple_to_sanction` SET inprinciple_to_sanction_amt_ratio=@itsAmtRatio, inprinciple_to_sanction_weight=0.35*(@k + 1), inprinciple_to_sanction_rank=(@k + 1), sanction_amount=@sanAmt, inprinciple_amount=@ipAmt, modified_date=NOW() WHERE id=@id;
		
		SET @k = @k + 1;
		SET @ipAmt=0;
		SET @sanAmt=0;
	END WHILE;
	
	SET @k = 0;
	SET @itsAmtWeight = 0;
	WHILE(@k<@productData)DO
		SET @getData:= CONCAT('SELECT id,org_id, inprinciple_to_sanction_amt_ratio, inprinciple_to_sanction_weight into @id,@orgId,@itsAmtRatio,@itsWeight FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NOT NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY inprinciple_to_sanction_amt_ratio DESC,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		SET @itsAmtWeight = 0.65*(@k+1);
		UPDATE `loan_application`.`inprinciple_to_sanction` SET inprinciple_to_sanction_amt_weight=@itsAmtWeight, inprinciple_to_sanction_amt_rank=(@k+1), total_weight=(@itsAmtWeight + @itsWeight), modified_date=NOW()  WHERE id=@id;
		
		SET @k = @k + 1;
	END WHILE;
	
	SET @k = 0;
	WHILE(@k<@productData)DO
		SET @getData:= CONCAT('SELECT id into @id FROM `loan_application`.`inprinciple_to_sanction` WHERE `fp_product_id` IS NOT NULL AND is_active=TRUE AND `modified_date`>= CURDATE() ORDER BY total_weight,id LIMIT ',@k,',1');
		PREPARE stmt FROM @getData ;
		EXECUTE stmt;
		
		UPDATE `loan_application`.`inprinciple_to_sanction` SET rank=@k+1, modified_date=NOW()  WHERE id=@id;
		
		SET @k = @k + 1;
	END WHILE;
	-- product calc ends
	
	-- add first priority for kotak
	UPDATE loan_application.inprinciple_to_sanction SET rank = IF(org_id=30, 1, rank + 1)
	WHERE is_active=TRUE AND modified_date >= CURDATE()
	AND fp_product_id IS NULL;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spMatchSave` */

/*!50003 DROP PROCEDURE IF EXISTS  `spMatchSave` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spMatchSave`(IN applicationId BIGINT,IN fpProdId BIGINT,IN id BIGINT,IN matchCalculatedDate DATETIME)
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
	 ,is_msme_funding_display,is_msme_funding_mandatory  INTO 
	 
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
	 SET @isTenureMatch= NULL ,@tenureFS= NULL ,@tenureMinFP= NULL ,@tenureMaxFP= NULL; --  Tenure
	 SET @isScoreMatch= NULL ,@scoreFS= NULL ,@scoreMinFP= NULL ,@scoreMaxFP= NULL;  --  Score
	 SET @isColleteralCoverageMatch= NULL ,@colleteralCoverageFS= NULL ,@colleteralCoverageMinFP= NULL ,@colleteralCoverageMaxFP= NULL; --  Colleteral Coverage	 
	 SET @isCgtmseCoverageMatch= NULL ,@cgtmseCoverageFS= NULL ,@cgtmseCoverageFP= NULL; --  CGTMSE Coverage
	 SET @isMsmeFundingMatch= NULL ,@msmeFundingFS= NULL ,@msmeFundingFP= NULL; --  MSME Funding
	 SET @isTurnOverMatch= NULL ,@turnOverFS= NULL ,@turnOverMinFP= NULL ,@turnOverMaxFP= NULL; --  Turn Over
	 SET @isGrossCashAccuralMatch= NULL ,@grossCashAccuralFS= NULL ,@grossCashAccuralMinFP= NULL ,@grossCashAccuralMaxFP= NULL; --  Gross Cash Accural
	
-- ===================================== End Get Product Parameter Variable ===================================== --	
	
	
-- ===================================== Start Get Debt Equity Ration Matches Variable  ===================================== --
SET @debtEquityValFS=(SELECT (SELECT (IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other) + IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest) + IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)
							) FROM fs_corporate_cma_liabilities_details WHERE application_id =applicationId AND is_active = 1 AND YEAR= @itrYear)
							 /(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) - IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details WHERE application_id =applicationId AND is_active = 1 AND YEAR= @itrYear));
IF (@isDebtEquityDisplay = TRUE) THEN
	SET @debtEquityQuery:= CONCAT('SELECT CASE WHEN max_debt_equity=5 
					THEN 
					(SELECT COUNT(application_id) FROM fs_corporate_cma_liabilities_details 
					WHERE application_id=',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1 AND 
					-- //(SELECT (SELECT (IF(ISNULL(sub_total_a),0,sub_total_a)  IF(ISNULL(short_term_borrowing_from_others),0,short_term_borrowing_from_others) 
					(SELECT (SELECT ( IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares) 
					 +IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other)  +
					IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest)  +
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) 
					FROM fs_corporate_cma_liabilities_details WHERE application_id=',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) 
					/ 
					(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) - 
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details 
					WHERE application_id=',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1)) 
					>= (SELECT min_debt_equity FROM ',@fpTableName,' WHERE fp_product_id=',@fpProdId,')) 
					ELSE 
					(SELECT COUNT(application_id) FROM fs_corporate_cma_liabilities_details 
					WHERE application_id=',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1 AND 
					-- //(SELECT (SELECT (IF(ISNULL(sub_total_a),0,sub_total_a)  IF(ISNULL(short_term_borrowing_from_others),0,short_term_borrowing_from_others) 
					(SELECT (SELECT ( IF(ISNULL(total_term_liabilities),0,total_term_liabilities) - IF(ISNULL(preferences_shares),0,preferences_shares) 
					 +IF(ISNULL(other_ncl_unsecured_loans_from_other),0,other_ncl_unsecured_loans_from_other)  +
					IF(ISNULL(other_ncl_others),0,other_ncl_others) + IF(ISNULL(minority_interest),0,minority_interest)  +
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) 
					FROM fs_corporate_cma_liabilities_details WHERE application_id=',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) 
					/ 
					(SELECT (IF(ISNULL(preferences_shares),0,preferences_shares) + IF(ISNULL(net_worth),0,net_worth) - IF(ISNULL(minority_interest),0,minority_interest) - 
					IF(ISNULL(deferred_tax_liability),0,deferred_tax_liability)) FROM fs_corporate_cma_liabilities_details 
					WHERE application_id=',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1)) 
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
SET @customerConcentrationQuery:= CONCAT('SELECT `customer_concentration` INTO @customerConcentrationFS FROM `gst`.`gst_mapping_table` WHERE gstin IN (SELECT `gstin` FROM `loan_application`.`fs_corporate_applicant_details` WHERE `application_id` = ',@applicationId,' )  ORDER BY id DESC LIMIT 1');
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
SET @industryFSQuery:= CONCAT('SELECT `industry_name` INTO @industryFS FROM `one_form`.`industry` WHERE id IN (SELECT key_verical_funding FROM `loan_application`.fs_corporate_applicant_details WHERE application_id = ',@applicationId,')');
	PREPARE stmt FROM @industryFSQuery ;
	EXECUTE stmt ;
	
IF (@isIndustrySectorDisplay = TRUE) THEN
	SET @industrySectorQuery:= CONCAT('SELECT COUNT(application_id) INTO @industrySectorMatch FROM fs_corporate_applicant_details
							WHERE application_id = ',@applicationId,'  AND key_verical_funding
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
SET @geoGraphicalFocusFSQuery:= CONCAT('SELECT GROUP_CONCAT(`city_name`) INTO @geoGraphicalFocusFS FROM `one_form`.`city` WHERE id IN (SELECT registered_city_id FROM loan_application.fs_corporate_applicant_details WHERE application_id = ',@applicationId,')');
	PREPARE stmt FROM @geoGraphicalFocusFSQuery ;
	EXECUTE stmt ;
	
IF (@isGeographicalDisplay = TRUE) THEN
	SET @geoGraphicalFocusQuery:= CONCAT('SELECT COUNT(application_id) INTO @isGeoGraphicalFocusMatch FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId,'
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
SET @currentRatioFSQuery:= CONCAT('SELECT (IF(ISNULL(current_ratio),0,current_ratio)) INTO @currentRatioFS FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND is_active = 1
						AND YEAR= ',@itrYear,' ORDER BY id DESC LIMIT 1');
	PREPARE stmt FROM @currentRatioFSQuery ;
	EXECUTE stmt ;
	
IF (@isCurrentratioDisplay = TRUE) THEN
	SET @currentRatioMatchQuery:= CONCAT('SELECT CASE WHEN max_current_ratio=5
						THEN
						(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
						AND ((SELECT (IF(ISNULL(current_ratio),0,current_ratio)) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND is_active = 1
						AND YEAR= ',@itrYear,' ORDER BY id DESC LIMIT 1)) >= (SELECT min_current_ratio FROM
						',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))
						ELSE 
						(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
						AND ((SELECT (IF(ISNULL(current_ratio),0,current_ratio)) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND is_active = 1
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
	 
SET @interestCoverageRatioFSQuery:= CONCAT('SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) INTO @interestCoverageRatioFS FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND is_active = 1 AND YEAR=',@itrYear);
PREPARE stmt FROM @interestCoverageRatioFSQuery ;
EXECUTE stmt ;
	
IF (@isInterestCoverageDisplay = TRUE) THEN
	SET @ebitValueQuery:= CONCAT('SELECT IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) INTO @ebitValue FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND is_active = 1 AND YEAR = ',@itrYear);
	PREPARE stmt FROM @ebitValueQuery ;
	EXECUTE stmt ;
	SET @interestValueQuery:= CONCAT('SELECT IF(ISNULL(interest),0,interest) INTO @interestValue FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND is_active = 1 AND YEAR = ',@itrYear);
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
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1 
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1 
							AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) <= (SELECT max_interest_coverage FROM ',@fpTableName,' 
							WHERE fp_product_id =',@fpProdId,'))
							WHEN max_interest_coverage=10
							THEN
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1
							AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1) >= (SELECT min_interest_coverage FROM ',@fpTableName,'
							WHERE fp_product_id =',@fpProdId,'))
							ELSE
							(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
							AND (SELECT (IF(ISNULL(op_profit_before_intrest),0,op_profit_before_intrest) / IF(ISNULL(interest),0,interest)) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1
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
SET @tolTnwMatchMaxFSQuery:= CONCAT('SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) INTO @tolTnwMatchMaxFS FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND is_active = 1 AND YEAR= ',@itrYear);
	PREPARE stmt FROM @tolTnwMatchMaxFSQuery ;
	EXECUTE stmt ;
	
IF (@isTolTnwDisplay = TRUE) THEN
	SET @tolTnwMatchQuery:= CONCAT('SELECT CASE WHEN max_tol_tnw=10
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND YEAR= ',@itrYear,' AND is_active = 1
					AND (SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) FROM fs_corporate_cma_assets_details
					WHERE application_id = ',@applicationId,' AND is_active = 1
					AND YEAR=',@itrYear,') >= (SELECT min_tol_tnw FROM ',@fpTableName,' WHERE fp_product_id = ',@fpProdId,'))
					ELSE
					(SELECT COUNT(application_id) FROM fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
					AND (SELECT (IF(ISNULL(total_out_side_liability),0,total_out_side_liability)) FROM
					fs_corporate_cma_assets_details WHERE application_id = ',@applicationId,' AND is_active = 1
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
SET @pastYearTurnOverFSQuery:= CONCAT('SELECT (IF(IsNull(domestic_sales),0,domestic_sales)) + (IF(IsNull(export_sales),0,export_sales)) INTO @pastYearTurnOverFS FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1 AND year=',@itrYear);
	PREPARE stmt FROM @pastYearTurnOverFSQuery ;
	EXECUTE stmt ;
	
IF (@isPastYearTurnoverDisplay = TRUE) THEN
	SET @pastYearTurnOverQuery:= CONCAT('SELECT CASE WHEN min_past_turnover=0 && max_past_turnover=1000000000
					THEN
					1
					WHEN min_past_turnover=0
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1
					AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					<= (SELECT max_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					WHEN max_past_turnover=1000000000
					THEN
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1
					AND YEAR=',@itrYear,' ORDER BY id DESC LIMIT 1))
					>= (SELECT min_past_turnover FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))
					ELSE
					(SELECT COUNT(application_id) FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND YEAR=',@itrYear,' AND is_active = 1
					AND ((SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales))  FROM fs_corporate_cma_operating_statement_details WHERE application_id =',@applicationId,' AND is_active = 1
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
SET @businessYearFSQuery:= CONCAT('SELECT business_since_year INTO @businessYear FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId);
	PREPARE stmt FROM @businessYearFSQuery ;
	EXECUTE stmt ;
	
IF (@businessYear IS NULL) THEN
	SET @establishmentYearFSQuery:= CONCAT('SELECT establishment_year INTO @estYear FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId);
		PREPARE stmt FROM @establishmentYearFSQuery ;
		EXECUTE stmt ;
	SET @establishmentMonthFSQuery:= CONCAT('SELECT establishment_month INTO @estMonth FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId);
		PREPARE stmt FROM @establishmentMonthFSQuery ;
		EXECUTE stmt ;
		
	SET @ageOfEstablishmentFSQuery:= CONCAT('SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW()) INTO @ageOfEstablishmentFS');
		PREPARE stmt FROM @ageOfEstablishmentFSQuery ;
		EXECUTE stmt ;
ELSE
	SET @businessMonthFSQuery:= CONCAT('SELECT business_since_month INTO @businessMonth FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId);
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
						THEN (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE application_id = ',@applicationId,'
						AND (SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW())) >=(SELECT min_age_establishment FROM ',@fpTableName,'
						WHERE fp_product_id = ',@fpProdId,')) ELSE (SELECT COUNT(application_id) FROM fs_corporate_applicant_details
						WHERE application_id =',@applicationId,' AND (SELECT TIMESTAMPDIFF(YEAR,"',@estYear,'-01-',@estMonth,'",NOW())) BETWEEN (SELECT min_age_establishment FROM ',@fpTableName,'
						WHERE fp_product_id =',@fpProdId,')  AND (SELECT max_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,')) END INTO @isAgeOfEstablishmentMatch');
		
	ELSE
		SET @ageOfEstablishmentQuery:= CONCAT('SELECT CASE WHEN ((SELECT max_age_establishment FROM @fpTableName WHERE fp_product_id =', @fpProdId,') >= 30 )',
						' THEN (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE application_id =',@applicationId,' AND ',@ageOfEstablishmentFS,' >= ',
						' (SELECT min_age_establishment FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId,'))', 
						' ELSE (SELECT COUNT(application_id) FROM fs_corporate_applicant_details WHERE application_id =',@applicationId,' AND ', @ageOfEstablishmentFS,
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
SET @profitablityHistoryFSQuery:= CONCAT('SELECT COUNT(net_profit_or_loss) INTO @profitablityHistoryFS FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= ',@itrYear,' AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryFSQuery ;
		EXECUTE stmt ;
		
IF (@isProfitabilityHistoryDisplay = TRUE) THEN
	SET @profitablityHistoryFPQuery:= CONCAT('SELECT profitability_history INTO @profitablityHistoryFP FROM ',@fpTableName,' WHERE fp_product_id =',@fpProdId);
	PREPARE stmt FROM @profitablityHistoryFPQuery;
	EXECUTE stmt ;
	IF (@profitablityHistoryFP = 1) THEN 
		SET @isProfitablityHistoryMatch = TRUE;
	ELSEIF (@profitablityHistoryFP = 2) THEN 
		SET @profitablityHistoryOneYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= (',@itrYear,'-2) AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryOneYearQuery ;
		EXECUTE stmt ;
	ELSEIF (@profitablityHistoryFP = 3) THEN 
		SET @profitablityHistoryTwoYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= (',@itrYear,'-1) AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryTwoYearQuery ;
		EXECUTE stmt ;
	ELSEIF (@profitablityHistoryFP = 4) THEN 
		SET @profitablityHistoryThreeYearQuery:= CONCAT('SELECT IF (COUNT(net_profit_or_loss)>0,TRUE,FALSE) INTO @isProfitablityHistoryMatch FROM fs_corporate_cma_operating_statement_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= ',@itrYear,' AND net_profit_or_loss > 0 ORDER BY YEAR');
		PREPARE stmt FROM @profitablityHistoryThreeYearQuery ;
		EXECUTE stmt ;
	END IF;
	
END IF;
-- ===================================== End Positive Profitablity History Matches Variable =====================================  --
-- ===================================== Start Networth Matches Variable =====================================  --
SET @networthFSQuery:= CONCAT('SELECT COUNT(tangible_net_worth) INTO @networthFS FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= ',@itrYear,' AND tangible_net_worth >0 ORDER BY YEAR');
	PREPARE stmt FROM @networthFSQuery ;
	EXECUTE stmt ;
	
IF (@isNetworthDisplay = TRUE) THEN
	SET @networthFPQuery:= CONCAT('SELECT net_worth INTO @networthFP FROM ',@fpTableName,' WHERE fp_product_id =',fpProdId);
		PREPARE stmt FROM @networthFPQuery ;
		EXECUTE stmt ;
		
	IF (@networthFP = 1) THEN 
		SET @isNetworthMatch = TRUE;
	ELSEIF (@networthFP = 2) THEN 
		SET @isNetworthOneYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= (',@itrYear,'-2) AND tangible_net_worth >0 ORDER BY YEAR');
		PREPARE stmt FROM @isNetworthOneYearMatchQuery ;
		EXECUTE stmt ;
	ELSEIF (@networthFP = 3) THEN 
		SET @isNetworthTwoYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= (',@itrYear,'-1) AND tangible_net_worth >0 ORDER BY YEAR');
		PREPARE stmt FROM @isNetworthTwoYearMatchQuery ;
		EXECUTE stmt ;
	ELSEIF (@networthFP = 4) THEN 
		SET @isNetworthThreeYearMatchQuery:= CONCAT('SELECT IF (COUNT(tangible_net_worth)>0,TRUE,FALSE) INTO @isNetworthMatch FROM fs_corporate_cma_assets_details WHERE is_active = 1 AND application_id = ',@applicationId,' AND `year` <= ',@itrYear,' AND tangible_net_worth >0 ORDER BY YEAR');
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
SET @investmentSizeFSQuery:= CONCAT('SELECT ((SELECT max_loan_amount FROM loan_application.application_product_audit WHERE application_id = ',@applicationId,' AND fp_product_id = ',@fpProdId,' AND is_active = 1) * 
							(SELECT d_value FROM denomination_master WHERE id = (SELECT denomination_id FROM fs_loan_application_master WHERE application_id = ',@applicationId,' ))) INTO @investmentSizeFS ');
	PREPARE stmt FROM @investmentSizeFSQuery ;
	EXECUTE stmt ;
	
IF (@isInvestmentSizeDisplay = TRUE) THEN
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
-- ===================================== End Investment Size Matches Variable =====================================  --
-- ===================================== Start Tenure Matches Variable =====================================  --
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
	
SET @netSaleFSQuery:= CONCAT('SELECT (IF(ISNULL(domestic_sales),0,domestic_sales)) + (IF(ISNULL(export_sales),0,export_sales)) INTO @netSaleFS FROM fs_corporate_cma_operating_statement_details WHERE application_id = ',@applicationId,' AND is_active = 1 AND YEAR = ',@itrYear);
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
							WHERE application_id = ',@applicationId,' AND is_active = 1 AND year=',@itrYear);
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
-- ------------------------------------------------------------ End Recalculation ------------------------------------------------------------
-- ===================================== Start Display Values Of Variable =====================================  --
INSERT INTO `application_product_match_data`
SELECT 	  
	  @id
	 ,@applicationId,@fpProdId
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
	 ,@isTenureMatch,@tenureFS,@tenureMinFP,@tenureMaxFP -- Tenure
	 ,@isScoreMatch,@scoreFS,@scoreMinFP,@scoreMaxFP  -- Score
	 ,@isColleteralCoverageMatch,@colleteralCoverageFS,@colleteralCoverageMinFP,@colleteralCoverageMaxFP -- Colleteral Coverage	 
	 ,@isCgtmseCoverageMatch,@cgtmseCoverageFS,@cgtmseCoverageFP -- CGTMSE Coverage
	 ,@isMsmeFundingMatch,@msmeFundingFS,@msmeFundingFP -- MSME Funding
	 ,@isTurnOverMatch,@turnOverFS,@turnOverMinFP,@turnOverMaxFP -- Turn Over
	 ,@isGrossCashAccuralMatch,@grossCashAccuralFS,@grossCashAccuralMinFP,@grossCashAccuralMaxFP -- Gross Cash Accural
	 
	 ,@isIndustrySectorDisplay,@isIndustrySectorMandatory
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
	 ,@matchCalculatedDate,@matchDataStoredDate
	 
	FROM DUAL;
-- ===================================== End Display Values Of Variable =====================================  --
END */$$
DELIMITER ;

/* Procedure structure for procedure `spMatchSaveUpdate` */

/*!50003 DROP PROCEDURE IF EXISTS  `spMatchSaveUpdate` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spMatchSaveUpdate`(IN applicationId BIGINT,IN fpProdId BIGINT,IN id BIGINT,IN matchCalculatedDate DATETIME)
BEGIN
DECLARE CONTINUE HANDLER FOR SQLEXCEPTION
-- ===================================== Start Get Global Variable ===================================== --
		  
SET @applicationId := applicationId;
SET @id := id;
SET @matchCalculatedDate := matchCalculatedDate;
SET @matchDataStoredDate := NOW();
SET @updatedDate := NOW();
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
	 ,is_msme_funding_display,is_msme_funding_mandatory  INTO 
	 
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
	 SET @isTenureMatch= NULL ,@tenureFS= NULL ,@tenureMinFP= NULL ,@tenureMaxFP= NULL; --  Tenure
	 SET @isScoreMatch= NULL ,@scoreFS= NULL ,@scoreMinFP= NULL ,@scoreMaxFP= NULL;  --  Score
	 SET @isColleteralCoverageMatch= NULL ,@colleteralCoverageFS= NULL ,@colleteralCoverageMinFP= NULL ,@colleteralCoverageMaxFP= NULL; --  Colleteral Coverage	 
	 SET @isCgtmseCoverageMatch= NULL ,@cgtmseCoverageFS= NULL ,@cgtmseCoverageFP= NULL; --  CGTMSE Coverage
	 SET @isMsmeFundingMatch= NULL ,@msmeFundingFS= NULL ,@msmeFundingFP= NULL; --  MSME Funding
	 SET @isTurnOverMatch= NULL ,@turnOverFS= NULL ,@turnOverMinFP= NULL ,@turnOverMaxFP= NULL; --  Turn Over
	 SET @isGrossCashAccuralMatch= NULL ,@grossCashAccuralFS= NULL ,@grossCashAccuralMinFP= NULL ,@grossCashAccuralMaxFP= NULL; --  Gross Cash Accural
	
-- ===================================== End Get Product Parameter Variable ===================================== --	
	
	
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
		 
		 IF (@valueOne IS NOT NULL) THEN 
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
-- ------------------------------------------------------------ End Recalculation ------------------------------------------------------------
-- ===================================== Start Update Values Of Variable =====================================  --
UPDATE `application_product_match_values_new`
SET      `isChequeBounceLastOneMonthMatch`=@isChequeBounceLastOneMonthMatch
	 ,`chequeBounceLastOneMonthFS`=@chequeBounceLastOneMonthFS
	 ,`chequeBounceLastOneMonthMinFP`=@chequeBounceLastOneMonthMinFP
	 ,`chequeBounceLastOneMonthMaxFP`=@chequeBounceLastOneMonthMaxFP -- Cheque Bounce Last One Month
	 ,isChequeBounceLastSixMonthMatch=@isChequeBounceLastSixMonthMatch
	 ,chequeBounceLastSixMonthFS=@chequeBounceLastSixMonthFS
	 ,chequeBounceLastSixMonthMinFP=@chequeBounceLastSixMonthMinFP
	 ,chequeBounceLastSixMonthMaxFP=@chequeBounceLastSixMonthMaxFP -- Cheque Bounce Last Six Month
	 ,isIndividualCibilMatch=@isIndividualCibilMatch
	 ,individualCibilFS=@individualCibilFS
	 ,individualCibilFP=@individualCibilFP -- Individual CIBIL
 	 ,isCommercialCibilMatch=@isCommercialCibilMatch
 	 ,commercialCibilFS=@commercialCibilFS
 	 ,commercialCibilFP=@commercialCibilFP  -- Commercial Cibil
 	 ,updatedDate=@updatedDate
WHERE
	id=@id;
		 
-- ===================================== End Update Values Of Variable =====================================  --
END */$$
DELIMITER ;

/* Procedure structure for procedure `spSaveMatches_One` */

/*!50003 DROP PROCEDURE IF EXISTS  `spSaveMatches_One` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spSaveMatches_One`()
BEGIN
        SET @applicationId=NULL;
	SET @fpProdId=NULL;
	SET @id=NULL;
	
	SELECT COUNT(*) INTO @n FROM `application_product_audit` WHERE id BETWEEN 6320001 AND 6340000 AND stage_id=4 and `created_date` != '0000-00-00 00:00:00';
	
	
	SET @i=1;
		WHILE @i<@n DO 
		
		SET @getAppIdFpIdQuery:= CONCAT('SELECT application_id,fp_product_id,id,created_date  INTO @applicationId,@fpProdId,@id,@createdDate FROM `application_product_audit` WHERE id  BETWEEN 6320001 AND 6340000 AND stage_id=4 AND created_date != "0000-00-00 00:00:00" LIMIT ',@i,',1');
		PREPARE stmt FROM @getAppIdFpIdQuery ;
		EXECUTE stmt ;
	       
	        SET @isIdAvailable=FALSE;
	       
                SET @isIdAvailableQuery:= CONCAT('SELECT IF(COUNT(id)>0,TRUE,FALSE) INTO @isIdAvailable FROM `loan_application`.`application_product_match_data` WHERE id = ',@id);
		PREPARE stmt FROM @isIdAvailableQuery ;
		EXECUTE stmt ;
	        
	        IF(@isIdAvailable = FALSE) THEN
			CALL spMatchSave(@applicationId,@fpProdId,@id,@createdDate);
	        END IF;
	    
		SET @i = @i + 1;
		
	END WHILE;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spSaveMatches_Three` */

/*!50003 DROP PROCEDURE IF EXISTS  `spSaveMatches_Three` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spSaveMatches_Three`()
BEGIN
        SET @applicationId=NULL;
	SET @fpProdId=NULL;
	SET @id=NULL;
	
	SELECT COUNT(*) INTO @n FROM `application_product_audit` WHERE id BETWEEN 6300001 AND 6320000 AND stage_id=4 AND `created_date` != '0000-00-00 00:00:00';
	
	
	SET @i=1;
		WHILE @i<@n DO 
		
		SET @getAppIdFpIdQuery:= CONCAT('SELECT application_id,fp_product_id,id,created_date  INTO @applicationId,@fpProdId,@id,@createdDate FROM `application_product_audit` WHERE id  BETWEEN 6300001 AND 6320000 AND stage_id=4 AND created_date != "0000-00-00 00:00:00" LIMIT ',@i,',1');
		PREPARE stmt FROM @getAppIdFpIdQuery ;
		EXECUTE stmt ;
	       
	        SET @isIdAvailable=FALSE;
	       
                SET @isIdAvailableQuery:= CONCAT('SELECT IF(COUNT(id)>0,TRUE,FALSE) INTO @isIdAvailable FROM `loan_application`.`application_product_match_data` WHERE id = ',@id);
		PREPARE stmt FROM @isIdAvailableQuery ;
		EXECUTE stmt ;
	        
	        IF(@isIdAvailable = FALSE) THEN
			CALL spMatchSave(@applicationId,@fpProdId,@id,@createdDate);
	        END IF;
	    
		SET @i = @i + 1;
		
	END WHILE;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spSaveMatches_Two` */

/*!50003 DROP PROCEDURE IF EXISTS  `spSaveMatches_Two` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spSaveMatches_Two`()
BEGIN
        SET @applicationId=NULL;
	SET @fpProdId=NULL;
	SET @id=NULL;
	
	SELECT COUNT(*) INTO @n FROM `application_product_audit` WHERE id BETWEEN 6250001 AND 6300000 AND stage_id=4 AND `created_date` != '0000-00-00 00:00:00';
	
	
	SET @i=1;
		WHILE @i<@n DO 
		
		SET @getAppIdFpIdQuery:= CONCAT('SELECT application_id,fp_product_id,id,created_date  INTO @applicationId,@fpProdId,@id,@createdDate FROM `application_product_audit` WHERE id  BETWEEN 6250001 AND 6300000 AND stage_id=4 AND created_date != "0000-00-00 00:00:00" LIMIT ',@i,',1');
		PREPARE stmt FROM @getAppIdFpIdQuery ;
		EXECUTE stmt ;
	       
	        SET @isIdAvailable=FALSE;
	       
                SET @isIdAvailableQuery:= CONCAT('SELECT IF(COUNT(id)>0,TRUE,FALSE) INTO @isIdAvailable FROM `loan_application`.`application_product_match_data` WHERE id = ',@id);
		PREPARE stmt FROM @isIdAvailableQuery ;
		EXECUTE stmt ;
	        
	        IF(@isIdAvailable = FALSE) THEN
			CALL spMatchSave(@applicationId,@fpProdId,@id,@createdDate);
	        END IF;
	    
		SET @i = @i + 1;
		
	END WHILE;
	
    END */$$
DELIMITER ;

/* Procedure structure for procedure `spUpdateOfflineSanctionedFlag` */

/*!50003 DROP PROCEDURE IF EXISTS  `spUpdateOfflineSanctionedFlag` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `spUpdateOfflineSanctionedFlag`(IN appId INT,IN orgId INT,IN branchId INT,IN userId INT,OUT result BOOLEAN)
BEGIN
	DECLARE vb_inl_primary_id INTEGER;
	DECLARE vb_gstin VARCHAR(50);
	DECLARE vb_inprinciple_date_range INT;
	DECLARE vb_inprinciple_after_date_range INT;
	DECLARE vb_inl_created_date DATETIME;
	
	SELECT CAST(`value` AS UNSIGNED) INTO vb_inprinciple_date_range FROM `loan_application`.`common_properties` WHERE `key` = 'CONNECT_MSME_INPRINCIPLE_DATE_RANGE';
	SET vb_inprinciple_after_date_range = 0 - vb_inprinciple_date_range; 
	
	SELECT id,SUBSTR(`gstin`,3,10),created_date INTO vb_inl_primary_id,vb_gstin,vb_inl_created_date FROM `loan_application`.`ineligible_proposal_details` WHERE `application_id` = appId AND `user_org_id` = orgId AND `branch_id` = branchId AND `is_active` = TRUE;
	
	SET result = FALSE;
	IF vb_inl_primary_id > 0  THEN
		-- IF FOUND ENTRY BY BRANK,BRANCH AND APPLICATION ID THEN NEED TO UPDATE CURRENT ROW IS SANCTIONED
		-- STATUS 2 MEAND APPLICATION IS SANCTIONED
		UPDATE `loan_application`.`ineligible_proposal_details` SET `is_sanctioned` = TRUE,`status` = 2,`modified_by` = userId,`modified_date` = NOW() WHERE id = vb_inl_primary_id;
		-- NOW UPDATE ALL OTHER BANK ENTRY ON SAME GSTIN TO ITS ALREADY SANCTIONED BY OTHER BANK
		-- STATUS 6 MEANS SANCTIONED BY OTHER BRANCH
		UPDATE `loan_application`.`ineligible_proposal_details` SET `status` = 6,`modified_by` = userId,`modified_date` = NOW(),is_active = FALSE WHERE SUBSTR(`gstin`,3,10) = vb_gstin AND `is_active` = TRUE AND user_org_id = orgId  AND id != vb_inl_primary_id AND created_date BETWEEN (DATE_SUB(vb_inl_created_date, INTERVAL vb_inprinciple_date_range DAY)) AND (DATE_SUB(vb_inl_created_date, INTERVAL vb_inprinciple_after_date_range DAY));
		-- STATUS 5 MEANS SANCTIONED BY OTHER BANK
		UPDATE `loan_application`.`ineligible_proposal_details` SET `status` = 5,`modified_by` = userId,`modified_date` = NOW(),is_active = FALSE WHERE SUBSTR(`gstin`,3,10) = vb_gstin AND `is_active` = TRUE AND user_org_id != orgId  AND id != vb_inl_primary_id AND created_date BETWEEN (DATE_SUB(vb_inl_created_date, INTERVAL vb_inprinciple_date_range DAY)) AND (DATE_SUB(vb_inl_created_date, INTERVAL vb_inprinciple_after_date_range DAY));
		SET result = TRUE;
	END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_dfs_disbursed_fetch` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_dfs_disbursed_fetch` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `sp_dfs_disbursed_fetch`()
BEGIN
	SELECT sd.state_code,sd.district_code,sd.state_name,sd.district_name,IFNULL(COUNT(mis.`application_id`),0),SUM(IFNULL(mis.`disbursed_amount`,0))
	FROM `reports`.`state_district` sd
	LEFT JOIN `tableau`.`vw_mis_report` mis 
	ON mis.`registered_distinct_name` = sd.district_name AND sd.cw_state_code = mis.registered_state_id
	AND mis.`stage_id` IN (7,9) AND mis.`disbursed_date` BETWEEN DATE(DATE_SUB(NOW() ,INTERVAL 2 DAY)) AND DATE(DATE_SUB(NOW() ,INTERVAL 1 DAY))
	GROUP BY sd.state_name,sd.district_name;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_dfs_in_principle_fetch` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_dfs_in_principle_fetch` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `sp_dfs_in_principle_fetch`()
BEGIN
	SELECT sd.state_code,sd.district_code,sd.state_name,sd.district_name,IFNULL(COUNT(mis.`application_id`),0),SUM(IFNULL(mis.`el_amount`,0))
	FROM `reports`.`state_district` sd
	LEFT JOIN `tableau`.`vw_mis_report` mis 
	ON mis.`registered_distinct_name` = sd.district_name AND sd.cw_state_code = mis.registered_state_id
	AND mis.`stage_id` IN (7,9) AND mis.`modified_date` BETWEEN DATE(DATE_SUB(NOW() ,INTERVAL 2 DAY)) AND DATE(DATE_SUB(NOW() ,INTERVAL 1 DAY))
	GROUP BY sd.state_name,sd.district_name;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_dfs_offline_disbursed_fetch` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_dfs_offline_disbursed_fetch` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `sp_dfs_offline_disbursed_fetch`()
BEGIN
	SELECT sd.state_code,sd.district_code,sd.state_name,sd.district_name,IFNULL(COUNT(mis.`application_id`),0),SUM(IFNULL(mis.`disbursed_amount`,0))
	FROM `reports`.`state_district` sd
	LEFT JOIN `tableau`.`vw_mis_report` mis 
	ON mis.`registered_distinct_name` = sd.district_name AND sd.cw_state_code = mis.registered_state_id
	AND mis.`stage_id` not IN (7,9) AND mis.`disbursed_date` BETWEEN DATE(DATE_SUB(NOW() ,INTERVAL 2 DAY)) AND DATE(DATE_SUB(NOW() ,INTERVAL 1 DAY))
	GROUP BY sd.state_name,sd.district_name;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_dfs_offline_sanction_fetch` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_dfs_offline_sanction_fetch` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `sp_dfs_offline_sanction_fetch`()
BEGIN
	SELECT sd.state_code,sd.district_code,sd.state_name,sd.district_name,IFNULL(COUNT(mis.`application_id`),0),SUM(IFNULL(mis.`sanction_amount`,0))
	FROM `reports`.`state_district` sd
	LEFT JOIN `tableau`.`vw_mis_report` mis 
	ON mis.`registered_distinct_name` = sd.district_name AND sd.cw_state_code = mis.registered_state_id
	AND mis.`stage_id` not IN (7,9) AND mis.`sanction_date` BETWEEN DATE(DATE_SUB(NOW() ,INTERVAL 2 DAY)) AND DATE(DATE_SUB(NOW() ,INTERVAL 1 DAY))
	GROUP BY sd.state_name,sd.district_name;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `sp_dfs_sanction_fetch` */

/*!50003 DROP PROCEDURE IF EXISTS  `sp_dfs_sanction_fetch` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`dbsidbi`@`%` PROCEDURE `sp_dfs_sanction_fetch`()
BEGIN
	SELECT sd.state_code,sd.district_code,sd.state_name,sd.district_name,IFNULL(COUNT(mis.`application_id`),0),SUM(IFNULL(mis.`sanction_amount`,0))
	FROM `reports`.`state_district` sd
	LEFT JOIN `tableau`.`vw_mis_report` mis 
	ON mis.`registered_distinct_name` = sd.district_name AND sd.cw_state_code = mis.registered_state_id
	AND mis.`stage_id` IN (7,9) AND mis.`sanction_date` BETWEEN DATE(DATE_SUB(NOW() ,INTERVAL 2 DAY)) AND DATE(DATE_SUB(NOW() ,INTERVAL 1 DAY))
	GROUP BY sd.state_name,sd.district_name;
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
