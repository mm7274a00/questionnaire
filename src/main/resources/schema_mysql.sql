CREATE TABLE IF NOT EXISTS `user` (
  `name` varchar(45) DEFAULT NULL,
  `phone_number` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `age` int DEFAULT '0',
  `qn_id` int NOT NULL,
  `q_id` int NOT NULL,
  `ans` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`phone_number`),
  UNIQUE KEY `email_UNIQUE` (`email`)
);
