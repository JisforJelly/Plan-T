-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: enjoytrip3
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `trip_plan_group`
--

DROP TABLE IF EXISTS `trip_plan_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_plan_group` (
  `trip_plan_group_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modifyed_at` datetime(6) DEFAULT NULL,
  `trip_plan_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`trip_plan_group_id`),
  KEY `FK6b57b41r362lusmqi4rgutxn5` (`trip_plan_id`),
  KEY `FKcnsitsp5a4hf2olgnrwfpps9x` (`user_id`),
  CONSTRAINT `FK6b57b41r362lusmqi4rgutxn5` FOREIGN KEY (`trip_plan_id`) REFERENCES `trip_plan` (`trip_plan_id`),
  CONSTRAINT `FKcnsitsp5a4hf2olgnrwfpps9x` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_plan_group`
--

LOCK TABLES `trip_plan_group` WRITE;
/*!40000 ALTER TABLE `trip_plan_group` DISABLE KEYS */;
INSERT INTO `trip_plan_group` VALUES (1,'2023-05-25 09:09:58.559000','2023-05-25 09:09:58.559000',1,1),(2,'2023-05-25 10:20:40.873000','2023-05-25 10:20:40.873000',2,1),(3,'2023-05-25 10:39:28.808000','2023-05-25 10:39:28.808000',3,1),(4,'2023-05-25 10:46:29.558000','2023-05-25 10:46:29.558000',4,1),(5,'2023-05-25 10:49:33.277000','2023-05-25 10:49:33.277000',5,1),(6,'2023-05-25 10:50:43.828000','2023-05-25 10:50:43.828000',6,1),(7,'2023-05-25 10:52:44.019000','2023-05-25 10:52:44.019000',7,1),(8,'2023-05-25 10:55:51.021000','2023-05-25 10:55:51.021000',3,3);
/*!40000 ALTER TABLE `trip_plan_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 20:07:52
