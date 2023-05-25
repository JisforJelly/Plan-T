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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modifyed_at` datetime(6) DEFAULT NULL,
  `content` varchar(4096) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view_cnt` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK72mt33dhhs48hf9gcqrq4fxte` (`user_id`),
  CONSTRAINT `FK72mt33dhhs48hf9gcqrq4fxte` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'2023-05-25 08:56:21.608000','2023-05-25 10:12:38.255000','<p>삼성 청년 SW 아카데미 10기 에세이 및 SW적성진단에 대하여 안내 드립니다.</p><p><br></p><p><strong>① 에세이</strong></p><p>■ 제출 기한 : 2023. 5. 9(화) ~ 5. 20(토) 23:59 까지</p><p>&nbsp;&nbsp;※ 단, SW적성진단(SW비전공) 응시일 5.13(토)는 에세이 작성 및 제출 불가</p><p>■ 제출 방법 : ssafy.com 로그인 → \"지원현황조회\" → \"에세이 작성\"</p>','삼성 청년 SW 아카데미 10기 에세이 및 SW적성진단에 대하여 안내 드립니다.',24,1),(2,'2023-05-25 08:56:45.992000','2023-05-25 10:09:59.583000','<p><strong style=\"color: rgb(9, 151, 238);\">삼성 청년 SW 아카데미(SSAFY)</strong><strong style=\"color: rgb(31, 31, 31);\">는&nbsp;삼성의 SW 교육 경험과</strong></p><p><strong style=\"color: rgb(31, 31, 31);\">고용노동부의 취업지원 노하우를 바탕으로 취업 준비생에게 SW 역량 향상 교육 및</strong></p><p><strong style=\"color: rgb(31, 31, 31);\">다양한 취업지원 서비스를 제공하여 취업에 성공하도록 돕는 프로그램입니다.</strong></p>','삼성 청년 SW 아카데미(SSAFY)',2,1),(3,'2023-05-25 09:00:32.136000','2023-05-25 10:10:03.542000','<p>SSAFY의 새로운 취업브랜드! 2학기 재학생과 미취업 수료생을 위한 취업의 장!</p><p>SSAFY 캠퍼스 리크루팅이 3월 27일부터 4월 14일까지 3주간 진행되었습니다.</p><p><br></p><p>SSAFY캠퍼스 리크루팅은 SSAFY캠퍼스 내에서 우수IT기업과 교육생간 채용 면접 및 상담을 실시하는 프로그램입니다.</p><p>이를 통해 교육생 및 수료생에게는 면접 참여에 대한 접근성을 높이고,</p><p>참여 기업에게는 우수인재를 확보할 수 있는 또 다른 기회를 제공합니다.</p><p><br></p><p>이번 캠퍼스 리크루팅에서는 한화비전, 에릭슨LG 등 총 13개 기업이 SSAFY 캠퍼스에 방문하여</p><p>수료를 앞두고 있는 8기 재학생들과&nbsp;취업 준비중인 7기 수료생들을 대상으로 면접 및 채용상담을 진행하였습니다.</p><p>한국IBM과 골드플래닛 등은 별도의 채용설명회를 진행할 만큼 SSAFY 인재를 채용하기 위해 적극적인 모습을 보였습니다.</p><p><br></p>','SSAFY 캠퍼스 리크루팅 개최',1,1),(4,'2023-05-25 09:01:00.688000','2023-05-25 10:10:06.573000','<h2>미션기반의 코드리뷰 중심</h2><h2><br></h2><ol><li>강의식 수업보다는 단계별로 나누어져 있는 여러 개의 미션을 통해 학습합니다. </li><li>미션을 구현한 후 현장 경험을 가진 경력 개발자에게 리뷰 요청을 하면 피드백을 받는 방식으로 진행합니다.</li></ol>','우아한테크코스(1)',2,1),(5,'2023-05-25 09:01:18.641000','2023-05-25 09:01:19.783000','<h2>소통과 협업 위주 학습</h2><h2><br></h2><p>모든 미션 진행을 짝 프로그래밍으로 진행하여 교육생 사이에 토론을 활성화하고, </p><p><br></p><p>공동 학습 환경을 구축합니다</p>','소통과 협업 위주 학습',1,1),(6,'2023-05-25 09:01:35.665000','2023-05-25 10:10:08.856000','<h2>현장 중심 교육</h2><h2><br></h2><p>교육 과정의 절반을 프로젝트로 구성하여 현장과 같은 협업 경험을 하고,</p><p><br></p><p> 레거시 코드를 리팩터링 하는 경험을 하도록 설계합니다.</p>','현장 중심 교육',2,1),(7,'2023-05-25 09:01:59.497000','2023-05-25 10:10:10.775000','<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 245, 198);\">한 가지 일에 몰입해 성공한 경험</span><span style=\"color: rgb(51, 51, 51);\">이 있어야 합니다. </span></p><p><span style=\"color: rgb(51, 51, 51);\">깊이 있는 몰입을 통해 성공한 경험이 있는 사람은 다른 분야를 도전해도 성공 가능성이 높다고 생각합니다. </span></p><p><span style=\"color: rgb(51, 51, 51);\">목표 달성을 위한 과정과 문제를 극복하는 방법을 알기 때문입니다.</span></p>','우아한 테크코스(3)',2,1),(8,'2023-05-25 09:02:51.056000','2023-05-25 10:10:13.983000','<p><br></p><p>지속 가능한 개발자로 성장하고 싶은 분을 기다립니다.</p><p><br></p><p><strong style=\"color: rgb(34, 34, 34);\"><em><u>자기 주도적으로 행동하는 분</u></em></strong></p><ul><li>“해야 하는데”에서 끝나지 않고 실천하는 분</li><li>적당히, 그러려니 하기 보다 더 나은&nbsp;방법을 떠올리는 분</li><li>주변 사람들에게 인정받을 정도로&nbsp;무언가에 몰입해 본 분</li></ul><p><strong style=\"color: rgb(34, 34, 34);\"><em>열린 사고로 동료와 협력하는 분</em></strong></p><ul><li>나와 다른 의견이 틀린 것은 아니라고&nbsp;생각하는 분</li><li>나만의 노하우, 방법, 생각을 기록하고&nbsp;공유하길 즐기는 분</li></ul><p><br></p>','BoostCamp',1,1),(9,'2023-05-25 09:04:35.057000','2023-05-25 10:10:20.953000','<p><strong>STEP 1</strong></p><p><span style=\"color: rgb(117, 117, 117);\">냄비에 핏물을 빼고 살짝 데친 갈비와 큼직하게 썬 양파, 대파, 물 10컵을 넣고 센불에서 1시간 이상 삶습니다. </span></p><p><span style=\"color: rgb(117, 117, 117);\">(갈비는 30분간 찬물에 담가 핏물을 빼고 끓는 물에 살짝 데친 후 찬물에 헹궈 준비해주세요.)</span></p><p><br></p><p><strong>STEP 2</strong></p><p><span style=\"color: rgb(117, 117, 117);\">갈비 삶은 국물은 체에 걸러두고 고기는 결 반대 방향으로 칼집을 넣습니다. </span></p><p><span style=\"color: rgb(117, 117, 117);\">양념 재료를 잘 섞은 후 갈비를 양념에 30분 이상 재워주세요.</span></p><p><br></p><p><strong>STEP 3</strong></p><p><span style=\"color: rgb(117, 117, 117);\">무와 당근은 한입 크기로 자른 후 모서리를 둥글게 다듬고 홍고추는 어슷하게 썰어줍니다.</span></p><p><span style=\"color: rgb(117, 117, 117);\">표고버섯은 4등분하고 밤은 껍질을 까주세요.</span></p><p><br></p><p><strong>STEP 4</strong></p><p><span style=\"color: rgb(117, 117, 117);\">냄비에 갈비와 갈비 삶은 국물 3컵을 넣고 센 불에서 20분간 끓이다가 다듬은 채소를 넣고 중간불에서 15~20분간 끓입니다.</span></p><p><br></p><ul><li><span style=\"color: rgb(117, 117, 117);\">예로부터 임금님 수라상에 올랐던 귀한 음식인 갈비찜은 명절이면 빠지지 않고 등장하는 음식이죠. 생일상이나 집들이 음식으로도 두루 사랑 받고 있어요. </span></li><li><span style=\"color: rgb(117, 117, 117);\">알맞게 익어 야들야들하고 ‘달콤짭짜름’한 갈비와 쫄깃한 표고버섯, 고소한 밤과 은행까지 골고루 맛보다 보면 만족스러운 한 끼 식사가 되지요.</span></li></ul>','갈비찜 레시피',2,1),(10,'2023-05-25 09:04:58.655000','2023-05-25 10:10:16.479000','<h2>애플리케이션</h2><p>이 문서는 애플리케이션(이하 앱)에 대해 안내합니다.</p><p><a href=\"https://developers.kakao.com/\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(45, 80, 255);\">카카오디벨로퍼스</a>(Kakao Developers, 이하 개발자 웹사이트)는 카카오 개발자 웹사이트입니다. 카카오 API는 개발자 웹사이트에 등록된 각각의 애플리케이션 정보 기반으로 동작하므로, 카카오 API를 사용하려면 개발자 웹사이트에 앱을 등록해야 합니다.</p>','카카오디벨로퍼스',3,1),(11,'2023-05-25 09:05:20.888000','2023-05-25 10:10:25.325000','<h3>기능 소개</h3><p>카카오스토리는 사용자가 자신의 스토리를 친구 관계인 다른 사용자들과 공유하는 소셜 네트워크 서비스(Social Network Service)입니다. 스토리(Story)란 글, 이미지, 링크로 구성된 포스트를 의미합니다. 카카오스토리 API는 카카오스토리에 새로운 게시물을 쓰거나 삭제하는 것은 물론, 사용자의 스토리를 불러오는 등 카카오스토리와 연계된 서비스를 구현할 때 유용한 기능들을 제공합니다.</p><p>카카오스토리 API는 <span style=\"color: rgb(34, 34, 34);\">사용자가 카카오 로그인한 상태</span>에서만 호출할 수 있고, 해당 사용자가 카카오스토리를 이용하고 있어야만 각 기능을 사용할 수 있습니다. 때문에 현재 로그인한 사용자가 카카오스토리를 이용하고 있는지 확인하는 기능을 제공합니다.</p><p>카카오스토리 API가 제공하는 기능을 정리하면 다음과 같습니다.</p>','카카오스토리',1,1);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 20:07:36
