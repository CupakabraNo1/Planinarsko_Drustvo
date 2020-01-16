-- MySQL Script generated by MySQL Workbench
-- Fri 03 Jan 2020 10:33:42 PM CET
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Planina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Planina` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Planina` (
  `idPlanina` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPlanina`),
  UNIQUE INDEX `idPlanina_UNIQUE` (`idPlanina` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Dom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Dom` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Dom` (
  `idDom` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  `Planina_idPlanina` INT NOT NULL,
  `max_kapacitet` INT NOT NULL,
  PRIMARY KEY (`idDom`),
  UNIQUE INDEX `idDom_UNIQUE` (`idDom` ASC) VISIBLE,
  INDEX `fk_Dom_Planina1_idx` (`Planina_idPlanina` ASC) VISIBLE,
  CONSTRAINT `fk_Dom_Planina1`
    FOREIGN KEY (`Planina_idPlanina`)
    REFERENCES `mydb`.`Planina` (`idPlanina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Uloga`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Uloga` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Uloga` (
  `idUloga` INT NOT NULL AUTO_INCREMENT,
  `naziv` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUloga`),
  UNIQUE INDEX `idUloga_UNIQUE` (`idUloga` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Clanarina`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Clanarina` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Clanarina` (
  `idClanarina` INT NOT NULL AUTO_INCREMENT,
  `od` DATETIME NOT NULL,
  `Clanarinacol` DATETIME NOT NULL,
  PRIMARY KEY (`idClanarina`),
  UNIQUE INDEX `idClanarina_UNIQUE` (`idClanarina` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Korisnik`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Korisnik` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Korisnik` (
  `idKorisnik` INT NOT NULL AUTO_INCREMENT,
  `korisnicko_ime` VARCHAR(45) NOT NULL,
  `lozinka` VARCHAR(100) NOT NULL,
  `ime` VARCHAR(45) NOT NULL,
  `prezime` VARCHAR(45) NOT NULL,
  `Uloga_idUloga` INT NOT NULL,
  `Clanarina_idClanarina` INT NOT NULL,
  PRIMARY KEY (`idKorisnik`),
  UNIQUE INDEX `idKorisnik_UNIQUE` (`idKorisnik` ASC) VISIBLE,
  UNIQUE INDEX `korisnicko_ime_UNIQUE` (`korisnicko_ime` ASC) VISIBLE,
  INDEX `fk_Korisnik_Uloga1_idx` (`Uloga_idUloga` ASC) VISIBLE,
  INDEX `fk_Korisnik_Clanarina1_idx` (`Clanarina_idClanarina` ASC) VISIBLE,
  CONSTRAINT `fk_Korisnik_Uloga1`
    FOREIGN KEY (`Uloga_idUloga`)
    REFERENCES `mydb`.`Uloga` (`idUloga`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Korisnik_Clanarina1`
    FOREIGN KEY (`Clanarina_idClanarina`)
    REFERENCES `mydb`.`Clanarina` (`idClanarina`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Izvestaj`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Izvestaj` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Izvestaj` (
  `idIzvestaj` INT NOT NULL AUTO_INCREMENT,
  `tekst` VARCHAR(123) NOT NULL,
  `Planina_idPlanina` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  PRIMARY KEY (`idIzvestaj`),
  UNIQUE INDEX `idIzvestaj_UNIQUE` (`idIzvestaj` ASC) VISIBLE,
  INDEX `fk_Izvestaj_Planina1_idx` (`Planina_idPlanina` ASC) VISIBLE,
  INDEX `fk_Izvestaj_Korisnik1_idx` (`Korisnik_idKorisnik` ASC) VISIBLE,
  CONSTRAINT `fk_Izvestaj_Planina1`
    FOREIGN KEY (`Planina_idPlanina`)
    REFERENCES `mydb`.`Planina` (`idPlanina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Izvestaj_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Planinarska_staza`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Planinarska_staza` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Planinarska_staza` (
  `idStaza` INT NOT NULL AUTO_INCREMENT,
  `tezina` VARCHAR(45) NOT NULL,
  `mapa` LONGBLOB NULL,
  `opis` VARCHAR(123) NULL,
  `Planina_idPlanina` INT NOT NULL,
  PRIMARY KEY (`idStaza`),
  UNIQUE INDEX `idStaza_UNIQUE` (`idStaza` ASC) VISIBLE,
  INDEX `fk_Planinarska_staza_Planina1_idx` (`Planina_idPlanina` ASC) VISIBLE,
  CONSTRAINT `fk_Planinarska_staza_Planina1`
    FOREIGN KEY (`Planina_idPlanina`)
    REFERENCES `mydb`.`Planina` (`idPlanina`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Znamenitost`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Znamenitost` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Znamenitost` (
  `idZnamenitost` INT NOT NULL AUTO_INCREMENT,
  `opis` VARCHAR(123) NULL,
  `tip` VARCHAR(45) NOT NULL,
  `Planinarska_staza_idStaza` INT NOT NULL,
  PRIMARY KEY (`idZnamenitost`),
  UNIQUE INDEX `idZnamenitost_UNIQUE` (`idZnamenitost` ASC) VISIBLE,
  INDEX `fk_Znamenitost_Planinarska_staza1_idx` (`Planinarska_staza_idStaza` ASC) VISIBLE,
  CONSTRAINT `fk_Znamenitost_Planinarska_staza1`
    FOREIGN KEY (`Planinarska_staza_idStaza`)
    REFERENCES `mydb`.`Planinarska_staza` (`idStaza`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Termin_znamenitost`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Termin_znamenitost` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Termin_znamenitost` (
  `idTermin` INT NOT NULL AUTO_INCREMENT,
  `pocetak` DATETIME NOT NULL,
  `kraj` DATETIME NOT NULL,
  `Znamenitost_idZnamenitost` INT NOT NULL,
  PRIMARY KEY (`idTermin`),
  UNIQUE INDEX `idTermin_UNIQUE` (`idTermin` ASC) VISIBLE,
  INDEX `fk_Termin_Znamenitost1_idx` (`Znamenitost_idZnamenitost` ASC) VISIBLE,
  CONSTRAINT `fk_Termin_Znamenitost1`
    FOREIGN KEY (`Znamenitost_idZnamenitost`)
    REFERENCES `mydb`.`Znamenitost` (`idZnamenitost`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Poseta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Poseta` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Poseta` (
  `idPoseta` INT NOT NULL AUTO_INCREMENT,
  `Korisnik_idKorisnik` INT NOT NULL,
  `Korisnik_Uloga_idUloga` INT NOT NULL,
  `Termin_idTermin` INT NOT NULL,
  PRIMARY KEY (`idPoseta`),
  UNIQUE INDEX `idKomentar_UNIQUE` (`idPoseta` ASC) VISIBLE,
  INDEX `fk_Poseta_Korisnik1_idx` (`Korisnik_idKorisnik` ASC, `Korisnik_Uloga_idUloga` ASC) VISIBLE,
  INDEX `fk_Poseta_Termin1_idx` (`Termin_idTermin` ASC) VISIBLE,
  CONSTRAINT `fk_Poseta_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik` , `Korisnik_Uloga_idUloga`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik` , `Uloga_idUloga`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Poseta_Termin1`
    FOREIGN KEY (`Termin_idTermin`)
    REFERENCES `mydb`.`Termin_znamenitost` (`idTermin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Slika`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Slika` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Slika` (
  `idSlika` INT NOT NULL AUTO_INCREMENT,
  `slika` LONGBLOB NULL,
  `Znamenitost_idZnamenitost` INT NOT NULL,
  `Izvestaj_idIzvestaj` INT NOT NULL,
  PRIMARY KEY (`idSlika`),
  UNIQUE INDEX `idSlika_UNIQUE` (`idSlika` ASC) VISIBLE,
  INDEX `fk_Slika_Znamenitost1_idx` (`Znamenitost_idZnamenitost` ASC) VISIBLE,
  INDEX `fk_Slika_Izvestaj1_idx` (`Izvestaj_idIzvestaj` ASC) VISIBLE,
  CONSTRAINT `fk_Slika_Znamenitost1`
    FOREIGN KEY (`Znamenitost_idZnamenitost`)
    REFERENCES `mydb`.`Znamenitost` (`idZnamenitost`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Slika_Izvestaj1`
    FOREIGN KEY (`Izvestaj_idIzvestaj`)
    REFERENCES `mydb`.`Izvestaj` (`idIzvestaj`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Termin_dom`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Termin_dom` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Termin_dom` (
  `idTermin` INT NOT NULL AUTO_INCREMENT,
  `od` DATETIME NOT NULL,
  `do` DATETIME NOT NULL,
  `Dom_idDom` INT NOT NULL,
  PRIMARY KEY (`idTermin`),
  UNIQUE INDEX `idTermin_dom_UNIQUE` (`idTermin` ASC) VISIBLE,
  INDEX `fk_Termin_dom_Dom1_idx` (`Dom_idDom` ASC) VISIBLE,
  CONSTRAINT `fk_Termin_dom_Dom1`
    FOREIGN KEY (`Dom_idDom`)
    REFERENCES `mydb`.`Dom` (`idDom`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Rezervacija`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Rezervacija` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Rezervacija` (
  `idRezervacija` INT NOT NULL,
  `Termin_dom_idTermin` INT NOT NULL,
  `Korisnik_idKorisnik` INT NOT NULL,
  `Korisnik_Uloga_idUloga` INT NOT NULL,
  INDEX `fk_Termin_dom_has_Korisnik_Korisnik1_idx` (`Korisnik_idKorisnik` ASC, `Korisnik_Uloga_idUloga` ASC) VISIBLE,
  INDEX `fk_Termin_dom_has_Korisnik_Termin_dom1_idx` (`Termin_dom_idTermin` ASC) VISIBLE,
  PRIMARY KEY (`idRezervacija`),
  UNIQUE INDEX `idRezervacija_UNIQUE` (`idRezervacija` ASC) VISIBLE,
  CONSTRAINT `fk_Termin_dom_has_Korisnik_Termin_dom1`
    FOREIGN KEY (`Termin_dom_idTermin`)
    REFERENCES `mydb`.`Termin_dom` (`idTermin`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Termin_dom_has_Korisnik_Korisnik1`
    FOREIGN KEY (`Korisnik_idKorisnik` , `Korisnik_Uloga_idUloga`)
    REFERENCES `mydb`.`Korisnik` (`idKorisnik` , `Uloga_idUloga`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Komentar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Komentar` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Komentar` (
  `idKomentar` INT NOT NULL AUTO_INCREMENT,
  `tekst` VARCHAR(123) NOT NULL,
  `Poseta_idPoseta` INT NOT NULL,
  PRIMARY KEY (`idKomentar`),
  UNIQUE INDEX `idKomentar_UNIQUE` (`idKomentar` ASC) VISIBLE,
  INDEX `fk_Komentar_Poseta1_idx` (`Poseta_idPoseta` ASC) VISIBLE,
  CONSTRAINT `fk_Komentar_Poseta1`
    FOREIGN KEY (`Poseta_idPoseta`)
    REFERENCES `mydb`.`Poseta` (`idPoseta`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;