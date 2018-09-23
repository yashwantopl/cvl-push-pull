/*
SQLyog Ultimate v11.5 (64 bit)
MySQL - 5.7.17-log : Database - loan_application
*********************************************************************
*/

USE `loan_application`;

/*Table structure for table `ineligible_proposal_details` */

DROP TABLE IF EXISTS `ineligible_proposal_details`;

CREATE TABLE `ineligible_proposal_details` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `application_id` bigint(20) DEFAULT NULL,
  `loan_amount` decimal(19,2) DEFAULT NULL,
  `user_org_id` bigint(20) DEFAULT NULL,
  `branch_id` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `modified_date` datetime DEFAULT NULL,
  `is_active` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

