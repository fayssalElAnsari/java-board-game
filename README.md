# l2s4-projet-2021 - Groupe 3

# Equipe

- Aya LAKEHAL
- Mehdi BOUTAB  
- Fayssal EL ANSARI
- Zakaria HASSEIN BEY

# Sujet

[Le sujet 2021](https://www.fil.univ-lille1.fr/portail/index.php?dipl=L&sem=S4&ue=Projet&label=Documents)

# Livrables

## Livrable 1
Nous avons réalisé le diagramme necessaire qui représente les deux jeux .

### Atteinte des objectifs
faire les classes necessaire et deviser le travail entre les membres de groupe .

### Difficultés restant à résoudre

## Livrable 2
Nous avons réalisé la classe :
 * Player qui représente un joueur et chaque joueur possède un nombre limité d'or.
 * Game qui représente le jeu 
 * Unit 
 * Map qui représente la table du jeu.
 * Tile qui réprésente les différents tuiles 

### Atteinte des objectifs

### Difficultés restant à résoudre

## Livrable 3
- Cette semaine on fait un changement du nom de la classe d'unité en Personnage (personnage) pour mieux correspondre à la description donnée dans l'énoncé.
- Suppression de la classe de soldat car elle est remplacée par simplement un int dans la classe Army
- Changement du nom de certaines méthodes pour mieux s'adapter au diagramme UML
Suppression des méthodes répétées dans les classes héritées
### Atteinte des objectifs
faire presque toutes les classes necessaire pour le jeu
### Difficultés restant à résoudre
pouvoir affichée l'interface graphique (map)

on a pu réaliser les deux condition pour pouvoir créer "Map" qui sont 
 • le plateau doit comporter au minimum deux tiers de tuiles de type océan 
 • toutes les tuiles de type montagne, plaine, désert ou forêt doivent au moins avoir une tuile adjacente qui n’est pas
de type océan.
On a fait un changement au niveau de packetage .
Pour faire les tests il suffit de lancer le jeu en Map et on obtient :

<details>
  <summary>
    output du terminal:
  </summary>

    1: war; 2: farm
    Choose game type:> 
    1
    User chosen: game.WarGame
    (1,1) has 4 ocean tiles.

    (2,1) has 3 ocean tiles.

    (3,1) has 3 ocean tiles.

    (4,1) has 4 ocean tiles.

    (5,1) has 3 ocean tiles.

    (6,1) has 3 ocean tiles.

    (1,2) has 3 ocean tiles.

    (2,2) has 2 ocean tiles.

    (1,3) has 3 ocean tiles.

    (2,3) has 3 ocean tiles.

    (3,3) has 3 ocean tiles.

    (4,3) has 3 ocean tiles.

    (5,3) has 3 ocean tiles.

    (6,3) has 3 ocean tiles.

    (1,4) has 3 ocean tiles.

    (2,4) has 2 ocean tiles.

    (1,5) has 3 ocean tiles.

    (2,5) has 3 ocean tiles.

    (3,5) has 3 ocean tiles.

    (4,5) has 4 ocean tiles.

    (5,5) has 3 ocean tiles.

    (6,5) has 3 ocean tiles.

    (1,6) has 3 ocean tiles.

    (2,6) has 2 ocean tiles.

    ROUND: 1 OF 10

    fayssal has 15 gold; and 0 workers.

    aya has 15 gold; and 0 workers.

    mehdi has 15 gold; and 0 workers.

    ziko has 15 gold; and 0 workers.

    *===* testMap *===*

        0    1    2    3    4    5    6    7  

    0 [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    1 [O, *] [F, *] [M, *] [O, *] [F, *] [M, *] [D, *] [O, *] 

    2 [O, *] [M, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    3 [O, *] [P, *] [P, *] [D, *] [M, *] [M, *] [D, *] [O, *] 

    4 [O, *] [D, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *]

    5 [O, *] [P, *] [M, *] [O, *] [M, *] [M, *] [F, *] [O, *] 

    6 [O, *] [F, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *]

    7 [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    It's fayssal's turn: 

    ROCK: 0; CORN: 0; SAND: 0; WOOD: 0; 

    1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP

    make your choice :> 1

    chose a position :> 1,1 // c'est dans ce niveau où normalement le joeur prend la position dans la cellule (1,1) mais ça ne fonctionne pas pour l'instant : 

    ROUND: 1 OF 10

    fayssal has 15 gold; and 0 workers.

    aya has 15 gold; and 0 workers.

    mehdi has 15 gold; and 0 workers.

    ziko has 15 gold; and 0 workers.

    *===* testMap *===*

        0    1    2    3    4    5    6    7  

    0 [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    1 [O, *] [F, *] [M, *] [O, *] [F, *] [M, *] [D, *] [O, *] 

    2 [O, *] [M, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    3 [O, *] [P, *] [P, *] [D, *] [M, *] [M, *] [D, *] [O, *] 

    4 [O, *] [D, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    5 [O, *] [P, *] [M, *] [O, *] [M, *] [M, *] [F, *] [O, *] 

    6 [O, *] [F, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    7 [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] [O, *] 

    It's aya's turn: 

    ROCK: 0; CORN: 0; SAND: 0; WOOD: 0; 

    1 => DEPLOY; 2 => EXCHANGE; 3 => SKIP
</details>


## Livrable 4
* les methodes unecessaires ont etes supprimes
* les methodes repetes entre les superclass et les subclasses ont etes suprimme
* utilisation des methodes abstract pour definir des fonctionalites communs antre les subclasses
* division de la classe player en 2 subclasses PlayerWar et PlayerFarm.
* le programme maintenant nous donne comme output: <br /> 
  ![une gif contenant l'execution](https://gitlab-etu.fil.univ-lille1.fr/lakehala/l2s4-projet-2021/-/raw/master/images/2021-04-09%2010-25-34.gif)
* creation d'un diagramme uml final
* ajoute des commentaires pour toutes les methodes
* extraction des constantes (ctrl+1 sur eclipse) pour mieux organiser le code
* ajout des tests map, game, gameWar
* suppression des fichier non necessaires du depot git.


### Atteinte des objectifs
finir la classe Map
### Difficultés restant à résoudre
On a eu des diffecultés au niveu de tuiles pour qui'elles doivent au moins avoir une tuile adjacente qui n’est pas
de type océan.
# Journal de bord

## Semaine 1
on a essayé de faire le diagramme UML qui représente notre projet pour meiux comprendre et faciliter le travail.
## Semaine 2
On a eu du mal pour comprendre si certaine classes sont necessaire où pas comme la class soldiers et army et c'est quoi la nature de chaqu'une 
## Semaine 3
Dans l'evolution de faire des nouvelles methodes pour pouvoir affficher le plateau avec les tuiles et les joeurs. 
## Semaine 4
danscette semaine on a finit la classe Map pour pouvoir continuer le reste des condtion pour chaque jeux . 
## Semaine 5

## Semaine 6

## Semaine 7
* suppression des methodes unecessaires/repetees.
* changement de la visibilite de quelque variables de publique vers privees/protected
* correction du design des classes utilises dans le projet
  
## Semaine 8
* todo: actions des characters
* todo: les tests de quelques classes
* todo: ajoute de couleurs a la class map pour chaque systeme d'exploitation.
* test de map qui s'affiche maintenant avec des couleurs:  
![map](https://gitlab-etu.fil.univ-lille1.fr/lakehala/l2s4-projet-2021/-/raw/master/images/map_with_color.png)

    * des fois les couleurs ne marchent pas a cause du manque du support code ASCII dans la console utilisee.
* apres ajoute de couleurs et ajout de l'analyse de l'entrée utilisateur:
![gif contenant un test apres modifications](https://gitlab-etu.fil.univ-lille1.fr/lakehala/l2s4-projet-2021/-/raw/master/images/2021-04-10%2005-03-41.gif)
* demonstration actions support:
![gif support](https://gitlab-etu.fil.univ-lille1.fr/lakehala/l2s4-projet-2021/-/raw/master/images/2021-04-11%2018-12-54.gif)
* demonstration actions attack:
![gif attack](https://gitlab-etu.fil.univ-lille1.fr/lakehala/l2s4-projet-2021/-/raw/master/images/2021-04-11%2018-17-04.gif)
## Semaine 9

## Semaine 10

## Semaine 11

## Semaine 12
