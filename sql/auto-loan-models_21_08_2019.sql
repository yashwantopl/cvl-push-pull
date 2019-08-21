CREATE TABLE `loan_application`.`auto_loan_model` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  
  `is_personal_use` BIT(1) DEFAULT NULL,
  `is_official_use` BIT(1) DEFAULT NULL,
  `is_small_car` BIT(1) DEFAULT NULL,
  `is_mid_car` BIT(1) DEFAULT NULL,
  `is_luxury_car` BIT(1) DEFAULT NULL,
  `is_suv_or_muv` BIT(1) DEFAULT NULL,
  `is_electric_or_non_conventional_car` BIT(1) DEFAULT NULL,
  `is_two_wheeler_loan` BIT(1) DEFAULT NULL,
  `is_electric_or_non_conventional_two_wheeler` BIT(1) DEFAULT NULL,
  `is_second_hand_four_wheeler_loan` INT(3) DEFAULT NULL,
  `is_second_hand_two_wheeler_loan` INT(3) DEFAULT NULL,
 
  PRIMARY KEY (`id`),
  CONSTRAINT `retail_model_auto_loan_fk` FOREIGN KEY (`id`) REFERENCES `retail_model` (`id`)
);

CREATE TABLE `loan_application`.`auto_loan_model_temp` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  
  `is_personal_use` BIT(1) DEFAULT NULL,
  `is_official_use` BIT(1) DEFAULT NULL,
  `is_small_car` BIT(1) DEFAULT NULL,
  `is_mid_car` BIT(1) DEFAULT NULL,
  `is_luxury_car` BIT(1) DEFAULT NULL,
  `is_suv_or_muv` BIT(1) DEFAULT NULL,
  `is_electric_or_non_conventional_car` BIT(1) DEFAULT NULL,
  `is_two_wheeler_loan` BIT(1) DEFAULT NULL,
  `is_electric_or_non_conventional_two_wheeler` BIT(1) DEFAULT NULL,
  `is_second_hand_four_wheeler_loan` INT(3) DEFAULT NULL,
  `is_second_hand_two_wheeler_loan` INT(3) DEFAULT NULL,
 
  PRIMARY KEY (`id`),
  CONSTRAINT `retail_model_temp_auto_loan_fk` FOREIGN KEY (`id`) REFERENCES `retail_model_temp` (`id`)
);




