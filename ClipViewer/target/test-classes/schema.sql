
DROP TABLE IF EXISTS media;
CREATE TABLE media (
  `id_media` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_project` int(10) unsigned DEFAULT NULL,
  `type` int(10) unsigned DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `importdate` datetime DEFAULT NULL,
  `comment` text,
  `numScenes` int(10) unsigned DEFAULT NULL,
  `size` bigint(20) unsigned DEFAULT NULL,
  `serial_number` varchar(40) DEFAULT NULL,
  `location` varchar(1024) DEFAULT NULL,
  `id_serialnumber` int(11) DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_media`)
);


DROP TABLE IF EXISTS `notes`;
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
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_notes`)
);

DROP TABLE IF EXISTS `property`;
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
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_property`)
 );
  
DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene` (
  `id_scene` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_media` int(10) unsigned NOT NULL,
  `sceneNumber` int(10) unsigned DEFAULT NULL,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id_scene`),
  CONSTRAINT `id_media` FOREIGN KEY (`id_media`) REFERENCES `media` (`id_media`) ON DELETE CASCADE ON UPDATE CASCADE
); 
 