# LAFRANCE Antoine - Projet J2EE Pokémon

## Récupération du projet 

`git clone https://github.com/ifi-2018/projet-ig1na.git`

## Compilation et lancement du projet

### GUI

`cd projet-ig1na/05-gui`
`mvn package`
`java -jar target/tp-05-gui-1.0.0.jar`

### Fight

`cd projet-ig1na/fight`
`mvn package`
`java -jar target/fight-1.0-SNAPSHOT.jar`

## Utilisation de l'application

Se rendre à l'adresse http://localhost:9000

Pour démarrer un combat, cliquer sur Trainers, et choisir un trainer avec qui combattre, choisir ensuite l'adversaire, puis cliquer sur start, le combat démarre.

Pour voir l'historique des combats, cliquer sur Fights

Pour voir les combats d'un trainer, se rendre sur http://localhost:9000/trainers/fights/{name}
