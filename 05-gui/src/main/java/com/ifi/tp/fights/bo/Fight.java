package com.ifi.tp.fights.bo;

import com.ifi.tp.trainers.bo.Trainer;

public class Fight {

    private int id;
    private Trainer trainer1, trainer2, winner, loser;
    private FightLogs logs;

    public Fight() {}

    public Fight(Trainer trainer1, Trainer trainer2, Trainer winner, Trainer loser, FightLogs logs) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.winner = winner;
        this.loser = loser;
        this.logs = logs;
    }

    public int getId() { return this.id; }

    public Trainer getTrainer1() {
        return this.trainer1;
    }

    public Trainer getTrainer2() {
        return this.trainer2;
    }

    public Trainer getWinner() {
        return this.winner;
    }

    public Trainer getLoser() {
        return this.loser;
    }

    public FightLogs getLogs() { return this.logs; }
}
