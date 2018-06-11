
CREATE TABLE `loan_application`.`user_loan_amount_mapping` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`user_id`  bigint(20) NOT NULL,
`product_id`  bigint(20) NOT NULL,
`min_amount`  double DEFAULT NULL,
`max_amount`  double DEFAULT NULL,
`created_by`  bigint(20) NOT NULL,
`created_date`  datetime DEFAULT NULL,
`modified_by`  bigint(20) NOT NULL,
`modified_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

