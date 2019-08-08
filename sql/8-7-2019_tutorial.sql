CREATE TABLE `loan_application`.`tutorial_view_audit` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT(20),
  `role_id` BIGINT(20),
  `tutorial_id` BIGINT(20),
  `view_date` DATE,
  PRIMARY KEY (`id`)
);
