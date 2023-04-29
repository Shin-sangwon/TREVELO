-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-------------------------------------------------------
-- Schema tripdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `tripdb` ;

-- -----------------------------------------------------
-- Schema tripdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `tripdb` DEFAULT CHARACTER SET utf8 ;

-- -----------------------------------------------------
-- Table `tripdb`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`member` (
  `member_id` BIGINT NOT NULL AUTO_INCREMENT,
  `login_id` VARCHAR(16) NOT NULL,
  `login_password` VARCHAR(72) NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `birthday` DATETIME NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `address` VARCHAR(40) NULL DEFAULT NULL,
  `sex` CHAR(1) NULL DEFAULT NULL,
  `role` VARCHAR(10) NOT NULL,
  `grade` VARCHAR(10) NOT NULL,
  `mileage` BIGINT NOT NULL DEFAULT 0,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE INDEX `login_id_UNIQUE` (`login_id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`room` (
  `room_id` BIGINT NOT NULL AUTO_INCREMENT,
  `owner_id` BIGINT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `address` VARCHAR(199) NOT NULL,
  `introduce` VARCHAR(1000) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`room_id`),
  INDEX `member_to_room_id_idx` (`owner_id` ASC) VISIBLE,
  CONSTRAINT `member_to_room_id`
    FOREIGN KEY (`owner_id`)
    REFERENCES `tripdb`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`room_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`room_detail` (
  `roomdetail_id` BIGINT NOT NULL AUTO_INCREMENT,
  `room_id` BIGINT NOT NULL,
  `room_introduce` VARCHAR(1000) NULL,
  `price_per_night` BIGINT NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`roomdetail_id`),
  INDEX `room_to_roomdetail_idx` (`room_id` ASC) VISIBLE,
  CONSTRAINT `room_to_roomdetail`
    FOREIGN KEY (`room_id`)
    REFERENCES `tripdb`.`room` (`room_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `tripdb`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`board` (
  `board_id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NOT NULL,
  `title` VARCHAR(80) NOT NULL,
  `content` TEXT NOT NULL,
  `hit` INT NOT NULL,
  `board_tag` VARCHAR(45) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`board_id`),
  INDEX `id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_to_board_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripdb`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`hashtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`hashtag` (
  `hashtag_id` BIGINT NOT NULL AUTO_INCREMENT,
  `tagname` VARCHAR(45) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`hashtag_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`boardtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`boardtag` (
  `boardtag_id` BIGINT NOT NULL AUTO_INCREMENT,
  `board_id` BIGINT NOT NULL,
  `hashtag_id` BIGINT NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`boardtag_id`),
  INDEX `id_idx` (`board_id` ASC) VISIBLE,
  INDEX `id_idx1` (`hashtag_id` ASC) VISIBLE,
  CONSTRAINT `board_to_boardtag_id`
    FOREIGN KEY (`board_id`)
    REFERENCES `tripdb`.`board` (`board_id`),
  CONSTRAINT `hashtag_to_boardtag_id`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `tripdb`.`hashtag` (`hashtag_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`comment` (
  `comment_id` BIGINT NOT NULL,
  `board_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `id_idx` (`board_id` ASC) VISIBLE,
  INDEX `id_idx1` (`member_id` ASC) VISIBLE,
  CONSTRAINT `board_to_comment_id`
    FOREIGN KEY (`board_id`)
    REFERENCES `tripdb`.`board` (`board_id`),
  CONSTRAINT `member_to_comment_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripdb`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`reservation` (
  `reservation_id` BIGINT NOT NULL AUTO_INCREMENT,
  `customer_id` BIGINT NULL DEFAULT NULL,
  `roomdetail_id` BIGINT NULL DEFAULT NULL,
  `total_price` BIGINT NULL DEFAULT NULL,
  `ispaid` TINYINT NULL DEFAULT 0,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`reservation_id`),
  INDEX `member_to_payment_idx` (`customer_id` ASC) VISIBLE,
  INDEX `roomdetail_to_payment_idx` (`roomdetail_id` ASC) VISIBLE,
  CONSTRAINT `member_to_payment`
    FOREIGN KEY (`customer_id`)
    REFERENCES `tripdb`.`member` (`member_id`),
  CONSTRAINT `roomdetail_to_payment`
    FOREIGN KEY (`roomdetail_id`)
    REFERENCES `tripdb`.`room_detail` (`roomdetail_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`plan` (
  `plan_id` BIGINT NOT NULL,
  `member_id` BIGINT NOT NULL,
  `path` JSON NULL DEFAULT NULL,
  `plan_start` DATETIME NOT NULL,
  `plan_end` DATETIME NOT NULL,
  `plan_name` VARCHAR(16) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`plan_id`),
  INDEX `member_to_plan_id_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_to_plan_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripdb`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`review` (
  `review_id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NOT NULL,
  `room_id` BIGINT NOT NULL,
  `content` VARCHAR(1000) NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  INDEX `id_idx` (`room_id` ASC) VISIBLE,
  INDEX `id_idx1` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_to_review_id`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripdb`.`member` (`member_id`),
  CONSTRAINT `room_to_review_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `tripdb`.`room` (`room_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`roomtag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`roomtag` (
  `roomtag_id` BIGINT NOT NULL AUTO_INCREMENT,
  `room_id` BIGINT NOT NULL,
  `hashtag_id` BIGINT NOT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`roomtag_id`),
  INDEX `id_idx` (`room_id` ASC) VISIBLE,
  INDEX `id_idx1` (`hashtag_id` ASC) VISIBLE,
  CONSTRAINT `hashtag_to_roomtag_id`
    FOREIGN KEY (`hashtag_id`)
    REFERENCES `tripdb`.`hashtag` (`hashtag_id`),
  CONSTRAINT `room_to_roomtag_id`
    FOREIGN KEY (`room_id`)
    REFERENCES `tripdb`.`room` (`room_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `tripdb`.`transcation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `tripdb`.`transcation` (
  `transcation_id` BIGINT NOT NULL AUTO_INCREMENT,
  `member_id` BIGINT NOT NULL,
  `transaction_amount` BIGINT NULL DEFAULT NULL,
  `transaction_type` VARCHAR(10) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  `createdat` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedat` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`transcation_id`),
  INDEX `member_to_transcation_idx` (`member_id` ASC) VISIBLE,
  CONSTRAINT `member_to_transcation`
    FOREIGN KEY (`member_id`)
    REFERENCES `tripdb`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
