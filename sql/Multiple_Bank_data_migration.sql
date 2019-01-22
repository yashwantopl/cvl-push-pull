1. UPDATE `fs_corporate_final_mcq_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
2. UPDATE `fs_sector_subsector_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
3. UPDATE `industry_sector_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
4. UPDATE `fs_corporate_existing_product_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
5. UPDATE `fs_corporate_associated_concern_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
6. UPDATE `fs_corporate_security_corporate_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
7. UPDATE `fs_corporate_credit_rating_organization_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
8. UPDATE `fs_corporate_finance_means_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
9. UPDATE `fs_corporate_project_cost_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
10. UPDATE `fs_ddr_form_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
11. UPDATE `product_storage_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
12. UPDATE `fs_corporate_cma_assets_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
13. UPDATE `fs_corporate_cma_liabilities_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
14. UPDATE `fs_corporate_cma_operating_statement_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
15. UPDATE `fs_corporate_promotor_background_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
16. UPDATE `fs_corporate_current_financial_arrangements_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
17. UPDATE `fs_corporate_director_background_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
18. UPDATE `fs_corporate_bs_profitibility_statement_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
19. UPDATE `application_status_audit` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
20. UPDATE `fs_corporate_ownership_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
21. UPDATE `fs_corporate_achievement_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
22. UPDATE `fs_corporate_guarantors_corporate_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
23. UPDATE `fs_corporate_proposed_product_details` AS apm, `proposal_details` AS pd SET apm.proposal_mapping_id = pd.id WHERE apm.application_id = pd.application_id;
24. INSERT INTO `loan_application`.`fs_corporate_applicant_details` (`application_id`,`organisation_name`,`group_name`,`pan`,`landline_no`,`constitution_id`,`establishment_year`,`establishment_month`,`website_address`,`registered_premise_number`,`registered_street_name`,`registered_land_mark`,`registered_city_id`,`registered_state_id`,`registered_country_id`,`registered_pincode`,`same_as`,`administrative_premise_number`,`administrative_street_name`,`administrative_land_mark`,`administrative_city_id`,`administrative_state_id`,`administrative_country_id`,`administrative_pincode`,`about_us`,`key_verical_funding`,`latitude`,`longitude`,`created_by`,`modified_by`,`created_date`,`modified_date`,`is_active`,`key_vertical_sector`,`key_vertical_subsector`,`gstin`,`email`,`aadhar`,`credit_rating_id`,`cont_liability_fy_amt`,`cont_liability_sy_amt`,`cont_liability_ty_amt`,`cont_liability_year`,`not_applicable`,`total_cost_of_estimate`,`total_means_of_finance`,`collateral_security_amt_total`,`share_price_face_value`,`share_price_market_value`,`msme_registration_number`,`administrative_dist_mapping_id`,`registered_dist_mapping_id`,`establishment_date`,`environmental_impact_id`,`is_gst_completed`,`is_itr_completed`,`proposal_mapping_id`,`business_since_year`,`business_since_month`)
    SELECT fsad.`application_id`,fsad.`organisation_name`,fsad.`group_name`,fsad.`pan`,fsad.`landline_no`,
    fsad.`constitution_id`,fsad.`establishment_year`,fsad.`establishment_month`,fsad.`website_address`,
    fsad.`registered_premise_number`,fsad.`registered_street_name`,fsad.`registered_land_mark`,
    fsad.`registered_city_id`,fsad.`registered_state_id`,fsad.`registered_country_id`,fsad.`registered_pincode`,
    fsad.`same_as`,fsad.`administrative_premise_number`,fsad.`administrative_street_name`,fsad.`administrative_land_mark`,
    fsad.`administrative_city_id`,fsad.`administrative_state_id`,fsad.`administrative_country_id`,fsad.`administrative_pincode`,
    fsad.`about_us`,fsad.`key_verical_funding`,fsad.`latitude`,fsad.`longitude`,fsad.`created_by`,fsad.`modified_by`,fsad.`created_date`,
    fsad.`modified_date`,fsad.`is_active`,fsad.`key_vertical_sector`,fsad.`key_vertical_subsector`,fsad.`gstin`,fsad.`email`,fsad.`aadhar`,
    fsad.`credit_rating_id`,fsad.`cont_liability_fy_amt`,fsad.`cont_liability_sy_amt`,fsad.`cont_liability_ty_amt`,fsad.`cont_liability_year`,
    fsad.`not_applicable`,fsad.`total_cost_of_estimate`,fsad.`total_means_of_finance`,fsad.`collateral_security_amt_total`,fsad.`share_price_face_value`,
    fsad.`share_price_market_value`,fsad.`msme_registration_number`,fsad.`administrative_dist_mapping_id`,fsad.`registered_dist_mapping_id`,
    fsad.`establishment_date`,fsad.`environmental_impact_id`,fsad.`is_gst_completed`,fsad.`is_itr_completed`,pd.id AS proposal_mapping_id,
    fsad.`business_since_year`,fsad.`business_since_month` FROM `loan_application`.`fs_corporate_applicant_details` fsad
    INNER JOIN `loan_application`.`proposal_details` pd ON fsad.`application_id` = pd.`application_id` WHERE fsad.`proposal_mapping_id` IS NULL