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
-- Table structure for table `trip_plan`
--

DROP TABLE IF EXISTS `trip_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trip_plan` (
  `trip_plan_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modifyed_at` datetime(6) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`trip_plan_id`),
  KEY `FKiiey7oq2csusw8gojn2twyj4i` (`user_id`),
  CONSTRAINT `FKiiey7oq2csusw8gojn2twyj4i` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trip_plan`
--

LOCK TABLES `trip_plan` WRITE;
/*!40000 ALTER TABLE `trip_plan` DISABLE KEYS */;
INSERT INTO `trip_plan` VALUES (1,'2023-05-25 09:09:58.549000','2023-05-25 09:09:58.549000','2023-05-26','강원 북부 끝판왕 코스',1),(2,'2023-05-25 10:20:40.870000','2023-05-25 10:20:40.870000','2023-05-26','부산과 울산 여행을 한번에!',1),(3,'2023-05-25 10:39:28.807000','2023-05-25 10:39:28.807000','2023-05-27','카페 마니아 주목, 카페 투어 코스!',1),(4,'2023-05-25 10:46:29.557000','2023-05-25 10:46:29.557000','2023-05-26','명동에서 이태원까지 서울의 낭만을...',1),(5,'2023-05-25 10:49:33.276000','2023-05-25 10:49:33.276000','2023-05-26','역삼 맛집 투어 ',1),(6,'2023-05-25 10:50:43.827000','2023-05-25 10:50:43.827000','2023-05-26','노원 나들이',1),(7,'2023-05-25 10:52:44.018000','2023-05-25 10:52:44.018000','2023-06-01','여수 도심을 가로질러 오동도까지 걸어라',1);
/*!40000 ALTER TABLE `trip_plan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 20:07:46
