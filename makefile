.PHONY:all clean

all: classes game doc gameTest

#Compilation des classes :

game : src/game/*.java src/game/character/*.java src/game/character/unit/*.java src/game/util*.java src/game/util/tile/*.java classes
   javac -cp classes src/game/*.java src/game/character/*.java src/game/character/unit/*.java src/game/util*.java src/game/util/tile/*.java -d classes

#Compilation des classes de test :

gameTest : test/game/*.java test/game/character/*.java test/game/character/*.java test/game/character/unit/*.java test/game/util/*.java game
	javac -cp test-1.7.jar:classes test/game/*.java test/game/character/*.java test/game/character/*.java test/game/character/unit/*.java test/game/util/*.java -d classes

#Création de la documentation :

doc : src/game/*.java src/game/character/*.java src/game/character/unit/*.java src/game/util*.java src/game/util/tile/*.java
	javadoc -d docs -author -sourcepath src -subpackages game
   
#Commandes d'éxecution :
#Pour les test :

farmGameTest : gameTest
   java -jar test-1.7.jar -cp classes game.FarmGameTest

gameTest : gameTest
   java -jar test-1.7.jar -cp classes game.GameTest

warGameTest : gameTest
   java -jar test-1.7.jar -cp classes game.WarGameTest

playerTest : gameTest
   java -jar test-1.7.jar -cp classes game.character.PlayerTest

playerTestFarm : gameTest
   java -jar test-1.7.jar -cp classes game.character.PlayerTestFarm

playerTestWar : gameTest
   java -jar test-1.7.jar -cp classes game.character.PlayerTestWar
   
armyTest : gameTest
   java -jar test-1.7.jar -cp classes game.character.unit.ArmyTest
   
unitTest : gameTest
   java -jar test-1.7.jar -cp classes game.character.unit.UnitTest
   
workerTest : gameTest
   java -jar test-1.7.jar -cp classes game.util.WorkerTest

mapTest : gameTest
   java -jar test-1.7.jar -cp classes game.util.WorkerTest

PositionTest : gameTest
   java -jar test-1.7.jar -cp classes game.util.WorkerTest
   
TileTest : gameTest
   java -jar test-1.7.jar -cp classes game.character.unit.WorkerTest  
#Affiche les tests un par un à chaque fermeture d'une fenêtre Junit:
farmGameTest gameTest warGameTest playerTest playerTestFarm playerTestWar armyTest unitTest workerTest


#Commande de nettoyage :
clean:
	rm -rf classes docs rps.jar