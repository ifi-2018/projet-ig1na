package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.repositories.FightRepository;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;
    private List<String> logs;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @Override
    public Fight fight(Trainer trainer1, Trainer trainer2) {
        List<Pokemon> team1 = trainer1.getTeam();
        List<Pokemon> team2 = trainer2.getTeam();

        logs = new ArrayList<>();
        logs.add("Combat entre " + trainer1.getName() + " et " + trainer2.getName());

        while(!team1.isEmpty() && !team2.isEmpty()) {
            Pokemon p1 = team1.get(0);
            Pokemon p2 = team2.get(0);
            Pokemon loser;

            logs.add(trainer1.getName() + " envoie " + p1.getPokemonType().getName() + " !");
            logs.add(trainer2.getName() + " envoie " + p2.getPokemonType().getName() + " !");

            if(p1.getPokemonType().getStats().getSpeed() > p2.getPokemonType().getStats().getSpeed()) {
                logs.add(p1.getPokemonType().getName() + " attaque en premier !");
                loser = pokemonFight(p1, p2);
            } else {
                logs.add(p2.getPokemonType().getName() + " attaque en premier !");
                loser = pokemonFight(p2, p1);
            }

            if(team1.contains(loser)) team1.remove(loser);
            else if(team2.contains(loser)) team2.remove(loser);
        }

        Trainer winner, loser;
        if(team1.isEmpty()) { winner = trainer2; loser = trainer1; }
        else { winner = trainer1; loser = trainer2; }

        this.logs.add(winner.getName() + " remporte le combat !");

        Fight fight = new Fight(trainer1.getName(), trainer2.getName(), winner.getName(), loser.getName(), logs);
        this.fightRepository.save(fight);
        return fight;
    }

    @Override
    public Iterable<Fight> getFights() {
        return this.fightRepository.findAll();
    }

    @Override
    public Fight getFightFromId(int id) { return this.fightRepository.findById(id).orElse(null); }

    private Pokemon pokemonFight(Pokemon first, Pokemon second) {
        String nameFirst = first.getPokemonType().getName();
        String nameSec = second.getPokemonType().getName();

        Pokemon loser = null;
        while(loser == null) {
           loser = pokemonAttack(first, second);
           if(loser == null) loser = pokemonAttack(second, first);
        }
        return loser;
    }

    private Pokemon pokemonAttack(Pokemon first, Pokemon second) {
        String nameFirst = first.getPokemonType().getName();
        String nameSec = second.getPokemonType().getName();

        int dmg = first.getStats().getAttack() - second.getStats().getDefense();
        if(dmg <= 0) dmg = 1;

        logs.add(nameFirst + " attaque et inflige " + dmg + " dégats à " + nameSec);
        second.setHp(second.getHp() - dmg);

        if(second.getHp() <= 0) {
            logs.add(nameSec + " est K.O !");
            return second;
        }

        return null;
    }
}
