/*
SQLyog Ultimate v11.5 (64 bit)
MySQL - 5.6.17 : Database - loan_applications
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`loan_applications` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `loan_applications`;

/*Table structure for table `application_sequence` */

DROP TABLE IF EXISTS `application_sequence`;

CREATE TABLE `application_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `sequence_number` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `application_sequence` */

insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (1,1,10008);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (2,2,10005);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (3,3,10003);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (4,7,10002);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (5,12,10001);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (6,13,10001);
insert  into `application_sequence`(`id`,`product_id`,`sequence_number`) values (7,14,10000);

/*Table structure for table `credit_rating_organization_term_mapping` */

DROP TABLE IF EXISTS `credit_rating_organization_term_mapping`;

CREATE TABLE `credit_rating_organization_term_mapping` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `rating` varchar(255) DEFAULT NULL,
  `credit_rating_organization_term_id` bigint(20) unsigned DEFAULT NULL,
  `priority` int(10) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1C0982391FEAEB2D` (`credit_rating_organization_term_id`),
  CONSTRAINT `credit_rating_organization_term_mapping_ibfk_1` FOREIGN KEY (`credit_rating_organization_term_id`) REFERENCES `credit_rating_organization_term_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

/*Data for the table `credit_rating_organization_term_mapping` */

insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (3,'AAA',1,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (4,'AA',1,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (5,'AA+',1,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (6,'AA-\r\n',1,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (7,'A',1,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (8,'A+',1,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (9,'A-\r\n',1,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (10,'BBB',1,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (11,'BBB+',1,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (12,'BBB-\r\n',1,10,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (13,'BB',1,12,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (14,'BB+',1,11,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (15,'BB-',1,13,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (16,'B',1,15,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (17,'B+\r\n',1,14,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (18,'B-',1,16,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (19,'C',1,18,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (20,'C+\r\n',1,17,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (21,'C-',1,19,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (22,'D',1,20,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (23,'A1',2,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (24,'A1+',2,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (25,'A2',2,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (26,'A2+',2,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (27,'A3',2,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (28,'A3+',2,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (29,'A4',2,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (30,'A4+',2,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (31,'D\r\n',2,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (36,'1',3,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (37,'2',3,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (38,'3',3,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (39,'4',3,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (40,'5',3,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (41,'6',3,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (42,'7',3,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (43,'8',3,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (44,'CCR AAA',4,1,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (45,'CCR AA+',4,2,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (46,'CCR AA',4,3,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (47,'CCR AA-',4,4,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (48,'CCR A+',4,5,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (49,'CCR A',4,6,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (50,'CCR A-',4,7,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (51,'CCR BBB+',4,8,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (52,'CCR BBB',4,9,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (53,'CCR BBB-',4,10,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (54,'CCR BB+',4,11,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (55,'CCR BB',4,12,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (56,'CCR BB-',4,13,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (57,'CCR B+',4,14,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (58,'CCR B',4,15,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (59,'CCR B-',4,16,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (60,'CCR C+',4,17,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (61,'CCR C',4,18,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (62,'CCR C-',4,19,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_mapping`(`id`,`rating`,`credit_rating_organization_term_id`,`priority`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (63,'D',4,20,'2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');

/*Table structure for table `credit_rating_organization_term_master` */

DROP TABLE IF EXISTS `credit_rating_organization_term_master`;

CREATE TABLE `credit_rating_organization_term_master` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `credit_rating_organization_term_master` */

insert  into `credit_rating_organization_term_master`(`id`,`name`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (1,'Long Term','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_master`(`id`,`name`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (2,'Short Term','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_master`(`id`,`name`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (3,'SME','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');
insert  into `credit_rating_organization_term_master`(`id`,`name`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (4,'Corporate Credit rating(CCR)','2017-04-28 13:18:20','2017-04-28 13:18:20',NULL,NULL,'');

/*Table structure for table `denomination_master` */

DROP TABLE IF EXISTS `denomination_master`;

CREATE TABLE `denomination_master` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `d_value` int(255) DEFAULT NULL,
  `orderNumber` int(2) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `denomination_master` */

insert  into `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (1,'Lakhs',100000,1,'2016-11-08 18:10:23','2016-11-08 18:10:23',NULL,NULL,'');
insert  into `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (2,'Millions',1000000,2,'2016-11-08 18:10:23','2016-11-08 18:10:34',NULL,NULL,'');
insert  into `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (3,'Crores',10000000,3,'2016-11-08 18:10:23','2016-11-08 18:10:39',NULL,NULL,'');
insert  into `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (4,'Billions',100000000,4,'2016-11-08 18:10:23','2016-11-08 18:10:44',NULL,NULL,'');
insert  into `denomination_master`(`id`,`name`,`d_value`,`orderNumber`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (5,'Absolute',1,5,'2016-11-17 18:51:11','2016-11-17 18:51:11',NULL,NULL,'');

/*Table structure for table `fp_car_loan_details` */

DROP TABLE IF EXISTS `fp_car_loan_details`;

CREATE TABLE `fp_car_loan_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_asset_value` decimal(19,2) DEFAULT NULL,
  `max_asset_value` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_car_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_car_loan_details` */

/*Table structure for table `fp_geographical_city_details` */

DROP TABLE IF EXISTS `fp_geographical_city_details`;

CREATE TABLE `fp_geographical_city_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `city_id` bigint(20) DEFAULT NULL,
  `fp_product_master_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master_id`),
  CONSTRAINT `fp_geographical_city_details_ibfk_1` FOREIGN KEY (`fp_product_master_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=latin1;

/*Data for the table `fp_geographical_city_details` */

/*Table structure for table `fp_geographical_country_details` */

DROP TABLE IF EXISTS `fp_geographical_country_details`;

CREATE TABLE `fp_geographical_country_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `country_id` bigint(10) DEFAULT NULL,
  `fp_product_master` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_country_details_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=latin1;

/*Data for the table `fp_geographical_country_details` */

/*Table structure for table `fp_geographical_state_details` */

DROP TABLE IF EXISTS `fp_geographical_state_details`;

CREATE TABLE `fp_geographical_state_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `state_id` bigint(20) DEFAULT NULL,
  `fp_product_master` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fp_product_master` (`fp_product_master`),
  CONSTRAINT `fp_geographical_state_details_ibfk_1` FOREIGN KEY (`fp_product_master`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=latin1;

/*Data for the table `fp_geographical_state_details` */

/*Table structure for table `fp_home_loan_details` */

DROP TABLE IF EXISTS `fp_home_loan_details`;

CREATE TABLE `fp_home_loan_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_asset_value` decimal(19,2) DEFAULT NULL,
  `max_asset_value` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_home_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_home_loan_details` */

/*Table structure for table `fp_loan_against_property_details` */

DROP TABLE IF EXISTS `fp_loan_against_property_details`;

CREATE TABLE `fp_loan_against_property_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_asset_value` decimal(19,2) DEFAULT NULL,
  `max_asset_value` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_loan_against_property_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_loan_against_property_details` */

/*Table structure for table `fp_loan_against_share_details` */

DROP TABLE IF EXISTS `fp_loan_against_share_details`;

CREATE TABLE `fp_loan_against_share_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_asset_value` decimal(19,2) DEFAULT NULL,
  `max_asset_value` decimal(19,2) DEFAULT NULL,
  `tenure_month` int(5) DEFAULT NULL,
  `tenure_year` int(5) DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_loan_against_share_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_loan_against_share_details` */

/*Table structure for table `fp_personal_loan_details` */

DROP TABLE IF EXISTS `fp_personal_loan_details`;

CREATE TABLE `fp_personal_loan_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `min_loan_amount` decimal(19,2) DEFAULT NULL,
  `max_loan_amount` decimal(19,2) DEFAULT NULL,
  `min_yearly_income_range` double DEFAULT NULL,
  `max_yearly_income_range` double DEFAULT NULL,
  `min_age` double DEFAULT NULL,
  `max_age` double DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `is_loan_amount_display` bit(1) DEFAULT b'0',
  `is_loan_amount_mandatory` bit(1) DEFAULT b'0',
  `is_yearly_income_range_display` bit(1) DEFAULT b'0',
  `is_yearly_income_range_mandatory` bit(1) DEFAULT b'0',
  `is_age_display` bit(1) DEFAULT b'0',
  `is_age_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `is_asset_value_display` bit(1) DEFAULT b'0',
  `is_asset_value_mandatory` bit(1) DEFAULT b'0',
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_personal_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_personal_loan_details` */

/*Table structure for table `fp_product_master` */

DROP TABLE IF EXISTS `fp_product_master`;

CREATE TABLE `fp_product_master` (
  `fp_product_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) unsigned DEFAULT NULL,
  `user_id` bigint(20) unsigned DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `fp_name` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `is_parameter_filled` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `product_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=latin1;

/*Data for the table `fp_product_master` */

/*Table structure for table `fp_term_loan_details` */

DROP TABLE IF EXISTS `fp_term_loan_details`;

CREATE TABLE `fp_term_loan_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `denomination` int(2) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `long_term_credit_rating` int(5) DEFAULT NULL,
  `short_term_credit_rating` int(5) DEFAULT NULL,
  `min_age_establishment` int(5) DEFAULT NULL,
  `max_age_establishment` int(5) DEFAULT NULL,
  `profitability_history` int(2) DEFAULT NULL,
  `min_past_turnover` decimal(19,2) DEFAULT NULL,
  `max_past_turnover` decimal(19,2) DEFAULT NULL,
  `min_debt_equity` int(3) DEFAULT NULL,
  `max_debt_equity` int(3) DEFAULT NULL,
  `min_collateral` int(3) DEFAULT NULL,
  `max_collateral` int(3) DEFAULT NULL,
  `min_networth` decimal(19,2) DEFAULT NULL,
  `max_networth` decimal(19,2) DEFAULT NULL,
  `uninterested_industry` bigint(20) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT b'0',
  `is_industry_sector_mandatory` bit(1) DEFAULT b'0',
  `is_investment_size_display` bit(1) DEFAULT b'0',
  `is_investment_size_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_credit_rating_display` bit(1) DEFAULT b'0',
  `is_credit_rating_mandatory` bit(1) DEFAULT b'0',
  `is_establishment_display` bit(1) DEFAULT b'0',
  `is_establishment_mandatory` bit(1) DEFAULT b'0',
  `is_profitability_history_display` bit(1) DEFAULT b'0',
  `is_profitability_history_mandatory` bit(1) DEFAULT b'0',
  `is_past_year_turnover_display` bit(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` bit(1) DEFAULT b'0',
  `is_debt_equity_display` bit(1) DEFAULT b'0',
  `is_debt_equity_mandatory` bit(1) DEFAULT b'0',
  `is_collateral_display` bit(1) DEFAULT b'0',
  `is_collateral_mandatory` bit(1) DEFAULT b'0',
  `is_networth_display` bit(1) DEFAULT b'0',
  `is_networth_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_term_loan_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_term_loan_details` */

/*Table structure for table `fp_working_capital_details` */

DROP TABLE IF EXISTS `fp_working_capital_details`;

CREATE TABLE `fp_working_capital_details` (
  `fp_product_id` bigint(20) unsigned NOT NULL,
  `currency` int(2) DEFAULT NULL,
  `denomination` int(2) DEFAULT NULL,
  `min_invest_size` decimal(19,2) DEFAULT NULL,
  `max_invest_size` decimal(19,2) DEFAULT NULL,
  `min_tenure` double DEFAULT NULL,
  `max_tenure` double DEFAULT NULL,
  `long_term_credit_rating` int(5) DEFAULT NULL,
  `short_term_credit_rating` int(5) DEFAULT NULL,
  `min_age_establishment` int(5) DEFAULT NULL,
  `max_age_establishment` int(5) DEFAULT NULL,
  `profitability_history` int(2) DEFAULT NULL,
  `min_past_turnover` decimal(19,2) DEFAULT NULL,
  `max_past_turnover` decimal(19,2) DEFAULT NULL,
  `min_debt_equity` int(3) DEFAULT NULL,
  `max_debt_equity` int(3) DEFAULT NULL,
  `min_collateral` int(3) DEFAULT NULL,
  `max_collateral` int(3) DEFAULT NULL,
  `min_networth` decimal(19,2) DEFAULT NULL,
  `max_networth` decimal(19,2) DEFAULT NULL,
  `uninterested_industry` bigint(20) DEFAULT NULL,
  `is_industry_sector_display` bit(1) DEFAULT b'0',
  `is_industry_sector_mandatory` bit(1) DEFAULT b'0',
  `is_investment_size_display` bit(1) DEFAULT b'0',
  `is_investment_size_mandatory` bit(1) DEFAULT b'0',
  `is_tenure_display` bit(1) DEFAULT b'0',
  `is_tenure_mandatory` bit(1) DEFAULT b'0',
  `is_geographical_display` bit(1) DEFAULT b'0',
  `is_geographical_mandatory` bit(1) DEFAULT b'0',
  `is_credit_rating_display` bit(1) DEFAULT b'0',
  `is_credit_rating_mandatory` bit(1) DEFAULT b'0',
  `is_establishment_display` bit(1) DEFAULT b'0',
  `is_establishment_mandatory` bit(1) DEFAULT b'0',
  `is_profitability_history_display` bit(1) DEFAULT b'0',
  `is_profitability_history_mandatory` bit(1) DEFAULT b'0',
  `is_past_year_turnover_display` bit(1) DEFAULT b'0',
  `is_past_year_turnover_mandatory` bit(1) DEFAULT b'0',
  `is_debt_equity_display` bit(1) DEFAULT b'0',
  `is_debt_equity_mandatory` bit(1) DEFAULT b'0',
  `is_collateral_display` bit(1) DEFAULT b'0',
  `is_collateral_mandatory` bit(1) DEFAULT b'0',
  `is_networth_display` bit(1) DEFAULT b'0',
  `is_networth_mandatory` bit(1) DEFAULT b'0',
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`fp_product_id`),
  CONSTRAINT `fp_working_capital_details_ibfk_1` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fp_working_capital_details` */

/*Table structure for table `fs_application_status_master` */

DROP TABLE IF EXISTS `fs_application_status_master`;

CREATE TABLE `fs_application_status_master` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL DEFAULT '',
  `code` varchar(5) NOT NULL DEFAULT '',
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_application_status_master` */

/*Table structure for table `fs_corporate_achievement_details` */

DROP TABLE IF EXISTS `fs_corporate_achievement_details`;

CREATE TABLE `fs_corporate_achievement_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `milestone_achieved_detail` text,
  `year` varchar(6) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_Date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_achievement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_achievement_details` */

/*Table structure for table `fs_corporate_applicant_details` */

DROP TABLE IF EXISTS `fs_corporate_applicant_details`;

CREATE TABLE `fs_corporate_applicant_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `organisation_name` varchar(100) DEFAULT '',
  `group_name` varchar(100) DEFAULT '',
  `pan` varchar(10) DEFAULT NULL,
  `landline_no` varchar(20) DEFAULT NULL,
  `constitution_id` int(2) unsigned DEFAULT NULL,
  `establishment_year` int(4) unsigned DEFAULT NULL,
  `establishment_month` int(2) unsigned DEFAULT NULL,
  `website_address` varchar(100) DEFAULT NULL,
  `registered_premise_number` varchar(200) DEFAULT NULL,
  `registered_street_name` varchar(200) DEFAULT NULL,
  `registered_land_mark` varchar(200) DEFAULT NULL,
  `registered_city_id` bigint(20) unsigned DEFAULT NULL,
  `registered_state_id` bigint(20) unsigned DEFAULT NULL,
  `registered_country_id` int(20) unsigned DEFAULT NULL,
  `registered_pincode` int(10) DEFAULT NULL,
  `same_as` bit(1) DEFAULT b'0',
  `administrative_premise_number` varchar(100) DEFAULT NULL,
  `administrative_street_name` varchar(100) DEFAULT NULL,
  `administrative_land_mark` varchar(100) DEFAULT NULL,
  `administrative_city_id` bigint(20) unsigned DEFAULT NULL,
  `administrative_state_id` int(20) unsigned DEFAULT NULL,
  `administrative_country_id` int(20) unsigned DEFAULT NULL,
  `administrative_pincode` int(10) DEFAULT NULL,
  `about_us` text,
  `key_verical_funding` bigint(2) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `corp_app_application_id_fk1` (`application_id`),
  CONSTRAINT `corp_app_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_applicant_details` */

/*Table structure for table `fs_corporate_associated_concern_details` */

DROP TABLE IF EXISTS `fs_corporate_associated_concern_details`;

CREATE TABLE `fs_corporate_associated_concern_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `invested_amount` double DEFAULT NULL,
  `brief_description` text,
  `name` varchar(50) DEFAULT NULL,
  `nature_activity` varchar(200) DEFAULT NULL,
  `nature_association` varchar(200) DEFAULT NULL,
  `turn_over_first_year` double DEFAULT NULL,
  `turn_over_second_year` double DEFAULT NULL,
  `turn_over_third_year` double DEFAULT NULL,
  `currentYear` int(6) DEFAULT NULL,
  `profit_past_one_year` double DEFAULT NULL,
  `profit_past_two_year` double DEFAULT NULL,
  `profit_past_three_year` double DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_associated_concern_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_associated_concern_details` */

/*Table structure for table `fs_corporate_availability_proposed_plant_details` */

DROP TABLE IF EXISTS `fs_corporate_availability_proposed_plant_details`;

CREATE TABLE `fs_corporate_availability_proposed_plant_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description_p_m` varchar(255) DEFAULT NULL,
  `estimated_value` varchar(255) DEFAULT NULL,
  `imported_or_indigenous` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  `use_or_purpose` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_availability_proposed_plant_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_availability_proposed_plant_details` */

/*Table structure for table `fs_corporate_board_of_directors_details` */

DROP TABLE IF EXISTS `fs_corporate_board_of_directors_details`;

CREATE TABLE `fs_corporate_board_of_directors_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `any_special_achievement` text,
  `designation` text,
  `experience` text,
  `functional_duties` text,
  `name` varchar(255) DEFAULT NULL,
  `qualification` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB4994491DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_board_of_directors_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_board_of_directors_details` */

/*Table structure for table `fs_corporate_bs_balance_sheet_details` */

DROP TABLE IF EXISTS `fs_corporate_bs_balance_sheet_details`;

CREATE TABLE `fs_corporate_bs_balance_sheet_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `advance_from_customers` double DEFAULT NULL,
  `capital_advance` double DEFAULT NULL,
  `capital_redemption_reserve` double DEFAULT NULL,
  `capital_reserve` double DEFAULT NULL,
  `capital_work_in_progress` double DEFAULT NULL,
  `contingency_reserve` double DEFAULT NULL,
  `current_investments` double DEFAULT NULL,
  `debenture_redemption_reserve` double DEFAULT NULL,
  `debentures` double DEFAULT NULL,
  `deferred_payment_credits` double DEFAULT NULL,
  `deferred_tax_asset` double DEFAULT NULL,
  `deferred_tax_liability` double DEFAULT NULL,
  `deposits_and_installments` double DEFAULT NULL,
  `depreciation_to_date` double DEFAULT NULL,
  `dividend_payable` double DEFAULT NULL,
  `exports` double DEFAULT NULL,
  `finished_goods` double DEFAULT NULL,
  `fixed_assets` double DEFAULT NULL,
  `fixed_deposits_with_banks` double DEFAULT NULL,
  `foreign_currency_translation_reserve` double DEFAULT NULL,
  `general_reserve` double DEFAULT NULL,
  `government_and_other_trustee` double DEFAULT NULL,
  `grand_total` double DEFAULT NULL,
  `gross_fixed_assets` double DEFAULT NULL,
  `hedging_reserve` double DEFAULT NULL,
  `intangible_assets` double DEFAULT NULL,
  `inventory` double DEFAULT NULL,
  `investment_in_associates` double DEFAULT NULL,
  `investment_in_quoted` double DEFAULT NULL,
  `investment_in_subsidiaries` double DEFAULT NULL,
  `long_term_borrowing` double DEFAULT NULL,
  `long_term_loans_and_advance` double DEFAULT NULL,
  `long_term_provisions` double DEFAULT NULL,
  `misc_expences` double DEFAULT NULL,
  `non_current_investments` double DEFAULT NULL,
  `ordinary_share_capital` double DEFAULT NULL,
  `other_consumables_spares` double DEFAULT NULL,
  `other_consumables_spares_imported` double DEFAULT NULL,
  `other_consumables_spares_indegenous` double DEFAULT NULL,
  `other_non_current_liability` double DEFAULT NULL,
  `other_than_exports` double DEFAULT NULL,
  `others` double DEFAULT NULL,
  `others_current_assets` double DEFAULT NULL,
  `others_current_liability` double DEFAULT NULL,
  `others_non_current_assets` double DEFAULT NULL,
  `others_pls_specify` double DEFAULT NULL,
  `preference_share_capital` double DEFAULT NULL,
  `provision_for_tax` double DEFAULT NULL,
  `raw_material` double DEFAULT NULL,
  `raw_material_imported` double DEFAULT NULL,
  `raw_material_indegenous` double DEFAULT NULL,
  `reserves_and_surplus` double DEFAULT NULL,
  `revaluation_reserve` double DEFAULT NULL,
  `securities_premium_account` double DEFAULT NULL,
  `share_application_pending_allotment` double DEFAULT NULL,
  `short_term_borrowings` double DEFAULT NULL,
  `short_term_loans_and_advances` double DEFAULT NULL,
  `statutory_liability_dues` double DEFAULT NULL,
  `stock_in_process` double DEFAULT NULL,
  `surplus_in_profit_and_loss_account` double DEFAULT NULL,
  `term_deposits` double DEFAULT NULL,
  `term_loans` double DEFAULT NULL,
  `total_current_and_non_current_liability` double DEFAULT NULL,
  `trade_payables` double DEFAULT NULL,
  `trade_receivables` double DEFAULT NULL,
  `unsecured_loans_from_promoters` double DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `debit_balance_pl` double DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_bs_balance_sheet_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_bs_balance_sheet_details` */

/*Table structure for table `fs_corporate_bs_profitibility_statement_details` */

DROP TABLE IF EXISTS `fs_corporate_bs_profitibility_statement_details`;

CREATE TABLE `fs_corporate_bs_profitibility_statement_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_and_selling_expenses` double DEFAULT NULL,
  `already_paid` double DEFAULT NULL,
  `amortisation` double DEFAULT NULL,
  `bs_provision` double DEFAULT NULL,
  `closing_stock_fg` double DEFAULT NULL,
  `closing_stock_wip` double DEFAULT NULL,
  `cost_raw_material_consumed` double DEFAULT NULL,
  `current_tax` double DEFAULT NULL,
  `deferred_tax` double DEFAULT NULL,
  `depreciation` double DEFAULT NULL,
  `depreciation_and_amortisation` double DEFAULT NULL,
  `dividend` double DEFAULT NULL,
  `employee_benefit_expenses` double DEFAULT NULL,
  `extraordinary_items` double DEFAULT NULL,
  `factory_wages` double DEFAULT NULL,
  `finance_cost` double DEFAULT NULL,
  `gross_operating_revenue` double DEFAULT NULL,
  `increase_or_decrease_in_inventory_fg` double DEFAULT NULL,
  `increase_or_decrease_in_inventory_wip` double DEFAULT NULL,
  `less_any_other_item` double DEFAULT NULL,
  `less_excise_duty_or_vat_or_service_tax` double DEFAULT NULL,
  `net_sales` double DEFAULT NULL,
  `non_operating_expenses` double DEFAULT NULL,
  `non_operating_income` double DEFAULT NULL,
  `opening_stock_of_fg` double DEFAULT NULL,
  `opening_stock_wip` double DEFAULT NULL,
  `operating_expenses` double DEFAULT NULL,
  `operating_profit_before_depreciation` double DEFAULT NULL,
  `operating_profit_before_interest_and_tax` double DEFAULT NULL,
  `operating_profit_before_tax` double DEFAULT NULL,
  `other_expenses` double DEFAULT NULL,
  `other_operating_revenue` double DEFAULT NULL,
  `other_pls_specify` double DEFAULT NULL,
  `personnel_cost` double DEFAULT NULL,
  `power_and_fuel` double DEFAULT NULL,
  `profit_after_tax` double DEFAULT NULL,
  `profit_before_tax` double DEFAULT NULL,
  `provision_for_tax` double DEFAULT NULL,
  `purchases_stock_tn_trade` double DEFAULT NULL,
  `raw_material_imported` double DEFAULT NULL,
  `raw_material_indigenous` double DEFAULT NULL,
  `retained_profit` double DEFAULT NULL,
  `sales` double DEFAULT NULL,
  `sales_domestic` double DEFAULT NULL,
  `sales_export` double DEFAULT NULL,
  `store_and_spares` double DEFAULT NULL,
  `store_and_spares_imported` double DEFAULT NULL,
  `store_and_spares_indeigenous` double DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_bs_profitibility_statement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_bs_profitibility_statement_details` */

/*Table structure for table `fs_corporate_capacity_details` */

DROP TABLE IF EXISTS `fs_corporate_capacity_details`;

CREATE TABLE `fs_corporate_capacity_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_installed_capacity` varchar(255) DEFAULT NULL,
  `current_operating_level` varchar(255) DEFAULT NULL,
  `future_capacity` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `measurement_for_current_installed_capacity` varchar(100) DEFAULT NULL,
  `measurement_for_future_capacity` varchar(100) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_capacity_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_capacity_details` */

/*Table structure for table `fs_corporate_cma_assets_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_assets_details`;

CREATE TABLE `fs_corporate_cma_assets_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `advance_payment_taxes` double DEFAULT NULL,
  `advance_to_supplier_raw_materials` double DEFAULT NULL,
  `advance_to_suppliers_capital_goods` double DEFAULT NULL,
  `any_other` double DEFAULT NULL,
  `bad_or_doubtful_expenses` double DEFAULT NULL,
  `cash_and_bank_balance` double DEFAULT NULL,
  `current_ratio` double DEFAULT NULL,
  `deferred_receviables` double DEFAULT NULL,
  `deferred_receviables_others` double DEFAULT NULL,
  `depreciation_to_date` double DEFAULT NULL,
  `export_receivables` double DEFAULT NULL,
  `finished_goods` double DEFAULT NULL,
  `fixed_deposits_with_banks` double DEFAULT NULL,
  `good_will` double DEFAULT NULL,
  `government_and_other_trustee` double DEFAULT NULL,
  `gross_block` double DEFAULT NULL,
  `instalments_deferred` double DEFAULT NULL,
  `inventory` double DEFAULT NULL,
  `investments` double DEFAULT NULL,
  `investments_in_subsidiary` double DEFAULT NULL,
  `investments_or_book_debts` double DEFAULT NULL,
  `net_block` double DEFAULT NULL,
  `net_working_capital` double DEFAULT NULL,
  `non_consumable_store_and_spares` double DEFAULT NULL,
  `other_consumable_spares` double DEFAULT NULL,
  `other_consumable_spares_imported` double DEFAULT NULL,
  `other_consumable_spares_indegenous` double DEFAULT NULL,
  `other_current_assets` double DEFAULT NULL,
  `other_non_current_assets` double DEFAULT NULL,
  `others` double DEFAULT NULL,
  `patents` double DEFAULT NULL,
  `prelim_expenses` double DEFAULT NULL,
  `raw_material` double DEFAULT NULL,
  `raw_material_imported` double DEFAULT NULL,
  `raw_material_indegenous` double DEFAULT NULL,
  `receivable_other_than_defferred` double DEFAULT NULL,
  `stock_in_process` double DEFAULT NULL,
  `tangible_net_worth` double DEFAULT NULL,
  `total_assets` double DEFAULT NULL,
  `total_current_assets` double DEFAULT NULL,
  `total_intangible_assets` double DEFAULT NULL,
  `total_other_non_current_assets` double DEFAULT NULL,
  `total_out_side_liability` double DEFAULT NULL,
  `total_term_liability` double DEFAULT NULL,
  `year` varchar(50) DEFAULT NULL,
  `deferred_tax_assets` double DEFAULT NULL,
  `intangible_assets` double DEFAULT NULL,
  `storage_details_id` bigint(20) unsigned DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_assets_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_cma_assets_details` */

/*Table structure for table `fs_corporate_cma_liabilities_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_liabilities_details`;

CREATE TABLE `fs_corporate_cma_liabilities_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `advance_payments_from_customers` double DEFAULT NULL,
  `debentures` double DEFAULT NULL,
  `deferred_payments_credits` double DEFAULT NULL,
  `deferred_tax_liability` double DEFAULT NULL,
  `deposits_or_instalments_of_term_loans` double DEFAULT NULL,
  `dividend_payable` double DEFAULT NULL,
  `from_application_bank` double DEFAULT NULL,
  `from_other_banks` double DEFAULT NULL,
  `general_reserve` double DEFAULT NULL,
  `net_worth` double DEFAULT NULL,
  `which_bp_and_bd` double DEFAULT NULL,
  `ordinary_shares_capital` double DEFAULT NULL,
  `other_current_liability` double DEFAULT NULL,
  `other_reservse` double DEFAULT NULL,
  `other_statutory_liability` double DEFAULT NULL,
  `other_term_liabilies` double DEFAULT NULL,
  `others` double DEFAULT NULL,
  `preferences_shares` double DEFAULT NULL,
  `provisional_for_taxation` double DEFAULT NULL,
  `revaluation_reservse` double DEFAULT NULL,
  `short_term_borrowing_from_others` double DEFAULT NULL,
  `sub_total_a` double DEFAULT NULL,
  `sub_total_b` double DEFAULT NULL,
  `sundry_creditors` double DEFAULT NULL,
  `surplus_or_deficit` double DEFAULT NULL,
  `term_deposits` double DEFAULT NULL,
  `term_loans` double DEFAULT NULL,
  `total_current_liabilities` double DEFAULT NULL,
  `total_liability` double DEFAULT NULL,
  `total_outside_liabilities` double DEFAULT NULL,
  `total_term_liabilities` double DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `storage_details_id` bigint(20) unsigned DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_liabilities_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_cma_liabilities_details` */

/*Table structure for table `fs_corporate_cma_operating_statement_details` */

DROP TABLE IF EXISTS `fs_corporate_cma_operating_statement_details`;

CREATE TABLE `fs_corporate_cma_operating_statement_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `op_profit_before_intrest` double DEFAULT NULL,
  `add_operating_stock` double DEFAULT NULL,
  `add_operating_stock_fg` double DEFAULT NULL,
  `add_other_non_op_income` double DEFAULT NULL,
  `add_other_revenue_income` double DEFAULT NULL,
  `production_cost` double DEFAULT NULL,
  `deduct_cl_stock_fg` double DEFAULT NULL,
  `deduct_other_items` double DEFAULT NULL,
  `deduct_other_non_op_exp` double DEFAULT NULL,
  `deduct_stock_in_process` double DEFAULT NULL,
  `depreciation` double DEFAULT NULL,
  `direct_labour` double DEFAULT NULL,
  `dividend_rate` double DEFAULT NULL,
  `domestic_sales` double DEFAULT NULL,
  `equity_deividend_paid_amt` double DEFAULT NULL,
  `expenses_amortised` double DEFAULT NULL,
  `export_sales` double DEFAULT NULL,
  `interest` double DEFAULT NULL,
  `less_excise_duty` double DEFAULT NULL,
  `netof_non_op_income_or_expenses` double DEFAULT NULL,
  `net_profit_or_loss` double DEFAULT NULL,
  `net_sales` double DEFAULT NULL,
  `op_profit_after_interest` double DEFAULT NULL,
  `other_mfg_expenses` double DEFAULT NULL,
  `other_spares` double DEFAULT NULL,
  `other_spares_imported` double DEFAULT NULL,
  `other_spares_indigenous` double DEFAULT NULL,
  `percentage_rise_or_fall` double DEFAULT NULL,
  `power_and_fuel` double DEFAULT NULL,
  `profit_before_tax_or_loss` double DEFAULT NULL,
  `provision_for_deferred_tax` double DEFAULT NULL,
  `provision_for_taxes` double DEFAULT NULL,
  `raw_materials` double DEFAULT NULL,
  `raw_materials_imported` double DEFAULT NULL,
  `raw_materials_indigenous` double DEFAULT NULL,
  `retained_profit` double DEFAULT NULL,
  `retained_profit_or_net_profit` double DEFAULT NULL,
  `selling_genl_admn_expenses` double DEFAULT NULL,
  `sub_total_cost_Sales` double DEFAULT NULL,
  `sub_total_cost_sales_and_selling` double DEFAULT NULL,
  `sub_total_expenses` double DEFAULT NULL,
  `sub_total_of_cost_sales_and_operating_stock` double DEFAULT NULL,
  `sub_total_deduct_and_cost_of_production` double DEFAULT NULL,
  `sub_total_of_income` double DEFAULT NULL,
  `total_cost_sales` double DEFAULT NULL,
  `total_gross_sales` double DEFAULT NULL,
  `year` varchar(100) DEFAULT NULL,
  `storage_details_id` bigint(20) unsigned DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_cma_operating_statement_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_cma_operating_statement_details` */

/*Table structure for table `fs_corporate_credit_rating_organization_details` */

DROP TABLE IF EXISTS `fs_corporate_credit_rating_organization_details`;

CREATE TABLE `fs_corporate_credit_rating_organization_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `amount` double(19,2) DEFAULT NULL,
  `credit_rating_fund_id` int(2) DEFAULT NULL,
  `rating_agency_id` int(2) DEFAULT NULL,
  `facility_name` text,
  `credit_rating_option_id` int(2) DEFAULT NULL,
  `credit_rating_term_id` int(2) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_Date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8E277427CFFB7AD9` (`credit_rating_fund_id`),
  KEY `FK8E277427DF197CC7` (`rating_agency_id`),
  KEY `FK8E2774278ADCA19` (`credit_rating_option_id`),
  KEY `FK8E2774271FEAEB2D` (`credit_rating_term_id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_credit_rating_organization_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_credit_rating_organization_details` */

/*Table structure for table `fs_corporate_current_financial_arrangements_details` */

DROP TABLE IF EXISTS `fs_corporate_current_financial_arrangements_details`;

CREATE TABLE `fs_corporate_current_financial_arrangements_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `financial_institution_name` varchar(100) DEFAULT '',
  `facility_nature_id` int(2) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKC7018B2C5007A65` (`facility_nature_id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_current_financial_arrangements_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_current_financial_arrangements_details` */

/*Table structure for table `fs_corporate_dpr_user_data_details` */

DROP TABLE IF EXISTS `fs_corporate_dpr_user_data_details`;

CREATE TABLE `fs_corporate_dpr_user_data_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `absence_civic_restrictions` text,
  `labour_availability` text,
  `other_availability` text,
  `power_availability` text,
  `transport_availability` text,
  `water_availability` text,
  `competitive_landscape` text,
  `global_scenario` text,
  `key_players` text,
  `manufacturing_process` text,
  `market_for_the_product` text,
  `market_growth` text,
  `market_needs` text,
  `market_trends` text,
  `markets_currently_served` text,
  `national_scenario` text,
  `shifts_in_day_number` text,
  `working_Days_in_month__number` text,
  `other_benefits` text,
  `project_Jjustification` text,
  `proximity_to_source_raw_materials` text,
  `special_features_products_and_services` text,
  `target_market_strategy` text,
  `technical_know_how` text,
  `whether_clearance_is_obtained_from_pollution_control_authority` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_dpr_user_data_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_dpr_user_data_details` */

/*Table structure for table `fs_corporate_driver_for_future_growth_details` */

DROP TABLE IF EXISTS `fs_corporate_driver_for_future_growth_details`;

CREATE TABLE `fs_corporate_driver_for_future_growth_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_string` text,
  `second_string` text,
  `third_string` text,
  `forth_string` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6CFF66DDC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_driver_for_future_growth_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_driver_for_future_growth_details` */

/*Table structure for table `fs_corporate_employees_category_breaks_details` */

DROP TABLE IF EXISTS `fs_corporate_employees_category_breaks_details`;

CREATE TABLE `fs_corporate_employees_category_breaks_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employment_status_future` varchar(255) DEFAULT NULL,
  `employment_status_present` varchar(255) DEFAULT NULL,
  `employment` varchar(50) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_employees_category_breaks_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_employees_category_breaks_details` */

/*Table structure for table `fs_corporate_existing_product_details` */

DROP TABLE IF EXISTS `fs_corporate_existing_product_details`;

CREATE TABLE `fs_corporate_existing_product_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product` text,
  `application` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK77968CF1DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_existing_product_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_existing_product_details` */

/*Table structure for table `fs_corporate_final_term_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_final_term_loan_details`;

CREATE TABLE `fs_corporate_final_term_loan_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `technology_type_id` int(2) unsigned DEFAULT NULL,
  `is_technology_tied` bit(1) DEFAULT b'0',
  `technology_patented_id` int(2) unsigned DEFAULT NULL,
  `technology_requires_upgradation_id` int(2) unsigned DEFAULT NULL,
  `market_position_id` int(2) unsigned DEFAULT NULL,
  `market_positioning_top_id` int(2) unsigned DEFAULT NULL,
  `market_share_turnover_id` int(2) unsigned DEFAULT NULL,
  `india_distribution_network_id` int(2) unsigned DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` int(2) unsigned DEFAULT NULL,
  `brand_ambassador_id` int(2) unsigned DEFAULT NULL,
  `marketing_positioning_id` int(2) unsigned DEFAULT NULL,
  `product_services_perse_id` int(2) unsigned DEFAULT NULL,
  `is_depends_majorly_on_government` bit(1) DEFAULT b'0',
  `environment_certification_id` int(2) unsigned DEFAULT NULL,
  `is_iso_certified` bit(1) DEFAULT b'0',
  `accounting_systems_id` int(2) unsigned DEFAULT NULL,
  `internal_audit_id` int(2) unsigned DEFAULT NULL,
  `competence_id` int(2) unsigned DEFAULT NULL,
  `existing_share_holders_id` int(2) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `final_term_application_id_fk1` (`application_id`),
  CONSTRAINT `final_term_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_final_term_loan_details` */

/*Table structure for table `fs_corporate_final_wc_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_final_wc_loan_details`;

CREATE TABLE `fs_corporate_final_wc_loan_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned NOT NULL,
  `technology_type_id` int(2) unsigned DEFAULT NULL,
  `is_technology_tied` bit(1) DEFAULT b'0',
  `technology_patented_id` int(2) unsigned DEFAULT NULL,
  `technology_requires_upgradation_id` int(2) unsigned DEFAULT NULL,
  `market_position_id` int(2) unsigned DEFAULT NULL,
  `market_positioning_top_id` int(2) unsigned DEFAULT NULL,
  `market_share_turnover_id` int(2) unsigned DEFAULT NULL,
  `india_distribution_network_id` int(2) unsigned DEFAULT NULL,
  `distribution_and_marketing_tie_ups_id` int(2) unsigned DEFAULT NULL,
  `brand_ambassador_id` int(2) unsigned DEFAULT NULL,
  `marketing_positioning_id` int(2) unsigned DEFAULT NULL,
  `product_services_perse_id` int(2) unsigned DEFAULT NULL,
  `is_depends_majorly_on_government` bit(1) DEFAULT b'0',
  `environment_certification_id` int(2) unsigned DEFAULT NULL,
  `is_iso_certified` bit(1) DEFAULT b'0',
  `accounting_systems_id` int(2) unsigned DEFAULT NULL,
  `internal_audit_id` int(2) unsigned DEFAULT NULL,
  `competence_id` int(2) unsigned DEFAULT NULL,
  `existing_share_holders_id` int(2) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`application_id`,`id`),
  KEY `application_id` (`application_id`),
  KEY `id` (`id`),
  CONSTRAINT `wc_final_application_id_fk` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_final_wc_loan_details` */

/*Table structure for table `fs_corporate_finance_means_details` */

DROP TABLE IF EXISTS `fs_corporate_finance_means_details`;

CREATE TABLE `fs_corporate_finance_means_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `already_infused` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `to_be_incurred` double DEFAULT NULL,
  `finance_means_category_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCF8CE3B3DC8C6C36` (`application_id`),
  KEY `FKCF8CE3B33C516935` (`finance_means_category_id`),
  CONSTRAINT `fs_corporate_finance_means_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_finance_means_details` */

/*Table structure for table `fs_corporate_future_financial_estimates_details` */

DROP TABLE IF EXISTS `fs_corporate_future_financial_estimates_details`;

CREATE TABLE `fs_corporate_future_financial_estimates_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_assets` double DEFAULT NULL,
  `current_liabilities` double DEFAULT NULL,
  `ebitda` double DEFAULT NULL,
  `fixed_assets` double DEFAULT NULL,
  `long_term_debt` double DEFAULT NULL,
  `net_worth` double DEFAULT NULL,
  `pat` double DEFAULT NULL,
  `sales` double DEFAULT NULL,
  `financial_year` varchar(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `debt` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_future_financial_estimates_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_future_financial_estimates_details` */

/*Table structure for table `fs_corporate_guarantors_corporate_details` */

DROP TABLE IF EXISTS `fs_corporate_guarantors_corporate_details`;

CREATE TABLE `fs_corporate_guarantors_corporate_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `occupation` varchar(50) DEFAULT NULL,
  `properties_owned` varchar(50) DEFAULT NULL,
  `property_type` varchar(50) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKCA1FBA03DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_guarantors_corporate_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_guarantors_corporate_details` */

/*Table structure for table `fs_corporate_key_management_details` */

DROP TABLE IF EXISTS `fs_corporate_key_management_details`;

CREATE TABLE `fs_corporate_key_management_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `any_special_achievement` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `experience` varchar(255) DEFAULT NULL,
  `functionalDuties` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `qualification` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3AC23CE4DC8C6C36` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_key_management_details` */

/*Table structure for table `fs_corporate_monthly_turnover_details` */

DROP TABLE IF EXISTS `fs_corporate_monthly_turnover_details`;

CREATE TABLE `fs_corporate_monthly_turnover_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `month_name` varchar(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_Date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKB7B50680DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_monthly_turnover_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_monthly_turnover_details` */

/*Table structure for table `fs_corporate_overseas_network_mapping_details` */

DROP TABLE IF EXISTS `fs_corporate_overseas_network_mapping_details`;

CREATE TABLE `fs_corporate_overseas_network_mapping_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `overseas_network_id` int(2) unsigned DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBD855ADA8BAD821` (`overseas_network_id`),
  KEY `FKBD855AD4549B531` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_overseas_network_mapping_details` */

/*Table structure for table `fs_corporate_ownership_details` */

DROP TABLE IF EXISTS `fs_corporate_ownership_details`;

CREATE TABLE `fs_corporate_ownership_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `stack_percentage` double DEFAULT NULL,
  `remarks` text,
  `share_holding_category_id` int(2) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_Date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKFF8ADCC2DC8C6C36` (`application_id`),
  KEY `FKFF8ADCC27F8B473` (`share_holding_category_id`),
  CONSTRAINT `fs_corporate_ownership_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_ownership_details` */

/*Table structure for table `fs_corporate_past_financial_estimates_details` */

DROP TABLE IF EXISTS `fs_corporate_past_financial_estimates_details`;

CREATE TABLE `fs_corporate_past_financial_estimates_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `current_assets` double DEFAULT NULL,
  `current_liabilities` double DEFAULT NULL,
  `debt` double DEFAULT NULL,
  `ebitda` double DEFAULT NULL,
  `financial_year` varchar(20) DEFAULT NULL,
  `fixed_assets` double DEFAULT NULL,
  `net_worth` double DEFAULT NULL,
  `pat` double DEFAULT NULL,
  `sales` double DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_Date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31441620DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_past_financial_estimates_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_past_financial_estimates_details` */

/*Table structure for table `fs_corporate_primary_term_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_primary_term_loan_details`;

CREATE TABLE `fs_corporate_primary_term_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `project_brief` text,
  `total_cost_of_estimate` double(19,2) DEFAULT NULL,
  `total_means_of_finance` double(19,2) DEFAULT NULL,
  `collateral_security_amt_total` double(19,2) DEFAULT '0.00',
  `credit_rating_id` int(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `corp_tl_application_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_primary_term_loan_details` */

/*Table structure for table `fs_corporate_primary_wc_loan_details` */

DROP TABLE IF EXISTS `fs_corporate_primary_wc_loan_details`;

CREATE TABLE `fs_corporate_primary_wc_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `have_existing_limit` bit(1) DEFAULT NULL,
  `enhancement_of_limit` text,
  `project_brief` text,
  `credit_rating_id` int(2) unsigned DEFAULT NULL,
  `collateral_security_amt_total` double(19,2) DEFAULT '0.00',
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_corporate_primary_wc_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_corporate_primary_wc_loan_details` */

/*Table structure for table `fs_corporate_project_cost_details` */

DROP TABLE IF EXISTS `fs_corporate_project_cost_details`;

CREATE TABLE `fs_corporate_project_cost_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `already_incurred` double DEFAULT NULL,
  `to_be_incurred` double DEFAULT NULL,
  `total_cost` double DEFAULT NULL,
  `particulars_id` int(2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKBCFB238FDC8C6C36` (`application_id`),
  KEY `FKBCFB238FA0987F39` (`particulars_id`),
  CONSTRAINT `fs_corporate_project_cost_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_project_cost_details` */

/*Table structure for table `fs_corporate_project_implementation_schedule_details` */

DROP TABLE IF EXISTS `fs_corporate_project_implementation_schedule_details`;

CREATE TABLE `fs_corporate_project_implementation_schedule_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activities` varchar(255) DEFAULT NULL,
  `commencement_date` varchar(255) DEFAULT NULL,
  `completion_date` varchar(255) DEFAULT NULL,
  `timeline_total` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK14BCEFC4DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_project_implementation_schedule_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_project_implementation_schedule_details` */

/*Table structure for table `fs_corporate_promotor_background_details` */

DROP TABLE IF EXISTS `fs_corporate_promotor_background_details`;

CREATE TABLE `fs_corporate_promotor_background_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pan_no` varchar(10) DEFAULT NULL,
  `address` text,
  `age` double DEFAULT NULL,
  `promotors_name` varchar(50) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `qualification` text,
  `total_experience` double DEFAULT NULL,
  `achivements` text,
  `salutation_id` int(2) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `FK85614096DC8C6C36` (`application_id`),
  KEY `FK856140969461B69B` (`salutation_id`),
  CONSTRAINT `fs_corporate_promotor_background_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_promotor_background_details` */

/*Table structure for table `fs_corporate_proposed_product_details` */

DROP TABLE IF EXISTS `fs_corporate_proposed_product_details`;

CREATE TABLE `fs_corporate_proposed_product_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product` text,
  `application` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8973B036DC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_proposed_product_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_proposed_product_details` */

/*Table structure for table `fs_corporate_requirements_and_availability_raw_materials_details` */

DROP TABLE IF EXISTS `fs_corporate_requirements_and_availability_raw_materials_details`;

CREATE TABLE `fs_corporate_requirements_and_availability_raw_materials_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `availability` varchar(255) DEFAULT NULL,
  `lead_time` varchar(255) DEFAULT NULL,
  `name` varchar(2000) DEFAULT NULL,
  `quality` varchar(255) DEFAULT NULL,
  `sources` varchar(255) DEFAULT NULL,
  `measurement_unit__quantity` varchar(100) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fs_ibfk_2` (`application_id`),
  CONSTRAINT `fs_ibfk_2` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_requirements_and_availability_raw_materials_details` */

/*Table structure for table `fs_corporate_revenue_and_order_book_details` */

DROP TABLE IF EXISTS `fs_corporate_revenue_and_order_book_details`;

CREATE TABLE `fs_corporate_revenue_and_order_book_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `clientName` varchar(255) DEFAULT NULL,
  `geography` varchar(255) DEFAULT NULL,
  `orders_in_hand` varchar(255) DEFAULT NULL,
  `potential_orders` varchar(255) DEFAULT NULL,
  `revenues` double DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_revenue_and_order_book_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_revenue_and_order_book_details` */

/*Table structure for table `fs_corporate_scot_analysis_details` */

DROP TABLE IF EXISTS `fs_corporate_scot_analysis_details`;

CREATE TABLE `fs_corporate_scot_analysis_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `concerns_details` text,
  `concerns_measure` text,
  `opportunities_detials` text,
  `strength_details` text,
  `weakness_detials` text,
  `weakness_measure` text,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_scot_analysis_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_scot_analysis_details` */

/*Table structure for table `fs_corporate_security_corporate_details` */

DROP TABLE IF EXISTS `fs_corporate_security_corporate_details`;

CREATE TABLE `fs_corporate_security_corporate_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `primary_security_name` varchar(100) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_security_corporate_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_security_corporate_details` */

/*Table structure for table `fs_corporate_strategic_alliances_details` */

DROP TABLE IF EXISTS `fs_corporate_strategic_alliances_details`;

CREATE TABLE `fs_corporate_strategic_alliances_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `relationship_details` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `key_alliance_partners` varchar(50) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK839769FADC8C6C36` (`application_id`),
  CONSTRAINT `fs_corporate_strategic_alliances_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_strategic_alliances_details` */

/*Table structure for table `fs_corporate_technology_positioning_details` */

DROP TABLE IF EXISTS `fs_corporate_technology_positioning_details`;

CREATE TABLE `fs_corporate_technology_positioning_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `details` text,
  `type` varchar(255) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  `storage_details_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_corporate_technology_positioning_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

/*Data for the table `fs_corporate_technology_positioning_details` */

/*Table structure for table `fs_loan_application_master` */

DROP TABLE IF EXISTS `fs_loan_application_master`;

CREATE TABLE `fs_loan_application_master` (
  `application_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` int(11) unsigned DEFAULT NULL,
  `currency_id` int(2) unsigned DEFAULT NULL,
  `denomination_id` int(2) unsigned DEFAULT NULL,
  `name` varchar(100) DEFAULT '',
  `status` int(11) unsigned DEFAULT NULL,
  `tenure` double DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `denomination_master_id` bigint(2) unsigned DEFAULT NULL,
  `category_code` varchar(11) DEFAULT '',
  `user_id` bigint(20) DEFAULT NULL,
  `is_applicant_details_filled` bit(1) DEFAULT b'0',
  `is_applicant_primary_filled` bit(1) DEFAULT b'0',
  `is_applicant_final_filled` bit(1) DEFAULT b'0',
  `is_co_app1_details_filled` bit(1) DEFAULT b'0',
  `is_co_app1_final_filled` bit(1) DEFAULT b'0',
  `is_co_app2_details_filled` bit(1) DEFAULT b'0',
  `is_co_app2_final_filled` bit(1) DEFAULT b'0',
  `is_guarantor1_details_filled` bit(1) DEFAULT b'0',
  `is_guarantor1_final_filled` bit(1) DEFAULT b'0',
  `is_guarantor2_details_filled` bit(1) DEFAULT b'0',
  `is_guarantor2_final_filled` bit(1) DEFAULT b'0',
  `is_primary_upload_filled` bit(1) DEFAULT b'0',
  `is_final_mcq_filled` bit(1) DEFAULT b'0',
  `is_final_dpr_upload_filled` bit(1) DEFAULT b'0',
  `is_final_upload_filled` bit(1) DEFAULT b'0',
  `is_primary_locked` bit(1) DEFAULT b'0',
  `is_final_locked` bit(1) DEFAULT b'0',
  `details_filled_time` bigint(20) DEFAULT NULL,
  `primary_filled_time` bigint(20) DEFAULT NULL,
  `final_filled_time` bigint(20) DEFAULT NULL,
  `details_filled_count` decimal(19,2) DEFAULT '0.00',
  `primary_filled_count` decimal(19,2) DEFAULT '0.00',
  `final_filled_count` decimal(19,2) DEFAULT '0.00',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` bigint(11) DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `modified_by` bigint(11) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `application_code` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  KEY `status` (`status`),
  KEY `denomination_master_id` (`denomination_master_id`),
  CONSTRAINT `fs_loan_application_master_ibfk_1` FOREIGN KEY (`status`) REFERENCES `fs_application_status_master` (`id`),
  CONSTRAINT `fs_loan_application_master_ibfk_2` FOREIGN KEY (`denomination_master_id`) REFERENCES `denomination_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=255 DEFAULT CHARSET=latin1;

/*Data for the table `fs_loan_application_master` */

/*Table structure for table `fs_retail_applicant_details` */

DROP TABLE IF EXISTS `fs_retail_applicant_details`;

CREATE TABLE `fs_retail_applicant_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `gender_id` int(2) DEFAULT NULL,
  `title_id` int(2) unsigned DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `status_id` int(2) unsigned DEFAULT NULL,
  `occupation_id` int(2) unsigned DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `employed_with_id` int(2) unsigned DEFAULT NULL,
  `self_employed_occupation_id` int(2) unsigned DEFAULT NULL,
  `entity_name` varchar(255) DEFAULT NULL,
  `industry_type_id` int(2) unsigned DEFAULT NULL,
  `land_size` decimal(19,2) DEFAULT NULL,
  `allied_activity_id` int(2) unsigned DEFAULT NULL,
  `pan` varchar(10) DEFAULT NULL,
  `aadhar_number` varchar(20) DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `currency_id` int(2) unsigned DEFAULT NULL,
  `permanent_premise_number_name` varchar(255) DEFAULT NULL,
  `permanent_street_name` varchar(255) DEFAULT NULL,
  `permanent_land_mark` varchar(255) DEFAULT NULL,
  `permanent_pincode` bigint(12) unsigned DEFAULT NULL,
  `permanent_country_id` int(2) unsigned DEFAULT NULL,
  `permanent_state_id` int(2) unsigned DEFAULT NULL,
  `permanent_city_id` bigint(10) unsigned DEFAULT NULL,
  `office_premise_number_name` varchar(255) DEFAULT NULL,
  `office_street_name` varchar(255) DEFAULT NULL,
  `office_land_mark` varchar(255) DEFAULT NULL,
  `office_pincode` bigint(12) unsigned DEFAULT NULL,
  `office_country_id` int(2) unsigned DEFAULT NULL,
  `office_state_id` int(2) unsigned DEFAULT NULL,
  `office_city_id` bigint(10) unsigned DEFAULT NULL,
  `cast_id` int(2) unsigned DEFAULT NULL,
  `cast_other` varchar(100) DEFAULT NULL,
  `religion` int(2) unsigned DEFAULT NULL,
  `religion_other` varchar(100) DEFAULT NULL,
  `birth_place` varchar(100) DEFAULT NULL,
  `father_name` varchar(200) DEFAULT NULL,
  `mother_name` varchar(200) DEFAULT NULL,
  `spouse_name` varchar(100) DEFAULT NULL,
  `is_spouse_employed` bit(1) DEFAULT NULL,
  `no_children` int(2) unsigned DEFAULT NULL,
  `no_dependent` int(2) unsigned DEFAULT NULL,
  `highest_qualification` int(2) unsigned DEFAULT NULL,
  `highest_qualification_other` varchar(100) DEFAULT NULL,
  `qualifying_year` datetime DEFAULT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `residence_type` int(2) unsigned DEFAULT NULL,
  `annual_rent` decimal(19,2) DEFAULT NULL,
  `residing_year` double DEFAULT NULL,
  `residing_month` double DEFAULT NULL,
  `website_address` varchar(100) DEFAULT NULL,
  `address_premise_name` varchar(100) DEFAULT NULL,
  `address_street_name` varchar(100) DEFAULT NULL,
  `address_landmark` varchar(100) DEFAULT NULL,
  `address_pincode` bigint(10) unsigned DEFAULT NULL,
  `address_country` int(2) unsigned DEFAULT NULL,
  `address_state` bigint(10) unsigned DEFAULT NULL,
  `address_city` bigint(10) unsigned DEFAULT NULL,
  `employment_status` int(2) unsigned DEFAULT NULL,
  `current_industry` varchar(100) DEFAULT NULL,
  `current_department` varchar(100) DEFAULT NULL,
  `current_designation` varchar(100) DEFAULT NULL,
  `current_job_month` int(10) unsigned DEFAULT NULL,
  `current_job_year` int(10) unsigned DEFAULT NULL,
  `total_experience_month` int(10) unsigned DEFAULT NULL,
  `total_experience_year` int(10) unsigned DEFAULT NULL,
  `previous_job_month` int(10) unsigned DEFAULT NULL,
  `previous_job_year` int(10) unsigned DEFAULT NULL,
  `previous_employers_name` varchar(100) DEFAULT NULL,
  `previous_employers_address` varchar(200) DEFAULT NULL,
  `total_land_owned` decimal(19,2) DEFAULT NULL,
  `presently_irrigated` varchar(200) DEFAULT NULL,
  `seasonal_irrigated` varchar(200) DEFAULT NULL,
  `rain_fed` varchar(100) DEFAULT NULL,
  `unattended` varchar(100) DEFAULT NULL,
  `name_of_entity` varchar(100) DEFAULT NULL,
  `ownership_type` int(2) unsigned DEFAULT NULL,
  `office_type` int(2) unsigned DEFAULT NULL,
  `no_partners` int(5) unsigned DEFAULT NULL,
  `partners_name` varchar(100) DEFAULT NULL,
  `business_start_date` datetime DEFAULT NULL,
  `share_holding` varchar(100) DEFAULT NULL,
  `annual_turnover` decimal(19,2) DEFAULT NULL,
  `trade_license_number` varchar(100) DEFAULT NULL,
  `trade_license_expiry_date` datetime DEFAULT NULL,
  `poa_holder_name` varchar(100) DEFAULT NULL,
  `repayment_mode` int(2) unsigned DEFAULT NULL,
  `repayment_cycle` int(2) unsigned DEFAULT NULL,
  `interest_rate` int(2) unsigned DEFAULT NULL,
  `employed_with_other` varchar(100) DEFAULT NULL,
  `self_employed_occupation_other` varchar(100) DEFAULT NULL,
  `industry_type_other` varchar(100) DEFAULT NULL,
  `address_same_as` bit(1) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `retail_applicant_id_fk1` (`application_id`),
  CONSTRAINT `retail_applicant_id_fk1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_applicant_details` */

/*Table structure for table `fs_retail_bank_account_held_details` */

DROP TABLE IF EXISTS `fs_retail_bank_account_held_details`;

CREATE TABLE `fs_retail_bank_account_held_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_held_for` varchar(100) DEFAULT NULL,
  `bank_name_and_branch` varchar(100) DEFAULT NULL,
  `account_number` varchar(25) DEFAULT NULL,
  `account_type` varchar(100) DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `applicant_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `applicant_id` (`applicant_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_bank_account_held_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_bank_account_held_details` */

/*Table structure for table `fs_retail_co_applicant_details` */

DROP TABLE IF EXISTS `fs_retail_co_applicant_details`;

CREATE TABLE `fs_retail_co_applicant_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `relationship_with_applicant` int(2) DEFAULT NULL,
  `title_id` int(2) DEFAULT NULL,
  `gender_id` int(1) unsigned DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `middle_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL,
  `occupation_id` int(2) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `employed_with_id` int(2) DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `self_employed_occupation_id` int(2) DEFAULT NULL,
  `entity_name` varchar(255) DEFAULT NULL,
  `industry_type_id` int(2) DEFAULT NULL,
  `land_size` decimal(19,2) DEFAULT NULL,
  `allied_activity_id` int(2) DEFAULT NULL,
  `pan` varchar(10) DEFAULT NULL,
  `aadhar_number` varchar(20) DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `permanent_premise_number_name` varchar(255) DEFAULT NULL,
  `permanent_street_name` varchar(255) DEFAULT NULL,
  `permanent_land_mark` varchar(255) DEFAULT NULL,
  `permanent_pincode` int(12) DEFAULT NULL,
  `permanent_country_id` int(2) DEFAULT NULL,
  `permanent_state_id` int(2) DEFAULT NULL,
  `permanent_city_id` int(2) DEFAULT NULL,
  `office_premise_number_name` varchar(255) DEFAULT NULL,
  `office_street_name` varchar(255) DEFAULT NULL,
  `office_land_mark` varchar(255) DEFAULT NULL,
  `office_pincode` int(12) DEFAULT NULL,
  `office_country_id` int(2) DEFAULT NULL,
  `office_state_id` int(2) DEFAULT NULL,
  `office_city_id` int(2) DEFAULT NULL,
  `cast_id` int(2) DEFAULT NULL,
  `cast_other` varchar(100) DEFAULT NULL,
  `religion` int(2) DEFAULT NULL,
  `religion_other` varchar(100) DEFAULT NULL,
  `birth_place` varchar(100) DEFAULT NULL,
  `father_name` varchar(200) DEFAULT NULL,
  `mother_name` varchar(200) DEFAULT NULL,
  `spouse_name` varchar(100) DEFAULT NULL,
  `is_spouse_employed` bit(1) DEFAULT NULL,
  `no_children` int(2) DEFAULT NULL,
  `no_dependent` int(2) DEFAULT NULL,
  `highest_qualification` int(2) DEFAULT NULL,
  `highest_qualification_other` varchar(100) DEFAULT NULL,
  `qualifying_year` datetime DEFAULT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `residence_type` int(2) DEFAULT NULL,
  `annual_rent` decimal(19,2) DEFAULT NULL,
  `residing_year` double DEFAULT NULL,
  `residing_month` double DEFAULT NULL,
  `website_address` varchar(100) DEFAULT NULL,
  `address_premise_name` varchar(100) DEFAULT NULL,
  `address_street_name` varchar(100) DEFAULT NULL,
  `address_landmark` varchar(100) DEFAULT NULL,
  `address_pincode` bigint(10) DEFAULT NULL,
  `address_country` int(2) DEFAULT NULL,
  `address_state` int(2) DEFAULT NULL,
  `address_city` int(2) DEFAULT NULL,
  `employment_status` int(2) DEFAULT NULL,
  `current_industry` varchar(100) DEFAULT NULL,
  `current_department` varchar(100) DEFAULT NULL,
  `current_designation` varchar(100) DEFAULT NULL,
  `current_job_month` int(10) DEFAULT NULL,
  `current_job_year` int(10) DEFAULT NULL,
  `total_experience_month` int(10) DEFAULT NULL,
  `total_experience_year` int(10) DEFAULT NULL,
  `previous_job_month` int(10) DEFAULT NULL,
  `previous_job_year` int(10) DEFAULT NULL,
  `previous_employers_name` varchar(100) DEFAULT NULL,
  `previous_employers_address` varchar(200) DEFAULT NULL,
  `total_land_owned` decimal(19,2) DEFAULT NULL,
  `presently_irrigated` varchar(200) DEFAULT NULL,
  `seasonal_irrigated` varchar(200) DEFAULT NULL,
  `rain_fed` varchar(100) DEFAULT NULL,
  `unattended` varchar(100) DEFAULT NULL,
  `name_of_entity` varchar(100) DEFAULT NULL,
  `ownership_type` int(2) DEFAULT NULL,
  `office_type` int(2) DEFAULT NULL,
  `no_partners` int(5) DEFAULT NULL,
  `partners_name` varchar(100) DEFAULT NULL,
  `business_start_date` datetime DEFAULT NULL,
  `share_holding` varchar(100) DEFAULT NULL,
  `annual_turnover` decimal(19,2) DEFAULT NULL,
  `trade_license_number` varchar(100) DEFAULT NULL,
  `trade_license_expiry_date` datetime DEFAULT NULL,
  `poa_holder_name` varchar(100) DEFAULT NULL,
  `employed_with_other` varchar(100) DEFAULT NULL,
  `self_employed_occupation_other` varchar(100) DEFAULT NULL,
  `industry_type_other` varchar(100) DEFAULT NULL,
  `address_same_as` bit(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `application_id_fk12` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_co_applicant_details` */

/*Table structure for table `fs_retail_credit_cards_details` */

DROP TABLE IF EXISTS `fs_retail_credit_cards_details`;

CREATE TABLE `fs_retail_credit_cards_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `card_number` varchar(50) DEFAULT NULL,
  `issuer_name` varchar(100) DEFAULT NULL,
  `outstanding_balance` double DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `applicantion_id` bigint(20) unsigned DEFAULT NULL,
  `credit_card_types_id` int(2) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA331396FDC8C6C36` (`applicantion_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_1` FOREIGN KEY (`applicantion_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_credit_cards_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_credit_cards_details` */

/*Table structure for table `fs_retail_existing_loan_detail` */

DROP TABLE IF EXISTS `fs_retail_existing_loan_detail`;

CREATE TABLE `fs_retail_existing_loan_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_or_financer_name` varchar(100) DEFAULT NULL,
  `emi_amount` double DEFAULT NULL,
  `no_of_emi_paid` int(3) DEFAULT NULL,
  `outstanding_balance` double DEFAULT NULL,
  `loan_tenure` int(2) DEFAULT NULL,
  `loan_type` varchar(100) DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `applicant_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKAD7D565C2771166F` (`guarantor_detail_id`),
  KEY `FKAD7D565CDC8C6C36` (`applicant_id`),
  KEY `FKAD7D565C58921DA5` (`co_applicant_detail_id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_1` FOREIGN KEY (`applicant_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_existing_loan_detail_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_existing_loan_detail` */

/*Table structure for table `fs_retail_final_car_loan_details` */

DROP TABLE IF EXISTS `fs_retail_final_car_loan_details`;

CREATE TABLE `fs_retail_final_car_loan_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned NOT NULL,
  `delivery_date` datetime DEFAULT NULL,
  `car_colour` varchar(200) DEFAULT NULL,
  `car_supplier` varchar(200) DEFAULT NULL,
  `car_registration_number` varchar(200) DEFAULT NULL,
  `vehicle_cost` decimal(19,2) DEFAULT NULL,
  `insurance_cost` decimal(19,2) DEFAULT NULL,
  `accessories_cost` decimal(19,2) DEFAULT NULL,
  `road_tax` decimal(19,2) DEFAULT NULL,
  `others_cost` decimal(19,2) DEFAULT NULL,
  `loan_total_cost` decimal(19,2) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_final_car_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_final_car_loan_details` */

/*Table structure for table `fs_retail_final_home_loan_details` */

DROP TABLE IF EXISTS `fs_retail_final_home_loan_details`;

CREATE TABLE `fs_retail_final_home_loan_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `property_address_premise` varchar(200) DEFAULT NULL,
  `property_address_street` varchar(200) DEFAULT NULL,
  `property_address_landmark` varchar(200) DEFAULT NULL,
  `property_address_pincode` varchar(200) DEFAULT NULL,
  `property_address_country` int(2) DEFAULT NULL,
  `property_address_city` int(2) DEFAULT NULL,
  `property_address_state` int(2) DEFAULT NULL,
  `built_up_area` decimal(19,2) DEFAULT NULL,
  `carpet_area` decimal(19,2) DEFAULT NULL,
  `super_built_up_area` decimal(19,2) DEFAULT NULL,
  `seller_name` varchar(200) DEFAULT NULL,
  `sellers_address_premise` varchar(200) DEFAULT NULL,
  `sellers_address_street` varchar(200) DEFAULT NULL,
  `sellers_address_landmark` varchar(200) DEFAULT NULL,
  `sellers_address_pincode` varchar(200) DEFAULT NULL,
  `sellers_address_country` int(2) DEFAULT NULL,
  `sellers_address_city` int(2) DEFAULT NULL,
  `sellers_address_state` int(2) DEFAULT NULL,
  `property_used` varchar(200) DEFAULT NULL,
  `estimated_rental_income` decimal(19,2) DEFAULT NULL,
  `project_city_state` varchar(200) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `id` (`id`),
  CONSTRAINT `fs_retail_final_home_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_final_home_loan_details` */

/*Table structure for table `fs_retail_fixed_deposits_details` */

DROP TABLE IF EXISTS `fs_retail_fixed_deposits_details`;

CREATE TABLE `fs_retail_fixed_deposits_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double DEFAULT NULL,
  `bank_name` varchar(100) DEFAULT NULL,
  `fd_number` varchar(50) DEFAULT NULL,
  `maturity_date` date DEFAULT NULL,
  `rate` double DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_fixed_deposits_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_fixed_deposits_details` */

/*Table structure for table `fs_retail_guarantor_details` */

DROP TABLE IF EXISTS `fs_retail_guarantor_details`;

CREATE TABLE `fs_retail_guarantor_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `title_id` int(2) DEFAULT NULL,
  `gender_id` int(1) unsigned DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `middle_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `contact_no` varchar(20) DEFAULT NULL,
  `status_id` int(2) DEFAULT NULL,
  `occupation_id` int(2) DEFAULT NULL,
  `company_name` varchar(200) DEFAULT NULL,
  `employed_with_id` int(2) DEFAULT NULL,
  `self_employed_occupation_id` int(2) DEFAULT NULL,
  `entity_name` varchar(200) DEFAULT NULL,
  `industry_type_id` int(2) DEFAULT NULL,
  `land_size` decimal(19,2) DEFAULT NULL,
  `allied_activity_id` int(2) DEFAULT NULL,
  `pan` varchar(10) DEFAULT NULL,
  `aadhar_number` varchar(20) DEFAULT NULL,
  `monthly_income` double DEFAULT NULL,
  `permanent_premise_number_name` varchar(200) DEFAULT NULL,
  `permanent_street_name` varchar(200) DEFAULT NULL,
  `permanent_land_mark` varchar(200) DEFAULT NULL,
  `permanent_pincode` int(20) DEFAULT NULL,
  `permanent_country_id` int(2) DEFAULT NULL,
  `permanent_state_id` int(2) DEFAULT NULL,
  `permanent_city_id` int(2) DEFAULT NULL,
  `office_premise_number_name` varchar(200) DEFAULT NULL,
  `office_street_name` varchar(200) DEFAULT NULL,
  `office_land_mark` varchar(200) DEFAULT NULL,
  `office_pincode` int(20) DEFAULT NULL,
  `office_country_id` int(2) DEFAULT NULL,
  `office_state_id` int(2) DEFAULT NULL,
  `office_city_id` int(2) DEFAULT NULL,
  `cast_id` int(2) DEFAULT NULL,
  `cast_other` varchar(100) DEFAULT NULL,
  `religion` int(2) DEFAULT NULL,
  `religion_other` varchar(100) DEFAULT NULL,
  `birth_place` varchar(100) DEFAULT NULL,
  `father_name` varchar(100) DEFAULT NULL,
  `mother_name` varchar(100) DEFAULT NULL,
  `spouse_name` varchar(100) DEFAULT NULL,
  `is_spouse_employed` bit(1) DEFAULT NULL,
  `no_children` int(2) DEFAULT NULL,
  `no_dependent` int(2) DEFAULT NULL,
  `highest_qualification` int(2) DEFAULT NULL,
  `highest_qualification_other` varchar(100) DEFAULT NULL,
  `qualifying_year` datetime DEFAULT NULL,
  `institute` varchar(100) DEFAULT NULL,
  `residence_type` int(2) DEFAULT NULL,
  `annual_rent` decimal(19,2) DEFAULT NULL,
  `residing_year` double DEFAULT NULL,
  `residing_month` double DEFAULT NULL,
  `website_address` varchar(100) DEFAULT NULL,
  `address_premise_name` varchar(200) DEFAULT NULL,
  `address_street_name` varchar(200) DEFAULT NULL,
  `address_landmark` varchar(200) DEFAULT NULL,
  `address_pincode` bigint(20) DEFAULT NULL,
  `address_country` int(2) DEFAULT NULL,
  `address_state` int(2) DEFAULT NULL,
  `address_city` int(2) DEFAULT NULL,
  `employment_status` int(2) DEFAULT NULL,
  `current_industry` varchar(100) DEFAULT NULL,
  `current_department` varchar(100) DEFAULT NULL,
  `current_designation` varchar(100) DEFAULT NULL,
  `current_job_month` int(10) DEFAULT NULL,
  `current_job_year` int(10) DEFAULT NULL,
  `total_experience_month` int(10) DEFAULT NULL,
  `total_experience_year` int(10) DEFAULT NULL,
  `previous_job_month` int(10) DEFAULT NULL,
  `previous_job_year` int(10) DEFAULT NULL,
  `previous_employers_name` varchar(100) DEFAULT NULL,
  `previous_employers_address` varchar(200) DEFAULT NULL,
  `total_land_owned` decimal(19,2) DEFAULT NULL,
  `presently_irrigated` varchar(100) DEFAULT NULL,
  `seasonal_irrigated` varchar(100) DEFAULT NULL,
  `rain_fed` varchar(100) DEFAULT NULL,
  `unattended` varchar(100) DEFAULT NULL,
  `name_of_entity` varchar(100) DEFAULT NULL,
  `ownership_type` int(2) DEFAULT NULL,
  `office_type` int(2) DEFAULT NULL,
  `no_partners` int(5) DEFAULT NULL,
  `partners_name` varchar(100) DEFAULT NULL,
  `business_start_date` datetime DEFAULT NULL,
  `share_holding` varchar(100) DEFAULT NULL,
  `annual_turnover` decimal(19,2) DEFAULT NULL,
  `trade_license_number` varchar(100) DEFAULT NULL,
  `trade_license_expiry_date` datetime DEFAULT NULL,
  `poa_holder_name` varchar(100) DEFAULT NULL,
  `employed_with_other` varchar(100) DEFAULT NULL,
  `self_employed_occupation_other` varchar(100) DEFAULT NULL,
  `industry_type_other` varchar(100) DEFAULT NULL,
  `address_same_as` bit(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_guarantor_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_guarantor_details` */

/*Table structure for table `fs_retail_lap_loan_details` */

DROP TABLE IF EXISTS `fs_retail_lap_loan_details`;

CREATE TABLE `fs_retail_lap_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `loan_purpose` int(2) DEFAULT NULL,
  `loan_purpose_other` varchar(200) DEFAULT NULL,
  `property_type` int(2) DEFAULT NULL,
  `property_type_other` varchar(200) DEFAULT NULL,
  `occupation_status` int(2) DEFAULT NULL,
  `occupation_status_other` varchar(200) DEFAULT NULL,
  `property_age_in_year` int(10) DEFAULT NULL,
  `property_age_in_month` int(10) DEFAULT NULL,
  `land_area` decimal(19,2) DEFAULT NULL,
  `built_up_area` decimal(19,2) DEFAULT NULL,
  `property_value` decimal(19,2) DEFAULT NULL,
  `owner_name` varchar(200) DEFAULT NULL,
  `address_premise` varchar(200) DEFAULT NULL,
  `address_street` varchar(200) DEFAULT NULL,
  `address_landmark` varchar(200) DEFAULT NULL,
  `address_city` int(2) DEFAULT NULL,
  `address_state` int(2) DEFAULT NULL,
  `address_country` int(2) DEFAULT NULL,
  `pincode` int(10) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_lap_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_lap_loan_details` */

/*Table structure for table `fs_retail_las_loan_details` */

DROP TABLE IF EXISTS `fs_retail_las_loan_details`;

CREATE TABLE `fs_retail_las_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `loan_purpose` int(2) unsigned DEFAULT NULL,
  `loan_purpose_other` varchar(200) DEFAULT NULL,
  `month` int(2) unsigned DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_las_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_las_loan_details` */

/*Table structure for table `fs_retail_other_current_asset_details` */

DROP TABLE IF EXISTS `fs_retail_other_current_asset_details`;

CREATE TABLE `fs_retail_other_current_asset_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `asset_description` text,
  `asset_value` double DEFAULT NULL,
  `asset_types_id` int(2) DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_other_current_asset_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_other_current_asset_details` */

/*Table structure for table `fs_retail_other_income_details` */

DROP TABLE IF EXISTS `fs_retail_other_income_details`;

CREATE TABLE `fs_retail_other_income_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `gross_income` double DEFAULT NULL,
  `income_head` text,
  `net_income` double DEFAULT NULL,
  `income_details_id` int(2) DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_other_income_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_other_income_details` */

/*Table structure for table `fs_retail_personal_loan_details` */

DROP TABLE IF EXISTS `fs_retail_personal_loan_details`;

CREATE TABLE `fs_retail_personal_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `loan_purpose` bigint(20) DEFAULT NULL,
  `loan_purpose_other` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_personal_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_personal_loan_details` */

/*Table structure for table `fs_retail_primary_car_loan_details` */

DROP TABLE IF EXISTS `fs_retail_primary_car_loan_details`;

CREATE TABLE `fs_retail_primary_car_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL DEFAULT '0',
  `car_type` int(2) DEFAULT NULL,
  `new_car_purchase_type` int(2) DEFAULT NULL,
  `purchase_reimbursment_date` datetime DEFAULT NULL,
  `purchase_preowned_date` datetime DEFAULT NULL,
  `certified_dealer` tinyint(1) DEFAULT NULL,
  `dealer_name` varchar(200) DEFAULT NULL,
  `manufacturer_name` varchar(200) DEFAULT NULL,
  `car_model_name` varchar(200) DEFAULT NULL,
  `car_varient` varchar(200) DEFAULT NULL,
  `on_road_car_price` decimal(19,2) DEFAULT NULL,
  `down_payment` decimal(19,2) DEFAULT NULL,
  `delivery_date` datetime DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_primary_car_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_primary_car_loan_details` */

/*Table structure for table `fs_retail_primary_home_loan_details` */

DROP TABLE IF EXISTS `fs_retail_primary_home_loan_details`;

CREATE TABLE `fs_retail_primary_home_loan_details` (
  `application_id` bigint(20) unsigned NOT NULL,
  `property_type` int(2) DEFAULT NULL,
  `property_used_type` int(2) DEFAULT NULL,
  `is_construction_completed` bit(1) DEFAULT b'0',
  `completion_time_in_month` int(10) DEFAULT NULL,
  `completion_time_in_year` int(10) DEFAULT NULL,
  `project_name` varchar(200) DEFAULT NULL,
  `project_city` varchar(40) DEFAULT NULL,
  `area` decimal(19,2) DEFAULT NULL,
  `property_price` decimal(19,2) DEFAULT NULL,
  `bunglow_cost` decimal(19,2) DEFAULT NULL,
  `construction_cost` decimal(19,2) DEFAULT NULL,
  `construction_completion_time_in_month` int(10) DEFAULT NULL,
  `construction_completion_time_in_year` int(10) DEFAULT NULL,
  `renovation_type` int(2) DEFAULT NULL,
  `other_renovation_type` varchar(200) DEFAULT NULL,
  `renovation_cost` decimal(19,2) DEFAULT NULL,
  `renovation_completion_time_in_month` int(10) DEFAULT NULL,
  `renovation_completion_time_in_year` int(10) DEFAULT NULL,
  `is_loan_taken` bit(1) DEFAULT b'0',
  `date_of_loan_taken` datetime DEFAULT NULL,
  `land_plot_cost` decimal(19,2) DEFAULT NULL,
  `land_area` decimal(19,2) DEFAULT NULL,
  `down_payment` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`application_id`),
  CONSTRAINT `fs_retail_primary_home_loan_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_primary_home_loan_details` */

/*Table structure for table `fs_retail_references_retail_details` */

DROP TABLE IF EXISTS `fs_retail_references_retail_details`;

CREATE TABLE `fs_retail_references_retail_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` text,
  `email` varchar(100) DEFAULT NULL,
  `mobile` varchar(25) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `telephone` varchar(25) DEFAULT NULL,
  `references_list_id` int(2) DEFAULT NULL,
  `co_applicant_detail_id` bigint(20) DEFAULT NULL,
  `guarantor_detail_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `co_applicant_detail_id` (`co_applicant_detail_id`),
  KEY `guarantor_detail_id` (`guarantor_detail_id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_2` FOREIGN KEY (`co_applicant_detail_id`) REFERENCES `fs_retail_co_applicant_details` (`id`),
  CONSTRAINT `fs_retail_references_retail_details_ibfk_3` FOREIGN KEY (`guarantor_detail_id`) REFERENCES `fs_retail_guarantor_details` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `fs_retail_references_retail_details` */

/*Table structure for table `fs_retail_security_retail_details` */

DROP TABLE IF EXISTS `fs_retail_security_retail_details`;

CREATE TABLE `fs_retail_security_retail_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buying_date` datetime DEFAULT NULL,
  `security_type` int(2) DEFAULT NULL,
  `security_name` varchar(200) DEFAULT NULL,
  `number_of_units` int(10) DEFAULT NULL,
  `current_market_value` decimal(19,2) DEFAULT NULL,
  `total_value` decimal(19,2) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `is_promoter` bit(1) DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_retail_security_retail_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `fs_retail_security_retail_details` */

/*Table structure for table `fs_sector_subsector_details` */

DROP TABLE IF EXISTS `fs_sector_subsector_details`;

CREATE TABLE `fs_sector_subsector_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `sector_subsector_transaction_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `modified_by` bigint(20) unsigned DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  CONSTRAINT `fs_sector_subsector_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`)
) ENGINE=InnoDB AUTO_INCREMENT=211 DEFAULT CHARSET=latin1;

/*Data for the table `fs_sector_subsector_details` */

/*Table structure for table `fund_provider_sequence` */

DROP TABLE IF EXISTS `fund_provider_sequence`;

CREATE TABLE `fund_provider_sequence` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `sequence_number` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `fund_provider_sequence` */

insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (1,1,10008);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (2,2,10005);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (3,3,10002);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (4,7,10000);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (5,12,10000);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (6,13,10000);
insert  into `fund_provider_sequence`(`id`,`product_id`,`sequence_number`) values (7,14,10000);

/*Table structure for table `industry_sector_details` */

DROP TABLE IF EXISTS `industry_sector_details`;

CREATE TABLE `industry_sector_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `industry_id` bigint(20) DEFAULT NULL,
  `sector_id` bigint(20) DEFAULT NULL,
  `application_id` bigint(20) unsigned DEFAULT NULL,
  `fp_product_id` bigint(20) unsigned DEFAULT NULL,
  `created_date` datetime NOT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `application_id` (`application_id`),
  KEY `fp_product_id` (`fp_product_id`),
  CONSTRAINT `industry_sector_details_ibfk_1` FOREIGN KEY (`application_id`) REFERENCES `fs_loan_application_master` (`application_id`),
  CONSTRAINT `industry_sector_details_ibfk_2` FOREIGN KEY (`fp_product_id`) REFERENCES `fp_product_master` (`fp_product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1117 DEFAULT CHARSET=latin1;

/*Data for the table `industry_sector_details` */

/*Table structure for table `proposal_details` */

DROP TABLE IF EXISTS `proposal_details`;

CREATE TABLE `proposal_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `fp_product_id` bigint(20) DEFAULT NULL,
  `proposal_status_id` bigint(20) unsigned DEFAULT NULL,
  `is_proposal_auto` bit(1) DEFAULT b'0',
  `proposal_stage` int(1) DEFAULT NULL,
  `initiated_by` int(1) DEFAULT NULL,
  `last_action_performed_by` int(1) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'0',
  PRIMARY KEY (`id`),
  KEY `match_status_id` (`proposal_status_id`),
  CONSTRAINT `proposal_details_ibfk_1` FOREIGN KEY (`proposal_status_id`) REFERENCES `proposal_status_master` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=311 DEFAULT CHARSET=latin1;

/*Data for the table `proposal_details` */

/*Table structure for table `proposal_status_master` */

DROP TABLE IF EXISTS `proposal_status_master`;

CREATE TABLE `proposal_status_master` (
  `id` bigint(20) unsigned NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `is_active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `proposal_status_master` */

insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (1,'Pending','PENDING','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (2,'Accept','ACCEPT','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (3,'Hold','HOLD','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (4,'Decline','DECLINE','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (5,'Approved','APPROVED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (6,'Published','PUBLISHED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');
insert  into `proposal_status_master`(`id`,`status`,`code`,`created_date`,`modified_date`,`created_by`,`modified_by`,`is_active`) values (7,'Canceled','CANCELED','2017-04-25 12:22:02','2017-04-25 12:22:02',NULL,NULL,'');

/* Procedure structure for procedure `procPrintData` */

/*!50003 DROP PROCEDURE IF EXISTS  `procPrintData` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `procPrintData`()
Begin
DECLARE done INT DEFAULT FALSE;
DECLARE CustomerId INT;
declare FirstName VARCHAR(50);
declare LastName VARCHAR(50);
DECLARE MessageOutput VARCHAR(100);
DECLARE Customer_Cursor CURSOR FOR select `CustomerId`,  `FirstName`,  `LastName` from  `demo`.`Customers` limit 0, 1000;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
OPEN Customer_Cursor;
read_loop: loop
	IF done THEN
		LEAVE read_loop;
	END IF;
	fetch Customer_Cursor into CustomerId, FirstName, LastName;
	select FirstName;	
end loop;
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
