SRC = \
	src/main/*.java \
	src/game/*.java \
	src/game/character/*.java \
	src/game/player/*.java \
	src/game/util/*.java \


all: cls doc guerre.jar agricole.jar

cls:
	javac $(SRC) -d classes

clean: 
	rm -fr docs
	rm -fr classes
	rm -f jar/*.jar

doc: 
	javadoc $(SRC) -d ./docs

guerre.jar: classes/game
	cd classes; jar cvfm ../jar/guerre.jar ../jar/manifest-WarMain game

agricole.jar: classes/game
	cd classes; jar cvfm ../jar/agricole.jar ../jar/manifest-FarmMain game