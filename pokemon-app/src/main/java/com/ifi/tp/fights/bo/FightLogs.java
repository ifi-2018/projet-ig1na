package com.ifi.tp.fights.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FightLogs {

    @Id
    @GeneratedValue
    private int id;
    @ElementCollection
    private List<String> logs;

    public FightLogs() {
        this.logs = new ArrayList<>();
    }

    public void addLog(String log) {
        System.out.println(log);
        this.logs.add(log);
    }

    public List<String> getLogs() {
        return this.logs;
    }
}
