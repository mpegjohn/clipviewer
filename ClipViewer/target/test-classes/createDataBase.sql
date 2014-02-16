-- MySQL dump 10.13  Distrib 5.1.61, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: test_vidlibdata
-- ------------------------------------------------------
-- Server version	5.1.61-0ubuntu0.10.10.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `media`
--

DROP TABLE IF EXISTS `media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `media` (
  `id_media` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_project` int(10) unsigned DEFAULT '0',
  `type` int(10) unsigned DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `importdate` datetime DEFAULT NULL,
  `comment` text,
  `numScenes` int(10) unsigned DEFAULT NULL,
  `size` bigint(20) unsigned DEFAULT NULL,
  `serial_number` varchar(40) DEFAULT NULL,
  `location` varchar(1024) DEFAULT NULL,
  `id_serialnumber` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_media`) USING BTREE,
  KEY `id_project` (`id_project`),
  KEY `type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notes`
--

DROP TABLE IF EXISTS `notes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notes` (
  `id_notes` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_scene` int(10) unsigned NOT NULL,
  `operator` varchar(45) DEFAULT NULL,
  `description` mediumtext,
  `noteName` varchar(45) DEFAULT NULL,
  `location` varchar(45) DEFAULT NULL,
  `dateChanged` int(11) DEFAULT NULL,
  `id_importer` int(11) DEFAULT NULL,
  `id_cameraman` int(11) DEFAULT NULL,
  `id_location` int(11) DEFAULT NULL,
  `title` varchar(1000) DEFAULT NULL,
  `headline` varchar(1000) DEFAULT NULL,
  `caption` text,
  `keywords` text,
  `starrating` int(10) unsigned DEFAULT '0',
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_notes`),
  KEY `id_scene_notes` (`id_scene`),
  KEY `id_importer` (`id_importer`),
  KEY `id_cameraman` (`id_cameraman`),
  KEY `id_location` (`id_location`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `property`
--

DROP TABLE IF EXISTS `property`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `property` (
  `id_property` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_scene` int(10) unsigned NOT NULL DEFAULT '0',
  `fileName` varchar(45) NOT NULL,
  `fileCreateDate` datetime NOT NULL,
  `recordedDate` datetime DEFAULT NULL,
  `fileExtension` varchar(45) NOT NULL,
  `fullFilename` varchar(1024) NOT NULL,
  `filesize` int(10) unsigned NOT NULL DEFAULT '0',
  `fileImportDate` datetime DEFAULT NULL,
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_property`) USING BTREE,
  KEY `id_scene` (`id_scene`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `scene`
--

DROP TABLE IF EXISTS `scene`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scene` (
  `id_scene` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_media` int(10) unsigned NOT NULL,
  `sceneNumber` int(10) unsigned DEFAULT NULL,
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_scene`),
  KEY `id_media` (`id_media`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `thumbnail`
--

DROP TABLE IF EXISTS `thumbnail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `thumbnail` (
  `id_thumbnail` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_scene` int(10) unsigned NOT NULL,
  `image` varchar(200) NOT NULL,
  `imageTime` varchar(45) NOT NULL,
  `imageSize` int(10) unsigned NOT NULL,
  `imageOrderNumber` int(10) unsigned DEFAULT NULL,
  `version` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_thumbnail`) USING BTREE,
  KEY `id_scene_fk` (`id_scene`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-12 13:10:38
