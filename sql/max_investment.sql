/*
SQLyog Ultimate v11.5 (64 bit)
MySQL - 5.7.23-log : Database - loan_application
*********************************************************************
*/
USE `loan_application`;

/*Table structure for table `max_investment_bankwise` */

DROP TABLE IF EXISTS `max_investment_bankwise`;

CREATE TABLE `max_investment_bankwise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `org_id` bigint(20) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `max_investment_size` double DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DELIMITER $$

USE `loan_application`$$

DROP PROCEDURE IF EXISTS `spToGetMaxLoanAmountOrgWise`$$

CREATE PROCEDURE `spToGetMaxLoanAmountOrgWise`()
BEGIN
	-- for update old record of day
	UPDATE `loan_application`.`max_investment_bankwise` SET is_active=FALSE WHERE `modified_date`>= CURDATE();
	-- org calc start
	-- for insert organisation ratio
	SELECT COUNT(user_org_id) INTO @countV1 FROM users.`user_organisation_master`;
	SET @i = 0;
			
	WHILE(@i<@countV1)DO
		SET @getuserOrgIdQuery:= CONCAT('SELECT user_org_id,organisation_code INTO @uId,@uCode FROM users.`user_organisation_master` LIMIT ',@i,',1');
		PREPARE stmt FROM @getuserOrgIdQuery ;
		EXECUTE stmt ;
		
		SET @getfpprodcount:=CONCAT('SELECT count(fp.fp_product_id) into @prodcount FROM `loan_application`.`fp_product_master` fp 					 
					  WHERE fp.user_org_id=',@uId,' AND fp.product_id IN(1,2,16) AND `campaign_type` IN(2,3) AND fp.is_active=TRUE');
		PREPARE stmt FROM @getfpprodcount ;
		EXECUTE stmt ;	
		
		SET @j = 0;
		SET @maxValForBank = 0;
		WHILE(@j < @prodcount)DO
			SET @getfpvalues:=CONCAT('SELECT fp.fp_product_id,fp.product_id,la.loan_arrangement_id,fp.wc_renewal_status into @fpId,@prodTypeId,@loanArrType,@wcRenewalStatus FROM `loan_application`.`fp_product_master` fp 
						INNER JOIN `loan_application`.`fp_loan_arrangement_value_mapping` la ON la.`fp_product_id`=fp.`fp_product_id` AND la.`is_active`=TRUE AND la.`loan_arrangement_id` IN (2,3)
						WHERE fp.user_org_id=',@uId,' AND fp.product_id IN(1,2,16) AND `campaign_type` IN(2,3) AND fp.is_active=TRUE ORDER BY fp.`fp_product_id` limit ',@j,',1');
			PREPARE stmt FROM @getfpvalues ;
			EXECUTE stmt ;
		
			IF (@prodTypeId = 1)THEN -- wc
				IF (@loanArrType = 3)THEN -- sole banking
					SET @getfpmaxval:=CONCAT('SELECT `max_total_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_working_capital_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;
				ELSEIF (@loanArrType = 2)THEN -- additional banking
					SET @getfpmaxval:=CONCAT('SELECT `max_additional_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_working_capital_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;
				ELSEIF (@wc_renewal_status = 2) THEN -- renewal
					SET @getfpmaxval:=CONCAT('SELECT `max_invest_size` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_working_capital_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;
				END IF;
					
		
			ELSEIF (@prodTypeId = 2)THEN -- tl
				IF (@loanArrType = 3)THEN -- sole banking
					SET @getfpmaxval:=CONCAT('SELECT `max_total_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_term_loan_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;			
				ELSEIF (@loanArrType = 2)THEN -- additional banking
					SET @getfpmaxval:=CONCAT('SELECT `max_additional_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_term_loan_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;
				END IF;				
			ELSEIF (@prodTypeId = 16)THEN -- wctl
				IF (@loanArrType = 3)THEN -- sole banking
					SET @getfpmaxval:=CONCAT('SELECT `max_total_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_wc_tl_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;	
				ELSEIF (@loanArrType = 2)THEN -- additional banking
					SET @getfpmaxval:=CONCAT('SELECT `max_additional_loan` * (SELECT d_value FROM denomination_master WHERE id=denomination) into @fpMaxVal FROM `loan_application`.`fp_wc_tl_details` WHERE `fp_product_id`=',@fpId);
					PREPARE stmt FROM @getfpmaxval ;
					EXECUTE stmt ;	
				END IF;					
			END IF;
			
			IF (@maxValForBank<@fpMaxVal)THEN
				SET @maxValForBank = @fpMaxVal;
			END IF;
			
			SET @j = @j + 1;
			
		END WHILE;
		
		INSERT INTO `loan_application`.`max_investment_bankwise`(org_id,CODE,max_investment_size,created_date,modified_date) VALUES(@uId,@uCode,@maxValForBank,NOW(),NOW());
		
		SET @i = @i + 1;		
	END WHILE;	
	
    END$$

DELIMITER ;