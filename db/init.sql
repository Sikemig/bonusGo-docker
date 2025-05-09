-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bonusgo
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

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
-- Table structure for table `ganancia_monedas`
--

DROP TABLE IF EXISTS `ganancia_monedas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ganancia_monedas` (
  `ID_Ganancia` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Usuario` int(11) NOT NULL,
  `ID_Objetivo` int(11) NOT NULL,
  `reclamado` bit(1) NOT NULL,
  `habilitado` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`ID_Ganancia`),
  KEY `ganancia_monedas_usuarios_FK` (`ID_Usuario`),
  KEY `ganancia_monedas_objetivos_FK` (`ID_Objetivo`),
  CONSTRAINT `ganancia_monedas_objetivos_FK` FOREIGN KEY (`ID_Objetivo`) REFERENCES `objetivos` (`ID_Objetivo`),
  CONSTRAINT `ganancia_monedas_usuarios_FK` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuarios` (`ID_Usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ganancia_monedas`
--

LOCK TABLES `ganancia_monedas` WRITE;
/*!40000 ALTER TABLE `ganancia_monedas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ganancia_monedas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `objetivos`
--

DROP TABLE IF EXISTS `objetivos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `objetivos` (
  `ID_Objetivo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `Monedas` int(11) NOT NULL,
  `categoria` enum('BRONCE','ORO','PLATA') DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Objetivo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `objetivos`
--

LOCK TABLES `objetivos` WRITE;
/*!40000 ALTER TABLE `objetivos` DISABLE KEYS */;
INSERT INTO `objetivos` VALUES (1,'Puntualidad semanal','Por haberse personado puntualmente a todas las reuniones semanales',100,'BRONCE','https://cdn.shopify.com/s/files/1/0414/9982/0194/files/rellotge-ordinador-2.jpg?v=1610793758'),(2,'Reunión con cliente','Por haber desempeñado correctamente sus funciones y haber satisfecho al cliente',300,'PLATA','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9E_yi3aGPk3GkKlMdBn3owdY6ekQVz0KJQw&s'),(3,'Ideas para proyecto','Por haber propuesto ideas de mejora en el proyecto de su departamento',250,'PLATA','https://www.ntaskmanager.com/wp-content/uploads/2020/06/recognize-improvement-process-improvement-plan-700x280.png'),(4,'Expansión de proyecto','Por proponer ideas que mejoren significativamente el proyecto en curso y sus tareas',1000,'ORO','https://www.aihr.com/wp-content/uploads/performance-improvement-plan-template-cover.png'),(5,'Orden y disciplina','Por haber realizado sus tareas de una manera clara y ordenada para el correcto entendimiento de sus funciones',150,'BRONCE','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4gifU5nVuzoKvZPrznVi5YaJxsagq85WQZw&s'),(6,'Captación de clientes','Por haber formalizado un acuerdo con un nuevo cliente de cara a relaciones laborales con la empresa',2000,'ORO','https://bloglaboral.garrigues.com/wp-content/uploads/2022/09/BLOG-LABORAL-diligencia-debida-y-derechos-humanos.jpg');
/*!40000 ALTER TABLE `objetivos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `ID_Producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `Coste` int(11) NOT NULL,
  `tipo` enum('EXPERIENCIA','ROPA','TARJETAS') DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Producto`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Bono Masterchef','Bono descuento para restaurante Masterchef',500,'TARJETAS','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4VL_j1d5mcC7PDvg_WHR5qBfyTzzfJTlPvA&s'),(2,'Botas de protección','Conjunto extra de botas con punta reforzada para realizar tus funciones laborales de forma segura',750,'ROPA','https://m.media-amazon.com/images/I/81nLLcMs6aL._AC_UF894,1000_QL80_.jpg'),(3,'Wonderbox Escapada','Paquete Wonderbox para disfrute del trabajador',1000,'EXPERIENCIA','https://eheuropa.com/45175-thickbox_default/pack-wonderbox-spa-relax-para-dos.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID_Rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `permisos` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_Rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_USER','Usuario de la plataforma','Comprar productos'),(2,'ROLE_ADMIN','Administrador de la plataforma','Editar objetivos y productos y administrar a los usuarios');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transacciones`
--

DROP TABLE IF EXISTS `transacciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transacciones` (
  `ID_Transaccion` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Usuario` int(11) NOT NULL,
  `ID_Producto` int(11) NOT NULL,
  `canjeado` bit(1) NOT NULL,
  PRIMARY KEY (`ID_Transaccion`),
  KEY `transacciones_productos_FK` (`ID_Producto`),
  KEY `transacciones_usuarios_FK` (`ID_Usuario`),
  CONSTRAINT `transacciones_productos_FK` FOREIGN KEY (`ID_Producto`) REFERENCES `productos` (`ID_Producto`),
  CONSTRAINT `transacciones_usuarios_FK` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuarios` (`ID_Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transacciones`
--

LOCK TABLES `transacciones` WRITE;
/*!40000 ALTER TABLE `transacciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `transacciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `ID_Usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) DEFAULT NULL,
  `apellido` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(15) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `ID_Rol` int(11) NOT NULL,
  `moneda` int(11) DEFAULT 0,
  PRIMARY KEY (`ID_Usuario`),
  KEY `usuarios_roles_FK` (`ID_Rol`),
  CONSTRAINT `usuarios_roles_FK` FOREIGN KEY (`ID_Rol`) REFERENCES `roles` (`ID_Rol`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Bonus','Go','bonusgo@bonusgo.es','987654321','$2a$10$YA9GQDhrx4Nwh2PYcBiQwetW.umGm4XzXD90lWyDKpNO1dzLuCqEW',2,0),(3,'Juan Manuel','Miravete Pérez del Corral','Juan@bonusgo.es','987654321','$2a$10$QIEr4A.G1w8vKl.gopCBTef5LKZT2mFmYhHiFUEeUfIPrv5O.VXkG',2,0),(4,'Cristina','Garrido Gómez','Cristina@bonusgo.es','987654321','$2a$10$kvIMm.z7tVnHQpshyoxZduYkd337OY5Z.Cuix0TM.PBOB20acbm9K',2,0),(5,'Sikem','Iglesias González','Sikem@bonusgo.es','987654321','$2a$10$NB6KLQc0igo6DGQGo279n.mtkSudo4oD6EYshjdbdYUi5Eqj1nw32',2,0),(6,'Usuario','Normal','usuario@bonusgo.es','123456789','$2a$10$JMmoz6IVnKbiFeUkzWrxpefzvQgzT8fJNavsS5HXWO0pEAgvfIMTC',1,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'bonusgo'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-08 17:01:24
