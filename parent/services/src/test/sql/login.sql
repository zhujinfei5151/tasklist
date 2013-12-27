CREATE DATABASE IF NOT EXISTS `tasklist` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `tasklist`;

CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `done` bit(1) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4fmjedju7b35tb5cr71n3ntb0` (`user_id`),
  CONSTRAINT `FK_4fmjedju7b35tb5cr71n3ntb0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passwordHash` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

delete from `task`;
delete from `user`;

insert into `user` (passwordHash, username) values ("38fe8951595f01a3c16f3d50ea0bcc53", "matti");
