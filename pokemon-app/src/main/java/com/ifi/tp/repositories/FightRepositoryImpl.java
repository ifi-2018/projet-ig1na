package com.ifi.tp.repositories;

import com.ifi.tp.fights.bo.Fight;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FightRepositoryImpl implements FightRepository {

    private List<Fight> fights;

    public FightRepositoryImpl() { fights = new ArrayList<>(); }

    @Override
    public List<Fight> findFightsByTrainerName(String name) {
        List<Fight> fightsByTrainerName = new ArrayList<>();
        for(Fight f : fights) {
            if(f.getTrainer1().equals(name) || f.getTrainer2().equals(name)) {
                fightsByTrainerName.add(f);
            }
        }
        return fightsByTrainerName;
    }

    @Override
    public List<Fight> findAllFights() {
        return fights;
    }

    @Override
    public Fight findFightById(int id) {
        for(Fight f : fights) {
            if(f.getId() == id) return f;
        }
        return null;
    }

    @Override
    public void save(Fight f) {
        this.fights.add(f);
    }
}
