-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 18 oct. 2024 à 10:32
-- Version du serveur : 11.2.2-MariaDB
-- Version de PHP : 8.2.13

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
  `numAnimateur` int(11) NOT NULL AUTO_INCREMENT,
  `nomAnimateur` varchar(50) NOT NULL,
  `prenomAnimateur` varchar(50) NOT NULL,
  `paysAnimateur` varchar(50) NOT NULL,
  `villeAnimateur` varchar(50) NOT NULL,
  `nomRueAnimateur` varchar(50) NOT NULL,
  `numRueAnimateur` varchar(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`numAnimateur`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `animateur`
--

INSERT INTO `animateur` (`numAnimateur`, `nomAnimateur`, `prenomAnimateur`, `paysAnimateur`, `villeAnimateur`, `nomRueAnimateur`, `numRueAnimateur`, `email`) VALUES
(1, 'Dupont', 'Jean', 'France', 'Paris', 'Rue de la Paix', '12', 'jeandupont@gmail.com'),
(2, 'Martin', 'Sophie', 'Belgique', 'Bruxelles', 'Avenue Louise', '34', 'sophiemartin@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `animation`
--

DROP TABLE IF EXISTS `animation`;
CREATE TABLE IF NOT EXISTS `animation` (
  `idAnimation` int(11) NOT NULL AUTO_INCREMENT,
  `nomAnimation` varchar(50) NOT NULL,
  `libelleAnimation` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idAnimation`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `animation`
--

INSERT INTO `animation` (`idAnimation`, `nomAnimation`, `libelleAnimation`) VALUES
(1, 'Atelier de Peinture', 'Atelier de peinture pour débutants'),
(2, 'Tournoi de Volley-ball', 'Tournoi de volley-ball sur la plage'),
(3, 'Cours de Yoga', 'Cours de yoga en plein air'),
(4, 'Golf', 'Golf sur la plage');

-- --------------------------------------------------------

--
-- Structure de la table `animer`
--

DROP TABLE IF EXISTS `animer`;
CREATE TABLE IF NOT EXISTS `animer` (
  `idAnimer` int(11) NOT NULL AUTO_INCREMENT,
  `numAnimateur` int(11) NOT NULL,
  `idCreneau` int(11) NOT NULL,
  PRIMARY KEY (`idAnimer`),
  KEY `numAnimateur` (`numAnimateur`),
  KEY `idCreneau` (`idCreneau`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  `idConnection` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idConnection`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  `idCreneau` int(11) NOT NULL AUTO_INCREMENT,
  `heureCreneau` time NOT NULL,
  `dateCreneau` date NOT NULL,
  `dureeCreneau` time DEFAULT NULL,
  `placesCreneau` int(11) DEFAULT NULL,
  `idAnimation` int(11) NOT NULL,
  `idLieu` int(11) NOT NULL,
  PRIMARY KEY (`idCreneau`),
  KEY `idAnimation` (`idAnimation`),
  KEY `idLieu` (`idLieu`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

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
  `idLieu` int(11) NOT NULL AUTO_INCREMENT,
  `libelleLieu` varchar(100) DEFAULT NULL,
  `coordonnee` varchar(100) DEFAULT NULL,
  `paysLieu` varchar(50) DEFAULT NULL,
  `villeLieu` varchar(50) DEFAULT NULL,
  `nomRueLieu` varchar(50) DEFAULT NULL,
  `numRueLieu` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idLieu`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Déchargement des données de la table `lieu`
--

INSERT INTO `lieu` (`idLieu`, `libelleLieu`, `coordonnee`, `paysLieu`, `villeLieu`, `nomRueLieu`, `numRueLieu`) VALUES
(1, 'Salle des fêtes', '48.8566,2.3522', 'France', 'Paris', 'Rue des Fêtes', '10'),
(2, 'Terrain de sport', '50.8503,4.3517', 'Belgique', 'Bruxelles', 'Avenue des Sports', '20'),
(3, 'Plage de sable', '43.7102,7.2620', 'France', 'Nice', 'Boulevard de la Mer', '5');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
