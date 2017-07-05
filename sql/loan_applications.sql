/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.17 : Database - preprod_loan_applications
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


/*Table structure for table `application_sequence` */

DROP TABLE IF EXISTS `application_sequence`;

CREATE TABLE `application_sequence` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) DEFAULT NULL,
  `sequence_number` BIGINT(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `application_sequence` */

INSERT  INTO `application_sequence`(`id`,`product_id`,`sequence_number`) VALUES (1,1,10009),(2,2,10006),(3,3,10004),(4,7,10002),(5,12,10001),(6,13,10001),(7,14,10000);

/*Table structure for table `credit_rating_organization_term_mapping` */

DROP TABLE IF EXISTS `credit_rating_organization_term_mapping`;

CREATE TABLE `credit_rating_organization_term_mapping` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `rating` VARCHAR(255) DEFAULT NULL,
  `credit_rating_organization_term_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `priority` INT(10) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1C0982391FEAEB2D` (`credit_rating_organization_term_id`),
  CONSTRAINT `credit_rating_organization_term_mapping_ibfk_1` FOREIGN KEY (`credit_rating_organization_term_id`) REFERENCES `credit_rating_organization_term_master` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `credit_rating_organization_term_mapping` */

INSERT  INTO `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) VALUES (3,'AAA',1,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(4,'AA',1,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(5,'AA+',1,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(6,'AA-\r\n',1,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(7,'A',1,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(8,'A+',1,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(9,'A-\r\n',1,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(10,'BBB',1,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(11,'BBB+',1,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(12,'BBB-\r\n',1,10,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(13,'BB',1,12,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(14,'BB+',1,11,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(15,'BB-',1,13,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(16,'B',1,15,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(17,'B+\r\n',1,14,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(18,'B-',1,16,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(19,'C',1,18,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(20,'C+\r\n',1,17,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(21,'C-',1,19,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(22,'D',1,20,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(23,'A1',2,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(24,'A1+',2,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(25,'A2',2,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(26,'A2+',2,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(27,'A3',2,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(28,'A3+',2,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(29,'A4',2,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(30,'A4+',2,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(31,'D\r\n',2,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(36,'1',3,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(37,'2',3,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(38,'3',3,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(39,'4',3,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(40,'5',3,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(41,'6',3,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(42,'7',3,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(43,'8',3,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(44,'CCR AAA',4,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(45,'CCR AA+',4,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(46,'CCR AA',4,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(47,'CCR AA-',4,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(48,'CCR A+',4,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(49,'CCR A',4,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(50,'CCR A-',4,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(51,'CCR BBB+',4,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(52,'CCR BBB',4,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(53,'CCR BBB-',4,10,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(54,'CCR BB+',4,11,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(55,'CCR BB',4,12,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(56,'CCR BB-',4,13,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(57,'CCR B+',4,14,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(58,'CCR B',4,15,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(59,'CCR B-',4,16,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(60,'CCR C+',4,17,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(61,'CCR C',4,18,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(62,'CCR C-',4,19,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(63,'D',4,20,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1);

/*Table structure for table `credit_rating_organization_term_master` */

DROP TABLE IF EXISTS `credit_rating_organization_term_master`;

CREATE TABLE `credit_rating_organization_term_master` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `credit_rating_organization_term_master` */

INSERT  INTO `credit_rating_organization_term_master`(`id`,`name`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) VALUES (1,'Long Term','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(2,'Short Term','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(3,'SME','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1),(4,'Corporate Credit rating(CCR)','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,1);

/*Table structure for table `denomination_master` */

DROP TABLE IF EXISTS `denomination_master`;

CREATE TABLE `denomination_master` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) DEFAULT NULL,
  `d_value` INT(255) DEFAULT NULL,
  `orderNumber` INT(2) NOT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `denomination_master` */

INSERT  INTO `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) VALUES (1,'Lakhs',100000,1,'2016-11-08 18:10:23','2016-11-08 18:10:23',NULL,NULL,1),(2,'Millions',1000000,2,'2016-11-08 18:10:23','2016-11-08 18:10:34',NULL,NULL,1),(3,'Crores',10000000,3,'2016-11-08 18:10:23','2016-11-08 18:10:39',NULL,NULL,1),(4,'Billions',100000000,4,'2016-11-08 18:10:23','2016-11-08 18:10:44',NULL,NULL,1),(5,'Absolute',1,5,'2016-11-17 18:51:11','2016-11-17 18:51:11',NULL,NULL,1);

/*Table structure for table `fp_car_loan_details` */

DROP TABLE IF EXISTS `fp_car_loan_details`;

CREATE TABLE `fp_car_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `min_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `max_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `min_yearly_income_range` DOUBLE DEFAULT NULL,
  `max_yearly_income_range` DOUBLE DEFAULT NULL,
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,
  `min_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `max_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `is_loan_amount_display` BIT(1) DEFAULT b'0',
  `is_loan_amount_mandatory` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_display` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` BIT(1) DEFAULT b'0',
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',
  `is_asset_value_display` BIT(1) DEFAULT b'0',
  `is_asset_value_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_car_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `fp_car_loan_details` */

/*Table structure for table `fp_geographical_city_details` */

DROP TABLE IF EXISTS `fp_geographical_city_details`;

CREATE TABLE `fp_geographical_city_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `city_id` BIGINT(20) DEFAULT NULL,
  `fp_product_master_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master_id`),
  CONSTRAINT `fp_geographical_city_details_ibfk_1` FOREIGN KEY (`fp_product_master_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB AUTO_INCREMENT=2770 DEFAULT CHARSET=latin1;


/*Table structure for table `fp_geographical_country_details` */

DROP TABLE IF EXISTS `fp_geographical_country_details`;

CREATE TABLE `fp_geographical_country_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `country_id` BIGINT(10) DEFAULT NULL,
  `fp_product_master` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_country_details_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB AUTO_INCREMENT=2768 DEFAULT CHARSET=latin1;

/*Table structure for table `fp_geographical_state_details` */

DROP TABLE IF EXISTS `fp_geographical_state_details`;

CREATE TABLE `fp_geographical_state_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `state_id` BIGINT(20) DEFAULT NULL,
  `fp_product_master` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_state_details_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB AUTO_INCREMENT=2769 DEFAULT CHARSET=latin1;

/*Table structure for table `fp_home_loan_details` */

DROP TABLE IF EXISTS `fp_home_loan_details`;

CREATE TABLE `fp_home_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `min_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `max_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `min_yearly_income_range` DOUBLE DEFAULT NULL,
  `max_yearly_income_range` DOUBLE DEFAULT NULL,
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,
  `min_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `max_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `is_loan_amount_display` BIT(1) DEFAULT b'0',
  `is_loan_amount_mandatory` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_display` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` BIT(1) DEFAULT b'0',
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',
  `is_asset_value_display` BIT(1) DEFAULT b'0',
  `is_asset_value_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_home_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fp_loan_against_property_details` */

DROP TABLE IF EXISTS `fp_loan_against_property_details`;

CREATE TABLE `fp_loan_against_property_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `min_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `max_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `min_yearly_income_range` DOUBLE DEFAULT NULL,
  `max_yearly_income_range` DOUBLE DEFAULT NULL,
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,
  `min_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `max_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `is_loan_amount_display` BIT(1) DEFAULT b'0',
  `is_loan_amount_mandatory` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_display` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` BIT(1) DEFAULT b'0',
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',
  `is_asset_value_display` BIT(1) DEFAULT b'0',
  `is_asset_value_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_loan_against_property_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fp_loan_against_share_details` */

DROP TABLE IF EXISTS `fp_loan_against_share_details`;

