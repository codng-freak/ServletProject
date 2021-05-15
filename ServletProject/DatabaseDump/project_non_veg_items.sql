-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `non_veg_items`
--

DROP TABLE IF EXISTS `non_veg_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_veg_items` (
  `diet_name` varchar(30) NOT NULL,
  `item_no` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(25) NOT NULL,
  `amount_in_grams` double NOT NULL,
  `calories_per_100_grams` double NOT NULL,
  `total_calories` double NOT NULL,
  PRIMARY KEY (`item_no`),
  KEY `non_veg_items_ibfk_1` (`diet_name`),
  CONSTRAINT `non_veg_items_ibfk_1` FOREIGN KEY (`diet_name`) REFERENCES `diet_plan` (`diet_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_veg_items`
--

LOCK TABLES `non_veg_items` WRITE;
/*!40000 ALTER TABLE `non_veg_items` DISABLE KEYS */;
INSERT INTO `non_veg_items` VALUES ('My Diet',8,'Chicken',500,180,900),('Fitness Plan',9,'Chicken with Rice',300,210,630),('My Diet3',10,'Chicken',500,180,900),('Fitness Plan',23,'test',200,300,600),('NewTest',42,'Chicken',100,210,210),('NewTest',43,'Egg',50,150,75),('Fitness Plan',44,'TestForDelete',100,120,120),('TestForUpdate',45,'Fish',100,210,210),('TestForDelete',46,'Meat',100,210,210);
/*!40000 ALTER TABLE `non_veg_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 23:55:59
