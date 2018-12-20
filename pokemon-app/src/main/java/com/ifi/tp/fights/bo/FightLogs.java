package com.ifi.tp.fights.bo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FightLogs {

    @Id
    @GeneratedValue
    private Integer id;
    @ElementCollection
    private List<String> logs;

    public FightLogs() {
        this.logs = new ArrayList<>();
    }

    public void addLog(String log) {
        System.out.println(log);
        this.logs.add(log);
    }

    public int getId() { return this.id; }

    public void setId(int id ) { this.id = id; }

    public List<String> getLogs() {
        return this.logs;
    }
}
