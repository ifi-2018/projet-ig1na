package com.ifi.tp.fights.bo;

import java.util.ArrayList;
import java.util.List;

public class FightLogs {

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
