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
-- Table structure for table `fruit_items`
--

DROP TABLE IF EXISTS `fruit_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fruit_items` (
  `diet_name` varchar(30) NOT NULL,
  `item_no` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(25) NOT NULL,
  `amount_in_grams` double NOT NULL,
  `calories_per_100_grams` double NOT NULL,
  `total_calories` double NOT NULL,
  PRIMARY KEY (`item_no`),
  KEY `fruit_items_ibfk_1` (`diet_name`),
  CONSTRAINT `fruit_items_ibfk_1` FOREIGN KEY (`diet_name`) REFERENCES `diet_plan` (`diet_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fruit_items`
--

LOCK TABLES `fruit_items` WRITE;
/*!40000 ALTER TABLE `fruit_items` DISABLE KEYS */;
INSERT INTO `fruit_items` VALUES ('My Diet',14,'Watermelon',200,150,300),('My Diet',15,'Banana',100,180,180),('Fitness Plan',16,'Watermelon',100,150,150),('Fitness Plan',17,'Grapes',50,80,40),('Fitness Plan',18,'Banana',100,180,180),('My Diet3',19,'Watermelon',200,150,300),('My Diet3',20,'Banana',200,180,360),('Fitness Plan',33,'test',100,250,250),('Fitness Plan',48,'TestForDelete',100,120,120),('NewTest',49,'Blueberry',50,150,75),('NewTest',50,'Kiwi',50,180,90),('TestForUpdate',51,'Watermelon',50,100,50),('TestForDelete',52,'Banana',50,150,75);
/*!40000 ALTER TABLE `fruit_items` ENABLE KEYS */;
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
