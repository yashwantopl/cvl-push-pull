ALTER TABLE `loan_application`.`fs_corporate_applicant_details`
ADD COLUMN `administrative_dist_mapping_id` BIGINT(20) NULL ,
ADD COLUMN `registered_dist_mapping_id` BIGINT(20) NULL ;


ALTER TABLE `loan_application`.`fs_corporate_director_background_details`
ADD COLUMN `district_mapping_id` BIGINT(20) NULL ;
