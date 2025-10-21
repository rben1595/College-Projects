-- MySQL dump 10.13  Distrib 8.0.33, for macos13 (x86_64)
--
-- Host: 127.0.0.1    Database: blackjack
-- ------------------------------------------------------
-- Server version	5.7.39

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
-- Table structure for table `card_details`
--

DROP TABLE IF EXISTS `card_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card_details` (
  `card_details_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `card_holder_name` varchar(255) NOT NULL,
  `card_number` varchar(255) NOT NULL,
  `card_expiry_date` varchar(255) NOT NULL,
  `card_csv` varchar(255) NOT NULL,
  `card_type` varchar(255) NOT NULL,
  `store_details` varchar(255) NOT NULL,
  PRIMARY KEY (`card_details_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card_details`
--

LOCK TABLES `card_details` WRITE;
/*!40000 ALTER TABLE `card_details` DISABLE KEYS */;
INSERT INTO `card_details` VALUES (2,54,'Ben Ryan','1234564766789089','2024-04-27','089','Mastercard','true'),(3,54,'JO/zCyEzv9Yi28nmdfcPmw==','8TS6N6jZC25iJt5bK9ff8hiKm2XMLQvWJVnqauRIf+4=','LR3eaBOFCRO19wRN1vpQHA==','jEWzMTEC8Bo3cpHff5Ao0w==','LuinSC4S45w+/f1H3zIOEQ==','true');
/*!40000 ALTER TABLE `card_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `game_details`
--

DROP TABLE IF EXISTS `game_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_details` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `total_bet` varchar(255) NOT NULL,
  `total_won` varchar(255) NOT NULL,
  `total_lost` varchar(255) NOT NULL,
  `number_hands_won` varchar(255) NOT NULL,
  `game_start_time` datetime NOT NULL,
  `game_end_time` datetime NOT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_details`
--

LOCK TABLES `game_details` WRITE;
/*!40000 ALTER TABLE `game_details` DISABLE KEYS */;
INSERT INTO `game_details` VALUES (8,56,'rD4GsP4+of1iQMB+DpoRjg==','W/6zU7S8h+GOwNsV/mPFzw==','rD4GsP4+of1iQMB+DpoRjg==','1','2024-04-25 03:35:01','2024-04-25 03:35:16'),(9,56,'W/6zU7S8h+GOwNsV/mPFzw==','ysN/Y+jNEaFK8M1LtKyOqw==','W/6zU7S8h+GOwNsV/mPFzw==','0','2024-04-25 04:23:52','2024-04-25 04:24:03'),(10,56,'AVwWPRwiMKBJ7ySOh1aENQ==','ysN/Y+jNEaFK8M1LtKyOqw==','rD4GsP4+of1iQMB+DpoRjg==','0','2024-04-25 04:26:09','2024-04-25 04:26:22'),(11,56,'rD4GsP4+of1iQMB+DpoRjg==','ysN/Y+jNEaFK8M1LtKyOqw==','AS9r1MDcKLHQp6BxD+2OgQ==','0','2024-04-25 22:53:29','2024-04-25 22:53:49'),(12,56,'W/6zU7S8h+GOwNsV/mPFzw==','ysN/Y+jNEaFK8M1LtKyOqw==','W/6zU7S8h+GOwNsV/mPFzw==','0','2024-04-25 22:57:25','2024-04-25 22:57:31'),(13,56,'W/6zU7S8h+GOwNsV/mPFzw==','ysN/Y+jNEaFK8M1LtKyOqw==','W/6zU7S8h+GOwNsV/mPFzw==','0','2024-04-25 23:02:21','2024-04-25 23:02:28'),(14,56,'l0kebtfIyhw+ELT81APEFw==','ysN/Y+jNEaFK8M1LtKyOqw==','AwGyDqnyfN+r8Z3CMly3sQ==','0','2024-04-25 23:08:44','2024-04-25 23:09:01'),(15,56,'AVwWPRwiMKBJ7ySOh1aENQ==','ysN/Y+jNEaFK8M1LtKyOqw==','rD4GsP4+of1iQMB+DpoRjg==','0','2024-04-25 23:12:13','2024-04-25 23:12:28'),(16,56,'W/6zU7S8h+GOwNsV/mPFzw==','ysN/Y+jNEaFK8M1LtKyOqw==','ysN/Y+jNEaFK8M1LtKyOqw==','0','2024-04-28 21:51:59','2024-04-28 21:52:38');
/*!40000 ALTER TABLE `game_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leaderboard`
--

DROP TABLE IF EXISTS `leaderboard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leaderboard` (
  `leaderboard_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `amount_won` int(11) NOT NULL,
  `game_number` int(11) NOT NULL,
  `player_id` varchar(45) NOT NULL,
  `player_name` varchar(45) NOT NULL,
  PRIMARY KEY (`leaderboard_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leaderboard`
--

LOCK TABLES `leaderboard` WRITE;
/*!40000 ALTER TABLE `leaderboard` DISABLE KEYS */;
/*!40000 ALTER TABLE `leaderboard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login_details`
--

DROP TABLE IF EXISTS `login_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login_details` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_details`
--

LOCK TABLES `login_details` WRITE;
/*!40000 ALTER TABLE `login_details` DISABLE KEYS */;
INSERT INTO `login_details` VALUES (1,'rben1595@gmail.com','JYBVvhM7ZXq5vf1T2UnxcQ==','admin'),(10,'ben1000@gmail.com','KqqZ4YWSYGgtBRJoP9EjmQ==','user'),(11,'ben1000@gmail.com','KqqZ4YWSYGgtBRJoP9EjmQ==','user'),(12,'ben1000@gmail.com','KqqZ4YWSYGgtBRJoP9EjmQ==','user'),(13,'rben5@gmail.com','/Bv7xhSYm/H5fBzbjvUeIw==','user'),(14,'jimMCc@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(15,'jimjo@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(16,'nayrneb@gmail.com','eWMSS2W3SQIBjYwJDyxQOQ==','user'),(17,'kilduff123@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(18,'rben1500@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(19,'rben1505@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(20,'rben4444@gmail.com','Csc7cDldnTsBS4nYUCG/qw==','user'),(21,'rben1111@gmail.com','JYBVvhM7ZXq5vf1T2UnxcQ==','user'),(24,'rben5555@gmail.com','JYBVvhM7ZXq5vf1T2UnxcQ==','user');
/*!40000 ALTER TABLE `login_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_details`
--

DROP TABLE IF EXISTS `payment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_details` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `payment_amount` int(11) NOT NULL,
  `payment_date` datetime NOT NULL,
  `payment_type` varchar(45) NOT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_details`
--

LOCK TABLES `payment_details` WRITE;
/*!40000 ALTER TABLE `payment_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchase_history` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `order_total` int(11) NOT NULL,
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_history`
--

LOCK TABLES `purchase_history` WRITE;
/*!40000 ALTER TABLE `purchase_history` DISABLE KEYS */;
INSERT INTO `purchase_history` VALUES (1,54,100),(2,54,100),(3,54,100),(4,54,100),(5,54,100),(6,54,100),(7,54,100);
/*!40000 ALTER TABLE `purchase_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchased_items`
--

DROP TABLE IF EXISTS `purchased_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchased_items` (
  `purchase_item_id` int(11) NOT NULL AUTO_INCREMENT,
  `purchase_history_id` int(11) NOT NULL,
  `chip_type` varchar(45) NOT NULL,
  `chip_price` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sub_total` int(11) NOT NULL,
  PRIMARY KEY (`purchase_item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchased_items`
--

LOCK TABLES `purchased_items` WRITE;
/*!40000 ALTER TABLE `purchased_items` DISABLE KEYS */;
INSERT INTO `purchased_items` VALUES (1,1,'€100 Poker Chips',100,1,100),(2,2,'€100 Poker Chips',100,1,100),(3,3,'€100 Poker Chips',100,1,100),(4,4,'€100 Poker Chips',100,1,100),(5,5,'€100 Poker Chips',100,1,100),(6,6,'€100 Poker Chips',100,1,100),(7,7,'€100 Poker Chips',100,1,100);
/*!40000 ALTER TABLE `purchased_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_key_info`
--

DROP TABLE IF EXISTS `security_key_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `security_key_info` (
  `security_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `salt` varchar(45) NOT NULL,
  `iv_parameter_spec` varchar(100) NOT NULL,
  PRIMARY KEY (`security_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_key_info`
--

LOCK TABLES `security_key_info` WRITE;
/*!40000 ALTER TABLE `security_key_info` DISABLE KEYS */;
INSERT INTO `security_key_info` VALUES (1,'hfujjvjj','iugigb','110,5,-21,70,-42,14,58,-94,18,96,2,-103,21,2,53,-25,');
/*!40000 ALTER TABLE `security_key_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `country` varchar(45) NOT NULL,
  `post_code` varchar(45) NOT NULL,
  `account_credits` varchar(45) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'ben','ryan','rben1595@gmail.com','123456789','tulla','clare','b78er09','2345'),(41,'NnQgmyvJHgWzpDC7vEn0/w==','An3h75WoFWTqvF5MJ73VJQ==','idSaxk3Y5rXmLmiTNNYpRFAXcyuqn/5E9OcSEjM5HJY=','4GRi3CRPHy3WdTorQ8zSMw==','eIJNi7fkOrOct1VYF+Qe3w==','LwKlv17b92HKK9AC07YdQQ==','wtJI4QeOL96S5OzjbdKMgA==','0'),(42,'gd80t1lsJ7hqZq9yQJ+t1g==','oWOnSICoMkldhPYihkf5qg==','YjBjFaweHSvJk6cg9saXDH/s5UoUfMk7y0PnmlVgJ08=','eRqnrvmGZIZwa+us+zdkrA==','47J4MUVRP63eYC4O0+erUQ==','Fs++2dVahKsCE79EWWNA1w==','GXLjGKJO98d/LLAchtINcg==','0'),(43,'e2HSgQvBaich+Pt+yCinlw==','rDxFsDofq5Fsh1z3x0VWKA==','m+2pnG4UYL3+pz/TyXYh18wLAnMDlxCiTvtyhYOW88k=','5qiednTgR9Dzdv3pDvn4NA==','gZ46c8ynM7Lxpq5Yhsjvqg==','nUlMUwJ8q/TvjssDsCH9PA==','O7P5S2epusalGM3FvDDRAw==','0'),(44,'JfgS3SOY/CG1t/Qjrp58eg==','dbVIGcfnM1lsqN/UtDTMEg==','DGgU3px3XK07N0lrA9wnYENNQWdgB8JBBThX8YTwdTs=','rKrRrREfo3WlE1ZnJgag3Q==','XwZIqymVbv5Cw8FnVuLUSA==','uc9gBfG4/lgITpe2VYUtEw==','xEJkt47eBbqSIkpSZRaLlw==','0'),(45,'9FSeG4XbzZE5GR9IpES8pQ==','RCkDmmuJ3i1WIIXPkhSSLg==','Dzsz+zyZZ7ndqd2c9tx8Pw==','fjV3K/jKdXD4VFTcbe2XjA==','JKgBjbWsntfYHNYrnhXq5w==','O2ijARboKYiWVNitMLdOTw==','H+YwCpOAV4wSkbAAlyss7g==','0'),(46,'SoMxFd03Zx9M8Z35amM0bw==','nFRwLE139AzzdL1UBwpRdQ==','W8r3mZZQ3/yxyF+d6aCSbcP7xc9ShZA6DX0JfeN1M5I=','SGcJ2ECXG0UYJtKfV1J0EA==','YDsCrAATpU68tEoTAS8bFQ==','yplc/o+tWym+UsV9sMITsg==','3hvvoSq00WAMPIktpfm8xw==','0'),(47,'g+pwLSc7/HTWeeaZvFG3aA==','4MsV4Cf0TN1veOIBPqAOXw==','o/+D8ni1q6Xw5N+Dn/sqGg==','AbBrzEBupR/zLruWaXKGwA==','1YKM9vB/bMdkwh8e1MMtGw==','3j31781t8XB88WTM9qK/uQ==','sO/XjUEwdAGbAjlfrhnPBw==','0'),(48,'Jrc41o+UEmCNFZg6l3tb3g==','uYc76w9h3m+rSD2mVERYow==','OSXf9qnAugVeDJHxqxVW3/6p7MI0nimXvFJJuaqfLlM=','XniaKK9pt5hUi3zdsFPx9A==','USkPrSS75THyXv+Tmc3fRA==','hpCRL6Vjp3MezZYsRgituQ==','Z577bYGPE3OqqFd6+wqPqg==','0'),(49,'IXRAqiulvyF4AhMLmnuulg==','j6xDgTIl9+amT2eqVrQ4Pw==','Ikoy0jfbznFdDnOZ37MEhGHHtKH2CQuV+m9Q4pGEzTk=','8Nj4t/mFrc3E48TM8YF1Bw==','jhBzGyhi3PXV05vEyCJnYQ==','e+6eCObm+k/aZ7t4ey5ucg==','s/R3yDMtzCh6RChl5Q7kfQ==','0'),(50,'plJUShHRvFJIM5y1lPwbWQ==','QmGJTjD6mKTe8ERSgCa5NQ==','UAGKZt4+qj1zWP8nByn5ffWLnPY1+fyouZRLJZGjjg4=','n8cb5hwHwsUYfgzlv2U7fg==','uo1H6CHuYxoEImfIQFgLPw==','3eMHcp4zzH2p/xndalJ8gA==','s+yNL5ipaFz96K3fSaV/3w==','0'),(51,'plJUShHRvFJIM5y1lPwbWQ==','QmGJTjD6mKTe8ERSgCa5NQ==','wsXXi3463LfutcwWsksHwc0siAw57JHxB/Uqak5VgR4=','n8cb5hwHwsUYfgzlv2U7fg==','uo1H6CHuYxoEImfIQFgLPw==','3eMHcp4zzH2p/xndalJ8gA==','s+yNL5ipaFz96K3fSaV/3w==','1000'),(52,'plJUShHRvFJIM5y1lPwbWQ==','QmGJTjD6mKTe8ERSgCa5NQ==','q/FrnneVTzw1G5lJz7d2BYv+oNi2S+1PomAOt0YpHEk=','n8cb5hwHwsUYfgzlv2U7fg==','uo1H6CHuYxoEImfIQFgLPw==','3eMHcp4zzH2p/xndalJ8gA==','s+yNL5ipaFz96K3fSaV/3w==','10000'),(53,'plJUShHRvFJIM5y1lPwbWQ==','QmGJTjD6mKTe8ERSgCa5NQ==','Tbnj53he4T5geZKrZ8ThcUwD7P/Y/UHjm+5Mk2tTLhw=','n8cb5hwHwsUYfgzlv2U7fg==','uo1H6CHuYxoEImfIQFgLPw==','3eMHcp4zzH2p/xndalJ8gA==','s+yNL5ipaFz96K3fSaV/3w==','1000'),(56,'plJUShHRvFJIM5y1lPwbWQ==','QmGJTjD6mKTe8ERSgCa5NQ==','aCu3WdieIa5DQgkekOU8T1bvUPoV82HjOIrfSzHEi8w=','n8cb5hwHwsUYfgzlv2U7fg==','uo1H6CHuYxoEImfIQFgLPw==','3eMHcp4zzH2p/xndalJ8gA==','s+yNL5ipaFz96K3fSaV/3w==','7700');
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

-- Dump completed on 2024-04-30 19:09:34
