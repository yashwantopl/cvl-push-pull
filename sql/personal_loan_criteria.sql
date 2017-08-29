/*
SQLyog Ultimate v12.09 (64 bit)
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

/*Table structure for table `personal_loan_criteria` */

DROP TABLE IF EXISTS `personal_loan_criteria`;

CREATE TABLE `personal_loan_criteria` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `type` int(2) unsigned DEFAULT NULL,
  `bank_id` int(3) unsigned DEFAULT NULL,
  `min` bigint(20) unsigned DEFAULT NULL,
  `max` bigint(20) unsigned DEFAULT NULL,
  `foir` decimal(7,2) DEFAULT NULL,
  `roi_low` decimal(7,2) DEFAULT NULL,
  `roi_high` decimal(7,2) DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  `created_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_date` datetime DEFAULT NULL,
  `created_by` bigint(20) unsigned DEFAULT NULL,
  `updated_by` bigint(20) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `personal_loan_criteria` */

insert  into `personal_loan_criteria`(`id`,`type`,`bank_id`,`min`,`max`,`foir`,`roi_low`,`roi_high`,`is_active`,`created_date`,`updated_date`,`created_by`,`updated_by`) values (1,2,10,20000,9223372036854775807,'60.00','12.00','20.00','','2017-08-22 18:19:16',NULL,NULL,NULL),(2,2,6,10000,9223372036854775807,'60.00','14.00','34.00','','2017-08-22 18:20:02',NULL,NULL,NULL),(3,1,6,25000,9223372036854775807,'60.00','14.00','34.00','','2017-08-22 18:20:39',NULL,NULL,NULL),(4,2,13,10000,9223372036854775807,'40.00','18.00','34.00','','2017-08-22 18:21:48',NULL,NULL,NULL),(5,2,11,42000,9223372036854775807,'65.00','12.00','13.75','','2017-08-22 18:23:07',NULL,NULL,NULL),(6,2,14,20000,9223372036854775807,'50.00','12.50','21.00','','2017-08-22 18:24:09',NULL,NULL,NULL),(7,2,1,20000,9223372036854775807,'50.00','12.00','17.00','','2017-08-22 18:24:45',NULL,NULL,NULL),(8,2,15,20000,9223372036854775807,'50.00','12.00','20.00','','2017-08-22 18:31:19',NULL,NULL,NULL),(9,2,2,18000,9223372036854775807,'65.00','13.00','20.00','','2017-08-22 18:31:45',NULL,NULL,NULL),(10,2,3,15000,24999,'50.00','12.25','24.00','','2017-08-22 18:32:12',NULL,NULL,NULL),(11,2,3,25000,9223372036854775807,'60.00','12.25','24.00','','2017-08-22 18:32:32',NULL,NULL,NULL),(12,2,4,17500,9223372036854775807,'55.00','12.00','18.00','','2017-08-22 18:33:51',NULL,NULL,NULL),(13,2,16,20000,9223372036854775807,'60.00','14.00','17.00','','2017-08-22 18:34:33',NULL,NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
