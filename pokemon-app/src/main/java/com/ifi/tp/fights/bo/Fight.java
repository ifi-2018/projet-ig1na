package com.ifi.tp.fights.bo;

import javax.persistence.*;
import java.util.List;

@Entity
public class Fight {

    @Id
    @GeneratedValue
    private int id;
    private String trainer1, trainer2, winner, loser;
    @ElementCollection(targetClass = String.class)
    private List<String> logs;

    public Fight() {}

    public Fight(String trainer1, String trainer2, String winner, String loser, List<String> logs) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.winner = winner;
        this.loser = loser;
        this.logs = logs;
    }


    public int getId() { return id; }

    public String getTrainer1() {
        return this.trainer1;
    }

    public String getTrainer2() {
        return this.trainer2;
    }

    public String getWinner() {
        return this.winner;
    }

    public String getLoser() {
        return this.loser;
    }

    public List<String> getLogs() { return this.logs; }
}
