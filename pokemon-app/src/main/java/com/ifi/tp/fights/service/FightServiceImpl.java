package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.repositories.FightRepository;
import com.ifi.tp.trainers.bo.Pokemon;
import com.ifi.tp.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightServiceImpl implements FightService {

    private final FightRepository fightRepository;

    @Autowired
    public FightServiceImpl(FightRepository fightRepository) {
        this.fightRepository = fightRepository;
    }

    @Override
    public Fight fight(Trainer trainer1, Trainer trainer2) {
        List<Pokemon> team1 = trainer1.getTeam();
        List<Pokemon> team2 = trainer2.getTeam();

        while(!team1.isEmpty() && !team2.isEmpty()) {
            Pokemon p1 = team1.get(0);
            Pokemon p2 = team2.get(0);

            Pokemon loser;
            if(p1.getPokemonType().getStats().getSpeed() > p2.getPokemonType().getStats().getSpeed()) {
                loser = pokemonFight(p1, p2);
            } else {
                loser = pokemonFight(p2, p1);
            }

            if(team1.contains(loser)) team1.remove(loser);
            else if(team2.contains(loser)) team2.remove(loser);
            else System.out.println(loser);
        }

        Trainer winner, loser;
        if(team1.isEmpty()) { winner = trainer2; loser = trainer1; }
        else { winner = trainer1; loser = trainer2; }

        Fight fight = new Fight(trainer1.getName(), trainer2.getName(), winner.getName(), loser.getName());
        this.fightRepository.save(fight);
        return fight;
    }

    public Iterable<Fight> getFights() {
        return this.fightRepository.findAll();
    }

    private Pokemon pokemonFight(Pokemon first, Pokemon second) {
        while(first.getHp() > 0 && second.getHp() > 0) {
            int dmg = first.getStats().getAttack() - second.getStats().getDefense();
            if(dmg <= 0) dmg = 1;

            second.setHp(second.getHp() - dmg);
            System.out.println(second);
            if(second.getHp() <= 0) {
                return second;
            }

            dmg = second.getStats().getAttack() - first.getStats().getDefense();
            if(dmg <= 0) dmg = 1;

            first.setHp(first.getHp() - dmg);
            System.out.println(first);
            if(first.getHp() <= 0) {
                return first;
            }
        }
        return null;
    }
}
