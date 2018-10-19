set names utf8mb4;
set charset utf8mb4;
drop database if exists groenetenen;
create database groenetenen charset utf8mb4;
use groenetenen;

CREATE TABLE filialen (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  naam varchar(50) NOT NULL,
  hoofdFiliaal tinyint NOT NULL,
  straat varchar(50) NOT NULL,
  huisNr varchar(7) NOT NULL,
  postcode smallint unsigned NOT NULL,
  gemeente varchar(50) NOT NULL,
  inGebruikName date,
  waardeGebouw decimal(10,2),
  versie int unsigned NOT NULL DEFAULT 0
);
--
-- Dumping data for table `filialen`
--

INSERT INTO filialen(naam,hoofdFiliaal,straat,huisNr,postcode,gemeente,inGebruikName,waardeGebouw) VALUES
('Andros',1,'Keizerslaan','11',1000,'Brussel','2009-01-01',1000),
('Delos',0,'Oude Vest','17',9200,'Dendermonde','2009-02-02',2000),
('Gavdos',0,'Europalaan','37',3600,'Genk','2009-03-03',3000),
('Hydra',0,'Interleuvenlaan','2',3001,'Heverlee','2009-04-04',4000),
('Ikaria',0,'Vlamingstraat','10',8560,'Wevelgem','2009-05-05',5000),
('Oinouses',0,'Archimedesstraat','4',8400,'Oostende','2009-06-06',6000);

