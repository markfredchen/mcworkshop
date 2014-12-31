-- -----------------------------------------------------
-- Table `rentRelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rentRelation` ;

CREATE TABLE IF NOT EXISTS `rentRelation` (
  `relationID` BIGINT NOT NULL AUTO_INCREMENT,
  `rentorID` BIGINT NULL,
  `renteeID` BIGINT NULL,
  `nonDJCompany` VARCHAR(255) NULL,
  `area` DOUBLE NULL,
  `address` VARCHAR(500) NULL,
  `startDate` DATE NULL,
  `endDate` DATE NULL,
  PRIMARY KEY (`relationID`),
  INDEX `IDX_RENTEE_ID` (`renteeID` ASC),
  INDEX `IDX_RENTOR_ID` (`rentorID` ASC))
ENGINE = InnoDB;


insert into rentrelation(rentorID, renteeID, area, address, startDate, endDate)
select rentorID, renteeID, area, address, startDate, endDate from rentstatus;