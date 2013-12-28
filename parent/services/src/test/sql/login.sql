CREATE DATABASE IF NOT EXISTS `tasklist` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tasklist`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passwordHash` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `tasklist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_hodpdl33lpsx5jjf38j4rr3bi` (`user_id`),
  CONSTRAINT `FK_hodpdl33lpsx5jjf38j4rr3bi` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `done` bit(1) NOT NULL,
  `orderNum` int(11) NOT NULL,
  `tasklist_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_byr4jh14ok1cu0m2usfg5b5pu` (`tasklist_id`),
  CONSTRAINT `FK_byr4jh14ok1cu0m2usfg5b5pu` FOREIGN KEY (`tasklist_id`) REFERENCES `tasklist` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

delete from `task`;
delete from `tasklist`;
delete from `user`;

insert into `user` (passwordHash, username) values ("38fe8951595f01a3c16f3d50ea0bcc53", "matti");