CREATE TABLE `fp_loan_against_share_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `min_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `max_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `min_yearly_income_range` DOUBLE DEFAULT NULL,
  `max_yearly_income_range` DOUBLE DEFAULT NULL,
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,
  `min_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `max_asset_value` DECIMAL(19,2) DEFAULT NULL,
  `tenure_month` INT(5) DEFAULT NULL,
  `tenure_year` INT(5) DEFAULT NULL,
  `is_loan_amount_display` BIT(1) DEFAULT b'0',
  `is_loan_amount_mandatory` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_display` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` BIT(1) DEFAULT b'0',
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',
  `is_asset_value_display` BIT(1) DEFAULT b'0',
  `is_asset_value_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_loan_against_share_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `fp_loan_against_share_details` */

/*Table structure for table `fp_personal_loan_details` */

DROP TABLE IF EXISTS `fp_personal_loan_details`;

CREATE TABLE `fp_personal_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `min_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `max_loan_amount` DECIMAL(19,2) DEFAULT NULL,
  `min_yearly_income_range` DOUBLE DEFAULT NULL,
  `max_yearly_income_range` DOUBLE DEFAULT NULL,
  `min_age` DOUBLE DEFAULT NULL,
  `max_age` DOUBLE DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `is_loan_amount_display` BIT(1) DEFAULT b'0',
  `is_loan_amount_mandatory` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_display` BIT(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` BIT(1) DEFAULT b'0',
  `is_age_display` BIT(1) DEFAULT b'0',
  `is_age_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  `is_asset_value_display` BIT(1) DEFAULT b'0',
  `is_asset_value_mandatory` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_personal_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fp_product_master` */

DROP TABLE IF EXISTS `fp_product_master`;

CREATE TABLE `fp_product_master` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(11) UNSIGNED DEFAULT NULL,
  `user_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `start_date` DATETIME DEFAULT NULL,
  `end_date` DATETIME DEFAULT NULL,
  `fp_name` VARCHAR(50) DEFAULT NULL,
  `name` VARCHAR(50) DEFAULT NULL,
  `is_parameter_filled` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  `product_code` VARCHAR(20) DEFAULT NULL,
  `is_matched` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`)
) ENGINE=INNODB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;


/*Table structure for table `fp_term_loan_details` */

DROP TABLE IF EXISTS `fp_term_loan_details`;

CREATE TABLE `fp_term_loan_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `denomination` INT(2) DEFAULT NULL,
  `min_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `max_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `long_term_credit_rating` INT(5) DEFAULT NULL,
  `short_term_credit_rating` INT(5) DEFAULT NULL,
  `min_age_establishment` INT(5) DEFAULT NULL,
  `max_age_establishment` INT(5) DEFAULT NULL,
  `profitability_history` INT(2) DEFAULT NULL,
  `min_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `max_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `min_debt_equity` INT(3) DEFAULT NULL,
  `max_debt_equity` INT(3) DEFAULT NULL,
  `min_collateral` INT(3) DEFAULT NULL,
  `max_collateral` INT(3) DEFAULT NULL,
  `min_networth` DECIMAL(19,2) DEFAULT NULL,
  `max_networth` DECIMAL(19,2) DEFAULT NULL,
  `uninterested_industry` BIGINT(20) DEFAULT NULL,
  `is_industry_sector_display` BIT(1) DEFAULT b'0',
  `is_industry_sector_mandatory` BIT(1) DEFAULT b'0',
  `is_investment_size_display` BIT(1) DEFAULT b'0',
  `is_investment_size_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `is_credit_rating_display` BIT(1) DEFAULT b'0',
  `is_credit_rating_mandatory` BIT(1) DEFAULT b'0',
  `is_establishment_display` BIT(1) DEFAULT b'0',
  `is_establishment_mandatory` BIT(1) DEFAULT b'0',
  `is_profitability_history_display` BIT(1) DEFAULT b'0',
  `is_profitability_history_mandatory` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_display` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` BIT(1) DEFAULT b'0',
  `is_debt_equity_display` BIT(1) DEFAULT b'0',
  `is_debt_equity_mandatory` BIT(1) DEFAULT b'0',
  `is_collateral_display` BIT(1) DEFAULT b'0',
  `is_collateral_mandatory` BIT(1) DEFAULT b'0',
  `is_networth_display` BIT(1) DEFAULT b'0',
  `is_networth_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_term_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `fp_working_capital_details` */

DROP TABLE IF EXISTS `fp_working_capital_details`;

CREATE TABLE `fp_working_capital_details` (
  `fp_product_id` BIGINT(20) UNSIGNED NOT NULL,
  `currency` INT(2) DEFAULT NULL,
  `denomination` INT(2) DEFAULT NULL,
  `min_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `max_invest_size` DECIMAL(19,2) DEFAULT NULL,
  `min_tenure` DOUBLE DEFAULT NULL,
  `max_tenure` DOUBLE DEFAULT NULL,
  `long_term_credit_rating` INT(5) DEFAULT NULL,
  `short_term_credit_rating` INT(5) DEFAULT NULL,
  `min_age_establishment` INT(5) DEFAULT NULL,
  `max_age_establishment` INT(5) DEFAULT NULL,
  `profitability_history` INT(2) DEFAULT NULL,
  `min_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `max_past_turnover` DECIMAL(19,2) DEFAULT NULL,
  `min_debt_equity` INT(3) DEFAULT NULL,
  `max_debt_equity` INT(3) DEFAULT NULL,
  `min_collateral` INT(3) DEFAULT NULL,
  `max_collateral` INT(3) DEFAULT NULL,
  `min_networth` DECIMAL(19,2) DEFAULT NULL,
  `max_networth` DECIMAL(19,2) DEFAULT NULL,
  `uninterested_industry` BIGINT(20) DEFAULT NULL,
  `is_industry_sector_display` BIT(1) DEFAULT b'0',
  `is_industry_sector_mandatory` BIT(1) DEFAULT b'0',
  `is_investment_size_display` BIT(1) DEFAULT b'0',
  `is_investment_size_mandatory` BIT(1) DEFAULT b'0',
  `is_tenure_display` BIT(1) DEFAULT b'0',
  `is_tenure_mandatory` BIT(1) DEFAULT b'0',
  `is_geographical_display` BIT(1) DEFAULT b'0',
  `is_geographical_mandatory` BIT(1) DEFAULT b'0',
  `is_credit_rating_display` BIT(1) DEFAULT b'0',
  `is_credit_rating_mandatory` BIT(1) DEFAULT b'0',
  `is_establishment_display` BIT(1) DEFAULT b'0',
  `is_establishment_mandatory` BIT(1) DEFAULT b'0',
  `is_profitability_history_display` BIT(1) DEFAULT b'0',
  `is_profitability_history_mandatory` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_display` BIT(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` BIT(1) DEFAULT b'0',
  `is_debt_equity_display` BIT(1) DEFAULT b'0',
  `is_debt_equity_mandatory` BIT(1) DEFAULT b'0',
  `is_collateral_display` BIT(1) DEFAULT b'0',
  `is_collateral_mandatory` BIT(1) DEFAULT b'0',
  `is_networth_display` BIT(1) DEFAULT b'0',
  `is_networth_mandatory` BIT(1) DEFAULT b'0',
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_working_capital_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fs_application_status_master` */

DROP TABLE IF EXISTS `fs_application_status_master`;

CREATE TABLE `fs_application_status_master` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(20) NOT NULL DEFAULT '',
  `code` VARCHAR(5) NOT NULL DEFAULT '',
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `fs_application_status_master` */

/*Table structure for table `fs_corporate_achievement_details` */

DROP TABLE IF EXISTS `fs_corporate_achievement_details`;

CREATE TABLE `fs_corporate_achievement_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `milestone_achieved_detail` TEXT,
  `year` VARCHAR(6) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_Date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_achievement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=144 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_corporate_applicant_details` */

DROP TABLE IF EXISTS `fs_corporate_applicant_details`;

CREATE TABLE `fs_corporate_applicant_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `organisation_name` VARCHAR(100) DEFAULT '',
  `group_name` VARCHAR(100) DEFAULT '',
  `pan` VARCHAR(10) DEFAULT NULL,
  `landline_no` VARCHAR(20) DEFAULT NULL,
  `constitution_id` INT(2) UNSIGNED DEFAULT NULL,
  `establishment_year` INT(4) UNSIGNED DEFAULT NULL,
  `establishment_month` INT(2) UNSIGNED DEFAULT NULL,
  `website_address` VARCHAR(100) DEFAULT NULL,
  `registered_premise_number` VARCHAR(200) DEFAULT NULL,
  `registered_street_name` VARCHAR(200) DEFAULT NULL,
  `registered_land_mark` VARCHAR(200) DEFAULT NULL,
  `registered_city_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `registered_state_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `registered_country_id` INT(20) UNSIGNED DEFAULT NULL,
  `registered_pincode` INT(10) DEFAULT NULL,
  `same_as` BIT(1) DEFAULT b'0',
  `administrative_premise_number` VARCHAR(100) DEFAULT NULL,
  `administrative_street_name` VARCHAR(100) DEFAULT NULL,
  `administrative_land_mark` VARCHAR(100) DEFAULT NULL,
  `administrative_city_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `administrative_state_id` INT(20) UNSIGNED DEFAULT NULL,
  `administrative_country_id` INT(20) UNSIGNED DEFAULT NULL,
  `administrative_pincode` INT(10) DEFAULT NULL,
  `about_us` TEXT,
  `key_verical_funding` BIGINT(2) DEFAULT NULL,
  `latitude` DOUBLE DEFAULT NULL,
  `longitude` DOUBLE DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `corp_app_application_id_fk1` (`application_id`),
  CONSTRAINT `corp_app_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=1025 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_corporate_associated_concern_details` */

DROP TABLE IF EXISTS `fs_corporate_associated_concern_details`;

CREATE TABLE `fs_corporate_associated_concern_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `invested_amount` DOUBLE DEFAULT NULL,
  `brief_description` TEXT,
  `name` VARCHAR(50) DEFAULT NULL,
  `nature_activity` VARCHAR(200) DEFAULT NULL,
  `nature_association` VARCHAR(200) DEFAULT NULL,
  `turn_over_first_year` DOUBLE DEFAULT NULL,
  `turn_over_second_year` DOUBLE DEFAULT NULL,
  `turn_over_third_year` DOUBLE DEFAULT NULL,
  `currentYear` INT(6) DEFAULT NULL,
  `profit_past_one_year` DOUBLE DEFAULT NULL,
  `profit_past_two_year` DOUBLE DEFAULT NULL,
  `profit_past_three_year` DOUBLE DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_associated_concern_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_availability_proposed_plant_details` */

DROP TABLE IF EXISTS `fs_corporate_availability_proposed_plant_details`;

CREATE TABLE `fs_corporate_availability_proposed_plant_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `description_p_m` VARCHAR(255) DEFAULT NULL,
  `estimated_value` VARCHAR(255) DEFAULT NULL,
  `imported_or_indigenous` VARCHAR(255) DEFAULT NULL,
  `supplier` VARCHAR(255) DEFAULT NULL,
  `use_or_purpose` VARCHAR(255) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_availability_proposed_plant_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_availability_proposed_plant_details` */

/*Table structure for table `fs_corporate_board_of_directors_details` */

DROP TABLE IF EXISTS `fs_corporate_board_of_directors_details`;

CREATE TABLE `fs_corporate_board_of_directors_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `any_special_achievement` TEXT,
  `designation` TEXT,
  `experience` TEXT,
  `functional_duties` TEXT,
  `name` VARCHAR(255) DEFAULT NULL,
  `qualification` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4994491DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_board_of_directors_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_bs_balance_sheet_details` */

DROP TABLE IF EXISTS `fs_corporate_bs_balance_sheet_details`;

CREATE TABLE `fs_corporate_bs_balance_sheet_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `advance_from_customers` DOUBLE DEFAULT NULL,
  `capital_advance` DOUBLE DEFAULT NULL,
  `capital_redemption_reserve` DOUBLE DEFAULT NULL,
  `capital_reserve` DOUBLE DEFAULT NULL,
  `capital_work_in_progress` DOUBLE DEFAULT NULL,
  `contingency_reserve` DOUBLE DEFAULT NULL,
  `current_investments` DOUBLE DEFAULT NULL,
  `debenture_redemption_reserve` DOUBLE DEFAULT NULL,
  `debentures` DOUBLE DEFAULT NULL,
  `deferred_payment_credits` DOUBLE DEFAULT NULL,
  `deferred_tax_asset` DOUBLE DEFAULT NULL,
  `deferred_tax_liability` DOUBLE DEFAULT NULL,
  `deposits_and_installments` DOUBLE DEFAULT NULL,
  `depreciation_to_date` DOUBLE DEFAULT NULL,
  `dividend_payable` DOUBLE DEFAULT NULL,
  `exports` DOUBLE DEFAULT NULL,
  `finished_goods` DOUBLE DEFAULT NULL,
  `fixed_assets` DOUBLE DEFAULT NULL,
  `fixed_deposits_with_banks` DOUBLE DEFAULT NULL,
  `foreign_currency_translation_reserve` DOUBLE DEFAULT NULL,
  `general_reserve` DOUBLE DEFAULT NULL,
  `government_and_other_trustee` DOUBLE DEFAULT NULL,
  `grand_total` DOUBLE DEFAULT NULL,
  `gross_fixed_assets` DOUBLE DEFAULT NULL,
  `hedging_reserve` DOUBLE DEFAULT NULL,
  `intangible_assets` DOUBLE DEFAULT NULL,
  `inventory` DOUBLE DEFAULT NULL,
  `investment_in_associates` DOUBLE DEFAULT NULL,
  `investment_in_quoted` DOUBLE DEFAULT NULL,
  `investment_in_subsidiaries` DOUBLE DEFAULT NULL,
  `long_term_borrowing` DOUBLE DEFAULT NULL,
  `long_term_loans_and_advance` DOUBLE DEFAULT NULL,
  `long_term_provisions` DOUBLE DEFAULT NULL,
  `misc_expences` DOUBLE DEFAULT NULL,
  `non_current_investments` DOUBLE DEFAULT NULL,
  `ordinary_share_capital` DOUBLE DEFAULT NULL,
  `other_consumables_spares` DOUBLE DEFAULT NULL,
  `other_consumables_spares_imported` DOUBLE DEFAULT NULL,
  `other_consumables_spares_indegenous` DOUBLE DEFAULT NULL,
  `other_non_current_liability` DOUBLE DEFAULT NULL,
  `other_than_exports` DOUBLE DEFAULT NULL,
  `others` DOUBLE DEFAULT NULL,
  `others_current_assets` DOUBLE DEFAULT NULL,
  `others_current_liability` DOUBLE DEFAULT NULL,
  `others_non_current_assets` DOUBLE DEFAULT NULL,
  `others_pls_specify` DOUBLE DEFAULT NULL,
  `preference_share_capital` DOUBLE DEFAULT NULL,
  `provision_for_tax` DOUBLE DEFAULT NULL,
  `raw_material` DOUBLE DEFAULT NULL,
  `raw_material_imported` DOUBLE DEFAULT NULL,
  `raw_material_indegenous` DOUBLE DEFAULT NULL,
  `reserves_and_surplus` DOUBLE DEFAULT NULL,
  `revaluation_reserve` DOUBLE DEFAULT NULL,
  `securities_premium_account` DOUBLE DEFAULT NULL,
  `share_application_pending_allotment` DOUBLE DEFAULT NULL,
  `short_term_borrowings` DOUBLE DEFAULT NULL,
  `short_term_loans_and_advances` DOUBLE DEFAULT NULL,
  `statutory_liability_dues` DOUBLE DEFAULT NULL,
  `stock_in_process` DOUBLE DEFAULT NULL,
  `surplus_in_profit_and_loss_account` DOUBLE DEFAULT NULL,
  `term_deposits` DOUBLE DEFAULT NULL,
  `term_loans` DOUBLE DEFAULT NULL,
  `total_current_and_non_current_liability` DOUBLE DEFAULT NULL,
  `trade_payables` DOUBLE DEFAULT NULL,
  `trade_receivables` DOUBLE DEFAULT NULL,
  `unsecured_loans_from_promoters` DOUBLE DEFAULT NULL,
  `year` VARCHAR(100) DEFAULT NULL,
  `debit_balance_pl` DOUBLE DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_bs_balance_sheet_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_bs_balance_sheet_details` */

/*Table structure for table `fs_corporate_bs_profitibility_statement_details` */

DROP TABLE IF EXISTS `fs_corporate_bs_profitibility_statement_details`;

CREATE TABLE `fs_corporate_bs_profitibility_statement_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `admin_and_selling_expenses` DOUBLE DEFAULT NULL,
  `already_paid` DOUBLE DEFAULT NULL,
  `amortisation` DOUBLE DEFAULT NULL,
  `bs_provision` DOUBLE DEFAULT NULL,
  `closing_stock_fg` DOUBLE DEFAULT NULL,
  `closing_stock_wip` DOUBLE DEFAULT NULL,
  `cost_raw_material_consumed` DOUBLE DEFAULT NULL,
  `current_tax` DOUBLE DEFAULT NULL,
  `deferred_tax` DOUBLE DEFAULT NULL,
  `depreciation` DOUBLE DEFAULT NULL,
  `depreciation_and_amortisation` DOUBLE DEFAULT NULL,
  `dividend` DOUBLE DEFAULT NULL,
  `employee_benefit_expenses` DOUBLE DEFAULT NULL,
  `extraordinary_items` DOUBLE DEFAULT NULL,
  `factory_wages` DOUBLE DEFAULT NULL,
  `finance_cost` DOUBLE DEFAULT NULL,
  `gross_operating_revenue` DOUBLE DEFAULT NULL,
  `increase_or_decrease_in_inventory_fg` DOUBLE DEFAULT NULL,
  `increase_or_decrease_in_inventory_wip` DOUBLE DEFAULT NULL,
  `less_any_other_item` DOUBLE DEFAULT NULL,
  `less_excise_duty_or_vat_or_service_tax` DOUBLE DEFAULT NULL,
  `net_sales` DOUBLE DEFAULT NULL,
  `non_operating_expenses` DOUBLE DEFAULT NULL,
  `non_operating_income` DOUBLE DEFAULT NULL,
  `opening_stock_of_fg` DOUBLE DEFAULT NULL,
  `opening_stock_wip` DOUBLE DEFAULT NULL,
  `operating_expenses` DOUBLE DEFAULT NULL,
  `operating_profit_before_depreciation` DOUBLE DEFAULT NULL,
  `operating_profit_before_interest_and_tax` DOUBLE DEFAULT NULL,
  `operating_profit_before_tax` DOUBLE DEFAULT NULL,
  `other_expenses` DOUBLE DEFAULT NULL,
  `other_operating_revenue` DOUBLE DEFAULT NULL,
  `other_pls_specify` DOUBLE DEFAULT NULL,
  `personnel_cost` DOUBLE DEFAULT NULL,
  `power_and_fuel` DOUBLE DEFAULT NULL,
  `profit_after_tax` DOUBLE DEFAULT NULL,
  `profit_before_tax` DOUBLE DEFAULT NULL,
  `provision_for_tax` DOUBLE DEFAULT NULL,
  `purchases_stock_tn_trade` DOUBLE DEFAULT NULL,
  `raw_material_imported` DOUBLE DEFAULT NULL,
  `raw_material_indigenous` DOUBLE DEFAULT NULL,
  `retained_profit` DOUBLE DEFAULT NULL,
  `sales` DOUBLE DEFAULT NULL,
  `sales_domestic` DOUBLE DEFAULT NULL,
  `sales_export` DOUBLE DEFAULT NULL,
  `store_and_spares` DOUBLE DEFAULT NULL,
  `store_and_spares_imported` DOUBLE DEFAULT NULL,
  `store_and_spares_indeigenous` DOUBLE DEFAULT NULL,
  `year` VARCHAR(100) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_bs_profitibility_statement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_bs_profitibility_statement_details` */

/*Table structure for table `fs_corporate_capacity_details` */

DROP TABLE IF EXISTS `fs_corporate_capacity_details`;

CREATE TABLE `fs_corporate_capacity_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `current_installed_capacity` VARCHAR(255) DEFAULT NULL,
  `current_operating_level` VARCHAR(255) DEFAULT NULL,
  `future_capacity` VARCHAR(255) DEFAULT NULL,
  `product_name` VARCHAR(255) DEFAULT NULL,
  `measurement_for_current_installed_capacity` VARCHAR(100) DEFAULT NULL,
  `measurement_for_future_capacity` VARCHAR(100) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_capacity_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_cma_assets_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_assets_details`;

CREATE TABLE `fs_corporate_cma_assets_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `advance_payment_taxes` DOUBLE DEFAULT NULL,
  `advance_to_supplier_raw_materials` DOUBLE DEFAULT NULL,
  `advance_to_suppliers_capital_goods` DOUBLE DEFAULT NULL,
  `any_other` DOUBLE DEFAULT NULL,
  `bad_or_doubtful_expenses` DOUBLE DEFAULT NULL,
  `cash_and_bank_balance` DOUBLE DEFAULT NULL,
  `current_ratio` DOUBLE DEFAULT NULL,
  `deferred_receviables` DOUBLE DEFAULT NULL,
  `deferred_receviables_others` DOUBLE DEFAULT NULL,
  `depreciation_to_date` DOUBLE DEFAULT NULL,
  `export_receivables` DOUBLE DEFAULT NULL,
  `finished_goods` DOUBLE DEFAULT NULL,
  `fixed_deposits_with_banks` DOUBLE DEFAULT NULL,
  `good_will` DOUBLE DEFAULT NULL,
  `government_and_other_trustee` DOUBLE DEFAULT NULL,
  `gross_block` DOUBLE DEFAULT NULL,
  `instalments_deferred` DOUBLE DEFAULT NULL,
  `inventory` DOUBLE DEFAULT NULL,
  `investments` DOUBLE DEFAULT NULL,
  `investments_in_subsidiary` DOUBLE DEFAULT NULL,
  `investments_or_book_debts` DOUBLE DEFAULT NULL,
  `net_block` DOUBLE DEFAULT NULL,
  `net_working_capital` DOUBLE DEFAULT NULL,
  `non_consumable_store_and_spares` DOUBLE DEFAULT NULL,
  `other_consumable_spares` DOUBLE DEFAULT NULL,
  `other_consumable_spares_imported` DOUBLE DEFAULT NULL,
  `other_consumable_spares_indegenous` DOUBLE DEFAULT NULL,
  `other_current_assets` DOUBLE DEFAULT NULL,
  `other_non_current_assets` DOUBLE DEFAULT NULL,
  `others` DOUBLE DEFAULT NULL,
  `patents` DOUBLE DEFAULT NULL,
  `prelim_expenses` DOUBLE DEFAULT NULL,
  `raw_material` DOUBLE DEFAULT NULL,
  `raw_material_imported` DOUBLE DEFAULT NULL,
  `raw_material_indegenous` DOUBLE DEFAULT NULL,
  `receivable_other_than_defferred` DOUBLE DEFAULT NULL,
  `stock_in_process` DOUBLE DEFAULT NULL,
  `tangible_net_worth` DOUBLE DEFAULT NULL,
  `total_assets` DOUBLE DEFAULT NULL,
  `total_current_assets` DOUBLE DEFAULT NULL,
  `total_intangible_assets` DOUBLE DEFAULT NULL,
  `total_other_non_current_assets` DOUBLE DEFAULT NULL,
  `total_out_side_liability` DOUBLE DEFAULT NULL,
  `total_term_liability` DOUBLE DEFAULT NULL,
  `year` VARCHAR(50) DEFAULT NULL,
  `deferred_tax_assets` DOUBLE DEFAULT NULL,
  `intangible_assets` DOUBLE DEFAULT NULL,
  `storage_details_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_assets_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_cma_liabilities_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_liabilities_details`;

CREATE TABLE `fs_corporate_cma_liabilities_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `advance_payments_from_customers` DOUBLE DEFAULT NULL,
  `debentures` DOUBLE DEFAULT NULL,
  `deferred_payments_credits` DOUBLE DEFAULT NULL,
  `deferred_tax_liability` DOUBLE DEFAULT NULL,
  `deposits_or_instalments_of_term_loans` DOUBLE DEFAULT NULL,
  `dividend_payable` DOUBLE DEFAULT NULL,
  `from_application_bank` DOUBLE DEFAULT NULL,
  `from_other_banks` DOUBLE DEFAULT NULL,
  `general_reserve` DOUBLE DEFAULT NULL,
  `net_worth` DOUBLE DEFAULT NULL,
  `which_bp_and_bd` DOUBLE DEFAULT NULL,
  `ordinary_shares_capital` DOUBLE DEFAULT NULL,
  `other_current_liability` DOUBLE DEFAULT NULL,
  `other_reservse` DOUBLE DEFAULT NULL,
  `other_statutory_liability` DOUBLE DEFAULT NULL,
  `other_term_liabilies` DOUBLE DEFAULT NULL,
  `others` DOUBLE DEFAULT NULL,
  `preferences_shares` DOUBLE DEFAULT NULL,
  `provisional_for_taxation` DOUBLE DEFAULT NULL,
  `revaluation_reservse` DOUBLE DEFAULT NULL,
  `short_term_borrowing_from_others` DOUBLE DEFAULT NULL,
  `sub_total_a` DOUBLE DEFAULT NULL,
  `sub_total_b` DOUBLE DEFAULT NULL,
  `sundry_creditors` DOUBLE DEFAULT NULL,
  `surplus_or_deficit` DOUBLE DEFAULT NULL,
  `term_deposits` DOUBLE DEFAULT NULL,
  `term_loans` DOUBLE DEFAULT NULL,
  `total_current_liabilities` DOUBLE DEFAULT NULL,
  `total_liability` DOUBLE DEFAULT NULL,
  `total_outside_liabilities` DOUBLE DEFAULT NULL,
  `total_term_liabilities` DOUBLE DEFAULT NULL,
  `year` VARCHAR(255) DEFAULT NULL,
  `storage_details_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_liabilities_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_cma_operating_statement_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_operating_statement_details`;

CREATE TABLE `fs_corporate_cma_operating_statement_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `op_profit_before_intrest` DOUBLE DEFAULT NULL,
  `add_operating_stock` DOUBLE DEFAULT NULL,
  `add_operating_stock_fg` DOUBLE DEFAULT NULL,
  `add_other_non_op_income` DOUBLE DEFAULT NULL,
  `add_other_revenue_income` DOUBLE DEFAULT NULL,
  `production_cost` DOUBLE DEFAULT NULL,
  `deduct_cl_stock_fg` DOUBLE DEFAULT NULL,
  `deduct_other_items` DOUBLE DEFAULT NULL,
  `deduct_other_non_op_exp` DOUBLE DEFAULT NULL,
  `deduct_stock_in_process` DOUBLE DEFAULT NULL,
  `depreciation` DOUBLE DEFAULT NULL,
  `direct_labour` DOUBLE DEFAULT NULL,
  `dividend_rate` DOUBLE DEFAULT NULL,
  `domestic_sales` DOUBLE DEFAULT NULL,
  `equity_deividend_paid_amt` DOUBLE DEFAULT NULL,
  `expenses_amortised` DOUBLE DEFAULT NULL,
  `export_sales` DOUBLE DEFAULT NULL,
  `interest` DOUBLE DEFAULT NULL,
  `less_excise_duty` DOUBLE DEFAULT NULL,
  `netof_non_op_income_or_expenses` DOUBLE DEFAULT NULL,
  `net_profit_or_loss` DOUBLE DEFAULT NULL,
  `net_sales` DOUBLE DEFAULT NULL,
  `op_profit_after_interest` DOUBLE DEFAULT NULL,
  `other_mfg_expenses` DOUBLE DEFAULT NULL,
  `other_spares` DOUBLE DEFAULT NULL,
  `other_spares_imported` DOUBLE DEFAULT NULL,
  `other_spares_indigenous` DOUBLE DEFAULT NULL,
  `percentage_rise_or_fall` DOUBLE DEFAULT NULL,
  `power_and_fuel` DOUBLE DEFAULT NULL,
  `profit_before_tax_or_loss` DOUBLE DEFAULT NULL,
  `provision_for_deferred_tax` DOUBLE DEFAULT NULL,
  `provision_for_taxes` DOUBLE DEFAULT NULL,
  `raw_materials` DOUBLE DEFAULT NULL,
  `raw_materials_imported` DOUBLE DEFAULT NULL,
  `raw_materials_indigenous` DOUBLE DEFAULT NULL,
  `retained_profit` DOUBLE DEFAULT NULL,
  `retained_profit_or_net_profit` DOUBLE DEFAULT NULL,
  `selling_genl_admn_expenses` DOUBLE DEFAULT NULL,
  `sub_total_cost_Sales` DOUBLE DEFAULT NULL,
  `sub_total_cost_sales_and_selling` DOUBLE DEFAULT NULL,
  `sub_total_expenses` DOUBLE DEFAULT NULL,
  `sub_total_of_cost_sales_and_operating_stock` DOUBLE DEFAULT NULL,
  `sub_total_deduct_and_cost_of_production` DOUBLE DEFAULT NULL,
  `sub_total_of_income` DOUBLE DEFAULT NULL,
  `total_cost_sales` DOUBLE DEFAULT NULL,
  `total_gross_sales` DOUBLE DEFAULT NULL,
  `year` VARCHAR(100) DEFAULT NULL,
  `storage_details_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_operating_statement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_credit_rating_organization_details` */

DROP TABLE IF EXISTS `fs_corporate_credit_rating_organization_details`;

CREATE TABLE `fs_corporate_credit_rating_organization_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE(19,2) DEFAULT NULL,
  `credit_rating_fund_id` INT(2) DEFAULT NULL,
  `rating_agency_id` INT(2) DEFAULT NULL,
  `facility_name` TEXT,
  `credit_rating_option_id` INT(2) DEFAULT NULL,
  `credit_rating_term_id` INT(2) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_Date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E277427CFFB7AD9` (`credit_rating_fund_id`),
  KEY `FK8E277427DF197CC7` (`rating_agency_id`),
  KEY `FK8E2774278ADCA19` (`credit_rating_option_id`),
  KEY `FK8E2774271FEAEB2D` (`credit_rating_term_id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_credit_rating_organization_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_current_financial_arrangements_details` */

DROP TABLE IF EXISTS `fs_corporate_current_financial_arrangements_details`;

CREATE TABLE `fs_corporate_current_financial_arrangements_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `financial_institution_name` VARCHAR(100) DEFAULT '',
  `facility_nature_id` INT(2) DEFAULT NULL,
  `amount` DOUBLE DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7018B2C5007A65` (`facility_nature_id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_current_financial_arrangements_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_dpr_user_data_details` */

DROP TABLE IF EXISTS `fs_corporate_dpr_user_data_details`;

CREATE TABLE `fs_corporate_dpr_user_data_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `absence_civic_restrictions` TEXT,
  `labour_availability` TEXT,
  `other_availability` TEXT,
  `power_availability` TEXT,
  `transport_availability` TEXT,
  `water_availability` TEXT,
  `competitive_landscape` TEXT,
  `global_scenario` TEXT,
  `key_players` TEXT,
  `manufacturing_process` TEXT,
  `market_for_the_product` TEXT,
  `market_growth` TEXT,
  `market_needs` TEXT,
  `market_trends` TEXT,
  `markets_currently_served` TEXT,
  `national_scenario` TEXT,
  `shifts_in_day_number` TEXT,
  `working_Days_in_month__number` TEXT,
  `other_benefits` TEXT,
  `project_Jjustification` TEXT,
  `proximity_to_source_raw_materials` TEXT,
  `special_features_products_and_services` TEXT,
  `target_market_strategy` TEXT,
  `technical_know_how` TEXT,
  `whether_clearance_is_obtained_from_pollution_control_authority` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_dpr_user_data_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_driver_for_future_growth_details` */

DROP TABLE IF EXISTS `fs_corporate_driver_for_future_growth_details`;

CREATE TABLE `fs_corporate_driver_for_future_growth_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_string` TEXT,
  `second_string` TEXT,
  `third_string` TEXT,
  `forth_string` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6CFF66DDC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_driver_for_future_growth_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_employees_category_breaks_details` */

DROP TABLE IF EXISTS `fs_corporate_employees_category_breaks_details`;

CREATE TABLE `fs_corporate_employees_category_breaks_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `employment_status_future` VARCHAR(255) DEFAULT NULL,
  `employment_status_present` VARCHAR(255) DEFAULT NULL,
  `employment` VARCHAR(50) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_employees_category_breaks_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_existing_product_details` */

DROP TABLE IF EXISTS `fs_corporate_existing_product_details`;

CREATE TABLE `fs_corporate_existing_product_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product` TEXT,
  `application` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK77968CF1DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_existing_product_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_final_term_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_final_term_loan_details`;

CREATE TABLE `fs_corporate_final_term_loan_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `technology_type_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_technology_tied` BIT(1) DEFAULT b'0',
  `technology_patented_id` INT(2) UNSIGNED DEFAULT NULL,
  `technology_requires_upgradation_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_position_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_positioning_top_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_share_turnover_id` INT(2) UNSIGNED DEFAULT NULL,
  `india_distribution_network_id` INT(2) UNSIGNED DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` INT(2) UNSIGNED DEFAULT NULL,
  `brand_ambassador_id` INT(2) UNSIGNED DEFAULT NULL,
  `marketing_positioning_id` INT(2) UNSIGNED DEFAULT NULL,
  `product_services_perse_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_depends_majorly_on_government` BIT(1) DEFAULT b'0',
  `environment_certification_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_iso_certified` BIT(1) DEFAULT b'0',
  `accounting_systems_id` INT(2) UNSIGNED DEFAULT NULL,
  `internal_audit_id` INT(2) UNSIGNED DEFAULT NULL,
  `competence_id` INT(2) UNSIGNED DEFAULT NULL,
  `existing_share_holders_id` INT(2) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `modified_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `final_term_application_id_fk1` (`application_id`),
  CONSTRAINT `final_term_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;



/*Table structure for table `fs_corporate_final_wc_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_final_wc_loan_details`;

CREATE TABLE `fs_corporate_final_wc_loan_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `technology_type_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_technology_tied` BIT(1) DEFAULT b'0',
  `technology_patented_id` INT(2) UNSIGNED DEFAULT NULL,
  `technology_requires_upgradation_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_position_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_positioning_top_id` INT(2) UNSIGNED DEFAULT NULL,
  `market_share_turnover_id` INT(2) UNSIGNED DEFAULT NULL,
  `india_distribution_network_id` INT(2) UNSIGNED DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` INT(2) UNSIGNED DEFAULT NULL,
  `brand_ambassador_id` INT(2) UNSIGNED DEFAULT NULL,
  `marketing_positioning_id` INT(2) UNSIGNED DEFAULT NULL,
  `product_services_perse_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_depends_majorly_on_government` BIT(1) DEFAULT b'0',
  `environment_certification_id` INT(2) UNSIGNED DEFAULT NULL,
  `is_iso_certified` BIT(1) DEFAULT b'0',
  `accounting_systems_id` INT(2) UNSIGNED DEFAULT NULL,
  `internal_audit_id` INT(2) UNSIGNED DEFAULT NULL,
  `competence_id` INT(2) UNSIGNED DEFAULT NULL,
  `existing_share_holders_id` INT(2) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`application_id`,`id`),
  KEY `application_id` (`application_id`),
  KEY `id` (`id`),
  CONSTRAINT `wc_final_application_id_fk` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;



/*Table structure for table `fs_corporate_finance_means_details` */

DROP TABLE IF EXISTS `fs_corporate_finance_means_details`;

CREATE TABLE `fs_corporate_finance_means_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `already_infused` DOUBLE DEFAULT NULL,
  `total` DOUBLE DEFAULT NULL,
  `to_be_incurred` DOUBLE DEFAULT NULL,
  `finance_means_category_id` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF8CE3B3DC8C6C36` (`application_id`),
  KEY `FKCF8CE3B33C516935` (`finance_means_category_id`),
  CONSTRAINT `fs_corporate_finance_means_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_future_financial_estimates_details` */

DROP TABLE IF EXISTS `fs_corporate_future_financial_estimates_details`;

CREATE TABLE `fs_corporate_future_financial_estimates_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `current_assets` DOUBLE DEFAULT NULL,
  `current_liabilities` DOUBLE DEFAULT NULL,
  `ebitda` DOUBLE DEFAULT NULL,
  `fixed_assets` DOUBLE DEFAULT NULL,
  `long_term_debt` DOUBLE DEFAULT NULL,
  `net_worth` DOUBLE DEFAULT NULL,
  `pat` DOUBLE DEFAULT NULL,
  `sales` DOUBLE DEFAULT NULL,
  `financial_year` VARCHAR(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `debt` DOUBLE DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_future_financial_estimates_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_guarantors_corporate_details` */

DROP TABLE IF EXISTS `fs_corporate_guarantors_corporate_details`;

CREATE TABLE `fs_corporate_guarantors_corporate_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) DEFAULT NULL,
  `occupation` VARCHAR(50) DEFAULT NULL,
  `properties_owned` VARCHAR(50) DEFAULT NULL,
  `property_type` VARCHAR(50) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCA1FBA03DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_guarantors_corporate_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_key_management_details` */

DROP TABLE IF EXISTS `fs_corporate_key_management_details`;

CREATE TABLE `fs_corporate_key_management_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `any_special_achievement` VARCHAR(255) DEFAULT NULL,
  `designation` VARCHAR(255) DEFAULT NULL,
  `experience` VARCHAR(255) DEFAULT NULL,
  `functionalDuties` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `qualification` VARCHAR(255) DEFAULT NULL,
  `application_id` BIGINT(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3AC23CE4DC8C6C36` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_monthly_turnover_details` */

DROP TABLE IF EXISTS `fs_corporate_monthly_turnover_details`;

CREATE TABLE `fs_corporate_monthly_turnover_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE DEFAULT NULL,
  `month_name` VARCHAR(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_Date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB7B50680DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_monthly_turnover_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=251 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_overseas_network_mapping_details` */

DROP TABLE IF EXISTS `fs_corporate_overseas_network_mapping_details`;

CREATE TABLE `fs_corporate_overseas_network_mapping_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `overseas_network_id` INT(2) UNSIGNED DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBD855ADA8BAD821` (`overseas_network_id`),
  KEY `FKBD855AD4549B531` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_ownership_details` */

DROP TABLE IF EXISTS `fs_corporate_ownership_details`;

CREATE TABLE `fs_corporate_ownership_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `stack_percentage` DOUBLE DEFAULT NULL,
  `remarks` TEXT,
  `share_holding_category_id` INT(2) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_Date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF8ADCC2DC8C6C36` (`application_id`),
  KEY `FKFF8ADCC27F8B473` (`share_holding_category_id`),
  CONSTRAINT `fs_corporate_ownership_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_past_financial_estimates_details` */

DROP TABLE IF EXISTS `fs_corporate_past_financial_estimates_details`;

CREATE TABLE `fs_corporate_past_financial_estimates_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `current_assets` DOUBLE DEFAULT NULL,
  `current_liabilities` DOUBLE DEFAULT NULL,
  `debt` DOUBLE DEFAULT NULL,
  `ebitda` DOUBLE DEFAULT NULL,
  `financial_year` VARCHAR(20) DEFAULT NULL,
  `fixed_assets` DOUBLE DEFAULT NULL,
  `net_worth` DOUBLE DEFAULT NULL,
  `pat` DOUBLE DEFAULT NULL,
  `sales` DOUBLE DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_Date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31441620DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_past_financial_estimates_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=232 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_primary_term_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_primary_term_loan_details`;

CREATE TABLE `fs_corporate_primary_term_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `project_brief` TEXT,
  `total_cost_of_estimate` DOUBLE(19,2) DEFAULT NULL,
  `total_means_of_finance` DOUBLE(19,2) DEFAULT NULL,
  `collateral_security_amt_total` DOUBLE(19,2) DEFAULT '0.00',
  `credit_rating_id` INT(2) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `corp_tl_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `fs_corporate_primary_wc_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_primary_wc_loan_details`;

CREATE TABLE `fs_corporate_primary_wc_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `have_existing_limit` BIT(1) DEFAULT NULL,
  `enhancement_of_limit` TEXT,
  `project_brief` TEXT,
  `credit_rating_id` INT(2) UNSIGNED DEFAULT NULL,
  `collateral_security_amt_total` DOUBLE(19,2) DEFAULT '0.00',
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_corporate_primary_wc_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `fs_corporate_project_cost_details` */

DROP TABLE IF EXISTS `fs_corporate_project_cost_details`;

CREATE TABLE `fs_corporate_project_cost_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `already_incurred` DOUBLE DEFAULT NULL,
  `to_be_incurred` DOUBLE DEFAULT NULL,
  `total_cost` DOUBLE DEFAULT NULL,
  `particulars_id` INT(2) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBCFB238FDC8C6C36` (`application_id`),
  KEY `FKBCFB238FA0987F39` (`particulars_id`),
  CONSTRAINT `fs_corporate_project_cost_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_project_implementation_schedule_details` */

DROP TABLE IF EXISTS `fs_corporate_project_implementation_schedule_details`;

CREATE TABLE `fs_corporate_project_implementation_schedule_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `activities` VARCHAR(255) DEFAULT NULL,
  `commencement_date` VARCHAR(255) DEFAULT NULL,
  `completion_date` VARCHAR(255) DEFAULT NULL,
  `timeline_total` VARCHAR(255) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14BCEFC4DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_project_implementation_schedule_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_promotor_background_details` */

DROP TABLE IF EXISTS `fs_corporate_promotor_background_details`;

CREATE TABLE `fs_corporate_promotor_background_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `pan_no` VARCHAR(10) DEFAULT NULL,
  `address` TEXT,
  `age` DOUBLE DEFAULT NULL,
  `promotors_name` VARCHAR(50) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `qualification` TEXT,
  `total_experience` DOUBLE DEFAULT NULL,
  `achivements` TEXT,
  `salutation_id` INT(2) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FK85614096DC8C6C36` (`application_id`),
  KEY `FK856140969461B69B` (`salutation_id`),
  CONSTRAINT `fs_corporate_promotor_background_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_proposed_product_details` */

DROP TABLE IF EXISTS `fs_corporate_proposed_product_details`;

CREATE TABLE `fs_corporate_proposed_product_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product` TEXT,
  `application` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8973B036DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_proposed_product_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_requirements_and_availability_raw_materials_details` */

DROP TABLE IF EXISTS `fs_corporate_requirements_and_availability_raw_materials_details`;

CREATE TABLE `fs_corporate_requirements_and_availability_raw_materials_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `availability` VARCHAR(255) DEFAULT NULL,
  `lead_time` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(2000) DEFAULT NULL,
  `quality` VARCHAR(255) DEFAULT NULL,
  `sources` VARCHAR(255) DEFAULT NULL,
  `measurement_unit__quantity` VARCHAR(100) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fs_ibfk_2` (`application_id`),
  CONSTRAINT `fs_ibfk_2` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_requirements_and_availability_raw_materials_details` */

/*Table structure for table `fs_corporate_revenue_and_order_book_details` */

DROP TABLE IF EXISTS `fs_corporate_revenue_and_order_book_details`;

CREATE TABLE `fs_corporate_revenue_and_order_book_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `clientName` VARCHAR(255) DEFAULT NULL,
  `geography` VARCHAR(255) DEFAULT NULL,
  `orders_in_hand` VARCHAR(255) DEFAULT NULL,
  `potential_orders` VARCHAR(255) DEFAULT NULL,
  `revenues` DOUBLE DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_revenue_and_order_book_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_scot_analysis_details` */

DROP TABLE IF EXISTS `fs_corporate_scot_analysis_details`;

CREATE TABLE `fs_corporate_scot_analysis_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `concerns_details` TEXT,
  `concerns_measure` TEXT,
  `opportunities_detials` TEXT,
  `strength_details` TEXT,
  `weakness_detials` TEXT,
  `weakness_measure` TEXT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_scot_analysis_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_security_corporate_details` */

DROP TABLE IF EXISTS `fs_corporate_security_corporate_details`;

CREATE TABLE `fs_corporate_security_corporate_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE DEFAULT NULL,
  `primary_security_name` VARCHAR(100) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_security_corporate_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_corporate_strategic_alliances_details` */

DROP TABLE IF EXISTS `fs_corporate_strategic_alliances_details`;

CREATE TABLE `fs_corporate_strategic_alliances_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `relationship_details` VARCHAR(255) DEFAULT NULL,
  `name` VARCHAR(255) DEFAULT NULL,
  `key_alliance_partners` VARCHAR(50) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK839769FADC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_strategic_alliances_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=291 DEFAULT CHARSET=utf8;



/*Table structure for table `fs_corporate_technology_positioning_details` */

DROP TABLE IF EXISTS `fs_corporate_technology_positioning_details`;

CREATE TABLE `fs_corporate_technology_positioning_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `details` TEXT,
  `type` VARCHAR(255) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  `storage_details_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_technology_positioning_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=229 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_loan_application_master` */

DROP TABLE IF EXISTS `fs_loan_application_master`;

CREATE TABLE `fs_loan_application_master` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `product_id` INT(11) UNSIGNED DEFAULT NULL,
  `currency_id` INT(2) UNSIGNED DEFAULT NULL,
  `denomination_id` INT(2) UNSIGNED DEFAULT NULL,
  `name` VARCHAR(100) DEFAULT '',
  `status` INT(11) UNSIGNED DEFAULT NULL,
  `tenure` DOUBLE DEFAULT NULL,
  `amount` DECIMAL(19,2) UNSIGNED DEFAULT NULL,
  `denomination_master_id` BIGINT(2) UNSIGNED DEFAULT NULL,
  `category_code` VARCHAR(11) DEFAULT '',
  `user_id` BIGINT(20) DEFAULT NULL,
  `is_applicant_details_filled` BIT(1) DEFAULT b'0',
  `is_applicant_primary_filled` BIT(1) DEFAULT b'0',
  `is_applicant_final_filled` BIT(1) DEFAULT b'0',
  `is_co_app1_details_filled` BIT(1) DEFAULT b'0',
  `is_co_app1_final_filled` BIT(1) DEFAULT b'0',
  `is_co_app2_details_filled` BIT(1) DEFAULT b'0',
  `is_co_app2_final_filled` BIT(1) DEFAULT b'0',
  `is_guarantor1_details_filled` BIT(1) DEFAULT b'0',
  `is_guarantor1_final_filled` BIT(1) DEFAULT b'0',
  `is_guarantor2_details_filled` BIT(1) DEFAULT b'0',
  `is_guarantor2_final_filled` BIT(1) DEFAULT b'0',
  `is_primary_upload_filled` BIT(1) DEFAULT b'0',
  `is_final_mcq_filled` BIT(1) DEFAULT b'0',
  `is_final_dpr_upload_filled` BIT(1) DEFAULT b'0',
  `is_final_upload_filled` BIT(1) DEFAULT b'0',
  `is_primary_locked` BIT(1) DEFAULT b'0',
  `is_final_locked` BIT(1) DEFAULT b'0',
  `details_filled_time` BIGINT(20) DEFAULT NULL,
  `primary_filled_time` BIGINT(20) DEFAULT NULL,
  `final_filled_time` BIGINT(20) DEFAULT NULL,
  `details_filled_count` DECIMAL(19,2) DEFAULT '0.00',
  `primary_filled_count` DECIMAL(19,2) DEFAULT '0.00',
  `final_filled_count` DECIMAL(19,2) DEFAULT '0.00',
  `created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
  `created_by` BIGINT(11) DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `modified_by` BIGINT(11) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  `application_code` VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `status` (`status`),
  KEY `denomination_master_id` (`denomination_master_id`),
  CONSTRAINT `fs_loan_application_master_ibfk_1` FOREIGN KEY (`status`) REFERENCES `fs_application_status_master` (`id`),
  CONSTRAINT `fs_loan_application_master_ibfk_2` FOREIGN KEY (`denomination_master_id`) REFERENCES `denomination_master` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=3054 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_applicant_details` */

DROP TABLE IF EXISTS `fs_retail_applicant_details`;

CREATE TABLE `fs_retail_applicant_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `gender_id` INT(2) DEFAULT NULL,
  `title_id` INT(2) UNSIGNED DEFAULT NULL,
  `first_name` VARCHAR(50) DEFAULT NULL,
  `middle_name` VARCHAR(50) DEFAULT NULL,
  `last_name` VARCHAR(50) DEFAULT NULL,
  `birth_date` DATETIME DEFAULT NULL,
  `status_id` INT(2) UNSIGNED DEFAULT NULL,
  `occupation_id` INT(2) UNSIGNED DEFAULT NULL,
  `company_name` VARCHAR(255) DEFAULT NULL,
  `employed_with_id` INT(2) UNSIGNED DEFAULT NULL,
  `self_employed_occupation_id` INT(2) UNSIGNED DEFAULT NULL,
  `entity_name` VARCHAR(255) DEFAULT NULL,
  `industry_type_id` INT(2) UNSIGNED DEFAULT NULL,
  `land_size` DECIMAL(19,2) DEFAULT NULL,
  `allied_activity_id` INT(2) UNSIGNED DEFAULT NULL,
  `pan` VARCHAR(10) DEFAULT NULL,
  `aadhar_number` VARCHAR(20) DEFAULT NULL,
  `monthly_income` DOUBLE DEFAULT NULL,
  `contact_no` VARCHAR(20) DEFAULT NULL,
  `currency_id` INT(2) UNSIGNED DEFAULT NULL,
  `permanent_premise_number_name` VARCHAR(255) DEFAULT NULL,
  `permanent_street_name` VARCHAR(255) DEFAULT NULL,
  `permanent_land_mark` VARCHAR(255) DEFAULT NULL,
  `permanent_pincode` BIGINT(12) UNSIGNED DEFAULT NULL,
  `permanent_country_id` INT(2) UNSIGNED DEFAULT NULL,
  `permanent_state_id` INT(2) UNSIGNED DEFAULT NULL,
  `permanent_city_id` BIGINT(10) UNSIGNED DEFAULT NULL,
  `office_premise_number_name` VARCHAR(255) DEFAULT NULL,
  `office_street_name` VARCHAR(255) DEFAULT NULL,
  `office_land_mark` VARCHAR(255) DEFAULT NULL,
  `office_pincode` BIGINT(12) UNSIGNED DEFAULT NULL,
  `office_country_id` INT(2) UNSIGNED DEFAULT NULL,
  `office_state_id` INT(2) UNSIGNED DEFAULT NULL,
  `office_city_id` BIGINT(10) UNSIGNED DEFAULT NULL,
  `cast_id` INT(2) UNSIGNED DEFAULT NULL,
  `cast_other` VARCHAR(100) DEFAULT NULL,
  `religion` INT(2) UNSIGNED DEFAULT NULL,
  `religion_other` VARCHAR(100) DEFAULT NULL,
  `birth_place` VARCHAR(100) DEFAULT NULL,
  `father_name` VARCHAR(200) DEFAULT NULL,
  `mother_name` VARCHAR(200) DEFAULT NULL,
  `spouse_name` VARCHAR(100) DEFAULT NULL,
  `is_spouse_employed` BIT(1) DEFAULT NULL,
  `no_children` INT(2) UNSIGNED DEFAULT NULL,
  `no_dependent` INT(2) UNSIGNED DEFAULT NULL,
  `highest_qualification` INT(2) UNSIGNED DEFAULT NULL,
  `highest_qualification_other` VARCHAR(100) DEFAULT NULL,
  `qualifying_year` DATETIME DEFAULT NULL,
  `institute` VARCHAR(100) DEFAULT NULL,
  `residence_type` INT(2) UNSIGNED DEFAULT NULL,
  `annual_rent` DECIMAL(19,2) DEFAULT NULL,
  `residing_year` DOUBLE DEFAULT NULL,
  `residing_month` DOUBLE DEFAULT NULL,
  `website_address` VARCHAR(100) DEFAULT NULL,
  `address_premise_name` VARCHAR(100) DEFAULT NULL,
  `address_street_name` VARCHAR(100) DEFAULT NULL,
  `address_landmark` VARCHAR(100) DEFAULT NULL,
  `address_pincode` BIGINT(10) UNSIGNED DEFAULT NULL,
  `address_country` INT(2) UNSIGNED DEFAULT NULL,
  `address_state` BIGINT(10) UNSIGNED DEFAULT NULL,
  `address_city` BIGINT(10) UNSIGNED DEFAULT NULL,
  `employment_status` INT(2) UNSIGNED DEFAULT NULL,
  `current_industry` VARCHAR(100) DEFAULT NULL,
  `current_department` VARCHAR(100) DEFAULT NULL,
  `current_designation` VARCHAR(100) DEFAULT NULL,
  `current_job_month` INT(10) UNSIGNED DEFAULT NULL,
  `current_job_year` INT(10) UNSIGNED DEFAULT NULL,
  `total_experience_month` INT(10) UNSIGNED DEFAULT NULL,
  `total_experience_year` INT(10) UNSIGNED DEFAULT NULL,
  `previous_job_month` INT(10) UNSIGNED DEFAULT NULL,
  `previous_job_year` INT(10) UNSIGNED DEFAULT NULL,
  `previous_employers_name` VARCHAR(100) DEFAULT NULL,
  `previous_employers_address` VARCHAR(200) DEFAULT NULL,
  `total_land_owned` DECIMAL(19,2) DEFAULT NULL,
  `presently_irrigated` VARCHAR(200) DEFAULT NULL,
  `seasonal_irrigated` VARCHAR(200) DEFAULT NULL,
  `rain_fed` VARCHAR(100) DEFAULT NULL,
  `unattended` VARCHAR(100) DEFAULT NULL,
  `name_of_entity` VARCHAR(100) DEFAULT NULL,
  `ownership_type` INT(2) UNSIGNED DEFAULT NULL,
  `office_type` INT(2) UNSIGNED DEFAULT NULL,
  `no_partners` INT(5) UNSIGNED DEFAULT NULL,
  `partners_name` VARCHAR(100) DEFAULT NULL,
  `business_start_date` DATETIME DEFAULT NULL,
  `share_holding` VARCHAR(100) DEFAULT NULL,
  `annual_turnover` DECIMAL(19,2) DEFAULT NULL,
  `trade_license_number` VARCHAR(100) DEFAULT NULL,
  `trade_license_expiry_date` DATETIME DEFAULT NULL,
  `poa_holder_name` VARCHAR(100) DEFAULT NULL,
  `repayment_mode` INT(2) UNSIGNED DEFAULT NULL,
  `repayment_cycle` INT(2) UNSIGNED DEFAULT NULL,
  `interest_rate` INT(2) UNSIGNED DEFAULT NULL,
  `employed_with_other` VARCHAR(100) DEFAULT NULL,
  `self_employed_occupation_other` VARCHAR(100) DEFAULT NULL,
  `industry_type_other` VARCHAR(100) DEFAULT NULL,
  `address_same_as` BIT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `modified_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `retail_applicant_id_fk1` (`application_id`),
  CONSTRAINT `retail_applicant_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=1499 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_bank_account_held_details` */

DROP TABLE IF EXISTS `fs_retail_bank_account_held_details`;

CREATE TABLE `fs_retail_bank_account_held_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `account_held_for` VARCHAR(100) DEFAULT NULL,
  `bank_name_and_branch` VARCHAR(100) DEFAULT NULL,
  `account_number` VARCHAR(25) DEFAULT NULL,
  `account_type` VARCHAR(100) DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `applicant_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `applicant_id` (`applicant_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_retail_co_applicant_details` */

DROP TABLE IF EXISTS `fs_retail_co_applicant_details`;

CREATE TABLE `fs_retail_co_applicant_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `relationship_with_applicant` INT(2) DEFAULT NULL,
  `title_id` INT(2) DEFAULT NULL,
  `gender_id` INT(1) UNSIGNED DEFAULT NULL,
  `first_name` VARCHAR(50) DEFAULT NULL,
  `middle_name` VARCHAR(50) DEFAULT NULL,
  `last_name` VARCHAR(50) DEFAULT NULL,
  `birth_date` DATETIME DEFAULT NULL,
  `status_id` INT(2) DEFAULT NULL,
  `occupation_id` INT(2) DEFAULT NULL,
  `company_name` VARCHAR(255) DEFAULT NULL,
  `employed_with_id` INT(2) DEFAULT NULL,
  `contact_no` VARCHAR(20) DEFAULT NULL,
  `self_employed_occupation_id` INT(2) DEFAULT NULL,
  `entity_name` VARCHAR(255) DEFAULT NULL,
  `industry_type_id` INT(2) DEFAULT NULL,
  `land_size` DECIMAL(19,2) DEFAULT NULL,
  `allied_activity_id` INT(2) DEFAULT NULL,
  `pan` VARCHAR(10) DEFAULT NULL,
  `aadhar_number` VARCHAR(20) DEFAULT NULL,
  `monthly_income` DOUBLE DEFAULT NULL,
  `permanent_premise_number_name` VARCHAR(255) DEFAULT NULL,
  `permanent_street_name` VARCHAR(255) DEFAULT NULL,
  `permanent_land_mark` VARCHAR(255) DEFAULT NULL,
  `permanent_pincode` INT(12) DEFAULT NULL,
  `permanent_country_id` INT(2) DEFAULT NULL,
  `permanent_state_id` INT(2) DEFAULT NULL,
  `permanent_city_id` INT(2) DEFAULT NULL,
  `office_premise_number_name` VARCHAR(255) DEFAULT NULL,
  `office_street_name` VARCHAR(255) DEFAULT NULL,
  `office_land_mark` VARCHAR(255) DEFAULT NULL,
  `office_pincode` INT(12) DEFAULT NULL,
  `office_country_id` INT(2) DEFAULT NULL,
  `office_state_id` INT(2) DEFAULT NULL,
  `office_city_id` INT(2) DEFAULT NULL,
  `cast_id` INT(2) DEFAULT NULL,
  `cast_other` VARCHAR(100) DEFAULT NULL,
  `religion` INT(2) DEFAULT NULL,
  `religion_other` VARCHAR(100) DEFAULT NULL,
  `birth_place` VARCHAR(100) DEFAULT NULL,
  `father_name` VARCHAR(200) DEFAULT NULL,
  `mother_name` VARCHAR(200) DEFAULT NULL,
  `spouse_name` VARCHAR(100) DEFAULT NULL,
  `is_spouse_employed` BIT(1) DEFAULT NULL,
  `no_children` INT(2) DEFAULT NULL,
  `no_dependent` INT(2) DEFAULT NULL,
  `highest_qualification` INT(2) DEFAULT NULL,
  `highest_qualification_other` VARCHAR(100) DEFAULT NULL,
  `qualifying_year` DATETIME DEFAULT NULL,
  `institute` VARCHAR(100) DEFAULT NULL,
  `residence_type` INT(2) DEFAULT NULL,
  `annual_rent` DECIMAL(19,2) DEFAULT NULL,
  `residing_year` DOUBLE DEFAULT NULL,
  `residing_month` DOUBLE DEFAULT NULL,
  `website_address` VARCHAR(100) DEFAULT NULL,
  `address_premise_name` VARCHAR(100) DEFAULT NULL,
  `address_street_name` VARCHAR(100) DEFAULT NULL,
  `address_landmark` VARCHAR(100) DEFAULT NULL,
  `address_pincode` BIGINT(10) DEFAULT NULL,
  `address_country` INT(2) DEFAULT NULL,
  `address_state` INT(2) DEFAULT NULL,
  `address_city` INT(2) DEFAULT NULL,
  `employment_status` INT(2) DEFAULT NULL,
  `current_industry` VARCHAR(100) DEFAULT NULL,
  `current_department` VARCHAR(100) DEFAULT NULL,
  `current_designation` VARCHAR(100) DEFAULT NULL,
  `current_job_month` INT(10) DEFAULT NULL,
  `current_job_year` INT(10) DEFAULT NULL,
  `total_experience_month` INT(10) DEFAULT NULL,
  `total_experience_year` INT(10) DEFAULT NULL,
  `previous_job_month` INT(10) DEFAULT NULL,
  `previous_job_year` INT(10) DEFAULT NULL,
  `previous_employers_name` VARCHAR(100) DEFAULT NULL,
  `previous_employers_address` VARCHAR(200) DEFAULT NULL,
  `total_land_owned` DECIMAL(19,2) DEFAULT NULL,
  `presently_irrigated` VARCHAR(200) DEFAULT NULL,
  `seasonal_irrigated` VARCHAR(200) DEFAULT NULL,
  `rain_fed` VARCHAR(100) DEFAULT NULL,
  `unattended` VARCHAR(100) DEFAULT NULL,
  `name_of_entity` VARCHAR(100) DEFAULT NULL,
  `ownership_type` INT(2) DEFAULT NULL,
  `office_type` INT(2) DEFAULT NULL,
  `no_partners` INT(5) DEFAULT NULL,
  `partners_name` VARCHAR(100) DEFAULT NULL,
  `business_start_date` DATETIME DEFAULT NULL,
  `share_holding` VARCHAR(100) DEFAULT NULL,
  `annual_turnover` DECIMAL(19,2) DEFAULT NULL,
  `trade_license_number` VARCHAR(100) DEFAULT NULL,
  `trade_license_expiry_date` DATETIME DEFAULT NULL,
  `poa_holder_name` VARCHAR(100) DEFAULT NULL,
  `employed_with_other` VARCHAR(100) DEFAULT NULL,
  `self_employed_occupation_other` VARCHAR(100) DEFAULT NULL,
  `industry_type_other` VARCHAR(100) DEFAULT NULL,
  `address_same_as` BIT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `application_id_fk12` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_credit_cards_details` */

DROP TABLE IF EXISTS `fs_retail_credit_cards_details`;

CREATE TABLE `fs_retail_credit_cards_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `card_number` VARCHAR(50) DEFAULT NULL,
  `issuer_name` VARCHAR(100) DEFAULT NULL,
  `outstanding_balance` DOUBLE DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `applicantion_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `credit_card_types_id` INT(2) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA331396FDC8C6C36` (`applicantion_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_1` FOREIGN KEY (`applicantion_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_credit_cards_details` */

/*Table structure for table `fs_retail_existing_loan_detail` */

DROP TABLE IF EXISTS `fs_retail_existing_loan_detail`;

CREATE TABLE `fs_retail_existing_loan_detail` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `bank_or_financer_name` VARCHAR(100) DEFAULT NULL,
  `emi_amount` DOUBLE DEFAULT NULL,
  `no_of_emi_paid` INT(3) DEFAULT NULL,
  `outstanding_balance` DOUBLE DEFAULT NULL,
  `loan_tenure` INT(2) DEFAULT NULL,
  `loan_type` VARCHAR(100) DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `applicant_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAD7D565C2771166F` (`guarantor_detail_id`),
  KEY `FKAD7D565CDC8C6C36` (`applicant_id`),
  KEY `FKAD7D565C58921DA5` (`co_applicant_detail_id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_retail_final_car_loan_details` */

DROP TABLE IF EXISTS `fs_retail_final_car_loan_details`;

CREATE TABLE `fs_retail_final_car_loan_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `delivery_date` DATETIME DEFAULT NULL,
  `car_colour` VARCHAR(200) DEFAULT NULL,
  `car_supplier` VARCHAR(200) DEFAULT NULL,
  `car_registration_number` VARCHAR(200) DEFAULT NULL,
  `vehicle_cost` DECIMAL(19,2) DEFAULT NULL,
  `insurance_cost` DECIMAL(19,2) DEFAULT NULL,
  `accessories_cost` DECIMAL(19,2) DEFAULT NULL,
  `road_tax` DECIMAL(19,2) DEFAULT NULL,
  `others_cost` DECIMAL(19,2) DEFAULT NULL,
  `loan_total_cost` DECIMAL(19,2) DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_final_car_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_final_home_loan_details` */

DROP TABLE IF EXISTS `fs_retail_final_home_loan_details`;

CREATE TABLE `fs_retail_final_home_loan_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `property_address_premise` VARCHAR(200) DEFAULT NULL,
  `property_address_street` VARCHAR(200) DEFAULT NULL,
  `property_address_landmark` VARCHAR(200) DEFAULT NULL,
  `property_address_pincode` VARCHAR(200) DEFAULT NULL,
  `property_address_country` INT(2) DEFAULT NULL,
  `property_address_city` INT(2) DEFAULT NULL,
  `property_address_state` INT(2) DEFAULT NULL,
  `built_up_area` DECIMAL(19,2) DEFAULT NULL,
  `carpet_area` DECIMAL(19,2) DEFAULT NULL,
  `super_built_up_area` DECIMAL(19,2) DEFAULT NULL,
  `seller_name` VARCHAR(200) DEFAULT NULL,
  `sellers_address_premise` VARCHAR(200) DEFAULT NULL,
  `sellers_address_street` VARCHAR(200) DEFAULT NULL,
  `sellers_address_landmark` VARCHAR(200) DEFAULT NULL,
  `sellers_address_pincode` VARCHAR(200) DEFAULT NULL,
  `sellers_address_country` INT(2) DEFAULT NULL,
  `sellers_address_city` INT(2) DEFAULT NULL,
  `sellers_address_state` INT(2) DEFAULT NULL,
  `property_used` VARCHAR(200) DEFAULT NULL,
  `estimated_rental_income` DECIMAL(19,2) DEFAULT NULL,
  `project_city_state` VARCHAR(200) DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `id` (`id`),
  CONSTRAINT `fs_retail_final_home_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_fixed_deposits_details` */

DROP TABLE IF EXISTS `fs_retail_fixed_deposits_details`;

CREATE TABLE `fs_retail_fixed_deposits_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `amount` DOUBLE DEFAULT NULL,
  `bank_name` VARCHAR(100) DEFAULT NULL,
  `fd_number` VARCHAR(50) DEFAULT NULL,
  `maturity_date` DATE DEFAULT NULL,
  `rate` DOUBLE DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



/*Table structure for table `fs_retail_guarantor_details` */

DROP TABLE IF EXISTS `fs_retail_guarantor_details`;

CREATE TABLE `fs_retail_guarantor_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `title_id` INT(2) DEFAULT NULL,
  `gender_id` INT(1) UNSIGNED DEFAULT NULL,
  `first_name` VARCHAR(100) DEFAULT NULL,
  `middle_name` VARCHAR(100) DEFAULT NULL,
  `last_name` VARCHAR(100) DEFAULT NULL,
  `birth_date` DATETIME DEFAULT NULL,
  `contact_no` VARCHAR(20) DEFAULT NULL,
  `status_id` INT(2) DEFAULT NULL,
  `occupation_id` INT(2) DEFAULT NULL,
  `company_name` VARCHAR(200) DEFAULT NULL,
  `employed_with_id` INT(2) DEFAULT NULL,
  `self_employed_occupation_id` INT(2) DEFAULT NULL,
  `entity_name` VARCHAR(200) DEFAULT NULL,
  `industry_type_id` INT(2) DEFAULT NULL,
  `land_size` DECIMAL(19,2) DEFAULT NULL,
  `allied_activity_id` INT(2) DEFAULT NULL,
  `pan` VARCHAR(10) DEFAULT NULL,
  `aadhar_number` VARCHAR(20) DEFAULT NULL,
  `monthly_income` DOUBLE DEFAULT NULL,
  `permanent_premise_number_name` VARCHAR(200) DEFAULT NULL,
  `permanent_street_name` VARCHAR(200) DEFAULT NULL,
  `permanent_land_mark` VARCHAR(200) DEFAULT NULL,
  `permanent_pincode` INT(20) DEFAULT NULL,
  `permanent_country_id` INT(2) DEFAULT NULL,
  `permanent_state_id` INT(2) DEFAULT NULL,
  `permanent_city_id` INT(2) DEFAULT NULL,
  `office_premise_number_name` VARCHAR(200) DEFAULT NULL,
  `office_street_name` VARCHAR(200) DEFAULT NULL,
  `office_land_mark` VARCHAR(200) DEFAULT NULL,
  `office_pincode` INT(20) DEFAULT NULL,
  `office_country_id` INT(2) DEFAULT NULL,
  `office_state_id` INT(2) DEFAULT NULL,
  `office_city_id` INT(2) DEFAULT NULL,
  `cast_id` INT(2) DEFAULT NULL,
  `cast_other` VARCHAR(100) DEFAULT NULL,
  `religion` INT(2) DEFAULT NULL,
  `religion_other` VARCHAR(100) DEFAULT NULL,
  `birth_place` VARCHAR(100) DEFAULT NULL,
  `father_name` VARCHAR(100) DEFAULT NULL,
  `mother_name` VARCHAR(100) DEFAULT NULL,
  `spouse_name` VARCHAR(100) DEFAULT NULL,
  `is_spouse_employed` BIT(1) DEFAULT NULL,
  `no_children` INT(2) DEFAULT NULL,
  `no_dependent` INT(2) DEFAULT NULL,
  `highest_qualification` INT(2) DEFAULT NULL,
  `highest_qualification_other` VARCHAR(100) DEFAULT NULL,
  `qualifying_year` DATETIME DEFAULT NULL,
  `institute` VARCHAR(100) DEFAULT NULL,
  `residence_type` INT(2) DEFAULT NULL,
  `annual_rent` DECIMAL(19,2) DEFAULT NULL,
  `residing_year` DOUBLE DEFAULT NULL,
  `residing_month` DOUBLE DEFAULT NULL,
  `website_address` VARCHAR(100) DEFAULT NULL,
  `address_premise_name` VARCHAR(200) DEFAULT NULL,
  `address_street_name` VARCHAR(200) DEFAULT NULL,
  `address_landmark` VARCHAR(200) DEFAULT NULL,
  `address_pincode` BIGINT(20) DEFAULT NULL,
  `address_country` INT(2) DEFAULT NULL,
  `address_state` INT(2) DEFAULT NULL,
  `address_city` INT(2) DEFAULT NULL,
  `employment_status` INT(2) DEFAULT NULL,
  `current_industry` VARCHAR(100) DEFAULT NULL,
  `current_department` VARCHAR(100) DEFAULT NULL,
  `current_designation` VARCHAR(100) DEFAULT NULL,
  `current_job_month` INT(10) DEFAULT NULL,
  `current_job_year` INT(10) DEFAULT NULL,
  `total_experience_month` INT(10) DEFAULT NULL,
  `total_experience_year` INT(10) DEFAULT NULL,
  `previous_job_month` INT(10) DEFAULT NULL,
  `previous_job_year` INT(10) DEFAULT NULL,
  `previous_employers_name` VARCHAR(100) DEFAULT NULL,
  `previous_employers_address` VARCHAR(200) DEFAULT NULL,
  `total_land_owned` DECIMAL(19,2) DEFAULT NULL,
  `presently_irrigated` VARCHAR(100) DEFAULT NULL,
  `seasonal_irrigated` VARCHAR(100) DEFAULT NULL,
  `rain_fed` VARCHAR(100) DEFAULT NULL,
  `unattended` VARCHAR(100) DEFAULT NULL,
  `name_of_entity` VARCHAR(100) DEFAULT NULL,
  `ownership_type` INT(2) DEFAULT NULL,
  `office_type` INT(2) DEFAULT NULL,
  `no_partners` INT(5) DEFAULT NULL,
  `partners_name` VARCHAR(100) DEFAULT NULL,
  `business_start_date` DATETIME DEFAULT NULL,
  `share_holding` VARCHAR(100) DEFAULT NULL,
  `annual_turnover` DECIMAL(19,2) DEFAULT NULL,
  `trade_license_number` VARCHAR(100) DEFAULT NULL,
  `trade_license_expiry_date` DATETIME DEFAULT NULL,
  `poa_holder_name` VARCHAR(100) DEFAULT NULL,
  `employed_with_other` VARCHAR(100) DEFAULT NULL,
  `self_employed_occupation_other` VARCHAR(100) DEFAULT NULL,
  `industry_type_other` VARCHAR(100) DEFAULT NULL,
  `address_same_as` BIT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_guarantor_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_lap_loan_details` */

DROP TABLE IF EXISTS `fs_retail_lap_loan_details`;

CREATE TABLE `fs_retail_lap_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `loan_purpose` INT(2) DEFAULT NULL,
  `loan_purpose_other` VARCHAR(200) DEFAULT NULL,
  `property_type` INT(2) DEFAULT NULL,
  `property_type_other` VARCHAR(200) DEFAULT NULL,
  `occupation_status` INT(2) DEFAULT NULL,
  `occupation_status_other` VARCHAR(200) DEFAULT NULL,
  `property_age_in_year` INT(10) DEFAULT NULL,
  `property_age_in_month` INT(10) DEFAULT NULL,
  `land_area` DECIMAL(19,2) DEFAULT NULL,
  `built_up_area` DECIMAL(19,2) DEFAULT NULL,
  `property_value` DECIMAL(19,2) DEFAULT NULL,
  `owner_name` VARCHAR(200) DEFAULT NULL,
  `address_premise` VARCHAR(200) DEFAULT NULL,
  `address_street` VARCHAR(200) DEFAULT NULL,
  `address_landmark` VARCHAR(200) DEFAULT NULL,
  `address_city` INT(2) DEFAULT NULL,
  `address_state` INT(2) DEFAULT NULL,
  `address_country` INT(2) DEFAULT NULL,
  `pincode` INT(10) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_lap_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Table structure for table `fs_retail_las_loan_details` */

DROP TABLE IF EXISTS `fs_retail_las_loan_details`;

CREATE TABLE `fs_retail_las_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `loan_purpose` INT(2) UNSIGNED DEFAULT NULL,
  `loan_purpose_other` VARCHAR(200) DEFAULT NULL,
  `month` INT(2) UNSIGNED DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_las_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `fs_retail_other_current_asset_details` */

DROP TABLE IF EXISTS `fs_retail_other_current_asset_details`;

CREATE TABLE `fs_retail_other_current_asset_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `asset_description` TEXT,
  `asset_value` DOUBLE DEFAULT NULL,
  `asset_types_id` INT(2) DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;



/*Table structure for table `fs_retail_other_income_details` */

DROP TABLE IF EXISTS `fs_retail_other_income_details`;

CREATE TABLE `fs_retail_other_income_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `gross_income` DOUBLE DEFAULT NULL,
  `income_head` TEXT,
  `net_income` DOUBLE DEFAULT NULL,
  `income_details_id` INT(2) DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_retail_personal_loan_details` */

DROP TABLE IF EXISTS `fs_retail_personal_loan_details`;

CREATE TABLE `fs_retail_personal_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `loan_purpose` BIGINT(20) DEFAULT NULL,
  `loan_purpose_other` VARCHAR(200) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_personal_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=3051 DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_primary_car_loan_details` */

DROP TABLE IF EXISTS `fs_retail_primary_car_loan_details`;

CREATE TABLE `fs_retail_primary_car_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0',
  `car_type` INT(2) DEFAULT NULL,
  `new_car_purchase_type` INT(2) DEFAULT NULL,
  `purchase_reimbursment_date` DATETIME DEFAULT NULL,
  `purchase_preowned_date` DATETIME DEFAULT NULL,
  `certified_dealer` TINYINT(1) DEFAULT NULL,
  `dealer_name` VARCHAR(200) DEFAULT NULL,
  `manufacturer_name` VARCHAR(200) DEFAULT NULL,
  `car_model_name` VARCHAR(200) DEFAULT NULL,
  `car_varient` VARCHAR(200) DEFAULT NULL,
  `on_road_car_price` DECIMAL(19,2) DEFAULT NULL,
  `down_payment` DECIMAL(19,2) DEFAULT NULL,
  `delivery_date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_primary_car_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_primary_home_loan_details` */

DROP TABLE IF EXISTS `fs_retail_primary_home_loan_details`;

CREATE TABLE `fs_retail_primary_home_loan_details` (
  `application_id` BIGINT(20) UNSIGNED NOT NULL,
  `property_type` INT(2) DEFAULT NULL,
  `property_used_type` INT(2) DEFAULT NULL,
  `is_construction_completed` BIT(1) DEFAULT b'0',
  `completion_time_in_month` INT(10) DEFAULT NULL,
  `completion_time_in_year` INT(10) DEFAULT NULL,
  `project_name` VARCHAR(200) DEFAULT NULL,
  `project_city` VARCHAR(40) DEFAULT NULL,
  `area` DECIMAL(19,2) DEFAULT NULL,
  `property_price` DECIMAL(19,2) DEFAULT NULL,
  `bunglow_cost` DECIMAL(19,2) DEFAULT NULL,
  `construction_cost` DECIMAL(19,2) DEFAULT NULL,
  `construction_completion_time_in_month` INT(10) DEFAULT NULL,
  `construction_completion_time_in_year` INT(10) DEFAULT NULL,
  `renovation_type` INT(2) DEFAULT NULL,
  `other_renovation_type` VARCHAR(200) DEFAULT NULL,
  `renovation_cost` DECIMAL(19,2) DEFAULT NULL,
  `renovation_completion_time_in_month` INT(10) DEFAULT NULL,
  `renovation_completion_time_in_year` INT(10) DEFAULT NULL,
  `is_loan_taken` BIT(1) DEFAULT b'0',
  `date_of_loan_taken` DATETIME DEFAULT NULL,
  `land_plot_cost` DECIMAL(19,2) DEFAULT NULL,
  `land_area` DECIMAL(19,2) DEFAULT NULL,
  `down_payment` DECIMAL(19,2) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_primary_home_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;


/*Table structure for table `fs_retail_references_retail_details` */

DROP TABLE IF EXISTS `fs_retail_references_retail_details`;

CREATE TABLE `fs_retail_references_retail_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `address` TEXT,
  `email` VARCHAR(100) DEFAULT NULL,
  `mobile` VARCHAR(25) DEFAULT NULL,
  `name` VARCHAR(100) DEFAULT NULL,
  `telephone` VARCHAR(25) DEFAULT NULL,
  `references_list_id` INT(2) DEFAULT NULL,
  `co_applicant_detail_id` BIGINT(20) DEFAULT NULL,
  `guarantor_detail_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8;


/*Table structure for table `fs_retail_security_retail_details` */

DROP TABLE IF EXISTS `fs_retail_security_retail_details`;

CREATE TABLE `fs_retail_security_retail_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `buying_date` DATETIME DEFAULT NULL,
  `security_type` INT(2) DEFAULT NULL,
  `security_name` VARCHAR(200) DEFAULT NULL,
  `number_of_units` INT(10) DEFAULT NULL,
  `current_market_value` DECIMAL(19,2) DEFAULT NULL,
  `total_value` DECIMAL(19,2) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_promoter` BIT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_security_retail_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;



/*Table structure for table `fs_sector_subsector_details` */

DROP TABLE IF EXISTS `fs_sector_subsector_details`;

CREATE TABLE `fs_sector_subsector_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `sector_subsector_transaction_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME NOT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `modified_by` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_sector_subsector_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


/*Table structure for table `fund_provider_sequence` */

DROP TABLE IF EXISTS `fund_provider_sequence`;

CREATE TABLE `fund_provider_sequence` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `product_id` BIGINT(20) DEFAULT NULL,
  `sequence_number` BIGINT(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `fund_provider_sequence` */

INSERT  INTO `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) VALUES (1,1,10011),(2,2,10008),(3,3,10002),(4,7,10000),(5,12,10000),(6,13,10000),(7,14,10000);

/*Table structure for table `industry_sector_details` */

DROP TABLE IF EXISTS `industry_sector_details`;

CREATE TABLE `industry_sector_details` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `industry_id` BIGINT(20) DEFAULT NULL,
  `sector_id` BIGINT(20) DEFAULT NULL,
  `application_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `fp_product_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `fp_product_id` (`fp_product_id`),
  CONSTRAINT `industry_sector_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `industry_sector_details_ibfk_2` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=INNODB AUTO_INCREMENT=15291 DEFAULT CHARSET=latin1;


/*Table structure for table `proposal_details` */

DROP TABLE IF EXISTS `proposal_details`;

CREATE TABLE `proposal_details` (
  `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `application_id` BIGINT(20) DEFAULT NULL,
  `fp_product_id` BIGINT(20) DEFAULT NULL,
  `proposal_status_id` BIGINT(20) UNSIGNED DEFAULT NULL,
  `is_proposal_auto` BIT(1) DEFAULT b'0',
  `proposal_stage` INT(1) DEFAULT NULL,
  `initiated_by` INT(1) DEFAULT NULL,
  `last_action_performed_by` INT(1) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `match_status_id` (`proposal_status_id`),
  CONSTRAINT `proposal_details_ibfk_1` FOREIGN KEY (`proposal_status_id`) REFERENCES `proposal_status_master` (`id`)
) ENGINE=INNODB AUTO_INCREMENT=2312 DEFAULT CHARSET=latin1;


/*Table structure for table `proposal_status_master` */

DROP TABLE IF EXISTS `proposal_status_master`;

CREATE TABLE `proposal_status_master` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  `status` VARCHAR(20) DEFAULT NULL,
  `code` VARCHAR(20) DEFAULT NULL,
  `created_date` DATETIME DEFAULT NULL,
  `modified_date` DATETIME DEFAULT NULL,
  `created_by` BIGINT(20) DEFAULT NULL,
  `modified_by` BIGINT(20) DEFAULT NULL,
  `is_active` BIT(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=latin1;

/*Data for the table `proposal_status_master` */

INSERT  INTO `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) VALUES (1,'Pending','PENDING','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(2,'Accept','ACCEPT','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(3,'Hold','HOLD','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(4,'Decline','DECLINE','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(5,'Approved','APPROVED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(6,'Published','PUBLISHED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1),(7,'Canceled','CANCELED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,1);

