.PHONY:all clean

all: classes game doc gameTest

#Compilation des classes :

game : src/game/*.java src/game/character/*.java src/game/character/unit/*.java src/game/util*.java src/game/util/tile/*.java classes
   javac -cp classes src/game/*.java src/game/character/*.java src/game/character/unit/*.java src/game/util*.java src/game/util/tile/*.java -d classes

#Compilation des classes de test :

gameTest : test/game/*.java test/game/character/*.java test/game/character/*.java game
	javac -cp test-1.7.jar:classes test/pfc/*.java test/pfc/util/*.java -d classes

   
   
   
#Commande de nettoyage :
clean:
	rm -rf classes docs rps.jar