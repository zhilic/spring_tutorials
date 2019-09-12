DROP SCHEMA IF EXISTS `advanced_mapping`;

CREATE SCHEMA `advanced_mapping`;

use `advanced_mapping`;

-- Temporarily disable foreign key constraint
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `instructor_detail`;

CREATE TABLE `advanced_mapping`.`instructor_detail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `youtube_channel` VARCHAR(45) NULL DEFAULT NULL,
  `hobby` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT=1
DEFAULT CHARACTER SET = latin1;

DROP TABLE IF EXISTS `instructor`;

CREATE TABLE `advanced_mapping`.`instructor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL DEFAULT NULL,
  `last_name` VARCHAR(45) NULL DEFAULT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `instructor_detail_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_DETAIL_idx` (`instructor_detail_id` ASC),
  CONSTRAINT `FK_DETAIL`
    FOREIGN KEY (`instructor_detail_id`)
    REFERENCES `advanced_mapping`.`instructor_detail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

DROP TABLE IF EXISTS `course`;

CREATE TABLE `advanced_mapping`.`course` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) DEFAULT NULL,
  `instructor_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `title_UNIQUE` (`title` ASC),
  INDEX `FK_INSTRUCTOR_idx` (`instructor_id` ASC),
  CONSTRAINT `FK_INSTRUCTOR`
    FOREIGN KEY (`instructor_id`)
    REFERENCES `instructor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = latin1;

DROP TABLE IF EXISTS `review `;

CREATE TABLE `advanced_mapping`.`review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` VARCHAR(45) DEFAULT NULL,
  `course_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_COURSE_ID_idx` (`course_id` ASC),
  CONSTRAINT `FK_COURSE_ID`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = latin1;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `course_student`;

CREATE TABLE `course_student` (
  `course_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  PRIMARY KEY (`course_id`, `student_id`),
  INDEX `FK_STUDENT_idx` (`student_id` ASC),
  CONSTRAINT `FK_COURSE`
    FOREIGN KEY (`course_id`)
    REFERENCES `course` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_STUDENT`
    FOREIGN KEY (`student_id`)
    REFERENCES `student` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- Enable foreign key constraint
SET FOREIGN_KEY_CHECKS = 1;