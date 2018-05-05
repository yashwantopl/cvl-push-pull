
CREATE TABLE `loan_application`.`audit_master` (
`id` bigint(20) unsigned NOT NULL auto_increment,
`type` int NOT NULL,
`application_id`  bigint(20) DEFAULT NULL,
`user_id`  bigint(20) DEFAULT NULL,
`is_success` bit(1) DEFAULT b'0',
`created_by`  bigint(20) DEFAULT NULL,
`created_date`  datetime DEFAULT NULL,
`modified_by` bigint(20) DEFAULT NULL,
`modified_date` datetime DEFAULT NULL,
`is_active` bit(1) DEFAULT b'0',
 PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
