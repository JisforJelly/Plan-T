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
-- Table structure for table `hot_place`
--

DROP TABLE IF EXISTS `hot_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hot_place` (
  `hot_place_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modifyed_at` datetime(6) DEFAULT NULL,
  `connent` varchar(4096) DEFAULT NULL,
  `content_type` varchar(255) DEFAULT NULL,
  `hash_tag` varchar(255) DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`hot_place_id`),
  KEY `FKaj03pvw9l55jb4k69f3q7u5tl` (`user_id`),
  CONSTRAINT `FKaj03pvw9l55jb4k69f3q7u5tl` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hot_place`
--

LOCK TABLES `hot_place` WRITE;
/*!40000 ALTER TABLE `hot_place` DISABLE KEYS */;
INSERT INTO `hot_place` VALUES (1,'2023-05-25 08:46:41.733000','2023-05-25 08:46:41.733000','꿈꾸고(Dream), 만들고(Design), 누린다(Play)는 뜻을 가지고 있는 DDP(동대문 디자인 플라자)는 서울특별시 동대문에 위치한 복합문화공간이다. 이라크 태생의 영국의 건축가 자하 하디드와 삼우종합건축사사무소가 디자인하였다. 2014년 3월 21일 개관한 이래 굵직한 전시와 패션쇼, 신제품 발표회, 포럼과 콘퍼런스를 개최해오고 있다. 알림터, 배움터, 살림터, DDP디자인뮤지엄, 디자인장터와 어울림광장, 동대문역사문화공원으로 나뉘었다. 알림터에서는 서울패션위크, MAMA(Mnet Asian Music Awards), 반클리프 아펠전, BMW 신차 발표회 등을 개최했고, 다목적 연회 공간인 국제회의장 시설을 갖추었다. BTS 주제전과 서울도시건축비엔날레 등이 열렸던 배움터, 누구나 디자이너가 되는 곳을 주제로 2019년 개장한 시민 라운지 ‘D-숲’과 DDP 관련 기념품을 소개하는 DDP디자인스토어를 아우르는 살림터까지, 규모에 걸맞은 내실로 관람객의 흥미를 북돋운다. 식당과 베이커리, 카페가 들어선 디자인장터 역시 감성 충전을 기대하는 가족이나 연인을 만족시키고도 남을 산책 코스다.','문화시설','#동대문',37.5841522714614,'서울 동대문구 겸재로 16',127.069923144158,'DDP동대문디자인플라자',1),(3,'2023-05-25 08:47:41.500000','2023-05-25 08:47:41.500000','N서울타워(영어: N Seoul Tower)는 대한민국 서울특별시 용산구 용산동2가 남산 정상 부근에 위치한 전파 송출 및 관광용 타워이다. 1969년에 착공하여 1975년 7월 30일 완공되었다. 높이는 236.7m, 해발 479.7m이다. 수도권의 지상파 방송사들이 이 타워를 이용하여 전파를 송출한다. 전망대에서 서울 시내 전역을 내려다볼 수 있으며, 맑은 날씨에 찾는 관광지로 잘 알려져 있다. 남산에 있어서 보통 남산타워라고 널리 부르고, 서울에 있어서 서울타워라고 부르기도 하지만, 행정안전부에 등록된 정식 명칭은 YTN서울타워이다.','관광지','#서울타워',37.4869603647052,'서울 강남구 강남대로 262',127.033445189741,'N서울타워',1),(4,'2023-05-25 08:48:39.951000','2023-05-25 08:48:39.951000','경복궁(景福宮, 영어: Gyeongbokgung Palace)은 서울특별시 종로구 사직로에 위치한 조선 왕조의 법궁(法宮, 정궁)이다. 1395년 창건되어 1592년 임진왜란으로 전소되었고, 1868년 흥선대원군의 주도로 중건되었다.\n\n《주례》 〈고공기〉에 입각하여 건축되었다. 3문 3조로 구성되었는데 각각 외조, 내조, 연조이다. 내조는 근정전을 중심으로 하는데, 궁 밖에서 근정전까지 바깥부터 광화문, 흥례문, 근정문이다.\n\n\'경복(景福)\'은 시경에 나오는 말로 왕과 그 자손, 온 백성들이 태평성대의 큰 복을 누리기를 축원한다는 의미다. 풍수지리적으로도 백악산을 뒤로 하고 좌우에는 낙산과 인왕산으로 둘러싸여 있어 길지의 요건을 갖추고 있다. 1592년, 임진왜란으로 인해 불탄 이후 법궁의 역할을 창덕궁에 넘겨주었다가 1865년(고종 2년)에 흥선대원군의 명으로 중건되었다.\n\n1910년 한일 병합 후 일제강점기에는 1915년 조선물산공진회 개최와 1926년 조선총독부 건설로 많은 전각들이 철거 혹은 훼손되었으며, 그 자리에는 박물관과 잔디밭을 비롯한 정원이 들어섰다. 이러한 모습은 1945년 해방 후에도 이어졌으며 6·25 전쟁을 거치면서 일부 전각이 추가로 소실되었다.\n\n1968년 광화문 복원을 시작으로 경복궁의 본모습을 되찾기 위한 각계의 관심과 노력이 증대되어, 1980년대 말부터 본격적인 복원사업 계획이 시작되었다. 1995년 조선총독부 청사 철거, 2001년 흥례문 권역 복원, 2010년 광화문 목조 복원, 2023년 광화문 월대 복원을 비롯하여 각 권역별 주요 전각들을 오는 2045년까지 복원시킬 계획이다.','문화시설','#경복궁',37.5758613027702,'서울 종로구 사직로 지하 130',126.973062710191,'경복궁',1),(5,'2023-05-25 08:49:39.100000','2023-05-25 08:49:39.100000','국립중앙박물관(國立中央博物館, 영어: National Museum of Korea)은 대한민국 서울특별시 용산구 서빙고로 137번지에 위치한 국립박물관이다. 1972년 7월 19일 발족하였으며, 현재의 박물관은 2005년 10월 28일 신축 개관한 것이다.\n\n본관은 동관과 서관으로 나누어져 있으며, 지하 1층·지상 6층으로 이루어져 있다. 전시 공간은 3개 층으로 나누어져 있다. 길이 404미터, 최고 높이 43.08미터의 건물로 세계에서 13번째로 큰 박물관이다.\n\n3층 규모에 총 6개의 관과 50개의 실로 구성되어 있다. 12,044점의 유물을 전시하여 제공하고 있으며 전시물은 외부전시일정 및 유물의 보존 상태를 위하여 주기적으로 교체하고 있다. 상설전시장 및 어린이박물관은 무료로 관람할 수 있다. 전시장은 국립중앙박물관 앱 또는 PMP로 전시안내서비스를 이용하여 관람에 도움을 받을 수 있다. 또한 영어, 일본어, 중국어, 프랑스어 등 외국인을 위한 전시해설도 갖춰져 있다.\n\n개관시간은 항상 오전 10시이며 월, 화, 목, 금요일에는 18시까지 관람할 수 있고 수, 토요일은 21시 까지이다. 일요일 및 공휴일에는 19시까지이다.[3] 매년 1월 1일과 설날 당일, 추석 당일에는 휴관한다.','문화시설','#국립중앙박물관',37.5247022357875,'서울 용산구 서빙고로 137',126.977740246542,'국립중앙박물관',1),(6,'2023-05-25 08:50:05.602000','2023-05-25 08:50:05.602000','명동(明洞)은 대한민국 서울특별시 중구에 있는 번화가이자 지역 이름이다. 명동1가와 명동2가를 합친 면적은 0.91 km2이다. 명동1·2가, 충무로1·2가, 을지로1·2가 등을 포함한다.\n\n명동은 대한민국 최대 번화가로, 뉴욕 타임스퀘어, 런던 피카딜리, 파리 샹젤리제, 로마 포로로마노, 베이징 왕푸칭 거리, 타이베이 서문정, 도쿄 긴자 등에 해당된다. 주한 중국 대사관, 명동성당, 가톨릭회관 건물을 제외한 거의 대부분이 쇼핑 & 문화 중심지이다. 계절을 불문하고 다양한 국적, 연령대의 관광객이 방문하는 서울의 명소이며, 대한민국 도심 중 유동인구가 가장 많은 지역이기도 하다. 또한 명동은 대한민국에서 땅값이 가장 비싼 지역으로 꼽힌다.\n\n조선 시대에 명례동(明禮洞) 또는 명례방, \'남촌\'이라고 불리며 주로 가난한 선비들이 모여사는 주택가였으나 , 일제강점기 때 명치정(明治町 메이지초)이라는 이름의 상업 중심지로 발전한 것이 시작이다. 명치정1정목(明治町一丁目 메이지초잇초메), 명치정2정목(明治町二丁目 메이지초니초메)은 1943년 6월 10일에 설치된 중구의 관할로 편성되었다.\n\n1970년대 이전에는 증권 회사가 모였던 대한민국 경제의 중심지였다. 지금도 행정동 명동 산하의 법정동인 다동과 삼각동 일대에는 은행들이 꽤 밀집해 있는, 서울 도심의 금융 중심지이기도 하다.\n\n중화 요리점이 많고 중국인 학교도 있는 등 화교들이 많이 모인 지역이지만, 차이나타운이라고 하기에는 무리가 있다. 지금의 명동은 서울의 주요 관광지 중 하나다. 명동은 일본인, 중국인을 비롯한 세계 각국의 외국인 관광객들이 많이 찾는 지역으로 알려져 있다.','쇼핑','#명동',37.5637786522174,'서울 중구 명동길 1-4',126.982461609052,'명동',1),(7,'2023-05-25 08:50:55.204000','2023-05-25 08:50:55.204000','북촌 한옥마을은 서울특별시 종로구의 가회동과 삼청동 내에 위치한 한옥마을이다. 지리상으로 경복궁과 창덕궁, 종묘의 사이에 자리잡고 있다. 조선 왕조의 두 궁궐 사이에 위치한 이 지역은 예로부터 청계천과 종로의 윗동네라는 의미로 ‘북촌’이라 불리었으며, 현재의 가회동, 삼청동, 원서동, 재동, 계동 일대에 해당한다. 많은 사적과 문화재, 민속자료가 있어 도심 속의 박물관이라 불리기도 한다. 경복궁과 마주하고 있는 삼청동길 주변에는 많은 갤러리가 늘어서 있고, 화동길을 비롯한 곳에서는 각종 먹거리와 특색있는 카페를 접할 수 있다. 또한 원서동에는 전통 기능의 보유자 및 예술인들이 모여 살고 있다.\n\n1990년대 이후 다세대가구 주택이 급속하게 들어서며 많은 수의 한옥이 사라졌지만, 현재까지 양호한 상태의 한옥이 군집을 이룬 채 일부 지역에 남아 있다. 그 중에서도 가회동 11번지, 31번지, 33번지 일대는 대표적인 한옥 밀집 지역이다.\n\n북촌은 북악과 응봉을 잇는 산줄기의 남쪽 경사면에 위치하고 있어 지리적 환경이 뛰어났기 때문에 예로부터 권문세족의 주거지로 자리매김해 왔다. 1906년 호적자료상의 북촌의 인구 10,241명(1,932호)을 호주의 신분에 따라 구분했을 때 양반과 관료가 43.6%를 차지한다. 이를 통해 근대에도 상류층이 많이 거주했음을 알 수 있다.','레포츠','#북촌한옥마을',37.5770357156895,'서울 종로구 북촌로 1',126.985798983513,'북촌한옥마을',1),(8,'2023-05-25 08:52:35.621000','2023-05-25 08:52:35.621000','스타필드 코엑스몰(Starfield COEX Mall)은 대한민국 서울특별시 강남구 삼성동에 위치한 아시아 최대의 지하쇼핑공간으로 멀티플렉스 영화관인 메가박스와 테마형 수족관을 포함, 쇼핑에서 외식, 엔터테인먼트에서 문화체험까지 고루 아우르는 복합문화공간이다.\n\n스타필드 코엑스몰이 위치해 있는 한국종합무역센터의 전체 연면적은 1,212,507m2이며 이 중 무역센터 상업 시설 총 면적은 463,994m2이며 총 점포수는 약 1150여개에 달한다.\n\n이중 코엑스몰이라고 할 수 있는 매장 면적은 약 182,675m3(임대면적 기준)이며 임대점포 수는 약 460여개이다. 주요 점포로는 메가박스, 코엑스 아쿠아리움, 영풍문고, 자라, 유니클로, 스타벅스 등이 있다.\n\n스타필드 코엑스몰은 2000년 5월 3일에 전면 개장하였고, 한국무역협회는 5월 3일 11시 개관식을 가진 뒤 일반에 개방하였다. 뒤이어, 코엑스몰 메가박스 시네플렉스가 당시 동양 최대의 규모인 4천 336석 규모로 2000년 5월 13일 개장하였다.\n\n\'세상에서 가장 큰 아이\', \'쓱데이 페스티벌\', \'LG U+ 3쿠션 마스터스 대회\'등을 개최한 이력이 있다.\n\n2013년 3월부터는 전시장과 더불어 전체 리노베이션에 착수하여 2014년 11월 그랜드 오픈했다.\n\n2016년 12월부터 신세계그룹이 10년간 운영권을 따내어 현재의 이름으로 변경되었다.\n\n2020년 코로나 여파로 힘들어진 패션 브랜드들과 함께 \'코로나19 상생 특별전\'을 열어 패션 브랜드들의 어려움 해소에 보탬이 되었다.','쇼핑','#스타필드코엑스몰',37.5128320848839,'서울 강남구 봉은사로 524',127.057250899584,'스타필드코엑스몰',1),(9,'2023-05-25 08:53:07.888000','2023-05-25 08:53:07.888000','인사동(仁寺洞, Insa-dong)은 대한민국 서울특별시 종로구에 있는 법정동이다. 행정동인 종로동 산하에 있으며, 인사동네거리를 지나 안국동 로터리로부터 종로2가의 탑골공원까지의 구역을 말한다. 주요 거리는 조선시대 형성된 길이 0.7km의, 너비 12m의 인사동길이 있다. 현재의 인사동길은 건축가 김진애의 설계로 2000년에 재조성된 것으로 돌걸상과 돌의자, \'남인사 물동이\', \'북인사 물길\' 등이 설치된 돌길 형태로 단장되었다. 서울의 주요 도심로와 연결돼 있고, 청계천, 경복궁 등 주요 관광명소와도 가깝다.\n\n인사동은 조선 시대부터 근 600년 동안 서울의 심장부에 있다. 일제 강점기에는 주로 일본인을 대상으로 고미술품을 거래하는 시장이 형성되었고, 1970년대부터 화랑, 필방, 표구사 등이 들어서기 시작하였다. 인사동은 외국인에게 인기 있는 쇼핑 명소이자 매리의 골목(Mary\'s Alley)이라고도 알려져 있다.\n\n인사동에는 골동품 상점이 많이 모여 있다. 가격은 주로 10,000원부터 수백만 원에 이르기까지 다양하다. 거의 모든 상점은 오래된 책이나 사진, 서예, 기념품, 사진, 도자기, 목제품, 보석 등을 판매한다. 도자기들은 신라 시대 질그릇부터 조선 시대 백자까지 다양하게 존재한다. 인사동은 일본, 중국, 프랑스, 미국 등 많은 나라에서 관광객들이 방문하는 곳이다. 특히, 봄과 가을에 인사동을 방문하는 관광객들의 숫자가 절정을 이룬다. 특이하고 예쁜 물건들을 파는 3-4층 규모의 인사동 쌈지길도 이곳에 있다. 경복궁에서 나와서 쭉 오다가 광화문 사거리에서 왼쪽으로 돌아서 가면 인사동이 보인다.','여행코스','#인사동',37.5705611277251,'서울 종로구 삼일대로 401-8',126.987024769656,'인사동',1),(10,'2023-05-25 08:53:51.188000','2023-05-25 08:53:51.188000','청계천(淸溪川)은 대한민국 서울특별시 내부에 있는 지방하천으로, 한강 수계에 속하며 중랑천의 지류이다. 최장 발원지는 종로구 청운동에 위치한 ‘백운동 계곡’이며, 남으로 흐르다가 청계광장 부근의 지하에서 삼청동천을 합치며 몸집을 키운다. 이곳에서 방향을 동쪽으로 틀어 서울의 전통적인 도심지를 가로지르다가, 한양대학교 서울캠퍼스 옆에서 중랑천으로 흘러든다.\n\n발원지에서부터 잰 본류의 길이는 10.92 km, 유역 면적은 50.96 km2이나, 백운동 계곡 언저리를 제외한 상류 2.50 km 구간은 일제강점기 이래 전면 복개되어 타 용도로 전용되는 등 사실상 하천의 기능을 상실하였다. 현재는 서울 이곳저곳에서 취수한 물을 중류의 청계광장에서 하루에 40,000 세제곱미터씩 인공적으로 방류하는 형태로 하천을 유지하고 있다.\n\n발원지의 계곡은 서울특별시 기념물로 지정되었으며, 청계광장에서 중랑천 합수머리까지의 중·하류 8.12 km 구간은 서울시설공단의 관리 하에 공원으로 쓰인다.','여행코스','#청계천',37.5720071392864,'서울 동대문구 청계천로 417',127.023906598956,'청계천',1),(11,'2023-05-25 08:54:25.292000','2023-05-25 08:54:25.292000','하늘공원은 월드컵공원내에 속해 있는 공원으로 평화공원, 난지천공원, 난지한강공원, 노을공원과 함께 월드컵 경기장 주변의 5대 공원을 이룬다. 생태환경을 복원할 목적으로 조성되었으며, 자연에너지를 사용하여 자체 시설을 운영하고 있다. 억새 식재지, 혼생초지, 암석원, 해바라기 식재지, 전망휴게소, 풍력발전기 등으로 구성되어 있다. 월드컵공원 중 하늘과 가장 가까운 곳에 있으며, 쓰레기 매립지의 척박한 땅에서 자연으로 복원되는 변화를 보여주는 공원이다. 가을의 은빛 억새꽃이 장관을 이루며 북한산, 한강 등 서울의 풍경을 한눈에 볼 수 있다. 또한 노을과 야경이 아름답기로 알려진 명소이기도 하다.','여행코스','#하늘공원',37.4872395213832,'서울 구로구 벚꽃로 370',126.880809818246,'하늘공원',1);
/*!40000 ALTER TABLE `hot_place` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 20:07:04
