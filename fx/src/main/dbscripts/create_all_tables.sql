SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `tmbox_real_data`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `tmbox_real_data` ;

CREATE TABLE IF NOT EXISTS `tmbox_real_data` (
  `transactionID` BIGINT NOT NULL AUTO_INCREMENT,
  `ticket` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `size` DECIMAL(5,2) NOT NULL,
  `item` VARCHAR(45) NOT NULL,
  `openTime` DATETIME NOT NULL,
  `closeTime` DATETIME NOT NULL,
  `openPrice` DECIMAL(10,5) NOT NULL,
  `closePrice` DECIMAL(10,5) NOT NULL,
  `profit` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`transactionID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

CREATE TABLE IF NOT EXISTS `contact` (
  `contactID` BIGINT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `comments` VARCHAR(1000) NULL,
  PRIMARY KEY (`contactID`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
