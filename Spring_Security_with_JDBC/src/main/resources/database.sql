DROP DATABASE IF EXISTS `spring_security_JDBC`;

CREATE DATABASE IF NOT EXISTS `spring_security_JDBC`;
USE `spring_security_JDBC`;


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` VARCHAR(50) NOT NULL,
  `password` CHAR(68) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

INSERT INTO `users`
VALUES
('john', '{bcrypt}$2a$10$..rQTLV7oQwwFQjFJit9uOxxRWGe367WVTUu5z70eG/U12INNuA06',1),
('mary', '{bcrypt}$2a$10$..rQTLV7oQwwFQjFJit9uOxxRWGe367WVTUu5z70eG/U12INNuA06',1),
('susan', '{bcrypt}$2a$10$..rQTLV7oQwwFQjFJit9uOxxRWGe367WVTUu5z70eG/U12INNuA06',1);

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`, `authority`),
  CONSTRAINT `authorities_ibfk_1`
    FOREIGN KEY (`username`)
    REFERENCES `users` (`username`)
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

INSERT INTO `authorities`
VALUES
('john','ROLE_EMPLOYEE'),
('mary','ROLE_EMPLOYEE'),
('mary','ROLE_MANAGER'),
('susan','ROLE_EMPLOYEE'),
('susan','ROLE_ADMIN');
