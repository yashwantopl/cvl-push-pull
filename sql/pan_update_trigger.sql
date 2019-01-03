DELIMITER $$
CREATE
    TRIGGER `loan_application`.`pan_update_connect` AFTER UPDATE
    ON `loan_application`.`fs_corporate_applicant_details`
    FOR EACH ROW BEGIN
	IF (NEW.pan IS NOT NULL AND NEW.pan <> OLD.pan) THEN
	UPDATE `connect`.`connect_log` lg
           SET lg.pan = NEW.pan
           WHERE lg.application_id = NEW.application_id;
        END IF;
    END$$

DELIMITER ;


DELIMITER $$
CREATE
    TRIGGER `loan_application`.`pan_update_connect_insert` AFTER INSERT
    ON `loan_application`.`fs_corporate_applicant_details`
    FOR EACH ROW BEGIN
	IF (NEW.pan IS NOT NULL) THEN
	UPDATE `connect`.`connect_log` lg
           SET lg.pan = NEW.pan
           WHERE lg.application_id = NEW.application_id;
        END IF;
    END$$
DELIMITER ;

DELIMITER $$
USE `loan_application`$$
DROP TRIGGER  `gstin_update_connect`$$
CREATE
    TRIGGER `gstin_update_connect` AFTER UPDATE ON `fs_corporate_applicant_details` 
    FOR EACH ROW BEGIN
	IF (NEW.gstin IS NOT NULL AND NEW.pan <> OLD.gstin) THEN
	UPDATE `connect`.`connect_log` lg
           SET lg.gstin = NEW.gstin
           WHERE lg.application_id = NEW.application_id;
        END IF;
    END;
$$
DELIMITER ;



DELIMITER $$
USE `loan_application`$$
DROP TRIGGER  `gstin_update_connect_insert`$$
CREATE
    TRIGGER `gstin_update_connect_insert` AFTER UPDATE ON `fs_corporate_applicant_details` 
    FOR EACH ROW BEGIN
	IF (NEW.gstin IS NOT NULL) THEN
	UPDATE `connect`.`connect_log` lg
           SET lg.gstin = NEW.gstin
           WHERE lg.application_id = NEW.application_id;
        END IF;
    END;
$$
DELIMITER ;