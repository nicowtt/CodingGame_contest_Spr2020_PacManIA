# Coding Game compétition "Pacman" -> Spring Challenge 2020
Le Spring Challenge 2020 est une compétition de creation d'IA (Bot) mondiale d'une durée d'un mois
ouverte à tous et à tout les languages de programmation.
Il y a 6 ligues avec 1 bot (boss) pour chaque niveau.

![alt text](https://github.com/nicowtt/CodingGame_contest_Spr2020_PacManIA/blob/master/pacman.png)

### Techniques utilisés pour ce projet:
- Language JAVA 8.
- Programation Orienté Objet (POO).

### Explication du jeux
- Une map aléatoire avec des tunnels possible en latéral.
![alt text](https://github.com/nicowtt/CodingGame_contest_Spr2020_PacManIA/blob/master/map.png)
- De 1 à 5 pacmans par joueur.
- 5 grosses pastilles -> valeur 10 points
- Tous les chemins sont remplis par d'autres pastilles -> valeur 1 point.
- Les pacMans peuvent prendre du speed -> 2 cases pour 1 chez le concurent.
- Les pacMan peuvent se transformer en pierre/ciseaux/papier pour jouer a shiFouMi lors de collision.
On peut donc manger l'adversaire. 
- Celui qui à la majorité de point gagne!

### Algorithme et fonctions développé (stratégie):

    * Mes pacs se dirigent tous sur la grosse pastille la plus proche.
        - plusieurs pac peuvent se diriger vers la même grosse pastilles.
    * Ensuite mes pacs se dirigent vers la petites pastille la plus proche.
    (Il ne peuvent pas se diriger vers une pastille verouillé par un autre pac)
    * Lorsqu'il n'y a plus de pastille visible par un pac (le pac voie que les pastilles du chemin visible):
        - si un des pac a de la visiblité sur une pastille, le pac se dirige vers la plus proche.
    * Si aucun pac ne voient de pastilles:
        - mouvement aléatoire en évitant la derniere cellule.
        - il peut prendre les tunnels.
    

![alt text](https://github.com/nicowtt/CodingGame_contest_SubMarineIA/blob/master/Tof3.jpg)

### Contrainte:
Response time first turn ≤ 1000 ms
Response time per turn ≤ 50 ms

### Les règles officielles:
https://www.codingame.com/ide/challenge/ocean-of-code

### Le code source:
https://github.com/CodinGameCommunity/ocean-of-code.

### Resultat:
Ceci est ma première compétition de code. (j'ai de la marge de progression... ;o)  
7 258 participants dans le monde!
2279 classés
mon résultat:
![alt text](https://github.com/nicowtt/CodingGame_contest_SubMarineIA/blob/master/Tof2.jpg)

### Dernière partie:
https://www.codingame.com/replay/452687072

### Impressions personnelles:
C'est un défis trés difficile... mais tellement fun!
La refactorisation du code quand ça devient de plus en plus complexe prend un peu de temp (mais ça ma évité de me perdre!).
J'ai beaucoup appris durant ce challenge.
