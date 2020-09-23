CREATE DATABASE  IF NOT EXISTS `a290s19_woboland` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `a290s19_woboland`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: a290s19_woboland
-- ------------------------------------------------------
-- Server version	5.5.36

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
-- Table structure for table `ingredient_classes`
--

DROP TABLE IF EXISTS `ingredient_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredient_classes` (
  `IngredientClassID` smallint(6) NOT NULL,
  `IngredientClassDescription` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`IngredientClassID`),
  KEY `RecipeClassID` (`IngredientClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient_classes`
--

LOCK TABLES `ingredient_classes` WRITE;
/*!40000 ALTER TABLE `ingredient_classes` DISABLE KEYS */;
INSERT INTO `ingredient_classes` VALUES (1,'Spice'),(2,'Meat'),(3,'Vegetable'),(4,'Oil'),(5,'Pasta'),(6,'Grain'),(7,'Flour'),(8,'Dairy'),(9,'Liquid'),(10,'Seafood'),(11,'Butter'),(12,'Cheese'),(13,'Sauce'),(14,'Dressing'),(15,'Gravy'),(16,'Topping'),(17,'Fruit'),(18,'Chips'),(19,'Condiment'),(20,'Bottle'),(21,'Packaged food'),(22,'Herb'),(23,'Sorbet'),(24,'Liquor');
/*!40000 ALTER TABLE `ingredient_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredients`
--

DROP TABLE IF EXISTS `ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ingredients` (
  `IngredientID` int(11) NOT NULL,
  `IngredientName` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `IngredientClassID` smallint(6) DEFAULT NULL,
  `MeasureAmountID` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`IngredientID`),
  KEY `Ingredient_ClassesIngredients` (`IngredientClassID`),
  KEY `IngredientID` (`IngredientID`),
  KEY `MeasurementsIngredients` (`MeasureAmountID`),
  CONSTRAINT `Ingredients_FK01` FOREIGN KEY (`MeasureAmountID`) REFERENCES `measurements` (`MeasureAmountID`),
  CONSTRAINT `Ingredients_FK00` FOREIGN KEY (`IngredientClassID`) REFERENCES `ingredient_classes` (`IngredientClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredients`
--

LOCK TABLES `ingredients` WRITE;
/*!40000 ALTER TABLE `ingredients` DISABLE KEYS */;
INSERT INTO `ingredients` VALUES (1,'Beef',2,1),(2,'Onion',3,17),(3,'Water',9,1),(4,'Guinness Beer',9,1),(5,'Potato',3,2),(6,'Carrot',3,2),(7,'Tomato',3,8),(8,'Jalapeno',3,2),(9,'Garlic',1,16),(10,'Black Pepper (ground)',1,3),(11,'Salt',1,3),(12,'Halibut',10,5),(13,'Chicken, Fryer',2,5),(14,'Bacon',2,8),(15,'Romaine Lettuce',3,13),(16,'Iceberg Lettuce',3,13),(17,'Butterhead Lettuce',3,13),(18,'Scallop',10,5),(19,'Salmon',10,5),(20,'Vinegar',9,1),(21,'Olive Oil',4,1),(22,'Cucumber',3,17),(23,'Mushrooms',3,2),(24,'Red Wine',9,2),(25,'White Wine',9,2),(26,'Milk',8,2),(27,'Cheddar Cheese',12,2),(28,'Tortilla Chips',18,11),(29,'Black Olives',19,2),(30,'Green Beans',3,14),(31,'Fettuccini Pasta',5,1),(32,'Heavy Cream',8,1),(33,'Chicken, Pre-cut',2,17),(34,'T-bone Steak',2,17),(35,'Chicken Breast',2,7),(36,'Chicken Leg',2,17),(37,'Chicken Wing',2,7),(38,'Chicken Thigh',2,7),(39,'New York Steak',2,17),(40,'Spaghetti',5,1),(41,'Mustard, Regular',19,1),(42,'Mustard, Dijon',19,1),(43,'Ketchup',19,1),(44,'Salsa',13,1),(45,'Parmesan Cheese',12,1),(46,'Blue Cheese',12,1),(47,'Butter',11,4),(48,'Green Onion',3,14),(49,'Green Olives',19,2),(50,'Vegetable Oil',4,4),(51,'White Pepper (ground)',1,1),(52,'Cayenne Pepper, Ground',1,1),(53,'Radishes',3,14),(54,'Balsamic Vinaigrette Dressing',20,1),(55,'Sponge Cake',21,20),(56,'Raspberry Jello',21,20),(57,'Bird\'s Custard Powder',21,20),(58,'Colored sugar sprinkles',1,3),(59,'Raspberry Jam',21,21),(61,'Flour',6,2),(62,'Eggs',8,7),(63,'Beef drippings',4,3),(64,'Red Snapper',10,1),(65,'Canned tomatoes',3,10),(66,'Nutmeg',1,3),(67,'Cinnamon',1,3),(68,'Lime Juice',9,3),(69,'Asparagus',3,5),(70,'Parsley',22,22),(71,'Pie dough for 2-crust pie',21,24),(72,'Ground Pork',2,5),(73,'Ground Clove',1,3),(74,'Bread Crumbs',21,2),(75,'Leek',3,7),(76,'Red Bell Pepper',3,7),(77,'Lemon Juice',9,4),(78,'Lemon ',17,7),(79,'Lemon Sorbet',23,26),(80,'Vodka',24,4);
/*!40000 ALTER TABLE `ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `measurements`
--

DROP TABLE IF EXISTS `measurements`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `measurements` (
  `MeasureAmountID` smallint(6) NOT NULL,
  `MeasurementDescription` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`MeasureAmountID`),
  KEY `RecipeClassID` (`MeasureAmountID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `measurements`
--

LOCK TABLES `measurements` WRITE;
/*!40000 ALTER TABLE `measurements` DISABLE KEYS */;
INSERT INTO `measurements` VALUES (1,'Ounce'),(2,'Cup'),(3,'Teaspoon'),(4,'Tablespoon'),(5,'Pound'),(6,'Pinch'),(7,'Piece'),(8,'Slice'),(9,'Dash'),(10,'Can'),(11,'Bag'),(12,'Bottle'),(13,'Head'),(14,'Bunch'),(15,'Ear'),(16,'Clove'),(17,'Whole'),(18,'Pint'),(19,'To Taste'),(20,'Package'),(21,'Jar'),(22,'Sprig'),(23,'Quarts'),(24,'sticks'),(25,'filets'),(26,'Scoop');
/*!40000 ALTER TABLE `measurements` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_classes`
--

DROP TABLE IF EXISTS `recipe_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_classes` (
  `RecipeClassID` smallint(6) NOT NULL,
  `RecipeClassDescription` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`RecipeClassID`),
  KEY `RecipeClassID` (`RecipeClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_classes`
--

LOCK TABLES `recipe_classes` WRITE;
/*!40000 ALTER TABLE `recipe_classes` DISABLE KEYS */;
INSERT INTO `recipe_classes` VALUES (1,'Main course'),(2,'Vegetable'),(3,'Starch'),(4,'Salad'),(5,'Hors d\'oeuvres'),(6,'Dessert'),(7,'Soup');
/*!40000 ALTER TABLE `recipe_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipe_ingredients`
--

DROP TABLE IF EXISTS `recipe_ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipe_ingredients` (
  `RecipeID` int(11) NOT NULL,
  `RecipeSeqNo` smallint(6) NOT NULL,
  `IngredientID` int(11) DEFAULT NULL,
  `MeasureAmountID` smallint(6) DEFAULT NULL,
  `Amount` float DEFAULT NULL,
  PRIMARY KEY (`RecipeID`,`RecipeSeqNo`),
  KEY `IngredientID` (`IngredientID`),
  KEY `IngredientsRecipe_Ingredients` (`IngredientID`),
  KEY `MeasureAmountID` (`MeasureAmountID`),
  KEY `MeasurementsRecipe_Ingredients` (`MeasureAmountID`),
  KEY `RecipeID` (`RecipeID`),
  KEY `RecipesRecipe_Ingredients` (`RecipeID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipe_ingredients`
--

LOCK TABLES `recipe_ingredients` WRITE;
/*!40000 ALTER TABLE `recipe_ingredients` DISABLE KEYS */;
INSERT INTO `recipe_ingredients` VALUES (1,1,1,5,1),(1,2,2,17,2),(1,3,5,17,4),(1,4,6,17,6),(1,5,3,23,4),(1,6,4,1,12),(2,1,8,17,6),(2,2,7,17,2),(2,3,2,17,0.5),(2,4,10,4,1),(2,5,11,3,1),(3,1,8,17,4),(3,2,27,2,1),(3,3,2,17,0.5),(3,4,28,11,0.5),(3,5,29,2,0.25),(4,1,30,5,0.5),(4,2,9,16,2),(4,3,21,4,1),(5,1,31,1,16),(5,2,50,4,1),(5,3,11,3,3),(5,4,47,4,6),(5,5,32,2,0.25),(5,12,45,1,6),(5,16,51,19,0),(6,1,36,7,2),(6,2,38,7,2),(6,3,11,3,1.5),(6,4,10,3,1.5),(6,5,9,16,3),(6,6,52,19,0),(7,1,15,13,1),(7,2,23,7,12),(7,3,22,17,1),(7,4,7,17,1),(7,5,53,14,1),(7,6,54,4,3),(8,1,55,20,1),(8,2,56,20,1),(8,3,57,20,1),(8,4,59,21,1),(8,5,58,3,1),(9,1,1,5,4),(9,2,9,16,6),(9,3,11,3,1),(9,4,10,3,1),(10,1,61,2,1.5),(10,2,3,2,1),(10,3,62,7,2),(10,4,11,3,0.5),(10,5,26,2,0.5),(10,6,63,3,4),(11,1,64,5,2),(11,2,21,1,4),(11,3,2,17,1),(11,4,65,10,2),(11,5,11,3,1),(11,6,10,3,0.5),(11,7,66,3,0.25),(11,8,67,3,0.5),(11,9,68,3,2),(11,10,49,7,8),(11,11,61,2,0.5),(11,12,70,22,2),(12,1,69,5,1),(12,2,47,4,4),(12,3,9,16,4),(13,1,71,24,2),(13,2,72,5,2),(13,3,2,2,0.333),(13,4,67,4,1),(13,5,73,3,0.5),(13,6,11,3,0.25),(13,7,10,3,0.5),(13,8,74,2,0.5),(14,1,19,25,4),(14,2,6,7,2),(14,3,75,7,1),(14,4,76,7,1),(14,5,47,4,4),(14,6,25,4,2),(14,7,77,4,2),(14,8,78,7,1),(14,9,11,3,0.5),(14,10,10,3,0.5),(15,1,79,26,2),(15,2,80,4,2);
/*!40000 ALTER TABLE `recipe_ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recipes`
--

DROP TABLE IF EXISTS `recipes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recipes` (
  `RecipeID` int(11) NOT NULL,
  `RecipeTitle` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `RecipeClassID` smallint(6) DEFAULT NULL,
  `Preparation` text,
  `Notes` text,
  PRIMARY KEY (`RecipeID`),
  KEY `Recipe_ClassesRecipes` (`RecipeClassID`),
  KEY `RecipeID` (`RecipeID`),
  CONSTRAINT `Recipes_FK00` FOREIGN KEY (`RecipeClassID`) REFERENCES `recipe_classes` (`RecipeClassID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recipes`
--

LOCK TABLES `recipes` WRITE;
/*!40000 ALTER TABLE `recipes` DISABLE KEYS */;
INSERT INTO `recipes` VALUES (1,'Irish Stew',1,'Cut the beef into 1\" chunks. Braise the meat. Add water and Guinness. Chop onions, potatoes, and carrots into 1/2\" chunks. Add to stew. Simmer until vegetables are done.',NULL),(2,'Salsa Buena',5,'Coarsely dice the jalapenos.  Mix all ingredients thoroughly in a bowl and let stand in the refrigerator for about an hour.  Serve with your favorite corn chips.',NULL),(3,'Machos Nachos',5,'Slice the jalapenos crosswise (in circles) and set aside.  Grate the cheddar cheese and set aside.  Dice the onion and set aside.  Spread the tortilla chips on a large microwavable dish.  Top the chips with the grated cheese, diced onion, and jalapenos.  Place the dish in the micowave and cook until the cheese just melts, about 3-4 minutes on high.  When the cheese has melted, remove the dish and top with the black olives.','You can add a half a cup of diced tomatoes to this dish if you like. You can either add them prior to cooking in the microwave or afterwards, just before you add the black olives.'),(4,'Garlic Green Beans',2,'Snap off and discard the ends of the beans, then rinse them in cold water.  Mince the two cloves of garlic.  Heat the oil in a frying pan on medium high heat.  When the oil is hot, add the green beans and garlic.  Stir contiuously for about four minutes.  Place the beans on a serving dish when they\'re done and sprinkle silvered almonds on the top.','Be sure not to burn the oil. Watch it carefully while it\'s heating.'),(5,'Fettuccini Alfredo',1,'Fill a large pot two thirds full with water. Add one tablespoon each of salt and vegetable oil. Bring to a rollling boil.  Reduce heat, add pasta, and stir briefly. Cook until pasta is al dente.  Just before the pasta is ready (about five minutes), melt the butter in a frying pan on low heat.  After the butter has melted, add the heavy cream to the pan. Increase the heat to medium and stir until the mixture is slightly thickened. Remove the pan from the heat once the mixture is ready.  Drain the fettuccini when it\'s done and add it to the mixture in the frying pan.  Mix three ounces of the cheese the the fettuccini and toss the entire mixture.  Add another three ounces of cheese and the white pepper, and toss again lightly.',NULL),(6,'Pollo Picoso',1,'Wash chicken pieces thoroughly in cold water. Pat dry and set aside.  Mince garlic and then mix it with the salt, pepper, and cayenne.  Make sure the mixture is combined as thoroughly as possible.  Coat each chicken piece (to taste) with the mixture.  Place pieces in the broiler pan and cook for 15 minutes. Turn pieces and cook for another 15 minutes. Turn pieces once more and cook for 35 - 40 minutes.  When the chicken is cooked, remove from stove and let it stand for 10 minutes.','Pre-heat the oven to 400 degrees.  Cover the bottom of a broiler pan with a sheet of aluminum foil and then pour in about 1/2 an inch of water. The water will keep the chicken grease drippings from splattering throughout the inside of the stove and causing smoke. The foil makes the pan easier to clean.'),(7,'Mike\'s Summer Salad',4,'Rinse lettuce and cut into bite size pieces. (You can tear them if you really want to.)  Dust off mushrooms, remove stems, and slice into thick pieces, about 1/4\".  Peel the cucumber and slice it into 1/4\" thick circles.  Slice the tomatoes into 1/2\" wedges.  Wash radishes, remove leafy head and root, and slice into 1/8\" circles.  Mix all ingredients together in a large salad bowl and add your favorite balsamic vinaigrette dressing.',NULL),(8,'Trifle',6,'Prepare the Jello and custard per package directions.  In tall dessert cups, place a 1/2\" layer of the sponge cake.  Soak the layer of cake in Jello.  Spoon on a thin layer of raspberry jam.  Add a layer of custard.\nContinue adding layers of sponge cake, jam, and custard until the cup is full (with a layer of custard on top) -- 2 to 3 layers.  Chill for 2 hours.  Sprinkle colored sugar on top and serve.','For a \"spicier\" dessert, replace half the liquid in the Jello with brandy or your favorite liqueur.'),(9,'Roast Beef',1,'Place the beef on a roasting rack in a roasting pan.  Make a paste of ground garlic, salt, and pepper.  Smother the outside of the roast with the paste.  Roast at 325 for about 20 minutes per pound or to an internal temperature of 160F for medium-rare.  Remove from oven and let stand 15 minutes before carving.  Reserve the beef drippings for gravy or Yorkshire Pudding.',NULL),(10,'Yorkshire Pudding',3,'Place the eggs and water in a blender.  While running the blender, slowly add the flour.  Add the salt and milk and blend for 30 seconds more.  Let stand in a refrigerator for 1 hour or more.  Heat the roasting pan with beef drippings to 450F.  Pour in the pudding mixture.  Cook in a very hot oven for 20-25 minutes or until puffed up and golden brown.  Remove from the pan immediately and cut into slices.  Serve with brown gravy.',NULL),(11,'Huachinango Veracruzana (Red Snapper, Veracruz style)',1,'Heat one ounce of olive oil in a 1.5 quart saucepan.  Slice the onion and sautee lightly in the olive oil.  Drain the canned tomatoes (you can use peeled fresh tomatoes if you like) and puree in a blender.  To the pureed tomatoes, add all the spices, thinly sliced Jalapenos, and green olives.  Pour the tomato and spice mixture into the saucepan with the onions and simmer on a very low heat covered for 30 minutes.  While the sauce is cooking, peel and boil the potatoes.  Just before the potatoes are done, heat the remaining olive oil in a large frypan.  Wash and lightly dust the fish pieces in a mixture of flour, salt, and pepper.  Fry the fish, turning once, until just done.  Place the cooked fish in a large platter.  Surround the fish with the boiled potatoes.  Pour the sauce over the fish, sprinkle with chopped parsley, and serve immediately.\nServes 6.','You can substitute any firm white fish filets for the Red Snapper.  If you use salted canned tomatoes, reduce the salt in the sauce by half.  Adjust the amount of the Jalapenos in the sauce to suit your taste for spicy food!'),(12,'Asparagus',2,'Wash the asparagus and break off the tough part (if any) at the bottom of the stalks.  Arrange on a steaming rack in a large saucepan.  Dab liberally with pats of butter and sprinkle on the chopped garlic.  Steam until just tender -- no more than 5 minutes for large stalks.  Serve immediately.','You can chill the cooked asparagus and serve with your favorite dip as an appetizer.'),(13,'Tourtire (French-Canadian Pork Pie)',1,'Brown ground pork and chopped onion, stirring and breaking up pork.  Add spices and salt and pepper.  Simmer, uncovered, for 45 minutes, stirring occasionally.  Preheat oven to 375 degrees.  Prepare pie dough for 2-crust pie.  Line 9\" pie plate with half of the rolled-out dough.  Drain pork and stir bread crumbs into pork.  Taste and add more salt and pepper if desired.  Fill pie with pork mixture and top with second half of the rolled-out dough.  Crimp edges of pie and slit the top in several places.  Bake in 375-degree oven for one hour, covering edges of pie crust with foil if necessary.  Serve hot or cold.','Be sure to use fresh ground pork, not sausage.  Can be made with half ground pork and half ground beef, if desired.'),(14,'Salmon Filets in Parchment Paper',1,'Julienne carrots, leeks, and bell peppers and steam for several minutes.  Drain and rinse vegetables in ice water and set aside.  Preheat oven to 400 degrees.  Butter 4 large rounds of parchment paper.  Distribute half of vegetables on one side of each round of parchment.  Place a salmon filet on the vegetables on each round.  Top the filets with the rest of the vegetables.  Combine white wine and lemon juice and spoon 1 tablespoon on each filet.  Pour melted butter on filets.  Place a thinly-sliced lemon round on each.  Salt and pepper very lightly.  Fold over parchment paper into half circles and roll and crimp edges tightly.  Bake packets at 400 degrees for 10-15 minutes, depending on thickness of the filets.','Serve the salmon in the parchment packets. A salad and boiled new potatoes, buttered and sprinkled with fresh parsley, are the perfect complements.'),(15,'Coupe Colonel',6,'For each person, place 2 scoops of lemon sorbet in a stemmed glass.  Top with vodka.','This is a lovely, light, and refreshing dessert.  Use the best sorbet and vodka you can find.  Serve with crisp cookies.');
/*!40000 ALTER TABLE `recipes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-03-30 16:10:26
