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
-- Table structure for table `veg_items`
--

DROP TABLE IF EXISTS `veg_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `veg_items` (
  `diet_name` varchar(30) NOT NULL,
  `item_no` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(30) NOT NULL,
  `item_type` varchar(25) NOT NULL,
  `amount_in_grams` double NOT NULL,
  `calories_per_100_grams` double NOT NULL,
  `total_calories` double NOT NULL,
  PRIMARY KEY (`item_no`),
  KEY `veg_items_ibfk_1` (`diet_name`),
  CONSTRAINT `veg_items_ibfk_1` FOREIGN KEY (`diet_name`) REFERENCES `diet_plan` (`diet_name`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veg_items`
--

LOCK TABLES `veg_items` WRITE;
/*!40000 ALTER TABLE `veg_items` DISABLE KEYS */;
INSERT INTO `veg_items` VALUES ('My Diet',10,'Potato','Vegetable',200,150,300),('My Diet',11,'Carrot','Vegetable',200,80,160),('Fitness Plan',12,'Carrot and Cucumber ','Salad',300,150,450),('Fitness Plan',13,'Mashed Potato','Vegetable',200,180,360),('My Diet3',14,'Potato','Vegetable',100,80,80),('My Diet3',15,'Carrot','Vegetable',200,80,160),('Fitness Plan',34,'test','nuts',100,200,200),('NewTest',93,'Potato','Vegetable',100,180,180),('NewTest',94,'Carrot','Salad',50,100,50),('NewTest',95,'Spinach','Vegetable',50,150,75),('Fitness Plan',96,'TestForDelete','Salad',100,120,120),('TestForUpdate',97,'Pumpkin with Chapati','Vegetable',200,180,360),('TestForDelete',98,'Soyabean','Pulses',100,210,210);
/*!40000 ALTER TABLE `veg_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-15 23:56:00
