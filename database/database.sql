BEGIN TRANSACTION;
CREATE TABLE "Question" (
	`id_question`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`text`	TEXT
);

INSERT INTO `Question` VALUES(0,'Est-ce que tu veux être en intérieur ?');
INSERT INTO `Question` VALUES(1,'Veux tu mettre de l''argent dans ton activité ?');
INSERT INTO `Question` VALUES(2,'Veux tu dépenser de l''argent ?');
INSERT INTO `Question` VALUES(3,'Veux tu faire une activité physique ?');
INSERT INTO `Question` VALUES(4,'Veux tu faire du sport ?');
INSERT INTO `Question` VALUES(5,'Veux tu être devant un écran ?');
INSERT INTO `Question` VALUES(6,'Veux-tu être accompagné ?');
INSERT INTO `Question` VALUES(7,'Es tu seul actuellement ?');
INSERT INTO `Question` VALUES(8,'Souhaites tu faire quelque chose d''utile ?');
INSERT INTO `Question` VALUES(9,'Souhaites tu faire une activité artistique ?');
INSERT INTO `Question` VALUES(10,'Est-ce que tu veux manger ?');
INSERT INTO `Question` VALUES(11,'Est-ce que tu as faim ?');
INSERT INTO `Question` VALUES(12,'Veux tu de détendre ?');
INSERT INTO `Question` VALUES(13,'Es-tu sur les nerfs ?');
INSERT INTO `Question` VALUES(14,'Est-ce qu''il fait beau ?');
INSERT INTO `Question` VALUES(15,'Est-ce qu''il pleut ?');


CREATE TABLE `Answer` (
	`id_answer`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`text1`	TEXT,
	`text2`	TEXT,
	`text3`	TEXT
);
INSERT INTO `Answer` VALUES(0,'Oui','Carrément','Ok');
INSERT INTO `Answer` VALUES(1,'Non','Tu rêves','No way !');
INSERT INTO `Answer` VALUES(2,'Peu importe','Je m''en fiche','Je passe');

CREATE TABLE `Activity` (
	`id_activity`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`text`	TEXT,
	`favorite`	INTEGER,
	`discover`	INTEGER
);
INSERT INTO `Activity` VALUES(0,'Va mater Derrick sur France 2',0,0);
INSERT INTO `Activity` VALUES(1,'Regarde les infos pour voir ce
 qu''il se passe dans le monde',0,0);
INSERT INTO `Activity` VALUES(2,'Lis l''intégrale du Seigneur des Anneaux, histoire de voir les différences avec le film',0,0);
INSERT INTO `Activity` VALUES(3,'Lis toi une bonne BD',0,0);
INSERT INTO `Activity` VALUES(4,'Prépare un gratin dauphinois',0,0);
INSERT INTO `Activity` VALUES(5,'Prépare un dessert pour ce soir',0,0);
INSERT INTO `Activity` VALUES(6,'Prends ton plus beau crayon et 
dessine moi un mouton',0,0);
INSERT INTO `Activity` VALUES(7,'Dessine la première chose
 qui te traverse l''esprit',0,0);
 INSERT INTO `Activity` VALUES(8,'Repeins le mur de ta chambre couleur 
taupe (c''est Valérie Damidot qui l''a dit)',0,0);
 INSERT INTO `Activity` VALUES(9,'Prends toi pour Baudelaire en écrivant un
poème pour ton amoureux(se) ...ou alors ta mère',0,0);
 INSERT INTO `Activity` VALUES(10,'Brode une écharpe pour ta grand-mère',0,0);
 INSERT INTO `Activity` VALUES(11,'Répare les chaussettes trouées que tu
caches au fond de ton tiroir',0,0);
 INSERT INTO `Activity` VALUES(12,'Construis la maison de tes rêves en
jouant au Sims 3',0,0);


CREATE TABLE `ActivityQuestion` (
	`id_activity`	INTEGER,
	`id_question`	INTEGER,
	`impact`	INTEGER
);

INSERT INTO `ActivityQuestion` VALUES(0,0,0);


COMMIT;
