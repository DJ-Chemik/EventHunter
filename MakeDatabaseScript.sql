-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema projekt
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema projekt
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `projekt` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `projekt` ;

-- -----------------------------------------------------
-- Table `projekt`.`aktor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`aktor` ;

CREATE TABLE IF NOT EXISTS `projekt`.`aktor` (
  `id_aktora` DOUBLE NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(255) NOT NULL,
  `nazwisko` VARCHAR(255) NOT NULL,
  `nazwa_grupy` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_aktora`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`przedstawienie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`przedstawienie` ;

CREATE TABLE IF NOT EXISTS `projekt`.`przedstawienie` (
  `id_przedstawienia` DOUBLE NOT NULL AUTO_INCREMENT,
  `tytul` VARCHAR(255) NOT NULL,
  `dlugosc` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id_przedstawienia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`aktorzy_przedstawienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`aktorzy_przedstawienia` ;

CREATE TABLE IF NOT EXISTS `projekt`.`aktorzy_przedstawienia` (
  `id_aktora` DOUBLE NOT NULL,
  `id_przedstawienia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_aktora`, `id_przedstawienia`),
  INDEX `aktorzy_przedstawienie_fk` (`id_przedstawienia` ASC) VISIBLE,
  CONSTRAINT `aktor_fk`
    FOREIGN KEY (`id_aktora`)
    REFERENCES `projekt`.`aktor` (`id_aktora`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `aktorzy_przedstawienie_fk`
    FOREIGN KEY (`id_przedstawienia`)
    REFERENCES `projekt`.`przedstawienie` (`id_przedstawienia`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`miejscowość`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`miejscowość` ;

CREATE TABLE IF NOT EXISTS `projekt`.`miejscowość` (
  `id_miasta` DOUBLE NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(255) NOT NULL,
  `województwo` VARCHAR(255) NOT NULL,
  `kod_pocztowy` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_miasta`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`miejsce`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`miejsce` ;

CREATE TABLE IF NOT EXISTS `projekt`.`miejsce` (
  `id_obiektu` DOUBLE NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(255) NOT NULL,
  `typ_obiektu` VARCHAR(255) NULL DEFAULT NULL,
  `id_miasta` DOUBLE NOT NULL,
  PRIMARY KEY (`id_obiektu`),
  INDEX `miejscowosc_fk` (`id_miasta` ASC) VISIBLE,
  CONSTRAINT `miejscowosc_fk`
    FOREIGN KEY (`id_miasta`)
    REFERENCES `projekt`.`miejscowość` (`id_miasta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`wydarzenie`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`wydarzenie` ;

CREATE TABLE IF NOT EXISTS `projekt`.`wydarzenie` (
  `id_wydarzenia` DOUBLE NOT NULL AUTO_INCREMENT,
  `nazwa` VARCHAR(255) NOT NULL,
  `data` DATETIME NOT NULL,
  `cena_biletu` DOUBLE NOT NULL,
  `ilosc_miejsc` DOUBLE NOT NULL,
  `typ` VARCHAR(255) NOT NULL,
  `id_obiektu` DOUBLE NOT NULL,
  PRIMARY KEY (`id_wydarzenia`),
  INDEX `miejsce_fk` (`id_obiektu` ASC) VISIBLE,
  CONSTRAINT `miejsce_fk`
    FOREIGN KEY (`id_obiektu`)
    REFERENCES `projekt`.`miejsce` (`id_obiektu`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`kabaret`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`kabaret` ;

CREATE TABLE IF NOT EXISTS `projekt`.`kabaret` (
  `id_wydarzenia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_wydarzenia`),
  CONSTRAINT `kab_wydarzenie_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`wydarzenie` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`kabaret_przedstawienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`kabaret_przedstawienia` ;

CREATE TABLE IF NOT EXISTS `projekt`.`kabaret_przedstawienia` (
  `id_przedstawienia` DOUBLE NOT NULL,
  `id_wydarzenia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_przedstawienia`, `id_wydarzenia`),
  INDEX `kabaret_fk` (`id_wydarzenia` ASC) VISIBLE,
  CONSTRAINT `kab_przedstawienie_fk`
    FOREIGN KEY (`id_przedstawienia`)
    REFERENCES `projekt`.`przedstawienie` (`id_przedstawienia`),
  CONSTRAINT `kabaret_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`kabaret` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`koncert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`koncert` ;

CREATE TABLE IF NOT EXISTS `projekt`.`koncert` (
  `id_wydarzenia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_wydarzenia`),
  CONSTRAINT `konc_wydarzenie_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`wydarzenie` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`muzyk`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`muzyk` ;

CREATE TABLE IF NOT EXISTS `projekt`.`muzyk` (
  `id_muzyka` DOUBLE NOT NULL AUTO_INCREMENT,
  `imie` VARCHAR(255) NOT NULL,
  `nazwisko` VARCHAR(255) NOT NULL,
  `pseudonim` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_muzyka`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`koncert_muzycy`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`koncert_muzycy` ;

CREATE TABLE IF NOT EXISTS `projekt`.`koncert_muzycy` (
  `id_muzyka` DOUBLE NOT NULL,
  `id_wydarzenia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_muzyka`, `id_wydarzenia`),
  INDEX `koncert_fk` (`id_wydarzenia` ASC) VISIBLE,
  CONSTRAINT `k_muzyk_fk`
    FOREIGN KEY (`id_muzyka`)
    REFERENCES `projekt`.`muzyk` (`id_muzyka`),
  CONSTRAINT `koncert_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`koncert` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`płyta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`płyta` ;

CREATE TABLE IF NOT EXISTS `projekt`.`płyta` (
  `id_plyty` DOUBLE NOT NULL AUTO_INCREMENT,
  `tytuł` VARCHAR(255) NOT NULL,
  `rok_wydania` SMALLINT(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id_plyty`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`muzyk_plyty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`muzyk_plyty` ;

CREATE TABLE IF NOT EXISTS `projekt`.`muzyk_plyty` (
  `id_plyty` DOUBLE NOT NULL,
  `id_muzyka` DOUBLE NOT NULL,
  PRIMARY KEY (`id_plyty`, `id_muzyka`),
  INDEX `m_muzyk_fk` (`id_muzyka` ASC) VISIBLE,
  CONSTRAINT `m_muzyk_fk`
    FOREIGN KEY (`id_muzyka`)
    REFERENCES `projekt`.`muzyk` (`id_muzyka`),
  CONSTRAINT `muzyk_plyta_fk`
    FOREIGN KEY (`id_plyty`)
    REFERENCES `projekt`.`płyta` (`id_plyty`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`wystep_teatralny`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`wystep_teatralny` ;

CREATE TABLE IF NOT EXISTS `projekt`.`wystep_teatralny` (
  `id_wydarzenia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_wydarzenia`),
  CONSTRAINT `wys_wydarzenie_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`wydarzenie` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`teatr_przedstawienia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`teatr_przedstawienia` ;

CREATE TABLE IF NOT EXISTS `projekt`.`teatr_przedstawienia` (
  `id_wydarzenia` DOUBLE NOT NULL,
  `id_przedstawienia` DOUBLE NOT NULL,
  PRIMARY KEY (`id_wydarzenia`, `id_przedstawienia`),
  INDEX `teatr_przedstawienie_fk` (`id_przedstawienia` ASC) VISIBLE,
  CONSTRAINT `teatr_przedstawienie_fk`
    FOREIGN KEY (`id_przedstawienia`)
    REFERENCES `projekt`.`przedstawienie` (`id_przedstawienia`),
  CONSTRAINT `wystep_teatralny_fk`
    FOREIGN KEY (`id_wydarzenia`)
    REFERENCES `projekt`.`wystep_teatralny` (`id_wydarzenia`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `projekt`.`utwór`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `projekt`.`utwór` ;

CREATE TABLE IF NOT EXISTS `projekt`.`utwór` (
  `id_utworu` DOUBLE NOT NULL AUTO_INCREMENT,
  `tytuł` VARCHAR(255) NOT NULL,
  `rok_wydania` SMALLINT(6) NULL DEFAULT NULL,
  `gatunek` VARCHAR(255) NULL DEFAULT NULL,
  `ilość_wyświetleń_na_yt` DOUBLE NULL DEFAULT NULL,
  `id_muzyka` DOUBLE NOT NULL,
  `id_plyty` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`id_utworu`),
  INDEX `u_muzyk_fk` (`id_muzyka` ASC) VISIBLE,
  INDEX `u_plyta_fk` (`id_plyty` ASC) VISIBLE,
  CONSTRAINT `u_muzyk_fk`
    FOREIGN KEY (`id_muzyka`)
    REFERENCES `projekt`.`muzyk` (`id_muzyka`),
  CONSTRAINT `u_plyta_fk`
    FOREIGN KEY (`id_plyty`)
    REFERENCES `projekt`.`płyta` (`id_plyty`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
