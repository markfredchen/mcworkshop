DROP TABLE IF EXISTS `rentStatus` ;

CREATE TABLE IF NOT EXISTS `rentStatus` (
  `rentorID` BIGINT NOT NULL,
  `renteeID` BIGINT NOT NULL,
  `area` DOUBLE NOT NULL,
  `address` VARCHAR(1000) NULL,
  `startDate` DATE NULL,
  `endDate` DATE NULL,
  PRIMARY KEY (`rentorID`, `renteeID`))
ENGINE = InnoDB;



ALTER TABLE `dongjing`.`company` 
CHANGE COLUMN `factoryArea` `factoryArea` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `factoryUsageArea` `factoryUsageArea` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `factoryRentArea` `factoryRentArea` DOUBLE NULL DEFAULT NULL ,
CHANGE COLUMN `factoryControlArea` `factoryControlArea` DOUBLE NULL DEFAULT NULL ,
ADD COLUMN `isRent` BIT NULL AFTER `landUsageID`,
ADD COLUMN `factoryActualArea` DOUBLE NULL AFTER `factoryControlArea`;

