-- phpMyAdmin SQL Dump
-- version 4.6.6deb4
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Erstellungszeit: 03. Aug 2020 um 19:00
-- Server-Version: 10.1.44-MariaDB-0+deb9u1
-- PHP-Version: 7.0.33-0+deb9u7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `temp_travelingapp`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `activity`
--

CREATE TABLE `activity` (
  `activity_id` int(11) NOT NULL,
  `destination_id` int(11) DEFAULT NULL,
  `name` char(50) DEFAULT NULL,
  `provider` char(50) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `season_id` int(11) DEFAULT NULL,
  `description` char(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `videoLink` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `activity`
--

INSERT INTO `activity` (`activity_id`, `destination_id`, `name`, `provider`, `enabled`, `season_id`, `description`, `price`, `difficulty`, `videoLink`) VALUES
(1, 5, 'SkyDivying', 'Skytel', 1, 4, 'Feel free like a bird.', 128.99, 2, NULL),
(2, 9, 'Diving', 'PlumberInc', 1, 2, 'Blub.', 39.56, 4, NULL),
(3, 12, 'City Tour', 'Tourz', 1, 2, 'Sight Seeing.', 25, 1, NULL),
(4, 2, 'Gokart', 'Bumper', 1, 3, 'Wrumm.', 84, 2, NULL),
(5, 12, 'Camel Riding', 'provider01', 1, 2, 'Ride a camel in the desert.', 50, 1, 'https://www.youtube.com/watch?v=-kIpiHfC644'),
(6, 12, 'Photography lessons', 'provider01', 1, 2, 'Do some great pictures with a professional.', 25, 0, 'https://www.youtube.com/watch?v=tZNvwBhRh7s'),
(7, 1, 'Museum Tour', 'BradenburgerTM', 1, 2, 'Shows the cultural importance of Berlin', 12.95, 1, NULL),
(8, 12, 'Dessert Exploration', 'provider01', 1, 1, 'Explore the secrets of the deserts!', 57, 2, 'https://www.youtube.com/watch?v=sbtt5hKeGNI'),
(9, 1, 'Biere in Berlin!', 'Berlin Beer Tours', 1, 2, 'Get to know Berlins beer culture', 54.5, 1, 'https://www.youtube.com/watch?v=i3j9ImfF4eA'),
(10, 1, 'Live Escape Game Berlin', 'THE ROOM', 1, 2, 'Die Tür fällt ins Schloss. Jetzt seid ihr allein.', 45.99, 3, 'https://www.youtube.com/watch?v=NMUGJls72a0'),
(18, 2, 'Bosporus-Boatstour', 'Istanbul Welcome Card', 1, 2, 'Take a look at the beautiful Bosporus', 10, 3, 'https://www.youtube.com/watch?v=-6AcfYAPypM'),
(19, 2, 'Rhythm of the Dance', 'Hodjapasha Cultural Center', 1, 4, 'A variety of lovely folkloric dances from Anatolia', 45.05, 2, 'https://www.youtube.com/watch?v=43JabyO_-l0'),
(20, 2, 'Turkish Bath Package', 'Aga Hamami', 1, 4, 'Experience a Turkish bath in a hammam in Istanbul.', 28.5, 1, 'https://www.youtube.com/watch?v=YGgx8ua_Xx0'),
(21, 3, 'Kyoto Cultural Forest', 'Kyoto', 1, 3, 'This is the ideal choice for visitors to Kyoto.', 119.24, 1, 'https://www.youtube.com/watch?v=yK4aAYHKAjg'),
(22, 3, 'Japanese Tea Ceremony', 'Tea Ceremony Juan', 1, 1, 'The ceremony is described by many as mesmerizing.', 25.28, 1, 'https://www.youtube.com/watch?v=8fIeYpdbFHY'),
(23, 3, 'Samurai experience in Kyoto', 'Kyoto Samurai Experience', 1, 1, 'Try to be a samurai for the day.', 99.65, 3, 'https://www.youtube.com/watch?v=2CV3RdpyD4U'),
(24, 4, 'City Tour of St. Petersburg', 'City Break Tours St. Petersburg', 1, 2, 'Get an overview of St Petersburg on this excursion', 31.5, 1, 'https://www.youtube.com/watch?v=j8g7ASbKViI'),
(25, 4, 'Matryoshka Master Class', 'Matryoshka Master Class', 1, 4, 'Discover the art of matryoshka doll making.', 23, 2, 'https://www.youtube.com/watch?v=Usig6HyuVDE'),
(26, 4, 'Eremitage St. Petersburg', ' Marina Wilson Private Tours', 1, 3, 'Tour of the Eremitage Museum in St. Petersburg.', 46.99, 1, 'https://www.youtube.com/watch?v=fWRHjOh6tlc'),
(27, 5, 'Polish Culinary Tour', 'The Walking Parrot Warsaw Tours and Pubcrawls', 1, 2, 'Immerse yourself in the Polish cuisine.', 35.9, 1, 'https://www.youtube.com/watch?v=Oeq8EtUkF3U'),
(28, 5, 'The Chopins Chamber Concert.', 'Chopin Point Warsaw', 1, 1, 'Take some time in Warsaw to enjoy Chopin.', 18, 1, 'https://www.youtube.com/watch?v=xhQFt0OOec0'),
(29, 5, 'American taxi', 'provider01', 0, 2, 'Take an american taxi.', 25, 3, 'https://www.youtube.com/watch?v=9gUBd5K62N8'),
(31, 5, 'Lunch in Nature', 'provider01', 0, 3, 'A nice Picknick right next to the Spree', 25, 0, 'https://www.youtube.com/watch?v=1ybm6DJHqyc'),
(35, 8, 'Surfing', 'provider01', 0, 1, '', 25, 0, 'https://www.youtube.com/watch?v=hwLo7aU1Aas'),
(36, 4, 'Learn real Russian!', 'provider01', 0, 2, 'Real russian from a real pro!', 25, 0, 'https://www.youtube.com/watch?v=AYRZupz6rdw'),
(37, 7, 'Eat a nice Baguette', 'provider01', 0, 2, 'eat baguette', 11, 2, 'https://www.youtube.com/watch?v=cCNd0Loa7JM'),
(38, 11, 'Camping', 'provider01', 0, 4, 'camp', 35, 5, 'youtube.com/camping'),
(39, 10, 'Swimming', 'provider01', 0, 3, '', 25, 2, '');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `activity_available`
--

CREATE TABLE `activity_available` (
  `activity_available_id` int(11) NOT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `day` date DEFAULT NULL,
  `start_time` time DEFAULT NULL,
  `end_time` time DEFAULT NULL,
  `slots` int(11) DEFAULT NULL,
  `maxSlots` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `activity_available`
--

INSERT INTO `activity_available` (`activity_available_id`, `activity_id`, `day`, `start_time`, `end_time`, `slots`, `maxSlots`) VALUES
(1, 1, '2020-07-10', '16:30:00', '17:30:00', 12, 12),
(2, 2, '2020-08-10', '10:00:00', '10:45:00', 6, 6),
(3, 3, '2020-09-10', '10:00:00', '15:00:00', 42, 42),
(4, 4, '2020-10-10', '15:30:00', '17:30:00', 16, 16),
(5, 5, '2020-07-03', '20:09:27', '21:24:27', 17, 20),
(6, 5, '2020-07-04', '21:23:48', '10:24:48', 10, 10),
(7, 5, '2020-07-03', '21:23:18', '10:05:18', 3, 5),
(8, 8, '2020-07-04', '06:24:43', '08:26:43', 10, 12),
(9, 6, '2020-07-06', '15:22:31', '16:22:31', 5, 7),
(10, 6, '2020-07-05', '10:21:39', '11:22:39', 8, 10),
(11, 8, '2020-07-07', '12:24:03', '14:25:03', 6, 8),
(12, 6, '2020-07-09', '15:45:52', '17:47:52', 64, 65),
(13, 5, '2020-08-10', '13:00:00', '15:00:00', 32, 32);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `authority`
--

CREATE TABLE `authority` (
  `ID` int(11) NOT NULL,
  `authority` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `authority`
--

INSERT INTO `authority` (`ID`, `authority`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER'),
(3, 'ROLE_PROVIDER');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `authority_user`
--

CREATE TABLE `authority_user` (
  `user_id` int(11) DEFAULT NULL,
  `authority_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `authority_user`
--

INSERT INTO `authority_user` (`user_id`, `authority_id`) VALUES
(1, 1),
(4, 2),
(5, 3),
(6, 2),
(9, 2),
(3, 2),
(7, 2),
(2, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking`
--

CREATE TABLE `booking` (
  `booking_id` int(11) NOT NULL,
  `destination_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `person_count` int(11) DEFAULT NULL,
  `hotel_id` int(11) DEFAULT NULL,
  `date_from` date DEFAULT NULL,
  `date_to` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `booking`
--

INSERT INTO `booking` (`booking_id`, `destination_id`, `user_id`, `person_count`, `hotel_id`, `date_from`, `date_to`) VALUES
(1, 9, 4, 3, 9, '2020-07-10', '2020-07-27'),
(2, 12, 1, 2, 12, '2020-04-02', '2020-04-13'),
(3, 3, 3, 1, 3, '2020-05-15', '2020-06-01'),
(4, 5, 2, 2, 5, '2020-12-01', '2022-12-11'),
(5, 12, 1, 2, 12, '2020-07-01', '2020-07-15'),
(6, 12, 3, 2, 12, '2020-07-03', '2020-05-10'),
(7, 12, 5, 1, 12, '2020-07-07', '2020-07-18'),
(8, 2, 3, 1, 2, '2020-06-27', '2020-06-30'),
(9, 8, 1, 1, 8, '2020-08-16', '2020-08-19'),
(10, 1, 2, 1, 1, '2020-07-03', '2020-07-06'),
(11, 4, 1, 1, 4, '2020-07-04', '2020-07-07'),
(12, 7, 5, 3, 7, '2020-08-15', '2020-08-21'),
(13, 8, 5, 1, 8, '2020-11-25', '2020-11-27'),
(14, 8, 5, 1, 8, '2020-11-25', '2020-11-27'),
(15, 8, 5, 1, 8, '2020-11-25', '2020-11-27'),
(16, 8, 5, 1, 8, '2020-11-25', '2020-11-27'),
(17, 6, 2, 1, 6, '2020-07-28', '2020-07-29');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `booking_activity`
--

CREATE TABLE `booking_activity` (
  `activity_available_id` int(11) DEFAULT NULL,
  `booking_id` int(11) DEFAULT NULL,
  `booked_slots` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `booking_activity`
--

INSERT INTO `booking_activity` (`activity_available_id`, `booking_id`, `booked_slots`) VALUES
(3, 1, 3),
(4, 1, 2),
(1, 1, 2),
(2, 2, 1),
(3, 4, 2),
(12, 7, 1),
(13, 2, 1),
(5, 6, 2),
(7, 5, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `destination`
--

CREATE TABLE `destination` (
  `destination_id` int(11) NOT NULL,
  `country` char(50) DEFAULT NULL,
  `city` char(50) DEFAULT NULL,
  `continent` char(50) DEFAULT NULL,
  `abr` varchar(250) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `destination`
--

INSERT INTO `destination` (`destination_id`, `country`, `city`, `continent`, `abr`, `description`) VALUES
(1, 'Germany', 'Berlin', 'Europe', 'DE', 'Wonderful German city!'),
(2, 'Turkey', 'Istanbul', 'Europe', 'TR', 'Best cultural secret of Turkey.'),
(3, 'Japan', 'Kyoto', 'Asia', 'JP', 'You like the whole asian culture? This is the place for you!'),
(4, 'Russia', 'St. Petersburg', 'Asia', 'RU', 'Everybody should visit Russia atleast once, right?'),
(5, 'Poland', 'Warsaw', 'Europe', 'PL', 'No, this isn\'t Russia, but it\'s as much fun as Russia.'),
(6, 'Bulgaria', 'Sofia', 'Europe', 'BG', 'Beautiful landsacpes and buildings.'),
(7, 'France', 'Paris', 'Europe', 'FR', 'You\'ve been searching for the best baguette in the world? This is the right place.'),
(8, 'USA', 'New York', 'North Amerika', 'US', 'You should visit the statue of liberty!'),
(9, 'China', 'Wuhan', 'Asia', 'CN', 'Alot of people, fast traffic, its chaos in a nice way.'),
(10, 'Netherlands', 'Amsterdam', 'Europe', 'NL', 'Great culture and food.'),
(11, 'United Kingdom', 'London', 'Europe', 'GB', 'You love the british accent, don\'t you?'),
(12, 'United Arab Emirates', 'Dubai', 'Middle East', 'AE', 'Warm but very exotic, try riding a camel, I\'ve heard its great!');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `hotel`
--

CREATE TABLE `hotel` (
  `hotel_id` int(11) NOT NULL,
  `destination_id` int(11) DEFAULT NULL,
  `name` char(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `hotel`
--

INSERT INTO `hotel` (`hotel_id`, `destination_id`, `name`, `price`, `description`) VALUES
(1, 1, 'Deluxe', 1000, 'Deluxe hotel in the center of the capital.'),
(2, 2, 'Ciragan Palace Kempinski', 421, 'Enjoy your most favourite night in the city of istanbul.'),
(3, 3, 'Gion Ryokan Q-beh', 45, 'Beautiful hotel for families.'),
(4, 4, 'Boutique 1852', 68, 'You want a relax vacation? This is your place.'),
(5, 5, 'The Westin Warsaw', 91, 'You want to party and have fun? This is your place!'),
(6, 6, 'Thracia', 84, 'Relax? Sleep? This is your place.'),
(7, 7, 'Hotel Arvor Saint Georges', 188, 'Want a beautiful view over the whole city of love paris? This is your place!'),
(8, 8, 'Trump International Hotel & Tower', 355, 'Just come please'),
(9, 9, 'Shangri-La Wuhan', 17, 'No bad soup served.'),
(10, 10, 'NH Collection Amsterdam Flower Market', 142, 'Get high on the highest floor in amsterdam.'),
(11, 11, 'No 1 The Mansions', 420, 'Its a huge mansion, come and see it.'),
(12, 12, 'Burj Al Arab', 1136, 'Wow such big building, and so warm but amazing.');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `rating`
--

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `activity_id` int(11) NOT NULL,
  `rating_value` int(11) DEFAULT NULL,
  `comment` char(250) DEFAULT NULL,
  `picture_name` varchar(250) DEFAULT NULL,
  `rating_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `rating`
--

INSERT INTO `rating` (`rating_id`, `user_id`, `activity_id`, `rating_value`, `comment`, `picture_name`, `rating_date`) VALUES
(1, 1, 1, 1, 'Lame', NULL, '2020-07-31'),
(2, 2, 4, 1, 'Gelungen', '2.jpg', '2020-04-14'),
(3, 1, 5, 5, 'it was very coool experience', '3.jpg', '2020-07-30'),
(4, 2, 5, 2, 'i got really scared', NULL, '2020-07-31');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `season`
--

CREATE TABLE `season` (
  `season_id` int(11) NOT NULL,
  `season_name` char(50) DEFAULT NULL,
  `season_start` date DEFAULT NULL,
  `season_end` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `season`
--

INSERT INTO `season` (`season_id`, `season_name`, `season_start`, `season_end`) VALUES
(1, 'Frühling', '2020-03-20', '2020-06-20'),
(2, 'Sommer', '2020-06-21', '2020-09-22'),
(3, 'Herbst', '2020-09-23', '2020-12-21'),
(4, 'Winter', '2021-01-23', '2021-03-19');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `user`
--

CREATE TABLE `user` (
  `ID` int(11) NOT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  `password` varchar(250) DEFAULT NULL,
  `username` char(50) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `profilePicture` varchar(255) DEFAULT 'user.png',
  `reset_token` char(36) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Daten für Tabelle `user`
--

INSERT INTO `user` (`ID`, `enabled`, `password`, `username`, `email`, `profilePicture`, `reset_token`) VALUES
(1, 1, '$2a$04$F8sbJxwCh4xfGdE3HwKKb.MBXG4S.Ax/uZBA0oC/ZArESblcHxR22', 'admin', 'admin@hotmail.com', 'admin.jpg', NULL),
(2, 1, '$2a$04$3ceBwg2LCI1KNKqJ1qBoqel8ixdjCNtBCoQU7/Be/c1l8mb8KLdxO', 'user', 'user@hotmail.com', NULL, NULL),
(3, 1, '$2a$04$vKmV2RCjLzrflYk7gbozROu0BdmxInGOuQMz1dpxCeJvM9ezsSOFK', 'account01', 'account01@hotmail.com', 'user.png', NULL),
(4, 1, '$2a$04$F8sbJxwCh4xfGdE3HwKKb.MBXG4S.Ax/uZBA0oC/ZArESblcHxR22', 'account02', 'account02@hotmail.com', 'user.png', NULL),
(5, 1, '$2a$04$F8sbJxwCh4xfGdE3HwKKb.MBXG4S.Ax/uZBA0oC/ZArESblcHxR22', 'provider01', 'provider01@hotmail.com', 'provider01.jpg', NULL),
(6, 1, '$2a$04$32vl455jIfDnVncgIR3w8.ODKV0Gsx34X8g.iAQsnypRO08AHBq1S', 'test01', 'test1@hotmail.com', 'user.png', NULL),
(7, 0, '$2a$04$lJXNxr1huy0BXl.vWgkHA.FTuRLX1vWtwpX0o9ag4QyqmqLQQIkjS', 'test02', 'test2@hotmail.com', NULL, NULL),
(9, 1, '$2a$04$F8sbJxwCh4xfGdE3HwKKb.MBXG4S.Ax/uZBA0oC/ZArESblcHxR22', 'pictest2', 'pictest2@hotmail.com', 'user.png', NULL);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activity_id`),
  ADD KEY `season_id` (`season_id`),
  ADD KEY `destination_id` (`destination_id`);

--
-- Indizes für die Tabelle `activity_available`
--
ALTER TABLE `activity_available`
  ADD PRIMARY KEY (`activity_available_id`),
  ADD KEY `activity_id` (`activity_id`);

--
-- Indizes für die Tabelle `authority`
--
ALTER TABLE `authority`
  ADD PRIMARY KEY (`ID`);

--
-- Indizes für die Tabelle `authority_user`
--
ALTER TABLE `authority_user`
  ADD KEY `user_id` (`user_id`),
  ADD KEY `authority_id` (`authority_id`);

--
-- Indizes für die Tabelle `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`booking_id`),
  ADD KEY `destination_id` (`destination_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- Indizes für die Tabelle `booking_activity`
--
ALTER TABLE `booking_activity`
  ADD KEY `booking_id` (`booking_id`),
  ADD KEY `activity_available_id` (`activity_available_id`);

--
-- Indizes für die Tabelle `destination`
--
ALTER TABLE `destination`
  ADD PRIMARY KEY (`destination_id`);

--
-- Indizes für die Tabelle `hotel`
--
ALTER TABLE `hotel`
  ADD PRIMARY KEY (`hotel_id`),
  ADD KEY `destination_id` (`destination_id`);

--
-- Indizes für die Tabelle `rating`
--
ALTER TABLE `rating`
  ADD PRIMARY KEY (`rating_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `activity_id` (`activity_id`);

--
-- Indizes für die Tabelle `season`
--
ALTER TABLE `season`
  ADD PRIMARY KEY (`season_id`);

--
-- Indizes für die Tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `activity`
--
ALTER TABLE `activity`
  MODIFY `activity_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT für Tabelle `activity_available`
--
ALTER TABLE `activity_available`
  MODIFY `activity_available_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT für Tabelle `authority`
--
ALTER TABLE `authority`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT für Tabelle `booking`
--
ALTER TABLE `booking`
  MODIFY `booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT für Tabelle `destination`
--
ALTER TABLE `destination`
  MODIFY `destination_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT für Tabelle `hotel`
--
ALTER TABLE `hotel`
  MODIFY `hotel_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT für Tabelle `rating`
--
ALTER TABLE `rating`
  MODIFY `rating_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `season`
--
ALTER TABLE `season`
  MODIFY `season_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT für Tabelle `user`
--
ALTER TABLE `user`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`season_id`) REFERENCES `season` (`season_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `activity_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `activity_available`
--
ALTER TABLE `activity_available`
  ADD CONSTRAINT `activity_available_ibfk_1` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `authority_user`
--
ALTER TABLE `authority_user`
  ADD CONSTRAINT `authority_user_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `authority_user_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`ID`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `booking_ibfk_3` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`hotel_id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `booking_activity`
--
ALTER TABLE `booking_activity`
  ADD CONSTRAINT `booking_activity_ibfk_1` FOREIGN KEY (`booking_id`) REFERENCES `booking` (`booking_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `booking_activity_ibfk_2` FOREIGN KEY (`activity_available_id`) REFERENCES `activity_available` (`activity_available_id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `hotel`
--
ALTER TABLE `hotel`
  ADD CONSTRAINT `hotel_ibfk_1` FOREIGN KEY (`destination_id`) REFERENCES `destination` (`destination_id`) ON DELETE CASCADE;

--
-- Constraints der Tabelle `rating`
--
ALTER TABLE `rating`
  ADD CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`ID`) ON DELETE CASCADE,
  ADD CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`activity_id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
