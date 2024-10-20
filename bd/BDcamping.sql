-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : dim. 20 oct. 2024 à 21:55
-- Version du serveur : 8.0.31
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `camping`
--

-- --------------------------------------------------------

--
-- Structure de la table `animateur`
--

DROP TABLE IF EXISTS `animateur`;
CREATE TABLE IF NOT EXISTS `animateur` (
  `numAnimateur` int NOT NULL AUTO_INCREMENT,
  `nomAnimateur` varchar(50) NOT NULL,
  `prenomAnimateur` varchar(50) NOT NULL,
  `paysAnimateur` varchar(50) NOT NULL,
  `villeAnimateur` varchar(50) NOT NULL,
  `nomRueAnimateur` varchar(50) NOT NULL,
  `numRueAnimateur` varchar(20) NOT NULL,
  `emailAnimateur` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  PRIMARY KEY (`numAnimateur`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `animateur`
--

INSERT INTO `animateur` (`numAnimateur`, `nomAnimateur`, `prenomAnimateur`, `paysAnimateur`, `villeAnimateur`, `nomRueAnimateur`, `numRueAnimateur`, `emailAnimateur`) VALUES
(22, 'Tanaka', 'Kenji', 'Japon', 'Tokyo', 'Chuo-dori', '50', 'kenji.tanaka@example.jp'),
(23, 'Oliveira', 'Ana', 'Brésil', 'Rio de Janeiro', 'Avenida Atlântica', '100', 'ana.oliveira@example.br'),
(24, 'O\'Connor', 'Liam', 'Irlande', 'Dublin', 'O\'Connell Street', '75', 'liam.oconnor@example.ie'),
(20, 'Müller', 'Hans', 'Allemagne', 'Berlin', 'Unter den Linden', '23', 'hans.muller@example.de'),
(21, 'García', 'María', 'Espagne', 'Madrid', 'Calle de Alcalá', '30', 'maria.garcia@example.es'),
(19, 'Smith', 'Emily', 'États-Unis', 'New York', 'Broadway', '120', 'emily.smith@example.com'),
(18, 'Rossi', 'Luca', 'Italie', 'Rome', 'Via del Corso', '8', 'luca.rossi@example.it'),
(17, 'Martin', 'Sophie', 'Canada', 'Montréal', 'Avenue des Canadiens', '45', 'sophie.martin@example.ca'),
(16, 'Dupont', 'Jean', 'France', 'Paris', 'Rue de la Paix', '12', 'jean.dupont@example.com'),
(25, 'Novak', 'Petra', 'République tchèque', 'Prague', 'Václavské nám?stí', '55', 'petra.novak@example.cz'),
(26, 'Khan', 'Ayesha', 'Inde', 'Mumbai', 'Marine Drive', '32', 'ayesha.khan@example.in'),
(27, 'Chen', 'Wei', 'Chine', 'Shanghai', 'Nanjing Road', '88', 'wei.chen@example.cn'),
(28, 'Jones', 'Michael', 'Australie', 'Sydney', 'George Street', '10', 'michael.jones@example.au'),
(29, 'Ahmed', 'Fatima', 'Égypte', 'Le Caire', 'Rue El-Tahrir', '25', 'fatima.ahmed@example.eg'),
(30, 'Ivanov', 'Dmitry', 'Russie', 'Moscou', 'Arbat Street', '40', 'dmitry.ivanov@example.ru'),
(31, 'Kim', 'Ji-hoon', 'Corée du Sud', 'Séoul', 'Gangnam-daero', '77', 'jihoon.kim@example.kr'),
(32, 'Nielsen', 'Freja', 'Danemark', 'Copenhague', 'Strøget', '22', 'freja.nielsen@example.dk'),
(33, 'Rodriguez', 'Carlos', 'Mexique', 'Mexico', 'Avenida Insurgentes', '90', 'carlos.rodriguez@example.mx'),
(34, 'Ali', 'Omar', 'Maroc', 'Casablanca', 'Boulevard de la Corniche', '15', 'omar.ali@example.ma'),
(35, 'Bakker', 'Sven', 'Pays-Bas', 'Amsterdam', 'Damrak', '5', 'sven.bakker@example.nl');

-- --------------------------------------------------------

--
-- Structure de la table `animation`
--

DROP TABLE IF EXISTS `animation`;
CREATE TABLE IF NOT EXISTS `animation` (
  `numAnimation` int NOT NULL AUTO_INCREMENT,
  `nomAnimation` varchar(50) NOT NULL,
  `libelleAnimation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`numAnimation`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `animation`
--

INSERT INTO `animation` (`numAnimation`, `nomAnimation`, `libelleAnimation`) VALUES
(1, 'Atelier de Peinture', 'Atelier de peinture pour débutants'),
(2, 'Tournoi de Volley-ball', 'Tournoi de volley-ball sur la plage'),
(4, 'Golf', 'Golf sur la plage'),
(5, 'Cours de yoga', 'Cours de yoga en plein air');

-- --------------------------------------------------------

--
-- Structure de la table `animer`
--

DROP TABLE IF EXISTS `animer`;
CREATE TABLE IF NOT EXISTS `animer` (
  `idAnimer` int NOT NULL AUTO_INCREMENT,
  `numAnimateur` int NOT NULL,
  `idCreneau` int NOT NULL,
  PRIMARY KEY (`idAnimer`),
  KEY `numAnimateur` (`numAnimateur`),
  KEY `idCreneau` (`idCreneau`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `animer`
--

INSERT INTO `animer` (`idAnimer`, `numAnimateur`, `idCreneau`) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `connection`
--

DROP TABLE IF EXISTS `connection`;
CREATE TABLE IF NOT EXISTS `connection` (
  `idConnection` int NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idConnection`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `connection`
--

INSERT INTO `connection` (`idConnection`, `login`, `mdp`) VALUES
(1, 'saraH', 'AzERTY123456!'),
(2, 'noa', 'Qsdfghjklmù*5'),
(4, 'tony', 'Qsdfghjklm555*');

-- --------------------------------------------------------

--
-- Structure de la table `creneau`
--

DROP TABLE IF EXISTS `creneau`;
CREATE TABLE IF NOT EXISTS `creneau` (
  `idCreneau` int NOT NULL AUTO_INCREMENT,
  `heureCreneau` time NOT NULL,
  `dateCreneau` date NOT NULL,
  `dureeCreneau` time DEFAULT NULL,
  `placesCreneau` int DEFAULT NULL,
  `idAnimation` int NOT NULL,
  `idLieu` int NOT NULL,
  PRIMARY KEY (`idCreneau`),
  KEY `idAnimation` (`idAnimation`),
  KEY `idLieu` (`idLieu`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `creneau`
--

INSERT INTO `creneau` (`idCreneau`, `heureCreneau`, `dateCreneau`, `dureeCreneau`, `placesCreneau`, `idAnimation`, `idLieu`) VALUES
(1, '10:00:00', '2024-09-21', '01:30:00', 20, 1, 1),
(2, '14:00:00', '2024-09-22', '02:00:00', 15, 2, 2),
(3, '16:00:00', '2024-09-23', '01:00:00', 10, 3, 3);

-- --------------------------------------------------------

--
-- Structure de la table `lieu`
--

DROP TABLE IF EXISTS `lieu`;
CREATE TABLE IF NOT EXISTS `lieu` (
  `idLieu` int NOT NULL AUTO_INCREMENT,
  `libelleLieu` varchar(100) DEFAULT NULL,
  `coordonneesLieu` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `paysLieu` varchar(50) DEFAULT NULL,
  `villeLieu` varchar(50) DEFAULT NULL,
  `nomRueLieu` varchar(50) DEFAULT NULL,
  `numRueLieu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idLieu`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`idLieu`, `libelleLieu`, `coordonneesLieu`, `paysLieu`, `villeLieu`, `nomRueLieu`, `numRueLieu`) VALUES
(1, 'Salle des fêtes', '48.8566,2.3522', 'France', 'Paris', 'Rue des Fêtes', '10'),
(2, 'Terrain de sport', '50.8503,4.3517', 'Belgique', 'Bruxelles', 'Avenue des Sports', '20'),
(3, 'Plage de sable', '43.7102,7.2620', 'France', 'Nice', 'Boulevard de la Mer', '5');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
