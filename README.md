# Coding Game compétition "Pacman" -> Spring Challenge 2020
Le Spring Challenge 2020 est une compétition de création d'IA (Bot) mondiale d'une durée d'un mois
ouverte à tous et à tout les languages de programmation.<br/>
Il y a 6 ligues avec 1 bot (boss) pour chaque niveau.<br/>
2 ligues bois, bronze, argent, or et enfin legend.

![alt text](https://github.com/nicowtt/CodingGame_contest_Spr2020_PacManIA/blob/master/pacman.png)

### Techniques utilisés pour ce projet:
- Language JAVA 8.
- Programation Orienté Objet (POO).

### Explication du jeux
- Une map aléatoire avec des tunnels possible en latéral. <br/>
![alt text](https://github.com/nicowtt/CodingGame_contest_Spr2020_PacManIA/blob/master/map.png)

- De 1 à 5 pacman(s) par joueur.
- 5 grosses pastilles -> valeur 10 points
- Tous les chemins sont remplis par d'autres pastilles -> valeur 1 point.
- Les pacMans peuvent prendre du speed -> 2 cases pour 1 chez le concurent. (cooldown 10 tours)
- Les pacMan peuvent se transformer en pierre/ciseaux/papier pour jouer a shiFouMi lors de collision. (cooldown 5 tours)
On peut donc manger l'adversaire. 
- Celui qui à la majorité de point gagne la partie!

### Algorithme et fonctions développées (stratégie):

    * Mes pacs se dirigent tous sur une grosse pastille la plus proche.
        - Plusieurs pac peuvent se déplacer vers la même grosse pastilles.
    * Ensuite mes pacs se dirigent vers la plus proche petite pastille.
    (Ils ne peuvent pas aller vers une pastille verrouillé par un autre pac)
    * Lorsqu'il n'y a plus de pastilles visible par un pac (le pac voit uniquement les pastilles du chemin visible):
        - Si un des pac à de la visiblité sur une pastille, les autres pacs se dirigent vers la plus proche.
    * Si aucune pastilles n'est visible par aucun pac:
        - Mouvement aléatoire en évitant la dernière cellule.
        - Possibilité de prendre les tunnels.


### Les règles officielles:
to be updated

### Le code source:
to be updated

### Resultat:
C'est ma deuxième participation dans une compétition de code.
13 699 inscription à travers le monde!
4955 qui ont soumis leur code.
mon résultat:<br/>

![alt text](https://github.com/nicowtt/CodingGame_contest_Spr2020_PacManIA/blob/master/result.png)

-> je suis classé 30/100

### Impressions personnelles:
Toujours trés agréable et difficile de crée une IA afin de battre d'autre concurents humain. Il y a beaucoup 
de temp à observer les parties afin de trouver une stratégie qui peut marcher.<br/>
J'ai pris beaucoup de plaisir à coder mes features !<br/>
Je n'ai pas refactorisé mon code. J'ai donc amélioré ma capacité et ma vision objet afin de ne pas me perdre.

J'ai réussi pour cette deuxième participation à me classer sur une ligue supérieure.<br/>
1er contest -> ligue bronze -> finish 42eme/100 <br/>
ici -> ligue argent! -> finish 30eme/100
