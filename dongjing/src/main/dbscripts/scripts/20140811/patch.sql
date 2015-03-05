DROP TABLE IF EXISTS `natureShareHolder` ;

CREATE TABLE IF NOT EXISTS `natureShareHolder` (
  `contactID` BIGINT NOT NULL AUTO_INCREMENT,
  `companyID` BIGINT NOT NULL,
  `fullName` VARCHAR(255) NULL,
  `cellPhone` VARCHAR(255) NULL,
  `phone` VARCHAR(255) NULL,
  `emailAddress` VARCHAR(100) NULL,
  `idCard` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `fax` VARCHAR(20) NULL,
  `shareRatio` VARCHAR(45) NULL,
  PRIMARY KEY (`contactID`),
  INDEX `fk_natureShareHolder_company1_idx` (`companyID` ASC),
  CONSTRAINT `fk_natureShareHolder_company1`
    FOREIGN KEY (`companyID`)
    REFERENCES `company` (`companyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `companyShareholder`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `companyShareholder` ;

CREATE TABLE IF NOT EXISTS `companyShareholder` (
  `contactID` BIGINT NOT NULL AUTO_INCREMENT,
  `companyID` BIGINT NOT NULL,
  `fullName` VARCHAR(255) NULL,
  `phone` VARCHAR(255) NULL,
  `idType` VARCHAR(45) NULL,
  `idCard` VARCHAR(45) NULL,
  `address` VARCHAR(1000) NULL,
  `shareRatio` VARCHAR(45) NULL,
  PRIMARY KEY (`contactID`),
  INDEX `fk_natureShareHolder_company1_idx` (`companyID` ASC),
  CONSTRAINT `fk_natureShareHolder_company10`
    FOREIGN KEY (`companyID`)
    REFERENCES `company` (`companyID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
