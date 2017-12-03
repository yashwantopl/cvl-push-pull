CREATE TABLE `loan_applications`.org_branch_audit (
	id BIGINT (20) NOT NULL AUTO_INCREMENT,
	application_id BIGINT (20) DEFAULT NULL,
	branch_id BIGINT (20) DEFAULT NULL,
	branch_name VARCHAR (150) DEFAULT NULL,
	org_id BIGINT(20) DEFAULT NULL,
	created_date DATETIME DEFAULT NULL,
	modified_date DATETIME DEFAULT NULL,
  	created_by BIGINT(20) DEFAULT NULL,
  	modified_by BIGINT(20) DEFAULT NULL,
  	is_active BIT(1) DEFAULT NULL,
 	PRIMARY KEY  (id)
) ENGINE=INNODB DEFAULT CHARSET=latin1;