CREATE TABLE werknemers(
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  voornaam varchar(50) NOT NULL,
  familienaam varchar(50) NOT NULL,
  filiaalId int unsigned NOT NULL,
  wedde decimal(10,2) NOT NULL,
  rijksregisterNr bigint unsigned NOT NULL unique,
  CONSTRAINT werknemersFilialen FOREIGN KEY (filiaalId) REFERENCES filialen(id)
) AUTO_INCREMENT=310;
INSERT INTO `werknemers` VALUES (1,'Willy','Abbeloos',2,2130,73021113078),(2,'Josephke','Achten',3,2210,64030521064),(3,'François','Adam',2,2320,82010132079),(4,'Jan','Adriaensens',2,2430,74030643009),(5,'René','Adriaensens',2,2530,85042253061),(6,'Frans','Aerenhouts',2,2620,85042762011),(7,'Emile','Aerts',2,2730,70112073081),(8,'Jean','Aerts',2,2830,79100883028),(9,'Mario','Aerts',2,2930,60112493031),(10,'Paul','Aerts',2,2030,89102003059),(11,'Stefan','Aerts',2,2130,71120813067),(12,'François','Alexander',1,2210,67060821080),(13,'Albert','Anciaux',1,2320,83020432054),(14,'Urbain','Anseeuw',6,2420,63022042085),(15,'Etienne','Antheunis',2,2510,66052851041),(16,'Jacques','Arlet',3,2630,62010463039),(17,'Wim','Arras',2,2730,68092073050),(18,'Roger','Baens',2,2830,80110883107),(19,'Dirk','Baert',3,2930,89102493021),(20,'Hubert','Baert',3,2030,61122003061),(21,'Jean-Pierre','Baert',3,2130,76050813021),(22,'Armand','Baeyens',2,2220,73021622048),(23,'Jan','Baeyens',2,2320,80110432064),(24,'Roger','Baguet',3,2410,81120941083),(25,'Serge','Baguet',3,2510,61122551114),(26,'Gérard','Balducq',6,2620,83022462094),(27,'Koen','Barbé',6,2730,69102073032),(28,'Georges','Barras',2,2810,63021781108),(29,'Auguste','Baumans',2,2920,84031692032),(30,'Arsène','Bauwens',2,2020,83021202108),(31,'Henri','Bauwens',2,2120,62012812070),(32,'Jules','Bayens',2,2210,61120521094),(33,'Albert','Beckaert',3,2330,64031533127),(34,'Marcel','Beckaert',3,2430,74030343046),(35,'Koen','Beeckman',3,2530,82011953085),(36,'Kamiel','Beeckman',3,2630,64030763093),(37,'Theophile','Beeckman',3,2730,77062373089),(38,'Benoni','Beheyt',3,2810,62011781049),(39,'Albert','Beirnaert',3,2910,75040891071),(40,'Jean','Belvaux',1,2020,81121202090),(41,'Adelin','Benoit',3,2110,82011711096),(42,'Auguste','Benoit',3,2210,63020521132),(43,'Jef','Berben',3,2310,69102131046),(44,'Jean-Pierre','Berckmans',2,2410,60091241073),(45,'Albert','Berton',3,2510,62012551093),(46,'Frans','Beths',2,2630,60110463138),(47,'René','Beyens',2,2710,80110171097),(48,'Herman','Beyssens',2,2830,84031183102),(49,'Albert','Billiet',3,2920,83021692080),(50,'Hector','Billiet',3,2020,83021202128),(51,'Marcel','Blavier',1,2120,63022812062),(52,'Roger','Blockx',1,2210,61120521114),(53,'Maurice','Blomme',6,2310,73022131125),(54,'Willytje','Bocklandt',3,2410,87061241119),(55,'Emile','Bodart',3,2510,61122551144),(56,'Alfons','Boekaerts',2,2610,75041661125),(57,'Cesar','Bogaert',3,2720,62011272119),(58,'Jan','Bogaert',3,2820,70112882074),(59,'Jean','Bogaerts',2,2930,62012793102),(60,'Frans','Bonduel',1,2020,82011202069),(61,'Tom','Boonen',3,2110,79101711116),(62,'Jozef','Boons',2,2230,80112423128),(63,'Gabriel','Borra',2,2330,62011233123),(64,'Joseph','Bosmans',2,2420,63022042135),(65,'Walter','Boucquet',3,2530,84031953156),(66,'Marcel','Boumon',3,2610,72011361129),(67,'Ferdinand','Bracke',6,2710,85040171158),(68,'Adolphe','Braeckeveldt',3,2810,69082381160),(69,'Omer','Braekevelt',3,2920,84031992162),(70,'Frans','Brands',2,2010,71120101087),(71,'Jean','Brankart',3,2130,72011113079),(72,'Emile','Brichard',5,2230,83022723131),(73,'Georges','Brosteaux',1,2310,76052431151),(74,'Emile','Bruneau',4,2420,62012042076),(75,'Jean-Marie','Bruyère',6,2520,77060852146),(76,'Dave','Bruylandts',2,2620,84032762109),(77,'Johan','Bruyneel',1,2730,73022373144),(78,'Lucien','Buysse',6,2810,62011781089),(79,'Christophe','Brandt',3,2910,76050591090),(80,'Norbert','Callens',2,2020,84031202130),(81,'Johan','Capiot',3,2110,81121711177),(82,'Pino','Cerami',4,2210,60110521172),(83,'Georges','Christiaens',2,2330,68071833155),(84,'Georges','Claes',2,2430,72012843126),(85,'Karel','Clerckx',1,2520,72010852102),(86,'Alex','Close',6,2630,89100463158),(87,'Yvan','Corbusier',1,2710,83020471130),(88,'Hilaire','Couvreur',1,2830,85041183114),(89,'Wilfried','Cretskens',2,2910,77060891162),(90,'Claude','Criquielion',3,2030,67062603130),(91,'Emile','Daems',2,2130,70110813175),(92,'Gustave','Danneels',2,2230,85042723095),(93,'Fred','De Bruyne',6,2310,73022431158),(94,'Arthur','Decabooter',1,2420,66052342171),(95,'Hans','De Clerq',6,2530,82011953145),(96,'Roger','Decock',6,2610,71121361103),(97,'Georges','Decraeye',6,2730,75042373108),(98,'Odiel','Defraeye',6,2830,83021183180),(99,'Albert','De Jonghe',6,2910,75040891131),(100,'Julien','Delbecque',6,2010,75040401179),(101,'Alfons','Deloor',1,2110,82011711156),(102,'Gustaaf','Deloor',1,2210,63020521192),(103,'Hubert','Deltour',1,2320,83020432144),(104,'Paul','Deman',3,2430,69102843146),(105,'Marc','Demeyer',1,2520,71120852163),(106,'Frans','De Mulder',1,2610,74031661106),(107,'Johan','De Muynck',6,2710,84030471122),(108,'Jef','Demuysere',6,2810,62012081112),(109,'Jules','Depoorter',1,2910,74030891169),(110,'Richard','Depoorter',1,2010,76050401161),(111,'Prosper','Depredomme',6,2120,67060312133),(112,'Willy','Derboven',3,2230,83022723171),(113,'Germain','Derijcke',6,2330,65041533179),(114,'Michel','Dernies',2,2420,63022042185),(115,'Charles','Deruyter',1,2530,85041953178),(116,'Maurice','Desimpelaere',6,2610,79081961163),(117,'Gilbert','Desmet',3,2710,83020171167),(118,'Georges','Desplenter',1,2820,77060382145),(119,'Léon','Despontin',3,2910,73020891207),(120,'Eric','De Vlaeminck',6,2010,76050701164),(121,'Roger','De Vlaeminck',6,2110,87062311216),(122,'Stijn','Devolder',1,2230,83022723181),(123,'Maurice','Dewaele',6,2320,84030432136),(124,'Alfons','De Wolf',1,2420,63022042195),(125,'Rudy','Dhaenens',2,2530,82011953175),(126,'Michel','D\'Hooghe',6,2630,64030763183),(127,'Ludo','Dierckxsens',2,2730,75042673131),(128,'Frans','Dictus',2,2810,61121781180),(129,'Lomme','Driessens',2,2910,74030891189),(130,'Gustave','Drioul',1,2010,73020101175),(131,'Marcel','Dupont',3,2110,82011711186),(132,'Niko','Eeckhout',3,2230,82012723219),(133,'Nico','Emonds',2,2310,70112131205),(134,'Peter','Farazijn',3,2430,73020343174),(135,'Herman','Frison',3,2510,62012551183),(136,'Henri','Garnier',1,2620,82012462232),(137,'Frans','Gielen',3,2710,81120171159),(138,'Romain','Gijssels',2,2830,84031183192),(139,'Walter','Godefroot',3,2910,75040891171),(140,'Dries','Govaerts',2,2030,63022303202),(141,'Sylvain','Grysolle',6,2130,75041113162),(142,'Roger','Gyselinck',6,2210,64030821197),(143,'Paul','Haghedooren',3,2330,65041833202),(144,'Alfred','Hamerlinck',6,2420,66052342221),(145,'Louis','Hardiquest',3,2520,75041152168),(146,'Emile','Hardy',2,2630,60110463238),(147,'Marcel','Hendrikx',1,2730,74032373186),(148,'Joseph','Hoevenaers',2,2820,76050382203),(149,'Kevin','Hulsmans',2,2930,63022793164),(150,'Raymond','Impanis',2,2020,84031202200),(151,'Paul','In\'t',3,2120,88092512177),(152,'Willy','In\'t',3,2220,69101322213),(153,'Marcel','Janssens',2,2330,64031533247),(154,'Benjamin','Javaux',1,2410,84030941213),(155,'Karel','Kaers',2,2530,80111653184),(156,'Francis','Kemplaire',6,2610,76051661197),(157,'Norbert','Kerckhove',6,2710,86050471213),(158,'Désiré','Keteleer',1,2830,84031183212),(159,'Marcel','Kint',3,2920,80111392197),(160,'Firmin','Lambot',3,2010,72010101233),(161,'Roger','Lambrecht',3,2110,84032011250),(162,'Eric','Leman',3,2230,79102423256),(163,'Camille','Leroy',2,2330,62011233223),(164,'Roland','Liboton',3,2420,63022042235),(165,'Jules','Lowie',6,2530,80111653194),(166,'André','Lurquin',3,2620,82012462262),(167,'Henri','\'Rik\'',4,2730,70112073241),(168,'Pierrot','Machiels',2,2830,85041183194),(169,'André','Maelbrancke',6,2930,66050293223),(170,'Freddy','Maertens',2,2030,64032303204),(171,'Romain','Maes',2,2120,60112512189),(172,'Sylvère','Maes',2,2220,71121322177),(173,'Joseph','Marchand',5,2330,64031533267),(174,'René','Martens',2,2420,61122042217),(175,'Jacques','Martin',3,2510,63022551195),(176,'Emile','père',6,2620,79102162182),(177,'Florent','Mathieu',4,2720,64031272183),(178,'Nico','Mattan',3,2810,60111781258),(179,'Filip','Meirhaeghe',6,2920,85041992244),(180,'Axel','Merckx',1,2010,70110101225),(181,'Eddy','Merckx',1,2110,80111711208),(182,'André','Messelis',2,2230,83022723241),(183,'Maurice','Meuleman',3,2330,65041533249),(184,'Eloi','Meulenberg',2,2420,64032342220),(185,'Frans','Mintjens',2,2530,83021953207),(186,'Yvo','Molenaers',2,2610,72011661242),(187,'Maurice','Mollin',3,2710,83020171237),(188,'Arthur','Mommerency',2,2820,76050382243),(189,'Jean-Pierre','Monséré',6,2920,88071692274),(190,'Willy','Monty',2,2030,60112003259),(191,'Sammie','Moreels',2,2120,63022812202),(192,'Alfred','Mottard',5,2220,73021622218),(193,'Ernest','Mottart',3,2320,83020432234),(194,'Louis','Mottiat',3,2420,62012042196),(195,'Johan','Museeuw',6,2520,72010852212),(196,'Wilfried','Nelissen',3,2630,66050763197),(197,'François','Neuville',6,2730,76052373277),(198,'André','Noyelle',6,2820,72012882242),(199,'Guy','Nulens',2,2910,69100591225),(200,'Nick','Nuyens',2,2010,70110101245),(201,'Sven','Nys',2,2110,77081411222),(202,'Stan','Ockers',2,2210,60110521292),(203,'Petrus','Oellibrandt',3,2330,67061833206),(204,'Valère','Ollivier',1,2430,74030343216),(205,'Eddy','Peelman',3,2520,71120852263),(206,'Edward','Peeters',2,2620,83022462274),(207,'Luc','Petitjean',3,2710,82010471278),(208,'Victor','\'Louis\'',4,2820,73022882224),(209,'Georges','Pintens',2,2920,84031692212),(210,'Théodore','Pirmez',3,2010,74030101227),(211,'Eddy','Planckaert',3,2120,64030312220),(212,'Jo','Planckaert',3,2220,72011922259),(213,'Walter','Planckaert',3,2320,86050732260),(214,'Willy','Planckaert',3,2420,65042342222),(215,'Michel','Pollentier',1,2520,76051152307),(216,'Léon','Poncelet',3,2630,62010763232),(217,'Louis','Proost',3,2710,81120171239),(218,'Robert','Protin',3,2810,62011781229),(219,'Albert','Ramon',3,2930,61122493310),(220,'Gaston','Rebry',2,2030,61122003261),(221,'Jens','Renders',2,2120,61122812301),(222,'Guido','Reybrouck',6,2210,64030821277),(223,'Marcel','Rijckaert',3,2310,75042431232),(224,'Albert','Ritserveldt',3,2430,77060643242),(225,'Bert','Roesems',2,2520,71120852283),(226,'Louis','Rolus',2,2630,60110463318),(227,'Georges','Ronsse',6,2710,83020171277),(228,'André','Rosseel',1,2820,72012882272),(229,'Félicien','Salmon',3,2910,74030591296),(230,'Léopold','Schaeken',3,2030,65042303236),(231,'Willy','Scheers',2,2120,62012812270),(232,'Alfons','Schepers',2,2230,84032723263),(233,'Joseph','Scherens',2,2330,64031533327),(234,'Jef','Scherens',2,2430,71120343246),(235,'Briek','Schotte',6,2520,72010852252),(236,'Frans','Schoubben',3,2610,74031661236),(237,'Léon','Scieur',1,2710,80110171287),(238,'Félix','Sellier',1,2820,72012882282),(239,'Edward','Sels',2,2920,80111392277),(240,'Albert','Sercu',4,2030,61122003281),(241,'Patrick','Sercu',4,2130,72010813256),(242,'Andy','de Smet',3,2220,71121622337),(243,'Joseph','Somers',2,2310,72012131246),(244,'Tom','Steels',2,2410,79100941262),(245,'Ernest','Sterckx',1,2520,73020852331),(246,'Lucien','Storme',6,2610,72011361309),(247,'Tom','Stubbe',6,2710,79100171325),(248,'Roger','Swerts',2,2810,61121781300),(249,'Arthur','Targez',3,2910,72010591275),(250,'Andrei','Tchmil',1,2010,72010101323),(251,'Emmanuel','Thoma',2,2130,73020813335),(252,'Philippe','Thys',2,2220,72011322313),(253,'Hector','Tiberghien',3,2320,86050732300),(254,'Léon','Tommies',2,2420,61122042297),(255,'Lode','Troonbeeckx',1,2530,85042253311),(256,'Greg','Van Avermaet',3,2610,76051961290),(257,'Armand','Van Bruaene',6,2730,77062673302),(258,'Georges','Vanconingsloo',4,2820,80090682291),(259,'Léon','Van Daele',6,2910,73020891347),(260,'Charles','Van Den Born',3,2010,79080701317),(261,'Frank','Vandenbroucke',6,2120,68070612345),(262,'Odiel','Vanden Meerschaut',3,2230,62110823525),(263,'Eric','Vanderaerden',3,2310,76052731334),(264,'Kurt','Van de Wouwer',1,2420,67062642306),(265,'Richard','Van Genechten',3,2520,80091452335),(266,'Martin','Van Geneugden',3,2620,89080262282),(267,'Cyrille','Van Hauwaert',3,2710,89080771329),(268,'Maurice','Van Herzele',6,2830,88071483300),(269,'Jules','Van Hevel',1,2910,74030891329),(270,'Edwig','Van Hooydonck',6,2020,88071802291),(271,'Lucien','Van Impe',6,2130,74031113320),(272,'Henri','Van Kerkhove',6,2210,67061121333),(273,'Rik','Van Linden',3,2320,83020732307),(274,'Rik','Van Looy',2,2430,71120343286),(275,'Willy','Vannitsen',3,2510,64032851357),(276,'Peter','Van Petegem',2,2630,66051063367),(277,'Peter','Van Santvliet',3,2720,68071872351),(278,'Victor','Van Schil',1,2810,65042081295),(279,'Herman','van Springel',1,2910,78071191317),(280,'Rik','Van Steenbergen',3,2010,78071001358),(281,'Guillaume','Van Tongerloo',4,2120,72110612350),(282,'Noël','Vantyghem',2,2210,63020821365),(283,'Rik','Verbrugghe',6,2320,83020732317),(284,'Auguste','Verdyck',6,2420,64032042327),(285,'Jozef','Verhaert',3,2530,83021953307),(286,'René','Vermandel',1,2610,73021661314),(287,'Stive','Vermaut',3,2720,62011272349),(288,'Adolf','Verschueren',3,2830,86051483376),(289,'Constant','Verschueren',3,2930,69080293558),(290,'Johan','Verstrepen',3,2020,85041502305),(291,'Félicien','Vervaecke',6,2110,87062011296),(292,'Julien','Vervaecke',6,2210,65040821319),(293,'Edward','Vissers',2,2320,83020432334),(294,'Lucien','Vlaemynck',6,2410,85041241318),(295,'André','Vlaeyen',3,2520,72010852312),(296,'Jean','Vliegen',3,2620,81122462336),(297,'Luc','Wallays',2,2720,60111272331),(298,'René','Walschot',3,2830,82011183311),(299,'Jean-Marie','Wampers',2,2920,87061692315),(300,'Robert','Wancour',1,2020,83021202378),(301,'Bart','Wellens',2,2120,61122812381),(302,'Wilfried','Wesemael',1,2230,86052723374),(303,'Wouter','Weylandt',3,2330,64031533397),(304,'Marc','Wauters',2,2420,61122042347),(305,'Daniel','Willems',2,2520,73020852391),(306,'Jozef','Wouters',2,2620,82012462402),(307,'Hendrik','Desmet',3,2710,83020171357),(308,'Hendrik','Desmet',3,2810,63021781388),(309,'Gerard','Desmet',3,2910,72010591335);

