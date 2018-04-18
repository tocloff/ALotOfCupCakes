-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: tiendaonline
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compras` (
  `ID_COMPRA` bigint(20) NOT NULL AUTO_INCREMENT,
  `COMENTARIO_COMPRA` varchar(45) DEFAULT NULL,
  `FK_ID_CLIENTE` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID_COMPRA`),
  UNIQUE KEY `ID_COMPRA_UNIQUE` (`ID_COMPRA`),
  KEY `fk_COMPRAS_CLIENTES_idx` (`FK_ID_CLIENTE`),
  CONSTRAINT `fk_COMPRAS_CLIENTES` FOREIGN KEY (`FK_ID_CLIENTE`) REFERENCES `usuarios` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
INSERT INTO `compras` VALUES (1,NULL,1);
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lineas_compra`
--

DROP TABLE IF EXISTS `lineas_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lineas_compra` (
  `CANTIDAD` int(11) DEFAULT NULL,
  `FK_ID_COMPRA` bigint(20) NOT NULL,
  `FK_ID_PRODUCTO` int(11) NOT NULL,
  KEY `fk_LINEAS_COMPRA_COMPRAS1_idx` (`FK_ID_COMPRA`),
  KEY `fk_LINEAS_COMPRA_PRODUCTOS1_idx` (`FK_ID_PRODUCTO`),
  CONSTRAINT `fk_LINEAS_COMPRA_COMPRAS1` FOREIGN KEY (`FK_ID_COMPRA`) REFERENCES `compras` (`ID_COMPRA`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_LINEAS_COMPRA_PRODUCTOS1` FOREIGN KEY (`FK_ID_PRODUCTO`) REFERENCES `productos` (`ID_PRODUCTO`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lineas_compra`
--

LOCK TABLES `lineas_compra` WRITE;
/*!40000 ALTER TABLE `lineas_compra` DISABLE KEYS */;
INSERT INTO `lineas_compra` VALUES (1,1,1);
/*!40000 ALTER TABLE `lineas_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_PRODUCTO` varchar(45) NOT NULL,
  `DESCRIPCION_PRODUCTO` varchar(500) DEFAULT NULL,
  `CATEGORIA_PRODUCTO` varchar(45) DEFAULT NULL COMMENT 'Tabla de la lista de productos de la tienda.',
  `PRECIO_PRODUCTO` varchar(45) DEFAULT NULL,
  `DESCUENTO` double DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  `FOTO` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`ID_PRODUCTO`),
  UNIQUE KEY `idProducto_UNIQUE` (`ID_PRODUCTO`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'Cupcake reeses','Cupcake de reeses con betún de reeses con mantequilla','Cupcakes','15.00',0,50,'Cup1.jpg'),(2,'Galleta de avena con chispas de chocolate','Galleta de avena con chispas de chocolate y chocolate blanco','Postres de la semana','12.00',0,50,'Gal1.jpg'),(3,'Cupcake guiness','Cupcake de chocolate de halloween y dia de muertos','Cupcakes','15.00',0,50,'Cup2.jpg'),(4,'Pay de zarzamoras','Pay de zarzamoras','Pays','55.00',0,50,'Pay1.jpg'),(5,'Cupcake guiness','Cupcake de merengue de guiness','Cupcakes','18.00',0,50,'Cup3.jpg'),(6,'Pay de nuez','Pay de nuez','Pays','50.00',0,50,'Pay2.jpg'),(7,'Cupcake chocolate','Cupcake chocolate con ganash de chocolate','Cupcakes','15.00',0,50,'Cup4.jpg'),(8,'Cupcake con philadelphia','Cupcake chocolate o vainilla con betún de philadelphia','Cupcakes','15.00',0,50,'Cup5.jpg'),(9,'Mini Pay de nuez','Mini Pay de nuez','Pays','20.00',0,50,'Pay3.jpg'),(10,'Pastel con fresas','Pastel de tres leches con fresas','Pasteles','200.00',0,50,'Pas1.jpg'),(11,'Cupcake con fresas y/o durazno','Cupcake con fresas y/o duraznos','Cupcakes','15.00',0,50,'Cup6.jpg'),(12,'Pastel de tres leches para 20 personas','Pastel de tres leches con trozos de chocolate para 20 personas','Pasteles','300.00',0,10,'Pas2.jpg'),(13,'Cupcake vegano','Cupcake vegano con crema de mani','Cupcakes','20.00',0,30,'Cup7.jpg'),(14,'Brownie de chocolate','Brownie de chocolate','Brownies','18.00',0,50,'Bro1.jpg'),(15,'Jar de galletas de avena','Jar con 5 galletas de avena','Postres de la semana','55.00',0,30,'Gal2.jpg'),(16,'Mini Pay de piña coco','Mini Pay de piña con coco','Pays','20.00',0,50,'Pay4.jpg'),(17,'Mega Cupcake de ferrero','Mega Cupcake de chocolates ferrero','Cupcakes','250.00',0,30,'Cup8.jpg'),(18,'Empapado de piña coco','Empapado de piña coco','Empapados','30.00',0,50,'Emp1.jpg');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER` varchar(45) DEFAULT NULL,
  `PASSWORD` varchar(45) DEFAULT NULL,
  `EMAIL` varchar(45) DEFAULT NULL,
  `DIRECCION` varchar(45) DEFAULT NULL,
  `CIUDAD` varchar(45) DEFAULT NULL,
  `ESTADO` varchar(45) DEFAULT NULL,
  `CP` varchar(45) DEFAULT NULL,
  `PAIS` varchar(45) DEFAULT NULL,
  `telefono` varchar(45) DEFAULT NULL,
  `ULTIMA_CONEC` date DEFAULT NULL,
  `NIVEL_PRIVILEGIO` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','tamara89','b210898@gmail.com','18 de Marzo 3103A, Colonia Pedro Lozano','Monterrey','Nuevo Leon','64440','Mexico','8110770706','2018-04-17','admin'),(2,'thomas','tamara89','tocloff@gmail.com','Miguel Dominguez 2811, Colonia Talleres','Monterrey','Nuevo Leon','64440','Mexico','8121794843','2018-04-17','cliente');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-18  8:54:31
