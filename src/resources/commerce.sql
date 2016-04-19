-- MySQL dump 10.13  Distrib 5.7.10, for Win64 (x86_64)
--
-- Host: localhost    Database: commercedb
-- ------------------------------------------------------
-- Server version	5.7.10-log

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

CREATE DATABASE 'commercedb' DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'category1','category1'),(2,'category2','description2'),(3,'category3','description3'),(4,'category4','description4'),(5,'category5','description5'),(6,'category6','description6'),(7,'category7','description7'),(8,'category8','description8'),(9,'category9','description9'),(10,'category10','description10'),(11,'category11','description11'),(12,'category12','description12'),(13,'category13','description13'),(14,'category14','description14'),(15,'category15','description15'),(16,'category16','description16'),(17,'category17','description17'),(18,'category18','description18');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commodity`
--

DROP TABLE IF EXISTS `commodity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `description` text,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKk3fgmjjsvkqunh9aosp9eoylk` (`category_id`),
  CONSTRAINT `FKk3fgmjjsvkqunh9aosp9eoylk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `catagory_id_ref` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commodity`
--

LOCK TABLES `commodity` WRITE;
/*!40000 ALTER TABLE `commodity` DISABLE KEYS */;
INSERT INTO `commodity` VALUES (1,'name1','description1',1),(2,'name2','description2',2),(3,'name3sdfsafsdf','descriptios;ldjf',3),(4,'name4','description4',4),(5,'name5','des',5),(6,'name6sdf','description6sdf',6),(7,'name7','description7',7),(8,'name8','description8',8),(9,'name9sgd','description9',9),(10,'name10','description10',10),(11,'name11','description11',11),(12,'name12','description12',12),(13,'name13','description13',13),(14,'name14','description14',14),(15,'name15','description15',15),(16,'name16','description16',16),(17,'name17','description17',17),(18,'name18','description18',18),(19,'name19','description19',1),(20,'name20','description20',2),(21,'name21','description21',3),(22,'name22','description22',4),(23,'name23','description23',5),(24,'name24','description24',6),(25,'name25','description25',7),(26,'name26','description26',8),(27,'name27','description27',9),(28,'name28','description28',10),(29,'name29','description29',11),(30,'name30','description30',12),(31,'name31','description31',13),(32,'name32','description32',14),(33,'name33','description33',15),(34,'name34','description34',16),(35,'name35','description35',17),(36,'name36','description36',18),(37,'name37','description37',1),(38,'name38','description38',2),(39,'name39','description39',3),(40,'name40','description40',4),(41,'name41','description41',5),(42,'name42','description42',6),(43,'name43','description43',7),(44,'name44','description44',8),(45,'name45','description45',9),(46,'name46','description46',10),(47,'name47','description47',11),(48,'name48','description48',12),(49,'name49','description49',13),(50,'name50','description50',14),(51,'name51','description51',15),(52,'name52','description52',16),(53,'name53','description53',17),(54,'name54','description54',18),(55,'name55','description55',1),(56,'name56','description56',2),(57,'name57','description57',3),(58,'name58','description58',4),(59,'name59','description59',5),(60,'name60','description60',6),(61,'name61','description61',7),(62,'name62','description62',8),(63,'name63','description63',9),(64,'name64','description64',10),(65,'name65','description65',11),(66,'name66','description66',12),(67,'name67','description67',13),(68,'name68','description68',14),(69,'name69','description69',15),(70,'name70','description70',16),(71,'name71','description71',17),(72,'name72','description72',18),(73,'name73','description73',1),(74,'name74','description74',2),(75,'name75','description75',3),(76,'name76','description76',4),(77,'name77','description77',5),(78,'name78','description78',6),(79,'name79','description79',7),(80,'name80','description80',8),(81,'name81','description81',9),(82,'name82','description82',10),(83,'name83','description83',11),(84,'name84','description84',12),(85,'name85','description85',13),(86,'name86','description86',14),(87,'name87','description87',15),(88,'name88','description88',16),(89,'name89','description89',17),(90,'name90','description90',18),(91,'name91','description91',1),(92,'name92','description92',2),(93,'name93','description93',3),(94,'name94','description94',4),(95,'name95','description95',5),(96,'name96','description96',6),(97,'name97','description97',7),(98,'name98','description98',8),(99,'name99','description99',9),(100,'name100','description100',10);
/*!40000 ALTER TABLE `commodity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('TangDaZhu','1234','tangdazhu@sina.com',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-04-18 11:42:14
