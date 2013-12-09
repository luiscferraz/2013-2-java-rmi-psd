SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `sccdb` DEFAULT CHARACTER SET utf8 ;
USE `sccdb` ;

-- -----------------------------------------------------
-- Table `sccdb`.`administrador`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sccdb`.`administrador` (
  `cpf` VARCHAR(11) NOT NULL ,
  `senha` VARCHAR(20) NOT NULL ,
  PRIMARY KEY (`cpf`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sccdb`.`cliente`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sccdb`.`cliente` (
  `cpf` VARCHAR(11) NOT NULL ,
  `nome` VARCHAR(20) NOT NULL ,
  `sobrenome` VARCHAR(20) NOT NULL ,
  `senha` VARCHAR(20) NOT NULL ,
  `numero_cartao` VARCHAR(16) NULL DEFAULT NULL ,
  PRIMARY KEY (`cpf`) ,
  INDEX `fk_cliente_cartao` (`numero_cartao` ASC) ,
  CONSTRAINT `fk_cliente_cartao`
    FOREIGN KEY (`numero_cartao` )
    REFERENCES `sccdb`.`cartao` (`numero` )
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sccdb`.`cartao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `sccdb`.`cartao` (
  `numero` VARCHAR(16) NOT NULL ,
  `cpf_cliente` VARCHAR(11) NOT NULL ,
  `validade` VARCHAR(5) NOT NULL ,
  `cvv` VARCHAR(3) NOT NULL ,
  `senha` VARCHAR(6) NOT NULL ,
  PRIMARY KEY (`numero`) ,
  INDEX `fk_cartao_cliente` (`cpf_cliente` ASC) ,
  CONSTRAINT `fk_cartao_cliente`
    FOREIGN KEY (`cpf_cliente` )
    REFERENCES `sccdb`.`cliente` (`cpf` )
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