CREATE TABLE offertes (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  voornaam varchar(50) NOT NULL,
  familienaam varchar(50) NOT NULL,
  emailadres varchar(50) NOT NULL,
  aangemaakt date NOT NULL,
  oppervlakte int unsigned NOT NULL
);

CREATE TABLE users (
  username varchar(50) NOT NULL primary key,
  `password` varchar(255) NOT NULL,
  enabled tinyint NOT NULL default 1
);

INSERT INTO users(username,`password`) VALUES
('averell','{bcrypt}$2a$10$HI7LCswjUQmLWvKRyJfVA.P9ZQ3nNKi4GWfW6MKJwTCbeK9MWU.Oi'),
('joe','{bcrypt}$2a$10$3DPuiwzO.I2UYggelBe8NuCHdd7Jblz2cu8K0ZkkguQZYnCIA4u5O');

CREATE TABLE authorities (
  username varchar(50) NOT NULL,
  authority varchar(50) NOT NULL,
  CONSTRAINT authoritiesUsers FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO `authorities` VALUES ('averell','helpdeskmedewerker'),('averell','magazijnier'),('joe','manager');

CREATE TABLE gebruikers (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  naam varchar(50) NOT NULL,
  paswoord varchar(255) NOT NULL,
  actief tinyint NOT NULL default 1,
  KEY naam(naam)
);

INSERT INTO gebruikers(naam,paswoord) VALUES
('joe','{bcrypt}$2a$10$3DPuiwzO.I2UYggelBe8NuCHdd7Jblz2cu8K0ZkkguQZYnCIA4u5O'),
('averell','{bcrypt}$2a$10$HI7LCswjUQmLWvKRyJfVA.P9ZQ3nNKi4GWfW6MKJwTCbeK9MWU.Oi');

CREATE TABLE rollen (
  id int unsigned NOT NULL AUTO_INCREMENT primary key,
  naam varchar(50) NOT NULL unique
);

insert into rollen(naam) values ('manager'),('helpdeskmedewerker'),('magazijnier');


CREATE TABLE gebruikersrollen (
  gebruikerid int unsigned NOT NULL,
  rolid int unsigned NOT NULL,
  PRIMARY KEY (gebruikerid,rolid),
  CONSTRAINT gebruikersrollenRollen FOREIGN KEY (rolid) REFERENCES rollen(id),
  CONSTRAINT gebruikersrollenGebruikers FOREIGN KEY (gebruikerid) REFERENCES gebruikers(id)
);

INSERT INTO `gebruikersrollen` VALUES (1,1),(2,2),(2,3);

create user if not exists cursist identified by 'cursist';
grant select,insert,update,delete on filialen to cursist;
grant select,insert on offertes to cursist;
grant select on werknemers to cursist;
grant select on users to cursist;
grant select on authorities to cursist;
grant select on gebruikers to cursist;
grant select on rollen to cursist;
grant select on gebruikersrollen to cursist;
