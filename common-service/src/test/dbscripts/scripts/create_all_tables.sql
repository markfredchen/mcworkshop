SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Table `contacts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contacts` ;

CREATE TABLE IF NOT EXISTS `contacts` (
  `contactID` BIGINT NOT NULL AUTO_INCREMENT,
  `fullName` VARCHAR(200) NOT NULL,
  `phone` VARCHAR(100) NULL,
  `cellPhone` VARCHAR(100) NULL,
  `emailAddress` VARCHAR(100) NULL,
  PRIMARY KEY (`contactID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `IDX_UQ_contacts_emailAddress` ON `contacts` (`emailAddress` ASC);


-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `users` ;

CREATE TABLE IF NOT EXISTS `users` (
  `userID` BIGINT NOT NULL AUTO_INCREMENT,
  `contactID` BIGINT NOT NULL,
  `userStatusID` BIGINT NOT NULL,
  `username` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NOT NULL,
  `lastLoginDate` DATETIME NULL,
  `createdDate` DATETIME NOT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `fk_user_contact`
    FOREIGN KEY (`contactID`)
    REFERENCES `contacts` (`contactID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `IDX_UQ_user_username` ON `users` (`username` ASC);

CREATE INDEX `fk_user_contact_idx` ON `users` (`contactID` ASC);

CREATE UNIQUE INDEX `IDX_UQ_user_contactID` ON `users` (`contactID` ASC);


-- -----------------------------------------------------
-- Table `roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `roles` ;

CREATE TABLE IF NOT EXISTS `roles` (
  `roleID` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`roleID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `IDX_UQ_roles_name` ON `roles` (`name` ASC);


-- -----------------------------------------------------
-- Table `rights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `rights` ;

CREATE TABLE IF NOT EXISTS `rights` (
  `rightID` BIGINT NOT NULL AUTO_INCREMENT,
  `parentRightID` BIGINT NULL,
  `name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`rightID`),
  CONSTRAINT `fk_rights_parentRight`
    FOREIGN KEY (`parentRightID`)
    REFERENCES `rights` (`rightID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `IDX_UQ_rights_name` ON `rights` (`name` ASC);

CREATE INDEX `fk_rights_rights1_idx` ON `rights` (`parentRightID` ASC);


-- -----------------------------------------------------
-- Table `role_right`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role_right` ;

CREATE TABLE IF NOT EXISTS `role_right` (
  `roleID` BIGINT NOT NULL,
  `rightID` BIGINT NOT NULL,
  PRIMARY KEY (`roleID`, `rightID`),
  CONSTRAINT `fk_role_has_right_role1`
    FOREIGN KEY (`roleID`)
    REFERENCES `roles` (`roleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_role_has_right_right1`
    FOREIGN KEY (`rightID`)
    REFERENCES `rights` (`rightID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_role_has_right_right1_idx` ON `role_right` (`rightID` ASC);

CREATE INDEX `fk_role_has_right_role1_idx` ON `role_right` (`roleID` ASC);


-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

CREATE TABLE IF NOT EXISTS `user_role` (
  `userID` BIGINT NOT NULL,
  `roleID` BIGINT NOT NULL,
  PRIMARY KEY (`userID`, `roleID`),
  CONSTRAINT `fk_user_has_role_user1`
    FOREIGN KEY (`userID`)
    REFERENCES `users` (`userID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_role_role1`
    FOREIGN KEY (`roleID`)
    REFERENCES `roles` (`roleID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_user_has_role_role1_idx` ON `user_role` (`roleID` ASC);

CREATE INDEX `fk_user_has_role_user1_idx` ON `user_role` (`userID` ASC);


-- -----------------------------------------------------
-- Table `message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `message` ;

CREATE TABLE IF NOT EXISTS `message` (
  `messageID` BIGINT NOT NULL AUTO_INCREMENT,
  `domainID` VARCHAR(45) NULL,
  `locale` VARCHAR(10) NOT NULL,
  `message` VARCHAR(2000) NOT NULL,
  PRIMARY KEY (`messageID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `menus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menus` ;

CREATE TABLE IF NOT EXISTS `menus` (
  `menuID` BIGINT NOT NULL AUTO_INCREMENT,
  `parentID` BIGINT NULL,
  `moduleID` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `targetPage` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`menuID`),
  CONSTRAINT `fk_menu_menu1`
    FOREIGN KEY (`parentID`)
    REFERENCES `menus` (`menuID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_menu_menu1_idx` ON `menus` (`parentID` ASC);


-- -----------------------------------------------------
-- Table `sysEnums`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `sysEnums` ;

CREATE TABLE IF NOT EXISTS `sysEnums` (
  `sysEnumID` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(255) NOT NULL,
  `value` VARCHAR(255) NOT NULL,
  `sequence` INT NOT NULL,
  PRIMARY KEY (`sysEnumID`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `type_value_UNIQUE` ON `sysEnums` (`type` ASC, `value` ASC);


-- -----------------------------------------------------
-- Table `menu_right`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `menu_right` ;

CREATE TABLE IF NOT EXISTS `menu_right` (
  `menuID` BIGINT NOT NULL,
  `rightID` BIGINT NOT NULL,
  PRIMARY KEY (`menuID`, `rightID`),
  CONSTRAINT `fk_menu_has_right_menu1`
    FOREIGN KEY (`menuID`)
    REFERENCES `menus` (`menuID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_menu_has_right_right1`
    FOREIGN KEY (`rightID`)
    REFERENCES `rights` (`rightID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_menu_has_right_right1_idx` ON `menu_right` (`rightID` ASC);

CREATE INDEX `fk_menu_has_right_menu1_idx` ON `menu_right` (`menuID` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
