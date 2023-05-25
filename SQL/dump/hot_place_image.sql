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
-- Table structure for table `hot_place_image`
--

DROP TABLE IF EXISTS `hot_place_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hot_place_image` (
  `hot_place_image_id` int NOT NULL AUTO_INCREMENT,
  `image_path` varchar(255) DEFAULT NULL,
  `hot_place_id` int DEFAULT NULL,
  PRIMARY KEY (`hot_place_image_id`),
  KEY `FK9bxlq16ua2l2ggohjfqx9ynwr` (`hot_place_id`),
  CONSTRAINT `FK9bxlq16ua2l2ggohjfqx9ynwr` FOREIGN KEY (`hot_place_id`) REFERENCES `hot_place` (`hot_place_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot_place_image`
--

LOCK TABLES `hot_place_image` WRITE;
/*!40000 ALTER TABLE `hot_place_image` DISABLE KEYS */;
INSERT INTO `hot_place_image` VALUES (1,'84232b68-9004-4f7b-bd76-8bc6399a8db0DDP동대문디자인플라자-1.jpg',1),(2,'00061988-ef77-4dc2-88da-ad6d1b77b3ddDDP동대문디자인플라자-2.jpg',1),(3,'79f01f60-0954-4571-9a96-8455ff577fc1DDP동대문디자인플라자-3.jpg',1),(7,'696ecd83-6082-4ced-a845-ac9c06be0b54N서울타워-1.jpg',3),(8,'25fae7df-317e-489d-8c8a-60d145d19539N서울타워-2.jpg',3),(9,'e079f281-4f6c-4916-909b-1e0386bb5856N서울타워-3.jpg',3),(10,'63923bb0-38ef-4935-921e-9881ea87080b경복궁-1.jpg',4),(11,'1640d1a3-b04c-459e-8f59-50ab3a60e419경복궁-2.jpg',4),(12,'856e6c33-e3a1-4024-8516-d3e300cf3221경복궁-3.jpg',4),(13,'e74a4a81-86c1-47af-875a-d76a31370265경복궁-4.jpg',4),(14,'b7e208c9-65d2-4e92-8e03-4da3eed81422국립중앙박물관-1.jpg',5),(15,'44653653-448f-4edd-9418-0e4ce3b512ac국립중앙박물관-2.jpg',5),(16,'a5a4f08b-f80e-449e-bfc6-0e22f64aa460국립중앙박물관-3.jpg',5),(17,'5f7dd48e-8715-4ab5-87d3-68a14aff470b명동-1.jpg',6),(18,'2ab55acc-78c3-4395-ad50-25b505d8bd1f명동-2.jpg',6),(19,'82107c8a-19d6-4df2-80d4-0ba7d6f661d9명동-3.jpg',6),(20,'89a66d0b-a615-49ab-b269-3043bc984942북촌한옥마을-1.jpg',7),(21,'b02eac0b-2327-4e0c-ad61-9e4a578bf819북촌한옥마을-2.jpg',7),(22,'43f6056d-b44f-4fe4-a2e4-922588518376북촌한옥마을-3.jpg',7),(23,'f813959d-e7d7-4612-a8d4-265e4deda41c스타필드코엑스몰-1.jpg',8),(24,'496e0316-0636-45e9-8379-6ca5ce3b0424스타필드코엑스몰-2.jpg',8),(25,'8b60ab01-097e-4929-98d2-ae5a3542c6a1스타필드코엑스몰-3.jpg',8),(26,'037d2bdb-e590-4606-8cc9-5084b7209e6b인사동-1.jpg',9),(27,'11bd0068-7006-4340-b185-ca3b343bc033인사동-2.png',9),(28,'a556af2d-562e-4acd-a3c4-affd9744a9e8인사동-3.jpg',9),(29,'080cc7e8-0a68-446c-a2d5-5d30156952af청계천-1.jpg',10),(30,'d59d553a-df0c-4ed9-8ff2-03f247d11f87청계천-2.jpg',10),(31,'c857717f-3bf9-46ae-a367-52ebdaabc9a1청계천-3.jpg',10),(32,'9afa4b07-e587-4282-be05-fceb28afc55b하늘공원-1.jpg',11),(33,'9079faa7-47c4-4f2f-a93c-3a72d1485494하늘공원-2.jpg',11),(34,'720869ec-c968-484f-b19c-53073ffdde49하늘공원-3.jpg',11);
/*!40000 ALTER TABLE `hot_place_image` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 20:07:14
