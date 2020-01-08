-- MySQL Script generated by MySQL Workbench
-- Sun Jan  5 16:11:11 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema e-shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema e-shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `e-shop` DEFAULT CHARACTER SET utf8 ;
USE `e-shop` ;

-- -----------------------------------------------------
-- Table `e-shop`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-shop`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(68) NOT NULL,
  `enabled` TINYINT(4) NOT NULL DEFAULT 1,
  PRIMARY KEY (`username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-shop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-shop`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `quantity` INT NOT NULL,
  `minPrice` DECIMAL(10,2) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `discount` INT NULL DEFAULT 0,
  `addedToBlackFriday` TINYINT(4) NULL DEFAULT 0,
  `reducedPrice` DECIMAL(10,2) NULL,
  `enabled` TINYINT(4) NULL DEFAULT 1,
  PRIMARY KEY (`product_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-shop`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-shop`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `price` DECIMAL(10,2) NULL,
  `product_id` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orders_products1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_orders_users1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_orders_products1`
    FOREIGN KEY (`product_id`)
    REFERENCES `e-shop`.`products` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`username`)
    REFERENCES `e-shop`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `e-shop`.`authorities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `e-shop`.`authorities` (
  `username` VARCHAR(45) NOT NULL,
  `authority` VARCHAR(45) NOT NULL,
  INDEX `fk_authorities_users1_idx` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_authorities_users1`
    FOREIGN KEY (`username`)
    REFERENCES `e-shop`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
