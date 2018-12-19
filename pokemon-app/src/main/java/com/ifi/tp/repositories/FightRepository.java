package com.ifi.tp.repositories;

import com.ifi.tp.fights.bo.Fight;

import java.util.List;

public interface FightRepository {
    List<Fight> findFightsByTrainerName(String name);
    List<Fight> findAllFights();
    Fight findFightById(int id);
    void save(Fight f);
}
