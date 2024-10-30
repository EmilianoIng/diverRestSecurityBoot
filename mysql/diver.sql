-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: diver
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `dettagli_anagrafici`
--

DROP TABLE IF EXISTS `dettagli_anagrafici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dettagli_anagrafici` (
  `username` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `cognome` varchar(45) NOT NULL,
  `data_nascita` varchar(60) DEFAULT NULL,
  `luogo_nascita` varchar(45) NOT NULL,
  `residenza` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `username_idx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dettagli_anagrafici`
--

LOCK TABLES `dettagli_anagrafici` WRITE;
/*!40000 ALTER TABLE `dettagli_anagrafici` DISABLE KEYS */;
INSERT INTO `dettagli_anagrafici` VALUES ('emilianodicarlo83@gmail.com','Giulio','Riva','1970-04-23 00:00:00','Roma','Roma','2022-11-28 18:46:27','2022-11-28 18:46:27'),('triviato83@gmail.com','Emiliano','Di Carlo','1983-08-22 00:00:00','Tagliacozzo','Roma',NULL,'2022-11-29 08:40:05');
/*!40000 ALTER TABLE `dettagli_anagrafici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `esperienze`
--

DROP TABLE IF EXISTS `esperienze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `esperienze` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `id_viaggio` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `descrizione` varchar(800) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_viaggio_idx` (`id_viaggio`),
  CONSTRAINT `id_viaggio` FOREIGN KEY (`id_viaggio`) REFERENCES `viaggi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `esperienze`
--

LOCK TABLES `esperienze` WRITE;
/*!40000 ALTER TABLE `esperienze` DISABLE KEYS */;
INSERT INTO `esperienze` VALUES (1,'Cala Dell\'Acqua',1,'2022-11-30 11:27:52','2023-03-30 17:39:38','cala dell\'acqua descrizione update','2022-07-10'),(2,'Stifone Fiume Nera',NULL,'2022-11-30 11:27:52','2023-03-31 18:33:52','Lungo la riva sinistra del fiume Nera, in Umbria, sorge un paesino dove un tempo i romani costruivano le loro navi. Si chiama Stifone ed è una piccola meraviglia che potrete trovare lungo l\'itinerario delle Gole del Nera.\\n          Stifone, dove vivono circa 40 persone, è una frazione del comune di Narni, in provincia di Terni.\\n          In questo borgo, il fiume crea un\'ansa e una misteriosa sorgente subacquea riversa nel suo alveo migliaia di litri d\'acqua.\\n          Nessuno sa bene dove si trovino queste sorgenti. L\'acqua ha una particolare concentrazione di minerali che gli fornisce un colore turchese intenso. Ecco le foto che ti faranno venire in voglia di visitarlo.','2021-09-12'),(3,'Best 2021',1,'2022-11-30 11:27:52','2023-03-15 19:40:40','Testa','2021-07-04'),(4,'Palmarola',2,'2022-11-30 11:27:52','2022-11-30 11:27:52','descrizione Palmarola','2022-07-04'),(20,'Sant\'Angelo',19,'2023-02-17 14:28:35','2023-03-09 18:49:41','Sant\'Angelo descrizion','2022-08-11'),(24,'Carta Romana',19,'2023-02-20 16:30:30','2023-02-20 16:30:30','Carta Romana','2022-08-12'),(41,'Lago di Albano',NULL,'2023-03-11 12:47:45','2023-03-31 17:51:17','Lago Di Albano desc','2023-02-14'),(42,'Tulip Resort - Marsa Alam',35,'2023-03-15 12:17:42','2023-03-15 19:37:13','Tulip Resort Marsa Alam testa','2021-07-18'),(43,'Tropea',36,'2023-03-30 17:42:50','2023-03-31 18:35:05','Tropea descrizione','2022-08-23'),(45,'Castello',NULL,'2023-04-03 16:34:36','2023-04-03 16:34:36','Castello di Santa Severa','2023-03-26');
/*!40000 ALTER TABLE `esperienze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gite`
--

DROP TABLE IF EXISTS `gite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gite` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `id_experience` int DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `demo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_experience_idx` (`id_experience`),
  CONSTRAINT `id_experience` FOREIGN KEY (`id_experience`) REFERENCES `esperienze` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_cs_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gite`
--

LOCK TABLES `gite` WRITE;
/*!40000 ALTER TABLE `gite` DISABLE KEYS */;
INSERT INTO `gite` VALUES (1,'Stifone',2,'2022-11-30 11:27:52','2023-03-23 13:25:28',0),(8,'Castel Gandolfo',41,'2023-03-11 12:47:45','2023-03-23 15:30:23',0),(9,'Santa Severa',45,'2023-04-03 16:34:36','2023-04-03 16:34:36',0);
/*!40000 ALTER TABLE `gite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagini`
--

DROP TABLE IF EXISTS `immagini`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `immagini` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_esp` int DEFAULT NULL,
  `is_vertical` tinyint DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `path_image` varchar(200) DEFAULT NULL,
  `main_image` tinyint DEFAULT NULL,
  `experience_image` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_esp_idx` (`id_esp`),
  CONSTRAINT `id_esp` FOREIGN KEY (`id_esp`) REFERENCES `esperienze` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1058 DEFAULT CHARSET=big5;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagini`
--

LOCK TABLES `immagini` WRITE;
/*!40000 ALTER TABLE `immagini` DISABLE KEYS */;
INSERT INTO `immagini` VALUES (1,NULL,1,'2022-11-30 11:27:52','2022-11-30 11:27:52','~/assets/images/newTravel.jpg',1,0),(2,4,0,'2022-11-30 11:27:52','2023-03-23 19:10:54','_communityImage_1679595050874_image%3A19023.jpeg',1,1),(3,3,0,'2022-11-30 11:27:52','2023-03-30 16:55:51','~/assets/images/Best2021.png',1,1),(6,1,1,'2022-11-30 11:27:52','2023-03-30 16:55:51','_communityImage_1679669690371_image%3A19067.jpeg',0,1),(18,20,0,'2023-02-17 14:28:35','2023-03-09 12:40:09','_communityImage_1676640487540_image%3A49528.jpeg',1,1),(23,24,0,'2023-02-20 16:30:30','2023-03-09 12:40:09','_communityImage_1676906901131_image%3A49629.jpeg',0,1),(1016,20,0,'2023-03-09 15:54:03','2023-03-09 15:54:03','_communityImage_1678373621928_image%3A49465.jpeg',0,0),(1017,2,0,'2022-11-30 11:27:52','2023-03-23 18:59:05','_communityImage_1679594342486_image%3A19893.jpeg',0,1),(1024,41,0,'2023-03-11 12:47:45','2023-03-24 15:50:40','_communityImage_1679669435535_image%3A59372.jpeg',0,1),(1029,42,0,'2023-03-15 12:17:42','2023-03-15 12:17:42','_communityImage_1678879022615_image%3A48168.jpeg',1,1),(1037,41,0,'2023-03-17 16:16:29','2023-03-17 16:16:29','_communityImage_1679066170401_image%3A59389.jpeg',0,0),(1039,20,0,'2023-03-17 16:58:15','2023-03-17 16:58:15','_communityImage_1679068694518_image%3A49530.jpeg',0,0),(1041,42,0,'2023-03-24 18:47:18','2023-03-24 18:47:18','_communityImage_1679680033400_image%3A48166.jpeg',0,0),(1046,24,1,'2023-03-24 19:47:05','2023-03-24 19:47:05','_communityImage_1679683620181_image%3A49648.jpeg',0,0),(1047,2,1,'2023-03-24 19:48:40','2023-03-24 19:48:40','_communityImage_1679683714772_image%3A19876.jpeg',0,0),(1048,41,0,'2023-03-24 19:50:40','2023-03-24 19:50:40','_communityImage_1679683823683_image%3A61880.jpeg',0,0),(1049,43,0,'2023-03-30 17:42:50','2023-04-03 16:28:38','_communityImage_1680532114172_image%3A50346.jpeg',1,1),(1050,43,0,'2023-03-30 18:19:19','2023-03-30 18:19:19','_communityImage_1680193154338_image%3A50354.jpeg',0,0),(1054,45,0,'2023-04-03 16:34:36','2023-04-03 16:34:36','_communityImage_1680532434179_image%3A63933.jpeg',0,1),(1056,45,0,'2023-04-03 17:11:55','2023-04-03 17:11:55','_communityImage_1680534711978_image%3A63940.jpeg',0,0),(1057,43,0,'2023-04-03 17:13:05','2023-04-03 17:13:05','_communityImage_1680534780547_image%3A50341.jpeg',0,0);
/*!40000 ALTER TABLE `immagini` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'User'),(2,'Admin');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(60) NOT NULL,
  `password` varchar(100) NOT NULL,
  `enabled` tinyint NOT NULL DEFAULT '0',
  `username_dett_anag` varchar(60) DEFAULT NULL,
  `is_deleted` tinyint DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_dettAnag_UNIQUE` (`username_dett_anag`),
  CONSTRAINT `username_dettAnag` FOREIGN KEY (`username_dett_anag`) REFERENCES `dettagli_anagrafici` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (53,'triviato83@gmail.com','$2a$10$ty.VHvUEuEAQcF.KaIwhyulXyXkwW8bfuBw3AucUmmxq2BTGrv81G',1,NULL,0,'2022-12-01 15:22:59','2022-11-11 17:55:50'),(67,'dicarlo1983emiliano@gmail.com','$2a$10$w1wM9X0Ih9GWCv6bFjD.xOXdSJvWnLX1tLrVIrGLoNYZEP8GzRTzm',0,NULL,1,NULL,NULL),(69,'dicarlo1983emiliano@gmail.com','$2a$10$2pRQ7R1Y5S8YVCKif3ABfOyKOFzZADw6Mjrjn/hdCTlmuVlzozKNO',1,NULL,0,'2022-11-29 19:04:47',NULL),(70,'emilianodicarlo83@gmail.com','$2a$10$s19qeS.vp/3ZWwGp2w27HOBvIdaGc9uiNHHOBP8zsybGE.a8a3CVO',1,NULL,0,'2022-11-28 18:33:15','2022-11-28 18:33:15'),(72,'jacopodicarlo25062012@gmail.com','$2a$10$CkWvj7xkpe9sQ4oH.0QhAeNVkHKj7jJPJIIzGLlLAl83SFLyW/s5m',1,NULL,0,'2022-11-28 19:07:02','2022-11-28 19:06:49');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id_idx` (`role_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (53,2,'2022-11-14 13:06:49','2022-11-14 13:06:49'),(69,1,'2022-11-14 17:06:49','2022-11-14 17:06:49'),(70,1,'2022-11-28 19:00:49','2022-11-28 19:00:49'),(72,1,'2022-11-28 19:06:49','2022-11-28 19:06:49');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationtoken`
--

DROP TABLE IF EXISTS `verificationtoken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verificationtoken` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(100) NOT NULL,
  `expiry_date` datetime NOT NULL,
  `id_user` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `_idx` (`id_user`),
  CONSTRAINT `` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationtoken`
--

LOCK TABLES `verificationtoken` WRITE;
/*!40000 ALTER TABLE `verificationtoken` DISABLE KEYS */;
INSERT INTO `verificationtoken` VALUES (34,'d7c316d8-a228-4d8f-9374-78684cbe2ede','2022-10-01 16:20:37',53),(47,'6b97ccf7-a7c8-4540-92f2-d0736c349fa0','2022-11-29 17:52:48',69),(48,'6b2f2956-86a9-4fe4-89a3-5ac7ede8d1bd','2022-11-29 18:33:15',70),(50,'a10aa580-5f90-4003-ad2d-f866467d7ce6','2022-11-29 19:06:49',72);
/*!40000 ALTER TABLE `verificationtoken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaggi`
--

DROP TABLE IF EXISTS `viaggi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaggi` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `demo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaggi`
--

LOCK TABLES `viaggi` WRITE;
/*!40000 ALTER TABLE `viaggi` DISABLE KEYS */;
INSERT INTO `viaggi` VALUES (1,'Ponza','2022-11-30 11:27:52','2023-03-24 15:54:54',0),(2,'Palmarola','2022-11-30 11:27:52','2023-03-23 15:09:13',0),(19,'Ischia','2023-02-17 14:28:35','2023-03-03 19:03:05',0),(35,'Egitto','2023-03-15 12:17:42','2023-03-15 12:17:42',0),(36,'Calabria','2023-03-30 17:42:50','2023-03-30 17:42:50',0);
/*!40000 ALTER TABLE `viaggi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `youtube_id` varchar(45) NOT NULL,
  `last_update` datetime DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `id_esperienza` int NOT NULL,
  `thumbnail` varchar(60) DEFAULT NULL,
  `descrizione` varchar(800) DEFAULT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `duration` int DEFAULT '0',
  `demo` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_esperienza_idx` (`id_esperienza`),
  CONSTRAINT `id_esperienza` FOREIGN KEY (`id_esperienza`) REFERENCES `esperienze` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,'katQzPZBHwU','2022-11-30 11:27:52','2022-11-30 11:27:52',1,'~/assets/images/relitto.png','All\'isola di Ponza, in località Le Forna si trova Cala dell\'Acqua. Prende questo nome dalla sorgente d\'acqua dolce che alimentava l\'acquedotto romano, capolavoro di ingegneria idraulica. Attraverso dei cunicoli l\'acqua percorreva buona parte dell\'isola. L\'acqua veniva decantata dalle rocce di bentonite, minerale presente in questa parte dell\'isola. Oggi a pochi metri di profondità in questa Cala c\'è il relitto di una nave il Kastell Luanda, naufragata nel dicembre del 1974.','Relitto Kastell Luanda',NULL,1),(2,'EpDIvbGZy_U','2022-11-30 11:27:52','2022-11-30 11:27:52',2,'~/assets/images/stifone.png','Lungo la riva sinistra del fiume Nera, in Umbria, sorge un paesino dove un tempo i romani costruivano le loro navi. Si chiama Stifone ed è una piccola meraviglia che potrete trovare lungo l\'itinerario delle Gole del Nera.Stifone, dove vivono circa 40 persone, è una frazione del comune di Narni, in provincia di Terni.In questo borgo, il fiume crea un\'ansa e una misteriosa sorgente subacquea riversa nel suo alveo migliaia di litri d\'acqua. Nessuno sa bene dove si trovino queste sorgenti. L\'acqua ha una particolare concentrazione di minerali che gli fornisce un colore turchese intenso. Ecco le foto che ti faranno venire in voglia di visitarlo.','Diving Fiume Nera',NULL,1),(3,'F_gH3YOw9D8','2022-11-30 11:27:52','2022-11-30 11:27:52',3,'~/assets/images/Best2021.png','L\'isola di Ponza è sicuramente una delle isole pontine più rinomate della Provincia di Latina. Le sue cale, l’acqua cristallina e la sua forma a mezzaluna le hanno permesso di renderla famosa in tutto il mediterraneo. Inoltre la presenza di siti archeologici risalenti ad epoca romana la rendono una meta alternativa non solo per gli amanti delle spiagge. L’isola si divide in tre località principali : Ponza (zona Porto), Campo Inglese e Le Forna. Ponza è raggiungibile dai porti di Formia e Terracina,tutto l’anno e in estate anche da Anzio e San Felice Circeo.','Best 2021',NULL,1),(27,'LWHFLj7yk_Q','2023-02-09 17:14:45','2023-02-09 13:18:41',1,'_communityImage_1675959278483_image%3A39536.jpeg','Grotta del parroco, grotta azzurra e altre meravigliose grotte visitabili dal tour con la barca Beluga di Silverio','Grotte',NULL,0),(30,'_2bWEEmc-os','2023-03-17 18:06:17','2023-03-17 17:32:16',20,'_communityImage_1679070554793_image%3A63491.jpeg','Splendida escursione in freediving, partendo dalla località di Carta Romana si possono ammirare i resti di una villa Romana a circa 7 metri di profondità \nProseguendo in direzione del castello i fondali si fanno via via piu\' sabbiosi,\nIn questo tratto di mare si possono facilmente incontrare numerose specie di pesci come cefali gli immancabili lattarini fino ad arrivare alle numerosissime castagnole.\nLa nostra escursione si è conclusa a ridosso del castello Aragonese dove è possibile godere di una fantastica Jacuzzi naturale.\nL\'origine vulcanica dell\'isola infatti, qui si manifesta con emossioni gassose sottomarine che danno questo meraviglioso effetto.','Diving Carta Romana - Castello Aragonese',NULL,0),(34,'qOWlXMewqMw','2023-04-03 16:06:14','2023-04-03 16:01:57',42,'_communityImage_1680530769013_image%3A47779.jpeg','Descrizione Abud dabab','Abu Dabab',NULL,0);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-13 16:22:24
