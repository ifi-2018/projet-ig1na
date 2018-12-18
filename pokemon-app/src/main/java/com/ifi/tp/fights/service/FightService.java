package com.ifi.tp.fights.service;

import com.ifi.tp.fights.bo.Fight;
import com.ifi.tp.trainers.bo.Trainer;

public interface FightService {
    Fight fight(Trainer trainer1, Trainer trainer2);
    Iterable<Fight> getFights();
}
