-- Adminer 4.8.1 MySQL 8.1.0 dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP DATABASE IF EXISTS `catering`;
CREATE DATABASE `catering` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `catering`;

DROP TABLE IF EXISTS `Events`;
CREATE TABLE `Events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `date_start` date DEFAULT NULL,
  `date_end` date DEFAULT NULL,
  `expected_participants` int DEFAULT NULL,
  `organizer_id` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

INSERT INTO `Events` (`id`, `name`, `date_start`, `date_end`, `expected_participants`, `organizer_id`) VALUES
(1,	'Convegno Agile Community',	'2020-09-25',	'2020-09-25',	100,	2),
(2,	'Compleanno di Manuela',	'2020-08-13',	'2020-08-13',	25,	2),
(3,	'Fiera del Sedano Rapa',	'2020-10-02',	'2020-10-04',	400,	1);

DROP TABLE IF EXISTS `Documentations`;
CREATE TABLE `Documentations` (
  `event_id` int NOT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `id` int NOT NULL AUTO_INCREMENT,
  `binary_data` mediumblob NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `Documentations_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `Events` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `MenuFeatures`;
CREATE TABLE `MenuFeatures` (
  `menu_id` int NOT NULL,
  `name` varchar(128) NOT NULL DEFAULT '',
  `value` tinyint(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `MenuFeatures` (`menu_id`, `name`, `value`) VALUES
(80,	'Richiede cuoco',	0),
(80,	'Buffet',	0),
(80,	'Richiede cucina',	0),
(80,	'Finger food',	0),
(80,	'Piatti caldi',	0),
(82,	'Richiede cuoco',	0),
(82,	'Buffet',	0),
(82,	'Richiede cucina',	0),
(82,	'Finger food',	0),
(82,	'Piatti caldi',	0),
(86,	'Richiede cuoco',	0),
(86,	'Buffet',	0),
(86,	'Richiede cucina',	0),
(86,	'Finger food',	0),
(86,	'Piatti caldi',	0);

DROP TABLE IF EXISTS `MenuItems`;
CREATE TABLE `MenuItems` (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL,
  `section_id` int DEFAULT NULL,
  `description` tinytext,
  `recipe_id` int NOT NULL,
  `position` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=121 DEFAULT CHARSET=utf8mb3;

INSERT INTO `MenuItems` (`id`, `menu_id`, `section_id`, `description`, `recipe_id`, `position`) VALUES
(96,	80,	0,	'Croissant vuoti',	9,	0),
(97,	80,	0,	'Croissant alla marmellata',	9,	1),
(98,	80,	0,	'Pane al cioccolato mignon',	10,	2),
(99,	80,	0,	'Panini al latte con prosciutto crudo',	12,	4),
(100,	80,	0,	'Panini al latte con prosciutto cotto',	12,	5),
(101,	80,	0,	'Panini al latte con formaggio spalmabile alle erbe',	12,	6),
(102,	80,	0,	'Girelle all\'uvetta mignon',	11,	3),
(103,	82,	0,	'Biscotti',	13,	1),
(104,	82,	0,	'Lingue di gatto',	14,	2),
(105,	82,	0,	'Bigné alla crema',	15,	3),
(106,	82,	0,	'Bigné al caffè',	15,	4),
(107,	82,	0,	'Pizzette',	16,	5),
(108,	82,	0,	'Croissant al prosciutto crudo mignon',	9,	6),
(109,	82,	0,	'Tramezzini tonno e carciofini mignon',	17,	7),
(112,	86,	41,	'Vitello tonnato',	1,	0),
(113,	86,	41,	'Carpaccio di spada',	2,	1),
(114,	86,	41,	'Alici marinate',	3,	2),
(115,	86,	42,	'Penne alla messinese',	5,	0),
(116,	86,	42,	'Risotto alla zucca',	20,	1),
(117,	86,	43,	'Salmone al forno',	8,	0),
(118,	86,	44,	'Sorbetto al limone',	18,	0),
(119,	86,	44,	'Torta Saint Honoré',	19,	1);

DROP TABLE IF EXISTS `MenuSections`;
CREATE TABLE `MenuSections` (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NOT NULL,
  `name` tinytext,
  `position` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;

INSERT INTO `MenuSections` (`id`, `menu_id`, `name`, `position`) VALUES
(41,	86,	'Antipasti',	0),
(42,	86,	'Primi',	1),
(43,	86,	'Secondi',	2),
(44,	86,	'Dessert',	3),
(45,	87,	'Antipasti',	0);

DROP TABLE IF EXISTS `Menus`;
CREATE TABLE `Menus` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` tinytext,
  `owner_id` int DEFAULT NULL,
  `published` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb3;

INSERT INTO `Menus` (`id`, `title`, `owner_id`, `published`) VALUES
(80,	'Coffee break mattutino',	2,	1),
(82,	'Coffee break pomeridiano',	2,	1),
(86,	'Cena di compleanno pesce',	3,	1);

DROP TABLE IF EXISTS `Preparations`;
CREATE TABLE `Preparations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `description` varchar(4096) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Recipes`;
CREATE TABLE `Recipes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` tinytext,
  `time_to_prepare` time NOT NULL DEFAULT '00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

INSERT INTO `Recipes` (`id`, `name`, `time_to_prepare`) VALUES
(1,	'Vitello tonnato',	'00:00:00'),
(2,	'Carpaccio di spada',	'00:00:00'),
(3,	'Alici marinate',	'00:00:00'),
(4,	'Insalata di riso',	'00:00:00'),
(5,	'Penne al sugo di baccalà',	'00:00:00'),
(6,	'Pappa al pomodoro',	'00:00:00'),
(7,	'Hamburger con bacon e cipolla caramellata',	'00:00:00'),
(8,	'Salmone al forno',	'00:00:00'),
(9,	'Croissant',	'00:00:00'),
(10,	'Pane al cioccolato',	'00:00:00'),
(11,	'Girelle all\'uvetta',	'00:00:00'),
(12,	'Panini al latte',	'00:00:00'),
(13,	'Biscotti di pasta frolla',	'00:00:00'),
(14,	'Lingue di gatto',	'00:00:00'),
(15,	'Bigné farciti',	'00:00:00'),
(16,	'Pizzette',	'00:00:00'),
(17,	'Tramezzini',	'00:00:00'),
(18,	'Sorbetto al limone',	'00:00:00'),
(19,	'Torta Saint Honoré',	'00:00:00'),
(20,	'Risotto alla zucca',	'00:00:00');

DROP TABLE IF EXISTS `Recurrences`;
CREATE TABLE `Recurrences` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `occurrence` int NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `Recurrences_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `Events` (`id`),
  CONSTRAINT `Recurrences_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `Events` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `RecurrencyInfos`;
CREATE TABLE `RecurrencyInfos` (
  `id` int NOT NULL,
  `event_id` int NOT NULL,
  `type` int NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  KEY `event_id` (`event_id`),
  CONSTRAINT `RecurrencyInfos_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `Events` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `Roles`;
CREATE TABLE `Roles` (
  `id` char(1) NOT NULL,
  `role` varchar(128) NOT NULL DEFAULT 'servizio',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `Roles` (`id`, `role`) VALUES
('c',	'cuoco'),
('h',	'chef'),
('o',	'organizzatore'),
('s',	'servizio');

DROP TABLE IF EXISTS `Services`;
CREATE TABLE `Services` (
  `id` int NOT NULL AUTO_INCREMENT,
  `event_id` int NOT NULL,
  `name` varchar(128) DEFAULT NULL,
  `proposed_menu_id` int NOT NULL DEFAULT '0',
  `approved_menu_id` int DEFAULT '0',
  `service_date` date DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `expected_participants` int DEFAULT NULL,
  `summarysheet_id` int DEFAULT NULL,
  `support_cook_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `summarysheet_id` (`summarysheet_id`),
  KEY `support_cook_id` (`support_cook_id`),
  CONSTRAINT `Services_ibfk_1` FOREIGN KEY (`summarysheet_id`) REFERENCES `SummarySheets` (`id`) ON DELETE SET NULL,
  CONSTRAINT `Services_ibfk_2` FOREIGN KEY (`support_cook_id`) REFERENCES `Users` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;

INSERT INTO `Services` (`id`, `event_id`, `name`, `proposed_menu_id`, `approved_menu_id`, `service_date`, `time_start`, `time_end`, `expected_participants`, `summarysheet_id`, `support_cook_id`) VALUES
(1,	2,	'Cena',	86,	0,	'2020-08-13',	'20:00:00',	'23:30:00',	25,	NULL,	NULL),
(2,	1,	'Coffee break mattino',	0,	0,	'2020-09-25',	'10:30:00',	'11:30:00',	100,	9,	NULL),
(3,	1,	'Colazione di lavoro',	0,	0,	'2020-09-25',	'13:00:00',	'14:00:00',	80,	NULL,	NULL),
(4,	1,	'Coffee break pomeriggio',	0,	82,	'2020-09-25',	'16:00:00',	'16:30:00',	100,	NULL,	NULL),
(5,	1,	'Cena sociale',	0,	0,	'2020-09-25',	'20:00:00',	'22:30:00',	40,	NULL,	NULL),
(6,	3,	'Pranzo giorno 1',	0,	0,	'2020-10-02',	'12:00:00',	'15:00:00',	200,	NULL,	NULL),
(7,	3,	'Pranzo giorno 2',	0,	0,	'2020-10-03',	'12:00:00',	'15:00:00',	300,	NULL,	NULL),
(8,	3,	'Pranzo giorno 3',	0,	0,	'2020-10-04',	'12:00:00',	'15:00:00',	400,	NULL,	NULL);

DROP TABLE IF EXISTS `ShiftAttendances`;
CREATE TABLE `ShiftAttendances` (
  `shift_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `shift_id` (`shift_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `ShiftAttendances_ibfk_5` FOREIGN KEY (`shift_id`) REFERENCES `Shifts` (`id`) ON DELETE CASCADE,
  CONSTRAINT `ShiftAttendances_ibfk_6` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `ShiftAttendances` (`shift_id`, `user_id`) VALUES
(2,	2),
(3,	8),
(3,	9),
(3,	10),
(3,	7),
(3,	4);

DROP TABLE IF EXISTS `ShiftTypes`;
CREATE TABLE `ShiftTypes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `ShiftTypes` (`id`, `type`) VALUES
(0,	'PREPARATORY'),
(1,	'SERVICE');

DROP TABLE IF EXISTS `Shifts`;
CREATE TABLE `Shifts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `limit` int NOT NULL,
  `type` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `type` (`type`),
  CONSTRAINT `Shifts_ibfk_1` FOREIGN KEY (`type`) REFERENCES `ShiftTypes` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Shifts` (`id`, `start`, `end`, `limit`, `type`) VALUES
(2,	'2020-09-25 08:00:00',	'2020-09-25 10:30:00',	25,	0),
(3,	'2020-09-25 10:30:00',	'2020-09-25 11:30:00',	12,	1);

DROP TABLE IF EXISTS `SummarySheets`;
CREATE TABLE `SummarySheets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `taskorder` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order` (`taskorder`),
  CONSTRAINT `SummarySheets_ibfk_1` FOREIGN KEY (`taskorder`) REFERENCES `TaskListOrders` (`id`) ON DELETE SET DEFAULT
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `SummarySheets` (`id`, `taskorder`) VALUES
(9,	0),
(8,	1);

DROP TABLE IF EXISTS `SummarySheetsOwners`;
CREATE TABLE `SummarySheetsOwners` (
  `summarysheet_id` int NOT NULL,
  `user_id` int NOT NULL,
  KEY `summarysheet_id` (`summarysheet_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `SummarySheetsOwners_ibfk_1` FOREIGN KEY (`summarysheet_id`) REFERENCES `SummarySheets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `SummarySheetsOwners_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `Users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `SummarySheetsOwners` (`summarysheet_id`, `user_id`) VALUES
(9,	2);

DROP TABLE IF EXISTS `TaskListOrders`;
CREATE TABLE `TaskListOrders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `TaskListOrders` (`id`, `name`) VALUES
(0,	'BY_DIFFICULTY'),
(1,	'BY_IMPORTANCE'),
(2,	'Chronological');

DROP TABLE IF EXISTS `Tasks`;
CREATE TABLE `Tasks` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(1024) NOT NULL,
  `ingredients` varchar(1024) NOT NULL,
  `staff_instructions` varchar(1024) NOT NULL,
  `notes` varchar(1024) NOT NULL,
  `recipe_id` int DEFAULT NULL,
  `preparation_id` int DEFAULT NULL,
  `summarysheet_id` int NOT NULL,
  `start_offset` time NOT NULL,
  `end_offset` time NOT NULL,
  `assegnee_id` int DEFAULT NULL,
  `importance_value` int NOT NULL,
  `difficulty_value` int NOT NULL,
  `order` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `summarysheet_id` (`summarysheet_id`),
  KEY `assegnee_id` (`assegnee_id`),
  KEY `recipe_id` (`recipe_id`),
  KEY `preparation_id` (`preparation_id`),
  CONSTRAINT `Tasks_ibfk_1` FOREIGN KEY (`recipe_id`) REFERENCES `Recipes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Tasks_ibfk_2` FOREIGN KEY (`summarysheet_id`) REFERENCES `SummarySheets` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Tasks_ibfk_3` FOREIGN KEY (`assegnee_id`) REFERENCES `Users` (`id`) ON DELETE RESTRICT,
  CONSTRAINT `Tasks_ibfk_4` FOREIGN KEY (`recipe_id`) REFERENCES `Recipes` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Tasks_ibfk_5` FOREIGN KEY (`preparation_id`) REFERENCES `Preparations` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `Tasks` (`id`, `name`, `ingredients`, `staff_instructions`, `notes`, `recipe_id`, `preparation_id`, `summarysheet_id`, `start_offset`, `end_offset`, `assegnee_id`, `importance_value`, `difficulty_value`, `order`) VALUES
(1,	'Prep Pane al cioccolato',	'Cioccolato, pane, vaniglia',	'Manteca bene il cioccolato con la vaniglia',	'',	10,	NULL,	9,	'00:00:00',	'00:00:00',	2,	0,	2,	0),
(2,	'Prep Pane al cioccolato',	'Cioccolato, pane, vaniglia',	'Preparare il pane al cioccolato',	'null',	10,	NULL,	9,	'00:00:00',	'00:00:00',	2,	0,	2,	0);

DROP TABLE IF EXISTS `UserRoles`;
CREATE TABLE `UserRoles` (
  `user_id` int NOT NULL,
  `role_id` char(1) NOT NULL DEFAULT 's'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

INSERT INTO `UserRoles` (`user_id`, `role_id`) VALUES
(1,	'o'),
(2,	'o'),
(2,	'h'),
(3,	'h'),
(4,	'h'),
(4,	'c'),
(5,	'c'),
(6,	'c'),
(7,	'c'),
(8,	's'),
(9,	's'),
(10,	's'),
(7,	's');

DROP TABLE IF EXISTS `Users`;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;

INSERT INTO `Users` (`id`, `username`) VALUES
(1,	'Carlin'),
(2,	'Lidia'),
(3,	'Tony'),
(4,	'Marinella'),
(5,	'Guido'),
(6,	'Antonietta'),
(7,	'Paola'),
(8,	'Silvia'),
(9,	'Marco'),
(10,	'Piergiorgio');

-- 2024-01-15 19:54:54
