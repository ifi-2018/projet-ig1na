package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.fights.bo.FightLogs;
import com.ifi.tp.repositories.FightRepository;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;
    private FightLogs logs;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @Override
    public Fight fight(Trainer trainer1, Trainer trainer2) {
        List<Pokemon> team1 = trainer1.getTeam();
        List<Pokemon> team2 = trainer2.getTeam();

        logs = new FightLogs();
        logs.addLog("Combat entre " + trainer1.getName() + " et " + trainer2.getName());

        while(!team1.isEmpty() && !team2.isEmpty()) {
            Pokemon p1 = team1.get(0);
            Pokemon p2 = team2.get(0);
            Pokemon loser;

            logs.addLog(trainer1.getName() + " envoie " + p1.getPokemonType().getName() + " !");
            logs.addLog(trainer2.getName() + " envoie " + p2.getPokemonType().getName() + " !");

            if(p1.getPokemonType().getStats().getSpeed() > p2.getPokemonType().getStats().getSpeed()) {
                logs.addLog(p1.getPokemonType().getName() + " attaque en premier !");
                loser = pokemonFight(p1, p2);
            } else {
                logs.addLog(p2.getPokemonType().getName() + " attaque en premier !");
                loser = pokemonFight(p2, p1);
            }

            if(team1.contains(loser)) team1.remove(loser);
            else if(team2.contains(loser)) team2.remove(loser);
        }

        Trainer winner, loser;
        if(team1.isEmpty()) { winner = trainer2; loser = trainer1; }
        else { winner = trainer1; loser = trainer2; }

        Fight fight = new Fight(trainer1, trainer2, winner, loser, logs);
        this.fightRepository.save(fight);
        return fight;
    }

    @Override
    public List<Fight> getFights() {
        return this.fightRepository.findAllFights();
    }

    @Override
    public Fight getFightFromId(int id) {
        return this.fightRepository.findFightById(id);
    }

    private Pokemon pokemonFight(Pokemon first, Pokemon second) {
        String nameFirst = first.getPokemonType().getName();
        String nameSec = second.getPokemonType().getName();

        while(first.getHp() > 0 && second.getHp() > 0) {
            int dmg = first.getStats().getAttack() - second.getStats().getDefense();
            if(dmg <= 0) dmg = 1;

            logs.addLog(nameFirst + " attaque et inflige " + dmg + " dégats à " + nameSec);
            second.setHp(second.getHp() - dmg);

            if(second.getHp() <= 0) {
                logs.addLog(nameSec + " est K.O !");
                return second;
            }

            dmg = second.getStats().getAttack() - first.getStats().getDefense();
            if(dmg <= 0) dmg = 1;

            logs.addLog(nameSec + " attaque et inflige " + dmg + " dégats à " + nameFirst);
            first.setHp(first.getHp() - dmg);

            if(first.getHp() <= 0) {
                logs.addLog(nameFirst + " est K.O !");
                return first;
            }
        }
        return null;
    }
}
