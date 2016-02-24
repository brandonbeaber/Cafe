-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: cafetrex.com    Database: cafe
-- ------------------------------------------------------
-- Server version	5.5.44-0ubuntu0.14.04.1

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
-- Table structure for table `Cart`
--

DROP TABLE IF EXISTS `Cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cart` (
  `shoppingcart_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`shoppingcart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cart`
--

LOCK TABLES `Cart` WRITE;
/*!40000 ALTER TABLE `Cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Menu`
--

DROP TABLE IF EXISTS `Menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `price` double DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Menu`
--

LOCK TABLES `Menu` WRITE;
/*!40000 ALTER TABLE `Menu` DISABLE KEYS */;
INSERT INTO `Menu` VALUES (1,'T-Rexadillas','Chicken, roasted red bell peppers, green onions, Monterey Jack and Cheddar cheeses grilled in flour tortillas. Served with pico de galla and T-Rex avocado cream sauce. ',9.99),(2,'Dexter\'s Dual Dip','Our phenomenal combination of Chili Con Queso Dip and Spinach and Pepper Jack Queso Dip served with tri-colored tortilla chips.',9.99),(3,'Trixie\'s Fried Cheese','Mozzarella sticks lightly breaded, fried and served with our marinara sauce.',8.99),(4,'Artifact Stack','Dig into these fried onion rings, served with our BBQ ranch and buttermilk ranch.',8.99),(5,'Colosso Nachos','Start at the summit of this delicious colossal heap of tortilla chips topped with ground beef, black beans, pico de gallo, green onions, cheddar cheese and sour cream.',14.99),(6,'Lava Me Tenders','Golden-fried chicken tenders breaded with coconut, piled high and served with our coconut curry and honey mustard sauces.',10.99),(7,'Super Saurus Sampler','Chili Con Queso Dip with tortilla chips, Lava Me Tenders, T-Rexadillas, Fried Cheese and Onion Rings.',17.99),(8,'Jurassic Salad','Mixed greens tossed with carrots, cucumbers and Roma tomatoes, served with you choice of dressing.',6.99),(9,'Caesarsaurus','A small Caesar salad with croutons and Parmesan cheese.',6.99),(10,'Chicken Caesarsaurus','A classic Caesar salad with croutons, Parmesan cheese and grilled chicken.',14.99),(11,'Chop! Chop!','Grilled Chicken, romaine and iceberg lettuce, black olives, cucumbers, carrots, blue cheese, crumbles and dried cranberries tossed in a raspberry vinaigrette dressing and topped with candied pecans.',16.99),(12,'Pterodactyl Salad','Rotisserie chicken, mixed greens, celery, tomatoes, blue cheese crumbles, chopped egg and bacon. Tossed in a balsamic vinagrette dressing.',16.99),(13,'Tropical Island Salad','Crisp romaine and spring mix lettuce with grilled shrimp, mangos, strawberries, grapes, and roasted pumpkin seeds tossed in a honey lime vinaigrette dressing.',16.99),(14,'Dino Combo','Build your combo with your choice of soup and either any half sandwich or any half salad.',14.99),(15,'Asteroid French Onion Soup','Traditional sherry-based onion soup baked with a large crouton and bubbling Swiss cheese.',7.99),(16,'Lava Tomato Basil Soup','Tomato basil soup garnished with Parmesan cheese.',6.99),(17,'Cretaceous Chicken Fried Steak','Breaded beef steak fried golden brown, topped with country gravy. Served with red skinned mashed potatoes and your choice of a side. ',17.99),(18,'Chickasaurus Chicken Fried Steak','Breaded Chicken breast fried golden brown. Served with red skinned mashed potatoes and your choice of a side.',16.99),(19,'Heartford\'s Honey Chicken','Tempura chicken with broccoli and carrots in a honey glaze, served with jasmine rice.',15.99),(20,'Fire-Roasted Rotisserie Chicken','Half of a chicken, slow-roasted. Served with red skinned mashed potatoes and your choice of a side',17.99),(21,'Boneyard Buffet','Half of a chicken, slow-roasted and St. Louis style pork spareribs. Served with waffle fries and coleslaw.',24.99),(22,'Ferocious Flat Iron Steak','Flat iron steak charbroiled to perfection and topped with steak butter. Served with red skinned mashed potatoes and seasonal vegetables.',22.99),(23,'Mega Mes-O-Bones','Tender, slow-roasted St. Louis style pork spareribs, based with BBQ sauce. Served with waffle fries and coleslaw.',23.99),(24,'Sebastian\'s Steak and Shrimp','Charbroiled flat iron steak served with Tar Pit Fried Shrimp and Tempura Shrimp. Served with red skinned potatoes and seaosnal vegetables.',25.99),(25,'Predator Platter','Rotisserie chicken, slow-roasted St. Louis style pork spareribs and Tar Pit Fried Shrimp served with your choice of a side.',27.99),(26,'Meteor Meatloaf','Meatloaf is basted with BBQ sauce, baked to perfection, and served with mashed potatoes, seasonal vegetables and fried onion strings.',16.99),(27,'Red Skinned Mashed Potatoes','Red Skinned Mashed Potatoes',3.29),(28,'Waffle Fries','Waffle Fries',3.29),(29,'Coleslaw','Coleslaw',3.29),(30,'Raptor Rice','Raptor Rice',3.29),(31,'Seasonal Vegetables','Seasonal Vegetables',3.29),(32,'Prehistoric Pepperoni Pizza','Loaded with pepperoni, marinara sauce and mozzarella cheese.',14.99),(33,'Cosmic BBQ Chicken Pizza','Fire-Roasted Rotisserie Chicken and Smokin BBQ sauce topped iwth mozzarella cheese and  crispy onion strings.',15.99),(34,'Seismic Stromboli','Beef, mozzarella cheese, caramelized onions and bell peppers rolled in fresh pizza dough and baked in our pizza oven. Served with your choice of coleslaw or a salad.',15.99),(35,'Colossal Calzone','A Italian classic baked with creamy ricotta and mozzarella cheeses, zesty Italian sausage, pepperoni and roasted red peppers. Served with a choice of a salad.',15.99),(36,'Herbivore\'s Delight','Tomato and basil tortilla layered and rolled with Caesar salad, grilled zucchini, roasted red bell pepper, spinach and a sliced, grilled portobello mushroom.',12.99),(37,'Paleozoic Chicken Sandwich','Pizza dough with grilled chicken, lettuce, bacon, pepper jack cheese, avocado, tomato and lemon aioli.',15.99),(38,'Tarrogon Chicken Salad Sandwich','Grilled chicken, tossed with tarragon, celery, onions, toasted almonds and mayonnaise. Served on a croissant with lettuce and tomato.',13.99),(39,'Rocksy\'s Reuben','A classic Reuben with corned beef, melted Swiss cheese, steamy sauerkraut and Thousand Island dressing on grilled marble rye. ',15.99),(40,'Stegosaurus Steak and Cheese Sandwich','Cheesesteak with sauteed onions and peppers, topped with melted Monterey Jack and Swiss cheese, served with lettuce and tomatoes on a toasted hoagi roll.',15.99),(41,'T-Rex Club','A traditional BLT with shaved turkey breast, pepper jack cheese and mayonnaise on toasted sourdough bread.',15.99),(42,'Bronto Burger','A beef burger piled high with a fried onion ring. Served with lettuce, tomato, pickles and cheese.',13.99),(43,'Guac-asaurus Burger','A beef burger topped with quacamole, a fried onion ring, pepper jack cheese and bacon. Served with lettuce, tomato and pickles.',16.99),(44,'Gigantosaurus Burger','Two beef burger patties topped with an fried onion ring. Served with lettuce, tomato, pickles and cheese.',16.99),(45,'BBQ Bacon Cheeseburger','A beef burger topped with our Smoking Mojo BBQ sauce, Cheddar cheese, bacon and a fried onion ring. Served with lettuce, tomato and  pickles.',14.99),(46,'Tempura Shrimp','Tender shrimp tempura battered and fried, served with roasted vegetables, jasmine rice and orange wasabi sauce for dipping.',18.99),(47,'Tar Pit Fried Shrimp','Shrimp lightly fried golden brown, served with tartar and cocktail sauces, waffle fries, and coleslaw',18.99),(48,'Triceratops Trio','A combination of our Fish and Chips, Tempura Shrimp and Tar Pit Fried Shrimp served with waffle fries, coleslaw and dipping sauces. ',21.99),(49,'Cedar Plank Salmon','Fillet of salmon seared on a cedar plank and topped with a roasted hazelnut and Frangelico butter, served with Raptor Rice and seasonal vegetables.',22.99),(50,'Fossil Fish and Chips','Flaky, white fish, golden fried, served English-style with tartar sauce, waffle fries and coleslaw.',17.99),(51,'Layer of the Earth Lasagna','Fresh pasta layered with tomato and meat sauce, ricotta, mozzarella and Parmesan cheeses, baked to perfection and served with a Caesar salad or mixed greens with your choice of dressing.',17.99),(52,'Jurassic Pasta','Grilled chicken, cavatappi pasta, walnut pesto, broccoli, red peppers and spinach tossed with garlic Alfredo sauce.',17.99),(53,'Velociraptor Rigatoni','Rigatoni with Italian sausage, tomatoes, portabella mushrooms and fresh basil.',16.99),(54,'Seismosaurus Shrimp','Shrimp sauteed with garlic, basil, white wine, Roma tomatoes, crushed red pepper and marinara sauce tossed with bow tie pasta and topped with Parmesan cheese.',16.99);
/*!40000 ALTER TABLE `Menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `totprice` double DEFAULT NULL,
  `instructions` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Person`
--

DROP TABLE IF EXISTS `Person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Person` (
  `person_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Person`
--

LOCK TABLES `Person` WRITE;
/*!40000 ALTER TABLE `Person` DISABLE KEYS */;
/*!40000 ALTER TABLE `Person` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `uri` int(11) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (1,'admin','password1');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-10-17 17:53:59
