package com.ifi.tp.trainers.service;

import com.ifi.tp.trainers.bo.Trainer;

public interface TrainerService {

    Iterable<Trainer> getAllTrainers();

    Trainer getTrainer(String name);

}
